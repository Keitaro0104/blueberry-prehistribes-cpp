import java.io.ByteArrayInputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;

// $VF: renamed from: a
public final class MidiPlayer implements PlayerListener {
   // $VF: renamed from: a javax.microedition.media.control.VolumeControl
   VolumeControl volumeControl;
   // $VF: renamed from: a javax.microedition.media.Player
   Player player;

   public MidiPlayer(String var1) {
      try {
         ByteArrayInputStream var2 = new ByteArrayInputStream(
            Game.midiData, Game.unusedShortArrayF[Game.nextMusicTrack], Game.unusedShortArrayE[Game.nextMusicTrack]
         );
         this.player = Manager.createPlayer(var2, var1);
         var2.close();
         this.player.realize();
         this.player.addPlayerListener(this);
      } catch (Exception var3) {
      }
   }

   // $VF: renamed from: c () void
   private void startPlayback() {
      try {
         if (this.player != null) {
            if (this.player.getState() == 300) {
               this.stopPlayback();
            }

            if (this.player.getState() != 400) {
               this.player.prefetch();
               this.player.start();
            }
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: a () void
   final void stopPlayback() {
      try {
         if (this.player != null) {
            if (this.player.getState() == 400) {
               this.player.setMediaTime(0L);
               this.player.stop();
            }
         }
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: a () int
   final int getPlayerState() {
      return this.player == null ? -1 : this.player.getState();
   }

   // $VF: renamed from: b () void
   final void closePlayer() {
      if (this.player != null) {
         if (this.player.getState() != 0) {
            this.player.close();
         }
      }
   }

   // $VF: renamed from: a (int) void
   final void setVolume(int var1) {
      try {
         if (this.player == null) {
            return;
         }

         this.volumeControl = (VolumeControl)this.player.getControl("VolumeControl");
         if (var1 != 0) {
            if (this.player.getState() != 400) {
               this.startPlayback();
            }

            this.volumeControl.setLevel(var1 * 20);
            return;
         }

         this.stopPlayback();
      } catch (Exception var3) {
      }
   }

   public final void playerUpdate(Player var1, String var2, Object var3) {
      if (var2.equals("deviceUnavailable")) {
         this.stopPlayback();
         System.currentTimeMillis();
      }

      if (var2.equals("deviceAvailable")) {
         this.startPlayback();
      }
   }
}
