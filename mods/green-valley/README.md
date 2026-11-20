# Green Valley — example mod (phase 1)

This is our own example content, so it lives in the repo (unlike the
original game files, which are never committed — the port stays
bring-your-own-JAR).

## What it does

Replaces the custom level q=11 ("plot of land") with the Green Valley
map (40x32), loaded at runtime from `maps/green-valley.json`. No JAR
repack, no editing of `data/`: drop this folder into the `mods/`
folder next to `tribes.exe` (Windows) or pass `--mods` (Linux smoke
test) and start a Custom Game on slot 11.

## Files

- `mod.json` — manifest, format v0.2.
- `maps/green-valley.json` — the map. Tiles are identical to the
  v0.1 example in the research repo
  (`blueberry-prehistribes_code`, `mods/example/example-map.json`),
  which was proven by the J2ME repack proof: the encoder reproduces
  the original `ma` streams byte-for-byte, and the map was played in
  freej2me. The only changes for v0.2 are `format_version: "0.2"` and
  the new `slot_q: 11` field (the level this map replaces). The
  `setup` field is omitted on purpose: the loader reuses q=11's setup
  table, scaled to 40x32 and nudged so nothing spawns out of bounds,
  with the camera start at the centroid of the player villagers —
  exactly what the repack did.

## How to test it (Windows)

1. Build the dist and copy this folder to `<dist>\mods\green-valley\`.
2. Run `tribes-launcher.exe`, import the JAR, Play.
3. New Game → Custom Game → pick **Green Valley** (slot 11).
4. Check `tribes-log.txt` next to the exe: it should contain
   `MOD: loaded green-valley`, `MOD: map green-valley -> slot q=11
   (replace) 40x32` and the `rec149`/`rec150`/`rec279` patch lines.
