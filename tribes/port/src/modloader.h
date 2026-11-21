// modloader.h — no-repack mod loader, phase 1 (textures + maps) plus the
// v0.2 `setup` extension (additive extra setup entries, applied after
// the replaced level's original setup at level load).
//
// Own implementation. Design: tribes/docs/MOD-LOADER-DESIGN.md.
// The loader is engine-level (shared by the Windows port and the future
// Android fork). Mods live in <modsDir>/<mod-id>/ with a mod.json
// manifest; the engine reads mod files instead of the base files at load
// time. No repack, no Python, no editing data/ in place.
//
// Threading: init() runs once on the main thread before the game starts
// (main.cpp / linux_smoke.cpp); after that the mod state is read-only and
// the hooks (applyMods, spriteOverride, hasModMap, modMapTile,
// reapplyMapNames) run on the game thread during loading. Every hook is
// a safe no-op when init() never ran or no mods are enabled, so a build
// without mods behaves exactly as before.

#pragma once

#include <string>

class Game;
struct Image;

namespace ModLoader {

// Scan modsDir for mods (each direct subfolder with a valid mod.json).
// A missing mods dir is fine (zero mods). A bad mod is skipped with a
// MOD-REJECT log line and never blocks startup.
void init(const std::string& modsDir);

// Apply all enabled mods' map overrides to a fully-loaded game:
// patch /a rec 149 (size), rec 279 (win/lose flags), the setup table
// (rec 250+q, scaled + nudged), rec 150 (camera start = centroid of
// the remapped player villagers), and the map name in the loaded s
// text. Called once from ResourceLoader's constructor (after loadMaps),
// via a counted SOURCE_PATCHES entry in tools/java2cpp.py. No-op when
// no map mods are enabled.
//
// Extra `setup` entries from a winning map are NOT applied here: they
// are additive and run later, at every level load, via applyExtraSetup
// (below), after the original setup has spawned.
void applyMods(Game* game);

// Apply a winning map's extra `setup` entries (MOD-FORMAT v0.2,
// additive). Called at the end of Game::spawnInitialEntities, via a
// counted SOURCE_PATCHES entry in tools/java2cpp.py, so it runs after
// the original setup at every level load, for the active missionIndex
// only. Each entry is dispatched exactly like spawnInitialEntities
// does (kind >= 0: building spawner; kind == -1: spawnVillager;
// kind == -2: spawnEffect), after nudging (x, y) against the mod
// map's tile occupancy (radius 8, same rule as applyMap). An entry
// that cannot be placed is skipped with a warning, never a crash.
// No-op when no map mod replaces the active level or the map has no
// `setup` array.
void applyExtraSetup(Game* game);

// Return the mod replacement image for sprite (pack, index), or nullptr
// to use the base image. `base` is the already-decoded base image, used
// to check the replacement's dimensions match exactly (a mismatch is
// logged and the base image is kept). Called from loadSpritePack and
// from the lazy pi8 load, via counted SOURCE_PATCHES entries.
Image* spriteOverride(Game* game, int pack, int index, Image* base);

// Map tile source for Game.loadMap(): true when level q is replaced by a
// mod map; the base tile (0..255) at (row, col) of that map. The loop
// body (jitter, markers, spawn registration, borders) is unchanged.
bool hasModMap(int levelQ);
int modMapTile(int levelQ, int row, int col);

// Re-apply the active map-name patches to the loaded s strings. Called
// from TextLoader.loadTextFile after the s file is read (via a counted
// SOURCE_PATCHES entry): picking a language reloads the s file from the
// base data, which would otherwise wipe the name patches applyMods made.
void reapplyMapNames(Game* game);

}  // namespace ModLoader
