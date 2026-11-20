# NOTICE

This project is a from-scratch, behavioural re-implementation of *Prehistoric Tribes* (THQ Wireless / Gear Games, 2008), written by reading the original game's own decompiled bytecode. It is not affiliated with, endorsed by, or supported by THQ Wireless, Gear Games, or the game's original developers and publishers.

- No script, image, sound, string table or byte of the original `.jar` release is committed here, and none is distributed with any release.
- The Java under `tribes/decompiled/` and `tribes/src/` is decompiler output kept as the record of the reverse engineering, and the released executables contain code derived from it. Neither can run without the original game data, which you supply yourself.
- *Prehistoric Tribes* is a trademark of its respective owners, used here for identification and documentation of the decompilation only.

## Licence of this project's own code

To be decided by the project owner. Until a licence file is added, the code in this repository is all rights reserved by default.

## Method reference

The porting method (decompile → rename → mechanical translate → hand-written runtime, "bring your own JAR") follows samioan/gow-betrayal-remastered and SupaRj/gow-betrayal-remastered-Android-Native-port. Neither project carries a licence file, so no code is copied from them; only the method is followed. Third-party components used by the port (stb_image, puff, Vineflower, javalang) keep their own licences; see their PROVENANCE files when they are vendored.
