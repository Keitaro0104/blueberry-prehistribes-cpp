// platform.h — platform-specific services: directories, resource loading,
// the media Player implementation, and the executable directory.
//
// Own implementation (not copied from any other port). The MIDI backend is
// Windows-only (midiOut sequencer); on other platforms
// createPlatformPlayer() returns a silent Player that still walks the
// MIDP state machine, so the game logic runs identically.

#pragma once

#include <string>
#include <vector>

#include "engine.h"
#include "jrt.h"

using namespace jrt;

struct Platform {
   static std::string dataDir;   // unpacked game data (the user's JAR)
   static std::string saveDir;   // saves + settings (RecordStores)
   static std::string userDir;   // per-user folder (saves, logs, config)
   static std::string exeDir();  // directory of the running executable
};

// Reads <dataDir>/<relPath> (a leading '/' is stripped, like a JAR entry
// name). Returns false when the file is missing.
bool readDataFile(const String& relPath, std::vector<uint8_t>& out);
std::vector<uint8_t> readDataFile(const String& relPath);

// Factory for the platform media Player (defined in platform.cpp).
Player* createPlatformPlayer(InputStream* in, const String& type);
