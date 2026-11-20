# Asset formats

Binary formats of the original JAR, verified by the analysis scripts in the research repo. Parsers must reproduce the game's own loaders and assert exact consumption.

## `/a` — balance data (18,842 bytes)

312 records in `[LE u16 length][blob]` segments, plus trailing groups of 285 BE u16 values. Parsing ends exactly at 18,842 bytes.

- Record 138: building table, 24 rows × 26 cols. Types 0–11 buildings, 20–22 traps, max level 3. Max HP = `rec138[(7+level−1)*26+type]` (Hut 25/40/90). Construction pays cost rows 13–15 in full; upgrade/repair pays rows 10–12. Hut build cost 5/10/0, upgrade cost 15/5/0 (resource order wood/stone/bones = stocks [2]/[3]/[4] is inferred, not proven). Row 16 = construction-site proxy type. Rows 21–23 = per-level mood bonus.
- Record 80: worker capacity per building (Hut = 3).
- Records 97/98: villager HP by age; max HP 99 until age 49, 0 at age 100.
- Record 102: melee damage table `[1,1,2,3,5,3,0,0,0,0]`; record 124: throwing bonus `[1,1,1,2,2,3,3,4,6,8]`; record 159: projectile gravity `[25,30,40]`.
- Records 214/215/216: wave interval base, difficulty modifier, level durations (×500 ticks).
- Records 241/243–246/248: AI tick interval, AI build order, level → map.
- Record 249: custom map list `[0,2,3,4,5,7,8,9,10,11]`.
- Record 261: spawn remap table (villagers + buildings per level).
- Record 279: win/lose flags per level q: q0=2, q1=3, q2=2, q3=4, q4=3, q5=2, q6=3.
- Record 197: `ma` RLE control table (see below).
- Records 288/291: 21 scripted mission events (not yet decoded).

## `ma` — maps (RLE)

Control table in `/a` segment 197: run lengths T = [4,4,2,2,2,2], run values V = [3,0,43,6,3,0]. Byte < 250 = literal tile; byte 250+k repeats V[k] for T[k] tiles. Tiles with base ≤ 8 get +0..2 random jitter at load. 10/12 maps consume their piece exactly 100%.

Levels q=0–11: q0 outside 40×32, q1 outside 64×48, q2 revenge 64×80, q3 escape 64×64, q4 wastelands 64×96, q5 a new home 64×96, q6 a new home 96×96, q7 "the end" 96×64, q8 desert pass 80×80, q9 forest belt 96×48, q10 slaughter 48×96, q11 plot of land 64×64. Campaign q0–q6, q7 "the end", q8–q11 custom-only.

## `pi0` / `pi8` / `pi9` — sprites (concatenated PNGs)

Each file is a stream of PNGs, each prefixed by a 2-byte big-endian length, terminated by 0xFF. 27 + 12 + 6 = 45 PNGs. `pi8` is story/cutscene art, loaded lazily.

## `sa` — music (26,616 bytes)

5 MIDI songs concatenated (8,014 / 6,315 / 5,648 / 3,405 / 3,234 bytes), random choice at runtime.

## `0/s`, `1/s`, ... — text

Per-language string tables: `s` (229 UI strings), `t` (72 help), `d0` (236 story pieces incl. the tutorial script at indices 18–84). Languages: 0 English, 1 French, 2 Italian, 3 German, 4 Spanish.

## Saves — RecordStores `t0` / `t1` / `t2`

- `t0`: settings + campaign progress (incl. highest unlocked level).
- `t1`: campaign save. `t2`: custom save.
- The whole world state is written in exact field order; overwrite is delete-then-create. No per-second autosave.
