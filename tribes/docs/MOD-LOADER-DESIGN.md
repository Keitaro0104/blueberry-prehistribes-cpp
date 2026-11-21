# Mod Loader Design — Prehistoric Tribes C++ port (no-repack)

English. Status: design only — no engine code has been changed for this
doc. Local staging file; the loader is engine-level work in
`tribes/port/src/` (shared by the Windows port and the future Android
fork). All results in English, per project rule.

Related docs: `MOD-FORMAT.md` (mod format v0.1, in the research repo
`blueberry-prehistribes_code`, `mods/format/MOD-FORMAT.md`) — this design
reuses v0.1 where it exists and extends it to v0.2;
`ANDROID-STUDY.md` §6 (Android storage design);
`TRANSLATOR-NOTES.md` (the `SOURCE_PATCHES` discipline this design
relies on); `DECODE-REMAINING.md`, `PD0-ANALYSIS.md`, `MA-MAPS.md`
(decoded formats this design stands on).

## 0. What this loader is

The original game reads all of its data from files inside the JAR
(`pi0`/`pi8`/`pi9` sprite packs, `pd0` balance tables, `/a` record
tables, `/ma` maps, `/sa` music, `0/s` `0/t` `0/d0` text). The C++
port already reads those same files from a plain folder (`data/`),
because the launcher extracts the user's JAR there once
(`tribes/port/src/launcher/launcher.cpp`; `tribes.exe` reads
`<exe>\data` by default, `--data <dir>` override;
`Platform::dataDir`, `Platform::saveDir`, `Platform::userDir` in
`tribes/port/src/platform.h`).

The mod loader is the last step that makes modding painless: instead
of editing the files in `data/` (or repacking the JAR, as the J2ME
era required), a modder drops a folder into `mods/` and the engine
reads the mod's files **instead of** the base files at load time.
No repack, no Python, no editing `data/` in place.

### 0.1 Ground rules

1. **BYO-JAR, always.** The loader never bundles, copies, or
   redistributes original game files. Mods contain only the modder's
   own files (JSON, PNG, `.mid`). A mod that contains a `.jar`,
   `.class`, or a file copied from the original JAR is rejected.
2. **The engine never writes into `data/`.** Mods are overlays: the
   loader reads `data/` read-only and reads `mods/` read-only. The
   only writes are saves (`RecordStore` files in `saves/`) and logs.
3. **Data-only mods.** A mod never contains code. Behaviour changes
   come only from changing data values the engine already reads.
   Anything that needs new code paths is out of scope (see §8).
4. **Versioned.** Mods declare `format_version`. The loader rejects
   versions it does not understand instead of guessing.
5. **Validate, then load.** A mod is validated in full before any of
   it is applied. A mod that fails validation is skipped entirely and
   the failure is written to the log with the mod id, file, and field.
   Never load a half-valid mod.
6. **Log every decision.** Every mod found, every override applied,
   every conflict, and every rejection is written to the session log
   (`tribes-log.txt` next to the exe on Windows;
   `<root>/logs/` on Android). If a mod does nothing, the log must
   say why.
7. **Generated code stays generated.** The loader lives in
   hand-written code (`tribes/port/src/modloader.h/.cpp`, new) plus
   counted `SOURCE_PATCHES` in `tools/java2cpp.py` where a hook must
   land inside translated code (the same discipline as the
   `renderWorldEntities` byte→int fix). Never hand-edit
   `tribes/port/src/gen/`.

## 1. Folder layout + manifest

### 1.1 Where `mods/` lives

| Platform | `mods/` location | Decided by |
|---|---|---|
| Windows | `<exe>\mods` (next to `data\` and `saves\`) | `Platform::userDir` (`main.cpp`: `userDir = exeDir`); add `Platform::modsDir = userDir + "\\mods"` |
| Android | `<root>/mods` under the storage root (e.g. `BlueberryTribes/mods` on shared storage, internal fallback) | `initStoragePaths` (`ANDROID-STUDY.md` §6.1: `<root>/data`, `<root>/saves`, `<root>/logs` — `mods` joins that layout) |

The loader resolves `modsDir` from the same place that resolves
`dataDir`/`saveDir`, on both platforms. A `--mods <dir>` flag (Windows)
mirrors `--data`/`--saves` for testing.

### 1.2 Mod folder layout (v0.2)

```
mods/<mod-id>/
  mod.json                # manifest (required)
  maps/<id>.json          # MOD-FORMAT v0.1 map (+ v0.2 "setup", §4.2)
  buildings/<id>.json     # MOD-FORMAT v0.1 building variant
  weapons.json            # v0.2: record overrides for weapon behaviour (§4.4)
  balance.json            # v0.2: generic /a record overrides (§5)
  sprites/pi0-<index>.png # one PNG per overridden sprite (§4.1)
  sprites/pi9-<index>.png
  sprites/pi8-<index>.png
  text/<lang>/s           # whole-file text replacement (§4.5)
  text/<lang>/t
  text/<lang>/d0
  text/strings.json       # phase 2: per-string patches (§4.5)
  music/song-<n>.mid      # n = 0..4 (§4.6)
  animals/<id>.json       # phase 4 (§4.7)
  data/<path>             # opaque passthrough for future systems (§4.7)
```

Rules (extend MOD-FORMAT §3): file names use lowercase letters,
digits, `-`, `_` only; `mod.json` is required — a folder without it
is not a mod; unknown files are ignored but must never be game
assets.

### 1.3 Manifest — `mod.json` v0.2 (superset of v0.1)

`format_version` is `"0.2"` for this spec. A v0.1 mod (no new
fields) loads unchanged.

| Field | Type | Required | Meaning |
|---|---|---|---|
| `format_version` | string | yes | `"0.2"`. |
| `id` | string | yes | Unique mod id, lowercase/digits/`-`/`_`. Must equal the folder name. |
| `name` | string | yes | Human-readable name. |
| `author` | string | no | Mod author. |
| `description` | string | no | One or two sentences. |
| `game` | string | yes | Must be `"prehistoric-tribes"`. |
| `maps` | array of strings | no | Map files, e.g. `["maps/green-valley.json"]`. |
| `buildings` | array of strings | no | Building files, e.g. `["buildings/large-hut.json"]`. |
| `weapons` | string | no | Path to the weapons file, e.g. `"weapons.json"`. |
| `balance` | string | no | Path to the record-patch file, e.g. `"balance.json"`. |
| `textures` | array of strings | no | Sprite files, e.g. `["sprites/pi0-05.png"]`. |
| `text` | array of objects | no | Whole-file replacements: `{"lang": 0, "file": "s", "path": "text/0/s"}`. |
| `music` | array of objects | no | Song replacements: `{"song": 0, "path": "music/song-0.mid"}`. |
| `animals` | array of strings | no | Animal files, e.g. `["animals/python.json"]`. |
| `requires` | array of strings | no | Mod ids this mod must load after (ordering, §2). |
| `conflicts` | array of strings | no | Mod ids this mod must not load with (error if both present). |

## 2. Load / overlay order + conflicts

1. **Discovery.** At startup (before `ResourceLoader` runs), the
   loader scans `modsDir`. Each direct subfolder with a valid
   `mod.json` is a mod. A folder without `mod.json` is ignored and
   logged.
2. **Order.** Mods load in this order: folders sorted alphabetically
   by folder name, then re-sorted so every mod comes after the mods
   in its `requires` list (topological sort; a cycle is a validation
   error and all mods in the cycle are skipped). An optional
   `mods/load-order.txt` (one mod id per line) pins the full order
   and overrides alphabetical order when present; ids in it that are
   not installed are ignored with a log line.
3. **Overlay rule (per key, last wins).** Every override is keyed:
   sprite `(pack, index)`, map slot `q`, building type, `/a` record
   offset, text `(lang, file, index)`, song index, animal id. When
   two mods override the same key, the **later** mod wins and the
   conflict is logged (`MOD-CONFLICT: <key> overridden by <later-id>,
   was <earlier-id>`). No merging inside one key: a building variant
   replaces the whole row set for its type; a map replaces the whole
   slot.
4. **Enabling.** A mod is enabled when its folder is directly under
   `mods/` and its `mod.json` is valid. An optional `mods/enabled.txt`
   (one id per line) selects a subset; when present, only listed mods
   load. Disabling a mod is deleting/renaming its folder (or removing
   it from `enabled.txt`). There is no in-game mod menu in phase 1
   (see §8). **Phase 1 note:** `enabled.txt` is not implemented yet —
   in phase 1 a mod is enabled iff its folder is present with a valid
   `mod.json`; disabling is deleting/renaming the folder.
5. **Scope.** Mods are global: they apply to every save/profile.
   (Open question §9.2 asks whether per-profile mods are wanted.)
6. **Failure mode.** If any enabled mod fails validation, that mod is
   skipped and the game starts with the remaining mods. The log must
   name the mod, the file, and the field. The game never refuses to
   start because of a bad mod.

## 3. The engine seams (where the loader hooks in)

The loader is a new hand-written module,
`tribes/port/src/modloader.h` / `modloader.cpp` (plus a JSON reader;
the port has no JSON parser yet — use a small single-header parser
vendored under `tribes/port/third_party/`, or a hand-written
recursive-descent parser for the flat shapes this spec needs; do
not pull in a large dependency for this). It hooks into the engine
at exactly four seams. No other engine code changes.

### Seam 1 — whole-file override (no generated-code change)

`JavaClass::getResourceAsStream(name)` in
`tribes/port/src/platform.cpp` is the single function every resource
load goes through (`readDataFile` → `Platform::dataDir / rel`).
The loader adds one call at its top:

```cpp
// platform.cpp, inside JavaClass::getResourceAsStream:
if (auto mod = ModLoader::resolveResource(name)) return mod; // mod file bytes
return readDataFile(name, ...);                               // base data/
```

`resolveResource` consults the loaded mods' whole-file table.
Allowed whole-file replacements (declared in `mod.json`):

**Phase 1 note:** Seam 1 is not implemented yet — phase 1 needs no
whole-file override (textures are per-sprite, maps are JSON-direct).
It lands with whole-file text replacement in phase 2.

| Resource | Mod file | Notes |
|---|---|---|
| `/<lang>/s`, `/<lang>/t`, `/<lang>/d0`, `/<lang>/d<n>` | `text/<lang>/s` etc. | Text, §4.5. Proven by the VN mod (all 5 languages replaced). |
| anything under `/mods/<id>/data/<path>` | `data/<path>` | Opaque passthrough for future systems, §4.7. |

**Forbidden as whole files** (rejected at validation): `/a`,
`pd0`, `/ma`, `/sa`, `pi0`, `pi8`, `pi9`. Each of those files is
mostly original bytes; a mod that ships one would be redistributing
the original game. They are overridden per-item instead (record
patches §5, per-sprite §4.1, per-song §4.6, JSON maps §4.2).

### Seam 2 — post-load hooks (one `SOURCE_PATCHES` entry each)

`ResourceLoader`'s constructor (`tribes/src/ResourceLoader.java`)
runs, in order: `allocateBalanceTables`, `initScreenBuffers`,
`initWorldState`, `loadSpritePack` (pi0), `loadSpritePack` (pi9),
`loadBalanceData` (pd0), `loadGameData` (`/a`), `initTextLoader`,
`loadMusic` (`/sa`), `loadMaps` (`/ma`). The loader adds a call
after each step whose data it patches:

| After | Hook | Applies |
|---|---|---|
| `loadMaps()` | `ModLoader::applyMods(game)` | Phase 1: the `/a` record patches for replaced levels (rec 149 size, rec 279 win/lose flags, rec 150 cursor start, setup record rec 250+q) + the map-name patch (§4.2). No-op when no map mods are enabled. |
| `loadGameData()` | `ModLoader::applyBalancePatches(game)` | Phase 2: all `/a` record edits (§5): buildings, weapons, level tables, wave tables. Not implemented in phase 1. |
| `loadMusic()` | `ModLoader::applyMusicOverrides(game)` | Phase 3: song splicing + `unusedShortArrayE/F` rebuild (§4.6). Not implemented in phase 1. |
| `loadSpritePack()` (inside the image loop, **after** `Image::createImage`) | `ModLoader::spriteOverride(game, pack, index, baseImage)` | Per-sprite PNG replacement (§4.1). The base image is decoded first so the hook can check the replacement's dimensions against it; a mismatch keeps the base image. Also applies at the lazy pi8 load (`Game.java:3551`, `c(byte)`). |
| `TextLoader.loadTextFile`, after the `s` file (var1 == 0) is read | `ModLoader::reapplyMapNames(game)` | Re-applies the map-name patches after the `s` file is reloaded (e.g. when the user picks a language). No-op when no map mods are enabled. |

Each hook is a counted `SOURCE_PATCHES` entry in
`tools/java2cpp.py` (fail loudly if it stops matching exactly once),
exactly like the `renderWorldEntities` byte→int fix. Regen with
`python tools/java2cpp.py` and verify the `gen/` diff is only the
hook lines.

### Seam 3 — map tile source (`loadMap`, one patch)

`Game.loadMap()` (`tribes/src/Game.java:7530`) decodes the `ma` RLE
stream into `terrainGrid` and applies the marker/spawn logic. A mod
map must flow through the **same loop body** (jitter, blocked
markers, spawn registration, border), with only the tile source
changed. The patch adds a branch at the top of `loadMap()`:

- If the current `missionIndex` maps to a mod map, the loop reads
  tiles from the loader (`ModLoader::hasModMap(q)` /
  `ModLoader::modMapTile(q, row, col)`) instead of from
  `mapData`/`mapChunkOffsets`. The tiles stay in the loader's `ModMap`
  struct (row-major, **without** jitter — the loop applies the `+0..2`
  jitter for tiles ≤ 8 itself, `Game.java:7560-7563`); there is no
  `modMapTiles` array or `modMapActive` flag on `Game`.
- Otherwise the loop is unchanged (base maps still come from `ma`).

The `ma` file and the hardcoded `mapChunkOffsets` table
(`ResourceLoader.loadMaps`, 10 entries) are never modified and
never extended: mod maps are always JSON-direct and never enter
`ma`. That avoids the piece-table problem entirely.

### Seam 4 — nothing for saves

Save/load (`t0`/`t1`/`t2` RecordStores, `Game.loadSlot` /
`saveSlot`, `tribes/src/Game.java:7742+`) is untouched. A save made
with mods active stores the same world state as a base save
(`entityData`, `homePosition`, villager groups). Consequence, stated
plainly: **a save made while a balance mod is active may behave
differently (or break) if loaded without that mod**, because the
saved world references modded values (e.g. a building type whose
HP table changed). The loader logs a warning when a save is loaded
while the active mod set differs from the set recorded at save
time — which requires recording the active mod ids in the save.
That recording is a phase-2 addition (a `t0` extension or a
sidecar file `saves/mods.txt`); phase 1 logs only the current mod
set at startup. See §9.3.

## 4. Per-category spec

### 4.1 Textures — sprite PNG override

**What the game has.** Sprite packs are concatenated PNGs:
each image is prefixed by a 2-byte big-endian length, the stream
ends with `0xFF` (`ResourceLoader.loadSpritePack`,
`tribes/src/ResourceLoader.java:161`; format proven in
`DECODE-REMAINING.md` §1, implemented by `extract_pngs.py`).
Counts: `pi0` 27 images (62,502 bytes), `pi9` 6 images (8,597),
`pi8` 12 images (129,809). Load order in the constructor:
`packPaths = {"/pi0", "/pi9", "/pi8"}`, `packImageCounts =
{31, 6, 12}` — but only indices 0–26 are actually created from
pi0 (`if (var3 < 27)`), 0–5 from pi9, and pi8 is loaded **lazily**
on demand (`Game.java:3551`, at most 12, only the requested index
kept). Valid sprite ids are therefore:

| Pack | Ids | Notes |
|---|---|---|
| `pi0` | 0–26 | In-game art, loaded at startup into `spritePacks[0]`. |
| `pi9` | 0–5 | In-game art, loaded at startup into `spritePacks[1]`. Same dimensions as some pi0 images, different content. |
| `pi8` | 0–11 | Story/cutscene art (`spritePacks[2]`), loaded lazily, one index at a time. All 240×130 except [10] 200×211 and [11] 226×165. |

**Sprite identity is an input, not invented here.** Which sprite
id a villager frame, a building, a tree, or a weapon uses is
decided by the sprite→entity mapping table, filled in from the
sprite gallery identification (user work in progress) plus the draw
call sites (`Game.java:2219`, `2233`, `2345` draw from
`spritePacks[0]/[1]`; `3022`, `3551` draw `spritePacks[2][0]`).
That table lives in `tribes/docs/SPRITE-MAP.md` (to be written by
the gallery work) and is used by mod authors and by the gallery —
**not** by the loader at runtime. The loader only needs
`(pack, index)`.

**Mod layout.** A mod declares textures in `mod.json`
(`"textures": ["sprites/pi0-05.png", ...]`) and ships one PNG per
file, named `<pack>-<index>.png` (e.g. `sprites/pi0-05.png`
overrides pi0 index 5; `sprites/pi8-00.png` overrides pi8 index 0).

**Rules.**

1. The replacement PNG **must match the original image's pixel
   dimensions exactly** (checked against the base pack's index at
   load time). The game draws sprites by region; a different size
   breaks the region math. Mismatch = mod rejected, log line naming
   the file and both sizes.
2. The replacement must be a valid PNG (the port's `Image::createImage`
   decodes it; a decode failure rejects the mod).
3. `pi0[25]` (181×39, RGBA) is the bitmap digit strip
   (`0123456789. % / p`, the HUD number font; `TextLoader` draws
   from `spritePacks[0][25 + fontSize]`). Replacing it changes the
   number font everywhere. Allowed, but the loader writes a warning
   to the log when a font-strip sprite is overridden.
4. Size-based hints from `DECODE-REMAINING.md` §1 (unproven, do
   not build on them): pi0[00] 128×136 UI/icon atlas; pi0[01]
   100×113 building/object atlas; pi0[02] 121×151 villager portrait
   grid; pi0[05]/pi9[00] 76×111 dense repeating pattern (likely
   terrain); pi0[07] 52×132 trees/plants; pi0[18] 190×59 title logo.
   The gallery identification replaces these hints with facts.

**VN-mod pattern (follow it).** The Vietnamese mod added new
images as **new files** (`reptiles.png` 144×64, `voi-l.png` /
`voi-la.png` / `voi-r.png` / `voi-ra.png` ~52×40, `relics/*.png`
8 icons) and **did not touch** the original bundles (`pi0`/`pi8`/
`pi9` byte-identical). Our loader follows the same pattern:
original bundles are never modified; mod sprites are separate
files; the engine resolves a sprite id to the mod file when one
exists.

**Hook.** Seam 2, `spriteOverride(pack, index)` inside
`loadSpritePack`'s image loop and at the lazy pi8 load. It returns
the mod PNG bytes when an enabled mod overrides that id, else
nothing (base image decodes as before). Override resolution is
per key, last mod wins (§2.3).

**Proving gate (phase 1).** A test mod replaces one pi0 image
with a visibly different PNG of identical dimensions; the Linux
smoke test boots with the mod active; the Winlator playtest shows
the replaced sprite in game.

### 4.2 Maps — JSON v0.1 + v0.2 `setup`

**What the game has.** A level is identified by `q`
(`missionIndex`, 0–11). Loading a level (`Game.loadMap`,
`Game.java:7530`) reads, per `q`:

| Piece of data | Source | Used as |
|---|---|---|
| Map size (w, h) | `/a` rec 149, 2 bytes per q | `mapWidth`/`mapHeight` (`q=0` forced 40×32; `q=5` width − 32) |
| Cursor start | `/a` rec 150, 2 bytes per q | Starting cursor position |
| Map piece | `/a` rec 248, 1 byte per q → `mapChunkOffsets[piece]` → offset into `mapData` (`/ma`) | RLE tile stream |
| Custom-list entry | `/a` rec 249, 1 byte per menu position → q | Custom Game menu (`Game.java:5820`, `6113`) |
| Setup table | `/a` rec 250+q, 5 parallel arrays × count | Villager + building placement (`spawnInitialEntities`, `Game.java:7629`) |
| Setup count | `/a` rec 262, 1 byte per q | Number of setup entries |
| Starting tribe stats | `/a` rec 263, 22 bytes per q | `tribeStats[0]/[1]` (first 11 values, `spawnInitialEntities`) |
| Win/lose flags | `/a` rec 279, 1 byte per q | `World.checkWinLose` (`b.ak()`) |
| Map name | text file `s`, index `82 + piece` | `Game.java:3246` (`hudValues[62] = 82 + rec248[q]`) |

**JSON v0.1 already covers** (MOD-FORMAT §5): `width`,
`height`, `tiles`, `spawns` (types 1–5, tiles 175–179),
`player_start`, `win_lose` (flags + booleans). The converter
`convert_map.py` encodes JSON → `ma` RLE with a proven
byte-for-byte round-trip for the original maps.

**What v0.1 does not cover — the v0.2 `slot_q` field.** v0.1 has no
way to say *which* level a map replaces: in the JAR world the repack
tool chose the slot (q=11 for Green Valley). v0.2 therefore adds a
required `"slot_q"` integer to the map JSON: the level `q` this map
replaces (8–11 in phase 1 replace mode). A map without `slot_q` —
including an otherwise valid v0.1 map — is rejected with a clear
message; adding the one line is the whole migration from v0.1 to
v0.2 for maps.

**What v0.1 does not cover — the v0.2 `setup` extension.**
The repack proof (Green Valley, q=11) had to remap rec 261 by
hand: 35 villagers + 25 buildings, 20 nudged, 0 unplaceable. A
loader cannot do that from the v0.1 fields alone, because the
setup table is not in the JSON. MOD-FORMAT v0.2 therefore adds an
optional `setup` array to the map schema. Each entry has the 5
raw fields of the setup table, whose meanings are read from
`spawnInitialEntities` (`Game.java:7629-7675`):

| Field | JSON name | Meaning (from code) |
|---|---|---|
| f0 | `packed` | Villager: bit 0 = tribe (0/1); `>> 1` (+ difficulty when tribe = 1) = the 5th `spawnVillager` argument (skill/level). Building/effect: passed through as the 1st argument. |
| f1 | `kind` | `>= 0` → spawn building via the 5-arg spawner (`a_byte_int_int_int_int_boolean_int_byte`, `true, -1`); `== -1` → `spawnVillager(...)`; `== -2` → `spawnEffect(1, x, y, f0)`. |
| f2 | `x` | Tile column. |
| f3 | `y` | Tile row. |
| f4 | `param` | 4th `spawnVillager` argument / 5th building-spawner argument. **Confirmed during implementation (2026-11-21):** villager = AGE (`entityData[1414]`, bands < 12 child / < 30 / < 50); building = LEVEL, 0 = auto default from rec 113 per mission group (q < 2 → +0, q == 2 → +12, else +24). For effects `param` is ignored (the dispatch passes only f0). |

A v0.2 map may omit `setup`; then the loader reuses the setup
table of the level it replaces (phase 1, replace mode) — but the
loader must still check that every setup entry lands on a valid
tile of the new map, and log a summary line (entries, villagers,
buildings, how many nudged, how many unplaceable) plus a warning
when any entry cannot be placed (the repack proof's rule: nudge
within the map, never place outside it; 0 unplaceable is the bar).
Per-entry nudge lines are not logged — Green Valley alone nudges
20 entries, which would flood the log.

**Additive `setup`: the implemented rule (2026-11-21).** When a
v0.2 map *does* carry a `setup` array, the entries are ADDITIVE:
they are applied AFTER the replaced level's original setup (already
rescaled + nudged by `applyMods`), which stays untouched. There is
no rec 250+q rewrite — the extra entries never enter the table;
they are spawned directly at level load. This is what the map
editor exports for enemy markers: enemy building
`{"packed":1,"kind":<0-11>,"x":c,"y":r,"param":0}`, enemy unit
`{"packed":1,"kind":-1,"x":c,"y":r,"param":25}`.

- **Hook.** `ModLoader::applyExtraSetup(game)` at the end of
  `Game::spawnInitialEntities` (SOURCE_PATCHES patch 8 in
  `tools/java2cpp.py`), so it runs after the original setup at
  every level load, for the active `missionIndex` only. No-op when
  no map mod replaces that level or the map has no `setup` array.
- **Dispatch** mirrors `spawnInitialEntities` exactly: kind >= 0
  → building spawner `(packed, kind, x, y, param, true, -1)`;
  kind == -1 → `spawnVillager(packed & 1, x, y, param, (packed >>
  1) + difficulty-if-tribe-1)`; kind == -2 → `spawnEffect(1, x, y,
  packed)`.
- **Validation** (`validateMap`, same style as the other checks):
  `packed`/`kind`/`x`/`y`/`param` must be integers; `packed`
  0..127 (buildings 0/1 only — the tribe byte, slots 1–30 / 31–60;
  effects 0..2 — `effectCounters` has 3 entries, and the base
  data's packed-3/4 effects only occur on q=3, where the
  increment is skipped); `kind` -2..11; `x`/`y` inside the map;
  `param` 0..127. Any bad entry rejects the whole map JSON with a
  clear message (a broken mod is skipped, the game never dies).
- **Nudge.** Villagers: `nearestFree` (radius 8). Buildings:
  `placeBuildingFootprint` (spiral radius 8, same order as the
  repack proof) with the game's own footprint check as the
  acceptor — a tile-free candidate rejected by the game (an
  already-placed building occupies part of it; placed buildings
  live in `occupantGrid`, which the tile nudge cannot see) keeps
  walking the spiral instead of dropping the entry. Effects are
  never nudged. Placed extras are marked occupied as they go, so
  extras never stack on each other. An entry with no placement in
  radius 8 is skipped (a failed spawner attempt is side-effect-free:
  it returns -2 before any write). Skipped entries never crash.
- **Log line.** `MOD: <owner> extra setup q=<q>: <n> entries (<v>
  villagers, <b> buildings, <e> effects), <n> nudged, <s>
  skipped`, plus a WARNING line when `<s> > 0`.
- **Building types 0–11** (footprints from rec 138 +104/+130):
  0 Armoury 3×2, 1 Gym 5×4, 2 Hut 2×2, 3 Chief's hut 4×4,
  4 Kitchen 4×3, 5 Range 6×4, 6 Store 3×3, 7 Disco 5×4,
  8 Sanctuary 3×3, 9 PIA center 4×4, 10 Nursery 4×4, 11 Tower
  1×1. Enemy tribe = 1: enemy villagers occupy slots 51–100,
  enemy buildings slots 31–60.
- **Win flag.** Rec 279 bit 1 (win by destroying all enemy
  buildings) counts extra enemy buildings too — they occupy slots
  31–60 like any enemy building, so adding enemy buildings makes
  the win condition harder, as the map author intends.
- **Proving gate.** `tools/run_mod_test.sh` (full run): the
  `extra-setup-test` mod (48×48 map for q=10 with 4 setup
  entries) — enemy Chief's hut in a slot 31–60, enemy villager
  (age 25) in a slot 51–100, the rock-block-centre villager
  skipped, 26 enemy villagers total (25 original + 1 extra),
  effect at (2,6), log line `4 entries (2 villagers, 1 buildings,
  1 effects), 0 nudged, 1 skipped`.

**Slot modes.**

- **Replace (phase 1, proven).** The mod map replaces an existing
  level's slot. Recommended slots are the custom-only levels
  q=8–11 (q=11 is proven by Green Valley: piece 9 replaced,
  rec 149/150/261 remapped). The loader writes size, cursor,
  win/lose flags, and setup into that q's records, and decodes the
  JSON tiles directly (Seam 3) instead of the `ma` piece. The base
  `ma` piece stays on disk, unused for that q. The custom list
  (rec 249) already contains q=8–11, so no list change is needed.
- **Add (phase 2).** A new level q=12+ grows rec 149, 150, 248,
  249, 262, 263, 279 and adds a setup record 250+q (see §5 for the
  blob-rebuild mechanism). The VN mod proves the binary format
  supports this: it grew to 17 levels (rec 149/150 24→34 bytes,
  rec 248 12→17, rec 249 10→20, rec 262 12→17, `ma` 42,381→60,458
  with 5 new pieces). **Validation rule:** when a level is added,
  rec 279 MUST be grown to cover it — `checkWinLose` reads
  `blob[starts[279] + q]`, and for q beyond the record it would
  read the next record's bytes. (The VN mod left rec 279 at 12
  entries for 17 levels; do not repeat that.)

**Camera rule (repack lesson, now a loader rule).** The camera
follows the player's tribe, not `player_start`. The loader
computes rec 150 (cursor start) as the **centroid of the remapped
player villagers** from the setup table (the `repack_jar.py`
`player_tribe_centroid()` rule). The engine positions the camera
from rec 150 (`placeCameraStartPositions`: `camTile = rec150 −
cursorCol`; the game's `homeCursorCol/Row` are never assigned, so
they stay 0 and `camTile = rec150`). The JSON `player_start` is
**not** used for the camera at all — the engine has no such
concept. The loader only validates it (in bounds; warns when it
lands on a blocked tile). A map whose tribe spawns over unseen
fog renders black — the loader must not allow that.

**Name rule.** The map name comes from text `s` index `82 +
piece`. For a replaced slot the piece is unchanged, so the name
index is unchanged. The loader renames the map by patching the
loaded `s` strings in memory at that index, from the map JSON's
own `name` field (same-length overwrite, using the rec-280 glyph
mapping exactly as `TextLoader.loadTextFile` does; the patch is
skipped with a warning when the lengths differ), and re-applies
the patch after every `s` reload (picking a language reloads the
`s` file from the base data, which would otherwise wipe the
names). No text override (§4.5) is involved in phase 1. For an
added level the loader requires the mod to supply the name string
via the text override (the `s` file grows by whole-file
replacement); the loader validates that index `82 + piece` exists
in the active `s` file, else the mod is rejected.

**Wave/duration inheritance.** In phase 1 replace mode the loader
touches **no** wave/duration records: the replaced slot keeps its
own (exactly like the repack proof, which changed only rec
149/150/261 and the `s` name). `based_on.level_q` in the JSON is
informational only in phase 1. Per-record `waves` overrides (rec
206–217, 220, 241, 216 — see §4.4) are a phase-2 addition, when
add-a-level needs them.

**Hook.** Seam 3 (`loadMap` tile-source branch) + §5 record
patches. In replace mode the loader patches only rec 149 (size),
rec 279 (win/lose flags), rec 150 (cursor start = tribe centroid)
and the setup record (rec 250+q, scaled + nudged). It
deliberately does **not** touch rec 248 (the piece is unchanged, so
the map-name index `82 + piece` stays valid), rec 249 (the custom
list already contains q=8–11), rec 262 (the setup entry count is
unchanged) or rec 263 (tribe stats unchanged). Growing
248/249/262/263 belongs to add mode (phase 2). No `ma` write, ever.

**Proving gate (phase 1).** Green Valley as a no-repack mod:
`mods/green-valley/` containing the existing `example-map.json`
plus `mod.json`; Linux smoke test boots with it active; Winlator
playtest opens Custom Game → Green Valley and plays it (the same
map that previously needed a repacked JAR).

### 4.3 Buildings — `/a` rec 138 table override

**What the game has.** All building balance lives in `/a`
record 138: 24 rows × 26 columns (row-major, one column per
type). The code reads it everywhere (`Game.java` alone has ~30
`recordStarts[138]` sites). The decoded rows (MOD-FORMAT §6,
`PD0-ANALYSIS.md` §3):

| Rows | Meaning | Formula / notes |
|---|---|---|
| 4, 5 | Width, height (tiles) | Read as `rec138[104 + type]`, `rec138[130 + type]` (×22 / ×16 px). |
| 7, 8, 9 | Max HP at levels 1, 2, 3 | `rec138[(7 + level − 1) * 26 + type]`. Hut 25/40/90. |
| 10, 11, 12 | Upgrade cost per step | Paid progressively. |
| 13, 14, 15 | Build cost (full) | Paid once, from tribe stock `[2], [3], [4]` (wood/stone/bones order inferred, not proven — MOD-FORMAT §6 caveat; the loader uses wood=2, stone=3, bones=4 consistently). |
| 16 | Construction-site proxy type | Not a real building. Do not touch. |
| 21, 22, 23 | Mood bonus per level | e.g. Disco +2/+3/0, Hut 0/+2/0. |
| 0, 1, 2, 3, 17, 18, 19, 20 | Unproven | Off-limits for mods until decoded. |

Worker capacity lives in `/a` rec 80 (inferred): `[3, 7, 3, 5, 5,
7, 5, 6, 3, 5, 3, 2]` for types 0–11. Living space: Hut +4,
capped at 50 tribe-wide (`Game.java` hut handling). Names: text
`s` 0–11; descriptions: text `t` 0–11. Traps are types 20–22
(net 10/0/0, spring 10/5/0, pit 5/10/0, upgrade-cost rows 10–12)
and are **not** in the v0.1 building schema (different placement
code).

**Mod layout.** MOD-FORMAT v0.1 `buildings/<id>.json`:
`base_type` (0–11), `width`, `height`, `hp_by_level[3]`,
`build_cost`, `upgrade_cost`, `mood_bonus_by_level[3]` (optional),
`worker_capacity` (optional), `living_space` (optional),
`sprite_ref` (reference only, §4.1), `name`/`description`
(→ text override, §4.5).

**Semantics (v1: replacement only).** A building variant
**replaces its `base_type`'s columns**: every building of that
type in the game uses the variant's values. The variant reuses
the base type's code path (behaviour, role requirements, sprite
family). This is what MOD-FORMAT v0.1 defines, and it is all the
loader does in v1.

**Adding a genuinely new type is not a data-only change.**
Types 12–19 are construction-site proxies (rec 138 row 16);
types 20–22 are traps; types 23–25 are unidentified in the
original data. There is no proven free type slot with a working
code path. A mod that declares `base_type` outside 0–11 is
rejected. (Investigation item: probe types 23–25 in a local
playtest — do they crash, render nothing, or behave? — before
any add-a-type design.)

**Hook.** §5 record patches: the loader writes the variant's
values into `fileAData` at `recordStarts[138] + row*26 + type`
(rows 4, 5, 7–15, 21–23) and `recordStarts[80] + type` for
capacity. Patch order: base table first, then mods in load
order (§2.3).

**Validation.** MOD-FORMAT §7 rules 8–9 (base_type 0–11,
width/height 1–6, HP > 0, costs ≥ 0, exactly 3 HP/mood values,
capacity/living_space ≥ 0) plus: rows outside the allowed set are
rejected; a variant may not change a type's width/height to a
value that breaks placement on existing maps (the loader checks
the replaced level's setup tables — entries must still fit;
a violation is a warning, not a rejection, because maps are
validated separately).

### 4.4 Weapons — `/a` record overrides (confirmed vs unknown)

**What the game has — confirmed.** Damage and projectiles are
computed in `World` (`World.java`, from `b.java`) from `/a`
records. Every value below is proven from a code site
(`PD0-ANALYSIS.md` §6):

| Record | Values | Meaning (proven) | Code site |
|---|---|---|---|
| 102 | 10: `[1,1,2,3,5,3,0,0,0,0]` | Melee damage bonus **by role** (roles 0–5 fight, 6–9 do not) | Melee damage `(meleeSkill/3 + rec102[role]) × (rand(11−defense)+defense)/10`, `b.java:3715` |
| 124 | 10: `[1,1,1,2,2,3,3,4,6,8]` | Throwing damage added, by throwing skill | `b.java:3790` |
| 79 | 10: `[1,1,5,2,2,1,1,5,2,2]` | Weapon used by a task (index into the weapon set) | Task→weapon mapping |
| 159 | 3: `[25,30,40]` | Projectile gravity / flight factor **by throwing-weapon class** (3 classes); flight time = `distance > g ? distance / g : 1` | `b.java:3645,3673–3674` |
| 212/213 | 12 per level | Attacker weapon-skill cap (easy/hard) | Wave spawning, `b.java:5490` |
| pd0 table 3 | 3 × 8 | Projectile velocity table, 3 weapons × 8 directions (fixed-point ÷256) | `b.java:3673` |

Related, partially known: rec 103 (9 values
`[4,3,0,5,2,1,4,3,0]`) is weapon-related but its exact use is
**unproven**; rec 100 (17 values) is used in a combat/role check
(`b.java:1021`), exact role **unproven**; tribe stock indices
`[7],[8],[9],[10]` are consumed by tasks 25–28 and are
**inferred** to be weapon stocks (not proven).

**What is unknown (do not invent).**

- The six named weapons (text `s` 12–17: club, stone, spear,
  stone axe, bone mace, tomahawk) have **no proven per-weapon
  damage table**. Melee damage is per **role** (rec 102), not per
  weapon. There is no confirmed record that says "the spear does
  X damage".
- The mapping between the six named weapons and the three
  throwing classes (rec 159) is **unknown**. Rec 79's values
  (1, 2, 5) do not line up with a 0–5 weapon index, which suggests
  "weapon" in rec 79 means something narrower (likely the throwing
  class), but that is not proven.
- The role names for rec 102's roles 0–9 are **unknown**.
- There is **no proven weapon registry**: where the six weapons
  are registered, how the Armoury produces them, and how their
  stocks (`[7]`–`[10]`, inferred) link to the names is not traced.

**What a weapon mod can change in v1 (retune only).**
`weapons.json` carries record overrides — the same §5 mechanism
as buildings, but for the weapon records:

```json
{
  "format_version": "0.2",
  "kind": "weapons",
  "melee_bonus_by_role": [1,1,2,3,5,3,0,0,0,0],
  "throw_damage_by_skill": [1,1,1,2,2,3,3,4,6,8],
  "weapon_by_task": [1,1,5,2,2,1,1,5,2,2],
  "projectile_gravity_by_class": [25,30,40],
  "projectile_velocity_table": "weapons/velocity.bin"
}
```

Each field maps to exactly one record (102, 124, 79, 159; the
velocity table is pd0 table 3, overridden as a raw 48-short
blob — allowed only with the exact length, else rejected).
`weapons.json` may also carry `attacker_skill_cap` (rec 212/213,
per level, easy/hard) and wave records (§4.4 is also where the
wave tables live: rec 206–217, 220, 241, 216 — see
`PD0-ANALYSIS.md` §7; a map mod's `waves` object writes the same
records for its own level).

**What a weapon mod cannot do in v1.** Add a 7th weapon. There
is no free slot with a proven code path, and the name→behaviour
link is undecoded. That needs the investigation below, then
engine work.

**Investigation items (before any add-a-weapon design).**

1. Trace the weapon registry: Armoury production
   (`World.tickBuildings`), the stock indices `[7]`–`[10]`, and
   the text `s` 12–17 link. (Where does the game decide a
   villager "has" a spear?)
2. Pin rec 103's use and rec 100's use from their call sites.
3. Name the three throwing classes (rec 159) and the ten roles
   (rec 102) from in-game text/behaviour.

### 4.5 Text — per-language string overrides

**What the game has.** `TextLoader`
(`tribes/src/TextLoader.java`) loads, per language
(`Game.languageIndex`, 0 English, 1 French, 2 Italian, 3 German,
4 Spanish), three files by `getResourceAsStream`:

| File | Contents | Counts |
|---|---|---|
| `/<lang>/s` | UI strings | 229 strings (`s`, `uiTextData`, `uiTextOffsets[230]`) |
| `/<lang>/t` | Help strings | 72 strings (`t`) |
| `/<lang>/d<n>` | Story/dialogue | 236 pieces (`d0` etc.; `selectStorySection` picks the file by `storyFileIndex`) |

Files are raw bytes: strings are separated by byte `124`
(`|`); byte `127` is a line break; byte `31` substitutes the
version string. At load time each byte is mapped through
`/a` rec 280 into a glyph code and stored in `uiTextData` /
`longTextData` with `textOffsets` boundaries. Text the game needs
by index: building names `s` 0–11, weapon names `s` 12–17, map
names `s` 82–91, villager names `s` 106–227, building
descriptions `t` 0–11, win/lose `t` 56–57, tutorial script
`d0` 18–84.

**Whole-file replacement (phase 1, proven).** A mod declares in
`mod.json` (`"text": [{"lang": 0, "file": "s", "path":
"text/0/s"}]`) and ships a complete replacement file. The
loader serves it through Seam 1 (`resolveResource`). This is
exactly what the VN mod did: all five languages' `s`/`t`/`d0`
files replaced (0/s 1,734→2,004 bytes, 0/t 2,576→3,242,
0/d0 28,921→31,712). Validation: the file must be non-empty and
parse as the same format (separator bytes present; the loader
counts `|` separators and requires the count to be within the
expected range for that file — `s` must yield 229 strings, `t`
72; a wrong count rejects the mod, because every string index
after a missing separator would shift). The file uses the game's
byte encoding — the loader does **not** transcode; a mod author
writes text in the same charset the game already displays.

**Per-string patches (phase 2).** `text/strings.json`:
`[{"lang": 0, "file": "s", "index": 82, "text": "..."}]`.
This needs the loader to re-encode one string through the rec
280 glyph mapping and shift all subsequent `textOffsets` —
the offsets are cumulative, so a longer/shorter replacement
moves every later string. Implementation is a
`TextLoader`-level patch applied after `loadTextFile`, and is
marked as investigation work (the exact re-encoding path is in
`TextLoader.loadTextFile`, `TextLoader.java:loadTextFile`).

**Language rule.** A text mod targets explicit languages. A mod
that replaces only language 0 leaves the other four untouched.
The loader never invents a translation for the other languages.

### 4.6 Sound — per-song `.mid` replacement

**What the game has.** Music only — there are no sound effects.
`ResourceLoader.loadMusic` (`tribes/src/ResourceLoader.java`)
reads `/sa` (26,616 bytes) into `Game.midiData` and sets two
hardcoded tables:

| Table | Values | Meaning |
|---|---|---|
| `unusedShortArrayE` (lengths) | `[8014, 6315, 5648, 3405, 3234]` | Byte length of songs 0–4 |
| `unusedShortArrayF` (offsets) | `[0, 8014, 14329, 19977, 23382]` | Cumulative start offsets |
| `musicTrackTable` | `[0, 1, 2, 3, 4, 4]` | Mood → track (`selectMusicTrack`, `Game.java:8107`: `musicTrackTable[musicMood * 2 + rand(2)]`) |

`MidiPlayer` (`tribes/src/MidiPlayer.java:17`) plays one song as
`ByteArrayInputStream(midiData, offsets[track],
lengths[track])` through the platform `Player` (Windows:
`WinMidiPlayer` in `tribes/port/src/platform.cpp`, SMF parser +
`midiOut` sequencer; Android: `TribesMidiPlayer.java` via
MediaPlayer — see `ANDROID-STUDY.md` §5).

**Mod layout.** `music/song-<n>.mid` (`n` = 0..4), declared in
`mod.json` (`"music": [{"song": 0, "path":
"music/song-0.mid"}]`).

**Rules.**

1. The replacement must be a valid SMF (Standard MIDI File,
   format 0 or 1). The platform parser validates it (Windows
   `parseMidi`; on failure the song plays silent and the log
   records `MIDI: no playable events (song failed to parse)`).
2. The loader splices the new song's bytes into `midiData` and
   **rebuilds** the E/F tables (offsets are cumulative, so every
   song after a replaced one shifts). Total `midiData` is capped
   at 64 KiB; the loader reallocates `Game.midiData` (a field, not
   a fixed buffer, in the C++ port) and reassigns it before the
   first play. A mod whose songs exceed the cap is rejected.
3. The loader never changes `musicTrackTable` in phase 1 (mood→
   track mapping stays base). A mod that wants a different mood
   mapping declares it in `music` as `track_table` (phase 2).
4. Volume is out of scope for the loader
   (`MidiPlayer.setVolume` dereferences a `VolumeControl` that
   the Android player must implement first — `ANDROID-STUDY.md`
   §5.2).

**Proving gate (phase 3).** A test mod replaces song 0 with a
short, obviously different MIDI. The Linux smoke test boots with
it active and the log shows the song parsed; the Windows
playtest cannot verify sound in Winlator (no MIDI synth), so the
gate is the log line plus the `tribes-log.txt` MIDI lines — the
same evidence rule as the no-sound investigation.

### 4.7 Animals — definition format (design) + new-system evidence

**What the game has.** Wild animals already exist: at most 22
(`World.tickAnimals`, `tribes/src/World.java:6404`, loop
`currentVillager < 22`, alive when `entityData[9513+v] > 0`).
Animal position is `entityData[9359+v]` (col) /
`[9381+v]` (row); animal type is `entityData[9491+v]` (index
into `/a` rec 1 offsets); movement uses `animalPaths[2][22][5]`
and `updateAnimal()` / `animateAnimal()`. Spawning is
table-driven per level: rec 202 (count), rec 204 (interval ×40
ticks), rec 205 (threshold), rec 185/199/200/201 (spawn tables).
Villager tasks 92/93/106 are "wild animal attacker".

**What the VN mod proved.** New animals are a **code + data**
change in the original: new classes `ReptileMotion.class` and
`ElephantMotion.class`, new image files (`reptiles.png` 144×64,
`voi-l.png` / `voi-la.png` / `voi-r.png` / `voi-ra.png` ~52×40),
and (presumably) spawn-table edits. The original bundles were
not touched. The VN mod also added two **new systems** the same
way: relics (`Relics.class` + `relics/plan` "RLC1" + 8 icons
`relics/0–7.png`) and waves (`wave-plan` + `V2Runtime` /
`V3Runtime` / `V4Screen` / `V5Fix` / `RewardCycles.class`).
Those are new code classes plus plan data files — not data-only
mods.

**Animal definition (design, phase 4).**
`animals/<id>.json`:

```json
{
  "format_version": "0.2",
  "kind": "animal",
  "id": "python",
  "name": "Python",
  "sprite_sheet": "sprites/animals-python.png",
  "frames": 4,
  "frame_width": 36,
  "hp": 30,
  "damage": 5,
  "speed": 2,
  "behavior": "snake",
  "spawn_levels": [0, 1, 2],
  "spawn": {"count": 2, "interval_ticks": 4000, "threshold": 1}
}
```

`behavior` must name an **existing** motion pattern (the values
come from the `updateAnimal`/`animateAnimal` dispatch, once
mapped). A new animal whose behaviour maps onto an existing
pattern is data-only; a behaviour that needs a new motion class
is engine work, not a mod.

**What is design vs investigation (stated plainly).**

- Design: the JSON shape above; the spawn-table records the
  loader writes (rec 199/200/201/202/204/205); the sprite-sheet
  convention (one PNG per animal, frames laid out horizontally,
  frame count + width declared).
- Investigation needed before phase 4: (1) the behaviour
  dispatch in `updateAnimal`/`animateAnimal` — how the animal
  type field (`entityData[9491+v]`) selects motion, and what the
  existing patterns are; (2) the exact semantics of the spawn
  tables (rec 185/199/200/201/202/204/205); (3) which sprite
  regions the original animals use (gallery identification);
  (4) the animal HP/damage fields (which entity fields hold
  them, and which `/a` records, if any, feed them).

**New systems (relics/waves) are out of scope for the loader's
first phases.** The loader supports them structurally through
the opaque passthrough (Seam 1, `data/<path>`: a mod ships
`data/relics/plan` and the engine reads it as
`/mods/<id>/relics/plan`), but the engine must implement the
relic/wave system first. A mod cannot conjure a new system from
data alone.

## 5. The `/a` record-patch mechanism (shared primitive)

Most balance mods (buildings, weapons, level tables, wave
tables) are edits to `/a` records. The loader implements one
primitive for all of them; every category above is a caller of
it.

**How `/a` is stored.** File `/a` (18,842 bytes) = 312 records:
each record is `[LE u16 length][blob]`, parsed by
`ResourceLoader.loadGameData` into `Game.fileAData`
(byte[17638]) with `Game.recordStarts[312]` (cumulative starts),
followed by 5 groups of BE u16 values (`offsetTable[570]`).
Records 0–279 are game data tables; 280–311 are font tables.
(Proven in `DECODE-REMAINING.md` §2; the parse ends exactly at
18,842.)

**The primitive.** A mod's `balance.json` (or a category file
that compiles to it) is a list of record edits:

```json
{
  "format_version": "0.2",
  "kind": "balance",
  "records": [
    {"record": 138, "offset": 104, "values": [3, 2]},
    {"record": 138, "offset": 182, "values": [25, 40, 90]},
    {"record": 279, "offset": 11, "values": [3]}
  ]
}
```

Semantics: `values` are written as bytes at
`fileAData[recordStarts[record] + offset + i]`. An edit may
**grow** a record (values past its current end extend it); the
loader then rebuilds the whole blob: recompute `recordStarts`
for every record after the grown one, reallocate `fileAData`
(the C++ `Arr<int8_t>` field is reassignable), and re-attach
the 5 trailing groups unchanged. Caps: total blob ≤ 32 KiB
(`recordStarts` is `short[312]`, cumulative starts must fit);
a single record ≤ 1,024 bytes unless the design says otherwise.

**Rules.**

1. Records 280–311 (font tables) are **off-limits** — never
   patched. Font data is not balance data.
2. An edit must stay inside the record it names, unless it is a
   declared growth (then the record's new length is recorded and
   the rebuild shifts everything after it).
3. Every edit is validated before it is applied: record index
   0–279, offset ≥ 0, values are bytes 0–255 (or -128–127 where
   the consumer reads them signed — the schema documents the
   signedness per record).
4. The loader applies edits in load order (§2.3), base table
   first. The log records every applied edit as
   `MOD: <mod-id> rec<record>+<offset> = <values>`.
5. The patched blob is built **once**, after `loadGameData()`
   and before the first game tick (Seam 2 hook). The original
   `/a` file on disk is never touched.

**Records the loader touches (index).** 79, 80, 102, 103, 124,
138, 149, 150, 159, 199–205, 206–217, 216, 220, 241, 248, 249,
250–261 (setup), 262, 263, 279. Every other record is
off-limits until its consumer is decoded and the design names
it.

## 6. Validation + errors + logging

**Validation order (per mod, before any of it is applied).**

1. `mod.json` parses, `format_version` is `"0.1"` or `"0.2"`,
   `game` is `"prehistoric-tribes"`, `id` matches the folder name.
2. Every file listed in `mod.json` exists; every content file is
   listed (MOD-FORMAT §7 rules 2–3). **Phase 1 note:** only the
   first half is enforced — a listed file that is missing rejects
   the mod, but an unlisted content file in the mod folder is
   ignored (not a rejection). The forbidden-file scan (§0.1) still
   applies to every file in the folder.
3. Every JSON file parses and passes its schema (MOD-FORMAT §7
   rules 4–10 for maps/buildings; the per-category rules in §4
   for the rest).
4. Sprite PNGs decode and match the original dimensions (§4.1
   rule 1). MIDI files parse as SMF (§4.6 rule 1). Text files
   yield the expected string counts (§4.5).
5. Record edits pass §5 rules (record 0–279, in-bounds or
   declared growth, font records untouched).
6. `requires` ids are installed; `conflicts` ids are absent.

A failure at any step rejects the whole mod with one log line
naming the mod, the file, and the field. The game starts with
the remaining mods (§2.6).

**Log format.** Every loader line is prefixed `MOD:` (Windows
`tribes-log.txt`, Android session log). Lines: `MOD: found
<id> (<folder>)`, `MOD: loaded <id> (maps=1 buildings=0 ...)`,
`MOD: <id> rec<record>+<offset> = <values>`,
`MOD-CONFLICT: <key> overridden by <later-id>, was
<earlier-id>`, `MOD-REJECT: <id> <file>: <reason>`,
`MOD: sprite sprites/pi0-05.png -> pi0[5] (76x111)`,
`MOD: map <id> -> slot q=<q> (replace)`,
`MOD: song <n> replaced (<bytes> bytes, <events> events)`.

## 7. Implementation phases + proving gates

Each phase is: schema addition (if any) → loader hook →
validation → Linux smoke test → Windows CI green → Winlator
playtest by the user. **Phase 1 must be proven on Windows
before any Android work uses the loader** (the loader is
engine-level, so the proof transfers).

| Phase | What lands | Proving gate |
|---|---|---|
| 1 — Textures + maps (replace mode) | `modloader.h/.cpp` (hand-written recursive-descent JSON parser, no new dependency); `modsDir` resolution (Windows `--mods`, Linux smoke `--mods`); Seam 2 sprite hook (in `loadSpritePack` + lazy pi8 load, base-first so dimensions can be checked) + `applyMods` after `loadMaps()` (rec 149/150/279 + setup record + map-name patch) + `reapplyMapNames` after the `s` reload; Seam 3 `loadMap` tile-source branch (`hasModMap`/`modMapTile`, no `modMapTiles` field); `mod.json` v0.2; map `slot_q` (required, 8–11) + `setup` optional (reuse replaced level's setup). Seam 1 (whole-file text), the balance hook and the music hook are **not** phase 1 (see §3). | Linux smoke test boots with a test mod active. Windows CI green. **Winlator:** Green Valley as a no-repack mod (`mods/green-valley/`, same JSON as the repack proof) loads from the Custom Game menu and plays; one reskinned sprite is visible in game. |
| 2 — Buildings + text (whole-file) + add-a-level | Building variants via §5; whole-file text replacement; record growth (§5 rebuild) for rec 149/150/248/249/262/263/279; a new level q=12 in the Custom Game menu. | Same gates, plus: a new level q=12 appears in Custom Game and plays to a verdict (win/lose via rec 279). A building variant (e.g. Large Hut) is buildable in game with its new HP/costs. A full Vietnamese-style text replacement loads (whole-file, one language). |
| 3 — Weapons + sound + per-string text | `weapons.json` (rec 79/102/124/159, pd0 table 3); per-song `.mid` replacement + E/F rebuild; per-string `strings.json` (after the re-encoding investigation). | Same gates, plus: a weapon rebalance changes combat observably (damage numbers in the log/playtest); a replaced song parses (log line) — sound itself cannot be verified in Winlator, so the gate is the parse + the MIDI log lines. |
| 4 — Animals + new-system passthrough | Animal JSON + spawn-table writes, after the §4.7 investigations (behaviour dispatch, spawn semantics, animal sprite regions). Opaque `data/` passthrough for future systems. | Same gates, plus: a reskinned existing animal (same behaviour, new sprite) appears in game; then, if the dispatch allows, a new animal type spawns and behaves. Relics/waves remain out of scope until their systems are implemented in the engine. |

Phase order is deliberate: textures+maps are the most proven
(formats + encoder + repack proof exist); buildings+text are
fully decoded; weapons need the record work but no new
concepts; animals need code investigation first.

## 8. Non-goals (explicit)

1. **No JAR repack.** The loader never reads or writes a JAR.
   The J2ME repack tools (`convert_map.py`, `repack_jar.py`,
   `patch_unlock.py`) stay research tools; they are not part of
   the loader.
2. **No writes into `data/`.** (§0.1 rule 2.)
3. **No new building types (beyond 0–11), no new weapons
   (beyond 6), no new unit roles** in the data-only loader.
   Each needs code-path work (see §4.3, §4.4). A mod that
   declares one is rejected, not silently reinterpreted.
4. **No tick-rate, save-format, or engine-behaviour changes.**
   (MOD-FORMAT §9.) Saves keep the `t0`/`t1`/`t2` field order.
5. **No mod code.** No `.class`, `.jar`, or scripts in mods.
6. **No in-game mod menu in phase 1.** Enabling is
   file-based (§2.4). A menu screen is future UI work in
   `Game.java` (a new `screenId`), not loader work.
7. **No per-profile mods in phase 1.** Mods are global (§2.5).
8. **No new systems from data alone.** Relics/waves-style
   systems need engine work first (§4.7).

## 9. Open questions (for the user)

1. **Load order on conflict.** Proposed: alphabetical by folder
   name, `requires` to force order, optional `mods/load-order.txt`
   to pin it. Or would you rather have an in-game mod list to
   enable/reorder mods (which is UI work in `Game.java`)?
2. **Global or per-profile?** Proposed: mods apply to every
   save. Or should each save/profile have its own mod set?
3. **New maps: auto-place or full setup?** Proposed: a v0.2 map
   may omit `setup` and reuse the replaced level's setup (the
   loader checks and nudges entries). Or should every map JSON
   be required to carry the full setup table (exact control, more
   work for the modder)?
4. **Texture naming.** Proposed: mods name sprites by pack and
   index (`sprites/pi0-05.png`). Or should a mod also be allowed
   to name a sprite by the thing it replaces (e.g. `"hut"`,
   `"villager"`) using the gallery's sprite→entity table — which
   means the loader reads that table at runtime?

## Appendix A — `/a` record index (records the loader touches)

| Record | Size (base) | Meaning | Loader use |
|---|---|---|---|
| 79 | 10 | Weapon by task | Weapons (§4.4) |
| 80 | 12 | Worker capacity per building type | Buildings (§4.3) |
| 102 | 10 | Melee damage bonus by role | Weapons (§4.4) |
| 103 | 9 | Weapon-related, exact use unproven | Weapons — read-only until proven |
| 124 | 10 | Throw damage by skill | Weapons (§4.4) |
| 138 | 24×26 | Building table | Buildings (§4.3) |
| 149 | 24 | Level sizes (2 bytes × 12) | Maps (§4.2) |
| 150 | 24 | Cursor start (2 bytes × 12) | Maps (§4.2) |
| 159 | 3 | Projectile gravity by class | Weapons (§4.4) |
| 185, 199, 200, 201, 202, 204, 205 | per level | Animal spawn tables | Animals (§4.7) |
| 206–217, 216, 220, 241 | per level | Wave tables, level duration, AI tick | Maps `waves` / weapons (§4.2, §4.4) |
| 248 | 12 | Level → map piece | Maps (§4.2) |
| 249 | 10 | Custom-game list (menu pos → q) | Maps (§4.2) |
| 250–261 | 50–350 each (5 × rec262[q]) | Per-level setup tables (5 fields × count) | Maps `setup` (§4.2) |
| 262 | 12 | Setup entry count per level | Maps (§4.2) |
| 263 | 264 | Starting tribe stats (22 bytes × 12) | Maps (§4.2) |
| 279 | 12 | Win/lose flags per level | Maps (§4.2) |

Records 280–311 are font tables: **off-limits**.

## Appendix B — file map (which engine function reads which file)

| File | Read by (Java / C++) | Loader seam |
|---|---|---|
| `pi0`, `pi9`, `pi8` | `ResourceLoader.loadSpritePack` → `Image.createImage` | Seam 2 sprite hook (§4.1) |
| `pd0` | `ResourceLoader.loadBalanceData` | §5 via pd0 table 3 only (weapons); whole-file forbidden |
| `/a` | `ResourceLoader.loadGameData` → `Game.fileAData` / `Game.recordStarts` | Seam 2 balance hook + §5 |
| `/ma` | `ResourceLoader.loadMaps` → `Game.mapData`; decoded by `Game.loadMap` | Seam 3 (JSON-direct; `ma` untouched) |
| `/sa` | `ResourceLoader.loadMusic` → `Game.midiData` | Seam 2 music hook (§4.6) |
| `/<lang>/s`, `/<lang>/t`, `/<lang>/d<n>` | `TextLoader.loadTextFile` | Seam 1 whole-file (§4.5) |
| JAR (import only) | `launcher.cpp` (extract → `data/`, `import.txt`) | Not a mod path; import stays BYO-JAR |
| `t0`/`t1`/`t2` saves | `Game.loadSlot` / `saveSlot` via `RecordStore` | Untouched (§3 Seam 4) |
