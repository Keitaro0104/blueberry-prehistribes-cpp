#!/bin/bash
# run_mod_test.sh — build and run the deterministic mod-loader test.
#
# Sets up two mods folders under /tmp/tribes-build and runs the
# mod_test driver against each:
#   baseline: only the green-valley example mod (no texture override).
#   full:     green-valley + texture-test (solid-magenta pi0[5]) +
#             broken-mod (invalid JSON, must be skipped with MOD-REJECT).
#
# Proves: (a) the loader logs the mod + the map override, (b) q=11
# loads as the 40x32 Green Valley map with the exact tile counts,
# (c) the texture override changes the loaded image (FNV differs from
# the baseline and every pixel is the magenta override), (d) the
# broken mod is skipped with a log line and the game still runs.
# The driver itself also checks the /a patches (rec 149/150/279), the
# map-name patch (s[91]), the setup remap (35 villagers in bounds),
# and that a base level (q=1, 64x48) still loads untouched.

set -u
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PORT="$ROOT/tribes/port"
BUILD_DIR="${BUILD_DIR:-/tmp/tribes-build}"
DATA_DIR="${DATA_DIR:-$HOME/workspace/j2me-prehistoric-tribes/extracted}"

BASE_MODS="$BUILD_DIR/test-mods-baseline"
FULL_MODS="$BUILD_DIR/test-mods"
BASE_SAVES="$BUILD_DIR/mod-test-saves-baseline"
FULL_SAVES="$BUILD_DIR/mod-test-saves"

fail() { echo "MOD TEST FAIL: $*" >&2; exit 1; }

echo "== setting up test mods =="
rm -rf "$BASE_MODS" "$FULL_MODS" "$BASE_SAVES" "$FULL_SAVES"
mkdir -p "$BASE_MODS" "$FULL_MODS" "$BASE_SAVES" "$FULL_SAVES"
cp -r "$ROOT/mods/green-valley" "$BASE_MODS/green-valley"
cp -r "$ROOT/mods/green-valley" "$FULL_MODS/green-valley"

# texture-test: replaces pi0[5] (76x111) with a solid-magenta PNG.
mkdir -p "$FULL_MODS/texture-test/sprites"
cat > "$FULL_MODS/texture-test/mod.json" <<'EOF'
{"format_version": "0.2", "game": "prehistoric-tribes",
 "id": "texture-test", "name": "Texture Test",
 "textures": ["sprites/pi0-05.png"]}
EOF
python3 - "$FULL_MODS/texture-test/sprites/pi0-05.png" <<'EOF'
import struct, sys, zlib
w, h = 76, 111
raw = b"".join(b"\x00" + b"\xff\x00\xff\xff" * w for _ in range(h))
def chunk(tag, data):
    c = struct.pack(">I", len(data)) + tag + data
    return c + struct.pack(">I", zlib.crc32(tag + data) & 0xFFFFFFFF)
png = (b"\x89PNG\r\n\x1a\n"
       + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 6, 0, 0, 0))
       + chunk(b"IDAT", zlib.compress(raw))
       + chunk(b"IEND", b""))
open(sys.argv[1], "wb").write(png)
print("wrote magenta pi0-05.png:", len(png), "bytes")
EOF

# broken-mod: invalid JSON — must be skipped, never block startup.
mkdir -p "$FULL_MODS/broken-mod"
printf '{ this is not valid json' > "$FULL_MODS/broken-mod/mod.json"

echo "== compiling the mod test driver =="
g++ -std=c++17 -O1 -Wall -Wno-unused-parameter \
  -I"$PORT/src" -I"$PORT/third_party/stb" \
  "$PORT/src/jrt.cpp" "$PORT/src/gfx.cpp" "$PORT/src/engine.cpp" \
  "$PORT/src/platform.cpp" "$PORT/src/crashlog.cpp" "$PORT/src/modloader.cpp" \
  "$PORT/src/gen/Game.cpp" \
  "$PORT/src/gen/World.cpp" "$PORT/src/gen/MidiPlayer.cpp" \
  "$PORT/src/gen/TextLoader.cpp" "$PORT/src/gen/ResourceLoader.cpp" \
  "$PORT/src/gen/Splash.cpp" "$PORT/src/gen/TribesMIDlet.cpp" \
  "$PORT/third_party/stb/stb_image_impl.cpp" \
  "$ROOT/tools/mod_test.cpp" \
  -o "$BUILD_DIR/mod_test" -lpthread || fail "compile failed"

echo "== baseline run (green-valley only) =="
"$BUILD_DIR/mod_test" --data "$DATA_DIR" --saves "$BASE_SAVES" \
  --mods "$BASE_MODS" > "$BUILD_DIR/mod-test-baseline.txt" 2>&1
BASE_RC=$?
cat "$BUILD_DIR/mod-test-baseline.txt"
[ "$BASE_RC" -eq 0 ] || fail "baseline run exited $BASE_RC"
grep -q "CHECK .* FAIL" "$BUILD_DIR/mod-test-baseline.txt" &&
  fail "baseline run has a failing CHECK"
BASE_FNV=$(grep "^TEXTURE-FNV" "$BUILD_DIR/mod-test-baseline.txt" |
           awk '{print $3}')
[ -n "$BASE_FNV" ] || fail "baseline run printed no TEXTURE-FNV"
echo "baseline pi0[5] FNV: $BASE_FNV"

echo "== full run (green-valley + texture-test + broken-mod) =="
"$BUILD_DIR/mod_test" --data "$DATA_DIR" --saves "$FULL_SAVES" \
  --mods "$FULL_MODS" > "$BUILD_DIR/mod-test-full.txt" 2>&1
FULL_RC=$?
cat "$BUILD_DIR/mod-test-full.txt"
[ "$FULL_RC" -eq 0 ] || fail "full run exited $FULL_RC"
grep -q "CHECK .* FAIL" "$BUILD_DIR/mod-test-full.txt" &&
  fail "full run has a failing CHECK"
FULL_FNV=$(grep "^TEXTURE-FNV" "$BUILD_DIR/mod-test-full.txt" |
           awk '{print $3}')
[ -n "$FULL_FNV" ] || fail "full run printed no TEXTURE-FNV"
echo "override pi0[5] FNV: $FULL_FNV"
[ "$BASE_FNV" != "$FULL_FNV" ] ||
  fail "texture override did not change pi0[5] (FNV identical: $FULL_FNV)"
grep -q "solidMagenta=yes" "$BUILD_DIR/mod-test-full.txt" ||
  fail "override run pi0[5] is not the solid-magenta PNG"
grep -q "solidMagenta=no" "$BUILD_DIR/mod-test-baseline.txt" ||
  fail "baseline pi0[5] unexpectedly solid magenta"

echo "== checking the full-run log =="
LOG="$FULL_SAVES/tribes-log.txt"
[ -f "$LOG" ] || fail "no tribes-log.txt in $FULL_SAVES"
for needle in \
  "MOD: loaded green-valley" \
  "MOD: map green-valley -> slot q=11 (replace) 40x32" \
  "MOD: sprite sprites/pi0-05.png -> pi0[5]" \
  "MOD-REJECT: broken-mod" \
  "rec149+22 = [40, 32]" \
  "rec150+22 = [8, 21]" \
  "MOD: map name s[91] -> \"Green Valley\""; do
  grep -Fq "$needle" "$LOG" || fail "log is missing: $needle"
  echo "log OK: $needle"
done

echo "mod test: PASS"
