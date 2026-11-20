# Tools

Extraction, decompilation, renaming, parsing and translation scripts for the port. All scripts print derived data to stdout only — never commit parser output or game data.

## Pipeline

```
python tools/extract_jar.py        # unpack your jar from roms/
python tools/decompile.py          # decompile -> tribes/decompiled/
python tools/rename_tribes.py      # renamed Java -> tribes/src + compile check
python tools/java2cpp.py           # -> tribes/port/src/gen
tribes/port/build.bat              # builds tribes/port/build/tribes_port.exe
```

Regen rule: `python tools/rename_tribes.py && python tools/java2cpp.py`, then rebuild. **Never edit `tribes/src/` or `tribes/port/src/gen/` by hand.**

## Scripts (planned)

| Script | Purpose |
| --- | --- |
| `extract_jar.py` | Unpack the user's JAR into `tribes/extracted/` (gitignored). A JAR is a plain zip. |
| `decompile.py` | Run Vineflower 1.12.0 over the extracted classes (fetched via curl, gitignored). First compare its output with the existing CFR 0.152 decompile. |
| `rename_tribes.py` | Class-file-level rename from `tribes/docs/names.map` + compile check against MIDP-2.0/CLDC-1.0 stub JARs. |
| `java2cpp.py` | Mechanical Java → C++ translator (own implementation following the javalang pattern). |
| `tribes_port_patches.py` | Counted patches applied inside the translator; a patch that matches the wrong number of times fails loudly. |
| `parse_ma.py`, `parse_a_records.py`, `parse_sprites.py` | Asset-format parsers with exact-consumption asserts (formalized from the research repo's decoders). |
| `outline.py` | Method list with line ranges, sizes, callers/callees. |

## Toolchain

- Python 3.10+
- Java 17+ (for the decompiler and the compile check)
- `javalang` (`pip install javalang`) for the translator
- CMake + Ninja + MSVC for the Windows build (found via `vswhere`)
