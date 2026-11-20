// display.h — the Win32 window and GDI presentation for the port.
//
// Own implementation (not copied from any other port). The game renders
// into a 240x320 software Surface (see gfx.h); this class owns the window
// and blits that Surface to it with StretchDIBits, letterboxed to keep
// the 3:4 aspect ratio. The Surface pixels are 0xAARRGGBB, which is the
// in-memory layout of a 32-bit top-down DIB, so no conversion is needed.

#pragma once

#include <mutex>
#include <string>

#define WIN32_LEAN_AND_MEAN
#include <windows.h>

#include "gfx.h"

class WinDisplay {
  public:
   WinDisplay(int scale, bool fullscreen);
   ~WinDisplay();

   bool create(const std::string& title);
   HWND hwnd() const { return hwnd_; }
   bool isFullscreen() const { return fullscreen_; }

   // Blit the 240x320 game Surface to the window (letterboxed).
   void present(const Surface& screen);
   // Re-present the last frame (called from WM_PAINT).
   void repaintLast(const Surface& screen);

   // The screen Surface the shell paints into (set by main.cpp so
   // WM_PAINT can re-present the last frame).
   void setScreen(const Surface* screen) { screen_ = screen; }
   const Surface* screen() const { return screen_; }

   // The process-wide screen mutex (see screenMutex() above).
   std::mutex& screenMutex() { return ::screenMutex(); }

  private:
   void updateClientSize();
   RECT destRect() const;  // letterboxed destination for the 240x320 frame

   HWND hwnd_ = nullptr;
   int scale_ = 3;
   bool fullscreen_ = false;
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
