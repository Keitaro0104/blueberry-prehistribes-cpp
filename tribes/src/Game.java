import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;

// $VF: renamed from: f
public final class Game extends Canvas implements Runnable {
   // $VF: renamed from: a int
   static int softkeyLeftCode = -6;
   // $VF: renamed from: b int
   static int softkeyRightCode = -7;
   // $VF: renamed from: a boolean
   boolean cheatsEnabled = true;
   // $VF: renamed from: a tribes
   TribesMIDlet midlet;
   // $VF: renamed from: a b
   World world;
   // $VF: renamed from: a c
   TextLoader textLoader;
   // $VF: renamed from: b boolean
   boolean running;
   // $VF: renamed from: c int
   int tickCounter;
   // $VF: renamed from: d int
   int msgAdvanceCounter;
   // $VF: renamed from: e int
   int animFrame;
   // $VF: renamed from: f int
   int animCycle;
   // $VF: renamed from: g int
   int uptimeTicks;
   // $VF: renamed from: h int
   int trapScanRow;
   // $VF: renamed from: i int
   int tickLengthMs;
   // $VF: renamed from: j int
   int tickMsAlt = 120;
   // $VF: renamed from: k int
   int animSpeedFactor = 1;
   // $VF: renamed from: c boolean
   boolean fastTick;
   // $VF: renamed from: a byte
   static byte languageIndex;
   // $VF: renamed from: a java.lang.String
   String version;
   // $VF: renamed from: d boolean
   boolean gameLinkEnabled;
   // $VF: renamed from: b byte
   byte selectedDifficulty;
   // $VF: renamed from: c byte
   byte campaignDifficulty;
   // $VF: renamed from: d byte
   byte customDifficulty;
   // $VF: renamed from: l int
   int chiefId = 1;
   // $VF: renamed from: a byte[]
   byte[] fileAData;
   // $VF: renamed from: a short[]
   short[] recordStarts;
   // $VF: renamed from: b short[]
   short[] offsetTable;
   // $VF: renamed from: c short[]
   short[] unusedShortArrayC;
   // $VF: renamed from: e byte
   byte musicLevel;
   // $VF: renamed from: f byte
   static byte nextMusicTrack;
   // $VF: renamed from: g byte
   static byte currentMusicTrack;
   // $VF: renamed from: b byte[]
   byte[] musicTrackTable;
   // $VF: renamed from: a a
   MidiPlayer midiPlayer;
   // $VF: renamed from: h byte
   byte musicMood;
   // $VF: renamed from: m int
   int gameAction;
   // $VF: renamed from: e boolean
   boolean keyUp;
   // $VF: renamed from: f boolean
   boolean keyDown;
   // $VF: renamed from: g boolean
   boolean keyLeft;
   // $VF: renamed from: h boolean
   boolean keyRight;
   // $VF: renamed from: i boolean
   boolean firePressed;
   // $VF: renamed from: j boolean
   boolean storyAdvancePressed;
   // $VF: renamed from: k boolean
   boolean worldReady;
   // $VF: renamed from: i byte
   byte numericKey;
   // $VF: renamed from: l boolean
   static boolean leftSoftkeyPressed;
   // $VF: renamed from: m boolean
   static boolean rightSoftkeyPressed;
   // $VF: renamed from: j byte
   byte ticksSinceKeypress;
   // $VF: renamed from: n int
   int fireTapCount;
   // $VF: renamed from: o int
   int startupPhase;
   // $VF: renamed from: p int
   int tickCount;
   // $VF: renamed from: n boolean
   boolean inputLocked;
   // $VF: renamed from: o boolean
   boolean keyDownFlag;
   // $VF: renamed from: p boolean
   boolean keyReleasedFlag;
   // $VF: renamed from: k byte
   byte selectedVillagerId;
   // $VF: renamed from: l byte
   byte inspectedVillagerId;
   // $VF: renamed from: q boolean
   boolean moveOrderFlash;
   // $VF: renamed from: q int
   int menuTargetId;
   // $VF: renamed from: r int
   int orderTargetId;
   // $VF: renamed from: s int
   int placementType;
   // $VF: renamed from: t int
   int placeGhostX;
   // $VF: renamed from: u int
   int placeGhostY;
   // $VF: renamed from: r boolean
   boolean placementValid;
   // $VF: renamed from: v int
   int dialogId;
   // $VF: renamed from: w int
   int tickerLastDialogId;
   // $VF: renamed from: x int
   int savedDialogId;
   // $VF: renamed from: y int
   int scrollOffsetY;
   // $VF: renamed from: z int
   int tickerScrollShown;
   // $VF: renamed from: m byte
   byte endScreenPhase;
   // $VF: renamed from: n byte
   byte freeOrderSlot;
   // $VF: renamed from: o byte
   byte storySpeaker;
   // $VF: renamed from: s boolean
   boolean storyMode;
   // $VF: renamed from: p byte
   byte newGameFlag;
   // $VF: renamed from: q byte
   byte missionIndex;
   // $VF: renamed from: r byte
   byte campaignProgress;
   // $VF: renamed from: s byte
   byte continueProgress;
   // $VF: renamed from: t byte
   byte savedMissionProgress;
   // $VF: renamed from: u byte
   byte endgameTimer;
   // $VF: renamed from: v byte
   byte mode;
   // $VF: renamed from: a short
   short screenBaseX;
   // $VF: renamed from: b short
   short screenBaseY;
   // $VF: renamed from: A int
   int viewHeightPx;
   // $VF: renamed from: a int[][]
   int[][] titleParticles;
   // $VF: renamed from: w byte
   byte interactMode;
   // $VF: renamed from: x byte
   byte savedMode;
   // $VF: renamed from: y byte
   byte cursorCol;
   // $VF: renamed from: z byte
   byte savedCursorCol;
   // $VF: renamed from: A byte
   byte cursorRow;
   // $VF: renamed from: B byte
   byte savedCursorRow;
   // $VF: renamed from: C byte
   byte homeCursorCol;
   // $VF: renamed from: D byte
   byte homeCursorRow;
   // $VF: renamed from: B int
   int selRectX;
   // $VF: renamed from: C int
   int selRectY;
   // $VF: renamed from: D int
   int selRectW;
   // $VF: renamed from: E int
   int selRectH;
   // $VF: renamed from: t boolean
   boolean fireHeld;
   // $VF: renamed from: u boolean
   boolean dragSelecting;
   // $VF: renamed from: E byte
   byte fireHoldTicks;
   // $VF: renamed from: F int
   int selectedGroup;
   // $VF: renamed from: G int
   int dragStartTileX;
   // $VF: renamed from: H int
   int dragStartTileY;
   // $VF: renamed from: F byte
   byte fireHeldCount;
   // $VF: renamed from: G byte
   byte groupSlot;
   // $VF: renamed from: a byte[][]
   byte[][] villagerGroups;
   // $VF: renamed from: v boolean
   boolean fireTapped;
   // $VF: renamed from: w boolean
   boolean keyHeld;
   // $VF: renamed from: I int
   int groupKeyHoldTicks;
   // $VF: renamed from: H byte
   byte groupMemberTmp;
   // $VF: renamed from: J int
   int dragRectScanX;
   // $VF: renamed from: K int
   int dragRectScanY;
   // $VF: renamed from: L int
   int selCountTemp;
   // $VF: renamed from: M int
   int selectedCount;
   // $VF: renamed from: I byte
   byte camTileX;
   // $VF: renamed from: J byte
   byte savedCamCol;
   // $VF: renamed from: K byte
   byte camTileY;
   // $VF: renamed from: L byte
   byte savedCamRow;
   // $VF: renamed from: N int
   int spriteTableIndex;
   // $VF: renamed from: O int
   int spriteDataValue;
   // $VF: renamed from: P int
   int spriteFlags;
   // $VF: renamed from: Q int
   int minimapRectX;
   // $VF: renamed from: R int
   int minimapRectY;
   // $VF: renamed from: S int
   int ghostPx;
   // $VF: renamed from: T int
   int ghostPy;
   // $VF: renamed from: a int[]
   int[] screenWidths;
   // $VF: renamed from: b int[]
   int[] screenHeights;
   // $VF: renamed from: c int[]
   int[] screenContentHeights;
   // $VF: renamed from: d int[]
   int[] screenPanelY;
   // $VF: renamed from: c byte[]
   byte[] screenBufferIndex;
   // $VF: renamed from: d byte[]
   byte[] hudPanelForScreen;
   // $VF: renamed from: a javax.microedition.lcdui.Image[]
   static final Image[] screenBuffers = new Image[4];
   // $VF: renamed from: a javax.microedition.lcdui.Graphics[]
   static final Graphics[] screenBufferGraphics = new Graphics[4];
   // $VF: renamed from: M byte
   static byte menuBufferIdx = 0;
   // $VF: renamed from: N byte
   byte drawBufferIndex;
   // $VF: renamed from: c short
   short mapWidth;
   // $VF: renamed from: d short
   short mapHeight;
   // $VF: renamed from: e short
   short tableMapWidth;
   // $VF: renamed from: f short
   short tableMapHeight;
   // $VF: renamed from: O byte
   byte screenColsTiles;
   // $VF: renamed from: P byte
   byte screenRowsTiles;
   // $VF: renamed from: b byte[][]
   byte[][] terrainGrid;
   // $VF: renamed from: c byte[][]
   byte[][] occupantGrid;
   // $VF: renamed from: a int[][][]
   int[][][] effectBitfield;
   // $VF: renamed from: a boolean[][]
   boolean[][] tileDirty;
   // $VF: renamed from: a short[][]
   short[][] tribeStats;
   // $VF: renamed from: e byte[]
   byte[] entityData;
   // $VF: renamed from: d byte[][]
   byte[][] effects;
   // $VF: renamed from: x boolean
   boolean spriteFlipped;
   // $VF: renamed from: e int[]
   int[] hpBarColors;
   // $VF: renamed from: f byte[]
   byte[] maxLevelPerType;
   // $VF: renamed from: g byte[]
   byte[] partEntityIds;
   // $VF: renamed from: h byte[]
   byte[] partDrawnFlagsH;
   // $VF: renamed from: i byte[]
   byte[] partDrawnFlagsI;
   // $VF: renamed from: e byte[][]
   byte[][] buildAnims;
   // $VF: renamed from: a byte[][][]
   byte[][][] buildingStores;
   // $VF: renamed from: f byte[][]
   byte[][] trapTable;
   // $VF: renamed from: b byte[][][]
   byte[][][] pendingOrders;
   // $VF: renamed from: c byte[][][]
   byte[][][] aiTargets;
   // $VF: renamed from: U int
   int weaponSlotIndex;
   // $VF: renamed from: V int
   int weaponValue;
   // $VF: renamed from: g byte[][]
   byte[][] tileEntityDraw;
   // $VF: renamed from: h byte[][]
   byte[][] spriteYOffsetTable;
   // $VF: renamed from: i byte[][]
   byte[][] spriteFrameTable;
   // $VF: renamed from: a javax.microedition.lcdui.Image[][]
   Image[][] spritePacks;
   // $VF: renamed from: b short[][]
   short[][] pd0ShortTables;
   // $VF: renamed from: j byte[][]
   byte[][] pd0ByteTables;
   // $VF: renamed from: W int
   int minimapOriginX;
   // $VF: renamed from: X int
   int minimapOriginY;
   // $VF: renamed from: b int[][]
   int[][] paletteColors;
   // $VF: renamed from: Y int
   int placeMode;
   // $VF: renamed from: Q byte
   byte screenId;
   // $VF: renamed from: R byte
   byte prevScreen;
   // $VF: renamed from: S byte
   byte prevPrevScreen;
   // $VF: renamed from: Z int
   int menuCursor;
   // $VF: renamed from: aa int
   int savedMenuCursor;
   // $VF: renamed from: ab int
   int menuItemCount;
   // $VF: renamed from: T byte
   byte loadAction;
   // $VF: renamed from: U byte
   byte homeScreenId;
   // $VF: renamed from: V byte
   byte campaignScreenId;
   // $VF: renamed from: y boolean
   boolean loadingDone;
   // $VF: renamed from: W byte
   byte nearestEntityId;
   // $VF: renamed from: ac int
   int nearestDist;
   // $VF: renamed from: z boolean
   boolean repaintPending;
   // $VF: renamed from: ad int
   int scrollXPx;
   // $VF: renamed from: ae int
   int scrollYPx;
   // $VF: renamed from: A boolean
   boolean screenContentChanged;
   // $VF: renamed from: B boolean
   boolean terrainDirty;
   // $VF: renamed from: C boolean
   boolean hudChanged;
   // $VF: renamed from: af int
   int scrollDirX;
   // $VF: renamed from: ag int
   int scrollDirY;
   // $VF: renamed from: ah int
   int renderCol;
   // $VF: renamed from: ai int
   int renderRow;
   // $VF: renamed from: aj int
   int redrawColStart;
   // $VF: renamed from: ak int
   int redrawRowStart;
   // $VF: renamed from: al int
   int redrawColEnd;
   // $VF: renamed from: am int
   int redrawRowEnd;
   // $VF: renamed from: D boolean
   boolean rowsNeedRedraw;
   // $VF: renamed from: X byte
   byte currentSpriteEntityId;
   // $VF: renamed from: an int
   int spriteVariant;
   // $VF: renamed from: ao int
   int overlayHeadPart;
   // $VF: renamed from: ap int
   int overlayBodyPart;
   // $VF: renamed from: aq int
   int drawEntityId;
   // $VF: renamed from: ar int
   int drawX;
   // $VF: renamed from: as int
   int drawY;
   // $VF: renamed from: at int
   int drawFrame;
   // $VF: renamed from: Y byte
   byte spriteKind;
   // $VF: renamed from: au int
   int spriteDefIndex;
   // $VF: renamed from: av int
   int spriteFrameId;
   // $VF: renamed from: aw int
   int spritePackIndex;
   // $VF: renamed from: ax int
   int spritePartVariant;
   // $VF: renamed from: ay int
   int spritePartType;
   // $VF: renamed from: az int
   int spritePartIndex;
   // $VF: renamed from: aA int
   int spritePartBase;
   // $VF: renamed from: aB int
   int partLoopIndex;
   // $VF: renamed from: Z byte
   byte cursorVisibility;
   // $VF: renamed from: aa byte
   byte orderCursorType;
   // $VF: renamed from: ab byte
   byte orderTileX;
   // $VF: renamed from: ac byte
   byte orderTileY;
   // $VF: renamed from: aC int
   int highlightCount;
   // $VF: renamed from: E boolean
   boolean cursorMoved;
   // $VF: renamed from: ad byte
   byte activeHudPanel;
   // $VF: renamed from: a boolean[]
   boolean[] hudPanelVisible;
   // $VF: renamed from: b boolean[]
   boolean[] hudPanelDirty;
   // $VF: renamed from: c boolean[]
   boolean[] hudPanelVisible9;
   // $VF: renamed from: d boolean[]
   boolean[] popupButtonFlags;
   // $VF: renamed from: F boolean
   boolean chiefMode;
   // $VF: renamed from: j byte[]
   byte[] menuItemAvailable;
   // $VF: renamed from: k byte[]
   byte[] hudElemSeen;
   // $VF: renamed from: d short[]
   short[] hudValues;
   // $VF: renamed from: aD int
   int hudPanelId;
   // $VF: renamed from: aE int
   int hudElemIndex;
   // $VF: renamed from: aF int
   int hudElemValue;
   // $VF: renamed from: aG int
   int hudLastDrawX;
   // $VF: renamed from: aH int
   int hudLastDrawY;
   // $VF: renamed from: aI int
   int hudSpriteId;
   // $VF: renamed from: aJ int
   int hudDrawX;
   // $VF: renamed from: aK int
   int hudDrawY;
   // $VF: renamed from: ae byte
   byte hudButtonState;
   // $VF: renamed from: G boolean
   boolean hudContentChanged;
   // $VF: renamed from: af byte
   static byte loadProgress;
   // $VF: renamed from: f int[]
   int[] loadBarColors;
   // $VF: renamed from: l byte[]
   byte[] loadBarXOffsets;
   // $VF: renamed from: m byte[]
   byte[] loadBarYOffsets;
   // $VF: renamed from: n byte[]
   byte[] loadBarWidths;
   // $VF: renamed from: o byte[]
   byte[] loadBarHeights;
   // $VF: renamed from: ag byte
   static byte minimapViewW = 40;
   // $VF: renamed from: ah byte
   static byte minimapViewH = 60;
   // $VF: renamed from: aL int
   int minimapRowOffset;
   // $VF: renamed from: aM int
   int minimapTilesW;
   // $VF: renamed from: aN int
   int minimapTilesH;
   // $VF: renamed from: aO int
   int minimapPixelColor;
   // $VF: renamed from: H boolean
   boolean menuNeedsDraw;
   // $VF: renamed from: I boolean
   boolean menuBackPressed;
   // $VF: renamed from: aP int
   int storyImageId;
   // $VF: renamed from: aQ int
   int shakeOffsetY;
   // $VF: renamed from: ai byte
   byte storySceneId;
   // $VF: renamed from: aR int
   int storyStepDir;
   // $VF: renamed from: aj byte
   byte storyStepCount;
   // $VF: renamed from: ak byte
   byte storyStepIndex;
   // $VF: renamed from: al byte
   byte storyStepSlot;
   // $VF: renamed from: am byte
   byte subjectEntityId;
   // $VF: renamed from: an byte
   byte stepAction;
   // $VF: renamed from: aS int
   int storyStepType;
   // $VF: renamed from: p byte[]
   byte[] storyActors;
   // $VF: renamed from: aT int
   int storyTableBase;
   // $VF: renamed from: ao byte
   byte storySceneType;
   // $VF: renamed from: J boolean
   boolean storyIsCutscene;
   // $VF: renamed from: K boolean
   boolean menuItemActivated;
   // $VF: renamed from: ap byte
   byte popupKind;
   // $VF: renamed from: L boolean
   boolean placeConfirmFlagL;
   // $VF: renamed from: M boolean
   boolean placingActive;
   // $VF: renamed from: N boolean
   boolean placeConfirmFlagN;
   // $VF: renamed from: O boolean
   boolean placeModeChanged;
   // $VF: renamed from: aq byte
   byte speedSetting;
   // $VF: renamed from: q byte[]
   byte[] homePosition;
   // $VF: renamed from: P boolean
   boolean panelRefreshNeeded;
   // $VF: renamed from: Q boolean
   boolean menuScrollUp;
   // $VF: renamed from: R boolean
   boolean menuScrollDown;
   // $VF: renamed from: ar byte
   byte menuItemTotal;
   // $VF: renamed from: as byte
   byte menuSelectedIndex;
   // $VF: renamed from: aU int
   static int menuMaxVisibleRows;
   // $VF: renamed from: aV int
   static int menuRowHeightPx;
   // $VF: renamed from: aW int
   int menuVisibleRows;
   // $VF: renamed from: aX int
   int menuScrollTop;
   // $VF: renamed from: aY int
   int menuWidthPx;
   // $VF: renamed from: aZ int
   int menuHeightPx;
   // $VF: renamed from: at byte
   byte adjustMax;
   // $VF: renamed from: au byte
   byte storeRowIndex;
   // $VF: renamed from: av byte
   byte firstWeaponIndex;
   // $VF: renamed from: S boolean
   boolean screenDrawn;
   // $VF: renamed from: r byte[]
   byte[] menuItemIds;
   // $VF: renamed from: s byte[]
   byte[] menuItemValues;
   // $VF: renamed from: T boolean
   boolean menuListChanged;
   // $VF: renamed from: U boolean
   boolean menuSelectionChanged;
   // $VF: renamed from: V boolean
   boolean selectionChanged;
   // $VF: renamed from: aw byte
   byte spawnTribe;
   // $VF: renamed from: t byte[]
   byte[] effectCounters;
   // $VF: renamed from: ba int
   int bufferCopyX;
   // $VF: renamed from: bb int
   int bufferCopyY;
   // $VF: renamed from: ax byte
   byte visiblePanelCount;
   // $VF: renamed from: u byte[]
   byte[] messageFlags;
   // $VF: renamed from: k byte[][]
   byte[][] focusTable;
   // $VF: renamed from: g int[]
   int[] messageDurations;
   // $VF: renamed from: ay byte
   byte messageType;
   // $VF: renamed from: h int[]
   int[] mapChunkOffsets;
   // $VF: renamed from: v byte[]
   byte[] mapData;
   // $VF: renamed from: bc int
   int storyFrameX;
   // $VF: renamed from: bd int
   int storyFrameY;
   // $VF: renamed from: be int
   int storyFrameW;
   // $VF: renamed from: bf int
   int storyFrameH;
   // $VF: renamed from: b java.lang.String
   String recordStorePrefix;
   // $VF: renamed from: W boolean
   boolean customGameAvailable;
   // $VF: renamed from: X boolean
   boolean menuActionPending;
   // $VF: renamed from: a java.util.Random
   Random random;
   // $VF: renamed from: Y boolean
   boolean paused;
   // $VF: renamed from: Z boolean
   boolean hideRequested;
   // $VF: renamed from: aa boolean
   boolean gameLoaded;
   // $VF: renamed from: ab boolean
   boolean pauseScreenFirstRun;
   // $VF: renamed from: ac boolean
   boolean gameActive;
   // $VF: renamed from: ad boolean
   boolean pauseMusicStopped;
   // $VF: renamed from: e short[]
   static short[] unusedShortArrayE;
   // $VF: renamed from: f short[]
   static short[] unusedShortArrayF;
   // $VF: renamed from: w byte[]
   static byte[] midiData;
   // $VF: renamed from: i int[]
   int[] cheatKeyHistory;
   // $VF: renamed from: j int[]
   int[] cheatCode;
   // $VF: renamed from: ae boolean
   boolean cheatSeqMatched;
   // $VF: renamed from: af boolean
   boolean cheatUnlockedCustom;
   // $VF: renamed from: ag boolean
   boolean cheatFlagAg;

   public Game(TribesMIDlet var1) {
      String[] var10000 = new String[]{"en", "fr", "it", "de", "es"};
      this.dialogId = -1;
      this.tickerLastDialogId = -1;
      this.continueProgress = -1;
      this.savedMissionProgress = -1;
      this.mode = 2;
      this.fireHeld = false;
      this.dragSelecting = false;
      this.fireTapped = false;
      this.keyHeld = false;
      this.drawBufferIndex = 1;
      this.mapWidth = 96;
      this.mapHeight = 96;
      this.tableMapWidth = 96;
      this.tableMapHeight = 96;
      this.partEntityIds = new byte[11];
      this.partDrawnFlagsH = new byte[11];
      this.partDrawnFlagsI = new byte[11];
      this.weaponSlotIndex = 5;
      this.campaignScreenId = 4;
      this.nearestDist = 1000;
      this.spriteVariant = -1;
      this.hudPanelVisible = new boolean[20];
      this.hudPanelDirty = new boolean[20];
      this.hudPanelVisible9 = new boolean[9];
      this.popupButtonFlags = new boolean[9];
      this.menuItemAvailable = new byte[8];
      this.loadBarColors = new int[]{0, 5251341, 16704820, 16033043, 12010496};
      this.loadBarXOffsets = new byte[]{0, 1, 1, 1, 1};
      this.loadBarYOffsets = new byte[]{0, 1, 1, 2, 3};
      this.loadBarWidths = new byte[]{100, 98, 0, 0, 0};
      this.loadBarHeights = new byte[]{5, 3, 1, 1, 1};
      this.storyActors = new byte[40];
      this.speedSetting = 2;
      this.homePosition = new byte[2];
      this.menuSelectedIndex = 3;
      this.spawnTribe = 2;
      this.messageFlags = new byte[10];
      this.focusTable = new byte[3][10];
      this.messageDurations = new int[10];
      this.recordStorePrefix = "t";
      this.random = new Random(1L);
      this.paused = false;
      this.hideRequested = false;
      this.gameLoaded = false;
      this.setFullScreenMode(true);
      this.midlet = var1;
   }

   // $VF: renamed from: a () void
   public final void startGameThread() {
      new Thread(this).start();
   }

   // $VF: renamed from: b () void
   public final void quitGame() {
      this.running = false;
      this.repaintPending = false;
      this.stopMusic();
      this.midlet.notifyDestroyed();
   }

   // $VF: renamed from: f () void
   private void initGame() {
      this.world = new World();
      System.gc();
      Thread.yield();
      new ResourceLoader(this);
      loadProgress = 98;
      this.drawLoadProgress(0);
      this.midlet.display.setCurrent(this);
      this.loadingDone = true;
   }

   // $VF: renamed from: g () void
   private void allocateBuffers() {
      System.gc();
      Thread.yield();
      this.tileEntityDraw = new byte[this.screenRowsTiles + 3][this.screenColsTiles];
      this.spriteYOffsetTable = new byte[13][101];
      this.spriteFrameTable = new byte[13][101];
      this.effectBitfield = new int[2][3][96];
      this.tileDirty = new boolean[this.screenRowsTiles + 3][this.screenColsTiles];
      System.gc();
      Thread.yield();
      this.villagerGroups = new byte[5][50];
      this.tribeStats = new short[2][26];
      this.effects = new byte[7][40];
      this.hpBarColors = new int[]{14680064, 14999808, 55808, 55808};
      this.maxLevelPerType = new byte[12];
      this.buildAnims = new byte[3][30];
      this.buildingStores = new byte[2][10][12];
      this.trapTable = new byte[4][30];
      this.pendingOrders = new byte[2][5][30];
      this.aiTargets = new byte[2][5][5];
      System.gc();
      Thread.yield();
      this.paletteColors = new int[][]{
         {7250486, 11637795, 2652080, 2454300, 3881776, 4605510, 16766976, 14352640, 10966528, 16750080, 7406848, -1},
         {5276696, 8810262, 678290, 480512, 1907986, 2631720, 16766976, -1, -1, 16750080, 5439488, -1}
      };
      this.hudElemSeen = new byte[107];
      this.hudValues = new short[107];
      this.menuItemIds = new byte[50];
      this.menuItemValues = new byte[100];
      System.gc();
      Thread.yield();
      this.world.villagerPaths = new byte[2][101][80];
      this.world.animalPaths = new byte[2][22][5];
      this.world.diseaseSpots = new byte[5][6][6];
      this.world.hashNext = new byte[5][50];
      this.world.directionSteps = new byte[2];
      this.world.pathCursors = new byte[6];
      this.world.pathCosts = new byte[3];
      this.world.pathLengths = new byte[3];
      this.world.candidatePaths = new byte[3][2][96];
      this.world.pathOrder = new byte[3];
      this.world.pathSegments = new byte[2][3][10];
      this.world.savedTerrain = new byte[10];
      this.world.matingPairs = new byte[6][10];
      this.world.pathBlockedFlags = new boolean[3];
      this.world.pathPoints = new byte[2][50];
      this.world.messageCooldowns = new short[6];
      this.world.buildingSlotMapI = new byte[8];
      this.world.needyListE = new byte[4][8];
      this.world.trapRegistryF = new byte[2][10];
      this.world.upgradeRegistryG = new byte[3][20];
      this.world.aiRank = new byte[50];
      this.world.tileQueryResult = new byte[4];
      this.cheatKeyHistory = new int[6];
      this.cheatCode = new int[]{56, 55, 51, 57, 49};
      this.effectCounters = new byte[3];
   }

   public final void run() {
      try {
         this.initGame();
         this.allocateBuffers();
         this.world.attachGame(this);
         if (!this.loadSlot(0)) {
            this.loadSlot(1);
            this.loadSlot(2);
         }

         this.screenId = 14;
         this.mode = 0;
         this.menuNeedsDraw = true;
         this.prevScreen = this.homeScreenId;
         this.dialogId = 59;
         this.tickCounter = -1;
         this.hudElemSeen[55] = -1;
         this.titleParticles[0][3] = -1000;
         this.gameLoaded = true;
         this.running = true;
         this.resetInputFlags();

         while (true) {
            if (!this.running) {
               return;
            }

            long var1 = nowMillis();
            this.tickPauseMusicCheck();
            this.tickUpdate();
            if (!this.running) {
               break;
            }

            this.renderToBuffer();
            this.requestRepaint();

            while (this.repaintPending && !this.hideRequested) {
               Thread.yield();
            }

            if (this.scrollXPx != 0 || this.scrollYPx != 0) {
               this.scrollXPx = this.scrollYPx = 0;
               TribesMIDlet.sleep(this.tickLengthMs / 2);
               this.requestRepaint();

               while (this.repaintPending && !this.hideRequested) {
                  Thread.yield();
               }
            }

            long var5 = nowMillis() - var1;
            if ((var5 = this.tickLengthMs - var5) < 0L || var5 > this.tickLengthMs) {
               var5 = 0L;
            }

            TribesMIDlet.sleep((int)var5);
         }
      } catch (Exception var8) {
      }
   }

   // $VF: renamed from: h () void
   private void tickUpdate() {
      this.tickLengthMs = this.fastTick ? 50 : this.tickMsAlt;
      this.animSpeedFactor = this.tickMsAlt / this.tickLengthMs;
      this.animFrame++;
      if (this.animFrame >= 6) {
         this.cursorMoved = false;
         this.moveOrderFlash = false;
      }

      if (this.animFrame >= 8) {
         this.animFrame = 0;
      }

      Thread.yield();
      if (this.mode == 0) {
         this.dispatchMenuInput();
         this.keyDownFlag = false;
      } else if (this.mode == 1) {
         if (this.numericKey == 0) {
            this.numericKey = -1;
            if (this.interactMode != 3 && !this.hudPanelVisible[6] && !this.hudPanelVisible[8] && !this.hudPanelVisible[7]) {
               if (this.hudPanelVisible[0]) {
                  this.endScreenPhase = 0;
               } else if (this.hudPanelVisible[4]) {
                  this.endScreenPhase = 1;
                  if (this.fileAData[this.recordStarts[296] + this.world.hintStepIndex] == 3) {
                     this.world.needSlotJ = (byte)(120 - this.fileAData[this.recordStarts[298] + this.world.hintStepIndex]);
                  }
               }

               this.mode = 0;
               this.setPlaceMode(this.fileAData[this.recordStarts[305] + this.fileAData[this.recordStarts[306] + this.endScreenPhase] + 0]);
               this.screenId = 25;
               this.prevScreen = 28;
               this.menuCursor = 0;
               this.menuNeedsDraw = true;
               this.savedCursorCol = this.cursorCol;
               this.savedCursorRow = this.cursorRow;
               this.savedCamCol = this.camTileX;
               this.savedCamRow = this.camTileY;
            }
         } else if (this.interactMode != 2 && this.interactMode != 4) {
            this.dispatchInGameInput();
            this.keyDownFlag = false;
            this.findNearestEntity();
         } else {
            this.handleMenuInput();
            this.keyDownFlag = false;
         }

         if (this.mode == 1) {
            this.msgAdvanceCounter++;
            if (this.msgAdvanceCounter > 5) {
               this.msgAdvanceCounter = 0;
               this.postMessage((byte)100, (byte)0, (byte)0, (byte)0);
            }

            if (this.fireHoldTicks <= 4) {
               this.fireHoldTicks++;
            } else if (this.fireHoldTicks >= 0) {
               this.fireTapCount = 0;
            }

            this.ticksSinceKeypress++;
            this.tickWorldUpdate();
         }
      }

      this.uptimeTicks++;
      this.animCycle++;
      if (this.animCycle >= 120) {
         this.animCycle = 0;
      }

      if (this.startupPhase < 3) {
         this.startupPhase++;
         this.inputLocked = true;
      } else if (this.startupPhase == 3) {
         this.startupPhase++;
         this.resetInputFlags();
         this.inputLocked = false;
      }

      if (this.worldReady) {
         this.worldReady = false;
         this.placementType = 0;
         this.setPlaceMode(-1);
         this.panelRefreshNeeded = false;
         this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         this.chiefMode = false;
         this.interactMode = 0;
         this.selectedVillagerId = 0;
         this.menuTargetId = 0;
         this.selRectX = this.selRectY = this.selRectW = this.selRectH = 0;
         this.fireHeldCount = -1;
         this.clearGroup4();
         this.dragSelecting = false;
         this.fireHeld = false;
         this.resetInputFlags();
      }

      if (this.keyReleasedFlag) {
         this.keyReleasedFlag = false;
         this.resetInputFlags();
      }

      this.tickCount++;
   }

   // $VF: renamed from: i () void
   private void resetInputFlags() {
      rightSoftkeyPressed = false;
      leftSoftkeyPressed = false;
      this.keyUp = this.keyDown = this.keyLeft = this.keyRight = false;
      this.firePressed = this.fireTapped = false;
      this.fireHoldTicks = 4;
      this.keyHeld = false;
      this.numericKey = -1;
      this.fireHeld = false;
      this.fireTapCount = 0;
   }

   // $VF: renamed from: j () void
   private void tickWorldUpdate() {
      try {
         this.world.tickBuildings();
         this.world.tickWorld();
         this.world.tickAnimals();
         this.world.tickDirector();
      } catch (Exception var3) {
      }

      this.tickCounter++;
      this.trapScanRow++;
      if (this.trapScanRow >= 100) {
         int var1;
         if ((var1 = this.trapScanRow - 100) >= this.mapHeight - 1) {
            this.trapScanRow = 0;
         }

         for (int var2 = 0; var2 < this.mapWidth; var2++) {
            if (this.occupantGrid[var1][var2] <= -114 && this.occupantGrid[var1][var2] > -117) {
               this.occupantGrid[var1][var2] = 0;
               this.markTileRect(var2, var1, 1, 1);
            }
         }
      }

      if (this.panelRefreshNeeded && this.interactMode != 4 && this.interactMode != 3) {
         this.buildContextMenu();
      }

      this.panelRefreshNeeded = false;
      if (this.endgameTimer > 0 && this.mode == 1) {
         this.endgameTimer++;
         if (this.endgameTimer == 41 || this.endgameTimer == 90) {
            this.mode = 0;
            this.screenId = 24;
            this.menuNeedsDraw = true;
            this.dialogId = 56 + 41 / this.endgameTimer;
            this.storySpeaker = (byte)(11 - 41 / this.endgameTimer);
            this.startupPhase = 0;
         }
      }
   }

   // $VF: renamed from: k () void
   private void findNearestEntity() {
      int var1 = this.camTileX + this.cursorCol;
      int var2 = this.camTileY + this.cursorRow;

      try {
         byte var3 = this.occupantGrid[var2][var1];
         if (this.dragSelecting || var3 <= -1 && var3 >= -30) {
            this.nearestEntityId = 0;
            return;
         }

         boolean var4 = false;
         if ((var3 < 51 || this.interactMode != 1) && (var3 < 1 || var3 > 50)) {
            for (int var9 = -1; var9 <= 1; var9++) {
               for (int var10 = -1; var10 <= 1; var10++) {
                  if (var1 + var10 >= 0
                     && var2 + var9 >= 0
                     && var1 + var10 < this.mapWidth
                     && var2 + var9 < this.mapHeight
                     && ((var3 = this.occupantGrid[var2 + var9][var1 + var10]) >= 51 && this.interactMode == 1 || var3 >= 1 && var3 <= 50)) {
                     int var7 = this.entityData[202 + var3];
                     int var8 = this.entityData[303 + var3];
                     var8 = var9 < 0 ? 16 - var8 : (var9 == 0 ? 0 : 16 + var8);
                     byte var13;
                     var7 = (var13 = (byte)(var10 < 0 ? 22 - var7 : (var10 == 0 ? 0 : 22 + var7))) * var13;
                     int var16;
                     int var6 = ((var16 = var8 * var8) + var7) / 2;
                     if (this.nearestEntityId == var3 && (var6 < 13 || var3 <= 50)) {
                        this.nearestDist = var6;
                        var4 = true;
                     }

                     if (var6 < this.nearestDist && (var6 < 13 || var3 <= 50)) {
                        this.nearestDist = var6;
                        this.nearestEntityId = var3;
                        var4 = true;
                     }
                  }
               }
            }

            if (!var4) {
               this.nearestEntityId = 0;
               this.nearestDist = 1000;
            }
         } else {
            this.nearestEntityId = var3;
         }

         if (this.nearestEntityId < 50 && this.interactMode == 1) {
            this.nearestEntityId = 0;
         }
      } catch (Exception var11) {
      }
   }

   // $VF: renamed from: l () void
   private void requestRepaint() {
      this.repaintPending = true;
      this.repaint();
      Thread.yield();
   }

   protected final void paint(Graphics var1) {
      if (this.mode == 1) {
         byte var2;
         if (this.scrollXPx == 0 && this.scrollYPx == 0) {
            var2 = this.screenBufferIndex[1];
         } else {
            var2 = 3;
         }

         drawRegionSafe(
            var1,
            screenBuffers[var2],
            0,
            0,
            262,
            this.viewHeightPx,
            this.screenBaseX - 11 + this.scrollXPx,
            this.screenBaseY - 8 + this.bufferCopyY - this.visiblePanelCount + this.scrollYPx + 0,
            0
         );

         for (byte var3 = 0; var3 < 20; var3++) {
            if (this.hudPanelVisible[var3]) {
               drawRegionSafe(
                  var1,
                  screenBuffers[this.screenBufferIndex[this.fileAData[this.recordStarts[195] + 0 + var3]]],
                  0,
                  this.screenContentHeights[this.fileAData[this.recordStarts[195] + 0 + var3]],
                  this.screenWidths[this.fileAData[this.recordStarts[195] + 0 + var3]] + 11,
                  this.screenHeights[this.fileAData[this.recordStarts[195] + 0 + var3]],
                  this.screenBaseX - 11,
                  this.screenBaseY + this.screenPanelY[this.fileAData[this.recordStarts[195] + 0 + var3]] + 0,
                  0
               );
               if (var3 == 8 || var3 == 7) {
                  this.a_Graphics_int_int_int_void(
                     var1,
                     556,
                     this.screenBaseX + this.screenWidths[this.fileAData[this.recordStarts[195] + 0 + var3]] - 68,
                     this.screenBaseY
                        + this.screenHeights[this.fileAData[this.recordStarts[195] + 0 + var3]]
                        - 55
                        + this.bufferCopyY
                        + this.screenPanelY[this.fileAData[this.recordStarts[195] + 0 + var3]]
                        + 0
                        + 5
                  );
               }

               if (this.fileAData[this.recordStarts[195] + 20 + var3] == 1) {
                  this.a_Graphics_int_int_int_void(var1, 524, this.screenBaseX, this.screenBaseY + this.bufferCopyY + 0);
               } else if (this.fileAData[this.recordStarts[195] + 20 + var3] == 2) {
                  this.a_Graphics_int_int_int_void(var1, 528, this.screenBaseX, this.screenBaseY + 320 - this.bufferCopyX + 0);
               }
            }
         }
      } else {
         drawRegionSafe(var1, screenBuffers[menuBufferIdx], 0, 0, 251, 328, this.screenBaseX - 11, this.screenBaseY - 8 + 0, 0);
         if (this.storyMode && !this.storyIsCutscene && this.storyImageId < 0) {
            var1.setColor(0);
            fillRect(var1, this.screenBaseX - 11, this.screenBaseY - 8 + 0, 240, 16 + -this.storyImageId);
            fillRect(var1, this.screenBaseX - 11, this.screenBaseY - 8 + 16 + 320 - -this.storyImageId + 0, 240, -this.storyImageId);
         }
      }

      this.repaintPending = false;
   }

   // $VF: renamed from: m () void
   private void renderToBuffer() {
      if (this.mode == 1) {
         this.drawBufferIndex = this.screenBufferIndex[1];
         this.scrollRedrawTerrain();
         this.drawMinimapViewport();
         this.renderWorldEntities();
         screenBufferGraphics[3].drawImage(screenBuffers[this.screenBufferIndex[1]], 0, 0, 0);
         this.drawBufferIndex = 3;
         this.drawCursor(this.cursorCol * 22 - this.scrollXPx, this.cursorRow * 16 - this.scrollYPx);
         this.drawBufferIndex = this.screenBufferIndex[1];
         this.drawCursor(this.cursorCol * 22, this.cursorRow * 16);
         this.screenDrawn = false;

         for (byte var1 = 0; var1 < 20; var1++) {
            if (this.hudPanelVisible[var1]) {
               this.layoutHudElement(var1, 0, this.screenContentHeights[this.fileAData[this.recordStarts[195] + 0 + var1]] - 8);
               this.drawBufferIndex = this.screenBufferIndex[1];
               if (this.fileAData[this.recordStarts[195] + 20 + var1] == 1) {
                  this.a_int_int_int_void(524, 0, this.visiblePanelCount);
               } else if (this.fileAData[this.recordStarts[195] + 20 + var1] == 2) {
                  this.a_int_int_int_void(528, 0, 320 - this.bufferCopyY - this.bufferCopyX + this.visiblePanelCount);
               }
            }
         }
      } else {
         if (this.mode == 0) {
            this.dispatchMenuScreen();
         }
      }
   }

   // $VF: renamed from: n () void
   private void scrollRedrawTerrain() {
      try {
         if (this.scrollDirY != 0 && this.scrollDirX != 0) {
            this.screenContentChanged = true;
         }

         this.redrawColStart = 0;
         this.redrawRowStart = 0;
         if (this.screenContentChanged) {
            this.screenContentChanged = false;
            this.hudChanged = false;
            this.redrawColEnd = this.screenColsTiles - 1;
            this.redrawRowEnd = this.screenRowsTiles - 1;
            this.scrollDirX = this.scrollDirY = 0;
            this.redrawTerrainRows();
         } else {
            this.renderRow = 0;
            this.redrawRowEnd = -1;
            if (this.scrollDirX != 0) {
               this.redrawColStart = -this.scrollDirX * 22;
            } else if (this.scrollDirY != 0) {
               this.redrawRowStart = -this.scrollDirY * 16;
            }

            drawRegionSafe(
               screenBufferGraphics[this.screenBufferIndex[1]],
               screenBuffers[this.screenBufferIndex[0]],
               0,
               0,
               264,
               this.viewHeightPx,
               this.redrawColStart,
               this.redrawRowStart,
               0
            );
            if (this.scrollDirX != 0) {
               this.redrawColStart = ((this.scrollDirX - 2) / 2 + 1) * (this.screenColsTiles - 1);
               this.redrawColEnd = this.redrawColStart;
               this.redrawRowStart = 0;
               this.redrawRowEnd = this.screenRowsTiles - 1;
               this.scrollDirX = 0;
               this.redrawTerrainRows();
            }

            if (this.scrollDirY != 0) {
               this.redrawRowStart = ((this.scrollDirY - 2) / 2 + 1) * (this.screenRowsTiles - 1);
               this.redrawRowEnd = this.redrawRowStart;
               this.redrawColStart = 0;
               this.redrawColEnd = this.screenColsTiles - 1;
               this.scrollDirY = 0;
               this.redrawTerrainRows();
            }

            if (this.hudChanged) {
               this.hudChanged = false;
               this.redrawColStart = 0;
               this.redrawColEnd = this.screenColsTiles - 1;
               this.redrawRowStart = this.screenRowsTiles - 1 - 3;
               this.redrawRowEnd = this.screenRowsTiles - 1;
               this.redrawTerrainRows();
            }

            if (this.terrainDirty) {
               this.terrainDirty = false;
               this.redrawColStart = 0;
               this.redrawColEnd = this.screenColsTiles - 1;
               this.redrawRowStart = 0;
               this.redrawRowEnd = this.screenRowsTiles - 1;
               this.rowsNeedRedraw = true;
               this.redrawTerrainRows();
               this.rowsNeedRedraw = false;
            }
         }

         if (this.redrawRowEnd != -1 && !this.storyMode) {
            drawRegionSafe(screenBufferGraphics[this.screenBufferIndex[0]], screenBuffers[this.screenBufferIndex[1]], 0, 0, 264, this.viewHeightPx, 0, 0, 0);
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: o () void
   private void redrawTerrainRows() {
      for (this.renderRow = this.redrawRowStart; this.renderRow <= this.redrawRowEnd; this.renderRow++) {
         for (this.renderCol = this.redrawColStart; this.renderCol <= this.redrawColEnd; this.renderCol++) {
            if (this.rowsNeedRedraw) {
               if (!this.tileDirty[this.renderRow][this.renderCol]) {
                  continue;
               }

               this.tileDirty[this.renderRow][this.renderCol] = false;
            }

            if (!this.storyMode || this.isTileInBounds(this.renderCol + this.camTileX, this.renderRow + this.camTileY)) {
               int var1;
               if ((var1 = this.terrainGrid[this.renderRow + this.camTileY][this.renderCol + this.camTileX] & 255) < 175) {
                  this.a_int_int_int_void(var1, this.renderCol * 22, this.renderRow * 16);
               } else {
                  this.drawTile(var1, (byte)0);
               }

               byte var2;
               if ((var2 = this.occupantGrid[this.renderRow + this.camTileY][this.renderCol + this.camTileX]) < 0 && var2 >= -123) {
                  this.drawTile(var2, (byte)0);
               }
            }
         }
      }
   }

   // $VF: renamed from: a (int, byte) void
   private void drawTile(int var1, byte var2) {
      int var3 = -var1;
      int var4 = 0;
      byte var5 = 0;
      byte var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = this.renderCol * 22;
      int var11 = this.renderRow * 16;
      if (tribeOfBuilding(var1) != 2) {
         var4 = this.entityData[8749 + var3];
         var5 = this.fileAData[this.recordStarts[138] + 104 + var4];
         var7 = this.entityData[9115 + var3];
         var8 = this.renderCol + this.camTileX - this.entityData[8383 + var3];
         var9 = this.renderRow + this.camTileY - this.entityData[8444 + var3];
         var10 -= var8 * 22;
         var11 -= var9 * 16;
         this.spriteTableIndex = this.pd0ShortTables[0][var4 + 1];
         this.spriteDataValue = this.pd0ByteTables[0][var4 + 1];
         if (var2 == 0) {
            int var13 = 0;
            int var12 = var5 * var9 + var8;

            while (true) {
               if ((this.pd0ByteTables[3][this.spriteTableIndex] & 255) >> 6 == 0) {
                  if (var13 == var12) {
                     break;
                  }

                  var13++;
               }

               if ((this.pd0ByteTables[3][this.spriteTableIndex] & 7) != 0) {
                  break;
               }

               this.spriteTableIndex++;
               this.spriteDataValue--;
            }
         } else {
            for (byte var15 = this.entityData[8993 + var3]; var15 > 0; var15 = this.entityData[4545 + var15]) {
               this.partEntityIds[this.entityData[4646 + var15]] = (byte)var15;
            }
         }
      } else if (isBuildingFootprintCode(var1)) {
         var3 -= 61;
         var4 = this.entityData[9833 + var3];
         var7 = this.entityData[9933 + var3];
         this.spriteTableIndex = this.pd0ShortTables[0][var4 + 27 - 1];
         this.spriteDataValue = this.pd0ByteTables[0][var4 + 27 - 1];
      } else if (tribeOfOccupantCode(var1) != 2) {
         if (tribeOfOccupantCode(var1) != 3) {
            this.partEntityIds[0] = this.occupantGrid[this.camTileY + this.renderRow][this.camTileX + this.renderCol];
            if (tribeOfOccupantCode(this.partEntityIds[0]) != 2) {
               if (tribeOfOccupantCode(this.partEntityIds[0]) == 1) {
                  var2 = -1;
               }

               var4 = 20 - (this.partEntityIds[0] + 111);
               var7 = 1;
            } else if (tribeOfOccupantCode(this.entityData[4545 + this.partEntityIds[0]]) != 2) {
               var4 = 20 - (this.entityData[4545 + this.partEntityIds[0]] + 111);
               var7 = 2;
            }

            while (var4 > 22) {
               var4 -= 3;
            }
         } else {
            var4 = 23;
            var7 = 0;
         }

         this.spriteTableIndex = this.pd0ShortTables[0][var4 + 1];
         this.spriteDataValue = this.pd0ByteTables[0][var4 + 1];
         var3 = 0;
      } else if (var1 >= 1000) {
         var10 = this.ghostPx;
         var11 = this.ghostPy;
         var4 = var1 - 1000;
         this.spriteTableIndex = this.pd0ShortTables[0][var4];
         this.spriteDataValue = this.pd0ByteTables[0][var4];
         if (this.mode == 0) {
            this.partEntityIds[0] = 1;
            this.partEntityIds[1] = 2;
            this.partEntityIds[2] = 3;
            this.entityData[2930] = 39;
            this.entityData[2931] = 42;
            this.entityData[2932] = 42;
            this.entityData[607] = 1;
            this.entityData[608] = 0;
            this.entityData[609] = 6;
            this.entityData[1415] = 25;
            this.entityData[1416] = 45;
            this.entityData[1417] = 70;
            this.entityData[3031] = (byte)(this.animCycle % 8);
            this.entityData[3032] = (byte)(this.animCycle % 8);
            this.entityData[3033] = (byte)(this.animCycle % 10);
            this.entityData[5152] = 8;
            this.entityData[6566] = 9;
            this.entityData[5153] = 5;
            this.entityData[6567] = 2;
            this.entityData[5154] = 10;
            this.entityData[6568] = 0;
            this.entityData[1314] = this.entityData[1315] = this.entityData[1316] = 0;
            this.entityData[6465] = this.entityData[6466] = this.entityData[6467] = 0;
         }

         var7 = 0;
         var3 = 0;
      } else if (var1 >= 175) {
         var4 = var1 - 180 + 34;
         this.spriteTableIndex = this.pd0ShortTables[0][var4];
         this.spriteDataValue = this.pd0ByteTables[0][var4];
         var11 += this.shakeOffsetY;
         var7 = 0;
         var3 = 0;
      }

      if (var2 == 0) {
         do {
            this.spriteFlags = (this.pd0ByteTables[3][this.spriteTableIndex] & 255) >> 6;
            if (this.spriteFlags <= var7) {
               this.a_int_int_int_void(
                  this.pd0ShortTables[1][this.spriteTableIndex] & 4095,
                  (this.renderCol - var8) * 22 + this.pd0ByteTables[1][this.spriteTableIndex],
                  (this.renderRow - var9) * 16 + this.pd0ByteTables[2][this.spriteTableIndex]
               );
            }

            this.spriteTableIndex++;
         } while ((this.pd0ByteTables[3][this.spriteTableIndex] & 7) == 0 && (this.pd0ByteTables[3][this.spriteTableIndex] & 255) >> 6 != 0);
      } else {
         this.drawEntityWithParts(var3, var7, (byte)var4, var10, var11, var2);
      }

      for (byte var16 = 0; var16 < 8; var16++) {
         this.partEntityIds[var16] = this.partDrawnFlagsH[var16] = this.partDrawnFlagsI[var16] = 0;
      }
   }

   // $VF: renamed from: a (int, byte, byte, int, int, byte) void
   private void drawEntityWithParts(int var1, byte var2, byte var3, int var4, int var5, byte var6) {
      int var9 = 0;
      int var10 = this.animCycle;
      byte var11 = 0;
      this.weaponSlotIndex = 0;

      try {
         for (int var15 = 0; var15 < this.spriteDataValue; var15++) {
            int var8 = this.pd0ByteTables[3][this.spriteTableIndex] & 7;
            var11 = (byte)((this.pd0ByteTables[3][this.spriteTableIndex] & 63) >> 3);
            this.spriteFlags = (this.pd0ByteTables[3][this.spriteTableIndex] & 255) >> 6;
            this.currentSpriteEntityId = this.partEntityIds[var11];
            boolean var12 = false;
            int var14 = (this.pd0ShortTables[1][this.spriteTableIndex] & '쀀') >> 14 << 7;
            if (var8 == 0 && var6 == 2 && this.spriteFlags == var2) {
               this.a_int_int_int_void(
                  this.pd0ShortTables[1][this.spriteTableIndex] & 4095,
                  var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                  var5 + this.pd0ByteTables[2][this.spriteTableIndex]
               );
            } else if (var8 == 1 && this.spriteFlags <= var2) {
               if (var3 == 6 && this.weaponSlotIndex < 5) {
                  this.tribeStats[0][this.fileAData[this.recordStarts[142]]] = (byte)(
                     this.tribeStats[0][5] + this.tribeStats[0][8] + this.tribeStats[0][5] + this.tribeStats[0][9] + this.tribeStats[0][10]
                  );
                  if (this.tribeStats[0][this.fileAData[this.recordStarts[142] + this.weaponSlotIndex]] > 0 || var1 >= 31) {
                     this.weaponValue = this.tribeStats[0][this.fileAData[this.recordStarts[142] + this.weaponSlotIndex]]
                        / 7
                        / (this.weaponSlotIndex == 0 ? 1 : 10);
                     if (this.weaponValue >= 5 || var1 >= 31) {
                        this.weaponValue = 4;
                     }

                     this.a_int_int_int_void(
                        (this.pd0ShortTables[1][this.spriteTableIndex] & 4095) + this.weaponValue,
                        var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                        var5 + this.pd0ByteTables[2][this.spriteTableIndex]
                     );
                  }

                  this.weaponSlotIndex++;
               } else if (var3 == 4 && this.weaponSlotIndex < 1) {
                  if (var1 >= 31) {
                     this.a_int_int_int_void(
                        (this.pd0ShortTables[1][this.spriteTableIndex] & 4095) + 2,
                        var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                        var5 + this.pd0ByteTables[2][this.spriteTableIndex]
                     );
                  } else if (this.tribeStats[0][0] >= 5) {
                     this.weaponValue = this.tribeStats[0][0] > 35 ? 2 : (this.tribeStats[0][0] - 5) / 15;
                     this.a_int_int_int_void(
                        (this.pd0ShortTables[1][this.spriteTableIndex] & 4095) + this.weaponValue,
                        var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                        var5 + this.pd0ByteTables[2][this.spriteTableIndex]
                     );
                  }

                  this.weaponSlotIndex++;
               } else {
                  this.a_int_int_int_void(
                     this.pd0ShortTables[1][this.spriteTableIndex] & 4095,
                     var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                     var5 + this.pd0ByteTables[2][this.spriteTableIndex]
                  );
               }
            } else if ((var8 == 2 || var8 == 4 || var8 == 6) && var6 != 2) {
               if (this.spriteFlags == 0 || this.spriteFlags <= var2) {
                  int var7 = this.spriteTableIndex;
                  var9 = this.pd0ShortTables[1][this.spriteTableIndex] & 4095;
                  if (var8 == 6) {
                     var10 += this.camTileY + this.renderRow + this.spriteTableIndex * 2;

                     while (var10 >= 120) {
                        var10 -= 120;
                     }

                     if (var11 == 1 || var11 == 2 && (this.entityData[8627 + var1] == 1 || this.entityData[8627 + var1] == 2)) {
                        if (var1 >= 31) {
                           var9++;
                        }

                        var12 = true;
                     } else if (var11 != 2) {
                        var12 = true;
                     }
                  } else if (this.missionIndex == 3 && var1 == 31 && var11 == 0) {
                     if (var9 == 116 && var8 == 4) {
                        var12 = true;
                        var10 = 0;
                     }
                  } else if (var8 == 2 && (this.entityData[1313 + this.currentSpriteEntityId] <= 0 || this.entityData[1313 + this.currentSpriteEntityId] >= 50)
                     )
                   {
                     var12 = true;
                     if (this.entityData[1313 + this.currentSpriteEntityId] == 51) {
                        this.partDrawnFlagsI[var11] = 1;
                     }
                  } else if (var8 == 4
                     && this.partDrawnFlagsI[var11] == 0
                     && this.entityData[1313 + this.currentSpriteEntityId] >= 1
                     && this.entityData[1313 + this.currentSpriteEntityId] != 50) {
                     var9 = this.pd0ShortTables[1][this.spriteTableIndex + this.entityData[1313 + this.currentSpriteEntityId] - 1] & 4095;
                     var10 = this.entityData[3030 + this.currentSpriteEntityId];
                     var12 = true;
                     this.partDrawnFlagsI[var11] = 1;
                     if (this.entityData[8749 + var1] == 2 && this.entityData[1313 + this.currentSpriteEntityId] == 2) {
                        this.drawSpriteAt(90, var4 + this.pd0ByteTables[1][var7] + var14, var5 + this.pd0ByteTables[2][var7], this.animCycle);
                     }
                  }

                  if (var12) {
                     this.drawSpriteAt(var9, var4 + this.pd0ByteTables[1][var7] + var14, var5 + this.pd0ByteTables[2][var7], var10);
                  }
               }
            } else if ((var8 == 3 || var8 == 5 || var8 == 7) && this.partDrawnFlagsH[var11] == 0 && this.currentSpriteEntityId > 0 && var6 != 2) {
               byte var13 = 0;
               if (var1 < 31 || (this.entityData[9176 + var1] & 3) >= 2) {
                  if (var8 == 7 && this.entityData[1313 + this.currentSpriteEntityId] == 50) {
                     var12 = true;
                  } else if (var8 == 3 && this.entityData[1313 + this.currentSpriteEntityId] >= 1 && this.entityData[1313 + this.currentSpriteEntityId] != 50) {
                     switch (var13 = this.fileAData[this.recordStarts[139] + this.fileAData[this.recordStarts[140] + var3] + var11]) {
                        case -1:
                           var13 = this.entityData[606 + this.currentSpriteEntityId];
                        default:
                           var12 = true;
                     }
                  } else if (var8 == 5 && this.entityData[1313 + this.currentSpriteEntityId] <= 0) {
                     var12 = true;
                     if ((this.entityData[3535 + this.currentSpriteEntityId] != 0 || this.entityData[6565 + this.currentSpriteEntityId] == 1)
                        && var3 != 10
                        && var3 != 11) {
                        this.spriteFlipped = true;
                     }

                     if (this.mode == 0) {
                        var13 = this.entityData[606 + this.currentSpriteEntityId];
                     }
                  }
               }

               if (var12) {
                  this.drawEntity(
                     this.currentSpriteEntityId,
                     var4 + this.pd0ByteTables[1][this.spriteTableIndex] + var14,
                     var5 + this.pd0ByteTables[2][this.spriteTableIndex],
                     var13,
                     (byte)29
                  );
                  this.partDrawnFlagsH[var11] = 1;
               }
            }

            this.spriteTableIndex++;
         }
      } catch (Exception var16) {
      }

      this.currentSpriteEntityId = 0;
   }

   // $VF: renamed from: a (int, int, int, int, byte) void
   private void drawEntity(int var1, int var2, int var3, int var4, byte var5) {
      try {
         this.drawEntityId = var1;
         this.drawX = var2;
         this.drawY = var3;
         this.drawFrame = var4;
         this.spriteKind = var5;
         this.spriteFrameId = this.entityData[this.offsetTable[this.spriteKind] + this.drawEntityId] & 255;
         int var6 = 0;
         if (this.drawFrame >= 0) {
            this.drawFrame = this.fileAData[this.recordStarts[99] + ((this.pd0ShortTables[3][this.spriteFrameId] >> 14 & 3) << 3) + this.drawFrame];
            var6 = (this.pd0ShortTables[2][this.spriteFrameId] & 4095)
               + this.drawFrame * this.pd0ByteTables[4][this.spriteFrameId]
               + this.entityData[this.offsetTable[this.spriteKind + 1] + this.drawEntityId];
            this.spriteDefIndex = this.pd0ShortTables[4][var6];
            this.drawX = this.drawX + this.pd0ByteTables[7][var6];
            this.drawY = this.drawY + this.pd0ByteTables[8][var6] + this.shakeOffsetY;
            if (this.spriteFlipped) {
               if (this.spriteFrameId != 124) {
                  this.spriteDefIndex += 48;
                  this.spriteFrameId = 1;
               }

               this.spriteFlipped = false;
            }

            if (this.spriteFrameId == 141 || this.spriteFrameId == 142 || this.spriteFrameId == 143 || this.spriteFrameId == 144) {
               this.drawX = this.drawX + 22 * this.fileAData[this.recordStarts[1] + this.entityData[606 + this.drawEntityId]];
            }
         } else {
            this.spriteFrameId = -this.drawFrame;
            if (this.entityData[1414 + this.drawEntityId] < 12) {
               this.spriteFrameId = 14;
               this.drawY -= 6;
            }

            this.spriteDefIndex = this.pd0ShortTables[4][(this.pd0ShortTables[2][this.spriteFrameId] & 4095) + 2];
         }

         this.spritePackIndex = (this.pd0ShortTables[2][this.spriteFrameId] & '\uf000') >> 14;
         this.drawEntityParts();
         if (this.mode != 0) {
            this.drawEntityOverlays();
         }
      } catch (Exception var7) {
      }
   }

   // $VF: renamed from: p () void
   private void drawEntityParts() {
      this.spritePartBase = this.pd0ShortTables[8][this.spriteDefIndex];
      this.overlayHeadPart = -1;
      this.overlayBodyPart = -1;

      for (this.partLoopIndex = 0; this.partLoopIndex < this.pd0ByteTables[15][this.spriteDefIndex]; this.partLoopIndex++) {
         this.spritePartIndex = this.spritePartBase + this.partLoopIndex;
         if (this.pd0ShortTables[7][this.spritePartIndex] != 0) {
            this.spritePartVariant = 0;
            this.spritePartType = this.pd0ByteTables[14][this.spritePartIndex] & 15;
            if (this.spriteKind == 29) {
               if (!this.highlightSelected()) {
                  continue;
               }

               if (this.entityData[1414 + this.drawEntityId] >= 12) {
                  if (this.drawEntityId >= 51) {
                     this.spriteVariant = 4;
                  }

                  if (!this.filterSpritePart()) {
                     continue;
                  }
               } else {
                  if (this.spritePartType == 7 && this.entityData[1515 + this.drawEntityId] == 0) {
                     continue;
                  }

                  if (this.spritePartType == 0) {
                     this.overlayBodyPart = this.spritePartIndex;
                     if (this.drawEntityId >= 51) {
                        this.spritePartVariant++;
                     }
                  } else if (this.spritePartType == 9) {
                     this.overlayHeadPart = this.spritePartIndex;
                     this.spritePartVariant = this.entityData[5151 + this.drawEntityId] & 1;
                  } else if (this.spritePartType == 5) {
                     continue;
                  }
               }

               if (this.pd0ShortTables[7][this.spritePartIndex] == 1) {
                  continue;
               }
            } else if (this.spritePartType == 0) {
               this.overlayHeadPart = this.overlayBodyPart = this.spritePartIndex;
            }

            if ((this.pd0ByteTables[14][this.spritePartIndex] & 255) >> 7 == 0) {
               this.b_int_int_int_void(
                  this.pd0ShortTables[7][this.spritePartIndex] + this.spritePartVariant,
                  this.drawX + this.pd0ByteTables[12][this.spritePartIndex],
                  this.drawY + this.pd0ByteTables[13][this.spritePartIndex]
               );
            } else {
               this.a_int_int_int_void(
                  this.pd0ShortTables[7][this.spritePartIndex] + this.spritePartVariant,
                  this.drawX + this.pd0ByteTables[12][this.spritePartIndex],
                  this.drawY + this.pd0ByteTables[13][this.spritePartIndex]
               );
            }

            this.spriteVariant = -1;
         }
      }
   }

   // $VF: renamed from: a () boolean
   private boolean highlightSelected() {
      if (this.spritePartType == 10 && !this.storyMode && this.mode == 1 && this.drawFrame > -1) {
         if ((this.entityData[909 + this.drawEntityId] == 0 || this.entityData[909 + this.drawEntityId] != this.fireHeldCount)
            && (this.fireHeldCount != -1 || this.selectedVillagerId != this.drawEntityId)) {
            if (this.nearestEntityId == this.drawEntityId) {
               this.spritePartVariant = 1;
               if (this.nearestEntityId >= 51) {
                  this.spritePartVariant = 3;
               }
            }
         } else {
            this.a_int_int_int_void(599, this.drawX + this.pd0ByteTables[12][this.spritePartIndex], this.drawY + this.pd0ByteTables[13][this.spritePartIndex]);
         }

         this.highlightCount = 0;

         for (int var1 = this.selRectY + 1; var1 <= this.selRectY + this.selRectH; var1++) {
            for (int var2 = this.selRectX + 1; var2 <= this.selRectX + this.selRectW; var2++) {
               byte var3;
               if ((var3 = this.occupantGrid[var1][var2]) >= 1 && var3 <= 50 && this.entityData[1919 + var3] > 0) {
                  this.highlightCount++;
                  if (var3 == this.drawEntityId) {
                     this.spritePartVariant = 1;
                  }
               }
            }
         }
      }

      return true;
   }

   // $VF: renamed from: b () boolean
   private boolean filterSpritePart() {
      if (this.spritePartType == 0) {
         this.overlayBodyPart = this.spritePartIndex;
         this.spritePartVariant = 0;
         this.spritePartVariant = this.entityData[5151 + this.drawEntityId] >> 2 & 3;
         if (this.spritePartVariant > 1) {
            this.spritePartVariant -= 2;
            this.spriteVariant = 0;
         }

         this.spritePartVariant *= 72;
         if (this.drawEntityId >= 51) {
            this.spritePartVariant += 36;
         }

         this.spritePartVariant = this.spritePartVariant + this.entityData[1515 + this.drawEntityId] * 18;
      } else if (this.spritePartType == 9) {
         this.overlayHeadPart = this.spritePartIndex;
         this.spritePartVariant = 0;
         this.spritePartVariant = this.entityData[5151 + this.drawEntityId] & 3;
         if (this.drawEntityId >= 51) {
            this.spriteVariant = 3;
         } else if (this.spritePartVariant > 0) {
            this.spriteVariant = this.spritePartVariant;
         }

         this.spritePartVariant = this.entityData[1515 + this.drawEntityId] == 1
            ? 1
            : (this.entityData[1414 + this.drawEntityId] < 30 ? 3 : (this.entityData[1414 + this.drawEntityId] < 50 ? 2 : 0));
         this.spritePartVariant *= 12;
      } else if (this.spritePartType == 7) {
         if (this.drawEntityId == this.tribeStats[0][17]) {
            this.spritePartVariant += 2;
         } else {
            if (this.fileAData[this.recordStarts[42] + this.fileAData[this.recordStarts[41] + this.entityData[6464 + this.drawEntityId]]] == 0) {
               return false;
            }

            this.spritePartVariant = this.spritePartVariant
               + (this.fileAData[this.recordStarts[42] + this.fileAData[this.recordStarts[41] + this.entityData[6464 + this.drawEntityId]]] - 1);
         }
      } else if (this.spritePackIndex == 0 && this.spritePartType >= 5 && this.spritePartType <= 8) {
         if (this.drawFrame < 0 && this.entityData[6565 + this.drawEntityId] == 1) {
            return false;
         }

         if ((this.spritePartType != 5 || this.entityData[6565 + this.drawEntityId] != 4)
            && (this.spritePartType != 6 || this.entityData[6565 + this.drawEntityId] != 5)) {
            if (this.fileAData[this.recordStarts[145] + this.entityData[6565 + this.drawEntityId]] != this.spritePartType) {
               return false;
            }
         } else {
            this.spritePartVariant = 1;
         }
      } else if (this.spritePackIndex == 2) {
         if (this.entityData[3535 + this.drawEntityId] != 0) {
            if (this.spritePartType != 4
               || this.entityData[3636 + this.drawEntityId] <= 0
                  && this.spriteFrameId != 113
                  && this.spriteFrameId != 114
                  && this.spriteFrameId != 115
                  && this.spriteFrameId != 116) {
               if (this.spritePartType == 5) {
                  if (this.entityData[6565 + this.drawEntityId] == 9) {
                     return false;
                  }

                  this.spritePartVariant = this.spritePartVariant + this.entityData[6565 + this.drawEntityId];
               } else if (this.spritePartType == 8) {
                  return false;
               }
            } else {
               this.spritePartVariant = this.spritePartVariant + this.entityData[3535 + this.drawEntityId];
            }
         } else if (this.entityData[3535 + this.drawEntityId] == 0) {
            if (this.spritePartType == 4) {
               return false;
            }

            if (this.spritePartType == 5) {
               if (this.entityData[6565 + this.drawEntityId] == 1) {
                  return false;
               }

               if (this.entityData[6565 + this.drawEntityId] == 9) {
                  return false;
               }

               this.spritePartVariant = this.spritePartVariant + this.entityData[6565 + this.drawEntityId];
            } else if (this.spritePartType == 8 && this.entityData[6565 + this.drawEntityId] != 1) {
               return false;
            }
         }
      } else if (this.spritePackIndex == 1 && this.spritePartType == 5) {
         if (this.entityData[6565 + this.drawEntityId] == 9) {
            return false;
         }

         this.spritePartVariant = this.spritePartVariant + this.entityData[6565 + this.drawEntityId];
      }

      return true;
   }

   // $VF: renamed from: q () void
   private void drawEntityOverlays() {
      if (!this.storyMode && this.overlayHeadPart >= 0 && this.overlayBodyPart >= 0 && this.mode == 1) {
         this.textLoader
            .drawFloatingTexts(
               this.spriteKind == 29 ? this.drawEntityId : 101 + this.drawEntityId,
               this.drawX + this.pd0ByteTables[12][this.overlayHeadPart] + 4,
               this.drawY + this.pd0ByteTables[13][this.overlayHeadPart]
            );
         if (this.spriteKind == 29 && this.drawFrame > -1) {
            for (int var1 = 0; var1 < 30; var1++) {
               if (this.pendingOrders[0][2][var1] == this.drawEntityId && this.pendingOrders[0][1][var1] != 3) {
                  this.drawSpriteAt(
                     65 + (1 - this.pendingOrders[0][4][var1]) * 5 + this.pendingOrders[0][1][var1],
                     this.drawX + this.pd0ByteTables[12][this.overlayBodyPart],
                     this.drawY + this.pd0ByteTables[13][this.overlayBodyPart],
                     this.animCycle
                  );
                  break;
               }
            }

            if (this.entityData[4646 + this.drawEntityId] == 1
               && this.entityData[2828 + this.drawEntityId] == 51
               && this.entityData[1313 + this.drawEntityId] > 0) {
               this.drawSpriteAt(
                  65, this.drawX + this.pd0ByteTables[12][this.overlayBodyPart], this.drawY + this.pd0ByteTables[13][this.overlayBodyPart], this.animCycle
               );
            }

            if (this.entityData[3333 + this.drawEntityId] > -1) {
               this.drawSpriteAt(
                  77 + this.entityData[3333 + this.drawEntityId],
                  this.drawX + this.pd0ByteTables[12][this.overlayHeadPart],
                  this.drawY + this.pd0ByteTables[13][this.overlayHeadPart],
                  (this.tickCounter - (this.drawEntityId & 7)) % 15
               );
            }

            if (this.entityData[4646 + this.drawEntityId] < -1) {
               this.drawSpriteAt(
                  -this.entityData[4646 + this.drawEntityId],
                  this.drawX + this.pd0ByteTables[12][this.overlayBodyPart],
                  this.drawY + this.pd0ByteTables[13][this.overlayBodyPart],
                  this.entityData[3030 + this.drawEntityId]
               );
            }

            if ((this.entityData[7979 + this.drawEntityId] & 4) == 4) {
               this.drawSpriteAt(
                  73,
                  this.drawX + this.pd0ByteTables[12][this.overlayHeadPart],
                  this.drawY + this.pd0ByteTables[13][this.overlayHeadPart],
                  this.animCycle + this.drawEntityId
               );
            }

            if ((this.entityData[7979 + this.drawEntityId] & 8) == 8) {
               this.drawSpriteAt(
                  69, this.drawX + this.pd0ByteTables[12][this.overlayBodyPart], this.drawY + this.pd0ByteTables[13][this.overlayBodyPart], this.animCycle
               );
            }

            if (this.drawEntityId <= 50) {
               if (this.speedSetting == 0
                  || this.speedSetting == 1
                     && (
                        this.drawEntityId == this.selectedVillagerId
                           || this.drawEntityId == this.nearestEntityId
                           || this.entityData[909 + this.drawEntityId] == this.fireHeldCount
                           || this.drawEntityId == this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]
                           || this.currentSpriteEntityId != 0
                              && -this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]
                                 == this.entityData[4444 + this.drawEntityId]
                     )) {
                  this.a_int_int_int_void(
                     577 + this.entityData[2222 + this.drawEntityId],
                     this.drawX + this.pd0ByteTables[12][this.overlayHeadPart],
                     this.drawY + this.pd0ByteTables[13][this.overlayHeadPart]
                  );
               }

               if (!this.chiefMode
                  && this.entityData[1919 + this.drawEntityId] > 0
                  && (
                     this.drawEntityId == this.selectedVillagerId
                        || this.drawEntityId == this.nearestEntityId
                        || this.entityData[909 + this.drawEntityId] == this.fireHeldCount
                        || this.drawEntityId == this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]
                        || this.entityData[6767 + this.drawEntityId] / 3 * 2 > this.entityData[1919 + this.drawEntityId]
                        || this.currentSpriteEntityId != 0
                           && -this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol] == this.entityData[4444 + this.drawEntityId]
                  )) {
                  int var3 = this.entityData[6767 + this.drawEntityId] / 20;
                  byte var2 = 0;
                  if (this.entityData[1616 + this.drawEntityId] >= 5
                     || this.entityData[1717 + this.drawEntityId] >= 5
                     || this.entityData[7575 + this.drawEntityId] > 5) {
                     var2 = 2;
                     this.a_int_int_int_void(
                        602
                           + (
                              this.entityData[1616 + this.drawEntityId] > this.entityData[1717 + this.drawEntityId]
                                 ? (
                                    this.entityData[1616 + this.drawEntityId] > this.entityData[7575 + this.drawEntityId]
                                       ? this.entityData[1616 + this.drawEntityId]
                                       : (
                                          this.entityData[1717 + this.drawEntityId] >= this.entityData[7575 + this.drawEntityId]
                                             ? this.entityData[1717 + this.drawEntityId]
                                             : this.entityData[7575 + this.drawEntityId] - 1
                                       )
                                 )
                                 : (
                                    this.entityData[1717 + this.drawEntityId] >= this.entityData[7575 + this.drawEntityId]
                                       ? this.entityData[1717 + this.drawEntityId]
                                       : this.entityData[7575 + this.drawEntityId] - 1
                                 )
                           )
                           - 5,
                        this.drawX + this.pd0ByteTables[12][this.overlayHeadPart] + 2 - var3 + 2 - 10,
                        this.drawY + this.pd0ByteTables[13][this.overlayHeadPart]
                     );
                  }

                  this.a_int_int_int_void(
                     607 + var3, this.drawX + this.pd0ByteTables[12][this.overlayHeadPart] + var2, this.drawY + this.pd0ByteTables[13][this.overlayHeadPart]
                  );
                  screenBufferGraphics[this.drawBufferIndex]
                     .setColor(this.hpBarColors[(this.entityData[1919 + this.drawEntityId] - 1) * 3 / this.entityData[6767 + this.drawEntityId]]);
                  fillRect(
                     screenBufferGraphics[this.drawBufferIndex],
                     this.drawX + this.pd0ByteTables[12][this.overlayHeadPart] + 2 - var3 + var2,
                     this.drawY + this.pd0ByteTables[13][this.overlayHeadPart] - 4,
                     this.fileAData[this.recordStarts[128] + var3] * this.entityData[1919 + this.drawEntityId] / this.entityData[6767 + this.drawEntityId],
                     2
                  );
               }
            }

            if ((this.chiefMode || this.selectionChanged) && this.drawEntityId == this.inspectedVillagerId) {
               this.drawSpriteAt(
                  41,
                  this.drawX + this.pd0ByteTables[12][this.overlayBodyPart] - 1,
                  this.drawY + this.pd0ByteTables[13][this.overlayBodyPart] - 16 - 5,
                  this.animFrame
               );
            }
         }
      }
   }

   // $VF: renamed from: a (int, int, int, int) void
   final void spawnAnimEffect(int var1, int var2, int var3, int var4) {
      for (int var5 = 0; var5 < 40; var5++) {
         if (this.effects[0][var5] == 0) {
            this.effects[1][var5] = 0;
            this.effects[0][var5] = (byte)var4;
            this.effects[2][var5] = (byte)var3;
            this.effects[6][var5] = (byte)var2;
            this.effects[5][var5] = (byte)var1;
            return;
         }
      }
   }

   // $VF: renamed from: r () void
   private void updateEffects() {
      boolean var1 = false;
      int var2 = 0;
      int var3 = 0;
      byte var4 = 0;
      int var5 = 0;
      short var6 = 0;
      int var7 = 0;
      short var8 = 0;
      this.drawEntityId = -1;

      try {
         for (int var12 = 0; var12 < 40; var12++) {
            if (this.effects[0][var12] > 0) {
               byte var9 = 29;
               this.drawEntityId = this.effects[5][var12];
               if (this.effects[6][var12] == 1) {
                  var2 = this.entityData[0 + this.drawEntityId];
                  var3 = this.entityData[101 + this.drawEntityId];
               } else if (this.effects[6][var12] == 2) {
                  var2 = this.entityData[9359 + this.drawEntityId];
                  var3 = this.entityData[9381 + this.drawEntityId];
                  var9 = 108;
                  this.effects[2][var12] = 0;
               } else {
                  var2 = this.entityData[8383 + this.drawEntityId] + (this.effects[2][var12] & 15);
                  var3 = this.entityData[8444 + this.drawEntityId] + (this.effects[2][var12] >> 4 & 15);
               }

               if (!this.isTileOnScreen(var2, var3)
                  || this.effectBitAt(var2, var3) != 0
                  || this.entityData[1919 + this.drawEntityId] <= 0 && this.effects[6][var12] == 1) {
                  this.effects[0][var12] = 0;
                  return;
               }

               var2 = (var2 - this.camTileX) * 22;
               var3 = (var3 - this.camTileY) * 16;
               if (this.effects[6][var12] == 1 || this.effects[6][var12] == 2) {
                  var4 = this.entityData[606 + this.drawEntityId];
                  var4 = this.fileAData[this.recordStarts[99]
                     + (this.pd0ShortTables[3][this.entityData[this.offsetTable[var9] + this.drawEntityId] & 0xFF] >> 14 & 3) * 8
                     + var4];
                  var5 = (this.pd0ShortTables[2][this.entityData[this.offsetTable[var9] + this.drawEntityId] & 0xFF] & 4095)
                     + var4 * this.pd0ByteTables[4][this.entityData[this.offsetTable[var9] + this.drawEntityId] & 0xFF]
                     + this.entityData[this.offsetTable[var9 + 1] + this.drawEntityId];
                  var6 = this.pd0ShortTables[4][var5];
                  var7 = 0;
                  var8 = this.pd0ShortTables[8][this.pd0ShortTables[4][var5]];

                  for (short var10 = 0; var10 < this.pd0ByteTables[15][var6]; var10++) {
                     var7 = var8 + var10;
                     if ((this.pd0ByteTables[14][var7] & 15) == this.effects[2][var12]) {
                        break;
                     }
                  }

                  if (this.effects[6][var12] == 1) {
                     var2 += this.entityData[202 + this.drawEntityId] + this.pd0ByteTables[12][var7];
                     var3 += this.entityData[303 + this.drawEntityId] + this.pd0ByteTables[13][var7];
                  } else if (this.effects[6][var12] == 2) {
                     var2 += this.entityData[9403 + this.drawEntityId] + this.pd0ByteTables[12][var7];
                     var3 += this.entityData[9425 + this.drawEntityId] + this.pd0ByteTables[13][var7];
                  }
               }

               this.drawSpriteAt(this.effects[0][var12], var2, var3, this.effects[1][var12]);
               this.effects[1][var12]++;
               if (this.effects[1][var12] >= this.pd0ByteTables[9][this.effects[0][var12]]) {
                  this.effects[0][var12] = 0;
               }
            }
         }
      } catch (Exception var11) {
      }
   }

   // $VF: renamed from: a (int, int) void
   final void enqueueBuildAnim(int var1, int var2) {
      for (int var3 = 0; var3 < 30; var3++) {
         if (this.buildAnims[0][var3] == 0) {
            this.buildAnims[1][var3] = 0;
            this.buildAnims[2][var3] = (byte)var2;
            this.buildAnims[0][var3] = (byte)var1;
            return;
         }
      }
   }

   // $VF: renamed from: s () void
   private void updateBuildAnims() {
      for (int var6 = 0; var6 < 30; var6++) {
         if (this.buildAnims[0][var6] != 0) {
            byte var1 = this.buildAnims[0][var6];
            byte var2 = this.entityData[8383 + var1];
            int var3 = this.entityData[8444 + var1] - 1;
            byte var4 = this.fileAData[this.recordStarts[138] + 104 + this.entityData[8749 + var1]];
            byte var5 = this.fileAData[this.recordStarts[138] + 130 + this.entityData[8749 + var1]];
            if (this.buildAnims[1][var6] >= 20) {
               this.buildAnims[0][var6] = 0;
               if (this.buildAnims[2][var6] == 3) {
                  this.c_int_boolean_void(var1, false);
                  this.entityData[8627 + var1] = 3;
               }

               this.markTileRect(var2, var3 + 1, var4, var5);
               this.refreshPanelIfMenuTarget(var1);
            } else {
               for (byte var7 = 0; var7 < var5 + 1; var7 += 2) {
                  for (byte var8 = 0; var8 < var4; var8 += 2) {
                     if (this.isTileOnScreen(var2 + var8, var3 + var7)) {
                        this.drawSpriteAt(
                           74, (var2 + var8 - this.camTileX) * 22, (var3 + var7 - this.camTileY) * 16, (var8 * 2 + var7) * 2 + this.buildAnims[1][var6] - 1
                        );
                     }
                  }
               }
            }

            this.buildAnims[1][var6]++;
         }
      }
   }

   // $VF: renamed from: a (int, int) boolean
   private boolean isTileOnScreen(int var1, int var2) {
      return var1 >= this.camTileX && var1 < this.camTileX + this.screenColsTiles && var2 >= this.camTileY && var2 < this.camTileY + this.screenRowsTiles;
   }

   // $VF: renamed from: t () void
   private void clearEffects() {
      for (byte var1 = 0; var1 < 40; var1++) {
         this.effects[0][var1] = 0;
      }
   }

   // $VF: renamed from: d (int, int, int, int) void
   private void drawSpriteAt(int var1, int var2, int var3, int var4) {
      int var5 = this.pd0ShortTables[5][var1] + var4 % this.pd0ByteTables[9][var1];
      this.a_int_int_int_void(this.pd0ShortTables[6][var5], var2 + this.pd0ByteTables[10][var5], var3 + this.pd0ByteTables[11][var5]);
   }

   // $VF: renamed from: a (int, int, int) void
   final void a_int_int_int_void(int var1, int var2, int var3) {
      int var4 = this.pd0ShortTables[9][var1] & 8191;
      int var5;
      if ((var5 = (this.pd0ShortTables[9][var1] & '\uffff') >> 14 & 3) != 0) {
         this.b_int_int_int_void((var5 - 1) * 3 + 1, var2, var3);
      }

      for (int var8 = var4; var8 < var4 + (this.pd0ByteTables[16][var1] & 63); var8++) {
         int var6 = this.pd0ShortTables[10][var8] & 8191;
         int var7 = (this.pd0ShortTables[10][var8] & '쀀') >> 14 << 7;
         if ((this.pd0ShortTables[10][var8] & 8192) >> 13 == 0) {
            this.b_int_int_int_void(var6, var2 + this.pd0ByteTables[18][var8] + var7, var3 + this.pd0ByteTables[19][var8]);
         } else {
            this.a_int_int_int_void(var6, var2 + this.pd0ByteTables[18][var8] + var7, var3 + this.pd0ByteTables[19][var8]);
         }
      }
   }

   // $VF: renamed from: b (int, int, int) void
   private void b_int_int_int_void(int var1, int var2, int var3) {
      if (var1 != 0) {
         int var4 = this.pd0ByteTables[20][var1] & 31;
         var2 += 11;
         var3 += 8;
         if (var4 < 27) {
            boolean var5 = false;
            if (this.spriteVariant > -1) {
               if (this.spriteVariant == 0) {
                  if (var4 == 5) {
                     var4 = this.spriteVariant;
                     var5 = true;
                  }
               } else if (this.spriteVariant == 4) {
                  if (var4 == 14) {
                     var4 = 4;
                     var5 = true;
                  } else if (var4 == 15) {
                     var4 = 5;
                     var5 = true;
                  }
               } else if (var4 == 9) {
                  var4 = this.spriteVariant;
                  var5 = true;
               }
            }

            if (!var5) {
               drawRegionSafe(
                  screenBufferGraphics[this.drawBufferIndex],
                  this.spritePacks[0][var4],
                  this.pd0ByteTables[21][var1] & 127,
                  this.pd0ByteTables[22][var1],
                  this.pd0ByteTables[23][var1],
                  this.pd0ByteTables[24][var1],
                  var2,
                  var3,
                  20
               );
               return;
            }

            if (drawRegionSafe(
               screenBufferGraphics[this.drawBufferIndex],
               this.spritePacks[1][var4],
               this.pd0ByteTables[21][var1] & 127,
               this.pd0ByteTables[22][var1],
               this.pd0ByteTables[23][var1],
               this.pd0ByteTables[24][var1],
               var2,
               var3,
               20
            )) {
               return;
            }
         } else {
            int var8 = (this.pd0ByteTables[20][var1] & 255) >> 5 | (this.pd0ByteTables[21][var1] & 128) >> 4;
            screenBufferGraphics[this.drawBufferIndex]
               .setColor(this.pd0ByteTables[17][var8 * 3 + 0] & 255, this.pd0ByteTables[17][var8 * 3 + 1] & 255, this.pd0ByteTables[17][var8 * 3 + 2] & 255);
            if (var4 == 27) {
               drawLine(
                  screenBufferGraphics[this.drawBufferIndex],
                  var2 + (this.pd0ByteTables[21][var1] & 127),
                  var3 + this.pd0ByteTables[22][var1],
                  var2 + this.pd0ByteTables[23][var1],
                  var3 + this.pd0ByteTables[24][var1]
               );
               return;
            }

            if (var4 == 28) {
               drawRect(
                  screenBufferGraphics[this.drawBufferIndex],
                  var2 + (this.pd0ByteTables[21][var1] & 127),
                  var3 + this.pd0ByteTables[22][var1],
                  this.pd0ByteTables[23][var1],
                  this.pd0ByteTables[24][var1]
               );
               return;
            }

            if (var4 == 29) {
               fillRect(
                  screenBufferGraphics[this.drawBufferIndex],
                  var2 + (this.pd0ByteTables[21][var1] & 127) - 11,
                  var3 + this.pd0ByteTables[22][var1] - 8,
                  this.pd0ByteTables[23][var1],
                  this.pd0ByteTables[24][var1]
               );
               return;
            }

            if (var4 == 30) {
               fillRect(
                  screenBufferGraphics[this.drawBufferIndex],
                  var2 + (this.pd0ByteTables[21][var1] & 127) * 4 - 11,
                  var3 + this.pd0ByteTables[22][var1] * 4 - 8,
                  this.pd0ByteTables[23][var1] * 4,
                  this.pd0ByteTables[24][var1] * 4
               );
            }
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int) void
   private void a_Graphics_int_int_int_void(Graphics var1, int var2, int var3, int var4) {
      int var5;
      for (int var8 = var5 = this.pd0ShortTables[9][var2] & 8191; var8 < var5 + (this.pd0ByteTables[16][var2] & 63); var8++) {
         int var6 = this.pd0ShortTables[10][var8] & 8191;
         int var7 = (this.pd0ShortTables[10][var8] & '쀀') >> 14 << 7;
         if ((this.pd0ShortTables[10][var8] & 8192) >> 13 == 0) {
            this.b_Graphics_int_int_int_void(var1, var6, var3 + this.pd0ByteTables[18][var8] + var7, var4 + this.pd0ByteTables[19][var8]);
         } else {
            this.a_Graphics_int_int_int_void(var1, var6, var3 + this.pd0ByteTables[18][var8] + var7, var4 + this.pd0ByteTables[19][var8]);
         }
      }
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Graphics, int, int, int) void
   private void b_Graphics_int_int_int_void(Graphics var1, int var2, int var3, int var4) {
      if (var2 != 0) {
         int var5 = this.pd0ByteTables[20][var2] & 31;

         try {
            if (var5 == 27) {
               var1.setColor(0);
               drawLine(
                  var1,
                  var3 + (this.pd0ByteTables[21][var2] & 127),
                  var4 + this.pd0ByteTables[22][var2],
                  var3 + this.pd0ByteTables[23][var2],
                  var4 + this.pd0ByteTables[24][var2]
               );
            } else if (var5 == 28) {
               var1.setColor(0);
               drawRect(
                  var1,
                  var3 + (this.pd0ByteTables[21][var2] & 127),
                  var4 + this.pd0ByteTables[22][var2],
                  this.pd0ByteTables[23][var2],
                  this.pd0ByteTables[24][var2]
               );
            } else if (var5 == 29) {
               var1.setColor(0);
               fillRect(
                  var1,
                  var3 + (this.pd0ByteTables[21][var2] & 127) - 11,
                  var4 + this.pd0ByteTables[22][var2] - 8,
                  this.pd0ByteTables[23][var2],
                  this.pd0ByteTables[24][var2]
               );
            } else {
               if (var5 != 30) {
                  drawRegionSafe(
                     var1,
                     this.spritePacks[0][var5],
                     this.pd0ByteTables[21][var2] & 127,
                     this.pd0ByteTables[22][var2],
                     this.pd0ByteTables[23][var2],
                     this.pd0ByteTables[24][var2],
                     var3,
                     var4,
                     20
                  );
                  return;
               }

               var1.setColor(0);
               fillRect(
                  var1,
                  var3 + (this.pd0ByteTables[21][var2] & 127) * 4 - 11,
                  var4 + this.pd0ByteTables[22][var2] * 4 - 8,
                  this.pd0ByteTables[23][var2] * 4,
                  this.pd0ByteTables[24][var2] * 4
               );
            }
         } catch (Exception var8) {
         }
      }
   }

   // $VF: renamed from: i (int) void
   private void drawEntitySprite(int var1) {
      byte var4 = 0;
      byte var5 = 0;
      int var6 = 0;
      int var7 = 0;
      boolean var8 = true;

      try {
         do {
            if (!var8) {
               var1 = this.entityData[6060 + var1];
            }

            var8 = false;
            var4 = this.entityData[7676 + var1];
            var6 = 0 + (this.entityData[5252 + var1] - this.camTileX) * 22 + 11 + this.entityData[5454 + var1];
            var7 = 0
               + (this.entityData[5353 + var1] - this.camTileY) * 16
               + 8
               + this.entityData[5555 + var1]
               - this.spriteYOffsetTable[this.entityData[5858 + var1]][var1]
               - 10;
            if ((var5 = this.entityData[6565 + var1]) == 1 || var5 == 2 || var5 == 5) {
               this.a_int_int_int_void(
                  292
                     + this.fileAData[this.recordStarts[190 + this.fileAData[this.recordStarts[77] + var5]]
                        + var4 * this.fileAData[this.recordStarts[193] + 6 + var5]
                        + this.spriteFrameTable[this.entityData[5858 + var1]][var1]]
                     + this.fileAData[this.recordStarts[193] + var5],
                  var6,
                  var7
               );
            }
         } while (this.entityData[6060 + var1] != 0);
      } catch (Exception var10) {
      }
   }

   // $VF: renamed from: u () void
   private void renderWorldEntities() {
      int var1 = 0;
      byte var2 = -16;
      byte var4 = 0;
      int var5 = 0;

      try {
         this.drawWorldMarkers();

         for (this.renderRow = -1; this.renderRow < this.screenRowsTiles + 3; this.renderRow++) {
            int var7;
            if ((var7 = this.renderRow + this.camTileY) < this.mapHeight) {
               for (this.renderCol = this.screenColsTiles - 1 + 1; this.renderCol >= -1; this.renderCol--) {
                  int var8 = this.renderCol + this.camTileX;
                  if (var7 >= 0 && var7 < this.mapHeight && var8 >= 0 && var8 < this.mapWidth) {
                     var1 = this.renderCol * 22;
                     byte var3 = this.effectBitAt(var8, var7);
                     if (this.storyMode && !this.storyIsCutscene) {
                        var3 = 0;
                     }

                     var4 = this.occupantGrid[var7][var8];
                     var5 = this.terrainGrid[var7][var8] & 255;
                     if (var4 != 0) {
                        if (var3 >= 2 || (var5 < 180 || var5 > 231) && var5 != 243) {
                           if (var3 < 1 && var4 >= 1 && var4 <= 100 && (!this.storyMode || this.storySceneType != 9)) {
                              if (tribeOfOccupantCode(this.entityData[4545 + var4]) != 2) {
                                 this.drawTile(this.entityData[4545 + var4], (byte)1);
                              }

                              if (tribeOfOccupantCode(this.entityData[4545 + var4]) == 2 || this.entityData[2929 + var4] <= 14) {
                                 if (this.renderRow >= 0
                                    && this.renderCol - 1 >= 0
                                    && this.entityData[9833 + (this.occupantGrid[var7][var8 - 1] + 61) * -1] == 5
                                    && isBuildingFootprintCode(this.occupantGrid[var7][var8 - 1])) {
                                    if (this.renderCol < this.screenColsTiles) {
                                       this.tileEntityDraw[this.renderRow][this.renderCol] = 0;
                                    }

                                    this.renderCol--;
                                    var8 = this.renderCol + this.camTileX;
                                    this.drawTile(this.occupantGrid[var7][var8], (byte)1);
                                 }

                                 if (tribeOfEntity(var4) == 0 || (this.entityData[7979 + var4] & 2) == 0) {
                                    this.drawEntity(
                                       var4, var1 + this.entityData[202 + var4], var2 + this.entityData[303 + var4], this.entityData[606 + var4], (byte)29
                                    );
                                 }
                              }
                           } else if (var4 <= -1 && var4 >= -60 && (var3 < 2 || this.entityData[9176 + -var4] > 0)) {
                              if ((this.entityData[9176 + -var4] & 4) != 0) {
                                 switch (var3) {
                                    case 0:
                                       this.entityData[9176 + -var4] = 6;
                                       break;
                                    case 1:
                                       this.entityData[9176 + -var4] = 5;
                                 }
                              }

                              switch (this.entityData[9176 + -var4] & 3) {
                                 case 1:
                                    var3 = 1;
                                    break;
                                 case 2:
                                    var3 = 0;
                              }

                              if ((this.entityData[8871 + -var4] & 1) == 0) {
                                 this.drawTile(var4, (byte)1);
                                 this.entityData[8871 + -var4] = (byte)(this.entityData[8871 + -var4] | 1);
                                 byte var9 = this.entityData[8749 + -var4];
                                 byte var10 = this.fileAData[this.recordStarts[138] + 104 + var9];
                                 byte var11 = this.fileAData[this.recordStarts[138] + 130 + var9];
                                 if ((this.entityData[9176 + -var4] & 4) == 0) {
                                    this.world.scratchByteS = 0;
                                    this.world.fillFogRect(this.entityData[8383 + -var4], this.entityData[8444 + -var4], var10, var11);
                                    this.world.scratchByteS = 1;
                                    this.entityData[9176 + -var4] = 4;
                                 }

                                 if (!this.storyMode && var9 < 22) {
                                    if ((
                                          this.fileAData[this.recordStarts[138] + 156 + var9] != -1
                                             || this.entityData[8627 + -var4] == 1
                                             || this.entityData[8627 + -var4] == 2
                                       )
                                       && this.entityData[8810 + -var4] == 0
                                       && tribeOfBuilding(var4) == 0) {
                                       this.drawSpriteAt(
                                          40,
                                          (this.entityData[8383 + -var4] - this.camTileX) * 22 + var10 * 11 - 11,
                                          (this.entityData[8444 + -var4] - this.camTileY) * 16 + var11 * 8 - 8,
                                          this.animFrame
                                       );
                                    }

                                    if ((
                                          this.entityData[8505 + -var4] > 0
                                                && (
                                                   var4 == this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]
                                                      || this.entityData[8566 + -var4] / 2 > this.entityData[8505 + -var4]
                                                )
                                             || this.entityData[8627 + -var4] == 1
                                             || this.entityData[8627 + -var4] == 2
                                             || var9 >= 12
                                       )
                                       && tribeOfBuilding(var4) == 0) {
                                       int var12 = this.entityData[8566 + -var4] / 80 + 6;
                                       int var13 = (this.entityData[8383 + -var4] - this.camTileX) * 22 + var10 * 11 - 11;
                                       int var14 = var2 - this.fileAData[this.recordStarts[138] + 520 + var9] * 8 - 5;
                                       this.a_int_int_int_void(607 + var12, var13, var14);
                                       screenBufferGraphics[this.drawBufferIndex]
                                          .setColor(this.hpBarColors[(this.entityData[8505 + -var4] - 1) * 3 / this.entityData[8566 + -var4]]);
                                       fillRect(
                                          screenBufferGraphics[this.drawBufferIndex],
                                          var13 + 1,
                                          var14 + 1,
                                          this.fileAData[this.recordStarts[128] + var12] * this.entityData[8505 + -var4] / this.entityData[8566 + -var4],
                                          2
                                       );
                                       if (var4 == this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]) {
                                          byte var15;
                                          int var16 = (var15 = this.world.buildingWorkerInfo(-var4)) >> 4 & 15;
                                          int var17 = var15 & 15;
                                          int var18;
                                          if ((var18 = var16 + var17) > 0) {
                                             var13 = var13 + 1 + this.fileAData[this.recordStarts[128] + var12] / 2 - 4 * var18 / 2 + 1;
                                             var14 += 6;
                                             screenBufferGraphics[this.drawBufferIndex].setColor(0);
                                             fillRect(screenBufferGraphics[this.drawBufferIndex], var13 - 0, var14 - 1, var18 * 4 - 1, 4);
                                             fillRect(screenBufferGraphics[this.drawBufferIndex], var13 - 1, var14 - 0, var18 * 4 + 1, 2);

                                             for (byte var22 = 0; var22 < var18; var22++) {
                                                screenBufferGraphics[this.drawBufferIndex].setColor(var22 < var17 ? 16164864 : 4207389);
                                                fillRect(screenBufferGraphics[this.drawBufferIndex], var13 + var22 * 4, var14, 3, 2);
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           } else if (var3 < 1 && isObjectId(var4)) {
                              int var6 = var4 - 101;
                              this.drawEntity(
                                 var6, var1 + this.entityData[9403 + var6], var2 + this.entityData[9425 + var6], this.entityData[9491 + var6], (byte)108
                              );
                           } else if (var3 >= 2 || !isBuildingFootprintCode(var4) && tribeOfOccupantCode(var4) != 3) {
                              if (var3 == 0 && var4 <= -114 && var4 > -117) {
                                 if (this.trapScanRow >= 94 + var7 && this.trapScanRow <= 100 + var7) {
                                    this.drawSpriteAt(75, var1, var2, this.trapScanRow - (100 + var7) + 6);
                                 } else if ((this.trapScanRow + this.renderCol + this.renderRow) % 10 < 5) {
                                    this.a_int_int_int_void(this.terrainGrid[var7][var8] & 255, var1, var2);
                                 }
                              }
                           } else {
                              this.drawTile(var4, (byte)1);
                           }
                        } else {
                           this.drawTile(var5, (byte)1);
                        }
                     }

                     if (this.renderCol >= 0
                        && this.renderCol < this.screenColsTiles
                        && this.renderRow >= 0
                        && this.tileEntityDraw[this.renderRow][this.renderCol] > 0) {
                        this.drawEntitySprite(this.tileEntityDraw[this.renderRow][this.renderCol]);
                        this.tileEntityDraw[this.renderRow][this.renderCol] = 0;
                     }

                     if (var3 == 1) {
                        this.b_int_int_int_void(12, var1 - 2, var2 - 4);
                     } else if (var3 == 2) {
                        screenBufferGraphics[this.screenBufferIndex[1]].setColor(0);
                        if (var8 != 0
                           && var8 != this.mapWidth - 1
                           && var7 != 0
                           && var7 != this.mapHeight - 1
                           && this.effectBitAt(var8 - 1, var7) == 2
                           && this.effectBitAt(var8 + 1, var7) == 2
                           && this.effectBitAt(var8, var7 - 1) == 2
                           && this.effectBitAt(var8, var7 + 1) == 2) {
                           fillRect(screenBufferGraphics[this.screenBufferIndex[1]], var1, var2, 22, 16);
                        } else {
                           this.b_int_int_int_void(11, var1 - 3, var2 - 2);
                        }
                     }

                     if (this.renderRow == this.cursorRow && this.renderCol == this.cursorCol) {
                        this.cursorVisibility = var3;
                     }
                  }
               }

               if ((this.placeConfirmFlagL || this.placeConfirmFlagN) && this.homePosition[1] - this.camTileY == this.renderRow) {
                  this.drawSpriteAt(105, (this.homePosition[0] - this.camTileX) * 22, var2, this.animCycle);
               }

               var2 += 16;
            }
         }

         this.updateEffects();
         this.updateBuildAnims();
         if (this.scrollXPx == 0 && this.scrollYPx == 0) {
            if (this.placeConfirmFlagN) {
               this.drawSpriteAt(30, 218, 11, this.animCycle);
               return;
            }

            if (this.placeConfirmFlagL) {
               this.drawSpriteAt(31, 218, 11, this.animCycle);
            }
         }
      } catch (Exception var23) {
      }
   }

   // $VF: renamed from: v () void
   private void drawWorldMarkers() {
      for (int var1 = 1; var1 <= 60; var1++) {
         this.entityData[8871 + var1] = (byte)(this.entityData[8871 + var1] & 254);
         if (this.entityData[9176 + var1] > 0) {
            this.entityData[9176 + var1] = 5;
         }
      }

      byte var5 = 0;

      for (int var2 = 0; var2 < 5; var2++) {
         if (this.aiTargets[var5][var2][2] >= 0
            && this.aiTargets[var5][var2][3] == 1
            && this.aiTargets[var5][var2][0] >= this.camTileX - 1
            && this.aiTargets[var5][var2][0] <= this.camTileX + this.screenColsTiles + 1
            && this.aiTargets[var5][var2][1] >= this.camTileY - 1
            && this.aiTargets[var5][var2][1] <= this.camTileY + this.screenRowsTiles + 1) {
            for (int var3 = 0; var3 < 9; var3++) {
               if (this.effectBitAt(
                     this.aiTargets[var5][var2][0] + this.fileAData[this.recordStarts[141] + 0 + var3],
                     this.aiTargets[var5][var2][1] + this.fileAData[this.recordStarts[141] + 9 + var3]
                  )
                  == 0) {
                  this.drawSpriteAt(
                     68,
                     (this.aiTargets[var5][var2][0] - this.camTileX + this.fileAData[this.recordStarts[141] + 0 + var3]) * 22,
                     (this.aiTargets[var5][var2][1] - this.camTileY + this.fileAData[this.recordStarts[141] + 9 + var3]) * 16,
                     this.animCycle
                  );
               }
            }
         }

         if (var2 == 4 && var5 == 0) {
            var2 = 0;
            var5 = 1;
         }
      }

      for (int var6 = 0; var6 < 30; var6++) {
         byte var7 = this.pendingOrders[0][2][var6];
         byte var4 = this.pendingOrders[0][3][var6];
         if (this.pendingOrders[0][1][var6] == 3
            && this.pendingOrders[0][0][var6] != 0
            && var7 >= this.camTileX - 1
            && var7 <= this.camTileX + this.screenColsTiles + 1
            && var4 >= this.camTileY - 1
            && var4 <= this.camTileY + this.screenRowsTiles + 1
            && this.effectBitAt(var7, var4) == 0) {
            this.drawSpriteAt(68 + (1 - this.pendingOrders[0][4][var6]) * 5, (var7 - this.camTileX - 0) * 22, (var4 - this.camTileY - 0) * 16, this.animCycle);
         }
      }
   }

   // $VF: renamed from: w () void
   private void drawMinimapViewport() {
      if (this.dragSelecting) {
         this.minimapRectX = this.dragStartTileX - this.camTileX <= this.cursorCol ? this.dragStartTileX - this.camTileX : this.cursorCol;
         this.selRectX = this.minimapRectX + this.camTileX - 1;
         this.minimapRectY = this.dragStartTileY - this.camTileY <= this.cursorRow ? this.dragStartTileY - this.camTileY : this.cursorRow;
         this.selRectY = this.minimapRectY + this.camTileY - 1;
         this.minimapRectX *= 22;
         this.minimapRectY *= 16;
         int var1 = this.camTileX + this.cursorCol - this.dragStartTileX;
         int var2 = this.camTileY + this.cursorRow - this.dragStartTileY;
         if (var1 < 0) {
            var1 *= -1;
         }

         this.selRectW = var1 + 1;
         if (var2 < 0) {
            var2 *= -1;
         }

         this.selRectH = var2 + 1;
         var1 = (var1 + 1) * 22;
         var2 = (var2 + 1) * 16;
         screenBufferGraphics[this.screenBufferIndex[1]].setColor(0);
         drawRect(screenBufferGraphics[this.screenBufferIndex[1]], 11 + this.minimapRectX + 1, 8 + this.minimapRectY + 1, var1, var2);
         screenBufferGraphics[this.screenBufferIndex[1]].setColor(1105252);
         drawRect(screenBufferGraphics[this.screenBufferIndex[1]], 11 + this.minimapRectX, 8 + this.minimapRectY, var1, var2);
      }

      byte var8;
      if (tribeOfBuilding(var8 = this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol]) == 0) {
         int var10 = (this.entityData[8383 + -var8] - this.camTileX) * 22;
         int var3 = (this.entityData[8444 + -var8] - this.camTileY) * 16;
         int var4 = this.fileAData[this.recordStarts[138] + 104 + this.entityData[8749 + -var8]] * 22;
         int var5 = this.fileAData[this.recordStarts[138] + 130 + this.entityData[8749 + -var8]] * 16;
         this.drawSpriteAt(6, var10, var3, this.animFrame);
         this.drawSpriteAt(7, var10 + var4, var3, this.animFrame);
         this.drawSpriteAt(8, var10, var3 + var5, this.animFrame);
         this.drawSpriteAt(9, var10 + var4, var3 + var5, this.animFrame);
      }
   }

   // $VF: renamed from: c (int, int) void
   private void drawCursor(int var1, int var2) {
      if (this.orderCursorType != 0) {
         this.drawSpriteAt(34 + this.orderCursorType, (this.orderTileX - this.camTileX) * 22, (this.orderTileY - this.camTileY) * 16, this.animFrame);
         if (this.animFrame == 5) {
            this.orderCursorType = 0;
         }
      }

      byte var4 = this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol];
      if (this.interactMode != 2) {
         byte var5 = -1;
         byte var3 = 32;
         if (this.interactMode == 1) {
            if (this.selectedVillagerId != 0) {
               var5 = 1;
            } else if (this.fireHeldCount != -1) {
               var5 = 0;

               for (int var9 = 0; var9 < 50; var9++) {
                  if (this.villagerGroups[this.fireHeldCount][var9] != 0) {
                     var5++;
                  }
               }
            }

            var3 = (byte)(this.cursorVisibility != 0
                  || tribeOfBuilding(var4) != 1 && !isObjectId(var4) && (tribeOfEntity(var4) != 1 || (this.entityData[7979 + var4] & 2) != 0)
               ? 33
               : 34);
         } else if (this.interactMode == 3) {
            byte var10000;
            if (this.placementType == 100) {
               this.placementValid = true;
               var10000 = 105;
            } else if (this.placementType >= 0 && this.placementType > 3) {
               byte var6 = this.fileAData[this.recordStarts[138] + 104 + this.placementType];
               byte var7 = this.fileAData[this.recordStarts[138] + 130 + this.placementType];
               this.placeGhostX = this.cursorCol - var6 / 2;
               this.placeGhostY = this.cursorRow - var7 / 2;
               this.ghostPx = var1 - var6 / 2 * 22;
               this.ghostPy = var2 - var7 / 2 * 16 - 5 + this.animFrame;
               this.drawTile(1000 + this.placementType + 1, (byte)2);
               this.placementValid = this.a_int_int_int_boolean_boolean(
                  this.placementType, this.camTileX + this.placeGhostX, this.camTileY + this.placeGhostY, true
               );
               var10000 = (byte)(this.placementValid ? 41 : 40);
            } else if ((tribeOfEntity(var4) != 0 || this.entityData[1414 + var4] < 12)
               && (tribeOfEntity(this.nearestEntityId) != 0 || this.entityData[1414 + this.nearestEntityId] < 12)) {
               if (this.placementType == 3) {
                  this.placementValid = true;
                  var10000 = 33;
               } else {
                  this.placementValid = false;
                  var10000 = 32;
               }
            } else {
               this.orderTargetId = this.nearestEntityId;
               this.placeGhostX = this.entityData[0 + this.orderTargetId];
               this.placeGhostY = this.entityData[101 + this.orderTargetId];
               this.placementValid = true;
               var10000 = 33;
            }

            var3 = var10000;
         }

         if (this.cursorMoved && this.animFrame < 2 && var3 <= 34) {
            var3 = (byte)(var3 + 5);
         }

         this.drawSpriteAt(var3, var1, var2, this.animFrame);
         if (var5 > 0) {
            this.textLoader.queueString(var1 + 22, var2 + 8, 0, "" + var5, 1);
            this.textLoader.drawLines(screenBufferGraphics[this.drawBufferIndex]);
         }

         if (this.highlightCount > 0) {
            this.textLoader.queueString(var1 + 22, var2 + 8, 0, "" + this.highlightCount, 1);
            this.textLoader.drawLines(screenBufferGraphics[this.drawBufferIndex]);
         }

         if (this.interactMode == 3) {
            if (this.placementType < 0) {
               this.a_int_int_int_void(659 - this.placementType - 1, var1 + 11 + 5, var2 + 8 + 5);
            } else if (this.placementType <= 3) {
               this.drawSpriteAt(this.placementType + 48, var1, var2, this.animFrame);
            }
         }
      }

      byte var8 = 0;
      if (this.interactMode == 1) {
         if (this.moveOrderFlash && this.orderCursorType != 0) {
            var8 = 47;
            this.cursorVisibility = 0;
         } else if (var4 == -124) {
            var8 = 44;
         } else if (var4 == -125) {
            var8 = 45;
         } else if (tribeOfBuilding(var4) == 0 && this.entityData[8749 + -var4] != 10) {
            var8 = 41;
         } else if (isBuildingFootprintCode(var4)) {
            var8 = (byte)(this.entityData[9833 + (var4 + 61) * -1] == 7 ? 46 : 43);
         } else if (isObjectId(var4) && this.cursorVisibility == 0) {
            var8 = 43;
         }

         if (var8 != 0 && this.cursorVisibility != 2) {
            this.drawSpriteAt(var8, var1, var2, this.animFrame);
         }
      }
   }

   // $VF: renamed from: a (byte, int, int) void
   private void layoutHudElement(byte var1, int var2, int var3) {
      this.hudPanelId = var1;

      try {
         boolean var4 = this.refreshHudPanel();
         if (this.hudPanelDirty[var1]) {
            this.hudPanelDirty[17] = true;
            this.hudButtonState = 0;
         } else if (var4) {
            this.hudButtonState = 1;
         } else {
            if (this.fileAData[this.recordStarts[195] + 80 + var1] != 1) {
               return;
            }

            this.hudButtonState = 2;
         }

         this.drawBufferIndex = this.screenBufferIndex[this.fileAData[this.recordStarts[195] + 0 + var1]];
         this.hudElemIndex = this.fileAData[this.recordStarts[195] + 60 + var1];
         byte var5 = this.fileAData[this.recordStarts[195] + 40 + var1];
         boolean var6 = false;

         for (int var7 = this.pd0ShortTables[0][var5]; var7 < this.pd0ShortTables[0][var5] + this.pd0ByteTables[0][var5]; var7++) {
            int var8 = (this.pd0ByteTables[3][var7] & 255) >> 6;
            int var9 = this.pd0ByteTables[3][var7] & 7;
            this.hudSpriteId = this.pd0ShortTables[1][var7] & 4095;
            if (var8 >= this.hudButtonState || var6) {
               this.hudElemValue = 0;
               if (var6) {
                  var6 = false;
                  if (this.hudElemSeen[this.hudElemIndex] == 0 && this.hudButtonState > 0 || this.hudElemSeen[this.hudElemIndex] < 0) {
                     this.hudElemIndex++;
                     continue;
                  }

                  this.hudElemValue = this.hudValues[this.hudElemIndex];
                  this.hudElemSeen[this.hudElemIndex] = 0;
                  this.hudElemIndex++;
               } else if (var8 == 1) {
                  var6 = true;
                  if (this.hudElemSeen[this.hudElemIndex] == 0 && this.hudButtonState > 0
                     || this.hudSpriteId == 0
                     || var8 < this.hudButtonState
                     || this.hudElemSeen[this.hudElemIndex] < 0) {
                     continue;
                  }
               } else if (var8 == 2 && this.hudElemSeen[this.hudElemIndex] < 0) {
                  continue;
               }

               int var10 = (this.pd0ShortTables[1][var7] >> 14 & 3) << 7;
               int var11 = (this.pd0ShortTables[1][var7] >> 12 & 3) << 7;
               this.hudDrawX = this.pd0ByteTables[1][var7];
               this.hudDrawY = this.pd0ByteTables[2][var7];
               if (var1 >= 13 && var1 <= 16) {
                  this.hudDrawX += 120;
                  this.hudDrawY += 110;
               } else {
                  this.hudDrawX += var2;
                  this.hudDrawY += var3;
               }

               this.hudDrawX += var10;
               this.hudDrawY += var11;
               if (var7 == 1011) {
                  this.hudSpriteId = this.pd0ShortTables[1][1007] & 4095;
                  this.hudElemValue = this.hudValues[3];
                  this.drawHudElement(var9, 1007);
                  this.hudSpriteId = 0;
               }

               if (var7 == 1010) {
                  this.drawMenuItem(var7, this.hudButtonState, this.hudDrawY);
               } else if (var7 == 1137) {
                  this.c_int_int_int_void(var7, this.hudDrawX, this.hudDrawY);
               } else if (var9 == 0 && (this.pd0ByteTables[3][var7] & 255) >> 6 == 3) {
                  screenBufferGraphics[this.drawBufferIndex].setColor(0);
                  byte var12 = 99;
                  if (var7 == 1030) {
                     var12 = this.entityData[6767 + this.inspectedVillagerId];
                  }

                  if (this.hudElemValue >= 0) {
                     int var13 = this.pd0ByteTables[23][this.hudSpriteId] * this.hudElemValue / var12;
                     fillRect(
                        screenBufferGraphics[this.drawBufferIndex],
                        this.pd0ByteTables[1][var7] + var13 + 1,
                        this.hudDrawY,
                        this.pd0ByteTables[23][this.hudSpriteId] - var13 + var11,
                        4
                     );
                  }
               } else if (var9 == 3 && this.hudSpriteId >= 3 && this.hudSpriteId <= 4) {
                  if (this.dialogId != -1) {
                     if (this.hudPanelDirty[var1] || this.dialogId != this.tickerLastDialogId) {
                        this.textLoader
                           .wrapText(
                              this.hudLastDrawX,
                              this.hudLastDrawY,
                              this.pd0ByteTables[23][this.pd0ShortTables[1][var7 - 1] & 4095] * 4,
                              this.pd0ByteTables[24][this.pd0ShortTables[1][var7 - 1] & 4095] * 4,
                              this.dialogId,
                              (this.pd0ByteTables[3][var7] & 255) >> 6,
                              this.hudSpriteId - 3
                           );
                     }

                     if (this.tickerLastDialogId != this.dialogId) {
                        this.tickerLastDialogId = this.dialogId;
                        this.scrollOffsetY = 0;
                     }

                     this.tickerScrollShown = this.scrollOffsetY;
                     this.textLoader.drawTextPage(screenBufferGraphics[this.drawBufferIndex], this.scrollOffsetY);
                  }
               } else if (this.hudSpriteId != 0) {
                  this.drawHudElement(var9, var7);
               }
            } else if (var8 == 1) {
               var6 = true;
            }
         }

         this.textLoader.drawLines(screenBufferGraphics[this.drawBufferIndex]);
         this.hudPanelDirty[var1] = false;
      } catch (Exception var14) {
      }
   }

   // $VF: renamed from: d (int, int) void
   private void drawHudElement(int var1, int var2) {
      if (var1 == 0) {
         this.b_int_int_int_void(this.hudSpriteId, this.hudDrawX, this.hudDrawY);
         this.hudLastDrawX = this.hudDrawX;
         this.hudLastDrawY = this.hudDrawY;
      } else if (var1 == 1) {
         this.drawHudNumbers(var2);
      } else if (var1 == 2) {
         if ((!this.menuScrollUp || this.hudSpriteId != 4 && this.hudSpriteId != 94)
            && (!this.menuScrollDown || this.hudSpriteId != 5 && this.hudSpriteId != 95)
            && (this.menuListChanged || this.hudSpriteId != 1)
            && (this.menuSelectionChanged || this.hudSpriteId != 2)) {
            if (var2 - 1 == 1201) {
               screenBufferGraphics[menuBufferIdx].setColor(1105252);
               drawRect(
                  screenBufferGraphics[menuBufferIdx],
                  11 + this.hudDrawX + (this.camTileX - this.minimapOriginX) * 5,
                  8 + this.hudDrawY + (this.camTileY - this.minimapOriginY + this.minimapRowOffset) * 4,
                  this.screenColsTiles * 5 - 1,
                  this.screenRowsTiles * 4 - 1
               );
               this.drawSpriteAt(
                  this.hudSpriteId,
                  this.hudDrawX + (this.cursorCol + this.camTileX - this.minimapOriginX) * 5 - 11,
                  this.hudDrawY + (this.cursorRow + this.camTileY - this.minimapOriginY + this.minimapRowOffset) * 4 - 8,
                  this.animCycle / this.animSpeedFactor
               );
            } else {
               this.drawSpriteAt(this.hudSpriteId, this.hudDrawX, this.hudDrawY, this.animCycle / this.animSpeedFactor);
            }

            this.screenDrawn = true;
         }
      } else if (var1 == 3 && this.hudSpriteId == 11) {
         drawRegionSafe(
            screenBufferGraphics[menuBufferIdx],
            screenBuffers[this.screenBufferIndex[1]],
            0,
            0,
            this.pd0ByteTables[23][this.pd0ShortTables[1][1181] & 4095] * 4,
            this.pd0ByteTables[24][this.pd0ShortTables[1][1181] & 4095] * 4,
            11 + this.hudDrawX,
            8 + this.hudDrawY,
            0
         );
      } else if (var1 == 3 && this.hudSpriteId == 10) {
         try {
            this.c_byte_void(this.storySpeaker);
            screenBufferGraphics[menuBufferIdx].drawImage(this.spritePacks[2][0], (11 + this.hudDrawX) * 1, (8 + this.hudDrawY) * 1, 3);
         } catch (Exception var4) {
         }
      } else if (var1 == 3 && this.hudSpriteId <= 2) {
         this.drawHudLabels(var2);
      } else if (var1 == 3 && this.hudSpriteId <= 6) {
         if (var2 - 1 == 1128 && (this.messageFlags[0] == 3 || this.messageFlags[0] == 1)) {
            this.textLoader
               .queueLineParts(
                  this.hudDrawX,
                  this.hudDrawY,
                  (this.pd0ByteTables[3][var2] & 255) >> 6,
                  108 + this.entityData[6868 + this.focusTable[2][0]],
                  this.hudElemValue + (this.entityData[1515 + this.focusTable[2][0]] == 0 ? 0 : 1),
                  -1,
                  this.hudSpriteId - 5
               );
         } else {
            this.textLoader.queueLine(this.hudDrawX, this.hudDrawY, (this.pd0ByteTables[3][var2] & 255) >> 6, this.hudElemValue, this.hudSpriteId - 5);
         }
      } else {
         if (var1 == 4) {
            if (this.hudSpriteId != 1) {
               int var3 = this.hudSpriteId;
               this.storyFrameX = 11 + this.hudDrawX + (this.pd0ByteTables[21][var3] & 127);
               this.storyFrameY = 8 + this.hudDrawY + this.pd0ByteTables[22][var3];
               this.storyFrameW = this.pd0ByteTables[23][var3];
               this.storyFrameH = this.pd0ByteTables[24][var3];
               setClipRect(screenBufferGraphics[this.drawBufferIndex], this.storyFrameX, this.storyFrameY, this.storyFrameW, this.storyFrameH);
               return;
            }

            this.storyFrameX = 0;
            this.storyFrameY = 0;
            this.storyFrameW = 528;
            this.storyFrameH = 704;
            setClipRect(screenBufferGraphics[this.drawBufferIndex], this.storyFrameX, this.storyFrameY, this.storyFrameW, this.storyFrameH);
         }
      }
   }

   // $VF: renamed from: j (int) void
   private void drawHudNumbers(int var1) {
      if (var1 - 1 == 1118) {
         byte var7 = this.menuItemIds[this.menuSelectedIndex];
         if (this.activeHudPanel != 6) {
            var7 = (byte)(var7 == 16 ? this.entityData[8749 + this.menuTargetId] : var7 + -6 + 20);
         }

         byte var8 = this.fileAData[this.recordStarts[138] + 468 + var7];
         int var9 = 0;

         for (int var6 = 0; var6 < 3; var6++) {
            if (this.fileAData[this.recordStarts[138] + (10 + var6) * 26 + var7] != 0) {
               int var10 = this.hudDrawY
                  - this.fileAData[this.recordStarts[283] + 2] * (var8 - 1) / 2
                  + (this.fileAData[this.recordStarts[283] + 2] + 2) * var9;
               var9++;
               this.a_int_int_int_void(627 + var6, this.hudDrawX - 10 + 3, var10);
               this.textLoader
                  .queueString(
                     this.hudDrawX + 19,
                     var10,
                     (this.pd0ByteTables[3][var1] & 255) >> 6,
                     String.valueOf(this.fileAData[this.recordStarts[138] + (10 + var6) * 26 + var7]),
                     0
                  );
            }
         }
      } else if (var1 - 1 == 1107) {
         int var2 = this.menuItemIds[this.menuSelectedIndex] + 12;
         int var3 = 0;

         for (int var5 = 0; var5 < 3; var5++) {
            if (this.fileAData[this.recordStarts[138] + (10 + var5) * 26 + var2] != 0) {
               int var4 = this.hudDrawX - 34 * (this.fileAData[this.recordStarts[138] + 468 + var2] - 1) / 2 + 30 * var3;
               var3++;
               this.a_int_int_int_void(627 + var5, var4 - 2 - 4, this.hudDrawY);
               this.textLoader
                  .queueString(
                     var4 + 15,
                     this.hudDrawY,
                     (this.pd0ByteTables[3][var1] & 255) >> 6,
                     String.valueOf(this.fileAData[this.recordStarts[138] + (10 + var5) * 26 + var2]),
                     0
                  );
            }
         }

         this.screenPanelY[8] = 0;
         if (this.entityData[8749 + this.menuTargetId] == 6 && this.menuItemIds[this.menuSelectedIndex] != 1) {
            this.screenPanelY[8] = -20;
         }
      } else {
         switch (var1 - 1) {
            case 1073:
               this.hudElemValue /= 21;
            default:
               this.a_int_int_int_void(this.hudSpriteId + this.hudElemValue, this.hudDrawX, this.hudDrawY);
         }
      }
   }

   // $VF: renamed from: k (int) void
   private void drawHudLabels(int var1) {
      switch (var1 - 1) {
         case 1043:
         case 1047:
         case 1051:
         case 1059:
         case 1063:
            if (this.hudElemValue < 0 || this.fileAData[this.recordStarts[126] + this.hudElemValue] == -1) {
               return;
            }

            this.hudElemValue = this.entityData[this.offsetTable[this.fileAData[this.recordStarts[126] + this.hudElemValue]] + this.inspectedVillagerId]
               + this.fileAData[this.recordStarts[127] + this.hudElemValue];
         case 1044:
         case 1045:
         case 1046:
         case 1048:
         case 1049:
         case 1050:
         case 1052:
         case 1053:
         case 1054:
         case 1056:
         case 1057:
         case 1058:
         case 1060:
         case 1061:
         case 1062:
         case 1064:
         case 1065:
         case 1066:
         default:
            break;
         case 1055:
            this.hudElemValue = (9 - this.hudElemValue) * 2 + 1;
            break;
         case 1067:
            if (this.hudElemValue == 0 || this.entityData[3535 + this.inspectedVillagerId] == 0 || this.entityData[3535 + this.inspectedVillagerId] == 15) {
               return;
            }
      }

      switch (var1 - 1) {
         case 1015:
            this.textLoader
               .queueString(
                  this.hudDrawX,
                  this.hudDrawY,
                  (this.pd0ByteTables[3][var1] & 255) >> 6,
                  this.hudElemValue + "/" + this.tribeStats[0][16],
                  this.hudSpriteId - 1
               );
            return;
         default:
            this.textLoader
               .queueString(this.hudDrawX, this.hudDrawY, (this.pd0ByteTables[3][var1] & 255) >> 6, String.valueOf(this.hudElemValue), this.hudSpriteId - 1);
      }
   }

   // $VF: renamed from: c () boolean
   private boolean refreshHudPanel() {
      this.hudContentChanged = false;
      if (this.hudPanelId == 0) {
         for (int var1 = 0; var1 < 5; var1++) {
            if (this.tribeStats[0][this.fileAData[this.recordStarts[146] + var1]] != this.hudValues[this.fileAData[this.recordStarts[195] + 60 + 0] + var1]) {
               this.hudContentChanged = true;
               this.hudValues[this.fileAData[this.recordStarts[195] + 60 + 0] + var1] = this.tribeStats[0][this.fileAData[this.recordStarts[146] + var1]];
               this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + 0] + var1] = 1;
            }
         }
      } else if (this.hudPanelId == 1 || this.hudPanelId == 9) {
         this.buildVillagerMenu();
      } else if (this.hudPanelId == 2 || this.hudPanelId == 5) {
         this.refreshSelectionInfoHud();
      } else if (this.hudPanelId == 4) {
         this.refreshVillagerInfoHud();
      } else if (this.hudPanelId == 6) {
         this.refreshPanel6();
      } else if (this.hudPanelId == 7 || this.hudPanelId == 8) {
         if (this.hudValues[46] != this.menuItemIds[this.menuSelectedIndex] || this.hudPanelDirty[this.hudPanelId]) {
            this.hudValues[46] = this.menuItemIds[this.menuSelectedIndex];
            this.hudContentChanged = true;
            if (this.menuItemIds[this.menuSelectedIndex] < 10) {
               this.hudValues[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId]] = this.menuItemIds[this.menuSelectedIndex];
               this.hudValues[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + 1] = this.fileAData[this.recordStarts[138]
                  + 338
                  + this.menuItemIds[this.menuSelectedIndex]
                  + 12];
               this.hudValues[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + 2] = this.fileAData[this.recordStarts[138]
                  + 364
                  + this.menuItemIds[this.menuSelectedIndex]
                  + 12];
            }
         }
      } else if (this.hudPanelId == 10) {
         if (this.screenId == 16 && this.tickerScrollShown != this.scrollOffsetY) {
            this.hudElemSeen[53] = 1;
         }
      } else if (this.hudPanelId == 15) {
         this.hudValues[73] = (short)(98 + this.endScreenPhase);
         if (this.tickerScrollShown != this.scrollOffsetY) {
            this.hudElemSeen[72] = 1;
            this.hudContentChanged = true;
         }
      } else if (this.hudPanelId == 12) {
         if (this.subjectEntityId >= 51 && this.missionIndex <= 2) {
            this.subjectEntityId = 51;
         }

         this.hudValues[57] = this.tribeStats[0][17] == this.subjectEntityId ? 104 : (short)(108 + this.entityData[6868 + this.subjectEntityId]);
         if (this.screenId == 17 && this.storyStepSlot == 3 && (this.storySceneId == 35 || this.storySceneId == 50 || this.storySceneId == 54)) {
            this.hudValues[57] = 105;
         }

         this.hudValues[58] = this.entityData[2727 + this.subjectEntityId];
         if (this.tickerScrollShown != this.scrollOffsetY) {
            this.hudElemSeen[56] = 1;
            this.hudContentChanged = true;
         }
      } else if (this.hudPanelId == 13) {
         this.hudValues[62] = (short)(82 + (this.missionIndex == 7 ? 21 : this.fileAData[this.recordStarts[248] + this.missionIndex]));
         this.hudValues[63] = this.storyStepIndex;
         if (this.tickerScrollShown != this.scrollOffsetY) {
            this.hudElemSeen[61] = 1;
            this.hudContentChanged = true;
         }
      } else if (this.hudPanelId == 14) {
         this.menuListChanged = this.minimapOriginX != 0;
         this.menuSelectionChanged = this.minimapOriginX + minimapViewW < this.mapWidth;
         this.menuScrollUp = this.minimapOriginY == 0;
         this.menuScrollDown = this.minimapOriginY + minimapViewH >= this.mapHeight;
         if (this.hudValues[66] != this.minimapOriginX || this.hudValues[71] != this.minimapOriginY) {
            this.hudValues[66] = (short)this.minimapOriginX;
            this.hudValues[71] = (short)this.minimapOriginY;
            this.drawMinimapOverlay();
         }

         this.hudElemSeen[66] = 1;
         this.scrollDirX = this.scrollDirY = 0;
      } else if (this.hudPanelId == 17) {
         this.refreshSoftkeyPanel();
      } else if (this.hudPanelId == 16) {
         if (this.tickerScrollShown != this.scrollOffsetY) {
            this.hudElemSeen[76] = 1;
            this.hudContentChanged = true;
         }
      } else if (this.hudPanelId == 18 || this.hudPanelId == 19) {
         for (int var2 = 0; var2 < this.fileAData[this.recordStarts[307] + this.endScreenPhase]; var2++) {
            this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + var2] = (byte)(var2 == this.menuCursor ? 1 : 0);
         }
      }

      return this.hudContentChanged;
   }

   // $VF: renamed from: x () void
   private void buildVillagerMenu() {
      this.hudElemSeen[47] = this.messageType;

      for (int var1 = 0; var1 < 2; var1++) {
         this.hudElemSeen[48 + var1] = (byte)(this.fireHeldCount == 2 + var1 ? 2 : (this.villagerGroups[2 + var1][0] > 0 ? 1 : 0));
      }

      this.hudElemSeen[50] = (byte)(41 + this.messageFlags[0]);
      this.hudElemSeen[51] = this.fileAData[this.recordStarts[304] + this.messageFlags[0]];

      for (int var2 = this.fileAData[this.recordStarts[195] + 60 + 9]; var2 < this.fileAData[this.recordStarts[195] + 60 + 9] + 5; var2++) {
         if (this.hudElemSeen[var2] != -1) {
            if (this.hudValues[var2] != this.hudElemSeen[var2]) {
               this.hudValues[var2] = this.hudElemSeen[var2];
               this.hudElemSeen[var2] = 1;
               this.hudContentChanged = true;
            } else {
               this.hudElemSeen[var2] = 0;
            }
         }
      }
   }

   // $VF: renamed from: y () void
   private void refreshVillagerInfoHud() {
      for (int var1 = 0; var1 < 23; var1++) {
         if (this.entityData[this.offsetTable[this.fileAData[this.recordStarts[147] + var1]] + this.inspectedVillagerId]
            != this.hudValues[this.fileAData[this.recordStarts[195] + 60 + 4] + var1]) {
            this.hudContentChanged = true;
            this.hudValues[this.fileAData[this.recordStarts[195] + 60 + 4] + var1] = this.entityData[this.offsetTable[this.fileAData[this.recordStarts[147]
                  + var1]]
               + this.inspectedVillagerId];
            this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + 4] + var1] = 1;
         }
      }

      if (this.hudElemSeen[11] == 1 || this.hudElemSeen[12] == 1) {
         this.hudElemSeen[11] = this.hudElemSeen[12] = 1;
      }

      if (this.entityData[3636 + this.inspectedVillagerId] == 0) {
         this.hudValues[29] = 0;
      }

      this.hudValues[14] = (short)(108 + this.entityData[6868 + this.inspectedVillagerId]);
   }

   // $VF: renamed from: z () void
   private void refreshSelectionInfoHud() {
      if (this.hudValues[4] > 0) {
         this.dialogId = this.hudValues[4];
         this.hudElemSeen[5] = this.hudElemSeen[3] = -1;
         this.hudContentChanged = true;
      } else {
         if (this.hudValues[5] != this.menuItemIds[this.menuSelectedIndex] || this.hudPanelDirty[this.hudPanelId]) {
            this.hudValues[5] = this.menuItemIds[this.menuSelectedIndex];
            this.hudElemSeen[5] = this.hudElemSeen[3] = 1;
            this.hudValues[3] = this.menuItemIds[this.menuSelectedIndex] >= 10
               ? (short)(21 + this.menuItemIds[this.menuSelectedIndex] - 10)
               : (
                  this.menuItemIds[this.menuSelectedIndex] >= 0
                     ? (short)(12 + this.menuItemIds[this.menuSelectedIndex])
                     : (
                        this.menuItemActivated
                           ? 41
                           : (!this.chiefMode && this.selectionChanged ? 29 : (short)(108 + this.entityData[6868 + -this.menuItemIds[this.menuSelectedIndex]]))
                     )
               );
            this.hudElemSeen[4] = -1;
            this.hudContentChanged = true;
         }
      }
   }

   // $VF: renamed from: A () void
   private void refreshPanel6() {
      if (this.hudValues[36] != this.menuItemIds[this.menuSelectedIndex] || this.hudPanelDirty[this.hudPanelId]) {
         this.dialogId = 0 + this.menuItemIds[this.menuSelectedIndex];
         this.hudValues[36] = this.menuItemIds[this.menuSelectedIndex];
         this.hudElemSeen[36] = this.hudElemSeen[38] = this.hudElemSeen[34] = 1;
         this.hudElemSeen[35] = this.hudElemSeen[37] = 1;
         this.hudContentChanged = true;
      }

      if (this.tickerScrollShown != this.scrollOffsetY) {
         this.hudElemSeen[38] = 1;
         this.hudContentChanged = true;
      }

      this.hudValues[34] = (short)(0 + this.menuItemIds[this.menuSelectedIndex]);
   }

   // $VF: renamed from: B () void
   private void refreshSoftkeyPanel() {
      if (this.mode == 1) {
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId]] = (byte)(this.interactMode != 3
               && this.placeMode != 4
               && (
                  this.menuTargetId <= 0
                     || this.entityData[8749 + this.menuTargetId] != 0
                     || this.menuItemIds[this.menuSelectedIndex] < 0
                     || this.menuItemIds[this.menuSelectedIndex] > 5
               )
            ? (this.interactMode != 4 && this.interactMode != 2 ? 1 : 0)
            : 2);
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + 1] = (byte)(this.menuItemActivated
            ? 2
            : (this.interactMode != 1 && this.fireHeldCount == -1 && (this.activeHudPanel == 1 || this.activeHudPanel == 9) ? 1 : 0));
      } else if (this.screenId == 32) {
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId]] = 2;
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + 1] = 3;
      } else {
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId]] = this.fileAData[this.recordStarts[153] + 116 + this.screenId];
         this.hudElemSeen[this.fileAData[this.recordStarts[195] + 60 + this.hudPanelId] + 1] = this.fileAData[this.recordStarts[153] + 145 + this.screenId];
      }

      for (int var1 = this.fileAData[this.recordStarts[195] + 60 + 17]; var1 < this.fileAData[this.recordStarts[195] + 60 + 17] + 2; var1++) {
         if (this.hudValues[var1] != this.hudElemSeen[var1]) {
            this.hudValues[var1] = this.hudElemSeen[var1];
            this.hudElemSeen[var1] = 1;
            this.hudContentChanged = true;
         } else {
            this.hudElemSeen[var1] = 0;
         }
      }
   }

   // $VF: renamed from: a (int, byte, int) void
   private void drawMenuItem(int var1, byte var2, int var3) {
      boolean var4 = false;

      for (int var10 = 0; var10 < this.menuVisibleRows; var10++) {
         this.spriteTableIndex = var1;
         int var7 = menuRowHeightPx * var10;
         byte var5 = 1;
         if (this.menuScrollTop + var10 == this.menuSelectedIndex) {
            var5 = 0;
         } else if (var2 == 2) {
            continue;
         }

         this.b_int_int_int_void(this.pd0ShortTables[1][var1] & 4095, this.menuWidthPx + var7, var3 - 1);
         this.a_int_int_int_void(548 + var5, this.menuWidthPx + var7, var3);
         byte var6;
         Game var10000;
         byte var10001;
         int var10002;
         int var10003;
         int var10004;
         if ((var6 = this.menuItemIds[this.menuScrollTop + var10]) < 0) {
            this.drawEntity(-var6, this.menuWidthPx + var7 - -1, var3 + 8 + 2 + 3, -8, (byte)29);
            if (!this.selectionChanged || this.chiefMode || this.menuScrollTop + var10 != this.menuSelectedIndex) {
               continue;
            }

            var10000 = this;
            var10001 = 18;
            var10002 = this.menuWidthPx + var7;
            var10003 = var3;
            var10004 = this.animCycle;
         } else {
            if (var6 <= 8) {
               this.a_int_int_int_void(659 + var6, this.menuWidthPx + var7, var3);
               byte var8 = 0;
               byte var9 = 0;
               if (this.entityData[8749 + this.menuTargetId] == 6) {
                  var8 = -1;
                  var9 = 6;
               }

               this.textLoader
                  .queueString(
                     this.menuWidthPx + var7 + 19 + var8 + 6, var3 + 5 + var9 + 2, 1, String.valueOf(this.menuItemValues[var10 + this.menuScrollTop]), 0
                  );
               if (this.entityData[8749 + this.menuTargetId] != 6
                  && this.menuScrollTop + var10 < this.firstWeaponIndex
                  && this.menuScrollTop + var10 == this.menuSelectedIndex) {
                  this.drawSpriteAt(3, this.menuWidthPx + var7 + 6, var3 + 2 + 2, this.animCycle);
                  this.screenDrawn = true;
               }
               continue;
            }

            var10000 = this;
            var10001 = var6;
            var10002 = this.menuWidthPx + var7;
            var10003 = var3;
            var10004 = this.menuScrollTop + var10 == this.menuSelectedIndex ? this.animCycle : 0;
         }

         var10000.drawSpriteAt(var10001, var10002, var10003, var10004);
      }

      if (this.menuListChanged) {
         this.drawSpriteAt(1, this.menuWidthPx - 9, var3 + 8, this.animFrame);
      }

      if (this.menuSelectionChanged) {
         this.drawSpriteAt(2, this.menuWidthPx + this.menuHeightPx - 9 + 9, var3 + 8, this.animFrame);
      }
   }

   // $VF: renamed from: c (int, int, int) void
   private void c_int_int_int_void(int var1, int var2, int var3) {
      int var6 = this.fileAData[this.recordStarts[283] + 2] + 6;
      int var7 = 0;
      this.menuItemCount = this.fileAData[this.recordStarts[152] + this.screenId + 1] - this.fileAData[this.recordStarts[152] + this.screenId];
      int var4 = var2 + this.pd0ByteTables[23][this.pd0ShortTables[1][var1] & 4095] / 2 * 4;
      int var5 = var3 - this.menuItemCount * var6 / 2 + var6 / 2;

      for (int var8 = 0; var8 < this.menuItemCount; var8++) {
         if (var8 == this.menuCursor) {
            var7 = var5;
         }

         this.b_int_int_int_void(this.pd0ShortTables[1][var1] & 4095, var2, var5);
         this.textLoader
            .queueLine(var4, var5, 1, 57 + this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + this.screenId] + var8], 0);
         var5 += var6;
      }

      this.textLoader.drawLines(screenBufferGraphics[this.drawBufferIndex]);
      this.drawSpriteAt(2, var4 - 50 - 20, var7 + 4, this.animFrame);
      this.drawSpriteAt(1, var4 + 49 + 20, var7 + 4, this.animFrame);
   }

   // $VF: renamed from: a (int) void
   final void drawLoadProgress(int var1) {
      loadProgress = (byte)(loadProgress + var1);
      if (loadProgress > 98) {
         loadProgress = 98;
      }

      this.loadBarWidths[2] = this.loadBarWidths[3] = this.loadBarWidths[4] = loadProgress;

      for (int var2 = 0; var2 < 5; var2++) {
         screenBufferGraphics[menuBufferIdx].setColor(this.loadBarColors[var2]);
         fillRect(
            screenBufferGraphics[menuBufferIdx],
            70 + this.loadBarXOffsets[var2],
            312 + this.loadBarYOffsets[var2],
            this.loadBarWidths[var2],
            this.loadBarHeights[var2]
         );
      }

      if (!this.loadingDone) {
         this.midlet.splashScreen.showSplash();
         Thread.yield();
      } else {
         this.requestRepaint();
      }
   }

   // $VF: renamed from: c (byte) void
   private void c_byte_void(byte var1) {
      boolean var5 = false;

      try {
         System.gc();
         Thread.yield();
         DataInputStream var8 = new DataInputStream(this.getClass().getResourceAsStream("/pi8"));

         byte var4;
         for (byte var10 = 0; var10 < 12 && (var4 = (byte)(var8.readByte() & 0xFF)) != 255; var10++) {
            short var6;
            byte[] var7 = new byte[var6 = (short)(var4 << 8 | var8.readByte() & 0xFF)];
            var8.read(var7, 0, var6);
            if (var10 == var1) {
               this.spritePacks[2][0] = Image.createImage(var7, 0, var6);
               break;
            }
         }

         var8.close();
      } catch (Exception var9) {
      }
   }

   // $VF: renamed from: C () void
   private void drawMinimap() {
      this.minimapTilesW = this.mapWidth > minimapViewW ? minimapViewW : this.mapWidth;
      this.minimapTilesH = this.mapHeight > minimapViewH ? minimapViewH : this.mapHeight;
      this.drawBufferIndex = 1;
      this.b_int_int_int_void(this.pd0ShortTables[1][1181] & 4095, -11, -8);
      int var3 = 0;
      int var4 = 0;
      boolean var5 = false;

      for (int var6 = 0; var6 < this.minimapTilesH; var6++) {
         int var2 = 0;
         int var1 = -1;

         for (int var7 = 0; var7 <= this.minimapTilesW; var7++) {
            if (var7 == this.minimapTilesW) {
               var5 = true;
            } else {
               this.drawTerrainTile(this.minimapOriginX + var7, this.minimapOriginY + var6);
            }

            if (!var5 && this.minimapPixelColor == var1) {
               var4++;
            } else {
               if (var4 > 0) {
                  screenBufferGraphics[this.drawBufferIndex].setColor(var1);
                  fillRect(screenBufferGraphics[this.drawBufferIndex], var2 * 5 - 11, (this.minimapRowOffset + var3) * 4 - 8, var4 * 5, 4);
                  var4 = 0;
               }

               if (var5) {
                  var5 = false;
               } else {
                  var1 = this.minimapPixelColor;
                  var2 = var7;
                  var3 = var6;
                  var4 = 1;
               }
            }
         }
      }
   }

   // $VF: renamed from: D () void
   private void drawMinimapOverlay() {
      this.drawBufferIndex = menuBufferIdx;
      int var1 = (this.pd0ShortTables[1][1181] >> 12 & 3) << 7;
      this.b_int_int_int_void(this.pd0ShortTables[1][1181] & 4095, 120 + this.pd0ByteTables[1][1181] + 11, 160 + this.pd0ByteTables[2][1181] + 8 + var1);
      drawRegionSafe(
         screenBufferGraphics[menuBufferIdx],
         screenBuffers[this.screenBufferIndex[1]],
         (this.scrollDirX + 1) / 2 * 5,
         (this.scrollDirY + 1) / 2 * 4,
         this.pd0ByteTables[23][this.pd0ShortTables[1][1181] & 4095] * 5 - 5 * this.fileAData[this.recordStarts[148] + this.scrollDirX + 1] - 44,
         this.pd0ByteTables[24][this.pd0ShortTables[1][1181] & 4095] * 5 - 4 * this.fileAData[this.recordStarts[148] + this.scrollDirY + 1] - 32,
         131 + this.pd0ByteTables[1][1181] + (-this.scrollDirX + 1) / 2 * 5,
         168 + this.pd0ByteTables[2][1181] + (-this.scrollDirY + 1) / 2 * 4 + var1,
         0
      );
      screenBufferGraphics[this.screenBufferIndex[1]]
         .drawImage(
            screenBuffers[this.screenBufferIndex[0]], -(120 + this.pd0ByteTables[1][1181] + 11) * 1, -(160 + this.pd0ByteTables[2][1181] + 8 + var1) * 1, 0
         );
      int var2 = (this.scrollDirX + 1 >> 1) * (this.minimapTilesW - 1);
      int var3 = (this.scrollDirY + 1 >> 1) * (this.minimapTilesH - 1);
      this.drawBufferIndex = 1;
      if (this.scrollDirY == 0) {
         screenBufferGraphics[this.drawBufferIndex].setColor(11180356);
         fillRect(screenBufferGraphics[this.drawBufferIndex], var2 * 5 - 11, var3 - 8, 5, minimapViewH * 4);

         for (int var5 = 0; var5 < this.minimapTilesH; var5++) {
            this.drawTerrainTile(this.minimapOriginX + var2, this.minimapOriginY + var5);
            screenBufferGraphics[this.drawBufferIndex].setColor(this.minimapPixelColor);
            fillRect(screenBufferGraphics[this.drawBufferIndex], var2 * 5 - 11, (this.minimapRowOffset + var5) * 4 - 8, 5, 4);
         }
      } else if (this.scrollDirX == 0) {
         for (int var4 = 0; var4 < this.minimapTilesW; var4++) {
            this.drawTerrainTile(this.minimapOriginX + var4, this.minimapOriginY + var3);
            screenBufferGraphics[this.drawBufferIndex].setColor(this.minimapPixelColor);
            fillRect(screenBufferGraphics[this.drawBufferIndex], var4 * 5 - 11, (this.minimapRowOffset + var3) * 4 - 8, 5, 4);
         }
      }
   }

   // $VF: renamed from: e (int, int) void
   private void drawTerrainTile(int var1, int var2) {
      this.minimapPixelColor = -1;
      byte var3;
      if ((var3 = this.effectBitAt(var1, var2)) != 2) {
         if (this.occupantGrid[var2][var1] <= 0) {
            if (this.occupantGrid[var2][var1] < 0) {
               this.minimapPixelColor = this.paletteColors[var3][tribeOfBuilding(this.occupantGrid[var2][var1]) + 9];
            }
         } else {
            this.minimapPixelColor = tribeOfEntity(this.occupantGrid[var2][var1]) == 1 && (this.entityData[7979 + this.occupantGrid[var2][var1]] & 2) != 0
               ? -1
               : this.paletteColors[var3][tribeOfEntity(this.occupantGrid[var2][var1]) + 6];
         }

         if (this.minimapPixelColor == -1) {
            this.minimapPixelColor = this.paletteColors[var3][this.fileAData[this.recordStarts[194] + (this.terrainGrid[var2][var1] & 255)]];
            return;
         }
      } else {
         this.minimapPixelColor = 6049325;
      }
   }

   // $VF: renamed from: E () void
   private void centerMinimapViewport() {
      this.minimapOriginX = this.camTileX - minimapViewW / 2 + (this.screenColsTiles >> 1);
      this.minimapOriginX = this.minimapOriginX < 0
         ? 0
         : (this.minimapOriginX + minimapViewW >= this.mapWidth ? this.mapWidth - minimapViewW : this.minimapOriginX);
      if (minimapViewH > this.mapHeight) {
         this.minimapRowOffset = minimapViewH - this.mapHeight >> 1;
      } else {
         this.minimapOriginY = this.camTileY - minimapViewH / 2 + (this.screenRowsTiles >> 1);
         this.minimapOriginY = this.minimapOriginY < 0
            ? 0
            : (this.minimapOriginY + minimapViewH >= this.mapHeight ? this.mapHeight - minimapViewH : this.minimapOriginY);
      }
   }

   // $VF: renamed from: F () void
   private void dispatchMenuScreen() {
      if (this.tickCounter == -1 && this.fileAData[this.recordStarts[153] + 0 + this.screenId] == 1) {
         this.renderTitleScreen();
      } else if (this.fileAData[this.recordStarts[153] + 0 + this.screenId] == 1) {
         this.renderMenuScreen();
      } else if (this.screenId == 23) {
         this.renderScreen23();
      } else if (this.screenId == 15) {
         this.renderScreen15();
      } else if (this.screenId == 17) {
         this.renderStoryScreen();
      } else if (this.screenId == 20) {
         this.renderPopupScreen();
      } else if (this.screenId == 18) {
         if (this.menuNeedsDraw) {
            this.menuNeedsDraw = false;
            this.hudPanelDirty[13] = true;
            this.hudPanelDirty[11] = true;
            this.layoutHudElement((byte)11, 0, 0);
            this.layoutHudElement((byte)17, 0, 320);
         }

         this.layoutHudElement((byte)13, 0, 0);
      } else if (this.screenId == 19) {
         this.renderMapScreen();
      } else if (this.screenId == 24) {
         if (this.menuNeedsDraw) {
            this.menuNeedsDraw = false;
            this.hudPanelDirty[11] = this.hudPanelDirty[16] = true;
            this.layoutHudElement((byte)11, 0, 0);
            this.layoutHudElement((byte)16, 0, 0);
            this.layoutHudElement((byte)17, 0, 320);
         }
      } else if (this.screenId == 25) {
         this.renderScreen25();
      } else if (this.screenId == 27) {
         this.renderPauseScreen();
      } else if (this.screenId == 32 && this.menuNeedsDraw) {
         this.menuNeedsDraw = false;

         try {
            screenBufferGraphics[menuBufferIdx].drawImage(Image.createImage("/l3"), 11, 8, 20);
         } catch (Exception var2) {
         }

         this.hudPanelDirty[17] = true;
         this.layoutHudElement((byte)17, 0, 320);
      }

      this.drawBufferIndex = this.screenBufferIndex[1];
   }

   // $VF: renamed from: G () void
   private void renderTitleScreen() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.drawBufferIndex = this.screenBufferIndex[1];
         screenBufferGraphics[this.drawBufferIndex].setColor(9422847);
         fillRect(screenBufferGraphics[this.drawBufferIndex], 0, -8, this.screenWidths[this.drawBufferIndex], this.screenHeights[this.drawBufferIndex]);
         screenBufferGraphics[this.drawBufferIndex].setColor(940839);
         fillRect(
            screenBufferGraphics[this.drawBufferIndex], 0, -8 + this.screenHeights[this.drawBufferIndex] - 64, this.screenWidths[this.drawBufferIndex], 64
         );
         this.ghostPx = 0;
         this.ghostPy = 248;
         this.drawTile(1118, (byte)2);
         if (this.screenId == this.homeScreenId
            || this.screenId == 3
            || this.screenId == 12
            || this.screenId == 13
            || this.screenId == 2
            || this.screenId == this.campaignScreenId
            || this.screenId == 9
            || this.screenId == 14) {
            this.hudElemSeen[53] = -1;
            this.hudElemSeen[54] = 1;
         } else if (this.screenId == 16 || this.screenId == 21 || this.screenId == 22) {
            this.hudElemSeen[53] = 1;
            this.hudElemSeen[54] = -1;
         }

         this.hudElemSeen[52] = -1;
      }

      this.drawBufferIndex = menuBufferIdx;
      screenBufferGraphics[this.drawBufferIndex].drawImage(screenBuffers[this.screenBufferIndex[1]], 0, 328, 36);
      screenBufferGraphics[this.drawBufferIndex].setColor(9422847);
      fillRect(screenBufferGraphics[this.drawBufferIndex], 0, 0, this.screenWidths[this.drawBufferIndex], 80);

      for (int var1 = 0; var1 < 4; var1++) {
         if (this.titleParticles[0][var1] > 240 || this.titleParticles[0][3] == -1000) {
            this.titleParticles[0][var1] = -88 - this.randRange(65535, 60);
            this.titleParticles[1][var1] = this.randRange(65535, 48);
            this.titleParticles[2][var1] = this.randRange(255, 4);
         }

         this.a_int_int_int_void(
            1667 + this.titleParticles[2][var1],
            this.titleParticles[0][var1] = this.titleParticles[0][var1] + (this.titleParticles[2][var1] + 2 >> 1),
            this.titleParticles[1][var1] = this.titleParticles[1][var1] + (this.randRange(255, 5) == 0 ? this.randRange(255, 3) - 1 : 0)
         );
      }

      if (this.screenId == this.homeScreenId
         || this.screenId == 12
         || this.screenId == 13
         || this.screenId == 2
         || this.screenId == this.campaignScreenId
         || this.screenId == 9
         || this.screenId == 14) {
         this.hudElemSeen[53] = -1;
         this.hudElemSeen[54] = 1;
      } else if (this.screenId == 16 || this.screenId == 21 || this.screenId == 22) {
         this.hudElemSeen[53] = 1;
         this.hudElemSeen[54] = -1;
      }

      this.hudElemSeen[52] = -1;
      this.hudPanelDirty[10] = true;
      this.a_int_int_int_void(1665, 120, 0);
      this.ghostPx = 0;
      this.ghostPy = 272;
      this.drawTile(1118, (byte)1);
      this.layoutHudElement((byte)10, 25, 64);
      this.hudPanelDirty[17] = true;
      this.layoutHudElement((byte)17, 0, 320);
   }

   // $VF: renamed from: H () void
   private void renderMenuScreen() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.copyGameBufferToMenu();
         if (this.screenId == 10 || this.screenId == 11 || this.screenId == 12 || this.screenId == 13) {
            this.hudElemSeen[53] = -1;
            this.hudElemSeen[54] = 1;
         } else if (this.screenId == 16 || this.screenId == 21 || this.screenId == 22) {
            this.hudElemSeen[53] = 1;
            this.hudElemSeen[54] = -1;
         }

         this.hudElemSeen[52] = -1;
         this.hudPanelDirty[10] = true;
      }

      this.layoutHudElement((byte)10, 25, 64);
      this.layoutHudElement((byte)17, 0, 320);
   }

   // $VF: renamed from: I () void
   private void renderScreen23() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.hudPanelDirty[11] = true;
         this.dialogId = 69;
         this.hudElemSeen[55] = 1;
         this.layoutHudElement((byte)11, 0, 0);
         this.hudElemSeen[55] = -1;
      }
   }

   // $VF: renamed from: J () void
   private void renderScreen15() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.hudPanelDirty[11] = this.hudPanelDirty[15] = true;
         this.layoutHudElement((byte)11, 0, 0);
         this.layoutHudElement((byte)17, 0, 320);
      }

      this.layoutHudElement((byte)15, 0, 0);
   }

   // $VF: renamed from: K () void
   private void renderPopupScreen() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.screenContentChanged = true;
         this.scrollRedrawTerrain();
         this.renderWorldEntities();
         this.copyGameBufferToMenu();
         this.hudPanelDirty[12] = true;
      }

      this.layoutHudElement((byte)12, 0, 0);
      this.layoutHudElement((byte)17, 0, 320);
   }

   // $VF: renamed from: L () void
   private void renderMapScreen() {
      if (this.menuNeedsDraw) {
         this.menuNeedsDraw = false;
         this.hudPanelDirty[11] = true;
         this.layoutHudElement((byte)11, 0, 0);
         this.hudPanelDirty[14] = true;
         this.drawMinimap();
      }

      this.layoutHudElement((byte)14, 120, 160);
      this.hudPanelDirty[17] = true;
      this.layoutHudElement((byte)17, 0, 320);
   }

   // $VF: renamed from: M () void
   private void renderScreen25() {
      this.menuNeedsDraw = false;
      if (!this.menuBackPressed) {
         this.copyGameBufferToMenu();
         screenBufferGraphics[1].drawImage(screenBuffers[0], 0, 0, 0);
      }

      this.hudPanelDirty[2] = true;
      drawRegionSafe(screenBufferGraphics[0], screenBuffers[1], 0, 0, 264, 301, 0, 0, 0);
      this.layoutHudElement((byte)(18 + this.endScreenPhase), 0, 0);
      this.layoutHudElement((byte)2, 0, this.screenPanelY[this.fileAData[this.recordStarts[195] + 0 + 2]]);
      this.layoutHudElement((byte)17, 0, 320);
      this.menuBackPressed = true;
   }

   // $VF: renamed from: N () void
   private void renderPauseScreen() {
      if (this.pauseScreenFirstRun) {
         this.pauseScreenFirstRun = false;
         this.savedDialogId = this.dialogId;
         this.dialogId = 61;
         this.hudPanelDirty[11] = true;
         this.hudElemSeen[55] = 1;
         this.layoutHudElement((byte)11, 0, 0);
         this.hudElemSeen[55] = -1;
         this.layoutHudElement((byte)17, 0, 320);
      }
   }

   // $VF: renamed from: O () void
   private void copyGameBufferToMenu() {
      this.screenBufferIndex[9] = this.screenBufferIndex[8] = 0;
      drawRegionSafe(
         screenBufferGraphics[this.screenBufferIndex[0]],
         screenBuffers[this.screenBufferIndex[1]],
         0,
         0,
         264,
         this.viewHeightPx,
         0,
         this.bufferCopyY - this.visiblePanelCount,
         0
      );

      for (byte var1 = 0; var1 < 20; var1++) {
         if (this.hudPanelVisible[var1]) {
            this.hudPanelDirty[var1] = true;
            this.layoutHudElement(var1, 0, this.screenPanelY[this.fileAData[this.recordStarts[195] + 0 + var1]]);
            this.drawBufferIndex = this.screenBufferIndex[0];
            if (this.fileAData[this.recordStarts[195] + 20 + var1] == 1 && this.screenId != 20) {
               this.a_int_int_int_void(524, 0, 0 + this.bufferCopyY);
            } else if (this.fileAData[this.recordStarts[195] + 20 + var1] == 2) {
               this.a_int_int_int_void(528, 0, 320 - this.bufferCopyX);
            }
         }
      }

      this.screenBufferIndex[9] = this.screenBufferIndex[8] = 2;
   }

   // $VF: renamed from: P () void
   private void renderStoryScreen() {
      if (this.storyImageId > 0 && !this.storyIsCutscene) {
         this.storyImageId += 4;
         screenBufferGraphics[menuBufferIdx].setColor(0);
         fillRect(screenBufferGraphics[menuBufferIdx], 0, 0, 240, 16 + this.storyImageId);
         fillRect(screenBufferGraphics[menuBufferIdx], 0, 336 - this.storyImageId, 240, this.storyImageId);
         this.fastTick = true;
         if (this.storyImageId - 4 <= 160) {
            return;
         }

         this.storyImageId = -this.storyImageId;
         this.fastTick = false;
         this.resetInputFlags();
      }

      if (this.menuNeedsDraw && !this.storyIsCutscene) {
         this.hudPanelDirty[12] = true;
         screenBufferGraphics[menuBufferIdx].setColor(0);
         fillRect(screenBufferGraphics[menuBufferIdx], 0, 0, 240, 112);
         fillRect(screenBufferGraphics[menuBufferIdx], 0, 240, 240, 96);
      }

      if (this.fileAData[this.recordStarts[155] + 0 + this.storyStepType] == 1) {
         if (this.storyStepDir >= 0) {
            if (this.storyStepDir >= this.fileAData[this.recordStarts[155] + 84 + this.storyStepType] && this.subjectEntityId == 0) {
               if (this.subjectEntityId == 0 && this.storyStepType != 0) {
                  this.storyStepDir = -1;
                  this.storyAdvancePressed = true;
                  return;
               }
            } else {
               this.drawBufferIndex = 0;
               screenBufferGraphics[menuBufferIdx].setColor(0);
               fillRect(screenBufferGraphics[menuBufferIdx], 0, 112, 240, 128);
               this.drawSpriteAt(
                  98 + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType],
                  120,
                  176,
                  this.subjectEntityId == 0 ? this.storyStepDir : this.animCycle
               );
               this.drawSpriteAt(104, 120, 176, this.animCycle);
               this.storyStepDir = this.subjectEntityId == 0 ? this.storyStepDir + 1 : this.storyStepDir;
            }
         }
      } else if (this.fileAData[this.recordStarts[155] + 0 + this.storyStepType] >= 2 || this.fileAData[this.recordStarts[155] + 0 + this.storySceneType] >= 2) {
         this.advanceStory();
         this.renderStory();
      }

      this.menuNeedsDraw = false;
      if (this.storyImageId < 0) {
         this.storyImageId += 4;
         this.fastTick = true;
         if (this.storyImageId >= -48) {
            this.storyImageId = 0;
            this.fastTick = false;
            this.resetInputFlags();
         }
      }

      if (this.storyImageId == 0 && this.storyStepSlot != 0) {
         this.layoutHudElement((byte)12, 0, 0);
         this.layoutHudElement((byte)17, 0, 320);
      }
   }

   // $VF: renamed from: Q () void
   private void advanceStory() {
      if (this.storyStepType > 0 && this.storyStepDir >= 0 && (this.storyImageId == 0 || this.menuNeedsDraw)) {
         if (this.storyStepDir == 0 && this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] > 0) {
            for (int var2 = 0; var2 < this.storyActors.length; var2++) {
               if (this.storyActors[var2] != 0) {
                  byte var1 = (byte)(var2 > 6 ? 6 : var2);
                  if (this.fileAData[this.recordStarts[196] + var1 * 33 + 0 + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] * 3] != 0) {
                     this.entityData[2929 + this.storyActors[var2]] = this.fileAData[this.recordStarts[196]
                        + var1 * 33
                        + 0
                        + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] * 3];
                     this.entityData[1313 + this.storyActors[var2]] = this.fileAData[this.recordStarts[196]
                        + var1 * 33
                        + 2
                        + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] * 3];
                     this.entityData[2828 + this.storyActors[var2]] = 105;
                  }

                  if (this.fileAData[this.recordStarts[196] + var1 * 33 + 1 + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] * 3] != 0) {
                     this.entityData[606 + this.storyActors[var2]] = (byte)(
                        -10 + this.fileAData[this.recordStarts[196] + var1 * 33 + 1 + this.fileAData[this.recordStarts[155] + 28 + this.storyStepType] * 3]
                     );
                     this.entityData[808 + this.storyActors[var2]] = 0;
                  }

                  this.entityData[3030 + this.storyActors[var2]] = 0;
               }
            }
         }

         if (this.storyStepDir >= this.fileAData[this.recordStarts[155] + 84 + this.storyStepType]) {
            this.storyStepDir = -2;
            if (this.storyStepSlot == 0) {
               this.storyAdvancePressed = true;
               return;
            }
         }

         this.storyStepDir++;
         if (this.storyStepDir > 0) {
            this.tickWorldUpdate();
         }
      }
   }

   // $VF: renamed from: R () void
   private void renderStory() {
      if (this.stepAction != 0 || this.fileAData[this.recordStarts[155] + 84 + this.storySceneType] >= -1 || this.storyStepDir > 0) {
         if (this.menuNeedsDraw) {
            this.screenContentChanged = true;
            this.drawBufferIndex = 1;
            this.scrollRedrawTerrain();
         }

         if (this.storyIsCutscene) {
            if (this.menuNeedsDraw) {
               this.menuNeedsDraw = false;
               this.screenContentChanged = true;
               this.scrollRedrawTerrain();
               this.renderWorldEntities();
               this.copyGameBufferToMenu();
               this.hudPanelDirty[12] = true;
               return;
            }
         } else if (this.storyImageId == 0 || this.menuNeedsDraw) {
            this.shakeOffsetY = 0;
            if (this.storyStepType == 24) {
               this.shakeOffsetY = this.randRange(255, 8);
            }

            drawRegionSafe(
               screenBufferGraphics[this.screenBufferIndex[0]],
               screenBuffers[this.screenBufferIndex[1]],
               0,
               120,
               264,
               128 - this.shakeOffsetY,
               0,
               120 + this.shakeOffsetY,
               0
            );
            if (this.storyStepType == 24) {
               screenBufferGraphics[menuBufferIdx].setColor(0);
               fillRect(screenBufferGraphics[menuBufferIdx], 0, 96, 240, 16);
               fillRect(screenBufferGraphics[menuBufferIdx], 0, 240, 240, 16);
            }

            this.drawBufferIndex = 0;
            this.storyFrameX = 0;
            this.storyFrameY = 120;
            this.storyFrameW = 264;
            this.storyFrameH = 128;
            setClipRect(screenBufferGraphics[this.drawBufferIndex], this.storyFrameX, this.storyFrameY, this.storyFrameW, this.storyFrameH);
            this.renderWorldEntities();
            this.storyFrameX = 0;
            this.storyFrameY = 0;
            this.storyFrameW = 528;
            this.storyFrameH = 704;
            setClipRect(screenBufferGraphics[this.drawBufferIndex], this.storyFrameX, this.storyFrameY, this.storyFrameW, this.storyFrameH);
         }
      }
   }

   // $VF: renamed from: b (int) void
   final void advanceStoryStep(int var1) {
      this.resetInputFlags();
      if (var1 >= 0) {
         this.storySceneId = (byte)var1;
         this.setupStoryScene();
         this.setupStorySceneData();
      } else {
         if (this.storyStepIndex == this.storyStepCount - 1) {
            this.endStory();
            return;
         }

         this.storyStepIndex++;
      }

      this.startupPhase = 0;
      this.dialogId = 71 + this.storyStepIndex;
      this.storySpeaker = (byte)(this.fileAData[this.recordStarts[154] + this.missionIndex] + this.storyStepIndex);
      this.storyStepType = this.fileAData[this.recordStarts[311] + 229 + this.storyTableBase + this.storyStepIndex];
      this.storyStepDir = this.fileAData[this.recordStarts[155] + 84 + this.storyStepType] < 0 ? -1 : 0;
      this.storyStepSlot = this.fileAData[this.recordStarts[311] + 0 + this.storyTableBase + this.storyStepIndex];
      this.subjectEntityId = this.storyActors[this.storyStepSlot];
      this.stepAction = this.fileAData[this.recordStarts[311] + 458 + this.storyTableBase + this.storyStepIndex];
      if (this.stepAction > 100) {
         this.f_int_int_void(this.stepAction, -2);
      } else if (this.stepAction == 99) {
         this.subjectEntityId = this.storyActors[0];
         this.f_int_int_void(this.subjectEntityId, -2);
      } else if (this.stepAction > 0) {
         this.f_int_int_void(this.storyActors[this.stepAction], -2);
      } else if (this.stepAction == -100) {
         if (this.storySceneId == 51) {
            this.camTileX = 8;
            this.camTileY = 67;
         }

         this.f_int_int_void(-100, -2);
      } else if (this.stepAction < 0) {
         this.f_int_int_void(this.stepAction, -3);
         if (this.storySceneId == 36 || this.storySceneId == 38) {
            this.camTileX = (byte)(this.camTileX + 4);
            this.camTileY--;
         }
      }

      this.menuNeedsDraw = true;
   }

   // $VF: renamed from: S () void
   private void setupStoryScene() {
      this.clearDragSelect();
      this.clearEffects();
      this.storyStepIndex = 0;
      this.menuCursor = 0;
      this.textLoader.selectStorySection(this.storySceneId);
      this.storyTableBase = this.fileAData[this.recordStarts[310] + 0 + this.storySceneId] & 255;
      this.storySceneType = this.fileAData[this.recordStarts[311] + 229 + this.storyTableBase];
      if (this.storySceneId == 4 || this.storySceneType == 0) {
         this.fileAData[this.recordStarts[311] + 229 + this.storyTableBase] = this.storySceneType = 27;
      }

      this.storyStepCount = (byte)((this.fileAData[this.recordStarts[310] + 0 + this.storySceneId + 1] & 255) - this.storyTableBase);
      this.storyIsCutscene = this.storySceneType >= 27;
      this.mode = 0;
      this.prevScreen = this.screenId;
      this.screenId = 17;
      this.storyImageId = this.fileAData[this.recordStarts[155] + 56 + this.storySceneType];
      if (this.storyImageId == 1) {
         this.copyGameBufferToMenu();
      }

      if (this.storySceneType != 9 && !this.storyIsCutscene && this.storyImageId != 1) {
         this.camTileY--;
      }

      this.popupButtonFlags = new boolean[]{
         this.hudPanelVisible[0],
         this.hudPanelVisible[1],
         this.hudPanelVisible[2],
         this.hudPanelVisible[4],
         this.hudPanelVisible[5],
         this.hudPanelVisible[6],
         this.hudPanelVisible[7],
         this.hudPanelVisible[8],
         this.hudPanelVisible[9]
      };
      this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
      this.storyMode = true;
      byte var1 = this.fileAData[this.recordStarts[310] + 71 + this.storySceneId];
      System.arraycopy(
         this.fileAData,
         this.recordStarts[156] + this.fileAData[this.recordStarts[157] + var1],
         this.storyActors,
         0,
         this.fileAData[this.recordStarts[157] + var1 + 1] - this.fileAData[this.recordStarts[157] + var1]
      );
   }

   // $VF: renamed from: T () void
   private void setupStorySceneData() {
      if (this.storySceneId == 3) {
         this.d_int_int_int_void(5, 20, 2);
         this.entityData[6161 + this.storyActors[2]] = 3;
         this.entityData[2828 + this.storyActors[2]] = 105;
         this.entityData[6262 + this.storyActors[2]] = 5;
         this.entityData[6363 + this.storyActors[2]] = 10;
         this.entityData[1313 + this.storyActors[2]] = 3;
         this.entityData[5050 + this.storyActors[2]] = -1;
      } else if (this.storySceneId == 37) {
         this.world.clearWorld();
         this.spawnTribe = 1;
         this.world.spawnWaveUnits(4, 46, 9, (byte)0, (byte)9);
         this.spawnVillager((byte)0, (byte)48, (byte)9, (byte)3, (byte)0);
         this.entityData[2934] = 14;
         this.entityData[611] = 2;
         this.spawnTribe = 2;
         this.world.spawnWaveUnits(1, 44, 9, (byte)1, (byte)2);
         this.world.spawnWaveUnits(1, 45, 11, (byte)1, (byte)2);
         this.entityData[658] = 7;
         this.world.spawnWaveUnits(1, 48, 11, (byte)1, (byte)2);
         this.entityData[659] = 5;
         this.world.spawnWaveUnits(2, 48, 9, (byte)1, (byte)2);
         this.tribeStats[0][17] = 0;
      } else if (this.storySceneId == 38) {
         this.world.clearWorld();
         this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 2, 52, 18, 2, true, -1);
         this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 2, 54, 16, 2, true, -1);
         this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 2, 55, 19, 2, true, -1);
         this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 2, 58, 19, 2, true, -1);
         this.spawnTribe = 0;
         this.world.spawnWaveUnits(5, 60, 12, (byte)0, (byte)0);
         this.spawnTribe = 2;
         this.d_int_int_int_void(57, 17, 1);
         this.textLoader.reset();
      } else if (this.storySceneId == 43) {
         this.resetVillagerPositions(52, 7);
      } else if (this.storySceneId == 45) {
         this.world.clearEnemyTribe();
         this.resetVillagerPositions(57, 30);
         this.spawnEffect(1, 54, 30, 5);
         this.spawnEffect(1, 54, 31, 5);
         this.spawnEffect(1, 55, 31, 5);
         this.spawnEffect(1, 55, 32, 5);
      } else {
         if (this.storySceneId == 48) {
            this.missionIndex = 2;
            this.resetWorldState();
            this.world.scriptEventIndex = 6;
            this.loadMap();
            this.missionIndex = 3;
            this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 24, 18, 67, 2, true, -1);
            this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 25, 19, 69, 2, true, -1);
            this.a_byte_int_int_int_int_boolean_int_byte((byte)0, 25, 21, 71, 2, true, -1);
            this.tribeStats[0][12] = 0;
            this.spawnVillager((byte)0, (byte)26, (byte)69, (byte)60, (byte)0);
            this.tribeStats[0][17] = 1;
            this.world.spawnWaveUnits(7, 26, 69, (byte)0, (byte)2);
            this.d_int_int_int_void(23, 69, 1);
            this.world.spawnWaveUnits(2, 1, 69, (byte)1, (byte)3);
            this.spawnVillager((byte)1, (byte)21, (byte)69, (byte)60, (byte)0);
            this.entityData[659] = 0;
            this.entityData[2982] = 42;
            this.f_int_int_void(53, -2);
            this.entityData[657] = 0;
         }
      }
   }

   // $VF: renamed from: U () void
   private void endStory() {
      this.world.rebuildFogOfWar();
      if (this.storySceneId == 26) {
         this.storyMode = false;
         this.loadNextLevel();
         this.storyMode = true;
      }

      if (this.storySceneType == 9) {
         this.world.restoreCouncil();
      } else if (this.storySceneId == 28) {
         this.d_int_int_int_void(5, 11, 0);
         this.world.slotScanI = (byte)(this.world.slotScanI & 254);
         this.world.messageCooldowns[this.fileAData[this.recordStarts[292] + 4]] = 0;
      } else if (this.storySceneId == 34) {
         this.d_int_int_int_void(48, 15, 5);
         this.world.slotScanI = 0;
      } else if (this.storySceneId == 38) {
         this.d_int_int_int_void(65, 18, 1);
         this.endgameTimer = 1;
      } else if (this.storySceneId == 44) {
         for (int var1 = 0; var1 < 10; var1++) {
            this.entityData[9601 + var1] = this.subjectEntityId;
         }

         this.d_int_int_int_void(53, 5, 0);
         this.f_int_int_void(this.subjectEntityId, -2);
         this.textLoader.reset();
      } else if (this.storySceneId == 48) {
         this.endgameTimer = 1;
         this.d_int_int_int_void(45, 69, 6);
      }

      for (int var2 = 0; var2 < this.storyActors.length; var2++) {
         if (this.entityData[2828 + this.storyActors[var2]] == 105) {
            if (this.entityData[4646 + this.storyActors[var2]] < 0) {
               this.entityData[2828 + this.storyActors[var2]] = 69;
            } else {
               this.entityData[2828 + this.storyActors[var2]] = 57;
            }
         }

         this.storyActors[var2] = 0;
      }

      if (this.world.scriptEventIndex >= 50) {
         this.world.timerOrCountF = this.storySceneId == 15 ? 1000000 : this.tickCounter;
         this.world.scriptEventIndex = (byte)(this.world.scriptEventIndex - 49);
         this.world.hintStepIndex = this.fileAData[this.recordStarts[294] + this.world.scriptEventIndex - 1];
         this.world.needSlotJ = 0;
      }

      if (this.storySceneType != 9) {
         this.savedCamCol = this.camTileX;
         this.savedCamRow = this.camTileY = this.storyIsCutscene && !this.popupButtonFlags[3] ? this.camTileY : (byte)(this.camTileY + 1);
      } else {
         this.camTileX = this.savedCamCol;
         this.camTileY = this.savedCamRow;
      }

      this.storyMode = false;
      this.clampCamera();
      this.savedCamCol = this.camTileX;
      this.savedCamRow = this.camTileY;
      if (this.storySceneId != 0
         && this.storySceneId != 1
         && (this.storySceneId != 2 || this.newGameFlag == 0)
         && this.storySceneId != 32
         && this.storySceneId != 36
         && this.storySceneId != 37
         && this.storySceneId != 39
         && this.storySceneId != 46
         && this.storySceneId != 47
         && this.storySceneId != 49
         && this.storySceneId != 55) {
         if (this.storySceneId != 59) {
            this.mode = 1;
         }

         this.screenContentChanged = true;
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
            this.popupButtonFlags[0],
            this.popupButtonFlags[1],
            this.popupButtonFlags[2],
            this.popupButtonFlags[3],
            this.popupButtonFlags[4],
            this.popupButtonFlags[5],
            this.popupButtonFlags[6],
            this.popupButtonFlags[7],
            this.popupButtonFlags[8]
         );
         this.screenId = this.prevScreen;
      } else {
         this.storySceneId++;
         this.screenId = 28;
         this.advanceStoryStep(this.storySceneId);
         if (this.storySceneId == 47) {
            this.screenId = 18;
            this.storySpeaker = this.fileAData[this.recordStarts[154] + 8];
         }
      }
   }

   // $VF: renamed from: d (int, int, int) void
   private void d_int_int_int_void(int var1, int var2, int var3) {
      for (int var4 = 0; var4 < this.storyActors.length; var4++) {
         if (var4 == var3 || var4 >= 6) {
            this.e_int_int_int_void(var1, var2, this.storyActors[var4]);
         }
      }
   }

   // $VF: renamed from: e (int, int, int) void
   private void e_int_int_int_void(int var1, int var2, int var3) {
      this.entityData[2828 + var3] = 69;
      this.entityData[3131 + var3] = (byte)var1;
      this.entityData[3232 + var3] = (byte)var2;
      this.entityData[6161 + var3] = 2;
      this.entityData[4747 + var3] = 0;
      this.entityData[7979 + var3] = (byte)(this.entityData[7979 + var3] & 191);
   }

   // $VF: renamed from: c () void
   final void loadNextLevel() {
      this.mapWidth = this.tableMapWidth;
      this.mapHeight = this.tableMapHeight;
      this.missionIndex++;
      this.spawnInitialEntities();
      this.world.missionRegionBaseG = this.recordStarts[219] + this.missionIndex * 4;
   }

   // $VF: renamed from: a (byte, byte, byte) void
   final void a_byte_byte_byte_void(byte var1, byte var2, byte var3) {
      if (var1 >= 18 || this.chiefId != 0 && this.screenId != 17 && (var1 == 18 || !this.menuItemActivated) && this.endgameTimer <= 0) {
         this.menuCursor = 0;
         this.startupPhase = 0;
         this.resetInputFlags();
         this.popupKind = var1;
         this.mode = 0;
         this.screenId = 20;
         this.menuNeedsDraw = true;
         this.dialogId = 12 + var1;
         this.subjectEntityId = var2;
         this.stepAction = var3;
         this.popupButtonFlags = new boolean[]{
            this.hudPanelVisible[0],
            this.hudPanelVisible[1],
            this.hudPanelVisible[2],
            this.hudPanelVisible[4],
            this.hudPanelVisible[5],
            this.hudPanelVisible[6],
            this.hudPanelVisible[7],
            this.hudPanelVisible[8],
            this.hudPanelVisible[9]
         };
         this.a_boolean_boolean_boolean_boolean_void(this.hudPanelVisible[0], true, false, this.hudPanelVisible[4]);
         this.screenContentChanged = true;
         if (var1 != 4 && var1 != 5 && var1 != 14 && var1 != 15) {
            this.fileAData[this.recordStarts[153] + 145 + 20] = 2;
         } else {
            this.fileAData[this.recordStarts[153] + 145 + 20] = 0;
         }
      }
   }

   protected final void keyPressed(int var1) {
      if (!this.inputLocked) {
         if (!this.keyDownFlag) {
            this.keyDownFlag = true;
            if (isHandledKeyCode(var1)) {
               this.ticksSinceKeypress = 0;
               if (var1 == softkeyLeftCode || var1 == -softkeyLeftCode || var1 == 42) {
                  leftSoftkeyPressed = true;
               } else if (var1 != softkeyRightCode && var1 != -softkeyRightCode && var1 != 35) {
                  switch (var1) {
                     case 35:
                     case 36:
                     case 37:
                     case 38:
                     case 39:
                     case 40:
                     case 41:
                     case 42:
                     case 43:
                     case 44:
                     case 45:
                     case 46:
                     case 47:
                     case 50:
                     case 52:
                     case 53:
                     case 54:
                     case 56:
                     default:
                        break;
                     case 48:
                        this.numericKey = 0;
                        break;
                     case 49:
                        this.numericKey = 1;
                        this.keyHeld = true;
                        break;
                     case 51:
                        this.numericKey = 3;
                        this.keyHeld = true;
                        break;
                     case 55:
                        this.numericKey = 7;
                        this.keyHeld = true;
                        break;
                     case 57:
                        this.numericKey = 9;
                        this.keyHeld = true;
                  }

                  if (this.cheatsEnabled && this.loadingDone) {
                     this.m_int_void(var1);
                  }

                  try {
                     this.gameAction = this.getGameAction(var1);
                  } catch (Exception var3) {
                     this.gameAction = 666;
                  }

                  if (this.gameAction != 8 && var1 != 53) {
                     if (this.gameAction == 1 || var1 == 50) {
                        this.keyUp = true;
                        return;
                     }

                     if (this.gameAction == 6 || var1 == 56) {
                        this.keyDown = true;
                        return;
                     }

                     if (this.gameAction == 2 || var1 == 52) {
                        this.keyLeft = true;
                        return;
                     }

                     if (this.gameAction == 5 || var1 == 54) {
                        this.keyRight = true;
                     }
                  } else {
                     this.firePressed = true;
                     this.fireHeld = true;
                     this.fireTapCount++;
                     if (!this.dragSelecting) {
                        this.fireHoldTicks = 0;
                        return;
                     }
                  }
               } else {
                  rightSoftkeyPressed = true;
               }
            }
         }
      }
   }

   protected final void keyReleased(int var1) {
      if (!this.inputLocked) {
         if (isHandledKeyCode(var1)) {
            this.ticksSinceKeypress = 0;
            switch (var1) {
               case 35:
               case 36:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 50:
               case 52:
               case 53:
               case 54:
               case 56:
               default:
                  break;
               case 49:
                  this.keyHeld = false;
                  break;
               case 51:
                  this.keyHeld = false;
                  break;
               case 55:
                  this.keyHeld = false;
                  break;
               case 57:
                  this.keyHeld = false;
            }

            try {
               this.gameAction = this.getGameAction(var1);
            } catch (Exception var3) {
               this.gameAction = 666;
            }

            if (var1 == 53 || this.gameAction == 8) {
               this.fireHeld = false;
               if (this.fireHoldTicks < 4) {
                  this.fireTapped = true;
               }
            }

            if (this.keyDownFlag) {
               this.keyReleasedFlag = true;
            } else if (var1 == 50 || this.gameAction == 1) {
               this.keyUp = false;
            } else if (var1 == 56 || this.gameAction == 6) {
               this.keyDown = false;
            } else if (var1 != 52 && this.gameAction != 2) {
               if (var1 == 54 || this.gameAction == 5) {
                  this.keyRight = false;
               }
            } else {
               this.keyLeft = false;
            }
         }
      }
   }

   // $VF: renamed from: a (int) boolean
   private static boolean isHandledKeyCode(int var0) {
      return var0 != -10;
   }

   // $VF: renamed from: V () void
   private void dispatchInGameInput() {
      this.handleSoftkeys();
      if (this.dragSelecting && this.firePressed) {
         this.firePressed = false;
         this.endDragSelect();
      } else if (this.fireHeld && this.interactMode != 3) {
         this.firePressed = false;
         this.startDragSelect();
      } else {
         this.selectedGroup = 0;
      }

      if (this.groupKeyHoldTicks >= 4 && this.interactMode == 1) {
         this.saveGroup();
      } else if (this.groupKeyHoldTicks > 0 && this.groupKeyHoldTicks < 4 && !this.keyHeld) {
         this.recallGroup();
      }

      if (this.fireTapped) {
         this.fireTapped = false;
         this.fireHoldTicks = 4;
         this.firePressed = false;
         byte var1 = this.occupantGrid[this.camTileY + this.cursorRow][this.camTileX + this.cursorCol];
         if (this.interactMode == 1 && this.nearestEntityId >= 51 && this.nearestEntityId <= 100) {
            this.cursorCol = (byte)(this.entityData[0 + this.nearestEntityId] - this.camTileX);
            this.cursorRow = (byte)(this.entityData[101 + this.nearestEntityId] - this.camTileY);
            this.e_byte_void(this.nearestEntityId);
         } else if (this.interactMode == 1 && tribeOfEntity(var1) != 0) {
            this.e_byte_void(var1);
         } else if (this.interactMode == 3) {
            this.confirmPlacement();
         } else if (tribeOfEntity(var1) == 0) {
            this.d_byte_void(var1);
         } else if (this.interactMode == 0 && tribeOfBuilding(var1) == 0) {
            this.f_byte_void((byte)(-var1));
         } else if (tribeOfEntity(this.nearestEntityId) == 0) {
            this.d_byte_void(this.nearestEntityId);
            this.cursorCol = (byte)(this.entityData[0 + this.nearestEntityId] - this.camTileX);
            this.cursorRow = (byte)(this.entityData[101 + this.nearestEntityId] - this.camTileY);
         }

         this.cursorMoved = true;
         this.animFrame = 0;
      }

      this.groupKeyHoldTimer();
      if (this.scrollDirX == 0 && this.scrollDirY == 0 && !this.hudChanged) {
         this.scrollCamera();
         this.scrollXPx = this.scrollDirX * 11;
         this.scrollYPx = this.scrollDirY * 8;
      }
   }

   // $VF: renamed from: W () void
   private void handleSoftkeys() {
      if (leftSoftkeyPressed) {
         leftSoftkeyPressed = false;
         if (this.interactMode != 3) {
            this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(true, false, false, false, true, false, false, false, false);
            this.interactMode = 2;
            this.buildContextMenu();
         }
      } else if (rightSoftkeyPressed) {
         rightSoftkeyPressed = false;
         if (this.interactMode != 3) {
            if (this.interactMode != 1 && this.fireHeldCount == -1) {
               this.mode = 0;
               this.screenId = (byte)(this.missionIndex < 7 ? (this.savedMissionProgress > -1 ? 10 : 11) : (this.customGameAvailable ? 10 : 11));
               this.prevScreen = 28;
               this.menuCursor = 0;
               this.menuNeedsDraw = true;
               this.menuActionPending = false;
               this.savedCursorCol = this.cursorCol;
               this.savedCursorRow = this.cursorRow;
               this.savedCamCol = this.camTileX;
               this.savedCamRow = this.camTileY;
               return;
            }

            this.worldReady = true;
            return;
         }

         this.placementType = 0;
         this.setPlaceMode(-1);
      }

      if (this.numericKey == 1) {
         this.numericKey = -1;
         if (this.messageType == 1) {
            this.messageType = 0;
            this.cursorCol = this.homeCursorCol;
            this.camTileX = (byte)(this.focusTable[0][0] - this.cursorCol);
            this.cursorRow = this.homeCursorRow;
            this.camTileY = (byte)(this.focusTable[1][0] - this.cursorRow);
            this.clampCamera();
            this.screenContentChanged = true;
         }
      } else if (this.numericKey == 3) {
         this.numericKey = -1;
         if (this.missionIndex == 3) {
            this.a_byte_byte_byte_void((byte)20, (byte)1, (byte)0);
         } else {
            this.mode = 0;
            this.screenId = 19;
            this.menuNeedsDraw = true;
            this.dialogId = -1;
            this.menuScrollUp = this.menuScrollDown = true;
            this.fastTick = true;
            this.savedCursorCol = this.cursorCol;
            this.savedCursorRow = this.cursorRow;
            this.savedCamCol = this.camTileX;
            this.savedCamRow = this.camTileY;
            this.centerMinimapViewport();
            this.resetInputFlags();
         }
      }
   }

   // $VF: renamed from: X () void
   private void confirmPlacement() {
      if (this.placementValid) {
         if (this.placementType == 100) {
            if (this.placeModeChanged) {
               this.placeConfirmFlagN = this.placeModeChanged;
               this.placeConfirmFlagL = this.placingActive = false;
               this.world.flagDistressed();
            } else if (this.placingActive) {
               this.placeConfirmFlagL = this.placingActive;
               this.world.flagDistressed();
            }

            this.homePosition[0] = (byte)(this.camTileX + this.cursorCol);
            this.homePosition[1] = (byte)(this.camTileY + this.cursorRow);
            this.setPlaceMode(-1);
            return;
         }

         if (this.placementType < 0) {
            this.entityData[3131 + this.orderTargetId] = this.entityData[8383 + this.menuTargetId];
            this.entityData[3232 + this.orderTargetId] = this.entityData[8444 + this.menuTargetId];
            this.entityData[2828 + this.orderTargetId] = (byte)(44 + -this.placementType - 1);
            this.entityData[6161 + this.orderTargetId] = 3;
            return;
         }

         if (this.placementType <= 3) {
            this.freeOrderSlot = 0;

            while (this.freeOrderSlot < 29 && this.pendingOrders[0][0][this.freeOrderSlot] != 0) {
               this.freeOrderSlot++;
            }

            this.pendingOrders[0][1][this.freeOrderSlot] = (byte)this.placementType;
            if (this.pendingOrders[0][1][this.freeOrderSlot] == 3) {
               this.pendingOrders[0][0][this.freeOrderSlot] = (byte)this.menuTargetId;
               this.pendingOrders[0][2][this.freeOrderSlot] = (byte)(this.camTileX + this.cursorCol);
               this.pendingOrders[0][3][this.freeOrderSlot] = (byte)(this.camTileY + this.cursorRow);
               this.setPlaceMode(-1);
               return;
            }

            if (tribeOfEntity(this.orderTargetId) == 0) {
               this.pendingOrders[0][0][this.freeOrderSlot] = (byte)this.menuTargetId;
               this.pendingOrders[0][2][this.freeOrderSlot] = (byte)this.orderTargetId;
               return;
            }
         } else if (this.isTileInBounds(this.camTileX + this.placeGhostX, this.camTileY + this.placeGhostY)
            && this.a_byte_int_int_int_int_boolean_int_byte(
                  (byte)0, this.placementType, this.camTileX + this.placeGhostX, this.camTileY + this.placeGhostY, 1, false, -1
               )
               > 0) {
            this.setPlaceMode(-1);
            this.interactMode = 2;
         }
      }
   }

   // $VF: renamed from: d (byte) void
   private void d_byte_void(byte var1) {
      if (this.fireTapCount > 1) {
         this.fireTapCount = 0;
         this.selRectX = this.camTileX - 1;
         this.selRectY = this.camTileY - 1;
         this.selRectW = this.screenColsTiles;
         this.selRectH = this.screenRowsTiles;
         this.dragSelecting = true;
         this.fireHeld = true;
         this.endDragSelect();
         this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         this.selectedVillagerId = this.inspectedVillagerId = 0;
      } else {
         if (this.entityData[1919 + var1] > 0) {
            this.a_boolean_boolean_boolean_boolean_void(false, true, false, true);
            this.interactMode = 1;
            this.selectedVillagerId = this.inspectedVillagerId = var1;
            this.fireHeldCount = -1;
         }
      }
   }

   // $VF: renamed from: e (byte) void
   private void e_byte_void(byte var1) {
      if (this.fireHeldCount != -1) {
         for (int var2 = 0; var2 < 50; var2++) {
            if (this.villagerGroups[this.fireHeldCount][var2] > 0) {
               this.a_byte_byte_void(this.villagerGroups[this.fireHeldCount][var2], var1);
            }
         }
      } else {
         this.a_byte_byte_void(this.selectedVillagerId, var1);
      }

      this.fireTapCount = 0;
   }

   // $VF: renamed from: a (byte, byte) void
   private void a_byte_byte_void(byte var1, byte var2) {
      if (this.entityData[505 + var1] >= 10 || this.placeConfirmFlagN || this.placeConfirmFlagL && (this.entityData[7979 + var1] & 64) != 0) {
         if (tribeOfBuilding(var2) == 0 && this.entityData[8749 + -var2] == 6) {
            this.a_boolean_boolean_boolean_boolean_void(true, false, true, false);
            this.menuTargetId = -var2;
            this.interactMode = 2;
            this.buildContextMenu();
         } else {
            if (this.fireTapCount > 1
               || this.entityData[3131 + var1] == this.cursorCol + this.camTileX && this.entityData[3232 + var1] == this.cursorRow + this.camTileY) {
               this.moveOrderFlash = true;
               if (this.entityData[7777 + var1] == 0) {
                  this.entityData[7777 + var1] = 3;
                  this.world.moveStance(var1);
                  if (this.entityData[7777 + var1] > 0) {
                     this.entityData[1111 + var1] = (byte)(
                        (this.entityData[1111 + var1] & 240)
                           + this.fileAData[this.recordStarts[129 + (this.entityData[606 + var1] & 1) * 2]
                              + this.fileAData[this.recordStarts[129 + (this.entityData[606 + var1] & 1) * 2 + 1] + this.entityData[1010 + var1] - 5]
                              + (this.entityData[1111 + var1] & 15)]
                     );
                  }
               }
            } else if (this.entityData[7777 + var1] > 0) {
               this.entityData[1111 + var1] = (byte)(
                  (this.entityData[1111 + var1] & 240)
                     + this.fileAData[this.recordStarts[133 + (this.entityData[606 + var1] & 1) * 2]
                        + this.fileAData[this.recordStarts[133 + (this.entityData[606 + var1] & 1) * 2 + 1] + this.entityData[1010 + var1] - 5]
                        + (this.entityData[1111 + var1] & 15)]
               );
               this.entityData[7777 + var1] = 0;
            }

            this.entityData[3131 + var1] = (byte)(this.cursorCol + this.camTileX);
            this.entityData[3232 + var1] = (byte)(this.cursorRow + this.camTileY);
            this.entityData[7979 + var1] = (byte)(this.entityData[7979 + var1] & 223);
            this.entityData[6161 + var1] = 2;
            this.entityData[4747 + var1] = var2;
            this.orderTileX = (byte)(this.cursorCol + this.camTileX);
            this.orderTileY = (byte)(this.cursorRow + this.camTileY);
            this.orderCursorType = (byte)((tribeOfEntity(var2) == 1 || tribeOfBuilding(var2) == 1 || isObjectId(var2))
                  && this.effectBitAt(this.cursorCol + this.camTileX, this.cursorRow + this.camTileY) == 0
               ? 2
               : 1);
         }
      } else {
         this.world.showMessage((byte)12, var1, 0);
      }
   }

   // $VF: renamed from: c (int) void
   final void unassignEntity(int var1) {
      for (int var2 = (var1 - 1) / 50 * 30 + 1; var2 <= (var1 - 1) / 50 * 30 + 30; var2++) {
         if (this.entityData[8810 + var2] == var1 && (this.entityData[8749 + var2] != 2 || this.entityData[8627 + var2] != 0)) {
            this.clearEntityJob(var2, false);
            this.entityData[8810 + var2] = 0;
            this.entityData[9298 + var2] = 0;
            return;
         }

         if ((this.entityData[8627 + var2] == 2 || this.entityData[8627 + var2] == 1) && this.entityData[9237 + var2] == var1) {
            this.entityData[9237 + var2] = 0;
         }
      }
   }

   // $VF: renamed from: a (int, boolean) void
   final void clearEntityJob(int var1, boolean var2) {
      for (byte var3 = this.entityData[8993 + var1]; var3 > 0; var3 = this.entityData[4545 + var3]) {
         if ((this.entityData[4646 + var3] != 0 || var2)
            && (
               this.entityData[8749 + var1] != 11
                  || this.entityData[8627 + var1] != 0
                  || this.fileAData[this.recordStarts[18] + this.entityData[6565 + var3]] == 0
            )
            && this.entityData[2929 + var3] != -108
            && this.entityData[2929 + var3]
               != this.fileAData[this.recordStarts[173] + this.fileAData[this.recordStarts[174] + this.entityData[8749 + var1]] + this.entityData[4646 + var3]]
            && this.entityData[2929 + var3]
               != this.fileAData[this.recordStarts[176] + this.fileAData[this.recordStarts[177] + this.entityData[8749 + var1]] + this.entityData[4646 + var3]]
            )
          {
            this.a_int_int_boolean_boolean_void(var3, var1, false, false);
         }
      }
   }

   // $VF: renamed from: f (byte) void
   private void f_byte_void(byte var1) {
      if (this.entityData[8749 + var1] == 3 && this.entityData[4444 + this.tribeStats[0][17]] != var1) {
         this.menuItemIds[0] = this.menuSelectedIndex = 0;
         this.setPlaceMode(4);
      } else {
         this.a_boolean_boolean_boolean_boolean_void(true, false, true, false);
         this.menuTargetId = var1;
         this.interactMode = 2;
         this.selectedVillagerId = 0;
         this.buildContextMenu();
      }
   }

   // $VF: renamed from: Y () void
   private void scrollCamera() {
      if (!this.terrainDirty) {
         if (this.keyLeft) {
            if (this.cursorCol > this.homeCursorCol) {
               this.cursorCol--;
               return;
            }

            if (this.camTileX > 0) {
               this.camTileX--;
               this.scrollDirX = -1;
               return;
            }

            if (this.cursorCol > 0) {
               this.cursorCol--;
               return;
            }
         } else if (this.keyRight) {
            if (this.cursorCol < this.homeCursorCol) {
               this.cursorCol++;
               return;
            }

            if (this.camTileX < this.mapWidth - this.screenColsTiles) {
               this.camTileX++;
               this.scrollDirX = 1;
               return;
            }

            if (this.camTileX + this.cursorCol < this.mapWidth - 1 - 0) {
               this.cursorCol++;
               return;
            }
         } else if (this.keyUp) {
            if (this.cursorRow > this.homeCursorRow) {
               this.cursorRow--;
               return;
            }

            if (this.camTileY > 0 && !this.hudPanelVisible[4] || this.camTileY > 1 && this.hudPanelVisible[4]) {
               this.camTileY--;
               this.scrollDirY = -1;
               return;
            }

            if (this.cursorRow > 0) {
               this.cursorRow--;
               return;
            }
         } else if (this.keyDown) {
            if (this.cursorRow < this.homeCursorRow) {
               this.cursorRow++;
               return;
            }

            if (this.camTileY < this.mapHeight - this.screenRowsTiles - 1 - (this.bufferCopyX >> 5)) {
               this.camTileY++;
               this.scrollDirY = 1;
               return;
            }

            if (this.camTileY + this.cursorRow < this.mapHeight - 2 - 0 - (this.bufferCopyX >> 5)) {
               this.cursorRow++;
            }
         }
      }
   }

   // $VF: renamed from: Z () void
   private void buildContextMenu() {
      try {
         if (this.hudPanelVisible[2] || this.hudPanelVisible[5] || this.hudPanelVisible[6]) {
            this.menuItemTotal = 0;

            for (byte var1 = 0; var1 < this.menuItemIds.length; var1++) {
               this.menuItemIds[var1] = 0;
            }

            this.adjustMax = 99;
            if (this.hudPanelVisible[2]) {
               this.buildMenuContentsA();
               this.buildMenuContentsB();
               this.buildMenuContentsC();
            } else if (this.hudPanelVisible[5]) {
               this.buildSelectMenuContents();
            } else {
               if (!this.hudPanelVisible[6]) {
                  this.cancelMenu();
                  return;
               }

               int var4 = this.missionIndex > 1 ? 11 : (this.storySceneId < 18 ? 3 : 6);

               for (byte var2 = 0; var2 < var4; var2++) {
                  this.menuItemIds[var2] = this.fileAData[this.recordStarts[137] + var2];
                  this.menuItemTotal++;
               }
            }

            if (this.panelRefreshNeeded) {
               for (; this.menuScrollTop + this.menuVisibleRows > this.menuItemTotal && this.menuSelectedIndex != 0; this.menuSelectedIndex--) {
                  if (this.menuScrollTop > 0) {
                     this.menuScrollTop--;
                  }
               }
            } else {
               this.menuSelectedIndex = 0;
               this.menuScrollTop = 0;
               this.inspectedVillagerId = 0;
            }

            this.hudPanelDirty[this.activeHudPanel] = true;
            this.menuVisibleRows = menuMaxVisibleRows;
            if (this.menuItemTotal < this.menuVisibleRows) {
               this.menuVisibleRows = this.menuItemTotal;
            }

            this.menuHeightPx = menuRowHeightPx * this.menuVisibleRows;
            this.menuWidthPx = (240 - this.menuHeightPx + 9) / 2;
            this.refreshMenu();
         }
      } catch (Exception var3) {
      }
   }

   // $VF: renamed from: aa () void
   private void buildMenuContentsA() {
      if (this.entityData[8749 + this.menuTargetId] == 0) {
         this.storeRowIndex = 0;

         while (this.buildingStores[0][this.storeRowIndex][11] != this.menuTargetId) {
            this.storeRowIndex++;
         }

         this.firstWeaponIndex = -1;
         int var5 = this.entityData[9115 + this.menuTargetId] - 1;

         for (byte var2 = this.fileAData[this.recordStarts[144] + var5]; var2 < this.fileAData[this.recordStarts[144] + var5 + 1]; var2++) {
            if (0 + this.fileAData[this.recordStarts[143] + var2] >= 6 && this.firstWeaponIndex == -1) {
               this.firstWeaponIndex = (byte)(var2 - this.fileAData[this.recordStarts[144] + var5]);
            }

            this.menuItemIds[this.menuItemTotal] = this.fileAData[this.recordStarts[143] + var2];
            this.menuItemValues[var2 - this.fileAData[this.recordStarts[144] + var5]] = this.buildingStores[0][this.storeRowIndex][this.fileAData[this.recordStarts[143]
               + var2]];
            this.menuItemTotal++;
         }

         this.adjustMax = 9;
      } else if (this.entityData[8749 + this.menuTargetId] == 6) {
         if (this.selectedVillagerId > 0) {
            if (this.entityData[3636 + this.selectedVillagerId] > 0) {
               this.menuItemIds[this.menuItemTotal] = 29;
               this.menuItemTotal++;
            }
         } else if (this.fireHeldCount != -1) {
            for (byte var3 = 0; this.villagerGroups[this.fireHeldCount][var3] != 0; var3++) {
               if (this.entityData[3636 + this.villagerGroups[this.fireHeldCount][var3]] > 0) {
                  this.menuItemIds[this.menuItemTotal] = 29;
                  this.menuItemTotal++;
                  break;
               }
            }
         }

         this.tribeStats[0][6] = (short)(this.tribeStats[0][3] / 5);
         if (this.tribeStats[0][6] > this.adjustMax) {
            this.tribeStats[0][6] = this.adjustMax;
         }

         for (byte var4 = 0; var4 < 6; var4++) {
            if (this.tribeStats[0][5 + var4] > 0) {
               this.menuItemIds[this.menuItemTotal] = var4;
               this.menuItemValues[this.menuItemTotal] = (byte)this.tribeStats[0][5 + var4];
               this.menuItemTotal++;
            }
         }
      } else {
         if (this.entityData[8749 + this.menuTargetId] == 8) {
            for (byte var1 = 0; var1 < this.entityData[9115 + this.menuTargetId] * 2; var1++) {
               this.menuItemIds[this.menuItemTotal] = (byte)(19 + var1);
               this.menuItemTotal++;
            }
         }
      }
   }

   // $VF: renamed from: ab () void
   private void buildMenuContentsB() {
      for (byte var1 = 0; var1 < 8; var1++) {
         this.menuItemAvailable[var1] = 0;
      }

      byte var5 = 0;
      int var2 = 0;
      if (this.entityData[8810 + this.menuTargetId] > 0 && (this.entityData[8749 + this.menuTargetId] != 2 || this.entityData[8627 + this.menuTargetId] != 0)) {
         this.menuItemAvailable[0] = this.entityData[8810 + this.menuTargetId];
         var2++;
         var5 = 1;
      }

      for (byte var3 = this.entityData[8993 + this.menuTargetId]; var3 > 0; var3 = this.entityData[4545 + var3]) {
         if (this.entityData[1313 + var3] > 0
            && (
               this.entityData[8810 + this.menuTargetId] != var3
                  || this.entityData[8749 + this.menuTargetId] == 2 && this.entityData[8627 + this.menuTargetId] == 0
            )) {
            this.menuItemAvailable[var5 + this.entityData[4646 + var3]] = var3;
            var2++;
         }
      }

      byte var6 = 0;

      for (byte var4 = 0; var4 < var2; var4++) {
         while (this.menuItemAvailable[var6] == 0) {
            var6++;
         }

         this.menuItemIds[this.menuItemTotal] = (byte)(-this.menuItemAvailable[var6]);
         this.menuItemTotal++;
         var6++;
      }
   }

   // $VF: renamed from: ac () void
   private void buildMenuContentsC() {
      if (this.entityData[8627 + this.menuTargetId] == 2) {
         this.menuItemIds[this.menuItemTotal] = 28;
         this.menuItemTotal++;
      } else if (this.entityData[8627 + this.menuTargetId] != 1
         && this.entityData[8749 + this.menuTargetId] < 12
         && this.entityData[9115 + this.menuTargetId] < this.maxLevelPerType[this.entityData[8749 + this.menuTargetId]]) {
         this.menuItemIds[this.menuItemTotal] = 16;
         this.menuItemTotal++;
      }

      if (this.entityData[8627 + this.menuTargetId] == 1) {
         this.menuItemIds[this.menuItemTotal] = 27;
         this.menuItemTotal++;
      } else if (this.entityData[8505 + this.menuTargetId] < this.entityData[8566 + this.menuTargetId] && this.entityData[8749 + this.menuTargetId] < 12) {
         this.menuItemIds[this.menuItemTotal] = 26;
         this.menuItemTotal++;
      }

      this.menuItemIds[this.menuItemTotal] = 17;
      this.menuItemTotal++;
   }

   // $VF: renamed from: ad () void
   private void buildSelectMenuContents() {
      if (!this.chiefMode) {
         if (this.missionIndex != 3) {
            this.menuItemIds[this.menuItemTotal] = 10;
            this.menuItemTotal++;
         }

         this.menuItemIds[this.menuItemTotal] = (byte)(this.placeConfirmFlagN ? 15 : 12);
         this.menuItemTotal++;
         if (!this.placeConfirmFlagN) {
            this.menuItemIds[this.menuItemTotal] = (byte)(this.placeConfirmFlagL ? 14 : 11);
            this.menuItemTotal++;
         }

         this.menuItemIds[this.menuItemTotal] = 13;
         this.menuItemTotal++;
         this.menuItemIds[this.menuItemTotal] = (byte)(this.speedSetting + 23);
         this.menuItemTotal++;
      } else {
         for (this.drawEntityId = 1; this.drawEntityId <= 50; this.drawEntityId++) {
            if (this.entityData[1919 + this.drawEntityId] > 0 && this.entityData[1414 + this.drawEntityId] >= 12) {
               this.menuItemIds[this.menuItemTotal] = (byte)(-this.drawEntityId);
               this.menuItemTotal++;
            }
         }

         if (!this.menuItemActivated) {
            for (this.drawEntityId = 1; this.drawEntityId <= 50; this.drawEntityId++) {
               if (this.entityData[1919 + this.drawEntityId] > 0 && this.entityData[1414 + this.drawEntityId] < 12) {
                  this.menuItemIds[this.menuItemTotal] = (byte)(-this.drawEntityId);
                  this.menuItemTotal++;
               }
            }
         }

         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(false, false, false, true, true, false, false, false, false);
      }
   }

   // $VF: renamed from: ae () void
   private void handleMenuInput() {
      if (this.fireTapCount > 1) {
         this.fireTapCount = 0;
      } else {
         if (this.keyLeft && this.ticksSinceKeypress != 1) {
            if (this.menuSelectedIndex >= 0) {
               if (this.menuSelectedIndex > 0) {
                  this.menuSelectedIndex--;
               }

               if (this.menuSelectedIndex < this.menuScrollTop) {
                  this.menuScrollTop--;
               }

               this.selectionChanged = false;
               this.refreshMenu();
               return;
            }
         } else if (this.keyRight && this.ticksSinceKeypress != 1) {
            if (this.menuSelectedIndex < this.menuItemTotal - 1) {
               if (this.menuSelectedIndex >= this.menuVisibleRows - 1 && this.menuSelectedIndex - this.menuScrollTop == this.menuVisibleRows - 1) {
                  this.menuScrollTop++;
               }

               this.menuSelectedIndex++;
               this.selectionChanged = false;
               this.refreshMenu();
               return;
            }
         } else if (this.keyUp && this.screenDrawn) {
            if (this.hudPanelVisible[6]) {
               if (!this.menuScrollUp) {
                  this.scrollOffsetY--;
                  return;
               }
            } else if (this.menuItemValues[this.menuSelectedIndex] < this.adjustMax) {
               this.menuItemValues[this.menuSelectedIndex]++;
               this.buildingStores[0][this.storeRowIndex][this.menuItemIds[this.menuSelectedIndex]] = this.menuItemValues[this.menuSelectedIndex];
               return;
            }
         } else if (this.keyDown && this.screenDrawn) {
            if (this.hudPanelVisible[6]) {
               if (!this.menuScrollDown) {
                  this.scrollOffsetY++;
                  return;
               }
            } else if (this.menuItemValues[this.menuSelectedIndex] > 0) {
               this.menuItemValues[this.menuSelectedIndex]--;
               this.buildingStores[0][this.storeRowIndex][this.menuItemIds[this.menuSelectedIndex]] = this.menuItemValues[this.menuSelectedIndex];
               return;
            }
         } else {
            if (this.firePressed || leftSoftkeyPressed) {
               this.firePressed = false;
               leftSoftkeyPressed = false;
               this.fireTapped = false;
               this.fireHoldTicks = 4;
               if (this.interactMode == 4 && this.placeMode == 3) {
                  this.setPlaceMode(-2);
                  return;
               }

               if (this.placeMode == 4) {
                  return;
               }

               this.activateMenuItem();
               return;
            }

            if (rightSoftkeyPressed) {
               rightSoftkeyPressed = false;
               this.cancelMenu();
               return;
            }
         }
      }
   }

   // $VF: renamed from: af () void
   private void activateMenuItem() {
      if (this.menuItemIds[this.menuSelectedIndex] == 13) {
         this.chooseChief();
      } else if (this.menuItemIds[this.menuSelectedIndex] < 0) {
         if (this.chiefMode) {
            if (this.selectedVillagerId == this.inspectedVillagerId) {
               if (this.occupantGrid[this.entityData[101 + this.selectedVillagerId]][this.entityData[0 + this.selectedVillagerId]] != this.selectedVillagerId) {
                  this.a_int_int_boolean_boolean_void(this.selectedVillagerId, this.entityData[4444 + this.selectedVillagerId], true, true);
               }

               if (this.menuItemActivated) {
                  this.tribeStats[0][17] = this.selectedVillagerId;
                  this.menuItemActivated = false;
                  this.world.releaseHome(this.selectedVillagerId);
               }

               this.d_byte_void(this.selectedVillagerId);
               this.chiefMode = false;
            }

            this.selectedVillagerId = this.inspectedVillagerId;
         } else if (this.selectionChanged) {
            this.a_int_int_boolean_boolean_void(this.inspectedVillagerId, this.menuTargetId, true, true);
            this.selectionChanged = false;
         } else {
            this.selectionChanged = true;
            this.panelRefreshNeeded = true;
         }

         this.f_int_int_void(
            this.entityData[4646 + this.inspectedVillagerId] >= 0
                  && this.entityData[2828 + this.inspectedVillagerId] != 92
                  && this.entityData[2828 + this.inspectedVillagerId] != 106
               ? -this.entityData[4444 + this.inspectedVillagerId]
               : this.inspectedVillagerId,
            0
         );
      } else if (this.hudPanelVisible[6]) {
         this.clearDragSelect();
         this.setPlaceMode(0);
         this.placementType = this.fileAData[this.recordStarts[138] + 416 + this.menuItemIds[this.menuSelectedIndex]];
      } else if (this.menuItemIds[this.menuSelectedIndex] == 17) {
         this.setPlaceMode(3);
      } else if (this.menuItemIds[this.menuSelectedIndex] == 16 || this.menuItemIds[this.menuSelectedIndex] == 26) {
         this.setEntityState(this.menuTargetId, this.menuItemIds[this.menuSelectedIndex] == 16);
         this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         this.interactMode = 0;
      } else if (this.menuItemIds[this.menuSelectedIndex] == 27) {
         this.entityData[8566 + this.menuTargetId] = this.fileAData[this.recordStarts[138]
            + (7 + this.entityData[9115 + this.menuTargetId] - 1) * 26
            + this.entityData[8749 + this.menuTargetId]];
         if (this.entityData[8505 + this.menuTargetId] > this.entityData[8566 + this.menuTargetId]) {
            this.entityData[8505 + this.menuTargetId] = this.entityData[8566 + this.menuTargetId];
         }

         if (this.entityData[8505 + this.menuTargetId]
            > this.fileAData[this.recordStarts[138] + (7 + this.entityData[9115 + this.menuTargetId] - 1) * 26 + this.entityData[8749 + this.menuTargetId]]) {
            this.entityData[8505 + this.menuTargetId] = this.fileAData[this.recordStarts[138]
               + (7 + this.entityData[9115 + this.menuTargetId] - 1) * 26
               + this.entityData[8749 + this.menuTargetId]];
         }

         this.entityData[9054 + this.menuTargetId] = (byte)(this.entityData[9054 + this.menuTargetId] | 1);
         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] == 28) {
         this.entityData[9054 + this.menuTargetId] = (byte)(this.entityData[9054 + this.menuTargetId] | 1);
         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] == 10) {
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(true, false, false, false, false, true, false, false, false);
         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] == 11) {
         this.placingActive = true;
         this.setPlaceMode(2);
         this.placementType = 100;
         this.clearDragSelect();
      } else if (this.menuItemIds[this.menuSelectedIndex] == 14) {
         this.placeConfirmFlagL = false;
         this.placingActive = false;
         this.world.clearDistressFlags();
         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] == 12) {
         this.placeModeChanged = true;
         this.setPlaceMode(2);
         this.placementType = 100;
         this.clearDragSelect();
      } else if (this.menuItemIds[this.menuSelectedIndex] == 15) {
         this.placeConfirmFlagN = false;
         this.placeModeChanged = false;
         this.world.clearDistressFlags();
         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] >= 23 && this.menuItemIds[this.menuSelectedIndex] <= 25) {
         this.speedSetting++;
         if (this.speedSetting > 2) {
            this.speedSetting = 0;
         }

         this.panelRefreshNeeded = true;
      } else if (this.menuItemIds[this.menuSelectedIndex] >= 6 && this.menuItemIds[this.menuSelectedIndex] <= 8) {
         this.setPlaceMode(2);
         this.placementType = 20 + this.menuItemIds[this.menuSelectedIndex] - 6;
      } else if (this.menuItemIds[this.menuSelectedIndex] >= 6 && this.menuItemIds[this.menuSelectedIndex] != 29) {
         if (this.menuItemIds[this.menuSelectedIndex] >= 19 && this.interactMode != 3) {
            this.setPlaceMode(this.menuItemIds[this.menuSelectedIndex] == 22 ? 2 : 1);
            this.placementType = (byte)(this.menuItemIds[this.menuSelectedIndex] - 19);
         }
      } else {
         this.assignJob();
      }
   }

   // $VF: renamed from: ag () void
   private void assignJob() {
      if (this.entityData[8749 + this.menuTargetId] == 6) {
         byte var1 = 3;
         if (this.menuItemIds[this.menuSelectedIndex] == 29) {
            var1 = 2;
         }

         if (this.selectedVillagerId > 0) {
            this.entityData[3131 + this.selectedVillagerId] = (byte)(this.cursorCol + this.camTileX);
            this.entityData[3232 + this.selectedVillagerId] = (byte)(this.cursorRow + this.camTileY);
            this.entityData[2828 + this.selectedVillagerId] = (byte)(44 + this.menuItemIds[this.menuSelectedIndex]);
            this.entityData[6161 + this.selectedVillagerId] = var1;
            this.a_boolean_boolean_boolean_boolean_void(false, true, false, true);
            this.interactMode = 1;
         } else if (this.fireHeldCount == -1) {
            this.setPlaceMode(1);
            this.placementType = -this.menuItemIds[this.menuSelectedIndex] - 1;
         } else {
            for (int var2 = 0; this.villagerGroups[this.fireHeldCount][var2] > 0; var2++) {
               this.entityData[3131 + this.villagerGroups[this.fireHeldCount][var2]] = (byte)(this.cursorCol + this.camTileX);
               this.entityData[3232 + this.villagerGroups[this.fireHeldCount][var2]] = (byte)(this.cursorRow + this.camTileY);
               this.entityData[2828 + this.villagerGroups[this.fireHeldCount][var2]] = (byte)(44 + this.menuItemIds[this.menuSelectedIndex]);
               this.entityData[6161 + this.villagerGroups[this.fireHeldCount][var2]] = var1;
            }

            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            this.interactMode = 1;
         }

         this.inspectedVillagerId = this.selectedVillagerId;
      }
   }

   // $VF: renamed from: ah () void
   private void cancelMenu() {
      if (!this.menuItemActivated) {
         this.panelRefreshNeeded = false;
         if (this.interactMode == 4) {
            this.setPlaceMode(-1);
         } else {
            if (this.selectedVillagerId != 0) {
               this.interactMode = 1;
               this.inspectedVillagerId = this.selectedVillagerId;
               this.a_boolean_boolean_boolean_boolean_void(false, true, false, true);
            } else if (this.fireHeldCount != -1) {
               this.interactMode = 1;
               this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            } else {
               this.interactMode = 0;
               this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            }

            this.menuTargetId = 0;
            this.chiefMode = false;
            this.resetInputFlags();
            this.selectionChanged = false;
         }
      }
   }

   // $VF: renamed from: ai () void
   private void refreshMenu() {
      this.hudPanelDirty[2] = true;
      if (this.activeHudPanel == 6) {
         this.menuListChanged = this.menuSelectedIndex > 0;
         this.menuSelectionChanged = this.menuSelectedIndex < this.menuItemTotal - 1;
      } else {
         this.menuListChanged = this.menuScrollTop > 0;
         this.menuSelectionChanged = this.menuScrollTop + this.menuVisibleRows < this.menuItemTotal;
      }

      if (this.menuItemIds[this.menuSelectedIndex] < 0) {
         this.inspectedVillagerId = (byte)(-this.menuItemIds[this.menuSelectedIndex]);
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
            false, false, this.hudPanelVisible[2], true, this.hudPanelVisible[5], false, false, false, false
         );
      } else if (this.hudPanelVisible[4]) {
         this.inspectedVillagerId = 0;
         this.a_boolean_boolean_boolean_boolean_void(true, false, true, false);
      }

      this.hudPanelVisible[8] = false;
      this.hudPanelVisible[7] = false;
      if (this.activeHudPanel != 6
         && (this.menuItemIds[this.menuSelectedIndex] < 6 || this.menuItemIds[this.menuSelectedIndex] > 8)
         && this.menuItemIds[this.menuSelectedIndex] != 16) {
         if (this.menuItemIds[this.menuSelectedIndex] >= 0 && this.menuItemIds[this.menuSelectedIndex] < 6) {
            this.hudPanelVisible[7] = this.hudPanelDirty[7] = true;
         }
      } else {
         this.hudPanelVisible[8] = this.hudPanelDirty[8] = true;
      }
   }

   // $VF: renamed from: aj () void
   private void chooseChief() {
      this.chiefMode = true;
      this.menuScrollTop = this.menuSelectedIndex = 0;
      this.selectedVillagerId = 0;

      while (this.entityData[1919 + this.inspectedVillagerId] <= 0) {
         this.inspectedVillagerId++;
      }

      this.panelRefreshNeeded = true;
   }

   // $VF: renamed from: l (int) void
   private void setPlaceMode(int var1) {
      this.placeMode = var1;
      if (this.placeMode > -1) {
         this.hudValues[4] = (short)(33 + this.placeMode);
         this.hudElemSeen[4] = 1;
         this.savedMode = this.interactMode;
         this.hudPanelVisible9 = new boolean[]{
            this.hudPanelVisible[0],
            this.hudPanelVisible[1],
            this.hudPanelVisible[2],
            this.hudPanelVisible[4],
            this.hudPanelVisible[5],
            this.hudPanelVisible[6],
            this.hudPanelVisible[7],
            this.hudPanelVisible[8],
            this.hudPanelVisible[9]
         };
         if (this.placeMode <= 5) {
            this.a_boolean_boolean_boolean_boolean_void(true, false, true, false);
         } else {
            this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
               this.hudPanelVisible[0],
               false,
               true,
               this.hudPanelVisible[4],
               this.hudPanelVisible[5],
               this.hudPanelVisible[6],
               this.hudPanelVisible[7],
               this.hudPanelVisible[8],
               this.hudPanelVisible[9]
            );
         }

         this.interactMode = (byte)(this.placeMode >= 3 ? 4 : 3);
         this.hudPanelDirty[2] = true;
      } else if (this.placeMode == -2) {
         this.c_int_boolean_void(this.menuTargetId, true);
         this.hudValues[4] = 0;
      } else {
         this.interactMode = this.savedMode;
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
            this.hudPanelVisible9[0],
            this.hudPanelVisible9[1],
            this.hudPanelVisible9[2],
            this.hudPanelVisible9[3],
            this.hudPanelVisible9[4],
            this.hudPanelVisible9[5],
            this.hudPanelVisible9[6],
            this.hudPanelVisible9[7],
            this.hudPanelVisible9[8]
         );
         this.hudValues[4] = 0;
         if (this.placementType == 100) {
            this.cancelMenu();
         } else {
            this.panelRefreshNeeded = true;
         }

         this.placeModeChanged = this.placingActive = false;
      }

      this.placementType = 0;
   }

   // $VF: renamed from: d (int) void
   final void refreshPanelIfMenuTarget(int var1) {
      if (this.interactMode == 2 && var1 == this.menuTargetId) {
         this.panelRefreshNeeded = true;
      }
   }

   // $VF: renamed from: b (int, boolean) void
   final void setEntityState(int var1, boolean var2) {
      this.world.registerUpgrade((byte)var1);

      for (byte var3 = this.entityData[8993 + var1]; var3 > 0; var3 = this.entityData[4545 + var3]) {
         this.a_int_int_boolean_boolean_void(var3, var1, false, false);
      }

      this.entityData[8627 + var1] = (byte)(var2 ? 1 : 2);
      if (var2) {
         this.entityData[8566 + var1] = this.fileAData[this.recordStarts[138] + (7 + this.entityData[9115 + var1]) * 26 + this.entityData[8749 + var1]];
      }
   }

   // $VF: renamed from: ak () void
   private void dispatchMenuInput() {
      try {
         this.resumeFromPause();
         if (this.screenId == 22 || this.screenId == 23) {
            this.showLoadSaveResult();
         } else if (rightSoftkeyPressed) {
            rightSoftkeyPressed = false;
            this.menuBack();
         } else if ((this.firePressed || leftSoftkeyPressed || this.storyAdvancePressed)
            && this.storyStepDir <= 0
            && this.storyImageId == 0
            && (this.screenId < 28 || this.screenId == 32)) {
            this.firePressed = this.storyAdvancePressed = false;
            leftSoftkeyPressed = false;
            this.fireTapped = false;
            this.fireHoldTicks = 4;
            if (this.screenId == 32) {
               return;
            }

            this.menuSelect();
         } else if (this.keyUp) {
            this.menuCursorUp();
         } else if (this.keyDown) {
            this.menuCursorDown();
         }

         this.handleScreenTransition();
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: al () void
   private void resumeFromPause() {
      if (this.screenId == 27 && (this.firePressed || leftSoftkeyPressed)) {
         this.firePressed = false;
         leftSoftkeyPressed = false;
         this.fireTapped = false;
         this.fireHoldTicks = 4;
         this.resetInputFlags();
         this.screenId = this.prevScreen;
         this.prevScreen = this.prevPrevScreen;
         this.menuNeedsDraw = true;
         this.dialogId = this.savedDialogId;
         if (this.musicLevel > 0) {
            this.selectMusicTrack();
         }
      }
   }

   // $VF: renamed from: am () void
   private void showLoadSaveResult() {
      this.screenId = this.prevScreen;
      this.menuNeedsDraw = true;
      if (this.loadAction == 0) {
         this.g_byte_void(this.missionIndex);
         if (this.screenId == 18) {
            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            this.endScreenPhase = 0;
            this.screenId = 28;
            this.advanceStoryStep(this.fileAData[this.recordStarts[303] + this.missionIndex]);
            this.screenId = 18;
         }
      } else if (this.loadAction == 1) {
         if (this.endgameTimer > 0 && this.endgameTimer < 41) {
            this.savedMissionProgress++;
         }

         if (!this.saveSlot(this.missionIndex < 7 ? 1 : 2)) {
            if (this.missionIndex < 7) {
               this.savedMissionProgress = -1;
            } else {
               this.customGameAvailable = false;
            }

            this.dialogId = 66;
         } else {
            this.dialogId = 67;
            this.saveSlot(0);
         }

         this.prevScreen = (byte)(this.missionIndex < 7 ? (this.savedMissionProgress > -1 ? 10 : 11) : (this.customGameAvailable ? 10 : 11));
         this.screenContentChanged = true;
         this.mode = 1;
         this.scrollRedrawTerrain();
         this.renderWorldEntities();
         this.mode = 0;
      } else {
         if (this.loadAction == 2 && !this.loadSlot(this.missionIndex < 7 ? 1 : 2)) {
            this.prevScreen = this.screenId;
            this.loadAction = 0;
            this.screenId = 23;
         }
      }
   }

   // $VF: renamed from: an () void
   private void menuBack() {
      if (this.screenId == 32) {
         this.screenId = this.homeScreenId;
         this.menuNeedsDraw = true;
      } else {
         if (this.fileAData[this.recordStarts[153] + 29 + this.screenId] == 1) {
            if (this.screenId == 25) {
               this.setPlaceMode(-1);
               this.panelRefreshNeeded = false;
               this.menuBackPressed = false;
            }

            this.screenId = 28;
         } else if (this.screenId == this.homeScreenId) {
            this.prevScreen = this.screenId;
            this.screenId = 30;
         } else if (this.screenId == 20) {
            if (this.fileAData[this.recordStarts[153] + 145 + 20] == 0) {
               this.savedCursorCol = this.cursorCol;
               this.savedCursorRow = this.cursorRow;
               this.savedCamCol = this.camTileX;
               this.savedCamRow = this.camTileY;
               this.screenId = 28;
               this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
                  this.popupButtonFlags[0],
                  this.popupButtonFlags[1],
                  this.popupButtonFlags[2],
                  this.popupButtonFlags[3],
                  this.popupButtonFlags[4],
                  this.popupButtonFlags[5],
                  this.popupButtonFlags[6],
                  this.popupButtonFlags[7],
                  this.popupButtonFlags[8]
               );
            }
         } else if (this.screenId == 16 && this.dialogId == 60) {
            this.loadAction = 0;
            this.screenId = 22;
            this.dialogId = 69;
            this.prevScreen = 18;
         } else if (this.screenId == 2) {
            this.screenId = this.homeScreenId;
            this.menuCursor = 0;
         } else if (this.screenId == this.campaignScreenId || this.screenId == 9) {
            this.screenId = 13;
            this.menuCursor = 1;
         } else if (this.screenId == 14) {
            this.quitGame();
         } else if (this.fileAData[this.recordStarts[153] + 58 + this.screenId] == 1) {
            if (this.screenId == 12) {
               this.saveSlot(0);
            }

            this.screenId = this.prevScreen;
            this.prevScreen = this.prevPrevScreen;
            this.menuCursor = this.savedMenuCursor;
            this.endScreenPhase = 0;
            this.gameActive = true;
         } else if (this.fileAData[this.recordStarts[153] + 58 + this.screenId] > 1) {
            this.screenId = this.fileAData[this.recordStarts[153] + 58 + this.screenId];
            this.prevScreen = this.prevPrevScreen;
            this.menuCursor = this.savedMenuCursor;
            this.endScreenPhase = 0;
         }

         this.menuNeedsDraw = true;
      }
   }

   // $VF: renamed from: ao () void
   private void menuSelect() {
      byte var1 = this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + this.screenId] + this.menuCursor];
      if (this.cheatUnlockedCustom) {
         this.campaignScreenId = 8;
         this.campaignProgress = 4;
      }

      if (this.fileAData[this.recordStarts[153] + 87 + this.screenId] == 1) {
         this.screenId = this.prevScreen;
      } else if (this.screenId == 19) {
         this.savedCursorCol = this.cursorCol;
         this.savedCursorRow = this.cursorRow;
         this.savedCamCol = this.camTileX;
         this.savedCamRow = this.camTileY;
         this.screenId = 28;
      } else if (this.screenId == 20) {
         this.popupConfirm();
      } else if (this.screenId == 18 || this.screenId == 17) {
         this.advanceStoryStep(-1);
      } else if (this.screenId == 15) {
         this.handleMissionEnd();
      } else if (this.screenId == 16) {
         this.handleScreen16();
      } else if (this.screenId == 12) {
         this.toggleOptions();
      } else if (this.screenId == 13) {
         this.selectDifficulty();
      } else if (this.screenId == this.campaignScreenId) {
         this.selectCampaignMission();
      } else if (this.screenId == 9) {
         this.missionIndex = this.fileAData[this.recordStarts[249] + var1 - 25];
         this.loadAction = 0;
         this.screenId = 22;
         this.dialogId = 69;
         this.prevScreen = 29;
      } else if (this.screenId == 24) {
         this.showMissionEndScore();
      } else if (this.screenId == 25) {
         this.menuCursor++;
         if (this.menuCursor >= this.fileAData[this.recordStarts[307] + this.endScreenPhase]) {
            this.menuCursor = 0;
         }

         this.hudValues[4] = (short)(
            33 + this.fileAData[this.recordStarts[305] + this.fileAData[this.recordStarts[306] + this.endScreenPhase] + this.menuCursor]
         );
         this.hudPanelDirty[2] = true;
      } else if (this.screenId == 14) {
         languageIndex = (byte)this.menuCursor;
         this.textLoader.loadTextFile(0);
         this.textLoader.loadTextFile(1);
         this.screenId = 16;
      } else if (var1 == 6) {
         this.screenId = 28;
      } else if (var1 == 0) {
         this.prevScreen = this.screenId;
         this.screenId = 2;
         this.savedMenuCursor = this.menuCursor;
      } else if (var1 == 12) {
         this.missionIndex = 0;
         this.prevScreen = this.screenId;
         if (this.continueProgress > -1) {
            this.screenId = 3;
         } else {
            this.screenId = 13;
            this.menuCursor = 1;
         }

         this.savedMenuCursor = 0;
      } else if (var1 == 13) {
         this.openCustomGame();
      } else if (var1 == 10) {
         this.navigateMenuX();
      } else if (var1 == 11) {
         this.screenId = 31;
      } else if (var1 == 9) {
         this.navigateMenuY();
      } else if (var1 == 5) {
         this.prevScreen = this.screenId;
         this.screenId = 30;
         this.savedMenuCursor = this.menuCursor;
      } else if (var1 == 1) {
         this.navigateMenuZ();
         this.fileAData[this.recordStarts[153] + 116 + this.screenId] = 4;
         this.endScreenPhase = 1;
         this.textLoader.selectStorySection(70);
      } else if (var1 == 3) {
         this.navigateMenuZ();
         this.fileAData[this.recordStarts[153] + 116 + this.screenId] = 2;
         this.endScreenPhase = 0;
         this.textLoader.selectStorySection(74);
      } else if (var1 == 2) {
         this.prevScreen = this.screenId;
         this.screenId = 12;
         this.savedMenuCursor = this.menuCursor;
         this.menuCursor = 1;
         this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + 12] + 1] = (byte)(17 + this.musicLevel / 2);
         this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + 12] + 3] = (byte)(17 + this.chiefId);
      } else if (var1 == 4) {
         this.screenId = 32;
      } else if (var1 == 7) {
         this.navigateMenuA();
      } else if (var1 == 8) {
         this.prevScreen = this.screenId;
         this.screenId = 16;
         this.savedMenuCursor = this.menuCursor;
         this.dialogId = 63;
      }

      this.menuNeedsDraw = true;
   }

   // $VF: renamed from: ap () void
   private void popupConfirm() {
      if (this.fileAData[this.recordStarts[153] + 145 + 20] == 0) {
         if (this.popupKind != 14 && this.popupKind != 15) {
            this.f_int_int_void(this.stepAction, 0);
         } else {
            this.removeFromGroups(this.subjectEntityId);
            this.f_int_void(this.subjectEntityId);
            this.world.currentVillager = this.subjectEntityId;
            this.world.tribeIndex = 0;
            if (this.world.findNearestBuilding(this.popupKind == 14 ? 4 : 2)) {
               this.entityData[6161 + this.subjectEntityId] = 2;
            }
         }

         this.clearDragSelect();
         if (this.interactMode == 3) {
            this.setPlaceMode(-1);
            this.cancelMenu();
         } else {
            this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
               this.popupButtonFlags[0],
               this.popupButtonFlags[1],
               this.popupButtonFlags[2],
               this.popupButtonFlags[3],
               this.popupButtonFlags[4],
               this.popupButtonFlags[5],
               this.popupButtonFlags[6],
               this.popupButtonFlags[7],
               this.popupButtonFlags[8]
            );
         }
      } else {
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
            this.popupButtonFlags[0],
            this.popupButtonFlags[1],
            this.popupButtonFlags[2],
            this.popupButtonFlags[3],
            this.popupButtonFlags[4],
            this.popupButtonFlags[5],
            this.popupButtonFlags[6],
            this.popupButtonFlags[7],
            this.popupButtonFlags[8]
         );
      }

      this.savedCursorCol = this.cursorCol;
      this.savedCursorRow = this.cursorRow;
      this.savedCamCol = this.camTileX;
      this.savedCamRow = this.camTileY;
      this.screenId = 28;
      if (this.menuItemActivated) {
         this.startupPhase = 0;
         this.clearDragSelect();
         this.setPlaceMode(-1);
         this.panelRefreshNeeded = false;
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(true, false, false, false, true, false, false, false, false);
         this.interactMode = 2;
         this.hudPanelDirty[4] = true;
         this.buildContextMenu();
         this.chooseChief();
      }
   }

   // $VF: renamed from: aq () void
   private void handleMissionEnd() {
      if (this.endScreenPhase < 4) {
         if (this.endScreenPhase > 0) {
            if (this.endScreenPhase == 3) {
               this.fileAData[this.recordStarts[153] + 116 + this.screenId] = 2;
            }

            this.textLoader.selectStorySection(70 + this.endScreenPhase);
            this.dialogId = 71;
            this.endScreenPhase++;
         } else {
            if (this.endScreenPhase == -1) {
               if (this.endgameTimer == 41) {
                  this.resetWorldState();
                  if (this.missionIndex < 5) {
                     this.missionIndex++;
                     this.loadAction = 0;
                     this.screenId = 23;
                     this.dialogId = 69;
                     this.prevScreen = 18;
                     this.continueProgress = this.fileAData[this.recordStarts[248] + this.missionIndex];
                     if (this.campaignProgress < this.continueProgress) {
                        this.campaignProgress = this.continueProgress;
                     }

                     this.campaignScreenId = (byte)(4 + this.campaignProgress);
                     this.saveSlot(0);
                  } else if (this.missionIndex == 6) {
                     this.tickCounter = -1;
                     this.screenId = this.homeScreenId;
                     this.advanceStoryStep(59);
                     this.missionIndex++;
                     this.screenId = 18;
                  } else {
                     this.tickCounter = -1;
                     this.screenId = this.homeScreenId;
                  }

                  this.menuCursor = 0;
                  return;
               }

               if (this.missionIndex == 1 || this.missionIndex == 6) {
                  this.missionIndex--;
               }

               this.resetWorldState();
               this.prevScreen = this.homeScreenId;
               this.screenId = 16;
               this.dialogId = 58;
               this.menuCursor = 0;
               this.tickCounter = -1;
            }
         }
      }
   }

   // $VF: renamed from: ar () void
   private void handleScreen16() {
      if (this.dialogId == 68) {
         this.quitGame();
      } else if (this.dialogId == 65) {
         this.resetWorldState();
         this.screenId = this.homeScreenId;
         this.menuCursor = 0;
         this.tickCounter = -1;
         this.uptimeTicks = 0;
      } else if (this.dialogId == 58) {
         this.loadAction = 0;
         this.screenId = 22;
         this.dialogId = 69;
         this.prevScreen = (byte)(this.missionIndex < 7 ? 18 : 29);
      } else if (this.dialogId == 59) {
         this.musicLevel = 2;
         this.gameActive = true;
         this.selectMusicTrack();
         this.prevScreen = this.screenId = this.homeScreenId;
      } else if (this.dialogId == 64) {
         this.loadAction = 1;
         this.screenId = 22;
         this.dialogId = 69;
         this.prevScreen = 21;
      } else if (this.dialogId == 63) {
         this.screenId = 31;
      } else if (this.dialogId == 60) {
         this.newGameFlag = 1;
         this.loadAction = 0;
         this.screenId = 22;
         this.dialogId = 69;
         this.prevScreen = 18;
      } else {
         if (this.dialogId == 62) {
            this.continueProgress = -1;
            this.savedMissionProgress = -2;
            this.screenId = 13;
            this.prevScreen = 2;
            this.menuCursor = 1;
            this.saveSlot(0);
         }
      }
   }

   // $VF: renamed from: as () void
   private void toggleOptions() {
      if (this.menuCursor == 1) {
         this.musicLevel = (byte)(this.musicLevel == 0 ? 2 : 0);
         this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + 12] + 1] = (byte)(17 + this.musicLevel / 2);
         if (this.midiPlayer != null) {
            this.midiPlayer.setVolume(this.musicLevel);
         } else {
            this.stopMusic();
            this.selectMusicTrack();
         }
      } else {
         if (this.menuCursor == 3) {
            this.chiefId = this.chiefId == 0 ? 1 : 0;
            this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + 12] + 3] = (byte)(17 + this.chiefId);
         }
      }
   }

   // $VF: renamed from: at () void
   private void selectDifficulty() {
      this.selectedDifficulty = (byte)(this.menuCursor / 2);
      if (this.missionIndex >= 7) {
         this.screenId = 9;
         this.customDifficulty = this.selectedDifficulty;
      } else {
         if (this.campaignProgress == 0) {
            this.screenId = 16;
            this.dialogId = 60;
            this.newGameFlag = 0;
            this.continueProgress = 0;
         } else {
            this.screenId = this.campaignScreenId;
         }

         this.campaignDifficulty = this.selectedDifficulty;
         this.saveSlot(0);
      }

      this.menuCursor = 0;
   }

   // $VF: renamed from: au () void
   private void selectCampaignMission() {
      this.missionIndex = this.fileAData[this.recordStarts[249]
         + this.fileAData[this.recordStarts[151] + this.fileAData[this.recordStarts[152] + this.screenId] + this.menuCursor]
         - 25];
      this.continueProgress = this.fileAData[this.recordStarts[248] + this.missionIndex];
      if (this.missionIndex == 0) {
         this.screenId = 16;
         this.dialogId = 60;
         this.newGameFlag = 0;
      } else {
         this.loadAction = 0;
         this.screenId = 22;
         this.dialogId = 69;
      }

      this.prevScreen = 18;
      this.saveSlot(0);
   }

   // $VF: renamed from: av () void
   private void showMissionEndScore() {
      this.screenId = 15;
      this.menuCursor = 0;
      this.fileAData[this.recordStarts[153] + 116 + this.screenId] = 0;
      this.fileAData[this.recordStarts[153] + 145 + this.screenId] = 2;
      this.fileAData[this.recordStarts[153] + 58 + this.screenId] = 0;
      this.textLoader.selectStorySection(75);
      this.dialogId = 71;
      this.endScreenPhase = -1;

      for (int var1 = 0; var1 < 2; var1++) {
         for (int var2 = 0; var2 < 5; var2++) {
            this.tribeStats[var1][24] = (short)(
               this.tribeStats[var1][24] + this.tribeStats[var1][19 + var2] * this.fileAData[this.recordStarts[112] + var2] / 10
            );
         }
      }
   }

   // $VF: renamed from: aw () void
   private void openCustomGame() {
      this.missionIndex = 7;
      this.prevScreen = this.screenId;
      if (this.customGameAvailable) {
         this.screenId = 3;
         this.menuCursor = 0;
      } else if (this.campaignProgress <= 0 && !this.cheatUnlockedCustom) {
         this.dialogId = 70;
         this.screenId = 21;
         this.menuCursor = 0;
      } else {
         this.screenId = 13;
         this.menuCursor = 1;
      }

      this.savedMenuCursor = 1;
   }

   // $VF: renamed from: ax () void
   private void navigateMenuX() {
      this.menuCursor = 0;
      this.prevScreen = this.screenId;
      if (this.missionIndex < 7) {
         if (this.continueProgress > -1) {
            this.screenId = 16;
            this.dialogId = 62;
         } else if (this.screenId == 4) {
            this.screenId = 16;
            this.dialogId = 60;
            this.newGameFlag = 0;
         } else {
            this.screenId = this.campaignScreenId;
         }
      } else {
         this.screenId = 13;
         this.menuCursor = this.savedMenuCursor = 1;
      }
   }

   // $VF: renamed from: ay () void
   private void navigateMenuY() {
      if (!this.menuActionPending) {
         this.prevScreen = this.screenId;
         this.screenId = 16;
         this.dialogId = 65;
      } else {
         this.resetWorldState();
         this.screenId = this.homeScreenId;
         this.menuCursor = 0;
         this.tickCounter = -1;
         this.uptimeTicks = 0;
      }
   }

   // $VF: renamed from: az () void
   private void navigateMenuZ() {
      this.prevScreen = this.screenId;
      this.screenId = 15;
      this.savedMenuCursor = this.menuCursor;
      this.dialogId = 71;
      this.fileAData[this.recordStarts[153] + 145 + this.screenId] = 0;
      this.fileAData[this.recordStarts[153] + 58 + this.screenId] = 1;
   }

   // $VF: renamed from: aA () void
   private void navigateMenuA() {
      this.prevScreen = this.screenId;
      this.savedMenuCursor = this.menuCursor;
      this.menuActionPending = true;
      if (this.missionIndex < 7 ? this.savedMissionProgress <= -1 : !this.customGameAvailable) {
         this.loadAction = 1;
         this.screenId = 22;
         this.dialogId = 69;
         this.prevScreen = 21;
      } else {
         this.screenId = 16;
         this.dialogId = 64;
      }
   }

   // $VF: renamed from: aB () void
   private void menuCursorUp() {
      if (this.fileAData[this.recordStarts[153] + 174 + this.screenId] == 1) {
         if (!this.menuScrollUp) {
            this.scrollOffsetY--;
            return;
         }
      } else {
         if (this.screenId == 12) {
            this.menuCursor--;
         } else if (this.screenId == 13 && this.menuCursor == 1) {
            this.menuCursor--;
         }

         this.menuCursor = this.menuCursor > 0 ? this.menuCursor - 1 : this.menuItemCount - 1;
      }
   }

   // $VF: renamed from: aC () void
   private void menuCursorDown() {
      if (this.fileAData[this.recordStarts[153] + 174 + this.screenId] == 1) {
         if (!this.menuScrollDown) {
            this.scrollOffsetY++;
            return;
         }
      } else {
         this.menuCursor = this.menuCursor < this.menuItemCount - 1 ? this.menuCursor + 1 : 0;
         if (this.screenId == 12) {
            this.menuCursor = this.menuCursor / 2 * 2 + 1;
            return;
         }

         if (this.screenId == 13 && this.menuCursor == 0) {
            this.menuCursor++;
         }
      }
   }

   // $VF: renamed from: aD () void
   private void handleScreenTransition() {
      if (this.screenId == 31) {
         if (this.savedMissionProgress < this.continueProgress && this.missionIndex < 7) {
            this.missionIndex = this.continueProgress;
            if (this.missionIndex >= 1) {
               this.missionIndex++;
            }

            this.loadAction = 0;
            this.screenId = 22;
            this.dialogId = 69;
            this.prevScreen = 18;
         } else {
            this.loadAction = 2;
            this.screenId = 22;
            this.dialogId = 69;
            this.prevScreen = 28;
         }

         this.selectedDifficulty = this.campaignDifficulty;
      }

      if (this.screenId == 28) {
         this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
            this.hudPanelVisible[0],
            this.hudPanelVisible[1],
            this.hudPanelVisible[2],
            this.hudPanelVisible[4],
            this.hudPanelVisible[5],
            this.hudPanelVisible[6],
            this.hudPanelVisible[7],
            this.hudPanelVisible[8],
            this.hudPanelVisible[9]
         );
         this.fastTick = false;
         this.cursorCol = this.savedCursorCol;
         this.cursorRow = this.savedCursorRow;
         this.camTileX = this.savedCamCol;
         this.camTileY = this.savedCamRow;
         this.screenContentChanged = true;
         this.mode = 1;
         this.resetInputFlags();
      } else if (this.screenId == 19) {
         this.scrollCamera();
         this.centerMinimapViewport();
      } else if (this.screenId == 30) {
         this.screenId = 16;
         this.dialogId = 68;
         this.menuNeedsDraw = true;
      } else {
         if (this.screenId == 29) {
            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            this.screenContentChanged = true;
            this.screenId = 28;
         }
      }
   }

   // $VF: renamed from: aE () void
   private void startDragSelect() {
      this.selectedGroup++;
      if (this.selectedGroup > 4) {
         this.dragSelecting = true;
         this.fireHeld = false;
         this.selectedGroup = 0;
         this.fireHeldCount = 4;
         this.selectedVillagerId = 0;
         this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         this.menuTargetId = 0;
         this.dragStartTileX = this.cursorCol + this.camTileX;
         this.dragStartTileY = this.cursorRow + this.camTileY;
         this.clearGroup4();
      }
   }

   // $VF: renamed from: aF () void
   private void endDragSelect() {
      this.dragSelecting = false;
      this.fireHeld = false;
      this.clearGroup4();
      this.selCountTemp = 0;

      for (this.dragRectScanY = this.selRectY + 1; this.dragRectScanY <= this.selRectY + this.selRectH; this.dragRectScanY++) {
         for (this.dragRectScanX = this.selRectX + 1; this.dragRectScanX <= this.selRectX + this.selRectW; this.dragRectScanX++) {
            this.groupMemberTmp = this.occupantGrid[this.dragRectScanY][this.dragRectScanX];
            if (this.groupMemberTmp >= 1 && this.groupMemberTmp <= 50 && this.entityData[1919 + this.groupMemberTmp] > 0) {
               if (this.selCountTemp < 50) {
                  this.villagerGroups[4][this.selCountTemp] = this.groupMemberTmp;
                  this.entityData[909 + this.groupMemberTmp] = 4;
               }

               this.selCountTemp++;
            }
         }
      }

      if (this.villagerGroups[4][0] == 0) {
         this.interactMode = 0;
         this.selectedVillagerId = 0;
         this.fireHeldCount = -1;
      } else if (this.villagerGroups[4][1] == 0) {
         this.entityData[909 + this.villagerGroups[4][0]] = 0;
         this.d_byte_void(this.villagerGroups[4][0]);
         this.villagerGroups[4][0] = 0;
         this.fireHeldCount = -1;
      } else {
         this.fireHeldCount = 4;
         this.interactMode = 1;
         this.selectedCount = this.selCountTemp;
         if (this.selectedCount > 50) {
            this.selectedCount = 50;
         }
      }

      this.selRectX = this.selRectY = this.selRectW = this.selRectH = 0;
   }

   // $VF: renamed from: aG () void
   private void saveGroup() {
      this.keyHeld = false;
      this.groupKeyHoldTicks = 0;
      switch (this.numericKey) {
         case 7:
            this.groupSlot = 2;
            break;
         case 9:
            this.groupSlot = 3;
            break;
         default:
            return;
      }

      for (this.selCountTemp = 0; this.selCountTemp < 50; this.selCountTemp++) {
         this.groupMemberTmp = this.fireHeldCount != -1
            ? this.villagerGroups[this.fireHeldCount][this.selCountTemp]
            : (this.selCountTemp == 0 ? this.selectedVillagerId : 0);
         this.villagerGroups[0][this.selCountTemp] = this.groupMemberTmp;
         this.entityData[909 + this.villagerGroups[this.groupSlot][this.selCountTemp]] = 0;
         this.villagerGroups[this.groupSlot][this.selCountTemp] = 0;
      }

      for (this.selCountTemp = 0; this.selCountTemp < 50; this.selCountTemp++) {
         this.groupMemberTmp = this.villagerGroups[0][this.selCountTemp];
         if (this.groupMemberTmp != 0) {
            this.removeFromGroups(this.groupMemberTmp);
            this.villagerGroups[this.groupSlot][this.selCountTemp] = this.groupMemberTmp;
            this.entityData[909 + this.groupMemberTmp] = this.groupSlot;
         }
      }

      this.interactMode = 1;
      this.fireHeldCount = this.groupSlot;
      this.selectedVillagerId = 0;
      this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
   }

   // $VF: renamed from: aH () void
   private void recallGroup() {
      this.keyHeld = false;
      this.groupKeyHoldTicks = 0;
      switch (this.numericKey) {
         case 7:
            this.groupSlot = 2;
            break;
         case 9:
            this.groupSlot = 3;
            break;
         default:
            return;
      }

      if (this.fireHeldCount == this.groupSlot) {
         this.f_int_int_void(this.villagerGroups[this.fireHeldCount][0], 0);
      } else {
         if (this.villagerGroups[this.groupSlot][0] != 0) {
            this.fireHeldCount = this.groupSlot;
            this.clearGroup4();
            this.selectedCount = 0;

            for (this.selCountTemp = 0; this.selCountTemp < 50; this.selCountTemp++) {
               if (this.villagerGroups[this.fireHeldCount][this.selCountTemp] != 0) {
                  this.entityData[909 + this.villagerGroups[this.fireHeldCount][this.selCountTemp]] = this.fireHeldCount;
                  this.selectedCount++;
               }
            }

            this.interactMode = 1;
            this.selectedVillagerId = 0;
            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         }
      }
   }

   // $VF: renamed from: e (int) void
   final void removeFromGroups(int var1) {
      for (int var5 = 0; var5 < 5; var5++) {
         byte var6;
         if ((var6 = var5 < 4 ? this.fileAData[this.recordStarts[125] + var5] : 4) != -1) {
            for (int var4 = 0; var4 < 50; var4++) {
               if (this.villagerGroups[var6][var4] == var1) {
                  while (var4 < 50) {
                     this.villagerGroups[var6][var4] = var4 < 49 ? this.villagerGroups[var6][var4 + 1] : 0;
                     var4++;
                  }

                  if (this.villagerGroups[var6][0] == 0 && this.fireHeldCount == this.entityData[909 + var1]) {
                     if (this.interactMode == 1) {
                        this.interactMode = 0;
                     }

                     this.fireHeldCount = -1;
                  }

                  this.entityData[909 + var1] = 0;
                  return;
               }
            }
         }
      }
   }

   // $VF: renamed from: aI () void
   private void clearGroup4() {
      for (int var1 = 0; var1 < 50; var1++) {
         if (this.villagerGroups[4][var1] != 0) {
            this.entityData[909 + this.villagerGroups[4][var1]] = 0;
            this.villagerGroups[4][var1] = 0;
         }
      }
   }

   // $VF: renamed from: aJ () void
   private void clearDragSelect() {
      if (this.dragSelecting) {
         this.dragSelecting = false;
         this.fireHeldCount = -1;
         this.selectedVillagerId = 0;
         this.selRectX = this.selRectY = this.selRectW = this.selRectH = 0;
      }
   }

   // $VF: renamed from: aK () void
   private void clampCamera() {
      this.cursorCol = this.homeCursorCol;
      this.cursorRow = this.homeCursorRow;

      while (this.camTileX < 0) {
         this.camTileX++;
      }

      while (this.camTileY < 0 && !this.storyMode) {
         this.camTileY++;
      }

      while (this.camTileX + this.screenColsTiles > this.mapWidth - 1) {
         this.camTileX--;
      }

      while (this.camTileY + this.screenRowsTiles >= this.mapHeight - 1) {
         this.camTileY--;
      }
   }

   // $VF: renamed from: aL () void
   private void groupKeyHoldTimer() {
      if (this.keyHeld) {
         this.groupKeyHoldTicks++;
         if (this.groupKeyHoldTicks > 10) {
            this.keyHeld = false;
            this.groupKeyHoldTicks = 0;
         }
      }
   }

   // $VF: renamed from: a (int) byte
   private static byte tribeOfEntity(int var0) {
      if (var0 >= 1 && var0 <= 50) {
         return 0;
      } else {
         return (byte)(var0 >= 51 && var0 <= 100 ? 1 : 2);
      }
   }

   // $VF: renamed from: a (byte, byte, byte, byte, byte) byte
   final byte spawnVillager(byte var1, byte var2, byte var3, byte var4, byte var5) {
      byte var7 = (byte)(1 + var1 * 50);
      byte var6 = 0;

      while (this.entityData[1919 + var7 + var6] != 0) {
         var6++;
      }

      if (var6 >= 50) {
         return -1;
      }

      var6 = (byte)(var6 + var7);
      if (this.chiefMode) {
         this.panelRefreshNeeded = true;
      }

      this.entityData[0 + var6] = this.entityData[6262 + var6] = this.entityData[3131 + var6] = var2;
      this.entityData[101 + var6] = this.entityData[6363 + var6] = this.entityData[3232 + var6] = var3;
      this.entityData[202 + var6] = this.entityData[303 + var6] = 0;
      this.entityData[808 + var6] = 0;
      this.entityData[909 + var6] = 0;
      this.entityData[1111 + var6] = 0;
      this.entityData[4949 + var6] = (byte)(4 + (var5 & 7));
      this.entityData[606 + var6] = 3;
      this.entityData[1313 + var6] = 0;
      this.entityData[1414 + var6] = var4;
      this.entityData[1616 + var6] = (byte)((var5 & 7) * 2 + this.randRange(255, 2));
      this.entityData[1717 + var6] = (byte)((var5 & 7) * 2 + this.randRange(255, 2));
      this.entityData[1818 + var6] = (byte)(3 + (var5 & 7));
      this.entityData[2020 + var6] = (byte)(50 + 25 * (1 - var1) + this.randRange(255, 25));
      this.entityData[2121 + var6] = (byte)(50 + 25 * (1 - var1) + this.randRange(255, 25));
      this.entityData[505 + var6] = (byte)(50 + 25 * (1 - var1) + this.randRange(255, 25));
      int var8 = (var5 & 32) != 0 ? 3 : 0;
      int var9 = this.randRange(255, 3);
      int var10 = this.randRange(255, 3);
      this.entityData[6969 + var6] = (byte)(var9 == 0 ? 2 + this.randRange(255, 2 + var8) : 1);
      this.entityData[7070 + var6] = (byte)(var9 == 1 ? 2 + this.randRange(255, 2 + var8) : 1);
      this.entityData[7272 + var6] = (byte)(var9 == 2 ? 2 + this.randRange(255, 2 + var8) : 1);
      this.entityData[7171 + var6] = (byte)(var10 == 0 ? 2 + this.randRange(255, 2 + var8) : 1);
      this.entityData[7373 + var6] = (byte)(var10 == 1 ? 2 + var1 + this.randRange(255, 2 + var8) : 1 + var1);
      this.entityData[7474 + var6] = (byte)(var10 == 2 ? 2 + this.randRange(255, 2 + var8) : 1);
      this.entityData[7575 + var6] = (byte)(1 + (var5 >> 3 & 3) * 3 + this.randRange(255, 3));
      this.entityData[6464 + var6] = 69;
      this.entityData[3434 + var6] = -1;
      this.entityData[3535 + var6] = 0;
      this.entityData[3636 + var6] = 0;
      this.entityData[3737 + var6] = (byte)(2 + this.randRange(255, 2));
      this.entityData[3838 + var6] = var6;
      this.entityData[4545 + var6] = 0;
      this.entityData[4646 + var6] = -1;
      this.entityData[4747 + var6] = var6;
      this.entityData[4848 + var6] = 0;
      int var11 = 0;
      int var12 = 0;
      var11 = this.randRange(255, 3);
      var12 = this.randRange(15, 4);
      this.entityData[5151 + var6] = (byte)(var12 << 2 | var11);
      if ((this.tribeStats[var1][12] <= this.tribeStats[var1][13] || this.spawnTribe == 0) && this.spawnTribe != 1) {
         this.entityData[1515 + var6] = 0;
         this.tribeStats[var1][12]++;
      } else {
         this.entityData[1515 + var6] = 1;
         this.tribeStats[var1][13]++;
      }

      if (var4 < 12) {
         this.entityData[2828 + var6] = 17;
         this.entityData[2929 + var6] = 14;
         this.entityData[6767 + var6] = this.entityData[1919 + var6] = (byte)(5 + this.randRange(255, 10));
         this.entityData[1010 + var6] = 8;
      } else {
         this.entityData[2828 + var6] = 69;
         this.entityData[2929 + var6] = 8;
         this.entityData[6767 + var6] = this.entityData[1919 + var6] = (byte)(
            (this.missionIndex == 0 && var1 == 1 ? 20 : 40) + (this.entityData[1515 + var6] - 1) * -10 + this.randRange(255, 15)
         );
         this.entityData[1010 + var6] = (byte)((var5 & 7) == 0 ? 8 : 9 - (var5 & 7));
      }

      this.entityData[4444 + var6] = var6;
      this.entityData[6565 + var6] = 9;
      if ((var5 & 64) != 0) {
         this.entityData[6565 + var6] = (byte)this.randRange(255, 4 + var1 * 2);
      }

      if (this.missionIndex == 3) {
         this.entityData[6565 + var6] = 0;
      }

      if ((this.missionIndex == 4 || this.missionIndex == 5) && this.entityData[6565 + var6] == 1) {
         this.entityData[6565 + var6] = 3;
      }

      this.entityData[3333 + var6] = -1;
      this.entityData[7979 + var6] = this.entityData[8282 + var6] = 0;
      this.entityData[8080 + var6] = 0;
      this.entityData[2222 + var6] = this.entityData[2323 + var6] = this.entityData[2424 + var6] = this.entityData[2525 + var6] = this.entityData[2626 + var6] = 0;
      this.g_int_void(var6);
      this.b_byte_void(var6);
      if (this.occupantGrid[var3][var2] == 0) {
         this.occupantGrid[var3][var2] = var6;
      }

      if (!this.storyMode) {
         this.tribeStats[var1][11]++;
         if (var1 == 0) {
            this.postMessage((byte)1, var2, var3, var6);
         }

         this.tribeStats[var1][19]++;
      }

      if (var1 == 0) {
         this.world.revealFogCircle(this.entityData[0 + var6], this.entityData[101 + var6], this.entityData[4949 + var6]);
      }

      return var6;
   }

   // $VF: renamed from: a (byte) void
   final void a_byte_void(byte var1) {
      byte var2 = (byte)((var1 - 1) / 50);
      int var3 = this.entityData[0 + var1] + this.fileAData[this.recordStarts[1] + this.entityData[606 + var1]];
      int var4 = this.entityData[101 + var1] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[606 + var1]];
      if ((this.entityData[2929 + var1] & 255) <= 7
         && (this.entityData[1111 + var1] & 15)
            < (this.fileAData[this.recordStarts[15] + (this.entityData[606 + var1] & 1) * 9 + this.entityData[1010 + var1] - this.entityData[7777 + var1]] + 1)
               / 2
         && this.occupantGrid[var4][var3] == -128) {
         this.occupantGrid[var4][var3] = (byte)(this.entityData[4545 + var1] >= 111 ? -this.entityData[4545 + var1] : 0);
      }

      this.removeFromGroups(var1);
      this.f_int_void(var1);
      this.entityData[1919 + var1] = -1;
      this.unassignEntity(var1);
      if (this.chiefMode || var1 == this.entityData[8810 + this.menuTargetId]) {
         this.panelRefreshNeeded = true;
      }

      if (!this.storyMode) {
         if (var2 == 0) {
            this.postMessage((byte)3, this.entityData[0 + var1], this.entityData[101 + var1], var1);
         }

         this.tribeStats[1 - var2][22]++;
      }
   }

   // $VF: renamed from: f (int, int) void
   private void f_int_int_void(int var1, int var2) {
      this.savedCamCol = this.camTileX;
      this.savedCamRow = this.camTileY;
      if (var1 > 100) {
         this.camTileX = (byte)(this.entityData[9359 + var1 - 101] - this.homeCursorCol);
         this.camTileY = (byte)(this.entityData[9381 + var1 - 101] - this.homeCursorRow + var2);
      } else if (var1 > 0) {
         this.camTileX = (byte)(this.entityData[0 + var1] - this.homeCursorCol);
         this.camTileY = (byte)(this.entityData[101 + var1] - this.homeCursorRow + var2);
      } else if (var1 == -100) {
         this.camTileX = (byte)(this.camTileX - this.homeCursorCol);
         this.camTileY = (byte)(this.camTileY - (this.homeCursorRow - var2));
      } else {
         if (var1 >= 0) {
            return;
         }

         this.camTileX = (byte)(
            this.entityData[8383 + -var1] + (this.fileAData[this.recordStarts[138] + 104 + this.entityData[8749 + -var1]] >> 1) - this.homeCursorCol
         );
         this.camTileY = (byte)(
            this.entityData[8444 + -var1] + (this.fileAData[this.recordStarts[138] + 130 + this.entityData[8749 + -var1]] >> 1) - this.homeCursorRow + var2
         );
      }

      this.clampCamera();
      this.screenContentChanged = true;
   }

   // $VF: renamed from: b (int, int) void
   final void resetVillagerPositions(int var1, int var2) {
      for (byte var3 = 1; var3 <= 50; var3++) {
         if (this.entityData[1919 + var3] > 0) {
            this.occupantGrid[this.entityData[101 + var3]][this.entityData[0 + var3]] = 0;
         }
      }

      for (byte var4 = 1; var4 <= 50; var4++) {
         if (this.entityData[1919 + var4] > 0) {
            if (this.occupantGrid[var2][var1] != 0) {
               this.world.currentVillager = 0;
               this.world.spiralSearchTile(1, 127, var1, var2);
               var1 = this.entityData[3131];
               var2 = this.entityData[3232];
            }

            if ((this.entityData[2929 + var4] & 255) <= 7
               && (this.entityData[1111 + var4] & 15)
                  < (
                        this.fileAData[this.recordStarts[15]
                              + (this.entityData[606 + var4] & 1) * 9
                              + this.entityData[1010 + var4]
                              - this.entityData[7777 + var4]]
                           + 1
                     )
                     / 2
               && this.occupantGrid[this.entityData[101 + var4] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[606 + var4]]][this.entityData[0
                        + var4]
                     + this.fileAData[this.recordStarts[1] + this.entityData[606 + var4]]]
                  == -128) {
               this.occupantGrid[this.entityData[101 + var4] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[606 + var4]]][this.entityData[0 + var4]
                  + this.fileAData[this.recordStarts[1] + this.entityData[606 + var4]]] = 0;
            }

            this.entityData[0 + var4] = this.entityData[6262 + var4] = this.entityData[3131 + var4] = (byte)var1;
            this.entityData[101 + var4] = this.entityData[6363 + var4] = this.entityData[3232 + var4] = (byte)var2;
            this.entityData[202 + var4] = this.entityData[303 + var4] = 0;
            this.entityData[808 + var4] = 0;
            this.entityData[2828 + var4] = 69;
            this.entityData[2929 + var4] = 8;
            this.entityData[3030 + var4] = 0;
            this.entityData[6161 + var4] = 0;
            this.occupantGrid[this.entityData[101 + var4]][this.entityData[0 + var4]] = var4;
            this.world.revealFogCircle(this.entityData[0 + var4], this.entityData[101 + var4], this.entityData[4949 + var4]);
         }
      }
   }

   // $VF: renamed from: f (int) void
   final void f_int_void(int var1) {
      if (this.selectedVillagerId == var1) {
         this.selectedVillagerId = 0;
         if (this.interactMode == 1) {
            this.interactMode = 0;
            this.inspectedVillagerId = 0;
            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
            return;
         }

         if (this.interactMode == 2 && this.entityData[8749 + this.menuTargetId] == 6) {
            this.interactMode = 0;
            this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
         }
      }
   }

   // $VF: renamed from: b (byte) void
   final void b_byte_void(byte var1) {
      byte var2 = 2;
      if (var1 >= 51) {
         var2 = (byte)(var1 == 51 ? 4 : 3);
      } else if (this.entityData[1414 + var1] < 12) {
         var2 = 0;
      } else if (this.entityData[1414 + var1] < 35) {
         var2 = 1;
      }

      this.entityData[2727 + var1] = (byte)(
         this.fileAData[this.recordStarts[189] + this.entityData[1515 + var1] * 10 + var2 * 2]
            + this.randRange(255, this.fileAData[this.recordStarts[189] + this.entityData[1515 + var1] * 10 + var2 * 2 + 1])
      );
      if (var1 == this.inspectedVillagerId) {
         this.hudPanelDirty[4] = this.hudPanelVisible[4];
      }
   }

   // $VF: renamed from: g (int) void
   final void g_int_void(int var1) {
      this.entityData[6868 + var1] = var1 == 51 || var1 == 52
         ? -2
         : (var1 > 51 ? -1 : (byte)((var1 > 3 ? 1 + this.randRange(65535, 58) : var1) % 60 + 60 * this.entityData[1515 + var1] - 1));
   }

   // $VF: renamed from: b (int) byte
   private static byte tribeOfBuilding(int var0) {
      if (var0 <= -1 && var0 >= -30) {
         return 0;
      } else {
         return (byte)(var0 <= -31 && var0 >= -60 ? 1 : 2);
      }
   }

   // $VF: renamed from: a (byte, int, int, int, int, boolean, int) byte
   final byte a_byte_int_int_int_int_boolean_int_byte(byte var1, int var2, int var3, int var4, int var5, boolean var6, int var7) {
      byte var8 = this.fileAData[this.recordStarts[138] + 104 + var2];
      byte var9 = this.fileAData[this.recordStarts[138] + 130 + var2];
      byte var11 = (byte)(1 + var1 * 30);
      byte var10 = 0;

      try {
         while (this.entityData[8505 + var11 + var10] != 0) {
            var10++;
         }

         if (var10 >= 30) {
            if (var1 == 0) {
               this.placementType = 100;
               this.setPlaceMode(-1);
               this.messageFlags[0] = 0;
               this.postMessage((byte)10, (byte)0, (byte)0, (byte)0);
            }

            return -1;
         } else {
            var10 = (byte)(var10 + var11);
            if (var7 == -1) {
               if (!this.a_int_int_int_boolean_boolean(var2, var3, var4, !var6)) {
                  return -2;
               }
            } else {
               var10 = (byte)var7;
            }

            if (var2 >= 20 && var2 <= 22) {
               if (var7 == -1) {
                  var10 = 0;

                  while (var10 < 30 && this.trapTable[0][var10] != 0) {
                     var10++;
                  }
               } else {
                  var10 = (byte)var7;
               }

               if (var10 == 30) {
                  return -1;
               }

               this.trapTable[0][var10] = (byte)this.menuTargetId;
               this.trapTable[2][var10] = (byte)var3;
               this.trapTable[3][var10] = (byte)var4;
               this.trapTable[1][var10] = (byte)var2;
               if (!var6) {
                  this.buildingStores[0][this.storeRowIndex][6 + this.placementType - 20]++;
                  this.occupantGrid[var4][var3] = (byte)(var1 == 0 ? -123 : -126);
               } else {
                  this.occupantGrid[var4][var3] = (byte)(20 - var2 - 111 - var1 * 6);
               }

               this.markTileRect(var3, var4, 1, 1);
               return var10;
            } else {
               if (var2 == 0) {
                  byte var12 = 0;

                  while (this.buildingStores[var1][var12][11] != 0) {
                     if (++var12 > 10) {
                        return -1;
                     }
                  }

                  this.buildingStores[var1][var12][11] = var10;
               }

               this.markTileRect(var3, var4, var8, var9);
               this.entityData[8383 + var10] = (byte)var3;
               this.entityData[8444 + var10] = (byte)var4;
               this.entityData[8749 + var10] = (byte)var2;
               this.entityData[8871 + var10] = 0;
               this.entityData[9176 + var10] = 0;
               this.entityData[8932 + var10] = 0;
               this.entityData[8993 + var10] = 0;
               this.entityData[9054 + var10] = 0;
               if (var5 == 0) {
                  var5 = this.fileAData[this.recordStarts[113] + (this.missionIndex < 2 ? 0 : (this.missionIndex == 2 ? 12 : 24)) + var2];
               }

               this.entityData[9115 + var10] = (byte)var5;
               this.entityData[8810 + var10] = 0;
               this.entityData[8688 + var10] = 5;
               this.entityData[8627 + var10] = 0;
               this.entityData[8505 + var10] = this.entityData[8566 + var10] = this.fileAData[this.recordStarts[138] + 182 + var2];
               if (var2 >= 12 && var2 <= 19) {
                  if (!this.world.registerTrap(var10)) {
                     if (var1 == 0) {
                        this.placementType = 100;
                        this.setPlaceMode(-1);
                        this.messageFlags[0] = 0;
                        this.postMessage((byte)10, (byte)0, (byte)0, (byte)0);
                     }

                     this.entityData[8505 + var10] = 0;
                     return -1;
                  }

                  this.entityData[9237 + var10] = (byte)(var1 == 0 ? this.menuItemIds[this.menuSelectedIndex] : this.world.currentVillager);
                  this.entityData[8566 + var10] = this.fileAData[this.recordStarts[138] + 182 + this.entityData[9237 + var10]];
                  this.entityData[9115 + var10] = 3;
               } else if (var1 == 0) {
                  this.world
                     .revealFogCircle(
                        this.entityData[8383 + var10] + this.fileAData[this.recordStarts[138] + 104 + this.entityData[8749 + var10]] / 2,
                        this.entityData[8444 + var10] + this.fileAData[this.recordStarts[138] + 130 + this.entityData[8749 + var10]] / 2,
                        this.entityData[8688 + var10] & 15
                     );
               }

               for (int var17 = 0; var17 < var9; var17++) {
                  for (int var13 = 0; var13 < var8; var13++) {
                     this.occupantGrid[var4 + var17][var3 + var13] = (byte)(-var10);
                  }
               }

               if (var2 == 2) {
                  this.tribeStats[var1][16] = (short)(this.tribeStats[var1][16] + 4);
                  if (this.tribeStats[var1][16] > 50) {
                     this.tribeStats[var1][16] = 50;
                  }
               }

               if (!this.storyMode && var7 == -1) {
                  this.tribeStats[var1][14]++;
                  this.tribeStats[var1][20]++;
               }

               if (var2 == 2) {
                  this.world.releaseMatingHut(var10);
               }

               if (var6 && var1 == 0) {
                  this.postMessage((byte)5, (byte)var3, (byte)var4, (byte)0);
               }

               return var10;
            }
         }
      } catch (Exception var14) {
         return -1;
      }
   }

   // $VF: renamed from: c (int, boolean) void
   final void c_int_boolean_void(int var1, boolean var2) {
      byte var3 = this.entityData[8749 + var1];
      byte var4 = this.entityData[8383 + var1];
      byte var5 = this.entityData[8444 + var1];
      byte var6 = this.fileAData[this.recordStarts[138] + 104 + var3];
      byte var7 = this.fileAData[this.recordStarts[138] + 130 + var3];
      int var8 = var1 < 31 ? 0 : 1;
      if (var3 == 2) {
         this.world.releaseMatingHut(var1);
      }

      try {
         if (var2) {
            this.f_int_int_void(var1 == 1 ? -1 : 0, 0);
            this.entityData[8505 + var1] = -1;
            this.enqueueBuildAnim(var1, 3);

            for (byte var14 = this.entityData[8993 + var1]; var14 > 0; var14 = this.entityData[4545 + var14]) {
               this.a_int_int_boolean_boolean_void(var14, var1, true, false);
            }

            if (this.entityData[8810 + var1] > 1 && (this.entityData[8749 + var1] != 2 || this.entityData[8627 + var1] != 0)) {
               this.a_int_int_boolean_boolean_void(this.entityData[8810 + var1], var1, true, false);
            }

            if ((this.interactMode == 2 || this.interactMode == 4) && var1 == this.menuTargetId) {
               this.interactMode = 0;
               this.menuTargetId = 0;
               this.a_boolean_boolean_boolean_boolean_void(true, true, false, false);
               return;
            }
         } else {
            this.entityData[8505 + var1] = 0;
            this.entityData[8627 + var1] = 3;

            for (int var9 = 0; var9 < var7; var9++) {
               for (int var10 = 0; var10 < var6; var10++) {
                  this.occupantGrid[var5 + var9][var4 + var10] = 0;
               }
            }

            if (var3 == 2) {
               this.tribeStats[var8][16] = (short)(this.tribeStats[var8][16] - 4);
               if (this.tribeStats[var8][16] < 0) {
                  this.tribeStats[var8][16] = 1;
               }

               for (int var12 = var8 * 50 + 1; var12 <= (var8 + 1) * 50; var12++) {
                  if (this.entityData[3434 + var12] == var1) {
                     this.entityData[3434 + var12] = 0;
                  }
               }
            }

            if (!this.storyMode) {
               this.tribeStats[var8][14]--;
               this.tribeStats[1 - var8][21]++;
            }

            if (var3 == 0) {
               byte var13 = 0;

               while (this.buildingStores[var8][var13][11] != var1) {
                  if (++var13 >= 10) {
                     var13--;
                     break;
                  }
               }

               this.buildingStores[var8][var13][11] = 0;
            }

            this.world.unregisterTrap(var1);
            if (var8 == 0) {
               this.postMessage((byte)8, (byte)var4, (byte)var5, (byte)0);
            }
         }
      } catch (Exception var11) {
      }
   }

   // $VF: renamed from: a (int, int, int, boolean) boolean
   private boolean a_int_int_int_boolean_boolean(int var1, int var2, int var3, boolean var4) {
      byte var5 = this.fileAData[this.recordStarts[138] + 104 + var1];
      byte var6 = this.fileAData[this.recordStarts[138] + 130 + var1];
      boolean var7 = true;

      for (int var8 = 0; var8 < var6; var8++) {
         for (int var9 = 0; var9 < var5; var9++) {
            if (!this.isTileInBounds(var2 + var9, var3 + var8)
               || this.occupantGrid[var3 + var8][var2 + var9] != 0
               || var4 && this.effectBitAt(var2 + var9, var3 + var8) != 0) {
               var7 = false;
            }
         }
      }

      return var7;
   }

   // $VF: renamed from: a (int, int, boolean, boolean) void
   private void a_int_int_boolean_boolean_void(int var1, int var2, boolean var3, boolean var4) {
      if (this.entityData[2828 + var1] == 93 || this.entityData[2828 + var1] == 92 || this.entityData[2828 + var1] == 106) {
         var3 = true;
      }

      if (this.entityData[8810 + var2] != var1 || this.entityData[8749 + var2] == 2 && this.entityData[8627 + var2] == 0) {
         if (!var3
            || this.entityData[8627 + var2] == 0
               && (
                  this.entityData[8749 + var2] == 2
                     || this.entityData[8749 + var2] == 7
                     || this.entityData[8749 + var2] == 4
                     || this.entityData[8749 + var2] == 8
               )) {
            this.entityData[2828 + var1] = 87;
            this.entityData[6161 + var1] = 3;
            return;
         }

         this.entityData[6161 + var1] = 2;
      } else {
         if (var3) {
            this.entityData[8810 + var2] = 0;
         }

         label42: {
            this.refreshPanelIfMenuTarget(var2);
            if (this.occupantGrid[this.entityData[101 + var1]][this.entityData[0 + var1]] != var1) {
               if (var3) {
                  this.entityData[6161 + var1] = 2;
                  break label42;
               }

               this.entityData[2828 + var1] = 87;
            } else {
               this.entityData[3131 + var1] = this.entityData[0 + var1];
               this.entityData[3232 + var1] = this.entityData[101 + var1];
               if (var3) {
                  this.entityData[6262 + var1] = this.entityData[0 + var1];
                  this.entityData[6363 + var1] = this.entityData[101 + var1];
               }

               this.entityData[2828 + var1] = 69;
               if (var3) {
                  this.entityData[6161 + var1] = 2;
                  this.entityData[4747 + var1] = 0;
                  break label42;
               }
            }

            this.entityData[6161 + var1] = 3;
         }

         if (var4) {
            this.clearEntityJob(var2, false);
            return;
         }
      }
   }

   // $VF: renamed from: h (int) void
   final void h_int_void(int var1) {
      this.f_int_void(var1);
      this.removeFromGroups(var1);
   }

   // $VF: renamed from: a (int, byte) byte
   final byte setPendingOrderState(int var1, byte var2) {
      byte var4 = 1;
      byte var5 = 0;
      if (var1 >= 51) {
         var4 = 31;
         var5 = 1;
      }

      while (var4 < 30) {
         if (this.entityData[8810 + var4 + 0] == var1) {
            var4 = (byte)(var4 + 0);
            break;
         }

         var4++;
      }

      byte var3;
      for (var3 = 0; var3 < 30; var3++) {
         if (this.pendingOrders[var5][0][var3] == var4) {
            this.pendingOrders[var5][4][var3] = var2;
            break;
         }
      }

      return var3;
   }

   // $VF: renamed from: b (int) boolean
   private static boolean isObjectId(int var0) {
      return var0 >= 101 && var0 <= 122;
   }

   // $VF: renamed from: b (int, int, int, int) void
   final void spawnEffect(int var1, int var2, int var3, int var4) {
      byte var5;
      for (var5 = 0; var5 < 22; var5++) {
         if (this.entityData[9513 + var5] == 0) {
            if (this.missionIndex != 3) {
               this.effectCounters[var4]++;
            }
            break;
         }
      }

      this.entityData[9513 + var5] = this.fileAData[this.recordStarts[180] + var1];
      this.entityData[9535 + var5] = (byte)var1;
      this.entityData[9359 + var5] = this.entityData[9645 + var5] = (byte)var2;
      this.entityData[9381 + var5] = this.entityData[9667 + var5] = (byte)var3;
      this.entityData[9403 + var5] = this.entityData[9425 + var5] = 0;
      this.entityData[9711 + var5] = this.entityData[9689 + var5] = 0;
      this.entityData[9601 + var5] = 0;
      this.entityData[9491 + var5] = (byte)this.randRange(255, 8);
      this.entityData[9469 + var5] = (byte)var4;
      this.entityData[9557 + var5] = this.fileAData[this.recordStarts[184] + var1];
      this.entityData[9579 + var5] = 0;
      this.entityData[9447 + var5] = 0;
      this.occupantGrid[var3][var2] = (byte)(101 + var5);
   }

   // $VF: renamed from: c (int) boolean
   private static boolean isBuildingFootprintCode(int var0) {
      return var0 <= -61 && var0 >= -110;
   }

   // $VF: renamed from: a (int, int, int, byte) void
   final void a_int_int_int_byte_void(int var1, int var2, int var3, byte var4) {
      byte var5 = 0;

      while (var5 < 50 && this.entityData[9833 + var5] != 0) {
         var5++;
      }

      if (var5 == 50) {
         var5 = 0;

         while (this.entityData[9833 + var5] != 6 || this.entityData[9833 + var5] != 7) {
            var5++;
         }

         this.occupantGrid[this.entityData[9783 + var5]][this.entityData[9733 + var5]] = 0;
      }

      this.entityData[9833 + var5] = (byte)var1;
      this.entityData[9733 + var5] = (byte)var2;
      this.entityData[9783 + var5] = (byte)var3;
      this.entityData[9883 + var5] = var4;
      this.occupantGrid[var3][var2] = (byte)(-61 - var5);
   }

   // $VF: renamed from: c (int) byte
   private static byte tribeOfOccupantCode(int var0) {
      if (var0 <= -111 && var0 >= -116) {
         return 0;
      } else if (var0 <= -117 && var0 >= -122) {
         return 1;
      } else {
         return (byte)(var0 == -123 ? 3 : 2);
      }
   }

   // $VF: renamed from: b (int, int) boolean
   private boolean isTileInBounds(int var1, int var2) {
      return var1 >= 0 && var2 >= 0 && var1 < this.mapWidth && var2 < this.mapHeight;
   }

   // $VF: renamed from: a (int, int) byte
   private byte effectBitAt(int var1, int var2) {
      if ((this.effectBitfield[0][var1 >> 5][var2] >> var1 & 31 & 1) == 0) {
         return 2;
      } else {
         return (byte)((this.effectBitfield[1][var1 >> 5][var2] >> var1 & 31 & 1) == 0 ? 1 : 0);
      }
   }

   // $VF: renamed from: c (int, int, int, int) void
   final void markTileRect(int var1, int var2, int var3, int var4) {
      for (int var5 = var2; var5 < var2 + var4; var5++) {
         for (int var6 = var1; var6 < var1 + var3; var6++) {
            if (this.isTileOnScreen(var6, var5)) {
               this.tileDirty[var5 - this.camTileY][var6 - this.camTileX] = true;
            }
         }
      }

      this.terrainDirty = true;
   }

   // $VF: renamed from: a (boolean, boolean, boolean, boolean) void
   private void a_boolean_boolean_boolean_boolean_void(boolean var1, boolean var2, boolean var3, boolean var4) {
      this.a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(var1, var2, var3, var4, false, false, false, false, false);
   }

   // $VF: renamed from: a (boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean) void
   private void a_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_boolean_void(
      boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9
   ) {
      this.hudPanelDirty[0] = this.hudPanelVisible[0] = var1;
      this.hudPanelDirty[1] = this.hudPanelVisible[1] = var2;
      this.hudPanelDirty[2] = this.hudPanelVisible[2] = var3;
      this.hudPanelDirty[4] = this.hudPanelVisible[4] = var4;
      this.hudPanelDirty[5] = this.hudPanelVisible[5] = var5;
      this.hudPanelDirty[6] = this.hudPanelVisible[6] = var6;
      this.hudPanelDirty[7] = this.hudPanelVisible[7] = var7;
      this.hudPanelDirty[8] = this.hudPanelVisible[8] = var8;
      this.hudPanelDirty[9] = this.hudPanelVisible[9] = var9;
      this.hudPanelDirty[17] = this.hudPanelVisible[17] = true;
      if (var2) {
         if (this.messageFlags[0] == 0) {
            this.hudPanelDirty[1] = this.hudPanelVisible[1] = true;
            this.hudPanelDirty[9] = this.hudPanelVisible[9] = false;
         } else if (this.messageFlags[0] != 0) {
            this.hudPanelDirty[9] = this.hudPanelVisible[9] = true;
            this.hudPanelDirty[1] = this.hudPanelVisible[1] = false;
         }
      }

      int var10 = this.bufferCopyY;
      if (this.hudPanelVisible[4]) {
         this.bufferCopyY = 48;
         this.visiblePanelCount = this.hudPanelForScreen[3];
      } else {
         this.bufferCopyY = 22;
         this.visiblePanelCount = this.hudPanelForScreen[2];
      }

      if (var10 < this.bufferCopyY && this.scrollDirY == 0 && this.camTileY < this.mapHeight - 1) {
         this.scrollDirY = 1;
         this.camTileY++;
         this.cursorRow--;
         if (this.camTileY < this.mapHeight - 1) {
            this.camTileY++;
            this.cursorRow--;
            this.screenContentChanged = true;
         }
      } else if (var10 > this.bufferCopyY && !var4 && this.scrollDirY == 0 && this.camTileY > 0) {
         this.scrollDirY = -1;
         this.camTileY--;
         this.cursorRow++;
         if (this.camTileY > 0) {
            this.camTileY--;
            this.cursorRow++;
            this.screenContentChanged = true;
         }
      }

      if (this.hudPanelVisible[1]) {
         this.activeHudPanel = 1;
      } else if (this.hudPanelVisible[2]) {
         this.activeHudPanel = 2;
      } else if (this.hudPanelVisible[5]) {
         this.activeHudPanel = 5;
      } else if (this.hudPanelVisible[6]) {
         this.activeHudPanel = 6;
      } else if (this.hudPanelVisible[9]) {
         this.activeHudPanel = 9;
      }

      this.bufferCopyX = this.screenHeights[this.fileAData[this.recordStarts[195] + 0 + this.activeHudPanel]];
      byte var11 = this.screenRowsTiles;
      this.screenRowsTiles = (byte)((319 - this.bufferCopyY - this.bufferCopyX + this.hudPanelForScreen[2]) / 16 + 1);
      if (this.screenRowsTiles > var11) {
         this.hudChanged = true;
      }

      this.viewHeightPx = this.screenRowsTiles * 16;
      this.viewHeightPx += 16;
      this.screenContentHeights[2] = this.screenContentHeights[3] = this.viewHeightPx;
      this.screenContentHeights[4] = this.screenContentHeights[5] = this.screenContentHeights[7] = this.screenContentHeights[10] = this.viewHeightPx
         + this.bufferCopyY;
      this.screenContentHeights[11] = this.screenContentHeights[this.fileAData[this.recordStarts[195] + 0 + this.activeHudPanel]]
         + this.screenHeights[this.fileAData[this.recordStarts[195] + 0 + this.activeHudPanel]];
   }

   // $VF: renamed from: a (byte, byte, byte, byte) void
   final void postMessage(byte var1, byte var2, byte var3, byte var4) {
      if (this.tickCounter != 0 && this.endgameTimer <= 0) {
         boolean var5 = false;
         if (var1 != 100) {
            var5 = true;

            for (int var6 = 0; var6 < 10; var6++) {
               if (this.messageFlags[var6] == 0) {
                  this.messageFlags[var6] = var1;
                  this.focusTable[0][var6] = var2;
                  this.focusTable[1][var6] = var3;
                  this.focusTable[2][var6] = var4;
                  this.messageDurations[var6] = 0;
                  break;
               }
            }
         } else if (this.messageFlags[0] != 0) {
            boolean var8 = false;
            var5 = true;
            this.messageType = 0;

            for (int var7 = 0; var7 < 9; var7++) {
               this.messageDurations[var7]++;
            }

            if (this.messageFlags[1] != 0) {
               if (this.messageDurations[0] > 1) {
                  var8 = true;
               }
            } else if (this.messageDurations[0] > 4) {
               var8 = true;
            }

            if (var8) {
               for (int var9 = 0; var9 < 9; var9++) {
                  this.messageFlags[var9] = this.messageFlags[var9 + 1];
                  this.focusTable[0][var9] = this.focusTable[0][var9 + 1];
                  this.focusTable[1][var9] = this.focusTable[1][var9 + 1];
                  this.focusTable[2][var9] = this.focusTable[2][var9 + 1];
                  this.messageDurations[var9] = this.messageDurations[var9 + 1];
                  this.messageFlags[var9 + 1] = 0;
               }

               if (this.messageFlags[0] != 0 && this.messageFlags[1] == 0) {
                  this.messageDurations[0] = 0;
               }
            }
         }

         if (var5 && (this.hudPanelVisible[1] || this.hudPanelVisible[9])) {
            this.a_boolean_boolean_boolean_boolean_void(this.hudPanelVisible[0], true, false, this.hudPanelVisible[4]);
            this.messageType = (byte)(this.focusTable[0][0] == 0 && this.focusTable[1][0] == 0 ? 0 : 1);
         }
      }
   }

   // $VF: renamed from: g (byte) void
   private void g_byte_void(byte var1) {
      try {
         this.missionIndex = var1;
         this.resetWorldState();
         this.loadMap();
         this.placeCameraStartPositions();
      } catch (Exception var3) {
      }
   }

   // $VF: renamed from: aM () void
   private void resetWorldState() {
      this.dragSelecting = false;
      this.fireHeld = false;
      this.fireHeldCount = -1;

      for (int var1 = 0; var1 < this.offsetTable[121]; var1++) {
         this.entityData[var1] = 0;
      }

      for (int var3 = 0; var3 <= 100; var3++) {
         this.entityData[4040 + var3] = -100;
         this.entityData[4242 + var3] = 1;
         this.entityData[4343 + var3] = -1;
      }

      for (int var4 = 0; var4 < 50; var4++) {
         this.villagerGroups[2][var4] = this.villagerGroups[3][var4] = this.villagerGroups[4][var4] = 0;
      }

      for (int var5 = 0; var5 < 10; var5++) {
         this.messageFlags[var5] = 0;
      }

      for (int var6 = 0; var6 < 5; var6++) {
         this.aiTargets[0][var6][2] = -1;
      }

      for (byte var7 = 0; var7 < 30; var7++) {
         this.buildAnims[0][var7] = 0;
      }

      for (byte var8 = 0; var8 < 3; var8++) {
         for (byte var2 = 0; var2 < 96; var2++) {
            this.effectBitfield[0][var8][var2] = 0;
         }
      }

      for (byte var9 = 0; var9 < 10; var9++) {
         for (byte var14 = 0; var14 < 12; var14++) {
            this.buildingStores[0][var9][var14] = 0;
            this.buildingStores[1][var9][var14] = 0;
         }
      }

      for (byte var10 = 0; var10 < 30; var10++) {
         for (byte var15 = 0; var15 < 4; var15++) {
            this.trapTable[var15][var10] = 0;
         }

         for (byte var16 = 0; var16 < 5; var16++) {
            this.pendingOrders[0][var16][var10] = this.pendingOrders[1][var16][var10] = 0;
         }
      }

      for (int var11 = 0; var11 < 96; var11++) {
         for (int var17 = 0; var17 < 96; var17++) {
            this.occupantGrid[var11][var17] = this.terrainGrid[var11][var17] = 0;
         }
      }

      for (int var12 = 0; var12 < this.screenRowsTiles + 3; var12++) {
         for (int var18 = 0; var18 < this.screenColsTiles; var18++) {
            this.tileEntityDraw[var12][var18] = 0;
         }
      }

      if (!this.storyMode) {
         for (byte var13 = 0; var13 < 26; var13++) {
            this.tribeStats[0][var13] = this.tribeStats[1][var13] = 0;
         }

         this.tribeStats[0][17] = 0;
      }

      this.tribeStats[0][16] = 1;
      this.clearEffects();
      this.tickCounter = 0;
      this.trapScanRow = 0;
      this.placeConfirmFlagL = this.placeConfirmFlagN = false;
      this.chiefMode = false;
      this.menuItemActivated = false;
      this.minimapOriginX = this.minimapOriginY = 0;
      this.minimapRowOffset = 0;
      this.endgameTimer = 0;
      this.messageType = 0;
      this.world.resetWorld();
      this.resetInputFlags();
      this.worldReady = true;
   }

   // $VF: renamed from: aN () void
   private void loadMap() {
      int var1 = 0;

      try {
         System.gc();
         Thread.yield();
         Object var4 = null;
         int var5 = 0;
         this.mapWidth = this.tableMapWidth = this.fileAData[this.recordStarts[149] + this.missionIndex * 2];
         this.mapHeight = this.tableMapHeight = this.fileAData[this.recordStarts[149] + this.missionIndex * 2 + 1];
         if (this.missionIndex == 0) {
            this.mapWidth = 40;
            this.mapHeight = 32;
         } else if (this.missionIndex == 5) {
            this.mapWidth = (short)(this.mapWidth - 32);
         }

         int var8 = 0;
         int var7 = 10;

         for (int var3 = 0; var3 < this.tableMapHeight; var3++) {
            for (int var2 = 0; var2 < this.tableMapWidth; var2++) {
               if (var7 >= this.fileAData[this.recordStarts[197] + 0 + var8]) {
                  var1 = (short)(this.mapData[this.mapChunkOffsets[this.fileAData[this.recordStarts[248] + this.missionIndex]] + var5] & 0xFF);
                  var5++;
                  if (var1 >= 250) {
                     var7 = 0;
                     var8 = var1 - 250;
                     var1 = this.fileAData[this.recordStarts[197] + 6 + var8] & 255;
                  }
               }

               var7++;
               int var6 = var1;
               if (var1 <= 8) {
                  var6 += this.randRange(3, 3);
               }

               this.terrainGrid[var3][var2] = (byte)var6;
               int var9 = (this.pd0ShortTables[9][var6] & '\uffff') >> 13 & 1;
               if ((var6 >= 175 || var9 == 1) && this.occupantGrid[var3][var2] != -127) {
                  this.occupantGrid[var3][var2] = -126;
               } else if (this.occupantGrid[var3][var2] == -126) {
                  this.occupantGrid[var3][var2] = 0;
               }

               if (this.missionIndex != 3) {
                  if (((this.pd0ByteTables[16][var6] & '\uffff') >> 6 & 3) == 2) {
                     this.occupantGrid[var3][var2] = -125;
                  }

                  if (var6 >= 180 && var6 <= 231) {
                     this.occupantGrid[var3][var2] = -124;
                  } else if (var6 >= 175 && var6 <= 179) {
                     this.a_int_int_int_byte_void(var6 - 175 + 1, var2, var3, this.fileAData[this.recordStarts[110] + var6 - 175 + 1]);
                  }
               }

               if (var3 == 0 || var3 == this.tableMapHeight - 1 || var2 == this.tableMapWidth - 1) {
                  this.occupantGrid[var3][var2] = -126;
               }
            }
         }

         for (int var13 = 0; var13 < this.mapHeight; var13++) {
            for (int var12 = 1; var12 < this.mapWidth - 1; var12++) {
               if (isBuildingFootprintCode(this.occupantGrid[var13][var12]) && this.entityData[9833 + (this.occupantGrid[var13][var12] + 61) * -1] == 5) {
                  byte var14;
                  if ((var14 = this.terrainGrid[var13][var12 - 1]) == 33 || var14 == 34 || var14 == 37 || var14 == 38) {
                     this.occupantGrid[var13][var12 - 1] = -127;
                  }

                  if ((var14 = this.terrainGrid[var13][var12 + 1]) == 33 || var14 == 34 || var14 == 37 || var14 == 38) {
                     this.occupantGrid[var13][var12 + 1] = -127;
                  }
               }
            }
         }
      } catch (Exception var11) {
      }
   }

   // $VF: renamed from: aO () void
   private void placeCameraStartPositions() {
      this.spawnInitialEntities();
      this.tribeStats[0][17] = (short)(this.missionIndex == 3 ? 0 : 1);
      this.cursorCol = this.homeCursorCol;
      this.cursorRow = this.homeCursorRow;
      this.camTileX = this.savedCamCol = (byte)(this.fileAData[this.recordStarts[150] + this.missionIndex * 2] - this.cursorCol);
      this.camTileY = this.savedCamRow = (byte)(this.fileAData[this.recordStarts[150] + this.missionIndex * 2 + 1] - this.cursorRow);
      this.clampCamera();
      this.savedCamCol = this.camTileX;
      this.savedCamRow = this.camTileY;
      this.savedCursorCol = this.cursorCol;
      this.savedCursorRow = this.cursorRow;
      this.world.rebuildFogOfWar();
   }

   // $VF: renamed from: aP () void
   private void spawnInitialEntities() {
      if (this.missionIndex == 3) {
         this.spawnTribe = 0;
      }

      short var1 = this.recordStarts[250 + this.missionIndex];
      byte var2 = this.fileAData[this.recordStarts[262] + this.missionIndex];

      for (byte var3 = 0; var3 < var2; var3++) {
         if (this.fileAData[var1 + 0 * var2 + var3] != -1) {
            if (this.fileAData[var1 + 1 * var2 + var3] >= 0) {
               this.a_byte_int_int_int_int_boolean_int_byte(
                  this.fileAData[var1 + 0 * var2 + var3],
                  this.fileAData[var1 + 1 * var2 + var3],
                  this.fileAData[var1 + 2 * var2 + var3],
                  this.fileAData[var1 + 3 * var2 + var3],
                  this.fileAData[var1 + 4 * var2 + var3],
                  true,
                  -1
               );
            } else if (this.fileAData[var1 + 1 * var2 + var3] == -1) {
               this.spawnVillager(
                  (byte)(this.fileAData[var1 + 0 * var2 + var3] & 1),
                  this.fileAData[var1 + 2 * var2 + var3],
                  this.fileAData[var1 + 3 * var2 + var3],
                  this.fileAData[var1 + 4 * var2 + var3],
                  (byte)((this.fileAData[var1 + 0 * var2 + var3] >> 1) + ((this.fileAData[var1 + 0 * var2 + var3] & 1) != 0 ? this.selectedDifficulty : 0))
               );
            } else if (this.fileAData[var1 + 1 * var2 + var3] == -2) {
               this.spawnEffect(1, this.fileAData[var1 + 2 * var2 + var3], this.fileAData[var1 + 3 * var2 + var3], this.fileAData[var1 + 0 * var2 + var3]);
            }

            if (var3 <= 10 && this.missionIndex != 1 && this.missionIndex != 6) {
               this.tribeStats[0][var3] = this.fileAData[this.recordStarts[263] + 11 * this.missionIndex * 2 + var3];
               this.tribeStats[1][var3] = (short)(this.fileAData[this.recordStarts[263] + 11 * (this.missionIndex * 2 + 1) + var3] * 20);
            }
         }
      }

      if (this.missionIndex == 0) {
         this.entityData[7476] = 4;
         this.entityData[7479] = 5;
      } else {
         if (this.missionIndex == 3) {
            this.spawnTribe = 2;
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Graphics, javax.microedition.lcdui.Image, int, int, int, int, int, int, int) boolean
   static boolean drawRegionSafe(Graphics var0, Image var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      try {
         var0.drawRegion(var1, var2, var3, var4, var5, 0, var6, var7, var8);
         return true;
      } catch (Exception var10) {
         return false;
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int) void
   private static void fillRect(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.fillRect(11 + var1, 8 + var2, var3, var4 + 0);
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Graphics, int, int, int, int) void
   private static void drawRect(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.drawRect(var1, var2, var3, var4);
   }

   // $VF: renamed from: c (javax.microedition.lcdui.Graphics, int, int, int, int) void
   private static void setClipRect(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.setClip(var1, var2, var3, var4);
   }

   // $VF: renamed from: d (javax.microedition.lcdui.Graphics, int, int, int, int) void
   private static void drawLine(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.drawLine(var1, var2, var3, var4);
   }

   // $VF: renamed from: d (int) boolean
   private boolean loadSlot(int var1) {
      boolean var2 = false;

      try {
         System.gc();
         Thread.yield();
         RecordStore var4;
         if ((var4 = RecordStore.openRecordStore(this.recordStorePrefix + var1, true)).getNumRecords() <= 0) {
            var4.closeRecordStore();
            return false;
         }

         DataInputStream var3 = new DataInputStream(new ByteArrayInputStream(var4.getRecord(1)));

         try {
            if (var1 == 0) {
               this.chiefId = var3.readInt();
               this.customGameAvailable = var3.readBoolean();
               this.campaignProgress = var3.readByte();
               this.campaignScreenId = (byte)(4 + this.campaignProgress);
               this.continueProgress = var3.readByte();
               this.savedMissionProgress = var3.readByte();
               this.campaignDifficulty = var3.readByte();
               this.newGameFlag = var3.readByte();
            } else {
               this.missionIndex = var3.readByte();
               this.resetWorldState();
               this.newGameFlag = var3.readByte();
               this.customDifficulty = var3.readByte();
               if (var1 == 2) {
                  this.selectedDifficulty = this.customDifficulty;
               }

               this.loadMap();
               var3.read(this.entityData);
               var3.read(this.homePosition);
               var3.read(this.villagerGroups[0]);
               var3.read(this.villagerGroups[1]);
               var3.read(this.villagerGroups[2]);
               var3.read(this.villagerGroups[3]);

               for (int var5 = 0; var5 < 26; var5++) {
                  this.tribeStats[0][var5] = var3.readShort();
                  this.tribeStats[1][var5] = var3.readShort();
               }

               var3.read(this.buildAnims[0]);
               var3.read(this.buildAnims[1]);
               var3.read(this.buildAnims[2]);

               for (int var12 = 0; var12 < 10; var12++) {
                  for (int var6 = 0; var6 < 12; var6++) {
                     this.buildingStores[0][var12][var6] = var3.readByte();
                     this.buildingStores[1][var12][var6] = var3.readByte();
                  }
               }

               var3.read(this.trapTable[0]);
               var3.read(this.trapTable[1]);
               var3.read(this.trapTable[2]);
               var3.read(this.trapTable[3]);

               for (int var13 = 0; var13 < 5; var13++) {
                  var3.read(this.pendingOrders[0][var13]);
                  var3.read(this.pendingOrders[1][var13]);
                  var3.read(this.aiTargets[0][var13]);
                  var3.read(this.aiTargets[1][var13]);
               }

               this.speedSetting = var3.readByte();
               this.cursorCol = this.savedCursorCol = var3.readByte();
               this.cursorRow = this.savedCursorRow = var3.readByte();
               this.camTileX = this.savedCamCol = var3.readByte();
               this.camTileY = this.savedCamRow = var3.readByte();
               this.musicMood = var3.readByte();
               this.storySceneId = var3.readByte();
               this.tickCounter = var3.readInt();
               this.placeConfirmFlagL = var3.readBoolean();
               this.placeConfirmFlagN = var3.readBoolean();

               for (int var14 = 0; var14 < 3; var14++) {
                  for (int var21 = 0; var21 < 96; var21++) {
                     this.effectBitfield[0][var14][var21] = var3.readInt();
                  }
               }

               var3.read(this.world.matingPairs[0]);
               var3.read(this.world.matingPairs[1]);
               var3.read(this.world.matingPairs[2]);
               var3.read(this.world.matingPairs[3]);
               var3.read(this.world.matingPairs[4]);
               var3.read(this.world.matingPairs[5]);
               this.world.scriptEventIndex = var3.readByte();
               this.world.rankShiftK = var3.readByte();
               this.world.scratchByteR = var3.readByte();
               this.world.scratchByteL = var3.readByte();
               this.world.nextWaveTickAQ = var3.readInt();
               var3.read(this.world.trapRegistryF[0]);
               var3.read(this.world.trapRegistryF[1]);
               var3.read(this.world.upgradeRegistryG[0]);
               var3.read(this.world.upgradeRegistryG[1]);
               var3.read(this.world.upgradeRegistryG[2]);
               var3.read(this.world.aiRank);
               this.world.groupSlotN = var3.readByte();
               this.world.groupCountO = var3.readByte();
               this.world.hintStepIndex = var3.readByte();
               this.world.needSlotJ = var3.readByte();
               this.world.slotScanI = var3.readByte();
               var3.read(this.effectCounters);
               int var7 = -111;

               while (var7 >= -122) {
                  byte var15;
                  if ((var15 = var3.readByte()) == 127) {
                     var7--;
                  } else {
                     byte var22 = var3.readByte();
                     this.occupantGrid[var22][var15] = (byte)var7;
                  }
               }

               for (int var16 = 0; var16 < 30; var16++) {
                  if (this.trapTable[0][var16] != 0) {
                     this.occupantGrid[this.trapTable[3][var16]][this.trapTable[2][var16]] = (byte)(this.trapTable[0][var16] <= 30 ? -123 : -126);
                  }
               }

               for (int var17 = 0; var17 < 50; var17++) {
                  if (this.entityData[9833 + var17] >= 6) {
                     this.occupantGrid[this.entityData[9783 + var17]][this.entityData[9733 + var17]] = (byte)(-61 - var17);
                  }
               }

               for (int var18 = 1; var18 <= 100; var18++) {
                  if (this.entityData[1919 + var18] != 0) {
                     this.entityData[808 + var18] = 0;
                     if (this.entityData[4646 + var18] < 0) {
                        this.occupantGrid[this.entityData[101 + var18]][this.entityData[0 + var18]] = (byte)var18;
                        if ((this.entityData[2929 + var18] & 255) <= 7
                           && (this.entityData[1111 + var18] & 15)
                              < (
                                    this.fileAData[this.recordStarts[15]
                                          + (this.entityData[606 + var18] & 1) * 9
                                          + this.entityData[1010 + var18]
                                          - this.entityData[7777 + var18]]
                                       + 1
                                 )
                                 / 2
                           && this.occupantGrid[this.entityData[101 + var18] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[606 + var18]]][this.entityData[0
                                    + var18]
                                 + this.fileAData[this.recordStarts[1] + this.entityData[606 + var18]]]
                              == 0) {
                           this.occupantGrid[this.entityData[101 + var18] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[606 + var18]]][this.entityData[0
                                 + var18]
                              + this.fileAData[this.recordStarts[1] + this.entityData[606 + var18]]] = -128;
                        }
                     }
                  }
               }

               for (int var19 = 0; var19 < 22; var19++) {
                  this.entityData[9689 + var19] = 0;
                  if (this.entityData[9513 + var19] != 0) {
                     this.occupantGrid[this.entityData[9381 + var19]][this.entityData[9359 + var19]] = (byte)(101 + var19);
                     if ((this.entityData[9557 + var19] & 255) <= 7
                        && (this.entityData[9623 + var19] & 15)
                           < (
                                 this.fileAData[this.recordStarts[15]
                                       + (this.entityData[9491 + var19] & 1) * 9
                                       + this.fileAData[this.recordStarts[179] + this.entityData[9535 + var19]]]
                                    + 1
                              )
                              / 2
                        && this.occupantGrid[this.entityData[9381 + var19] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[9491 + var19]]][this.entityData[9359
                                 + var19]
                              + this.fileAData[this.recordStarts[1] + this.entityData[9491 + var19]]]
                           == 0) {
                        this.occupantGrid[this.entityData[9381 + var19] + this.fileAData[this.recordStarts[1] + 8 + this.entityData[9491 + var19]]][this.entityData[9359
                              + var19]
                           + this.fileAData[this.recordStarts[1] + this.entityData[9491 + var19]]] = -128;
                     }
                  }
               }

               for (int var20 = 1; var20 <= 60; var20++) {
                  if (this.entityData[8505 + var20] != 0) {
                     for (int var8 = 0; var8 < this.fileAData[this.recordStarts[138] + 130 + this.entityData[8749 + var20]]; var8++) {
                        for (int var9 = 0; var9 < this.fileAData[this.recordStarts[138] + 104 + this.entityData[8749 + var20]]; var9++) {
                           this.occupantGrid[this.entityData[8444 + var20] + var8][this.entityData[8383 + var20] + var9] = (byte)(-var20);
                        }
                     }
                  }
               }

               this.world.rebuildSpatialHash();
               this.world.updateProgressLevels();
               this.world.rebuildFogOfWar();
               this.clampCamera();
               this.savedCamCol = this.camTileX;
               this.savedCamRow = this.camTileY;
            }

            var2 = true;
         } catch (Exception var10) {
         }

         var3.close();
         var4.closeRecordStore();
         if (!var2) {
            RecordStore.deleteRecordStore(this.recordStorePrefix + var1);
            this.loadSlot(var1);
         }

         return var2;
      } catch (Exception var11) {
         return false;
      }
   }

   // $VF: renamed from: e (int) boolean
   private boolean saveSlot(int var1) {
      boolean var2 = false;

      try {
         RecordStore var3 = null;
         ByteArrayOutputStream var4 = null;
         DataOutputStream var5 = null;

         try {
            RecordStore.deleteRecordStore(this.recordStorePrefix + var1);
            var3 = RecordStore.openRecordStore(this.recordStorePrefix + var1, true);
            var4 = new ByteArrayOutputStream();
            var5 = new DataOutputStream(var4);
            if (var1 == 0) {
               var5.writeInt(this.chiefId);
               var5.writeBoolean(this.customGameAvailable);
               var5.writeByte(this.campaignProgress);
               var5.writeByte(this.continueProgress);
               var5.writeByte(this.savedMissionProgress);
               var5.writeByte(this.campaignDifficulty);
               var5.writeByte(this.newGameFlag);
            } else {
               if (var1 == 2) {
                  this.customGameAvailable = true;
               } else if (var1 == 1) {
                  this.savedMissionProgress = this.fileAData[this.recordStarts[248] + this.missionIndex];
               }

               var5.writeByte(this.missionIndex);
               var5.writeByte(this.newGameFlag);
               var5.writeByte(this.customDifficulty);
               var5.write(this.entityData);
               var5.write(this.homePosition);
               var5.write(this.villagerGroups[0]);
               var5.write(this.villagerGroups[1]);
               var5.write(this.villagerGroups[2]);
               var5.write(this.villagerGroups[3]);

               for (int var6 = 0; var6 < 26; var6++) {
                  var5.writeShort(this.tribeStats[0][var6]);
                  var5.writeShort(this.tribeStats[1][var6]);
               }

               var5.write(this.buildAnims[0]);
               var5.write(this.buildAnims[1]);
               var5.write(this.buildAnims[2]);

               for (int var11 = 0; var11 < 10; var11++) {
                  for (int var7 = 0; var7 < 12; var7++) {
                     var5.writeByte(this.buildingStores[0][var11][var7]);
                     var5.writeByte(this.buildingStores[1][var11][var7]);
                  }
               }

               var5.write(this.trapTable[0]);
               var5.write(this.trapTable[1]);
               var5.write(this.trapTable[2]);
               var5.write(this.trapTable[3]);

               for (int var12 = 0; var12 < 5; var12++) {
                  var5.write(this.pendingOrders[0][var12]);
                  var5.write(this.pendingOrders[1][var12]);
                  var5.write(this.aiTargets[0][var12]);
                  var5.write(this.aiTargets[1][var12]);
               }

               var5.writeByte(this.speedSetting);
               var5.writeByte(this.cursorCol);
               var5.writeByte(this.cursorRow);
               var5.writeByte(this.camTileX);
               var5.writeByte(this.camTileY);
               var5.writeByte(this.musicMood);
               var5.writeByte(this.storySceneId);
               var5.writeInt(this.tickCounter);
               var5.writeBoolean(this.placeConfirmFlagL);
               var5.writeBoolean(this.placeConfirmFlagN);

               for (int var13 = 0; var13 < 3; var13++) {
                  for (int var15 = 0; var15 < 96; var15++) {
                     var5.writeInt(this.effectBitfield[0][var13][var15]);
                  }
               }

               var5.write(this.world.matingPairs[0]);
               var5.write(this.world.matingPairs[1]);
               var5.write(this.world.matingPairs[2]);
               var5.write(this.world.matingPairs[3]);
               var5.write(this.world.matingPairs[4]);
               var5.write(this.world.matingPairs[5]);
               var5.writeByte(this.world.scriptEventIndex);
               var5.writeByte(this.world.rankShiftK);
               var5.writeByte(this.world.scratchByteR);
               var5.writeByte(this.world.scratchByteL);
               var5.writeInt(this.world.nextWaveTickAQ);
               var5.write(this.world.trapRegistryF[0]);
               var5.write(this.world.trapRegistryF[1]);
               var5.write(this.world.upgradeRegistryG[0]);
               var5.write(this.world.upgradeRegistryG[1]);
               var5.write(this.world.upgradeRegistryG[2]);
               var5.write(this.world.aiRank);
               var5.writeByte(this.world.groupSlotN);
               var5.writeByte(this.world.groupCountO);
               var5.writeByte(this.world.hintStepIndex);
               var5.writeByte(this.world.needSlotJ);
               var5.writeByte(this.world.slotScanI);
               var5.write(this.effectCounters);

               for (int var8 = -111; var8 >= -122; var8--) {
                  for (int var14 = 0; var14 < this.mapWidth; var14++) {
                     for (int var16 = 0; var16 < this.mapHeight; var16++) {
                        if (this.occupantGrid[var16][var14] == var8) {
                           var5.writeByte(var14);
                           var5.writeByte(var16);
                        }
                     }
                  }

                  var5.writeByte(127);
               }
            }

            var3.addRecord(var4.toByteArray(), 0, var4.size());
            var2 = true;
         } catch (Exception var9) {
         }

         if (var3 != null) {
            var3.closeRecordStore();
         }

         if (var4 != null) {
            var4.close();
         }

         if (var5 != null) {
            var5.close();
         }
      } catch (Exception var10) {
      }

      return var2;
   }

   // $VF: renamed from: a (int, int) int
   final int randRange(int var1, int var2) {
      return (this.random.nextInt() & var1) % var2;
   }

   // $VF: renamed from: a () long
   private static long nowMillis() {
      return System.currentTimeMillis();
   }

   public final void hideNotify() {
      this.d_void();
   }

   public final void showNotify() {
      this.e_void();
   }

   // $VF: renamed from: d () void
   public final void d_void() {
      this.paused = true;
      this.hideRequested = true;
      if (this.gameLoaded && !this.pauseMusicStopped) {
         this.stopMusic();
      }

      this.pauseMusicStopped = true;
   }

   // $VF: renamed from: e () void
   public final void e_void() {
      this.paused = false;
   }

   // $VF: renamed from: aQ () void
   private void selectMusicTrack() {
      this.world.updateMusicMood();
      nextMusicTrack = this.musicTrackTable[this.musicMood * 2 + this.randRange(255, 2)];
      if (this.midiPlayer == null || this.midiPlayer.getPlayerState() <= 100 || nextMusicTrack != currentMusicTrack) {
         currentMusicTrack = nextMusicTrack;
         this.stopMusic();
         new StringBuffer().append("/s").append(nextMusicTrack).toString();
         this.midiPlayer = new MidiPlayer("audio/midi");
      }

      this.midiPlayer.setVolume(this.musicLevel);
   }

   // $VF: renamed from: aR () void
   private void stopMusic() {
      if (this.midiPlayer != null) {
         this.midiPlayer.stopPlayback();
         this.midiPlayer.closePlayer();
         this.midiPlayer = null;
      }
   }

   // $VF: renamed from: aS () void
   private void tickPauseMusicCheck() {
      if (this.hideRequested) {
         if (this.screenId != 27 && this.gameActive) {
            this.pauseScreenFirstRun = true;
            this.mode = 0;
            this.prevPrevScreen = this.prevScreen;
            this.prevScreen = this.screenId;
            this.screenId = 27;
            this.savedCursorCol = this.cursorCol;
            this.savedCursorRow = this.cursorRow;
            this.savedCamCol = this.camTileX;
            this.savedCamRow = this.camTileY;
         }

         while (this.paused) {
            this.inputLocked = false;
            Thread.yield();
            TribesMIDlet.sleep(10);
         }
      }

      this.hideRequested = false;
      this.pauseMusicStopped = false;
      if (this.screenId != 27 && this.musicLevel == 2 && (this.midiPlayer == null || this.midiPlayer != null && this.midiPlayer.getPlayerState() != 400)) {
         this.selectMusicTrack();
      }

      TribesMIDlet.sleep(10);
   }

   // $VF: renamed from: m (int) void
   private void m_int_void(int var1) {
      if (var1 == this.cheatCode[0]) {
         for (int var4 = 0; var4 < 6; var4++) {
            this.cheatKeyHistory[var4] = 0;
         }

         this.cheatKeyHistory[0] = this.cheatCode[0];
      } else {
         if (this.cheatKeyHistory[0] == this.cheatCode[0]) {
            for (int var2 = 0; var2 < 6; var2++) {
               if (this.cheatKeyHistory[var2] == 0) {
                  this.cheatKeyHistory[var2] = var1;
                  break;
               }
            }

            this.cheatSeqMatched = true;

            for (int var3 = 0; var3 < 5; var3++) {
               if (this.cheatKeyHistory[var3] != this.cheatCode[var3]) {
                  this.cheatSeqMatched = false;
                  break;
               }
            }

            if (this.cheatSeqMatched && this.cheatKeyHistory[5] != 0) {
               switch (this.cheatKeyHistory[5]) {
                  case 49:
                     this.cheatUnlockedCustom = true;
                  case 50:
                  case 52:
                  case 53:
                  case 54:
                  case 56:
                  default:
                     break;
                  case 51:
                     this.cheatFlagAg = true;
                     break;
                  case 55:
                     this.tribeStats[0][0] = (short)(this.tribeStats[0][0] + 100);
                     this.tribeStats[0][1] = (short)(this.tribeStats[0][1] + 100);
                     this.tribeStats[0][2] = (short)(this.tribeStats[0][2] + 100);
                     this.tribeStats[0][3] = (short)(this.tribeStats[0][3] + 100);
                     this.tribeStats[0][4] = (short)(this.tribeStats[0][4] + 100);
                     this.tribeStats[0][5] = (short)(this.tribeStats[0][5] + 3);
                     this.tribeStats[0][8] = (short)(this.tribeStats[0][8] + 3);
                     this.tribeStats[0][10] = (short)(this.tribeStats[0][10] + 3);
                     break;
                  case 57:
                     this.endgameTimer = 1;
                     if (this.missionIndex == 0) {
                        this.missionIndex++;
                     }
               }

               this.cheatKeyHistory[0] = 0;
            }
         }
      }
   }
}
