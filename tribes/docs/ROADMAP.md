# Roadmap

Master record for the Prehistoric Tribes C++ port.

## Target

- **Game:** Prehistoric Tribes, THQ Wireless, developed by Gear Games, Ltd., © 2008.
- **Build:** MIDlet 0.1.28, MIDP-2.0, CLDC-1.0, 240x320, JAR 487 KB, main class `tribes`.
- **Decompiled with CFR 0.152:** 13,418 lines, 7 classes — `tribes` (78, MIDlet), `f` (6,680, main game Canvas/loop/save), `b` (5,805, world logic/AI), `e` (280, resource loader), `c` (404, text loader), `d` (53, splash), `a` (118, MIDI player). Names obfuscated to `a`–`f`.
- **Research repo:** [blueberry-prehistribes_code](https://github.com/Keitaro0104/blueberry-prehistribes_code) (analysis only — no JAR, no extracted assets, no raw decompiled code there).

## Phases

- **Phase 0 — unpack / decompile / identify (DONE).** JAR extracted (43 files), decompiled with CFR, system dissection written up (see `CLASS_MAP.md`, `ASSET_FORMATS.md`).
- **Phase 1 — read-through + rename (DONE 2026-11-20).** Read a method at a time, name only what call sites prove, log evidence in `CLASS_MAP.md`. Output: `tribes/src/` (renamed Java) + compile check against MIDP-2.0/CLDC-1.0 stub JARs. Result: `names.map` has 931 human entries (7 classes, 576 fields — every field in the game — 348 methods) plus 30 mechanical auto names for unnamed single-letter Game overloads; the true strict-collision count is 580 (466 fields + 114 methods), not the 542 first measured (the inventory rendered multi-dimensional array types lossily). Compile check: **0 errors**. Spot-check: `checkWinLose` and `openCustomGame` semantically identical to Vineflower's `ak()`/`aw()` (verified with a class-scoped de-normalization). Confidence: 324 Certain / 571 Likely / 33 Structural / 3 Unknown. Full detail in `tribes/docs/RENAME-REPORT.md`.
- **Phase 2 — asset formats (DONE for the port).** Balance data in file `/a` (312 records); `ma` RLE map format proven from bytecode (10/12 maps consume their piece exactly 100%); 45 PNG sprites in `pi0`/`pi8`/`pi9`; 5 languages; saves in RecordStores `t0`/`t1`/`t2`. See `ASSET_FORMATS.md`.
- **Phase 3 — PC port (DONE 2026-11-20, except Windows CI verification).** Mechanical translation Java → C++ (`tools/java2cpp.py`, own implementation): 391 methods, 12,393 generated lines, method and field counts match Java exactly (391 methods + 6 ctors, 576 fields). Hand-written runtime (`tribes/port/src/`): `jrt` (Arr/String/Random JDK LCG/DataInputStream/Thread), software `Surface` + `Image` (the `Graphics` stand-in), `engine` (Canvas/MIDlet/Display/RecordStore/Player), `platform` (data/save dirs, `midiOut` MIDI on Windows, silent stub elsewhere). Game-thread model (the loop lives in translated `Game.run()`, not a shell `runFrame()`). Windows shell (`main.cpp` WinMain + message pump + `--dump`/`--bench`/`--trace`, `display.cpp` GDI `StretchDIBits`, `input.cpp` keyboard → MIDP keys) + launcher (BYO-JAR: fingerprint validation + ZIP extraction via `puff` + Play) + MSVC build files (`CMakeLists.txt`, `build.bat`, `build_dist.bat`, `check_dist.ps1`, `vcvars.bat`). **Linux smoke test PASSES** (`tools/run_linux_smoke.sh`): the game starts, the splash plays, the menu renders (language screen, viewed — sprites, glyph text, UI panels all work), FIRE advances to the "PLAY MUSIC?" prompt. Full detail in `tribes/docs/TRANSLATOR-NOTES.md`. **Not verified:** the Windows build has never been compiled (no MSVC on the Linux VM) — CI on a Windows runner is still needed (`build.bat` → `check_dist.ps1`); the launcher and the `midiOut` sequencer have never run; no full-game playthrough to a win/lose verdict yet.
- **Phase 4 — Android fork (NOT STARTED).** Same C++ core under SDL2 + thin Java layer, on-screen virtual buttons (original scheme), saves/logs in a storage folder. Follows the SupaRj layout.

## Open questions

- 21 scripted mission events (records 288/291) — handlers `b.v()`/`b.u()`, not yet decoded.
- Mission 5 win anomaly: winning q=5 falls to the else branch — back to menu, no advance, no save. Possibly an original bug. Reproduce it, do not silently fix it.
- Unknown `/a` records: 273–278, 283, 296–300, 305, 306, 311 (311 is the largest, 687 bytes).
- Tile visual meanings (which ID is grass / water / forest / rock).
- Spawn-object types 1–5 placed by tiles 175–179.
- Weapon-name mapping.
- `rec 138` rows 0, 1, 2, 3, 15 and types 23–25.
- `rec 27` flag bit names; `rec 41` task categories.
