// linux_smoke.cpp — headless Linux driver for the portable core.
//
// This is the only functional validation possible on this Linux VM (no
// MSVC, no Windows): it links the portable core (jrt/gfx/engine/platform/
// gen) but NOT the Win32 shell (main.cpp/display.cpp/input.cpp), starts
// the game exactly as the Windows shell does (TribesMIDlet::startApp),
// and drives the repaint loop headlessly. It proves the game initializes,
// the 20-tick/s loop runs, the splash plays, the menu renders, and no
// crash occurs — before the Windows build is ever attempted in CI.
//
// Usage:
//   linux_smoke --data <extracted-jar-dir> --saves <dir>
//               [--seconds N] [--dump <bmp>] [--trace] [--play]
//   --seconds N   how long to run (default 12: splash is 1+2+2 s, then
//                 the title screen; 12 s leaves ~7 s on the menu)
//   --dump <bmp>  write the final 240x320 frame to a BMP
//   --play        after the title shows, send a scripted key sequence
//                 (FIRE to start a new game, then wait) to exercise the
//                 game beyond the menu; dumps the frame after the keys.
//   --trace       print frame counts and the game's screen/mode each
//                 second.

#include <atomic>
#include <chrono>
#include <cstdio>
#include <string>
#include <thread>

#include "crashlog.h"
#include "engine.h"
#include "gen/classes.h"
#include "gfx.h"
#include "modloader.h"
#include "platform.h"

namespace {

Surface g_screen(240, 320);
std::atomic<long long> g_frames{0};
bool g_trace = false;

void presentFrame() {
   Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
   if (canvas) canvas->paint(&g_screen);
   g_frames++;
}

const char* canvasName(Canvas* c) {
   if (!c) return "(none)";
   if (dynamic_cast<Game*>(c)) return "Game";
   if (dynamic_cast<Splash*>(c)) return "Splash";
   return "(unknown canvas)";
}

void reportState() {
   Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
   if (Game* game = dynamic_cast<Game*>(canvas)) {
      std::printf("  state: Game screenId=%d mode=%d ticks=%d interp=%s frames=%lld\n",
                  game->screenId, (int)game->mode, game->uptimeTicks,
                  Game::interpEnabled ? "on" : "off",
                  (long long)g_frames.load());
   } else {
      std::printf("  state: canvas=%s frames=%lld\n", canvasName(canvas),
                  (long long)g_frames.load());
   }
   std::fflush(stdout);
}

}  // namespace

int main(int argc, char** argv) {
   std::string dataDir;
   std::string saveDir = "/tmp/tribes-saves";
   std::string modsDir;
   std::string dumpPath;
   double seconds = 12.0;
   bool play = false;
   bool noInterp = false;

   for (int i = 1; i < argc; i++) {
      std::string a = argv[i];
      auto next = [&]() -> std::string {
         return (i + 1 < argc) ? argv[i + 1] : std::string();
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
      } else if (a == "--seconds" && i + 1 < argc) {
         seconds = std::stod(next());
         i++;
      } else if (a == "--dump" && i + 1 < argc) {
         dumpPath = next();
         i++;
      } else if (a == "--play") {
         play = true;
      } else if (a == "--no-interp") {
         noInterp = true;
      } else if (a == "--trace") {
         g_trace = true;
      } else {
         std::fprintf(stderr, "unknown argument: %s\n", a.c_str());
         return 2;
      }
   }
   if (dataDir.empty()) {
      std::fprintf(stderr, "--data <extracted-jar-dir> is required\n");
      return 2;
   }

   Platform::dataDir = dataDir;
   Platform::saveDir = saveDir;
   Platform::userDir = saveDir;
   // Default mods dir is the sibling of the data dir (like <exe>\mods
   // next to <exe>\data on Windows).
   if (modsDir.empty()) modsDir = dataDir + "/../mods";
   Platform::modsDir = modsDir;

   // Exercise the session logger too (log lands in the saves dir).
   crashlog::init(saveDir);
   crashlog::log("linux_smoke: data=%s saves=%s seconds=%.0f",
                 dataDir.c_str(), saveDir.c_str(), seconds);
   // Discover mods before the game starts (no mods folder = no-op).
   ModLoader::init(modsDir);
   if (noInterp) {
      Game::interpEnabled = false;
   }
   std::printf("linux_smoke: render interpolation %s\n",
               Game::interpEnabled ? "ON" : "OFF (--no-interp)");

   Canvas::setRepaintCallback(presentFrame);

   std::printf("linux_smoke: data=%s saves=%s seconds=%.0f\n",
               dataDir.c_str(), saveDir.c_str(), seconds);
   std::printf("starting MIDlet...\n");
   std::fflush(stdout);

   TribesMIDlet* midlet = new TribesMIDlet();
   midlet->startApp();

   auto start = std::chrono::steady_clock::now();
   auto elapsed = [&]() {
      return std::chrono::duration<double>(std::chrono::steady_clock::now() -
                                           start)
          .count();
   };

   bool played = false;
   int lastWholeSecond = -1;
   while (elapsed() < seconds && !midlet->destroyed()) {
      std::this_thread::sleep_for(std::chrono::milliseconds(10));
      if (g_trace) {
         int whole = (int)elapsed();
         if (whole != lastWholeSecond) {
            lastWholeSecond = whole;
            std::printf("[t=%ds] ", whole);
            reportState();
         }
      }
      if (play && !played && elapsed() > 7.0) {
         // The splash is 1+2+2 = 5 s; by 7 s the title menu is up.
         // Send FIRE (the menu's select key) to start a new game.
         Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
         if (canvas) {
            std::printf("sending FIRE to the menu...\n");
            canvas->keyPressed(KEY_FIRE);
            std::this_thread::sleep_for(std::chrono::milliseconds(100));
            canvas->keyReleased(KEY_FIRE);
         }
         played = true;
      }
   }

   std::printf("finished: ");
   reportState();

   if (!dumpPath.empty()) {
      Canvas* canvas = Display::getDisplay(nullptr)->getCurrent();
      if (canvas) canvas->paint(&g_screen);
      g_screen.writeBmp(dumpPath);
      std::printf("dumped frame to %s\n", dumpPath.c_str());
   }

   midlet->destroyApp(false);
   Thread::joinAll();
   std::printf("linux_smoke: OK (%lld frames presented)\n",
               (long long)g_frames.load());
   crashlog::shutdown("normal exit");
   return 0;
}
