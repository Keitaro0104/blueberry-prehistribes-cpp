// engine.h — the MIDP stand-ins the translated game code extends and uses.
//
// Own implementation (not copied from any other port).
//
// Threading model (matches MIDP): the translated Game runs on its own
// thread (started by Game::startGameThread, i.e. `new Thread(this).start()`),
// exactly like the original game thread. The shell runs the Win32 message
// pump on the main thread and calls paint() on the current canvas whenever
// Canvas::repaint() requests it; paint() clears the game's repaintPending
// flag, which releases the game thread's wait loop. Key presses and focus
// changes are delivered by the shell calling keyPressed/keyReleased/
// hideNotify/showNotify directly — the same benign unsynchronized pattern
// as MIDP (the event thread writes boolean flags the game thread reads).
//
// The game loop is NOT restructured into a shell-driven runFrame(): the
// loop lives in the translated Game::run(), and restructuring it would
// require editing generated code. The shell's job is only to pump messages,
// paint on request, and forward input.

#pragma once

#include <atomic>
#include <functional>
#include <map>

#include "gfx.h"
#include "jrt.h"

using namespace jrt;

// MIDP key codes.
enum {
   KEY_UP = -1,
   KEY_DOWN = -2,
   KEY_LEFT = -3,
   KEY_RIGHT = -4,
   KEY_FIRE = -5,
   KEY_SOFT_LEFT = -6,
   KEY_SOFT_RIGHT = -7,
};

// MIDP game actions (returned by Canvas::getGameAction).
enum {
   ACTION_UP = 1,
   ACTION_LEFT = 2,
   ACTION_DOWN = 6,
   ACTION_RIGHT = 5,
   ACTION_FIRE = 8,
};

// ---------------------------------------------------------------------------
// Canvas — javax.microedition.lcdui.Canvas stand-in. The translated Game
// and Splash extend this.
// ---------------------------------------------------------------------------
class Canvas {
  public:
   virtual ~Canvas() = default;
   virtual void paint(Surface* g) {}
   virtual void keyPressed(int keyCode) {}
   virtual void keyReleased(int keyCode) {}
   virtual void hideNotify() {}
   virtual void showNotify() {}

   void setFullScreenMode(bool /*fullscreen*/) {}  // no-op on Windows
   int getWidth() const { return 240; }
   int getHeight() const { return 320; }

   // Requests a repaint: the shell paints the current canvas and the
   // translated paint() clears the game's repaintPending flag.
   void repaint();

   // Maps a MIDP key code to a game action (UP/DOWN/LEFT/RIGHT/FIRE).
   int getGameAction(int keyCode) const;

   // Set by the shell: called (on the shell thread) when repaint() fires.
   static void setRepaintCallback(std::function<void()> cb);
   static bool repaintRequested();

  private:
   static std::atomic<bool> repaintRequested_;
   static std::function<void()> repaintCallback_;
};

// ---------------------------------------------------------------------------
// MIDlet — javax.microedition.midlet.MIDlet stand-in.
// ---------------------------------------------------------------------------
class MIDlet {
  public:
   virtual ~MIDlet() = default;
   virtual void startApp() {}
   virtual void pauseApp() {}
   virtual void destroyApp(bool /*unconditional*/) {}
   void notifyDestroyed();
   bool destroyed() const { return destroyed_; }
   String getAppProperty(const String& /*key*/) { return String(); }

  private:
   bool destroyed_ = false;
};

// ---------------------------------------------------------------------------
// Display — javax.microedition.lcdui.Display stand-in (singleton).
// ---------------------------------------------------------------------------
class Display {
  public:
   static Display* getDisplay(MIDlet* /*midlet*/);
   void setCurrent(Canvas* canvas) { current_ = canvas; }
   Canvas* getCurrent() const { return current_; }

  private:
   Canvas* current_ = nullptr;
};

// ---------------------------------------------------------------------------
// RecordStore — javax.microedition.rms.RecordStore stand-in, backed by one
// file per record id in Platform::saveDir: <saveDir>/<name>_<id>.bin.
// ---------------------------------------------------------------------------
class RecordStore {
  public:
   static RecordStore* openRecordStore(const String& name, bool createIfNecessary);
   static void deleteRecordStore(const String& name);
   int getNumRecords() const;
   Arr<int8_t> getRecord(int id);
   int addRecord(Arr<int8_t> data, int offset, int numBytes);
   void closeRecordStore() {}

  private:
   explicit RecordStore(const String& name) : name_(name) {}
   std::string filePath(int id) const;
   void loadExisting();
   String name_;
   // id -> bytes, loaded on open.
   std::map<int, std::vector<uint8_t>> records_;
};

// ---------------------------------------------------------------------------
// Media — javax.microedition.media stand-ins.
// ---------------------------------------------------------------------------
class Player;
class PlayerListener {
  public:
   virtual ~PlayerListener() = default;
   // String is taken by value to match the translated Java signature
   // (MidiPlayer::playerUpdate(Player*, String, void*)).
   virtual void playerUpdate(Player* player, String event, void* data) = 0;
};

class VolumeControl {
  public:
   void setLevel(int level);
   int getLevel() const { return level_; }

  private:
   int level_ = 100;
};

// Player states (MIDP): UNREALIZED=0, REALIZED=100, PREFETCHED=300,
// STARTED=400, CLOSED=0.
class Player {
  public:
   virtual ~Player() = default;
   explicit Player(InputStream* in);
   virtual void realize();
   virtual void prefetch();
   virtual void start();
   virtual void stop();
   virtual void close();
   int getState() const { return state_; }
   virtual void setMediaTime(int64_t /*ms*/);
   void addPlayerListener(PlayerListener* l) { listener_ = l; }
   VolumeControl* getControl(const String& type);

  protected:
   void setState(int s) { state_ = s; }
   void fireEvent(const String& event);
   std::vector<uint8_t> data_;  // the whole media stream, read eagerly
   PlayerListener* listener_ = nullptr;
   VolumeControl volume_;

  private:
   int state_ = 0;
};

class Manager {
  public:
   static Player* createPlayer(InputStream* in, const String& type);
};
