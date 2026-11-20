import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

// $VF: renamed from: d
public final class Splash extends Canvas {
   // $VF: renamed from: a byte
   byte splashIndex = 0;
   // $VF: renamed from: a boolean
   boolean bufferCreated;

   public Splash(TribesMIDlet var1) {
      this.setFullScreenMode(true);
   }

   public final void paint(Graphics var1) {
      var1.drawImage(Game.screenBuffers[0], -11, -8, 0);
   }

   // $VF: renamed from: a () void
   final void showSplash() {
      if (!this.bufferCreated) {
         this.bufferCreated = true;
         Game.screenBuffers[0] = Image.createImage(264, 352);
         Game.screenBufferGraphics[0] = Game.screenBuffers[0].getGraphics();
      }

      try {
         if (this.splashIndex < 4) {
            Game.screenBufferGraphics[0].setColor(16777215);
            clearToWhite();
            Game.screenBufferGraphics[0].drawImage(Image.createImage("/l" + this.splashIndex), 131, 168, 3);
         }
      } catch (Exception var2) {
      }

      this.repaint();
   }

   // $VF: renamed from: b () void
   private static void clearToWhite() {
      Game.screenBufferGraphics[0].fillRect(11, 8, 240, 321);
   }

   protected final void keyPressed(int var1) {
   }
}
