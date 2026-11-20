// jrt.h — minimal Java runtime for the code produced by tools/java2cpp.py.
//
// Own implementation (not copied from any other port). Provides the Java
// types the translated game code needs: nullable bounds-checked arrays
// (Arr<T>), an immutable UTF-16 String, StringBuffer, the JDK Random LCG,
// big-endian DataInput/DataOutput streams, Thread/Runnable, and the
// JavaClass.getResourceAsStream hook (defined in platform.cpp, where the
// data directory lives).
//
// Java semantics preserved on purpose:
//   - Arr<T> is nullable and shared (assignment aliases, like Java).
//   - String == is reference equality (like Java); equals() is value equality.
//   - String + int/long/bool/char follows Java concatenation rules
//     ("a" + 1 == "a1", "a" + null == "anull").
//   - byte/short arithmetic promotes to int; narrowing casts truncate mod 256.
//   - Array bounds violations and reads past EOF throw JavaException,
//     which the translated try/catch blocks catch as (Exception).

#pragma once

#include <cstdint>
#include <cstring>
#include <memory>
#include <mutex>
#include <string>
#include <thread>
#include <vector>

namespace jrt {

// ---------------------------------------------------------------------------
// JavaException — stands in for Java exceptions (ArrayIndexOutOfBounds,
// EOFException, NullPointerException, and explicit `throw`).
// ---------------------------------------------------------------------------
struct JavaException {
   const char* what;
};

// ---------------------------------------------------------------------------
// Arr<T> — a Java array: nullable, bounds-checked, shared_ptr-backed so
// assignment aliases (like Java). operator[] throws JavaException on a null
// array or an out-of-range index.
// ---------------------------------------------------------------------------
template <typename T>
struct ArrVec {
   using type = std::vector<T>;
};
// std::vector<bool> is bit-packed and operator[] returns a proxy, not a
// reference, so boolean arrays are stored as bytes instead.
template <>
struct ArrVec<bool> {
   using type = std::vector<unsigned char>;
};

template <typename T>
class Arr {
   using Vec = typename ArrVec<T>::type;
   using Elem = typename Vec::value_type;

  public:
   Arr() = default;
   explicit Arr(size_t n) : data_(std::make_shared<Vec>(n)) {}
   Arr(std::initializer_list<T> init)
       : data_(std::make_shared<Vec>(init.begin(), init.end())) {}

   bool isNull() const { return data_ == nullptr; }
   int size() const {
      if (!data_) throw JavaException{"NullPointerException"};
      return (int)data_->size();
   }
   bool empty() const { return !data_ || data_->empty(); }

   Elem& operator[](int i) {
      if (!data_) throw JavaException{"NullPointerException"};
      if (i < 0 || (size_t)i >= data_->size())
         throw JavaException{"ArrayIndexOutOfBounds"};
      return (*data_)[(size_t)i];
   }
   const Elem& operator[](int i) const {
      if (!data_) throw JavaException{"NullPointerException"};
      if (i < 0 || (size_t)i >= data_->size())
         throw JavaException{"ArrayIndexOutOfBounds"};
      return (*data_)[(size_t)i];
   }

   bool operator==(const Arr& other) const { return data_ == other.data_; }
   bool operator!=(const Arr& other) const { return data_ != other.data_; }
   bool operator==(std::nullptr_t) const { return data_ == nullptr; }
   bool operator!=(std::nullptr_t) const { return data_ != nullptr; }
   Arr& operator=(std::nullptr_t) {
      data_ = nullptr;
      return *this;
   }

   // Raw access for arraycopy/memcpy helpers (bounds already checked by caller).
   Vec* vec() { return data_.get(); }
   const Vec* vec() const { return data_.get(); }

  private:
   std::shared_ptr<Vec> data_;
};

template <typename T>
Arr<T> newArr(int n) {
   return Arr<T>((size_t)n);
}
// new T[]{...} — array initializer. Elements are copied (Arr elements
// share their data, like Java references).
template <typename T>
Arr<T> newArr(std::initializer_list<T> init) {
   Arr<T> out(init.size());
   int i = 0;
   for (const T& v : init) out[i++] = v;
   return out;
}
// new T[n][m] — an array of n arrays, each of length m.
template <typename T>
Arr<Arr<T>> newArr2(int n, int m) {
   Arr<Arr<T>> out((size_t)n);
   for (int i = 0; i < n; i++) out[i] = newArr<T>(m);
   return out;
}
// new T[n][m][k]
template <typename T>
Arr<Arr<Arr<T>>> newArr3(int n, int m, int k) {
   Arr<Arr<Arr<T>>> out((size_t)n);
   for (int i = 0; i < n; i++) out[i] = newArr2<T>(m, k);
   return out;
}

// System.arraycopy — overlap-safe (memmove semantics), like Java.
template <typename T>
void arraycopy(const Arr<T>& src, int srcPos, Arr<T>& dest, int destPos,
               int length) {
   if (length <= 0) return;
   if (src.isNull() || dest.isNull())
      throw JavaException{"NullPointerException"};
   if (srcPos < 0 || destPos < 0 || length < 0 ||
       srcPos + length > src.size() || destPos + length > dest.size())
      throw JavaException{"ArrayIndexOutOfBounds"};
   // Copy element by element so overlapping ranges behave like memmove.
   if (src.vec() == dest.vec() && destPos > srcPos) {
      for (int i = length - 1; i >= 0; i--) dest[destPos + i] = src[srcPos + i];
   } else {
      for (int i = 0; i < length; i++) dest[destPos + i] = src[srcPos + i];
   }
}

// ---------------------------------------------------------------------------
// String — immutable UTF-16 value type. Default-constructed is Java null.
// == is reference equality (like Java); equals() is value equality.
// ---------------------------------------------------------------------------
class String {
  public:
   String() = default;
   String(std::nullptr_t) {}
   String(const char* utf8) { assignUtf8(utf8); }
   String(const std::u16string& s)
       : data_(std::make_shared<const std::u16string>(s)) {}
   String(std::u16string&& s)
       : data_(std::make_shared<const std::u16string>(std::move(s))) {}
   explicit String(char16_t c) : data_(std::make_shared<const std::u16string>(1, c)) {}

   bool isNull() const { return data_ == nullptr; }
   int length() const {
      if (!data_) throw JavaException{"NullPointerException"};
      return (int)data_->size();
   }
   char16_t charAt(int i) const {
      if (!data_) throw JavaException{"NullPointerException"};
      if (i < 0 || (size_t)i >= data_->size())
         throw JavaException{"StringIndexOutOfBounds"};
      return (*data_)[(size_t)i];
   }
   bool equals(const String& other) const {
      if (data_ == other.data_) return true;
      if (!data_ || !other.data_) return false;
      return *data_ == *other.data_;
   }
   String substring(int begin) const;
   String substring(int begin, int end) const;
   int indexOf(char16_t c) const;
   Arr<char16_t> toCharArray() const;

   // Java concatenation: null renders as the four letters "null".
   String concat(const String& other) const;

   bool operator==(const String& other) const { return data_ == other.data_; }
   bool operator!=(const String& other) const { return data_ != other.data_; }
   bool operator==(std::nullptr_t) const { return data_ == nullptr; }
   bool operator!=(std::nullptr_t) const { return data_ != nullptr; }
   String& operator=(std::nullptr_t) {
      data_ = nullptr;
      return *this;
   }

   static String valueOf(int v);
   static String valueOf(int64_t v);
   static String valueOf(bool v);
   static String valueOf(char16_t v);

   // UTF-8 rendering (for file paths and logging). Null renders as "null".
   std::string toUtf8() const;

   // Direct UTF-16 access (for StringBuffer). Null yields an empty string.
   const std::u16string& utf16() const {
      static const std::u16string kEmpty;
      return data_ ? *data_ : kEmpty;
   }

  private:
   void assignUtf8(const char* utf8);
   std::shared_ptr<const std::u16string> data_;
};

inline String jstr(const char* utf8) { return String(utf8); }
inline String jstr(char16_t c) { return String(c); }

inline String operator+(const String& a, const String& b) { return a.concat(b); }
inline String operator+(const String& a, const char* b) { return a.concat(String(b)); }
inline String operator+(const char* a, const String& b) { return String(a).concat(b); }
inline String operator+(const String& a, int b) { return a.concat(String::valueOf(b)); }
inline String operator+(int a, const String& b) { return String::valueOf(a).concat(b); }
inline String operator+(const String& a, int64_t b) { return a.concat(String::valueOf(b)); }
inline String operator+(int64_t a, const String& b) { return String::valueOf(a).concat(b); }
inline String operator+(const String& a, bool b) { return a.concat(String::valueOf(b)); }
inline String operator+(const String& a, char16_t b) { return a.concat(String::valueOf(b)); }

// ---------------------------------------------------------------------------
// StringBuffer — mutable UTF-16 buffer; append() returns `this` (a pointer)
// so that `new StringBuffer()->append(...)->append(...)` chains in C++ the
// way it does in Java (where append returns the StringBuffer reference).
// ---------------------------------------------------------------------------
class StringBuffer {
  public:
   StringBuffer() = default;
   StringBuffer* append(const String& s);
   StringBuffer* append(const char* s);
   StringBuffer* append(int v);
   StringBuffer* append(int64_t v);
   StringBuffer* append(bool v);
   StringBuffer* append(char16_t v);
   String toString() const;
   int length() const { return (int)buf_.size(); }

  private:
   std::u16string buf_;
};

// ---------------------------------------------------------------------------
// jlang — java.lang stand-ins used by the translated code.
// ---------------------------------------------------------------------------
namespace jlang {
template <typename T>
T abs(T v) {
   return v < 0 ? -v : v;
}
template <typename T>
T max(T a, T b) {
   return a > b ? a : b;
}
template <typename T>
T min(T a, T b) {
   return a < b ? a : b;
}
int64_t currentTimeMillis();
inline void gc() {}  // System.gc() — no-op; the C++ heap needs no hint.
}  // namespace jlang

// ---------------------------------------------------------------------------
// Random — the same LCG as java.util.Random (seed ^ 0x5DEECE66D, 48 bits).
// The game constructs it with a fixed seed of 1, so sequences are
// deterministic and identical to the original.
// ---------------------------------------------------------------------------
class Random {
  public:
   explicit Random(int64_t seed);
   int nextInt();
   int nextInt(int bound);
   bool nextBoolean();
   int64_t nextLong();

  private:
   int next(int bits);
   int64_t seed_;
};

// ---------------------------------------------------------------------------
// Streams — java.io stand-ins. DataInputStream reads eagerly: its
// constructor drains the wrapped InputStream into its own buffer, so the
// (usually temporary) InputStream can be discarded afterwards.
// ---------------------------------------------------------------------------
class InputStream {
  public:
   virtual ~InputStream() = default;
   virtual int read() {
      Arr<int8_t> one = newArr<int8_t>(1);
      int n = read(one, 0, 1);
      return n <= 0 ? -1 : (one[0] & 0xFF);
   }
   virtual int read(Arr<int8_t> b) { return read(b, 0, b.size()); }
   virtual int read(Arr<int8_t> b, int off, int len) = 0;
   virtual void close() {}
   virtual int available() { return 0; }
};

class ByteArrayInputStream : public InputStream {
  public:
   explicit ByteArrayInputStream(Arr<int8_t> buf);
   ByteArrayInputStream(Arr<int8_t> buf, int off, int len);
   int read() override;
   int read(Arr<int8_t> b, int off, int len) override;
   int available() override;

  private:
   Arr<int8_t> buf_;
   int pos_ = 0;
   int count_ = 0;
};

// Reads a whole file into memory at construction.
class FileInputStream : public InputStream {
  public:
   explicit FileInputStream(const std::string& path);
   bool opened() const { return opened_; }
   int read() override;
   int read(Arr<int8_t> b, int off, int len) override;
   int available() override;

  private:
   std::vector<uint8_t> buf_;
   size_t pos_ = 0;
   bool opened_ = false;
};

class OutputStream {
  public:
   virtual ~OutputStream() = default;
   virtual void write(int b) = 0;
   virtual void write(Arr<int8_t> b) { write(b, 0, b.size()); }
   virtual void write(Arr<int8_t> b, int off, int len) {
      for (int i = 0; i < len; i++) write(b[off + i]);
   }
   virtual void flush() {}
   virtual void close() {}
};

class ByteArrayOutputStream : public OutputStream {
  public:
   void write(int b) override;
   void write(Arr<int8_t> b) override;
   void write(Arr<int8_t> b, int off, int len) override;
   int size() const { return (int)buf_.size(); }
   Arr<int8_t> toByteArray() const;

  private:
   std::vector<uint8_t> buf_;
};

class DataInputStream {
  public:
   explicit DataInputStream(InputStream* in);
   int read();
   int read(Arr<int8_t> b);
   int read(Arr<int8_t> b, int off, int len);
   int8_t readByte();
   int readUnsignedByte();
   int16_t readShort();
   int readUnsignedShort();
   int readInt();
   int64_t readLong();
   bool readBoolean();
   char16_t readChar();
   void readFully(Arr<int8_t> b);
   int skip(int n);
   int available() const;
   void close() {}

  private:
   uint8_t need();
   std::vector<uint8_t> buf_;
   size_t pos_ = 0;
};

class DataOutputStream {
  public:
   explicit DataOutputStream(OutputStream* out) : out_(out) {}
   void write(int b);
   void write(Arr<int8_t> b);
   void write(Arr<int8_t> b, int off, int len);
   void writeByte(int v);
   void writeShort(int v);
   void writeInt(int v);
   void writeLong(int64_t v);
   void writeBoolean(bool v);
   void writeChar(char16_t v);
   void flush();
   void close();

  private:
   OutputStream* out_;
};

// ---------------------------------------------------------------------------
// Runnable / Thread — java.lang stand-ins. Thread::start() launches a real
// std::thread; Thread::joinAll() waits for every started thread (the shell
// calls it before exiting).
// ---------------------------------------------------------------------------
struct Runnable {
   virtual ~Runnable() = default;
   virtual void run() = 0;
};

class Thread {
  public:
   explicit Thread(Runnable* target) : target_(target) {}
   void start();
   static void yield();
   static void sleep(int64_t ms);
   static void joinAll();

  private:
   Runnable* target_;
   static std::vector<std::thread> threads_;
   static std::mutex threadsMutex_;
};

// ---------------------------------------------------------------------------
// JavaClass — the Class.getResourceAsStream() hook. Defined in platform.cpp
// (it needs Platform::dataDir). Returns nullptr when the file is missing.
// ---------------------------------------------------------------------------
class JavaClass {
  public:
   static JavaClass* instance();
   InputStream* getResourceAsStream(const String& name);

  private:
   JavaClass() = default;
};

}  // namespace jrt
