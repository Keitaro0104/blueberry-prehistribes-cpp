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

#include <atomic>
#include <chrono>
#include <cstdarg>
#include <cstdio>
#include <string>
#include <thread>

#include "display.h"
#include "engine.h"
#include "gen/classes.h"
#include "input.h"
#include "platform.h"

namespace {

Surface g_screen(240, 320);
std::atomic<long long> g_framesPresented{0};
bool g_trace = false;

void trace(const char* fmt, ...) {
   if (!g_trace) return;
   va_list ap;
   va_start(ap, fmt);
   std::vprintf(fmt, ap);
   va_end(ap);
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
         ok = false;
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
       "  --windowed      windowed mode (default)\n"
       "  --fullscreen    fullscreen (letterboxed)\n"
       "  --scale N       window scale, default 3 (720x960)\n"
       "  --dump <bmp>    headless: write the final frame to <bmp>\n"
       "  --bench <secs>  headless: run N seconds, report frames presented\n"
       "  --trace         verbose progress output\n");
}

}  // namespace

int WINAPI WinMain(HINSTANCE, HINSTANCE, LPSTR cmdLine, int) {
   std::string dataDir;
   std::string saveDir;
   std::string dumpPath;
   double dumpSeconds = 8.0;
   double benchSeconds = 0.0;
   bool fullscreen = false;
   int scale = 3;

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
   Platform::dataDir = dataDir;
   Platform::saveDir = saveDir;
   Platform::userDir = exeDir;

   trace("data dir: %s\n", dataDir.c_str());
   trace("save dir: %s\n", saveDir.c_str());
   if (!dirHasGameData(dataDir)) {
      std::fprintf(stderr,
                   "Game data not found. Run the launcher first (it "
                   "extracts your JAR into the data folder), or pass "
                   "--data <dir>.\n");
      return 1;
   }

   WinDisplay display(scale, fullscreen);
   if (!headless) {
      if (!display.create("Prehistoric Tribes")) {
         std::fprintf(stderr, "failed to create the window\n");
         return 1;
      }
      g_winDisplay = &display;
      display.setScreen(&g_screen);
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
   return 0;
}
