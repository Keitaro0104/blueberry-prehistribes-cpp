// display.h — the Win32 window and GDI presentation for the port.
//
// Own implementation (not copied from any other port). The game renders
// into a software Surface (see gfx.h) whose size is the logical view:
// 240x320 for the original aspect, 427x320 for 4:3, 569x320 for 16:9
// (see --aspect in main.cpp); this class owns the window and blits that
// Surface to it with StretchDIBits, letterboxed to keep the view's
// aspect ratio. The Surface pixels are 0xAARRGGBB, which is the
// in-memory layout of a 32-bit top-down DIB, so no conversion is needed.

#pragma once

#include <mutex>
#include <string>

#define WIN32_LEAN_AND_MEAN
#include <windows.h>

#include "gfx.h"

class WinDisplay {
  public:
   // scale: window scale for the original 240x320 view. logicalW/logicalH:
   // the logical view size (240x320 original, 427x320 4:3, 569x320 16:9).
   // setTargetClientSize() opens the window at a fixed client size
   // (1024x768 / 1280x720) instead of logical * scale.
   WinDisplay(int scale, bool fullscreen, int logicalW = 240,
              int logicalH = 320);
   ~WinDisplay();

   bool create(const std::string& title);
   void setTargetClientSize(int w, int h);
   HWND hwnd() const { return hwnd_; }
   bool isFullscreen() const { return fullscreen_; }

   // Blit the logical-view game Surface to the window (letterboxed).
   void present(const Surface& screen);
   // Re-present the last frame (called from WM_PAINT).
   void repaintLast(const Surface& screen);

   // The screen Surface the shell paints into (set by main.cpp so
   // WM_PAINT can re-present the last frame).
   void setScreen(const Surface* screen) { screen_ = screen; }
   const Surface* screen() const { return screen_; }

  private:
   void updateClientSize();
   RECT destRect() const;  // letterboxed destination for the logical-view frame

   HWND hwnd_ = nullptr;
   int scale_ = 3;
   bool fullscreen_ = false;
   int logicalW_ = 240, logicalH_ = 320;
   int targetClientW_ = 0, targetClientH_ = 0;
   int clientW_ = 720;
   int clientH_ = 960;
   const Surface* screen_ = nullptr;
};

// The mutex guarding the screen Surface, shared by the game thread
// (paint) and the shell thread (present / WM_PAINT). One process-wide
// mutex, so headless mode (no window/display) uses the same lock.
std::mutex& screenMutex();

// The active display (set by main.cpp); the window procedure uses it to
// forward input and repaint requests.
extern WinDisplay* g_winDisplay;
