import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;

// $VF: renamed from: c
public final class TextLoader {
   // $VF: renamed from: a f
   Game game;
   // $VF: renamed from: a byte[]
   byte[] dataBlob;
   // $VF: renamed from: a short[]
   short[] recordStarts;
   // $VF: renamed from: b byte[]
   byte[] uiTextData = new byte[1674];
   // $VF: renamed from: b short[]
   short[] uiTextOffsets = new short[230];
   // $VF: renamed from: c byte[]
   byte[] longTextData = new byte[37488];
   // $VF: renamed from: c short[]
   short[] longTextOffsets = new short[310];
   // $VF: renamed from: a byte[][]
   byte[][] textBuffers = new byte[][]{this.uiTextData, this.longTextData, this.longTextData};
   // $VF: renamed from: a short[][]
   short[][] textOffsets = new short[][]{this.uiTextOffsets, this.longTextOffsets, this.longTextOffsets};
   // $VF: renamed from: a java.lang.String[]
   String[] textFileNames = new String[]{"/s", "/t", "/d"};
   // $VF: renamed from: b byte[][]
   byte[][] glyphColumns;
   // $VF: renamed from: c byte[][]
   byte[][] glyphRows;
   // $VF: renamed from: d byte[]
   byte[] lineCharCounts;
   // $VF: renamed from: d short[]
   short[] linePixelWidths;
   // $VF: renamed from: e short[]
   short[] lineYOrAge;
   // $VF: renamed from: f short[]
   short[] lineXOrEntityId;
   // $VF: renamed from: e byte[]
   byte[] lineFontSizes;
   // $VF: renamed from: a byte
   byte lineCount = 0;
   // $VF: renamed from: b byte
   byte floatWriteIndex;
   // $VF: renamed from: c byte
   byte floatReadIndex;
   // $VF: renamed from: f byte[]
   byte[] pendingDeltas;
   // $VF: renamed from: a int
   int charIndex;
   // $VF: renamed from: a java.io.InputStream
   InputStream inputStream = null;
   // $VF: renamed from: b int
   int readByte;
   // $VF: renamed from: c int
   int cursor;
   // $VF: renamed from: d int
   int stringIndex;
   // $VF: renamed from: e int
   int langNameCharIndex;
   // $VF: renamed from: f int
   int storyFileIndex = -1;
   // $VF: renamed from: g int
   int storySectionId;
   // $VF: renamed from: h int
   int currentLineSlot;
   // $VF: renamed from: d byte
   byte currentCharCode;
   // $VF: renamed from: i int
   int totalWidth;
   // $VF: renamed from: j int
   int firstLineIndex;
   // $VF: renamed from: k int
   int loopIndex;
   // $VF: renamed from: l int
   int drawCharIndex;
   // $VF: renamed from: m int
   int drawX;
   // $VF: renamed from: n int
   int drawY;
   // $VF: renamed from: o int
   int glyphWidth;
   // $VF: renamed from: p int
   int prevGlyphWidth;
   // $VF: renamed from: q int
   int glyphDrawX;
   // $VF: renamed from: g short[]
   short[] wrappedLineStarts;
   // $VF: renamed from: h short[]
   short[] wrappedLineXOffsets;
   // $VF: renamed from: r int
   int wrappedTopY;
   // $VF: renamed from: s int
   int wrappedFontSize;
   // $VF: renamed from: t int
   int linesPerPage;
   // $VF: renamed from: u int
   int wrappedLineCount;
   // $VF: renamed from: v int
   int wrapScanIndex;
   // $VF: renamed from: w int
   int lastBreakIndex;
   // $VF: renamed from: x int
   int currentLineWidth;
   // $VF: renamed from: y int
   int widthAtLastBreak;
   // $VF: renamed from: z int
   int rowOrNumberIndex;
   // $VF: renamed from: A int
   int glyphColumn;

   public TextLoader(Game var1) {
      this.game = var1;
      this.dataBlob = this.game.fileAData;
      this.recordStarts = this.game.recordStarts;
   }

   // $VF: renamed from: a () void
   final void reset() {
      for (this.loopIndex = 13; this.loopIndex < 33; this.loopIndex++) {
         this.lineXOrEntityId[this.loopIndex] = 0;
      }

      this.floatWriteIndex = 0;
      this.floatReadIndex = 0;

      for (this.loopIndex = 0; this.loopIndex < 122; this.loopIndex++) {
         this.pendingDeltas[this.loopIndex] = 0;
      }
   }

   // $VF: renamed from: a (int) void
   final void selectStorySection(int var1) {
      this.game.tickerLastDialogId = -1;
      this.storySectionId = var1;

      for (this.cursor = this.recordStarts[308]; this.cursor < this.recordStarts[309]; this.cursor++) {
         if (var1 < this.dataBlob[this.cursor]) {
            this.cursor = this.cursor - this.recordStarts[308];
            break;
         }
      }

      if (this.storyFileIndex != this.cursor) {
         this.storyFileIndex = this.cursor;
         this.loadTextFile(2);
      }
   }

   // $VF: renamed from: b (int) void
   final void loadTextFile(int var1) {
      try {
         try {
            new StringBuffer().append("/").append(Game.languageIndex).append(this.textFileNames[var1]).toString();
            this.inputStream = this.getClass()
               .getResourceAsStream("/" + Game.languageIndex + this.textFileNames[var1] + (var1 == 2 ? String.valueOf(this.storyFileIndex) : ""));
            if (this.inputStream != null) {
               if (var1 < 2) {
                  this.stringIndex = 0;
                  this.textOffsets[var1][0] = 0;
                  this.cursor = 0;
               } else {
                  this.stringIndex = 71;
                  this.cursor = this.textOffsets[1][71];
               }

               while ((this.readByte = this.inputStream.read()) != -1) {
                  if (this.readByte == 124) {
                     this.stringIndex++;
                     this.textOffsets[var1][this.stringIndex] = (short)this.cursor;
                  } else {
                     if (this.readByte == 31) {
                        for (this.langNameCharIndex = 0; this.langNameCharIndex < this.game.version.length(); this.cursor++) {
                           this.textBuffers[var1][this.cursor] = this.dataBlob[this.recordStarts[280] + (byte)this.game.version.charAt(this.langNameCharIndex)];
                           this.langNameCharIndex++;
                        }

                        this.cursor--;
                     } else {
                        this.textBuffers[var1][this.cursor] = this.dataBlob[this.recordStarts[280] + this.readByte];
                     }

                     this.cursor++;
                  }
               }
            }

            this.inputStream.close();
            this.inputStream = null;
         } catch (IOException var3) {
            return;
         }
      } catch (Exception var4) {
      }
   }

   // $VF: renamed from: a (int, int, int, java.lang.String, int) void
   final void queueString(int var1, int var2, int var3, String var4, int var5) {
      for (this.charIndex = 0; this.charIndex < var4.length(); this.charIndex++) {
         this.uiTextData[this.uiTextOffsets[228] + this.charIndex] = this.dataBlob[this.recordStarts[280] + (byte)var4.charAt(this.charIndex)];
      }

      this.uiTextOffsets[229] = (short)(this.uiTextOffsets[228] + var4.length());
      this.queueLine(var1, var2, var3, 228, var5);
   }

   // $VF: renamed from: a (int, int, int, int, int) void
   final void queueLine(int var1, int var2, int var3, int var4, int var5) {
      this.currentLineSlot = var2 == -100 ? 13 + this.floatWriteIndex : this.lineCount;
      if (this.currentLineSlot < 13 || this.lineXOrEntityId[this.currentLineSlot] == 0) {
         this.lineCharCounts[this.currentLineSlot] = (byte)(this.uiTextOffsets[var4 + 1] - this.uiTextOffsets[var4]);
         this.lineFontSizes[this.currentLineSlot] = (byte)var5;
         if (this.currentLineSlot >= 13) {
            var5 = 1;
         }

         this.linePixelWidths[this.currentLineSlot] = (short)((this.lineCharCounts[this.currentLineSlot] - 1) * (0 + var5));
         this.lineXOrEntityId[this.currentLineSlot] = (short)var1;
         this.lineYOrAge[this.currentLineSlot] = (short)(this.currentLineSlot >= 13 ? 0 : var2);

         for (this.charIndex = 0; this.charIndex < this.lineCharCounts[this.currentLineSlot]; this.charIndex++) {
            this.currentCharCode = this.uiTextData[this.uiTextOffsets[var4] + this.charIndex];
            if (this.currentCharCode == 126) {
               this.glyphRows[this.currentLineSlot][this.charIndex] = this.currentCharCode;
               this.linePixelWidths[this.currentLineSlot] = (short)(this.linePixelWidths[this.currentLineSlot] - ((0 + var5) * 2 + 2));
            } else {
               this.glyphRows[this.currentLineSlot][this.charIndex] = (byte)(this.currentCharCode / 30);
               this.glyphColumns[this.currentLineSlot][this.charIndex] = (byte)(this.currentCharCode % 30);
               if (this.currentCharCode / 30 != 0 || this.currentCharCode % 30 < 14) {
                  this.linePixelWidths[this.currentLineSlot] = (short)(
                     this.linePixelWidths[this.currentLineSlot]
                        + (
                           (
                                 this.dataBlob[this.recordStarts[281 + var5]
                                       + this.glyphRows[this.currentLineSlot][this.charIndex] * 21
                                       + this.glyphColumns[this.currentLineSlot][this.charIndex]
                                       + 1]
                                    & 255
                              )
                              - (
                                 this.dataBlob[this.recordStarts[281 + var5]
                                       + this.glyphRows[this.currentLineSlot][this.charIndex] * 21
                                       + this.glyphColumns[this.currentLineSlot][this.charIndex]]
                                    & 255
                              )
                        )
                  );
               }
            }
         }

         if (var4 == 97) {
            this.game.a_int_int_int_void(1041, 26, var2 - 2);
            this.game.a_int_int_int_void(1047, 211, var2 - 2);
         }

         if (this.currentLineSlot < 13) {
            this.lineXOrEntityId[this.currentLineSlot] = (short)(
               this.lineXOrEntityId[this.currentLineSlot] - this.linePixelWidths[this.currentLineSlot] * var3 / 2
            );
            this.lineCount++;
         } else {
            this.floatWriteIndex++;
            if (this.floatWriteIndex >= 20) {
               this.floatWriteIndex = 0;
            }
         }
      }
   }

   // $VF: renamed from: a (int, int, int, int, int, int, int) void
   final void queueLineParts(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (var4 >= 0) {
         this.queueLine(var1, var2, 0, var4, var7);
         this.totalWidth = this.linePixelWidths[this.currentLineSlot] + 1;
         this.firstLineIndex = this.currentLineSlot;
      }

      if (var5 >= 0) {
         this.queueLine(var1 + this.totalWidth, var2, 0, var5, var7);
         this.totalWidth = this.totalWidth + this.linePixelWidths[this.currentLineSlot] + 1;
      }

      if (var6 >= 0) {
         this.queueLine(var1 + this.totalWidth, var2, 0, var6, var7);
         this.totalWidth = this.totalWidth + this.linePixelWidths[this.currentLineSlot];
      }

      while (this.firstLineIndex < this.lineCount) {
         this.lineXOrEntityId[this.firstLineIndex] = (short)(this.lineXOrEntityId[this.firstLineIndex] - this.totalWidth * var3 / 2);
         this.firstLineIndex++;
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Graphics) void
   final void drawLines(Graphics var1) {
      for (this.loopIndex = 0; this.loopIndex < this.lineCount; this.loopIndex++) {
         this.drawX = this.lineXOrEntityId[this.loopIndex];

         for (this.drawCharIndex = 0; this.drawCharIndex < this.lineCharCounts[this.loopIndex]; this.drawCharIndex++) {
            if (this.glyphRows[this.loopIndex][this.drawCharIndex] == 126) {
               this.drawX = this.drawX - (0 + this.lineFontSizes[this.loopIndex] + 2);
            } else {
               this.drawY = this.lineYOrAge[this.loopIndex];
               this.glyphDrawX = this.drawX;
               this.glyphWidth = (
                     this.dataBlob[this.recordStarts[281 + this.lineFontSizes[this.loopIndex]]
                           + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                           + this.glyphColumns[this.loopIndex][this.drawCharIndex]
                           + 1]
                        & 255
                  )
                  - (
                     this.dataBlob[this.recordStarts[281 + this.lineFontSizes[this.loopIndex]]
                           + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                           + this.glyphColumns[this.loopIndex][this.drawCharIndex]]
                        & 255
                  );
               if (this.glyphRows[this.loopIndex][this.drawCharIndex] == 0 && this.glyphColumns[this.loopIndex][this.drawCharIndex] >= 14) {
                  this.drawY = this.drawY - this.dataBlob[this.recordStarts[285] + this.glyphColumns[this.loopIndex][this.drawCharIndex] - 14];
                  this.glyphDrawX = this.glyphDrawX - (0 + (this.glyphWidth + this.prevGlyphWidth) / 2);
               }

               Game.drawRegionSafe(
                  var1,
                  this.game.spritePacks[0][25 + this.lineFontSizes[this.loopIndex]],
                  this.dataBlob[this.recordStarts[281 + this.lineFontSizes[this.loopIndex]]
                        + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                        + this.glyphColumns[this.loopIndex][this.drawCharIndex]]
                     & 255,
                  this.dataBlob[this.recordStarts[284] + this.lineFontSizes[this.loopIndex] * 3 + this.glyphRows[this.loopIndex][this.drawCharIndex]],
                  this.glyphWidth,
                  this.dataBlob[this.recordStarts[283] + this.lineFontSizes[this.loopIndex] * 3 + this.glyphRows[this.loopIndex][this.drawCharIndex]],
                  11 + this.glyphDrawX,
                  8 + this.drawY,
                  20
               );
               if (this.glyphRows[this.loopIndex][this.drawCharIndex] != 0 || this.glyphColumns[this.loopIndex][this.drawCharIndex] < 14) {
                  this.drawX = this.drawX + this.glyphWidth + 0 + this.lineFontSizes[this.loopIndex];
               }

               this.prevGlyphWidth = this.glyphWidth;
            }
         }
      }

      this.lineCount = 0;
   }

   // $VF: renamed from: b () void
   final void updateFloatingTexts() {
      for (this.loopIndex = 13 + this.floatReadIndex; this.loopIndex != 13 + this.floatWriteIndex; this.loopIndex++) {
         this.lineYOrAge[this.loopIndex] = (short)(this.lineYOrAge[this.loopIndex] + this.lineYOrAge[this.loopIndex] / 4 + 1);
         if (this.lineYOrAge[this.loopIndex] >= 15) {
            this.lineXOrEntityId[this.loopIndex] = 0;
            this.floatReadIndex++;
            if (this.floatReadIndex >= 20) {
               this.floatReadIndex = 0;
            }
         }

         if (this.loopIndex == 32) {
            this.loopIndex = 12;
         }
      }

      for (this.loopIndex = 0; this.loopIndex < 122; this.loopIndex++) {
         if (this.pendingDeltas[this.loopIndex] != 0 && this.loopIndex % 3 == this.game.tickCounter % 3) {
            this.queueString(
               this.loopIndex,
               -100,
               0,
               (this.pendingDeltas[this.loopIndex] > 0 ? "+" : "") + this.pendingDeltas[this.loopIndex],
               this.pendingDeltas[this.loopIndex] > 0 ? 2 : (this.loopIndex <= 50 ? 1 : 0)
            );
            this.pendingDeltas[this.loopIndex] = 0;
         }
      }
   }

   // $VF: renamed from: a (int, int, int) void
   final void drawFloatingTexts(int var1, int var2, int var3) {
      for (this.loopIndex = 13 + this.floatReadIndex; this.loopIndex != 13 + this.floatWriteIndex; this.loopIndex++) {
         if (this.lineXOrEntityId[this.loopIndex] == var1) {
            this.drawX = var2 - this.linePixelWidths[this.loopIndex] / 2 + this.game.randRange(255, 5) - 2;
            this.drawY = var3 - 5 - this.lineYOrAge[this.loopIndex] * 2;

            for (this.drawCharIndex = 0; this.drawCharIndex < this.lineCharCounts[this.loopIndex]; this.drawCharIndex++) {
               this.glyphWidth = (
                     this.dataBlob[this.recordStarts[282]
                           + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                           + this.glyphColumns[this.loopIndex][this.drawCharIndex]
                           + 1]
                        & 255
                  )
                  - (
                     this.dataBlob[this.recordStarts[282]
                           + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                           + this.glyphColumns[this.loopIndex][this.drawCharIndex]]
                        & 255
                  );
               Game.drawRegionSafe(
                  Game.screenBufferGraphics[1],
                  this.game.spritePacks[0][26],
                  this.dataBlob[this.recordStarts[282]
                        + this.glyphRows[this.loopIndex][this.drawCharIndex] * 21
                        + this.glyphColumns[this.loopIndex][this.drawCharIndex]]
                     & 255,
                  this.dataBlob[this.recordStarts[284] + 3 + this.glyphRows[this.loopIndex][this.drawCharIndex]]
                     + this.dataBlob[this.recordStarts[283] + 3 + 0] * this.lineFontSizes[this.loopIndex],
                  this.glyphWidth,
                  this.dataBlob[this.recordStarts[283] + 3 + this.glyphRows[this.loopIndex][this.drawCharIndex]],
                  11 + this.drawX,
                  8 + this.drawY,
                  20
               );
               this.drawX = this.drawX + this.glyphWidth + 0 + 1;
            }
         }

         if (this.loopIndex >= 32) {
            this.loopIndex = 12;
         }
      }
   }

   // $VF: renamed from: b (int, int, int, int, int, int, int) void
   final void wrapText(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      var1++;
      var3 -= 2;
      var2 += 4;
      var4 -= 4;
      if (var5 >= 71) {
         var5 += this.dataBlob[this.recordStarts[309] + this.storySectionId] & 255;
      }

      this.lastBreakIndex = 0;
      this.wrappedLineCount = 0;
      this.wrappedLineStarts[0] = this.textOffsets[1][var5];
      this.currentLineWidth = 0;

      for (this.wrapScanIndex = this.textOffsets[1][var5] & '\uffff'; this.wrapScanIndex < (this.textOffsets[1][var5 + 1] & '\uffff'); this.wrapScanIndex++) {
         if (this.textBuffers[1][this.wrapScanIndex] == 42
               && (this.textBuffers[1][this.wrapScanIndex + 1] < 90 || this.textBuffers[1][this.wrapScanIndex + 1] >= 112)
            || this.textBuffers[1][this.wrapScanIndex] == 127) {
            this.lastBreakIndex = this.wrapScanIndex + 1;
            this.widthAtLastBreak = this.currentLineWidth;
         }

         if (this.textBuffers[1][this.wrapScanIndex] == 126) {
            this.currentLineWidth -= 0 + var7 + 2;
         } else if (this.textBuffers[1][this.wrapScanIndex] < 127
            && (this.textBuffers[1][this.wrapScanIndex] / 30 != 0 || this.textBuffers[1][this.wrapScanIndex] % 30 < 14)) {
            this.currentLineWidth += 0 + var7;
            this.currentLineWidth = this.currentLineWidth
               + (
                  this.textBuffers[1][this.wrapScanIndex] >= 90
                     ? 15
                     : (
                           this.dataBlob[this.recordStarts[281 + var7]
                                 + this.textBuffers[1][this.wrapScanIndex] / 30 * 21
                                 + this.textBuffers[1][this.wrapScanIndex] % 30
                                 + 1]
                              & 255
                        )
                        - (
                           this.dataBlob[this.recordStarts[281 + var7]
                                 + this.textBuffers[1][this.wrapScanIndex] / 30 * 21
                                 + this.textBuffers[1][this.wrapScanIndex] % 30]
                              & 255
                        )
               );
         }

         if (this.currentLineWidth > var3 && this.lastBreakIndex > (this.wrappedLineStarts[this.wrappedLineCount] & '\uffff')
            || this.textBuffers[1][this.wrapScanIndex] == 127) {
            this.wrappedLineCount++;
            this.wrappedLineStarts[this.wrappedLineCount] = (short)this.lastBreakIndex;
            this.wrappedLineXOffsets[this.wrappedLineCount - 1] = (short)(var1 + (var3 - this.widthAtLastBreak) * (var6 & 1) / 2);
            this.wrapScanIndex = this.lastBreakIndex - 1;
            this.currentLineWidth = 0;
         }

         if (this.textBuffers[1][this.wrapScanIndex] == 70 || this.textBuffers[1][this.wrapScanIndex] == 73 || this.textBuffers[1][this.wrapScanIndex] == 78) {
            this.lastBreakIndex = this.wrapScanIndex + 1;
            this.widthAtLastBreak = this.currentLineWidth;
         }
      }

      if (this.currentLineWidth > 0) {
         this.wrappedLineCount++;
         this.wrappedLineXOffsets[this.wrappedLineCount - 1] = (short)(var1 + (var3 - this.currentLineWidth) * (var6 & 1) / 2);
         this.wrappedLineStarts[this.wrappedLineCount] = this.textOffsets[1][var5 + 1];
      }

      this.wrappedTopY = var2 + (var6 == 3 && this.wrappedLineCount * 17 < var4 ? (var4 - this.wrappedLineCount * 17) / 2 : 0);
      this.wrappedFontSize = var7;
      this.linesPerPage = var4 / 17;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Graphics, int) void
   final void drawTextPage(Graphics var1, int var2) {
      try {
         for (this.loopIndex = var2; this.loopIndex < var2 + this.linesPerPage && this.loopIndex < this.wrappedLineCount; this.loopIndex++) {
            this.drawX = this.wrappedLineXOffsets[this.loopIndex];

            for (this.drawCharIndex = this.wrappedLineStarts[this.loopIndex] & '\uffff';
               this.drawCharIndex < (this.wrappedLineStarts[this.loopIndex + 1] & '\uffff');
               this.drawCharIndex++
            ) {
               if (this.textBuffers[1][this.drawCharIndex] != 127) {
                  if (this.textBuffers[1][this.drawCharIndex] == 126) {
                     this.drawX = this.drawX - (0 + this.wrappedFontSize + 2);
                  } else {
                     this.drawY = this.wrappedTopY + (this.loopIndex - var2) * 17;
                     if (this.textBuffers[1][this.drawCharIndex] > 112) {
                        this.rowOrNumberIndex = this.textBuffers[1][this.drawCharIndex] - 112 - 1 + 19;
                        this.queueString(
                           24,
                           this.drawY,
                           0,
                           String.valueOf(
                              this.rowOrNumberIndex == 24
                                 ? this.game.tribeStats[0][this.rowOrNumberIndex] * 10 + this.game.randRange(65535, 10)
                                 : this.game.tribeStats[0][this.rowOrNumberIndex]
                           ),
                           0
                        );
                        this.queueString(
                           216,
                           this.drawY,
                           2,
                           String.valueOf(
                              this.rowOrNumberIndex == 24
                                 ? this.game.tribeStats[1][this.rowOrNumberIndex] * 10 + this.game.randRange(65535, 10)
                                 : this.game.tribeStats[1][this.rowOrNumberIndex]
                           ),
                           0
                        );
                     } else {
                        this.rowOrNumberIndex = this.textBuffers[1][this.drawCharIndex] / 30;
                        this.glyphColumn = this.textBuffers[1][this.drawCharIndex] % 30;
                        this.glyphWidth = this.rowOrNumberIndex >= 3
                           ? 15
                           : (this.dataBlob[this.recordStarts[281 + this.wrappedFontSize] + this.rowOrNumberIndex * 21 + this.glyphColumn + 1] & 255)
                              - (this.dataBlob[this.recordStarts[281 + this.wrappedFontSize] + this.rowOrNumberIndex * 21 + this.glyphColumn] & 255);
                        this.glyphDrawX = this.drawX;
                        if (this.rowOrNumberIndex >= 3) {
                           this.game.a_int_int_int_void(560 + this.textBuffers[1][this.drawCharIndex] - 90, this.glyphDrawX, this.drawY + -1);
                        } else {
                           if (this.rowOrNumberIndex == 0 && this.glyphColumn >= 14) {
                              this.drawY = this.drawY - this.dataBlob[this.recordStarts[285] + this.glyphColumn - 14];
                              this.glyphDrawX = this.glyphDrawX - (0 + (this.glyphWidth + this.prevGlyphWidth) / 2);
                           }

                           Game.drawRegionSafe(
                              var1,
                              this.game.spritePacks[0][25 + this.wrappedFontSize],
                              this.dataBlob[this.recordStarts[281 + this.wrappedFontSize] + this.rowOrNumberIndex * 21 + this.glyphColumn] & 255,
                              this.dataBlob[this.recordStarts[284] + this.wrappedFontSize * 3 + this.rowOrNumberIndex],
                              this.glyphWidth,
                              this.dataBlob[this.recordStarts[283] + this.wrappedFontSize * 3 + this.rowOrNumberIndex],
                              11 + this.glyphDrawX,
                              8 + this.drawY,
                              20
                           );
                        }

                        if (this.rowOrNumberIndex != 0 || this.glyphColumn < 14) {
                           this.drawX = this.drawX + this.glyphWidth + 0 + this.wrappedFontSize;
                        }

                        this.prevGlyphWidth = this.glyphWidth;
                     }
                  }
               }
            }
         }

         if (var2 == 0) {
            this.game.menuScrollUp = true;
         } else {
            this.game.menuScrollUp = false;
         }

         if (var2 + this.linesPerPage < this.wrappedLineCount) {
            this.game.menuScrollDown = false;
            return;
         }

         this.game.menuScrollDown = true;
      } catch (Exception var4) {
      }
   }
}
