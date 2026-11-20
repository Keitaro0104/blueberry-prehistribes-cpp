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

## Still unknown

- Most methods in `f` and `b` are not yet read through; see `ROADMAP.md` open questions.
