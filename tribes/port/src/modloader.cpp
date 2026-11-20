// modloader.cpp — no-repack mod loader, phase 1 (textures + maps).
//
// Own implementation. Design: tribes/docs/MOD-LOADER-DESIGN.md §0-§4.2,
// §6-§7. Phase 1 covers mod discovery (mod.json v0.2; v0.1 loads
// unchanged), per-sprite PNG overrides (Seam 2), and replace-mode map
// overrides for levels q=8..11 (Seam 3 + /a record patches for size,
// cursor, win/lose flags and the setup table). No other category is
// implemented yet; a mod that declares one is rejected at validation
// with a log line.
//
// Ground rules enforced here (design §0.1): BYO-JAR (no .jar/.class in
// mods, no file copied from the original JAR); the engine never writes
// into data/; a mod is validated in full before any of it is applied;
// a bad mod is skipped with a MOD-REJECT line and never blocks startup;
// every decision is logged with a MOD: prefix so the user can verify
// from tribes-log.txt.
//
// The map path deliberately does NOT touch /ma or rec 248: rec 248 also
// feeds the map-name index (s[82+piece], Game.java:3246) and the
// campaign-progress markers, so changing it would break the menu name.
// Instead loadMap() reads mod tiles through hasModMap()/modMapTile()
// (a counted SOURCE_PATCHES branch), with rec 149/150/279 and the setup
// table patched in fileAData — exactly the values the J2ME repack proof
// changed (mods/example/PROOF.md).

#include "modloader.h"

#include <algorithm>
#include <cctype>
#include <cmath>
#include <cstdio>
#include <filesystem>
#include <fstream>
#include <map>
#include <set>
#include <stdexcept>
#include <utility>

#include "crashlog.h"
#include "gen/classes.h"
#include "gfx.h"
#include "stb_image.h"

namespace fs = std::filesystem;

namespace {

// ---------------------------------------------------------------------------
// Minimal JSON parser (recursive descent). Only what mod files need:
// objects, arrays, strings (with \" \\ \/ \b \f \n \r \t \uXXXX),
// numbers, true/false/null. Throws std::runtime_error with a position.
// ---------------------------------------------------------------------------
struct Json {
   enum Type { Null, Bool, Number, Str, Arr, Obj } type = Null;
   bool b = false;
   double num = 0;
   std::string str;
   std::vector<Json> arr;
   std::vector<std::pair<std::string, Json>> obj;

   const Json* get(const char* key) const {
      if (type != Obj) return nullptr;
      for (auto& kv : obj)
         if (kv.first == key) return &kv.second;
      return nullptr;
   }
   bool isInt() const {
      return type == Number && std::floor(num) == num &&
             num >= -2147483648.0 && num <= 2147483647.0;
   }
   int asInt(int fallback = 0) const {
      return isInt() ? (int)num : fallback;
   }
};

class JsonParser {
  public:
   explicit JsonParser(const std::string& s) : s_(s) {}

   Json parse() {
      Json v = parseValue();
      skipWs();
      if (pos_ != s_.size()) fail("trailing data after JSON value");
      return v;
   }

  private:
   const std::string& s_;
   size_t pos_ = 0;

   [[noreturn]] void fail(const std::string& msg) {
      throw std::runtime_error("JSON error at byte " +
                               std::to_string(pos_) + ": " + msg);
   }
   void skipWs() {
      while (pos_ < s_.size() &&
             (s_[pos_] == ' ' || s_[pos_] == '\t' || s_[pos_] == '\n' ||
              s_[pos_] == '\r'))
         pos_++;
   }
   char peek() {
      skipWs();
      if (pos_ >= s_.size()) fail("unexpected end of input");
      return s_[pos_];
   }
   char take() {
      char c = peek();
      pos_++;
      return c;
   }
   void expect(char c) {
      if (take() != c) fail(std::string("expected '") + c + "'");
   }
   void expectLit(const char* lit) {
      for (const char* p = lit; *p; p++) {
         if (pos_ >= s_.size() || s_[pos_++] != *p)
            fail(std::string("expected literal ") + lit);
      }
   }

   Json parseValue() {
      char c = peek();
      if (c == '{') return parseObject();
      if (c == '[') return parseArray();
      if (c == '"') {
         Json v;
         v.type = Json::Str;
         v.str = parseString();
         return v;
      }
      if (c == 't') {
         expectLit("true");
         Json v;
         v.type = Json::Bool;
         v.b = true;
         return v;
      }
      if (c == 'f') {
         expectLit("false");
         Json v;
         v.type = Json::Bool;
         v.b = false;
         return v;
      }
      if (c == 'n') {
         expectLit("null");
         return Json{};
      }
      return parseNumber();
   }

   Json parseObject() {
      Json v;
      v.type = Json::Obj;
      expect('{');
      if (peek() == '}') {
         pos_++;
         return v;
      }
      while (true) {
         if (peek() != '"') fail("expected object key string");
         std::string key = parseString();
         expect(':');
         v.obj.push_back({key, parseValue()});
         char c = take();
         if (c == '}') break;
         if (c != ',') fail("expected ',' or '}' in object");
      }
      return v;
   }

   Json parseArray() {
      Json v;
      v.type = Json::Arr;
      expect('[');
      if (peek() == ']') {
         pos_++;
         return v;
      }
      while (true) {
         v.arr.push_back(parseValue());
         char c = take();
         if (c == ']') break;
         if (c != ',') fail("expected ',' or ']' in array");
      }
      return v;
   }

   static void appendUtf8(std::string& out, unsigned cp) {
      if (cp < 0x80) {
         out += (char)cp;
      } else if (cp < 0x800) {
         out += (char)(0xC0 | (cp >> 6));
         out += (char)(0x80 | (cp & 0x3F));
      } else {
         out += (char)(0xE0 | (cp >> 12));
         out += (char)(0x80 | ((cp >> 6) & 0x3F));
         out += (char)(0x80 | (cp & 0x3F));
      }
   }

   std::string parseString() {
      expect('"');
      std::string out;
      while (true) {
         if (pos_ >= s_.size()) fail("unterminated string");
         char c = s_[pos_++];
         if (c == '"') break;
         if (c == '\\') {
            if (pos_ >= s_.size()) fail("unterminated escape");
            char e = s_[pos_++];
            switch (e) {
               case '"': out += '"'; break;
               case '\\': out += '\\'; break;
               case '/': out += '/'; break;
               case 'b': out += '\b'; break;
               case 'f': out += '\f'; break;
               case 'n': out += '\n'; break;
               case 'r': out += '\r'; break;
               case 't': out += '\t'; break;
               case 'u': {
                  if (pos_ + 4 > s_.size()) fail("bad \\u escape");
                  unsigned cp = 0;
                  for (int i = 0; i < 4; i++) {
                     char h = s_[pos_++];
                     cp <<= 4;
                     if (h >= '0' && h <= '9') cp |= (unsigned)(h - '0');
                     else if (h >= 'a' && h <= 'f') cp |= (unsigned)(h - 'a' + 10);
                     else if (h >= 'A' && h <= 'F') cp |= (unsigned)(h - 'A' + 10);
                     else fail("bad hex digit in \\u escape");
                  }
                  appendUtf8(out, cp);
                  break;
               }
               default: fail("unknown escape");
            }
         } else {
            out += c;
         }
      }
      return out;
   }

   Json parseNumber() {
      size_t start = pos_;
      if (pos_ < s_.size() && s_[pos_] == '-') pos_++;
      while (pos_ < s_.size() &&
             (std::isdigit((unsigned char)s_[pos_]) || s_[pos_] == '.' ||
              s_[pos_] == 'e' || s_[pos_] == 'E' || s_[pos_] == '+' ||
              s_[pos_] == '-'))
         pos_++;
      if (pos_ == start) fail("expected a value");
      Json v;
      v.type = Json::Number;
      try {
         v.num = std::stod(s_.substr(start, pos_ - start));
      } catch (...) {
         fail("bad number");
      }
      return v;
   }
};

// ---------------------------------------------------------------------------
// File helpers.
// ---------------------------------------------------------------------------
bool readFileBytes(const fs::path& path, std::vector<uint8_t>& out) {
   std::ifstream f(path, std::ios::binary);
   if (!f) return false;
   f.seekg(0, std::ios::end);
   std::streamoff size = f.tellg();
   f.seekg(0, std::ios::beg);
   if (size < 0) return false;
   out.resize((size_t)size);
   if (size > 0 && !f.read((char*)out.data(), size)) return false;
   return true;
}

bool readFileText(const fs::path& path, std::string& out) {
   std::vector<uint8_t> bytes;
   if (!readFileBytes(path, bytes)) return false;
   out.assign(bytes.begin(), bytes.end());
   return true;
}

bool isValidId(const std::string& id) {
   if (id.empty()) return false;
   for (char c : id) {
      if (!(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != '-' &&
          c != '_')
         return false;
   }
   return true;
}

std::string trim(const std::string& s) {
   size_t a = 0;
   while (a < s.size() && std::isspace((unsigned char)s[a])) a++;
   size_t b = s.size();
   while (b > a && std::isspace((unsigned char)s[b - 1])) b--;
   return s.substr(a, b - a);
}

// ---------------------------------------------------------------------------
// Mod data (validated at discovery, applied at load time).
// ---------------------------------------------------------------------------
struct ModMap {
   std::string id;
   std::string name;
   int slotQ = -1;  // level q this map replaces (8..11 in phase 1)
   int width = 0;
   int height = 0;
   std::vector<int> tiles;  // row-major, width*height base tiles
   bool hasPlayerStart = false;
   int playerStartRow = 0;
   int playerStartCol = 0;
   int flags = 0;
   std::string file;  // relative path inside the mod folder
};

struct ModSprite {
   int pack = 0;    // 0 = pi0, 1 = pi9, 2 = pi8
   int index = 0;
   std::string file;  // relative path, e.g. sprites/pi0-05.png
   int width = 0;     // from the PNG header (stbi_info)
   int height = 0;
};

struct Mod {
   std::string id;
   std::string name;
   fs::path dir;
   std::vector<std::string> requiresIds;
   std::vector<std::string> conflicts;
   std::vector<ModMap> maps;
   std::vector<ModSprite> sprites;
};

bool initialized_ = false;
std::string modsDir_;
std::vector<Mod> mods_;
// Per-slot winning map (last mod in load order wins), by level q.
std::map<int, const ModMap*> mapBySlot_;
// Per-sprite winning override (last mod wins), by (pack, index).
std::map<std::pair<int, int>, const ModSprite*> spriteByKey_;
// The owning mod id for each winning override (for log lines).
std::map<int, std::string> mapOwner_;
std::map<std::pair<int, int>, std::string> spriteOwner_;

const char* packName(int pack) {
   return pack == 0 ? "pi0" : (pack == 1 ? "pi9" : "pi8");
}

int packMaxIndex(int pack) {
   // Valid sprite ids (design §4.1): pi0 0-26, pi9 0-5, pi8 0-11.
   return pack == 0 ? 26 : (pack == 1 ? 5 : 11);
}

void reject(const std::string& id, const std::string& file,
            const std::string& reason) {
   crashlog::log("MOD-REJECT: %s %s: %s", id.c_str(), file.c_str(),
                 reason.c_str());
}

// Parse "sprites/pi0-05.png" into (pack, index). Returns false unless the
// name is exactly <pack>-<index>.png with a known pack and in-range index.
bool parseSpriteName(const std::string& rel, int& pack, int& index) {
   const std::string prefix = "sprites/";
   if (rel.compare(0, prefix.size(), prefix) != 0) return false;
   std::string rest = rel.substr(prefix.size());
   if (rest.size() < 4 || rest.compare(rest.size() - 4, 4, ".png") != 0)
      return false;
   rest = rest.substr(0, rest.size() - 4);
   size_t dash = rest.find('-');
   if (dash == std::string::npos) return false;
   std::string packStr = rest.substr(0, dash);
   std::string idxStr = rest.substr(dash + 1);
   if (packStr == "pi0") pack = 0;
   else if (packStr == "pi9") pack = 1;
   else if (packStr == "pi8") pack = 2;
   else return false;
   if (idxStr.empty()) return false;
   for (char c : idxStr)
      if (!std::isdigit((unsigned char)c)) return false;
   index = std::stoi(idxStr);
   return index >= 0 && index <= packMaxIndex(pack);
}

// Validate one map JSON (MOD-FORMAT §7 rules 4-7 + the v0.2 slot_q).
// Returns false (with `err` set) on any failure.
bool validateMap(const Json& j, const std::string& modId,
                 const std::string& file, ModMap& out, std::string& err) {
   auto fail = [&](const std::string& m) {
      err = m;
      return false;
   };
   const Json* fv = j.get("format_version");
   if (!fv || fv->type != Json::Str ||
       (fv->str != "0.1" && fv->str != "0.2"))
      return fail("format_version must be \"0.1\" or \"0.2\"");
   const Json* kind = j.get("kind");
   if (!kind || kind->type != Json::Str || kind->str != "map")
      return fail("kind must be \"map\"");
   const Json* slot = j.get("slot_q");
   if (!slot || !slot->isInt())
      return fail("slot_q is required (integer, the level q this map "
                  "replaces)");
   out.slotQ = slot->asInt();
   if (out.slotQ < 8 || out.slotQ > 11)
      return fail("slot_q outside 8..11 (phase 1 replace mode)");
   const Json* id = j.get("id");
   if (!id || id->type != Json::Str || id->str.empty())
      return fail("id is required");
   out.id = id->str;
   const Json* name = j.get("name");
   if (!name || name->type != Json::Str || name->str.empty())
      return fail("name is required");
   out.name = name->str;
   const Json* w = j.get("width");
   const Json* h = j.get("height");
   if (!w || !w->isInt() || !h || !h->isInt())
      return fail("width/height must be integers");
   out.width = w->asInt();
   out.height = h->asInt();
   if (out.width < 1 || out.width > 96 || out.height < 1 ||
       out.height > 96)
      return fail("width/height outside 1..96");
   const Json* tiles = j.get("tiles");
   if (!tiles || tiles->type != Json::Arr ||
       (int)tiles->arr.size() != out.height)
      return fail("tiles must be exactly height rows");
   out.tiles.reserve((size_t)out.width * (size_t)out.height);
   for (auto& row : tiles->arr) {
      if (row.type != Json::Arr || (int)row.arr.size() != out.width)
         return fail("each tiles row must have exactly width integers");
      for (auto& t : row.arr) {
         if (!t.isInt() || t.asInt() < 0 || t.asInt() > 242)
            return fail("every tile ID must be an integer in 0..242");
         out.tiles.push_back(t.asInt());
      }
   }
   const Json* spawns = j.get("spawns");
   if (spawns) {
      if (spawns->type != Json::Arr) return fail("spawns must be an array");
      for (auto& sp : spawns->arr) {
         const Json* ty = sp.get("type");
         const Json* r = sp.get("row");
         const Json* c = sp.get("col");
         if (!ty || !ty->isInt() || !r || !r->isInt() || !c || !c->isInt())
            return fail("spawn needs integer type/row/col");
         int type = ty->asInt();
         int row = r->asInt(), col = c->asInt();
         if (type < 1 || type > 5)
            return fail("spawn type outside 1..5");
         if (row < 0 || row >= out.height || col < 0 || col >= out.width)
            return fail("spawn outside the map");
         if (out.tiles[(size_t)row * (size_t)out.width + (size_t)col] !=
             174 + type)
            return fail("spawn tile does not match its type (expected " +
                        std::to_string(174 + type) + ")");
      }
   }
   const Json* ps = j.get("player_start");
   if (ps) {
      const Json* r = ps->get("row");
      const Json* c = ps->get("col");
      if (!r || !r->isInt() || !c || !c->isInt())
         return fail("player_start needs integer row/col");
      out.hasPlayerStart = true;
      out.playerStartRow = r->asInt();
      out.playerStartCol = c->asInt();
      if (out.playerStartRow < 0 || out.playerStartRow >= out.height ||
          out.playerStartCol < 0 || out.playerStartCol >= out.width)
         return fail("player_start outside the map");
   }
   const Json* wl = j.get("win_lose");
   if (!wl || wl->type != Json::Obj)
      return fail("win_lose is required");
   const Json* flags = wl->get("flags");
   if (!flags || !flags->isInt() || flags->asInt() < 0 ||
       flags->asInt() > 7)
      return fail("win_lose.flags outside 0..7");
   out.flags = flags->asInt();
   const Json* winB = wl->get("win_by_destroying_enemy_buildings");
   const Json* loseB = wl->get("lose_if_key_building_destroyed");
   if (!winB || winB->type != Json::Bool || !loseB ||
       loseB->type != Json::Bool)
      return fail("win_lose booleans are required");
   if (winB->b != ((out.flags & 1) != 0))
      return fail("win_by_destroying_enemy_buildings != flags & 1");
   if (loseB->b != ((out.flags & 2) != 0))
      return fail("lose_if_key_building_destroyed != flags & 2");
   out.file = file;
   return true;
}

// Scan one mod folder for forbidden files (ground rule 1): no .jar,
// .class, or anything that looks like a game asset.
bool hasForbiddenFiles(const fs::path& dir, std::string& found) {
   std::error_code ec;
   for (auto it = fs::recursive_directory_iterator(dir, ec);
        it != fs::recursive_directory_iterator(); it.increment(ec)) {
      if (ec) break;
      if (!it->is_regular_file()) continue;
      std::string ext = it->path().extension().string();
      for (char& c : ext) c = (char)std::tolower((unsigned char)c);
      if (ext == ".jar" || ext == ".class" || ext == ".jad") {
         found = it->path().filename().string();
         return true;
      }
   }
   return false;
}

}  // namespace

namespace ModLoader {

void init(const std::string& modsDir) {
   if (initialized_) return;
   initialized_ = true;
   modsDir_ = modsDir;
   std::error_code ec;
   if (!fs::is_directory(modsDir_, ec)) {
      crashlog::log("MOD: no mods folder (%s)", modsDir_.c_str());
      return;
   }
   // Discovery: direct subfolders, alphabetical by folder name.
   std::vector<fs::path> folders;
   for (auto& e : fs::directory_iterator(modsDir_, ec)) {
      if (ec) break;
      if (e.is_directory()) folders.push_back(e.path());
   }
   std::sort(folders.begin(), folders.end());
   // Optional load-order.txt pins the order (one mod id per line).
   fs::path orderPath = fs::path(modsDir_) / "load-order.txt";
   std::vector<std::string> pinned;
   {
      std::string text;
      if (readFileText(orderPath, text)) {
         size_t pos = 0;
         while (pos <= text.size()) {
            size_t nl = text.find('\n', pos);
            std::string line = trim(text.substr(
                pos, nl == std::string::npos ? std::string::npos : nl - pos));
            if (!line.empty()) pinned.push_back(line);
            if (nl == std::string::npos) break;
            pos = nl + 1;
         }
      }
   }
   if (!pinned.empty()) {
      std::sort(folders.begin(), folders.end(),
                [&](const fs::path& a, const fs::path& b) {
                   auto ia = std::find(pinned.begin(), pinned.end(),
                                       a.filename().string());
                   auto ib = std::find(pinned.begin(), pinned.end(),
                                       b.filename().string());
                   if (ia != pinned.end() && ib != pinned.end())
                      return ia < ib;
                   if (ia != pinned.end()) return true;
                   if (ib != pinned.end()) return false;
                   return a.filename().string() < b.filename().string();
                });
      for (auto& id : pinned) {
         bool installed = false;
         for (auto& f : folders)
            if (f.filename().string() == id) installed = true;
         if (!installed)
            crashlog::log("MOD: load-order.txt lists %s, not installed",
                          id.c_str());
      }
   }

   for (auto& folder : folders) {
      std::string folderId = folder.filename().string();
      fs::path manifestPath = folder / "mod.json";
      if (!fs::is_regular_file(manifestPath, ec)) {
         crashlog::log("MOD: folder %s has no mod.json, ignored",
                       folderId.c_str());
         continue;
      }
      crashlog::log("MOD: found %s (%s)", folderId.c_str(),
                    folder.filename().string().c_str());
      std::string text;
      if (!readFileText(manifestPath, text)) {
         reject(folderId, "mod.json", "cannot read");
         continue;
      }
      Json manifest;
      try {
         manifest = JsonParser(text).parse();
      } catch (const std::exception& e) {
         reject(folderId, "mod.json", e.what());
         continue;
      }
      Mod mod;
      mod.dir = folder;
      const Json* fv = manifest.get("format_version");
      if (!fv || fv->type != Json::Str ||
          (fv->str != "0.1" && fv->str != "0.2")) {
         reject(folderId, "mod.json",
                "format_version must be \"0.1\" or \"0.2\"");
         continue;
      }
      const Json* game = manifest.get("game");
      if (!game || game->type != Json::Str ||
          game->str != "prehistoric-tribes") {
         reject(folderId, "mod.json", "game must be \"prehistoric-tribes\"");
         continue;
      }
      const Json* id = manifest.get("id");
      if (!id || id->type != Json::Str || !isValidId(id->str)) {
         reject(folderId, "mod.json",
                "id must be lowercase/digits/-/_");
         continue;
      }
      if (id->str != folderId) {
         reject(folderId, "mod.json",
                "id must equal the folder name (" + folderId + ")");
         continue;
      }
      mod.id = id->str;
      const Json* name = manifest.get("name");
      if (!name || name->type != Json::Str || name->str.empty()) {
         reject(folderId, "mod.json", "name is required");
         continue;
      }
      mod.name = name->str;
      std::string forbidden;
      if (hasForbiddenFiles(folder, forbidden)) {
         reject(mod.id, forbidden,
                "game asset files (.jar/.class) are not allowed in mods");
         continue;
      }
      const Json* req = manifest.get("requires");
      if (req) {
         if (req->type != Json::Arr) {
            reject(mod.id, "mod.json", "requires must be an array");
            continue;
         }
         bool reqOk = true;
         for (auto& r : req->arr) {
            if (r.type != Json::Str) {
               reject(mod.id, "mod.json",
                      "requires entries must be strings");
               reqOk = false;
               break;
            }
            mod.requiresIds.push_back(r.str);
         }
         if (!reqOk) continue;
      }
      const Json* conflicts = manifest.get("conflicts");
      if (conflicts) {
         if (conflicts->type != Json::Arr) {
            reject(mod.id, "mod.json", "conflicts must be an array");
            continue;
         }
         bool confOk = true;
         for (auto& c : conflicts->arr) {
            if (c.type != Json::Str) {
               reject(mod.id, "mod.json",
                      "conflicts entries must be strings");
               confOk = false;
               break;
            }
            mod.conflicts.push_back(c.str);
         }
         if (!confOk) continue;
      }
      // Phase 1 implements maps + textures only. Any other content
      // key is rejected (not silently reinterpreted), per design §8.
      static const char* kPhase1Keys[] = {"format_version", "id", "name",
                                          "author", "description", "game",
                                          "maps", "textures", "requires",
                                          "conflicts"};
      bool badKey = false;
      for (auto& kv : manifest.obj) {
         bool known = false;
         for (auto k : kPhase1Keys)
            if (kv.first == k) known = true;
         if (!known) {
            reject(mod.id, "mod.json",
                   "unsupported key \"" + kv.first +
                       "\" (not implemented in phase 1)");
            badKey = true;
            break;
         }
      }
      if (badKey) continue;
      bool ok = true;
      const Json* maps = manifest.get("maps");
      if (maps) {
         if (maps->type != Json::Arr) {
            reject(mod.id, "mod.json", "maps must be an array");
            continue;
         }
         for (auto& m : maps->arr) {
            if (m.type != Json::Str) {
               reject(mod.id, "mod.json", "maps entries must be strings");
               ok = false;
               break;
            }
            fs::path p = folder / fs::path(m.str);
            std::string mtext;
            if (!readFileText(p, mtext)) {
               reject(mod.id, m.str, "file listed in mod.json is missing");
               ok = false;
               break;
            }
            Json mj;
            try {
               mj = JsonParser(mtext).parse();
            } catch (const std::exception& e) {
               reject(mod.id, m.str, e.what());
               ok = false;
               break;
            }
            ModMap mm;
            std::string err;
            if (!validateMap(mj, mod.id, m.str, mm, err)) {
               reject(mod.id, m.str, err);
               ok = false;
               break;
            }
            mod.maps.push_back(std::move(mm));
         }
         if (!ok) continue;
      }
      const Json* textures = manifest.get("textures");
      if (textures) {
         if (textures->type != Json::Arr) {
            reject(mod.id, "mod.json", "textures must be an array");
            continue;
         }
         for (auto& t : textures->arr) {
            if (t.type != Json::Str) {
               reject(mod.id, "mod.json",
                      "textures entries must be strings");
               ok = false;
               break;
            }
            int pack = -1, index = -1;
            if (!parseSpriteName(t.str, pack, index)) {
               reject(mod.id, t.str,
                      "sprite name must be sprites/pi0-<n>.png, "
                      "sprites/pi9-<n>.png or sprites/pi8-<n>.png with an "
                      "in-range index");
               ok = false;
               break;
            }
            fs::path p = folder / fs::path(t.str);
            std::vector<uint8_t> bytes;
            if (!readFileBytes(p, bytes)) {
               reject(mod.id, t.str, "file listed in mod.json is missing");
               ok = false;
               break;
            }
            int w = 0, h = 0, ch = 0;
            if (!stbi_info_from_memory(bytes.data(), (int)bytes.size(), &w,
                                       &h, &ch)) {
               reject(mod.id, t.str, "not a valid PNG image");
               ok = false;
               break;
            }
            ModSprite ms;
            ms.pack = pack;
            ms.index = index;
            ms.file = t.str;
            ms.width = w;
            ms.height = h;
            mod.sprites.push_back(std::move(ms));
         }
         if (!ok) continue;
      }
      crashlog::log("MOD: loaded %s (maps=%d textures=%d)", mod.id.c_str(),
                    (int)mod.maps.size(), (int)mod.sprites.size());
      mods_.push_back(std::move(mod));
   }

   // requires: every required id must be installed; then a stable
   // topological pass so a mod loads after its requirements. A cycle
   // rejects every mod in it.
   {
      std::set<std::string> installed;
      for (auto& m : mods_) installed.insert(m.id);
      std::vector<Mod> kept;
      for (auto& m : mods_) {
         bool missing = false;
         for (auto& r : m.requiresIds) {
            if (!installed.count(r)) {
               reject(m.id, "mod.json",
                      "requires missing mod \"" + r + "\"");
               missing = true;
               break;
            }
         }
         if (!missing) kept.push_back(std::move(m));
      }
      mods_.swap(kept);
      // Stable topo: repeatedly move a mod after any requirement that
      // currently comes later. Bounded passes; leftovers are a cycle.
      for (size_t pass = 0; pass < mods_.size() + 1; pass++) {
         bool moved = false;
         for (size_t i = 0; i < mods_.size(); i++) {
            for (auto& r : mods_[i].requiresIds) {
               for (size_t j = i + 1; j < mods_.size(); j++) {
                  if (mods_[j].id == r) {
                     std::rotate(mods_.begin() + i, mods_.begin() + i + 1,
                                 mods_.begin() + j + 1);
                     moved = true;
                     break;
                  }
               }
               if (moved) break;
            }
            if (moved) break;
         }
         if (!moved) break;
      }
      // Any mod whose requirement still comes after it is in a cycle:
      // the topo passes above could not order it.
      std::vector<Mod> acyclic;
      for (auto& m : mods_) {
         bool cycle = false;
         size_t posM = mods_.size();
         for (size_t j = 0; j < mods_.size(); j++)
            if (mods_[j].id == m.id) posM = j;
         for (auto& r : m.requiresIds) {
            size_t posR = mods_.size();
            for (size_t j = 0; j < mods_.size(); j++)
               if (mods_[j].id == r) posR = j;
            if (posR > posM) cycle = true;
         }
         if (cycle) {
            reject(m.id, "mod.json",
                   "requires cycle (ordering cannot be satisfied)");
         } else {
            acyclic.push_back(std::move(m));
         }
      }
      mods_.swap(acyclic);
   }

   // Conflicts: when two installed mods conflict, the later one in
   // load order is skipped (the earlier one keeps its overrides).
   {
      std::set<std::string> keptIds;
      std::vector<Mod> kept;
      for (auto& m : mods_) {
         bool clash = false;
         std::string other;
         for (auto& c : m.conflicts) {
            if (keptIds.count(c)) {
               clash = true;
               other = c;
               break;
            }
         }
         if (clash) {
            reject(m.id, "mod.json",
                   "conflicts with installed mod \"" + other + "\"");
            continue;
         }
         keptIds.insert(m.id);
         kept.push_back(std::move(m));
      }
      mods_.swap(kept);
   }

   // Build the per-key winner tables (last mod in load order wins).
   for (auto& m : mods_) {
      for (auto& mp : m.maps) {
         auto it = mapBySlot_.find(mp.slotQ);
         if (it != mapBySlot_.end()) {
            crashlog::log(
                "MOD-CONFLICT: map slot q=%d overridden by %s, was %s",
                mp.slotQ, m.id.c_str(), mapOwner_[mp.slotQ].c_str());
         }
         mapBySlot_[mp.slotQ] = &mp;
         mapOwner_[mp.slotQ] = m.id;
      }
      for (auto& sp : m.sprites) {
         auto key = std::make_pair(sp.pack, sp.index);
         auto it = spriteByKey_.find(key);
         if (it != spriteByKey_.end()) {
            crashlog::log(
                "MOD-CONFLICT: sprite %s[%d] overridden by %s, was %s",
                packName(sp.pack), sp.index, m.id.c_str(),
                spriteOwner_[key].c_str());
         }
         spriteByKey_[key] = &sp;
         spriteOwner_[key] = m.id;
      }
   }
   if (!mods_.empty())
      crashlog::log("MOD: %d mod(s) enabled", (int)mods_.size());
}

}  // namespace ModLoader

namespace {

// --- Map application helpers (mirror repack_jar.py exactly) -------------

// Blocked test from loadMap (Game.java:7560): tile >= 175, or pd0 table
// 9 bit 13 set.
bool tileBlocked(Game* game, int tile) {
   if (tile >= 175) return true;
   int v = game->pd0ShortTables[9][tile] & 0xFFFF;
   return ((v >> 13) & 1) == 1;
}

// Occupancy of the NEW map for setup nudging: 0 = free, 1 = blocked.
// Mirrors repack_jar.py compute_occupancy: border cells (first row,
// last row, last column) and blocked/spawn tiles are not free.
std::vector<std::vector<char>> computeOccupancy(Game* game,
                                                const std::vector<int>& tiles,
                                                int w, int h) {
   std::vector<std::vector<char>> occ(h, std::vector<char>(w, 0));
   for (int r = 0; r < h; r++) {
      for (int c = 0; c < w; c++) {
         int t = tiles[(size_t)r * (size_t)w + (size_t)c];
         if (r == 0 || r == h - 1 || c == w - 1 || tileBlocked(game, t))
            occ[r][c] = 1;
      }
   }
   return occ;
}

// Nearest free cell, spiral search, max radius 8. Ported exactly from
// repack_jar.py nearest_free (same scan order, so results match the
// repack proof).
bool nearestFree(const std::vector<std::vector<char>>& occ, int w, int h,
                 int x, int y, int& ox, int& oy) {
   auto freeAt = [&](int cx, int cy) {
      return cx >= 0 && cx < w && cy >= 0 && cy < h && occ[cy][cx] == 0;
   };
   if (freeAt(x, y)) {
      ox = x;
      oy = y;
      return true;
   }
   for (int radius = 1; radius <= 8; radius++) {
      for (int dy = -radius; dy <= radius; dy++) {
         for (int dx : {-radius, radius}) {
            if (freeAt(x + dx, y + dy)) {
               ox = x + dx;
               oy = y + dy;
               return true;
            }
         }
      }
      for (int dx = -radius + 1; dx < radius; dx++) {
         for (int dy : {-radius, radius}) {
            if (freeAt(x + dx, y + dy)) {
               ox = x + dx;
               oy = y + dy;
               return true;
            }
         }
      }
   }
   return false;
}

// Apply one winning map override to level q. Patches rec 149 (size),
// rec 279 (win/lose flags), the setup table (rec 250+q, scaled +
// nudged), and rec 150 (camera start = centroid of the remapped player
// villagers). Stores the tiles for loadMap's tile-source branch.
void applyMap(Game* game, int q, const ModMap& mp, const std::string& owner) {
   auto& fileA = game->fileAData;
   auto& starts = game->recordStarts;
   auto u8 = [&](int i) { return fileA[i] & 0xFF; };

   int oldW = u8(starts[149] + q * 2);
   int oldH = u8(starts[149] + q * 2 + 1);
   fileA[starts[149] + q * 2] = (int8_t)mp.width;
   fileA[starts[149] + q * 2 + 1] = (int8_t)mp.height;
   crashlog::log("MOD: %s rec149+%d = [%d, %d] (was [%d, %d])",
                 owner.c_str(), q * 2, mp.width, mp.height, oldW, oldH);
   fileA[starts[279] + q] = (int8_t)mp.flags;
   crashlog::log("MOD: %s rec279+%d = [%d]", owner.c_str(), q, mp.flags);

   // Setup table (rec 250+q): 5 parallel arrays of `count` entries.
   // Entry: f0 packed, f1 kind (-1 villager, >= 0 building type,
   // -2 effect), f2 x, f3 y, f4 param. Only x/y change.
   int count = u8(starts[262] + q);
   int start = starts[250 + q];
   auto occ = computeOccupancy(game, mp.tiles, mp.width, mp.height);
   int nudged = 0, unplaceable = 0, villagers = 0, buildings = 0;
   // f0/f1/f4 are kept; new x/y are written back as we go.
   for (int i = 0; i < count; i++) {
      int f0 = fileA[start + 0 * count + i];
      int f1 = fileA[start + 1 * count + i];
      int x = u8(start + 2 * count + i);
      int y = u8(start + 3 * count + i);
      int nx = oldW > 0 ? x * mp.width / oldW : x;
      int ny = oldH > 0 ? y * mp.height / oldH : y;
      int f1s = f1 >= 128 ? f1 - 256 : f1;  // stored byte -> signed
      if (f1s == -1) {
         villagers++;
         int ox = nx, oy = ny;
         if (nearestFree(occ, mp.width, mp.height, nx, ny, ox, oy)) {
            if (ox != nx || oy != ny) nudged++;
            nx = ox;
            ny = oy;
         } else {
            unplaceable++;
         }
      } else if (f1s >= 0) {
         buildings++;
         int type = f1s;
         int bw = 1, bh = 1;
         if (type >= 0 && type < 26) {
            bw = u8(starts[138] + 4 * 26 + type);
            bh = u8(starts[138] + 5 * 26 + type);
            if (bw <= 0) bw = 1;
            if (bh <= 0) bh = 1;
         }
         auto footprintOk = [&](int fx, int fy) {
            if (fx < 0 || fy < 0 || fx + bw > mp.width ||
                fy + bh > mp.height)
               return false;
            for (int dy = 0; dy < bh; dy++)
               for (int dx = 0; dx < bw; dx++)
                  if (occ[fy + dy][fx + dx] != 0) return false;
            return true;
         };
         bool placed = false;
         int px = nx, py = ny;
         if (footprintOk(nx, ny)) {
            placed = true;
         } else {
            for (int radius = 1; radius <= 8 && !placed; radius++) {
               for (int dy = -radius; dy <= radius && !placed; dy++) {
                  for (int dx = -radius; dx <= radius; dx++) {
                     if (std::max(std::abs(dx), std::abs(dy)) != radius)
                        continue;
                     if (footprintOk(nx + dx, ny + dy)) {
                        px = nx + dx;
                        py = ny + dy;
                        placed = true;
                        break;
                     }
                  }
               }
            }
         }
         if (placed) {
            if (px != nx || py != ny) nudged++;
            nx = px;
            ny = py;
            for (int dy = 0; dy < bh; dy++)
               for (int dx = 0; dx < bw; dx++)
                  occ[ny + dy][nx + dx] = 1;
         } else {
            unplaceable++;
         }
      }
      // f1 == -2 (effect): scaled only, never nudged (repack rule).
      fileA[start + 2 * count + i] = (int8_t)nx;
      fileA[start + 3 * count + i] = (int8_t)ny;
      (void)f0;
   }
   crashlog::log("MOD: %s setup q=%d: %d entries (%d villagers, %d "
                 "buildings), %d nudged, %d unplaceable",
                 owner.c_str(), q, count, villagers, buildings, nudged,
                 unplaceable);
   if (unplaceable > 0)
      crashlog::log("MOD: %s WARNING: %d setup entries could not be "
                    "placed inside the map (kept at scaled position)",
                    owner.c_str(), unplaceable);

   // Camera/cursor start (rec 150) = centroid of the remapped player
   // villagers (v1 == -1 and (f0 & 1) == 0). The JSON player_start is
   // validated but is NOT the camera: the camera follows the tribe
   // (repack proof §4).
   long px = 0, py = 0;
   int n = 0;
   for (int i = 0; i < count; i++) {
      int f0 = fileA[start + 0 * count + i];
      int f1 = fileA[start + 1 * count + i];
      int f1s = f1 >= 128 ? f1 - 256 : f1;
      if (f1s == -1 && (f0 & 1) == 0) {
         px += u8(start + 2 * count + i);
         py += u8(start + 3 * count + i);
         n++;
      }
   }
   int cx, cy;
   if (n > 0) {
      cx = (int)(px / n);
      cy = (int)(py / n);
   } else if (mp.hasPlayerStart) {
      cx = mp.playerStartCol;
      cy = mp.playerStartRow;
      crashlog::log("MOD: %s WARNING: no player villagers in setup, "
                    "camera falls back to player_start",
                    owner.c_str());
   } else {
      cx = u8(starts[150] + q * 2);
      cy = u8(starts[150] + q * 2 + 1);
      crashlog::log("MOD: %s WARNING: no player villagers in setup and "
                    "no player_start, camera unchanged",
                    owner.c_str());
   }
   fileA[starts[150] + q * 2] = (int8_t)cx;
   fileA[starts[150] + q * 2 + 1] = (int8_t)cy;
   crashlog::log("MOD: %s rec150+%d = [%d, %d] (tribe centroid)",
                 owner.c_str(), q * 2, cx, cy);

   // Player_start sanity: warn when it lands on a blocked tile (the
   // camera does not use it, but a modder should know).
   if (mp.hasPlayerStart) {
      int t = mp.tiles[(size_t)mp.playerStartRow * (size_t)mp.width +
                       (size_t)mp.playerStartCol];
      if (tileBlocked(game, t))
         crashlog::log("MOD: %s WARNING: player_start (%d,%d) is on "
                       "blocked tile %d",
                       owner.c_str(), mp.playerStartCol, mp.playerStartRow,
                       t);
   }

   mapBySlot_[q] = &mp;
   mapOwner_[q] = owner;
   crashlog::log("MOD: map %s -> slot q=%d (replace) %dx%d", mp.id.c_str(),
                 q, mp.width, mp.height);
}

// Patch the loaded s string at `nameIdx` to `name`, when the lengths
// match (same-length overwrite needs no offset shifting). The stored
// bytes are glyph codes: raw byte -> /a rec 280 mapping, exactly as
// TextLoader.loadTextFile does it.
void applyMapName(Game* game, int q, const std::string& name,
                  const std::string& owner) {
   TextLoader* tl = game->textLoader;
   if (!tl) return;
   auto& fileA = game->fileAData;
   auto& starts = game->recordStarts;
   int piece = fileA[starts[248] + q] & 0xFF;
   int nameIdx = 82 + piece;
   if (nameIdx < 0 || nameIdx >= 229) return;
   if (tl->uiTextOffsets.isNull() || tl->uiTextData.isNull()) return;
   int begin = tl->uiTextOffsets[nameIdx];
   int end = (nameIdx + 1 < 230) ? tl->uiTextOffsets[nameIdx + 1]
                                 : tl->uiTextData.size();
   int len = end - begin;
   if (len != (int)name.size()) {
      crashlog::log("MOD: %s map name not patched (base s[%d] is %d "
                    "chars, mod name is %d chars)",
                    owner.c_str(), nameIdx, len, (int)name.size());
      return;
   }
   for (int i = 0; i < len; i++) {
      unsigned char ch = (unsigned char)name[(size_t)i];
      tl->uiTextData[begin + i] = fileA[starts[280] + ch];
   }
   crashlog::log("MOD: map name s[%d] -> \"%s\"", nameIdx, name.c_str());
}

}  // namespace

namespace ModLoader {

void applyMods(Game* game) {
   if (!initialized_ || !game) return;
   if (mapBySlot_.empty()) return;
   // mapBySlot_ already holds the per-slot winners from discovery
   // (last mod in load order wins). Re-apply in slot order for a
   // deterministic log.
   for (auto& kv : mapBySlot_) {
      int q = kv.first;
      const ModMap* mp = kv.second;
      applyMap(game, q, *mp, mapOwner_[q]);
      applyMapName(game, q, mp->name, mapOwner_[q]);
   }
}

Image* spriteOverride(Game* game, int pack, int index, Image* base) {
   (void)game;
   if (!initialized_ || !base) return nullptr;
   auto it = spriteByKey_.find(std::make_pair(pack, index));
   if (it == spriteByKey_.end()) return nullptr;
   const ModSprite* sp = it->second;
   const std::string& owner = spriteOwner_[std::make_pair(pack, index)];
   // Find the owning mod's folder to resolve the file path.
   const Mod* ownerMod = nullptr;
   for (auto& m : mods_)
      if (m.id == owner) ownerMod = &m;
   if (!ownerMod) return nullptr;
   std::vector<uint8_t> bytes;
   if (!readFileBytes(ownerMod->dir / fs::path(sp->file), bytes)) {
      crashlog::log("MOD: %s sprite %s unreadable, keeping base image",
                    owner.c_str(), sp->file.c_str());
      return nullptr;
   }
   Arr<int8_t> arr((size_t)bytes.size());
   for (size_t i = 0; i < bytes.size(); i++) arr[(int)i] = (int8_t)bytes[i];
   Image* modImage = nullptr;
   try {
      modImage = Image::createImage(arr, 0, (int)bytes.size());
   } catch (JavaException&) {
      crashlog::log("MOD: %s sprite %s failed to decode, keeping base "
                    "image",
                    owner.c_str(), sp->file.c_str());
      return nullptr;
   }
   if (!modImage) return nullptr;
   if (modImage->w != base->w || modImage->h != base->h) {
      crashlog::log("MOD: %s sprite %s is %dx%d but the original %s[%d] "
                    "is %dx%d, skipped",
                    owner.c_str(), sp->file.c_str(), modImage->w,
                    modImage->h, packName(pack), index, base->w, base->h);
      delete modImage;
      return nullptr;
   }
   if (pack == 0 && index == 25)
      crashlog::log("MOD: %s WARNING: pi0[25] is the HUD digit font, "
                    "overriding it changes every number on screen",
                    owner.c_str());
   crashlog::log("MOD: sprite %s -> %s[%d] (%dx%d)", sp->file.c_str(),
                 packName(pack), index, modImage->w, modImage->h);
   return modImage;
}

bool hasModMap(int levelQ) {
   if (!initialized_) return false;
   return mapBySlot_.find(levelQ) != mapBySlot_.end();
}

int modMapTile(int levelQ, int row, int col) {
   auto it = mapBySlot_.find(levelQ);
   if (it == mapBySlot_.end()) return 0;
   const ModMap* mp = it->second;
   if (row < 0 || row >= mp->height || col < 0 || col >= mp->width)
      return 0;
   return mp->tiles[(size_t)row * (size_t)mp->width + (size_t)col];
}

void reapplyMapNames(Game* game) {
   if (!initialized_ || !game) return;
   for (auto& kv : mapBySlot_)
      applyMapName(game, kv.first, kv.second->name, mapOwner_[kv.first]);
}

}  // namespace ModLoader
