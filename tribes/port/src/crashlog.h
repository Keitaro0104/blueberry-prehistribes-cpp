// crashlog.h — automatic session/crash logging for tribes.exe.
//
// Own implementation. The file logger itself is portable (C stdio +
// std::chrono) so the Linux smoke test can link it too; the crash
// handlers (unhandled SEH exceptions, signals, terminate) are
// Windows-only, guarded by _WIN32.
//
// How it is used:
//   - main.cpp calls crashlog::init() once at startup, and routes its
//     trace() through crashlog::log().
//   - Thread::start() (jrt.cpp) logs a C++ exception escaping the game
//     thread, then rethrows it (the terminate handler then writes the
//     full CRASH report).
//   - platform.cpp logs the MIDI open result through crashlog::log().
//
// The log file is <logDir>\tribes-log.txt, fresh on every launch. Every
// line is flushed immediately, so the file survives a hard crash (access
// violation, abort) with the trail intact. Every public function is safe
// to call before init() (a no-op) and from any thread.

#pragma once

#include <string>

namespace crashlog {

// Open <logDir>/tribes-log.txt (fresh file), write the session header,
// and install the crash handlers (Windows only). On Windows the caller
// passes the exe directory, so the log lands next to tribes.exe. If the
// file cannot be opened, logging silently becomes a no-op.
void init(const std::string& logDir);

// Write one timestamped line to the log and flush immediately. Safe to
// call before init() (no-op) and from any thread.
void log(const char* fmt, ...);

// Called when a C++ exception escapes a jrt Thread's run(). Logs the
// exception (what()) with a THREAD-EXCEPTION marker. The caller rethrows
// afterwards, so the terminate handler still writes the full CRASH
// report. Safe to call before init().
void logThreadException(const char* what);

// Write a final line and close the log (normal shutdown path).
void shutdown(const char* reason);

}  // namespace crashlog
