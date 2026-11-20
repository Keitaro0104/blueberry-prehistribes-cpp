// gfx.h — Image + Surface (the javax.microedition.lcdui.Graphics stand-in).
//
// Own implementation (not copied from any other port). A software
// framebuffer: pure CPU pixel pushing, no GPU API. Colors are 0xAARRGGBB.
// drawImage/drawRegion blend source pixels src-over (MIDP semantics:
// opaque pixels replace, transparent pixels are skipped, partial alpha
// blends). Only the Graphics subset the game uses is implemented:
// setColor, setClip, translate, fillRect, drawRect, drawLine, drawImage,
// drawRegion, getGraphics. No Font/drawString — the game draws all text
// as sprites (TextLoader renders glyphs with drawRegion).

#pragma once

#include <memory>
#include <string>
#include <vector>

#include "jrt.h"

using namespace jrt;

class Surface;

// MIDP anchor bits.
enum {
   ANCHOR_HCENTER = 1,
   ANCHOR_VCENTER = 2,
   ANCHOR_LEFT = 4,
   ANCHOR_RIGHT = 8,
   ANCHOR_TOP = 16,
   ANCHOR_BOTTOM = 32,
};

// MIDP drawRegion transforms.
enum {
   TRANS_NONE = 0,
   TRANS_MIRROR_ROT180 = 1,
   TRANS_MIRROR = 2,
   TRANS_ROT180 = 3,
   TRANS_MIRROR_ROT270 = 4,
   TRANS_ROT90 = 5,
   TRANS_ROT270 = 6,
   TRANS_MIRROR_ROT90 = 7,
};

struct Image {
   int w = 0;
   int h = 0;
   // Shared pixel storage so Surface views (from getGraphics) stay in sync.
   std::shared_ptr<std::vector<uint32_t>> px;

   static Image* createImage(int w, int h);
   static Image* createImage(const String& path);
   static Image* createImage(Arr<int8_t> data, int offset, int length);
   int getWidth() const { return w; }
   int getHeight() const { return h; }
   Surface* getGraphics();
};

class Surface {
  public:
   Surface(int w, int h);
   explicit Surface(Image* img);  // shares the image's pixels
   int getWidth() const { return w_; }
   int getHeight() const { return h_; }

   void setColor(int rgb);
   void setColor(int r, int g, int b);
   int getColor() const { return (int)(color_ & 0x00FFFFFF); }
   void setClip(int x, int y, int w, int h);
   void clipRect(int x, int y, int w, int h);
   int getClipX() const { return clipX_ - tx_; }
   int getClipY() const { return clipY_ - ty_; }
   int getClipWidth() const { return clipW_; }
   int getClipHeight() const { return clipH_; }
   void translate(int x, int y);

   void fillRect(int x, int y, int w, int h);
   void drawRect(int x, int y, int w, int h);
   void drawLine(int x1, int y1, int x2, int y2);
   void drawImage(Image* img, int x, int y, int anchor);
   void drawRegion(Image* src, int sx, int sy, int w, int h, int transform,
                   int dx, int dy, int anchor);

   void writeBmp(const std::string& path) const;

   const uint32_t* pixels() const { return px_->data(); }
   uint32_t* pixels() { return px_->data(); }

  private:
   void putPixel(int x, int y, uint32_t argb);
   void blendPixel(int x, int y, uint32_t src);
   void adjustAnchor(int anchor, int dw, int dh, int& x, int& y) const;

   int w_ = 0;
   int h_ = 0;
   std::shared_ptr<std::vector<uint32_t>> px_;
   uint32_t color_ = 0xFF000000;
   int clipX_ = 0, clipY_ = 0, clipW_ = 0, clipH_ = 0;
   int tx_ = 0, ty_ = 0;
};
