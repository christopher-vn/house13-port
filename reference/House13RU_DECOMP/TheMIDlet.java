import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class TheMIDlet extends MIDlet {
   public static TheMIDlet if;
   public static Display do;
   public b a;

   public TheMIDlet() {
      if = this;
      if.a = new b();
      do = Display.getDisplay(this);
   }

   public void startApp() {
      do.setCurrent(if.a);
   }

   public void pauseApp() {
   }

   public void destroyApp(boolean var1) {
   }

   public static void a() {
      b.aI = false;
      if.destroyApp(true);
      if.notifyDestroyed();
      if = null;
   }
}
