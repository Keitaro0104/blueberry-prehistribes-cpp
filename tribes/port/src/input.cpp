// input.cpp — implementations for input.h (see the header for the
// mapping table).

#include "input.h"

#define WIN32_LEAN_AND_MEAN
#include <windows.h>

#include "engine.h"

bool winKeyToMidp(int vk, int& outCode) {
   switch (vk) {
      case VK_UP:
      case 'W':
         outCode = KEY_UP;
         return true;
      case VK_DOWN:
      case 'S':
         outCode = KEY_DOWN;
         return true;
      case VK_LEFT:
      case 'A':
         outCode = KEY_LEFT;
         return true;
      case VK_RIGHT:
      case 'D':
         outCode = KEY_RIGHT;
         return true;
      case VK_RETURN:
      case VK_SPACE:
         outCode = KEY_FIRE;
         return true;
      case VK_F1:
      case 'Q':
         outCode = KEY_SOFT_LEFT;
         return true;
      case VK_ESCAPE:
      case VK_F2:
         outCode = KEY_SOFT_RIGHT;
         return true;
      default:
         break;
   }
   // Digits: the game reads the ASCII code directly ('0'..'9').
   if (vk >= '0' && vk <= '9') {
      outCode = vk;
      return true;
   }
   // Numpad digits (VK_NUMPAD0..VK_NUMPAD9) map to the same ASCII codes.
   if (vk >= VK_NUMPAD0 && vk <= VK_NUMPAD9) {
      outCode = '0' + (vk - VK_NUMPAD0);
      return true;
   }
   return false;
}

void winForwardKey(int midpCode, bool pressed) {
   Display* display = Display::getDisplay(nullptr);
   if (!display) return;
   Canvas* canvas = display->getCurrent();
   if (!canvas) return;
   // Delivered on the shell (message-pump) thread, like MIDP delivers
   // key events on its event thread; the game thread only reads the
   // boolean flags these set, which is the same benign pattern as the
   // original.
   if (pressed) {
      canvas->keyPressed(midpCode);
   } else {
      canvas->keyReleased(midpCode);
   }
}
