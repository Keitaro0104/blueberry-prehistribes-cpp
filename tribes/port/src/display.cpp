// display.cpp — implementations for display.h (see the header for the
// design notes).

#include "display.h"

#include <algorithm>

#include "engine.h"
#include "input.h"

WinDisplay* g_winDisplay = nullptr;

std::mutex& screenMutex() {
   static std::mutex m;
   return m;
}

namespace {

const char* kWindowClass = "BlueberryTribesWindow";

LRESULT CALLBACK wndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
   switch (msg) {
      case WM_PAINT: {
         PAINTSTRUCT ps;
         BeginPaint(hwnd, &ps);
         if (g_winDisplay && g_winDisplay->screen()) {
            // Re-present the last frame under the screen mutex.
            std::lock_guard<std::mutex> lock(screenMutex());
            g_winDisplay->present(*g_winDisplay->screen());
         }
         EndPaint(hwnd, &ps);
         return 0;
      }
      case WM_KEYDOWN:
      case WM_SYSKEYDOWN: {
         // Ignore auto-repeat: the game tracks held keys itself.
         if (!(lParam & (1 << 30))) {
            int code = 0;
            if (winKeyToMidp((int)wParam, code)) winForwardKey(code, true);
         }
         return 0;
      }
      case WM_KEYUP:
      case WM_SYSKEYUP: {
         int code = 0;
         if (winKeyToMidp((int)wParam, code)) winForwardKey(code, false);
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

WinDisplay::WinDisplay(int scale, bool fullscreen, int logicalW,
                       int logicalH)
    : scale_(scale),
      fullscreen_(fullscreen),
      logicalW_(logicalW),
      logicalH_(logicalH) {}

void WinDisplay::setTargetClientSize(int w, int h) {
   targetClientW_ = w;
   targetClientH_ = h;
}

WinDisplay::~WinDisplay() {
   if (hwnd_) DestroyWindow(hwnd_);
}

bool WinDisplay::create(const std::string& title) {
   HINSTANCE inst = GetModuleHandleA(nullptr);
   WNDCLASSA wc = {};
   wc.lpfnWndProc = wndProc;
   wc.hInstance = inst;
   wc.lpszClassName = kWindowClass;
   wc.hCursor = LoadCursorA(nullptr, IDC_ARROW);
   wc.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
   if (!RegisterClassA(&wc) && GetLastError() != ERROR_CLASS_ALREADY_EXISTS) {
      return false;
   }
   DWORD style = WS_OVERLAPPEDWINDOW;
   // --aspect sets the window client size (1024x768 / 1280x720);
   // otherwise the window is logical * scale.
   int w = targetClientW_ > 0 ? targetClientW_ : logicalW_ * scale_;
   int h = targetClientH_ > 0 ? targetClientH_ : logicalH_ * scale_;
   if (fullscreen_) {
      style = WS_POPUP;
      w = GetSystemMetrics(SM_CXSCREEN);
      h = GetSystemMetrics(SM_CYSCREEN);
   }
   RECT rc = {0, 0, w, h};
   if (!fullscreen_) AdjustWindowRect(&rc, style, FALSE);
   hwnd_ = CreateWindowExA(0, kWindowClass, title.c_str(), style,
                           CW_USEDEFAULT, CW_USEDEFAULT, rc.right - rc.left,
                           rc.bottom - rc.top, nullptr, nullptr, inst, this);
   if (!hwnd_) return false;
   updateClientSize();
   ShowWindow(hwnd_, SW_SHOW);
   UpdateWindow(hwnd_);
   return true;
}

void WinDisplay::updateClientSize() {
   if (!hwnd_) return;
   RECT rc;
   GetClientRect(hwnd_, &rc);
   clientW_ = rc.right - rc.left;
   clientH_ = rc.bottom - rc.top;
}

RECT WinDisplay::destRect() const {
   // Letterbox the logical frame (240x320 original, 427x320 4:3,
   // 569x320 16:9) into the client area, keeping its aspect ratio.
   double scale =
       std::min((double)clientW_ / logicalW_, (double)clientH_ / logicalH_);
   int dw = (int)(logicalW_ * scale);
   int dh = (int)(logicalH_ * scale);
   RECT r;
   r.left = (clientW_ - dw) / 2;
   r.top = (clientH_ - dh) / 2;
   r.right = r.left + dw;
   r.bottom = r.top + dh;
   return r;
}

void WinDisplay::present(const Surface& screen) {
   if (!hwnd_) return;
   updateClientSize();
   HDC dc = GetDC(hwnd_);
   if (!dc) return;
   RECT dest = destRect();
   // Black bars.
   RECT full = {0, 0, clientW_, clientH_};
   FillRect(dc, &full, (HBRUSH)GetStockObject(BLACK_BRUSH));
   BITMAPINFO bmi = {};
   bmi.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
   bmi.bmiHeader.biWidth = screen.getWidth();
   bmi.bmiHeader.biHeight = -screen.getHeight();  // top-down
   bmi.bmiHeader.biPlanes = 1;
   bmi.bmiHeader.biBitCount = 32;
   bmi.bmiHeader.biCompression = BI_RGB;
   StretchDIBits(dc, dest.left, dest.top, dest.right - dest.left,
                 dest.bottom - dest.top, 0, 0, screen.getWidth(),
                 screen.getHeight(), screen.pixels(), &bmi, DIB_RGB_COLORS,
                 SRCCOPY);
   ReleaseDC(hwnd_, dc);
}

void WinDisplay::repaintLast(const Surface& screen) { present(screen); }
