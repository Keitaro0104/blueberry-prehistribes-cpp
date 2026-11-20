// crashlog.cpp — implementations for crashlog.h (see the header for the
// design notes).
//
// Crash-handler notes (Windows):
//   - SetUnhandledExceptionFilter catches native faults (access
//     violation, ...). The report holds the exception code, the faulting
//     address, and a stack trace as module+offset per frame
//     (CaptureStackBackTrace + VirtualQuery for the module base; no
//     DbgHelp dependency, so there is nothing extra to link or fail).
//   - signal() for SIGSEGV/SIGABRT/SIGILL/SIGFPE is a backstop (a real
//     access violation on Windows goes through SEH first).
//   - std::set_terminate catches uncaught C++ exceptions (including one
//     rethrown out of a jrt Thread); std::current_exception() recovers
//     its what() for the report.
//   - A recursion guard (g_crashing) makes every handler a no-op if a
//     crash is already being reported, so a fault inside a handler (or
//     abort() raising SIGABRT after terminate) cannot loop.
//   - The crash path writes straight to the FILE* with fwrite+fflush
//     and never takes the log mutex: the crash may have happened while
//     another thread held it, and taking it would deadlock.

#include "crashlog.h"

#include <atomic>
#include <chrono>
#include <cstdarg>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <ctime>
#include <filesystem>
#include <mutex>

#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <csignal>
#include <exception>
#endif

namespace fs = std::filesystem;

namespace crashlog {
namespace {

FILE* g_log = nullptr;
std::mutex g_mutex;
std::chrono::steady_clock::time_point g_start;
std::atomic<bool> g_crashing{false};
bool g_init = false;

// "[12:34:56.789 +12345ms] " — wall clock + time since init, so a crash
// can be placed both in real time and in "seconds after launch".
void timestamp(char* buf, size_t n) {
   using namespace std::chrono;
   auto now = steady_clock::now();
   long long elapsedMs =
       g_init ? duration_cast<milliseconds>(now - g_start).count() : 0;
   int hh = 0, mm = 0, ss = 0, ms = 0;
#ifdef _WIN32
   SYSTEMTIME st;
   GetLocalTime(&st);
   hh = st.wHour;
   mm = st.wMinute;
   ss = st.wSecond;
   ms = st.wMilliseconds;
#else
   std::time_t t = std::time(nullptr);
   struct tm tmv {};
   localtime_r(&t, &tmv);
   hh = tmv.tm_hour;
   mm = tmv.tm_min;
   ss = tmv.tm_sec;
   ms = (int)(duration_cast<milliseconds>(now.time_since_epoch()).count() %
              1000);
#endif
   std::snprintf(buf, n, "[%02d:%02d:%02d.%03d +%lldms] ", hh, mm, ss, ms,
                 elapsedMs);
}

// Raw write used by the crash path: no mutex, direct fwrite + fflush.
void writeRaw(const char* text) {
   if (!g_log) return;
   std::fwrite(text, 1, std::strlen(text), g_log);
   std::fflush(g_log);
}

// Strip trailing newlines: callers (e.g. main.cpp trace()) often format
// with a trailing '\n', and the log adds its own.
void stripTrailingNewlines(char* s) {
   size_t len = std::strlen(s);
   while (len > 0 && (s[len - 1] == '\n' || s[len - 1] == '\r'))
      s[--len] = '\0';
}

// Timestamped raw line, no mutex (crash path only).
void crashLine(const char* fmt, ...) {
   if (!g_log) return;
   char msg[2048];
   va_list ap;
   va_start(ap, fmt);
   std::vsnprintf(msg, sizeof(msg), fmt, ap);
   va_end(ap);
   stripTrailingNewlines(msg);
   char ts[64];
   timestamp(ts, sizeof(ts));
   std::fprintf(g_log, "%s%s\n", ts, msg);
   std::fflush(g_log);
}

void writeLineLocked(const char* msg) {
   char ts[64];
   timestamp(ts, sizeof(ts));
   std::lock_guard<std::mutex> lock(g_mutex);
   std::fprintf(g_log, "%s%s\n", ts, msg);
   std::fflush(g_log);
}

#ifdef _WIN32

void writeStackTrace() {
   void* frames[48];
   USHORT n = CaptureStackBackTrace(0, 48, frames, nullptr);
   crashLine("stack trace (%u frames, module+offset):", (unsigned)n);
   for (USHORT i = 0; i < n; i++) {
      uintptr_t addr = (uintptr_t)frames[i];
      uintptr_t base = 0;
      char modPath[MAX_PATH] = "";
      MEMORY_BASIC_INFORMATION mbi{};
      if (VirtualQuery((LPCVOID)addr, &mbi, sizeof(mbi))) {
         base = (uintptr_t)mbi.AllocationBase;
         GetModuleFileNameA((HMODULE)base, modPath, MAX_PATH);
      }
      const char* modName = "?";
      if (modPath[0]) {
         const char* slash = std::strrchr(modPath, '\\');
         modName = slash ? slash + 1 : modPath;
      }
      crashLine("  #%02u %s+0x%llx  (0x%p)", (unsigned)i, modName,
                (unsigned long long)(addr - base), frames[i]);
   }
}

void writeCrashReport(const char* kind, const char* detail) {
   crashLine("");
   crashLine("================ CRASH ================");
   crashLine("%s", kind);
   if (detail && detail[0]) crashLine("%s", detail);
   writeStackTrace();
   crashLine("=======================================");
   crashLine("");
}

LONG WINAPI unhandledExceptionFilter(EXCEPTION_POINTERS* info) {
   if (g_crashing.exchange(true)) return EXCEPTION_CONTINUE_SEARCH;
   DWORD code = info->ExceptionRecord->ExceptionCode;
   void* addr = info->ExceptionRecord->ExceptionAddress;
   char detail[512];
   if (code == EXCEPTION_ACCESS_VIOLATION &&
       info->ExceptionRecord->NumberParameters >= 2) {
      ULONG_PTR op = info->ExceptionRecord->ExceptionInformation[0];
      ULONG_PTR fault = info->ExceptionRecord->ExceptionInformation[1];
      std::snprintf(detail, sizeof(detail),
                    "EXCEPTION_ACCESS_VIOLATION (%s address 0x%p) at 0x%p",
                    op == 0 ? "read" : op == 1 ? "write" : "execute",
                    (void*)fault, addr);
   } else {
      std::snprintf(detail, sizeof(detail),
                    "exception code 0x%08lX at 0x%p", (unsigned long)code,
                    addr);
   }
   writeCrashReport("unhandled SEH exception", detail);
   return EXCEPTION_EXECUTE_HANDLER;
}

void signalHandler(int sig) {
   if (g_crashing.exchange(true)) {
      // Already reporting a crash (e.g. abort() from the terminate
      // handler raised SIGABRT): get out immediately.
      _exit(3);
   }
   const char* name = "?";
   switch (sig) {
      case SIGSEGV: name = "SIGSEGV"; break;
      case SIGABRT: name = "SIGABRT"; break;
      case SIGILL: name = "SIGILL"; break;
      case SIGFPE: name = "SIGFPE"; break;
   }
   char detail[128];
   std::snprintf(detail, sizeof(detail), "signal %d (%s)", sig, name);
   writeCrashReport("signal", detail);
   _exit(3);  // do not return: process state is undefined after the fault
}

void terminateHandler() {
   if (g_crashing.exchange(true)) std::abort();
   std::string what = "unknown exception";
   if (auto e = std::current_exception()) {
      try {
         std::rethrow_exception(e);
      } catch (const std::exception& ex) {
         what = ex.what();
      } catch (...) {
         what = "unknown (non-std) exception";
      }
   }
   char detail[1024];
   std::snprintf(detail, sizeof(detail), "uncaught C++ exception: %s",
                 what.c_str());
   writeCrashReport("terminate (uncaught C++ exception)", detail);
   std::abort();  // raises SIGABRT; the signal handler exits (guard set)
}

#endif  // _WIN32

}  // namespace

void init(const std::string& logDir) {
   g_start = std::chrono::steady_clock::now();
   fs::path path = fs::path(logDir) / "tribes-log.txt";
   g_log = std::fopen(path.string().c_str(), "w");
   g_init = true;
   if (!g_log) return;
   writeRaw("Prehistoric Tribes C++ port - session log\n");
#ifdef _WIN32
   char exePath[MAX_PATH] = "?";
   GetModuleFileNameA(nullptr, exePath, MAX_PATH);
   crashLine("exe: %s", exePath);
   crashLine("command line: %s", GetCommandLineA());
#endif
   crashLine("log file: %s", path.string().c_str());
   crashLine("session started");
#ifdef _WIN32
   SetUnhandledExceptionFilter(unhandledExceptionFilter);
   std::signal(SIGSEGV, signalHandler);
   std::signal(SIGABRT, signalHandler);
   std::signal(SIGILL, signalHandler);
   std::signal(SIGFPE, signalHandler);
   std::set_terminate(terminateHandler);
#endif
}

void log(const char* fmt, ...) {
   if (!g_log) return;
   char msg[2048];
   va_list ap;
   va_start(ap, fmt);
   std::vsnprintf(msg, sizeof(msg), fmt, ap);
   va_end(ap);
   stripTrailingNewlines(msg);
   writeLineLocked(msg);
}

void logThreadException(const char* what) {
   log("THREAD-EXCEPTION: C++ exception escaped the game thread: %s",
       what ? what : "unknown");
}

void shutdown(const char* reason) {
   if (!g_log) return;
   log("session ended: %s", reason ? reason : "unknown");
   std::fclose(g_log);
   g_log = nullptr;
}

}  // namespace crashlog
