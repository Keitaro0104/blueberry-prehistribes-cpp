# Prehistoric Tribes — Native C++ Port

A native C++ port of *Prehistoric Tribes*, the 2008 real-time strategy game for J2ME (mobile Java) phones. Rebuilt from the original game's own decompiled code, not emulated.

**Status: skeleton.** The repository structure and documentation are in place; the port itself has not started yet. See `tribes/docs/ROADMAP.md` for the plan.

| Original | This port | You supply |
| --- | --- | --- |
| THQ Wireless / Gear Games, 2008, 240x320, MIDlet 0.1.28 | Windows C++ port (Android later) | `prehistoric_tribes_240x320-*.jar` (your own copy) |

## Download and play

**The game is not included.** It belongs to its rights holders, so you need your own copy of the `.jar`. The launcher unpacks it for you. *(Launcher not built yet.)*

## How it is made

The game is plain JVM bytecode with minifier-style obfuscation, so a decompiler recovers close-to-original Java directly. From there the code is read through and renamed, and the game's custom binary formats are parsed and verified byte for byte.

The port is translated mechanically: `tools/java2cpp.py` turns the renamed Java into C++, and a hand-written engine and platform layer sit underneath. Patches are applied during translation, so the renamed Java stays a plain decompile.

The method follows [samioan/gow-betrayal-remastered](https://github.com/samioan/gow-betrayal-remastered) (native Windows C++ port of the J2ME game *God of War: Betrayal*) and its Android fork [SupaRj/gow-betrayal-remastered-Android-Native-port](https://github.com/SupaRj/gow-betrayal-remastered-Android-Native-port). Their code is not copied (neither project carries a licence file); only the method is followed.

## Repository layout

| Path | Contents |
| --- | --- |
| `tribes/decompiled/` | Raw decompiler output |
| `tribes/src/` | Renamed, compile-checked Java (generated from `tribes/docs/names.map`) |
| `tribes/docs/` | Roadmaps, class map, asset formats and `names.map` |
| `tribes/port/` | The C++ port, launcher and packaging scripts |
| `tools/` | Extraction, decompilation, renaming, parsing and translation scripts |
| `android/` | Android port (later, per the SupaRj layout) |
| `roms/`, `tribes/extracted/` | Your original jar and its contents (gitignored, never commit) |

## Building from source

Not yet possible — the port code does not exist yet. When it does, the build will be: Windows, CMake + Ninja + MSVC; `tribes/port/build.bat` builds the game, `build_dist.bat` builds the shipping package, `check_dist.ps1` verifies it contains no game data.

## Legal

This project is not affiliated with, endorsed by, or supported by THQ Wireless, Gear Games, or the game's original developers and publishers. See `NOTICE.md`.
