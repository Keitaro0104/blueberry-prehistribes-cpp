#!/bin/bash
# run_linux_smoke.sh — build and run the headless Linux smoke test.
#
# This is the only functional validation of the game possible on this
# Linux VM (no MSVC, no Windows). It compiles the portable core
# (jrt/gfx/engine/platform/gen) plus the smoke driver, starts the game
# against the local extracted JAR contents, and reports whether the
# splash played, the menu rendered, and the loop ran without crashing.
#
# Usage: tools/run_linux_smoke.sh [--play] [--seconds N] [--trace]
#   --play       send a scripted FIRE key to the menu (start a new game)
#   --no-interp  disable render interpolation (old 1-render-per-tick)
#   --seconds N  run duration (default 12)
#   --trace      per-second state output
# The final frame is written to /tmp/tribes-build/smoke.bmp; the script
# checks it is non-blank (more than 3 distinct sampled colours).

set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PORT="$ROOT/tribes/port"
BUILD_DIR="${BUILD_DIR:-/tmp/tribes-build}"
DATA_DIR="${DATA_DIR:-$HOME/workspace/j2me-prehistoric-tribes/extracted}"
MODS_DIR="${MODS_DIR:-}"
SAVES_DIR="$BUILD_DIR/saves"
BMP="$BUILD_DIR/smoke.bmp"

PLAY=""
SECONDS_ARG="12"
TRACE=""
NOINTERP=""
while [ $# -gt 0 ]; do
  case "$1" in
    --play) PLAY="--play"; shift ;;
    --seconds) SECONDS_ARG="$2"; shift 2 ;;
    --trace) TRACE="--trace"; shift ;;
    --no-interp) NOINTERP="--no-interp"; shift ;;
    --mods) MODS_DIR="$2"; shift 2 ;;
    *) echo "unknown argument: $1" >&2; exit 2 ;;
  esac
done

mkdir -p "$BUILD_DIR" "$SAVES_DIR"
rm -f "$SAVES_DIR"/*.bin 2>/dev/null || true

echo "== compiling the portable core + smoke driver =="
g++ -std=c++17 -O1 -Wall -Wno-unused-parameter \
  -I"$PORT/src" -I"$PORT/third_party/stb" \
  "$PORT/src/jrt.cpp" "$PORT/src/gfx.cpp" "$PORT/src/engine.cpp" \
  "$PORT/src/platform.cpp" "$PORT/src/crashlog.cpp" "$PORT/src/modloader.cpp" \
  "$PORT/src/gen/Game.cpp" \
  "$PORT/src/gen/World.cpp" "$PORT/src/gen/MidiPlayer.cpp" \
  "$PORT/src/gen/TextLoader.cpp" "$PORT/src/gen/ResourceLoader.cpp" \
  "$PORT/src/gen/Splash.cpp" "$PORT/src/gen/TribesMIDlet.cpp" \
  "$PORT/third_party/stb/stb_image_impl.cpp" \
  "$ROOT/tools/linux_smoke.cpp" \
  -o "$BUILD_DIR/linux_smoke" -lpthread

echo "== running the smoke test (${SECONDS_ARG}s) =="
MODS_ARG=""
if [ -n "$MODS_DIR" ]; then MODS_ARG="--mods $MODS_DIR"; fi
"$BUILD_DIR/linux_smoke" --data "$DATA_DIR" --saves "$SAVES_DIR" \
  --seconds "$SECONDS_ARG" --dump "$BMP" $PLAY $TRACE $NOINTERP $MODS_ARG

echo "== checking the dumped frame =="
python3 - "$BMP" <<'EOF'
import sys, struct
path = sys.argv[1]
with open(path, 'rb') as f:
    data = f.read()
assert data[:2] == b'BM', "not a BMP"
w, h = struct.unpack_from('<ii', data, 18)
print(f"frame: {w}x{h}, {len(data)} bytes")
# Sample the pixel data for colour variety (a blank frame is one colour).
off = struct.unpack_from('<I', data, 10)[0]
sampled = set()
for i in range(off, len(data) - 2, 997):
    sampled.add(data[i:i+3])
print(f"distinct sampled colours: {len(sampled)}")
if len(sampled) < 4:
    print("FAIL: frame looks blank (fewer than 4 distinct sampled colours)")
    sys.exit(1)
print("frame OK (non-blank)")
EOF

echo "linux smoke test: PASS"
