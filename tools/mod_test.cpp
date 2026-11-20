// mod_test.cpp — deterministic mod-loader test driver.
//
// Unlike linux_smoke.cpp (which runs the game on its own thread through
// the splash and menu), this driver constructs the game directly on the
// driver thread and mirrors the real level-start flow (Game.g_byte_void:
// missionIndex = q; resetWorldState(); loadMap();
// placeCameraStartPositions();) so every check is deterministic and
// fast. It proves, for the green-valley example mod + a texture-test
// mod + a broken mod:
//
//   (a) the loader logs the mod and the map override (checked by the
//       shell script grepping tribes-log.txt),
//   (b) level q=11 loads as the 40x32 Green Valley map with the exact
//       tile counts (1,051x tile 0, 64x tile 3, 160x tile 43, 5 spawns),
//   (c) a texture override is applied (FNV checksum of spritePacks[0][5]
//       differs from the no-texture-mod baseline; the override PNG is
//       solid magenta, so every pixel must be 0xFFFF00FF),
//   (d) a deliberately broken mod is skipped with a MOD-REJECT log line
//       and the game still runs (checked by the shell script + the fact
//       that every other check still passes),
//   plus: the /a patches (rec 149 size, rec 150 centroid, rec 279
//   flags), the map-name patch (s[91] = "Green Valley"), the setup
//   remap (35 villagers spawn inside the 40x32 map), and that a
//   non-mod level (q=1, 64x48) still loads from the base data.
//
// Usage:
//   mod_test --data <extracted-jar-dir> --saves <dir> --mods <dir>
// Each check prints "CHECK <name> PASS|FAIL <detail>"; the process
// exits 1 if any check fails, 0 if all pass.

#include <cstdint>
#include <cstdio>
#include <string>

#include "crashlog.h"
#include "engine.h"
#include "gen/classes.h"
#include "gfx.h"
#include "modloader.h"
#include "platform.h"

namespace {

int failures = 0;

void check(bool ok, const char* name, const std::string& detail) {
   std::printf("CHECK %-16s %s %s\n", name, ok ? "PASS" : "FAIL",
               detail.c_str());
   std::fflush(stdout);
   if (!ok) failures++;
}

uint64_t fnv1a(const std::vector<uint32_t>& px) {
   uint64_t h = 1469598103934665603ULL;
   for (uint32_t v : px) {
      for (int i = 0; i < 4; i++) {
         h ^= (uint8_t)(v >> (i * 8));
         h *= 1099511628211ULL;
      }
   }
   return h;
}

int u8(Arr<int8_t> a, int i) { return a[i] & 0xFF; }

}  // namespace

int main(int argc, char** argv) {
   std::string dataDir;
   std::string saveDir = "/tmp/tribes-mod-test-saves";
   std::string modsDir;

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
      } else {
         std::fprintf(stderr, "unknown argument: %s\n", a.c_str());
         return 2;
      }
   }
   if (dataDir.empty() || modsDir.empty()) {
      std::fprintf(stderr, "--data <dir> and --mods <dir> are required\n");
      return 2;
   }

   Platform::dataDir = dataDir;
   Platform::saveDir = saveDir;
   Platform::userDir = saveDir;
   Platform::modsDir = modsDir;

   crashlog::init(saveDir);
   ModLoader::init(modsDir);

   // Build the game exactly as TribesMIDlet.run() does, but on this
   // thread and without the 5 s splash sleeps: the splash object must
   // exist because drawLoadProgress calls midlet->splashScreen while
   // loadingDone is false.
   TribesMIDlet* midlet = new TribesMIDlet();
   midlet->display = Display::getDisplay(midlet);
   midlet->splashScreen = new Splash(midlet);
   // TribesMIDlet.run() shows the splash BEFORE creating the Game;
   // showSplash creates screenBuffers[0]/screenBufferGraphics[0],
   // which drawLoadProgress needs during loading.
   midlet->splashScreen->showSplash();
   Game* game = new Game(midlet);
   game->initGame();
   game->allocateBuffers();
   game->world->attachGame(game);
   std::printf("mod_test: game initialised (mods=%s)\n", modsDir.c_str());

   // --- (b) level q=11: the Green Valley map ---------------------------
   game->missionIndex = 11;
   game->resetWorldState();
   game->loadMap();
   check(game->tableMapWidth == 40 && game->tableMapHeight == 32,
         "MAP-SIZE",
         "q=11 table=" + std::to_string((int)game->tableMapWidth) + "x" +
             std::to_string((int)game->tableMapHeight) + " (want 40x32)");
   check(game->mapWidth == 40 && game->mapHeight == 32, "MAP-DIMS",
         "q=11 map=" + std::to_string((int)game->mapWidth) + "x" +
             std::to_string((int)game->mapHeight) + " (want 40x32)");

   // Tile counts. loadMap adds +0..2 jitter to base tiles <= 8, so
   // base 0 lands in [0,2] and base 3 in [3,5]; 43 and 175-179 are
   // exact. Green Valley: 1,051x 0, 64x 3, 160x 43, 5x spawn.
   {
      int b02 = 0, b35 = 0, b43 = 0, bSpawn = 0, other = 0;
      for (int r = 0; r < 32; r++) {
         for (int c = 0; c < 40; c++) {
            int t = game->terrainGrid[r][c] & 0xFF;
            if (t <= 2) b02++;
            else if (t <= 5) b35++;
            else if (t == 43) b43++;
            else if (t >= 175 && t <= 179) bSpawn++;
            else other++;
         }
      }
      check(b02 == 1051 && b35 == 64 && b43 == 160 && bSpawn == 5 &&
                other == 0,
            "TILE-COUNTS",
            "tiles 0-2=" + std::to_string(b02) + " (want 1051), 3-5=" +
                std::to_string(b35) + " (want 64), 43=" +
                std::to_string(b43) + " (want 160), spawn=" +
                std::to_string(bSpawn) + " (want 5), other=" +
                std::to_string(other) + " (want 0)");
   }

   // Spawn registration: row-major order -> -61..-65.
   check(game->occupantGrid[5][5] == -61 &&
             game->occupantGrid[5][34] == -62 &&
             game->occupantGrid[16][20] == -63 &&
             game->occupantGrid[26][5] == -64 &&
             game->occupantGrid[26][34] == -65,
         "SPAWNS",
         "occ (5,5)=" + std::to_string((int)game->occupantGrid[5][5]) +
             " (5,34)=" + std::to_string((int)game->occupantGrid[5][34]) +
             " (16,20)=" + std::to_string((int)game->occupantGrid[16][20]) +
             " (26,5)=" + std::to_string((int)game->occupantGrid[26][5]) +
             " (26,34)=" +
             std::to_string((int)game->occupantGrid[26][34]) +
             " (want -61 -62 -63 -64 -65)");

   // --- /a patches applied by applyMods --------------------------------
   {
      auto& fileA = game->fileAData;
      auto& starts = game->recordStarts;
      check(u8(fileA, starts[149] + 22) == 40 &&
                u8(fileA, starts[149] + 23) == 32,
            "REC149",
            "rec149 q11=[" + std::to_string(u8(fileA, starts[149] + 22)) +
                ", " + std::to_string(u8(fileA, starts[149] + 23)) +
                "] (want [40, 32])");
      check(u8(fileA, starts[279] + 11) == 3, "REC279",
            "rec279 q11=" +
                std::to_string(u8(fileA, starts[279] + 11)) + " (want 3)");
      check(u8(fileA, starts[150] + 22) == 8 &&
                u8(fileA, starts[150] + 23) == 21,
            "REC150",
            "rec150 q11=[" + std::to_string(u8(fileA, starts[150] + 22)) +
                ", " + std::to_string(u8(fileA, starts[150] + 23)) +
                "] (want [8, 21], the repack centroid)");
   }

   // --- map name patch: s[91] must read "Green Valley" -----------------
   {
      TextLoader* tl = game->textLoader;
      auto& fileA = game->fileAData;
      auto& starts = game->recordStarts;
      std::string want = "Green Valley";
      int begin = tl->uiTextOffsets[91];
      int end = tl->uiTextOffsets[92];
      bool ok = (end - begin) == (int)want.size();
      if (ok) {
         for (size_t i = 0; i < want.size(); i++) {
            int expected =
                fileA[starts[280] + (unsigned char)want[i]] & 0xFF;
            if ((tl->uiTextData[begin + (int)i] & 0xFF) != expected) {
               ok = false;
               break;
            }
         }
      }
      check(ok, "MAP-NAME",
            "s[91] length=" + std::to_string(end - begin) +
                " (want 12, glyph codes of \"Green Valley\")");
   }

   // --- setup remap: villagers spawn inside the 40x32 map --------------
   game->placeCameraStartPositions();
   {
      // Villager slot s (1..100): occupied when entityData[1919+s] != 0;
      // x = entityData[s], y = entityData[101+s] (see spawnVillager).
      int count = 0, outOfBounds = 0;
      for (int s = 1; s <= 100; s++) {
         if (game->entityData[1919 + s] == 0) continue;
         count++;
         int x = game->entityData[s];
         int y = game->entityData[101 + s];
         if (x < 0 || x >= 40 || y < 0 || y >= 32) outOfBounds++;
      }
      check(count == 35 && outOfBounds == 0, "SETUP-SPAWN",
            std::to_string(count) +
                " villagers spawned (want 35, the q=11 setup count), " +
                std::to_string(outOfBounds) + " out of bounds (want 0)");
   }

   // --- (c) texture override: pi0[5] ------------------------------------
   {
      Image* img = game->spritePacks[0][5];
      bool sizeOk = img && img->w == 76 && img->h == 111;
      uint64_t fnv = 0;
      bool solidMagenta = false;
      if (img && img->px) {
         fnv = fnv1a(*img->px);
         solidMagenta = true;
         for (uint32_t p : *img->px) {
            if (p != 0xFFFF00FFu) {
               solidMagenta = false;
               break;
            }
         }
      }
      char buf[32];
      std::snprintf(buf, sizeof(buf), "%016llx",
                    (unsigned long long)fnv);
      std::printf("TEXTURE-FNV pi0[5] %s solidMagenta=%s\n", buf,
                  solidMagenta ? "yes" : "no");
      check(sizeOk, "TEXTURE-SIZE",
            "pi0[5] is " +
                std::to_string(img ? img->w : -1) + "x" +
                std::to_string(img ? img->h : -1) + " (want 76x111)");
      // The shell script compares TEXTURE-FNV between the baseline run
      // (green-valley only) and the override run (with texture-test);
      // here we only record whether this run's image is the magenta
      // override, which the script asserts for the override run.
      check(true, "TEXTURE-FNV-RECORDED",
            std::string("fnv=") + buf +
                " solidMagenta=" + (solidMagenta ? "yes" : "no"));
   }

   // --- regression: a non-mod level still loads from base data ---------
   game->missionIndex = 1;
   game->resetWorldState();
   game->loadMap();
   check(game->tableMapWidth == 64 && game->tableMapHeight == 48,
         "BASE-LEVEL",
         "q=1 table=" + std::to_string((int)game->tableMapWidth) + "x" +
             std::to_string((int)game->tableMapHeight) +
             " (want 64x48, untouched by mods)");

   crashlog::shutdown("mod test done");
   std::printf("mod_test: %s (%d failure(s))\n",
               failures == 0 ? "PASS" : "FAIL", failures);
   return failures == 0 ? 0 : 1;
}
