// platform.cpp — implementations for platform.h (see the header for the
// design notes). Includes JavaClass::getResourceAsStream (declared in
// jrt.h) because it needs Platform::dataDir.

#include "platform.h"

#include <cstdio>
#include <filesystem>

#include "crashlog.h"

#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <mmsystem.h>
#endif

#include <algorithm>
#include <atomic>
#include <chrono>
#include <cstring>
#include <thread>

namespace fs = std::filesystem;

std::string Platform::dataDir;
std::string Platform::saveDir;
std::string Platform::userDir;

std::string Platform::exeDir() {
#ifdef _WIN32
   wchar_t buf[MAX_PATH];
   DWORD n = GetModuleFileNameW(nullptr, buf, MAX_PATH);
   if (n == 0) return ".";
   return fs::path(buf).parent_path().string();
#else
   std::error_code ec;
   return fs::current_path(ec).string();
#endif
}

bool readDataFile(const String& relPath, std::vector<uint8_t>& out) {
   std::string rel = relPath.toUtf8();
   while (!rel.empty() && rel.front() == '/') rel.erase(rel.begin());
   fs::path full = fs::path(Platform::dataDir) / rel;
   FILE* f = std::fopen(full.string().c_str(), "rb");
   if (!f) return false;
   std::fseek(f, 0, SEEK_END);
   long size = std::ftell(f);
   std::fseek(f, 0, SEEK_SET);
   out.clear();
   if (size > 0) {
      out.resize((size_t)size);
      size_t got = std::fread(out.data(), 1, out.size(), f);
      out.resize(got);
   }
   std::fclose(f);
   return true;
}

std::vector<uint8_t> readDataFile(const String& relPath) {
   std::vector<uint8_t> out;
   readDataFile(relPath, out);
   return out;
}

namespace jrt {
InputStream* JavaClass::getResourceAsStream(const String& name) {
   std::vector<uint8_t> bytes;
   if (!readDataFile(name, bytes)) return nullptr;
   Arr<int8_t> arr((size_t)bytes.size());
   for (size_t i = 0; i < bytes.size(); i++) arr[(int)i] = (int8_t)bytes[i];
   return new ByteArrayInputStream(arr);
}
}  // namespace jrt

// ---------------------------------------------------------------------------
// MIDI playback.
// ---------------------------------------------------------------------------
namespace {

// One scheduled MIDI message: millisecond time + the short message bytes.
struct MidiEvent {
   int64_t ms = 0;
   uint32_t msg = 0;  // status | data1<<8 | data2<<16
};

// Minimal SMF (format 0/1) parser: merges all tracks into one millisecond
// event list, following tempo changes. Returns false on malformed data.
bool parseMidi(const std::vector<uint8_t>& bytes, std::vector<MidiEvent>& out) {
   if (bytes.size() < 14) return false;
   if (std::memcmp(bytes.data(), "MThd", 4) != 0) return false;
   auto be32 = [&](size_t p) {
      return (uint32_t)((bytes[p] << 24) | (bytes[p + 1] << 16) |
                        (bytes[p + 2] << 8) | bytes[p + 3]);
   };
   auto be16 = [&](size_t p) {
      return (uint16_t)((bytes[p] << 8) | bytes[p + 1]);
   };
   uint16_t format = be16(8);
   uint16_t ntracks = be16(10);
   uint16_t division = be16(12);
   if (format > 1 || ntracks == 0 || division == 0 || (division & 0x8000))
      return false;
   size_t pos = 14;
   struct RawEvent {
      int64_t tick = 0;
      uint32_t msg = 0;
   };
   std::vector<RawEvent> raw;
   int64_t tempoUsPerQuarter = 500000;  // default 120 BPM
   // Tempo map: (tick, usPerQuarter), in track order.
   std::vector<std::pair<int64_t, int64_t>> tempos;
   for (int t = 0; t < ntracks; t++) {
      if (pos + 8 > bytes.size() || std::memcmp(&bytes[pos], "MTrk", 4) != 0)
         return false;
      uint32_t trackLen = be32(pos + 4);
      size_t trackEnd = pos + 8 + trackLen;
      if (trackEnd > bytes.size()) return false;
      pos += 8;
      int64_t tick = 0;
      uint8_t runningStatus = 0;
      auto readVarLen = [&](int64_t& value) {
         value = 0;
         while (pos < trackEnd) {
            uint8_t b = bytes[pos++];
            value = (value << 7) | (b & 0x7F);
            if (!(b & 0x80)) return true;
         }
         return false;
      };
      while (pos < trackEnd) {
         int64_t delta = 0;
         if (!readVarLen(delta)) return false;
         tick += delta;
         if (pos >= trackEnd) break;
         uint8_t status = bytes[pos];
         if (status < 0x80) {
            status = runningStatus;
         } else {
            pos++;
            if (status < 0xF0) runningStatus = status;
         }
         if (status == 0xFF) {  // meta event
            if (pos >= trackEnd) break;
            uint8_t type = bytes[pos++];
            int64_t len = 0;
            if (!readVarLen(len)) return false;
            if (type == 0x51 && len == 3 && pos + 3 <= trackEnd) {
               int64_t us = (bytes[pos] << 16) | (bytes[pos + 1] << 8) | bytes[pos + 2];
               tempos.push_back({tick, us});
            }
            pos += (size_t)len;
         } else if (status == 0xF0 || status == 0xF7) {  // SysEx
            int64_t len = 0;
            if (!readVarLen(len)) return false;
            pos += (size_t)len;
         } else {
            uint8_t hi = status & 0xF0;
            int dataBytes = (hi == 0xC0 || hi == 0xD0) ? 1 : 2;
            if (pos + (size_t)dataBytes > trackEnd) return false;
            uint8_t d1 = bytes[pos++];
            uint8_t d2 = dataBytes == 2 ? bytes[pos++] : 0;
            if (hi != 0xF0) {
               raw.push_back({tick, (uint32_t)(status | (d1 << 8) | (d2 << 16))});
            }
         }
      }
      pos = trackEnd;
   }
   // Convert ticks to milliseconds with the tempo map.
   std::sort(raw.begin(), raw.end(),
             [](const RawEvent& a, const RawEvent& b) { return a.tick < b.tick; });
   std::sort(tempos.begin(), tempos.end());
   size_t tempoIdx = 0;
   int64_t lastTick = 0;
   int64_t ms = 0;
   for (const auto& ev : raw) {
      while (tempoIdx < tempos.size() && tempos[tempoIdx].first <= ev.tick) {
         ms += (tempos[tempoIdx].first - lastTick) * tempoUsPerQuarter /
               (division * 1000);
         lastTick = tempos[tempoIdx].first;
         tempoUsPerQuarter = tempos[tempoIdx].second;
         tempoIdx++;
      }
      int64_t eventMs =
          ms + (ev.tick - lastTick) * tempoUsPerQuarter / (division * 1000);
      out.push_back({eventMs, ev.msg});
   }
   return true;
}

}  // namespace

#ifdef _WIN32

// Windows MIDI backend: a sequencer thread sends the parsed events to the
// Windows MIDI mapper with midiOutShortMsg.
class WinMidiPlayer : public Player {
  public:
   explicit WinMidiPlayer(InputStream* in) : Player(in) {}
   ~WinMidiPlayer() override { close(); }

   void prefetch() override {
      Player::prefetch();
      if (getState() == 300 && events_.empty() && !parseFailed_) {
         if (!parseMidi(data_, events_)) parseFailed_ = true;
      }
   }

   void start() override {
      if (getState() == 100) prefetch();
      if (getState() != 300) return;
      if (parseFailed_ || events_.empty()) {
         // Nothing to play: behave as if the song ended immediately.
         crashlog::log(
             "MIDI: no playable events (%s); playing silent",
             parseFailed_ ? "song failed to parse" : "song has no events");
         setState(300);
         return;
      }
      applyVolume();
      stopRequested_.store(false);
      startMs_ = nowMs();
      thread_ = std::thread([this]() { sequencer(); });
      setState(400);
   }

   void stop() override {
      if (getState() != 400) {
         Player::stop();
         return;
      }
      stopRequested_.store(true);
      if (thread_.joinable()) thread_.join();
      sendAllNotesOff();
      setState(300);
   }

   void close() override {
      if (getState() == 400) stop();
      if (out_ != nullptr) {
         midiOutClose(out_);
         out_ = nullptr;
      }
      Player::close();
   }

  private:
   static int64_t nowMs() {
      using namespace std::chrono;
      return duration_cast<milliseconds>(steady_clock::now().time_since_epoch())
          .count();
   }

   void applyVolume() {
      // Logged (not silent): a missing MIDI device is the prime suspect
      // when the game has no sound, and the error code says why.
      UINT devs = midiOutGetNumDevs();
      MMRESULT rc = midiOutOpen(&out_, MIDI_MAPPER, 0, 0, CALLBACK_NULL);
      if (rc != MMSYSERR_NOERROR) {
         crashlog::log(
             "MIDI: midiOutOpen FAILED, error=%u (devices available: %u)",
             (unsigned)rc, (unsigned)devs);
         out_ = nullptr;
         return;
      }
      int level = volume_.getLevel();  // 0..100
      if (level < 0) level = 0;
      if (level > 100) level = 100;
      DWORD v = (DWORD)(level * 0xFFFF / 100);
      midiOutSetVolume(out_, v | (v << 16));
      crashlog::log("MIDI: midiOutOpen OK (MIDI_MAPPER, devices: %u), "
                    "volume=%d%%",
                    (unsigned)devs, level);
   }

   void sendAllNotesOff() {
      if (!out_) return;
      for (int ch = 0; ch < 16; ch++) {
         midiOutShortMsg(out_, 0xB0 | ch | (0x7B << 8));  // all notes off
         midiOutShortMsg(out_, 0xB0 | ch | (0x79 << 8));  // reset controllers
      }
   }

   void sequencer() {
      size_t idx = 0;
      while (idx < events_.size() && !stopRequested_.load()) {
         int64_t wait = startMs_ + events_[idx].ms - nowMs();
         if (wait > 0) {
            std::this_thread::sleep_for(
                std::chrono::milliseconds(wait > 50 ? 50 : wait));
            continue;
         }
         if (out_) midiOutShortMsg(out_, events_[idx].msg);
         idx++;
      }
      if (!stopRequested_.load()) {
         // Song ended: back to prefetched, like the original Player.
         setState(300);
      }
   }

   std::vector<MidiEvent> events_;
   bool parseFailed_ = false;
   HMIDIOUT out_ = nullptr;
   std::thread thread_;
   std::atomic<bool> stopRequested_{false};
   int64_t startMs_ = 0;
};

Player* createPlatformPlayer(InputStream* in, const String& /*type*/) {
   return new WinMidiPlayer(in);
}

#else

// Non-Windows: silent Player (state machine only). The game logic runs
// identically; there is just no sound.
Player* createPlatformPlayer(InputStream* in, const String& /*type*/) {
   return new Player(in);
}

#endif
