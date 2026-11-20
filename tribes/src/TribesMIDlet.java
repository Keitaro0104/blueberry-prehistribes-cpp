import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

// $VF: renamed from: tribes
public class TribesMIDlet extends MIDlet implements Runnable {
   // $VF: renamed from: a f
   Game game;
   // $VF: renamed from: a d
   Splash splashScreen;
   // $VF: renamed from: a javax.microedition.lcdui.Display
   Display display;

   public void startApp() {
      if (this.game != null) {
         this.game.e_void();
      } else {
         this.display = Display.getDisplay(this);
         new Thread(this).start();
      }
   }

   public void pauseApp() {
      if (this.game != null) {
         this.game.d_void();
      }
   }

   public void destroyApp(boolean var1) {
      if (this.game != null) {
         this.game.quitGame();
      }
   }

   public void run() {
      System.gc();
      Thread.yield();
      this.splashScreen = new Splash(this);
      this.splashScreen.showSplash();
      this.splashScreen.splashIndex = 0;
      this.display.setCurrent(this.splashScreen);
      sleep(1000);
      this.splashScreen.showSplash();
      sleep(2000);
      this.splashScreen.splashIndex = 1;
      this.splashScreen.showSplash();
      System.gc();
      Thread.yield();
      sleep(2000);
      this.splashScreen.splashIndex = 2;
      this.splashScreen.showSplash();
      Thread.yield();
      this.splashScreen.splashIndex = 5;
      this.game = new Game(this);
      this.game.startGameThread();
   }

   // $VF: renamed from: a (int) void
   static void sleep(int var0) {
      try {
         Thread.sleep(var0);
      } catch (InterruptedException var2) {
      }
   }
}
