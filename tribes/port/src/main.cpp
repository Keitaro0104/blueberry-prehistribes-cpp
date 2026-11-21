// main.cpp — the Windows shell: WinMain, argument parsing, the message
// pump, and the headless --dump / --bench modes used by CI smoke tests.
//
// Own implementation (not copied from any other port).
//
// Threading model (matches MIDP): the translated Game runs on its own
// thread (started by TribesMIDlet::startApp -> new Thread(midlet)), and
// this file only pumps Win32 messages and presents frames. A repaint is
// requested by the game thread via Canvas::repaint(); the callback below
// paints the current canvas into the 240x320 screen Surface and blits it
// to the window. That happens on the game thread, under the screen mutex,
// so the shell thread never touches the Surface except through WM_PAINT
// (which takes the same mutex).
//
// Headless modes (for CI, where there is no user and no PC to play on):
//   --dump <bmp> [seconds]   run without a window, then write the final
//                            frame to <bmp> (default 8 s: the splash
//                            takes 1+2+2 s, then the title screen shows).
//   --bench <seconds>        run without a window for N seconds and print
//                            how many frames were presented.
//   --trace                  print startup stages and progress lines.

#define WIN32_LEAN_AND_MEAN
#include <windows.h>

#include <shellapi.h>  // CommandLineToArgvW

#include <algorithm>
#include <atomic>
#include <chrono>
#include <cstdarg>
#include <cstdio>
#include <filesystem>
#include <string>
#include <thread>

#include "crashlog.h"
#include "display.h"
#include "engine.h"
#include "gen/classes.h"
#include "input.h"
#include "modloader.h"
#include "platform.h"

namespace {

Surface g_screen(240, 320);
std::atomic<long long> g_framesPresented{0};
bool g_trace = false;

void trace(const char* fmt, ...) {
   // Every stage line goes to tribes-log.txt (always); it also goes to
   // stdout when --trace was passed (the old behaviour).
   char buf[2048];
   va_list ap;
   va_start(ap, fmt);
   std::vsnprintf(buf, sizeof(buf), fmt, ap);
   va_end(ap);
   crashlog::log("%s", buf);
   if (!g_trace) return;
   std::fputs(buf, stdout);
   std::fflush(stdout);
}

// Called (on the game thread) whenever the game requests a repaint.
void presentFrame() {
   std::lock_guard<std::mutex> lock(screenMutex());
   Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
   if (canvas) canvas->paint(&g_screen);
   g_framesPresented++;
   if (g_winDisplay) g_winDisplay->present(g_screen);
}

bool dirHasGameData(const std::string& dir) {
   // The four files the port needs from the user's extracted JAR.
   static const char* kFiles[] = {"ma", "a", "sa", "pd0"};
   bool ok = true;
   for (const char* f : kFiles) {
      std::string path = dir + "\\" + f;
      DWORD attr = GetFileAttributesA(path.c_str());
      if (attr == INVALID_FILE_ATTRIBUTES) {
         std::fprintf(stderr, "missing game data file: %s\n", path.c_str());
         crashlog::log("data file %s: MISSING", f);
         ok = false;
      } else {
         std::error_code ec;
         auto size = std::filesystem::file_size(path, ec);
         if (ec) {
            crashlog::log("data file %s: present (size unknown)", f);
         } else {
            crashlog::log("data file %s: OK (%llu bytes)",
                          f, (unsigned long long)size);
         }
      }
   }
   return ok;
}

void printUsage() {
   std::printf(
       "Prehistoric Tribes (C++ port) -- bring your own JAR.\n"
       "Usage: tribes.exe [--data <dir>] [--saves <dir>] [--windowed]\n"
       "                 [--fullscreen] [--scale N] [--dump <bmp> [seconds]]\n"
       "                 [--bench <seconds>] [--trace]\n"
       "  --data <dir>    unpacked game data (default: <exe>\\data)\n"
       "  --saves <dir>   save folder (default: <exe>\\saves)\n"
       "  --mods <dir>    mod folder (default: <exe>\\mods)\n"
       "  --windowed      windowed mode (default)\n"
       "  --fullscreen    fullscreen (letterboxed)\n"
       "  --scale N       window scale, default 3 (720x960; original aspect\n"
       "                  only, ignored with --aspect)\n"
       "  --aspect <original|4:3|16:9>\n"
       "                  logical view: 240x320 (default), 427x320, 569x320;\n"
       "                  the world view widens (more tile columns), height\n"
       "                  stays 320; menus stay 240 wide and are centred;\n"
       "                  window opens at 1024x768 (4:3) or 1280x720 (16:9),\n"
       "                  or the largest fitting whole-multiple scale\n"
       "  --fps <30|60|auto>\n"
       "                  render target fps (default 60, ~17 ms per render);\n"
       "                  auto follows the display refresh rate;\n"
       "                  --no-interp overrides --fps\n"
       "  --dump <bmp>    headless: write the final frame to <bmp>\n"
       "  --bench <secs>  headless: run N seconds, report frames presented\n"
       "  --trace         verbose progress output\n"
       "  --no-interp     disable render interpolation (old 1-render-per-tick\n"
       "                  behaviour; logic rate unchanged either way)\n");
}

}  // namespace

int WINAPI WinMain(HINSTANCE, HINSTANCE, LPSTR cmdLine, int) {
   std::string dataDir;
   std::string saveDir;
   std::string modsDir;
   std::string dumpPath;
   double dumpSeconds = 8.0;
   double benchSeconds = 0.0;
   bool fullscreen = false;
   int scale = 3;
   bool noInterp = false;
   std::string aspect = "original";
   std::string fps = "60";

   // Minimal argument parsing (no external dependency).
   {
      int argc = 0;
      LPWSTR* argv = CommandLineToArgvW(GetCommandLineW(), &argc);
      for (int i = 1; i < argc; i++) {
         auto arg = [](LPWSTR w) {
            char buf[1024];
            WideCharToMultiByte(CP_UTF8, 0, w, -1, buf, sizeof(buf), nullptr,
                                nullptr);
            return std::string(buf);
         };
         std::string a = arg(argv[i]);
         auto next = [&]() -> std::string {
            return (i + 1 < argc) ? arg(argv[i + 1]) : std::string();
         };
         if (a == "--data" && i + 1 < argc) {
            dataDir = next();
            i++;
         } else if (a == "--saves" && i + 1 < argc) {
            saveDir = next();
            i++;
         } else if (a == "--mods" && i + 1 < argc) {
            modsDir = next();
            i++;
         } else if (a == "--windowed") {
            fullscreen = false;
         } else if (a == "--fullscreen") {
            fullscreen = true;
         } else if (a == "--scale" && i + 1 < argc) {
            scale = std::stoi(next());
            i++;
         } else if (a == "--dump" && i + 1 < argc) {
            dumpPath = next();
            i++;
            if (i + 1 < argc) {
               std::string maybeSecs = next();
               try {
                  dumpSeconds = std::stod(maybeSecs);
                  i++;
               } catch (...) {
               }
            }
         } else if (a == "--bench" && i + 1 < argc) {
            benchSeconds = std::stod(next());
            i++;
         } else if (a == "--trace") {
            g_trace = true;
         } else if (a == "--no-interp") {
            noInterp = true;
         } else if (a == "--aspect" && i + 1 < argc) {
            aspect = next();
            i++;
         } else if (a == "--fps" && i + 1 < argc) {
            fps = next();
            i++;
         } else if (a == "--help" || a == "-h") {
            printUsage();
            return 0;
         } else {
            std::fprintf(stderr, "unknown argument: %s\n", a.c_str());
            printUsage();
            return 2;
         }
      }
      LocalFree(argv);
   }

   const bool headless = !dumpPath.empty() || benchSeconds > 0.0;
   if (headless) {
      // Attach to the parent console (CI) so --dump/--bench output is
      // visible; fall back to a fresh console.
      if (!AttachConsole(ATTACH_PARENT_PROCESS)) AllocConsole();
      FILE* f = nullptr;
      freopen_s(&f, "CONOUT$", "w", stdout);
      freopen_s(&f, "CONOUT$", "w", stderr);
   }

   std::string exeDir = Platform::exeDir();
   if (dataDir.empty()) dataDir = exeDir + "\\data";
   if (saveDir.empty()) saveDir = exeDir + "\\saves";
   if (modsDir.empty()) modsDir = exeDir + "\\mods";
   Platform::dataDir = dataDir;
   Platform::saveDir = saveDir;
   Platform::userDir = exeDir;
   Platform::modsDir = modsDir;

   // Start the session log first: from here on, every stage is recorded
   // in <exe>\tribes-log.txt, even if the game crashes later.
   crashlog::init(exeDir);
   // Discover mods before the game starts (a missing mods folder is
   // fine — the loader is a no-op and the game runs exactly as before).
   ModLoader::init(modsDir);

   // --- Aspect: logical view width (height is always 320) ---------------
   // original 240x320, 4:3 427x320, 16:9 569x320. The world view widens
   // (more tile columns); menus stay 240 wide and are centred (patch 29).
   // The window opens at 1024x768 (4:3) or 1280x720 (16:9), or the largest
   // whole-multiple scale of the logical size that fits the desktop.
   int viewW = 240;
   int targetClientW = 0, targetClientH = 0;
   if (aspect == "4:3") {
      viewW = 427;
      targetClientW = 1024;
      targetClientH = 768;
   } else if (aspect == "16:9") {
      viewW = 569;
      targetClientW = 1280;
      targetClientH = 720;
   } else if (aspect != "original") {
      crashlog::log("WARNING: unknown --aspect '%s', falling back to "
                    "original (240x320)",
                    aspect.c_str());
      std::fprintf(stderr,
                   "WARNING: unknown --aspect '%s', falling back to "
                   "original (240x320)\n",
                   aspect.c_str());
   }
   if (targetClientW > 0) {
      RECT work;
      if (SystemParametersInfo(SPI_GETWORKAREA, 0, &work, 0)) {
         int workW = work.right - work.left;
         int workH = work.bottom - work.top;
         int s = std::max(1, std::min(workW / viewW, workH / 320));
         if (viewW * s < targetClientW || 320 * s < targetClientH) {
            targetClientW = viewW * s;
            targetClientH = 320 * s;
         }
      }
      if (scale != 3) {
         crashlog::log("NOTE: --scale %d ignored with --aspect %s (window "
                       "is sized for the aspect)",
                       scale, aspect.c_str());
      }
   }
   Game::viewWidthPx = viewW;
   g_screen.resize(viewW, 320);

   // --- FPS: render pacing for the interpolation renders ----------------
   // 60 -> ~17 ms per render (current behaviour), 30 -> ~33 ms, auto ->
   // the display refresh rate (EnumDisplaySettings). --no-interp keeps its
   // existing meaning (1 render per tick) and overrides --fps.
   int renderIntervalMs = 17;
   if (fps == "30") {
      renderIntervalMs = 33;
   } else if (fps == "auto") {
      DEVMODE dm{};
      dm.dmSize = sizeof(dm);
      int freq = 0;
      if (EnumDisplaySettings(nullptr, ENUM_CURRENT_SETTINGS, &dm) &&
          (dm.dmFields & DM_DISPLAYFREQUENCY) &&
          dm.dmDisplayFrequency >= 30 && dm.dmDisplayFrequency <= 240) {
         freq = dm.dmDisplayFrequency;
      }
      renderIntervalMs = (freq > 0)
                             ? std::max(7, std::min(33, (int)(1000.0 / freq + 0.5)))
                             : 17;
   } else if (fps != "60") {
      crashlog::log("WARNING: unknown --fps '%s', falling back to 60",
                    fps.c_str());
      std::fprintf(stderr, "WARNING: unknown --fps '%s', falling back to 60\n",
                   fps.c_str());
   }
   if (noInterp) {
      crashlog::log("NOTE: --no-interp overrides --fps %s (1 render per "
                    "tick, ~8.3/s)",
                    fps.c_str());
   }
   Game::interpRenderIntervalMs = renderIntervalMs;
   if (noInterp) {
      Game::interpEnabled = false;
   }
   trace("aspect: %s (logical %dx%d, window %dx%d)\n", aspect.c_str(), viewW,
         320, targetClientW > 0 ? targetClientW : 240 * scale,
         targetClientH > 0 ? targetClientH : 320 * scale);
   trace("render interpolation: %s (fps target: %s, render interval: %d ms)\n",
         noInterp ? "OFF (--no-interp)" : "ON", fps.c_str(),
         renderIntervalMs);
   trace("exe dir: %s\n", exeDir.c_str());
   trace("data dir: %s\n", dataDir.c_str());
   trace("save dir: %s\n", saveDir.c_str());
   if (!dirHasGameData(dataDir)) {
      std::fprintf(stderr,
                   "Game data not found. Run the launcher first (it "
                   "extracts your JAR into the data folder), or pass "
                   "--data <dir>.\n");
      crashlog::log("ERROR: game data not found, exiting");
      crashlog::shutdown("missing game data");
      return 1;
   }

   WinDisplay display(scale, fullscreen, viewW, 320);
   if (targetClientW > 0) {
      display.setTargetClientSize(targetClientW, targetClientH);
   }
   if (!headless) {
      if (!display.create("Prehistoric Tribes")) {
         std::fprintf(stderr, "failed to create the window\n");
         crashlog::log("ERROR: failed to create the window, exiting");
         crashlog::shutdown("window creation failed");
         return 1;
      }
      g_winDisplay = &display;
      display.setScreen(&g_screen);
      trace("window created (scale=%d, fullscreen=%d, logical %dx%d, client "
            "%dx%d)\n",
            scale, fullscreen ? 1 : 0, viewW, 320,
            targetClientW > 0 ? targetClientW : viewW * scale,
            targetClientH > 0 ? targetClientH : 320 * scale);
   }

   Canvas::setRepaintCallback(presentFrame);

   trace("starting MIDlet...\n");
   TribesMIDlet* midlet = new TribesMIDlet();
   midlet->startApp();
   trace("MIDlet started (splash runs 1s + 2s + 2s, then the game)\n");

   const double runSeconds =
       benchSeconds > 0.0 ? benchSeconds : (headless ? dumpSeconds : 0.0);
   auto start = std::chrono::steady_clock::now();
   auto elapsed = [&]() {
      return std::chrono::duration<double>(std::chrono::steady_clock::now() -
                                           start)
          .count();
   };

   // Message pump. In headless mode there is no window, so just wait.
   MSG msg;
   while (!midlet->destroyed()) {
      if (headless) {
         if (runSeconds > 0.0 && elapsed() >= runSeconds) break;
         std::this_thread::sleep_for(std::chrono::milliseconds(10));
         continue;
      }
      while (PeekMessageA(&msg, nullptr, 0, 0, PM_REMOVE)) {
         if (msg.message == WM_QUIT) {
            midlet->destroyApp(false);
            break;
         }
         TranslateMessage(&msg);
         DispatchMessageA(&msg);
      }
      if (midlet->destroyed()) break;
      // Short wait so the pump stays responsive without busy-spinning.
      // Presenting is driven by the game thread's repaint callback, not
      // by this loop.
      MsgWaitForMultipleObjects(0, nullptr, FALSE, 5, QS_ALLINPUT);
   }

   trace("shutting down (frames presented: %lld)\n",
         (long long)g_framesPresented.load());
   midlet->destroyApp(false);
   Thread::joinAll();

   if (!dumpPath.empty()) {
      // Paint one last frame so the dump shows the current screen even
      // if the game has not repainted recently.
      {
         std::lock_guard<std::mutex> lock(screenMutex());
         Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
         if (canvas) canvas->paint(&g_screen);
      }
      g_screen.writeBmp(dumpPath);
      std::printf("dumped frame to %s (%lld frames presented in %.1fs)\n",
                  dumpPath.c_str(), (long long)g_framesPresented.load(),
                  elapsed());
   }
   if (benchSeconds > 0.0) {
      double secs = elapsed();
      std::printf("bench: %lld frames presented in %.1fs (%.1f fps)\n",
                  (long long)g_framesPresented.load(), secs,
                  secs > 0 ? g_framesPresented.load() / secs : 0.0);
   }
   crashlog::shutdown("normal exit");
   return 0;
}
