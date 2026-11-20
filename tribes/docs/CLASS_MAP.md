# Class map

Obfuscated name → role, with confidence and evidence. Only members whose role was confirmed from call sites are renamed; see `names.map` for the rename list.

| Class | Role | Confidence | Evidence |
| --- | --- | --- | --- |
| `tribes` | MIDlet entry point | Certain | `extends MIDlet`, 78 lines; `startApp` starts the game thread, shows splash `d`, then `new f(this)`. |
| `f` | Main game: Canvas, game loop, menus, save/load | Certain | 6,680 lines; owns the screen state `Q`, mode `v` (0 menu, 1 in-game), the 20 ticks/s loop, RecordStore `t0`/`t1`/`t2` handling. |
| `b` | World logic and AI | Certain | 5,805 lines; owns the 96x96 grids, tribes, villagers, buildings, waves, win/lose check `b.ak()`. |
| `e` | Resource loader | Certain | 280 lines; loads files from the JAR (`/a`, `ma`, `pi0`/`pi8`/`pi9`, `sa`). |
| `c` | Text loader | Certain | 404 lines; loads the 5 languages (`s` 229 UI strings, `t` 72 help, `d0` 236 story pieces). |
| `d` | Splash screens | Certain | 53 lines; THQ logo 1 s → Gear Games logo 2 s → title 2 s. |
| `a` | MIDI player | Certain | 118 lines; plays the 5 songs concatenated in file `sa` (26,616 bytes), random choice. |

## Proven methods

| Method | Role | Confidence | Evidence |
| --- | --- | --- | --- |
| `b.ak()` | Win/lose decision | Certain | Win: enemy buildings `a[1][14] ≤ 0` and `(rec279[q] & 1)` → timer `u = 1`; lose: player population `a[0][11] ≤ 0` or (`a[8506] ≤ 0` and `(rec279[q] & 2)`) → `u = 50`; verdict at `u == 41` (win) / `u == 90` (lose). |
| `f.aw()` | Custom Game menu gate | Certain | `r > 0 || af` opens the difficulty/custom list; else shows `t[70]` ("pass the tutorial first!"). `r` is campaign progress 0–7; `af` is never set. |
| `f` tutorial | Menu item 12 → q=0 | Certain | Tutorial is mission q=0; menu item 13 sets q=7 ("the end"). |


## tribes (TribesMIDlet)

The MIDlet entry point: `startApp()` grabs the `Display` on first call and starts the `run()` thread, and on later calls just resumes the Game; `pauseApp()`/`destroyApp()` forward to Game. `run()` garbage-collects, creates the `Splash`, and steps `splashIndex` 0 → 1 → 2 → 5 with 1 s / 2 s / 2 s sleeps (via the `sleep()` helper), then constructs the Game and starts it. It owns only three fields: the Game, the splash screen, and the Display.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| tribes.a:f | game | Certain | `run()` ends with `new f(this)` and `startApp`/`pauseApp`/`destroyApp` forward lifecycle calls to it. |
| tribes.a:d | splashScreen | Certain | `run()` creates it, shows it with `setCurrent`, and steps its index 0 → 1 → 2 → 5. |
| tribes.a:Display | display | Certain | `startApp()` assigns `Display.getDisplay(this)` once, then shows the splash on it. |
| tribes.a(int):void | sleep | Certain | Body is only `Thread.sleep(var0)` in try/catch; `run()` calls it with 1000/2000 between splashes. |

## a (MidiPlayer)

The only sound in the game: one MMAPI MIDI player, no sound effects. The 5 songs live concatenated in file `sa` (26,616 bytes); Game picks the track, and if there is no player, its state is ≤ 100 (UNREALIZED), or the track changed, it releases the old player (`stopPlayback` + `closePlayer`) and builds a new `MidiPlayer("audio/midi")`. The ctor slices Game's music buffer at `[offsets[song] .. +lengths[song]]`, with lengths {8014,6315,5648,3405,3234} and offsets {0,8014,14329,19977,23382}. Game's per-tick check calls `setVolume(setting)` (level = setting×20, 0 = stop) and re-picks a song whenever nothing is STARTED (400); `playerUpdate` restarts playback on `deviceAvailable` and stops it on `deviceUnavailable`.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| a.a:Player | player | Certain | The MMAPI Player created in the ctor, realized, and started/stopped/closed by every method. |
| a.a:VolumeControl | volumeControl | Certain | Fetched via `getControl("VolumeControl")` in `setVolume` and used for `setLevel`. |
| a.c():void | startPlayback | Certain | Prefetches and starts unless already STARTED (400); called on `deviceAvailable` and from `setVolume`. |
| a.a():void | stopPlayback | Certain | If STARTED (400) it rewinds to 0 and stops; called on `deviceUnavailable` and by Game's release path. |
| a.a():int | getPlayerState | Certain | Returns `getState()`, or -1 when there is no player; Game tests it against 100/400. |
| a.b():void | closePlayer | Certain | Closes the player unless already CLOSED (0); Game's release calls stop, then close, then nulls the field. |
| a.a(int):void | setVolume | Certain | 0 stops playback, otherwise it ensures playing and calls `setLevel(var1 * 20)`; Game passes its music setting. |

## e (ResourceLoader)

Runs once from its ctor, in fixed order: `allocateBalanceTables` (sizes Game's 11 short + 25 byte tables from the 36-entry `pd0TableLengths` literal), `initScreenBuffers` (screen geometry, 13 screen-layout arrays, offscreen images), `initWorldState` (entity array, 96x96 grids, JAD properties `MIDlet-Version`/`GameLinkEnabled`/`Cheat-Support`), `loadSpritePack` for pi0 then pi9 (`currentPackIndex` 0/1), `loadBalanceData` (`/pd0`), `loadGameData` (`/a`: game-data + font tables), `initTextLoader` (creates the TextLoader and loads `/s`), `loadMusic` (`/sa`), `loadMaps` (`/ma`). Sprite packs are concatenated PNGs prefixed by 2-byte big-endian lengths, terminated by `0xFF`; pi8 (story art) is deliberately not loaded here — Game loads it lazily on demand.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| e.a:f | game | Certain | Ctor stores the Game; every loader method writes into its tables, images and flags. |
| e.a:short[] | pd0TableLengths | Certain | 36-entry literal (11 short + 25 byte table sizes) consumed by `allocateBalanceTables`/`loadBalanceData`; sums to the 96,990-byte `/pd0` layout. |
| e.a:byte | currentPackIndex | Certain | 0 then 1 in the ctor; `loadSpritePack` uses it to index `packPaths` and `packImageCounts`. |
| e.a:byte[] | packImageCounts | Likely | `{31,6,12}` is the image-array size / loop bound per pack; pi0 allocates 31 slots but only fills 27 PNGs (guard `var3 < 27`). |
| e.a:String[] | packPaths | Certain | `{"/pi0","/pi9","/pi8"}`; `loadSpritePack` opens `packPaths[currentPackIndex]`; pi8 is only loaded lazily by Game. |
| e.a():void | allocateBalanceTables | Likely | Allocates Game's 11 short tables and 25 byte tables sized from `pd0TableLengths`; runs first, before `loadBalanceData` fills them. |
| e.b():void | loadBalanceData | Certain | Skips 1 header byte of `/pd0`, reads the 11 short and 25 byte tables, then derives tile-size values `aU`/`aV`. |
| e.c():void | initScreenBuffers | Likely | Sets screen geometry and the 13 screen-layout arrays, then creates the 264x304 (x2) and 79x63 offscreen images. |
| e.d():void | loadSpritePack | Certain | Reads `packPaths[currentPackIndex]` into a 62,502-byte buffer and splits 2-byte-big-endian-length PNG entries into Game images, stopping at `0xFF`. |
| e.e():void | initTextLoader | Certain | Creates the TextLoader, allocates its 33-line buffers (122 deltas, 250 wrap slots) and loads `/s` via `loadTextFile(0)`. |
| e.f():void | loadGameData | Certain | Reads `/a`: 312 little-endian lengths + 17,638-byte blob, plus the 570-entry offset table in 5 groups (bases 0,122,243,249,273). |
| e.g():void | loadMusic | Certain | Sets song lengths `{8014,6315,5648,3405,3234}` and offsets `{0,8014,14329,19977,23382}`, then reads `/sa` (26,616 bytes) into Game's music buffer. |
| e.h():void | loadMaps | Certain | Sets map offsets `{0,2545,6965,10649,15487,22633,27261,31708,35466,39214}` and reads `/ma` (42,381 bytes) into Game's map buffer. |
| e.i():void | initWorldState | Likely | Allocates the 9,983-byte entity array and 96x96 terrain/occupant grids, then reads `MIDlet-Version`, `GameLinkEnabled` and `Cheat-Support` and sets the link flag. |

## c (TextLoader)

Loads and renders all text in the game's 5 languages (folders `0`–`4`: English, French, Italian, German, Spanish). `loadTextFile` opens `/<language>/s`, `/t` or `/d<storyFileIndex>`: `s` holds 229 UI strings, `t` 72 help strings, `d0` 236 story pieces; bytes are split on `|` into offset tables and mapped through font record 280. `selectStorySection` (called with 70–75) records the section id used to offset story text ids ≥ 71 via record 309, and reloads the `d` file when the file index changes — in practice always `d0`, the only story file shipped. Rendering has three systems sharing 33 line slots: static lines (slots 0–12), floating "+N"/"-N" numbers (slots 13–32, a ring fed by the World's per-entity `pendingDeltas`), and wrapped paragraphs (`wrapText`/`drawTextPage`, 17 px per line, paged). Glyphs are drawn from `pi0[25+size]`, row = code/30, column = code%30, widths from records 281+size.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| c.a:f | game | Certain | Ctor stores the Game; used for screen buffers, the language string, the tick counter and the page flags. |
| c.a:byte[] | dataBlob | Certain | The 17,638-byte `/a` file blob copied from Game, indexed by `recordStarts`. |
| c.a:short[] | recordStarts | Certain | 312 cumulative offsets into `dataBlob` (record 280 = font map, 281+ = glyph widths, 308/309 = story thresholds). |
| c.a:byte | lineCount | Certain | Bytecode: `drawLines` loops `k < lineCount` and resets it to 0, `queueLine` increments it for static lines, `queueLineParts` recenters up to it. |
| c.a:int | charIndex | Likely | Loop index over characters while queuing a line and while copying a literal string into temp slot 228. |
| c.a:InputStream | inputStream | Certain | The open text-file stream in `loadTextFile`, closed and nulled after parsing. |
| c.a:String[] | textFileNames | Certain | `{"/s","/t","/d"}`; `loadTextFile` opens `"/" + language + name[index]`. |
| c.a:byte[][] | textBuffers | Certain | `{uiTextData, longTextData, longTextData}`; `loadTextFile` writes decoded bytes into `buffers[index]`. |
| c.a:short[][] | textOffsets | Certain | `{uiTextOffsets, longTextOffsets, longTextOffsets}`; one offset per string, `|` advances the index. |
| c.b:byte[] | uiTextData | Certain | 1,674-byte buffer holding the decoded `/s` file (229 UI strings). |
| c.b:short[] | uiTextOffsets | Certain | 230 offsets for the `/s` strings, filled by `loadTextFile(0)`. |
| c.b:byte | floatWriteIndex | Certain | Ring write index for floating slots 13..32; `queueLine` uses `13 + index` and wraps at 20. |
| c.b:int | readByte | Certain | Holds each byte read from the text stream while parsing in `loadTextFile`. |
| c.b:byte[][] | glyphColumns | Certain | `glyphColumns[line][char]` = encoded code % 30, filled by `queueLine`. |
| c.c:byte[] | longTextData | Certain | 37,488-byte buffer shared by the `/t` (help) and `/d` (story) files. |
| c.c:short[] | longTextOffsets | Certain | 310 offsets shared by `/t` (0..71) and `/d` (71..306). |
| c.c:byte | floatReadIndex | Certain | Ring read index for floating slots; `updateFloatingTexts` advances it when a text expires (age >= 15). |
| c.c:int | cursor | Likely | Write cursor while parsing text files, and scan index over the record-308 threshold in `selectStorySection`. |
| c.c:byte[][] | glyphRows | Certain | `glyphRows[line][char]` = encoded code / 30, filled by `queueLine`. |
| c.d:byte[] | lineCharCounts | Certain | `lineCharCounts[line]` = character count of the queued line (string length from the offsets). |
| c.d:short[] | linePixelWidths | Certain | `linePixelWidths[line]` = pixel width summed from glyph widths, used to center lines. |
| c.d:byte | currentCharCode | Certain | Bytecode shows it only in `queueLine`: the encoded char just read from the text buffer, 126 = special (space/kerning) code. |
| c.d:int | stringIndex | Certain | Bytecode shows it only in `loadTextFile`: counts strings while parsing (0 for s/t, 71..306 for d), so it cannot be the byte field (306 > 127). |
| c.e:byte[] | lineFontSizes | Certain | `lineFontSizes[line]` = font-size param, selecting glyph-width table `281 + size`. |
| c.e:short[] | lineYOrAge | Likely | Y position for static lines (h<13) and age 0..15 for floating lines (h>=13), which rise and expire; one array, two roles by slot range. |
| c.e:int | langNameCharIndex | Likely | Bytecode shows it only in `loadTextFile`'s 31-case, indexing characters of the language folder name for accent expansion. |
| c.f:byte[] | pendingDeltas | Certain | World adds/subtracts per-entity amounts (b.java:1655,3287,4446); `updateFloatingTexts` renders them as "+N"/"-N". |
| c.f:short[] | lineXOrEntityId | Likely | X position for static lines (h<13) and owning entity id for floating lines, matched by `drawFloatingTexts`; one array, two roles by slot range. |
| c.f:int | storyFileIndex | Certain | Appended as `"/d" + index` by `loadTextFile(2)`; set by `selectStorySection`; initial -1. |
| c.g:int | storySectionId | Likely | Set by `selectStorySection` to 70..75; `wrapText` adds record-309[section] to story text ids >= 71. |
| c.g:short[] | wrappedLineStarts | Certain | `wrappedLineStarts[u]` = text offset where wrapped line u begins; `drawTextPage` iterates between starts. |
| c.h:int | currentLineSlot | Likely | Slot index being filled by `queueLine`; `queueLineParts` remembers it as the first line to recenter. |
| c.h:short[] | wrappedLineXOffsets | Certain | `wrappedLineXOffsets[u]` = centered x of wrapped line u; `drawTextPage` starts each line there. |
| c.i:int | totalWidth | Likely | Accumulated pixel width of the up-to-3 parts queued by `queueLineParts`, used to recenter them. |
| c.j:int | firstLineIndex | Likely | First line queued by `queueLineParts`; the recenter loop runs from `firstLineIndex` to `lineCount`-1. |
| c.k:int | loopIndex | Likely | Shared loop counter in reset, draw and update methods; also the entity id 0..121 in `updateFloatingTexts`. |
| c.l:int | drawCharIndex | Likely | Character index within the line being drawn in `drawLines`, `drawFloatingTexts` and `drawTextPage`. |
| c.m:int | drawX | Likely | Running x cursor while drawing a line; starts at the line's x and advances by glyph widths. |
| c.n:int | drawY | Likely | Y position of the line being drawn (static y, floating rise, or wrapped-page row). |
| c.o:int | glyphWidth | Certain | Pixel width of the current glyph from the font table; advances `drawX`. |
| c.p:int | prevGlyphWidth | Certain | Previous glyph's width, kept for the special-glyph centering adjustment (`glyphDrawX - (width + prevWidth)/2`). |
| c.q:int | glyphDrawX | Likely | X actually used to draw the current glyph after the special-glyph adjustment. |
| c.r:int | wrappedTopY | Likely | Y of the first wrapped line, including vertical centering when the block is shorter than its box. |
| c.s:int | wrappedFontSize | Certain | Font size of the wrapped block; `drawTextPage` indexes glyph tables by `281 + size`. |
| c.t:int | linesPerPage | Certain | Box height / 17; `drawTextPage` draws at most this many lines from the start line. |
| c.u:int | wrappedLineCount | Certain | Number of lines `wrapText` produced; `drawTextPage` stops at this count. |
| c.v:int | wrapScanIndex | Likely | Position scanned through the text while wrapping, rewound to `lastBreakIndex` on overflow. |
| c.w:int | lastBreakIndex | Likely | Text offset just after the last break opportunity (space, `*`, or code 127) found while wrapping. |
| c.x:int | currentLineWidth | Likely | Accumulated pixel width of the wrapped line in progress, compared against the box width. |
| c.y:int | widthAtLastBreak | Likely | `currentLineWidth` as it was at the last break opportunity, used to recenter the finished line. |
| c.z:int | rowOrNumberIndex | Likely | Glyph row (code/30) for normal glyphs, or number-table index (code-112-1+19) for codes >112 in `drawTextPage`; one scratch, two roles by branch. |
| c.A:int | glyphColumn | Likely | Glyph column (code%30) in `drawTextPage`; its only use in the class. |
| c.a():void | reset | Likely | Zeroes floating slots 13..32, pending deltas 0..121 and both ring indices; no external call site pins it, the body is the evidence. |
| c.a(int):void | selectStorySection | Likely | Stores the section id, scans the record-308 threshold (single byte 76) to derive the story file index, reloads the d-file if it changed; call sites pass 70..75. |
| c.b(int):void | loadTextFile | Certain | Opens `"/" + language + {s,t,d} + index`, splits on `|` into offsets and maps bytes through font record 280; index 2 reloads the story file. |
| c.a(int,int,int,String,int):void | queueString | Likely | Copies a literal string into temp slot 228, then queues it as a line via `queueLine`. |
| c.a(int,int,int,int,int):void | queueLine | Likely | Converts string-table entry var4 into glyph rows/columns, width and position, in a static (h<13) or floating (h>=13) slot. |
| c.a(int,int,int,int,int,int,int):void | queueLineParts | Likely | Queues up to three string-table entries on one line and recenters all lines from `firstLineIndex` to `lineCount`-1. |
| c.a(Graphics):void | drawLines | Certain | Draws every queued static line glyph by glyph from `pi0[25+size]` and resets `lineCount` to 0. |
| c.b():void | updateFloatingTexts | Certain | Ages floating texts (age rises by age/4+1, expires at 15) and turns pending per-entity deltas into "+N"/"-N" floating texts. |
| c.a(int,int,int):void | drawFloatingTexts | Likely | Draws, at (x,y), the floating texts whose entity id matches var1, using font table 282. |
| c.b(int,int,int,int,int,int,int):void | wrapText | Certain | Wraps text id var5 into lines no wider than var3, recording line starts and centered x offsets plus top y, font size and lines-per-page. |
| c.a(Graphics,int):void | drawTextPage | Likely | Draws one page of the wrapped text starting at line var2, handles number codes >112, and sets Game's page flags Q/R. |

## d (Splash)

A full-screen Canvas shown before the Game exists. It owns a single 264x352 offscreen buffer (created once, guarded by `bufferCreated`) and a `splashIndex`; `showSplash()` clears the buffer white, draws the image `/l<index>` centered at (131,168) while the index is < 4, and repaints. `tribes.run()` drives it: index 0 = THQ logo (1 s), 1 = Gear Games logo (2 s), 2 = title screen (2 s), 5 = done (nothing drawn). `paint()` copies the buffer to the screen at (-11,-8), so the visible 240x320 area is the buffer's centre; `keyPressed()` is empty, so input is ignored during the splash.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| d.a:byte | splashIndex | Certain | `tribes.run()` sets 0, 1, 2, 5; `showSplash` draws `"/l" + index` only while index < 4. |
| d.a:boolean | bufferCreated | Certain | Set true right after creating the 264x352 offscreen image and its Graphics; guards one-time creation. |
| d.a():void | showSplash | Certain | Clears to white, draws `"/l" + splashIndex` centered (131,168) when index < 4, then `repaint()`. |
| d.b():void | clearToWhite | Certain | Static helper filling rect (11,8,240,321) white on the offscreen Graphics. |


## b (World)

How class `b` works

1. **Data model.** One flat `entityData` byte array (9,983 bytes) holds everything: villagers
   (fields 0–82, stride 101, ids 1–50 player / 51–100 enemy), buildings (base 8383, 15 fields,
   stride 61, slots 1–30 player / 31–60 enemy), animals (base 9359, 17 fields, stride 22) and
   resource nodes (base 9733, 5 fields, stride 50). Per-tribe stats live in `tribeStats[2][26]`
   (resources 0–4 capped at 999, population, chief id, building count). The 96x96 world is
   tracked by `occupancyGrid` (0 empty, 1–100 villager, negative building/trap codes, −128
   moving) plus a spatial hash (`hashNext`, rebuilt by `rebuildSpatialHash`) and a fog grid.
2. **Tick order (20 ticks/sec).** The Game calls `tickBuildings`, `tickWorld`, `tickAnimals`,
   `tickDirector` per tick. `tickWorld` runs a preamble, then per tribe per villager:
   pre-check → move state → sick check → action selector → task progress → movement →
   post-move action → projectile step; then hut births, the projectile grid, the calm timer
   and the resource clamp. `tickBuildings` handles upgrades, repairs, traps and workers.
3. **Task system.** A villager's current task is entity field 2828 (codes 0–111, see
   `task-table.csv`); `setTask` resets progress, loads the duration from rec 38 and the pose
   from rec 32. Families: ≤9 go-to/interact, 10–16 gathering, 19–26 work, 28–49 spells,
   44–50 jobs, 69 idle, 77–85 combat, 109–111 mating. Needs (hunger 2020, energy 2121,
   mood 505, disease 8080) are ranked by `rankNeeds`/`pickDesire` and drive task choice.
4. **Movement.** `findPath` builds 3 candidate routes (8-direction wall-following, max length
   80 for villagers / 5 for animals), scores them by cost and merges the winner into
   `villagerPaths`; `followPathStep`/`moveVillagerStep` advance the villager and keep
   `occupancyGrid` in sync.
5. **Combat.** Thrown weapons are tracked in the thrower's own entity fields (target,
   velocity, flight tick/duration); `stepProjectile`/`landProjectile` advance and resolve
   them, `applyDamageAt` deals damage, and traps/upgrades live in `trapRegistryF`/
   `upgradeRegistryG`.
6. **Enemy AI and waves.** `tickEnemyAI` runs build/repair, wave timing (`nextWaveTickAQ`,
   interval rec 214 − rec 215 × difficulty), role assignment by rank (`aiRank`, threshold
   rec 220), return-home, enlistment and the wave advance toward the player village.
7. **Win/lose.** `checkWinLose` (seed rename): win when enemy buildings reach 0 and the
   mission's rec-279 flag bit 1 is set; lose when player population reaches 0 or the chief
   hut (building slot 1, `a[8506]`) falls with rec-279 bit 2 set; the verdict is delivered at
   `f.u == 41` (win) / `90` (lose). The q=5 near-wipe countdown reset is reproduced, not
   fixed. `updateMusicMood` only sets the music phase — it does not decide win/lose.

### Entity field layout (offsets into `entityData`, not `names.map` entries)

Villager `v` (1–100), field `i` at `i*101+v`: 0 x, 101 y, 202/303 pixel offsets, 404 task
progress, 505 mood, 606 facing, 707 path step, 808 path length, 1010 speed, 1111 skill+phase,
1313 state, 1414 age (adult ≥12), 1515 gender, 1818 task duration, 1919 HP, 2020 hunger,
2121 energy, 2828 task, 2929 role, 3131/3232 target x/y, 3333 desire, 3434 home hut,
4444 target entity (≥0 villager, <0 building), 4545 linked-list next, 4646 work slot,
4848 stuck counter, 5050 idle timer, 5858/5959 projectile flight tick/duration, 6161 movement
state (0 still, 1 walking, 2 to-target, 4 returning), 6262/6363 home x/y, 6565 profession
(9 = unemployed), 6666 age-progress counter (ages at 125 ticks player / 50 enemy), 6767 max HP,
7575 sight/strength, 7979 status flags (bit 1, 2, 4 disease, 16, 32, 64, 128), 8080 disease
meter (≥30 → sick flag), 8282 mating/flags.

Building slot `s` (1–60), field `i` at `8383 + i*61 + s` (field index `i` = 0, 61, 122, …):
8383 x, 8444 y, 8505 HP, 8566 max HP, 8627 mode (0 normal, 1 upgrading, 2 repairing),
8688 packed range (low nibble) + power (high nibble, towers), 8749 type (0 armoury, 1 gym,
2 hut, 3 chief's hut, 4 kitchen, 5 range, 6 store, 7 disco, 8 sanctuary, 9 pia center,
10 nursery, 11 tower, 12+ traps), 8810 foreman id / hut resident count (dual use by type),
8871 disease flag (bit 2), 8932 worker count, 8993 worker-list head, 9054 flags (bit 0
upgrade busy, bit 7 recently hit), 9115 level (1-based), 9176 unused in `b`, 9237 state
(hut mating 0–3, trap progress, saved foreman during upgrade), 9298 assigned-worker count.

Animals (base 9359, stride 22, indices 0–21) and resource nodes (base 9733, stride 50,
indices 0–49; 9733 x, 9783 y, 9833 type, 9883 amount, 9933 stage) use the same flat array.

### Record tables cached from `f` (file `a`)

`recordBlob` holds the file-`a` record bytes indexed by `recordStarts` (records 26–302);
notable records: 38 task durations, 39 movement class, 41 category, 53 25x25 distance table,
69 requirement ranges, 138 building table, 214/215 wave interval, 220 AI rank threshold,
279 win/lose flags per mission `[2,3,2,4,3,2,3]` for q=0–6. `fieldOffsets` is the 570-entry
offset table (`fieldOffsets[23+I]` = 2323/2424/2525/2626) with group bases `offsetGroupBases`
`[0,122,243,249,273]`.

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| b.a:f | game | Certain | The only Game reference; `attachGame` stores the `f` passed in and every `this.a.<field>` access reads game state (map, messages, tick). |
| b.a:byte[] | entityData | Certain | One 9,983-byte array holding all villagers (stride 101), buildings (base 8383, stride 61), animals (base 9359, stride 22) and resource nodes (base 9733, stride 50). |
| b.a:byte[][][] | villagerPaths | Likely | Indexed `[v][step][point]` in `followPathStep`/`mergePaths`; filled by `findPath` for the current villager only. |
| b.b:byte[][][] | animalPaths | Likely | Same shape as villager paths but written only by animal code (`moveAnimal`, `retargetAnimal`, `setAnimalTarget`). |
| b.a:short[][] | tribeStats | Certain | `[2][26]` per-tribe stats: `[u][0..4]` resources clamped to 999, `[11]` population, `[17]` chief id, `[14]` building count. |
| b.b:byte[] | recordBlob | Likely | Copied from `f`'s file-`a` record blob in `attachGame`; indexed by `recordStarts`, e.g. rec 38 task durations, rec 138 building table, rec 279 win/lose flags. |
| b.a:short[] | recordStarts | Likely | Start offsets into `recordBlob` for file-`a` records 26–302, built in `attachGame` from `f`'s offset table. |
| b.b:short[] | fieldOffsets | Likely | 570-entry offset table where `fieldOffsets[23+I]` = 2323/2424/2525/2626, i.e. entity-field offsets in steps of 101. |
| b.c:short[] | offsetGroupBases | Likely | Group bases `[0,122,243,249,273]` used with `fieldOffsets` to index entity fields by group. |
| b.c:byte[][][] | diseaseSpots | Likely | `diseaseSpots[u][i]` holds (x, y, count, tribe) of disease-spot grids consumed by `spreadDiseaseAndChiefCheck` and `drainNeeds`. |
| b.a:byte[][] | hashNext | Likely | Written only by `rebuildSpatialHash` as next-links and followed only by the `scanSector` hash walk. |
| b.b:byte[][] | occupancyGrid | Certain | 96x96 grid read by `tileWalkable`/`findPath` as 0 empty, 1–100 villager, negative building/trap codes, −128 moving, −127 special. |
| b.a:byte | facingDirection | Likely | Set to 0–7 (compass octant) by `computeDirection` from dx/dy and read by walk/combat code as the villager's facing. |
| b.b:byte | pathChoice | Likely | Index (0–2) of the chosen candidate path, set by `findPathTo` and read by `mergePaths`/`repathToTarget`. |
| b.c:byte | tileCheckResult | Likely | Holds the 0/1/2 result of the tile check in `a(int,int,int)` before it is returned as a byte. |
| b.c:byte[] | directionSteps | Likely | 8-direction step table `(dx,dy)` pairs, indexed `directionSteps[dir*2]` in `followPathStep` and `moveVillagerStep`. |
| b.d:byte[] | pathCursors | Likely | Per-path current step cursor, advanced by `followPathStep` and reset when a path is rebuilt. |
| b.e:byte[] | pathCosts | Likely | Per-path cost (walked length + penalty), compared in `findPathTo` to pick the cheapest of the 3 candidates. |
| b.f:byte[] | pathLengths | Likely | Per-path length in steps, written by `findPathTo` and read by `mergePaths` and the 80/5 length cap. |
| b.d:byte[][][] | candidatePaths | Likely | Three candidate routes `[3][maxLen][2]` scored by `findPathTo`; `pathChoice` selects one to copy into `villagerPaths`. |
| b.g:byte[] | pathOrder | Likely | Order in which the 3 candidates are tried/evaluated, written by `findPathTo` before scoring. |
| b.e:byte[][][] | pathSegments | Likely | Up to 10 path segments per candidate, consumed by `planRoutes` to turn a route into waypoints. |
| b.h:byte[] | savedTerrain | Likely | Saved terrain bytes under a planned path, restored by `mergePaths` when a path is discarded. |
| b.d:byte | tileBlockedCost | Likely | Cost penalty (1/2/3) written by `scoreDetour` for a blocked tile and read by `findPathTo`. |
| b.e:byte | prevTileBlocked | Likely | Previous tile's blocked flag, compared in `handleBlockedMove` to decide whether to repath. |
| b.c:byte[][] | matingPairs | Likely | `matingPairs[u][pair][2]` = (male, female) villager ids, written by `tryFindMate`/`findNearbyMate` and cleared by `clearMatingPair`. |
| b.a:int | loopIndexA | Structural | Plain loop counter used as a tile/villager index in several path and scan loops. |
| b.b:int | scratchIntB | Structural | Temporary integer reused across unrelated path and scan computations. |
| b.c:int | segmentCount | Likely | Count of path segments produced by `planRoutes`, read by `mergePaths` when splicing segments. |
| b.d:int | pathLengthD | Likely | Length of the current path being built in `buildCandidatePaths`/`planRoutes`. |
| b.e:int | pathIndexE | Likely | Index of the candidate path currently being scored in `findPathTo`. |
| b.f:int | dirAttemptF | Likely | Direction tried by the 8-direction wall-following search in `findPathTo`. |
| b.g:int | turnDirG | Likely | Turn direction (left/right) chosen when wall-following hits a blocked tile. |
| b.h:int | spliceCountH | Structural | Count of path points spliced when `mergePaths` joins two path pieces. |
| b.f:byte | scriptEventIndex | Likely | Index of the scripted event being fired, set by `fireMissionEvents` and read by `fireScriptedEvent`. |
| b.g:byte | hintStepIndex | Likely | Current tutorial-hint step, advanced by `updateTutorialHints`. |
| b.i:int | maxPathLength | Likely | Set to 80 (villager) or 5 (animal) by `findPath`, and read by `mergePaths` as the cap on merged path length. |
| b.a:boolean[] | pathBlockedFlags | Likely | Per-direction blocked flags consulted by `followPathStep` before advancing the cursor. |
| b.j:int | loopIndexJ | Structural | Loop counter over path points and scan cells in pathfinding code. |
| b.k:int | distSquaredK | Likely | Squared distance accumulated in `distanceTo`-style comparisons in path and target code. |
| b.l:int | distSquaredL | Likely | Second squared-distance accumulator used when comparing two candidate targets. |
| b.m:int | detourIndexM | Likely | Index of the detour candidate chosen by `scoreDetour`, or −1 when none exists. |
| b.n:int | candidateCountN | Likely | Number of candidate paths/targets found so far in the current search. |
| b.d:byte[][] | pathPoints | Likely | Waypoint list `(x,y)` pairs for the current route, consumed by `planRoutes`. |
| b.h:byte | pathIdScratch | Structural | Scratch byte holding a path id while a path buffer is being written. |
| b.i:byte | pathLengthAccum | Likely | Accumulated path length inside `pathLengthViaWaypoints`, returned as a byte. |
| b.j:byte | pathSegIndex | Likely | Index of the current path segment inside `planRoutes`/`mergePaths`. |
| b.k:byte | distSquaredScratchK | Structural | Scratch byte holding a small squared distance in tile checks. |
| b.l:byte | tileQueryCode | Likely | Kind code (0–6) passed to `queryTile`/`tileWalkable` to select what to look for. |
| b.m:byte | chosenTask | Likely | Task code chosen by `pickDesire`/`assignJobTask` before `setTask` is called with it. |
| b.o:int | bestOffsetO | Likely | Offset of the best tile found by the spiral search, measured from the search origin. |
| b.p:int | bestDistSquaredP | Likely | Smallest squared distance found so far when scanning for the nearest target. |
| b.q:int | candXOrDistQ | Structural | Candidate x coordinate or distance value in target searches (role varies by caller). |
| b.r:int | candYR | Structural | Candidate y coordinate paired with `candXOrDistQ` in target searches. |
| b.n:byte | neighborDyN | Structural | Dy of a neighboring cell while scanning the 8 tiles around a villager. |
| b.o:byte | neighborDxO | Structural | Dx of a neighboring cell, paired with `neighborDyN`. |
| b.p:byte | neighborOccupantP | Likely | Occupant code read from `occupancyGrid` for a neighboring tile. |
| b.s:int | slotLoopS | Likely | Loop over building/worker slots in `updateBuildingWorkers` and `assignBuildingTask`. |
| b.t:int | workerCursorT | Likely | Cursor into a building's worker list while iterating it. |
| b.q:byte | targetXQ | Likely | X of the tile a villager is being sent to, used by `findFreeTileNear`. |
| b.r:byte | targetYR | Likely | Y of the tile a villager is being sent to, paired with `targetXQ`. |
| b.d:short[] | messageCooldowns | Likely | Per-message-type cooldown tick, decremented by `tickMessageCooldowns` and checked by `showMessage`. |
| b.u:int | tribeIndex | Certain | 0 = player tribe, 1 = enemy tribe; every per-tribe array (`tribeStats[u]`, `diseaseSpots[u]`) is indexed by it. |
| b.v:int | currentVillager | Certain | Id of the villager currently being updated (1–50 player, 51–100 enemy); all `entityData[...+v]` reads use it. |
| b.w:int | scratchIntW | Structural | Temporary integer reused in villager-update code. |
| b.x:int | scratchIntX | Structural | Temporary integer reused in villager-update code. |
| b.y:int | randomRollY | Likely | Random roll (0–99) compared against a chance, e.g. disease spread and mood changes. |
| b.z:int | distOrTargetXZ | Likely | Distance to, or id of, the current target in villager-update code. |
| b.A:int | targetYA | Likely | Y coordinate of the current target, paired with `distOrTargetXZ`. |
| b.B:int | taskDurationB | Likely | Duration of the current task in ticks, loaded from rec 38 by `setTask` and counted down in `updateTaskProgress`. |
| b.C:int | scratchIntC | Structural | Temporary integer reused in villager-update code. |
| b.D:int | scratchIntD | Structural | Temporary integer reused in villager-update code. |
| b.E:int | stepResultE | Likely | Result code of a single movement/action step, checked by `updateMoveState`/`updateAction`. |
| b.F:int | timerOrCountF | Structural | Timer or count field reused in several update methods. |
| b.a:boolean | searchOkA | Likely | True when the current tile/target search succeeded; gates the follow-up action. |
| b.b:boolean | searchFlagB | Likely | Second search-success flag used when two searches run in one update. |
| b.c:boolean | isWalkingToTargetC | Likely | True while the villager is walking toward a target tile; cleared on arrival. |
| b.G:int | missionRegionBaseG | Likely | Base offset into a mission script region, set from the current mission index `q`. |
| b.H:int | resolvedTaskH | Likely | Task code after resolving a role/task lookup, passed to `setTask`. |
| b.d:boolean | actionDoneD | Likely | True once the current action step finished; drives the `updateAction` state machine. |
| b.s:byte | buildingCapacityS | Likely | Capacity of the current building from rec 138, used when assigning workers. |
| b.I:int | specialModeI | Likely | Mode selector for special handling in `handleSpecialRoles` (chief, council, ritual). |
| b.J:int | hintTimerJ | Likely | Countdown to the next tutorial hint in `updateTutorialHints`. |
| b.K:int | calmTimerK | Likely | Ticks since the last combat; gates enemy-wave pacing in `tickDirector`. |
| b.t:byte | swapTempT | Structural | Temporary byte used when swapping two villager ids in `swapVillager`. |
| b.u:byte | savedVillagerU | Likely | Villager id saved before a swap/linked-list operation and restored afterwards. |
| b.L:int | walkCycleOrProgressL | Structural | Walk-cycle phase or action-progress counter in movement code. |
| b.i:byte[] | buildingSlotMapI | Likely | Maps a villager to its building slot (`buildingSlotMapI[v]`), built in `updateBuildingWorkers`. |
| b.v:byte | villagerLoopV | Likely | Villager id being iterated in building-worker loops (byte form of `currentVillager`). |
| b.w:byte | workerOrParityW | Structural | Worker index or parity flag in building-worker loops. |
| b.x:byte | listCursorX | Likely | Cursor into a villager/building linked list (via entity field 4545). |
| b.M:int | aiRankThresholdM | Likely | AI rank threshold from rec 220 for the current mission, used to pick wave units. |
| b.y:byte | rollByteY | Structural | Small random roll stored as a byte in AI and mood code. |
| b.z:byte | scratchByteZ | Structural | Scratch byte reused in villager-update code. |
| b.A:byte | scratchByteA | Structural | Scratch byte reused in villager-update code. |
| b.B:byte | occupantByteB | Likely | Occupant code of a tile read from `occupancyGrid` during scans. |
| b.C:byte | distByteC | Structural | Small distance value stored as a byte in target code. |
| b.D:byte | scratchByteD | Structural | Scratch byte reused in villager-update code. |
| b.E:byte | dirByteE | Likely | Direction (0–7) of a movement or throw, used by `walkStep`/`launchProjectile`. |
| b.F:byte | foundEntityF | Likely | Id of the entity found by the last scan, or 0 when none was found. |
| b.N:int | eventRectBaseN | Likely | Base index of the mission-event rectangle data for the current event. |
| b.O:int | waveStepO | Likely | Current step of the enemy-wave sequence in `aiWaveControl`. |
| b.P:int | pendingBuildP | Likely | Building type the AI is about to construct or repair in `aiBuildRepair`. |
| b.Q:int | waveTargetQ | Likely | Target villager/building id for the current enemy wave. |
| b.e:byte[][] | needyListE | Likely | `needyListE[u][i]` = (villager, need) pairs ranked by `rankNeeds` for the council. |
| b.G:byte | scratchByteG | Structural | Scratch byte reused in director/AI code. |
| b.H:byte | scratchByteH | Structural | Scratch byte reused in director/AI code. |
| b.e:boolean | councilPickedE | Likely | True once the council has picked a needy villager this tick. |
| b.R:int | flagsScratchR | Structural | Scratch integer holding packed flag bits in director code. |
| b.S:int | scratchIntS | Structural | Temporary integer reused in director/AI code. |
| b.f:boolean | taskCompleteF | Likely | True when the current task's completion conditions are met; read by `tryCompleteTask`. |
| b.T:int | reqIndexT | Likely | Index of the requirement currently being checked in `checkStatRequirements`. |
| b.g:boolean | allReqsMetG | Likely | Starts true and is cleared if any requirement fails in `checkStatRequirements`. |
| b.U:int | currentStatU | Likely | Current value of the stat being checked against a requirement. |
| b.V:int | hpMissingV | Likely | HP missing from a villager/building, used when picking a heal or repair target. |
| b.W:int | workTargetW | Likely | Entity id the current worker is assigned to. |
| b.X:int | needRangeStartX | Likely | Start of the need range being scanned in `rankNeeds`. |
| b.Y:int | needIndexY | Likely | Index of the need currently being evaluated in `rankNeeds`. |
| b.Z:int | scratchIntZ | Structural | Temporary integer reused in director/AI code. |
| b.aa:int | bestDistSqAa | Likely | Best (smallest) squared distance when searching for the nearest building. |
| b.ab:int | candDistSqAb | Likely | Candidate squared distance compared against `bestDistSqAa`. |
| b.ac:int | nearestBuildingAc | Likely | Slot of the nearest building found, or 0 when none is in range. |
| b.h:boolean | foundBuildingH | Likely | True when a building search found a matching building. |
| b.ad:int | mateCursorAd | Likely | Cursor scanning villagers while looking for a mate. |
| b.ae:int | matePairIndexAe | Likely | Index into `matingPairs` for the pair currently being processed. |
| b.af:int | pairScanAf | Likely | Scan position over `matingPairs` in `updateMating`. |
| b.I:byte | slotScanI | Likely | Building slot being scanned in mating/hut code. |
| b.ag:int | sectorIndexAg | Likely | Index of the sector being scanned by `scanSector`. |
| b.ah:int | cellXLoopAh | Likely | X cell loop variable in the spatial-hash scan. |
| b.ai:int | cellYLoopAi | Likely | Y cell loop variable in the spatial-hash scan. |
| b.aj:int | hashKindAj | Likely | Kind filter used by the spatial-hash scan (`scanSector`). |
| b.ak:int | hashCursorAk | Likely | Cursor walking the hash chain in `scanSector`. |
| b.al:int | hashEntityAl | Likely | Entity id found at the current hash-chain position. |
| b.am:int | foundPriorityAm | Likely | Priority of the best entity found by the hash scan. |
| b.an:int | bestDistAn | Likely | Best distance found by the hash scan so far. |
| b.ao:int | distTenthAo | Likely | Distance in tenths used when comparing scan candidates. |
| b.ap:int | foundXAp | Likely | X of the entity found by the hash scan. |
| b.aq:int | foundYAq | Likely | Y of the entity found by the hash scan. |
| b.ar:int | deltaXAr | Likely | Dx from the scan origin to the found entity. |
| b.as:int | deltaYAs | Likely | Dy from the scan origin to the found entity. |
| b.at:int | scanMinCellXAt | Likely | Minimum X cell of the scan rectangle in `setScanBounds`. |
| b.au:int | scanMaxCellXAu | Likely | Maximum X cell of the scan rectangle in `setScanBounds`. |
| b.av:int | scanMinCellYAv | Likely | Minimum Y cell of the scan rectangle in `setScanBounds`. |
| b.aw:int | scanMaxCellYAw | Likely | Maximum Y cell of the scan rectangle in `setScanBounds`. |
| b.ax:int | flightTimeAx | Likely | Ticks a projectile has been flying, counted by `stepProjectile`. |
| b.ay:int | flightStepAy | Likely | Current step along a projectile's arc, used by `stepProjectile`/`landProjectile`. |
| b.az:int | targetVelXAz | Likely | X velocity of the projectile's target, used to lead the throw. |
| b.aA:int | targetVelYAa | Likely | Y velocity of the projectile's target, paired with `targetVelXAz`. |
| b.aB:int | throwSpeedAB | Likely | Speed of the thrown projectile, from the weapon's record. |
| b.aC:int | throwFacingAC | Likely | Facing (0–7) of the thrower when the projectile was launched. |
| b.aD:int | throwTargetKindAD | Likely | Kind of the projectile's target (villager, building, tile). |
| b.aE:int | throwDxAE | Likely | Dx from thrower to projectile target. |
| b.aF:int | throwDyAF | Likely | Dy from thrower to projectile target. |
| b.aG:int | throwWeaponAG | Likely | Weapon type of the thrown projectile, from the thrower's task data. |
| b.aH:int | hutSlotScanAH | Likely | Hut slot being scanned while assigning a home hut. |
| b.aI:int | hutSlotScanAI | Likely | Second hut-slot scan cursor in `assignHomeHut`. |
| b.aJ:int | randomRollAJ | Likely | Random roll used when picking a hut or mate. |
| b.f:byte[][] | trapRegistryF | Likely | `trapRegistryF[10]` slots of (x, y, type, state) for placed traps, registered by `registerTrap`. |
| b.g:byte[][] | upgradeRegistryG | Likely | `upgradeRegistryG[20]` slots of upgrade jobs, registered by `registerUpgrade` and finished by `completeUpgrade`. |
| b.aK:int | capacityAK | Likely | Capacity (worker slots) of a building being processed in `updateBuildingWorkers`. |
| b.aL:int | workerScanAL | Likely | Cursor scanning workers of a building in `updateBuildingWorkers`. |
| b.aM:int | nodeLoopAM | Likely | Loop over resource nodes in `updateResourceNode`. |
| b.J:byte | needSlotJ | Likely | Need slot being evaluated in `rankNeeds`/`pickDesire`. |
| b.K:byte | rankShiftK | Likely | Shift applied to an AI rank when comparing villagers in `aiAssignRoles`. |
| b.L:byte | scratchByteL | Structural | Scratch byte reused in building/AI code. |
| b.aN:int | queueScanAN | Likely | Cursor scanning the ritual/sanctuary queue in `updateRitualQueue`. |
| b.aO:int | queuePosAO | Likely | Position inside the ritual/sanctuary queue. |
| b.aP:int | queueBuildingAP | Likely | Building slot of the queue being processed. |
| b.j:byte[] | aiRank | Likely | Per-villager AI rank (0–5) used by `aiEnlistWave` to pick wave units. |
| b.M:byte | towerWorkerM | Likely | Worker count of the tower being processed in `updateTowerStats`. |
| b.N:byte | groupSlotN | Likely | Slot (0–4) of the group array being filled in `setGroupMembers`. |
| b.O:byte | groupCountO | Likely | Number of members in the current group, set by `setGroupMembers`. |
| b.P:byte | missionRectP | Likely | Rectangle index of the current mission event area. |
| b.Q:byte | waveTargetQByte | Likely | Byte form of the wave target id used in `aiWaveControl`. |
| b.R:byte | scratchByteR | Structural | Scratch byte reused in wave/AI code. |
| b.aQ:int | nextWaveTickAQ | Likely | Tick at which the next enemy wave is due (`nextWaveTickAQ = waveStartTickAR + interval`). |
| b.aR:int | waveStartTickAR | Likely | Tick at which the current wave started, read by `aiWaveControl`. |
| b.aS:int | scratchIntAS | Structural | Temporary integer reused in wave/AI code. |
| b.aT:int | scratchIntAT | Structural | Temporary integer reused in wave/AI code. |
| b.aU:int | waveEnlistedAU | Likely | Number of villagers enlisted into the current wave by `aiEnlistWave`. |
| b.aV:int | spawnRowAV | Likely | Map row used when spawning wave units at the map edge. |
| b.aW:int | fogWordAW | Likely | Packed fog-of-war word read by `setFogBits`/`fillFogRect`. |
| b.aX:int | fogBitsAX | Likely | Fog bits for one row, shifted by `setFogBits`. |
| b.aY:int | fogRowBitsAY | Likely | Fog bits of the row currently being revealed by `revealFogCircle`. |
| b.a:int[] | bitmasks | Certain | Filled by the constructor with `2^n - 1` masks; used as `bitmasks[n]` wherever bit fields are masked. |
| b.S:byte | scratchByteS | Structural | Scratch byte reused in fog/director code. |
| b.k:byte[] | tileQueryResult | Likely | `tileQueryResult[0]` = kind, `[1]` = subtype, `[2]` = entity id, `[3]` = flag, filled by `queryTile`. |
| b.a(f):void | attachGame | Likely | Stores the Game, copies record tables (`recordBlob`, `recordStarts`, `fieldOffsets`) from `f` and initializes grids; called once at startup. |
| b.a():void | resetWorld | Certain | Zeroes `entityData`, `occupancyGrid`, tribe stats and all tick state; called when a mission starts or restarts. |
| b.a(int,int,int,int,int,int):byte | findPath | Likely | Top-level pathfinder: builds candidates, picks the best, returns its length; called whenever a villager needs a route. |
| b.a(int,int,int,int):byte | findPathTo | Likely | Scores the 3 candidate paths and returns the chosen path's length; called by `findPath`. |
| b.d(int):void | scoreDetour | Likely | Scores one detour candidate around a blocked tile and writes the penalty into `tileBlockedCost`. |
| b.b(int,int,int,int):byte | pathLengthViaWaypoints | Likely | Walks a waypoint list, accumulates segment lengths into `pathLengthAccum` and returns the total. |
| b.o():void | repathToTarget | Likely | Re-runs pathfinding for the current villager when its target changed or its path was invalidated. |
| b.p():void | updateMovement | Likely | Per-tick movement driver for the current villager: advances along `villagerPaths` or triggers repathing. |
| b.a(int,int,int,int,int):void | planRoutes | Likely | Plans the route segments between waypoints into `pathSegments` for the current path. |
| b.a(int,int):void | computeDirection | Likely | Converts dx/dy into a 0–7 facing stored in `facingDirection`. |
| b.e(int):void | buildCandidatePaths | Likely | Builds the candidate path set for the given start, filling `candidatePaths`/`pathCosts`/`pathLengths`. |
| b.f(int):void | followPathStep | Likely | Advances `pathCursors` one step along the chosen path and updates the villager's pixel offsets. |
| b.a(boolean):void | mergePaths | Likely | Splices two path pieces up to `maxPathLength`, restoring `savedTerrain` for discarded points. |
| b.a(int,int,int):byte | tileWalkable | Likely | Returns 0/1/2 for a tile from `occupancyGrid` and the terrain grid (blocked / free / special). |
| b.g(int):void | moveVillagerStep | Likely | Moves the villager one step in the given direction, updating x/y and the occupancy grid. |
| b.h(int):void | handleBlockedMove | Likely | Called when a step is blocked: scores a detour or triggers `repathToTarget`. |
| b.a(int,int,boolean):void | inspectTileTarget | Likely | Reads a tile's occupant and, when the flag is set, starts the interaction for the current villager. |
| b.a(int,byte):void | setTask | Certain | Sets entity field 2828 (task) and resets progress (404), masks skill (1111), loads duration `taskDurationB` from rec 38 and pose from rec 32. |
| b.b(int,int):void | findFreeTileNear | Likely | Searches around (x,y) for a free tile and writes it to `targetXQ`/`targetYR`. |
| b.q():void | updateTargetTracking | Likely | Updates the current villager's target (3131/3232) when the target moved or died. |
| b.r():void | retargetOrIdle | Likely | Picks a new target for the villager or sends it idle (task 69) when none is available. |
| b.b(boolean):void | findWorkBuilding | Likely | Finds a building the villager can work at; when the flag is set it also assigns the work slot. |
| b.a(int,int,int):int | distanceTo | Likely | Returns the distance between two tiles (used with squared comparisons in target code). |
| b.b(int,int,int):int | clamp | Certain | Static helper returning `min(max(v, lo), hi)`; called from path, fog and stat code with explicit bounds. |
| b.b():void | flagDistressed | Likely | Sets distress flags on villagers whose needs (hunger/energy) crossed a threshold. |
| b.c():void | clearDistressFlags | Likely | Clears the distress flags set by `flagDistressed` at the start of the next tick. |
| b.d():void | rebuildFogOfWar | Certain | Rebuilds the fog grid from every villager's reveal radius; called after moves and spawns. |
| b.e():void | tickWorld | Certain | The world tick: preamble, per-tribe villager loop, hut births, projectile grid, calm timer, resource clamp. |
| b.s():void | tickPreamble | Likely | Runs once at the start of `tickWorld`: resets per-tick flags and the calm timer. |
| b.a():boolean | preTickVillager | Likely | Per-villager pre-check at the start of `tickWorld` (alive, valid slot); false skips the villager this tick. |
| b.t():void | updateMoveState | Likely | Updates the villager's movement state (6161: still/walking/to-target/returning). |
| b.b():boolean | tryActOnNeed | Likely | Tries to start an action for the villager's strongest need; false means no need is actionable. |
| b.u():void | updateAction | Likely | Advances the current action after movement (calls task-progress and post-move handling). |
| b.c():boolean | stepAlongPath | Likely | Steps the villager along its path; returns false when the path ended or is blocked. |
| b.v():void | updateTaskProgress | Likely | Counts down `taskDurationB`/progress (404) and fires completion when the task finishes. |
| b.w():void | tickActionTimer | Likely | Increments the action timer (3030), skill progress (1111) and triggers gather/role effects on expiry. |
| b.d():boolean | handleSpecialRoles | Likely | Handles chief/council/ritual special roles for the current villager; false means no special role applies. |
| b.x():void | leaveBuildingWork | Likely | Unlinks a worker from a building's worker list and resets its position and flags. |
| b.e():boolean | tryEnterBuilding | Likely | Tries to move the villager inside a building; false when the building is full or closed. |
| b.y():void | checkActionCompletion | Likely | Checks skill/completion gates for the current action and calls `completeAction` when done. |
| b.i(int):void | completeAction | Likely | Finishes the action for the given villager id: applies yield, clears flags, picks the next task. |
| b.f():boolean | checkBuildingWork | Likely | Checks whether the current villager's building work is done; false means it continues. |
| b.b(int):boolean | pickNextRole | Likely | Picks the next role (profession) for the given villager; false when no role fits. |
| b.g():boolean | findNearbyMate | Likely | Searches nearby villagers for a mating partner; false when none is in range. |
| b.z():void | startCourtship | Likely | Sets courtship task (−101/−100) on an adjacent villager to start mating. |
| b.c(int):boolean | canWorkBuilding | Likely | Returns whether the given building slot accepts another worker (capacity from rec 138). |
| b.a(int):byte | buildingWorkerInfo | Likely | Returns packed worker info (count/level) for the given building slot. |
| b.d(int):boolean | canEnterBuilding | Likely | Returns whether the villager may enter the given building (type, state and occupancy checks). |
| b.a(byte,boolean):boolean | checkCombatStance | Likely | Returns whether the given villager is in a combat stance (task 77–85) with the flag selecting the side. |
| b.h():boolean | checkSickOrRest | Likely | Returns true when the villager is sick or must rest (disease meter/energy checks). |
| b.A():void | rankNeeds | Likely | Ranks the current villager's needs into `needyListE` by urgency. |
| b.B():void | pickDesire | Likely | Picks the villager's strongest desire (3333) from the ranked needs. |
| b.C():void | updateTaskState | Likely | Advances the task state machine (3535/3636) for the current villager. |
| b.a(byte):void | swapVillager | Likely | Swaps two villagers' slots in `entityData` using `swapTempT`/`savedVillagerU`. |
| b.D():void | walkStep | Likely | Performs one walk step: advances pixel offsets toward the next path point. |
| b.E():void | computeWalkOffsets | Likely | Computes the pixel offsets (202/303) for the current walk step from direction and speed. |
| b.F():void | enterBuilding | Likely | Moves the villager inside a building: clears the occupancy cell and sets the inside flag. |
| b.G():void | assignWorkSlot | Likely | Assigns the villager a work slot (4646) in its building. |
| b.b(byte):void | updateTowerStats | Likely | Updates a tower's packed range/power (8688) and worker count from its type record. |
| b.a(int,int,int,int):boolean | spiralSearchTile | Likely | Spirals outward from (x,y) looking for a tile matching the kind code; returns whether one was found. |
| b.H():void | handleArrival | Likely | Runs when a villager arrives at its target: reveals fog, clears the walking flag, starts the task. |
| b.a(int,int,byte,int):void | setAnimalTarget | Likely | Sets an animal's target (9645/9667 target x/y, 9601 target id) when in range, else retargets it. |
| b.c(byte):boolean | canGatherAt | Likely | Returns whether the villager can gather at the given resource-node kind. |
| b.b(int,int,int,int):void | fireScriptedEvent | Likely | Fires the scripted mission event indexed by `scriptEventIndex` at the given rectangle. |
| b.b(int,int,int):void | setGroupMembers | Likely | Fills the Game group array (`f.p`) with the chief, the given villager and up to 3 others. |
| b.c(boolean):void | pickNeedyCouncil | Likely | Picks the neediest villager for the council; the flag selects player or enemy tribe. |
| b.c(int,int):void | saveCouncilMember | Likely | Saves a council member's villager id into the council slots for the given tribe. |
| b.f():void | restoreCouncil | Likely | Restores the council members saved by `saveCouncilMember` after a mission event. |
| b.i():boolean | tryCompleteTask | Likely | Tries to complete the current task; true when its completion conditions are met. |
| b.j():boolean | applyTaskEffect | Likely | Applies the current task's effect (resource/stat change); false when the effect cannot apply. |
| b.k():boolean | afterTaskCleanup | Likely | Cleans up after a task (clears flags, unlinks from building lists); false when nothing was pending. |
| b.I():void | afterTask | Likely | Post-task hook: picks the villager's next task based on needs and role. |
| b.J():void | applyTaskYield | Likely | Adds the task's yield (resources, skill) to the tribe stats and the villager. |
| b.K():void | applySpecialTaskEffect | Likely | Applies the effect of special tasks 94–97 (fetch, clear flag, mood, disease-spot shift). |
| b.a(int,int):boolean | adjustHealth | Likely | Adds/subtracts HP (clamped 0..max), clears the disease meter and flag bit 2 when healing; returns at-full-health. |
| b.c(byte):void | captureVillager | Likely | Captures the given villager (converts it to the capturing tribe). |
| b.j(int):void | shareMood | Likely | Spreads the given villager's mood (505) to nearby villagers. |
| b.l():boolean | roleFitsTask | Likely | Returns whether the villager's role (2929) fits the current task. |
| b.b(int,int):boolean | checkStatRequirements | Likely | Loops over rec 69 requirement ranges calling `applyStatChange` per requirement; returns all-passed. |
| b.a(byte,byte,byte,byte):boolean | applyStatChange | Likely | Applies one stat change (kind, stat, amount, tribe) to `tribeStats` and returns whether it was in range. |
| b.a(int,boolean,int):boolean | tryCastSpell | Likely | Tries to cast the spell (task 28–49) for the given villager; the flag selects player/enemy targeting. |
| b.b(int,boolean,int):boolean | checkSpellCost | Likely | Returns whether the tribe can afford the spell's cost (resources from rec 63–69). |
| b.L():void | assignProfession | Likely | Assigns a profession (6565) to the current villager from its skills and needs. |
| b.M():void | startCombatStance | Likely | Puts the villager into a combat stance (task 77–85) toward its target. |
| b.N():void | retargetAfterIdle | Likely | Picks a new target for an idle villager from nearby enemies or needs. |
| b.O():void | pickNextDestination | Likely | Picks the villager's next destination tile from its task and role. |
| b.a(int):boolean | findNearestBuilding | Likely | Finds the nearest building of the given type; returns whether one exists. |
| b.m():boolean | checkCallToArms | Likely | Returns whether a call-to-arms is active (enemy near the village) for the current tribe. |
| b.n():boolean | tryFindNeedTarget | Likely | Tries to find a target for the villager's strongest need; false when none exists. |
| b.a(int,byte):boolean | goToBuildingType | Likely | Sends the villager to the nearest building of the given type; false when none exists. |
| b.a(boolean):boolean | tryFindMate | Likely | Tries to find a mate for the villager; the flag selects same-tribe or any-tribe search. |
| b.o():boolean | updateMating | Likely | Advances the mating state machine (tasks 109–111, `matingPairs`); false when mating is over. |
| b.d(int,int):void | clearMatingPair | Likely | Clears the mating pair for the two given villagers from `matingPairs`. |
| b.a(int):void | releaseMatingHut | Likely | Frees the hut used for mating by the given villager. |
| b.k(int):void | endMatingFor | Likely | Ends mating for the given villager: clears the pair, the hut and the mating task. |
| b.b(int):byte | moveStance | Likely | Returns the movement stance (still/walking/to-target/returning) for the given villager. |
| b.P():void | setIdleRole | Likely | Sets the villager's idle role (unemployed, 6565 = 9) when it has no task. |
| b.Q():void | goIdle | Likely | Sends the villager to task 69 (idle) and clears its target. |
| b.p():boolean | findNewTargetTile | Likely | Finds a new target tile for the villager; false when no tile is available. |
| b.a(byte,byte,byte):void | scanForTargetKind | Likely | Sets scan bounds then scans the sector for the kind stored in field `D`; no callers found in `b.java` (called from `f` or dead code). |
| b.b(byte,byte,byte):void | setScanBounds | Likely | Sets the scan rectangle (`scanMinCellXAt`…`scanMaxCellYAw`) from the given origin and radius. |
| b.a(byte,byte,byte,byte):void | scanSector | Likely | Walks the spatial hash over the scan rectangle and keeps the best entity of the given kind. |
| b.a(byte,byte,int,int):void | launchProjectile | Likely | Launches a projectile from (x,y) toward the target, filling the thrower's flight fields (5858/5959, 5656/5757). |
| b.R():void | stepProjectile | Likely | Advances all in-flight projectiles one tick (flight tick 5858, pixel accumulators 5454/5555). |
| b.S():void | landProjectile | Likely | Lands a projectile: applies damage at the landing tile and clears the flight fields. |
| b.a(int,int,int,byte):void | resolveTileEvent | Likely | Resolves what happens on a tile (trap trigger, resource hit, building hit) for the given kind. |
| b.l(int):void | clearAnimal | Likely | Clears the animal at the given index from `entityData` (9359 block) and the occupancy grid. |
| b.m(int):void | killVillager | Likely | Kills the given villager: clears its entity fields, frees its hut and building slot. |
| b.c(int,int,int):void | gatherStrike | Likely | One gather hit on a resource node: reduces the node amount and adds to the tribe stock. |
| b.a(int,int,int,int,int,int,int,int):boolean | applyDamageAt | Likely | Applies damage at a tile to the occupant (villager/building) and returns whether something was hit. |
| b.T():void | updateProjectileGrid | Likely | Rebuilds the projectile/occupancy overlay after projectiles move or land. |
| b.U():void | leaveBuilding | Likely | Moves the villager out of a building onto a free adjacent tile. |
| b.V():void | assignHomeHut | Likely | Assigns the villager a home hut (3434) from the free hut slots. |
| b.b(int):void | releaseHome | Likely | Frees the home hut of the given villager. |
| b.q():boolean | ageVillager | Likely | Ages the villager (1414) by one step; returns true when it becomes an adult (age ≥ 12). |
| b.r():boolean | drainNeeds | Likely | Drains hunger (2020) and energy (2121) for the tick; returns true when a need hit zero. |
| b.s():boolean | checkAlive | Likely | Returns whether the current villager is alive (HP 1919 > 0 and not flagged dead). |
| b.W():void | updateMood | Likely | Updates the villager's mood (505) from needs, nearby events and `shareMood`. |
| b.X():void | updateDiseaseMeter | Likely | Increments the disease meter (8080) and sets the sick flag (7979 bit 4) at ≥ 30. |
| b.Y():void | spreadDisease | Likely | Spreads disease from a sick villager to neighbors and to `diseaseSpots`. |
| b.Z():void | tickHutBirths | Likely | Runs hut births: mating pairs in a hut (9237 state) produce a newborn (task 17). |
| b.g():void | tickBuildings | Certain | The building tick: upgrades, repairs, traps, worker updates and tower stats for both tribes. |
| b.aa():void | updateBuildingWorkers | Likely | Rebuilds each building's worker list (8993 head, 4545 links) and counts. |
| b.ab():void | assignBuildingTask | Likely | Assigns an idle villager to a building that needs workers. |
| b.ac():void | recountBuildingWorkers | Likely | Recounts workers per building (8932/9298) after assignments change. |
| b.ad():void | completeUpgrade | Likely | Finishes a building upgrade from `upgradeRegistryG`: raises level (9115) and clears flags. |
| b.a(byte):boolean | registerTrap | Likely | Registers a trap in `trapRegistryF`; false when all 10 slots are full. |
| b.b(byte):boolean | registerUpgrade | Likely | Registers an upgrade job in `upgradeRegistryG`; false when all 20 slots are full. |
| b.ae():void | assignIdleToBuilding | Likely | Sends idle villagers to buildings that still need workers. |
| b.c(int):void | unregisterTrap | Likely | Removes the trap at the given registry slot. |
| b.d(boolean):void | updateTrapAnimation | Likely | Advances trap animation/state; the flag selects player or enemy traps. |
| b.h():void | tickAnimals | Likely | The animal tick: updates each animal's state, movement and targeting. |
| b.af():void | updateAnimal | Likely | Updates one animal's state (movement, target, animation) for the current tick. |
| b.ag():void | moveAnimal | Likely | Moves the animal one step along its path (`animalPaths`), max path length 5. |
| b.ah():void | animateAnimal | Likely | Advances the animal's animation frame from its state and movement. |
| b.ai():void | pickAnimalState | Likely | Picks the animal's next state (graze, flee, attack) from its surroundings. |
| b.e(boolean):void | retargetAnimal | Likely | Picks a new target for the animal; the flag selects a random or a nearest target. |
| b.i():void | tickDirector | Certain | The director tick: disease/chief checks, mission events, `checkWinLose`, tutorial hints, progress levels. |
| b.aj():void | updateResourceNode | Likely | Updates resource nodes (9733 block): regrowth stage (9933) and amount (9883). |
| b.t():boolean | spreadDiseaseAndChiefCheck | Likely | Spreads disease from `diseaseSpots` and checks the chief's status; returns whether an event fired. |
| b.u():boolean | mission4ChiefEvent | Likely | Fires the mission-4 chief event; returns whether the event was triggered this tick. |
| b.v():boolean | fireMissionEvents | Likely | Fires pending mission events for the current mission; returns whether any fired. |
| b.ak():void | checkWinLose | Certain | Seed rename. Win: enemy buildings `tribeStats[1][14] <= 0` and `(rec279[q] & 1)`; lose: player population `<= 0` or chief hut `a[8506] <= 0` with `(rec279[q] & 2)`; verdict at `f.u == 41` (win) / `90` (lose). |
| b.al():void | updateTutorialHints | Likely | Advances tutorial hints (`hintStepIndex`, `hintTimerJ`) and shows the next hint message. |
| b.a(int,boolean):void | stopAtTile | Likely | Stops the villager at the given tile; the flag selects whether to face the target. |
| b.j():void | updateProgressLevels | Likely | Updates the mission progress levels shown in the UI from tribe stats. |
| b.k():void | updateMusicMood | Certain | Sets `f.h` (music phase 0/1/2) from combat/calm state; it does NOT decide win/lose. |
| b.am():void | updateGatherersAtNode | Likely | Updates villagers gathering at a resource node (strikes, amounts, departures). |
| b.an():void | findGatherTarget | Likely | Finds the nearest resource node for a gatherer to walk to. |
| b.b(boolean):boolean | findNearestResourceNode | Likely | Finds the nearest resource node of the needed kind; the flag selects player/enemy side. |
| b.a(int):int | updateRitualQueue | Likely | Advances the ritual queue for the given building and returns the next step. |
| b.f(boolean):void | completeRitualStep | Likely | Completes one ritual step at the sanctuary; the flag selects the ritual kind. |
| b.ao():void | clearSanctuaryQueue | Likely | Clears the sanctuary's ritual queue. |
| b.ap():void | leaveSanctuary | Likely | Removes the villager from the sanctuary queue and resets its task. |
| b.e(int,int):void | clearSanctuaryTarget | Likely | Clears the sanctuary target for the given villager/building pair. |
| b.aq():void | tickEnemyAI | Likely | The enemy-AI tick: build/repair, wave control, role assignment, return-home, enlist, advance. |
| b.n(int):void | spawnAt | Likely | Spawns a villager/animal at the given map position (edge spawn for waves). |
| b.ar():void | aiBuildRepair | Likely | AI: builds or repairs buildings (`pendingBuildP`) when resources allow. |
| b.as():void | aiWaveControl | Likely | AI: controls wave timing (`nextWaveTickAQ`/`waveStartTickAR`, interval from rec 214 − rec 215 × difficulty). |
| b.at():void | aiAssignRoles | Likely | AI: assigns roles to enemy villagers by rank (`aiRank`, threshold `aiRankThresholdM` from rec 220). |
| b.au():void | aiReturnHome | Likely | AI: sends enemy villagers back to their huts after a wave. |
| b.o(int):void | assignJobTask | Likely | Assigns the job task (44 + role) to the given villager. |
| b.av():void | aiEnlistWave | Likely | AI: enlists villagers into the next wave (`waveEnlistedAU`) by rank. |
| b.aw():void | aiAdvanceWave | Likely | AI: advances the wave toward the player village (`waveStepO`, `waveTargetQ`). |
| b.a(int,int,int,byte,byte):void | spawnWaveUnits | Likely | Spawns wave units of the given kinds at the given map edge position. |
| b.l():void | clearWorld | Likely | Clears all world state (entities, grids, registries) without resetting the Game link. |
| b.m():void | clearEnemyTribe | Likely | Clears the enemy tribe's villagers and buildings (mission restart). |
| b.a(int,int,int):void | revealFogCircle | Likely | Reveals a fog circle of the given radius around (x,y) via `setFogBits`. |
| b.a(int,int,int,int):void | fillFogRect | Likely | Fills a fog rectangle (rows × mask) starting at (x,y). |
| b.d(int,int,int):void | setFogBits | Likely | Sets fog bits for one row at (x,y) with the given bit mask. |
| b.c(int,int,int,int):void | queryTile | Likely | Fills `tileQueryResult` (kind, subtype, entity id, flag) for the given tile. |
| b.e(int,int,int):void | decodeTileTarget | Likely | Decodes a tile's target data (kind/subtype) into the query fields. |
| b.a():byte | queryTileKind | Likely | Returns the kind code of the tile under the current query (`tileQueryCode`). |
| b.a(byte,int,int):void | showMessage | Likely | Shows an in-game message of the given type for the given villagers, honoring `messageCooldowns`. |
| b.ax():void | tickMessageCooldowns | Likely | Decrements `messageCooldowns` each tick so messages can repeat. |
| b.n():void | rebuildSpatialHash | Certain | Rebuilds the spatial hash (`hashNext`) over all villagers/animals; called after spawns, deaths and moves. |


## f (Game)

How class f works

1. `f` is the whole game: a MIDP `Canvas` + `Runnable` (~20 ticks/s) holding all state, menus, rendering, input, save/load.
2. `run()` starts the thread: `f()` (init: create World/TextLoader/MidiPlayer, load pd0 tables via `e`, read manifest
   properties) then `g()` (allocate all arrays), then tries `loadSlot(0)` and falls back to `loadSlot(1)`/`(2)`.
3. The tick loop: `aS()` (pause/music check) → `h()` (per-tick update: input flags, timers, message advance every
   6 ticks, then `j()`) → `m()` (render to buffer `N`) → `l()` (repaint) → sleep `tickLengthMs` (50 fast / 120 normal).
4. `j()` is the world tick: advances `tickCounter`, counts the endgame timer (`endgameTimer` armed 1 → win verdict at
   tick 41, armed 50 → lose verdict at 90), and calls `world.ak()` (checkWinLose).
5. Screens are a single byte `screenId` (`Q`): 14 main menu, 4 campaign, 5 home, 16 new-game, 20 popup, 22/23 load/result,
   24 mission end, 25 quit, 27 system pause, 32 ad; `R`/`S` save the previous screens for Back.
6. `mode` (`v`, byte) is 0 = menu, 1 = in-game; `dialogId` (`v`, int) selects the popup text (56 lose … 71 score).
7. Input: `keyPressed`/`keyReleased` fill direction flags (`keyUp`/`keyDown`/`keyLeft`/`keyRight`), `firePressed`/`fireHeld`/
   `fireTapped`, numeric keys, and softkeys (`leftSoftkeyPressed`/`rightSoftkeyPressed`); `V()` dispatches them per mode.
8. In-game, the cursor (`cursorCol`/`cursorRow`) moves over the 96x96 `terrainGrid`/`occupantGrid`; the camera
   (`camTileX`/`camTileY`, 11x18 tiles on screen) follows it, clamped by `clampCamera`.
9. Selecting a villager (`selectedVillagerId`) opens a context menu (`menuItemIds`, `menuSelectedIndex`); ordering it
   (`a(BB)V`) sets `orderCursorType` (1 walk / 2 interact) and `orderTileX`/`orderTileY`; confirming a placement (`X()`)
   enqueues into `pendingOrders`, and spawning (`a(BIIIIZI)B`) writes the entity into `entityData` and the grids.
10. Entities live in one flat `entityData` byte array (9,983 bytes, ~100 slots): type at 8749, x at 8383, y at 8444,
    level at 9115, state at 8627, job at 8810/9237; ids 1–50 = tribe 0, 51–100 = tribe 1, negative ids = buildings.
11. Rendering order per frame: `m()` → `n()`/`o()` (terrain rows, consulting `tileDirty`) → `u()` (entities row by row,
    parts via `p()`, overlays via `q()`) → `v()` (trap/building markers) → HUD panels (`c()` dispatch on `hudPanelId`,
    elements via `a(BII)V`/`d(int,int)`) → `C()`/`D()` (minimap) → `w()` (viewport rect) → copy to the screen buffer.
12. Fog of war: `tileVisibility` returns 0 visible / 1 / 2 unseen per tile from `effectBitfield`; the minimap
    (`minimapOriginX`/`minimapOriginY`, 40x60 view) is drawn from the palette (`paletteColors`).
13. Story scenes run on `storySceneId`/`storyStepIndex`/`storyStepCount` (`S()` setup, `b(int)` advance, `U()` end);
    steps reference villagers through `storyActors` (`p[stepSlot]`) and the step action/type tables.
14. Saves: RecordStore `t0` (settings + progress, 7 values), `t1` (campaign save, mission < 7), `t2` (custom save,
    mission ≥ 7); `loadSlot`/`saveSlot` read/write the same field order (progress bytes, groups, traps, `chiefId`, …).
15. Music: `selectMusicTrack` picks `nextMusicTrack = musicTrackTable[musicMood*2 + rand(0..2)]` and restarts the MIDI
    player (data in `midiData`, 26,616 bytes from `/sa`; lengths/offsets in `songLengths`/`songOffsets`); the cheat
    code 8,7,3,9,1 (`cheatCode`) unlocks custom play (`cheatUnlockedCustom`).
16. The title screen animates 4 drifting `titleParticles`; the game over screen, pause screen, and story screens are
    all just `screenId` values rendered by `G()`–`R()` into the 4 screen buffers (`screenBuffers`).

| Old | New | Confidence | Evidence |
| --- | --- | --- | --- |
| f.a:boolean | cheatsEnabled | Certain | e.java:302 sets it from manifest `Cheat-Support` (`equals("on")`); manifest says `off`. |
| f.a:tribes | midlet | Certain | Constructor takes the `tribes` MIDlet; `b()` calls `this.a.notifyDestroyed()`. |
| f.a:b | world | Certain | `f()` creates it; `j()`/`ak()` call `this.a.ak()` (checkWinLose) and `this.a.a(...)` every tick. |
| f.a:c | textLoader | Certain | `f()` creates `new c(this)`; `c.a(int)` selects story text; `S()`/`T()` read `this.a.d0` text. |
| f.a:a | midiPlayer | Certain | `aQ()` creates `new a("audio/midi")` and calls `this.a.a(this.e)` (setMusicLevel). |
| f.a:Random | random | Certain | Constructor `new Random(1L)`; `a(int,int):int` (randRange) and `G()` use it. |
| f.a:String | version | Certain | e.java:300 sets it from manifest `MIDlet-Version` ("0.1.28"). |
| f.a:byte [static] | languageIndex | Certain | `e.b()` indexes language tables by it (0–4 = en/fr/it/de/es); `c.a(int)` loads that language. |
| f.a:short | screenBaseX | Likely | Never assigned in f (always 0); `paint()` offsets the frame by it. |
| f.a:byte[] | fileAData | Certain | e.java:222 allocates `[17638]`; `a()` reads balance records from it via `recordStarts`. |
| f.a:short[] | recordStarts | Certain | e.java:223 allocates `[312]`; `a()` indexes the 312 `/a` records through it. |
| f.a:short[][] | tribeStats | Certain | g() allocates `[2][26]`; per-tribe resources/weapons/population; `aM()` zeroes it, `d()`/`e()` persist it. |
| f.a:byte[][] | villagerGroups | Certain | g() allocates `[5][50]`; `aF()`/`aG()`/`aH()` save/recall groups; `d()`/`e()` persist it. |
| f.a:byte[][][] | buildingStores | Certain | g() allocates `[2][10][12]`; `aa()` finds the row by `a[0][au][11]==q`; `ae()` adjusts `a[0][au][item]`. |
| f.a:int[][][] | effectBitfield | Certain | g() allocates `[2][3][96]`; `a(int,int):byte` reads bits from it; `aM()` zeroes it. |
| f.a:boolean[][] | tileDirty | Likely | g() allocates `[P+3][O]` (screen-sized); `o()` reads it to decide which terrain rows to redraw. |
| f.a:Image[][] | spritePacks | Certain | e.java:84 allocates `[3][]`; `f()` loads pi0/pi9/pi8 into it; `b(Graphics,Image,...)` draws from it. |
| f.a:int[][] | titleParticles | Likely | e.java:297 allocates `[3][4]`; `G()` animates 4 particles (x drifts right, respawn at x>240); `run()` sets `a[0][3]=-1000`. |
| f.a:int[] | screenWidths | Certain | e.java:134 sets `{264,264,240,240,240,240,68,240,68,63,240,240,240}`; `G()`/`paint()` size screens from it. |
| f.a:int [static] | softkeyLeftCode | Certain | `<clinit>` sets `-6`; `keyPressed()` treats `var1==a` as the left softkey. |
| f.a:Graphics[] [static] | screenBufferGraphics | Certain | e.java:135-137 creates the 4 screen buffers and stores their Graphics here. |
| f.a:Image[] [static] | screenBuffers | Certain | e.java:135-137 creates images 264x304 (buffers 1,3), 264x352 (0), 79x63 (2). |
| f.a:boolean[] | hudPanelVisible | Likely | `<init>` allocates `[20]`; `a(boolean×9)` sets flags 0–8; menu/render methods read them. |
| f.b:String | recordStorePrefix | Certain | Constructor sets `"t"`; `d()`/`e()` open RecordStores `t0`/`t1`/`t2` from it. |
| f.b:boolean | running | Certain | `run()` loops `while (this.b)`; `b()` (quitGame) sets it false. |
| f.b:boolean[] | hudPanelDirty | Likely | `<init>` allocates `[20]`; `B()`/`c()` check `b[panelId]` before refreshing a HUD panel. |
| f.b:byte | selectedDifficulty | Certain | Menu sets it (`Z()`/`at()`); `aN()`/`aP()` use it when spawning the level. |
| f.b:byte[] | musicTrackTable | Likely | `aQ()` picks the next track as `b[h*2 + rand(0..2)]`. |
| f.b:int [static] | softkeyRightCode | Certain | `<clinit>` sets `-7`; `keyPressed()` treats `var1==b` as the right softkey. |
| f.b:int[] | screenHeights | Certain | e.java:135 sets `{352,304,22,48,22,48,55,86,49,55,38,0,0}`; `G()`/`paint()` size screens from it. |
| f.b:short | screenBaseY | Likely | Never assigned in f (always 0); `paint()` offsets the frame by it. |
| f.b:short[] | offsetTable | Certain | e.java:224 allocates `[570]`; `a()` indexes the 5 record groups through it. |
| f.b:byte[][] | terrainGrid | Certain | `aN()` RLE-decodes the map into this 96x96 grid; `o()`/`u()`/`e(int,int)` render from it. |
| f.b:byte[][][] | pendingOrders | Likely | g() allocates `[2][5][30]`; `X()` enqueues `[0]=targetId,[1]=type,[2]=x,[3]=y`; `v()` draws type-3 (trap) orders. |
| f.b:int[][] | paletteColors | Certain | g() sets 2 rows of 12 RGB literals; `C()` (minimap) reads colors from it. |
| f.b:short[][] | pd0ShortTables | Certain | e.java:70 allocates `[11][]`; `aN()`/`aP()`/`a(IB)V` read sprite/map data from it. |
| f.c:boolean | fastTick | Certain | `h()` uses it to pick tick length 50 ms vs 120 ms; set true only in story wipe `P()` and map-open `W()`. |
| f.c:boolean[] | hudPanelVisible9 | Likely | `<init>` allocates `[9]`; `l(int)` rebuilds it from `a[]` indices 0,1,2,4,5,6,7,8,9. |
| f.c:byte | campaignDifficulty | Likely | Menu sets it; `aN()` uses it for campaign missions (vs `d` = custom difficulty). |
| f.c:byte[] | screenBufferIndex | Likely | e.java:138 sets `{0,1,0,0,0,0,2,0,2,2,0,0,0}`; render methods index it by screen id. |
| f.c:int | tickCounter | Certain | `j()` increments it every tick; set to -1 at mission end/title; `b.java` reads it for win/lose timing. |
| f.c:int[] | screenContentHeights | Likely | e.java:136 sets `{0,0,304,304,326,326,0,326,0,0,326,0,0}`; `m()`/`paint()` clip content by it. |
| f.c:short | mapWidth | Certain | `aN()` sets it from the level table, then adjusts per level (q=0 → 40); `c()` copies `e`→`c` on next level. |
| f.c:short[] | unusedShortArrayC | Unknown | Declared but no field access in `f.class` bytecode and no writer found in `e.java`/`b.java`. |
| f.c:byte[][] | occupantGrid | Certain | `aN()` fills this 96x96 grid; `k()`/`j()`/`o()`/`u()` test occupancy through it. |
| f.c:byte[][][] | aiTargets | Likely | `b.java:2137` walks an AI villager to `c[1][B][0/1]` and sets `[3]=1` on arrival; `b.java:3262` shifts the queue. |
| f.d:boolean | gameLinkEnabled | Certain | e.java:301 sets it from manifest `GameLinkEnabled` (`equals("true")`); manifest says `false`. |
| f.d:boolean[] | popupButtonFlags | Likely | `<init>` allocates `[9]`; `S()` (story setup) writes it; `U()`/`a(BBB)`/`an()`/`ap()` read it for popup/menu buttons. |
| f.d:byte | customDifficulty | Likely | Menu sets it; `aN()` uses it for custom missions (vs `c` = campaign difficulty). |
| f.d:byte[] | hudPanelForScreen | Likely | e.java:139 sets `{0,0,6,0,0,0,0,0,0,0,0,0,0}`; `a(boolean×9)` is its only reader. |
| f.d:int | msgAdvanceCounter | Certain | `h()` posts message type 100 every 6 ticks while it counts. |
| f.d:int[] | screenPanelY | Likely | e.java:137 sets `{0,16,0,0,298,272,0,234,0,0,282,320,0}`; `M()`/`O()`/`j(int)` position HUD panels by it. |
| f.d:short | mapHeight | Certain | `aN()` sets it from the level table (q=0 → 32); `c()` copies `f`→`d` on next level. |
| f.d:short[] | hudValues | Likely | g() allocates `[107]`; `a(BII)V` loads the HUD element value as `d[aE]`; `l(int)` sets `d[4]=33+Y`. |
| f.d:byte[][] | effects | Certain | g() allocates `[7][40]`; `r()` updates the 40 effect slots; `t()` clears them; `a(IIII)V` spawns into them. |
| f.e:boolean | keyUp | Certain | `keyPressed()`/`keyReleased()` set it from the up key; `V()` (input dispatch) reads it for camera/selection. |
| f.e:byte | musicLevel | Certain | `aQ()` passes it to `midiPlayer.setMusicLevel` (level = value*20, 0 = stop); options toggle it. |
| f.e:byte[] | entityData | Certain | 9,983 bytes; every entity field is `e[base+id]` (type 8749, x 8383, y 8444, hp 9115, …). |
| f.e:int | animFrame | Certain | `h()` cycles it 0–7; `q()`/`u()`/`w()` pick animation frames from it. |
| f.e:int[] | hpBarColors | Likely | g() sets `{14680064,14999808,55808,55808}`; `q()` (entity overlays) and `u()` (render) read them. |
| f.e:short | tableMapWidth | Certain | `aN()` sets it from the level table; `c()` copies it to `c` (effective width) on next level. |
| f.e:short[] | unusedShortArrayE | Unknown | Declared (instance) but no field access in `f.class` bytecode and no writer found in `e.java`. |
| f.e:byte[][] | buildAnims | Certain | g() allocates `[3][30]`; `s()` updates the 30 build-animation slots; `a(int,int)` enqueues into them. |
| f.f:boolean | keyDown | Certain | `keyPressed()`/`keyReleased()` set it from the down key; `V()` reads it. |
| f.f:byte [static] | nextMusicTrack | Certain | `aQ()` sets it from `musicTrackTable[h*2+rand]`; `a.java` plays track `f.f` from the MIDI blob. |
| f.f:byte[] | maxLevelPerType | Likely | g() allocates `[12]`; `ac()` shows the upgrade item when `e[9115+q] < f[e[8749+q]]` (level < max for type). |
| f.f:int | animCycle | Certain | `h()` cycles it 0–119; `v()`/`P()` use it for timed effects and story timing. |
| f.f:int[] | loadBarColors | Certain | Constructor sets `{0,5251341,16704820,16033043,12010496}`; `a(int)` (drawLoadProgress) colors the 5 bars from it. |
| f.f:short | tableMapHeight | Certain | `aN()` sets it from the level table; `c()` copies it to `d` (effective height) on next level. |
| f.f:short[] | unusedShortArrayF | Unknown | Declared (instance) but no field access in `f.class` bytecode and no writer found in `e.java`. |
| f.f:byte[][] | trapTable | Likely | g() allocates `[4][30]`; `a(BIIIIZI)B` records entity types 20–22 as `[0]=targetId,[1]=type,[2]=x,[3]=y`; occupant marked -123/-126. |
| f.g:boolean | keyLeft | Certain | `keyPressed()`/`keyReleased()` set it from the left key; `V()` reads it. |
| f.g:byte [static] | currentMusicTrack | Certain | `aQ()` sets `g = f` when the track changes and restarts the MIDI player. |
| f.g:byte[] | partEntityIds | Likely | Constructor allocates `[11]`; `a(IBBIIB)V` stores the entity id per sprite-part slot (`X = g[slot]`). |
| f.g:int | uptimeTicks | Certain | `h()` increments it every tick; `ar()`/`ay()` use it for menu timing. |
| f.g:int[] | messageDurations | Likely | Constructor allocates `[10]`; `a(BBBB)V` (postMessage) is its only reader. |
| f.g:byte[][] | tileEntityDraw | Likely | g() allocates `[P+3][O]`; `u()` stages entity ids per screen tile in it, draws via `i(id)`, clears to 0. |
| f.h:boolean | keyRight | Certain | `keyPressed()`/`keyReleased()` set it from the right key; `V()` reads it. |
| f.h:byte | musicMood | Likely | `aQ()` indexes the track table as `b[h*2+rand]`; `b.java:5702` sets `h=2`; persisted in saves. |
| f.h:byte[] | partDrawnFlagsH | Likely | Constructor allocates `[11]`; `a(IBBIIB)V` sets/checks `h[slot]` while drawing parts (var8==3/5/7). |
| f.h:int | trapScanRow | Certain | `aM()` zeroes it; `j()` advances it one row per tick while scanning traps. |
| f.h:int[] | mapChunkOffsets | Likely | `aN()` (load map) is its only reader in f; allocated by `e.java` (map RLE chunk offsets). |
| f.h:byte[][] | spriteYOffsetTable | Likely | g() allocates `[13][101]`; `i(int)` reads `h[type][id]` as a Y offset — never written, so always 0. |
| f.i:boolean | firePressed | Certain | `keyPressed()` sets it on fire; `V()` (input dispatch) consumes it. |
| f.i:byte | numericKey | Certain | `keyPressed()` stores the last numeric key (0/1/3/7/9); `V()` dispatches group keys from it. |
| f.i:byte[] | partDrawnFlagsI | Likely | Constructor allocates `[11]`; `a(IBBIIB)V` sets/checks `i[slot]` while drawing parts (var8==4). |
| f.i:int | tickLengthMs | Certain | `h()` sets it to 50 (fastTick) or 120; `run()` sleeps `i` ms per tick. |
| f.i:int[] | cheatKeyHistory | Likely | g() allocates `[6]`; `m(int)` (cheat tracker) reads it while matching keys against `cheatCode`. |
| f.i:byte[][] | spriteFrameTable | Likely | g() allocates `[13][101]`; `i(int)` reads `i[type][id]` as a frame offset — never written, so always 0. |
| f.j:boolean | storyAdvancePressed | Likely | `P()` (story screen) and `Q()` (story advance) set it; `ak()` (menu input dispatch) reads it. |
| f.j:byte | ticksSinceKeypress | Certain | `keyPressed()`/`keyReleased()` reset it to 0; `h()` increments it; `V()` uses it for key repeat. |
| f.j:byte[] | menuItemAvailable | Likely | Constructor allocates `[8]`; `ab()` (context-menu contents) is its only reader. |
| f.j:int | tickMsAlt | Certain | Constructor sets 120; `h()` computes `k = j/i` (anim speed factor) from it. |
| f.j:int[] | cheatCode | Certain | g() sets `{56,55,51,57,49}` = ASCII `8,7,3,9,1`; `m(int)` matches key presses against it. |
| f.j:byte[][] | pd0ByteTables | Certain | e.java loads 25 byte tables from pd0 into it; `aN()`/`a()`/`b()` read sprite/map data from it. |
| f.k:boolean | worldReady | Likely | `aM()` (reset world state) sets it true at the end of setup; `W()` sets it; `h()` reads it as the run gate. |
| f.k:byte | selectedVillagerId | Certain | `a(BB)V` (order villager) and `V()` set it; `k()`/`q()`/`z()` use it for the selection. |
| f.k:byte[] | hudElemSeen | Likely | g() allocates `[107]`; `a(BII)V` tests `k[aE]` (0 = new, <0 = changed) while laying out HUD elements. |
| f.k:int | animSpeedFactor | Likely | `h()` sets `k = j/i` (120/tickMs: 2 fast, 1 normal); `d(int,int)` divides the frame by it. |
| f.k:byte[][] | focusTable | Likely | Constructor allocates `[3][10]`; `W()` centers the camera from `k[0][0]`/`k[1][0]`, `d(int,int)` draws entity `k[2][0]` — never written, so always 0. |
| f.l:boolean [static] | leftSoftkeyPressed | Certain | `keyPressed()` sets it when `var1==a` (-6) or `42` (`*`); `W()` (softkey handling) reads it. |
| f.l:byte | inspectedVillagerId | Certain | `a(BBB)V` (info popup) and `ao()` set it; `x()`/`z()` show info for it. |
| f.l:byte[] | loadBarXOffsets | Likely | Constructor sets `{0,1,1,1,1}`; `a(int)` (drawLoadProgress) positions the 5 loading bars with it. |
| f.l:int | chiefId | Certain | Constructor sets 1; `aj()` (choose chief) advances it to the next living villager; used as an entity index into `e[]`; persisted in saves. |
| f.m:boolean [static] | rightSoftkeyPressed | Certain | `keyPressed()` sets it when `var1==b` (-7) or `35` (`#`); `W()` reads it. |
| f.m:byte | endScreenPhase | Certain | Quit/score screens set it (variant + step); the end sound played is `70+m`. |
| f.m:byte[] | loadBarYOffsets | Likely | Constructor sets `{0,1,1,2,3}`; `a(int)` positions the 5 loading bars with it. |
| f.m:int | gameAction | Certain | `keyPressed()`/`keyReleased()` store `getGameAction(key)` in it; `V()` dispatches on it. |
| f.n:boolean | inputLocked | Certain | `h()` keeps it true for the first 3 ticks; `keyPressed()` ignores keys while set. |
| f.n:byte | freeOrderSlot | Certain | `X()` (place confirm) scans `b[0][0][n]!=0` to find a free pending-order slot (max 29). |
| f.n:byte[] | loadBarWidths | Likely | Constructor sets `{100,98,0,0,0}`; `a(int)` sizes the 5 loading bars with it. |
| f.n:int | fireTapCount | Certain | `keyPressed()` increments it on fire; `a(BB)V`/`ae()` use it to tell tap from hold. |
| f.o:boolean | keyDownFlag | Certain | `keyPressed()` sets it on any key down; `keyReleased()` clears it; `V()` reads it. |
| f.o:byte | storySpeaker | Likely | `b(int)` (advance story step) sets it from the story table (`a[154]+mission + step`); `d(int,int)` reads it to draw the speaker. |
| f.o:byte[] | loadBarHeights | Likely | Constructor sets `{5,3,1,1,1}`; `a(int)` sizes the 5 loading bars with it. |
| f.o:int | startupPhase | Certain | `h()` advances it 0–3 during startup; `j()` reads it to gate world updates. |
| f.p:boolean | keyReleasedFlag | Certain | `keyReleased()` sets it; `V()` (input dispatch) reads it to end a press. |
| f.p:byte | newGameFlag | Likely | `ar()` sets it to 1 when starting a new game (dialog 60); `U()` (end story) gates the scene-2 auto-advance on it. |
| f.p:byte[] | storyActors | Likely | Constructor allocates `[40]`; `b(int)` reads `p[stepSlot]` as the step's villager id; `U()` clears it. |
| f.p:int | tickCount | Likely | `h()` increments it every tick; never read elsewhere (write-only counter). |
| f.q:boolean | moveOrderFlash | Certain | `c(II)V` (cursor draw) sets it when a move order is issued; `q()` (overlays) flashes the target while set. |
| f.q:byte | missionIndex | Certain | 0–6 campaign, 7 "the end", 8–11 custom-only; `aN()` loads the map for it; `d()`/`e()` save it. |
| f.q:byte[] | homePosition | Likely | `b.java:3730` copies `q[0]`/`q[1]` into `e[3131+v]`/`e[3232+v]` (each villager's home x/y); persisted in saves. |
| f.q:int | menuTargetId | Certain | Building id (<0), villager id (>0), or 0 (none); set by `a(BB)V`/`aE()`; read by menus, `X()`, `d(int)`. |
| f.r:boolean | placementValid | Certain | `a(BIIIIZI)B` (spawn entity) sets it from the placement check; `c(II)V` draws the ghost green/red from it. |
| f.r:byte | campaignProgress | Certain | 0–7; `au()` (campaign mission select) and `ar()` read it; `d()`/`e()` persist it. |
| f.r:byte[] | menuItemIds | Certain | g() allocates `[50]`; `Z()`/`aa()`/`ab()`/`ac()`/`ad()` fill it with the context-menu item ids; `af()` activates `r[as]`. |
| f.r:int | orderTargetId | Certain | `X()` (place confirm) stores the order target here; `c(II)V` draws the order cursor at it. |
| f.s:boolean | storyMode | Certain | True while a story scene plays; `q()`/`u()` skip world rendering while set. |
| f.s:byte | continueProgress | Certain | Record-248 value (`-1` = none); `ar()` offers Continue from it; `d()`/`e()` persist it. |
| f.s:byte[] | menuItemValues | Likely | g() allocates `[100]`; `ae()`/`af()` adjust `s[as]` for the +/- menu items (amounts). |
| f.s:int | placementType | Certain | Building (>3), trap (0–3), job (<0), 100 = full/error; `X()` writes it into `pendingOrders[1]`. |
| f.t:boolean | fireHeld | Certain | `keyPressed()`/`keyReleased()` track the fire key; `V()` uses it to start drag-select (`aE()`). |
| f.t:byte | savedMissionProgress | Certain | Record-248 value (`-1`/`-2` sentinels); `d()`/`e()` persist it. |
| f.t:byte[] | effectCounters | Likely | g() allocates `[3]`; `b(int,int,int,int)` (spawn effect) increments `t[kind]`; persisted in saves. |
| f.t:int | placeGhostX | Certain | `c(II)V` sets it (`t = y - w/2`) for the placement ghost; `X()` places at it. |
| f.u:boolean | dragSelecting | Certain | `aE()` sets it when a drag-select starts; `aF()` clears it when the rect is filled. |
| f.u:byte | endgameTimer | Certain | 0 = idle; armed 1 → verdict at tick 41 (win), armed 50 → verdict at tick 90 (lose); `j()` counts it. |
| f.u:byte[] | messageFlags | Likely | Constructor allocates `[10]`; `a(BBBB)V` (postMessage) and `a(boolean×9)` read it; `aM()` zeroes it. |
| f.u:int | placeGhostY | Certain | `c(II)V` sets it (`u = A - h/2`) for the placement ghost; `X()` places at it. |
| f.v:boolean | fireTapped | Certain | `V()` sets it on a quick fire tap (vs hold); `aE()`/`aF()` use it to tell tap from drag. |
| f.v:byte | mode | Certain | 0 = menu, 1 = in-game; `h()`/`V()`/`paint()` branch on it. |
| f.v:byte[] | mapData | Certain | e.java:283 allocates `[42381]`; `aN()` (load map) RLE-decodes the level from it. |
| f.v:int | dialogId | Certain | 56 lose, 57 win, 58 retry, 59, 60 new campaign, 61, 62 continue, 63, 64 load, 65 new-game confirm, 66 load-failed, 67 loaded, 68 quit, 69 loading, 70 custom gate, 71 score. |
| f.w:boolean | keyHeld | Likely | `keyPressed()`/`keyReleased()` set/clear it; `i()` (reset input) clears it; `aL()` (group-key hold timer) reads it. |
| f.w:byte | interactMode | Certain | 0 none, 1 villager selected, 2 context menu, 3 placing, 4 placing submenu; `V()`/`W()`/`X()` branch on it. |
| f.w:byte[] [static] | midiData | Certain | e.java:269 allocates `[26616]` and reads resource `/sa` into it; `a.java` plays MIDI from it. |
| f.w:int | tickerLastDialogId | Likely | `a(BII)V` (HUD draw) stores the dialog id it last rendered here and resets `scrollOffsetY` when it changes. |
| f.x:boolean | spriteFlipped | Likely | `a(IBBIIB)V` (draw parts) sets it; `a(IIIIB)V` (draw entity) reads it. |
| f.x:byte | savedMode | Likely | N() saves `mode` into it before setting mode=61 (pause); l(int) saves `interactMode` into it and restores it; al() restores `mode` from it. |
| f.x:int | savedDialogId | Certain | `N()` (pause screen) saves `v` into `x` before setting `v=61`; `al()` (resume) restores it. |
| f.y:boolean | loadingDone | Likely | `f()` (init) sets it when loading finishes; `a(int)` (draw load progress) and `keyPressed()` read it. |
| f.y:byte | cursorCol | Certain | `V()`/`W()` move it with the keys; `e.c()` homes it to 5; `k()`/`c(II)V` use it as the cursor column. |
| f.y:int | scrollOffsetY | Likely | `a(BII)V` (HUD/message ticker) and `aB()`/`aC()`/`ae()` (menu list) share it as the vertical scroll offset. |
| f.z:boolean | repaintPending | Certain | `h()`/`l()` set it to request a repaint; `run()` waits on it (`while (z && !Z) yield`). |
| f.z:byte | savedCursorCol | Certain | Saved/restored with `B`/`J`/`L` when leaving/entering menus (see `d()`/`e()` save format). |
| f.z:int | tickerScrollShown | Likely | `a(BII)V` copies `scrollOffsetY` into it; `A()` (panel refresh) and `c()` (HUD refresh) read it. |
| f.A:boolean | screenContentChanged | Likely | Screen/menu methods (`K()`/`R()`/`U()`/`W()`/`a(BBB)`/`aD()`/`am()`/`f(int,int)`) set it; `n()` (scroll/redraw) reads it. |
| f.A:byte | cursorRow | Certain | `V()`/`W()` move it with the keys; `e.c()` homes it to 9; `k()`/`c(II)V` use it as the cursor row. |
| f.A:int | viewHeightPx | Certain | e.java:126 sets 288; `m()`/`paint()` size the game buffer from it (304 on menu screens). |
| f.B:boolean | terrainDirty | Likely | `c(int,int,int,int)` (mark tile rect) and `n()` set it; `Y()` (camera scroll) and `n()` read it. |
| f.B:byte | savedCursorRow | Certain | Saved/restored with `z`/`J`/`L` when leaving/entering menus. |
| f.B:int | selRectX | Certain | `aF()` (drag-select end) fills the selection rect `(B,C,D,E)`; `aJ()` clears it; `h()`/`w()` use it. |
| f.C:boolean | hudChanged | Likely | `a(boolean×9)` (HUD panel flags) and `n()` set it; `V()` (input dispatch) and `n()` read it. |
| f.C:byte | homeCursorCol | Certain | e.java:131 sets it to `y` (5); `aO()` (start positions) restores the cursor to it. |
| f.C:int | selRectY | Certain | See `selRectX`; the drag-select rectangle's top edge in tiles. |
| f.D:boolean | rowsNeedRedraw | Likely | `n()` (scroll/redraw) sets it when the scroll offset changes; `o()` (redraw rows) reads it. |
| f.D:byte | homeCursorRow | Certain | e.java:131 sets it to `A` (9); `aO()` restores the cursor to it. |
| f.D:int | selRectW | Certain | See `selRectX`; the drag-select rectangle's width in tiles. |
| f.E:boolean | cursorMoved | Likely | `V()` (input) and `h()` (tick) set it when the cursor/selection changes; `c(II)V` (cursor draw) reads it. |
| f.E:byte | fireHoldTicks | Certain | Counts ticks while fire is held (0–4+); `V()` starts drag-select when it passes 4. |
| f.E:int | selRectH | Certain | See `selRectX`; the drag-select rectangle's height in tiles. |
| f.F:boolean | chiefMode | Likely | `aj()` (choose chief) sets it true; `af()`/`ah()`/`aM()` clear it; `q()`/`z()`/`ad()` show chief UI while set. |
| f.F:byte | fireHeldCount | Certain | Counts fire-held ticks; `>4` switches from tap to drag-select (`aE()`). |
| f.F:int | selectedGroup | Certain | -1 none, 0–4; `V()` clears it, `aE()` recalls the group into it. |
| f.G:boolean | hudContentChanged | Likely | HUD refresh methods (`A()`/`B()`/`x()`/`y()`/`z()`) set it; `c()` (HUD refresh dispatch) reads it. |
| f.G:byte | groupSlot | Certain | 2 or 3 for keys 7/9; `aG()`/`aH()` save/recall groups through it. |
| f.G:int | dragStartTileX | Certain | `aE()` (start drag-select) sets `G = y + I` (cursor tile); `w()` (minimap viewport) reads it. |
| f.H:boolean | menuNeedsDraw | Likely | `run()` sets it at startup; `F()` (menu dispatch) sets it; screen renders (`G()`–`R()`) read it. |
| f.H:byte | groupMemberTmp | Certain | Temporary during group save/recall (`aF()`/`aG()`/`aH()`). |
| f.H:int | dragStartTileY | Certain | `aE()` sets `H = A + K` (cursor tile); `w()` reads it. |
| f.I:boolean | menuBackPressed | Likely | `an()` (menu back) sets it; `M()` (Q=25 render) reads it. |
| f.I:byte | camTileX | Certain | Camera column in tiles; `Y()`/`aK()` scroll/clamp it; `u()`/`o()` render from it. |
| f.I:int | groupKeyHoldTicks | Certain | `aG()`/`aH()` set it; `aL()` (group-key hold timer) counts it down. |
| f.J:boolean | storyIsCutscene | Likely | `S()` (story setup) sets `J = (ao >= 27)`; `P()`/`R()`/`U()`/`u()` branch on it. |
| f.J:byte | savedCamCol | Certain | Saved/restored with `z`/`B`/`L` when leaving/entering menus. |
| f.J:int | dragRectScanX | Likely | `aF()` (drag-select end) scans the drag rect with `J`/`K` as loop variables. |
| f.K:boolean | menuItemActivated | Likely | `af()` (menu item activate) sets it; `aM()` (reset) clears it; `B()`/`z()`/`ad()`/`ap()` read it. |
| f.K:byte | camTileY | Certain | Camera row in tiles; `Y()`/`aK()` scroll/clamp it; `u()`/`o()` render from it. |
| f.K:int | dragRectScanY | Likely | See `dragRectScanX`. |
| f.L:boolean | placeConfirmFlagL | Likely | `X()` (place confirm) sets it; `aM()`/`d(int)` manage it; `a(BB)V`/`ad()`/`af()`/`u()` read it; persisted. |
| f.L:byte | savedCamRow | Certain | Saved/restored with `z`/`B`/`J` when leaving/entering menus. |
| f.L:int | selCountTemp | Likely | `aF()`/`aG()`/`aH()` (group/drag-select) use it as a temporary selection count. |
| f.M:boolean | placingActive | Likely | `l(int)` (set place mode), `af()`, and `X()` set it; `X()` (place confirm) reads it. |
| f.M:byte [static] | menuBufferIdx | Certain | `<clinit>` sets 0; `G()` sets `N = M` (title screen draws into buffer 0). |
| f.M:int | selectedCount | Likely | `aF()`/`aH()` (drag-select/group recall) count the selected villagers into it. |
| f.N:boolean | placeConfirmFlagN | Likely | Same use sites as `placeConfirmFlagL` (set by `X()`, read by `a(BB)V`/`ad()`/`af()`/`u()`); the L-vs-N split is unproven. |
| f.N:byte | drawBufferIndex | Certain | `G()` sets `N = M` (0); screen renders set it per screen; `paint()`/`q()`/`u()`/`d(int,int)` draw into buffer `N`. |
| f.N:int | spriteTableIndex | Likely | `a(IB)V` (draw entity) sets `N = b[0][type+1]`; `a(IBBIIB)V` indexes `j[3][N]` from it. |
| f.O:boolean | placeModeChanged | Likely | `l(int)` (set place mode) and `af()` set it; `X()` (place confirm) reads it. |
| f.O:byte | screenColsTiles | Certain | e.java:127 sets 11; `g()` allocates `[P+3][O]`, `u()`/`n()`/`d(int,int)` lay out from it. |
| f.O:int | spriteDataValue | Likely | `a(IB)V` (draw entity) sets `O = j[0][type+1]` (pd0 byte table); only `a(IB)V`/`a(IBBIIB)V` use it. |
| f.P:boolean | panelRefreshNeeded | Certain | `d(int)` sets it when the menu target changes; `a(B)`/`a(BBBBB)B`/`af()`/`ah()`/`aj()`/`an()`/`ap()`/`h()`/`j()`/`l(int)` set or read it. |
| f.P:byte | screenRowsTiles | Certain | e.java:128 sets `(288-1)/16+1 = 18`; `g()` allocates `[P+3][O]`, `u()`/`n()` lay out from it. |
| f.P:int | spriteFlags | Likely | `a(IB)V`/`a(IBBIIB)V` set `P = (j[3][N]&255)>>6` (0–3 sprite flags); only the draw-entity methods use it. |
| f.Q:boolean | menuScrollUp | Likely | `W()` (softkey) and `c()` (HUD refresh) set it; `aB()` (menu cursor up) reads it. |
| f.Q:byte | screenId | Certain | 14 main menu at startup; 10/11 pause, 20 popup, 22/23 load/result, 24 mission end, 25 quit, 27 system pause, 32 ad. |
| f.Q:int | minimapRectX | Certain | `w()` (minimap viewport) computes the viewport rect x in px; `w()` is its only user. |
| f.R:boolean | menuScrollDown | Likely | `W()` and `c()` set it; `aC()` (menu cursor down) reads it. |
| f.R:byte | prevScreen | Certain | Saved before entering a new screen; `an()` (menu back) restores `Q = R`. |
| f.R:int | minimapRectY | Certain | See `minimapRectX`; the viewport rect y in px. |
| f.S:boolean | screenDrawn | Likely | `a(IBI)V`/`d(int,int)`/`m()` (render/draw) set it; `ae()` (menu input) reads it. |
| f.S:byte | prevPrevScreen | Certain | Saved entering Q=27 (system pause); `al()` (resume) restores from it. |
| f.S:int | ghostPx | Likely | `c(II)V` (cursor/placement draw) sets `S = x - w/2*22` (ghost top-left px); `a(IB)V` reads it. |
| f.T:boolean | menuListChanged | Likely | `ai()` (menu refresh) sets it; `a(IBI)V`/`c()`/`d(int,int)` (draw/refresh) read it. |
| f.T:byte | loadAction | Certain | 0 load level, 1 load save, 2 check save; `am()` (load/save result) branches on it. |
| f.T:int | ghostPy | Likely | `c(II)V` sets `T = y - h/2*16 - 5 + e` (ghost top-left px); `a(IB)V` reads it. |
| f.U:boolean | menuSelectionChanged | Likely | `ai()` (menu refresh) sets it; `a(IBI)V`/`c()`/`d(int,int)` read it. |
| f.U:byte | homeScreenId | Certain | 5 normally; 1 if `gameLinkEnabled`; `run()`/`ar()` return to it. |
| f.U:int | weaponSlotIndex | Likely | `a(IBBIIB)V` zeroes it then counts weapon slots (`U<5` when var3==6, `U<1` when var3==4), indexing table `a[142]`. |
| f.V:boolean | selectionChanged | Likely | `ae()`/`af()`/`ah()` (menu input/activate/cancel) set it; `a(IBI)V`/`q()`/`z()` (HUD/overlays/selection info) read it. |
| f.V:byte | campaignScreenId | Certain | 4; `run()`/`ar()` go to it for campaign mission select. |
| f.V:int | weaponValue | Likely | `a(IBBIIB)V` computes it from `tribeStats` (`a[0][table[142]+U]/7/...`) and from `a[0][0]` thresholds. |
| f.W:boolean | customGameAvailable | Likely | `W()` (softkey) sets it; persisted in saves (`d()`/`e()`); `aw()` (openCustomGame) and `aA()`/`am()` read it. |
| f.W:byte | nearestEntityId | Certain | `k()` (find nearest) sets it: 1–50 villager, 51–100 building, 0 none. |
| f.W:int | minimapOriginX | Certain | `E()` (center minimap viewport) sets it; `C()`/`D()`/`d(int,int)` draw the minimap from it. |
| f.X:boolean | menuActionPending | Likely | `W()` (softkey) and `aA()` (menu nav) set it; `ay()` (menu nav handler) reads it. |
| f.X:byte | currentSpriteEntityId | Likely | `a(IBBIIB)V` sets `X = g[slot]` while drawing parts; `q()` reads it. |
| f.X:int | minimapOriginY | Certain | See `minimapOriginX`. |
| f.Y:boolean | paused | Certain | `d()` (hideNotify) sets it true, `e()` (showNotify) clears it; `h()`/`aS()` stop music while set. |
| f.Y:byte | spriteKind | Likely | `a(IIIIB)V` stores the entity kind here (29 = villager); `q()` branches on `Y==29`. |
| f.Y:int | placeMode | Certain | Set by `l(int)` (set place mode); `B()`/`ae()` read it to show the placement HUD. |
| f.Z:boolean | hideRequested | Certain | `d()` (hideNotify) sets it; `aS()` reads it; `run()` waits on it. |
| f.Z:byte | cursorVisibility | Certain | 0 visible, 1/2 unseen; `a(int,int):byte` (tileVisibility, fog of war) computes it. |
| f.Z:int | menuCursor | Certain | The absolute menu cursor (0..`ab`-1, wraps); `aB()`/`aC()` move it; menu nav methods (`aA()`/`ao()`/`aq()`/…) read it. |
| f.aa:boolean | gameLoaded | Certain | `run()` sets it true after `f()`/`g()`/load; `d()` (pause) only stops music `if (aa && !ad)`. |
| f.aa:byte | orderCursorType | Likely | `a(BB)V` (order villager) sets 1 (walk) or 2 (interact/attack); `c(II)V` draws sprite `34+aa`. |
| f.aa:int | savedMenuCursor | Certain | `ao()`/`az()` save `Z` into it entering a submenu; `an()` restores `Z = aa`. |
| f.ab:boolean | pauseScreenFirstRun | Likely | `aS()` sets it entering Q=27; `N()` (pause render) consumes it on the first frame. |
| f.ab:byte | orderTileX | Likely | `a(BB)V` sets `ab = y + I` (cursor tile x); `c(II)V` draws the order cursor at it. |
| f.ab:int | menuItemCount | Certain | `c(int,int,int)` (menu list draw) computes it from the menu table; `aB()`/`aC()` clamp the cursor by it. |
| f.ac:boolean | gameActive | Likely | `aS()` only enters Q=27 when set; set by `ar()` (game start) and `an()` (return from options). |
| f.ac:byte | orderTileY | Likely | `a(BB)V` sets `ac = A + K` (cursor tile y); `c(II)V` draws the order cursor at it. |
| f.ac:int | nearestDist | Certain | `k()` (find nearest) resets it to 1000 and keeps the best (smallest) distance in it. |
| f.ad:boolean | pauseMusicStopped | Likely | `d()` (pause) sets it after stopping the music once; `aS()` clears it when resuming. |
| f.ad:byte | activeHudPanel | Likely | `a(boolean×9)` sets it from the panel flags (1/2/5/6/9); `B()`/`Z()`/`j(int)` branch on it. |
| f.ad:int | scrollXPx | Certain | `V()` sets `ad = af*11` (scroll dir × 11 px); `m()`/`paint()`/`run()` shift the buffer by it. |
| f.ae:boolean | cheatSeqMatched | Likely | `m(int)` (cheat tracker) sets it once keys 8,7,3,9,1 have been entered in order. |
| f.ae:byte | hudButtonState | Likely | `a(byte,int,int)` (HUD draw) sets 0 active / 1 highlighted / 2 greyed per element. |
| f.ae:int | scrollYPx | Certain | `V()` sets `ae = ag*8`; `m()`/`paint()`/`run()` shift the buffer by it. |
| f.af:boolean | cheatUnlockedCustom | Certain | `m(int)` sets it when the 6th cheat key is `1`; `aw()` (openCustomGame) allows custom play when `r>0 || af`. |
| f.af:byte [static] | loadProgress | Certain | `a(int)` (drawLoadProgress) adds the step and caps at 98; `f()` sets 98 when loading finishes. |
| f.af:int | scrollDirX | Certain | `Y()` (camera scroll) sets -1/0/1; `n()` consumes it into the redraw range and zeroes it. |
| f.ag:boolean | cheatFlagAg | Likely | `m(int)` sets it when the 6th cheat key is `3`; no reader found in f (may gate something in `b`). |
| f.ag:byte [static] | minimapViewW | Certain | e.java sets 40; `C()` (draw minimap) clamps the minimap width to it. |
| f.ag:int | scrollDirY | Certain | `Y()` sets -1/0/1; `n()` consumes it into the redraw range and zeroes it. |
| f.ah:byte [static] | minimapViewH | Certain | e.java sets 60; `C()` clamps the minimap height to it. |
| f.ah:int | renderCol | Likely | `o()`/`u()` (render) use it as the column loop variable. |
| f.ai:byte | storySceneId | Certain | `b(int)` (advance story step) sets `ai = scene`; `S()`/`T()`/`U()` set up steps from it; saved/loaded. |
| f.ai:int | renderRow | Likely | `o()`/`u()` use it as the row loop variable. |
| f.aj:byte | storyStepCount | Certain | `S()` (story setup) sets it from the scene data; `b(int)` ends the scene when `ak == aj-1`. |
| f.aj:int | redrawColStart | Likely | `n()` (scroll/redraw) computes the first column to redraw into it; `o()` loops from it. |
| f.ak:byte | storyStepIndex | Certain | `S()` zeroes it; `b(int)` increments it per step; `U()` ends the scene at the last step. |
| f.ak:int | redrawRowStart | Likely | `n()` computes the first row to redraw into it; `o()` loops from it. |
| f.al:byte | storyStepSlot | Likely | `b(int)` reads `al = table[311+0+aT+ak]` (the step's participant slot); `am = p[al]` is the villager. |
| f.al:int | redrawColEnd | Likely | `n()` computes the last column to redraw into it; `o()` loops to it. |
| f.am:byte | subjectEntityId | Likely | `b(int)` sets `am = p[al]` (story step's villager); `a(BBB)V` (info popup) sets `am = var2`. |
| f.am:int | redrawRowEnd | Likely | `n()` computes the last row to redraw into it; `o()` loops to it. |
| f.an:byte | stepAction | Likely | `b(int)` reads `an = table[311+458+aT+ak]` (the step's action); `a(BBB)V` sets `an = var3` (popup action). |
| f.an:int | spriteVariant | Likely | `p()` (draw parts) resets it to -1 then sets 0/3/4 per part; `b(int,int,int)` reads it to pick the alternate sprite. |
| f.ao:byte | storySceneType | Likely | `S()` reads `ao = table[311+229+aT]`; `U()` branches on `ao==9`; `J = (ao>=27)`. |
| f.ao:int | overlayHeadPart | Likely | `p()` sets `ao = az` when the part type is 9 (head); `q()` (overlays) draws from it. |
| f.ap:byte | popupKind | Likely | `a(BBB)V` (info popup) sets `ap = var1`; `ap()` (popup confirm) branches on `ap==14/15`. |
| f.ap:int | overlayBodyPart | Likely | `p()` sets `ap = az` when the part type is 0 (body); `q()` draws from it. |
| f.aq:byte | speedSetting | Likely | `ad()` (select menu) shows item `23+aq`; `af()` cycles it 0→1→2→0; persisted in saves. |
| f.aq:int | drawEntityId | Likely | `a(IIIIB)V` (draw entity) sets `aq = var1`; `p()`/`q()`/`r()` draw overlays for it. |
| f.ar:byte | menuItemTotal | Certain | `Z()` zeroes it then `ar++` per item added; `aa()`/`ab()`/`ac()`/`ad()` fill `r[ar]`. |
| f.ar:int | drawX | Likely | `a(IIIIB)V` sets `ar = var2` (draw x px); `p()`/`q()` offset sprites from it. |
| f.as:byte | menuSelectedIndex | Certain | `ae()` (menu input) moves it up/down; `af()` activates `r[as]`; `Z()` clamps it to the visible window. |
| f.as:int | drawY | Likely | `a(IIIIB)V` sets `as = var3` (draw y px); `p()`/`q()` offset sprites from it. |
| f.at:byte | adjustMax | Likely | `aa()` (build menu) sets it (9 for the store adjuster); `ae()` caps `s[as]` at it. |
| f.at:int | drawFrame | Likely | `a(IIIIB)V` sets `at = var4` (animation frame, -1 = none); `q()` branches on `at>-1`. |
| f.au:byte | storeRowIndex | Likely | `aa()` finds the building's store row (`a[0][au][11]==q`); `ae()` adjusts `a[0][au][item]`. |
| f.au:int | spriteDefIndex | Likely | `a(IIIIB)V` sets `au = b[4][var6]`; `p()` reads `b[8][au]`/`j[15][au]` for the sprite parts. |
| f.av:byte | firstWeaponIndex | Likely | `aa()` (build menu) records the first store item with value ≥6; `a(IBI)V` reads it. |
| f.av:int | spriteFrameId | Likely | `a(IIIIB)V` sets `av = e[b[Y]+aq] & 255`; `b()` (filter sprite part) reads it. |
| f.aw:byte | spawnTribe | Likely | Constructor sets 2; `T()` (story setup) and `aP()` (spawn initial) set it; `a(BBBBB)B` (spawn villager) reads it. |
| f.aw:int | spritePackIndex | Likely | `a(IIIIB)V` sets it; `b()` (filter sprite part) reads it to pick the sprite pack. |
| f.ax:byte | visiblePanelCount | Likely | `a(boolean×9)` (HUD panel flags) sets it alongside the flags; `O()`/`m()`/`paint()` read it. |
| f.ax:int | spritePartVariant | Likely | `p()` (draw parts) sets `ax` (0, or `e[5151+aq]&1` when the part type is 9); `b()` reads it. |
| f.ay:byte | messageType | Likely | `a(BBBB)V` (postMessage) sets it; `aM()` (reset) clears it; `W()`/`x()` read it. |
| f.ay:int | spritePartType | Likely | `p()` sets `ay = j[14][az] & 15` (part type 0 body, 5, 7, 9 head, …); `b()` filters parts by it. |
| f.aA:int | spritePartBase | Likely | `p()` (draw parts) sets `aA = b[8][au]` (pd0 table); `az = aA + aB` is the absolute part index. |
| f.aB:int | partLoopIndex | Likely | `p()` loops `aB = 0 .. j[15][au]-1` (part count for the sprite); `az = aA + aB`. |
| f.aC:int | highlightCount | Likely | `a()` (highlightSelected) zeroes it then counts (`aC++`); `c(II)V` (cursor draw) reads it. |
| f.aD:int | hudPanelId | Certain | `c()` (HUD refresh) switches on it (0–14) to refresh each panel; `a(BII)V` sets it per element. |
| f.aE:int | hudElemIndex | Likely | `a(BII)V` (HUD draw) sets `aE = a[195]+60+elementId` and advances it while scanning the element list. |
| f.aF:int | hudElemValue | Likely | `a(BII)V` loads it as `d[aE]` (the element's count/amount); `j(int)`/`k(int)` use it for labels. |
| f.aG:int | hudLastDrawX | Likely | `d(int,int)` (draw HUD element) copies `aJ` into it after drawing; `j(int)`/`k(int)` read it. |
| f.aH:int | hudLastDrawY | Likely | See `hudLastDrawX`; the Y companion. |
| f.aI:int | hudSpriteId | Likely | `a(BII)V` sets it from pd0 table `b[1][...] & 4095`; `d(int,int)`/`j(int)`/`k(int)` draw that sprite. |
| f.aJ:int | hudDrawX | Likely | `d(int,int)` draws sprite `aI` at (`aJ`,`aK`) and offsets the minimap rect from them. |
| f.aK:int | hudDrawY | Likely | See `hudDrawX`. |
| f.aL:int | minimapRowOffset | Likely | `E()` (center minimap viewport) sets it; `aM()` zeroes it; `d(int,int)` offsets the minimap rect by it. |
| f.aM:int | minimapTilesW | Certain | `C()` (draw minimap) sets `aM = min(mapWidth, 40)`; `D()` (overlay) uses `aM-1` for the viewport rect. |
| f.aN:int | minimapTilesH | Certain | `C()` sets `aN = min(mapHeight, 60)`; `D()` uses `aN-1`. |
| f.aO:int | minimapPixelColor | Likely | `C()` computes it per tile from the palette (`int[][] b`) or the constant 6049325; `D()`/`e(int,int)` read it. |
| f.aP:int | storyImageId | Likely | `P()`/`Q()`/`R()` (story) and `ak()` use it as the current story image/actor id; `paint()` draws from it. |
| f.aQ:int | shakeOffsetY | Likely | `R()` (story render) sets it to `rand(0..8)` for the story-image wipe; `a(IB)V`/`a(IIIIB)V` add it to the draw Y. |
| f.aR:int | storyStepDir | Likely | `b(int)` sets `aR = table[155]+84+aS < 0 ? -1 : 0`; `P()`/`Q()`/`R()` read it. |
| f.aS:int | storyStepType | Likely | `b(int)` reads `aS = table[311+229+aT+ak]` (the step's type); `P()`/`Q()`/`R()` branch on it. |
| f.aT:int | storyTableBase | Likely | `S()` (story setup) computes the base offset into the story table (`a[311]`); `b(int)` indexes steps from it. |
| f.aU:int [static] | menuMaxVisibleRows | Certain | e.java:117 sets `aU = (240 - j[1][1010]*2) / aV`; `Z()` (build menu) reads it. |
| f.aV:int [static] | menuRowHeightPx | Certain | e.java:116 sets `aV = j[23][...] + 9`; `Z()` and `a(IBI)V` lay out menu rows with it. |
| f.aW:int | menuVisibleRows | Likely | `Z()` (build menu) sets it; `a(IBI)V`/`ae()`/`ai()` use it to clamp the visible menu window. |
| f.aX:int | menuScrollTop | Likely | `Z()` sets it; `a(IBI)V`/`ae()`/`ai()`/`aj()` scroll the menu list from it. |
| f.aY:int | menuWidthPx | Likely | `Z()` sets it (menu pixel width); `a(IBI)V` draws the menu frame with it. |
| f.aZ:int | menuHeightPx | Likely | `Z()` sets it (menu pixel height); `a(IBI)V` draws the menu frame with it. |
| f.az:int | spritePartIndex | Likely | `p()` sets `az = aA + aB` (absolute part index); `b()` (filter sprite part) reads it. |
| f.ba:int | bufferCopyX | Likely | `O()` (copy game buffer to menu) and `Y()`/`m()`/`paint()` use it as the copy source X. |
| f.bb:int | bufferCopyY | Likely | See `bufferCopyX`; the copy source Y (e.java:146 sets `bb = b[2] = 22`). |
| f.bc:int | storyFrameX | Likely | `R()` (story render) sets the frame rect (`bc`,`bd`,`be`,`bf`); `d(int,int)` draws it. |
| f.bd:int | storyFrameY | Likely | See `storyFrameX`. |
| f.be:int | storyFrameW | Likely | See `storyFrameX`. |
| f.bf:int | storyFrameH | Likely | See `storyFrameX`. |
| f.a():void | startGameThread | Certain | Body is exactly `new Thread(this).start()`; called once from `tribes.startApp()` via `f.a()`. |
| f.a():boolean | highlightSelected | Likely | Returns whether the selected entity (`aq == l`) should be highlighted; sets `aC`/`ax`/`ay`/`az`; called by `c(II)V`. |
| f.a():long [static] | nowMillis | Certain | Body is exactly `return System.currentTimeMillis()`; `run()` times each tick with it. |
| f.b():void | quitGame | Certain | Sets `b=false`, stops music (`aR()`), calls `midlet.notifyDestroyed()`; the only exit path. |
| f.b():boolean | filterSpritePart | Likely | Returns whether sprite part (`ax`/`ay`/`az`/`av`/`aw`) should be drawn; called by `p()` for every part. |
| f.a(int,byte):void | drawTile | Likely | Draws one terrain tile at (x,y) from `terrainGrid`/`pd0ByteTables`; called by `o()`/`u()` per tile. |
| f.a(int,byte):byte | setPendingOrderState | Likely | Finds the pending-order slot for entity `var1` and sets `b[tribe][4][slot] = var2`; called from `b.java` (World). |
| f.a(int,int):void | enqueueBuildAnim | Likely | Writes a build animation into `buildAnims` (30 slots); called by `a(BIIIIZI)B` (spawn entity). |
| f.a(int,int):boolean | isTileOnScreen | Certain | Returns whether tile (x,y) is inside the camera view (`I`/`K`/`O`/`P`); called by `u()`/`o()`. |
| f.a(int,int):byte | effectBitAt | Likely | Reads the `effectBitfield` bit for (x,y) and returns 0/1/2; called by `a(IB)V`/`q()`. |
| f.a(int,int):int | randRange | Certain | Returns `random.nextInt(var2-var1)+var1`; called everywhere a random range is needed. |
| f.c():void | loadNextLevel | Likely | Increments `q` (missionIndex) and spawns the next level (`aN()`/`aP()`); called at mission end. |
| f.c():boolean | refreshHudPanel | Likely | Switches on `aD` (hudPanelId 0–14) and refreshes that panel; called by `h()` every tick. |
| f.a(int):void | drawLoadProgress | Certain | Adds the step to static `loadProgress` (cap 98), draws the 5 loading bars from `loadBarColors`; called by `e.b()`/`e.d()` during load. |
| f.a(int):boolean [static] | isHandledKeyCode | Likely | Body is exactly `return var0 != -10`; `keyPressed()`/`keyReleased()` ignore key code -10 through it. |
| f.a(int):byte [static] | tribeOfEntity | Certain | Returns 0 for ids 1–50, 1 for 51–100, else 2; called by `V()`/`X()`/`a(BB)V`/`c(II)V`/`e(int,int)`/`u()`. |
| f.b(int):void | advanceStoryStep | Likely | `var1>=0` starts scene `ai=var1` (`S()`+`T()`); `var1<0` advances one step (`ak++`, `U()` at the last step). |
| f.b(int):byte [static] | tribeOfBuilding | Certain | Returns 0 for ids -1..-30, 1 for -31..-60, else 2; called by `V()`/`a(BB)V`/`a(IB)V`/`c(II)V`/`e(int,int)`/`u()`/`w()`. |
| f.b(int):boolean [static] | isObjectId | Likely | Body is exactly `return 101 <= var0 && var0 <= 122`; `a(BB)V`/`c(II)V`/`u()` treat such ids as attackable/interactable targets. |
| f.c(int):void | unassignEntity | Likely | Frees all entities whose job/owner is `var1` (30-slot tribe block), clearing `e[8810]`/`e[9298]`/`e[9237]`. |
| f.c(int):byte [static] | tribeOfOccupantCode | Likely | Returns 0 for codes -111..-116, 1 for -117..-122, 3 for -123 (trap), else 2; called by `a(IB)V`/`u()` on `occupantGrid` cells. |
| f.c(int):boolean [static] | isBuildingFootprintCode | Likely | Body is exactly `return -110 <= var0 && var0 <= -61`; `aN()`/`u()`/`a(IB)V` test `occupantGrid` codes with it. |
| f.d(int):void | refreshPanelIfMenuTarget | Likely | If a context menu is open (`w==2`) for target `var1==q`, sets `P` (panelRefreshNeeded). |
| f.d(int):boolean | loadSlot | Certain | Opens RecordStore `t1` (q<7) or `t2` (q>=7), reads the save format in order; called by `run()`/`am()`. |
| f.e(int):void | removeFromGroups | Certain | Removes entity `var1` from every villager group (`[5][50]`), compacting; clears the selection if it was selected. |
| f.e(int):boolean | saveSlot | Certain | Writes the save format to RecordStore `t1`/`t2` in the same order `loadSlot` reads; called by `am()`. |
| f.b(int,int):void | resetVillagerPositions | Likely | Moves all 50 villagers of tribe 0 to tile (var1,var2) (home spawn), clearing their `occupantGrid` cells. |
| f.b(int,int):boolean | isTileInBounds | Certain | Body is exactly `return x>=0 && y>=0 && x<mapWidth && y<mapHeight`; called by `a(IB)V`/`c(II)V`/`u()`. |
| f.f():void | initGame | Certain | Creates `world`/`textLoader`/`midiPlayer`, loads pd0 tables via `e`, reads manifest properties. |
| f.g():void | allocateBuffers | Certain | Allocates every game array (`terrainGrid`, `occupantGrid`, `entityData`, groups, effects, …) and the screen buffers. |
| f.h():void | tickUpdate | Certain | Per-tick update: input flags, timers, music check (`aS()`), message advance, then `j()` (world). |
| f.i():void | resetInputFlags | Certain | Zeroes the per-tick input flags (`e`/`f`/`g`/`h`/`i`/`t`/`v`/`n`) at the start of each tick. |
| f.j():void | tickWorldUpdate | Certain | Per-tick world update + endgame countdown (`u` → verdict at 41/90) + `checkWinLose` via `world.ak()`. |
| f.k():void | findNearestEntity | Certain | Scans entities around the cursor, keeps the nearest in `W` (nearestEntityId) and `ac` (nearestDist, init 1000). |
| f.l():void | requestRepaint | Certain | Sets `z` (repaintPending); `run()` waits on it before the next frame. |
| f.l(int):void | setPlaceMode | Certain | Sets `Y` (placeMode) = var1 and refreshes the HUD flags (`c[]` rebuilt, `d[4]=33+Y`, `b[2]=true`). |
| f.m():void | renderToBuffer | Certain | Renders the game screen into buffer `N` (terrain, entities, HUD, minimap). |
| f.n():void | scrollRedrawTerrain | Certain | Computes the redraw range (`aj`/`ak`/`al`/`am`) from the scroll dirs and calls `o()`. |
| f.o():void | redrawTerrainRows | Certain | Redraws terrain rows `ai`/`ah` in the range, consulting `tileDirty`. |
| f.p():void | drawEntityParts | Certain | Loops the sprite parts (`aA`/`aB`/`az`/`ay`/`ax`) and draws each via `a(IBBIIB)V`. |
| f.q():void | drawEntityOverlays | Certain | Draws HP bars, selection corners, and chief markers for the current entity. |
| f.r():void | updateEffects | Certain | Advances the 40 effect slots in `effects` (`d[7][40]`). |
| f.s():void | updateBuildAnims | Certain | Advances the 30 build-animation slots in `buildAnims` (`e[3][30]`). |
| f.t():void | clearEffects | Certain | Zeroes the `effects` table. |
| f.u():void | renderWorldEntities | Certain | Row-by-row entity rendering; calls `v()` first, stages entities in `tileEntityDraw`. |
| f.v():void | drawWorldMarkers | Certain | Draws trap markers (from `aiTargets`) and building flags (from `pendingOrders` type 3). |
| f.w():void | drawMinimapViewport | Certain | Draws the minimap viewport rect (`Q`/`R`) and the selection corners. |
| f.x():void | buildVillagerMenu | Likely | Builds the villager context-menu contents (uses `villagerGroups` and `menuItemIds`). |
| f.y():void | refreshVillagerInfoHud | Likely | Refreshes the villager-info HUD panel for the inspected villager (`l`). |
| f.z():void | refreshSelectionInfoHud | Likely | Refreshes the selection-info HUD panel; reads `G` (hudContentChanged) and `V` (selectionChanged). |
| f.A():void | refreshPanel6 | Likely | Refreshes HUD panel 6; called by `c()` (HUD refresh dispatch). |
| f.B():void | refreshSoftkeyPanel | Likely | Refreshes the softkey label panel; reads `b[ad]` (hudPanelDirty). |
| f.C():void | drawMinimap | Certain | Draws the minimap image from `terrainGrid`/`occupantGrid` via `paletteColors`; sets `aM`/`aN` (tiles w/h). |
| f.D():void | drawMinimapOverlay | Certain | Draws the minimap viewport rect and entity dots over the minimap. |
| f.E():void | centerMinimapViewport | Certain | Centers the minimap viewport (`W`/`X` origins) on the camera. |
| f.F():void | dispatchMenuScreen | Certain | Dispatches on `Q` (screenId) to the screen renderers (`G()`–`R()`). |
| f.G():void | renderTitleScreen | Certain | Draws the title screen, including the 4 drifting `titleParticles`. |
| f.H():void | renderMenuScreen | Certain | Renders the main menu screen (Q=14 and friends). |
| f.I():void | renderScreen23 | Likely | Renders screen Q=23 (load/result variant). |
| f.J():void | renderScreen15 | Likely | Renders screen Q=15. |
| f.K():void | renderPopupScreen | Likely | Renders the popup screen Q=20. |
| f.L():void | renderMapScreen | Likely | Renders the map screen Q=19. |
| f.M():void | renderScreen25 | Likely | Renders screen Q=25 (quit). |
| f.N():void | renderPauseScreen | Certain | Renders the system-pause screen Q=27; consumes `ab` (pauseScreenFirstRun) on the first frame. |
| f.O():void | copyGameBufferToMenu | Certain | Copies the game buffer into the menu buffer (`ba`/`bb` offsets). |
| f.P():void | renderStoryScreen | Certain | Renders the story screen Q=17 (wipe `P()` + scene image `aP`). |
| f.Q():void | advanceStory | Certain | Advances the story scene (steps `ak`/`aj`); called from `P()` on fire. |
| f.R():void | renderStory | Certain | Renders the story image with the wipe (`aQ` shake offset) and frame (`bc`–`bf`). |
| f.S():void | setupStoryScene | Certain | Sets up a story scene (`ai`, `aj`, `aT`, `ao`, `J`); called by `b(int)`. |
| f.T():void | setupStorySceneData | Likely | Per-scene data setup (loads the scene's text/actors from the story tables). |
| f.U():void | endStory | Certain | Ends the story scene, clears `p[]` (storyActors), restores the game screen. |
| f.V():void | dispatchInGameInput | Certain | Dispatches in-game keys (directions, fire, groups 7/9, softkeys) to `aE()`/`aF()`/`aG()`/`aH()`/`W()`. |
| f.W():void | handleSoftkeys | Certain | Handles the left/right softkeys (`l`/`m`) per screen; sets `W` (customGameAvailable) and `X`. |
| f.X():void | confirmPlacement | Certain | Confirms a placement: enqueues into `pendingOrders`, sets `L`/`N` flags, plays the sound. |
| f.Y():void | scrollCamera | Certain | Scrolls the camera (`I`/`K`) from the direction keys, setting `af`/`ag` (scroll dirs). |
| f.Z():void | buildContextMenu | Certain | Builds the context menu (`r[]` item ids, `ar` count, `aW`/`aX`/`aY`/`aZ` geometry). |
| f.aa():void | buildMenuContentsA | Likely | Builds one context-menu section (store/build); finds rows via `buildingStores`. |
| f.ab():void | buildMenuContentsB | Likely | Builds one context-menu section; reads `j` (menuItemAvailable). |
| f.ac():void | buildMenuContentsC | Likely | Builds one context-menu section (upgrade); compares level vs `maxLevelPerType`. |
| f.ad():void | buildSelectMenuContents | Likely | Builds the villager select menu; cycles `aq` (speedSetting) on items 23–25. |
| f.ae():void | handleMenuInput | Certain | Moves `as` (menuSelectedIndex) up/down, adjusts `s[as]` for +/- items, handles `Q`/`R` scroll flags. |
| f.af():void | activateMenuItem | Certain | Activates `r[as]` (the selected menu item); sets `K` (menuItemActivated). |
| f.ag():void | assignJob | Likely | Assigns the selected job to the selected villager(s). |
| f.ah():void | cancelMenu | Certain | Cancels the menu (clears `F` chiefMode, restores `w`); called on back. |
| f.ai():void | refreshMenu | Certain | Refreshes the menu after an action; sets `T`/`U` (menuListChanged/menuSelectionChanged). |
| f.aj():void | chooseChief | Certain | Sets `F` (chiefMode) true and advances `l` (chiefId) to the next living villager. |
| f.aA():void | navigateMenuA | Likely | One of the four menu-navigation handlers (`ax`/`ay`/`az`/`aA`) for screens 16/21/22. |
| f.aB():void | menuCursorUp | Certain | Moves `Z` (menuCursor) up (wraps by `ab` menuItemCount). |
| f.aC():void | menuCursorDown | Certain | Moves `Z` down (wraps by `ab`). |
| f.aD():void | handleScreenTransition | Likely | Handles screen transitions (`Q`/`R`/`S` save/restore) when entering Q=27 and friends. |
| f.aE():void | startDragSelect | Certain | On fire-held: sets `G`/`H` (drag start tile), `F` (selectedGroup)=0, `u` (dragSelecting)=true. |
| f.aF():void | endDragSelect | Certain | On fire release: fills the selection rect (`B`/`C`/`D`/`E`), counts `M` (selectedCount), clears `u`. |
| f.aG():void | saveGroup | Certain | Saves the current selection into group `G` (keys 7/9) in `villagerGroups`. |
| f.aH():void | recallGroup | Certain | Recalls group `G` from `villagerGroups` into the selection. |
| f.aI():void | clearGroup4 | Likely | Clears group slot 4 in `villagerGroups`. |
| f.aJ():void | clearDragSelect | Certain | Clears the drag-select rect (`B`/`C`/`D`/`E` = 0). |
| f.aK():void | clampCamera | Certain | Clamps `I`/`K` (camera) to the map bounds (`c`/`d` minus `O`/`P`). |
| f.aL():void | groupKeyHoldTimer | Certain | Counts down `I` (groupKeyHoldTicks) while a group key is held. |
| f.aM():void | resetWorldState | Certain | Zeroes `entityData` ranges, `tribeStats`, `buildingStores`, `trapTable`, `pendingOrders`, `aiTargets`, grids, and flags. |
| f.aN():void | loadMap | Certain | RLE-decodes the level map from `mapData` into `terrainGrid`/`occupantGrid`; sets `c`/`d`/`e`/`f` dims. |
| f.aO():void | placeCameraStartPositions | Certain | Centers the camera (`I`/`K`) and cursor (`y`/`A`) on the home position (`C`/`D`). |
| f.aP():void | spawnInitialEntities | Certain | Spawns the initial buildings/units for the level via `a(BIIIIZI)B`. |
| f.aQ():void | selectMusicTrack | Certain | Picks `f` (nextMusicTrack) = `musicTrackTable[h*2+rand]`; restarts the MIDI player when it changes. |
| f.aR():void | stopMusic | Certain | Stops the MIDI player (called on pause/quit/track change). |
| f.aS():void | tickPauseMusicCheck | Likely | Per-tick: if paused (`Y`) stops music, else resumes; manages `ab`/`ad` flags. |
| f.ak():void | dispatchMenuInput | Certain | Dispatches menu keys; calls `al()` then `am()` for Q=22/23. |
| f.al():void | resumeFromPause | Certain | Resumes from Q=27: restores `Q`/`v` (dialogId from `x`), clears `ab`. |
| f.am():void | showLoadSaveResult | Certain | Shows the load/save result screen (dialog 66/67) based on `T` (loadAction). |
| f.an():void | menuBack | Certain | Back action: restores `Q = R` (prevScreen) and `Z = aa` (savedMenuCursor). |
| f.ao():void | menuSelect | Certain | Select action: saves `Z` into `aa`, enters the submenu screen. |
| f.ap():void | popupConfirm | Certain | Confirms the popup (`ap` popupKind; 14/15 are special-cased). |
| f.aq():void | handleMissionEnd | Certain | Handles the mission-end screen (dialog 56/57 win/lose, 71 score). |
| f.ar():void | handleScreen16 | Likely | Handles screen Q=16 (new game / continue / options entry). |
| f.as():void | toggleOptions | Likely | Toggles an option (writes `l` chiefId? — see "Still unknown") and refreshes the menu. |
| f.at():void | selectDifficulty | Likely | Sets `b` (selectedDifficulty) from the difficulty menu. |
| f.au():void | selectCampaignMission | Likely | Selects the campaign mission from `r` (campaignProgress). |
| f.av():void | showMissionEndScore | Likely | Shows the mission-end score screen (dialog 71). |
| f.aw():void | openCustomGame | Certain | Seeded rename; opens the custom game when `r>0 || af` (cheatUnlockedCustom). |
| f.ax():void | navigateMenuX | Likely | Menu-navigation handler for screens 16/21/22 (with `ay`/`az`/`aA`). |
| f.ay():void | navigateMenuY | Likely | See `navigateMenuX`. |
| f.az():void | navigateMenuZ | Likely | See `navigateMenuX`. |
| f.i(int):void | drawEntitySprite | Likely | Draws one entity's sprite by id, using `spriteYOffsetTable`/`spriteFrameTable` for part offsets. |
| f.j(int):void | drawHudNumbers | Likely | Draws HUD numbers/labels at (`aJ`,`aK`) from `hudSpriteId`/`hudElemValue`. |
| f.k(int):void | drawHudLabels | Likely | Draws HUD labels/values from `hudElemValue` via tables `a[126]`/`a[127]`. |
| f.a(byte,byte,byte,byte,byte):byte | spawnVillager | Likely | Finds a free villager slot (50 per tribe) and spawns it; returns the slot or -1. |
| f.a(byte,byte,byte,byte):void | postMessage | Likely | Queues a message (type 100 = advance queue); `h()` posts type 100 every 6 ticks. |
| f.a(int,int,int,int):void | spawnAnimEffect | Likely | Writes an effect into `effects` (40 slots) at (x,y) with type/frame. |
| f.a(int,boolean):void | clearEntityJob | Likely | Clears entity `var1`'s job chain (`e[8993]`/`e[4545]`), called by `unassignEntity`. |
| f.a(int,int,int,int,byte):void | drawEntity | Likely | Draws one entity at (x,y) with frame/kind; sets `aq`/`ar`/`as`/`at`/`au`/`av`/`aw`. |
| f.a(int,byte,int):void | drawMenuItem | Likely | Draws one menu-list item; reads `aW`/`aX`/`aY`/`aZ` (menu geometry) and `S`/`T`/`U`/`V` flags. |
| f.a(byte,int,int):void | layoutHudElement | Likely | Computes a HUD element's layout (`aE`/`aF`/`aI`/`aJ`/`aK`) from the element tables. |
| f.a(int,byte,byte,int,int,byte):void | drawEntityWithParts | Likely | Draws an entity with all its parts (`p()` calls it); sets `N`/`O`/`P`/`U`/`V` sprite state. |
| f.b(int,int,int,int):void | spawnEffect | Likely | Writes an effect into `e[9513+...]` slots (22 max); called by `a(BIIIIZI)B`. |
| f.b(int,boolean):void | setEntityState | Likely | Sets `e[8627+var1]` to 1/2 (entity state) and updates its sprite from the tables. |
| f.c(int,int,int,int):void | markTileRect | Likely | Marks a tile rect (x,y,w,h) dirty (`tileDirty`) and sets `B` (terrainDirty); called by `a(BIIIIZI)B`. |
| f.c(int,int):void | drawCursor | Certain | Draws the cursor/selection box at (x,y), including the order cursor (`aa`/`ab`/`ac`). |
| f.d(int,int):void | drawHudElement | Likely | Draws HUD element `aI` (sprite) at (`aJ`,`aK`) and the minimap rect from `O`/`P`/`aL`. |
| f.d(int,int,int,int):void | drawSpriteAt | Likely | Draws sprite `var1` at (var2,var3) with frame `f/k` (anim speed factor). |
| f.e(int,int):void | drawTerrainTile | Likely | Draws one terrain tile at (x,y) from `terrainGrid` and the minimap color `aO`. |
| f.a(Graphics,Image,int,int,int,int,int,int,int):boolean [static] | drawRegionSafe | Likely | Safe `drawRegion` wrapper (clips to the screen); called by all sprite drawing. |
| f.a(Graphics,int,int,int,int):void [static] | fillRect | Likely | Thin wrapper over `Graphics.fillRect`; called by `R()` (story frame) and HUD drawing. |
| f.b(Graphics,int,int,int,int):void [static] | drawRect | Likely | Thin wrapper over `Graphics.drawRect`; called by `d(int,int)` (minimap rect). |
| f.c(Graphics,int,int,int,int):void [static] | setClipRect | Likely | Thin wrapper over `Graphics.setClip`; called by `R()`/`m()` before drawing. |
| f.d(Graphics,int,int,int,int):void [static] | drawLine | Likely | Thin wrapper over `Graphics.drawLine`; called by HUD/minimap drawing. |

### Notes for the translator

Notes for the translator

- The obfuscator reuses one letter for many members of different types in the same class; the map keys above
  (field name + Java type, method name + params + return type) are the only unambiguous keys. Never rename by name
  alone.
- Several fields are written by `e.java` (the loader), not by `f` itself: `f.a` (String version), `f.d` (boolean
  gameLinkEnabled), `f.a` (boolean cheatsEnabled) from manifest properties; `f.e`/`f.f` (static short[] song tables)
  and `f.w` (static byte[] midiData) from the `/sa` resource; `f.a`/`f.b`/`f.c`/`f.d` (int[] screen tables) and
  `f.a`/`f.b` (byte[] screen tables) from `e.c()`; `f.O`/`f.P` (byte screenColsTiles/screenRowsTiles) and `f.A`
  (int viewHeightPx) from `e.c()`; `f.aU`/`f.aV` (static menu layout) from `e.b()`. The translator must keep these
  cross-class writes.
- `int O`/`int P` (sprite temps, written by `a(IB)V`/`a(IBBIIB)V`) are DIFFERENT fields from `byte O`/`byte P`
  (screenColsTiles/screenRowsTiles, set by `e.c()`); likewise `int A` (viewHeightPx, 288) vs `byte A` (cursorRow, 9),
  and `int a` (static softkeyLeftCode, -6) vs the instance `a` fields. The type in the map key is what separates them.
- The save format order (`loadSlot`/`saveSlot`, RecordStores `t1`/`t2`) and the `t0` settings order must be preserved
  exactly, or old saves break; the field list in `d(int):boolean`/`e(int):boolean` is the format spec.
- `f` has 325 fields and 180 methods; 33 fields are unique names (aA–aZ, az, ba–bf) and most other methods are unique
  names. Only the 292 + 28 colliding members MUST be renamed for the Java to compile; the unique ones are renamed
  here for readability.

## Still unknown

- Most methods in `f` and `b` are not yet read through; see `ROADMAP.md` open questions.
- `c.a(int)` (`selectStorySection`) for section ids ≥ 76 would derive file index 16733 and try to open nonexistent `/d16733` (harmless no-op); all observed call args are 70–75, so the original intent for ids ≥ 76 is unknown.
- `e.packImageCounts` is 31 for pi0 but the pack holds only 27 PNGs; the extra 4 slots are never filled.
- Whether `f.java:4876-77` calls `TextLoader.loadTextFile` (assumed: reload s/t after a language change) or `World.b(int)`; only that call-site attribution is Likely.
- `c.cursor`, `c.lineYOrAge` and `c.lineXOrEntityId` each serve two roles; the single names cover both proven roles, but the original source may have had separate variables.
- The full range/meaning of Game's music setting passed to `MidiPlayer.setVolume`; only the player's contract (level = value×20, 0 = stop) is Certain.
- Deliberately not renamed: platform overrides keep their API names (`startApp`/`pauseApp`/`destroyApp`/`run`, `paint`/`keyPressed`, `playerUpdate`) and constructors are not renamed.
- World (b): exact meanings of record-27 flag bits and record-41 categories; which building type occupies player slot 1 (`a[8506]`); record-279 bit 4; building field 13 (offset 9176) never used in `b`; dual use of building field 8810 (foreman id vs hut resident count); exact roles of villager fields 1616/1717/7575; animal field details (9359 block); caller of `scanForTargetKind` (no callers in `b.java`); Structural names are revisit candidates.
- Game (f): 3 instance short arrays with no access found (`unusedShortArrayC/E/F` — cross-class bytecode scan needed); L-vs-N place-confirm flags split unproven; T-vs-U menu flags split unproven; `speedSetting` label unproven; what entity ids 101–122 are; `toggleOptions` vs change-chief; `visiblePanelCount`; `spriteFlipped` flip-vs-visibility; the `a(boolean×9)` write to `byte P`; 7 small unnamed helpers now carry mechanical auto names (see `names-auto.map`); 3 probably-vestigial never-written tables (`focusTable`, `spriteYOffsetTable`, `spriteFrameTable`); `cheatFlagAg` may gate something in World; `tickCount` probably a leftover counter.
