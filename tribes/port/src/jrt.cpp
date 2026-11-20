// jrt.cpp — implementations for jrt.h (see the header for the design notes).

#include "jrt.h"

#include <chrono>
#include <cstdio>

namespace jrt {

// ---------------------------------------------------------------------------
// String
// ---------------------------------------------------------------------------
void String::assignUtf8(const char* utf8) {
   if (!utf8) {
      data_ = nullptr;
      return;
   }
   std::u16string out;
   const unsigned char* p = (const unsigned char*)utf8;
   while (*p) {
      char32_t cp;
      if (*p < 0x80) {
         cp = *p++;
      } else if ((*p & 0xE0) == 0xC0) {
         cp = (*p & 0x1F) << 6;
         p++;
         cp |= (*p & 0x3F);
         p++;
      } else if ((*p & 0xF0) == 0xE0) {
         cp = (*p & 0x0F) << 12;
         p++;
         cp |= (*p & 0x3F) << 6;
         p++;
         cp |= (*p & 0x3F);
         p++;
      } else {
         cp = (*p & 0x07) << 18;
         p++;
         cp |= (*p & 0x3F) << 12;
         p++;
         cp |= (*p & 0x3F) << 6;
         p++;
         cp |= (*p & 0x3F);
         p++;
      }
      if (cp > 0xFFFF) {
         cp -= 0x10000;
         out.push_back((char16_t)(0xD800 + (cp >> 10)));
         out.push_back((char16_t)(0xDC00 + (cp & 0x3FF)));
      } else {
         out.push_back((char16_t)cp);
      }
   }
   data_ = std::make_shared<const std::u16string>(std::move(out));
}

std::string String::toUtf8() const {
   if (!data_) return "null";
   std::string out;
   const std::u16string& s = *data_;
   for (size_t i = 0; i < s.size(); i++) {
      char32_t cp = s[i];
      if (cp >= 0xD800 && cp <= 0xDBFF && i + 1 < s.size() &&
          s[i + 1] >= 0xDC00 && s[i + 1] <= 0xDFFF) {
         cp = 0x10000 + ((cp - 0xD800) << 10) + (s[i + 1] - 0xDC00);
         i++;
      }
      if (cp < 0x80) {
         out.push_back((char)cp);
      } else if (cp < 0x800) {
         out.push_back((char)(0xC0 | (cp >> 6)));
         out.push_back((char)(0x80 | (cp & 0x3F)));
      } else if (cp < 0x10000) {
         out.push_back((char)(0xE0 | (cp >> 12)));
         out.push_back((char)(0x80 | ((cp >> 6) & 0x3F)));
         out.push_back((char)(0x80 | (cp & 0x3F)));
      } else {
         out.push_back((char)(0xF0 | (cp >> 18)));
         out.push_back((char)(0x80 | ((cp >> 12) & 0x3F)));
         out.push_back((char)(0x80 | ((cp >> 6) & 0x3F)));
         out.push_back((char)(0x80 | (cp & 0x3F)));
      }
   }
   return out;
}

String String::concat(const String& other) const {
   if (isNull() && other.isNull()) return String("nullnull");
   if (isNull()) return String("null") + other;
   if (other.isNull()) return *this + String("null");
   return String(*data_ + *other.data_);
}

String String::substring(int begin) const {
   if (!data_) throw JavaException{"NullPointerException"};
   if (begin < 0 || (size_t)begin > data_->size())
      throw JavaException{"StringIndexOutOfBounds"};
   return String(data_->substr((size_t)begin));
}

String String::substring(int begin, int end) const {
   if (!data_) throw JavaException{"NullPointerException"};
   if (begin < 0 || end < begin || (size_t)end > data_->size())
      throw JavaException{"StringIndexOutOfBounds"};
   return String(data_->substr((size_t)begin, (size_t)(end - begin)));
}

int String::indexOf(char16_t c) const {
   if (!data_) throw JavaException{"NullPointerException"};
   size_t pos = data_->find(c);
   return pos == std::u16string::npos ? -1 : (int)pos;
}

Arr<char16_t> String::toCharArray() const {
   if (!data_) throw JavaException{"NullPointerException"};
   Arr<char16_t> out((size_t)data_->size());
   for (size_t i = 0; i < data_->size(); i++) out[(int)i] = (*data_)[i];
   return out;
}

String String::valueOf(int v) { return String(std::to_string(v).c_str()); }
String String::valueOf(int64_t v) { return String(std::to_string(v).c_str()); }
String String::valueOf(bool v) { return String(v ? "true" : "false"); }
String String::valueOf(char16_t v) { return String(v); }

// ---------------------------------------------------------------------------
// StringBuffer
// ---------------------------------------------------------------------------
StringBuffer* StringBuffer::append(const String& s) {
   if (s.isNull()) {
      const char* n = "null";
      while (*n) buf_.push_back((char16_t)*n++);
   } else {
      buf_ += s.utf16();
   }
   return this;
}
StringBuffer* StringBuffer::append(const char* s) {
   if (s) {
      while (*s) buf_.push_back((char16_t)(unsigned char)*s++);
   } else {
      const char* n = "null";
      while (*n) buf_.push_back((char16_t)*n++);
   }
   return this;
}
StringBuffer* StringBuffer::append(int v) { return append(String::valueOf(v)); }
StringBuffer* StringBuffer::append(int64_t v) { return append(String::valueOf(v)); }
StringBuffer* StringBuffer::append(bool v) { return append(String::valueOf(v)); }
StringBuffer* StringBuffer::append(char16_t v) {
   buf_.push_back(v);
   return this;
}
String StringBuffer::toString() const { return String(buf_); }

// ---------------------------------------------------------------------------
// jlang
// ---------------------------------------------------------------------------
namespace jlang {
int64_t currentTimeMillis() {
   using namespace std::chrono;
   return duration_cast<milliseconds>(system_clock::now().time_since_epoch())
       .count();
}
}  // namespace jlang

// ---------------------------------------------------------------------------
// Random — JDK LCG.
// ---------------------------------------------------------------------------
Random::Random(int64_t seed) : seed_((seed ^ 0x5DEECE66DLL) & ((1LL << 48) - 1)) {}

int Random::next(int bits) {
   seed_ = (seed_ * 0x5DEECE66DLL + 0xBLL) & ((1LL << 48) - 1);
   return (int)(seed_ >> (48 - bits));
}

int Random::nextInt() { return next(32); }

int Random::nextInt(int bound) {
   if (bound <= 0) throw JavaException{"IllegalArgumentException"};
   int r = next(31);
   int m = bound - 1;
   if ((bound & m) == 0) {
      return (int)(((int64_t)bound * (int64_t)r) >> 31);
   }
   int u;
   for (u = r; u - (r = u % bound) + m < 0; u = next(31)) {
   }
   return r;
}

bool Random::nextBoolean() { return next(1) != 0; }

int64_t Random::nextLong() {
   return ((int64_t)next(32) << 32) + next(32);
}

// ---------------------------------------------------------------------------
// Streams
// ---------------------------------------------------------------------------
ByteArrayInputStream::ByteArrayInputStream(Arr<int8_t> buf)
    : buf_(buf), pos_(0), count_(buf.isNull() ? 0 : buf.size()) {}

ByteArrayInputStream::ByteArrayInputStream(Arr<int8_t> buf, int off, int len)
    : buf_(buf), pos_(off), count_(off + len) {}

int ByteArrayInputStream::read() {
   if (pos_ >= count_) return -1;
   return buf_[pos_++] & 0xFF;
}

int ByteArrayInputStream::read(Arr<int8_t> b, int off, int len) {
   if (b.isNull()) throw JavaException{"NullPointerException"};
   if (pos_ >= count_) return -1;
   int n = len;
   if (pos_ + n > count_) n = count_ - pos_;
   for (int i = 0; i < n; i++) b[off + i] = buf_[pos_ + i];
   pos_ += n;
   return n;
}

int ByteArrayInputStream::available() { return count_ - pos_; }

FileInputStream::FileInputStream(const std::string& path) {
   FILE* f = std::fopen(path.c_str(), "rb");
   if (!f) return;
   std::fseek(f, 0, SEEK_END);
   long size = std::ftell(f);
   std::fseek(f, 0, SEEK_SET);
   if (size > 0) {
      buf_.resize((size_t)size);
      size_t got = std::fread(buf_.data(), 1, buf_.size(), f);
      buf_.resize(got);
   }
   std::fclose(f);
   opened_ = true;
}

int FileInputStream::read() {
   if (pos_ >= buf_.size()) return -1;
   return buf_[pos_++];
}

int FileInputStream::read(Arr<int8_t> b, int off, int len) {
   if (b.isNull()) throw JavaException{"NullPointerException"};
   if (pos_ >= buf_.size()) return -1;
   int n = len;
   if (pos_ + (size_t)n > buf_.size()) n = (int)(buf_.size() - pos_);
   for (int i = 0; i < n; i++) b[off + i] = (int8_t)buf_[pos_ + i];
   pos_ += (size_t)n;
   return n;
}

int FileInputStream::available() { return (int)(buf_.size() - pos_); }

void ByteArrayOutputStream::write(int b) { buf_.push_back((uint8_t)(b & 0xFF)); }

void ByteArrayOutputStream::write(Arr<int8_t> b) { write(b, 0, b.size()); }

void ByteArrayOutputStream::write(Arr<int8_t> b, int off, int len) {
   for (int i = 0; i < len; i++) buf_.push_back((uint8_t)(b[off + i] & 0xFF));
}

Arr<int8_t> ByteArrayOutputStream::toByteArray() const {
   Arr<int8_t> out((size_t)buf_.size());
   for (size_t i = 0; i < buf_.size(); i++) out[(int)i] = (int8_t)buf_[i];
   return out;
}

DataInputStream::DataInputStream(InputStream* in) {
   if (in) {
      Arr<int8_t> chunk = newArr<int8_t>(4096);
      int n;
      while ((n = in->read(chunk, 0, 4096)) > 0) {
         for (int i = 0; i < n; i++) buf_.push_back((uint8_t)(chunk[i] & 0xFF));
      }
   }
}

uint8_t DataInputStream::need() {
   if (pos_ >= buf_.size()) throw JavaException{"EOF"};
   return buf_[pos_++];
}

int DataInputStream::read() {
   if (pos_ >= buf_.size()) return -1;
   return buf_[pos_++];
}

int DataInputStream::read(Arr<int8_t> b) { return read(b, 0, b.size()); }

int DataInputStream::read(Arr<int8_t> b, int off, int len) {
   if (b.isNull()) throw JavaException{"NullPointerException"};
   if (pos_ >= buf_.size()) return -1;
   int n = len;
   if (pos_ + (size_t)n > buf_.size()) n = (int)(buf_.size() - pos_);
   for (int i = 0; i < n; i++) b[off + i] = (int8_t)buf_[pos_ + i];
   pos_ += (size_t)n;
   return n;
}

int8_t DataInputStream::readByte() { return (int8_t)need(); }

int DataInputStream::readUnsignedByte() { return need(); }

int16_t DataInputStream::readShort() {
   int v = (need() << 8) | need();
   return (int16_t)v;
}

int DataInputStream::readUnsignedShort() {
   return (need() << 8) | need();
}

int DataInputStream::readInt() {
   int v = (need() << 24) | (need() << 16) | (need() << 8) | need();
   return v;
}

int64_t DataInputStream::readLong() {
   int64_t v = 0;
   for (int i = 0; i < 8; i++) v = (v << 8) | need();
   return v;
}

bool DataInputStream::readBoolean() { return need() != 0; }

char16_t DataInputStream::readChar() { return (char16_t)readUnsignedShort(); }

void DataInputStream::readFully(Arr<int8_t> b) {
   if (read(b, 0, b.size()) != b.size()) throw JavaException{"EOF"};
}

int DataInputStream::skip(int n) {
   int skipped = 0;
   while (skipped < n && pos_ < buf_.size()) {
      pos_++;
      skipped++;
   }
   return skipped;
}

int DataInputStream::available() const { return (int)(buf_.size() - pos_); }

void DataOutputStream::write(int b) { out_->write(b); }

void DataOutputStream::write(Arr<int8_t> b) { out_->write(b); }

void DataOutputStream::write(Arr<int8_t> b, int off, int len) {
   out_->write(b, off, len);
}

void DataOutputStream::writeByte(int v) { out_->write(v & 0xFF); }

void DataOutputStream::writeShort(int v) {
   out_->write((v >> 8) & 0xFF);
   out_->write(v & 0xFF);
}

void DataOutputStream::writeInt(int v) {
   out_->write((v >> 24) & 0xFF);
   out_->write((v >> 16) & 0xFF);
   out_->write((v >> 8) & 0xFF);
   out_->write(v & 0xFF);
}

void DataOutputStream::writeLong(int64_t v) {
   for (int i = 7; i >= 0; i--) out_->write((int)((v >> (i * 8)) & 0xFF));
}

void DataOutputStream::writeBoolean(bool v) { out_->write(v ? 1 : 0); }

void DataOutputStream::writeChar(char16_t v) { writeShort(v); }

void DataOutputStream::flush() { out_->flush(); }

void DataOutputStream::close() { out_->close(); }

// ---------------------------------------------------------------------------
// Thread
// ---------------------------------------------------------------------------
std::vector<std::thread> Thread::threads_;
std::mutex Thread::threadsMutex_;

void Thread::start() {
   Runnable* target = target_;
   std::lock_guard<std::mutex> lock(threadsMutex_);
   threads_.emplace_back([target]() { target->run(); });
}

void Thread::yield() { std::this_thread::yield(); }

void Thread::sleep(int64_t ms) {
   if (ms > 0) std::this_thread::sleep_for(std::chrono::milliseconds(ms));
}

void Thread::joinAll() {
   std::vector<std::thread> copy;
   {
      std::lock_guard<std::mutex> lock(threadsMutex_);
      copy.swap(threads_);
   }
   for (auto& t : copy) {
      if (t.joinable()) t.join();
   }
}

// ---------------------------------------------------------------------------
// JavaClass — defined here except getResourceAsStream (platform.cpp).
// ---------------------------------------------------------------------------
JavaClass* JavaClass::instance() {
   static JavaClass inst;
   return &inst;
}

}  // namespace jrt
