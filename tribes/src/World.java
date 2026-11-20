// $VF: renamed from: b
public final class World {
   // $VF: renamed from: a f
   Game game;
   // $VF: renamed from: a byte[]
   byte[] entityData;
   // $VF: renamed from: a byte[][][]
   byte[][][] villagerPaths;
   // $VF: renamed from: b byte[][][]
   byte[][][] animalPaths;
   // $VF: renamed from: a short[][]
   short[][] tribeStats;
   // $VF: renamed from: b byte[]
   byte[] recordBlob;
   // $VF: renamed from: a short[]
   short[] recordStarts;
   // $VF: renamed from: b short[]
   short[] fieldOffsets;
   // $VF: renamed from: c short[]
   short[] offsetGroupBases;
   // $VF: renamed from: c byte[][][]
   byte[][][] diseaseSpots;
   // $VF: renamed from: a byte[][]
   byte[][] hashNext;
   // $VF: renamed from: b byte[][]
   byte[][] occupancyGrid;
   // $VF: renamed from: a byte
   byte facingDirection;
   // $VF: renamed from: b byte
   byte pathChoice;
   // $VF: renamed from: c byte
   byte tileCheckResult;
   // $VF: renamed from: c byte[]
   byte[] directionSteps;
   // $VF: renamed from: d byte[]
   byte[] pathCursors;
   // $VF: renamed from: e byte[]
   byte[] pathCosts;
   // $VF: renamed from: f byte[]
   byte[] pathLengths;
   // $VF: renamed from: d byte[][][]
   byte[][][] candidatePaths;
   // $VF: renamed from: g byte[]
   byte[] pathOrder;
   // $VF: renamed from: e byte[][][]
   byte[][][] pathSegments;
   // $VF: renamed from: h byte[]
   byte[] savedTerrain;
   // $VF: renamed from: d byte
   byte tileBlockedCost;
   // $VF: renamed from: e byte
   byte prevTileBlocked;
   // $VF: renamed from: c byte[][]
   byte[][] matingPairs;
   // $VF: renamed from: a int
   int loopIndexA;
   // $VF: renamed from: b int
   int scratchIntB;
   // $VF: renamed from: c int
   int segmentCount;
   // $VF: renamed from: d int
   int pathLengthD;
   // $VF: renamed from: e int
   int pathIndexE;
   // $VF: renamed from: f int
   int dirAttemptF;
   // $VF: renamed from: g int
   int turnDirG;
   // $VF: renamed from: h int
   int spliceCountH;
   // $VF: renamed from: f byte
   byte scriptEventIndex;
   // $VF: renamed from: g byte
   byte hintStepIndex;
   // $VF: renamed from: i int
   int maxPathLength;
   // $VF: renamed from: a boolean[]
   boolean[] pathBlockedFlags;
   // $VF: renamed from: j int
   int loopIndexJ;
   // $VF: renamed from: k int
   int distSquaredK;
   // $VF: renamed from: l int
   int distSquaredL;
   // $VF: renamed from: m int
   int detourIndexM;
   // $VF: renamed from: n int
   int candidateCountN;
   // $VF: renamed from: d byte[][]
   byte[][] pathPoints;
   // $VF: renamed from: h byte
   byte pathIdScratch;
   // $VF: renamed from: i byte
   byte pathLengthAccum;
   // $VF: renamed from: j byte
   byte pathSegIndex;
   // $VF: renamed from: k byte
   byte distSquaredScratchK;
   // $VF: renamed from: l byte
   byte tileQueryCode;
   // $VF: renamed from: m byte
   byte chosenTask;
   // $VF: renamed from: o int
   int bestOffsetO;
   // $VF: renamed from: p int
   int bestDistSquaredP;
   // $VF: renamed from: q int
   int candXOrDistQ;
   // $VF: renamed from: r int
   int candYR;
   // $VF: renamed from: n byte
   byte neighborDyN;
   // $VF: renamed from: o byte
   byte neighborDxO;
   // $VF: renamed from: p byte
   byte neighborOccupantP;
   // $VF: renamed from: s int
   int slotLoopS;
   // $VF: renamed from: t int
   int workerCursorT;
   // $VF: renamed from: q byte
   byte targetXQ;
   // $VF: renamed from: r byte
   byte targetYR;
   // $VF: renamed from: d short[]
   short[] messageCooldowns;
   // $VF: renamed from: u int
   int tribeIndex;
   // $VF: renamed from: v int
   int currentVillager;
   // $VF: renamed from: w int
   int scratchIntW;
   // $VF: renamed from: x int
   int scratchIntX;
   // $VF: renamed from: y int
   int randomRollY;
   // $VF: renamed from: z int
   int distOrTargetXZ;
   // $VF: renamed from: A int
   int targetYA;
   // $VF: renamed from: B int
   int taskDurationB;
   // $VF: renamed from: C int
   int scratchIntC;
   // $VF: renamed from: D int
   int scratchIntD;
   // $VF: renamed from: E int
   int stepResultE;
   // $VF: renamed from: F int
   int timerOrCountF;
   // $VF: renamed from: a boolean
   boolean searchOkA;
   // $VF: renamed from: b boolean
   boolean searchFlagB;
   // $VF: renamed from: c boolean
   boolean isWalkingToTargetC;
   // $VF: renamed from: G int
   int missionRegionBaseG;
   // $VF: renamed from: H int
   int resolvedTaskH;
   // $VF: renamed from: d boolean
   boolean actionDoneD;
   // $VF: renamed from: s byte
   byte buildingCapacityS;
   // $VF: renamed from: I int
   int specialModeI;
   // $VF: renamed from: J int
   int hintTimerJ;
   // $VF: renamed from: K int
   int calmTimerK;
   // $VF: renamed from: t byte
   byte swapTempT;
   // $VF: renamed from: u byte
   byte savedVillagerU;
   // $VF: renamed from: L int
   int walkCycleOrProgressL;
   // $VF: renamed from: i byte[]
   byte[] buildingSlotMapI;
   // $VF: renamed from: v byte
   byte villagerLoopV;
   // $VF: renamed from: w byte
   byte workerOrParityW;
   // $VF: renamed from: x byte
   byte listCursorX;
   // $VF: renamed from: M int
   int aiRankThresholdM;
   // $VF: renamed from: y byte
   byte rollByteY;
   // $VF: renamed from: z byte
   byte scratchByteZ;
   // $VF: renamed from: A byte
   byte scratchByteA;
   // $VF: renamed from: B byte
   byte occupantByteB;
   // $VF: renamed from: C byte
   byte distByteC;
   // $VF: renamed from: D byte
   byte scratchByteD;
   // $VF: renamed from: E byte
   byte dirByteE;
   // $VF: renamed from: F byte
   byte foundEntityF;
   // $VF: renamed from: N int
   int eventRectBaseN;
   // $VF: renamed from: O int
   int waveStepO;
   // $VF: renamed from: P int
   int pendingBuildP;
   // $VF: renamed from: Q int
   int waveTargetQ;
   // $VF: renamed from: e byte[][]
   byte[][] needyListE;
   // $VF: renamed from: G byte
   byte scratchByteG;
   // $VF: renamed from: H byte
   byte scratchByteH;
   // $VF: renamed from: e boolean
   boolean councilPickedE;
   // $VF: renamed from: R int
   int flagsScratchR;
   // $VF: renamed from: S int
   int scratchIntS;
   // $VF: renamed from: f boolean
   boolean taskCompleteF;
   // $VF: renamed from: T int
   int reqIndexT;
   // $VF: renamed from: g boolean
   boolean allReqsMetG;
   // $VF: renamed from: U int
   int currentStatU;
   // $VF: renamed from: V int
   int hpMissingV;
   // $VF: renamed from: W int
   int workTargetW;
   // $VF: renamed from: X int
   int needRangeStartX;
   // $VF: renamed from: Y int
   int needIndexY;
   // $VF: renamed from: Z int
   int scratchIntZ;
   // $VF: renamed from: aa int
   int bestDistSqAa;
   // $VF: renamed from: ab int
   int candDistSqAb;
   // $VF: renamed from: ac int
   int nearestBuildingAc;
   // $VF: renamed from: h boolean
   boolean foundBuildingH;
   // $VF: renamed from: ad int
   int mateCursorAd;
   // $VF: renamed from: ae int
   int matePairIndexAe;
   // $VF: renamed from: af int
   int pairScanAf;
   // $VF: renamed from: I byte
   byte slotScanI;
   // $VF: renamed from: ag int
   int sectorIndexAg;
   // $VF: renamed from: ah int
   int cellXLoopAh;
   // $VF: renamed from: ai int
   int cellYLoopAi;
   // $VF: renamed from: aj int
   int hashKindAj;
   // $VF: renamed from: ak int
   int hashCursorAk;
   // $VF: renamed from: al int
   int hashEntityAl;
   // $VF: renamed from: am int
   int foundPriorityAm;
   // $VF: renamed from: an int
   int bestDistAn;
   // $VF: renamed from: ao int
   int distTenthAo;
   // $VF: renamed from: ap int
   int foundXAp;
   // $VF: renamed from: aq int
   int foundYAq;
   // $VF: renamed from: ar int
   int deltaXAr;
   // $VF: renamed from: as int
   int deltaYAs;
   // $VF: renamed from: at int
   int scanMinCellXAt;
   // $VF: renamed from: au int
   int scanMaxCellXAu;
   // $VF: renamed from: av int
   int scanMinCellYAv;
   // $VF: renamed from: aw int
   int scanMaxCellYAw;
   // $VF: renamed from: ax int
   int flightTimeAx;
   // $VF: renamed from: ay int
   int flightStepAy;
   // $VF: renamed from: az int
   int targetVelXAz;
   // $VF: renamed from: aA int
   int targetVelYAa;
   // $VF: renamed from: aB int
   int throwSpeedAB;
   // $VF: renamed from: aC int
   int throwFacingAC;
   // $VF: renamed from: aD int
   int throwTargetKindAD;
   // $VF: renamed from: aE int
   int throwDxAE;
   // $VF: renamed from: aF int
   int throwDyAF;
   // $VF: renamed from: aG int
   int throwWeaponAG;
   // $VF: renamed from: aH int
   int hutSlotScanAH;
   // $VF: renamed from: aI int
   int hutSlotScanAI;
   // $VF: renamed from: aJ int
   int randomRollAJ;
   // $VF: renamed from: f byte[][]
   byte[][] trapRegistryF;
   // $VF: renamed from: g byte[][]
   byte[][] upgradeRegistryG;
   // $VF: renamed from: aK int
   int capacityAK;
   // $VF: renamed from: aL int
   int workerScanAL;
   // $VF: renamed from: aM int
   int nodeLoopAM;
   // $VF: renamed from: J byte
   byte needSlotJ;
   // $VF: renamed from: K byte
   byte rankShiftK;
   // $VF: renamed from: L byte
   byte scratchByteL;
   // $VF: renamed from: aN int
   int queueScanAN;
   // $VF: renamed from: aO int
   int queuePosAO;
   // $VF: renamed from: aP int
   int queueBuildingAP;
   // $VF: renamed from: j byte[]
   byte[] aiRank;
   // $VF: renamed from: M byte
   byte towerWorkerM;
   // $VF: renamed from: N byte
   byte groupSlotN;
   // $VF: renamed from: O byte
   byte groupCountO;
   // $VF: renamed from: P byte
   byte missionRectP;
   // $VF: renamed from: Q byte
   byte waveTargetQByte;
   // $VF: renamed from: R byte
   byte scratchByteR;
   // $VF: renamed from: aQ int
   int nextWaveTickAQ;
   // $VF: renamed from: aR int
   int waveStartTickAR;
   // $VF: renamed from: aS int
   int scratchIntAS;
   // $VF: renamed from: aT int
   int scratchIntAT;
   // $VF: renamed from: aU int
   int waveEnlistedAU;
   // $VF: renamed from: aV int
   int spawnRowAV;
   // $VF: renamed from: aW int
   int fogWordAW;
   // $VF: renamed from: aX int
   int fogBitsAX;
   // $VF: renamed from: aY int
   int fogRowBitsAY;
   // $VF: renamed from: a int[]
   int[] bitmasks = new int[26];
   // $VF: renamed from: S byte
   byte scratchByteS;
   // $VF: renamed from: k byte[]
   byte[] tileQueryResult;

   public World() {
      this.bitmasks[0] = 0;
      this.bitmasks[1] = 1;
      this.bitmasks[2] = 3;
      this.bitmasks[3] = 7;
      this.bitmasks[4] = 15;
      this.bitmasks[5] = 31;
      this.bitmasks[6] = 63;
      this.bitmasks[7] = 127;
      this.bitmasks[8] = 255;
      this.bitmasks[9] = 511;
      this.bitmasks[10] = 1023;
      this.bitmasks[11] = 2047;
      this.bitmasks[12] = 4095;
      this.bitmasks[13] = 8191;
      this.bitmasks[14] = 16383;
      this.bitmasks[15] = 32767;
      this.bitmasks[16] = 65535;
      this.bitmasks[17] = 131071;
      this.bitmasks[18] = 262143;
      this.bitmasks[19] = 524287;
      this.bitmasks[20] = 1048575;
      this.bitmasks[21] = 2097151;
      this.bitmasks[22] = 4194303;
      this.bitmasks[23] = 8388607;
      this.bitmasks[24] = 16777215;
      this.bitmasks[25] = 33554431;
   }

   // $VF: renamed from: a (f) void
   final void attachGame(Game var1) {
      this.game = var1;
      this.occupancyGrid = this.game.occupantGrid;
      this.entityData = this.game.entityData;
      this.tribeStats = this.game.tribeStats;
      this.recordBlob = this.game.fileAData;
      this.recordStarts = this.game.recordStarts;
      this.fieldOffsets = this.game.offsetTable;
      this.offsetGroupBases = this.game.unusedShortArrayC;
   }

   // $VF: renamed from: a () void
   final void resetWorld() {
      this.scriptEventIndex = this.hintStepIndex = 0;
      this.timerOrCountF = 0;
      this.needSlotJ = 0;
      this.scratchByteL = 0;
      this.scratchByteS = 1;
      this.rankShiftK = 50;
      this.game.textLoader.reset();
      this.slotScanI = (byte)(this.game.missionIndex == 2 ? 3 : 0);
      this.groupCountO = -1;
      this.nextWaveTickAQ = this.waveStartTickAR = -1;
      this.scratchByteR = (byte)(this.game.missionIndex != 2 && this.game.missionIndex != 4 ? 0 : 128);
      this.missionRectP = this.waveTargetQByte = -1;

      for (this.loopIndexA = 0; this.loopIndexA < 3; this.loopIndexA++) {
         this.game.effectCounters[this.loopIndexA] = 0;
      }

      for (this.loopIndexA = 0; this.loopIndexA < 10; this.loopIndexA++) {
         this.matingPairs[4][this.loopIndexA] = 0;
      }

      for (this.loopIndexA = 0; this.loopIndexA < 6; this.loopIndexA++) {
         this.messageCooldowns[this.loopIndexA] = -1;
      }

      for (this.loopIndexA = 0; this.loopIndexA < 10; this.loopIndexA++) {
         this.trapRegistryF[0][this.loopIndexA] = 0;
      }

      for (this.loopIndexA = 0; this.loopIndexA < 20; this.loopIndexA++) {
         this.upgradeRegistryG[0][this.loopIndexA] = 0;
      }

      for (this.loopIndexA = 0; this.loopIndexA < 50; this.loopIndexA++) {
         this.aiRank[this.loopIndexA] = (byte)this.loopIndexA;
      }

      this.updateProgressLevels();
      this.missionRegionBaseG = this.recordStarts[219] + this.game.missionIndex * 4;
   }

   // $VF: renamed from: a (int, int, int, int, int, int) byte
   private byte findPath(int var1, int var2, int var3, int var4, int var5, int var6) {
      try {
         this.maxPathLength = var6 == 0 ? 80 : 5;
         this.planRoutes(var2, var1, var4, var3, var6);

         for (this.loopIndexA = 0; this.loopIndexA < this.tileCheckResult; this.loopIndexA++) {
            this.followPathStep(var6);
            if (!this.pathBlockedFlags[0] && !this.pathBlockedFlags[1]) {
               this.scoreDetour(var6);
               if (this.detourIndexM < 0) {
                  this.pathLengths[this.pathOrder[0]] = (byte)(this.pathSegments[0][2][this.loopIndexA] + 1);
               } else {
                  this.pathLengths[this.pathOrder[(this.detourIndexM & 1) + 1]] = (byte)((this.detourIndexM >> 1) + 1);
                  this.pathBlockedFlags[this.detourIndexM & 1] = true;
                  this.mergePaths(false);
                  this.pathBlockedFlags[this.detourIndexM & 1] = false;
               }
               break;
            }

            this.mergePaths(true);
            if (this.pathLengths[this.pathOrder[0]] + var5 >= this.maxPathLength) {
               break;
            }
         }

         for (this.loopIndexJ = 0; this.loopIndexJ < this.tileCheckResult; this.loopIndexJ++) {
            if (this.pathSegments[1][0][this.loopIndexJ] >= 0) {
               this.game.terrainGrid[this.pathSegments[1][1][this.loopIndexJ]][this.pathSegments[1][0][this.loopIndexJ]] = this.savedTerrain[this.loopIndexJ];
            }
         }

         if (this.pathLengths[this.pathOrder[0]] + var5 >= this.maxPathLength) {
            this.pathLengths[this.pathOrder[0]] = (byte)(this.maxPathLength - var5);
         }

         for (this.loopIndexA = 0; this.loopIndexA < this.pathLengths[this.pathOrder[0]]; this.loopIndexA++) {
            if (var6 == 0) {
               this.villagerPaths[0][this.currentVillager][this.loopIndexA + var5] = this.candidatePaths[this.pathOrder[0]][0][this.loopIndexA];
               this.villagerPaths[1][this.currentVillager][this.loopIndexA + var5] = this.candidatePaths[this.pathOrder[0]][1][this.loopIndexA];
            } else {
               this.animalPaths[0][this.currentVillager][this.loopIndexA + var5] = this.candidatePaths[this.pathOrder[0]][0][this.loopIndexA];
               this.animalPaths[1][this.currentVillager][this.loopIndexA + var5] = this.candidatePaths[this.pathOrder[0]][1][this.loopIndexA];
            }
         }

         if (var6 == 0) {
            this.entityData[707 + this.currentVillager] = 0;
            this.entityData[3030 + this.currentVillager] = 0;
            this.setIdleRole();
         }
      } catch (Exception var8) {
      }

      return this.pathLengths[this.pathOrder[0]];
   }

   // $VF: renamed from: a (int, int, int, int) byte
   private byte findPathTo(int var1, int var2, int var3, int var4) {
      try {
         this.pathPoints[0][0] = (byte)var2;
         this.pathPoints[1][0] = (byte)var1;
         this.pathLengthD = 1;
         this.planRoutes(var2, var1, var4, var3, 0);

         for (this.loopIndexA = 0; this.loopIndexA < this.tileCheckResult; this.loopIndexA++) {
            this.followPathStep(0);
            if (!this.pathBlockedFlags[0] && !this.pathBlockedFlags[1]) {
               this.scoreDetour(0);
               this.pathPoints[0][this.pathLengthD] = this.detourIndexM < 0
                  ? this.pathSegments[0][0][this.loopIndexA]
                  : this.candidatePaths[this.pathOrder[(this.detourIndexM & 1) + 1]][0][this.detourIndexM >> 1];
               this.pathPoints[1][this.pathLengthD] = this.detourIndexM < 0
                  ? this.pathSegments[0][1][this.loopIndexA]
                  : this.candidatePaths[this.pathOrder[(this.detourIndexM & 1) + 1]][1][this.detourIndexM >> 1];

               for (this.loopIndexJ = 0; this.loopIndexJ < this.tileCheckResult; this.loopIndexJ++) {
                  if (this.pathSegments[1][0][this.loopIndexJ] >= 0) {
                     this.game.terrainGrid[this.pathSegments[1][1][this.loopIndexJ]][this.pathSegments[1][0][this.loopIndexJ]] = this.savedTerrain[this.loopIndexJ];
                  }
               }

               return (byte)this.pathLengthD;
            }

            this.scratchIntB = (!this.pathBlockedFlags[0] || this.pathLengths[this.pathOrder[1]] >= this.pathLengths[this.pathOrder[2]])
                  && this.pathBlockedFlags[1]
               ? 2
               : 1;
            this.pathPoints[0][this.pathLengthD] = this.candidatePaths[this.pathOrder[this.scratchIntB]][0][this.pathLengths[this.pathOrder[this.scratchIntB]]
               / 2];
            this.pathPoints[1][this.pathLengthD] = this.candidatePaths[this.pathOrder[this.scratchIntB]][1][this.pathLengths[this.pathOrder[this.scratchIntB]]
               / 2];
            this.loopIndexA = this.pathCosts[this.pathOrder[this.scratchIntB]];
            this.pathLengthD++;
         }

         this.distSquaredL = 0;
         this.pathPoints[0][this.pathLengthD] = (byte)var4;
         this.pathPoints[1][this.pathLengthD] = (byte)var3;

         for (this.loopIndexJ = 0; this.loopIndexJ < this.tileCheckResult; this.loopIndexJ++) {
            if (this.pathSegments[1][0][this.loopIndexJ] >= 0) {
               this.game.terrainGrid[this.pathSegments[1][1][this.loopIndexJ]][this.pathSegments[1][0][this.loopIndexJ]] = this.savedTerrain[this.loopIndexJ];
            }
         }
      } catch (Exception var6) {
      }

      return (byte)this.pathLengthD;
   }

   // $VF: renamed from: d (int) void
   private void scoreDetour(int var1) {
      this.distanceTo(this.pathSegments[0][0][this.loopIndexA], this.pathSegments[0][1][this.loopIndexA], var1);
      this.distSquaredL = (this.targetXQ - this.pathSegments[0][0][this.loopIndexA]) * (this.targetXQ - this.pathSegments[0][0][this.loopIndexA])
         + (this.targetYR - this.pathSegments[0][1][this.loopIndexA]) * (this.targetYR - this.pathSegments[0][1][this.loopIndexA]);
      this.detourIndexM = -1;
      if (this.distSquaredL > this.taskDurationB) {
         this.candidateCountN = this.pathLengths[this.pathOrder[this.pathLengths[this.pathOrder[1]] > this.pathLengths[this.pathOrder[2]] ? 1 : 2]] * 2;

         for (this.loopIndexJ = 0; this.loopIndexJ < this.candidateCountN; this.loopIndexJ++) {
            if (this.pathLengths[this.pathOrder[(this.loopIndexJ & 1) + 1]] > this.loopIndexJ >> 1) {
               this.distSquaredK = (this.targetXQ - this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][0][this.loopIndexJ >> 1])
                     * (this.targetXQ - this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][0][this.loopIndexJ >> 1])
                  + (this.targetYR - this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][1][this.loopIndexJ >> 1])
                     * (this.targetYR - this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][1][this.loopIndexJ >> 1]);
               if (this.distSquaredK < this.distSquaredL) {
                  this.distSquaredL = this.distSquaredK;
                  this.detourIndexM = this.loopIndexJ;
                  if (this.distanceTo(
                           this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][0][this.loopIndexJ >> 1],
                           this.candidatePaths[this.pathOrder[(this.loopIndexJ & 1) + 1]][1][this.loopIndexJ >> 1],
                           var1
                        )
                        / 10
                     <= this.taskDurationB) {
                     return;
                  }
               }
            }
         }
      }
   }

   // $VF: renamed from: b (int, int, int, int) byte
   private byte pathLengthViaWaypoints(int var1, int var2, int var3, int var4) {
      this.pathLengthAccum = 0;
      this.pathIdScratch = this.findPathTo(var1, var2, var3, var4);

      for (this.pathSegIndex = 0; this.pathSegIndex < this.pathIdScratch; this.pathSegIndex++) {
         this.pathLengthAccum = (byte)(
            this.pathLengthAccum
               + this.findPath(
                  this.pathPoints[1][this.pathSegIndex],
                  this.pathPoints[0][this.pathSegIndex],
                  this.pathPoints[1][this.pathSegIndex + 1],
                  this.pathPoints[0][this.pathSegIndex + 1],
                  this.pathLengthAccum,
                  0
               )
         );
         if (!this.pathBlockedFlags[0] && !this.pathBlockedFlags[1]) {
            break;
         }
      }

      return this.game.missionIndex == 3 && this.pathLengthAccum > 2 ? 2 : this.pathLengthAccum;
   }

   // $VF: renamed from: o () void
   private void repathToTarget() {
      if (this.entityData[4848 + this.currentVillager] > 1) {
         this.entityData[4848 + this.currentVillager] = 0;
      }

      this.pathBlockedFlags[2] = true;
      this.entityData[808 + this.currentVillager] = this.pathLengthViaWaypoints(
         this.entityData[101 + this.currentVillager],
         this.entityData[0 + this.currentVillager],
         this.entityData[3232 + this.currentVillager],
         this.entityData[3131 + this.currentVillager]
      );
   }

   // $VF: renamed from: p () void
   private void updateMovement() {
      this.repathToTarget();
      if (this.entityData[808 + this.currentVillager] < 1
         && this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) / 10 > this.taskDurationB) {
         this.entityData[4848 + this.currentVillager]++;
         if (!this.pathBlockedFlags[2]) {
            return;
         }

         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 223);
         if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 1) {
            this.findFreeTileNear(this.entityData[8749 + this.entityData[4444 + this.currentVillager]], this.entityData[4444 + this.currentVillager]);
         }

         if (this.entityData[2828 + this.currentVillager] == 69
            || this.entityData[2828 + this.currentVillager] == 79
            || this.entityData[2828 + this.currentVillager] == 59
            || this.entityData[2828 + this.currentVillager] == 87) {
            this.entityData[4848 + this.currentVillager] = 0;
            if (this.entityData[6464 + this.currentVillager] == 69
               && this.entityData[6262 + this.currentVillager] == this.entityData[3131 + this.currentVillager]
               && this.entityData[6363 + this.currentVillager] == this.entityData[3232 + this.currentVillager]) {
               this.entityData[6262 + this.currentVillager] = this.entityData[0 + this.currentVillager];
               this.entityData[6363 + this.currentVillager] = this.entityData[101 + this.currentVillager];
            }

            this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
            this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
            return;
         }

         if (this.entityData[2828 + this.currentVillager] == 17
            || this.entityData[2828 + this.currentVillager] == 109
            || this.entityData[2828 + this.currentVillager] == 110
            || this.entityData[2828 + this.currentVillager] == 27) {
            this.retargetAfterIdle();
            return;
         }

         if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] != 1 && this.entityData[2828 + this.currentVillager] > 9) {
            this.spiralSearchTile(
               0,
               this.entityData[4949 + this.currentVillager] * 2 + 1,
               clamp(this.entityData[0 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapWidth - 1),
               clamp(this.entityData[101 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapHeight - 1)
            );
            return;
         }
      } else if (this.entityData[808 + this.currentVillager] > 0
         && (
            this.entityData[2828 + this.currentVillager] <= 9
               || this.entityData[2828 + this.currentVillager] == 59
               || this.entityData[2828 + this.currentVillager] == 87
               || this.tribeIndex == 0 && this.game.placeConfirmFlagN
               || this.tribeIndex == 1 && this.waveTargetQByte > 0
               || this.game.missionIndex == 3 && this.scriptEventIndex == 4
         )) {
         this.entityData[7777 + this.currentVillager] = 3;
      }
   }

   // $VF: renamed from: a (int, int, int, int, int) void
   private void planRoutes(int var1, int var2, int var3, int var4, int var5) {
      try {
         this.computeDirection(var3 - var1, var4 - var2);

         for (this.loopIndexA = 0; this.loopIndexA < 3; this.loopIndexA++) {
            this.pathCursors[this.loopIndexA << 1] = (byte)var1;
            this.pathCursors[(this.loopIndexA << 1) + 1] = (byte)var2;
         }

         this.buildCandidatePaths(var5);
         this.scratchIntB = 1000;

         for (this.loopIndexA = 0; this.loopIndexA < 3; this.loopIndexA++) {
            if (this.pathCosts[this.loopIndexA] < this.scratchIntB) {
               this.scratchIntB = this.pathCosts[this.loopIndexA];
               this.pathOrder[0] = (byte)this.loopIndexA;
            }
         }

         this.pathOrder[1] = (byte)((this.pathOrder[0] + 1) % 3);
         this.pathOrder[2] = (byte)((this.pathOrder[0] + 2) % 3);
         this.tileBlockedCost = 0;
         this.tileCheckResult = 0;
         if (this.pathCosts[this.pathOrder[0]] > 0) {
            for (this.loopIndexA = 0; this.loopIndexA < this.pathLengths[this.pathOrder[0]]; this.loopIndexA++) {
               this.prevTileBlocked = this.tileBlockedCost;
               byte var6 = this.candidatePaths[this.pathOrder[0]][0][this.loopIndexA];
               byte var7 = this.candidatePaths[this.pathOrder[0]][1][this.loopIndexA];
               this.tileBlockedCost = this.occupancyGrid[var7][var6] == 0 ? 0 : this.tileWalkable(var6, var7, var5);
               if (this.prevTileBlocked == 0 && this.tileBlockedCost != 0) {
                  if (this.tileCheckResult >= 10) {
                     return;
                  }

                  this.pathSegments[0][0][this.tileCheckResult] = (byte)(
                     this.loopIndexA == 0 ? var1 : this.candidatePaths[this.pathOrder[0]][0][this.loopIndexA - 1]
                  );
                  this.pathSegments[0][1][this.tileCheckResult] = (byte)(
                     this.loopIndexA == 0 ? var2 : this.candidatePaths[this.pathOrder[0]][1][this.loopIndexA - 1]
                  );
                  this.pathSegments[0][2][this.tileCheckResult] = (byte)(this.loopIndexA - 1);
                  this.pathSegments[1][0][this.tileCheckResult] = -1;
                  this.tileCheckResult++;
               } else if (this.prevTileBlocked != 0 && this.tileBlockedCost == 0) {
                  this.pathSegments[1][0][this.tileCheckResult - 1] = var6;
                  this.pathSegments[1][1][this.tileCheckResult - 1] = var7;
                  this.pathSegments[1][2][this.tileCheckResult - 1] = (byte)this.loopIndexA;
                  this.savedTerrain[this.tileCheckResult - 1] = this.game.terrainGrid[var7][var6];
                  this.game.terrainGrid[var7][var6] = (byte)(256 - this.tileCheckResult);
               }
            }
         }
      } catch (Exception var8) {
      }
   }

   // $VF: renamed from: a (int, int) void
   private void computeDirection(int var1, int var2) {
      this.facingDirection = 0;
      if (var1 > 0) {
         this.facingDirection = (byte)(this.facingDirection + 4);
      } else {
         var1 *= -1;
      }

      if (var2 > 0) {
         this.facingDirection = (byte)(this.facingDirection + 2);
      } else {
         var2 *= -1;
      }

      if (var1 > var2) {
         this.facingDirection++;
         this.directionSteps[0] = (byte)(var1 - var2);
         this.directionSteps[1] = (byte)var2;
      } else {
         this.directionSteps[0] = (byte)(var2 - var1);
         this.directionSteps[1] = (byte)var1;
      }
   }

   // $VF: renamed from: e (int) void
   private void buildCandidatePaths(int var1) {
      for (this.loopIndexA = 0; this.loopIndexA < 3; this.loopIndexA++) {
         this.pathLengths[this.loopIndexA] = (byte)(this.directionSteps[0] + this.directionSteps[1]);
         this.pathCosts[this.loopIndexA] = 0;
      }

      for (this.pathIndexE = 0; this.pathIndexE < 2; this.pathIndexE++) {
         this.segmentCount = 0;

         for (this.loopIndexA = this.pathIndexE; this.loopIndexA != 2 - 3 * this.pathIndexE; this.loopIndexA = this.loopIndexA + (1 - 2 * this.pathIndexE)) {
            for (this.scratchIntB = 0; this.scratchIntB < this.directionSteps[this.loopIndexA]; this.segmentCount++) {
               this.pathCursors[this.pathIndexE << 1] = (byte)(
                  this.pathCursors[this.pathIndexE << 1] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + this.loopIndexA * 2]
               );
               this.pathCursors[(this.pathIndexE << 1) + 1] = (byte)(
                  this.pathCursors[(this.pathIndexE << 1) + 1] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + this.loopIndexA * 2 + 1]
               );
               this.candidatePaths[this.pathIndexE][0][this.segmentCount] = this.pathCursors[this.pathIndexE << 1];
               this.candidatePaths[this.pathIndexE][1][this.segmentCount] = this.pathCursors[(this.pathIndexE << 1) + 1];
               this.pathCosts[this.pathIndexE] = (byte)(
                  this.pathCosts[this.pathIndexE]
                     + (
                        this.occupancyGrid[this.pathCursors[(this.pathIndexE << 1) + 1]][this.pathCursors[this.pathIndexE << 1]] == 0
                           ? 0
                           : this.tileWalkable(this.pathCursors[this.pathIndexE << 1], this.pathCursors[(this.pathIndexE << 1) + 1], var1)
                     )
               );
               this.scratchIntB++;
            }
         }
      }

      this.pathIndexE = 0;
      this.dirAttemptF = 0;
      this.scratchIntB = this.turnDirG = this.directionSteps[1] > 0 ? this.directionSteps[0] * 50 / this.directionSteps[1] : 1000000;

      for (this.loopIndexA = 0; this.loopIndexA < this.directionSteps[0] + this.directionSteps[1]; this.loopIndexA++) {
         if ((this.scratchIntB < 50 || this.pathIndexE >= this.directionSteps[0]) && this.dirAttemptF < this.directionSteps[1]) {
            this.pathCursors[4] = (byte)(this.pathCursors[4] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + 2]);
            this.pathCursors[5] = (byte)(this.pathCursors[5] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + 3]);
            this.dirAttemptF++;
            this.scratchIntB = this.scratchIntB + this.turnDirG;
         } else {
            this.pathCursors[4] = (byte)(this.pathCursors[4] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + 0]);
            this.pathCursors[5] = (byte)(this.pathCursors[5] + this.recordBlob[this.recordStarts[0] + this.facingDirection * 4 + 1]);
            this.pathIndexE++;
            this.scratchIntB -= 50;
         }

         this.candidatePaths[2][0][this.loopIndexA] = this.pathCursors[4];
         this.candidatePaths[2][1][this.loopIndexA] = this.pathCursors[5];
         this.pathCosts[2] = (byte)(
            this.pathCosts[2]
               + (this.occupancyGrid[this.pathCursors[5]][this.pathCursors[4]] == 0 ? 0 : this.tileWalkable(this.pathCursors[4], this.pathCursors[5], var1))
         );
      }
   }

   // $VF: renamed from: f (int) void
   private void followPathStep(int var1) {
      try {
         this.turnDirG = 1;

         for (this.pathIndexE = 1; this.pathIndexE < 3; this.pathIndexE++) {
            this.spliceCountH = this.pathOrder[this.pathIndexE];
            this.pathLengths[this.spliceCountH] = 0;
            this.pathBlockedFlags[this.pathIndexE - 1] = false;
            this.pathChoice = this.recordBlob[this.recordStarts[3]
               + this.candidatePaths[this.pathOrder[0]][0][this.pathSegments[0][2][this.loopIndexA] + 1]
               - this.pathSegments[0][0][this.loopIndexA]
               + (this.candidatePaths[this.pathOrder[0]][1][this.pathSegments[0][2][this.loopIndexA] + 1] - this.pathSegments[0][1][this.loopIndexA]) * 3
               + 4];
            this.pathCursors[this.spliceCountH * 2] = this.pathSegments[0][0][this.loopIndexA];
            this.pathCursors[this.spliceCountH * 2 + 1] = this.pathSegments[0][1][this.loopIndexA];

            for (this.scratchIntB = 0; this.scratchIntB < 80; this.scratchIntB++) {
               for (this.dirAttemptF = 0; this.dirAttemptF < 8; this.dirAttemptF++) {
                  byte var2 = (byte)(this.pathCursors[this.spliceCountH * 2] + this.recordBlob[this.recordStarts[1] + this.pathChoice]);
                  byte var3 = (byte)(this.pathCursors[this.spliceCountH * 2 + 1] + this.recordBlob[this.recordStarts[1] + 8 + this.pathChoice]);
                  if ((
                        this.segmentCount = var2 < this.game.mapWidth && var3 < this.game.mapHeight && var2 >= 0 && var3 >= 0
                           ? (this.occupancyGrid[var3][var2] == 0 ? 0 : this.tileWalkable(var2, var3, var1))
                           : 2
                     )
                     == 2) {
                     this.scratchIntB = 130;
                     break;
                  }

                  if (this.segmentCount == 0) {
                     if ((this.pathChoice & 1) > 0) {
                        if ((
                                 var2 = this.game.terrainGrid[this.pathCursors[this.spliceCountH * 2 + 1]
                                    + this.recordBlob[this.recordStarts[1] + 8 + (this.pathChoice + this.turnDirG & 7)]][this.pathCursors[this.spliceCountH * 2]
                                    + this.recordBlob[this.recordStarts[1] + (this.pathChoice + this.turnDirG & 7)]]
                              )
                              < -this.loopIndexA
                           && var2 >= -10) {
                           this.pathCosts[this.spliceCountH] = (byte)(-(var2 + 1));
                           this.pathBlockedFlags[this.pathIndexE - 1] = true;
                           this.scratchIntB = 130;
                           break;
                        }

                        if (this.pathCursors[this.spliceCountH * 2] + this.recordBlob[this.recordStarts[1] + (this.pathChoice + this.turnDirG & 7)]
                              == this.pathSegments[0][0][this.loopIndexA]
                           && this.pathCursors[this.spliceCountH * 2 + 1] + this.recordBlob[this.recordStarts[1] + 8 + (this.pathChoice + this.turnDirG & 7)]
                              == this.pathSegments[0][1][this.loopIndexA]) {
                           this.scratchIntB = 130;
                           break;
                        }
                     }

                     this.pathCursors[this.spliceCountH * 2] = (byte)(
                        this.pathCursors[this.spliceCountH * 2] + this.recordBlob[this.recordStarts[1] + this.pathChoice]
                     );
                     this.pathCursors[this.spliceCountH * 2 + 1] = (byte)(
                        this.pathCursors[this.spliceCountH * 2 + 1] + this.recordBlob[this.recordStarts[1] + 8 + this.pathChoice]
                     );
                     break;
                  }

                  this.pathChoice = (byte)(this.pathChoice + this.turnDirG & 7);
               }

               if (this.dirAttemptF >= 8
                  || this.pathCursors[this.spliceCountH * 2] == this.pathSegments[0][0][this.loopIndexA]
                     && this.pathCursors[this.spliceCountH * 2 + 1] == this.pathSegments[0][1][this.loopIndexA]) {
                  if (this.dirAttemptF >= 8) {
                     this.pathBlockedFlags[2] = false;
                  }
                  break;
               }

               if (this.scratchIntB > 127) {
                  break;
               }

               byte var6;
               if ((var6 = this.game.terrainGrid[this.pathCursors[this.spliceCountH * 2 + 1]][this.pathCursors[this.spliceCountH * 2]]) < -this.loopIndexA
                  && var6 >= -10) {
                  this.pathCosts[this.spliceCountH] = (byte)(-(var6 + 1));
                  this.pathBlockedFlags[this.pathIndexE - 1] = true;
                  break;
               }

               this.candidatePaths[this.spliceCountH][0][this.scratchIntB] = this.pathCursors[this.spliceCountH * 2];
               this.candidatePaths[this.spliceCountH][1][this.scratchIntB] = this.pathCursors[this.spliceCountH * 2 + 1];
               this.pathLengths[this.spliceCountH]++;
               this.pathChoice = (byte)(this.pathChoice - this.turnDirG * 2 & 7);
            }

            this.turnDirG *= -1;
         }
      } catch (Exception var4) {
      }
   }

   // $VF: renamed from: a (boolean) void
   private void mergePaths(boolean var1) {
      if (!this.pathBlockedFlags[0] || this.pathLengths[this.pathOrder[1]] > this.pathLengths[this.pathOrder[2]] && this.pathBlockedFlags[1]) {
         this.pathIndexE = this.pathOrder[1];
         this.pathOrder[1] = this.pathOrder[2];
         this.pathOrder[2] = (byte)this.pathIndexE;
      }

      for (this.pathIndexE = 0; this.pathIndexE <= this.pathSegments[0][2][this.loopIndexA]; this.pathIndexE++) {
         this.candidatePaths[this.pathOrder[2]][0][this.pathIndexE] = this.candidatePaths[this.pathOrder[0]][0][this.pathIndexE];
         this.candidatePaths[this.pathOrder[2]][1][this.pathIndexE] = this.candidatePaths[this.pathOrder[0]][1][this.pathIndexE];
      }

      this.pathLengths[this.pathOrder[2]] = (byte)(this.pathSegments[0][2][this.loopIndexA] + 1);

      for (this.pathIndexE = 0;
         this.pathIndexE <= this.pathLengths[this.pathOrder[1]] && this.pathIndexE + this.pathLengths[this.pathOrder[2]] < this.maxPathLength;
         this.pathIndexE++
      ) {
         this.candidatePaths[this.pathOrder[2]][0][this.pathIndexE + this.pathLengths[this.pathOrder[2]]] = this.candidatePaths[this.pathOrder[1]][0][this.pathIndexE];
         this.candidatePaths[this.pathOrder[2]][1][this.pathIndexE + this.pathLengths[this.pathOrder[2]]] = this.candidatePaths[this.pathOrder[1]][1][this.pathIndexE];
      }

      this.pathLengths[this.pathOrder[2]] = (byte)clamp(this.pathLengths[this.pathOrder[2]] + this.pathLengths[this.pathOrder[1]], 0, this.maxPathLength);
      this.pathIndexE = this.pathOrder[0];
      this.pathOrder[0] = this.pathOrder[2];
      this.pathOrder[2] = (byte)this.pathIndexE;
      if (var1 && this.pathLengths[this.pathOrder[0]] < this.maxPathLength) {
         this.turnDirG = this.pathSegments[1][2][this.pathCosts[this.pathOrder[1]]];
         this.spliceCountH = this.pathLengths[this.pathOrder[1]] - this.turnDirG + this.pathSegments[0][2][this.loopIndexA] + 1;

         for (this.pathIndexE = 0;
            this.pathIndexE <= this.pathLengths[this.pathOrder[2]] - this.turnDirG
               && this.pathIndexE + this.pathLengths[this.pathOrder[0]] < this.maxPathLength;
            this.pathIndexE++
         ) {
            this.candidatePaths[this.pathOrder[0]][0][this.pathIndexE + this.pathLengths[this.pathOrder[0]]] = this.candidatePaths[this.pathOrder[2]][0][this.pathIndexE
               + this.turnDirG];
            this.candidatePaths[this.pathOrder[0]][1][this.pathIndexE + this.pathLengths[this.pathOrder[0]]] = this.candidatePaths[this.pathOrder[2]][1][this.pathIndexE
               + this.turnDirG];
         }

         this.pathLengths[this.pathOrder[0]] = (byte)clamp(
            this.pathLengths[this.pathOrder[0]] + this.pathLengths[this.pathOrder[2]] - this.turnDirG, 0, this.maxPathLength
         );
         this.loopIndexA = this.pathCosts[this.pathOrder[1]];

         for (this.pathIndexE = this.loopIndexA + 1; this.pathIndexE < this.tileCheckResult; this.pathIndexE++) {
            this.pathSegments[0][2][this.pathIndexE] = (byte)(this.pathSegments[0][2][this.pathIndexE] + this.spliceCountH);
            this.pathSegments[1][2][this.pathIndexE] = (byte)(this.pathSegments[1][2][this.pathIndexE] + this.spliceCountH);
         }
      }
   }

   // $VF: renamed from: a (int, int, int) byte
   private byte tileWalkable(int var1, int var2, int var3) {
      if (var3 == 0
         && (this.occupancyGrid[var2][var1] > 0 && this.occupancyGrid[var2][var1] <= 122 || this.occupancyGrid[var2][var1] == -128)
         && this.recordBlob[this.recordStarts[53]
               + (
                     var2 - this.entityData[101 + this.currentVillager] >= -12 && var2 - this.entityData[101 + this.currentVillager] <= 12
                        ? var2 - this.entityData[101 + this.currentVillager] + 12
                        : 0
                  )
                  * 25
               + (
                  var1 - this.entityData[0 + this.currentVillager] >= -12 && var1 - this.entityData[0 + this.currentVillager] <= 12
                     ? var1 - this.entityData[0 + this.currentVillager] + 12
                     : 0
               )]
            > 29) {
         if (this.occupancyGrid[var2][var1] == -128) {
            return 0;
         }

         if ((
               this.entityData[this.fieldOffsets[this.occupancyGrid[var2][var1] <= 100 ? 29 : 108]
                     + (this.occupancyGrid[var2][var1] <= 100 ? this.occupancyGrid[var2][var1] : this.occupancyGrid[var2][var1] - 101)]
                  & 255
            )
            <= 7) {
            return 0;
         }
      }

      if (this.tribeIndex == 0 && var3 == 0 && (this.game.effectBitfield[0][var1 >> 5][var2] >> (var1 & 31) & 1) == 0) {
         return 0;
      } else if (var3 == 0
         && this.occupancyGrid[var2][var1] <= -111 - (this.tribeIndex ^ 1) * 3 * 2
         && this.occupancyGrid[var2][var1] > -111 - (this.tribeIndex ^ 1) * 3 * 2 - 3
         && this.entityData[1414 + this.currentVillager] >= 12) {
         return 0;
      } else if (this.occupancyGrid[var2][var1] == this.currentVillager + var3) {
         return 0;
      } else if (var3 != 0
         || this.entityData[4848 + this.currentVillager] <= 0
         || this.occupancyGrid[var2][var1] <= this.tribeIndex * 50
         || this.occupancyGrid[var2][var1] > (this.tribeIndex + 1) * 50
         || this.recordBlob[this.recordStarts[43] + this.entityData[2828 + this.currentVillager]]
               <= this.recordBlob[this.recordStarts[43] + this.entityData[2828 + this.occupancyGrid[var2][var1]]]
            && this.recordBlob[this.recordStarts[43] + this.entityData[2828 + this.occupancyGrid[var2][var1]]] != 0
         || (this.entityData[2929 + this.occupancyGrid[var2][var1]] < 8 || this.entityData[2929 + this.occupancyGrid[var2][var1]] >= 21)
            && this.entityData[2929 + this.occupancyGrid[var2][var1]] != -101
            && this.entityData[2929 + this.occupancyGrid[var2][var1]] != -100
            && (
               (this.entityData[2929 + this.occupancyGrid[var2][var1]] & 255) <= 7
                  || this.entityData[2828 + this.occupancyGrid[var2][var1]] != 69
                     && this.entityData[2828 + this.occupancyGrid[var2][var1]] != 79
                     && this.entityData[2828 + this.occupancyGrid[var2][var1]] != 80
                     && (this.entityData[2828 + this.occupancyGrid[var2][var1]] < 83 || this.entityData[2828 + this.occupancyGrid[var2][var1]] > 85)
            )) {
         return (byte)(var3 == 0 && this.occupancyGrid[var2][var1] == -127 && this.entityData[2828 + this.currentVillager] == 13 ? 0 : 1);
      } else {
         return 0;
      }
   }

   // $VF: renamed from: g (int) void
   private void moveVillagerStep(int var1) {
      try {
         if (this.entityData[6161 + this.currentVillager] > 1) {
            this.entityData[6161 + this.currentVillager] = (byte)(this.entityData[6161 + this.currentVillager] == 2 ? 1 : 0);
            if (this.slotScanI == 1 || this.slotScanI == 2 || this.game.missionIndex == 2 && this.tribeIndex == 1 && this.scriptEventIndex == 1) {
               this.entityData[7777 + this.currentVillager] = 3;
            }
         }

         if (var1 < 3) {
            this.clearMatingPair(this.currentVillager, var1);
         }

         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
            this.leaveBuilding();
         } else {
            this.inspectTileTarget(this.entityData[3131 + this.currentVillager], this.entityData[3232 + this.currentVillager], var1 > 0);
            if (this.tileQueryCode >= 0 && (this.tileQueryCode != 10 || var1 < 2)) {
               this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
               this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
               this.goIdle();
               if (this.entityData[6161 + this.currentVillager] == 1) {
                  this.showMessage(this.tileQueryCode, this.currentVillager, this.currentVillager);
               }
            } else {
               this.handleBlockedMove(var1);
            }
         }
      } catch (Exception var3) {
      }
   }

   // $VF: renamed from: h (int) void
   private void handleBlockedMove(int var1) {
      this.entityData[4444 + this.currentVillager] = this.tileQueryResult[2];
      if (this.chosenTask == 2 || this.chosenTask == 7) {
         this.entityData[7979 + this.currentVillager] = (byte)(
            this.entityData[2828 + this.currentVillager] != 60
                  && var1 != 0
                  && (
                     this.entityData[2828 + this.currentVillager] != 2 && this.entityData[2828 + this.currentVillager] != 7
                        || (this.entityData[7979 + this.currentVillager] & 128) != 0
                  )
               ? this.entityData[7979 + this.currentVillager] | 128
               : this.entityData[7979 + this.currentVillager] & 127
         );
      }

      if ((
            var1 == 0
               || !this.game.placeConfirmFlagN
                  && (this.chosenTask == 12 || this.chosenTask == 16)
                  && (
                     this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 3
                        || this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 7
                  )
         )
         && (!this.game.placeConfirmFlagN && this.rankShiftK >= 50 || this.recordBlob[this.recordStarts[41] + this.chosenTask] != 0)) {
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
            && (
               this.recordBlob[this.recordStarts[39] + this.chosenTask] != 1
                  || this.entityData[8810 + this.tileQueryResult[2]] != this.currentVillager && this.recordBlob[this.recordStarts[41] + this.chosenTask] != 0
            )) {
            this.game.unassignEntity(this.currentVillager);
         }

         if (this.recordBlob[this.recordStarts[39] + this.chosenTask] != 1) {
            this.entityData[6464 + this.currentVillager] = var1 == 0 && this.chosenTask >= 19 && this.chosenTask <= 26
               ? 69
               : this.recordBlob[this.recordStarts[29] + this.chosenTask];
            this.entityData[6262 + this.currentVillager] = this.entityData[3131 + this.currentVillager];
            this.entityData[6363 + this.currentVillager] = this.entityData[3232 + this.currentVillager];
         }
      }

      if (this.entityData[1414 + this.currentVillager] >= 12
         && (
            var1 < 2
               || !this.game.placeConfirmFlagN
                  && (this.chosenTask == 12 || this.chosenTask == 16)
                  && (
                     this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 3
                        || this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 7
                  )
         )) {
         this.setTask(this.currentVillager, this.chosenTask);
      }

      if (this.entityData[2828 + this.currentVillager] == 13) {
         if (this.entityData[3131 + this.currentVillager] > 0
            && (
               this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager] - 1] == -127
                  || this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager] - 1] == this.currentVillager
            )) {
            this.entityData[3131 + this.currentVillager]--;
         }

         if (this.entityData[3131 + this.currentVillager] < this.game.mapWidth - 1
            && (
               this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager] + 1] == -127
                  || this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager] + 1] == this.currentVillager
            )) {
            this.entityData[3131 + this.currentVillager]++;
         }
      }

      if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 1) {
         this.findFreeTileNear(this.tileQueryResult[1], this.tileQueryResult[2]);
      }
   }

   // $VF: renamed from: a (int, int, boolean) void
   private void inspectTileTarget(int var1, int var2, boolean var3) {
      if (var3) {
         this.entityData[4747 + this.currentVillager] = this.occupancyGrid[var2][var1];
      }

      this.queryTile(var1, var2, this.tribeIndex, 0);
      this.distSquaredScratchK = this.tileQueryResult[0];
      this.tileQueryCode = this.queryTileKind();
      if (this.tileQueryResult[3] == 0) {
         this.chosenTask = this.recordBlob[this.recordStarts[16]
            + this.recordBlob[this.recordStarts[17] + this.distSquaredScratchK]
            + this.tileQueryResult[this.distSquaredScratchK != 4 ? 1 : 2]];
         if (this.distSquaredScratchK == 2) {
            if (this.tileQueryResult[1] == 6 && this.entityData[3636 + this.currentVillager] > 0 && this.entityData[3535 + this.currentVillager] <= 13 && !var3
               )
             {
               this.chosenTask = this.recordBlob[this.recordStarts[121] + this.entityData[3535 + this.currentVillager]];
            }

            if (this.tileQueryResult[1] >= 12
               && this.entityData[8505 + this.tileQueryResult[2]]
                  >= this.recordBlob[this.recordStarts[138] + 182 + this.entityData[9237 + this.tileQueryResult[2]]]) {
               this.chosenTask = 69;
            }
         }
      } else if (this.entityData[1414 + this.tileQueryResult[2]] < 12 && this.tileQueryResult[0] != 2) {
         this.chosenTask = 107;
      } else {
         this.chosenTask = (byte)(this.recordBlob[this.recordStarts[19] + this.distSquaredScratchK - 1] + (var3 ? 1 : 0));
      }

      if (this.chosenTask <= 9) {
         this.chosenTask = (byte)(this.chosenTask + this.recordBlob[this.recordStarts[18] + this.entityData[6565 + this.currentVillager]]);
      }

      if (this.game.missionIndex == 3 && this.recordBlob[this.recordStarts[41] + this.chosenTask] != 0) {
         this.chosenTask = 69;
      }
   }

   // $VF: renamed from: a (int, byte) void
   private void setTask(int var1, byte var2) {
      if (this.entityData[2828 + var1] != var2) {
         this.entityData[404 + var1] = 0;
         this.entityData[2828 + var1] = var2;
         this.entityData[1111 + var1] = (byte)(this.entityData[1111 + var1] & 15);
         this.taskDurationB = this.recordBlob[this.recordStarts[38] + var2];
         if (this.taskDurationB < 0) {
            this.taskDurationB = this.entityData[this.fieldOffsets[-this.taskDurationB] + var1];
         }

         if (var2 < 83 || var2 > 85) {
            this.updateTaskState();
         }
      }

      this.entityData[3838 + var1] = this.recordBlob[this.recordStarts[32] + var2] == 0
         ? (byte)var1
         : this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[32] + var2]] + var1];
   }

   // $VF: renamed from: b (int, int) void
   private void findFreeTileNear(int var1, int var2) {
      this.bestOffsetO = this.loopIndexA = 0;

      for (this.bestDistSquaredP = 10000; this.loopIndexA < this.recordBlob[this.recordStarts[20] + var1]; this.loopIndexA++) {
         this.candXOrDistQ = this.entityData[8383 + var2] + this.recordBlob[this.recordStarts[22] + var1 * 3 + this.loopIndexA];
         this.candYR = this.entityData[8444 + var2] + this.recordBlob[this.recordStarts[23] + var1 * 3 + this.loopIndexA];
         if (this.candXOrDistQ < this.game.mapWidth
            && this.candYR < this.game.mapHeight
            && this.candXOrDistQ >= 0
            && this.candYR >= 0
            && (this.occupancyGrid[this.candYR][this.candXOrDistQ] == 0 || this.tileWalkable(this.candXOrDistQ, this.candYR, 0) == 0)) {
            this.candXOrDistQ = (this.entityData[0 + this.currentVillager] - this.candXOrDistQ)
                  * (this.entityData[0 + this.currentVillager] - this.candXOrDistQ)
               + (this.entityData[101 + this.currentVillager] - this.candYR) * (this.entityData[101 + this.currentVillager] - this.candYR);
            if (this.bestDistSquaredP > this.candXOrDistQ) {
               this.bestDistSquaredP = this.candXOrDistQ;
               this.bestOffsetO = this.loopIndexA;
            }
         }
      }

      this.entityData[3131 + this.currentVillager] = (byte)(this.entityData[8383 + var2] + this.recordBlob[this.recordStarts[22] + var1 * 3 + this.bestOffsetO]);
      this.entityData[3232 + this.currentVillager] = (byte)(this.entityData[8444 + var2] + this.recordBlob[this.recordStarts[23] + var1 * 3 + this.bestOffsetO]);
      this.entityData[4040 + this.currentVillager] = this.recordBlob[this.recordStarts[21] + var1 * 3 + this.bestOffsetO];
   }

   // $VF: renamed from: q () void
   private void updateTargetTracking() {
      this.neighborOccupantP = this.entityData[4444 + this.currentVillager];
      if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[26] + this.entityData[2828 + this.currentVillager]]] + this.neighborOccupantP]
         <= 0) {
         this.retargetOrIdle();
      }

      if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 2) > 0) {
         this.neighborDyN = (byte)(this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) / 10);
         if (this.entityData[2828 + this.currentVillager] == 92 || this.entityData[2828 + this.currentVillager] == 106) {
            if (this.neighborDyN > this.entityData[1818 + this.currentVillager]) {
               this.findWorkBuilding(false);
               this.setTask(
                  this.currentVillager,
                  this.recordBlob[this.recordStarts[57]
                     + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                     + this.entityData[4646 + this.currentVillager]]
               );
               this.completeAction(0);
               this.entityData[3030 + this.currentVillager] = -1;
               return;
            }

            this.entityData[3131 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                  + this.entityData[2828 + this.currentVillager]]]
               + this.neighborOccupantP];
            this.entityData[3232 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                     + this.entityData[2828 + this.currentVillager]]
                  + 1]
               + this.neighborOccupantP];
            return;
         }

         if (this.neighborDyN <= this.entityData[4949 + this.currentVillager] + 2) {
            if (this.entityData[3131 + this.currentVillager]
                  != this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]]]
                     + this.neighborOccupantP]
               || this.entityData[3232 + this.currentVillager]
                  != this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]] + 1]
                     + this.neighborOccupantP]) {
               this.entityData[3131 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                     + this.entityData[2828 + this.currentVillager]]]
                  + this.neighborOccupantP];
               this.entityData[3232 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                        + this.entityData[2828 + this.currentVillager]]
                     + 1]
                  + this.neighborOccupantP];
               if (this.neighborDyN > this.taskDurationB) {
                  this.updateMovement();
                  if (this.entityData[2828 + this.currentVillager] >= 94
                     && this.entityData[2828 + this.currentVillager] <= 97
                     && (this.entityData[7979 + this.currentVillager] & 16) > 0) {
                     this.game.setPendingOrderState(this.currentVillager, (byte)0);
                     this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 239);
                  }
               }
            }

            return;
         }

         if (this.entityData[808 + this.currentVillager] == 0) {
            if (this.entityData[2828 + this.currentVillager] <= 9 && this.entityData[6161 + this.currentVillager] == 0) {
               this.goIdle();
               this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
               this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
               return;
            }

            this.entityData[3131 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                  + this.entityData[2828 + this.currentVillager]]]
               + this.neighborOccupantP];
            this.entityData[3232 + this.currentVillager] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28]
                     + this.entityData[2828 + this.currentVillager]]
                  + 1]
               + this.neighborOccupantP];
         }
      }
   }

   // $VF: renamed from: r () void
   private void retargetOrIdle() {
      if (this.entityData[2828 + this.currentVillager] == 2 || this.entityData[2828 + this.currentVillager] == 7) {
         if ((this.entityData[7979 + this.currentVillager] & 128) == 0) {
            this.moveVillagerStep(1);
         } else {
            this.goIdle();
         }
      }

      if (this.entityData[2828 + this.currentVillager] == 93
         || this.entityData[2828 + this.currentVillager] == 92
         || this.entityData[2828 + this.currentVillager] == 106) {
         this.findWorkBuilding(false);
         this.setTask(
            this.currentVillager,
            this.recordBlob[this.recordStarts[57]
               + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
               + this.entityData[4646 + this.currentVillager]]
         );
         this.completeAction(0);
         this.entityData[3030 + this.currentVillager] = -1;
      }

      if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 1) > 0) {
         if (this.entityData[1414 + this.currentVillager] < 12) {
            this.goIdle();
         } else {
            if (this.entityData[6565 + this.currentVillager] == 9
               && (this.entityData[2828 + this.currentVillager] == 0 || this.entityData[2828 + this.currentVillager] == 3)) {
               this.entityData[2828 + this.currentVillager] = (byte)(this.entityData[2828 + this.currentVillager] ^ 1);
            }

            this.searchOkA = this.searchFlagB = true;
            if (this.entityData[2828 + this.currentVillager] > 9 || !this.findNewTargetTile()) {
               this.goIdle();
               if ((this.entityData[2929 + this.currentVillager] & 255) > 7) {
                  this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
                  this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
               }
            }
         }
      }

      if (this.entityData[2828 + this.currentVillager] >= 94 && this.entityData[2828 + this.currentVillager] <= 96) {
         this.leaveSanctuary();
      }
   }

   // $VF: renamed from: b (boolean) void
   private void findWorkBuilding(boolean var1) {
      for (this.slotLoopS = this.tribeIndex * 30 + 1; this.slotLoopS <= (this.tribeIndex + 1) * 30; this.slotLoopS++) {
         if ((this.entityData[8749 + this.slotLoopS] == 11 || var1) && this.entityData[8932 + this.slotLoopS] >= 1) {
            for (this.workerCursorT = this.entityData[8993 + this.slotLoopS];
               this.workerCursorT > 0;
               this.workerCursorT = this.entityData[4545 + this.workerCursorT]
            ) {
               if (this.workerCursorT == this.currentVillager) {
                  this.entityData[4444 + this.currentVillager] = (byte)this.slotLoopS;
                  this.entityData[4747 + this.currentVillager] = (byte)(-this.slotLoopS);
                  this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
                  this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
                  return;
               }
            }
         }
      }
   }

   // $VF: renamed from: a (int, int, int) int
   private int distanceTo(int var1, int var2, int var3) {
      if (var3 == 0 && this.entityData[4444 + this.currentVillager] < -1) {
         return 0;
      }

      byte var4;
      if ((var4 = this.entityData[this.fieldOffsets[var3 == 0 ? 44 : 110] + this.currentVillager]) >= 1
         && this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]] != 43
         && var3 == 0) {
         this.targetXQ = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]]] + var4];
         this.targetYR = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]] + 1] + var4];
         if (this.recordBlob[this.recordStarts[28] + this.entityData[2828 + this.currentVillager]] == 83) {
            this.targetXQ = (byte)clamp(var1, this.targetXQ, this.targetXQ + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + var4]] - 1);
            this.targetYR = (byte)clamp(var2, this.targetYR, this.targetYR + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + var4]] - 1);
            if (this.entityData[2828 + this.currentVillager] <= 9) {
               this.entityData[3131 + this.currentVillager] = this.targetXQ;
               this.entityData[3232 + this.currentVillager] = this.targetYR;
            }
         }
      } else {
         this.targetXQ = this.entityData[this.fieldOffsets[var3 == 0 ? 31 : 112] + this.currentVillager];
         this.targetYR = this.entityData[this.fieldOffsets[var3 == 0 ? 32 : 113] + this.currentVillager];
      }

      byte var5 = (byte)(this.targetXQ - var1);
      byte var6 = (byte)(this.targetYR - var2);
      return var5 >= -12 && var6 >= -12 && var5 <= 12 && var6 <= 12 ? this.recordBlob[this.recordStarts[53] + (12 + var6) * 25 + 12 + var5] : 200;
   }

   // $VF: renamed from: b (int, int, int) int
   private static int clamp(int var0, int var1, int var2) {
      if (var0 < var1) {
         return var1;
      } else {
         return var0 < var2 ? var0 : var2;
      }
   }

   // $VF: renamed from: b () void
   final void flagDistressed() {
      for (this.currentVillager = 1; this.currentVillager <= 50; this.currentVillager++) {
         if ((
               this.game.placeConfirmFlagN
                  || this.recordBlob[this.recordStarts[100] + this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]]]
                           + this.recordBlob[this.recordStarts[100] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]]
                        > 1
                     && this.entityData[6565 + this.currentVillager] != 9
                     && (
                        this.entityData[1717 + this.currentVillager] > 4
                           || this.entityData[1616 + this.currentVillager] > 4
                           || this.entityData[7575 + this.currentVillager] > 4
                     )
            )
            && (!this.game.placeConfirmFlagL || (this.entityData[7979 + this.currentVillager] & 64) == 0)) {
            this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 96);
         }
      }
   }

   // $VF: renamed from: c () void
   final void clearDistressFlags() {
      for (this.currentVillager = 1; this.currentVillager <= 50; this.currentVillager++) {
         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 159);
         if (this.entityData[1919 + this.currentVillager] > 0
            && this.entityData[2828 + this.currentVillager] == 69
            && this.entityData[6161 + this.currentVillager] == 0) {
            this.stopAtTile(this.currentVillager, false);
         }
      }
   }

   // $VF: renamed from: d () void
   final void rebuildFogOfWar() {
      for (int var1 = 0; var1 < 3; var1++) {
         for (int var2 = 0; var2 < 96; var2++) {
            this.game.effectBitfield[1][var1][var2] = 0;
         }
      }

      for (int var4 = 1; var4 <= 50; var4++) {
         if (this.entityData[1919 + var4] != 0) {
            this.revealFogCircle(this.entityData[0 + var4], this.entityData[101 + var4], this.entityData[4949 + var4]);
         }
      }

      for (int var5 = 51; var5 <= 100; var5++) {
         if (this.entityData[1919 + var5] != 0
            && this.entityData[2929 + var5] >= 23
            && this.entityData[2929 + var5] <= 36
            && this.entityData[2828 + var5] <= 9
            && this.entityData[2828 + var5] != 2
            && this.entityData[2828 + var5] != 7) {
            this.revealFogCircle(this.entityData[0 + var5], this.entityData[101 + var5], 0);
         }
      }

      for (int var6 = 1; var6 <= 30; var6++) {
         if (this.entityData[8505 + var6] != 0) {
            if (this.entityData[8749 + var6] >= 12) {
               this.fillFogRect(
                  this.entityData[8383 + var6],
                  this.entityData[8444 + var6],
                  this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + var6]],
                  this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + var6]]
               );
            } else {
               this.revealFogCircle(
                  this.entityData[8383 + var6] + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + var6]] / 2,
                  this.entityData[8444 + var6] + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + var6]] / 2,
                  this.entityData[8688 + var6] & 15
               );
            }
         }
      }

      for (int var3 = 0; var3 < 3; var3++) {
         for (int var7 = 0; var7 < 96; var7++) {
            this.game.effectBitfield[0][var3][var7] = this.game.effectBitfield[0][var3][var7] | this.game.effectBitfield[1][var3][var7];
         }
      }
   }

   // $VF: renamed from: e () void
   final void tickWorld() {
      try {
         this.tickPreamble();

         for (this.tribeIndex = 0; this.tribeIndex < 2; this.tribeIndex++) {
            if (this.tribeIndex == 1) {
               this.tickEnemyAI();
            }

            this.updateRitualQueue(0);
            if (this.tribeStats[this.tribeIndex][18] >= 0) {
               this.tribeStats[this.tribeIndex][18]++;
            }

            if (this.tribeStats[this.tribeIndex][18]
               >= (this.tribeIndex == 0 ? 750 : this.recordBlob[this.recordStarts[273 + this.game.selectedDifficulty] + this.game.missionIndex] * 50)) {
               this.tribeStats[this.tribeIndex][18] = -1;
            }

            for (this.currentVillager = this.tribeIndex * 50 + 1; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
               if (this.entityData[1919 + this.currentVillager] != 0 && !this.preTickVillager()) {
                  if (this.entityData[4545 + this.currentVillager] > -111 || this.entityData[4545 + this.currentVillager] < -122) {
                     this.updateMoveState();
                     if (this.entityData[7575 + this.currentVillager] > 3 && this.checkSickOrRest()) {
                        continue;
                     }

                     if (this.game.tickCounter % 15 == (this.currentVillager & 7) && this.tribeIndex == 0 && this.entityData[1414 + this.currentVillager] >= 12
                        )
                      {
                        this.pickDesire();
                     }

                     if (this.tryActOnNeed()) {
                        continue;
                     }
                  }

                  this.updateTaskProgress();
                  this.searchFlagB = true;
                  if (this.entityData[5050 + this.currentVillager] == 0
                     && this.entityData[1414 + this.currentVillager] >= 12
                     && this.entityData[2828 + this.currentVillager] != 50
                     && this.entityData[2828 + this.currentVillager] != 105) {
                     this.searchOkA = (
                           this.entityData[6161 + this.currentVillager] == 0
                              || (this.slotScanI & this.tribeIndex + 1) != 0
                              || this.entityData[6161 + this.currentVillager] == 1
                                 && this.entityData[808 + this.currentVillager] <= this.entityData[707 + this.currentVillager]
                                 && this.distOrTargetXZ / 10 > this.taskDurationB
                                 && (this.entityData[2929 + this.currentVillager] & 255) > 7
                        )
                        && (
                           this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
                              || this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] == 16
                                 && this.recordBlob[this.recordStarts[18] + this.entityData[6565 + this.currentVillager]] > 0
                        )
                        && (this.entityData[7979 + this.currentVillager] & 2) == 0
                        && (
                           this.entityData[2828 + this.currentVillager] > 9
                              || (this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 4) == 0
                        );
                     this.searchFlagB = false;
                     this.findNewTargetTile();
                  }

                  this.updateAction();
                  if (this.entityData[5959 + this.currentVillager] > 0) {
                     this.stepProjectile();
                  }
               }
            }

            this.tickHutBirths();
         }

         this.updateProjectileGrid();
         if (this.rankShiftK < 50) {
            this.rankShiftK++;
         }

         for (this.currentVillager = 1; this.currentVillager <= 4; this.currentVillager++) {
            if (this.tribeStats[0][this.currentVillager] > 999) {
               this.tribeStats[0][this.currentVillager] = 999;
            }
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: s () void
   private void tickPreamble() {
      this.game.textLoader.updateFloatingTexts();
      if ((this.game.tickCounter & 3) == 0) {
         this.rebuildFogOfWar();
      }

      if (this.game.placeConfirmFlagL && (this.game.tickCounter & 63) == 2) {
         this.flagDistressed();
      }

      if (this.game.tickCounter == this.recordBlob[this.recordStarts[277] + this.game.missionIndex] * (1000 / (1 + this.game.selectedDifficulty * 3))) {
         this.tribeStats[1][8] = 5000;
      }

      if (this.game.tickCounter == this.recordBlob[this.recordStarts[278] + this.game.missionIndex] * (1000 / (1 + this.game.selectedDifficulty * 3))) {
         this.tribeStats[1][9] = 5000;
         this.tribeStats[1][10] = 5000;
      }

      if (this.scriptEventIndex == 2) {
         this.timerOrCountF = 0;
      }
   }

   // $VF: renamed from: a () boolean
   private boolean preTickVillager() {
      if (this.entityData[1919 + this.currentVillager] < 0) {
         this.entityData[3030 + this.currentVillager]++;
         if (this.entityData[3030 + this.currentVillager] >= this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF]) {
            this.killVillager(this.currentVillager);
         }

         if (this.entityData[5959 + this.currentVillager] > 0) {
            this.stepProjectile();
         }

         return true;
      } else {
         if (this.scriptEventIndex == 2
            && this.tribeIndex == 0
            && (this.entityData[1717 + this.currentVillager] > 4 || this.entityData[1616 + this.currentVillager] > 4)) {
            this.timerOrCountF++;
         }

         this.taskDurationB = this.recordBlob[this.recordStarts[38] + this.entityData[2828 + this.currentVillager]];
         if (this.taskDurationB < 0) {
            this.taskDurationB = this.entityData[this.fieldOffsets[-this.taskDurationB] + this.currentVillager];
         }

         if ((this.entityData[8282 + this.currentVillager] & 128) != 0
            && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            this.resolveTileEvent(19, this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], (byte)this.currentVillager);
            return true;
         }

         if ((this.game.tickCounter & (this.entityData[1414 + this.currentVillager] < 12 ? 1 : (this.tribeIndex == 0 ? 3 : 15))) == 0) {
            this.entityData[6666 + this.currentVillager]++;
         }

         if ((this.game.tickCounter & 3) == (this.currentVillager & 3) && !this.ageVillager()) {
            return true;
         }

         if ((this.game.tickCounter & 31) == (this.currentVillager & 15)) {
            this.updateMood();
         }

         if (this.entityData[3434 + this.currentVillager] < 1 && this.entityData[1414 + this.currentVillager] >= 12 && (this.game.tickCounter & 31) == 0) {
            this.assignHomeHut();
         }

         if ((this.entityData[7979 + this.currentVillager] & 4) != 0
            && (this.game.tickCounter & 7) == (this.currentVillager & 7)
            && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            this.spreadDisease();
         }

         if ((this.game.tickCounter & 1) == 1 && (this.entityData[7979 + this.currentVillager] & 4) == 0) {
            this.updateDiseaseMeter();
         }

         if (this.entityData[2828 + this.currentVillager] <= 9
            && this.entityData[2828 + this.currentVillager] != 2
            && this.entityData[2828 + this.currentVillager] != 7) {
            this.rankShiftK = 0;
         }

         this.entityData[5050 + this.currentVillager]++;
         if (this.entityData[202 + this.currentVillager] == 0 && this.entityData[303 + this.currentVillager] == 0) {
            this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 240);
         }

         if (this.entityData[3333 + this.currentVillager] >= 11
            && this.entityData[2929 + this.currentVillager] != 39
            && this.entityData[2929 + this.currentVillager] != 104
            && this.entityData[2929 + this.currentVillager] != 105) {
            this.entityData[3333 + this.currentVillager] = -1;
         }

         return false;
      }
   }

   // $VF: renamed from: t () void
   private void updateMoveState() {
      if (this.entityData[6161 + this.currentVillager] >= 2
         && this.entityData[202 + this.currentVillager] == 0
         && this.entityData[303 + this.currentVillager] == 0) {
         if (this.isWalkingToTargetC = this.entityData[6161 + this.currentVillager] == 2) {
            this.inspectTileTarget(this.entityData[3131 + this.currentVillager], this.entityData[3232 + this.currentVillager], true);
            if (this.tileQueryCode >= 0) {
               this.isWalkingToTargetC = false;
            }

            if (this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]
                  == this.recordBlob[this.recordStarts[41] + this.chosenTask]
               || this.recordBlob[this.recordStarts[41] + this.chosenTask] == 0 && (this.game.placeConfirmFlagN || this.rankShiftK < 50)) {
               this.isWalkingToTargetC = false;
            }
         }

         if (this.tribeIndex != 1
            && this.entityData[505 + this.currentVillager] < 10
            && this.entityData[6161 + this.currentVillager] == 2
            && this.recordBlob[this.recordStarts[41] + this.chosenTask] != 0) {
            this.entityData[6161 + this.currentVillager] = 0;
            this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
            this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
            this.entityData[4444 + this.currentVillager] = this.entityData[4747 + this.currentVillager] = 0;
            this.goIdle();
            this.showMessage((byte)12, this.currentVillager, 0);
         } else if (!this.checkCombatStance((byte)-1, this.isWalkingToTargetC)) {
            if (this.entityData[6161 + this.currentVillager] == 4) {
               this.entityData[4444 + this.currentVillager] = (byte)(
                  this.entityData[4747 + this.currentVillager] - (this.entityData[4747 + this.currentVillager] <= 100 ? 0 : 101)
               );
               this.entityData[4747 + this.currentVillager] = (byte)(this.entityData[4747 + this.currentVillager] <= 100 ? 0 : 99);
               this.entityData[3131 + this.currentVillager] = this.entityData[this.fieldOffsets[this.entityData[4747 + this.currentVillager]]
                  + this.entityData[4444 + this.currentVillager]];
               this.entityData[3232 + this.currentVillager] = this.entityData[this.fieldOffsets[this.entityData[4747 + this.currentVillager] + 1]
                  + this.entityData[4444 + this.currentVillager]];
               if (this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) / 10
                  <= this.entityData[4949 + this.currentVillager]) {
                  this.moveVillagerStep(1);
                  this.entityData[6161 + this.currentVillager] = 0;
               } else {
                  this.setTask(this.currentVillager, (byte)87);
               }
            }

            this.entityData[808 + this.currentVillager] = 0;
            if (this.entityData[6161 + this.currentVillager] > 0) {
               this.moveVillagerStep(this.entityData[6161 + this.currentVillager] == 2 ? 0 : 2);
               return;
            }
         }
      }
   }

   // $VF: renamed from: b () boolean
   private boolean tryActOnNeed() {
      if (((this.game.tickCounter & 31) == (this.currentVillager & 31) || this.game.tickCounter == 0)
         && this.tribeIndex == 0
         && this.entityData[1414 + this.currentVillager] >= 12) {
         this.rankNeeds();
      }

      if (this.entityData[202 + this.currentVillager] == 0
         && this.entityData[303 + this.currentVillager] == 0
         && this.entityData[6161 + this.currentVillager] < 2) {
         if (this.entityData[2828 + this.currentVillager] == 109) {
            this.updateMating();
         }

         if (this.entityData[5050 + this.currentVillager] >= this.entityData[1010 + this.currentVillager] - this.entityData[7777 + this.currentVillager]
            && ((this.entityData[2929 + this.currentVillager] & 255) <= 7 || this.entityData[3030 + this.currentVillager] == 0)) {
            if (this.entityData[1414 + this.currentVillager] >= 12 && this.checkCallToArms()) {
               this.entityData[3636 + this.currentVillager] = 0;
               return true;
            }

            if ((
                     this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]] == 13
                           && this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]] == 13
                        || this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] >= 13
                           && this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] <= 15
                  )
                  && (
                     this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] != 1
                        || this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager
                  )
                  && this.entityData[2929 + this.currentVillager] != -109
                  && this.entityData[3131 + this.currentVillager] == this.entityData[0 + this.currentVillager]
                  && this.entityData[3232 + this.currentVillager] == this.entityData[101 + this.currentVillager]
               || this.entityData[4444 + this.currentVillager] == -1) {
               if (this.entityData[4444 + this.currentVillager] == -1
                  && this.entityData[2828 + this.currentVillager] >= 10
                  && this.entityData[2828 + this.currentVillager] <= 16
                  && this.entityData[3636 + this.currentVillager] > 0) {
                  this.findGatherTarget();
               } else {
                  this.tryFindNeedTarget();
               }
            }

            if ((
                  this.tribeIndex == 0
                        && !this.game.placeConfirmFlagN
                        && (!this.game.placeConfirmFlagL || (this.entityData[7979 + this.currentVillager] & 64) == 0)
                     || this.tribeIndex == 1 && this.waveTargetQByte < 0
               )
               && this.entityData[1414 + this.currentVillager] >= 12
               && (
                  this.entityData[2828 + this.currentVillager] == 69
                     || this.entityData[2828 + this.currentVillager] == 84 && this.entityData[3939 + this.currentVillager] == 69
               )
               && this.entityData[7777 + this.currentVillager] == 0
               && this.entityData[6161 + this.currentVillager] == 0
               && (
                  this.entityData[3131 + this.currentVillager] != this.entityData[6262 + this.currentVillager]
                     || this.entityData[3232 + this.currentVillager] != this.entityData[6363 + this.currentVillager]
               )) {
               this.inspectTileTarget(this.entityData[6262 + this.currentVillager], this.entityData[6363 + this.currentVillager], true);
               this.goIdle();
               if (this.recordBlob[this.recordStarts[41] + this.chosenTask]
                     != this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]
                  && (this.chosenTask != 16 || this.entityData[6464 + this.currentVillager] != 12)) {
                  if (this.entityData[6464 + this.currentVillager] != 12 && this.entityData[6464 + this.currentVillager] != 16) {
                     this.entityData[4747 + this.currentVillager] = 0;
                  } else {
                     this.setTask(this.currentVillager, (byte)12);
                     if (!this.findNearestResourceNode(false)) {
                        this.setTask(this.currentVillager, (byte)60);
                        this.entityData[6464 + this.currentVillager] = 12;
                        this.entityData[3131 + this.currentVillager] = this.entityData[6262 + this.currentVillager];
                        this.entityData[3232 + this.currentVillager] = this.entityData[6363 + this.currentVillager];
                     }
                  }
               } else {
                  this.entityData[3131 + this.currentVillager] = this.entityData[6262 + this.currentVillager];
                  this.entityData[3232 + this.currentVillager] = this.entityData[6363 + this.currentVillager];
                  if (this.entityData[505 + this.currentVillager] >= 30) {
                     this.moveVillagerStep(1);
                  }
               }
            }

            this.entityData[5050 + this.currentVillager] = 0;
         }

         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
            || this.entityData[2828 + this.currentVillager] == 93
            || this.entityData[2828 + this.currentVillager] == 92
            || this.entityData[2828 + this.currentVillager] == 106) {
            if (this.entityData[4444 + this.currentVillager] >= 0) {
               this.updateTargetTracking();
            } else if (this.entityData[4444 + this.currentVillager] >= -1) {
               if ((this.game.tickCounter & 63) == (this.currentVillager & 63)) {
                  this.findGatherTarget();
               }
            } else {
               this.retargetAfterIdle();
            }
         }

         this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0);
         if (this.entityData[5050 + this.currentVillager] == 0
            && this.entityData[808 + this.currentVillager] <= this.entityData[707 + this.currentVillager]
            && this.distOrTargetXZ / 10 > this.taskDurationB
            && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            this.updateMovement();
         }
      }

      return false;
   }

   // $VF: renamed from: u () void
   private void updateAction() {
      if (this.entityData[202 + this.currentVillager] != 0
         || this.entityData[303 + this.currentVillager] != 0
         || this.entityData[1313 + this.currentVillager] == -1
         || this.distOrTargetXZ / 10 > this.taskDurationB && this.entityData[808 + this.currentVillager] > 0) {
         this.entityData[2929 + this.currentVillager] = this.moveStance(this.currentVillager);
         this.stepResultE = 0;
         if (this.stepAlongPath()) {
            return;
         }

         if ((this.entityData[2929 + this.currentVillager] & 255) <= 7 && this.stepResultE < 2) {
            this.walkStep();
            if (this.entityData[202 + this.currentVillager] == 0
               && this.entityData[303 + this.currentVillager] == 0
               && this.entityData[4545 + this.currentVillager] <= -111
               && this.entityData[4545 + this.currentVillager] >= -122) {
               this.entityData[2929 + this.currentVillager] = (byte)(-5 - (this.tribeIndex ^ 1) * 3 * 2 - this.entityData[4545 + this.currentVillager]);
               this.entityData[3030 + this.currentVillager] = 0;
               this.entityData[808 + this.currentVillager] = 0;
               this.entityData[1313 + this.currentVillager] = 1;
               this.game.markTileRect(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 1, 1);
               if (this.tribeIndex == 0) {
                  this.scratchByteR--;
                  return;
               }
            }
         }
      } else if ((this.entityData[2929 + this.currentVillager] & 255) <= 7) {
         this.entityData[3030 + this.currentVillager] = 0;
         this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 15);
         this.setIdleRole();
         this.entityData[808 + this.currentVillager] = 0;
      }
   }

   // $VF: renamed from: c () boolean
   private boolean stepAlongPath() {
      if (this.entityData[202 + this.currentVillager] == 0
         && this.entityData[303 + this.currentVillager] == 0
         && this.entityData[1313 + this.currentVillager] != -1) {
         try {
            this.entityData[606 + this.currentVillager] = this.recordBlob[this.recordStarts[4]
               + (this.villagerPaths[0][this.currentVillager][this.entityData[707 + this.currentVillager]] - this.entityData[0 + this.currentVillager] + 1) * 3
               + this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]]
               - this.entityData[101 + this.currentVillager]
               + 1];
         } catch (Exception var2) {
            this.retargetAfterIdle();
            this.entityData[808 + this.currentVillager] = 0;
            return true;
         }

         if (this.occupancyGrid[this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]]][this.villagerPaths[0][this.currentVillager][this.entityData[707
                  + this.currentVillager]]]
               != 0
            && this.tileWalkable(
                  this.villagerPaths[0][this.currentVillager][this.entityData[707 + this.currentVillager]],
                  this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]],
                  0
               )
               != 0) {
            if (!this.searchFlagB && this.rollByteY > 1) {
               if (this.entityData[2828 + this.currentVillager] == 0 || this.entityData[2828 + this.currentVillager] == 3) {
                  this.entityData[2828 + this.currentVillager]++;
               }

               if (this.entityData[6161 + this.currentVillager] == 1) {
                  this.entityData[6161 + this.currentVillager] = 0;
               }
            }

            this.updateMovement();
            return true;
         }

         if (this.occupancyGrid[this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]]][this.villagerPaths[0][this.currentVillager][this.entityData[707
                  + this.currentVillager]]]
               > this.tribeIndex * 50
            && this.occupancyGrid[this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]]][this.villagerPaths[0][this.currentVillager][this.entityData[707
                  + this.currentVillager]]]
               <= (this.tribeIndex + 1) * 50) {
            this.swapVillager(
               this.occupancyGrid[this.villagerPaths[1][this.currentVillager][this.entityData[707 + this.currentVillager]]][this.villagerPaths[0][this.currentVillager][this.entityData[707
                  + this.currentVillager]]]
            );
         } else {
            this.entityData[4848 + this.currentVillager] = 0;
         }

         if (this.stepResultE == 0) {
            this.scratchIntC = this.entityData[0 + this.currentVillager]
               + this.recordBlob[this.recordStarts[1] + 0 + this.entityData[606 + this.currentVillager]];
            this.scratchIntD = this.entityData[101 + this.currentVillager]
               + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]];
            if (this.occupancyGrid[this.scratchIntD][this.scratchIntC] != 0) {
               this.entityData[4545 + this.currentVillager] = (byte)(-this.occupancyGrid[this.scratchIntD][this.scratchIntC]);
            }

            this.occupancyGrid[this.scratchIntD][this.scratchIntC] = -128;
         }

         if ((this.entityData[2929 + this.currentVillager] & 255) <= 7 && this.stepResultE < 2) {
            this.entityData[707 + this.currentVillager]++;
         }
      }

      return false;
   }

   // $VF: renamed from: v () void
   private void updateTaskProgress() {
      try {
         this.actionDoneD = false;
         if (this.entityData[5959 + this.currentVillager] > 0
            && this.entityData[3030 + this.currentVillager] == 0
            && this.entityData[6565 + this.currentVillager] == 1
            && (
               this.entityData[2828 + this.currentVillager] >= 5 && this.entityData[2828 + this.currentVillager] <= 9
                  || this.entityData[2828 + this.currentVillager] == 93
                  || this.entityData[2828 + this.currentVillager] == 92
                  || this.entityData[2828 + this.currentVillager] == 106
            )
            && (this.entityData[2929 + this.currentVillager] & 255) > 7
            && this.entityData[5858 + this.currentVillager] + 2 < this.entityData[5959 + this.currentVillager]) {
            this.entityData[2929 + this.currentVillager] = 34;
         } else {
            this.tickActionTimer();
            if (this.entityData[3030 + this.currentVillager] >= this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF]) {
               this.entityData[3030 + this.currentVillager] = 0;
               if (this.entityData[4646 + this.currentVillager] < -1) {
                  this.entityData[4646 + this.currentVillager] = -1;
               }

               if ((this.entityData[2929 + this.currentVillager] & 255) > 7) {
                  if (this.handleSpecialRoles()) {
                     return;
                  }

                  if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 2
                     && this.entityData[1313 + this.currentVillager] == -3) {
                     if (this.entityData[2828 + this.currentVillager] == 59
                        && this.entityData[4646 + this.currentVillager] == 0
                        && (
                           this.recordBlob[this.recordStarts[138] + 156 + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] >= 0
                              || this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
                        )) {
                        this.game.clearEntityJob(this.entityData[4444 + this.currentVillager], false);
                     }

                     this.leaveBuildingWork();
                     return;
                  }

                  if (this.tryEnterBuilding()) {
                     return;
                  }

                  this.actionDoneD = true;
               }
            }

            this.checkActionCompletion();
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: w () void
   private void tickActionTimer() {
      this.entityData[3030 + this.currentVillager]++;
      if (this.recordBlob[this.recordStarts[171] + (this.entityData[2929 + this.currentVillager] & 255)] != -2) {
         this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] + 16);
      }

      if (this.entityData[2828 + this.currentVillager] <= 4
         && this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[26] + this.entityData[2828 + this.currentVillager]]]
               + this.entityData[4444 + this.currentVillager]]
            > 0
         && this.game.pd0ByteTables[5][this.entityData[2929 + this.currentVillager] & 0xFF] == this.entityData[3030 + this.currentVillager]) {
         this.gatherStrike(
            this.entityData[0 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + this.entityData[606 + this.currentVillager]],
            this.entityData[101 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]],
            this.game.pd0ByteTables[6][this.entityData[2929 + this.currentVillager] & 0xFF]
         );
      }

      if (this.entityData[3030 + this.currentVillager] == this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF] - 1
         && this.entityData[2929 + this.currentVillager] >= 35
         && this.entityData[2929 + this.currentVillager] <= 36) {
         if (this.entityData[2828 + this.currentVillager] >= 5 && this.entityData[2828 + this.currentVillager] <= 9) {
            this.launchProjectile(
               this.entityData[0 + this.currentVillager],
               this.entityData[101 + this.currentVillager],
               0,
               this.recordBlob[this.recordStarts[123] + this.entityData[2828 + this.currentVillager] - 5]
            );
         } else if (this.entityData[2828 + this.currentVillager] == 93
            || this.entityData[2828 + this.currentVillager] == 92
            || this.entityData[2828 + this.currentVillager] == 106) {
            this.launchProjectile(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 24, 37);
         }
      }

      if (this.entityData[2929 + this.currentVillager] == 22
         && this.entityData[3030 + this.currentVillager] == 10
         && this.entityData[2828 + this.currentVillager] <= 26) {
         if (this.entityData[2828 + this.currentVillager] < 26) {
            this.tribeStats[this.tribeIndex][23] = (short)(this.tribeStats[this.tribeIndex][23] + this.entityData[3636 + this.currentVillager]);
         }

         this.applyTaskYield();
      }

      if (this.entityData[2929 + this.currentVillager] == -108 && this.entityData[3030 + this.currentVillager] == 6) {
         this.entityData[6464 + this.currentVillager] = 69;
      }

      if (this.entityData[2929 + this.currentVillager] == -109 && this.entityData[3030 + this.currentVillager] == 1) {
         this.entityData[6464 + this.currentVillager] = this.recordBlob[this.recordStarts[29] + this.entityData[2828 + this.currentVillager]];
      }
   }

   // $VF: renamed from: d () boolean
   private boolean handleSpecialRoles() {
      if (this.entityData[2929 + this.currentVillager] == -107 || this.entityData[2929 + this.currentVillager] == -106) {
         this.completeAction(this.distOrTargetXZ);
         return true;
      }

      if (this.entityData[2929 + this.currentVillager] == -95) {
         this.entityData[3131 + this.currentVillager] = this.entityData[8384];
         this.entityData[3232 + this.currentVillager] = this.entityData[8445];
         this.moveVillagerStep(3);
         this.updateMovement();
         return true;
      }

      if (this.distOrTargetXZ / 10 <= this.taskDurationB
         && this.entityData[2828 + this.currentVillager] != 85
         && this.entityData[2828 + this.currentVillager] != 80) {
         if (this.entityData[6161 + this.currentVillager] == 1) {
            this.entityData[6161 + this.currentVillager] = 0;
         }

         this.entityData[7777 + this.currentVillager] = 0;
      }

      if (this.entityData[2929 + this.currentVillager] >= 106 && this.entityData[2929 + this.currentVillager] <= 108) {
         this.entityData[1919 + this.currentVillager] = (byte)(
            this.entityData[1919 + this.currentVillager] - this.recordBlob[this.recordStarts[106] + this.entityData[2929 + this.currentVillager] - 106]
         );
         this.game.textLoader.pendingDeltas[this.currentVillager] = (byte)(
            this.game.textLoader.pendingDeltas[this.currentVillager]
               - this.recordBlob[this.recordStarts[106] + this.entityData[2929 + this.currentVillager] - 106]
         );
         this.setIdleRole();
         this.entityData[1313 + this.currentVillager] = 0;
         this.entityData[4545 + this.currentVillager] = 0;
         if (this.entityData[1919 + this.currentVillager] <= 0) {
            this.resolveTileEvent(19, this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], (byte)this.currentVillager);
         }

         return true;
      } else {
         return false;
      }
   }

   // $VF: renamed from: x () void
   private void leaveBuildingWork() {
      this.entityData[8932 + this.entityData[4444 + this.currentVillager]]--;
      if (this.entityData[8993 + this.entityData[4444 + this.currentVillager]] == this.currentVillager) {
         this.entityData[8993 + this.entityData[4444 + this.currentVillager]] = this.entityData[4545 + this.currentVillager];
      } else {
         this.scratchIntX = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];

         while (this.entityData[4545 + this.scratchIntX] != this.currentVillager && this.scratchIntX != 0) {
            this.scratchIntX = this.entityData[4545 + this.scratchIntX];
         }

         this.entityData[4545 + this.scratchIntX] = this.entityData[4545 + this.currentVillager];
      }

      if ((this.entityData[8871 + this.entityData[4444 + this.currentVillager]] & 2) != 0 && (this.entityData[7979 + this.currentVillager] & 4) != 0) {
         this.entityData[8871 + this.entityData[4444 + this.currentVillager]] = (byte)(
            this.entityData[8871 + this.entityData[4444 + this.currentVillager]] & 253
         );

         for (this.scratchIntX = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];
            this.entityData[4545 + this.scratchIntX] != 0;
            this.scratchIntX = this.entityData[4545 + this.scratchIntX]
         ) {
            if ((this.entityData[7979 + this.scratchIntX] & 4) != 0
               && (
                  this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
                     || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 8
               )) {
               this.entityData[8871 + this.entityData[4444 + this.currentVillager]] = (byte)(
                  this.entityData[8871 + this.entityData[4444 + this.currentVillager]] | 2
               );
               break;
            }
         }
      }

      this.entityData[4646 + this.currentVillager] = -1;
      this.entityData[4545 + this.currentVillager] = 0;
      this.updateTowerStats(this.entityData[4444 + this.currentVillager]);
      this.spiralSearchTile(
         1,
         127,
         this.entityData[8383 + this.entityData[4444 + this.currentVillager]]
            + this.recordBlob[this.recordStarts[24] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]],
         this.entityData[8444 + this.entityData[4444 + this.currentVillager]]
            + this.recordBlob[this.recordStarts[25] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
      );
      this.entityData[0 + this.currentVillager] = this.entityData[3131 + this.currentVillager];
      this.entityData[101 + this.currentVillager] = this.entityData[3232 + this.currentVillager];
      this.entityData[202 + this.currentVillager] = 0;
      this.entityData[303 + this.currentVillager] = 0;
      this.entityData[606 + this.currentVillager] = this.recordBlob[this.recordStarts[111]
         + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]];
      this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] = (byte)this.currentVillager;
      this.entityData[1313 + this.currentVillager] = 0;
      this.entityData[1818 + this.currentVillager] = (byte)(this.entityData[1818 + this.currentVillager] - 2);
      if (this.entityData[2828 + this.currentVillager] == 59) {
         this.entityData[6464 + this.currentVillager] = 69;
         this.entityData[6262 + this.currentVillager] = this.entityData[0 + this.currentVillager];
         this.entityData[6363 + this.currentVillager] = this.entityData[101 + this.currentVillager];
      }

      this.setIdleRole();
      this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 240);
      this.entityData[5050 + this.currentVillager] = this.entityData[1010 + this.currentVillager];
      this.game.h_int_void(this.currentVillager);
      if (this.entityData[2828 + this.currentVillager] >= 109) {
         this.entityData[6161 + this.currentVillager] = 0;
      }

      if ((this.game.newGameFlag & 3) == 1 && this.entityData[7575 + this.currentVillager] > 3 && this.tribeIndex == 0 && this.game.missionIndex < 7) {
         this.setGroupMembers(this.currentVillager, 0, 1);
         this.game.advanceStoryStep(61);
         this.game.newGameFlag = (byte)(this.game.newGameFlag | 2);
      } else if (this.recordBlob[this.recordStarts[160] + this.entityData[2828 + this.currentVillager]] == 69
         && this.entityData[2828 + this.currentVillager] < 109) {
         if (!this.tryFindNeedTarget()) {
            this.spiralSearchTile(
               1,
               21,
               clamp(this.entityData[0 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapWidth - 1),
               clamp(this.entityData[101 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapHeight - 1)
            );
            this.goIdle();
         }

         this.updateMovement();
         this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0);
      } else {
         if (this.entityData[6161 + this.currentVillager] < 2) {
            this.retargetAfterIdle();
         }
      }
   }

   // $VF: renamed from: e () boolean
   private boolean tryEnterBuilding() {
      if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 1
         && this.entityData[4040 + this.currentVillager] > 0
         && (this.entityData[2929 + this.currentVillager] & 255) >= 8
         && this.entityData[3131 + this.currentVillager] == this.entityData[0 + this.currentVillager]
         && this.entityData[3232 + this.currentVillager] == this.entityData[101 + this.currentVillager]) {
         if (this.canWorkBuilding(this.entityData[4444 + this.currentVillager])) {
            this.entityData[2929 + this.currentVillager] = this.moveStance(this.currentVillager);
            if (this.entityData[8810 + this.entityData[4444 + this.currentVillager]] != this.currentVillager
               || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 2
                  && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0) {
               this.entityData[8932 + this.entityData[4444 + this.currentVillager]]++;
            }

            this.entityData[606 + this.currentVillager] = this.entityData[4040 + this.currentVillager];
            this.entityData[1313 + this.currentVillager] = -1;
            this.entityData[4040 + this.currentVillager] = -100;
            this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 240);
            this.entityData[4848 + this.currentVillager] = 0;
         } else if (this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] == 0
            && this.entityData[2828 + this.currentVillager] != 69
            && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 11) {
            if (this.findNearestBuilding(this.entityData[8749 + this.entityData[4444 + this.currentVillager]])) {
               this.moveVillagerStep(1);
            }
         } else if (this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] != 0
            && (
               this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] < 13
                  || this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] > 15
            )) {
            this.goIdle();
         }

         return true;
      } else {
         return (this.entityData[2929 + this.currentVillager] & 255) <= 7;
      }
   }

   // $VF: renamed from: y () void
   private void checkActionCompletion() {
      if (this.recordBlob[this.recordStarts[171] + (this.entityData[2929 + this.currentVillager] & 255)] != -2
            && (this.entityData[1111 + this.currentVillager] & 255) >> 4
               >= this.recordBlob[this.recordStarts[37] + this.entityData[2828 + this.currentVillager]]
         || this.recordBlob[this.recordStarts[171] + (this.entityData[2929 + this.currentVillager] & 255)] == -2 && this.actionDoneD) {
         this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 15);
         if (this.entityData[2828 + this.currentVillager] == 84
            && (
               this.entityData[3939 + this.currentVillager] >= 28 && this.entityData[3939 + this.currentVillager] <= 49
                  || this.entityData[3939 + this.currentVillager] == 99
            )
            && this.checkSpellCost(this.entityData[3939 + this.currentVillager] - 28, true, this.currentVillager)) {
            this.entityData[404 + this.currentVillager] = 100;
         }

         if ((
               this.entityData[4444 + this.currentVillager] >= 0 && this.distOrTargetXZ / 10 <= this.taskDurationB
                  || this.entityData[2828 + this.currentVillager] == 85
                  || this.entityData[2828 + this.currentVillager] == 80
            )
            && this.tryCompleteTask()
            && (this.entityData[2828 + this.currentVillager] != 69 || this.entityData[6161 + this.currentVillager] < 2)) {
            if (this.entityData[3131 + this.currentVillager] == this.entityData[0 + this.currentVillager]
               && this.entityData[3232 + this.currentVillager] == this.entityData[101 + this.currentVillager]) {
               if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
                  return;
               }
            } else {
               if (this.entityData[2828 + this.currentVillager] == 58 || this.entityData[2828 + this.currentVillager] == 57) {
                  this.currentVillager = this.updateRitualQueue(this.currentVillager);
               }

               this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0);
               this.updateMovement();
               if (this.entityData[808 + this.currentVillager] > 0) {
                  return;
               }
            }

            this.actionDoneD = true;
         }
      }

      if (this.actionDoneD) {
         this.completeAction(this.distOrTargetXZ);
      }
   }

   // $VF: renamed from: i (int) void
   private void completeAction(int var1) {
      if (!this.checkBuildingWork()) {
         if (this.entityData[2828 + this.currentVillager] != 69
            || this.entityData[202 + this.currentVillager] != 0
            || this.entityData[303 + this.currentVillager] != 0) {
            if (this.entityData[2828 + this.currentVillager] == 84 && this.entityData[3939 + this.currentVillager] == 69 && this.scratchIntX < 10) {
               this.setTask(this.currentVillager, (byte)85);
            }
         } else if (this.scratchIntX < 3) {
            this.entityData[606 + this.currentVillager] = (byte)(this.entityData[606 + this.currentVillager] + this.scratchIntX % 3 + 7 & 7);
         } else if (this.scratchIntX < 5 && var1 == 0 && (this.entityData[7979 + this.currentVillager] & 2) == 0) {
            this.startCombatStance();
         }

         if ((
               this.entityData[2828 + this.currentVillager] <= 9
                  || this.entityData[2828 + this.currentVillager] == 93
                  || this.entityData[2828 + this.currentVillager] == 92
                  || this.entityData[2828 + this.currentVillager] == 106
            )
            && var1 / 10 <= this.taskDurationB) {
            this.resolvedTaskH = this.entityData[6565 + this.currentVillager];
            this.randomRollY = this.game
               .randRange(16777215, this.recordBlob[this.recordStarts[18] + this.resolvedTaskH] == 0 ? 2 + this.entityData[1717 + this.currentVillager] : 1);
            this.entityData[2929 + this.currentVillager] = this.recordBlob[this.recordStarts[161]
               + this.recordBlob[this.recordStarts[163] + this.resolvedTaskH]
               + this.randomRollY];
         } else if (this.pickNextRole(var1)) {
            return;
         }

         if (this.recordBlob[this.recordStarts[38] + this.resolvedTaskH] != 0 && this.resolvedTaskH != 105) {
            this.entityData[606 + this.currentVillager] = (byte)(var1 % 10);
         }

         if (this.resolvedTaskH >= 94 && this.resolvedTaskH <= 97 && (this.entityData[7979 + this.currentVillager] & 16) == 0) {
            this.game.setPendingOrderState(this.currentVillager, (byte)1);
            this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 16);
         }

         if (this.entityData[2929 + this.currentVillager] == 39
            || this.entityData[2929 + this.currentVillager] == 104
            || this.entityData[2929 + this.currentVillager] == 105) {
            this.entityData[3333 + this.currentVillager] = (byte)(11 + (this.scratchIntX & 1));
         }

         if (this.resolvedTaskH == 57
            && this.tribeIndex == 0
            && this.game.missionIndex < 7
            && (
               (this.game.newGameFlag & 5) == 1
                  || (this.game.newGameFlag & 9) == 1 && this.entityData[9115 + this.entityData[4444 + this.currentVillager]] == 2
            )) {
            this.setGroupMembers(this.currentVillager, 0, 1);
            this.game.advanceStoryStep((this.game.newGameFlag & 5) == 1 ? 60 : 62);
            this.game.newGameFlag = (byte)(this.game.newGameFlag | ((this.game.newGameFlag & 5) == 1 ? 4 : 8));
         }
      }
   }

   // $VF: renamed from: f () boolean
   private boolean checkBuildingWork() {
      if (this.entityData[2828 + this.currentVillager] == 78
         && this.entityData[2929 + this.currentVillager] >= 113
         && this.entityData[2929 + this.currentVillager] <= 116) {
         return true;
      }

      if (this.entityData[4646 + this.currentVillager] >= 0 && this.entityData[1313 + this.currentVillager] == -2) {
         this.game.refreshPanelIfMenuTarget(this.entityData[4444 + this.currentVillager]);
         if (this.entityData[2828 + this.currentVillager] == 51) {
            this.scratchIntC = this.entityData[8810 + this.entityData[4444 + this.currentVillager]];
            if (this.entityData[1313 + this.scratchIntC] >= 0 && this.entityData[4646 + this.scratchIntC] >= 0) {
               this.entityData[2828 + this.scratchIntC] = 55;
            } else {
               this.entityData[6161 + this.currentVillager] = 3;
               this.entityData[2828 + this.currentVillager] = 87;
            }
         }

         if (this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]]
               != this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]]]
            && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]]] > 0) {
            this.entityData[2929 + this.currentVillager] = -109;
            this.entityData[1313 + this.currentVillager] = (byte)(this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
               ? 50
               : (this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 7 ? 51 : 1));
            return true;
         }

         if (this.entityData[8810 + this.entityData[4444 + this.currentVillager]] == this.currentVillager
            && (
               this.entityData[8749 + this.entityData[4444 + this.currentVillager]] >= 12
                  || this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
            )
            && this.entityData[3636 + this.currentVillager] == 9) {
            if (this.entityData[8749 + this.entityData[4444 + this.currentVillager]] >= 12) {
               this.villagerLoopV = 0;

               while (this.villagerLoopV < 10 && this.trapRegistryF[0][this.villagerLoopV] != this.entityData[4444 + this.currentVillager]) {
                  this.villagerLoopV++;
               }

               this.trapRegistryF[1][this.villagerLoopV]++;
            } else {
               this.villagerLoopV = 0;

               while (this.villagerLoopV < 20 && this.upgradeRegistryG[0][this.villagerLoopV] != this.entityData[4444 + this.currentVillager]) {
                  this.villagerLoopV++;
               }

               this.upgradeRegistryG[2][this.villagerLoopV] = (byte)(this.upgradeRegistryG[2][this.villagerLoopV] * -1);
            }

            this.entityData[3636 + this.currentVillager] = 0;
            this.entityData[3535 + this.currentVillager] = 0;
            this.entityData[1313 + this.currentVillager] = (byte)(this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0 ? 50 : 1);
            this.entityData[2929 + this.currentVillager] = -102;
            return true;
         }
      }

      this.scratchIntX = this.game.randRange(16777215, 100);
      return false;
   }

   // $VF: renamed from: b (int) boolean
   private boolean pickNextRole(int var1) {
      if ((
            this.entityData[4444 + this.currentVillager] < 0
               || var1 / 10 > this.taskDurationB
               || this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 1
                  && this.entityData[4646 + this.currentVillager] < 0
         )
         && this.entityData[2828 + this.currentVillager] != 105
         && this.entityData[2828 + this.currentVillager] != 85) {
         if (this.entityData[1414 + this.currentVillager] >= 12) {
            this.resolvedTaskH = 69;
         } else {
            this.resolvedTaskH = 17;
         }
      } else {
         this.entityData[4848 + this.currentVillager] = 0;
         this.resolvedTaskH = this.entityData[2828 + this.currentVillager];
      }

      if (this.resolvedTaskH == 13) {
         if ((this.entityData[2929 + this.currentVillager] & 255) < 141) {
            this.entityData[2929 + this.currentVillager] = -111;
            this.entityData[606 + this.currentVillager] = (byte)(
               this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager] + 1] <= -61
                     && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager] + 1] >= -110
                  ? 0
                  : 4
            );
            return true;
         }

         if (this.entityData[2929 + this.currentVillager] == -110) {
            return true;
         }
      }

      if ((this.entityData[2929 + this.currentVillager] == -101 || this.entityData[2929 + this.currentVillager] == -100) && this.scratchIntX > 20) {
         this.startCourtship();
      } else if (this.resolvedTaskH == 69 && (this.entityData[7979 + this.currentVillager] & 2) == 0 && this.scratchIntX > 98 && this.findNearbyMate()) {
         return true;
      }

      if (this.resolvedTaskH == 72) {
         this.randomRollY = this.recordBlob[this.recordStarts[158] + this.recordBlob[this.recordStarts[77] + this.entityData[6565 + this.currentVillager]]];
      } else if (this.resolvedTaskH == 69 && (this.entityData[7979 + this.currentVillager] & 2) > 0) {
         this.randomRollY = 6;
      } else if ((this.resolvedTaskH == 69 || this.resolvedTaskH == 109 || this.resolvedTaskH == 60 || this.resolvedTaskH == 51 || this.resolvedTaskH == 87)
         && (this.entityData[3535 + this.currentVillager] != 0 || this.entityData[6565 + this.currentVillager] == 1)) {
         this.randomRollY = this.resolvedTaskH == 60 ? 1 : (this.resolvedTaskH != 51 && this.resolvedTaskH != 87 ? 4 : 2);
      } else if (this.resolvedTaskH == 83 || this.resolvedTaskH == 85 || this.resolvedTaskH == 105) {
         this.randomRollY = this.entityData[1313 + this.currentVillager];
      } else if (this.resolvedTaskH == 84) {
         this.randomRollY = this.recordBlob[this.recordStarts[167] + this.entityData[1313 + this.currentVillager]];
      } else {
         this.randomRollY = 0;
      }

      for (this.randomRollY = this.randomRollY + (this.recordBlob[this.recordStarts[166] + this.resolvedTaskH] & 255);
         this.randomRollY < (this.recordBlob[this.recordStarts[166] + this.resolvedTaskH + 1] & 255);
         this.randomRollY++
      ) {
         if (this.recordBlob[this.recordStarts[165] + this.randomRollY] > this.scratchIntX) {
            this.entityData[2929 + this.currentVillager] = this.recordBlob[this.recordStarts[164] + this.randomRollY];
            if (this.entityData[4646 + this.currentVillager] < 0
               || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 2
               || this.entityData[1313 + this.currentVillager] <= 0) {
               if ((this.recordBlob[this.recordStarts[171] + (this.entityData[2929 + this.currentVillager] & 255)] & 254) != 254 && this.scratchIntX >= 85) {
                  this.entityData[2929 + this.currentVillager] = this.recordBlob[this.recordStarts[171] + (this.entityData[2929 + this.currentVillager] & 255)];
               }

               if (this.entityData[4646 + this.currentVillager] >= 0) {
                  this.entityData[1313 + this.currentVillager] = (
                           this.entityData[2828 + this.currentVillager]
                                 == this.recordBlob[this.recordStarts[58]
                                    + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                                    + this.entityData[4646 + this.currentVillager]]
                              || this.entityData[2828 + this.currentVillager] == 53
                              || this.entityData[2828 + this.currentVillager] == 111
                              || this.entityData[4646 + this.currentVillager] > 0 && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] < 12
                        )
                        && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                     ? (byte)(this.randomRollY - (this.recordBlob[this.recordStarts[166] + this.resolvedTaskH] & 255))
                     : 50;
               }
            }
            break;
         }
      }

      return false;
   }

   // $VF: renamed from: g () boolean
   private boolean findNearbyMate() {
      for (this.scratchIntC = this.entityData[0 + this.currentVillager] - 1;
         this.scratchIntC < this.entityData[0 + this.currentVillager] + 2;
         this.scratchIntC += 2
      ) {
         for (this.scratchIntD = this.entityData[101 + this.currentVillager] - 1;
            this.scratchIntD < this.entityData[101 + this.currentVillager] + 2;
            this.scratchIntD++
         ) {
            if (this.scratchIntC > 0 && this.scratchIntC < this.game.mapWidth - 1 && this.scratchIntD > 0 && this.scratchIntD < this.game.mapHeight - 1) {
               this.randomRollY = this.occupancyGrid[this.scratchIntD][this.scratchIntC];
               if (this.randomRollY > this.tribeIndex * 50
                  && this.randomRollY <= (this.tribeIndex + 1) * 50
                  && (
                     this.entityData[2929 + this.randomRollY] == 8
                        || this.entityData[2929 + this.randomRollY] == 9
                        || this.entityData[2929 + this.randomRollY] >= 15 && this.entityData[2929 + this.randomRollY] <= 18
                  )) {
                  this.entityData[2929 + this.currentVillager] = (byte)(this.entityData[3535 + this.currentVillager] == 0
                        && this.entityData[6565 + this.currentVillager] != 1
                     ? -101
                     : -100);
                  this.entityData[606 + this.currentVillager] = (byte)(
                     this.recordBlob[this.recordStarts[53]
                           + (12 + this.scratchIntD - this.entityData[101 + this.currentVillager]) * 25
                           + 12
                           + this.scratchIntC
                           - this.entityData[0 + this.currentVillager]]
                        % 10
                  );
                  this.entityData[606 + this.randomRollY] = (byte)(this.entityData[606 + this.currentVillager] + 4 & 7);
                  this.entityData[4646 + this.currentVillager] = (byte)(
                     -92 - this.recordBlob[this.recordStarts[99] + 8 + this.entityData[606 + this.currentVillager]]
                  );
                  return true;
               }
            }
         }
      }

      return false;
   }

   // $VF: renamed from: z () void
   private void startCourtship() {
      this.randomRollY = this.occupancyGrid[this.entityData[101 + this.currentVillager]
         + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]]][this.entityData[0 + this.currentVillager]
         + this.recordBlob[this.recordStarts[1] + this.entityData[606 + this.currentVillager]]];
      if (this.randomRollY > this.tribeIndex * 50
         && this.randomRollY <= (this.tribeIndex + 1) * 50
         && (
            this.entityData[2929 + this.randomRollY] == 8
               || this.entityData[2929 + this.randomRollY] == 9
               || this.entityData[2929 + this.randomRollY] >= 15 && this.entityData[2929 + this.randomRollY] <= 18
         )) {
         this.entityData[2929 + this.randomRollY] = (byte)(this.entityData[3535 + this.randomRollY] == 0 && this.entityData[6565 + this.randomRollY] != 1
            ? -101
            : -100);
         this.entityData[606 + this.randomRollY] = (byte)(this.entityData[606 + this.currentVillager] + 4 & 7);
         this.entityData[3030 + this.randomRollY] = (byte)(this.randomRollY < this.currentVillager ? 0 : -1);
         this.entityData[4646 + this.randomRollY] = (byte)(-92 - this.recordBlob[this.recordStarts[99] + 8 + this.entityData[606 + this.randomRollY]]);
      }
   }

   // $VF: renamed from: c (int) boolean
   private boolean canWorkBuilding(int var1) {
      if (!this.canEnterBuilding(var1)) {
         this.goIdle();
         return false;
      }

      this.buildingCapacityS = this.entityData[8627 + var1] == 0
         ? this.recordBlob[this.recordStarts[138] + (this.entityData[9115 + var1] - 1) * 26 + this.entityData[8749 + var1]]
         : this.recordBlob[this.recordStarts[138] + 494 + this.entityData[8749 + var1]];
      if ((this.recordBlob[this.recordStarts[138] + 156 + this.entityData[8749 + var1]] > -1 || this.entityData[8627 + var1] != 0)
         && this.entityData[8810 + var1] > 0
         && this.entityData[8810 + var1] != this.currentVillager
         && (
            this.occupancyGrid[this.entityData[101 + this.entityData[8810 + var1]]][this.entityData[0 + this.entityData[8810 + var1]]]
                  == this.entityData[8810 + var1]
               || this.entityData[4444 + this.entityData[8810 + var1]] != var1
         )) {
         this.buildingCapacityS--;
      }

      return this.entityData[2828 + this.currentVillager] == 51
         ? this.entityData[8810 + var1] >= 1
            && this.entityData[2828 + this.entityData[8810 + var1]] == 57
            && this.entityData[1313 + this.entityData[8810 + var1]] >= 1
            && this.entityData[8932 + var1] == 1
            && this.entityData[9237 + var1] == 0
         : this.buildingCapacityS > this.entityData[8932 + var1];
   }

   // $VF: renamed from: a (int) byte
   final byte buildingWorkerInfo(int var1) {
      byte var2 = this.entityData[8932 + var1];
      if ((this.recordBlob[this.recordStarts[138] + 156 + this.entityData[8749 + var1]] > -1 || this.entityData[8627 + var1] != 0)
         && this.entityData[8810 + var1] > 0
         && (this.entityData[4646 + this.entityData[8810 + var1]] < 0 || this.entityData[4444 + this.entityData[8810 + var1]] != var1)) {
         var2++;
      }

      byte var3;
      return var3 = (byte)(
         var2
            + (
               clamp(
                     (
                           this.entityData[8627 + var1] == 0
                              ? (
                                 this.entityData[8749 + var1] != 3
                                    ? this.recordBlob[this.recordStarts[138] + (this.entityData[9115 + var1] - 1) * 26 + this.entityData[8749 + var1]]
                                    : 1
                              )
                              : this.recordBlob[this.recordStarts[138] + 494 + this.entityData[8749 + var1]]
                        )
                        - var2,
                     0,
                     7
                  )
                  << 4
            )
      );
   }

   // $VF: renamed from: d (int) boolean
   private boolean canEnterBuilding(int var1) {
      if (this.entityData[8749 + var1] >= 12 && this.entityData[8505 + var1] >= this.recordBlob[this.recordStarts[138] + 182 + this.entityData[9237 + var1]]) {
         return false;
      }

      if (this.entityData[8505 + var1] <= 0) {
         return false;
      }

      this.inspectTileTarget(this.entityData[8383 + var1], this.entityData[8444 + var1], true);
      return this.recordBlob[this.recordStarts[41] + this.chosenTask] == this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]];
   }

   // $VF: renamed from: a (byte, boolean) boolean
   private boolean checkCombatStance(byte var1, boolean var2) {
      if (this.entityData[4646 + this.currentVillager] < 0
         || this.entityData[2929 + this.currentVillager]
               != this.recordBlob[this.recordStarts[173]
                  + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                  + this.entityData[4646 + this.currentVillager]]
            && this.entityData[2929 + this.currentVillager]
               != this.recordBlob[this.recordStarts[176]
                  + this.recordBlob[this.recordStarts[177] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                  + this.entityData[4646 + this.currentVillager]]) {
         if (this.entityData[2828 + this.currentVillager] == 79
            || this.entityData[2828 + this.currentVillager] == 80
            || this.entityData[2828 + this.currentVillager] >= 83 && this.entityData[2828 + this.currentVillager] <= 85
            || var2 && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]] != 0
            || this.entityData[2929 + this.currentVillager] == -108
               && this.entityData[3030 + this.currentVillager] != this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF] - 1) {
            if (this.entityData[2828 + this.currentVillager] != 83 && this.entityData[2828 + this.currentVillager] != 84) {
               if (this.entityData[2828 + this.currentVillager] != 79) {
                  if (var2
                     && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]] > 0
                     && this.entityData[2929 + this.currentVillager] != -108) {
                     this.entityData[808 + this.currentVillager] = 0;
                     this.entityData[2929 + this.currentVillager] = -108;
                     this.entityData[3030 + this.currentVillager] = var1;
                     if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
                        this.entityData[1313 + this.currentVillager] = (byte)(this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
                           ? 50
                           : (this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 7 ? 51 : 1));
                     }

                     return true;
                  }

                  return true;
               }

               this.setTask(this.currentVillager, (byte)80);
            } else {
               this.setTask(this.currentVillager, (byte)85);
            }

            this.completeAction(0);
            this.entityData[3030 + this.currentVillager] = var1;
            return true;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   // $VF: renamed from: h () boolean
   private boolean checkSickOrRest() {
      if ((this.game.tickCounter & 7) == (this.currentVillager & 7)) {
         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 1);
         this.spiralSearchTile(7, 17, this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager]);
         if (this.tribeIndex == 0) {
            for (this.occupantByteB = 0; this.occupantByteB < 5; this.occupantByteB++) {
               if (this.game.aiTargets[1][this.occupantByteB][2] >= 0 && this.game.aiTargets[1][this.occupantByteB][3] != 1) {
                  this.scratchByteZ = (byte)(this.game.aiTargets[1][this.occupantByteB][0] - this.entityData[0 + this.currentVillager]);
                  this.scratchByteA = (byte)(this.game.aiTargets[1][this.occupantByteB][1] - this.entityData[101 + this.currentVillager]);
                  if (this.recordBlob[this.recordStarts[53]
                           + (this.scratchByteA > -10 && this.scratchByteA < 10 ? 12 + this.scratchByteA : 0) * 25
                           + (this.scratchByteZ > -10 && this.scratchByteZ < 10 ? 12 + this.scratchByteZ : 0)]
                        / 10
                     <= this.entityData[7575 + this.currentVillager] - 3) {
                     this.game.aiTargets[1][this.occupantByteB][3] = 1;
                  }
               }
            }
         }
      }

      if (this.entityData[2929 + this.currentVillager] == -106 || this.entityData[2929 + this.currentVillager] == -107) {
         this.entityData[3030 + this.currentVillager]++;
         if (this.entityData[3030 + this.currentVillager] < this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF]) {
            return true;
         }

         if (this.entityData[2929 + this.currentVillager] == -107) {
            this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 2);
         }

         return false;
      } else {
         if (this.entityData[2828 + this.currentVillager] == 69
            && this.entityData[6464 + this.currentVillager] == 69
            && this.entityData[7777 + this.currentVillager] == 0
            && (this.entityData[7979 + this.currentVillager] & 3) == 1) {
            this.entityData[2929 + this.currentVillager] = -107;
            this.entityData[3030 + this.currentVillager] = 0;
            return true;
         }

         if ((this.entityData[7979 + this.currentVillager] & 2) == 0
            || (this.entityData[2828 + this.currentVillager] == 69 || this.entityData[2828 + this.currentVillager] == 105)
               && (this.entityData[7979 + this.currentVillager] & 1) != 0
               && this.entityData[7777 + this.currentVillager] <= 0) {
            return false;
         }

         this.entityData[2929 + this.currentVillager] = -106;
         this.entityData[3030 + this.currentVillager] = 0;
         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 253);
         return true;
      }
   }

   // $VF: renamed from: A () void
   private void rankNeeds() {
      this.entityData[2222 + this.currentVillager] = this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]];

      for (this.specialModeI = 0; this.specialModeI < 4; this.specialModeI++) {
         this.entityData[this.fieldOffsets[23 + this.specialModeI] + this.currentVillager] = (byte)(9 + (this.specialModeI >> 1));
      }

      for (this.specialModeI = 2; this.specialModeI >= 0; this.specialModeI--) {
         for (this.hintTimerJ = 25; this.hintTimerJ <= 26; this.hintTimerJ++) {
            if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[107]
                     + this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager]]]
                  + this.currentVillager]
               <= this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[107] + this.specialModeI]] + this.currentVillager]) {
               for (this.calmTimerK = 26; this.calmTimerK > this.hintTimerJ; this.calmTimerK--) {
                  this.entityData[this.fieldOffsets[this.calmTimerK] + this.currentVillager] = this.entityData[this.fieldOffsets[this.calmTimerK - 1]
                     + this.currentVillager];
               }

               this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager] = (byte)this.specialModeI;
               break;
            }
         }
      }

      this.entityData[1818 + this.currentVillager] = (byte)clamp(
         this.entityData[1818 + this.currentVillager], 3 + this.entityData[1616 + this.currentVillager] / 2, 7
      );
      this.entityData[4949 + this.currentVillager] = (byte)clamp(
         this.entityData[4949 + this.currentVillager], this.entityData[1818 + this.currentVillager] + 1, 9
      );

      for (this.specialModeI = 3; this.specialModeI < 9; this.specialModeI++) {
         for (this.hintTimerJ = 23; this.hintTimerJ <= 24; this.hintTimerJ++) {
            if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[107]
                     + this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager]]]
                  + this.currentVillager]
               < this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[107] + this.specialModeI]] + this.currentVillager]) {
               if (this.specialModeI == this.entityData[2525 + this.currentVillager]
                  || this.specialModeI == this.entityData[2626 + this.currentVillager]
                  || this.recordBlob[this.recordStarts[108] + this.specialModeI]
                     == (
                        this.entityData[2222 + this.currentVillager] < 8 && this.entityData[2222 + this.currentVillager] > 0
                           ? 7
                           : this.entityData[2222 + this.currentVillager]
                     )) {
                  break;
               }

               for (this.calmTimerK = 24; this.calmTimerK > this.hintTimerJ; this.calmTimerK--) {
                  this.entityData[this.fieldOffsets[this.calmTimerK] + this.currentVillager] = this.entityData[this.fieldOffsets[this.calmTimerK - 1]
                     + this.currentVillager];
               }

               this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager] = (byte)this.specialModeI;
               break;
            }
         }
      }

      for (this.hintTimerJ = 23; this.hintTimerJ <= 26; this.hintTimerJ++) {
         if (this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager] > 8) {
            this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager] = -1;
         } else {
            this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager] = this.recordBlob[this.recordStarts[108]
               + this.entityData[this.fieldOffsets[this.hintTimerJ] + this.currentVillager]];
         }
      }
   }

   // $VF: renamed from: B () void
   private void pickDesire() {
      if (this.entityData[2929 + this.currentVillager] != 39
         && this.entityData[2929 + this.currentVillager] != 104
         && this.entityData[2929 + this.currentVillager] != 105) {
         if (this.entityData[3333 + this.currentVillager] >= 0) {
            this.entityData[3333 + this.currentVillager] = (byte)(-2 - this.game.randRange(255, 4));
         } else if (this.entityData[3333 + this.currentVillager] < -1) {
            this.entityData[3333 + this.currentVillager]++;
         }

         if (this.entityData[3333 + this.currentVillager] >= -1 && this.entityData[2828 + this.currentVillager] > 9) {
            if (this.entityData[2828 + this.currentVillager] >= 109 && this.entityData[2828 + this.currentVillager] <= 111) {
               this.entityData[3333 + this.currentVillager] = 1;
            } else {
               this.specialModeI = this.game.randRange(16777215, 22);
               if (this.specialModeI < 11
                  && this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[56] + this.specialModeI]] + this.currentVillager]
                     >= this.recordBlob[this.recordStarts[56] + 11 + this.specialModeI]
                  && this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[56] + this.specialModeI]] + this.currentVillager]
                     <= this.recordBlob[this.recordStarts[56] + 22 + this.specialModeI]
                  && this.game.randRange(16777215, 100) < this.recordBlob[this.recordStarts[56] + 33 + this.specialModeI]) {
                  this.entityData[3333 + this.currentVillager] = (byte)this.specialModeI;
               }

               if (this.entityData[3434 + this.currentVillager] < 1 && this.entityData[505 + this.currentVillager] < 10) {
                  this.entityData[3333 + this.currentVillager] = 9;
               }

               if (this.entityData[2020 + this.currentVillager] == 0) {
                  this.entityData[3333 + this.currentVillager] = 0;
               }

               if (this.entityData[2121 + this.currentVillager] == 0) {
                  this.entityData[3333 + this.currentVillager] = 2;
               }
            }
         }
      }
   }

   // $VF: renamed from: C () void
   private void updateTaskState() {
      if (this.entityData[2828 + this.currentVillager] != 78
         || this.entityData[2929 + this.currentVillager] < 113
         || this.entityData[2929 + this.currentVillager] > 116) {
         if (this.recordBlob[this.recordStarts[40] + this.entityData[2828 + this.currentVillager]] != -1) {
            if (this.entityData[3535 + this.currentVillager] != this.recordBlob[this.recordStarts[40] + this.entityData[2828 + this.currentVillager]]) {
               this.entityData[3636 + this.currentVillager] = 0;
               this.entityData[3535 + this.currentVillager] = this.recordBlob[this.recordStarts[40] + this.entityData[2828 + this.currentVillager]];
               return;
            }
         } else if (this.entityData[3636 + this.currentVillager] == 0) {
            this.entityData[3535 + this.currentVillager] = 0;
         }
      }
   }

   // $VF: renamed from: a (byte) void
   private void swapVillager(byte var1) {
      this.savedVillagerU = (byte)this.currentVillager;
      this.currentVillager = var1;
      if (!this.checkCombatStance((byte)(var1 < this.savedVillagerU ? 0 : -1), false)) {
         this.entityData[808 + var1] = 0;
         this.swapTempT = this.entityData[0 + var1];
         this.entityData[0 + var1] = this.entityData[0 + this.savedVillagerU];
         this.entityData[0 + this.savedVillagerU] = this.swapTempT;
         this.swapTempT = this.entityData[101 + var1];
         this.entityData[101 + var1] = this.entityData[101 + this.savedVillagerU];
         this.entityData[101 + this.savedVillagerU] = this.swapTempT;
         if (this.entityData[6262 + var1] == this.entityData[0 + this.savedVillagerU]
            && this.entityData[6363 + var1] == this.entityData[101 + this.savedVillagerU]) {
            this.entityData[6262 + var1] = this.entityData[0 + var1];
            this.entityData[6363 + var1] = this.entityData[101 + var1];
            this.entityData[3131 + var1] = this.entityData[0 + var1];
            this.entityData[3232 + var1] = this.entityData[101 + var1];
         }

         this.occupancyGrid[this.entityData[101 + this.savedVillagerU]][this.entityData[0 + this.savedVillagerU]] = this.savedVillagerU;
         this.occupancyGrid[this.entityData[101 + var1]][this.entityData[0 + var1]] = var1;
         this.entityData[606 + var1] = (byte)(this.entityData[606 + this.savedVillagerU] + 4 & 7);
         this.entityData[1111 + var1] = (byte)(this.entityData[1111 + var1] & 240);
         this.entityData[4848 + var1] = 120;
         this.entityData[4848 + this.savedVillagerU] = 120;
         this.entityData[2929 + var1] = this.moveStance(var1);
         this.walkStep();
         if (var1 > this.savedVillagerU) {
            this.entityData[1111 + var1] = (byte)(this.entityData[1111 + var1] & 240);
         }

         this.stepResultE = 1;
      } else {
         this.stepResultE = 2;
      }

      this.currentVillager = this.savedVillagerU;
   }

   // $VF: renamed from: D () void
   private void walkStep() {
      this.computeWalkOffsets();
      if ((this.entityData[1111 + this.currentVillager] & 15)
            == (this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL] + 1) / 2
         && this.entityData[4848 + this.currentVillager] != 120) {
         this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] = 0;
         if (this.entityData[4545 + this.currentVillager] <= -111) {
            this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] = this.entityData[4545
               + this.currentVillager];
            this.entityData[4545 + this.currentVillager] = 0;
         }

         if (this.entityData[4545 + this.currentVillager] >= 111) {
            this.entityData[4545 + this.currentVillager] = (byte)(this.entityData[4545 + this.currentVillager] * -1);
            this.entityData[1313 + this.currentVillager] = 0;
         }

         if (this.entityData[1313 + this.currentVillager] == -1) {
            this.enterBuilding();
            return;
         }

         this.entityData[0 + this.currentVillager] = (byte)(
            this.entityData[0 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 0 + this.entityData[606 + this.currentVillager]]
         );
         this.entityData[101 + this.currentVillager] = (byte)(
            this.entityData[101 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]]
         );
         this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] = (byte)this.currentVillager;
      }

      if (this.entityData[4848 + this.currentVillager] == 120) {
         if ((this.entityData[1111 + this.currentVillager] & 15)
            < (this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL] + 1) / 2) {
            this.entityData[202 + this.currentVillager] = (byte)(
               this.entityData[202 + this.currentVillager] - this.recordBlob[this.recordStarts[1] + 0 + this.entityData[606 + this.currentVillager]] * 22
            );
            this.entityData[303 + this.currentVillager] = (byte)(
               this.entityData[303 + this.currentVillager] - this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]] * 16
            );
         }

         this.entityData[202 + this.currentVillager] = (byte)(
            this.entityData[202 + this.currentVillager]
               + this.recordBlob[this.recordStarts[9 + this.scratchIntW]
                     + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2] + this.walkCycleOrProgressL]
                     + (this.entityData[1111 + this.currentVillager] & 15)]
                  * this.recordBlob[this.recordStarts[2] + 0 + this.entityData[606 + this.currentVillager]]
         );
         this.entityData[303 + this.currentVillager] = (byte)(
            this.entityData[303 + this.currentVillager]
               + this.recordBlob[this.recordStarts[11 + this.scratchIntW * 2]
                     + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2] + this.walkCycleOrProgressL]
                     + (this.entityData[1111 + this.currentVillager] & 15)]
                  * this.recordBlob[this.recordStarts[2] + 8 + this.entityData[606 + this.currentVillager]]
         );
      }
   }

   // $VF: renamed from: E () void
   private void computeWalkOffsets() {
      this.scratchIntW = this.entityData[606 + this.currentVillager] & 1;
      this.walkCycleOrProgressL = this.entityData[1010 + this.currentVillager] - this.entityData[7777 + this.currentVillager];
      this.entityData[1111 + this.currentVillager]++;
      this.entityData[202 + this.currentVillager] = this.recordBlob[this.recordStarts[5 + this.scratchIntW]
         + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2] + this.walkCycleOrProgressL]
         + (
               this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL]
                  + (this.entityData[1111 + this.currentVillager] & 15)
                     * this.recordBlob[this.recordStarts[1] + 0 + this.entityData[606 + this.currentVillager]]
            )
            % this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL]];
      this.entityData[303 + this.currentVillager] = this.recordBlob[this.recordStarts[7 + this.scratchIntW]
         + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2] + this.walkCycleOrProgressL]
         + (
               this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL]
                  + (this.entityData[1111 + this.currentVillager] & 15)
                     * this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]]
            )
            % this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL]];
      if (this.entityData[202 + this.currentVillager] == 0
         && this.entityData[303 + this.currentVillager] == 0
         && this.entityData[4848 + this.currentVillager] == 120) {
         this.entityData[4848 + this.currentVillager] = 1;
      }

      if ((this.entityData[1111 + this.currentVillager] & 15)
         == (this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL] + 1) / 2) {
         if ((this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL] & 1) == 0
            && this.recordBlob[this.recordStarts[1] + 0 + this.entityData[606 + this.currentVillager]] < 0) {
            this.entityData[202 + this.currentVillager] = (byte)(this.entityData[202 + this.currentVillager] + 22);
         }

         if ((this.recordBlob[this.recordStarts[15] + this.scratchIntW * 9 + this.walkCycleOrProgressL] & 1) == 0
            && this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + this.currentVillager]] < 0) {
            this.entityData[303 + this.currentVillager] = (byte)(this.entityData[303 + this.currentVillager] + 16);
         }
      }
   }

   // $VF: renamed from: F () void
   private void enterBuilding() {
      this.entityData[202 + this.currentVillager] = 0;
      this.entityData[303 + this.currentVillager] = 0;
      this.entityData[3030 + this.currentVillager] = 0;
      this.entityData[1111 + this.currentVillager] = (byte)(this.entityData[1111 + this.currentVillager] & 240);
      if (this.entityData[6161 + this.currentVillager] <= 1
         && this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 1
         && this.canEnterBuilding(this.entityData[4444 + this.currentVillager])) {
         this.game.h_int_void(this.currentVillager);
         this.entityData[6161 + this.currentVillager] = 0;
         this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
         this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
         this.entityData[1313 + this.currentVillager] = -2;
         this.entityData[1818 + this.currentVillager] = (byte)(this.entityData[1818 + this.currentVillager] + 2);
         this.assignWorkSlot();
         this.entityData[4545 + this.currentVillager] = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];
         this.entityData[8993 + this.entityData[4444 + this.currentVillager]] = (byte)this.currentVillager;
         this.updateTowerStats(this.entityData[4444 + this.currentVillager]);
         if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 128) == 0) {
            this.setTask(
               this.currentVillager,
               this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                  ? this.recordBlob[this.recordStarts[57]
                     + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                     + this.entityData[4646 + this.currentVillager]]
                  : this.recordBlob[this.recordStarts[59]
                     + this.recordBlob[this.recordStarts[177] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                     + this.entityData[4646 + this.currentVillager]]
            );
         }

         if (this.entityData[4646 + this.currentVillager]
               == this.recordBlob[this.recordStarts[138] + 156 + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
            || this.recordBlob[this.recordStarts[138] + 156 + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] < 0
               && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 2
               && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 3
            || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] >= 12
            || this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0) {
            if (this.recordBlob[this.recordStarts[42]
                     + this.recordBlob[this.recordStarts[41]
                        + this.recordBlob[this.recordStarts[16]
                           + this.recordBlob[this.recordStarts[17] + 2]
                           + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]]]
                  == 0
               && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0) {
               this.entityData[6464 + this.currentVillager] = this.recordBlob[this.recordStarts[16]
                  + this.recordBlob[this.recordStarts[17] + 2]
                  + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]];
            }

            this.entityData[6262 + this.currentVillager] = this.entityData[8383 + this.entityData[4444 + this.currentVillager]];
            this.entityData[6363 + this.currentVillager] = this.entityData[8444 + this.entityData[4444 + this.currentVillager]];
         }

         if ((this.entityData[7979 + this.currentVillager] & 4) != 0
            && (
               this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
                  || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 8
            )) {
            this.entityData[8871 + this.entityData[4444 + this.currentVillager]] = (byte)(
               this.entityData[8871 + this.entityData[4444 + this.currentVillager]] | 2
            );
         }

         this.entityData[2929 + this.currentVillager] = this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
            ? this.recordBlob[this.recordStarts[172]
               + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
               + this.entityData[4646 + this.currentVillager]]
            : this.recordBlob[this.recordStarts[175]
               + this.recordBlob[this.recordStarts[177] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
               + this.entityData[4646 + this.currentVillager]];
      } else {
         if (this.entityData[6161 + this.currentVillager] == 1) {
            this.entityData[6161 + this.currentVillager] = 0;
         }

         if (this.entityData[8810 + this.entityData[4444 + this.currentVillager]] != this.currentVillager
            || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 2
               && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0) {
            this.entityData[8932 + this.entityData[4444 + this.currentVillager]]--;
         }

         this.entityData[1313 + this.currentVillager] = 0;
         this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] = (byte)this.currentVillager;
         this.setIdleRole();
         this.goIdle();
      }
   }

   // $VF: renamed from: G () void
   private void assignWorkSlot() {
      this.listCursorX = this.entityData[8749 + this.entityData[4444 + this.currentVillager]];
      this.villagerLoopV = this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
         ? this.recordBlob[this.recordStarts[138] + 156 + this.listCursorX]
         : 0;
      this.workerOrParityW = this.entityData[8932 + this.entityData[4444 + this.currentVillager]];

      for (this.scratchIntX = 0; this.scratchIntX < 8; this.scratchIntX++) {
         this.buildingSlotMapI[this.scratchIntX] = 0;
      }

      for (this.scratchIntX = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];
         this.scratchIntX > 0;
         this.scratchIntX = this.entityData[4545 + this.scratchIntX]
      ) {
         this.buildingSlotMapI[this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
            ? this.entityData[4646 + this.scratchIntX]
            : this.recordBlob[this.recordStarts[93] + this.recordBlob[this.recordStarts[94] + this.listCursorX] + this.entityData[4646 + this.scratchIntX]]] = (byte)this.scratchIntX;
      }

      if (this.villagerLoopV >= 0 && this.buildingSlotMapI[0] == 0) {
         if (this.entityData[8810 + this.entityData[4444 + this.currentVillager]] <= 0
            || this.entityData[8810 + this.entityData[4444 + this.currentVillager]] == this.currentVillager) {
            if (this.entityData[8810 + this.entityData[4444 + this.currentVillager]] == this.currentVillager) {
               this.entityData[8932 + this.entityData[4444 + this.currentVillager]]++;
            }

            this.entityData[4646 + this.currentVillager] = this.villagerLoopV;
            this.entityData[8810 + this.entityData[4444 + this.currentVillager]] = (byte)this.currentVillager;
            return;
         }

         this.buildingSlotMapI[this.villagerLoopV] = this.entityData[8810 + this.entityData[4444 + this.currentVillager]];
         if (this.entityData[4646 + this.buildingSlotMapI[this.villagerLoopV]] < 0
            || this.entityData[4444 + this.buildingSlotMapI[this.villagerLoopV]] != this.entityData[4444 + this.currentVillager]) {
            this.workerOrParityW++;
         }
      }

      this.entityData[4646 + this.currentVillager] = (byte)(
         this.game
               .randRange(
                  16777215,
                  (
                        this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                           ? this.recordBlob[this.recordStarts[138]
                              + (this.entityData[9115 + this.entityData[4444 + this.currentVillager]] - 1) * 26
                              + this.listCursorX]
                           : this.recordBlob[this.recordStarts[138] + 494 + this.listCursorX]
                     )
                     - this.workerOrParityW
                     + 1
               )
            + 1
      );
      if (this.listCursorX == 2 && this.buildingSlotMapI[0] == 0) {
         this.entityData[4646 + this.currentVillager] = 0;
      } else {
         this.scratchIntX = 0;

         for (this.randomRollY = 0;
            this.scratchIntX
               < (
                  this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                     ? this.recordBlob[this.recordStarts[138]
                        + (this.entityData[9115 + this.entityData[4444 + this.currentVillager]] - 1) * 26
                        + this.listCursorX]
                     : this.recordBlob[this.recordStarts[138] + 494 + this.listCursorX]
               );
            this.scratchIntX++
         ) {
            if (this.buildingSlotMapI[this.scratchIntX] == 0) {
               this.randomRollY++;
            }

            if (this.entityData[4646 + this.currentVillager] == this.randomRollY) {
               this.entityData[4646 + this.currentVillager] = (byte)this.scratchIntX;
               break;
            }
         }

         if (this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0) {
            this.entityData[4646 + this.currentVillager] = this.recordBlob[this.recordStarts[95]
               + this.recordBlob[this.recordStarts[96] + this.listCursorX]
               + this.entityData[4646 + this.currentVillager]];
         }
      }
   }

   // $VF: renamed from: b (byte) void
   private void updateTowerStats(byte var1) {
      this.entityData[8688 + var1] = 5;
      if (this.tribeIndex == 0 && this.entityData[8749 + var1] == 11) {
         for (this.aiRankThresholdM = this.entityData[8993 + var1];
            this.aiRankThresholdM > 0;
            this.aiRankThresholdM = this.entityData[4545 + this.aiRankThresholdM]
         ) {
            if (this.entityData[4949 + this.aiRankThresholdM] + 3 > (this.entityData[8688 + var1] & 15)) {
               this.entityData[8688 + var1] = (byte)((this.entityData[8688 + var1] & 240) + this.entityData[4949 + this.aiRankThresholdM] + 3);
            }

            if (this.entityData[7575 + this.aiRankThresholdM] > this.entityData[8688 + var1] >> 4) {
               this.entityData[8688 + var1] = (byte)((this.entityData[8688 + var1] & 15) + (this.entityData[7575 + this.aiRankThresholdM] << 4));
            }
         }
      }
   }

   // $VF: renamed from: a (int, int, int, int) boolean
   final boolean spiralSearchTile(int var1, int var2, int var3, int var4) {
      byte var6 = 0;
      byte var7 = 1;
      byte[] var10 = new byte[2];
      this.rollByteY = 0;
      byte var9 = (byte)(this.tribeIndex ^ 1);

      while (var6 < var2) {
         var6++;

         for (byte var5 = 0; var5 < 2; var5++) {
            for (byte var8 = 0; var8 < var6; var8++) {
               var10[var5] += var7;
               if (var3 + var10[0] >= 0 && var3 + var10[0] < this.game.mapWidth && var4 + var10[1] >= 0 && var4 + var10[1] < this.game.mapHeight) {
                  this.occupantByteB = this.occupancyGrid[var4 + var10[1]][var3 + var10[0]];
                  if (var2 < 24) {
                     this.distByteC = (byte)(this.recordBlob[this.recordStarts[53] + (12 + var10[1]) * 25 + 12 + var10[0]] / 10);
                     this.dirByteE = (byte)(this.recordBlob[this.recordStarts[53] + (12 + var10[1]) * 25 + 12 + var10[0]] % 10 + 4 & 7);
                  }

                  if (var1 > 4) {
                     if (var1 == 6) {
                        if (this.distByteC <= 3) {
                           if (-this.occupantByteB > 0 && -this.occupantByteB <= 30 && this.entityData[9601 + this.currentVillager] == 0) {
                              if (this.entityData[8505 + -this.occupantByteB] > 0 && this.entityData[8932 + -this.occupantByteB] != 0) {
                                 this.setAnimalTarget(var3 + var10[0], var4 + var10[1], this.occupantByteB, 50);
                              }
                           } else if (this.occupantByteB > 0
                              && this.occupantByteB <= 100
                              && this.entityData[1919 + this.occupantByteB] > 0
                              && (this.entityData[9601 + this.currentVillager] <= 0 || this.occupantByteB > 50)) {
                              this.setAnimalTarget(var3 + var10[0], var4 + var10[1], this.occupantByteB, 50);
                              if (this.occupantByteB > 50) {
                                 return true;
                              }
                           }
                        }
                     } else if (var1 == 7) {
                        if (this.occupantByteB > var9 * 50 && this.occupantByteB <= (var9 + 1) * 50) {
                           if (this.entityData[1919 + this.occupantByteB] > 0
                              && (
                                 (this.entityData[7979 + this.currentVillager] & 2) > 0
                                       && 1
                                             + (10 - this.entityData[7575 + this.currentVillager]) / 3
                                             + this.entityData[7575 + this.occupantByteB] / (this.entityData[7575 + this.currentVillager] - 3)
                                          >= this.distByteC
                                    || (this.entityData[7979 + this.currentVillager] & 2) == 0 && this.entityData[4949 + this.occupantByteB] >= this.distByteC
                              )) {
                              this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 254);
                              return true;
                           }
                        } else if (-this.occupantByteB > var9 * 30 && -this.occupantByteB <= (var9 + 1) * 30) {
                           if (this.entityData[8505 + -this.occupantByteB] > 0
                              && this.entityData[8932 + -this.occupantByteB] != 0
                              && var3 + var10[0]
                                 == this.entityData[8383 + -this.occupantByteB]
                                    + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + -this.occupantByteB]] / 2
                              && var4 + var10[1]
                                 == this.entityData[8444 + -this.occupantByteB]
                                    + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + -this.occupantByteB]] / 2
                              && (
                                 (this.entityData[7979 + this.currentVillager] & 2) > 0
                                       && 1
                                             + (10 - this.entityData[7575 + this.currentVillager]) / 3
                                             + (this.entityData[8688 + -this.occupantByteB] >> 4) / (this.entityData[7575 + this.currentVillager] - 3)
                                          >= this.distByteC
                                    || (this.entityData[7979 + this.currentVillager] & 2) == 0
                                       && (this.entityData[8688 + -this.occupantByteB] & 15) >= this.distByteC
                              )) {
                              this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 254);
                              return true;
                           }
                        } else if (-this.occupantByteB >= 111 + 6 * (this.tribeIndex ^ 1)
                           && -this.occupantByteB < 111 + 6 * (this.tribeIndex ^ 1) + 3
                           && this.entityData[7575 + this.currentVillager] >= 6
                           && this.distByteC <= this.entityData[7575 + this.currentVillager] - 4) {
                           this.occupancyGrid[var4 + var10[1]][var3 + var10[0]] = (byte)(
                              this.occupancyGrid[var4 + var10[1]][var3 + var10[0]] + (3 - this.tribeIndex * 6)
                           );
                           this.game.markTileRect(var3 + var10[0], var4 + var10[1], 1, 1);
                           if (this.tribeIndex == 0) {
                              this.scratchByteR--;
                           }
                        }
                     } else if (var1 >= 8
                        && this.occupantByteB >= (var1 == 9 ? 51 : 1)
                        && this.occupantByteB <= (var1 == 9 ? 100 : 50)
                        && this.entityData[1919 + this.occupantByteB] > 0
                        && this.entityData[1414 + this.occupantByteB] >= 12) {
                        this.game.storyActors[6 + this.waveStepO] = this.occupantByteB;
                        this.waveStepO++;
                     }
                  } else {
                     if (var1 == 3) {
                        if (this.occupantByteB != 0
                              && this.occupantByteB != this.matingPairs[0][this.matePairIndexAe]
                              && this.occupantByteB != this.matingPairs[1][this.matePairIndexAe]
                           || var3 + var10[0] >= this.game.mapWidth - 1) {
                           continue;
                        }

                        this.occupantByteB = this.occupancyGrid[var4 + var10[1]][var3 + var10[0] + 1];
                        if (this.occupantByteB != 0 && this.occupantByteB != this.matingPairs[1][this.matePairIndexAe]) {
                           continue;
                        }

                        this.occupantByteB = this.matingPairs[1 - (this.entityData[8282 + this.currentVillager] & 1)][this.matePairIndexAe];
                        if (this.entityData[2828 + this.occupantByteB] == 109) {
                           this.entityData[3131 + this.occupantByteB] = (byte)(var3 + var10[0] + 1 - (this.entityData[8282 + this.currentVillager] & 1));
                           this.entityData[3232 + this.occupantByteB] = (byte)(var4 + var10[1]);
                        }
                     } else if (var1 != 2 && var1 != 1
                        ? (
                           var1 == 4
                              ? this.game.terrainGrid[var4 + var10[1]][var3 + var10[0]] < 95
                                 || this.game.terrainGrid[var4 + var10[1]][var3 + var10[0]] > 114
                                 || this.occupantByteB != 0
                              : !this.canGatherAt(this.occupantByteB)
                        )
                        : this.occupantByteB != 0) {
                        continue;
                     }

                     this.entityData[this.fieldOffsets[var1 == 2 ? 112 : 31] + this.currentVillager] = (byte)(
                        var3 + var10[0] + (var1 == 3 ? this.entityData[8282 + this.currentVillager] & 1 : 0)
                     );
                     this.entityData[this.fieldOffsets[var1 == 2 ? 113 : 32] + this.currentVillager] = (byte)(var4 + var10[1]);
                     if (var1 >= 1 && var1 <= 3 || this.currentVillager == 0) {
                        return true;
                     }

                     this.entityData[4444 + this.currentVillager] = -1;
                     this.repathToTarget();
                     if (this.entityData[808 + this.currentVillager] > 0
                        || this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) / 10
                           <= this.taskDurationB) {
                        if (this.entityData[6161 + this.currentVillager] > 0
                           || this.entityData[2828 + this.currentVillager] == 10
                           || this.entityData[2828 + this.currentVillager] == 13
                           || this.entityData[2828 + this.currentVillager] == 15) {
                           this.entityData[6262 + this.currentVillager] = this.entityData[3131 + this.currentVillager];
                           this.entityData[6363 + this.currentVillager] = this.entityData[3232 + this.currentVillager];
                        }

                        this.moveVillagerStep(2);
                        return true;
                     }
                  }
               }
            }
         }

         var7 *= -1;
      }

      return false;
   }

   // $VF: renamed from: H () void
   private void handleArrival() {
      this.pendingBuildP = this.recordStarts[101] + this.game.missionIndex * 4;
      if ((this.slotScanI & this.tribeIndex + 1) == 0
         || this.scriptEventIndex < 50
            && (
               this.rollByteY == 2
                  || this.rollByteY == 1 && this.game.missionIndex != 2
                  || this.scratchByteZ < this.recordBlob[this.pendingBuildP]
                  || this.scratchByteA < this.recordBlob[this.pendingBuildP + 1]
                  || this.scratchByteZ > this.recordBlob[this.pendingBuildP + 2]
                  || this.scratchByteA > this.recordBlob[this.pendingBuildP + 3]
            )) {
         this.occupantByteB = this.occupancyGrid[this.scratchByteA][this.scratchByteZ];
         if (!this.searchOkA) {
            if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager
               && this.entityData[1313 + this.currentVillager] > 0) {
               this.entityData[2828 + this.currentVillager] = 87;
               this.entityData[6161 + this.currentVillager] = 3;
            }
         } else {
            this.taskCompleteF = this.entityData[6565 + this.currentVillager] != 9
               || this.entityData[2828 + this.currentVillager] <= 9
               || this.tribeIndex == 1
               || this.game.placeConfirmFlagN
               || this.rollByteY == 1
               || this.entityData[0 + this.currentVillager] - this.entityData[8384] >= -6
                  && this.entityData[0 + this.currentVillager] - this.entityData[8384] <= 6
                  && this.entityData[101 + this.currentVillager] - this.entityData[8445] >= -6
                  && this.entityData[101 + this.currentVillager] - this.entityData[8445] <= 6;
            if ((this.taskCompleteF || this.entityData[2828 + this.currentVillager] != 59)
               && (
                  this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
                     || this.rollByteY != 1
               )) {
               this.entityData[3131 + this.currentVillager] = this.taskCompleteF ? this.scratchByteZ : this.entityData[0 + this.currentVillager];
               this.entityData[3232 + this.currentVillager] = this.taskCompleteF ? this.scratchByteA : this.entityData[101 + this.currentVillager];
               if (this.taskCompleteF) {
                  if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
                     this.moveVillagerStep(1);
                     if ((this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0))
                           / 10
                        > this.taskDurationB) {
                        this.updateMovement();
                     }
                  } else {
                     if (this.entityData[2828 + this.currentVillager] <= 82) {
                        this.entityData[0 + this.currentVillager] = this.entityData[8383 + this.entityData[4444 + this.currentVillager]];
                        this.entityData[101 + this.currentVillager] = this.entityData[8444 + this.entityData[4444 + this.currentVillager]];
                     }

                     this.entityData[4747 + this.currentVillager] = this.occupantByteB;
                     this.entityData[4444 + this.currentVillager] = (byte)(
                        this.occupantByteB * this.recordBlob[this.recordStarts[62] + this.rollByteY] - (this.rollByteY == 2 ? 101 : 0)
                     );
                     this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[61] + this.rollByteY]);
                     this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0);
                     this.completeAction(this.distOrTargetXZ);
                     this.entityData[3030 + this.currentVillager] = 0;
                     this.entityData[1313 + this.currentVillager] = 1;
                  }
               } else {
                  this.setTask(this.currentVillager, (byte)59);
                  this.entityData[2929 + this.currentVillager] = -95;
                  this.entityData[3030 + this.currentVillager] = 0;
                  this.entityData[3636 + this.currentVillager] = 0;
                  this.entityData[808 + this.currentVillager] = 0;
               }
            }
         }

         if (this.rollByteY == 4 && this.tribeIndex == 0) {
            if ((this.entityData[7979 + this.currentVillager] & 2) == 0) {
               this.showMessage((byte)4, this.currentVillager, this.occupantByteB);
            }

            if (this.messageCooldowns[0] < 0) {
               this.messageCooldowns[0] = 0;
               this.game.postMessage((byte)6, this.scratchByteZ, this.scratchByteA, (byte)0);
            }
         }

         this.scratchByteD = this.foundEntityF;
         if ((this.rollByteY == 4 || this.rollByteY == 2 && (this.entityData[7979 + this.currentVillager] & 128) != 0)
            && (this.entityData[7979 + this.currentVillager] & 2) == 0) {
            this.scanForTargetKind(
               this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], this.entityData[4949 + this.currentVillager]
            );
         }
      } else if (this.rollByteY == 4) {
         if (this.entityData[4646 + this.currentVillager] < 0) {
            this.stopAtTile(this.currentVillager, false);
            this.setTask(this.currentVillager, (byte)69);
         } else {
            this.setTask(this.currentVillager, (byte)87);
            this.entityData[6161 + this.currentVillager] = 3;
         }
      }

      if (this.scriptEventIndex < this.recordBlob[this.recordStarts[291] + this.game.missionIndex]
         && this.tribeIndex == 0
         && this.recordBlob[this.recordStarts[287] + this.recordBlob[this.recordStarts[288] + this.game.missionIndex] + this.scriptEventIndex] == 1
         && this.rollByteY == 4
         && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
         this.fireScriptedEvent(this.currentVillager, this.occupantByteB > 0 ? this.occupantByteB : 0, this.scratchByteZ, this.scratchByteA);
      }
   }

   // $VF: renamed from: a (int, int, byte, int) void
   private void setAnimalTarget(int var1, int var2, byte var3, int var4) {
      if (this.recordBlob[this.recordStarts[181] + this.entityData[9535 + this.currentVillager]] <= var4 && this.entityData[9601 + this.currentVillager] == 0) {
         this.retargetAnimal(true);
      } else {
         this.entityData[9645 + this.currentVillager] = (byte)var1;
         this.entityData[9667 + this.currentVillager] = (byte)var2;
         this.entityData[9601 + this.currentVillager] = var3;
      }
   }

   // $VF: renamed from: c (byte) boolean
   private boolean canGatherAt(byte var1) {
      if (var1 == this.entityData[4747 + this.currentVillager]
         && this.entityData[2828 + this.currentVillager] != 10
         && this.entityData[2828 + this.currentVillager] != 15
         && this.entityData[2828 + this.currentVillager] != 12
         && this.entityData[2828 + this.currentVillager] != 16
         && this.entityData[2828 + this.currentVillager] != 13) {
         return true;
      }

      if (this.entityData[2828 + this.currentVillager] >= 10 && this.entityData[2828 + this.currentVillager] <= 16 && var1 <= -61 && var1 >= -110) {
         byte var2 = this.recordBlob[this.recordStarts[16] + this.recordBlob[this.recordStarts[17] + 3] + this.entityData[9772 - var1]];
         if ((this.entityData[2828 + this.currentVillager] == 12 || this.entityData[2828 + this.currentVillager] == 16)
               && (var2 == 12 || var2 == 16)
               && this.entityData[9822 - var1] > 0
            || this.entityData[9872 - var1] > 0 && var2 == this.entityData[2828 + this.currentVillager]) {
            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: b (int, int, int, int) void
   private void fireScriptedEvent(int var1, int var2, int var3, int var4) {
      this.eventRectBaseN = this.recordStarts[289] + this.recordBlob[this.recordStarts[290] + this.game.missionIndex] + this.scriptEventIndex * 4;
      if (var3 >= this.recordBlob[this.eventRectBaseN]
         && var4 >= this.recordBlob[this.eventRectBaseN + 1]
         && var3 <= this.recordBlob[this.eventRectBaseN + 2]
         && var4 <= this.recordBlob[this.eventRectBaseN + 3]
         && (this.game.missionIndex == 3 || this.entityData[1919 + this.tribeStats[0][17]] > 0 && this.entityData[1414 + this.tribeStats[0][17]] >= 12)) {
         if (this.game.missionIndex == 4 && this.scriptEventIndex == 1) {
            this.pickNeedyCouncil(false);

            for (this.eventRectBaseN = 0; this.eventRectBaseN < 4; this.eventRectBaseN++) {
               this.game.storyActors[this.eventRectBaseN + 1] = this.needyListE[this.eventRectBaseN][0] != 0
                  ? this.needyListE[this.eventRectBaseN][0]
                  : this.needyListE[0][0];
            }
         } else {
            this.setGroupMembers(var1, var2, 1);
            this.entityData[3131 + var2] = this.entityData[0 + var2];
            this.entityData[3232 + var2] = this.entityData[101 + var2];
            this.waveStepO = 0;
            this.spiralSearchTile(
               this.game.missionIndex == 2 ? 9 : 8,
               25,
               this.entityData[0 + (this.game.missionIndex == 2 ? var2 : var1)],
               this.entityData[101 + (this.game.missionIndex == 2 ? var2 : var1)]
            );
         }

         this.game
            .advanceStoryStep(
               this.recordBlob[this.recordStarts[286] + this.recordBlob[this.recordStarts[288] + this.game.missionIndex] + this.scriptEventIndex]
            );
         this.scriptEventIndex = (byte)(this.scriptEventIndex + 50);
      }
   }

   // $VF: renamed from: b (int, int, int) void
   private void setGroupMembers(int var1, int var2, int var3) {
      this.game.storyActors[1] = this.game.storyActors[2] = (byte)this.tribeStats[0][17];
      this.game.storyActors[5] = (byte)var2;
      if (var1 != 0) {
         this.game.storyActors[0] = this.game.storyActors[2] = this.game.storyActors[3] = this.game.storyActors[4] = (byte)var1;
      }

      this.waveStepO = var3;

      for (this.eventRectBaseN = var1 != this.tribeStats[0][17] && var1 != 0 ? 3 : 2; this.waveStepO <= 50 && this.eventRectBaseN <= 4; this.waveStepO++) {
         if (this.entityData[1919 + this.waveStepO] > 0
            && this.entityData[1414 + this.waveStepO] >= 12
            && this.waveStepO != var1
            && this.waveStepO != this.tribeStats[0][17]) {
            this.game.storyActors[this.eventRectBaseN] = (byte)this.waveStepO;
            this.eventRectBaseN++;
         }
      }
   }

   // $VF: renamed from: c (boolean) void
   private void pickNeedyCouncil(boolean var1) {
      this.councilPickedE = false;
      this.scratchByteH = 0;
      if (this.entityData[8994] == this.tribeStats[0][17]) {
         this.scratchByteH = 1;
      }

      this.saveCouncilMember(this.tribeStats[0][17], 0);

      for (this.eventRectBaseN = 1; this.eventRectBaseN < 4; this.eventRectBaseN++) {
         this.needyListE[this.eventRectBaseN][0] = this.needyListE[this.eventRectBaseN][1] = 0;
      }

      for (this.waveStepO = 1; this.waveStepO <= 50; this.waveStepO++) {
         if (this.entityData[1919 + this.waveStepO] > 0 && this.tribeStats[0][17] != this.waveStepO && this.entityData[1414 + this.waveStepO] >= 12) {
            this.waveTargetQ = this.entityData[1717 + this.waveStepO] > this.entityData[1616 + this.waveStepO]
               ? this.entityData[1717 + this.waveStepO]
               : this.entityData[1616 + this.waveStepO];
            World var10000 = this;
            int var10001 = 1;
            int var10002 = var1 && this.councilPickedE ? 1 : 0;

            while (true) {
               var10000.eventRectBaseN = var10001 + var10002;
               if (this.eventRectBaseN >= 4) {
                  break;
               }

               if (this.needyListE[this.eventRectBaseN][1] < this.waveTargetQ || var1 && !this.councilPickedE && this.entityData[6464 + this.waveStepO] == 57) {
                  for (this.pendingBuildP = 3; this.pendingBuildP > this.eventRectBaseN; this.pendingBuildP--) {
                     this.needyListE[this.pendingBuildP][0] = this.needyListE[this.pendingBuildP - 1][0];
                     this.needyListE[this.pendingBuildP][1] = this.needyListE[this.pendingBuildP - 1][1];
                  }

                  this.needyListE[this.eventRectBaseN][0] = (byte)this.waveStepO;
                  this.needyListE[this.eventRectBaseN][1] = (byte)this.waveTargetQ;
                  if (var1 && !this.councilPickedE && this.entityData[6464 + this.waveStepO] == 57) {
                     this.councilPickedE = true;
                  }
                  break;
               }

               var10000 = this;
               var10001 = this.eventRectBaseN;
               var10002 = 1;
            }
         }
      }

      for (this.scratchByteG = 1; this.scratchByteG < 4 && this.needyListE[this.scratchByteG][0] != 0; this.scratchByteG++) {
         this.saveCouncilMember(this.needyListE[this.scratchByteG][0], this.scratchByteG);
         if (this.scratchByteG == 1 && var1) {
            this.entityData[6464 + this.needyListE[this.scratchByteG][0]] = 57;
         }
      }

      this.entityData[8933] = this.scratchByteG;
   }

   // $VF: renamed from: c (int, int) void
   private void saveCouncilMember(int var1, int var2) {
      this.needyListE[var2][0] = (byte)var1;

      for (this.eventRectBaseN = 1; this.eventRectBaseN < 8; this.eventRectBaseN++) {
         this.needyListE[var2][this.eventRectBaseN] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[186] + this.eventRectBaseN]] + var1];
         this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[186] + this.eventRectBaseN]] + var1] = (byte)(
            this.recordBlob[this.recordStarts[186] + this.eventRectBaseN] == 29
               ? this.recordBlob[this.recordStarts[188] + var2]
               : (
                  this.recordBlob[this.recordStarts[186] + this.eventRectBaseN] == 46
                     ? var2 + 1
                     : this.recordBlob[this.recordStarts[187] + this.eventRectBaseN]
               )
         );
      }

      if (var1 != this.tribeStats[0][17] || this.scratchByteH == 0) {
         this.entityData[4545 + var1] = this.entityData[8994];
         this.entityData[8994] = (byte)var1;
      }
   }

   // $VF: renamed from: f () void
   final void restoreCouncil() {
      this.entityData[8933] = this.scratchByteH;

      for (this.waveStepO = this.scratchByteG - 1; this.waveStepO >= 0; this.waveStepO--) {
         for (this.eventRectBaseN = 1; this.eventRectBaseN < 8; this.eventRectBaseN++) {
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[186] + this.eventRectBaseN]] + this.needyListE[this.waveStepO][0]] = this.needyListE[this.waveStepO][this.eventRectBaseN];
         }
      }

      this.entityData[8994] = (byte)(this.scratchByteH > 0 ? this.tribeStats[0][17] : 0);
   }

   // $VF: renamed from: i () boolean
   private boolean tryCompleteTask() {
      if ((this.entityData[2929 + this.currentVillager] & 255) < 8
         || (this.entityData[2929 + this.currentVillager] & 255) > 14
         || this.entityData[2828 + this.currentVillager] == 60
         || this.entityData[2828 + this.currentVillager] == 87) {
         this.taskCompleteF = false;
         if (this.entityData[2828 + this.currentVillager] > 9 && !this.roleFitsTask()) {
            return false;
         }

         if ((this.entityData[2828 + this.currentVillager] < 28 || this.entityData[2828 + this.currentVillager] > 49)
            && this.entityData[2828 + this.currentVillager] != 99) {
            this.applyTaskYield();
            if (this.entityData[2929 + this.currentVillager] == 117) {
               this.shareMood(0);
            }

            if (this.entityData[4646 + this.currentVillager] >= 0) {
               if ((this.recordBlob[this.recordStarts[70] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] & 2) > 0
                  && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                  && (
                     this.entityData[8810 + this.entityData[4444 + this.currentVillager]] == this.currentVillager
                        || (this.recordBlob[this.recordStarts[70] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] & 1) > 0
                  )
                  && this.entityData[1313 + this.currentVillager] > 0) {
                  this.taskCompleteF = this.checkStatRequirements(
                     this.entityData[8749 + this.entityData[4444 + this.currentVillager]],
                     this.entityData[9115 + this.entityData[4444 + this.currentVillager]] - 1
                  );
               }
            } else if ((this.recordBlob[this.recordStarts[48] + this.entityData[2828 + this.currentVillager]] & 2) > 0) {
               this.applyStatChange(
                  this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[46] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[47] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[64] + this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]]]
               );
            }

            if (this.applyTaskEffect()) {
               return true;
            }

            if (this.entityData[2828 + this.currentVillager] == 84 && this.entityData[404 + this.currentVillager] == 100) {
               this.taskCompleteF = true;
            }

            if (this.entityData[2828 + this.currentVillager] == 70 || this.entityData[2828 + this.currentVillager] == 71) {
               this.scratchIntS = (this.entityData[6767 + this.currentVillager] + 12) / 12;
               if (this.entityData[2020 + this.currentVillager] % (100 / this.scratchIntS) <= 4) {
                  this.tribeStats[this.tribeIndex][0]--;
               }
            }

            if (this.flagsScratchR * this.targetYA >= this.recordBlob[this.recordStarts[30] + this.entityData[2828 + this.currentVillager]] * this.targetYA) {
               if (this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]] > 0
                  && this.entityData[2828 + this.currentVillager] > 9) {
                  this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
                     + this.entityData[3838 + this.currentVillager]] = this.recordBlob[this.recordStarts[30] + this.entityData[2828 + this.currentVillager]];
               }

               this.taskCompleteF = true;
            }
         } else {
            this.tryCastSpell(this.entityData[2828 + this.currentVillager], true, this.currentVillager);
         }

         if (this.taskCompleteF) {
            if (this.entityData[2828 + this.currentVillager] == 13 && (this.entityData[2929 + this.currentVillager] & 255) < 145) {
               this.entityData[2929 + this.currentVillager] = -110;
               this.entityData[3636 + this.currentVillager]--;
               this.entityData[404 + this.currentVillager] = 99;
               return false;
            }

            if (this.afterTaskCleanup()) {
               return true;
            }

            this.afterTask();
            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: j () boolean
   private boolean applyTaskEffect() {
      if (this.entityData[2929 + this.currentVillager] == -116
         && this.entityData[404 + this.currentVillager] >= this.recordBlob[this.recordStarts[49] + this.entityData[2828 + this.currentVillager]]) {
         if (this.entityData[2828 + this.currentVillager] >= 94 && this.entityData[2828 + this.currentVillager] <= 97) {
            this.applySpecialTaskEffect();
         } else if (this.entityData[2828 + this.currentVillager] == 55) {
            this.shareMood(1);
            this.entityData[404 + this.currentVillager] = 0;
         }
      } else if (this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]] == 4
         && this.entityData[404 + this.currentVillager] >= this.recordBlob[this.recordStarts[49] + this.entityData[2828 + this.currentVillager]]) {
         if (this.entityData[4646 + this.currentVillager] < 0) {
            if ((this.recordBlob[this.recordStarts[48] + this.entityData[2828 + this.currentVillager]] & 2) == 0) {
               this.applyStatChange(
                  this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[46] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[47] + this.entityData[2828 + this.currentVillager]],
                  this.recordBlob[this.recordStarts[64] + this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]]]
               );
            }
         } else {
            if ((this.recordBlob[this.recordStarts[70] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] & 2) == 0
                  && (
                     this.entityData[8810 + this.entityData[4444 + this.currentVillager]] == this.currentVillager
                        || (this.recordBlob[this.recordStarts[70] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]] & 1) > 0
                  )
               || this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0) {
               this.taskCompleteF = this.checkStatRequirements(
                  this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0
                     ? this.entityData[8749 + this.entityData[4444 + this.currentVillager]]
                     : 16,
                  this.entityData[9115 + this.entityData[4444 + this.currentVillager]] - 1
               );
            }

            if (this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0) {
               this.scratchIntS = 0;

               while (this.scratchIntS < 20 && this.upgradeRegistryG[0][this.scratchIntS] != this.entityData[4444 + this.currentVillager]) {
                  this.scratchIntS++;
               }

               this.upgradeRegistryG[1][this.scratchIntS]++;
            }
         }

         if (this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]] != 13) {
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
               + this.entityData[3838 + this.currentVillager]] = (byte)(
               this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
                     + this.entityData[3838 + this.currentVillager]]
                  + this.targetYA
            );
         }

         this.entityData[404 + this.currentVillager] = 0;
         if (this.entityData[2828 + this.currentVillager] == 111) {
            this.taskCompleteF = true;
         }

         if (this.entityData[2828 + this.currentVillager] == 107) {
            if (this.distOrTargetXZ < 20) {
               this.taskCompleteF = true;
            } else {
               this.entityData[4444 + this.entityData[4444 + this.currentVillager]] = (byte)this.currentVillager;
            }
         }

         if (this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]] == 36
            && this.recordBlob[this.recordStarts[30] + this.entityData[2828 + this.currentVillager]] == 99
            && this.entityData[3636 + this.currentVillager] >= this.entityData[3737 + this.currentVillager]) {
            this.taskCompleteF = true;
         }

         if ((this.entityData[2828 + this.currentVillager] == 11 || this.entityData[2828 + this.currentVillager] == 14)
            && this.entityData[3636 + this.currentVillager] < this.entityData[3737 + this.currentVillager]) {
            this.retargetAfterIdle();
            return true;
         }

         if (this.entityData[2828 + this.currentVillager] == 10
            || this.entityData[2828 + this.currentVillager] == 15
            || this.entityData[2828 + this.currentVillager] == 12
            || this.entityData[2828 + this.currentVillager] == 16
            || this.entityData[2828 + this.currentVillager] == 13) {
            this.entityData[9883 + this.entityData[4444 + this.currentVillager]]--;
            if (this.entityData[9883 + this.entityData[4444 + this.currentVillager]] < 0) {
               this.entityData[9883 + this.entityData[4444 + this.currentVillager]] = 0;
            }
         } else if (this.entityData[2828 + this.currentVillager] == 54) {
            this.tribeStats[this.tribeIndex][0]++;
         }
      }

      return false;
   }

   // $VF: renamed from: k () boolean
   private boolean afterTaskCleanup() {
      if (this.entityData[2828 + this.currentVillager] <= 9) {
         if (this.entityData[6565 + this.currentVillager] == 9
            && (this.entityData[2828 + this.currentVillager] == 0 || this.entityData[2828 + this.currentVillager] == 3)) {
            this.entityData[2828 + this.currentVillager] = (byte)(this.entityData[2828 + this.currentVillager] ^ 1);
         }

         this.searchOkA = this.searchFlagB = true;
         if (this.findNewTargetTile()) {
            return true;
         }
      }

      if (this.entityData[2828 + this.currentVillager] == 26 || this.entityData[2828 + this.currentVillager] == 27) {
         this.completeRitualStep(true);
      }

      if (this.entityData[2828 + this.currentVillager] >= 94 && this.entityData[2828 + this.currentVillager] <= 97) {
         this.clearSanctuaryQueue();
      }

      if (this.entityData[2828 + this.currentVillager] == 50) {
         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 191);
         this.assignProfession();
      }

      if (this.entityData[2828 + this.currentVillager] == 111) {
         this.entityData[3636 + this.currentVillager] = 0;
         this.updateMating();
      }

      if (this.entityData[2828 + this.currentVillager] == 109) {
         this.clearMatingPair(this.currentVillager, 0);
      }

      if (this.entityData[2828 + this.currentVillager] == 110) {
         this.entityData[3535 + this.currentVillager] = 15;
      }

      if (this.entityData[2828 + this.currentVillager] == 107) {
         this.captureVillager(this.entityData[4444 + this.currentVillager]);
      }

      if (this.entityData[2828 + this.currentVillager] == 79) {
         this.entityData[3333 + this.currentVillager] = -1;
      }

      if (this.entityData[2828 + this.currentVillager] <= 4
         && this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager]]
            == this.entityData[4747 + this.currentVillager]
         && this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
               + this.entityData[4444 + this.currentVillager]]
            == 0) {
         this.resolveTileEvent(
            this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]],
            this.entityData[3131 + this.currentVillager],
            this.entityData[3232 + this.currentVillager],
            this.entityData[4444 + this.currentVillager]
         );
      }

      if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 8) != 0 && this.tryFindNeedTarget()) {
         return true;
      }

      if (this.entityData[2828 + this.currentVillager] == 60) {
         this.setTask(this.currentVillager, (byte)12);
         if (this.findNearestResourceNode(true)) {
            return true;
         }

         this.setTask(this.currentVillager, (byte)60);
      }

      if (this.entityData[1414 + this.currentVillager] < 11 && this.entityData[2828 + this.currentVillager] == 17 && this.findNearestBuilding(10)) {
         this.setTask(this.currentVillager, (byte)73);
         this.moveVillagerStep(1);
         return true;
      } else {
         return false;
      }
   }

   // $VF: renamed from: I () void
   private void afterTask() {
      if (this.entityData[2828 + this.currentVillager] >= 83
         && this.entityData[3939 + this.currentVillager] >= 44
         && this.entityData[3939 + this.currentVillager] <= 48) {
         this.entityData[3535 + this.currentVillager] = (byte)(this.entityData[3939 + this.currentVillager] - 44 + 8);
      }

      if (this.entityData[2828 + this.currentVillager] == 85) {
         if (this.entityData[6161 + this.currentVillager] >= 2) {
            this.entityData[3939 + this.currentVillager] = 69;
         }

         this.setTask(this.currentVillager, this.entityData[3939 + this.currentVillager]);
         if (this.entityData[2828 + this.currentVillager] >= 44 && this.entityData[2828 + this.currentVillager] <= 48) {
            this.entityData[3535 + this.currentVillager] = 0;
         }

         this.entityData[3939 + this.currentVillager] = 69;
         this.entityData[1313 + this.currentVillager] = 0;
         this.entityData[404 + this.currentVillager] = 0;
      } else {
         this.setTask(
            this.currentVillager,
            this.entityData[2828 + this.currentVillager] >= 19
                  && this.entityData[2828 + this.currentVillager] <= 26
                  && this.entityData[6464 + this.currentVillager] == 69
               ? 69
               : this.recordBlob[this.recordStarts[160] + this.entityData[2828 + this.currentVillager]]
         );
      }

      if (this.entityData[2828 + this.currentVillager] == 26 || this.entityData[2828 + this.currentVillager] == 27) {
         this.completeRitualStep(false);
      }

      if (this.entityData[6161 + this.currentVillager] < 2
         && (
            this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] == 2
               || this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
         )) {
         this.retargetAfterIdle();
      }

      if (this.entityData[2828 + this.currentVillager] == 16) {
         this.findGatherTarget();
      }
   }

   // $VF: renamed from: J () void
   private void applyTaskYield() {
      this.targetYA = 1;
      if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 32) == 0) {
         this.targetYA = -1;
      }

      this.scratchIntC = (
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[35] + this.entityData[2828 + this.currentVillager]]] + this.currentVillager]
               + this.recordBlob[this.recordStarts[36] + this.entityData[2828 + this.currentVillager]]
         )
         * this.targetYA;
      if (this.recordBlob[this.recordStarts[35] + this.entityData[2828 + this.currentVillager]] >= 69
         && this.recordBlob[this.recordStarts[35] + this.entityData[2828 + this.currentVillager]] <= 74
         && this.tribeIndex == 0
         && this.entityData[0 + this.currentVillager] - this.entityData[0 + this.tribeStats[0][17]] >= -4
         && this.entityData[0 + this.currentVillager] - this.entityData[0 + this.tribeStats[0][17]] <= 4
         && this.entityData[101 + this.currentVillager] - this.entityData[101 + this.tribeStats[0][17]] >= -4
         && this.entityData[101 + this.currentVillager] - this.entityData[101 + this.tribeStats[0][17]] <= 4) {
         this.scratchIntC = this.scratchIntC * 3 / 2;
      }

      if (this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]] <= 0) {
         this.tribeStats[this.tribeIndex][-this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]]] = (short)(
            this.tribeStats[this.tribeIndex][-this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]]] + this.scratchIntC
         );
      } else {
         this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]]] + this.currentVillager] = (byte)(
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[33] + this.entityData[2828 + this.currentVillager]]] + this.currentVillager]
               + this.scratchIntC
         );
      }

      this.targetYA = 1;
      if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 16) == 0) {
         this.targetYA = -1;
      }

      this.scratchIntC = this.recordBlob[this.recordStarts[34] + this.entityData[2828 + this.currentVillager]] >= 0
         ? this.recordBlob[this.recordStarts[34] + this.entityData[2828 + this.currentVillager]]
         : this.entityData[this.fieldOffsets[-this.recordBlob[this.recordStarts[34] + this.entityData[2828 + this.currentVillager]]] + this.currentVillager];
      this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
         + this.entityData[3838 + this.currentVillager]] = (byte)(
         this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
               + this.entityData[3838 + this.currentVillager]]
            + this.scratchIntC * this.targetYA
      );
      this.flagsScratchR = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[31] + this.entityData[2828 + this.currentVillager]]]
         + this.entityData[3838 + this.currentVillager]];
   }

   // $VF: renamed from: K () void
   private void applySpecialTaskEffect() {
      this.entityData[404 + this.currentVillager] = 0;
      this.applyStatChange(
         this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]],
         this.recordBlob[this.recordStarts[46] + this.entityData[2828 + this.currentVillager]],
         this.recordBlob[this.recordStarts[47] + this.entityData[2828 + this.currentVillager]],
         this.recordBlob[this.recordStarts[64] + this.recordBlob[this.recordStarts[45] + this.entityData[2828 + this.currentVillager]]]
      );
      this.taskCompleteF = true;
      switch (this.entityData[2828 + this.currentVillager]) {
         case 94:
            this.taskCompleteF = this.adjustHealth(this.entityData[4444 + this.currentVillager], 10);
            return;
         case 95:
            this.entityData[8181 + this.entityData[4444 + this.currentVillager]] = 0;
            this.entityData[7979 + this.entityData[4444 + this.currentVillager]] = (byte)(
               this.entityData[7979 + this.entityData[4444 + this.currentVillager]] | 8
            );
            return;
         case 96:
            this.entityData[505 + this.flagsScratchR] = 99;
            this.flagsScratchR = this.currentVillager;
            this.currentVillager = this.entityData[4444 + this.currentVillager];
            if ((this.entityData[8282 + this.currentVillager] & 127) >> 1 == 0) {
               this.tryFindMate(true);
            }

            this.currentVillager = this.flagsScratchR;
            return;
         case 97:
            for (this.flagsScratchR = 3; this.flagsScratchR >= 0; this.flagsScratchR--) {
               if (this.game.aiTargets[this.tribeIndex][this.flagsScratchR][2] >= 0) {
                  for (this.scratchIntS = 0; this.scratchIntS < 4; this.scratchIntS++) {
                     this.game.aiTargets[this.tribeIndex][this.flagsScratchR + 1][this.scratchIntS] = this.game.aiTargets[this.tribeIndex][this.flagsScratchR][this.scratchIntS];
                  }
               }
            }

            this.game.aiTargets[this.tribeIndex][0][0] = this.entityData[3131 + this.currentVillager];
            this.game.aiTargets[this.tribeIndex][0][1] = this.entityData[3232 + this.currentVillager];
            this.game.aiTargets[this.tribeIndex][0][2] = 0;
            this.game.aiTargets[this.tribeIndex][0][3] = (byte)(this.tribeIndex ^ 1);
      }
   }

   // $VF: renamed from: a (int, int) boolean
   private boolean adjustHealth(int var1, int var2) {
      if (var2 > 1) {
         this.entityData[8080 + var1] = -100;
         this.entityData[7979 + var1] = (byte)(this.entityData[7979 + var1] & 251);
      }

      if (this.entityData[1919 + var1] >= this.entityData[6767 + var1] && var2 > 0) {
         return true;
      }

      if (this.tribeIndex == 0 || var2 < 0) {
         this.game.textLoader.pendingDeltas[var1] = (byte)(this.game.textLoader.pendingDeltas[var1] + var2);
      }

      this.entityData[1919 + var1] = (byte)clamp(this.entityData[1919 + var1] + var2, 0, this.entityData[6767 + var1]);
      return this.entityData[1919 + var1] >= this.entityData[6767 + var1];
   }

   // $VF: renamed from: c (byte) void
   private void captureVillager(byte var1) {
      for (this.flagsScratchR = this.tribeIndex * 50 + 1; this.flagsScratchR <= (this.tribeIndex + 1) * 50; this.flagsScratchR++) {
         if (this.entityData[1919 + this.flagsScratchR] == 0) {
            if (this.tribeIndex == 1) {
               this.game.removeFromGroups(var1);
               this.game.f_int_void(var1);
               this.showMessage((byte)17, this.tribeStats[0][17], var1);
            }

            this.entityData[4444 + var1] = 0;

            for (this.scratchIntS = 0; this.scratchIntS < 83; this.scratchIntS++) {
               this.entityData[this.fieldOffsets[this.scratchIntS] + this.flagsScratchR] = this.entityData[this.fieldOffsets[this.scratchIntS] + var1];
            }

            for (this.scratchIntS = 0; this.scratchIntS < 80; this.scratchIntS++) {
               this.villagerPaths[0][this.flagsScratchR][this.scratchIntS] = this.villagerPaths[0][var1][this.scratchIntS];
               this.villagerPaths[1][this.flagsScratchR][this.scratchIntS] = this.villagerPaths[1][var1][this.scratchIntS];
            }

            this.tribeStats[this.tribeIndex][11]++;
            this.tribeStats[this.tribeIndex ^ 1][11]--;
            this.tribeStats[this.tribeIndex][12 + this.entityData[1515 + this.flagsScratchR]]++;
            this.tribeStats[this.tribeIndex ^ 1][12 + this.entityData[1515 + this.flagsScratchR]]--;
            this.entityData[1919 + var1] = 0;
            this.occupancyGrid[this.entityData[101 + this.flagsScratchR]][this.entityData[0 + this.flagsScratchR]] = (byte)this.flagsScratchR;
            this.game.b_byte_void((byte)this.flagsScratchR);
            this.game.g_int_void((byte)this.flagsScratchR);
            return;
         }
      }
   }

   // $VF: renamed from: j (int) void
   private void shareMood(int var1) {
      for (this.scratchIntS = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];
         this.scratchIntS > 0;
         this.scratchIntS = this.entityData[4545 + this.scratchIntS]
      ) {
         if (this.scratchIntS != this.currentVillager) {
            if (var1 == 0) {
               this.entityData[505 + this.scratchIntS] = (byte)clamp(
                  this.entityData[505 + this.scratchIntS] + (this.entityData[7272 + this.currentVillager] + 2) / 2, 0, 99
               );
               this.entityData[2121 + this.scratchIntS] = (byte)clamp(
                  this.entityData[2121 + this.scratchIntS] + (this.entityData[7272 + this.currentVillager] + 2) / 2, 0, 99
               );
            } else {
               this.adjustHealth(this.scratchIntS, 10);
               if (this.entityData[1919 + this.scratchIntS] >= this.entityData[6767 + this.scratchIntS]) {
                  this.entityData[2828 + this.scratchIntS] = 87;
                  this.entityData[6161 + this.scratchIntS] = 3;
               }
            }
         }
      }
   }

   // $VF: renamed from: l () boolean
   private boolean roleFitsTask() {
      for (this.targetYA = this.recordBlob[this.recordStarts[166] + this.entityData[2828 + this.currentVillager]] & 255;
         this.targetYA < (this.recordBlob[this.recordStarts[166] + this.entityData[2828 + this.currentVillager] + 1] & 255);
         this.targetYA++
      ) {
         if (this.entityData[2929 + this.currentVillager] == this.recordBlob[this.recordStarts[164] + this.targetYA]) {
            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: b (int, int) boolean
   private boolean checkStatRequirements(int var1, int var2) {
      this.allReqsMetG = true;

      for (this.reqIndexT = this.recordBlob[this.recordStarts[69] + var1 * 4 + var2];
         this.reqIndexT < this.recordBlob[this.recordStarts[69] + var1 * 4 + var2 + 1];
         this.reqIndexT++
      ) {
         if (!this.applyStatChange(
            this.recordBlob[this.recordStarts[65] + this.recordBlob[this.recordStarts[68] + var1] + this.reqIndexT],
            this.recordBlob[this.recordStarts[66] + this.recordBlob[this.recordStarts[68] + var1] + this.reqIndexT],
            this.recordBlob[this.recordStarts[67] + this.recordBlob[this.recordStarts[68] + var1] + this.reqIndexT],
            this.recordBlob[this.recordStarts[63] + this.recordBlob[this.recordStarts[68] + var1] + this.reqIndexT]
         )) {
            this.allReqsMetG = false;
         }
      }

      return this.allReqsMetG;
   }

   // $VF: renamed from: a (byte, byte, byte, byte) boolean
   private boolean applyStatChange(byte var1, byte var2, byte var3, byte var4) {
      this.currentStatU = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[44] + var1]] + this.currentVillager];
      if (this.game.randRange(16777215, 100) < var2 && this.currentStatU * var4 < var3 * var4) {
         this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[44] + var1]] + this.currentVillager] = (byte)(
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[44] + var1]] + this.currentVillager] + var4
         );
         if (var1 == 4) {
            if (this.entityData[6767 + this.currentVillager] >= this.recordBlob[this.recordStarts[98] + this.entityData[1414 + this.currentVillager]]) {
               this.entityData[6767 + this.currentVillager] = this.recordBlob[this.recordStarts[98] + this.entityData[1414 + this.currentVillager]];
            }

            this.entityData[1919 + this.currentVillager] = (byte)(
               this.entityData[1919 + this.currentVillager] + (this.entityData[6767 + this.currentVillager] - this.currentStatU)
            );
            return true;
         }
      }

      return this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[44] + var1]] + this.currentVillager] * var4 >= var3 * var4 && var2 >= 100;
   }

   // $VF: renamed from: a (int, boolean, int) boolean
   private boolean tryCastSpell(int var1, boolean var2, int var3) {
      this.scratchIntS = var1 - 28;
      this.taskCompleteF = this.checkSpellCost(this.scratchIntS, var2, var3);
      if (!this.taskCompleteF) {
         if (var2) {
            this.startCombatStance();
            this.retargetAfterIdle();
         }

         return false;
      } else {
         int var4 = var1 == 37 ? clamp(9, 1, this.tribeStats[this.tribeIndex][1]) : 0;
         if (var1 != 99) {
            for (this.flagsScratchR = this.recordBlob[this.recordStarts[73] + this.scratchIntS];
               this.flagsScratchR < this.recordBlob[this.recordStarts[73] + this.scratchIntS + 1];
               this.flagsScratchR++
            ) {
               this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[71] + this.flagsScratchR]] = (short)(
                  this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[71] + this.flagsScratchR]]
                     - (var1 == 37 ? var4 : this.recordBlob[this.recordStarts[72] + this.flagsScratchR])
               );
            }
         } else {
            if (this.entityData[8627 + this.workTargetW] == 2) {
               for (this.flagsScratchR = 0; this.flagsScratchR < 3; this.flagsScratchR++) {
                  this.tribeStats[this.tribeIndex][2 + this.flagsScratchR] = (short)(
                     this.tribeStats[this.tribeIndex][2 + this.flagsScratchR]
                        - this.recordBlob[this.recordStarts[138] + (10 + this.flagsScratchR) * 26 + this.entityData[8749 + this.workTargetW]]
                           * this.hpMissingV
                           / (this.entityData[8566 + this.workTargetW] * 2)
                  );
               }
            } else {
               for (this.flagsScratchR = 0; this.flagsScratchR < 3; this.flagsScratchR++) {
                  this.tribeStats[this.tribeIndex][2 + this.flagsScratchR] = (short)(
                     this.tribeStats[this.tribeIndex][2 + this.flagsScratchR]
                        - this.recordBlob[this.recordStarts[138] + (13 + this.flagsScratchR) * 26 + this.entityData[8749 + this.workTargetW]]
                  );
               }
            }

            for (this.capacityAK = 0; this.capacityAK < 20; this.capacityAK++) {
               if (this.upgradeRegistryG[0][this.capacityAK] == this.workTargetW) {
                  this.upgradeRegistryG[2][this.capacityAK] = (byte)(
                     this.entityData[8627 + this.workTargetW] == 2
                        ? -this.hpMissingV
                        : this.recordBlob[this.recordStarts[138]
                              + (7 + this.entityData[9115 + this.workTargetW] - 1) * 26
                              + this.entityData[8749 + this.workTargetW]]
                           - this.recordBlob[this.recordStarts[138]
                              + (7 + this.entityData[9115 + this.workTargetW]) * 26
                              + this.entityData[8749 + this.workTargetW]]
                  );
                  if (!var2) {
                     this.upgradeRegistryG[2][this.capacityAK] = (byte)(this.upgradeRegistryG[2][this.capacityAK] * -1);
                  }
                  break;
               }
            }
         }

         if (var2) {
            if (this.entityData[2828 + var3] == 99) {
               this.entityData[3636 + var3] = 9;
               this.entityData[3535 + var3] = 2;
            } else if (this.entityData[2828 + var3] >= 44) {
               if (this.entityData[6565 + var3] <= 5) {
                  this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.entityData[6565 + var3]]] = (short)(
                     this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.entityData[6565 + var3]]]
                        + this.recordBlob[this.recordStarts[105] + this.entityData[6565 + var3]]
                  );
               }

               this.entityData[6565 + var3] = (byte)(this.entityData[2828 + var3] - 44);
               this.game.refreshPanelIfMenuTarget(this.entityData[4444 + var3]);
            } else {
               this.entityData[3636 + var3] = (byte)(var1 == 37 ? var4 : this.recordBlob[this.recordStarts[75] + this.scratchIntS]);
               this.entityData[3535 + var3] = this.recordBlob[this.recordStarts[74] + this.scratchIntS];
            }
         }

         return true;
      }
   }

   // $VF: renamed from: b (int, boolean, int) boolean
   private boolean checkSpellCost(int var1, boolean var2, int var3) {
      if (var1 == 71) {
         this.workTargetW = var2 ? -this.occupancyGrid[this.entityData[6363 + var3]][this.entityData[6262 + var3]] : this.currentVillager;
         if (this.entityData[8627 + this.workTargetW] == 2) {
            this.hpMissingV = this.entityData[8566 + this.workTargetW] - this.entityData[8505 + this.workTargetW];

            for (this.flagsScratchR = 0; this.flagsScratchR < 3; this.flagsScratchR++) {
               if (this.tribeStats[this.tribeIndex][2 + this.flagsScratchR]
                  < this.recordBlob[this.recordStarts[138] + (10 + this.flagsScratchR) * 26 + this.entityData[8749 + this.workTargetW]]
                     * this.hpMissingV
                     / (this.entityData[8566 + this.workTargetW] * 2)) {
                  this.entityData[3535 + var3] = this.recordBlob[this.recordStarts[76] + 2 + this.flagsScratchR];
                  if (this.messageCooldowns[1] < 0 && this.tribeIndex == 0) {
                     this.messageCooldowns[1] = 0;
                     this.game.postMessage((byte)9, (byte)0, (byte)0, (byte)0);
                  }

                  return false;
               }
            }

            return true;
         } else {
            for (this.flagsScratchR = 0; this.flagsScratchR < 3; this.flagsScratchR++) {
               if (this.tribeStats[this.tribeIndex][2 + this.flagsScratchR]
                  < this.recordBlob[this.recordStarts[138] + (13 + this.flagsScratchR) * 26 + this.entityData[8749 + this.workTargetW]]) {
                  this.entityData[3535 + var3] = this.recordBlob[this.recordStarts[76] + 2 + this.flagsScratchR];
                  if (this.messageCooldowns[1] < 0 && this.tribeIndex == 0) {
                     this.messageCooldowns[1] = 0;
                     this.game.postMessage((byte)9, (byte)0, (byte)0, (byte)0);
                  }

                  return false;
               }
            }

            return true;
         }
      } else {
         for (this.flagsScratchR = this.recordBlob[this.recordStarts[73] + var1];
            this.flagsScratchR < this.recordBlob[this.recordStarts[73] + var1 + 1];
            this.flagsScratchR++
         ) {
            if (this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[71] + this.flagsScratchR]]
                  < this.recordBlob[this.recordStarts[72] + this.flagsScratchR]
               && this.entityData[6565 + var3] != var1 - 16) {
               if (var1 < 16) {
                  this.entityData[3535 + var3] = this.recordBlob[this.recordStarts[76] + this.recordBlob[this.recordStarts[71] + this.flagsScratchR]];
                  if (this.messageCooldowns[1] < 0 && this.tribeIndex == 0) {
                     this.messageCooldowns[1] = 0;
                     this.game.postMessage((byte)9, (byte)0, (byte)0, (byte)0);
                  }
               }

               return false;
            }
         }

         return true;
      }
   }

   // $VF: renamed from: L () void
   private void assignProfession() {
      this.needRangeStartX = this.entityData[1717 + this.currentVillager] < this.entityData[1616 + this.currentVillager] ? 3 : 0;

      for (this.needIndexY = this.needRangeStartX; this.needIndexY < 6 + this.needRangeStartX; this.needIndexY++) {
         if (this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.recordBlob[this.recordStarts[103] + this.needIndexY]]]
            >= this.recordBlob[this.recordStarts[104] + this.needIndexY]) {
            this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.recordBlob[this.recordStarts[103] + this.needIndexY]]] = (short)(
               this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.recordBlob[this.recordStarts[103] + this.needIndexY]]]
                  - this.recordBlob[this.recordStarts[104] + this.needIndexY]
            );
            this.entityData[6565 + this.currentVillager] = this.recordBlob[this.recordStarts[103] + this.needIndexY];
            this.game.refreshPanelIfMenuTarget(this.entityData[4444 + this.currentVillager]);
            return;
         }
      }
   }

   // $VF: renamed from: M () void
   private void startCombatStance() {
      this.entityData[3939 + this.currentVillager] = this.entityData[2828 + this.currentVillager];
      this.setTask(this.currentVillager, (byte)83);
      this.flagsScratchR = this.recordBlob[this.recordStarts[169] + this.entityData[3939 + this.currentVillager]];
      this.entityData[1313 + this.currentVillager] = (byte)(
         this.recordBlob[this.recordStarts[168] + 0 + this.flagsScratchR]
            + this.game
               .randRange(
                  16777215,
                  this.recordBlob[this.recordStarts[168] + 2 + this.flagsScratchR] - this.recordBlob[this.recordStarts[168] + 0 + this.flagsScratchR] + 1
               )
      );
   }

   // $VF: renamed from: N () void
   private void retargetAfterIdle() {
      if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
         if (this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 2 && this.entityData[4646 + this.currentVillager] == 0) {
            this.scratchIntZ = this.entityData[8993 + this.entityData[4444 + this.currentVillager]];
            if (this.scratchIntZ == this.currentVillager) {
               this.scratchIntZ = this.entityData[4545 + this.currentVillager];
            }

            if (this.scratchIntZ > 0) {
               this.entityData[4646 + this.currentVillager] = this.entityData[4646 + this.scratchIntZ];
               this.entityData[4646 + this.scratchIntZ] = 0;
            }
         }

         this.moveVillagerStep(2);
      } else if (this.entityData[2828 + this.currentVillager] == 17
         || this.entityData[2828 + this.currentVillager] == 60
         || this.entityData[2828 + this.currentVillager] == 83
         || (this.entityData[2828 + this.currentVillager] == 11 || this.entityData[2828 + this.currentVillager] == 14)
            && this.entityData[3636 + this.currentVillager] > 0) {
         this.entityData[1313 + this.currentVillager] = 0;
         if (this.entityData[2828 + this.currentVillager] == 17
            && this.entityData[4444 + this.currentVillager] > (this.tribeIndex ^ 1) * 50
            && this.entityData[4444 + this.currentVillager] <= ((this.tribeIndex ^ 1) + 1) * 50) {
            if (this.entityData[2828 + this.entityData[4444 + this.currentVillager]] == 107) {
               this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.entityData[4444 + this.currentVillager]];
               this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.entityData[4444 + this.currentVillager]];
            } else {
               this.entityData[4444 + this.currentVillager] = 0;
            }
         } else {
            if (this.entityData[2828 + this.currentVillager] == 17
               || this.entityData[2828 + this.currentVillager] == 60
               || this.entityData[2828 + this.currentVillager] == 83) {
               this.entityData[4747 + this.currentVillager] = 0;
            }

            this.spiralSearchTile(
               0,
               this.entityData[4949 + this.currentVillager] * 2 + 1,
               clamp(this.entityData[0 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapWidth - 1),
               clamp(this.entityData[101 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapHeight - 1)
            );
         }
      } else if (this.entityData[2828 + this.currentVillager] == 109) {
         this.matePairIndexAe = ((this.entityData[8282 + this.currentVillager] & 127) >> 1) - 1;
         this.spiralSearchTile(3, 127, this.matingPairs[2][this.matePairIndexAe], this.matingPairs[3][this.matePairIndexAe]);
         this.moveVillagerStep(3);
      } else if (this.entityData[2828 + this.currentVillager] == 110) {
         if (!this.spiralSearchTile(
            4, this.entityData[4949 + this.currentVillager] * 2 + 1, this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager]
         )) {
            this.setTask(this.currentVillager, (byte)109);
            this.matePairIndexAe = ((this.entityData[8282 + this.currentVillager] & 127) >> 1) - 1;
            this.spiralSearchTile(3, 127, this.matingPairs[2][this.matePairIndexAe], this.matingPairs[3][this.matePairIndexAe]);
         }

         this.moveVillagerStep(3);
      } else {
         this.pickNextDestination();
      }
   }

   // $VF: renamed from: O () void
   private void pickNextDestination() {
      if (this.entityData[2828 + this.currentVillager] == 69
         || this.entityData[2828 + this.currentVillager] == 80
         || this.entityData[2828 + this.currentVillager] == 84
         || this.entityData[2828 + this.currentVillager] == 85) {
         this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
         this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
      } else if (this.entityData[2828 + this.currentVillager] >= 94 && this.entityData[2828 + this.currentVillager] <= 97) {
         this.queuePosAO = 0;

         while (this.queuePosAO < 30 && this.game.pendingOrders[this.tribeIndex][0][this.queuePosAO] != this.entityData[4040 + this.currentVillager]) {
            this.queuePosAO++;
         }

         if (this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO] < 0) {
            this.leaveSanctuary();
         } else if (this.entityData[2828 + this.currentVillager] != 97) {
            this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO]];
            this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO]];
            this.entityData[4444 + this.currentVillager] = this.entityData[4747 + this.currentVillager] = this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO];
         } else {
            this.entityData[3131 + this.currentVillager] = this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO];
            this.entityData[3232 + this.currentVillager] = this.game.pendingOrders[this.tribeIndex][3][this.queuePosAO];
            this.moveVillagerStep(2);
         }
      } else {
         if (this.entityData[2828 + this.currentVillager] == 27) {
            for (this.queueScanAN = 0; this.queueScanAN < 10; this.queueScanAN++) {
               this.queueBuildingAP = this.game.buildingStores[this.tribeIndex][this.queueScanAN][11];
               if (this.queueBuildingAP > 0 && this.currentVillager == this.entityData[8810 + this.queueBuildingAP]) {
                  for (this.queuePosAO = 0; this.queuePosAO < 30; this.queuePosAO++) {
                     if (this.game.trapTable[0][this.queuePosAO] == this.queueBuildingAP
                        && this.game.trapTable[1][this.queuePosAO] == this.game.buildingStores[this.tribeIndex][this.queueScanAN][10] + 14) {
                        this.entityData[3131 + this.currentVillager] = this.game.trapTable[2][this.queuePosAO];
                        this.entityData[3232 + this.currentVillager] = this.game.trapTable[3][this.queuePosAO];
                        this.moveVillagerStep(2);
                        return;
                     }
                  }

                  return;
               }
            }
         } else {
            if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 64) > 0) {
               if (this.entityData[2828 + this.currentVillager] != 65) {
                  this.entityData[3131 + this.currentVillager] = this.entityData[6262 + this.currentVillager];
                  this.entityData[3232 + this.currentVillager] = this.entityData[6363 + this.currentVillager];
               } else {
                  this.entityData[3131 + this.currentVillager] = this.entityData[8383 + this.entityData[3434 + this.currentVillager]];
                  this.entityData[3232 + this.currentVillager] = this.entityData[8444 + this.entityData[3434 + this.currentVillager]];
               }

               this.moveVillagerStep(2);
               return;
            }

            if (this.findNearestBuilding(6)) {
               this.moveVillagerStep(2);
               return;
            }

            this.entityData[4444 + this.currentVillager] = -6;
         }
      }
   }

   // $VF: renamed from: a (int) boolean
   final boolean findNearestBuilding(int var1) {
      this.bestDistSqAa = 1000000;
      this.foundBuildingH = false;
      boolean var2 = var1 == 6;

      for (this.scratchIntZ = this.tribeIndex * 30 + 1; this.scratchIntZ <= (this.tribeIndex + 1) * 30; this.scratchIntZ++) {
         if (this.entityData[8505 + this.scratchIntZ] > 0
            && (
               this.entityData[8749 + this.scratchIntZ] == var1
                  || var2
                     && this.entityData[8749 + this.scratchIntZ] == 16
                     && this.entityData[9237 + this.scratchIntZ] == 6
                     && this.entityData[2828 + this.currentVillager] <= 25
            )) {
            this.foundBuildingH = true;
            if ((this.entityData[8627 + this.scratchIntZ] == 0 || var1 == 6)
               && (this.recordBlob[this.recordStarts[138] + 156 + var1] < 0 || this.entityData[8810 + this.scratchIntZ] >= 1)
               && (
                  this.entityData[8932 + this.scratchIntZ] <= 0
                     || this.entityData[8932 + this.scratchIntZ]
                        < this.recordBlob[this.recordStarts[138] + (this.entityData[9115 + this.scratchIntZ] - 1) * 26 + var1]
                     || var1 == 6
               )) {
               this.candDistSqAb = (
                        this.entityData[8383 + this.scratchIntZ]
                           + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + this.scratchIntZ]] / 2
                           - this.entityData[0 + this.currentVillager]
                     )
                     * (
                        this.entityData[8383 + this.scratchIntZ]
                           + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + this.scratchIntZ]] / 2
                           - this.entityData[0 + this.currentVillager]
                     )
                  + (
                        this.entityData[8444 + this.scratchIntZ]
                           + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + this.scratchIntZ]] / 2
                           - this.entityData[101 + this.currentVillager]
                     )
                     * (
                        this.entityData[8444 + this.scratchIntZ]
                           + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + this.scratchIntZ]] / 2
                           - this.entityData[101 + this.currentVillager]
                     );
               if (this.bestDistSqAa > this.candDistSqAb || var2 && this.entityData[8749 + this.scratchIntZ] == var1) {
                  var2 = this.entityData[8749 + this.scratchIntZ] != var1;
                  this.bestDistSqAa = this.candDistSqAb;
                  this.nearestBuildingAc = this.scratchIntZ;
               }
            }
         }
      }

      if (this.bestDistSqAa < 1000000) {
         this.entityData[3131 + this.currentVillager] = (byte)clamp(
            this.entityData[0 + this.currentVillager],
            this.entityData[8383 + this.nearestBuildingAc],
            this.entityData[8383 + this.nearestBuildingAc] + this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + this.nearestBuildingAc]] - 1
         );
         this.entityData[3232 + this.currentVillager] = (byte)clamp(
            this.entityData[101 + this.currentVillager],
            this.entityData[8444 + this.nearestBuildingAc],
            this.entityData[8444 + this.nearestBuildingAc] + this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + this.nearestBuildingAc]] - 1
         );
         return true;
      } else {
         this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
         this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
         return false;
      }
   }

   // $VF: renamed from: m () boolean
   private boolean checkCallToArms() {
      if (this.tribeIndex == 1) {
         if ((this.entityData[7979 + this.currentVillager] & 32) != 0 && this.waveTargetQByte >= 1) {
            if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
               this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.waveTargetQByte];
               this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.waveTargetQByte];
               this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 223);
               this.setTask(this.currentVillager, (byte)69);
            } else {
               this.entityData[2828 + this.currentVillager] = 87;
            }

            this.entityData[6161 + this.currentVillager] = 3;
            return true;
         } else {
            return false;
         }
      } else if ((this.entityData[7979 + this.currentVillager] & 32) != 0
         || this.game.placeConfirmFlagN
            && this.entityData[3131 + this.currentVillager] == this.entityData[0 + this.currentVillager]
            && this.entityData[3232 + this.currentVillager] == this.entityData[101 + this.currentVillager]
            && (this.game.tickCounter & 31) <= this.entityData[1010 + this.currentVillager]) {
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            if (this.game.placeConfirmFlagN && (this.entityData[7979 + this.currentVillager] & 64) != 0) {
               this.entityData[3636 + this.currentVillager] = 0;
               if (this.entityData[2828 + this.currentVillager] != 50) {
                  if (this.findNearestBuilding(6) && this.entityData[6565 + this.currentVillager] == 9) {
                     this.setTask(this.currentVillager, (byte)50);
                     this.entityData[6161 + this.currentVillager] = 3;
                     this.entityData[7777 + this.currentVillager] = 3;
                     return true;
                  }

                  this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 191);
               }
            } else {
               this.entityData[3131 + this.currentVillager] = this.game.homePosition[0];
               this.entityData[3232 + this.currentVillager] = this.game.homePosition[1];
               this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 223);
               if (this.entityData[0 + this.currentVillager] != this.entityData[3131 + this.currentVillager]
                  || this.entityData[101 + this.currentVillager] != this.entityData[3232 + this.currentVillager]) {
                  this.setTask(this.currentVillager, (byte)69);
                  this.entityData[6161 + this.currentVillager] = 3;
                  this.entityData[7777 + this.currentVillager] = 3;
                  return true;
               }
            }
         } else if ((
               this.game.placeConfirmFlagN
                  || this.entityData[2020 + this.currentVillager] > 30
                  || this.entityData[8749 + this.entityData[4444 + this.currentVillager]] != 4
            )
            && this.entityData[1313 + this.currentVillager] > 0) {
            this.entityData[2828 + this.currentVillager] = 87;
            this.entityData[6161 + this.currentVillager] = 3;
            return true;
         }

         return false;
      } else {
         return false;
      }
   }

   // $VF: renamed from: n () boolean
   private boolean tryFindNeedTarget() {
      if (this.game.missionIndex != 3
         && !this.game.placeConfirmFlagN
         && (!this.game.placeConfirmFlagL && this.tribeIndex != 1 || (this.entityData[7979 + this.currentVillager] & 64) == 0)
         && this.entityData[1414 + this.currentVillager] >= 12) {
         if (this.tribeIndex == 0) {
            if (this.updateMating()) {
               return true;
            }

            if (this.tryFindMate(false)) {
               return true;
            }
         }

         if (this.recordBlob[this.recordStarts[50] + this.entityData[2828 + this.currentVillager]] == 127
            || this.recordBlob[this.recordStarts[52] + this.entityData[2828 + this.currentVillager]] == 127) {
            return false;
         }

         if (this.entityData[2121 + this.currentVillager] <= (this.entityData[505 + this.currentVillager] > 5 ? 20 : 50)) {
            if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
               this.setTask(this.currentVillager, (byte)87);
               this.moveVillagerStep(1);
            } else {
               this.randomRollAJ = this.game.randRange(255, 3);
               if (this.entityData[3434 + this.currentVillager] > 0
                  && this.entityData[8627 + this.entityData[3434 + this.currentVillager]] == 0
                  && this.entityData[9237 + this.entityData[3434 + this.currentVillager]] < 2) {
                  this.entityData[3131 + this.currentVillager] = this.entityData[8383 + this.entityData[3434 + this.currentVillager]];
                  this.entityData[3232 + this.currentVillager] = this.entityData[8444 + this.entityData[3434 + this.currentVillager]];
                  this.moveVillagerStep(1);
               } else if (this.randomRollAJ == 1 && this.findNearestBuilding(7)) {
                  this.moveVillagerStep(1);
               } else {
                  this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
                  this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
                  this.setTask(this.currentVillager, (byte)79);
                  this.entityData[2929 + this.currentVillager] = 40;
                  this.entityData[3030 + this.currentVillager] = 0;
               }

               if (this.entityData[3434 + this.currentVillager] <= 0) {
                  this.showMessage((byte)1, this.currentVillager, this.currentVillager);
               }
            }

            return true;
         } else {
            if (this.entityData[2020 + this.currentVillager] <= (this.entityData[505 + this.currentVillager] > 5 ? 20 : 50)
               && this.entityData[6464 + this.currentVillager] != 66
               && this.entityData[2828 + this.currentVillager] != 66
               && this.goToBuildingType(4, (byte)0)) {
               return true;
            }

            if (this.entityData[505 + this.currentVillager] <= 30
               && this.entityData[6464 + this.currentVillager] != 86
               && this.entityData[2828 + this.currentVillager] != 86) {
               if (this.goToBuildingType(7, (byte)2)) {
                  return true;
               }

               if (this.entityData[505 + this.currentVillager] <= 5
                  && this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] != 0) {
                  if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
                     this.setTask(this.currentVillager, (byte)87);
                     this.entityData[6161 + this.currentVillager] = 3;
                     return true;
                  }

                  this.goIdle();
               }
            }

            return (
                  this.entityData[1919 + this.currentVillager] < this.entityData[6767 + this.currentVillager] * 2 / 3
                     || (this.entityData[7979 + this.currentVillager] & 4) != 0
               )
               && this.entityData[6464 + this.currentVillager] != 57
               && this.entityData[2828 + this.currentVillager] != 57
               && this.goToBuildingType(8, (byte)3);
         }
      } else {
         return false;
      }
   }

   // $VF: renamed from: a (int, byte) boolean
   private boolean goToBuildingType(int var1, byte var2) {
      if (this.findNearestBuilding(var1)) {
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
            this.setTask(this.currentVillager, (byte)87);
         }

         this.moveVillagerStep(1);
         return true;
      } else {
         if (!this.foundBuildingH) {
            this.showMessage(var2, this.currentVillager, this.currentVillager);
         }

         return false;
      }
   }

   // $VF: renamed from: a (boolean) boolean
   private boolean tryFindMate(boolean var1) {
      if (!var1) {
         if (this.tribeStats[this.tribeIndex][18] >= 0) {
            return false;
         }

         if (this.game.randRange(65535, 10) < 4 || this.entityData[505 + this.currentVillager] < 75) {
            return false;
         }
      }

      int var3 = this.game.randRange(65535, this.tribeStats[this.tribeIndex][11] / 2 + 1);
      this.mateCursorAd = this.tribeIndex * 50 + 1;
      int var2 = 0;

      while (true) {
         if ((this.entityData[505 + this.mateCursorAd] >= 75 || var1)
            && (!var1 || this.mateCursorAd != this.flagsScratchR)
            && this.entityData[1515 + this.currentVillager] != this.entityData[1515 + this.mateCursorAd]
            && this.entityData[1919 + this.mateCursorAd] > 0
            && this.entityData[1414 + this.mateCursorAd] >= 12
            && this.entityData[2828 + this.mateCursorAd] < 109
            && (this.entityData[8282 + this.mateCursorAd] & 127) == 0) {
            if (++var2 > var3) {
               break;
            }
         }

         if (this.mateCursorAd > (this.tribeIndex + 1) * 50) {
            if (var2 == 0) {
               break;
            }

            this.mateCursorAd = this.tribeIndex * 50;
         }

         this.mateCursorAd++;
      }

      if (this.mateCursorAd > (this.tribeIndex + 1) * 50) {
         return false;
      }

      for (this.scratchIntX = 0; this.scratchIntX < 10; this.scratchIntX++) {
         if (this.matingPairs[4][this.scratchIntX] == 0) {
            var3 = this.game.randRange(65535, this.tribeStats[this.tribeIndex][14] / 2 + 1);
            this.matePairIndexAe = this.tribeIndex * 30 + 1;
            var2 = 0;

            while (true) {
               if (this.entityData[8505 + this.matePairIndexAe] >= 0
                  && this.entityData[8749 + this.matePairIndexAe] == 2
                  && this.entityData[8627 + this.matePairIndexAe] == 0
                  && this.entityData[9237 + this.matePairIndexAe] == 0
                  && this.entityData[8932 + this.matePairIndexAe] <= 1) {
                  if (++var2 > var3) {
                     this.tribeStats[this.tribeIndex][18] = 0;
                     this.matingPairs[4][this.scratchIntX] = 1;
                     this.matingPairs[0][this.scratchIntX] = (byte)this.currentVillager;
                     this.matingPairs[1][this.scratchIntX] = (byte)this.mateCursorAd;
                     this.entityData[8282 + this.currentVillager] = (byte)(this.entityData[8282 + this.currentVillager] + (this.scratchIntX + 1 << 1));
                     this.entityData[8282 + this.mateCursorAd] = (byte)(this.entityData[8282 + this.mateCursorAd] + (this.scratchIntX + 1 << 1) + 1);
                     this.matingPairs[5][this.scratchIntX] = (byte)this.matePairIndexAe;
                     this.entityData[9237 + this.matePairIndexAe] = 1;
                     this.matingPairs[2][this.scratchIntX] = (byte)(this.entityData[8383 + this.matePairIndexAe] + 1);
                     this.matingPairs[3][this.scratchIntX] = (byte)(this.entityData[8444 + this.matePairIndexAe] + 2);
                     this.setTask(this.currentVillager, (byte)(110 - this.entityData[1515 + this.currentVillager]));
                     this.retargetAfterIdle();
                     return true;
                  }
               }

               if (this.matePairIndexAe > (this.tribeIndex + 1) * 30) {
                  if (var2 == 0) {
                     return false;
                  }

                  this.matePairIndexAe = this.tribeIndex * 30;
               }

               this.matePairIndexAe++;
            }
         }
      }

      return false;
   }

   // $VF: renamed from: o () boolean
   private boolean updateMating() {
      this.scratchIntX = ((this.entityData[8282 + this.currentVillager] & 127) >> 1) - 1;
      if (this.scratchIntX < 0) {
         return false;
      }

      if (this.entityData[2828 + this.currentVillager] < 109) {
         this.setTask(this.currentVillager, (byte)(110 - this.entityData[1515 + this.currentVillager]));
         this.matingPairs[4][this.scratchIntX] = 2;
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            this.retargetAfterIdle();
         } else {
            this.moveVillagerStep(3);
         }
      } else if (this.matingPairs[4][this.scratchIntX] == 2) {
         if (this.entityData[0 + this.matingPairs[0][this.scratchIntX]] != this.entityData[3131 + this.matingPairs[0][this.scratchIntX]]
            || this.entityData[101 + this.matingPairs[0][this.scratchIntX]] != this.entityData[3232 + this.matingPairs[0][this.scratchIntX]]
            || this.entityData[0 + this.matingPairs[1][this.scratchIntX]] != this.entityData[3131 + this.matingPairs[0][this.scratchIntX]] + 1
            || this.entityData[101 + this.matingPairs[1][this.scratchIntX]] != this.entityData[3232 + this.matingPairs[0][this.scratchIntX]]
            || this.entityData[2828 + this.matingPairs[0][this.scratchIntX]] != 109
            || this.entityData[2828 + this.matingPairs[1][this.scratchIntX]] != 109
            || this.entityData[202 + this.matingPairs[0][this.scratchIntX]] != 0
            || this.entityData[303 + this.matingPairs[0][this.scratchIntX]] != 0
            || this.entityData[202 + this.matingPairs[1][this.scratchIntX]] != 0
            || this.entityData[303 + this.matingPairs[1][this.scratchIntX]] != 0) {
            return true;
         }

         this.scratchIntC = this.matingPairs[0 + this.entityData[1515 + this.matingPairs[0][this.scratchIntX]]][this.scratchIntX];
         this.scratchIntD = this.matingPairs[1 - this.entityData[1515 + this.matingPairs[0][this.scratchIntX]]][this.scratchIntX];
         if (this.entityData[2929 + this.scratchIntC] != -99) {
            this.entityData[2929 + this.scratchIntC] = -99;
            this.entityData[2929 + this.scratchIntD] = -98;
            this.entityData[2828 + this.scratchIntC] = 109;
            this.entityData[3030 + this.scratchIntC] = 0;
            this.entityData[3030 + this.scratchIntD] = 0;
            this.entityData[606 + this.matingPairs[0][this.scratchIntX]] = 0;
            this.entityData[606 + this.matingPairs[1][this.scratchIntX]] = 4;
            this.entityData[4646 + this.matingPairs[0][this.scratchIntX]] = -90;
            this.entityData[9237 + this.matingPairs[5][this.scratchIntX]] = 2;
            this.game.clearEntityJob(this.matingPairs[5][this.scratchIntX], true);
         } else if (this.entityData[3030 + this.currentVillager] == 6 && this.entityData[3535 + this.scratchIntC] == 15) {
            this.entityData[3535 + this.scratchIntC] = 0;
            this.entityData[3535 + this.scratchIntD] = 15;
            this.entityData[3636 + this.scratchIntC] = 0;
            this.entityData[3636 + this.scratchIntD] = 1;
         } else if (this.entityData[3030 + this.currentVillager] == this.game.pd0ByteTables[4][this.entityData[2929 + this.currentVillager] & 0xFF] - 1) {
            this.entityData[3131 + this.scratchIntC] = this.entityData[8383 + this.matingPairs[5][this.scratchIntX]];
            this.entityData[3232 + this.scratchIntC] = this.entityData[8444 + this.matingPairs[5][this.scratchIntX]];
            this.entityData[3131 + this.scratchIntD] = this.entityData[8383 + this.matingPairs[5][this.scratchIntX]];
            this.entityData[3232 + this.scratchIntD] = this.entityData[8444 + this.matingPairs[5][this.scratchIntX]];
            this.entityData[2828 + this.scratchIntC] = 111;
            this.entityData[2828 + this.scratchIntD] = 111;
            this.entityData[808 + this.scratchIntC] = 0;
            this.entityData[808 + this.scratchIntD] = 0;
            this.matingPairs[4][this.scratchIntX] = 3;
            this.matePairIndexAe = this.currentVillager;
            this.currentVillager = this.matingPairs[0][this.scratchIntX];
            this.moveVillagerStep(3);
            this.currentVillager = this.matingPairs[1][this.scratchIntX];
            this.moveVillagerStep(3);
            this.currentVillager = this.matePairIndexAe;
         }
      } else if (this.entityData[2828 + this.currentVillager] == 111) {
         this.entityData[9237 + this.matingPairs[5][this.scratchIntX]] = 3;
         this.clearMatingPair(this.currentVillager, 0);
      }

      return true;
   }

   // $VF: renamed from: d (int, int) void
   private void clearMatingPair(int var1, int var2) {
      this.matePairIndexAe = ((this.entityData[8282 + var1] & 127) >> 1) - 1;
      if (this.matePairIndexAe >= 0) {
         if (var2 > 0 && this.matingPairs[4][this.matePairIndexAe] != 3) {
            return;
         }

         this.matingPairs[4][this.matePairIndexAe] = 0;
         this.endMatingFor(this.matingPairs[0 + (this.entityData[8282 + var1] + 1 & 1)][this.matePairIndexAe]);
         if (var2 < 0) {
            this.endMatingFor(var1);
         }

         if (this.entityData[3535 + var1] == 15) {
            this.entityData[3636 + var1] = 0;
         }

         this.entityData[8282 + this.matingPairs[0][this.matePairIndexAe]] = (byte)(this.entityData[8282 + this.matingPairs[0][this.matePairIndexAe]] & 128);
         this.entityData[8282 + this.matingPairs[1][this.matePairIndexAe]] = (byte)(this.entityData[8282 + this.matingPairs[1][this.matePairIndexAe]] & 128);
         if (this.entityData[9237 + this.matingPairs[5][this.matePairIndexAe]] < 3) {
            this.entityData[9237 + this.matingPairs[5][this.matePairIndexAe]] = 0;
         }
      }
   }

   // $VF: renamed from: a (int) void
   final void releaseMatingHut(int var1) {
      if (this.entityData[9237 + var1] >= 1) {
         for (this.pairScanAf = 0; this.pairScanAf < 10; this.pairScanAf++) {
            if (this.matingPairs[4][this.pairScanAf] != 0 && this.matingPairs[5][this.pairScanAf] == var1) {
               this.clearMatingPair(this.matingPairs[0][this.pairScanAf], -1);
               return;
            }
         }
      }
   }

   // $VF: renamed from: k (int) void
   private void endMatingFor(int var1) {
      if (this.entityData[3535 + var1] == 15) {
         this.entityData[3636 + var1] = 0;
      }

      if (this.entityData[2828 + var1] >= 109) {
         if (this.occupancyGrid[this.entityData[101 + var1]][this.entityData[0 + var1]] == var1) {
            this.entityData[3131 + var1] = this.entityData[0 + var1];
            this.entityData[3232 + var1] = this.entityData[101 + var1];
            this.setTask(var1, (byte)69);
            return;
         }

         this.entityData[6161 + var1] = 3;
         this.setTask(var1, (byte)87);
      }
   }

   // $VF: renamed from: b (int) byte
   final byte moveStance(int var1) {
      if (this.entityData[1414 + var1] < 12) {
         this.entityData[7777 + var1] = 0;
         return 6;
      }

      if ((this.entityData[7979 + var1] & 2) <= 0
         || this.entityData[7777 + var1] != 0 && this.entityData[3535 + var1] == 0 && this.entityData[6565 + var1] != 1) {
         if (this.entityData[3535 + var1] != 0 || this.entityData[6565 + var1] == 1) {
            this.entityData[7777 + var1] = 0;
            return 1;
         } else {
            return (byte)(this.entityData[7777 + var1] > 0 ? 7 : 0);
         }
      } else {
         this.entityData[7777 + var1] = 0;
         return 5;
      }
   }

   // $VF: renamed from: P () void
   private void setIdleRole() {
      this.entityData[2929 + this.currentVillager] = (byte)(this.moveStance(this.currentVillager) % 7 + 8);
   }

   // $VF: renamed from: Q () void
   private void goIdle() {
      if (this.entityData[1414 + this.currentVillager] >= 12) {
         this.setTask(this.currentVillager, (byte)69);
      } else {
         this.setTask(this.currentVillager, (byte)17);
      }
   }

   // $VF: renamed from: p () boolean
   private boolean findNewTargetTile() {
      if ((this.game.missionIndex != 3 || this.scriptEventIndex < 5) && (this.game.missionIndex != 2 || this.scriptEventIndex != 1)) {
         this.setScanBounds(
            this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], this.entityData[4949 + this.currentVillager]
         );
         this.rollByteY = 0;

         for (this.sectorIndexAg = this.tribeIndex * (this.game.missionIndex == 3 ? 6 : 3);
            this.sectorIndexAg <= this.tribeIndex * (this.game.missionIndex == 3 ? 6 : 3) + 2;
            this.sectorIndexAg++
         ) {
            if (this.game.missionIndex != 3 || this.sectorIndexAg != 1) {
               this.hashKindAj = this.recordBlob[this.recordStarts[120] + this.sectorIndexAg];
               this.scanSector(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], (byte)0, (byte)0);
               if (this.rollByteY == this.recordBlob[this.recordStarts[119] + this.hashKindAj]) {
                  break;
               }
            }
         }

         if (this.rollByteY <= 0) {
            return false;
         }

         if (!this.searchFlagB && this.entityData[2828 + this.currentVillager] <= 9) {
            this.searchOkA = this.searchOkA
               && (
                  this.recordBlob[this.recordStarts[122] + this.entityData[2828 + this.currentVillager]] < this.rollByteY
                     || this.distOrTargetXZ / 10 > this.taskDurationB && this.bestDistAn <= this.taskDurationB
               );
         }

         this.occupantByteB = this.occupancyGrid[this.scratchByteA][this.scratchByteZ];
         this.handleArrival();
         return true;
      } else {
         return false;
      }
   }

   // $VF: renamed from: a (byte, byte, byte) void
   private void scanForTargetKind(byte var1, byte var2, byte var3) {
      this.setScanBounds(var1, var2, var3);
      this.hashKindAj = this.tribeIndex;
      this.scanSector(var1, var2, this.scratchByteD, var3);
   }

   // $VF: renamed from: b (byte, byte, byte) void
   private void setScanBounds(byte var1, byte var2, byte var3) {
      this.bestDistAn = 0;
      this.scanMinCellXAt = clamp(var1 - var3 >> 4, 0, 5);
      this.scanMaxCellXAu = clamp(var1 + var3 >> 4, 0, 5);
      this.scanMinCellYAv = clamp(var2 - var3 >> 4, 0, 5);
      this.scanMaxCellYAw = clamp(var2 + var3 >> 4, 0, 5);
   }

   // $VF: renamed from: a (byte, byte, byte, byte) void
   private void scanSector(byte var1, byte var2, byte var3, byte var4) {
      for (this.cellXLoopAh = this.scanMinCellXAt; this.cellXLoopAh <= this.scanMaxCellXAu; this.cellXLoopAh++) {
         for (this.cellYLoopAi = this.scanMinCellYAv; this.cellYLoopAi <= this.scanMaxCellYAw; this.cellYLoopAi++) {
            for (this.hashCursorAk = this.diseaseSpots[this.hashKindAj][this.cellYLoopAi][this.cellXLoopAh];
               this.hashCursorAk >= 0;
               this.hashCursorAk = this.hashNext[this.hashKindAj][this.hashCursorAk]
            ) {
               this.hashEntityAl = this.hashCursorAk + this.recordBlob[this.recordStarts[115] + this.hashKindAj];
               this.foundXAp = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118] + this.hashKindAj]] + this.hashEntityAl]
                  + (
                     this.hashKindAj != 2 && this.hashKindAj != 3
                        ? 0
                        : this.recordBlob[this.recordStarts[138] + 104 + this.entityData[8749 + this.hashEntityAl]] / 2
                  );
               this.foundYAq = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118] + this.hashKindAj] + 1] + this.hashEntityAl]
                  + (
                     this.hashKindAj != 2 && this.hashKindAj != 3
                        ? 0
                        : this.recordBlob[this.recordStarts[138] + 130 + this.entityData[8749 + this.hashEntityAl]] / 2
                  );
               this.deltaXAr = this.foundXAp - var1;
               this.deltaYAs = this.foundYAq - var2;
               this.distTenthAo = this.recordBlob[this.recordStarts[53]
                     + (this.deltaXAr >= -12 && this.deltaXAr <= 12 ? 12 + this.deltaXAr : 0) * 25
                     + (this.deltaYAs >= -12 && this.deltaYAs <= 12 ? 12 + this.deltaYAs : 0)]
                  / 10;
               if (var3 == 0) {
                  if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[117] + this.hashKindAj]] + this.hashEntityAl] > 0
                     && (this.hashKindAj > 1 || (this.entityData[7979 + this.hashEntityAl] & 1) == 0)
                     && (this.hashKindAj != 4 || this.entityData[2828 + this.currentVillager] == 60 || this.entityData[9601 + this.hashEntityAl] != 0)
                     && this.distTenthAo
                        <= this.entityData[this.fieldOffsets[this.occupancyGrid[var2][var1] == this.currentVillager ? 49 : 18] + this.currentVillager]) {
                     this.foundPriorityAm = this.hashKindAj < 2 && this.entityData[1414 + this.hashEntityAl] < 12
                        ? 1
                        : this.recordBlob[this.recordStarts[119] + this.hashKindAj];
                     if (this.rollByteY < this.foundPriorityAm || this.rollByteY == this.foundPriorityAm && this.distTenthAo < this.bestDistAn) {
                        this.rollByteY = (byte)this.foundPriorityAm;
                        this.bestDistAn = this.distTenthAo;
                        this.scratchByteZ = (byte)this.foundXAp;
                        this.scratchByteA = (byte)this.foundYAq;
                        this.foundEntityF = (byte)this.hashEntityAl;
                     }
                  }
               } else if (this.distTenthAo <= var4
                  && this.occupancyGrid[this.entityData[101 + this.hashEntityAl]][this.entityData[0 + this.hashEntityAl]] == this.hashEntityAl
                  && this.entityData[1919 + this.hashEntityAl] > 0
                  && this.entityData[1414 + this.hashEntityAl] >= 12
                  && this.entityData[6161 + this.hashEntityAl] == 0
                  && this.entityData[2828 + this.hashEntityAl] > 9
                  && this.entityData[2828 + this.hashEntityAl] != 59
                  && this.entityData[2828 + this.hashEntityAl] != 87
                  && this.entityData[2828 + this.hashEntityAl] != 50) {
                  this.entityData[6161 + this.hashEntityAl] = 4;
                  this.entityData[4747 + this.hashEntityAl] = var3;
               }
            }
         }
      }
   }

   // $VF: renamed from: a (byte, byte, int, int) void
   private void launchProjectile(byte var1, byte var2, int var3, int var4) {
      try {
         if (this.entityData[5959 + this.currentVillager] <= 0) {
            this.throwWeaponAG = this.recordBlob[this.recordStarts[77] + this.entityData[6565 + this.currentVillager]];
            this.throwDxAE = this.entityData[3131 + this.currentVillager] - var1;
            this.throwDyAF = this.entityData[3232 + this.currentVillager] - var2;
            if (this.recordBlob[this.recordStarts[53]
                     + (this.throwDyAF >= -12 && this.throwDyAF <= 12 ? 12 + this.throwDyAF : 0) * 25
                     + (this.throwDxAE >= -12 && this.throwDxAE <= 12 ? 12 + this.throwDxAE : 0)]
                  / 10
               <= this.taskDurationB) {
               this.flightTimeAx = this.fieldOffsets[this.offsetGroupBases[1]
                  + (this.throwDyAF > 0 ? this.throwDyAF : -this.throwDyAF) * 11
                  + (this.throwDxAE > 0 ? this.throwDxAE : -this.throwDxAE)];
               this.entityData[5959 + this.currentVillager] = (byte)(
                  this.flightTimeAx > this.recordBlob[this.recordStarts[159] + this.throwWeaponAG]
                     ? this.flightTimeAx / this.recordBlob[this.recordStarts[159] + this.throwWeaponAG]
                     : 1
               );
               this.flightStepAy = this.targetVelXAz = this.targetVelYAa = this.throwSpeedAB = 0;
               this.entityData[7878 + this.currentVillager] = 9;
               if ((this.recordBlob[this.recordStarts[27] + this.entityData[2828 + this.currentVillager]] & 2) > 0) {
                  if (this.entityData[4747 + this.currentVillager] > 100) {
                     if ((this.entityData[9557 + this.entityData[4444 + this.currentVillager]] & 255) <= 7) {
                        this.throwSpeedAB = this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.entityData[4444 + this.currentVillager]]];
                        this.throwFacingAC = this.entityData[9491 + this.entityData[4444 + this.currentVillager]];
                        this.throwTargetKindAD = 101;
                     }
                  } else if ((this.entityData[2929 + this.entityData[4444 + this.currentVillager]] & 255) <= 7) {
                     this.throwSpeedAB = this.entityData[1010 + this.entityData[4444 + this.currentVillager]]
                        - this.entityData[7777 + this.entityData[4444 + this.currentVillager]];
                     this.throwFacingAC = this.entityData[606 + this.entityData[4444 + this.currentVillager]];
                     this.throwTargetKindAD = 2;
                  }

                  if (this.throwSpeedAB > 0) {
                     this.entityData[7878 + this.currentVillager] = (byte)this.throwSpeedAB;
                     this.targetVelXAz = this.entityData[this.fieldOffsets[this.throwTargetKindAD] + this.entityData[4444 + this.currentVillager]]
                        + this.recordBlob[this.recordStarts[1] + 0 + this.throwFacingAC]
                           * 22
                           * this.entityData[5959 + this.currentVillager]
                           / this.recordBlob[this.recordStarts[15] + (this.throwFacingAC & 1) * 9 + this.throwSpeedAB];
                     this.targetVelYAa = this.entityData[this.fieldOffsets[this.throwTargetKindAD + 1] + this.entityData[4444 + this.currentVillager]]
                        + this.recordBlob[this.recordStarts[1] + 8 + this.throwFacingAC]
                           * 16
                           * this.entityData[5959 + this.currentVillager]
                           / this.recordBlob[this.recordStarts[15] + (this.throwFacingAC & 1) * 9 + this.throwSpeedAB];
                  }
               }

               this.entityData[5656 + this.currentVillager] = (byte)(
                  (this.throwDxAE * 22 + this.targetVelXAz + (this.throwDxAE > 0 ? 1 : -1) * this.entityData[5959 + this.currentVillager] / 2)
                     / this.entityData[5959 + this.currentVillager]
               );
               this.entityData[5757 + this.currentVillager] = (byte)(
                  (this.throwDyAF * 16 + this.targetVelYAa + (this.throwDyAF > 0 ? 1 : -1) * this.entityData[5959 + this.currentVillager] / 2)
                     / this.entityData[5959 + this.currentVillager]
               );

               for (this.flightStepAy = 0; this.flightStepAy <= this.entityData[5959 + this.currentVillager]; this.flightStepAy++) {
                  this.game.spriteYOffsetTable[this.flightStepAy][this.currentVillager] = (byte)(
                     (
                              -this.recordBlob[this.recordStarts[159] + this.throwWeaponAG]
                                    * this.flightStepAy
                                    * this.recordBlob[this.recordStarts[159] + this.throwWeaponAG]
                                    * this.flightStepAy
                                 + this.flightTimeAx * this.recordBlob[this.recordStarts[159] + this.throwWeaponAG] * this.flightStepAy
                           )
                           / this.fieldOffsets[this.offsetGroupBases[3] + this.throwWeaponAG * 8 + this.entityData[606 + this.currentVillager]]
                        + var3
                        - var4 * this.flightStepAy / this.entityData[5959 + this.currentVillager]
                  );
                  this.game.spriteFrameTable[this.flightStepAy][this.currentVillager] = (byte)(
                     this.throwWeaponAG == 2
                        ? this.flightStepAy & 1
                        : clamp(
                           (
                                    this.flightTimeAx
                                       - 2 * this.recordBlob[this.recordStarts[159] + this.throwWeaponAG] * this.flightStepAy
                                       - var4
                                          * this.fieldOffsets[this.offsetGroupBases[3] + this.throwWeaponAG * 8 + this.entityData[606 + this.currentVillager]]
                                          / this.flightTimeAx
                                 )
                                 * -4
                                 / this.fieldOffsets[this.offsetGroupBases[3] + this.throwWeaponAG * 8 + this.entityData[606 + this.currentVillager]]
                              + 8,
                           0,
                           16
                        )
                  );
               }

               this.entityData[7676 + this.currentVillager] = (byte)(
                  this.recordBlob[this.recordStarts[53] + (this.throwDyAF + 12) * 25 + this.throwDxAE + 12] % 10
               );
               this.entityData[5858 + this.currentVillager] = this.entityData[5454 + this.currentVillager] = this.entityData[5555 + this.currentVillager] = 0;
               this.entityData[5252 + this.currentVillager] = var1;
               this.entityData[5353 + this.currentVillager] = var2;
            }
         }
      } catch (Exception var6) {
         this.entityData[5959 + this.currentVillager] = 0;
      }
   }

   // $VF: renamed from: R () void
   private void stepProjectile() {
      if (this.entityData[5858 + this.currentVillager] < this.entityData[5959 + this.currentVillager]) {
         this.entityData[5454 + this.currentVillager] = (byte)(this.entityData[5454 + this.currentVillager] + this.entityData[5656 + this.currentVillager]);
         this.entityData[5555 + this.currentVillager] = (byte)(this.entityData[5555 + this.currentVillager] + this.entityData[5757 + this.currentVillager]);
         this.entityData[5252 + this.currentVillager] = (byte)(
            this.entityData[5252 + this.currentVillager] + ((this.entityData[5454 + this.currentVillager] + 11 + 66) / 22 - 3)
         );
         this.entityData[5353 + this.currentVillager] = (byte)(
            this.entityData[5353 + this.currentVillager] + ((this.entityData[5555 + this.currentVillager] + 8 + 48) / 16 - 3)
         );
         this.entityData[5454 + this.currentVillager] = (byte)(
            this.entityData[5454 + this.currentVillager] - ((this.entityData[5454 + this.currentVillager] + 11 + 66) / 22 - 3) * 22
         );
         this.entityData[5555 + this.currentVillager] = (byte)(
            this.entityData[5555 + this.currentVillager] - ((this.entityData[5555 + this.currentVillager] + 8 + 48) / 16 - 3) * 16
         );
      }

      this.entityData[5858 + this.currentVillager]++;
      if (this.entityData[5858 + this.currentVillager] > this.entityData[5959 + this.currentVillager]) {
         this.landProjectile();
      }
   }

   // $VF: renamed from: S () void
   private void landProjectile() {
      this.entityData[5959 + this.currentVillager] = 0;
      this.applyDamageAt(
         this.entityData[5252 + this.currentVillager],
         this.entityData[5353 + this.currentVillager],
         this.tribeIndex,
         this.entityData[2828 + this.currentVillager] <= 9
            ? this.recordBlob[this.recordStarts[79] + this.entityData[2828 + this.currentVillager]]
            : (
               this.entityData[2828 + this.currentVillager] == 92
                  ? 1
                  : (this.entityData[2828 + this.currentVillager] == 93 ? 2 : (this.entityData[2828 + this.currentVillager] == 106 ? 5 : 0))
            ),
         this.entityData[4444 + this.currentVillager],
         0,
         (this.entityData[1616 + this.currentVillager] / 3 + this.recordBlob[this.recordStarts[102] + this.entityData[6565 + this.currentVillager]])
            * (this.game.randRange(65535, 11 - this.entityData[7878 + this.currentVillager]) + this.entityData[7878 + this.currentVillager])
            / 10,
         1
      );
   }

   // $VF: renamed from: a (int, int, int, byte) void
   private void resolveTileEvent(int var1, int var2, int var3, byte var4) {
      if (var1 == 106) {
         this.clearAnimal(var4);
         this.game.a_int_int_int_byte_void(6, var2, var3, this.recordBlob[this.recordStarts[183] + this.entityData[9535 + var4]]);
         this.game.markTileRect(var2, var3, 1, 1);
      } else if (var1 == 19) {
         if (var4 <= 50) {
            switch (this.recordBlob[this.recordStarts[41] + this.entityData[6464 + var4]]) {
               case 8:
                  this.game.postMessage((byte)14, (byte)var2, (byte)var3, var4);
                  break;
               case 9:
                  this.game.postMessage((byte)12, (byte)var2, (byte)var3, var4);
                  break;
               case 10:
                  this.game.postMessage((byte)15, (byte)var2, (byte)var3, var4);
                  break;
               case 11:
                  this.game.postMessage((byte)13, (byte)var2, (byte)var3, var4);
            }

            if (var4 == this.tribeStats[0][17]) {
               for (int var5 = 1; var5 <= 50; var5++) {
                  if (this.entityData[1414 + var5] >= 12) {
                     this.entityData[505 + var5] = (byte)clamp(this.entityData[505 + var5] - 25, 0, 99);
                  }
               }
            }
         }

         this.game.a_byte_void(var4);
         this.entityData[2929 + var4] = (byte)(this.entityData[1414 + var4] >= 12 ? 43 : 44);
         this.entityData[3030 + var4] = (byte)(var4 > this.currentVillager ? -1 : 0);
         this.entityData[4646 + var4] = -64;
         this.clearSanctuaryTarget((var4 - 1) / 50, var4);
      } else if (var1 == 85) {
         this.game.c_int_boolean_void(var4, true);
      }

      if (this.scriptEventIndex < this.recordBlob[this.recordStarts[291] + this.game.missionIndex]
         && this.recordBlob[this.recordStarts[287] + this.recordBlob[this.recordStarts[288] + this.game.missionIndex] + this.scriptEventIndex] == 10
         && (var1 == 106 || var1 == 19 && var4 > 50)) {
         this.setGroupMembers(0, 0, 1);
         this.game
            .advanceStoryStep(
               this.recordBlob[this.recordStarts[286] + this.recordBlob[this.recordStarts[288] + this.game.missionIndex] + this.scriptEventIndex]
            );
         this.scriptEventIndex = (byte)(this.scriptEventIndex + 50);
      }
   }

   // $VF: renamed from: l (int) void
   private void clearAnimal(int var1) {
      if ((this.entityData[9557 + var1] & 255) <= 7
         && this.entityData[9623 + var1]
            < (
                  this.recordBlob[this.recordStarts[15]
                        + (this.entityData[9491 + var1] & 1) * 9
                        + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + var1]]]
                     + 1
               )
               / 2
         && this.occupancyGrid[this.entityData[9381 + var1] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + var1]]][this.entityData[9359
                  + var1]
               + this.recordBlob[this.recordStarts[1] + this.entityData[9491 + var1]]]
            == -128) {
         this.occupancyGrid[this.entityData[9381 + var1] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + var1]]][this.entityData[9359
               + var1]
            + this.recordBlob[this.recordStarts[1] + this.entityData[9491 + var1]]] = 0;
      }

      this.occupancyGrid[this.entityData[9381 + var1]][this.entityData[9359 + var1]] = 0;
      this.entityData[9513 + var1] = 0;
      if (this.entityData[9469 + var1] < this.recordBlob[this.recordStarts[202] + this.game.missionIndex]) {
         this.game.effectCounters[this.entityData[9469 + var1]]--;
      }
   }

   // $VF: renamed from: m (int) void
   private void killVillager(int var1) {
      this.clearMatingPair(var1, 0);
      this.entityData[1919 + var1] = 0;
      this.entityData[5959 + var1] = 0;
      this.occupancyGrid[this.entityData[101 + var1]][this.entityData[0 + var1]] = this.entityData[4545 + var1] <= -111 ? this.entityData[4545 + var1] : 0;
      this.tribeStats[(var1 - 1) / 50][11]--;
      this.tribeStats[(var1 - 1) / 50][12 + this.entityData[1515 + var1]]--;
      this.releaseHome(var1);
   }

   // $VF: renamed from: c (int, int, int) void
   private void gatherStrike(int var1, int var2, int var3) {
      if (this.applyDamageAt(
            var1,
            var2,
            this.tribeIndex,
            this.recordBlob[this.recordStarts[79] + this.entityData[2828 + this.currentVillager]],
            this.entityData[4444 + this.currentVillager],
            var3,
            this.recordBlob[this.recordStarts[124] + this.entityData[1717 + this.currentVillager]]
               + this.recordBlob[this.recordStarts[102] + this.entityData[6565 + this.currentVillager]],
            0
         )
         && (this.entityData[2929 + this.currentVillager] & 255) >= 31
         && this.entityData[6565 + this.currentVillager] != 9
         && this.tileQueryResult[0] == 1
         && this.entityData[202 + this.tileQueryResult[2]] == 0
         && this.entityData[303 + this.tileQueryResult[2]] == 0
         && (this.entityData[2929 + this.tileQueryResult[2]] < 106 || this.entityData[2929 + this.tileQueryResult[2]] > 108)) {
         this.entityData[2929 + this.tileQueryResult[2]] = 42;
         this.entityData[3030 + this.tileQueryResult[2]] = 0;
         this.entityData[4646 + this.tileQueryResult[2]] = (byte)(-(62 + (this.scratchIntX & 1)));
      }
   }

   // $VF: renamed from: a (int, int, int, int, int, int, int, int) boolean
   private boolean applyDamageAt(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.flightTimeAx = this.recordBlob[this.recordStarts[78] + 14 + var4];
      this.flightStepAy = var4 == 0
         ? 10
         : (
            var4 == 2
               ? 0
               : this.recordBlob[this.recordStarts[53]
                     + (12 + this.entityData[this.fieldOffsets[this.flightTimeAx + 1] + var5] - var2) * 25
                     + 12
                     + this.entityData[this.fieldOffsets[this.flightTimeAx] + var5]
                     - var1]
                  / 10
         );
      if (this.flightStepAy <= var8 && this.flightStepAy > 0) {
         var1 = this.entityData[this.fieldOffsets[this.flightTimeAx] + var5];
         var2 = this.entityData[this.fieldOffsets[this.flightTimeAx + 1] + var5];
      }

      this.queryTile(var1, var2, var3 & 1, 1);
      if (this.game.cheatFlagAg && this.tileQueryResult[0] == 1 && this.tileQueryResult[2] <= 50) {
         return false;
      }

      if (this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]] == 127) {
         return false;
      }

      if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]]] + this.tileQueryResult[2]] <= 0) {
         return false;
      }

      if (var3 >= 2 || this.tileQueryResult[3] != 0 || this.tileQueryResult[0] != 1 && this.tileQueryResult[0] != 2) {
         if (var3 == 0
            && this.entityData[0 + this.currentVillager] - this.entityData[0 + this.tribeStats[0][17]] >= -4
            && this.entityData[0 + this.currentVillager] - this.entityData[0 + this.tribeStats[0][17]] <= 4
            && this.entityData[101 + this.currentVillager] - this.entityData[101 + this.tribeStats[0][17]] >= -4
            && this.entityData[101 + this.currentVillager] - this.entityData[101 + this.tribeStats[0][17]] <= 4) {
            var7 = var7 * 3 / 2;
         }

         if (this.tileQueryResult[0] == 2) {
            if ((this.entityData[9054 + this.tileQueryResult[2]] & 128) == 0) {
               this.scratchByteD = (byte)(this.currentVillager + (var3 == 2 ? 101 : 0));
               this.tribeIndex ^= 1;
               this.scanForTargetKind(
                  (byte)(this.entityData[8383 + this.tileQueryResult[2]] + this.recordBlob[this.recordStarts[138] + 104 + this.tileQueryResult[1]] / 2),
                  (byte)(this.entityData[8444 + this.tileQueryResult[2]] + this.recordBlob[this.recordStarts[138] + 130 + this.tileQueryResult[1]] / 2),
                  (byte)8
               );
               this.tribeIndex ^= 1;
            }

            this.entityData[9054 + this.tileQueryResult[2]] = (byte)(this.entityData[9054 + this.tileQueryResult[2]] | 128);
            this.game.clearEntityJob(this.tileQueryResult[2], true);
            var7 /= var7 > 1 ? 2 : 1;
         }

         if (this.tileQueryResult[0] == 1 && (this.entityData[7979 + this.tileQueryResult[2]] & 8) != 0) {
            var7 /= var7 > 1 ? 2 : 1;
         }

         if ((var7 = var7 / (this.flightStepAy + 1)) < 1) {
            return false;
         }

         if (this.tileQueryResult[0] == 1
            && this.entityData[1919 + this.tileQueryResult[2]] > 0
            && this.entityData[1414 + this.tileQueryResult[2]] >= 12
            && this.entityData[6161 + this.tileQueryResult[2]] == 0
            && this.entityData[2828 + this.tileQueryResult[2]] > 9
            && this.entityData[2828 + this.tileQueryResult[2]] != 59
            && this.entityData[2828 + this.tileQueryResult[2]] != 87
            && this.entityData[2828 + this.tileQueryResult[2]] != 50) {
            this.entityData[6161 + this.tileQueryResult[2]] = 4;
            this.entityData[4747 + this.tileQueryResult[2]] = (byte)this.currentVillager;
         }

         if (this.tileQueryResult[0] == 1 || this.tileQueryResult[0] == 5) {
            this.game.textLoader.pendingDeltas[this.tileQueryResult[2] + (this.tileQueryResult[0] == 5 ? 101 : 0)] = (byte)(
               this.game.textLoader.pendingDeltas[this.tileQueryResult[2] + (this.tileQueryResult[0] == 5 ? 101 : 0)] - var7
            );
         }

         if (this.tileQueryResult[0] == 5 && var3 != 2 && this.entityData[9601 + this.tileQueryResult[2]] <= 0) {
            this.entityData[9447 + this.tileQueryResult[2]] = (byte)(19 + this.currentVillager);
         }

         this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]]] + this.tileQueryResult[2]] = (byte)(
            this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]]] + this.tileQueryResult[2]] - var7
         );
         if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]]] + this.tileQueryResult[2]] <= 0) {
            this.resolveTileEvent(this.recordBlob[this.recordStarts[78] + this.tileQueryResult[0]], var1, var2, this.tileQueryResult[2]);
            return false;
         }

         if (this.tileQueryResult[0] == 2) {
            var6 = var1 - this.entityData[8383 + this.tileQueryResult[2]] + (var2 - this.entityData[8444 + this.tileQueryResult[2]] << 4);
         }

         this.game
            .spawnAnimEffect(
               this.tileQueryResult[2],
               this.recordBlob[this.recordStarts[178] + this.tileQueryResult[0]],
               var6,
               clamp(52 + var7 / 4 * 3 + this.game.randRange(255, 3), 52, 61)
            );
         if (this.tileQueryResult[0] == 1 && this.tileQueryResult[2] <= 50) {
            if (this.entityData[1414 + this.tileQueryResult[2]] < 12) {
               this.showMessage((byte)16, this.tribeStats[0][17], this.tileQueryResult[2]);
            } else if (this.messageCooldowns[0] < 0) {
               this.messageCooldowns[0] = 0;
               this.game.postMessage((byte)7, (byte)var1, (byte)var2, (byte)0);
            } else if (var3 == 2 && this.entityData[2828 + this.tileQueryResult[2]] != 2 && this.entityData[2828 + this.tileQueryResult[2]] != 7) {
               this.scratchByteZ = (byte)var1;
               this.scratchByteA = (byte)var2;
               this.showMessage((byte)5, this.tileQueryResult[2], this.tileQueryResult[2]);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   // $VF: renamed from: T () void
   private void updateProjectileGrid() {
      for (this.tribeIndex = 0; this.tribeIndex < 2; this.tribeIndex++) {
         for (this.currentVillager = this.tribeIndex * 50 + 1; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
            if (this.entityData[5959 + this.currentVillager] > 0
               && this.entityData[5252 + this.currentVillager] >= this.game.camTileX
               && this.entityData[5252 + this.currentVillager] < this.game.camTileX + this.game.screenColsTiles
               && this.entityData[5353 + this.currentVillager] >= this.game.camTileY
               && this.entityData[5353 + this.currentVillager] < this.game.camTileY + this.game.screenRowsTiles) {
               this.entityData[6060 + this.currentVillager] = this.game.tileEntityDraw[this.entityData[5353 + this.currentVillager] - this.game.camTileY][this.entityData[5252
                     + this.currentVillager]
                  - this.game.camTileX];
               this.game.tileEntityDraw[this.entityData[5353 + this.currentVillager] - this.game.camTileY][this.entityData[5252 + this.currentVillager]
                  - this.game.camTileX] = (byte)this.currentVillager;
            }
         }
      }
   }

   // $VF: renamed from: U () void
   private void leaveBuilding() {
      this.findWorkBuilding(true);
      if (this.entityData[4646 + this.currentVillager] == 1
         && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] == 8
         && this.entityData[8627 + this.entityData[4444 + this.currentVillager]] == 0) {
         this.entityData[2828 + this.entityData[8810 + this.entityData[4444 + this.currentVillager]]] = 57;
         this.entityData[2929 + this.entityData[8810 + this.entityData[4444 + this.currentVillager]]] = 105;
         this.entityData[3030 + this.entityData[8810 + this.entityData[4444 + this.currentVillager]]] = 0;
      }

      this.entityData[2929 + this.currentVillager] = this.entityData[1414 + this.currentVillager] >= 12
            && this.entityData[8749 + this.entityData[4444 + this.currentVillager]] < 12
            && (
               this.entityData[8627 + this.entityData[4444 + this.currentVillager]] != 0
                  || this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] == 12
            )
         ? this.recordBlob[this.recordStarts[176]
            + this.recordBlob[this.recordStarts[177] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
            + this.entityData[4646 + this.currentVillager]]
         : this.recordBlob[this.recordStarts[173]
            + this.recordBlob[this.recordStarts[174] + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
            + this.entityData[4646 + this.currentVillager]];
      this.entityData[3030 + this.currentVillager] = 0;
      this.entityData[1313 + this.currentVillager] = -3;
      this.game.refreshPanelIfMenuTarget(this.entityData[4444 + this.currentVillager]);
      this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
      this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
      if (this.recordBlob[this.recordStarts[39] + this.entityData[2828 + this.currentVillager]] != 2) {
         this.setTask(this.currentVillager, (byte)59);
      }
   }

   // $VF: renamed from: V () void
   private void assignHomeHut() {
      if (this.currentVillager == this.tribeStats[this.tribeIndex][17]) {
         this.entityData[3434 + this.currentVillager] = 1;
      } else {
         for (this.hutSlotScanAI = this.tribeIndex * 30 + 1; this.hutSlotScanAI <= (this.tribeIndex + 1) * 30; this.hutSlotScanAI++) {
            if (this.entityData[8749 + this.hutSlotScanAI] == 2
               && this.entityData[8627 + this.hutSlotScanAI] == 0
               && this.entityData[8810 + this.hutSlotScanAI]
                  < this.recordBlob[this.recordStarts[138] + (this.entityData[9115 + this.hutSlotScanAI] - 1) * 26 + 2]) {
               this.entityData[3434 + this.currentVillager] = (byte)this.hutSlotScanAI;
               this.entityData[8810 + this.hutSlotScanAI]++;
               return;
            }
         }
      }
   }

   // $VF: renamed from: b (int) void
   final void releaseHome(int var1) {
      byte var2;
      if ((var2 = this.entityData[3434 + var1]) > 0) {
         this.entityData[this.fieldOffsets[this.entityData[8627 + var2] == 0 ? 90 : 97] + var2]--;
         this.entityData[3434 + var1] = 0;
      }
   }

   // $VF: renamed from: q () boolean
   private boolean ageVillager() {
      if (this.entityData[6666 + this.currentVillager] >= (this.tribeIndex == 0 ? 125 : 50)) {
         this.entityData[6666 + this.currentVillager] = 0;
         this.entityData[1414 + this.currentVillager]++;
         this.randomRollAJ = this.recordBlob[this.recordStarts[97] + this.entityData[1414 + this.currentVillager]] + this.game.randRange(255, 5) - 2;
         if (this.entityData[1515 + this.currentVillager] == 1) {
            this.randomRollAJ = this.randomRollAJ + (this.randomRollAJ > 0 ? -1 : 1);
         }

         this.entityData[6767 + this.currentVillager] = (byte)clamp(
            this.entityData[6767 + this.currentVillager] + this.randomRollAJ,
            0,
            this.recordBlob[this.recordStarts[98] + this.entityData[1414 + this.currentVillager]]
         );
         if (this.randomRollAJ > 0) {
            this.entityData[1919 + this.currentVillager] = (byte)(this.entityData[1919 + this.currentVillager] + this.randomRollAJ);
            if (this.entityData[6767 + this.currentVillager] >= 55
               && this.entityData[6767 + this.currentVillager] - this.randomRollAJ < 55
               && this.entityData[1010 + this.currentVillager] > 6
               && this.entityData[202 + this.currentVillager] == 0
               && this.entityData[303 + this.currentVillager] == 0) {
               this.entityData[1010 + this.currentVillager]--;
            }
         }

         this.entityData[1919 + this.currentVillager] = (byte)clamp(
            this.entityData[1919 + this.currentVillager], 0, this.entityData[6767 + this.currentVillager]
         );
         if (!this.checkAlive()) {
            return false;
         }

         if (this.entityData[1414 + this.currentVillager] == 12) {
            this.setTask(this.currentVillager, (byte)69);
            this.rankNeeds();
            this.entityData[6464 + this.currentVillager] = 69;
            this.entityData[3030 + this.currentVillager] = 20;
            this.entityData[6262 + this.currentVillager] = this.entityData[3131 + this.currentVillager] = this.entityData[0 + this.currentVillager];
            this.entityData[6363 + this.currentVillager] = this.entityData[3232 + this.currentVillager] = this.entityData[101 + this.currentVillager];
         }

         if (this.entityData[1414 + this.currentVillager] == 12 || this.entityData[1414 + this.currentVillager] == 35) {
            this.game.b_byte_void((byte)this.currentVillager);
         }
      }

      if (this.entityData[1414 + this.currentVillager] == 11
         && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager
         && this.entityData[1313 + this.currentVillager] >= 0) {
         this.entityData[6161 + this.currentVillager] = 3;
         this.setTask(this.currentVillager, (byte)59);
      }

      if (!this.drainNeeds()) {
         return false;
      }

      if ((this.entityData[7979 + this.currentVillager] & 8) > 0) {
         this.entityData[8181 + this.currentVillager]++;
         if (this.entityData[8181 + this.currentVillager] >= 120) {
            this.entityData[8181 + this.currentVillager] = 0;
            this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 247);
         }
      }

      return true;
   }

   // $VF: renamed from: r () boolean
   private boolean drainNeeds() {
      if (this.entityData[1414 + this.currentVillager] >= 12 && this.game.tickCounter >= this.fieldOffsets[this.offsetGroupBases[4] + this.game.missionIndex]) {
         if (this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 9
            && (this.game.tickCounter & 63) == (this.currentVillager & 63)) {
            if (this.entityData[2020 + this.currentVillager] < 100) {
               this.entityData[2020 + this.currentVillager]++;
            }
         } else if (this.recordBlob[this.recordStarts[50] + this.entityData[2828 + this.currentVillager]] < 127
            && this.game.tickCounter % (this.recordBlob[this.recordStarts[50] + this.entityData[2828 + this.currentVillager]] * (this.tribeIndex * 4 + 1)) <= 3
            )
          {
            if (this.entityData[2020 + this.currentVillager] > 0) {
               this.entityData[2020 + this.currentVillager]--;
            } else {
               this.adjustHealth(this.currentVillager, -1);
            }

            if (this.entityData[2020 + this.currentVillager] <= 5 && this.game.placeConfirmFlagL && (this.entityData[7979 + this.currentVillager] & 64) != 0) {
               this.showMessage((byte)14, this.currentVillager, this.currentVillager);
            }
         }

         this.randomRollAJ = (this.entityData[2929 + this.currentVillager] & 255) <= 14
            ? this.recordBlob[this.recordStarts[170] + this.entityData[2929 + this.currentVillager]]
            : this.recordBlob[this.recordStarts[52] + this.entityData[2828 + this.currentVillager]];
         if (this.randomRollAJ < 127 && this.game.tickCounter % (this.randomRollAJ * (this.tribeIndex * 4 + 1)) <= 3) {
            if (this.entityData[2121 + this.currentVillager] > 0) {
               this.entityData[2121 + this.currentVillager]--;
            } else {
               this.adjustHealth(this.currentVillager, -1);
            }

            if (this.entityData[2121 + this.currentVillager] <= 5 && this.game.placeConfirmFlagL && (this.entityData[7979 + this.currentVillager] & 64) != 0) {
               this.showMessage((byte)15, this.currentVillager, this.currentVillager);
            }
         }

         if (!this.checkAlive()) {
            return false;
         }
      }

      if (this.entityData[7373 + this.currentVillager] > 1 && this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]] == 11) {
         if (this.game.tickCounter % (46 - this.entityData[7373 + this.currentVillager] * 3) <= 3) {
            this.adjustHealth(this.currentVillager, 2);
         }
      } else if ((this.game.tickCounter & 127) == (this.currentVillager & 15)) {
         if ((this.entityData[7979 + this.currentVillager] & 4) == 0) {
            this.adjustHealth(this.currentVillager, 1);
         }
      } else if ((this.entityData[7979 + this.currentVillager] & 4) > 0 && (this.game.tickCounter & 31) == (this.currentVillager & 15)) {
         this.adjustHealth(this.currentVillager, -1);
         if (!this.checkAlive()) {
            return false;
         }
      }

      return true;
   }

   // $VF: renamed from: s () boolean
   private boolean checkAlive() {
      if (this.entityData[1919 + this.currentVillager] <= 0) {
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
            this.resolveTileEvent(19, this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], (byte)this.currentVillager);
            return false;
         }

         this.entityData[1919 + this.currentVillager] = this.entityData[6767 + this.currentVillager] = 1;
         this.entityData[6161 + this.currentVillager] = 2;
         this.entityData[8282 + this.currentVillager] = (byte)(this.entityData[8282 + this.currentVillager] | 128);
      }

      return true;
   }

   // $VF: renamed from: W () void
   private void updateMood() {
      if (this.game.tickCounter >= this.fieldOffsets[this.offsetGroupBases[4] + this.game.missionIndex]) {
         this.scratchIntW = this.entityData[505 + this.currentVillager]
            + this.recordBlob[this.recordStarts[51] + this.entityData[2828 + this.currentVillager]]
            + (
               this.recordBlob[this.recordStarts[51] + this.entityData[2828 + this.currentVillager]] < 0 && this.entityData[3434 + this.currentVillager] < 1
                  ? -3
                  : 0
            )
            - (this.entityData[2121 + this.currentVillager] < 20 ? 1 : 0)
            - (this.entityData[2020 + this.currentVillager] < 20 ? 1 : 0);
         this.scratchIntW = this.scratchIntW
            + (
               this.entityData[4646 + this.currentVillager] >= 0
                     && this.entityData[2828 + this.currentVillager] != 92
                     && this.entityData[2828 + this.currentVillager] != 93
                     && this.entityData[2828 + this.currentVillager] != 106
                  ? this.recordBlob[this.recordStarts[138]
                     + (21 + this.entityData[9115 + this.entityData[4444 + this.currentVillager]] - 1) * 26
                     + this.entityData[8749 + this.entityData[4444 + this.currentVillager]]]
                  : 0
            );
         this.entityData[505 + this.currentVillager] = (byte)clamp(this.scratchIntW + (this.game.missionIndex < 2 ? 1 : 0) + this.tribeIndex, 0, 99);
      }
   }

   // $VF: renamed from: X () void
   private void updateDiseaseMeter() {
      if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
         if (this.entityData[8080 + this.currentVillager] > 0) {
            this.entityData[8080 + this.currentVillager]--;
         }
      } else if (this.entityData[2828 + this.currentVillager] != 92
         && this.entityData[2828 + this.currentVillager] != 93
         && this.entityData[2828 + this.currentVillager] != 106) {
         this.entityData[8080 + this.currentVillager] = (byte)clamp(
            this.entityData[8080 + this.currentVillager] + ((this.entityData[8871 + this.entityData[4444 + this.currentVillager]] & 2) != 0 ? 1 : -1), 0, 30
         );
      }

      if (this.entityData[8080 + this.currentVillager] >= 30) {
         this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 4);
         this.entityData[8080 + this.currentVillager] = 0;
      }
   }

   // $VF: renamed from: Y () void
   private void spreadDisease() {
      for (this.neighborDyN = -1; this.neighborDyN < 2; this.neighborDyN++) {
         for (this.neighborDxO = -1; this.neighborDxO < 2; this.neighborDxO++) {
            if (this.entityData[0 + this.currentVillager] + this.neighborDyN >= 0
               && this.entityData[0 + this.currentVillager] + this.neighborDyN < this.game.mapWidth
               && this.entityData[101 + this.currentVillager] + this.neighborDyN >= 0
               && this.entityData[101 + this.currentVillager] + this.neighborDyN < this.game.mapHeight) {
               this.neighborOccupantP = this.occupancyGrid[this.entityData[101 + this.currentVillager] + this.neighborDxO][this.entityData[0
                     + this.currentVillager]
                  + this.neighborDyN];
               if (this.neighborOccupantP > 0
                  && this.neighborOccupantP <= 100
                  && (this.entityData[7979 + this.neighborOccupantP] & 4) == 0
                  && this.entityData[1414 + this.neighborOccupantP] >= 12) {
                  this.entityData[8080 + this.neighborOccupantP] = (byte)(this.entityData[8080 + this.neighborOccupantP] + 8);
               }
            }
         }
      }
   }

   // $VF: renamed from: Z () void
   private void tickHutBirths() {
      if (this.game.missionIndex != 3) {
         if (this.tribeIndex == 1) {
            if (this.tribeStats[1][11] >= this.recordBlob[this.recordStarts[275 + this.game.selectedDifficulty] + this.game.missionIndex]) {
               this.tribeStats[1][18] = 0;
            }

            if (this.tribeStats[1][18] != -1) {
               return;
            }
         }

         for (this.hutSlotScanAH = this.tribeIndex * 30 + 1; this.hutSlotScanAH <= (this.tribeIndex + 1) * 30; this.hutSlotScanAH++) {
            if (this.entityData[8505 + this.hutSlotScanAH] != 0
               && this.entityData[8749 + this.hutSlotScanAH] == 2
               && this.entityData[8627 + this.hutSlotScanAH] == 0
               && this.entityData[8932 + this.hutSlotScanAH] <= 3) {
               if (this.tribeIndex == 0) {
                  if (this.entityData[9237 + this.hutSlotScanAH] < 9) {
                     if (this.entityData[9237 + this.hutSlotScanAH] >= 3) {
                        this.entityData[9237 + this.hutSlotScanAH]++;
                     }
                     continue;
                  }
               } else {
                  if (this.entityData[8383 + this.hutSlotScanAH] >= this.game.camTileX - 1
                     && this.entityData[8444 + this.hutSlotScanAH] >= this.game.camTileY - 1
                     && this.entityData[8383 + this.hutSlotScanAH] <= this.game.camTileX + this.game.screenColsTiles
                     && this.entityData[8444 + this.hutSlotScanAH] <= this.game.camTileY + this.game.screenRowsTiles) {
                     continue;
                  }

                  this.tribeStats[this.tribeIndex][18] = 0;
                  if (this.tribeIndex == 1 && this.waveTargetQByte > 0) {
                     return;
                  }
               }

               this.entityData[9237 + this.hutSlotScanAH] = 0;
               this.currentVillager = this.game
                  .spawnVillager(
                     (byte)this.tribeIndex,
                     this.entityData[8383 + this.hutSlotScanAH],
                     this.entityData[8444 + this.hutSlotScanAH],
                     (byte)2,
                     (byte)(this.tribeIndex == 0 ? 0 : this.recordBlob[this.recordStarts[212 + this.game.selectedDifficulty] + this.game.missionIndex] / 4)
                  );
               if (this.currentVillager < 1) {
                  return;
               }

               this.entityData[4444 + this.currentVillager] = (byte)this.hutSlotScanAH;
               this.entityData[8932 + this.hutSlotScanAH]++;
               this.assignWorkSlot();
               this.entityData[4545 + this.currentVillager] = this.entityData[8993 + this.hutSlotScanAH];
               this.entityData[8993 + this.hutSlotScanAH] = (byte)this.currentVillager;
               this.entityData[1313 + this.currentVillager] = -3;
               this.entityData[2929 + this.currentVillager] = 71;
               this.entityData[3030 + this.currentVillager] = 0;
               this.entityData[2828 + this.currentVillager] = 59;
               if (this.tribeIndex == 1) {
                  return;
               }
            }
         }
      }
   }

   // $VF: renamed from: g () void
   final void tickBuildings() {
      if (this.game.tickCounter % 10 == 0) {
         this.rebuildSpatialHash();
      }

      try {
         this.tickMessageCooldowns();

         for (this.tribeIndex = 0; this.tribeIndex < 2; this.tribeIndex++) {
            for (this.currentVillager = this.tribeIndex * 30 + 1; this.currentVillager <= (this.tribeIndex + 1) * 30; this.currentVillager++) {
               if (this.entityData[8505 + this.currentVillager] != 0) {
                  this.distOrTargetXZ = this.entityData[8749 + this.currentVillager];
                  if ((this.game.tickCounter & 15) == 0) {
                     this.entityData[9054 + this.currentVillager] = (byte)(this.entityData[9054 + this.currentVillager] & 127);
                  }

                  if (this.distOrTargetXZ >= 12 || this.distOrTargetXZ == 4 || this.distOrTargetXZ == 2 || this.entityData[8627 + this.currentVillager] != 0) {
                     this.scratchIntX = this.targetYA = 0;
                     if (this.distOrTargetXZ >= 12) {
                        while (this.scratchIntX < 10 && this.trapRegistryF[0][this.scratchIntX] != this.currentVillager) {
                           this.scratchIntX++;
                        }
                     }

                     if (this.entityData[8627 + this.currentVillager] != 0) {
                        while (this.scratchIntX < 20 && this.upgradeRegistryG[0][this.scratchIntX] != this.currentVillager) {
                           this.scratchIntX++;
                        }
                     }

                     if (this.entityData[8627 + this.currentVillager] == 0
                        || (
                              this.upgradeRegistryG[2][this.scratchIntX] <= 0
                                 || this.upgradeRegistryG[1][this.scratchIntX] < this.upgradeRegistryG[2][this.scratchIntX]
                                 || this.entityData[8627 + this.currentVillager] != 1
                           )
                           && (
                              this.entityData[8627 + this.currentVillager] != 2
                                 || this.entityData[8505 + this.currentVillager] < this.entityData[8566 + this.currentVillager]
                           )
                           && (this.entityData[9054 + this.currentVillager] & 1) == 0) {
                        this.scratchIntC = 0;
                        this.updateBuildingWorkers();
                        if ((this.game.tickCounter & 0xFF) == this.currentVillager) {
                           this.recountBuildingWorkers();
                        }

                        if (this.distOrTargetXZ == 2 && this.scratchIntX > 0) {
                           if (this.entityData[1313 + this.scratchIntX] == 2 && this.targetYA != 3) {
                              this.entityData[1313 + this.scratchIntX] = 1;
                           }

                           if (this.entityData[1313 + this.scratchIntX] == 1 && this.targetYA == 3) {
                              this.entityData[1313 + this.scratchIntX] = 2;
                           }
                        }

                        if (this.distOrTargetXZ >= 12
                           && this.entityData[8505 + this.currentVillager]
                              >= this.recordBlob[this.recordStarts[138] + 182 + this.entityData[9237 + this.currentVillager]]
                           && this.entityData[8932 + this.currentVillager] == 0) {
                           this.game
                              .a_byte_int_int_int_int_boolean_int_byte(
                                 (byte)this.tribeIndex,
                                 this.entityData[9237 + this.currentVillager],
                                 this.entityData[8383 + this.currentVillager],
                                 this.entityData[8444 + this.currentVillager],
                                 1,
                                 true,
                                 this.currentVillager
                              );
                           this.game.enqueueBuildAnim(this.currentVillager, 0);
                           this.entityData[9237 + this.currentVillager] = 0;
                           this.trapRegistryF[0][this.scratchIntX] = 0;
                        }

                        if ((this.distOrTargetXZ >= 12 || this.entityData[8627 + this.currentVillager] != 0)
                           && (this.game.tickCounter & 15) == (this.currentVillager & 15)) {
                           this.assignIdleToBuilding();
                        }
                     } else {
                        this.game.clearEntityJob(this.currentVillager, true);
                        this.completeUpgrade();
                     }
                  }
               }
            }
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: aa () void
   private void updateBuildingWorkers() {
      for (this.scratchIntW = this.entityData[8993 + this.currentVillager]; this.scratchIntW > 0; this.scratchIntW = this.entityData[4545 + this.scratchIntW]) {
         this.scratchIntC++;
         if (this.distOrTargetXZ == 2 && this.entityData[8627 + this.currentVillager] == 0) {
            if (this.entityData[4646 + this.scratchIntW] == 0) {
               this.scratchIntX = this.scratchIntW;
            }

            if (this.entityData[1313 + this.scratchIntW] > 0 && this.entityData[2828 + this.scratchIntW] == 111) {
               this.targetYA = this.targetYA | 1 + this.entityData[1515 + this.scratchIntW];
            }
         } else if (this.distOrTargetXZ >= 12
            && this.entityData[8505 + this.currentVillager] >= this.recordBlob[this.recordStarts[138] + 182 + this.entityData[9237 + this.currentVillager]]) {
            switch (this.tribeIndex) {
               case 0:
                  this.entityData[2828 + this.scratchIntW] = 87;
                  this.entityData[6161 + this.scratchIntW] = 3;
                  break;
               case 1:
                  this.entityData[6161 + this.scratchIntW] = 2;
            }
         } else if (this.entityData[2828 + this.scratchIntW] != 87
            && this.entityData[1313 + this.scratchIntW] != -3
            && (
               this.entityData[1313 + this.scratchIntW] != -2
                  || this.entityData[3030 + this.scratchIntW] >= this.game.pd0ByteTables[4][this.entityData[2929 + this.scratchIntW] & 0xFF] - 1
                     && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.scratchIntW]]]
                        == this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.scratchIntW]]]
                     && this.entityData[3636 + this.scratchIntW] <= 0
            )
            && (
               this.entityData[2929 + this.scratchIntW] != -109 && this.entityData[2929 + this.scratchIntW] != -102
                  || this.entityData[3030 + this.scratchIntW] >= this.game.pd0ByteTables[4][this.entityData[2929 + this.scratchIntW] & 0xFF] - 1
            )
            && (
               this.entityData[8627 + this.currentVillager] == 0
                  || this.entityData[2828 + this.scratchIntW] == 56
                  || this.entityData[2828 + this.scratchIntW] == 76
                  || this.entityData[2828 + this.scratchIntW] == 77
                  || this.entityData[2828 + this.scratchIntW] == 78
            )) {
            this.assignBuildingTask();
         }
      }
   }

   // $VF: renamed from: ab () void
   private void assignBuildingTask() {
      if (this.entityData[4646 + this.scratchIntW] == 0) {
         if (this.entityData[6161 + this.scratchIntW] == 2) {
            return;
         }

         if (this.entityData[8627 + this.currentVillager] != 0) {
            if (this.upgradeRegistryG[1][this.scratchIntX] >= this.upgradeRegistryG[2][this.scratchIntX]) {
               this.upgradeRegistryG[1][this.scratchIntX] = this.upgradeRegistryG[2][this.scratchIntX] = 0;
            }

            if (this.upgradeRegistryG[2][this.scratchIntX] == 0) {
               this.setTask(this.scratchIntW, (byte)99);
               this.entityData[6161 + this.scratchIntW] = 3;
            }
         } else if (this.trapRegistryF[1][this.scratchIntX] < this.recordBlob[this.recordStarts[80] + this.entityData[9237 + this.currentVillager]]
               && this.distOrTargetXZ >= 12
            || this.distOrTargetXZ == 4 && this.tribeStats[this.tribeIndex][0] < 40 && this.entityData[2828 + this.scratchIntW] == 66) {
            this.entityData[6161 + this.scratchIntW] = 3;
            if (this.distOrTargetXZ >= 12) {
               this.setTask(
                  this.scratchIntW,
                  this.recordBlob[this.recordStarts[81]
                     + this.recordBlob[this.recordStarts[82] + this.entityData[9237 + this.currentVillager]]
                     + this.trapRegistryF[1][this.scratchIntX]]
               );
            } else if (this.entityData[3636 + this.scratchIntW] <= 0
               || this.entityData[3535 + this.scratchIntW] != 1
                  && this.entityData[3535 + this.scratchIntW] != 5
                  && this.entityData[3535 + this.scratchIntW] != 6
                  && this.entityData[3535 + this.scratchIntW] != 7) {
               this.setTask(this.scratchIntW, (byte)37);
            } else {
               this.setTask(this.scratchIntW, (byte)54);
               this.entityData[6161 + this.scratchIntW] = 0;
            }
         }

         if (this.entityData[8749 + this.currentVillager] == 6
            || this.entityData[8749 + this.currentVillager] == 16 && this.entityData[9237 + this.currentVillager] == 6) {
            if (this.entityData[6161 + this.scratchIntW] == 3) {
               this.entityData[6161 + this.scratchIntW] = 0;
               if (this.tryCastSpell(this.entityData[2828 + this.scratchIntW], false, this.scratchIntW)) {
                  if (this.entityData[8749 + this.currentVillager] == 16) {
                     this.trapRegistryF[1][this.scratchIntX]++;
                  }

                  this.updateTrapAnimation(false);
                  return;
               }

               this.taskCompleteF = true;
               if (this.entityData[8749 + this.currentVillager] != 6
                  && this.entityData[8505 + this.currentVillager]
                     < this.recordBlob[this.recordStarts[83]
                        + this.recordBlob[this.recordStarts[84] + this.entityData[9237 + this.currentVillager]]
                        + this.trapRegistryF[1][this.scratchIntX]]) {
                  this.taskCompleteF = false;
               }

               this.updateTrapAnimation(this.taskCompleteF);
               return;
            }

            this.updateTrapAnimation(false);
            return;
         }
      } else if (this.entityData[2828 + this.scratchIntW] != 59) {
         if (this.entityData[8627 + this.currentVillager] != 0) {
            if (this.upgradeRegistryG[1][this.scratchIntX] < this.upgradeRegistryG[2][this.scratchIntX]) {
               this.setTask(
                  this.scratchIntW,
                  this.recordBlob[this.recordStarts[60]
                     + this.recordBlob[this.recordStarts[177] + this.distOrTargetXZ]
                     + this.entityData[4646 + this.scratchIntW]]
               );
               return;
            }

            this.setTask(
               this.scratchIntW,
               this.recordBlob[this.recordStarts[59] + this.recordBlob[this.recordStarts[177] + this.distOrTargetXZ] + this.entityData[4646 + this.scratchIntW]]
            );
            return;
         }

         if (this.entityData[8505 + this.currentVillager]
                  < this.recordBlob[this.recordStarts[83]
                     + this.recordBlob[this.recordStarts[84] + this.entityData[9237 + this.currentVillager]]
                     + this.trapRegistryF[1][this.scratchIntX]]
               && this.distOrTargetXZ >= 12
            || this.distOrTargetXZ == 4 && this.tribeStats[this.tribeIndex][0] > 0) {
            this.setTask(
               this.scratchIntW,
               this.recordBlob[this.recordStarts[58] + this.recordBlob[this.recordStarts[174] + this.distOrTargetXZ] + this.entityData[4646 + this.scratchIntW]]
            );
            return;
         }

         this.setTask(
            this.scratchIntW,
            this.recordBlob[this.recordStarts[57] + this.recordBlob[this.recordStarts[174] + this.distOrTargetXZ] + this.entityData[4646 + this.scratchIntW]]
         );
      }
   }

   // $VF: renamed from: ac () void
   private void recountBuildingWorkers() {
      for (int var1 = this.tribeIndex * 50 + 1; var1 <= (this.tribeIndex + 1) * 50; var1++) {
         if (this.entityData[1919 + var1] > 0
            && this.entityData[4444 + var1] == this.currentVillager
            && this.entityData[4646 + var1] < 0
            && this.entityData[1313 + var1] == -1
            && (
               var1 != this.entityData[8810 + this.currentVillager]
                  || this.entityData[8749 + this.currentVillager] == 2 && this.entityData[8627 + this.currentVillager] == 0
            )) {
            this.scratchIntC++;
         }
      }

      this.entityData[8932 + this.currentVillager] = (byte)this.scratchIntC;
   }

   // $VF: renamed from: ad () void
   private void completeUpgrade() {
      if ((this.entityData[9054 + this.currentVillager] & 1) == 0) {
         this.game.enqueueBuildAnim(this.currentVillager, 0);
         if (this.entityData[8627 + this.currentVillager] == 1) {
            this.entityData[9115 + this.currentVillager]++;
         }
      }

      this.entityData[9054 + this.currentVillager] = (byte)(this.entityData[9054 + this.currentVillager] & 254);
      this.entityData[8627 + this.currentVillager] = 0;
      this.entityData[8810 + this.currentVillager] = this.entityData[9237 + this.currentVillager];
      this.entityData[9237 + this.currentVillager] = 0;
      this.upgradeRegistryG[0][this.scratchIntX] = 0;
      this.game.refreshPanelIfMenuTarget(this.currentVillager);
   }

   // $VF: renamed from: a (byte) boolean
   final boolean registerTrap(byte var1) {
      for (this.capacityAK = 0; this.capacityAK < 10; this.capacityAK++) {
         if (this.trapRegistryF[0][this.capacityAK] < 1) {
            this.trapRegistryF[0][this.capacityAK] = var1;
            this.trapRegistryF[1][this.capacityAK] = 0;
            this.entityData[9298 + var1] = 0;
            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: b (byte) boolean
   final boolean registerUpgrade(byte var1) {
      for (this.capacityAK = 0; this.capacityAK < 20; this.capacityAK++) {
         if (this.upgradeRegistryG[0][this.capacityAK] < 1) {
            this.upgradeRegistryG[0][this.capacityAK] = var1;
            this.upgradeRegistryG[1][this.capacityAK] = 0;
            this.upgradeRegistryG[2][this.capacityAK] = 0;
            this.entityData[9237 + var1] = this.entityData[8810 + var1];
            this.entityData[8810 + var1] = 0;
            this.entityData[9298 + var1] = 0;
            if (this.entityData[8749 + var1] == 2) {
               this.releaseMatingHut(var1);
            }

            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: ae () void
   private void assignIdleToBuilding() {
      if ((this.tribeIndex != 0 || !this.game.placeConfirmFlagN) && (this.tribeIndex != 1 || this.waveTargetQByte <= 0)) {
         this.capacityAK = this.entityData[8627 + this.currentVillager] == 0
            ? this.recordBlob[this.recordStarts[138] + (this.entityData[9115 + this.currentVillager] - 1) * 26 + this.entityData[8749 + this.currentVillager]]
            : this.recordBlob[this.recordStarts[138] + 494 + this.entityData[8749 + this.currentVillager]];
         if (this.entityData[9298 + this.currentVillager] < this.entityData[8932 + this.currentVillager] && this.entityData[8810 + this.currentVillager] > 0) {
            this.entityData[9298 + this.currentVillager] = this.entityData[8932 + this.currentVillager];
         }

         if (this.entityData[9298 + this.currentVillager] < this.capacityAK) {
            for (this.workerScanAL = this.tribeIndex * 50 + 1;
               this.workerScanAL <= (this.tribeIndex + 1) * 50 && this.entityData[9298 + this.currentVillager] < this.capacityAK;
               this.workerScanAL++
            ) {
               int var1;
               if ((
                     this.tribeIndex == 0
                           && (
                              this.entityData[2828 + this.workerScanAL] == 69
                                 || this.entityData[2828 + this.workerScanAL] == 84 && this.entityData[3939 + this.workerScanAL] == 69
                           )
                           && this.entityData[6161 + this.workerScanAL] == 0
                           && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.workerScanAL]]] == 2
                        || this.tribeIndex == 1
                           && this.aiRank[this.workerScanAL - this.tribeIndex * 50 - 1] < this.recordBlob[this.recordStarts[242] + this.game.missionIndex]
                           && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.workerScanAL]]] != 2
                  )
                  && (
                     this.tribeIndex != 0
                        || (var1 = -this.occupancyGrid[this.entityData[6363 + this.workerScanAL]][this.entityData[6262 + this.workerScanAL]])
                           <= this.tribeIndex * 30
                        || var1 > (this.tribeIndex + 1) * 30
                        || this.entityData[8749 + var1] < 12 && this.entityData[8627 + var1] == 0
                  )) {
                  this.entityData[6262 + this.workerScanAL] = this.entityData[3131 + this.workerScanAL] = this.entityData[8383 + this.currentVillager];
                  this.entityData[6363 + this.workerScanAL] = this.entityData[3232 + this.workerScanAL] = this.entityData[8444 + this.currentVillager];
                  this.entityData[6464 + this.workerScanAL] = this.entityData[2828 + this.workerScanAL] = 77;
                  this.entityData[6161 + this.workerScanAL] = 3;
                  this.entityData[9298 + this.currentVillager]++;
                  if (this.occupancyGrid[this.entityData[101 + this.workerScanAL]][this.entityData[0 + this.workerScanAL]] != this.workerScanAL) {
                     this.entityData[2828 + this.workerScanAL] = 87;
                  }
               }
            }
         }
      }
   }

   // $VF: renamed from: c (int) void
   final void unregisterTrap(int var1) {
      for (this.capacityAK = 0; this.capacityAK < 10; this.capacityAK++) {
         if (this.trapRegistryF[0][this.capacityAK] == var1) {
            this.trapRegistryF[0][this.capacityAK] = 0;
         }
      }
   }

   // $VF: renamed from: d (boolean) void
   private void updateTrapAnimation(boolean var1) {
      if (var1) {
         this.setTask(this.scratchIntW, (byte)78);
         if (this.entityData[2929 + this.scratchIntW] != 115 && this.entityData[2929 + this.scratchIntW] != 113) {
            this.entityData[2929 + this.scratchIntW] = 115;
            this.entityData[3030 + this.scratchIntW] = -1;
            this.entityData[1313 + this.scratchIntW] = 1;
            return;
         }

         if (this.entityData[3030 + this.scratchIntW] == this.game.pd0ByteTables[4][this.entityData[2929 + this.scratchIntW] & 0xFF] - 1) {
            this.entityData[2929 + this.scratchIntW] = 113;
            return;
         }
      } else {
         if (this.entityData[2929 + this.scratchIntW] == 115 || this.entityData[2929 + this.scratchIntW] == 113) {
            this.entityData[2929 + this.scratchIntW] = 116;
            this.entityData[3030 + this.scratchIntW] = -1;
            return;
         }

         if (this.entityData[3030 + this.scratchIntW] == this.game.pd0ByteTables[4][this.entityData[2929 + this.scratchIntW] & 0xFF] - 1) {
            this.setTask(this.scratchIntW, (byte)56);
         }
      }
   }

   // $VF: renamed from: h () void
   final void tickAnimals() {
      try {
         if (this.recordBlob[this.recordStarts[202] + this.game.missionIndex] > 0
            && this.game.tickCounter % (this.recordBlob[this.recordStarts[204] + this.game.missionIndex] * 40) == 10
            && (this.game.missionIndex != 4 || this.game.tickCounter > 5000)) {
            if (this.game.effectCounters[this.tribeIndex = this.game.randRange(16777215, this.recordBlob[this.recordStarts[202] + this.game.missionIndex])]
               >= this.recordBlob[this.recordStarts[205] + this.game.missionIndex]) {
               this.tribeIndex = 0;

               while (
                  this.tribeIndex < this.recordBlob[this.recordStarts[202] + this.game.missionIndex]
                     && this.game.effectCounters[this.tribeIndex] >= this.recordBlob[this.recordStarts[205] + this.game.missionIndex]
               ) {
                  this.tribeIndex++;
               }
            }

            if (this.tribeIndex < this.recordBlob[this.recordStarts[202] + this.game.missionIndex]) {
               this.currentVillager = this.game.randRange(16777215, 100);
               this.scratchIntW = 0;

               while (
                  this.scratchIntW < 3
                     && this.currentVillager
                        >= this.recordBlob[this.recordStarts[185]
                           + (this.recordBlob[this.recordStarts[201] + this.game.missionIndex] + this.tribeIndex) * 3
                           + this.scratchIntW]
               ) {
                  this.scratchIntW++;
               }

               this.currentVillager = 0;
               this.spiralSearchTile(
                  1,
                  10,
                  this.recordBlob[this.recordStarts[199] + this.recordBlob[this.recordStarts[201] + this.game.missionIndex] + this.tribeIndex],
                  this.recordBlob[this.recordStarts[200] + this.recordBlob[this.recordStarts[201] + this.game.missionIndex] + this.tribeIndex]
               );
               this.game.spawnEffect(this.scratchIntW, this.entityData[3131], this.entityData[3232], this.tribeIndex);
               if (this.game.missionIndex == 4 && this.game.effectCounters[0] > 1 && this.game.randRange(16777215, 10) < this.game.effectCounters[0]) {
                  for (this.currentVillager = 0; this.currentVillager < 22; this.currentVillager++) {
                     if (this.entityData[9469 + this.currentVillager] == 0) {
                        this.entityData[9469 + this.currentVillager] = 2;
                     }
                  }

                  this.game.effectCounters[0] = 0;
               }
            }
         }

         for (this.currentVillager = 0; this.currentVillager < 22; this.currentVillager++) {
            if (this.entityData[9513 + this.currentVillager] > 0) {
               this.updateAnimal();
               this.animateAnimal();
            }
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: af () void
   private void updateAnimal() {
      this.entityData[9579 + this.currentVillager]++;
      if (this.entityData[9579 + this.currentVillager] >= this.game.pd0ByteTables[4][this.entityData[9557 + this.currentVillager] & 0xFF]) {
         this.entityData[9579 + this.currentVillager] = 0;
         if (this.entityData[9557 + this.currentVillager] == 37 + this.entityData[9535 + this.currentVillager]) {
            this.applyDamageAt(
               this.entityData[9359 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + this.entityData[9491 + this.currentVillager]],
               this.entityData[9381 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + this.currentVillager]],
               2,
               this.entityData[9601 + this.currentVillager] > 0 ? 1 : 2,
               this.entityData[9601 + this.currentVillager] > 0 ? this.entityData[9601 + this.currentVillager] : -this.entityData[9601 + this.currentVillager],
               0,
               this.recordBlob[this.recordStarts[182] + this.entityData[9535 + this.currentVillager]] * (2 + this.game.randRange(255, 3)) / 4,
               0
            );
         }
      }

      if (this.entityData[9403 + this.currentVillager] == 0 && this.entityData[9425 + this.currentVillager] == 0) {
         this.entityData[9623 + this.currentVillager] = 0;
         if (this.entityData[9447 + this.currentVillager] > 5) {
            if (this.entityData[9447 + this.currentVillager] > 19) {
               this.entityData[9447 + this.currentVillager] = (byte)(this.entityData[9447 + this.currentVillager] - 19);
               this.setAnimalTarget(
                  this.entityData[0 + this.entityData[9447 + this.currentVillager]],
                  this.entityData[101 + this.entityData[9447 + this.currentVillager]],
                  this.entityData[9447 + this.currentVillager],
                  0
               );
            } else {
               this.retargetAnimal(false);
               this.entityData[9557 + this.currentVillager] = (byte)(10 + this.entityData[9535 + this.currentVillager]);
               this.entityData[9579 + this.currentVillager] = 0;
            }

            this.entityData[9447 + this.currentVillager] = 0;
         } else if ((
               this.game.tickCounter % this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]] == 0
                  || (this.entityData[9557 + this.currentVillager] & 255) <= 7
            )
            && (this.game.missionIndex != 3 || this.scriptEventIndex < 5)) {
            this.spiralSearchTile(6, 6, this.entityData[9359 + this.currentVillager], this.entityData[9381 + this.currentVillager]);
            if (this.entityData[9601 + this.currentVillager] != 0) {
               if (this.entityData[9601 + this.currentVillager] > 0) {
                  if (this.entityData[1919 + this.entityData[9601 + this.currentVillager]] > 0
                     && this.occupancyGrid[this.entityData[101 + this.entityData[9601 + this.currentVillager]]][this.entityData[0
                           + this.entityData[9601 + this.currentVillager]]]
                        == this.entityData[9601 + this.currentVillager]) {
                     this.entityData[9645 + this.currentVillager] = this.entityData[0 + this.entityData[9601 + this.currentVillager]];
                     this.entityData[9667 + this.currentVillager] = this.entityData[101 + this.entityData[9601 + this.currentVillager]];
                     this.entityData[9689 + this.currentVillager] = 0;
                     if (this.distanceTo(this.entityData[9359 + this.currentVillager], this.entityData[9381 + this.currentVillager], 101) > 59
                        && (this.game.missionIndex != 3 || this.scriptEventIndex != 4)) {
                        this.entityData[9601 + this.currentVillager] = 0;
                        this.entityData[9645 + this.currentVillager] = this.entityData[9359 + this.currentVillager];
                        this.entityData[9667 + this.currentVillager] = this.entityData[9381 + this.currentVillager];
                     }
                  } else {
                     this.entityData[9601 + this.currentVillager] = 0;
                  }
               } else if (this.entityData[8505 + -this.entityData[9601 + this.currentVillager]] <= 0) {
                  this.entityData[9601 + this.currentVillager] = 0;
               }
            }
         }

         this.distOrTargetXZ = this.distanceTo(this.entityData[9359 + this.currentVillager], this.entityData[9381 + this.currentVillager], 101);
         this.moveAnimal();
      } else {
         this.entityData[9557 + this.currentVillager] = (byte)(2 + this.entityData[9535 + this.currentVillager]);
      }
   }

   // $VF: renamed from: ag () void
   private void moveAnimal() {
      if (this.distOrTargetXZ / 10 - (this.entityData[9601 + this.currentVillager] != 0 ? 1 : 0) > 0) {
         if (this.entityData[9711 + this.currentVillager] >= this.entityData[9689 + this.currentVillager]) {
            if (this.game.tickCounter % this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]] == 0
               || this.entityData[9601 + this.currentVillager] != 0) {
               this.entityData[9689 + this.currentVillager] = this.entityData[9711 + this.currentVillager] = 0;
               this.entityData[9689 + this.currentVillager] = this.findPath(
                  this.entityData[9381 + this.currentVillager],
                  this.entityData[9359 + this.currentVillager],
                  this.entityData[9667 + this.currentVillager],
                  this.entityData[9645 + this.currentVillager],
                  0,
                  101
               );
               if (this.entityData[9689 + this.currentVillager] == 0 && this.pathBlockedFlags[2]) {
                  this.retargetAnimal(false);
               }
            }

            if (this.entityData[9557 + this.currentVillager] > 7
               || this.entityData[9711 + this.currentVillager] >= this.entityData[9689 + this.currentVillager]) {
               this.entityData[9557 + this.currentVillager] = (byte)(10 + this.entityData[9535 + this.currentVillager]);
               this.entityData[9579 + this.currentVillager] = 0;
            }
         }

         if (this.entityData[9711 + this.currentVillager] < this.entityData[9689 + this.currentVillager]) {
            if (this.occupancyGrid[this.animalPaths[1][this.currentVillager][this.entityData[9711 + this.currentVillager]]][this.animalPaths[0][this.currentVillager][this.entityData[9711
                  + this.currentVillager]]]
               != 0) {
               this.entityData[9689 + this.currentVillager] = this.entityData[9711 + this.currentVillager] = 0;
               this.entityData[9557 + this.currentVillager] = (byte)(10 + this.entityData[9535 + this.currentVillager]);
               this.entityData[9579 + this.currentVillager] = 0;
               return;
            }

            this.entityData[9491 + this.currentVillager] = this.recordBlob[this.recordStarts[4]
               + (this.animalPaths[0][this.currentVillager][this.entityData[9711 + this.currentVillager]] - this.entityData[9359 + this.currentVillager] + 1)
                  * 3
               + this.animalPaths[1][this.currentVillager][this.entityData[9711 + this.currentVillager]]
               - this.entityData[9381 + this.currentVillager]
               + 1];
            this.entityData[9557 + this.currentVillager] = (byte)(2 + this.entityData[9535 + this.currentVillager]);
            this.occupancyGrid[this.entityData[9381 + this.currentVillager]
               + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + this.currentVillager]]][this.entityData[9359 + this.currentVillager]
               + this.recordBlob[this.recordStarts[1] + this.entityData[9491 + this.currentVillager]]] = -128;
            this.entityData[9711 + this.currentVillager]++;
            this.entityData[9447 + this.currentVillager] = 0;
            return;
         }
      } else {
         if ((this.entityData[9557 + this.currentVillager] & 255) <= 7) {
            this.entityData[9579 + this.currentVillager] = 0;
         }

         if (this.entityData[9579 + this.currentVillager] == 0) {
            if (this.entityData[9447 + this.currentVillager] == 0) {
               this.pickAnimalState();
            }

            if (this.entityData[9601 + this.currentVillager] == 0 && this.entityData[9447 + this.currentVillager] < 19) {
               this.entityData[9447 + this.currentVillager]++;
               return;
            }

            this.entityData[9447 + this.currentVillager] = 0;
            this.entityData[9491 + this.currentVillager] = (byte)(this.distOrTargetXZ % 10);
         }
      }
   }

   // $VF: renamed from: ah () void
   private void animateAnimal() {
      if ((this.entityData[9557 + this.currentVillager] & 255) <= 7) {
         this.scratchIntW = this.entityData[9491 + this.currentVillager] & 1;
         this.entityData[9623 + this.currentVillager]++;
         this.entityData[9403 + this.currentVillager] = this.recordBlob[this.recordStarts[5 + this.scratchIntW]
            + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2]
               + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
            + (
                  this.recordBlob[this.recordStarts[15]
                        + this.scratchIntW * 9
                        + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
                     + this.entityData[9623 + this.currentVillager] * this.recordBlob[this.recordStarts[1] + 0 + this.entityData[9491 + this.currentVillager]]
               )
               % this.recordBlob[this.recordStarts[15]
                  + this.scratchIntW * 9
                  + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]];
         this.entityData[9425 + this.currentVillager] = this.recordBlob[this.recordStarts[7 + this.scratchIntW]
            + this.recordBlob[this.recordStarts[12 + this.scratchIntW * 2]
               + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
            + (
                  this.recordBlob[this.recordStarts[15]
                        + this.scratchIntW * 9
                        + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
                     + this.entityData[9623 + this.currentVillager] * this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + this.currentVillager]]
               )
               % this.recordBlob[this.recordStarts[15]
                  + this.scratchIntW * 9
                  + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]];
         if (this.entityData[9623 + this.currentVillager]
            == (
                  this.recordBlob[this.recordStarts[15]
                        + this.scratchIntW * 9
                        + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
                     + 1
               )
               / 2) {
            this.occupancyGrid[this.entityData[9381 + this.currentVillager]][this.entityData[9359 + this.currentVillager]] = 0;
            this.entityData[9359 + this.currentVillager] = (byte)(
               this.entityData[9359 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 0 + this.entityData[9491 + this.currentVillager]]
            );
            this.entityData[9381 + this.currentVillager] = (byte)(
               this.entityData[9381 + this.currentVillager] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + this.currentVillager]]
            );
            if ((
                     this.recordBlob[this.recordStarts[15]
                           + this.scratchIntW * 9
                           + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
                        & 1
                  )
                  == 0
               && this.recordBlob[this.recordStarts[1] + 0 + this.entityData[9491 + this.currentVillager]] < 0) {
               this.entityData[9403 + this.currentVillager] = (byte)(this.entityData[9403 + this.currentVillager] + 22);
            }

            if ((
                     this.recordBlob[this.recordStarts[15]
                           + this.scratchIntW * 9
                           + this.recordBlob[this.recordStarts[179] + this.entityData[9535 + this.currentVillager]]]
                        & 1
                  )
                  == 0
               && this.recordBlob[this.recordStarts[1] + 8 + this.entityData[9491 + this.currentVillager]] < 0) {
               this.entityData[9425 + this.currentVillager] = (byte)(this.entityData[9425 + this.currentVillager] + 16);
            }

            this.occupancyGrid[this.entityData[9381 + this.currentVillager]][this.entityData[9359 + this.currentVillager]] = (byte)(this.currentVillager + 101);
         }
      }
   }

   // $VF: renamed from: ai () void
   private void pickAnimalState() {
      if (this.entityData[9601 + this.currentVillager] == 0) {
         this.scratchIntX = this.game.randRange(16777215, 100);
         this.entityData[9557 + this.currentVillager] = this.recordBlob[this.recordStarts[184]
            + this.scratchIntX / 50 * 3
            + this.entityData[9535 + this.currentVillager]];
      } else {
         this.entityData[9557 + this.currentVillager] = (byte)(37 + this.entityData[9535 + this.currentVillager]);
      }

      if (this.game.missionIndex == 4 && this.entityData[9469 + this.currentVillager] == 2) {
         this.entityData[9469 + this.currentVillager] = 3;
      }
   }

   // $VF: renamed from: e (boolean) void
   private void retargetAnimal(boolean var1) {
      boolean var2 = this.game.missionIndex != 3
         || this.entityData[9469 + this.currentVillager] != 0
         || this.entityData[9359 + this.currentVillager] > 27
         || this.entityData[9359 + this.currentVillager] < 20
         || this.entityData[9381 + this.currentVillager] > 6;
      this.entityData[9689 + this.currentVillager] = 0;
      this.scratchIntX = this.game.randRange(16777215, 63);
      this.scratchIntW = this.recordBlob[this.recordStarts[201] + this.game.missionIndex] + this.entityData[9469 + this.currentVillager];
      this.taskDurationB = this.recordBlob[this.recordStarts[203] + this.game.missionIndex];
      this.entityData[9645 + this.currentVillager] = (byte)(
         !var2
            ? this.entityData[9359 + this.currentVillager]
            : clamp(
               this.entityData[9359 + this.currentVillager] + (var1 ? this.recordBlob[this.recordStarts[1] + this.dirByteE] : (this.scratchIntX & 7) - 4),
               var1 ? 0 : this.recordBlob[this.recordStarts[199] + this.scratchIntW] - this.taskDurationB,
               var1 ? this.game.mapWidth - 1 : this.recordBlob[this.recordStarts[199] + this.scratchIntW] + this.taskDurationB
            )
      );
      this.entityData[9667 + this.currentVillager] = (byte)(
         !var2
            ? this.entityData[9381 + this.currentVillager]
            : clamp(
               this.entityData[9381 + this.currentVillager]
                  + (var1 ? this.recordBlob[this.recordStarts[1] + 8 + this.dirByteE] : (this.scratchIntX >> 3 & 7) - 4),
               var1 ? 0 : this.recordBlob[this.recordStarts[200] + this.scratchIntW] - this.taskDurationB,
               var1 ? this.game.mapHeight - 1 : this.recordBlob[this.recordStarts[200] + this.scratchIntW] + this.taskDurationB
            )
      );
      if (this.occupancyGrid[this.entityData[9667 + this.currentVillager]][this.entityData[9645 + this.currentVillager]] != 0 && var2) {
         this.spiralSearchTile(2, 127, this.entityData[9645 + this.currentVillager], this.entityData[9667 + this.currentVillager]);
      }
   }

   // $VF: renamed from: i () void
   final void tickDirector() {
      for (this.nodeLoopAM = 0; this.nodeLoopAM < 50; this.nodeLoopAM++) {
         if (this.entityData[9833 + this.nodeLoopAM] != 0) {
            if ((this.game.tickCounter & 127) == 1 && this.entityData[9833 + this.nodeLoopAM] == 6) {
               if (this.entityData[9883 + this.nodeLoopAM] > 0) {
                  this.entityData[9883 + this.nodeLoopAM]--;
               }
            } else if ((this.game.tickCounter & 511) == 1
               && this.entityData[9833 + this.nodeLoopAM] != 7
               && this.entityData[9883 + this.nodeLoopAM] < this.recordBlob[this.recordStarts[110] + this.entityData[9833 + this.nodeLoopAM]]) {
               this.entityData[9883 + this.nodeLoopAM]++;
            }

            if ((this.game.tickCounter & 7) == (this.nodeLoopAM & 7)) {
               this.updateResourceNode();
            }

            for (this.scratchIntW = 0; this.scratchIntW <= 3; this.scratchIntW++) {
               if (this.entityData[9883 + this.nodeLoopAM]
                  <= this.recordBlob[this.recordStarts[110] + this.entityData[9833 + this.nodeLoopAM]]
                     * this.recordBlob[this.recordStarts[85] + this.scratchIntW]
                     / 10) {
                  if (this.entityData[9933 + this.nodeLoopAM] != this.scratchIntW) {
                     this.game.markTileRect(this.entityData[9733 + this.nodeLoopAM], this.entityData[9783 + this.nodeLoopAM], 1, 1);
                  }

                  this.entityData[9933 + this.nodeLoopAM] = (byte)this.scratchIntW;
                  break;
               }
            }
         }
      }

      if (!this.spreadDiseaseAndChiefCheck()) {
         if (this.game.missionIndex == 4) {
            if (this.mission4ChiefEvent()) {
               return;
            }
         } else if (this.fireMissionEvents()) {
            return;
         }

         this.checkWinLose();
         this.updateTutorialHints();
         if (this.game.missionIndex <= 4) {
            this.currentVillager = this.recordBlob[this.recordStarts[302] + this.game.missionIndex] + this.scratchByteL;
            if (this.rankShiftK >= 50
               && this.currentVillager < this.recordBlob[this.recordStarts[302] + this.game.missionIndex + 1]
               && this.game.tickCounter >= this.recordBlob[this.recordStarts[299] + this.currentVillager] * 100) {
               this.scratchByteL = (byte)(
                  this.scratchByteL + (this.game.tickCounter >= this.recordBlob[this.recordStarts[299] + this.currentVillager + 1] * 100 ? 2 : 1)
               );
               this.updateProgressLevels();
               this.setGroupMembers(0, 0, 3);
               this.game.advanceStoryStep(this.recordBlob[this.recordStarts[301] + this.currentVillager]);
            }
         }
      }
   }

   // $VF: renamed from: aj () void
   private void updateResourceNode() {
      if (this.entityData[9883 + this.nodeLoopAM] == 0) {
         if (this.entityData[9833 + this.nodeLoopAM] == 7) {
            this.entityData[9833 + this.nodeLoopAM] = 0;
            this.occupancyGrid[this.entityData[9783 + this.nodeLoopAM]][this.entityData[9733 + this.nodeLoopAM]] = 0;
            this.game.markTileRect(this.entityData[9733 + this.nodeLoopAM], this.entityData[9783 + this.nodeLoopAM], 1, 1);
         }

         if (this.entityData[9833 + this.nodeLoopAM] == 6) {
            this.entityData[9833 + this.nodeLoopAM] = 7;
            this.entityData[9883 + this.nodeLoopAM] = this.recordBlob[this.recordStarts[110] + 7];
            this.game.markTileRect(this.entityData[9733 + this.nodeLoopAM], this.entityData[9783 + this.nodeLoopAM], 1, 1);
         }

         this.updateGatherersAtNode();
      }
   }

   // $VF: renamed from: t () boolean
   private boolean spreadDiseaseAndChiefCheck() {
      for (this.nodeLoopAM = 0; this.nodeLoopAM < 2; this.nodeLoopAM++) {
         for (this.scratchIntW = 0; this.scratchIntW < 5 && this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][2] >= 0; this.scratchIntW++) {
            if ((this.game.tickCounter & 7) == 0) {
               this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][2]++;
            }

            if (this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][2] >= 100) {
               this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][2] = -1;
            } else {
               for (this.tribeIndex = -1; this.tribeIndex < 2; this.tribeIndex++) {
                  for (this.currentVillager = -1; this.currentVillager < 2; this.currentVillager++) {
                     this.scratchIntC = this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][0] + this.tribeIndex;
                     this.scratchIntD = this.game.aiTargets[this.nodeLoopAM][this.scratchIntW][1] + this.currentVillager;
                     if (this.scratchIntC >= 0 && this.scratchIntC < this.game.mapWidth && this.scratchIntD >= 0 && this.scratchIntD < this.game.mapHeight) {
                        this.targetYA = this.occupancyGrid[this.scratchIntD][this.scratchIntC];
                        if (this.targetYA >= 1 && this.targetYA <= 100 && this.entityData[1414 + this.targetYA] >= 12) {
                           this.entityData[7979 + this.targetYA] = (byte)(this.entityData[7979 + this.targetYA] | 4);
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.game.missionIndex != 3
         && (this.game.missionIndex != 2 || this.scriptEventIndex <= 2)
         && (this.entityData[1919 + this.tribeStats[0][17]] == 0 || this.entityData[1414 + this.tribeStats[0][17]] < 12)
         && !this.game.menuItemActivated
         && this.tribeStats[0][11] > 0
         && this.scriptEventIndex < 50
         && this.game.endgameTimer == 0
         && this.rankShiftK >= 50) {
         for (this.currentVillager = 1; this.currentVillager <= 50; this.currentVillager++) {
            if (this.entityData[1919 + this.currentVillager] > 0 && this.entityData[1414 + this.currentVillager] >= 12) {
               this.game.menuItemActivated = true;
               this.game.a_byte_byte_byte_void((byte)18, (byte)this.currentVillager, (byte)this.currentVillager);
               return true;
            }
         }
      }

      return false;
   }

   // $VF: renamed from: u () boolean
   private boolean mission4ChiefEvent() {
      if (this.entityData[1919 + this.tribeStats[0][17]] > 0 && this.entityData[1414 + this.tribeStats[0][17]] >= 12) {
         if ((this.game.effectBitfield[1][0][67] & 256) != 0 && (this.scratchByteR & 64) == 0 && this.rankShiftK >= 50) {
            this.setGroupMembers(this.tribeStats[0][17], 0, 1);
            this.game.advanceStoryStep(51);
            this.scratchByteR = (byte)(this.scratchByteR | 64);
            return true;
         }

         if (this.scriptEventIndex > 0 && this.scriptEventIndex < 3 && this.game.tickCounter >= 3000 + this.scriptEventIndex * 7000 && this.rankShiftK >= 50) {
            this.pickNeedyCouncil(false);

            for (this.eventRectBaseN = 0; this.eventRectBaseN < 4; this.eventRectBaseN++) {
               this.game.storyActors[this.eventRectBaseN + 1] = this.needyListE[this.eventRectBaseN][0] != 0
                  ? this.needyListE[this.eventRectBaseN][0]
                  : this.needyListE[0][0];
            }

            this.game.advanceStoryStep(this.scriptEventIndex == 1 ? 53 : 54);
            this.scriptEventIndex = (byte)(this.scriptEventIndex + 50);
            return true;
         }
      }

      return false;
   }

   // $VF: renamed from: v () boolean
   private boolean fireMissionEvents() {
      if (this.scriptEventIndex < this.recordBlob[this.recordStarts[291] + this.game.missionIndex]
         && (this.game.missionIndex == 3 || this.entityData[1919 + this.tribeStats[0][17]] > 0 && this.entityData[1414 + this.tribeStats[0][17]] >= 12)) {
         this.eventRectBaseN = this.recordBlob[this.recordStarts[287]
            + this.recordBlob[this.recordStarts[288] + this.game.missionIndex]
            + this.scriptEventIndex];
         if (this.tribeStats[1][11] <= 0 && this.eventRectBaseN == 3 && this.timerOrCountF == 1000000) {
            this.timerOrCountF = this.game.tickCounter;
         }

         if (this.eventRectBaseN >= 3 && this.eventRectBaseN <= 9) {
            if (this.eventRectBaseN == 7) {
               if (this.game.missionIndex != 3 || this.scriptEventIndex != 2 || this.rankShiftK >= 50) {
                  for (this.nodeLoopAM = 1; this.nodeLoopAM <= 50; this.nodeLoopAM++) {
                     if (this.entityData[1919 + this.nodeLoopAM] > 0
                        && this.entityData[1414 + this.nodeLoopAM] >= 12
                        && this.entityData[202 + this.nodeLoopAM] == 0
                        && this.entityData[303 + this.nodeLoopAM] == 0) {
                        this.fireScriptedEvent(this.nodeLoopAM, 0, this.entityData[0 + this.nodeLoopAM], this.entityData[101 + this.nodeLoopAM]);
                        if (this.scriptEventIndex >= 50) {
                           if (this.game.missionIndex == 3) {
                              for (this.nodeLoopAM = 0; this.nodeLoopAM < 5; this.nodeLoopAM++) {
                                 if (this.game.storyActors[this.nodeLoopAM] != 0) {
                                    this.stopAtTile(this.game.storyActors[this.nodeLoopAM], true);
                                 }
                              }
                           }

                           return true;
                        }
                     }
                  }
               }
            } else if (this.eventRectBaseN == 3 && this.game.tickCounter - this.timerOrCountF > 40
               || this.timerOrCountF > 2 && this.eventRectBaseN == 4
               || this.game.tickCounter - this.timerOrCountF > (this.game.missionIndex < 2 ? 30 : 120) && this.eventRectBaseN == 5
               || this.eventRectBaseN == 6 && this.tribeStats[1][14] < 5
               || this.eventRectBaseN == 8 && this.entityData[8536] == 0) {
               if (this.game.missionIndex < 2) {
                  this.slotScanI = (byte)(this.eventRectBaseN == 4 ? 3 : 0);
               }

               if (this.eventRectBaseN == 8) {
                  this.game.resetVillagerPositions(58, 30);
                  this.game.spawnTribe = 1;
                  this.spawnWaveUnits(3, 57, 30, (byte)0, (byte)9);
                  this.game.spawnTribe = 2;

                  for (this.eventRectBaseN = 1; this.eventRectBaseN < 9; this.eventRectBaseN++) {
                     if (this.entityData[1919 + this.eventRectBaseN] > 0) {
                        if (this.entityData[1515 + this.eventRectBaseN] == 0) {
                           if (this.game.storyActors[4] < 1) {
                              this.game.storyActors[4] = (byte)this.eventRectBaseN;
                           }
                        } else if (this.game.storyActors[2] < 1) {
                           this.game.storyActors[2] = (byte)this.eventRectBaseN;
                        } else if (this.game.storyActors[3] < 1) {
                           this.game.storyActors[3] = (byte)this.eventRectBaseN;
                        }

                        this.entityData[606 + this.eventRectBaseN] = (byte)(3 + (this.entityData[1515 + this.eventRectBaseN] << 2));
                     }
                  }

                  this.game.spawnVillager((byte)0, (byte)56, (byte)30, (byte)2, (byte)0);
                  this.game.spawnVillager((byte)0, (byte)55, (byte)31, (byte)2, (byte)0);
                  this.tribeStats[0][19] = (short)(this.tribeStats[0][19] - 5);
               } else if (this.eventRectBaseN == 6) {
                  this.spawnWaveUnits(8, 21, 72, (byte)1, (byte)3);
                  this.spawnWaveUnits(8, 21, 75, (byte)1, (byte)3);

                  for (this.eventRectBaseN = 51; this.eventRectBaseN <= 100; this.eventRectBaseN++) {
                     if (this.entityData[1919 + this.eventRectBaseN] > 0) {
                        this.entityData[6161 + this.eventRectBaseN] = 2;
                        this.entityData[4747 + this.eventRectBaseN] = -1;
                        this.entityData[3131 + this.eventRectBaseN] = this.entityData[8384];
                        this.entityData[3232 + this.eventRectBaseN] = this.entityData[8445];
                     }
                  }
               } else if (this.game.missionIndex == 5) {
                  this.setGroupMembers(0, 0, 1);
               } else {
                  this.pickNeedyCouncil(this.game.missionIndex == 2);

                  for (this.eventRectBaseN = 0; this.eventRectBaseN < 4; this.eventRectBaseN++) {
                     this.game.storyActors[this.eventRectBaseN + 1] = this.needyListE[this.eventRectBaseN][0] != 0
                        ? this.needyListE[this.eventRectBaseN][0]
                        : this.needyListE[0][0];
                  }
               }

               this.game
                  .advanceStoryStep(
                     this.recordBlob[this.recordStarts[286] + this.recordBlob[this.recordStarts[288] + this.game.missionIndex] + this.scriptEventIndex]
                  );
               this.scriptEventIndex = (byte)(this.scriptEventIndex + 50);
               return true;
            }
         }
      }

      return false;
   }

   // $VF: renamed from: ak () void
   private void checkWinLose() {
      if (this.tribeStats[1][14] <= 0 && (this.recordBlob[this.recordStarts[279] + this.game.missionIndex] & 1) != 0) {
         if (this.game.endgameTimer == 0) {
            this.game.endgameTimer = 1;
         }
      } else if ((this.tribeStats[0][11] <= 0 || this.entityData[8506] <= 0 && (this.recordBlob[this.recordStarts[279] + this.game.missionIndex] & 2) != 0)
         && this.game.endgameTimer == 0) {
         this.game.endgameTimer = 50;
      }

      if (this.tribeStats[1][14] <= 4 && this.tribeStats[1][11] <= 0) {
         switch (this.game.missionIndex) {
            case 2:
               if (this.scriptEventIndex < 2) {
                  this.scriptEventIndex = 2;
               }
               break;
            case 5:
               if (this.game.endgameTimer == 0) {
                  this.game.endgameTimer = 1;
                  return;
               }

               if (this.game.endgameTimer == 20) {
                  this.game.endgameTimer = 0;
                  this.game.loadNextLevel();
                  return;
               }
         }
      }
   }

   // $VF: renamed from: al () void
   private void updateTutorialHints() {
      if ((this.game.newGameFlag & 1) == 1
         && this.game.missionIndex < 2
         && this.hintStepIndex < this.recordBlob[this.recordStarts[294] + this.scriptEventIndex]
         && !this.game.menuItemActivated) {
         if (this.needSlotJ < 120 - this.recordBlob[this.recordStarts[298] + this.hintStepIndex]) {
            switch (this.recordBlob[this.recordStarts[296] + this.hintStepIndex]) {
               case 1:
                  for (this.currentVillager = 1; this.currentVillager <= 30; this.currentVillager++) {
                     if (!this.game.chiefMode
                        && this.entityData[8505 + this.currentVillager] > 0
                        && this.entityData[8749 + this.currentVillager] == this.recordBlob[this.recordStarts[297] + this.hintStepIndex]) {
                        this.needSlotJ = (byte)(120 - this.recordBlob[this.recordStarts[298] + this.hintStepIndex]);
                        break;
                     }
                  }

                  if (this.needSlotJ < 120 - this.recordBlob[this.recordStarts[298] + this.hintStepIndex]) {
                     this.needSlotJ = 0;
                  }
                  break;
               case 2:
                  for (this.currentVillager = 1; this.currentVillager <= 50; this.currentVillager++) {
                     if (this.entityData[1919 + this.currentVillager] > 0
                        && this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]]
                           == this.recordBlob[this.recordStarts[297] + this.hintStepIndex]) {
                        this.needSlotJ = (byte)(120 - this.recordBlob[this.recordStarts[298] + this.hintStepIndex]);
                        break;
                     }
                  }
               case 3:
               default:
                  break;
               case 4:
                  if (this.tribeStats[1][11] == 0 && this.game.placeConfirmFlagN) {
                     this.needSlotJ = (byte)(120 - this.recordBlob[this.recordStarts[298] + this.hintStepIndex]);
                  }
            }
         }

         if ((this.game.tickCounter & 7) == 0) {
            this.needSlotJ++;
         }

         if (this.needSlotJ
            >= (this.recordBlob[this.recordStarts[296] + this.hintStepIndex] == 0 ? this.recordBlob[this.recordStarts[298] + this.hintStepIndex] : 120)) {
            this.needSlotJ = 0;
            this.setGroupMembers(0, 0, 3);
            this.game.advanceStoryStep(this.recordBlob[this.recordStarts[295] + this.hintStepIndex]);
            this.hintStepIndex++;
            if (this.hintStepIndex == 11) {
               this.nextWaveTickAQ = this.game.tickCounter + 500;
            }
         }
      }
   }

   // $VF: renamed from: a (int, boolean) void
   private void stopAtTile(int var1, boolean var2) {
      this.entityData[3131 + var1] = this.entityData[0 + var1];
      this.entityData[3232 + var1] = this.entityData[101 + var1];
      if ((this.entityData[2929 + var1] & 255) <= 7
         && (this.entityData[1111 + var1] & 15)
            < (this.recordBlob[this.recordStarts[15] + (this.entityData[606 + var1] & 1) * 9 + this.entityData[1010 + var1] - this.entityData[7777 + var1]] + 1)
               / 2
         && this.entityData[1313 + var1] >= 0
         && this.entityData[808 + var1] > 0) {
         this.entityData[3131 + var1] = (byte)(this.entityData[3131 + var1] + this.recordBlob[this.recordStarts[1] + this.entityData[606 + var1]]);
         this.entityData[3232 + var1] = (byte)(this.entityData[3232 + var1] + this.recordBlob[this.recordStarts[1] + 8 + this.entityData[606 + var1]]);
      }

      this.entityData[808 + var1] = 0;
      if (var2) {
         this.entityData[6262 + var1] = this.entityData[3131 + var1];
         this.entityData[6363 + var1] = this.entityData[3232 + var1];
         this.setTask(var1, (byte)69);
      }
   }

   // $VF: renamed from: j () void
   final void updateProgressLevels() {
      for (this.scratchIntW = 0; this.scratchIntW < 12; this.scratchIntW++) {
         this.game.maxLevelPerType[this.scratchIntW] = this.recordBlob[this.recordStarts[113]
            + (this.game.missionIndex < 4 ? 0 : (this.game.missionIndex == 4 ? 12 : 24))
            + this.scratchIntW];
      }

      if (this.game.missionIndex <= 4) {
         for (this.scratchIntW = this.recordBlob[this.recordStarts[302] + this.game.missionIndex];
            this.scratchIntW < this.recordBlob[this.recordStarts[302] + this.game.missionIndex + 1];
            this.scratchIntW++
         ) {
            if (this.game.tickCounter < this.recordBlob[this.recordStarts[299] + this.scratchIntW] * 100) {
               return;
            }

            this.game.maxLevelPerType[this.recordBlob[this.recordStarts[300] + this.scratchIntW]]++;
         }
      }
   }

   // $VF: renamed from: k () void
   final void updateMusicMood() {
      if (this.game.mode == 1 && this.rankShiftK < 50) {
         this.game.musicMood = 2;
      } else if ((
            this.game.placeConfirmFlagN
               || this.game.placeConfirmFlagL
               || this.groupCountO >= 0
                  && this.groupCountO
                     == this.recordBlob[this.recordStarts[236] + this.recordBlob[this.recordStarts[238] + this.game.missionIndex] + this.groupSlotN] - 1
         )
         && this.game.mode == 1) {
         this.game.musicMood = 1;
      } else {
         this.game.musicMood = 0;
      }
   }

   // $VF: renamed from: am () void
   private void updateGatherersAtNode() {
      for (this.tribeIndex = 0; this.tribeIndex < 2; this.tribeIndex++) {
         for (this.currentVillager = this.tribeIndex * 50 + 1; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
            if (this.entityData[3232 + this.currentVillager] == this.entityData[9783 + this.nodeLoopAM]
               && (
                  this.entityData[3131 + this.currentVillager] == this.entityData[9733 + this.nodeLoopAM] || this.entityData[2828 + this.currentVillager] == 13
               )
               && this.entityData[2828 + this.currentVillager] >= 10
               && this.entityData[2828 + this.currentVillager] <= 16) {
               if (this.entityData[4444 + this.currentVillager] >= 0) {
                  this.entityData[4444 + this.currentVillager] = -1;
                  this.entityData[808 + this.currentVillager] = 0;
               }

               if ((this.entityData[2828 + this.currentVillager] == 16 || this.entityData[2828 + this.currentVillager] == 12)
                  && this.entityData[3636 + this.currentVillager] > 0) {
                  this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[160] + this.entityData[2828 + this.currentVillager]]);
                  this.retargetAfterIdle();
               }
            }
         }
      }
   }

   // $VF: renamed from: an () void
   private void findGatherTarget() {
      if (this.entityData[2828 + this.currentVillager] >= 10
         && this.entityData[2828 + this.currentVillager] <= 16
         && this.entityData[2828 + this.currentVillager] != 11
         && this.entityData[2828 + this.currentVillager] != 14) {
         this.entityData[3131 + this.currentVillager] = this.entityData[6262 + this.currentVillager];
         this.entityData[3232 + this.currentVillager] = this.entityData[6363 + this.currentVillager];
         if (this.canGatherAt(this.occupancyGrid[this.entityData[6363 + this.currentVillager]][this.entityData[6262 + this.currentVillager]])) {
            this.moveVillagerStep(1);
            return;
         }

         if (!this.findNearestResourceNode(false)) {
            if (this.entityData[3636 + this.currentVillager] > 0) {
               this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[160] + this.entityData[2828 + this.currentVillager]]);
               this.retargetAfterIdle();
               return;
            }

            if (this.entityData[2828 + this.currentVillager] == 12 || this.entityData[2828 + this.currentVillager] == 16) {
               this.setTask(this.currentVillager, (byte)60);
               this.entityData[6464 + this.currentVillager] = 12;
               this.entityData[4444 + this.currentVillager] = 0;
               return;
            }
         }
      } else {
         this.spiralSearchTile(
            0,
            19,
            clamp(this.entityData[0 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapWidth - 1),
            clamp(this.entityData[101 + this.currentVillager] - 3 + this.game.randRange(255, 6), 0, this.game.mapHeight - 1)
         );
      }
   }

   // $VF: renamed from: b (boolean) boolean
   private boolean findNearestResourceNode(boolean var1) {
      byte var2 = 0;
      byte var3 = 0;
      this.dirByteE = 12;

      for (this.nodeLoopAM = 0; this.nodeLoopAM < 50; this.nodeLoopAM++) {
         if (this.entityData[9833 + this.nodeLoopAM] != 0) {
            this.scratchByteZ = (byte)(this.entityData[9733 + this.nodeLoopAM] - this.entityData[this.fieldOffsets[var1 ? 0 : 62] + this.currentVillager]);
            this.scratchByteA = (byte)(this.entityData[9783 + this.nodeLoopAM] - this.entityData[this.fieldOffsets[var1 ? 1 : 63] + this.currentVillager]);
            this.distByteC = (byte)(
               this.recordBlob[this.recordStarts[53]
                     + (this.scratchByteA > -12 && this.scratchByteA < 12 ? 12 + this.scratchByteA : 0) * 25
                     + (this.scratchByteZ > -12 && this.scratchByteZ < 12 ? 12 + this.scratchByteZ : 0)]
                  / 10
            );
            if (this.distByteC <= (var1 ? this.entityData[4949 + this.currentVillager] : 9)
               && this.distByteC < this.dirByteE
               && this.canGatherAt((byte)(-this.nodeLoopAM - 61))) {
               var2 = this.entityData[9733 + this.nodeLoopAM];
               var3 = this.entityData[9783 + this.nodeLoopAM];
               this.dirByteE = this.distByteC;
            }
         }
      }

      if (this.dirByteE < 12) {
         this.entityData[3131 + this.currentVillager] = var2;
         this.entityData[3232 + this.currentVillager] = var3;
         this.moveVillagerStep(2);
         return true;
      } else {
         return false;
      }
   }

   // $VF: renamed from: a (int) int
   private int updateRitualQueue(int var1) {
      for (this.queueScanAN = 0; this.queueScanAN < 10; this.queueScanAN++) {
         if (this.game.buildingStores[this.tribeIndex][this.queueScanAN][11] > 0) {
            this.currentVillager = this.entityData[8810 + this.game.buildingStores[this.tribeIndex][this.queueScanAN][11]];
            if (this.currentVillager > 0 && this.entityData[2828 + this.currentVillager] == 58 && this.entityData[1313 + this.currentVillager] >= 0) {
               this.queuePosAO = 0;

               while (this.queuePosAO < 10 && this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO] <= 0) {
                  this.queuePosAO++;
               }

               if (this.queuePosAO != 10) {
                  this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[86] + this.queuePosAO]);
                  if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
                     this.retargetAfterIdle();
                  } else {
                     this.entityData[6161 + this.currentVillager] = 3;
                  }

                  this.game.buildingStores[this.tribeIndex][this.queueScanAN][10] = (byte)this.queuePosAO;
               }
            }
         }
      }

      for (this.queueScanAN = this.tribeIndex * 30 + 1; this.queueScanAN <= (this.tribeIndex + 1) * 30; this.queueScanAN++) {
         if (this.entityData[8505 + this.queueScanAN] > 0 && this.entityData[8749 + this.queueScanAN] == 8 && this.entityData[8810 + this.queueScanAN] >= 1) {
            this.currentVillager = this.entityData[8810 + this.queueScanAN];
            if (this.entityData[2828 + this.currentVillager] == 57
               && this.entityData[1313 + this.currentVillager] >= 0
               && this.entityData[2929 + this.currentVillager] != -109
               && (
                  this.entityData[8932 + this.queueScanAN] == 1
                     || this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager
               )) {
               this.queuePosAO = 0;

               while (
                  this.queuePosAO < 30
                     && this.game.pendingOrders[this.tribeIndex][0][this.queuePosAO] != this.queueScanAN
                     && this.game.pendingOrders[this.tribeIndex][0][this.queuePosAO] != 0
               ) {
                  this.queuePosAO++;
               }

               if (this.queuePosAO < 30 && this.game.pendingOrders[this.tribeIndex][0][this.queuePosAO] != 0) {
                  if (this.game.pendingOrders[this.tribeIndex][2][this.queuePosAO] >= 0) {
                     this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[87] + this.game.pendingOrders[this.tribeIndex][1][this.queuePosAO]]);
                     this.entityData[4040 + this.currentVillager] = (byte)this.queueScanAN;
                     this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] & 239);
                     if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
                        this.retargetAfterIdle();
                     } else {
                        this.entityData[6161 + this.currentVillager] = 3;
                     }

                     this.entityData[9237 + this.queueScanAN] = 1;
                  } else {
                     while (this.queuePosAO < 29) {
                        for (this.queueBuildingAP = 0; this.queueBuildingAP < 5; this.queueBuildingAP++) {
                           this.game.pendingOrders[this.tribeIndex][this.queueBuildingAP][this.queuePosAO] = this.game.pendingOrders[this.tribeIndex][this.queueBuildingAP][this.queuePosAO
                              + 1];
                        }

                        this.queuePosAO++;
                     }

                     this.game.pendingOrders[this.tribeIndex][0][29] = 0;
                     this.queueScanAN--;
                  }
               }
            }
         }
      }

      return var1;
   }

   // $VF: renamed from: f (boolean) void
   private void completeRitualStep(boolean var1) {
      for (this.queueScanAN = 0; this.queueScanAN < 10; this.queueScanAN++) {
         if (this.game.buildingStores[this.tribeIndex][this.queueScanAN][11] > 0
            && this.currentVillager == this.entityData[8810 + this.game.buildingStores[this.tribeIndex][this.queueScanAN][11]]) {
            this.queuePosAO = this.game.buildingStores[this.tribeIndex][this.queueScanAN][10];
            if (!var1) {
               this.entityData[3535 + this.currentVillager] = (byte)(this.recordBlob[this.recordStarts[88] + this.queuePosAO] - 5 + 8);
               this.entityData[3636 + this.currentVillager] = 1;
               return;
            }

            if (this.entityData[2828 + this.currentVillager] == 26) {
               this.tribeStats[this.tribeIndex][this.recordBlob[this.recordStarts[88] + this.queuePosAO]]++;
               this.game.refreshPanelIfMenuTarget(this.entityData[4444 + this.currentVillager]);
            } else {
               this.queueBuildingAP = 0;

               while (
                  this.queueBuildingAP < 30
                     && (
                        this.game.trapTable[0][this.queueBuildingAP] != this.game.buildingStores[this.tribeIndex][this.queueScanAN][11]
                           || this.game.trapTable[2][this.queueBuildingAP] != this.entityData[3131 + this.currentVillager]
                           || this.game.trapTable[3][this.queueBuildingAP] != this.entityData[3232 + this.currentVillager]
                     )
               ) {
                  this.queueBuildingAP++;
               }

               this.occupancyGrid[this.entityData[3232 + this.currentVillager]][this.entityData[3131 + this.currentVillager]] = (byte)(
                  -111 - this.tribeIndex * 3 * 2 - this.queuePosAO + 6
               );
               this.game.markTileRect(this.entityData[3131 + this.currentVillager], this.entityData[3232 + this.currentVillager], 1, 1);
               this.game.trapTable[0][this.queueBuildingAP] = 0;
               if (this.tribeIndex == 1) {
                  this.scratchByteR++;
               }
            }

            if (this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO] > 0) {
               this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO]--;
            }

            this.game.refreshPanelIfMenuTarget(this.game.buildingStores[this.tribeIndex][this.queueScanAN][11]);
            return;
         }
      }
   }

   // $VF: renamed from: ao () void
   private void clearSanctuaryQueue() {
      for (this.queueScanAN = this.tribeIndex * 30 + 1; this.queueScanAN <= (this.tribeIndex + 1) * 30; this.queueScanAN++) {
         if (this.entityData[8505 + this.queueScanAN] > 0
            && this.entityData[8749 + this.queueScanAN] == 8
            && this.entityData[8810 + this.queueScanAN] == this.currentVillager) {
            this.queuePosAO = 0;

            while (this.queuePosAO < 30 && this.game.pendingOrders[this.tribeIndex][0][this.queuePosAO] != this.queueScanAN) {
               this.queuePosAO++;
            }

            while (this.queuePosAO < 29) {
               for (this.queueBuildingAP = 0; this.queueBuildingAP < 5; this.queueBuildingAP++) {
                  this.game.pendingOrders[this.tribeIndex][this.queueBuildingAP][this.queuePosAO] = this.game.pendingOrders[this.tribeIndex][this.queueBuildingAP][this.queuePosAO
                     + 1];
               }

               this.queuePosAO++;
            }

            this.game.pendingOrders[this.tribeIndex][0][29] = 0;
            this.entityData[9237 + this.queueScanAN] = 0;
            return;
         }
      }
   }

   // $VF: renamed from: ap () void
   private void leaveSanctuary() {
      this.clearSanctuaryQueue();
      this.setTask(this.currentVillager, (byte)57);
      this.retargetAfterIdle();
      this.currentVillager = this.updateRitualQueue(this.currentVillager);
      this.distOrTargetXZ = this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0);
      this.updateMovement();
   }

   // $VF: renamed from: e (int, int) void
   private void clearSanctuaryTarget(int var1, int var2) {
      for (byte var3 = 0; var3 < 30 && this.game.pendingOrders[var1][0][var3] != 0; var3++) {
         if (this.game.pendingOrders[var1][2][var3] == var2 && this.game.pendingOrders[var1][1][var3] != 3) {
            this.game.pendingOrders[var1][2][var3] = -2;
         }
      }
   }

   // $VF: renamed from: aq () void
   private void tickEnemyAI() {
      if (this.scriptEventIndex < 50) {
         this.towerWorkerM = this.recordBlob[this.recordStarts[220] + this.game.missionIndex];
         this.targetYA = this.recordBlob[this.recordStarts[216] + this.game.missionIndex] * 500;
         if (this.tribeStats[this.tribeIndex][11] > 0 && this.waveTargetQByte < 1) {
            this.aiBuildRepair();
         }

         this.aiWaveControl();
         if (this.game.tickCounter % (this.recordBlob[this.recordStarts[264] + this.game.missionIndex] * 50) == 50) {
            for (this.queueScanAN = 0; this.queueScanAN < 10; this.queueScanAN++) {
               if ((this.queueBuildingAP = this.game.buildingStores[this.tribeIndex][this.queueScanAN][11]) > 0) {
                  this.queuePosAO = 0;

                  while (this.queuePosAO < 9 && this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO] == 0) {
                     this.queuePosAO++;
                  }

                  if (this.queuePosAO >= 9) {
                     for (this.queuePosAO = 0; this.queuePosAO < 2; this.queuePosAO++) {
                        for (this.currentVillager = this.queuePosAO * 3; this.currentVillager < 5; this.currentVillager++) {
                           if (this.tribeStats[this.tribeIndex][5 + this.recordBlob[this.recordStarts[109] + this.currentVillager]] != 0) {
                              this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.recordBlob[this.recordStarts[109] + this.currentVillager]] = this.recordBlob[this.recordStarts[265]
                                 + this.game.missionIndex * 5
                                 + this.queuePosAO];
                              break;
                           }
                        }
                     }

                     if (this.game.tickCounter > this.targetYA / 3
                        && (this.scratchByteR & 63) < this.recordBlob[this.recordStarts[268] + this.game.missionIndex]) {
                        for (this.queuePosAO = 2; this.queuePosAO < 5; this.queuePosAO++) {
                           this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO + 4] = this.recordBlob[this.recordStarts[265]
                              + this.game.missionIndex * 5
                              + this.queuePosAO];

                           for (this.scratchIntAS = 0;
                              this.scratchIntAS < this.game.buildingStores[this.tribeIndex][this.queueScanAN][this.queuePosAO + 4];
                              this.scratchIntAS++
                           ) {
                              this.spawnAt(266);
                              this.scratchIntAT = 0;

                              while (this.scratchIntAT < 29 && this.game.trapTable[0][this.scratchIntAT] != 0) {
                                 this.scratchIntAT++;
                              }

                              this.game.trapTable[0][this.scratchIntAT] = (byte)this.queueBuildingAP;
                              this.game.trapTable[2][this.scratchIntAT] = this.entityData[3131];
                              this.game.trapTable[3][this.scratchIntAT] = this.entityData[3232];
                              this.game.trapTable[1][this.scratchIntAT] = (byte)(this.queuePosAO + 4 + 14);
                              this.occupancyGrid[this.entityData[3232]][this.entityData[3131]] = -126;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (this.game.tickCounter % (this.recordBlob[this.recordStarts[269] + this.game.missionIndex] * 50) == 50 && this.game.tickCounter > this.targetYA / 2
            )
          {
            for (this.queueScanAN = this.tribeIndex * 30 + 1; this.queueScanAN <= (this.tribeIndex + 1) * 30; this.queueScanAN++) {
               if (this.entityData[8505 + this.queueScanAN] > 0 && this.entityData[8749 + this.queueScanAN] == 8) {
                  for (this.scratchIntAS = 0; this.scratchIntAS < this.recordBlob[this.recordStarts[270] + this.game.missionIndex]; this.scratchIntAS++) {
                     this.spawnAt(271);
                     this.scratchIntAT = 0;

                     while (this.scratchIntAT < 29 && this.game.pendingOrders[this.tribeIndex][0][this.scratchIntAT] != 0) {
                        this.scratchIntAT++;
                     }

                     this.game.pendingOrders[this.tribeIndex][0][this.scratchIntAT] = (byte)this.queueScanAN;
                     this.game.pendingOrders[this.tribeIndex][2][this.scratchIntAT] = this.entityData[3131];
                     this.game.pendingOrders[this.tribeIndex][3][this.scratchIntAT] = this.entityData[3232];
                     this.game.pendingOrders[this.tribeIndex][1][this.scratchIntAT] = 3;
                  }
               }
            }
         }
      }
   }

   // $VF: renamed from: n (int) void
   private void spawnAt(int var1) {
      this.currentVillager = 0;
      this.scratchIntW = this.recordStarts[var1]
         + (
               this.recordBlob[this.recordStarts[var1 + 1] + this.game.missionIndex]
                  + this.game
                     .randRange(
                        255,
                        this.recordBlob[this.recordStarts[var1 + 1] + this.game.missionIndex + 1]
                           - this.recordBlob[this.recordStarts[var1 + 1] + this.game.missionIndex]
                     )
            )
            * 4;
      this.spiralSearchTile(
         1,
         127,
         this.recordBlob[this.scratchIntW] + this.game.randRange(65535, this.recordBlob[this.scratchIntW + 2] - this.recordBlob[this.scratchIntW] + 1),
         this.recordBlob[this.scratchIntW + 1] + this.game.randRange(65535, this.recordBlob[this.scratchIntW + 3] - this.recordBlob[this.scratchIntW + 1] + 1)
      );
   }

   // $VF: renamed from: ar () void
   private void aiBuildRepair() {
      if ((this.game.tickCounter & 31) == 0 || this.game.tickCounter % (this.recordBlob[this.recordStarts[241] + this.game.missionIndex] * 10) == 0) {
         for (this.scratchIntAS = this.recordBlob[this.recordStarts[247] + this.game.missionIndex] & 255;
            this.scratchIntAS < (this.recordBlob[this.recordStarts[247] + this.game.missionIndex + 1] & 255);
            this.scratchIntAS++
         ) {
            this.scratchIntAT = -this.occupancyGrid[this.recordBlob[this.recordStarts[246] + this.scratchIntAS]][this.recordBlob[this.recordStarts[245]
               + this.scratchIntAS]];
            if (this.scratchIntAT >= 31 && this.scratchIntAT <= 60) {
               if (this.entityData[8749 + this.scratchIntAT] == this.recordBlob[this.recordStarts[243] + this.scratchIntAS]
                  && this.entityData[8505 + this.scratchIntAT] > 0
                  && this.entityData[8627 + this.scratchIntAT] == 0) {
                  if ((this.game.tickCounter & 31) == 0) {
                     if (this.entityData[8505 + this.scratchIntAT] < this.entityData[8566 + this.scratchIntAT] / 4) {
                        this.game.setEntityState(this.scratchIntAT, false);
                     }
                  } else if (this.entityData[9115 + this.scratchIntAT] < this.recordBlob[this.recordStarts[244] + this.scratchIntAS]) {
                     this.game.setEntityState(this.scratchIntAT, true);
                  }
               }
            } else if (this.scratchIntAT == 0 && (this.game.tickCounter & 31) != 0 && this.missionRectP == -1) {
               this.missionRectP = (byte)this.scratchIntAS;
            }
         }

         if (this.missionRectP != -1) {
            this.currentVillager = this.recordBlob[this.recordStarts[243] + (this.missionRectP & 255)];
            if (this.game
                  .a_byte_int_int_int_int_boolean_int_byte(
                     (byte)1,
                     this.recordBlob[this.recordStarts[138] + 416 + this.recordBlob[this.recordStarts[243] + (this.missionRectP & 255)]],
                     this.recordBlob[this.recordStarts[245] + (this.missionRectP & 255)],
                     this.recordBlob[this.recordStarts[246] + (this.missionRectP & 255)],
                     3,
                     true,
                     -1
                  )
               > 0) {
               this.missionRectP = -1;
            }
         }
      }
   }

   // $VF: renamed from: as () void
   private void aiWaveControl() {
      if (this.nextWaveTickAQ < this.game.tickCounter) {
         this.nextWaveTickAQ = (
               this.recordBlob[this.recordStarts[214] + this.game.missionIndex]
                  - this.recordBlob[this.recordStarts[215] + this.game.missionIndex] * this.game.selectedDifficulty
            )
            * 100;
         this.nextWaveTickAQ = this.game.tickCounter + this.nextWaveTickAQ / 4 + this.game.randRange(16777215, this.nextWaveTickAQ * 3 / 2);
      }

      if (this.scriptEventIndex >= this.recordBlob[this.recordStarts[217] + this.game.missionIndex]) {
         if ((this.game.tickCounter & 63) == 25) {
            this.scratchIntAT = this.recordStarts[218] + this.game.missionIndex * 4;
            this.waveTargetQByte = -1;

            for (this.currentVillager = 1; this.currentVillager <= 50; this.currentVillager++) {
               if (this.entityData[1919 + this.currentVillager] > 0
                  && this.entityData[1414 + this.currentVillager] >= 12
                  && this.entityData[0 + this.currentVillager] >= this.recordBlob[this.scratchIntAT]
                  && this.entityData[101 + this.currentVillager] >= this.recordBlob[this.scratchIntAT + 1]
                  && this.entityData[0 + this.currentVillager] <= this.recordBlob[this.scratchIntAT + 2]
                  && this.entityData[101 + this.currentVillager] <= this.recordBlob[this.scratchIntAT + 3]) {
                  this.waveTargetQByte = (byte)this.currentVillager;

                  for (this.scratchIntW = 51; this.scratchIntW <= 100; this.scratchIntW++) {
                     if (this.entityData[1919 + this.scratchIntW] > 0
                        && this.entityData[1414 + this.scratchIntW] >= 12
                        && (this.entityData[7979 + this.scratchIntW] & 64) == 0) {
                        this.entityData[7979 + this.scratchIntW] = (byte)(this.entityData[7979 + this.scratchIntW] | 32);
                     }
                  }
                  break;
               }
            }
         }

         for (this.currentVillager = this.tribeIndex * 50 + 1 + 0; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
            this.scratchIntAT = (byte)(this.currentVillager - this.tribeIndex * 50 - 1);
            if (this.entityData[1919 + this.currentVillager] > 0 && this.entityData[1414 + this.currentVillager] >= 12) {
               if ((this.entityData[7979 + this.currentVillager] & 64) != 0) {
                  if (this.groupCountO < 0
                     && (this.entityData[7979 + this.currentVillager] & 2) != 0
                     && this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) < 29
                     && this.entityData[202 + this.currentVillager] == 0
                     && this.entityData[303 + this.currentVillager] == 0) {
                     this.entityData[3131 + this.currentVillager] = this.entityData[8384];
                     this.entityData[3232 + this.currentVillager] = this.entityData[8445];
                     this.moveVillagerStep(1);
                  }
               } else {
                  this.aiAssignRoles();
               }
            } else if ((this.game.tickCounter & 63) == (this.currentVillager & 31) && this.aiRank[this.scratchIntAT] < this.towerWorkerM) {
               for (this.waveEnlistedAU = this.tribeIndex * 50 + 1; this.waveEnlistedAU <= (this.tribeIndex + 1) * 50; this.waveEnlistedAU++) {
                  if (this.aiRank[this.waveEnlistedAU - this.tribeIndex * 50 - 1] >= this.towerWorkerM
                     && this.entityData[1919 + this.currentVillager] > 0
                     && this.entityData[1414 + this.currentVillager] >= 12) {
                     this.aiRank[this.waveEnlistedAU - this.tribeIndex * 50 - 1] = this.aiRank[this.scratchIntAT];
                     this.aiRank[this.scratchIntAT] = this.towerWorkerM;
                     break;
                  }
               }
            }
         }
      }

      if (this.waveTargetQByte < 0 && this.game.missionIndex != 3 && this.game.tickCounter == this.nextWaveTickAQ) {
         this.waveStartTickAR = this.nextWaveTickAQ;
         this.aiEnlistWave();
      }

      if (this.groupCountO >= 0 && (this.game.tickCounter & 15) == 0) {
         this.aiAdvanceWave();
      }

      this.waveEnlistedAU = -1;
   }

   // $VF: renamed from: at () void
   private void aiAssignRoles() {
      if (this.waveTargetQByte < 0) {
         if (this.aiRank[this.scratchIntAT] < this.towerWorkerM) {
            this.scratchIntX = this.recordBlob[this.recordStarts[221 + this.game.missionIndex] + this.towerWorkerM * 3 + this.aiRank[this.scratchIntAT]];
            this.scratchIntX = this.game.missionIndex >= 4
                  && this.game.selectedDifficulty != 0
                  && (this.scratchIntAT >= this.recordBlob[this.recordStarts[242] + this.game.missionIndex] || this.scratchIntX != 2)
               ? (this.scratchIntX == 0 ? 4 : 5)
               : this.scratchIntX;
            if (this.entityData[6565 + this.currentVillager] != this.scratchIntX) {
               this.entityData[6565 + this.currentVillager] = (byte)this.scratchIntX;
            }
         } else if (this.entityData[6565 + this.currentVillager] == 9) {
            this.assignJobTask(-1);
         }
      }

      if ((this.game.tickCounter & 15) == (this.currentVillager & 15)) {
         boolean var1;
         if ((
               var1 = this.entityData[0 + this.currentVillager] < this.recordBlob[this.missionRegionBaseG]
                  || this.entityData[101 + this.currentVillager] < this.recordBlob[this.missionRegionBaseG + 1]
                  || this.entityData[0 + this.currentVillager] > this.recordBlob[this.missionRegionBaseG + 2]
                  || this.entityData[101 + this.currentVillager] > this.recordBlob[this.missionRegionBaseG + 3]
            )
            && this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] == 0
            && this.entityData[6161 + this.currentVillager] == 0
            && (this.game.missionIndex != 5 || this.game.tickCounter > 120)) {
            this.entityData[3131 + this.currentVillager] = this.recordBlob[this.recordStarts[233] + this.game.missionIndex];
            this.entityData[3232 + this.currentVillager] = this.recordBlob[this.recordStarts[234] + this.game.missionIndex];
            this.entityData[6161 + this.currentVillager] = 2;
         }

         if (this.waveTargetQByte < 0 && !var1) {
            if (this.aiRank[this.scratchIntAT] < this.towerWorkerM) {
               if ((
                     this.entityData[2828 + this.currentVillager] == 69
                        || this.entityData[2828 + this.currentVillager] == 84 && this.entityData[3939 + this.currentVillager] == 69
                  )
                  && this.recordBlob[this.recordStarts[42] + this.recordBlob[this.recordStarts[41] + this.entityData[6464 + this.currentVillager]]] != 2
                  && this.entityData[6161 + this.currentVillager] == 0
                  && (
                     this.entityData[6262 + this.currentVillager]
                           != this.recordBlob[this.recordStarts[221 + this.game.missionIndex] + this.towerWorkerM + this.aiRank[this.scratchIntAT]]
                        || this.entityData[6363 + this.currentVillager]
                           != this.recordBlob[this.recordStarts[221 + this.game.missionIndex] + this.towerWorkerM * 2 + this.aiRank[this.scratchIntAT]]
                  )) {
                  this.entityData[6262 + this.currentVillager] = this.recordBlob[this.recordStarts[221 + this.game.missionIndex]
                     + this.towerWorkerM
                     + this.aiRank[this.scratchIntAT]];
                  this.entityData[6363 + this.currentVillager] = this.recordBlob[this.recordStarts[221 + this.game.missionIndex]
                     + this.towerWorkerM * 2
                     + this.aiRank[this.scratchIntAT]];
                  this.entityData[6464 + this.currentVillager] = this.recordBlob[this.recordStarts[221 + this.game.missionIndex]
                     + this.aiRank[this.scratchIntAT]];
                  return;
               }
            } else if (this.entityData[2828 + this.currentVillager] == 69
               || this.entityData[2828 + this.currentVillager] == 84 && this.entityData[3939 + this.currentVillager] == 69
               || this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] >= 13
                  && this.recordBlob[this.recordStarts[41] + this.entityData[2828 + this.currentVillager]] <= 15
                  && this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] == this.currentVillager) {
               this.scratchIntW = this.recordBlob[this.recordStarts[212 + this.game.selectedDifficulty] + this.game.missionIndex];
               this.scratchIntX = (this.currentVillager & 3) == 0 && this.game.missionIndex >= 4
                  ? 2
                  : (this.entityData[1717 + this.currentVillager] >= this.entityData[1616 + this.currentVillager] ? 0 : 1);
               if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[107] + this.scratchIntX]] + this.currentVillager] < this.scratchIntW) {
                  if (this.findNearestBuilding(this.recordBlob[this.recordStarts[114] + this.scratchIntX])) {
                     this.setTask(this.currentVillager, this.recordBlob[this.recordStarts[114] + 3 + this.scratchIntX]);
                     this.entityData[6161 + this.currentVillager] = 3;
                     return;
                  }

                  this.aiReturnHome();
                  return;
               }

               this.aiReturnHome();
            }
         }
      }
   }

   // $VF: renamed from: au () void
   private void aiReturnHome() {
      if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
         this.entityData[6161 + this.currentVillager] = 2;
      } else {
         this.scratchIntC = this.entityData[0 + this.currentVillager] - this.recordBlob[this.recordStarts[233] + this.game.missionIndex];
         this.scratchIntD = this.entityData[101 + this.currentVillager] - this.recordBlob[this.recordStarts[234] + this.game.missionIndex];
         if (this.scratchIntC < -5 || this.scratchIntC > 5 || this.scratchIntD < -5 || this.scratchIntD > 5) {
            this.entityData[6262 + this.currentVillager] = this.recordBlob[this.recordStarts[233] + this.game.missionIndex];
            this.entityData[6363 + this.currentVillager] = this.recordBlob[this.recordStarts[234] + this.game.missionIndex];
            this.setTask(this.currentVillager, (byte)69);
            this.entityData[6464 + this.currentVillager] = 69;
         }
      }
   }

   // $VF: renamed from: o (int) void
   private void assignJobTask(int var1) {
      if (this.entityData[2828 + this.currentVillager] > 9
         && (this.entityData[2828 + this.currentVillager] < 44 || this.entityData[2828 + this.currentVillager] > 50)) {
         if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
            this.setTask(this.currentVillager, (byte)59);
            this.entityData[6161 + this.currentVillager] = 3;
         } else {
            if (this.entityData[202 + this.currentVillager] == 0 && this.entityData[303 + this.currentVillager] == 0) {
               this.setTask(this.currentVillager, (byte)(var1 >= 0 ? 44 + var1 : 50));
               if (this.findNearestBuilding(6)) {
                  this.moveVillagerStep(2);
                  return;
               }

               this.setTask(this.currentVillager, (byte)69);
            }
         }
      }
   }

   // $VF: renamed from: av () void
   private void aiEnlistWave() {
      this.waveEnlistedAU = 0;
      if (this.game.missionIndex < 7 || this.game.tickCounter >= this.targetYA / 7) {
         this.scratchIntX = clamp(
            this.recordBlob[this.recordStarts[206 + this.game.selectedDifficulty] + this.game.missionIndex]
               + this.game.tickCounter / this.recordBlob[this.recordStarts[210 + this.game.selectedDifficulty] + this.game.missionIndex] / 1000,
            0,
            this.recordBlob[this.recordStarts[208 + this.game.selectedDifficulty] + this.game.missionIndex]
         );
         this.scratchIntX = this.scratchIntX / 2 + this.game.randRange(16777215, this.scratchIntX + 1);
         this.randomRollY = this.recordBlob[this.recordStarts[212 + this.game.selectedDifficulty] + this.game.missionIndex];
         if (this.game.tickCounter < this.targetYA && (this.game.missionIndex != 0 || this.hintStepIndex < 11)) {
            if (this.game.missionIndex == 0) {
               return;
            }

            this.randomRollY = this.randomRollY / (3 - this.game.selectedDifficulty) + this.randomRollY * this.game.tickCounter / this.targetYA / 2;
         }

         this.distOrTargetXZ = this.game.randRange(65535, 10);

         for (this.currentVillager = this.tribeIndex * 50 + 1; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
            if (this.entityData[1919 + this.currentVillager] > 0
               && this.entityData[1414 + this.currentVillager] >= 12
               && this.aiRank[this.currentVillager - this.tribeIndex * 50 - 1] >= this.towerWorkerM
               && (this.entityData[7979 + this.currentVillager] & 64) == 0
               && this.entityData[6565 + this.currentVillager] != 9
               && (this.entityData[1515 + this.currentVillager] != 1 || (this.scratchByteR & 128) == 0)
               && (
                  this.entityData[1717 + this.currentVillager] >= this.randomRollY / 2
                     || this.entityData[1616 + this.currentVillager] >= this.randomRollY / 2
                     || this.entityData[7575 + this.currentVillager] >= this.randomRollY / 2
               )
               && (this.distOrTargetXZ < 2 || this.entityData[7575 + this.currentVillager] <= 3)) {
               this.entityData[7979 + this.currentVillager] = (byte)(this.entityData[7979 + this.currentVillager] | 64);
               this.entityData[1717 + this.currentVillager] = (byte)clamp(this.entityData[1717 + this.currentVillager], 0, this.randomRollY);
               this.entityData[1616 + this.currentVillager] = (byte)clamp(this.entityData[1616 + this.currentVillager], 0, this.randomRollY);
               if (this.game.tickCounter < this.targetYA) {
                  this.entityData[1919 + this.currentVillager] = this.entityData[6767 + this.currentVillager] = (byte)(
                     this.entityData[6767 + this.currentVillager] / 2
                        + this.entityData[6767 + this.currentVillager] * this.game.tickCounter / this.targetYA / 2
                  );
               }

               this.waveEnlistedAU++;
               if (this.waveEnlistedAU >= this.scratchIntX) {
                  break;
               }
            }
         }

         this.scratchByteR = (byte)(this.scratchByteR & 127);
         this.spawnWaveUnits(
            this.scratchIntX - this.waveEnlistedAU,
            this.recordBlob[this.recordStarts[233] + this.game.missionIndex],
            this.recordBlob[this.recordStarts[234] + this.game.missionIndex],
            (byte)this.tribeIndex,
            (byte)0
         );
         this.groupSlotN = (byte)this.game.randRange(65535, this.recordBlob[this.recordStarts[235] + this.game.missionIndex]);
         this.groupCountO = 0;
      }
   }

   // $VF: renamed from: aw () void
   private void aiAdvanceWave() {
      boolean var1 = true;
      this.waveEnlistedAU = 0;

      for (this.currentVillager = this.tribeIndex * 50 + 1; this.currentVillager <= (this.tribeIndex + 1) * 50; this.currentVillager++) {
         if (this.entityData[1919 + this.currentVillager] > 0
            && this.entityData[1414 + this.currentVillager] >= 12
            && (this.entityData[7979 + this.currentVillager] & 64) != 0) {
            this.waveEnlistedAU++;
            if (this.occupancyGrid[this.entityData[101 + this.currentVillager]][this.entityData[0 + this.currentVillager]] != this.currentVillager) {
               var1 = false;
               this.entityData[2828 + this.currentVillager] = 87;
               this.entityData[6161 + this.currentVillager] = 3;
            } else {
               if (this.entityData[6161 + this.currentVillager] == 0) {
                  this.scratchIntAT = this.recordStarts[239]
                     + (this.recordBlob[this.recordStarts[240] + this.game.missionIndex] & 255)
                     + this.recordBlob[this.recordStarts[237] + this.recordBlob[this.recordStarts[238] + this.game.missionIndex] + this.groupSlotN]
                     + this.groupCountO * 2;
                  this.entityData[6262 + this.currentVillager] = this.entityData[3131 + this.currentVillager] = this.recordBlob[this.scratchIntAT];
                  this.entityData[6363 + this.currentVillager] = this.entityData[3232 + this.currentVillager] = this.recordBlob[this.scratchIntAT + 1];
                  this.entityData[6161 + this.currentVillager] = 3;
                  this.entityData[2828 + this.currentVillager] = 69;
               } else {
                  var1 = false;
               }

               if (var1 && this.distanceTo(this.entityData[0 + this.currentVillager], this.entityData[101 + this.currentVillager], 0) > 69) {
                  var1 = false;
               }
            }
         }
      }

      if (var1 || this.waveStartTickAR + (this.groupCountO + 1) * 150 < this.game.tickCounter) {
         this.groupCountO++;
      }

      if (this.waveEnlistedAU == 0
         || this.groupCountO >= this.recordBlob[this.recordStarts[236] + this.recordBlob[this.recordStarts[238] + this.game.missionIndex] + this.groupSlotN]) {
         this.groupCountO = -1;
      }
   }

   // $VF: renamed from: a (int, int, int, byte, byte) void
   final void spawnWaveUnits(int var1, int var2, int var3, byte var4, byte var5) {
      for (this.loopIndexA = 0; this.loopIndexA < var1; this.loopIndexA++) {
         int var6 = var2;
         int var7 = var3;
         if (this.occupancyGrid[var7][var6] != 0) {
            this.currentVillager = 0;
            this.spiralSearchTile(1, 127, var6, var7);
            var6 = this.entityData[3131];
            var7 = this.entityData[3232];
         }

         if ((this.scratchIntB = this.game.spawnVillager(var4, (byte)var6, (byte)var7, (byte)20, (byte)0)) > 0) {
            this.entityData[6565 + this.scratchIntB] = var5;
            if (this.waveEnlistedAU >= 0 && var4 == 1) {
               this.currentVillager = this.scratchIntB;
               this.entityData[this.fieldOffsets[16 + (this.scratchIntB & 1)] + this.scratchIntB] = (byte)this.randomRollY;
               this.assignProfession();
               this.entityData[7979 + this.scratchIntB] = (byte)(this.entityData[7979 + this.scratchIntB] | 64);
            }
         }
      }
   }

   // $VF: renamed from: l () void
   final void clearWorld() {
      for (this.loopIndexA = 1; this.loopIndexA <= 60; this.loopIndexA++) {
         this.game.c_int_boolean_void(this.loopIndexA, false);
      }

      for (this.loopIndexA = 1; this.loopIndexA <= 100; this.loopIndexA++) {
         if (this.entityData[1919 + this.loopIndexA] != 0) {
            this.game.a_byte_void((byte)this.loopIndexA);
            this.killVillager(this.loopIndexA);
         }
      }

      for (this.loopIndexA = 0; this.loopIndexA < 22; this.loopIndexA++) {
         this.clearAnimal(this.loopIndexA);
      }
   }

   // $VF: renamed from: m () void
   final void clearEnemyTribe() {
      for (this.loopIndexA = 51; this.loopIndexA <= 100; this.loopIndexA++) {
         if (this.entityData[1919 + this.loopIndexA] != 0) {
            this.game.a_byte_void((byte)this.loopIndexA);
            this.killVillager(this.loopIndexA);
         }
      }

      for (this.loopIndexA = 0; this.loopIndexA < 22; this.loopIndexA++) {
         this.clearAnimal(this.loopIndexA);
      }
   }

   // $VF: renamed from: a (int, int, int) void
   final void revealFogCircle(int var1, int var2, int var3) {
      for (this.spawnRowAV = 0; this.spawnRowAV <= var3 << 1; this.spawnRowAV++) {
         if (var2 - var3 + this.spawnRowAV >= 0) {
            if (var2 - var3 + this.spawnRowAV >= this.game.mapHeight) {
               return;
            }

            this.fogRowBitsAY = this.recordBlob[this.recordStarts[54] + (this.recordBlob[this.recordStarts[55] + var3] & 255) + this.spawnRowAV];
            this.setFogBits(var1 - (this.fogRowBitsAY >> 1), var2 - var3 + this.spawnRowAV, this.fogRowBitsAY);
         }
      }
   }

   // $VF: renamed from: a (int, int, int, int) void
   final void fillFogRect(int var1, int var2, int var3, int var4) {
      for (this.spawnRowAV = 0; this.spawnRowAV < var4; this.spawnRowAV++) {
         if (var2 + this.spawnRowAV >= 0) {
            if (var2 + this.spawnRowAV >= this.game.mapHeight) {
               return;
            }

            this.setFogBits(var1, var2 + this.spawnRowAV, var3);
         }
      }
   }

   // $VF: renamed from: d (int, int, int) void
   private void setFogBits(int var1, int var2, int var3) {
      for (this.fogWordAW = 0; this.fogWordAW < 3; this.fogWordAW++) {
         this.fogBitsAX = 0;
         if (var1 >= this.fogWordAW << 5) {
            if (var1 < this.fogWordAW + 1 << 5) {
               this.fogBitsAX = this.bitmasks[var3] << var1 - (this.fogWordAW << 5);
            }
         } else if (var1 + var3 >= this.fogWordAW << 5) {
            this.fogBitsAX = this.bitmasks[var3] >> (this.fogWordAW << 5) - var1;
         }

         if (this.fogBitsAX != 0) {
            this.game.effectBitfield[this.scratchByteS][this.fogWordAW][var2] = this.game.effectBitfield[this.scratchByteS][this.fogWordAW][var2]
               | this.fogBitsAX;
         }
      }
   }

   // $VF: renamed from: c (int, int, int, int) void
   private void queryTile(int var1, int var2, int var3, int var4) {
      byte var5 = this.occupancyGrid[var2][var1];
      if (var4 == 0) {
         var5 = this.entityData[4747 + this.currentVillager];
      }

      if (var3 == 0 && (this.game.effectBitfield[0][var1 >> 5][var2] >> (var1 & 31) & 1) == 0) {
         this.decodeTileTarget(0, 0, var3);
      } else {
         for (byte var6 = 0; var6 < 7; var6++) {
            if (var5 >= this.recordBlob[this.recordStarts[89] + 0 + var6] && var5 <= this.recordBlob[this.recordStarts[89] + 7 + var6]) {
               if (var3 == 0 && var6 == 1 && (this.game.effectBitfield[1][var1 >> 5][var2] >> (var1 & 31) & 1) == 0) {
                  this.decodeTileTarget(0, 0, var3);
               } else {
                  this.decodeTileTarget(var5, var6, var3);
               }
               break;
            }
         }
      }

      if ((this.tileQueryResult[0] == 1 || this.tileQueryResult[0] == 2)
         && this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[78] + 0 + this.tileQueryResult[0]]] + this.tileQueryResult[2]] <= 0) {
         this.decodeTileTarget(-126, 6, var3);
      }
   }

   // $VF: renamed from: e (int, int, int) void
   private void decodeTileTarget(int var1, int var2, int var3) {
      this.tileQueryResult[0] = (byte)var2;
      this.tileQueryResult[2] = (byte)(var1 * this.recordBlob[this.recordStarts[90] + 0 + var2] + this.recordBlob[this.recordStarts[90] + 7 + var2]);
      this.tileQueryResult[1] = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[91] + var2]] + this.tileQueryResult[2]];
      if (this.recordBlob[this.recordStarts[92] + var2] >= 127) {
         this.tileQueryResult[3] = 0;
      } else {
         this.tileQueryResult[3] = (byte)(
            (this.tileQueryResult[2] - (this.tileQueryResult[0] != 1 && this.tileQueryResult[0] != 2 ? 0 : 1)) / this.recordBlob[this.recordStarts[92] + var2]
                  + var3
               & 1
         );
      }
   }

   // $VF: renamed from: a () byte
   private byte queryTileKind() {
      if (this.tileQueryResult[3] == 0) {
         if (this.tileQueryResult[0] == 2) {
            if (this.tileQueryResult[1] != 10 && this.entityData[1414 + this.currentVillager] < 12) {
               return 11;
            }

            if (this.tileQueryResult[1] == 2
               && this.entityData[8627 + this.tileQueryResult[2]] == 0
               && this.entityData[9237 + this.tileQueryResult[2]] > 1
               && this.entityData[2828 + this.currentVillager] != 111) {
               return 13;
            }

            if (this.entityData[8627 + this.tileQueryResult[2]] != 1 && this.entityData[8627 + this.tileQueryResult[2]] != 2) {
               if (this.tileQueryResult[1] == 1 && this.recordBlob[this.recordStarts[77] + this.entityData[6565 + this.currentVillager]] != -1) {
                  return 6;
               }

               if (this.tileQueryResult[1] == 5 && this.recordBlob[this.recordStarts[77] + this.entityData[6565 + this.currentVillager]] < 0) {
                  return 7;
               }

               if (this.tileQueryResult[1] == 10 && this.entityData[1414 + this.currentVillager] >= 11) {
                  return 9;
               }

               if (this.tileQueryResult[1] == 3 && this.currentVillager != this.tribeStats[0][17]) {
                  return 10;
               }

               if (this.entityData[8810 + this.tileQueryResult[2]] > 0 && this.entityData[8810 + this.tileQueryResult[2]] != this.currentVillager) {
                  this.distSquaredScratchK = 7;
               }
            } else {
               this.distSquaredScratchK = 8;
            }
         } else if (this.tileQueryResult[0] == 3 && this.tileQueryResult[1] == 5 && this.entityData[6565 + this.currentVillager] != 2) {
            return 8;
         }
      }

      return -1;
   }

   // $VF: renamed from: a (byte, int, int) void
   final void showMessage(byte var1, int var2, int var3) {
      if (var2 <= 50) {
         if (this.game.missionIndex >= this.recordBlob[this.recordStarts[293] + var1]) {
            if (this.messageCooldowns[this.recordBlob[this.recordStarts[292] + var1]] < 0) {
               this.messageCooldowns[this.recordBlob[this.recordStarts[292] + var1]] = 0;
               if (var1 != 4 && var1 != 5 && this.rankShiftK >= 50
                  || (var1 == 4 || var1 == 5)
                     && (
                        this.scratchByteZ < this.game.camTileX
                           || this.scratchByteZ >= this.game.camTileX + this.game.screenColsTiles
                           || this.scratchByteA < this.game.camTileY
                           || this.scratchByteA >= this.game.camTileY + this.game.screenRowsTiles
                     )) {
                  this.game.a_byte_byte_byte_void(var1, (byte)var2, (byte)var3);
               }
            }
         }
      }
   }

   // $VF: renamed from: ax () void
   private void tickMessageCooldowns() {
      for (this.loopIndexA = 0; this.loopIndexA < 6; this.loopIndexA++) {
         if (this.game.chiefId != 1) {
            this.messageCooldowns[this.loopIndexA] = this.fieldOffsets[this.offsetGroupBases[2] + this.loopIndexA];
         } else {
            if (this.messageCooldowns[this.loopIndexA] >= 0) {
               this.messageCooldowns[this.loopIndexA]++;
            }

            if (this.messageCooldowns[this.loopIndexA] >= this.fieldOffsets[this.offsetGroupBases[2] + this.loopIndexA]) {
               this.messageCooldowns[this.loopIndexA] = -1;
            }
         }
      }
   }

   // $VF: renamed from: n () void
   final void rebuildSpatialHash() {
      for (int var1 = 0; var1 < 5; var1++) {
         for (int var2 = 0; var2 < 6; var2++) {
            for (int var3 = 0; var3 < 6; var3++) {
               this.diseaseSpots[var1][var2][var3] = -1;
            }
         }
      }

      for (int var6 = 0; var6 < 5; var6++) {
         for (int var7 = this.recordBlob[this.recordStarts[115] + var6]; var7 <= this.recordBlob[this.recordStarts[116] + var6]; var7++) {
            if (this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[117] + var6]] + var7] > 0
               && (
                  var6 > 1
                     || this.occupancyGrid[this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118] + var6] + 1] + var7]][this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118]
                              + var6]]
                           + var7]]
                        == var7
                     || this.entityData[2828 + var7] == 93
                     || this.entityData[2828 + var7] == 92
                     || this.entityData[2828 + var7] == 106
               )) {
               int var4 = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118] + var6]] + var7] >> 4;
               int var5 = this.entityData[this.fieldOffsets[this.recordBlob[this.recordStarts[118] + var6] + 1] + var7] >> 4;
               int var8 = var7 - this.recordBlob[this.recordStarts[115] + var6];
               this.hashNext[var6][var8] = this.diseaseSpots[var6][var5][var4];
               this.diseaseSpots[var6][var5][var4] = (byte)var8;
            }
         }
      }
   }
}
