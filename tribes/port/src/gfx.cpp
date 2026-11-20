// gfx.cpp — implementations for gfx.h (see the header for the design notes).

#include "gfx.h"

#include <cstdio>

#include "platform.h"
#include "stb_image.h"

namespace {

// Src-over blend of one pixel (both 0xAARRGGBB).
uint32_t blend(uint32_t dst, uint32_t src) {
   unsigned sa = (src >> 24) & 0xFF;
   if (sa == 0) return dst;
   if (sa == 255) return src;
   unsigned sr = (src >> 16) & 0xFF, sg = (src >> 8) & 0xFF, sb = src & 0xFF;
   unsigned dr = (dst >> 16) & 0xFF, dg = (dst >> 8) & 0xFF, db = dst & 0xFF;
   unsigned r = (sr * sa + dr * (255 - sa)) / 255;
   unsigned g = (sg * sa + dg * (255 - sa)) / 255;
   unsigned b = (sb * sa + db * (255 - sa)) / 255;
   return 0xFF000000u | (r << 16) | (g << 8) | b;
}

Image* decodePng(const uint8_t* data, int len) {
   int w = 0, h = 0, channels = 0;
   unsigned char* rgba = stbi_load_from_memory(data, len, &w, &h, &channels, 4);
   if (!rgba) throw JavaException{"IOException"};
   Image* img = new Image();
   img->w = w;
   img->h = h;
   img->px = std::make_shared<std::vector<uint32_t>>((size_t)w * (size_t)h);
   for (int i = 0; i < w * h; i++) {
      unsigned r = rgba[i * 4 + 0], g = rgba[i * 4 + 1], b = rgba[i * 4 + 2],
               a = rgba[i * 4 + 3];
      (*img->px)[(size_t)i] = (a << 24) | (r << 16) | (g << 8) | b;
   }
   stbi_image_free(rgba);
   return img;
}

}  // namespace

// ---------------------------------------------------------------------------
// Image
// ---------------------------------------------------------------------------
Image* Image::createImage(int w, int h) {
   if (w <= 0 || h <= 0) throw JavaException{"IllegalArgumentException"};
   Image* img = new Image();
   img->w = w;
   img->h = h;
   img->px = std::make_shared<std::vector<uint32_t>>((size_t)w * (size_t)h, 0);
   return img;
}

Image* Image::createImage(const String& path) {
   std::vector<uint8_t> bytes = readDataFile(path);
   if (bytes.empty()) throw JavaException{"IOException"};
   return decodePng(bytes.data(), (int)bytes.size());
}

Image* Image::createImage(Arr<int8_t> data, int offset, int length) {
   if (data.isNull()) throw JavaException{"NullPointerException"};
   if (offset < 0 || length < 0 || offset + length > data.size())
      throw JavaException{"ArrayIndexOutOfBounds"};
   std::vector<uint8_t> bytes((size_t)length);
   for (int i = 0; i < length; i++) bytes[(size_t)i] = (uint8_t)(data[offset + i] & 0xFF);
   return decodePng(bytes.data(), length);
}

Surface* Image::getGraphics() { return new Surface(this); }

// ---------------------------------------------------------------------------
// Surface
// ---------------------------------------------------------------------------
Surface::Surface(int w, int h) : w_(w), h_(h) {
   if (w <= 0 || h <= 0) throw JavaException{"IllegalArgumentException"};
   px_ = std::make_shared<std::vector<uint32_t>>((size_t)w * (size_t)h, 0);
   clipW_ = w;
   clipH_ = h;
}

Surface::Surface(Image* img) : w_(img->w), h_(img->h), px_(img->px) {
   clipW_ = w_;
   clipH_ = h_;
}

void Surface::setColor(int rgb) {
   color_ = 0xFF000000u | (uint32_t)(rgb & 0x00FFFFFF);
}

void Surface::setColor(int r, int g, int b) {
   color_ = 0xFF000000u | ((uint32_t)(r & 0xFF) << 16) |
            ((uint32_t)(g & 0xFF) << 8) | (uint32_t)(b & 0xFF);
}

void Surface::setClip(int x, int y, int w, int h) {
   // The clip is given in translated coordinates; store it in device space.
   int x0 = x + tx_, y0 = y + ty_;
   if (x0 < 0) {
      w += x0;
      x0 = 0;
   }
   if (y0 < 0) {
      h += y0;
      y0 = 0;
   }
   if (x0 + w > w_) w = w_ - x0;
   if (y0 + h > h_) h = h_ - y0;
   if (w < 0) w = 0;
   if (h < 0) h = 0;
   clipX_ = x0;
   clipY_ = y0;
   clipW_ = w;
   clipH_ = h;
}

void Surface::clipRect(int x, int y, int w, int h) {
   int x0 = x + tx_, y0 = y + ty_;
   int x1 = clipX_ + clipW_, y1 = clipY_ + clipH_;
   if (x0 > clipX_) clipX_ = x0;
   if (y0 > clipY_) clipY_ = y0;
   if (x0 + w < x1) clipW_ = x0 + w - clipX_;
   if (y0 + h < y1) clipH_ = y0 + h - clipY_;
   if (clipW_ < 0) clipW_ = 0;
   if (clipH_ < 0) clipH_ = 0;
}

void Surface::translate(int x, int y) {
   tx_ += x;
   ty_ += y;
}

void Surface::putPixel(int x, int y, uint32_t argb) {
   x += tx_;
   y += ty_;
   if (x < clipX_ || y < clipY_ || x >= clipX_ + clipW_ ||
       y >= clipY_ + clipH_)
      return;
   (*px_)[(size_t)y * (size_t)w_ + (size_t)x] = argb;
}

void Surface::blendPixel(int x, int y, uint32_t src) {
   unsigned sa = (src >> 24) & 0xFF;
   if (sa == 0) return;
   x += tx_;
   y += ty_;
   if (x < clipX_ || y < clipY_ || x >= clipX_ + clipW_ ||
       y >= clipY_ + clipH_)
      return;
   uint32_t& dst = (*px_)[(size_t)y * (size_t)w_ + (size_t)x];
   dst = blend(dst, src);
}

void Surface::fillRect(int x, int y, int w, int h) {
   if (w <= 0 || h <= 0) return;
   int x0 = x + tx_, y0 = y + ty_;
   int x1 = x0 + w, y1 = y0 + h;
   if (x0 < clipX_) x0 = clipX_;
   if (y0 < clipY_) y0 = clipY_;
   if (x1 > clipX_ + clipW_) x1 = clipX_ + clipW_;
   if (y1 > clipY_ + clipH_) y1 = clipY_ + clipH_;
   for (int yy = y0; yy < y1; yy++) {
      uint32_t* row = &(*px_)[(size_t)yy * (size_t)w_ + (size_t)x0];
      for (int xx = x0; xx < x1; xx++) *row++ = color_;
   }
}

void Surface::drawRect(int x, int y, int w, int h) {
   if (w < 0 || h < 0) return;
   fillRect(x, y, w, 1);
   fillRect(x, y + h, w, 1);
   fillRect(x, y, 1, h);
   fillRect(x + w, y, 1, h);
}

void Surface::drawLine(int x1, int y1, int x2, int y2) {
   // Bresenham.
   int dx = x2 - x1, dy = y2 - y1;
   int sx = dx < 0 ? -1 : 1, sy = dy < 0 ? -1 : 1;
   dx = dx < 0 ? -dx : dx;
   dy = dy < 0 ? -dy : dy;
   int err = (dx > dy ? dx : -dy) / 2;
   while (true) {
      putPixel(x1, y1, color_);
      if (x1 == x2 && y1 == y2) break;
      int e2 = err;
      if (e2 > -dx) {
         err -= dy;
         x1 += sx;
      }
      if (e2 < dy) {
         err += dx;
         y1 += sy;
      }
   }
}

void Surface::adjustAnchor(int anchor, int dw, int dh, int& x, int& y) const {
   if (anchor & ANCHOR_HCENTER) x -= dw / 2;
   if (anchor & ANCHOR_RIGHT) x -= dw;
   if (anchor & ANCHOR_VCENTER) y -= dh / 2;
   if (anchor & ANCHOR_BOTTOM) y -= dh;
}

void Surface::drawImage(Image* img, int x, int y, int anchor) {
   if (!img || !img->px) throw JavaException{"NullPointerException"};
   int dw = img->w, dh = img->h;
   adjustAnchor(anchor, dw, dh, x, y);
   for (int yy = 0; yy < dh; yy++) {
      for (int xx = 0; xx < dw; xx++) {
         blendPixel(x + xx, y + yy, (*img->px)[(size_t)yy * (size_t)dw + (size_t)xx]);
      }
   }
}

void Surface::drawRegion(Image* src, int sx, int sy, int w, int h,
                         int transform, int dx, int dy, int anchor) {
   if (!src || !src->px) throw JavaException{"NullPointerException"};
   if (w <= 0 || h <= 0) return;
   // Destination size: 90/270-degree transforms swap width and height.
   int dw = w, dh = h;
   if (transform == TRANS_ROT90 || transform == TRANS_ROT270 ||
       transform == TRANS_MIRROR_ROT90 || transform == TRANS_MIRROR_ROT270) {
      dw = h;
      dh = w;
   }
   adjustAnchor(anchor, dw, dh, dx, dy);
   for (int y = 0; y < dh; y++) {
      for (int x = 0; x < dw; x++) {
         int sxx, syy;
         switch (transform) {
            case TRANS_NONE:
               sxx = sx + x;
               syy = sy + y;
               break;
            case TRANS_MIRROR:  // flip horizontal
               sxx = sx + w - 1 - x;
               syy = sy + y;
               break;
            case TRANS_ROT180:
               sxx = sx + w - 1 - x;
               syy = sy + h - 1 - y;
               break;
            case TRANS_MIRROR_ROT180:  // flip vertical
               sxx = sx + x;
               syy = sy + h - 1 - y;
               break;
            case TRANS_ROT90:  // rotate 90 clockwise
               sxx = sx + y;
               syy = sy + h - 1 - x;
               break;
            case TRANS_ROT270:  // rotate 90 counter-clockwise
               sxx = sx + w - 1 - y;
               syy = sy + x;
               break;
            case TRANS_MIRROR_ROT90:
               sxx = sx + w - 1 - y;
               syy = sy + h - 1 - x;
               break;
            case TRANS_MIRROR_ROT270:
               sxx = sx + y;
               syy = sy + x;
               break;
            default:
               sxx = sx + x;
               syy = sy + y;
               break;
         }
         if (sxx < 0 || syy < 0 || sxx >= src->w || syy >= src->h) continue;
         blendPixel(dx + x, dy + y,
                    (*src->px)[(size_t)syy * (size_t)src->w + (size_t)sxx]);
      }
   }
}

void Surface::writeBmp(const std::string& path) const {
   FILE* f = std::fopen(path.c_str(), "wb");
   if (!f) return;
   int rowBytes = ((w_ * 3 + 3) / 4) * 4;
   uint32_t fileSize = 54 + (uint32_t)rowBytes * (uint32_t)h_;
   unsigned char header[54] = {0};
   header[0] = 'B';
   header[1] = 'M';
   header[2] = (unsigned char)(fileSize & 0xFF);
   header[3] = (unsigned char)((fileSize >> 8) & 0xFF);
   header[4] = (unsigned char)((fileSize >> 16) & 0xFF);
   header[5] = (unsigned char)((fileSize >> 24) & 0xFF);
   header[10] = 54;
   header[14] = 40;
   header[18] = (unsigned char)(w_ & 0xFF);
   header[19] = (unsigned char)((w_ >> 8) & 0xFF);
   header[20] = (unsigned char)((w_ >> 16) & 0xFF);
   header[21] = (unsigned char)((w_ >> 24) & 0xFF);
   header[22] = (unsigned char)(h_ & 0xFF);
   header[23] = (unsigned char)((h_ >> 8) & 0xFF);
   header[24] = (unsigned char)((h_ >> 16) & 0xFF);
   header[25] = (unsigned char)((h_ >> 24) & 0xFF);
   header[26] = 1;
   header[28] = 24;
   std::fwrite(header, 1, 54, f);
   std::vector<unsigned char> row((size_t)rowBytes, 0);
   for (int y = h_ - 1; y >= 0; y--) {
      for (int x = 0; x < w_; x++) {
         uint32_t px = (*px_)[(size_t)y * (size_t)w_ + (size_t)x];
         row[(size_t)x * 3 + 0] = (unsigned char)(px & 0xFF);
         row[(size_t)x * 3 + 1] = (unsigned char)((px >> 8) & 0xFF);
         row[(size_t)x * 3 + 2] = (unsigned char)((px >> 16) & 0xFF);
      }
      std::fwrite(row.data(), 1, (size_t)rowBytes, f);
   }
   std::fclose(f);
}
