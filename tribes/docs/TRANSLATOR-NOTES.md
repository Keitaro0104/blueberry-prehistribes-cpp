# Translator Notes

How the mechanical Java → C++ translator (`tools/java2cpp.py`) maps the
renamed Prehistoric Tribes Java to C++, what the hand-written runtime
provides, what is not handled, and what is still unverified.

The translator is our own implementation (Python + javalang), following
the GOW Betrayal port's pattern. It never hand-edits its input: fix
`names.map` or the renamer to change the Java, and regenerate with
`python3 tools/java2cpp.py`. Never hand-edit `tribes/port/src/gen/`.

## Type mapping

| Java | C++ |
| --- | --- |
| `byte`, `short`, `int`, `long` | `int8_t`, `int16_t`, `int32_t`, `int64_t` |
| `boolean` | `bool` |
| `char` | `char16_t` |
| `float`, `double` | rejected (none in the game) |
| `String` | `String` — immutable UTF-16 value type (`jrt.h`) |
| `StringBuffer` | `StringBuffer` (`jrt.h`) |
| `T[]`, `T[][]`, `T[][][]` | `Arr<T>` — bounds-checked, `shared_ptr`-backed, so assignment aliases like Java |
| `Arr<bool>` | `vector<unsigned char>` inside `Arr` (`std::vector<bool>` is bit-packed and `operator[]` returns a proxy, not a reference) |
| game classes (`Game`, `World`, …) | raw pointers (`Game*`) |
| `Object` | `void*` (only used for one dead local and the unused `playerUpdate` event-data parameter) |
| static fields | `inline static` |
| `null` | `nullptr` |
| primitives without an initializer | value-initialized (`Type name{};`) — matches Java's 0 / false / null defaults |

`String ==` is reference equality (like Java `==`); `equals()` is value
equality. `Arr::operator==` is reference equality. `String + int/long/
bool/char` works through `operator+` overloads and `String::valueOf` /
`jstr()`. All generated members are `public` (Java package-private
access between these classes has no C++ equivalent). Override methods
get `override`. No field/method name clashes and no overloaded methods
exist in the code.

## J2ME API mapping

| Java (MIDP-2.0 / CLDC-1.0) | C++ stand-in |
| --- | --- |
| `javax.microedition.lcdui.Graphics` | `Surface` (`gfx.h`) — software framebuffer |
| `javax.microedition.lcdui.Image` | `Image` (`gfx.h`) — PNG via stb_image, `createImage(int,int)` / `createImage(String)` / `createImage(byte[],off,len)`, `getGraphics()` returns a `Surface` sharing the image's pixels |
| `javax.microedition.lcdui.Canvas` | `Canvas` (`engine.h`) — `paint` / `keyPressed` / `keyReleased` / `hideNotify` / `showNotify`, `setFullScreenMode` (no-op), `repaint()` + callback, `getGameAction` |
| `javax.microedition.midlet.MIDlet` | `MIDlet` (`engine.h`) — `startApp` / `pauseApp` / `destroyApp`, `notifyDestroyed` |
| `javax.microedition.lcdui.Display` | `Display` (`engine.h`) — singleton, `getDisplay(midlet)`, `setCurrent(canvas)`, `getCurrent()` |
| `javax.microedition.rms.RecordStore` | `RecordStore` (`engine.h`) — one file per record in `Platform::saveDir` (`<saveDir>/<name>_<id>.bin`) |
| `java.io.DataInputStream` | `DataInputStream` (`jrt.h`) — big-endian reads; reads eagerly at construction (drains the wrapped stream into its own buffer, so temporary streams can be discarded) |
| `java.io.DataOutputStream` | `DataOutputStream` (`jrt.h`) — big-endian writes |
| `java.io.ByteArrayInputStream` / `ByteArrayOutputStream` | same names (`jrt.h`); the 3-arg `ByteArrayInputStream(byte[], off, len)` is used by `MidiPlayer` |
| `java.util.Random` | `Random` (`jrt.h`) — the JDK linear-congruential generator, so the fixed seed 1 produces the same sequence as the original |
| `java.lang.Thread` / `Runnable` | `Thread` / `Runnable` (`jrt.h`) — `std::thread` underneath, with a static registry and `joinAll()` for shutdown |
| `java.lang.System.arraycopy` | `arraycopy(...)` (in namespace `jrt`, not `jlang`) |
| `System.gc()` / `System.currentTimeMillis()` | `jlang::gc()` (no-op) / `jlang::currentTimeMillis()` |
| `Thread.yield()` / `Thread.sleep()` | static, mapped to the C++ equivalents |
| `getClass().getResourceAsStream(path)` | `JavaClass::instance()->getResourceAsStream(path)` (`jrt.h`, defined in `platform.cpp` where `Platform::dataDir` lives); returns `nullptr` when the file is missing (the code null-checks it) |
| `javax.microedition.media.Player` / `Manager.createPlayer` | `Player` (`engine.h`) — state machine 0 / 100 / 300 / 400; on Windows the backend is a `midiOut` sequencer (`platform.cpp`, SMF format 0/1 parser + tempo map + sequencer thread); a silent stub elsewhere |
| `javax.microedition.media.PlayerListener` | `PlayerListener` (`engine.h`) — `playerUpdate(Player*, String, void*)` |
| `javax.microedition.media.control.VolumeControl` | `VolumeControl` (`engine.h`) |

### Graphics subset actually used

`setColor`, `setClip`, `translate`, `fillRect`, `drawRect`, `drawLine`,
`drawImage`, `drawRegion`, `getGraphics`. All text is drawn as glyph
sprites via `drawRegion` (the game never calls `drawString`/`setFont`/
`getFont`). All `drawRegion` calls go through `drawRegionSafe` with
transform 0 — only `TRANS_NONE` is exercised by the game, but all 8 MIDP
transforms are implemented. Anchor handling: 0 = top-left, 3 =
HCENTER|VCENTER, 20 = TOP|LEFT. `drawImage`/`drawRegion` blend source
pixels src-over (MIDP semantics: opaque pixels replace, transparent
pixels are skipped, partial alpha blends). Clip is stored in device
space. `Surface::writeBmp()` writes a 24-bit BMP for `--dump`.

## Threading model: game thread (a deliberate deviation from GOW)

The GOW port restructures the loop into a `runFrame()` called by the
shell. This port does not: the loop lives in the translated `Game.run()`,
so it runs on its own `std::thread` (started by the translated
`startGameThread()` → `new Thread(this).start()`), exactly like MIDP.
The Win32 shell only pumps messages and presents frames. A repaint is
requested by the game thread via `Canvas::repaint()`; the shell's
callback paints the current canvas into the 240×320 screen `Surface`
and blits it to the window. That happens on the game thread, under the
screen mutex, so the shell thread never touches the `Surface` except
through `WM_PAINT` (which takes the same mutex). Key events are
forwarded to the game thread's `keyPressed`/`keyReleased` — the same
benign cross-thread boolean-flag race as the original MIDP code, where
`keyPressed` runs on the event thread.

## The assumed subset (what the translator refuses)

The pre-flight check (`--check-only`) fails loudly on: generics,
lambdas, method references, `throw`, `synchronized`, `assert`,
enhanced-for, nested types, `finally`, float literals, `>>>`.
The renamed Java contains none of these (verified by survey).

Survey of the renamed Java (17,500 lines, 7 files): no generics,
lambdas, floats, `finally`, `throw`, `synchronized`, or enhanced-for.
No `>>>`. No `String ==` comparisons. No `super.` calls. No
switch-local variable declarations (no switch hoisting needed). No
`Math.*`, no `Character.*`. No `drawString`/`setFont`/`getFont`. No
`getWidth`/`getHeight`. No `drawRGB`/`fillArc`/`drawArc`/`fillTriangle`.
No `getClip*`. No `readChar`/`readUnsignedByte`/`readFully`/`skip`/
`available`. No `instanceof`.

## javalang parser patches (own code, same mechanism as GOW's `_patch_javalang`)

Three places where stock javalang 0.13.0 silently drops source
constructs. Each is verified against the parser and counted in the
regression guards:

1. **`default:` labels sharing a case group are dropped.** Game.java
   has 9 `default:` in source but only 5 in the AST; World.java 1 in
   source, 0 in the AST (the `keyPressed` digit switch groups
   `case 35…56: default: break;`). The patch records the default as a
   `"__default__"` sentinel label.
2. **A unary prefix operator before a cast is dropped** (a `Cast` has
   no prefix slot) — `-(int)x` loses the minus. The patch moves the
   operator onto the cast's inner expression.
3. **A parenthesized expression loses its prefix operators and
   selectors.** `parse_expression_3` unconditionally overwrites a
   primary's prefix_operators/selectors with its own empty lists; for a
   parenthesized expression the primary is an already-parsed inner
   node, so `(byte)(-this.menuItemAvailable[var6])` loses BOTH the
   minus and the `.menuItemAvailable[var6]` selector (4 sites in Game,
   2 in World; stock parser confirmed to lose them too). The patch
   preserves the primary's existing prefix operators (prepending ours)
   and selectors. This one was found during validation and is not in
   the GOW patch.

Other translator behaviours: javalang represents void method returns as
`return_type=None`; for-loop init is a `VariableDeclaration` (not
`LocalVariableDeclaration`); ArrayCreator dimensions live on the
creator node, not the type node; assignments are always parenthesized
(C++ `=` binds looser than `!=`/`->`); selectors are applied uniformly
in `emit_expr` for every node type (fixes `(var4 =
openRecordStore(...)).getNumRecords() <= 0` losing the method call);
low-precedence bases (assignment, binary/ternary, cast, new-expression)
are parenthesized before selectors apply (C++ forbids `new T()->m()`,
verified with a plain-class test, and `var4 = X->getNumRecords()`
would assign a bool); `getClass()` is emitted as
`JavaClass::instance()` because no translated class has a `getClass`
member; a labeled block (`label42: { … break label42; …}`, exactly one
in the code, in `Game.a_int_int_boolean_boolean_void`) is emitted as
`do { … } while (false);` with `break label42;` → `break;`, failing
loudly if an unlabeled break inside would be captured; array-initializer
elements of primitive type are explicitly cast to the element type
(C++ braced-list narrowing); prefix operators are space-joined so
`- -x` never becomes C++ `--x`; method resolution is exact → widening
→ **narrowing** (third pass added because the decompiler drops narrowing
casts — e.g. `World.afterTask` calls `setTask(int, byte)` with an
int-valued ternary `cond ? 69 : recordBlob[…]`; C++ does the same
narrowing implicitly at the call).

### The sequencing guard (a fourth translator fix, found by the compiler)

Java evaluates the operands of a binary operator left to right. C++
leaves them unsequenced (except `&&` and `||`, which sequence
left-before-right and short-circuit, matching Java), so an assignment
in one operand and a reference to the same variable in the other is
**undefined behaviour** in C++: the reference may see the old value.
The game has exactly one such site (a nearest-entity distance in
`Game`, found by `g++ -Wsequence-point`):

```java
var7 = (var13 = (byte)(var10 < 0 ? 22 - var7 : (var10 == 0 ? 0 : 22 + var7))) * var13;
```

Java squares the NEW `var13`; C++ might multiply by the OLD one
(`var13` was just declared as `int8_t var13{};` = 0). The translator
now rewrites the observed shape — left operand IS the assignment, right
operand reads the same variable, right operand does not itself assign
to it — as a comma expression that forces the Java order:

```cpp
(var7 = (((var13 = (int8_t)(...)), (var13 * var13))));
```

Every other shape (an assignment nested inside an operand, or an
assignment in the right operand read by the left) fails loudly with a
`TranslateError` instead of silently generating UB. After the fix,
`g++ -Wsequence-point` reports 0 warnings over the generated code.

Known limitation, not fixed: function-call arguments are
indeterminately sequenced in C++ (not UB, but the order may differ
from Java's left-to-right). The game does not rely on argument
evaluation order in any site found so far.

## Source corrections (counted patches in `tools/java2cpp.py`)

Vineflower occasionally infers a local-variable type that the bytecode
does not support. The renamed Java in `tribes/src/` keeps the
decompiler's text verbatim, so corrections live in `SOURCE_PATCHES` in
the translator, applied to the source text before parsing. Each patch
must match exactly once in its file or the translator fails loudly, so
a stale patch can never silently disappear and the generated code stays
reproducible from `tribes/src/` plus that list. Never edit `tribes/src/`
or `tribes/port/src/gen/` by hand.

| # | Location | Correction | Evidence |
| --- | --- | --- | --- |
| 1 | `Game.renderWorldEntities`, screen-row pixel Y | `byte var2 = -16;` → `int var2 = -16;` | Vineflower said `byte`; CFR said `int`. The bytecode proves `int`: the increment is `iinc 2, 16` (plain integer increment, no `i2b` narrowing — javac only emits `iinc` when no narrowing is needed). With `byte`, Y wraps at 128, so every entity on screen rows ≥ 8 (pixel Y ≥ 128) is drawn off-screen and invisible — the tutorial-dialog glitch where villagers only appear after the cursor scrolls them into the top half of the screen. Verified locally 2026-11-20: before the fix, the playtest frame at the in-game tutorial shows zero villagers (9 exist, alive, in-grid, fog 0); after the fix the same frame shows the line of villagers in front of the hub. |
| 2 | `ResourceLoader` ctor, after `loadMaps()` | Added `ModLoader.applyMods(this.game);` | Mod-loader hook (Seam 2/§5, design `tribes/docs/MOD-LOADER-DESIGN.md`): applies the map overrides once, after every base table is loaded. No-op when no map mods are enabled. |
| 3 | `ResourceLoader.loadSpritePack`, inside `if (var3 < 27)` | After `Image.createImage(...)`, added `Image modImage = ModLoader.spriteOverride(this.game, this.currentPackIndex, var3, ...); if (modImage != null) { ... = modImage; }` | Mod-loader hook (Seam 2): per-sprite PNG replacement. The base image is decoded first so the hook can check the replacement's dimensions against it. |
| 4 | `Game.c_byte_void` (lazy pi8 load), inside `if (var10 == var1)` | Same `spriteOverride` hook with `(this, 2, var1, ...)` after the `Image.createImage` | Mod-loader hook (Seam 2): the story/cutscene pack is loaded lazily, so it needs its own hook; the requested image lands at `spritePacks[2][0]`, so the override key is (pack 2, `var1`). |
| 5 | `Game.loadMap`, RLE fetch `if (var7 >= ...)` | Added a preceding branch: `if (ModLoader.hasModMap(this.missionIndex)) { var1 = ModLoader.modMapTile(...); } else if (var7 >= ...)` | Mod-loader hook (Seam 3): the tile source for mod maps. The translator emits the `else if` as `else { if (...) {...} }` — semantically identical; the rest of the loop body (jitter, markers, spawn registration, borders) is shared by both paths. `/ma` and rec 248 are never touched. |
| 6 | `TextLoader.loadTextFile`, after `inputStream = null` | Added `if (var1 == 0) { ModLoader.reapplyMapNames(this.game); }` | Mod-loader hook: picking a language reloads the `s` file from the base data, which would wipe the map-name patches `applyMods` made. No-op when no map mods are enabled. |

Patches 2–6 are a second kind of entry: not decompiler-error
corrections, but the wiring that connects the hand-written
`ModLoader` (`tribes/port/src/modloader.h/.cpp`) into the generated
code at the four engine seams. The same exactly-once rule applies,
and the `gen/` diff of a regen must be only the hook lines
(verified 2026-11-20: regen produced 12,409 lines, +16 vs 12,393,
and the only diffs were `Game.cpp` +4/+10, `ResourceLoader.cpp`
+1/+4, `TextLoader.cpp` +3; `classes.h` identical). `ModLoader`
is declared in `modloader.h`, which `platform.h` includes, so it
is visible to all generated code; `LIB_CLASSES`/`LIB_METHODS` in
the translator list the five `ModLoader` calls.

Open audit: the other `int8_t` locals in the generated code were eyeballed
after patch 1 and none is a pixel coordinate that can exceed 127, but no
systematic Vineflower-vs-CFR type audit has been done yet. If another
"invisible below a line" symptom appears, check the decompiler types
against the bytecode (`iinc` vs `iadd`+`i2b`) first.

## Intentional behavior changes (counted patches in `tools/java2cpp.py`)

A third kind of patch entry: deliberate changes to what the game does
— neither a decompiler-error correction nor a mod-loader hook. They
exist because the port is also a test bed (the user has no PC and tests
in Winlator), so a behavior that blocks testing may be switched off
here. Each one is a counted `SOURCE_PATCHES` entry with the same
exactly-once rule, and each is trivially reversible: delete the entry
from `tools/java2cpp.py` and rerun `python3 tools/java2cpp.py` — the
original code stays in `tribes/src/` untouched, and the patched-out
branch stays in the generated code as dead code. A behavior change
must never be hidden inside a correction patch; it gets its own entry
and its own row here.

| # | Location | Change | Why, and how it was verified |
| --- | --- | --- | --- |
| 7 | `Game.openCustomGame`, the tutorial gate | `else if (this.campaignProgress <= 0 && !this.cheatUnlockedCustom)` → `else if (false)` | The gate locks Custom Game on a fresh profile (`campaignProgress` is 0 until the tutorial is passed; `cheatUnlockedCustom` is never set). That blocks testing Custom Game mods (e.g. Green Valley) without playing a full tutorial mission first. Mirrors the 1-byte JAR unlock (`f.class` `aw()`: `ifgt` → `ifge`; see `mods/example/UNLOCKED-PROOF.md` in the research repo). Verified behaviorally 2026-11-20 with a scripted driver (`~/workspace/tribes-playtest/unlock_test.cpp`, fresh profile, menu path 14 language → 16 title → 0 main menu → action 0 → 2 Play submenu → action 13 Custom Game): patched build reaches screen 13 (difficulty), exit 0; the same driver against a regen without this patch reaches screen 21 + dialog 70 ("pass the tutorial first!"), exit 1 — the only difference between the two binaries is this one line. The tutorial itself is untouched: the existing playtest driver (FIRE-mash, New Game path) still reaches mode 1 at ~60 s on the 40x32 tutorial map (scene 3). Linux smoke test and mod test still pass. |
| 9–18 | `Game.run()` + `renderWorldEntities` / `drawEntitySprite` / `updateEffects` / `updateBuildAnims` | Render interpolation (INTENTIONAL ENHANCEMENT): logic still ticks at `tickLengthMs` (120 ms in normal play, 50 ms when `fastTick` — the original game's own rate, ~8.33 ticks/s, NOT 20/s as earlier notes claimed); `run()` renders up to ~60 fps in mode 1 by lerping camera / villager / animal / projectile positions between the previous and current tick (alpha 0..256, 17 ms pacing, picture lags logic by ≤ 1 tick). Only positions blend — animation frames, HUD values and game state never do. A move of more than 48 px in one tick is a teleport (spawn, load, building entry): it snaps instead of sliding. Effects and build animations advance once per tick (`interpAdvanceFx`, true only on the first render of a tick). The projectile grid (`tileEntityDraw`) is cleared once per tick in `interpSnapshot()` instead of being consumed at render time, so projectiles survive all renders of a tick. Mode 0 (menus/story) and `--no-interp` keep the original one-render-per-tick loop verbatim, including the scroll half-step repaint. | Verified 2026-11-21 on Linux: `g++ -fsyntax-only` 0 errors (only pre-existing unused-variable warnings); rate probe (75 s, fresh profile): logic 8.3 ticks/s before and after, renders in mode 1 8.3/s → 66/s (8 renders per 120 ms tick); playtest driver still reaches mode 1 at ~60 s on the 40x32 tutorial map; mod test PASS (29 CHECK lines, 0 FAIL); Linux smoke test PASS (136 frames, 111 colours) with and without `--no-interp`. Visual proof: scripted scroll probe (`~/workspace/tribes-playtest/scroll_probe.cpp`, holds KEY_DOWN in mode 1, dumps 48 consecutive frames): consecutive renders shift 1–3 px (mostly 2; predicted 16 px × 17 ms / 120 ms ≈ 2.3 px), 7 px across the first scroll tick and 14 px across a steady tick of a 16 px tick step; control frames with no camera motion are pixel-identical (mean abs. diff 0.00). Regen-diff proof: a translator copy without patches 9–18 produces 391 methods / 12,410 lines and `diff -r` shows only `Game.cpp` + `classes.h` differ, limited to the interpolation hooks. Known limitations: the minimap viewport rect and the minimap building-corner sprites still move at tick rate; an effect listed after an invalid effect is skipped on the tick's first render only (original `return` kept as-is); at alpha 0 the blended camera can sit up to ~33 px past the terrain buffer's ±11 px blit slack, leaving a ≤ 22 px leading-edge strip that keeps the previous frame's pixels for one render. |
| 19–21 | `Game.run()` render pacing | FPS selection (INTENTIONAL ENHANCEMENT): a `Game.interpRenderIntervalMs` field (default 17 ms) paces the interpolation renders in `run()`: `maxRenders` and the render spacing follow it instead of the hardcoded 17 ms. Launch flags `--fps 30` (33 ms, ~30 renders/s), `--fps 60` (17 ms, ~66/s, the current behaviour), `--fps auto` (the display refresh rate on Windows via `EnumDisplaySettings`, clamped to 7–33 ms; 60 on the headless Linux driver, which has no display to query). `--no-interp` keeps its meaning (1 render per tick, ~8.3/s) and overrides `--fps`. Invalid values warn and fall back to 60. GOW parallel: GOW exposes FPS as an Options-menu row; ours is a launch flag for v1, a menu row is a later step. | Verified 2026-11-21 on Linux: syntax 0 errors; rate probe at 16:9 in mode 1: `--fps 60` 65 renders/s, `--fps 30` 33/s, `--fps auto` 66/s (headless = 60), ticks 8.3/s in all three; smoke test PASS with `--fps 30`; invalid `--fps 99` falls back to 60 with a log warning. |
| 22–31 | `ResourceLoader.initScreenBuffers`, `Splash.showSplash`, `Game.paint`, `Game.scrollRedrawTerrain` | Widescreen (INTENTIONAL ENHANCEMENT): a `Game.viewWidthPx` field (default 240) sets the logical view width; height stays 320. Launch flags `--aspect original` (240x320, the current view), `--aspect 4:3` (427x320), `--aspect 16:9` (569x320). Tiles stay 22x16 — the wider view shows more columns (`screenColsTiles` = ceil(viewWidthPx/22): 11 / 20 / 26), never stretched sprites. The world buffers (1 and 3) and the menu/splash buffer 0 (which doubles as the scroll-backup copy) grow to (cols+1)x22 wide; `screenWidths[1]` follows; `paint()` blits the whole world buffer and centres the 240-wide menus/story screens with black side strips (the black fill is called with (-11, -8) because `Game.fillRect` adds (+11, +8) to its coordinates); `scrollRedrawTerrain` copies the whole buffer width. The Windows shell opens 1024x768 (4:3) or 1280x720 (16:9), or the largest fitting whole-multiple scale if the desktop is smaller, and letterboxes the logical frame with nearest-neighbour `StretchDIBits` as before. Invalid values warn and fall back to original. GOW parallel: GOW's `viewW`/`updateViewWidth` widen the camera and culling the same way; ours adapts it to the tile renderer (camera centring, scroll limits, culling and fog follow `screenColsTiles` automatically). A menu row / persisted setting is a later step. | Verified 2026-11-21 on Linux: syntax 0 errors; smoke test PASS at all three aspects (240x320 / 427x320 / 569x320, 136 frames each); playtest reaches mode 1 at ~60 s at 4:3 and 16:9 (cols=20 / 26, cursor centred); mod test PASS; rate probe at 16:9 ~66 renders/s, ticks 8.3/s; visible columns counted in dumped frames (full 22 px columns: 10 / 19 / 25; engine `screenColsTiles`: 11 / 20 / 26); menu logo + panel pixel-identical to the original-aspect frame at the centred offset (dx=164, 100.0%); original-aspect regression: a dumped mode-1 frame at --aspect original is pixel-identical (0/76800 px differ) to the same scene from a regen without patches 19–31; the menu frame logo is 100.0% and the panel 99.7% identical (remaining diffs are the animated title backdrop: clouds/water); regen-diff proof: a translator copy without patches 19–31 produces 401 methods / 12,610 lines and `diff -r` shows only `Game.cpp`, `ResourceLoader.cpp`, `Splash.cpp`, `classes.h` differ, limited to the widescreen/FPS hooks. |

To restore the original gate: delete patch 7 from `SOURCE_PATCHES` in
`tools/java2cpp.py` and rerun `python3 tools/java2cpp.py`. No other
file needs to change.

To restore the old 1-render-per-tick behaviour without deleting anything:
launch with `--no-interp` (interpolation is on by default). To remove
interpolation entirely: delete patches 9–18 from `SOURCE_PATCHES` in
`tools/java2cpp.py` and rerun `python3 tools/java2cpp.py`. No other
file needs to change.

To restore the original 240x320 view without deleting anything: launch
with `--aspect original` (the default). To remove widescreen entirely:
delete patches 22–31 from `SOURCE_PATCHES` in `tools/java2cpp.py` and
rerun `python3 tools/java2cpp.py`. To remove FPS selection entirely:
delete patches 19–21 (the interval field defaults to 17 ms, i.e. 60 fps,
when the patches are gone — the `--fps` flag then has nothing to set, so
remove it from `main.cpp` / `tools/linux_smoke.cpp` too). No other file
needs to change.

## Files

| Path | What it is |
| --- | --- |
| `tools/java2cpp.py` | The translator (~1,600 lines). Two passes: symbol tables for all 7 classes, then emission. Emits `tribes/port/src/gen/classes.h` + one `.cpp` per class. |
| `tribes/port/src/jrt.h` / `jrt.cpp` | `JavaException`, `Arr<T>` + `newArr`/`newArr2`/`newArr3`/`arraycopy`, `String`, `StringBuffer`, `jlang::abs/max/min/currentTimeMillis/gc`, `Random` (JDK LCG), `InputStream`/`ByteArrayInputStream`/`FileInputStream`/`OutputStream`/`ByteArrayOutputStream`/`DataInputStream`/`DataOutputStream`, `Runnable`/`Thread`, `JavaClass`. |
| `tribes/port/src/gfx.h` / `gfx.cpp` | `Image` + `Surface` (the `Graphics` stand-in). |
| `tribes/port/src/engine.h` / `engine.cpp` | `Canvas`, `MIDlet`, `Display`, `RecordStore`, `PlayerListener`, `VolumeControl`, `Player`, `Manager::createPlayer`. |
| `tribes/port/src/platform.h` / `platform.cpp` | `Platform` (dataDir/saveDir/userDir, `exeDir()`), `readDataFile`, `JavaClass::getResourceAsStream`, the MIDI backend (`WinMidiPlayer`, SMF 0/1 parser + tempo map + sequencer thread on `midiOut`; silent `Player` on non-Windows). |
| `tribes/port/src/display.h` / `display.cpp` | Win32 window + GDI `StretchDIBits` presentation (letterboxed, 3:4). |
| `tribes/port/src/input.h` / `input.cpp` | Keyboard → MIDP key codes (arrows/WASD move, Enter/Space/5 fire, digits, F1/Q left softkey, Esc/F2 right softkey). |
| `tribes/port/src/main.cpp` | Win32 shell: argument parsing (`--data`/`--saves`/`--windowed`/`--fullscreen`/`--scale`/`--dump`/`--bench`/`--trace`), message pump, headless modes. |
| `tribes/port/src/launcher/launcher.cpp` | The BYO-JAR launcher (file picker, fingerprint validation, ZIP extraction via `puff`, Play button). No self-update (future work). |
| `tribes/port/third_party/stb/` | `stb_image.h` v2.30 (pinned commit `2c980bb59875b0d32144a71867fbdebb2f77cd20`), `stb_image_impl.cpp`, `PROVENANCE.md`. |
| `tribes/port/third_party/puff/` | `puff.c` + `puff.h` v2.3 (21 Jan 2013, madler/zlib), `LICENSE.txt` (zlib licence), `PROVENANCE.md`. `puff()` is a raw-deflate decoder — exactly what ZIP DEFLATE entries are, so the launcher uses it directly. |
| `tribes/port/CMakeLists.txt`, `build.bat`, `build_dist.bat`, `check_dist.ps1`, `vcvars.bat` | MSVC build (C++17, static CRT, `winmm.lib`), dist assembly, dist verification (64-bit PE check + `--dump` non-blank + `--bench` frames > 0). |
| `tools/linux_smoke.cpp`, `tools/run_linux_smoke.sh` | Headless Linux driver for the portable core (see below). |

## Validation (2026-11-20, Linux)

- **Translator run:** completes without errors. **391 methods** translated
  (Game 180, World 176, MidiPlayer 6, TextLoader 11, ResourceLoader 9,
  Splash 4, TribesMIDlet 5) + 6 constructors. **12,393 generated lines**
  (13,425 total incl. header): `classes.h` 1,025, `Game.cpp` 6,486,
  `World.cpp` 5,193, `MidiPlayer.cpp` 82, `TextLoader.cpp` 307,
  `ResourceLoader.cpp` 241, `Splash.cpp` 36, `TribesMIDlet.cpp` 55.
  Cast counts: Game 317, World 456, MidiPlayer 1, TextLoader 19,
  ResourceLoader 8, Splash 0, TribesMIDlet 0. String concats: Game 8,
  TextLoader 4, Splash 1, others 0.
- **Count checks PASS:** per-class method counts match Java exactly
  (391 total; constructors 6; TribesMIDlet has 0 in both). Per-class
  field counts match Java exactly (Game 325, World 187, MidiPlayer 2,
  TextLoader 52, ResourceLoader 5, Splash 2, TribesMIDlet 3 — **576
  total**).
- **Spot check PASS:** `World::checkWinLose` (`gen/World.cpp:1215`)
  matches `World.java:6994` exactly in logic — win (enemy buildings
  ≤ 0 AND rec279 bit 1 → timer 1), lose (player population ≤ 0 OR
  `a[8506] ≤ 0` AND rec279 bit 2 → timer 50), the `case 2`/`case 5`
  switch, and the q=5 win anomaly (case 5: timer=1, return) reproduced,
  not fixed. (Java `else if` becomes C++ `else { if …}` — semantically
  identical.)
- **Syntax check PASS:** `g++ -std=c++17 -Wall -fsyntax-only` over
  `jrt.cpp`, `gfx.cpp`, `engine.cpp`, `platform.cpp`, all 7 generated
  `.cpp` files, `stb_image_impl.cpp`, and `linux_smoke.cpp` — **zero
  errors**. Only benign `-Wunused-parameter` warnings in the hand-written
  base-class stubs and dead decompiler locals, plus 0
  `-Wsequence-point` warnings after the sequencing fix.
- **Full compile + link PASS:** all objects linked into a test binary
  (1,344,128 bytes). This validates the runtime + generated code
  together; it does not run the game.
- **Linux smoke test PASS** (`tools/run_linux_smoke.sh`, the strongest
  validation available on this VM): the game starts against the local
  extracted JAR contents, the splash plays (1s + 2s + 2s), the game
  reaches the main menu (`screenId=14`, `mode=0`) at ~6 s, the loop
  runs (136 frames presented in 12 s), and the dumped 240×320 frame is
  non-blank (111 distinct sampled colours). Viewed, the frame is the
  real language-selection screen — "PREHISTORIC TRIBES" title, five
  languages, the world (trees, huts, villagers, water) behind it — so
  sprite loading, glyph text rendering, and UI panels all work. With
  `--play`, sending FIRE at the menu selects English and advances to
  the "PLAY MUSIC?" prompt (`screenId=16`), so the input path and menu
  navigation work too. The smoke test does not yet play a full game to
  a win/lose verdict.

## What the Windows shell still needs / what is unverified

- **The Windows build has never been compiled.** There is no MSVC on
  this Linux VM, so `main.cpp`, `display.cpp`, `input.cpp`, and
  `launcher.cpp` (all Win32-only) have never been compiled — only read
  back for review. They need a Windows runner in CI (`windows-latest`):
  `build.bat` → `check_dist.ps1` (`--dump` a non-blank frame,
  `--bench` frames > 0). The CI workflow itself is not written yet.
- **No Windows playtest.** The headless smoke tests prove the loop
  runs and frames render; they do not prove the game is playable
  (input, saves, audio) on Windows. Real playtesting needs a PC.
- **MIDI on Windows is untested.** The `midiOut` sequencer
  (`WinMidiPlayer`) is written but has never run; the Linux smoke test
  uses the silent stub. If MIDI fails on Windows, the game should still
  run (the player is best-effort) — but that has not been proven.
- **The launcher has never run.** Its ZIP parsing and `puff`
  extraction are written from the ZIP spec and the vendored `puff.c`,
  but no JAR has been imported through it yet.
- **No full-game playthrough.** The smoke test reaches the menu and
  the "PLAY MUSIC?" prompt. A scripted playthrough (start a campaign
  mission, run ticks, reach a win/lose verdict) is future work — it
  needs the menu flow and the in-game key sequence worked out from the
  renamed Java.
- **The 21 scripted mission events** (records 288/291) and the other
  open research items in `ROADMAP.md` are untouched by this phase.

## Rules for future work on the translator

- Never hand-edit `tribes/port/src/gen/` — regenerate via
  `python3 tools/java2cpp.py`. Never hand-edit `tribes/src/` — fix
  `names.map` or the renamer instead.
- No game data (JAR, extracted assets) in the staging dir. The Linux
  smoke test reads the extracted JAR from the research working dir
  (`~/workspace/j2me-prehistoric-tribes/extracted/`), never from the
  port repo.
- The translator fails loudly on constructs outside its assumed subset
  instead of silently mishandling them. If a new construct appears
  (e.g. a future rename introduces one), add it to the subset or to the
  fail-loudly list — do not work around it in the generated code.
- The q=5 win anomaly is reproduced, not fixed. Save format order
  (`loadSlot`/`saveSlot`, `t1`/`t2`, `t0` settings) must be preserved
  exactly.
