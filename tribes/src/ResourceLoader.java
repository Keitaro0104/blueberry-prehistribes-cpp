import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

// $VF: renamed from: e
public final class ResourceLoader {
   // $VF: renamed from: a f
   Game game;
   // $VF: renamed from: a short[]
   short[] pd0TableLengths = new short[]{
      119,
      1291,
      162,
      162,
      2782,
      144,
      1069,
      6903,
      823,
      1721,
      5492,
      119,
      1291,
      1291,
      1291,
      162,
      162,
      162,
      2782,
      2782,
      144,
      1069,
      1069,
      6903,
      6903,
      6903,
      823,
      1721,
      42,
      5492,
      5492,
      1810,
      1810,
      1810,
      1810,
      1810
   };
   // $VF: renamed from: a byte
   byte currentPackIndex;
   // $VF: renamed from: a byte[]
   byte[] packImageCounts = new byte[]{31, 6, 12};
   // $VF: renamed from: a java.lang.String[]
   String[] packPaths = new String[]{"/pi0", "/pi9", "/pi8"};

   public ResourceLoader(Game var1) {
      this.game = var1;
      this.allocateBalanceTables();
      this.initScreenBuffers();
      this.initWorldState();
      this.currentPackIndex = 0;
      this.loadSpritePack();
      this.currentPackIndex = 1;
      this.loadSpritePack();
      this.loadBalanceData();
      this.loadGameData();
      this.initTextLoader();
      this.loadMusic();
      this.loadMaps();
   }

   // $VF: renamed from: a () void
   private void allocateBalanceTables() {
      System.gc();
      Thread.yield();
      int var1 = 0;
      this.game.pd0ShortTables = new short[11][];
      this.game.pd0ByteTables = new byte[25][];

      for (int var2 = 0; var2 <= 24; var2++) {
         this.game.pd0ByteTables[var2] = new byte[this.pd0TableLengths[var1 + 10 + 1]];
         if (var2 <= 10) {
            this.game.pd0ShortTables[var2] = new short[this.pd0TableLengths[var1]];
         }

         var1++;
      }

      System.gc();
      Thread.yield();
      this.game.spritePacks = new Image[3][];
      this.game.spritePacks[2] = new Image[1];
   }

   // $VF: renamed from: b () void
   private void loadBalanceData() {
      System.gc();
      Thread.yield();

      try {
         DataInputStream var1;
         (var1 = new DataInputStream(this.getClass().getResourceAsStream("/pd0"))).readByte();
         int var2 = 0;

         for (int var3 = 0; var3 <= 10; var3++) {
            for (int var4 = 0; var4 < this.pd0TableLengths[var2]; var4++) {
               this.game.pd0ShortTables[var3][var4] = var1.readShort();
            }

            var2++;
            this.game.drawLoadProgress(1);
         }

         for (int var7 = 0; var7 <= 24; var7++) {
            for (int var5 = 0; var5 < this.pd0TableLengths[var2]; var5++) {
               this.game.pd0ByteTables[var7][var5] = var1.readByte();
            }

            var2++;
            this.game.drawLoadProgress(1);
         }

         var1.close();
         Game.menuRowHeightPx = this.game.pd0ByteTables[23][this.game.pd0ShortTables[10][this.game.pd0ShortTables[9][548] & 8191] & 8191] + 9;
         Game.menuMaxVisibleRows = (240 - this.game.pd0ByteTables[1][1010] * 2) / Game.menuRowHeightPx;
      } catch (Exception var6) {
      }
   }

   // $VF: renamed from: c () void
   private void initScreenBuffers() {
      try {
         System.gc();
         Thread.yield();
         this.game.viewHeightPx = 288;
         this.game.screenColsTiles = 11;
         this.game.screenRowsTiles = (byte)((this.game.viewHeightPx - 1) / 16 + 1);
         boolean var1 = false;
         this.game.cursorCol = (byte)(this.game.screenColsTiles >> 1);
         this.game.cursorRow = (byte)(this.game.screenRowsTiles >> 1);
         this.game.homeCursorCol = this.game.cursorCol;
         this.game.homeCursorRow = this.game.cursorRow;
         this.game.screenWidths = new int[]{264, 264, 240, 240, 240, 240, 68, 240, 68, 63, 240, 240, 240};
         this.game.screenHeights = new int[]{352, 304, 22, 48, 22, 48, 55, 86, 49, 55, 38, 0, 0};
         this.game.screenContentHeights = new int[]{0, 0, 304, 304, 326, 326, 0, 326, 0, 0, 326, 0, 0};
         this.game.screenPanelY = new int[]{0, 16, 0, 0, 298, 272, 0, 234, 0, 0, 282, 320, 0};
         this.game.screenBufferIndex = new byte[]{0, 1, 0, 0, 0, 0, 2, 0, 2, 2, 0, 0, 0};
         this.game.hudPanelForScreen = new byte[]{0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         Game.screenBuffers[this.game.screenBufferIndex[1]] = Image.createImage(264, 304);
         Game.screenBufferGraphics[this.game.screenBufferIndex[1]] = Game.screenBuffers[this.game.screenBufferIndex[1]].getGraphics();
         Game.screenBuffers[3] = Image.createImage(264, 304);
         Game.screenBufferGraphics[3] = Game.screenBuffers[3].getGraphics();
         Game.screenBuffers[this.game.screenBufferIndex[6]] = Image.createImage(79, 63);
         Game.screenBufferGraphics[this.game.screenBufferIndex[6]] = Game.screenBuffers[this.game.screenBufferIndex[6]].getGraphics();
         this.game.bufferCopyY = this.game.screenHeights[2];
      } catch (Exception var10) {
      }
   }

   // $VF: renamed from: d () void
   private void loadSpritePack() {
      System.gc();
      Thread.yield();
      byte var1 = this.packImageCounts[this.currentPackIndex];
      byte var3 = 0;

      try {
         System.gc();
         Thread.yield();
         DataInputStream var6 = new DataInputStream(this.getClass().getResourceAsStream(this.packPaths[this.currentPackIndex]));
         byte[] var7 = new byte[62502];
         var6.read(var7);
         var6.close();
         int var8 = 0;
         var3 = 0;

         while (true) {
            if (var3 >= var1) {
               return;
            }

            byte var2 = (byte)(var7[var8] & 0xFF);
            var8++;
            if (var3 == 0) {
               this.game.spritePacks[this.currentPackIndex] = new Image[var1];
            }

            if (var2 == 255) {
               break;
            }

            short var5 = (short)(var2 << 8 | var7[var8] & 0xFF);
            var8++;

            try {
               if (var3 < 27) {
                  this.game.spritePacks[this.currentPackIndex][var3] = Image.createImage(var7, var8, var5);
               }
            } catch (Exception var10) {
            }

            var8 += var5;
            this.game.drawLoadProgress(2);
            System.gc();
            Thread.yield();
            var3++;
         }
      } catch (Exception var11) {
      }
   }

   // $VF: renamed from: e () void
   private void initTextLoader() {
      System.gc();
      Thread.yield();
      this.game.textLoader = new TextLoader(this.game);
      this.game.textLoader.pendingDeltas = new byte[122];
      this.game.textLoader.lineFontSizes = new byte[33];
      this.game.textLoader.glyphColumns = new byte[33][25];
      this.game.textLoader.glyphRows = new byte[33][25];
      this.game.textLoader.lineCharCounts = new byte[33];
      this.game.textLoader.linePixelWidths = new short[33];
      this.game.textLoader.lineYOrAge = new short[33];
      this.game.textLoader.lineXOrEntityId = new short[33];
      this.game.textLoader.wrappedLineStarts = new short[250];
      this.game.textLoader.wrappedLineXOffsets = new short[250];
      this.game.textLoader.loadTextFile(0);
   }

   // $VF: renamed from: f () void
   private void loadGameData() {
      System.gc();
      Thread.yield();
      this.game.fileAData = new byte[17638];
      this.game.recordStarts = new short[312];
      this.game.offsetTable = new short[570];
      this.game.unusedShortArrayC = new short[5];
      InputStream var1 = null;

      try {
         if ((var1 = this.getClass().getResourceAsStream("/a")) != null) {
            this.game.recordStarts[0] = this.game.unusedShortArrayC[0] = 0;

            for (int var2 = 0; var2 < 312; var2++) {
               int var3 = var1.read() & 0xFF;
               int var5 = ((var1.read() & 0xFF) << 8) + var3;
               if (var2 < 311) {
                  this.game.recordStarts[var2 + 1] = (short)(this.game.recordStarts[var2] + var5);
               }

               if (var5 > 0) {
                  var1.read(this.game.fileAData, this.game.recordStarts[var2], var5);
               }
            }

            for (int var9 = 0; var9 < 5; var9++) {
               int var10 = var1.read() & 0xFF;
               int var12 = ((var1.read() & 0xFF) << 8) + var10;
               if (var9 < 4) {
                  this.game.unusedShortArrayC[var9 + 1] = (short)(this.game.unusedShortArrayC[var9] + var12);
               }

               for (int var11 = 0; var11 < var12; var11++) {
                  int var4 = var1.read() & 0xFF;
                  this.game.offsetTable[this.game.unusedShortArrayC[var9] + var11] = (short)(((var1.read() & 0xFF) << 8) + var4);
               }
            }
         }

         var1.close();
      } catch (IOException var7) {
      }
   }

   // $VF: renamed from: g () void
   private void loadMusic() {
      try {
         System.gc();
         Thread.yield();
         Game.unusedShortArrayE = new short[]{8014, 6315, 5648, 3405, 3234};
         Game.unusedShortArrayF = new short[]{0, 8014, 14329, 19977, 23382};
         Game.midiData = new byte[26616];
         this.game.musicTrackTable = new byte[]{0, 1, 2, 3, 4, 4};
         InputStream var1;
         (var1 = this.getClass().getResourceAsStream("/sa")).read(Game.midiData, 0, 26616);
         var1.close();
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: h () void
   private void loadMaps() {
      try {
         System.gc();
         Thread.yield();
         this.game.mapChunkOffsets = new int[]{0, 2545, 6965, 10649, 15487, 22633, 27261, 31708, 35466, 39214};
         this.game.mapData = new byte[42381];
         InputStream var1;
         (var1 = this.getClass().getResourceAsStream("/ma")).read(this.game.mapData, 0, 42381);
         var1.close();
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: i () void
   private void initWorldState() {
      System.gc();
      Thread.yield();
      this.game.entityData = new byte[9983];
      this.game.terrainGrid = new byte[96][96];
      this.game.occupantGrid = new byte[96][96];
      this.game.titleParticles = new int[3][4];

      try {
         this.game.version = this.game.midlet.getAppProperty("MIDlet-Version");
         this.game.gameLinkEnabled = this.game.midlet.getAppProperty("GameLinkEnabled").equals("true");
         this.game.cheatsEnabled = this.game.midlet.getAppProperty("Cheat-Support").equals("on");
      } catch (Exception var2) {
      }

      if (this.game.gameLinkEnabled) {
         this.game.homeScreenId = 1;
      }
   }
}
