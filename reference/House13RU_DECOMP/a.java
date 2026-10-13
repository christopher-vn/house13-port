import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class a {
   public static boolean do = true;
   private VolumeControl if;
   private Player a;

   public a(String var1) {
      this.a = this.a(var1);
   }

   public void a() {
      this.a(this.a);
   }

   public Player a(String var1) {
      try {
         InputStream var3 = this.getClass().getResourceAsStream("/" + var1 + ".mid");
         Player var2 = Manager.createPlayer(var3, "audio/midi");
         var2.realize();
         this.if = (VolumeControl)var2.getControl("VolumeControl");
         int var4 = this.if.setLevel(70);
         return var2;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   public void if() {
      if (this.a != null) {
         try {
            this.a.stop();
         } catch (Exception var4) {
            System.out.println("Sounds.close() error0");
         }

         try {
            this.a.deallocate();
         } catch (Exception var3) {
            System.out.println("Sounds.close() error1");
         }

         try {
            this.a.close();
         } catch (Exception var2) {
            System.out.println("Sounds.close() error2");
         }

         this.a = null;
      }

   }

   public void a(Player var1) {
      if (var1 != null && do) {
         try {
            var1.start();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }
}
