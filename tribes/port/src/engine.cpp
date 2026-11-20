// engine.cpp — implementations for engine.h (see the header for the design notes).

#include "engine.h"

#include <cstdio>
#include <filesystem>

#include "platform.h"

namespace fs = std::filesystem;

// ---------------------------------------------------------------------------
// Canvas
// ---------------------------------------------------------------------------
std::atomic<bool> Canvas::repaintRequested_{false};
std::function<void()> Canvas::repaintCallback_;

void Canvas::repaint() {
   repaintRequested_.store(true);
   if (repaintCallback_) repaintCallback_();
}

void Canvas::setRepaintCallback(std::function<void()> cb) {
   repaintCallback_ = std::move(cb);
}

bool Canvas::repaintRequested() { return repaintRequested_.load(); }

int Canvas::getGameAction(int keyCode) const {
   switch (keyCode) {
      case KEY_UP:
      case '2':
         return ACTION_UP;
      case KEY_DOWN:
      case '8':
         return ACTION_DOWN;
      case KEY_LEFT:
      case '4':
         return ACTION_LEFT;
      case KEY_RIGHT:
      case '6':
         return ACTION_RIGHT;
      case KEY_FIRE:
      case '5':
         return ACTION_FIRE;
      default:
         return 0;
   }
}

// ---------------------------------------------------------------------------
// MIDlet
// ---------------------------------------------------------------------------
void MIDlet::notifyDestroyed() { destroyed_ = true; }

// ---------------------------------------------------------------------------
// Display
// ---------------------------------------------------------------------------
Display* Display::getDisplay(MIDlet* /*midlet*/) {
   static Display instance;
   return &instance;
}

// ---------------------------------------------------------------------------
// RecordStore
// ---------------------------------------------------------------------------
std::string RecordStore::filePath(int id) const {
   return (fs::path(Platform::saveDir) /
           (name_.toUtf8() + "_" + std::to_string(id) + ".bin"))
       .string();
}

void RecordStore::loadExisting() {
   std::error_code ec;
   if (!fs::exists(Platform::saveDir, ec)) return;
   std::string prefix = name_.toUtf8() + "_";
   for (const auto& entry : fs::directory_iterator(Platform::saveDir, ec)) {
      std::string fname = entry.path().filename().string();
      if (fname.rfind(prefix, 0) != 0) continue;
      if (entry.path().extension() != ".bin") continue;
      std::string num = fname.substr(prefix.size(),
                                     fname.size() - prefix.size() - 4);
      try {
         int id = std::stoi(num);
         FILE* f = std::fopen(entry.path().string().c_str(), "rb");
         if (!f) continue;
         std::fseek(f, 0, SEEK_END);
         long size = std::ftell(f);
         std::fseek(f, 0, SEEK_SET);
         std::vector<uint8_t> bytes((size_t)(size > 0 ? size : 0));
         if (size > 0) std::fread(bytes.data(), 1, bytes.size(), f);
         std::fclose(f);
         records_[id] = std::move(bytes);
      } catch (...) {
      }
   }
}

RecordStore* RecordStore::openRecordStore(const String& name,
                                          bool createIfNecessary) {
   RecordStore* store = new RecordStore(name);
   store->loadExisting();
   if (store->records_.empty() && !createIfNecessary) {
      delete store;
      throw JavaException{"RecordStoreNotFoundException"};
   }
   return store;
}

void RecordStore::deleteRecordStore(const String& name) {
   std::error_code ec;
   if (!fs::exists(Platform::saveDir, ec)) return;
   std::string prefix = name.toUtf8() + "_";
   for (const auto& entry : fs::directory_iterator(Platform::saveDir, ec)) {
      std::string fname = entry.path().filename().string();
      if (fname.rfind(prefix, 0) == 0 && entry.path().extension() == ".bin") {
         fs::remove(entry.path(), ec);
      }
   }
}

int RecordStore::getNumRecords() const { return (int)records_.size(); }

Arr<int8_t> RecordStore::getRecord(int id) {
   auto it = records_.find(id);
   if (it == records_.end()) throw JavaException{"InvalidRecordIDException"};
   Arr<int8_t> out((size_t)it->second.size());
   for (size_t i = 0; i < it->second.size(); i++)
      out[(int)i] = (int8_t)it->second[i];
   return out;
}

int RecordStore::addRecord(Arr<int8_t> data, int offset, int numBytes) {
   if (data.isNull()) throw JavaException{"NullPointerException"};
   int id = 1;
   for (const auto& kv : records_) id = kv.first + 1;
   std::vector<uint8_t> bytes((size_t)numBytes);
   for (int i = 0; i < numBytes; i++)
      bytes[(size_t)i] = (uint8_t)(data[offset + i] & 0xFF);
   std::error_code ec;
   fs::create_directories(Platform::saveDir, ec);
   FILE* f = std::fopen(filePath(id).c_str(), "wb");
   if (f) {
      if (!bytes.empty()) std::fwrite(bytes.data(), 1, bytes.size(), f);
      std::fclose(f);
   }
   records_[id] = std::move(bytes);
   return id;
}

// ---------------------------------------------------------------------------
// Media
// ---------------------------------------------------------------------------
void VolumeControl::setLevel(int level) {
   level_ = level;
   // The platform MIDI backend reads the level when (re)starting playback.
}

Player::Player(InputStream* in) {
   if (in) {
      Arr<int8_t> chunk = newArr<int8_t>(4096);
      int n;
      while ((n = in->read(chunk, 0, 4096)) > 0) {
         for (int i = 0; i < n; i++) data_.push_back((uint8_t)(chunk[i] & 0xFF));
      }
   }
}

void Player::realize() {
   if (state_ == 0) state_ = 100;
}

void Player::prefetch() {
   if (state_ == 0) realize();
   if (state_ == 100) state_ = 300;
}

void Player::start() {
   if (state_ == 100) prefetch();
   if (state_ == 300) state_ = 400;
}

void Player::stop() {
   if (state_ == 400) state_ = 300;
}

void Player::close() { state_ = 0; }

void Player::setMediaTime(int64_t /*ms*/) {}

void Player::fireEvent(const String& event) {
   if (listener_) listener_->playerUpdate(this, event, nullptr);
}

VolumeControl* Player::getControl(const String& type) {
   if (type.equals(String("VolumeControl"))) return &volume_;
   return nullptr;
}

Player* Manager::createPlayer(InputStream* in, const String& type) {
   return createPlatformPlayer(in, type);
}
