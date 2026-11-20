// launcher.cpp — the "bring your own JAR" launcher for the port.
//
// Own implementation (not copied from any other port). The game itself
// never touches a JAR: this launcher asks the user to pick their own
// Prehistoric Tribes JAR, validates it (fingerprint: the MIDlet class,
// the 7 game classes, and the data files ma/a/sa/pd0/pi0/pi8/pi9), and
// extracts the data files into <exe>\data\ (the game reads them from
// there). Class files and META-INF are not extracted — the port's code
// is the translated C++, not the original classes. Then the user presses
// Play and the launcher starts tribes.exe.
//
// ZIP extraction uses puff (third_party/puff) — a raw-deflate decoder,
// which is exactly what ZIP DEFLATE entries are. No external dependency.
//
// No self-update (future work). No game data is bundled with the port.

#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <commdlg.h>
#include <shellapi.h>  // ShellExecuteA

#include <cstdarg>
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>

#include "puff.h"

namespace {

// The fingerprint: entries that must be present in the JAR for it to be
// Prehistoric Tribes. The class files prove it is the right game; the
// data files are the ones the port actually loads.
const char* kRequired[] = {
    "tribes.class", "f.class", "b.class", "a.class",
    "c.class",      "d.class", "e.class", "ma",
    "a",            "sa",      "pd0",     "pi0",
    "pi8",          "pi9",
};
// Data files the game needs to find in <exe>\data.
const char* kDataFiles[] = {"ma", "a", "sa", "pd0"};

std::string g_exeDir;
HWND g_hwnd = nullptr;
HWND g_statusLabel = nullptr;
HWND g_logBox = nullptr;
HWND g_playBtn = nullptr;
HWND g_chooseBtn = nullptr;
std::string g_dataDir;

void logLine(const char* fmt, ...) {
   char buf[1024];
   va_list ap;
   va_start(ap, fmt);
   vsnprintf(buf, sizeof(buf), fmt, ap);
   va_end(ap);
   if (!g_logBox) return;
   int len = GetWindowTextLengthA(g_logBox);
   SendMessageA(g_logBox, EM_SETSEL, (WPARAM)len, (LPARAM)len);
   SendMessageA(g_logBox, EM_REPLACESEL, FALSE, (LPARAM)buf);
   SendMessageA(g_logBox, EM_REPLACESEL, FALSE, (LPARAM) "\r\n");
}

void setStatus(const char* text) {
   if (g_statusLabel) SetWindowTextA(g_statusLabel, text);
}

std::string exeDir() {
   char path[MAX_PATH];
   GetModuleFileNameA(nullptr, path, MAX_PATH);
   std::string s(path);
   size_t slash = s.find_last_of("\\/");
   return (slash == std::string::npos) ? "." : s.substr(0, slash);
}

bool fileExists(const std::string& path) {
   DWORD attr = GetFileAttributesA(path.c_str());
   return attr != INVALID_FILE_ATTRIBUTES;
}

bool dataReady() {
   for (const char* f : kDataFiles) {
      if (!fileExists(g_dataDir + "\\" + f)) return false;
   }
   return true;
}

void makeDirs(const std::string& path) {
   // Create every parent directory of `path` (path is a file path).
   std::string cur;
   for (size_t i = 0; i < path.size(); i++) {
      char c = path[i];
      if (c == '\\' || c == '/') {
         if (!cur.empty()) CreateDirectoryA(cur.c_str(), nullptr);
      }
      cur += (c == '/') ? '\\' : c;
   }
}

// --- Minimal ZIP reader (central directory) -----------------------------

struct ZipEntry {
   std::string name;
   uint16_t method = 0;       // 0 stored, 8 deflate
   uint32_t compSize = 0;
   uint32_t uncompSize = 0;
   uint32_t localOff = 0;
};

uint16_t rd16(const unsigned char* p) {
   return (uint16_t)(p[0] | (p[1] << 8));
}
uint32_t rd32(const unsigned char* p) {
   return (uint32_t)p[0] | ((uint32_t)p[1] << 8) | ((uint32_t)p[2] << 16) |
          ((uint32_t)p[3] << 24);
}

// Parse the central directory of a ZIP/JAR held in memory. Returns false
// on a malformed archive.
bool parseZip(const unsigned char* data, size_t size,
              std::vector<ZipEntry>& out) {
   if (size < 22) return false;
   // Find the End of Central Directory record (PK\x05\x06), scanning
   // backwards (it may have a comment after it).
   size_t eocd = SIZE_MAX;
   size_t scanFrom = size - 22;
   size_t scanTo = (size > 22 + 65535) ? size - 22 - 65535 : 0;
   for (size_t i = scanFrom + 1; i-- > scanTo;) {
      if (data[i] == 'P' && data[i + 1] == 'K' && data[i + 2] == 5 &&
          data[i + 3] == 6) {
         eocd = i;
         break;
      }
   }
   if (eocd == SIZE_MAX) return false;
   uint16_t total = rd16(data + eocd + 10);
   uint32_t cdOff = rd32(data + eocd + 16);
   size_t p = cdOff;
   for (uint16_t i = 0; i < total; i++) {
      if (p + 46 > size) return false;
      if (data[p] != 'P' || data[p + 1] != 'K' || data[p + 2] != 1 ||
          data[p + 3] != 2)
         return false;
      ZipEntry e;
      e.method = rd16(data + p + 10);
      e.compSize = rd32(data + p + 20);
      e.uncompSize = rd32(data + p + 24);
      uint16_t nameLen = rd16(data + p + 28);
      uint16_t extraLen = rd16(data + p + 30);
      uint16_t commentLen = rd16(data + p + 32);
      e.localOff = rd32(data + p + 42);
      if (p + 46 + nameLen > size) return false;
      e.name.assign((const char*)(data + p + 46), nameLen);
      out.push_back(e);
      p += 46 + nameLen + extraLen + commentLen;
   }
   return true;
}

// Extract one entry into memory (decompressing with puff when needed).
// Returns false on failure.
bool extractEntry(const unsigned char* data, size_t size, const ZipEntry& e,
                  std::vector<unsigned char>& out) {
   if (e.localOff + 30 > size) return false;
   const unsigned char* lh = data + e.localOff;
   if (lh[0] != 'P' || lh[1] != 'K' || lh[2] != 3 || lh[3] != 4) return false;
   uint16_t nameLen = rd16(lh + 26);
   uint16_t extraLen = rd16(lh + 28);
   size_t dataOff = e.localOff + 30 + nameLen + extraLen;
   if (dataOff + e.compSize > size) return false;
   out.assign(e.uncompSize, 0);
   if (e.method == 0) {
      // Stored: copy verbatim.
      if (e.compSize != e.uncompSize) return false;
      std::memcpy(out.data(), data + dataOff, e.compSize);
      return true;
   }
   if (e.method == 8) {
      unsigned long destLen = e.uncompSize;
      unsigned long srcLen = e.compSize;
      int rc = puff(out.data(), &destLen, data + dataOff, &srcLen);
      return rc == 0 && destLen == e.uncompSize;
   }
   return false;  // unsupported compression method
}

bool isSafeName(const std::string& name) {
   if (name.empty() || name[0] == '/' || name[0] == '\\') return false;
   if (name.find("..") != std::string::npos) return false;
   if (name.find(':') != std::string::npos) return false;
   return true;
}

bool isExtracted(const std::string& name) {
   // Skip class files (the port is the translated C++) and META-INF.
   if (name.size() >= 6 &&
       name.compare(name.size() - 6, 6, ".class") == 0)
      return false;
   if (name.rfind("META-INF", 0) == 0) return false;
   if (!name.empty() && name.back() == '/') return false;  // directory
   return true;
}

void importJar(const std::string& jarPath) {
   logLine("Reading %s ...", jarPath.c_str());
   FILE* f = nullptr;
   if (fopen_s(&f, jarPath.c_str(), "rb") != 0 || !f) {
      logLine("ERROR: cannot open the JAR.");
      return;
   }
   fseek(f, 0, SEEK_END);
   long size = ftell(f);
   fseek(f, 0, SEEK_SET);
   if (size <= 0) {
      fclose(f);
      logLine("ERROR: the JAR is empty.");
      return;
   }
   std::vector<unsigned char> jar(size);
   if (fread(jar.data(), 1, size, f) != (size_t)size) {
      fclose(f);
      logLine("ERROR: failed to read the JAR.");
      return;
   }
   fclose(f);

   std::vector<ZipEntry> entries;
   if (!parseZip(jar.data(), jar.size(), entries)) {
      logLine("ERROR: not a valid ZIP/JAR file.");
      return;
   }
   logLine("JAR OK (%d entries). Checking the fingerprint...", (int)entries.size());

   // Fingerprint: every required entry must be present.
   std::vector<std::string> missing;
   for (const char* req : kRequired) {
      bool found = false;
      for (const auto& e : entries) {
         if (e.name == req) {
            found = true;
            break;
         }
      }
      if (!found) missing.push_back(req);
   }
   if (!missing.empty()) {
      logLine("ERROR: this does not look like Prehistoric Tribes. Missing:");
      for (const auto& m : missing) logLine("  - %s", m.c_str());
      return;
   }
   logLine("Fingerprint OK: Prehistoric Tribes.");

   // Extract the data files.
   CreateDirectoryA(g_dataDir.c_str(), nullptr);
   int extracted = 0;
   for (const auto& e : entries) {
      if (!isExtracted(e.name)) continue;
      if (!isSafeName(e.name)) {
         logLine("WARNING: skipping unsafe entry name: %s", e.name.c_str());
         continue;
      }
      std::string outPath = g_dataDir + "\\" + e.name;
      for (char& c : outPath) {
         if (c == '/') c = '\\';
      }
      std::vector<unsigned char> bytes;
      if (!extractEntry(jar.data(), jar.size(), e, bytes)) {
         logLine("ERROR: failed to extract %s", e.name.c_str());
         return;
      }
      makeDirs(outPath);
      FILE* out = nullptr;
      if (fopen_s(&out, outPath.c_str(), "wb") != 0 || !out) {
         logLine("ERROR: cannot write %s", outPath.c_str());
         return;
      }
      fwrite(bytes.data(), 1, bytes.size(), out);
      fclose(out);
      extracted++;
   }
   // Record where the data came from (for the "which JAR did I import"
   // question) and when.
   {
      std::string info = g_dataDir + "\\import.txt";
      FILE* out = nullptr;
      if (fopen_s(&out, info.c_str(), "w") == 0 && out) {
         SYSTEMTIME st;
         GetLocalTime(&st);
         std::fprintf(out, "source: %s\nimported: %04d-%02d-%02d %02d:%02d\n",
                      jarPath.c_str(), st.wYear, st.wMonth, st.wDay, st.wHour,
                      st.wMinute);
         std::fprintf(out, "entries extracted: %d\n", extracted);
         fclose(out);
      }
   }
   logLine("Imported %d files into %s", extracted, g_dataDir.c_str());
   setStatus("Game data ready — press Play.");
   EnableWindow(g_playBtn, TRUE);
}

void chooseJar() {
   char path[MAX_PATH] = {0};
   OPENFILENAMEA ofn = {};
   ofn.lStructSize = sizeof(ofn);
   ofn.hwndOwner = g_hwnd;
   ofn.lpstrFilter = "J2ME JAR files (*.jar)\0*.jar\0All files (*.*)\0*.*\0";
   ofn.lpstrFile = path;
   ofn.nMaxFile = MAX_PATH;
   ofn.Flags = OFN_FILEMUSTEXIST | OFN_PATHMUSTEXIST;
   ofn.lpstrTitle = "Choose your Prehistoric Tribes JAR";
   if (GetOpenFileNameA(&ofn)) {
      importJar(path);
   }
}

void play() {
   if (!dataReady()) {
      MessageBoxA(g_hwnd, "Game data is missing. Choose your JAR first.",
                  "Prehistoric Tribes", MB_OK | MB_ICONWARNING);
      return;
   }
   std::string exe = g_exeDir + "\\tribes.exe";
   if (!fileExists(exe)) {
      MessageBoxA(g_hwnd, "tribes.exe not found next to the launcher.",
                  "Prehistoric Tribes", MB_OK | MB_ICONERROR);
      return;
   }
   ShellExecuteA(nullptr, "open", exe.c_str(), nullptr, g_exeDir.c_str(),
                 SW_SHOWNORMAL);
   DestroyWindow(g_hwnd);
}

LRESULT CALLBACK wndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
   switch (msg) {
      case WM_COMMAND: {
         int id = LOWORD(wParam);
         if (id == 1) chooseJar();
         if (id == 2) play();
         return 0;
      }
      case WM_DESTROY:
         PostQuitMessage(0);
         return 0;
      case WM_CLOSE:
         DestroyWindow(hwnd);
         return 0;
   }
   return DefWindowProcA(hwnd, msg, wParam, lParam);
}

}  // namespace

int WINAPI WinMain(HINSTANCE inst, HINSTANCE, LPSTR, int) {
   g_exeDir = exeDir();
   g_dataDir = g_exeDir + "\\data";

   WNDCLASSA wc = {};
   wc.lpfnWndProc = wndProc;
   wc.hInstance = inst;
   wc.lpszClassName = "BlueberryTribesLauncher";
   wc.hCursor = LoadCursorA(nullptr, IDC_ARROW);
   wc.hbrBackground = (HBRUSH)(COLOR_BTNFACE + 1);
   RegisterClassA(&wc);

   g_hwnd = CreateWindowExA(0, "BlueberryTribesLauncher",
                            "Prehistoric Tribes — Launcher",
                            WS_OVERLAPPEDWINDOW & ~WS_MAXIMIZEBOX & ~WS_THICKFRAME,
                            CW_USEDEFAULT, CW_USEDEFAULT, 520, 380, nullptr,
                            nullptr, inst, nullptr);
   if (!g_hwnd) return 1;

   CreateWindowA("STATIC",
                 "Bring your own JAR: pick the Prehistoric Tribes JAR you "
                 "own, and the launcher extracts its data files.",
                 WS_VISIBLE | WS_CHILD, 12, 12, 480, 28, g_hwnd, nullptr,
                 inst, nullptr);
   g_statusLabel = CreateWindowA(
       "STATIC", "", WS_VISIBLE | WS_CHILD, 12, 44, 480, 16, g_hwnd, nullptr,
       inst, nullptr);
   g_chooseBtn = CreateWindowA("BUTTON", "Choose JAR...", WS_VISIBLE | WS_CHILD,
                               12, 68, 140, 28, g_hwnd, (HMENU)1, inst,
                               nullptr);
   g_playBtn = CreateWindowA("BUTTON", "Play", WS_VISIBLE | WS_CHILD | WS_DISABLED,
                             164, 68, 140, 28, g_hwnd, (HMENU)2, inst, nullptr);
   g_logBox = CreateWindowA("EDIT", "", WS_VISIBLE | WS_CHILD | WS_VSCROLL |
                                        ES_MULTILINE | ES_READONLY | ES_AUTOVSCROLL,
                            12, 108, 480, 220, g_hwnd, nullptr, inst, nullptr);

   if (dataReady()) {
      setStatus("Game data ready — press Play.");
      EnableWindow(g_playBtn, TRUE);
      logLine("Game data found in %s", g_dataDir.c_str());
      std::string info = g_dataDir + "\\import.txt";
      FILE* f = nullptr;
      if (fopen_s(&f, info.c_str(), "r") == 0 && f) {
         char line[512];
         while (fgets(line, sizeof(line), f)) {
            size_t n = strlen(line);
            while (n > 0 && (line[n - 1] == '\n' || line[n - 1] == '\r'))
               line[--n] = 0;
            logLine("%s", line);
         }
         fclose(f);
      }
   } else {
      setStatus("No game data found — choose your JAR to import it.");
      logLine("No game data in %s", g_dataDir.c_str());
      logLine("Pick the JAR you own (Prehistoric Tribes, J2ME) to import it.");
   }

   ShowWindow(g_hwnd, SW_SHOW);
   UpdateWindow(g_hwnd);

   MSG msg;
   while (GetMessageA(&msg, nullptr, 0, 0)) {
      TranslateMessage(&msg);
      DispatchMessageA(&msg);
   }
   return (int)msg.wParam;
}
