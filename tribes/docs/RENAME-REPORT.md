# Rename Report — Phase 1

Prehistoric Tribes (THQ/Gear Games 2008, J2ME MIDP-2.0), decompiled with
Vineflower 1.12.0. Phase 1 renamed the obfuscated identifiers (single
letters `a`–`z`, `A`–`Z`) into readable names, proven only by call sites,
constants, game data, and bytecode. No behavior was patched.

## Result

| Item | Count |
| --- | --- |
| Human renames in `tribes/docs/names.map` | **931** (7 classes, 576 fields, 348 methods) |
| Mechanical auto names (`tribes/docs/names-auto.map`) | **30** (all methods, all in Game) |
| Compile check (`javac --release 8` vs MIDP-2.0/CLDC-1.0 stubs) | **0 errors** |
| Spot-check (`checkWinLose`, `openCustomGame`) | **identical** to Vineflower `ak()` / `aw()` |
| Strict collisions (same name, different type/signature) | **580** (466 fields + 114 methods) |

Confidence across the 931 human entries: **324 Certain / 571 Likely /
33 Structural / 3 Unknown**. "Structural" names describe only the observed
code role (loop counters, scratch temps, path-buffer indices) and are the
first candidates to revisit. "Unknown" names are honest placeholders for
declared-but-never-accessed fields (see below).

## Count correction

The first measurement said 542 members must be renamed. The true count is
**580**. The early inventory rendered array types lossily
(`byte[]`/`byte[][]`/`byte[][][]` all printed the same), merging distinct
fields: +13 in World (`b`), +21 in Game (`f`). The renamer never used that
inventory — it re-runs `javap` fresh, with full type spellings — so the
pipeline was never affected; only the headline count was wrong.

Per-class strict collisions:

| Class | New name | Fields | Methods | Human entries |
| --- | --- | --- | --- | --- |
| `tribes` | TribesMIDlet | 3 | 0 | 5 |
| `a` | MidiPlayer | 2 | 2 | 8 |
| `b` | World | 129 | 84 | 364 |
| `c` | TextLoader | 33 | 0 | 64 |
| `d` | Splash | 2 | 0 | 5 |
| `e` | ResourceLoader | 5 | 0 | 15 |
| `f` | Game | 292 | 28 | 470 |
| **Total** | | **466** | **114** | **931** |

Every field in the game (576/576) now has a human name. Of 391 methods,
348 are human-named, 30 carry mechanical auto names (see below), and 13 keep
their original names on purpose: they are platform overrides
(`startApp`/`pauseApp`/`destroyApp`/`run`, `paint`/`keyPressed`/
`keyReleased`/`hideNotify`/`showNotify`, `playerUpdate`) — renaming an
override would break the override relationship. Constructors are never
renamed.

## How it was done

- **Renamer (own code, not copied):** `tools/ch_rename/TribesRenamer.java`,
  a Vineflower `IIdentifierRenamer` driven by `names.map`, exactly like the
  GOW mechanism (rename at class-file level, so the same letter reused in
  different classes is handled consistently). Map keys carry the full type
  (`field b.a:byte[] entityData`) and return type
  (`method f.a():int getPlayerState`), because this obfuscator reuses one
  letter for many members that differ only by type or return type.
- **Driver (own code):** `tools/rename_tribes.py` — builds the effective map
  (human `names.map` + auto-generated mechanical names for colliding members
  not yet covered), runs Vineflower, verifies every map entry was used,
  compile-checks with `javac --release 8`. `tribes/src/*.java` is generated;
  never hand-edit it.
- **Stubs (own code):** hand-written MIDP-2.0/CLDC-1.0 stubs covering only
  the 10 API classes the game uses
  (`~/workspace/j2me-prehistoric-tribes/tools/midp-stubs/`, kept out of the
  repo by `.gitignore`).
- **Readers:** three independent read-throughs (Game, World, small classes),
  each proposal validated before merging: every key checked against the
  `javap` inventory, no duplicate new names within a class, full collision
  coverage, one evidence row per rename. Two errors were caught and fixed in
  validation: a mistyped method key (`spawnVillager`'s first parameter is
  `byte`, not `int`) and a duplicated name (`currentSpriteEntityId` was given
  to both `f.x:byte` and `f.X:byte`; the lowercase field is `savedMode` — it
  stashes `mode`/`interactMode` across the pause screen).

## Top renames

- **Game (`f`):** `f` → `Game`; `run()` tick loop (`aS()` pause/music check →
  `h()` update → `m()` render → `l()` repaint); `j()` world tick with the
  endgame timer (win verdict at tick 41, lose at 90); `screenId` (`Q`: 14 main
  menu, 27 system pause, 32 ad); `mode` (`v:byte`, 0 menu / 1 in-game);
  `cheatCode` = {56,55,51,57,49} (ASCII 8,7,3,9,1); `spawnVillager`
  (`a(byte×5):byte`, finds a free slot of 50 per tribe); `musicMood` (`byte h`,
  set by World); `songLengths`/`songOffsets` (static, the 5 MIDI songs in
  `sa`); `selectMusicTrack`.
- **World (`b`):** `b` → `World`; `entityData` (9,983-byte flat array:
  villagers stride 101, buildings base 8383 stride 61, animals base 9359
  stride 22, nodes base 9733 stride 50); `tribeStats[2][26]` (resources
  capped at 999); `occupancyGrid` + `rebuildSpatialHash`; tick order
  `tickBuildings` → `tickWorld` → `tickAnimals` → `tickDirector` (20/s);
  `setTask` (task codes 0–111); `findPath` (3 candidates, max 80/5);
  `stepProjectile`/`landProjectile` (projectiles live in the thrower's own
  entity fields); `tickEnemyAI` (wave interval rec 214 − rec 215 ×
  difficulty); `checkWinLose` (seed rename, q=5 anomaly reproduced).
- **MidiPlayer (`a`):** `startPlayback` / `stopPlayback` / `getPlayerState` /
  `closePlayer` / `setVolume` (level = setting × 20, 0 = stop).
- **TextLoader (`c`):** `loadTextFile` (`/<language>/s|t|d`), `selectStorySection`
  (ids 70–75 → story file `d0`), `queueLine` / `queueLineParts` / `drawLines` /
  `updateFloatingTexts` (the "+N"/"−N" ring, slots 13–32) / `wrapText` /
  `drawTextPage`; glyphs from `pi0[25+size]`, row = code/30, column = code%30.
- **ResourceLoader (`e`):** ctor order `allocateBalanceTables` →
  `initScreenBuffers` → `initWorldState` → `loadSpritePack` (pi0, pi9) →
  `loadBalanceData` (`/pd0`) → `loadGameData` (`/a`, 312 records) →
  `initTextLoader` → `loadMusic` (`/sa`) → `loadMaps` (`/ma`).
- **Splash (`d`):** `showSplash` (images `/l0` THQ, `/l1` Gear Games,
  `/l2` title, index 5 = done).
- **TribesMIDlet:** `sleep` (1 s / 2 s / 2 s between splashes), `splashScreen`,
  `display`.

## What remains unknown

- **30 auto-named methods in Game** (`names-auto.map`): single-letter
  overloads the readers did not name (e.g. `a_byte_void`,
  `c_int_int_int_void`, the `a(boolean×9)` panel-flags writer). They compile
  and work; they are just not readable yet. Seven were flagged in the Game
  proposal as small helpers worth a second read (`b(byte)`, `c(byte)`,
  `d(byte)`, `e(byte)`, `f(byte)`, `f(int)`, `c(int,int,int)`).
- **3 Unknown placeholders:** instance short arrays `f.c`/`f.e`/`f.f`
  (`unusedShortArrayC/E/F`) — declared, no access found in any class.
  Settles it: grep the other classes' bytecode for fieldrefs to `f.c:[S` /
  `f.e:[S` / `f.f:[S`.
- **World:** exact meanings of record-27 flag bits and record-41 categories;
  which building type occupies player slot 1 (`a[8506]`, treated as the chief
  hut by `checkWinLose`); record-279 bit 4; building field 13 (offset 9176)
  never used in `b`; dual use of building field 8810 (foreman id vs hut
  resident count); villager fields 1616/1717/7575; animal field details;
  caller of `scanForTargetKind` (no callers in `b.java`).
- **Game:** L-vs-N place-confirm flags split; T-vs-U menu flags split;
  `speedSetting` label (menu items 23–25); what entity ids 101–122 are;
  `toggleOptions` vs change-chief; `visiblePanelCount`; `spriteFlipped`
  (flip vs visibility); the `a(boolean×9)` write to `byte P`; 3
  probably-vestigial never-written tables (`focusTable` [3][10],
  `spriteYOffsetTable`/`spriteFrameTable` [13][101]); `cheatFlagAg` may gate
  something in World; `tickCount` probably a leftover counter.
- **Small classes:** `selectStorySection` for section ids ≥ 76 (would open
  nonexistent `/d16733`); why `packImageCounts` is 31 for pi0 when only 27
  PNGs exist; whether `f.java:4876-77` calls `loadTextFile` or
  `World.b(int)`; dual-role scratch fields may have been separate variables
  originally.
- **Cross-cutting (from ROADMAP):** 21 scripted mission events (records
  288/291); unknown `/a` records 273–278, 283, 296–300, 305, 306, 311; tile
  visual meanings; spawn-object types 1–5; weapon-name mapping; rec-138 rows
  0, 1, 2, 3, 15 and types 23–25.

## Notes for the translator (Phase 3)

- Six new names are shared across classes with different old names
  (`game`, `entityData`, `recordStarts`, `tribeStats`, `drawX`, `drawY`).
  They are legal in Java (different classes) but the translator must stay
  class-scoped when resolving them.
- Several Game fields are written by `ResourceLoader`, not by Game itself
  (manifest properties, `/sa` song tables, screen tables from `e.c()`,
  menu layout from `e.b()`). The translator must keep these cross-class
  writes.
- `int O`/`int P` (sprite temps) are different fields from `byte O`/`byte P`
  (screenColsTiles/screenRowsTiles); likewise `int A` (viewHeightPx, 288) vs
  `byte A` (cursorRow, 9). The type in the map key is what separates them.
- Save format order (`loadSlot`/`saveSlot`, RecordStores `t1`/`t2`) and the
  `t0` settings order must be preserved exactly, or old saves break.
- The q=5 win anomaly is reproduced, not fixed.

## Working notes

The three reader proposals (`f-game.md`, `b-world.md`, `small-classes.md`)
were validated and merged into `names.map` / `CLASS_MAP.md`; the surviving
proposal files are kept as backup in `~/workspace/rename-proposals/`
(`small-classes.md` was lost when `/tmp` was wiped mid-session, but its
content is fully merged). Nothing in this phase was pushed to GitHub.
