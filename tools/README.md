# Tools

Extraction, decompilation, renaming, parsing and translation scripts for the port. All scripts print derived data to stdout only — never commit parser output or game data.

## Pipeline

```
python tools/extract_jar.py        # unpack your jar from roms/
python tools/decompile.py          # decompile -> tribes/decompiled/
python tools/rename_tribes.py      # renamed Java -> tribes/src + compile check
python tools/java2cpp.py           # -> tribes/port/src/gen
tribes/port/build.bat              # builds tribes/port/build/Release/tribes.exe
tools/run_linux_smoke.sh           # headless Linux smoke test (no Windows needed)
```

Regen rule: `python tools/rename_tribes.py && python tools/java2cpp.py`, then rebuild. **Never edit `tribes/src/` or `tribes/port/src/gen/` by hand.**

## Scripts

| Script | Purpose | Status |
| --- | --- | --- |
| `rename_tribes.py` | Class-file-level rename from `tribes/docs/names.map` (via `ch_rename/TribesRenamer.java`) + compile check against MIDP stub JARs. Auto-generates mechanical names for colliding members the human map does not cover yet (see below). | Done |
| `ch_rename/TribesRenamer.java` | Vineflower `IIdentifierRenamer` implementation, driven by `names.map`. Own code, written for this port. | Done |
| `extract_jar.py` | Unpack the user's JAR into `tribes/extracted/` (gitignored). A JAR is a plain zip. | Planned |
| `decompile.py` | Run Vineflower 1.12.0 over the extracted classes (fetched via curl, gitignored). | Planned |
| `java2cpp.py` | Mechanical Java → C++ translator (own implementation following the javalang pattern). Two passes (symbol tables, then emission); emits `tribes/port/src/gen/classes.h` + one `.cpp` per class. Handles the 13 platform-override methods, the three javalang parser patches (dropped `default:` labels, dropped prefix-before-cast, dropped prefix/selectors on parenthesized expressions), and the binary-operator sequencing guard (comma rewrite for `(V = X) OP R`). See `tribes/docs/TRANSLATOR-NOTES.md`. | Done |
| `linux_smoke.cpp` + `run_linux_smoke.sh` | Headless Linux driver for the portable core (jrt/gfx/engine/platform/gen, no Win32 shell). Starts the game against the local extracted JAR contents, drives the repaint loop, dumps a BMP frame, reports screen/mode. Proves the game initializes, the loop runs, and the menu renders — the only functional validation possible without Windows. | Done |
| `mod_test.cpp` + `run_mod_test.sh` | Deterministic mod-loader test driver (no game thread, no splash sleeps). Builds the game directly on the driver thread and mirrors the level-start flow (`missionIndex = q; resetWorldState(); loadMap(); placeCameraStartPositions();`). Proves, with the green-valley + texture-test + broken-mod folders it sets up under `/tmp/tribes-build/test-mods`: the map loads as 40x32 with the exact Green Valley tile counts, the spawn codes, the `/a` patches (rec 149/150/279), the map-name patch (s[91]), the setup remap (35 villagers in bounds), the texture override (FNV differs from the green-valley-only baseline, solid-magenta pixels), the broken mod is skipped with a MOD-REJECT line, and a base level (q=1, 64x48) still loads untouched. | Done (phase 1) |
| `tribes_port_patches.py` | Counted patches applied inside the translator; a patch that matches the wrong number of times fails loudly. | Planned |
| `parse_ma.py`, `parse_a_records.py`, `parse_sprites.py` | Asset-format parsers with exact-consumption asserts (formalized from the research repo's decoders). | Planned |
| `outline.py` | Method list with line ranges, sizes, callers/callees. | Planned |

## Auto-generated names

The raw decompile cannot compile as Java source: this obfuscator reuses one
letter for many members in one class (185 collision groups, 542 members that
must be renamed; plus overload groups whose call sites go ambiguous when
arguments widen, e.g. `byte` → `int`). `rename_tribes.py` therefore builds
the effective map as human `names.map` plus mechanical type-derived names
for colliding members the human map does not cover yet, written to
`tribes/docs/names-auto.map` (generated — do not edit by hand). As the human
map grows, auto entries disappear. The counts are reported on every run.

## MIDP stubs

The compile check needs MIDP-2.0/CLDC-1.0 stub JARs. They are not committed
(`tools/midp-stubs/` is gitignored). For this port they were built from
hand-written stub sources (MIDP 2.0 public API, only the classes and members
the game uses: `lcdui` Canvas/Display/Graphics/Image, `midlet` MIDlet,
`rms` RecordStore, `media` Manager/Player/PlayerListener,
`media.control` VolumeControl) — see the research working dir,
`j2me-prehistoric-tribes/tools/midp-stubs-src/`.

## Toolchain

- Python 3.10+
- Java 17+ (for the decompiler and the compile check)
- `javalang` (`pip install javalang`) for the translator
- CMake + Ninja + MSVC for the Windows build (found via `vswhere`)
