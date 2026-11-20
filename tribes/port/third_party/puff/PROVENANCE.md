# Provenance

- `puff.c` / `puff.h` — puff version 2.3, 21 Jan 2013, from
  https://github.com/madler/zlib (contrib/puff)
- Licence: zlib Licence, (C) Mark Adler. See `LICENSE.txt`.
- Used unmodified, raw DEFLATE only (exactly zip method 8). Chosen over
  full zlib because the launcher only ever decompresses.
