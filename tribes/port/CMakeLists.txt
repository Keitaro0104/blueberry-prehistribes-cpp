# CMakeLists.txt — Prehistoric Tribes C++ port (Windows).
#
# Build with build.bat (MSVC, static CRT) or:
#   cmake -S . -B build -A x64
#   cmake --build build --config Release
#
# The game links winmm.lib (midiOut MIDI playback). The launcher is a
# separate executable so the game never needs to know about JAR files.

cmake_minimum_required(VERSION 3.20)
project(prehistribes-cpp CXX C)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Static CRT (/MT), like the GOW port: the dist folder stays self-contained.
if(MSVC)
   set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded$<$<CONFIG:Debug>:Debug>")
   # NOMINMAX: windows.h defines min/max as function-like macros; without
   # this they expand inside (and break) the jlang::min/jlang::max
   # templates declared in src/jrt.h (C2988 and a cascade of follow-ons).
   add_compile_definitions(NOMINMAX)
endif()

# --- Game ---------------------------------------------------------------
file(GLOB GEN_SOURCES CONFIGURE_DEPENDS src/gen/*.cpp)

add_executable(tribes WIN32
   src/jrt.cpp
   src/gfx.cpp
   src/engine.cpp
   src/platform.cpp
   src/crashlog.cpp
   src/modloader.cpp
   src/display.cpp
   src/input.cpp
   src/main.cpp
   third_party/stb/stb_image_impl.cpp
   ${GEN_SOURCES}
)
target_include_directories(tribes PRIVATE src third_party/stb)
target_link_libraries(tribes PRIVATE winmm shell32)

# --- Launcher -----------------------------------------------------------
add_executable(tribes-launcher WIN32
   src/launcher/launcher.cpp
   third_party/puff/puff.c
)
target_include_directories(tribes-launcher PRIVATE src third_party/puff)
target_link_libraries(tribes-launcher PRIVATE shell32)

# Warnings as warnings (not errors): the generated code is mechanical and
# may contain unused locals from the decompiler.
if(MSVC)
   # /utf-8: the sources are UTF-8. The generated code has u'...' char
   # literals with non-ASCII code units; without this MSVC reads the
   # source as windows-1252 and rejects them (C2015).
   target_compile_options(tribes PRIVATE /W3 /utf-8)
   target_compile_options(tribes-launcher PRIVATE /W3 /utf-8)
endif()
