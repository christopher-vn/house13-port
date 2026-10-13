import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class TheMIDlet extends MIDlet {
   public static TheMIDlet instance;
   public static Display display;
   public MainCanvas maincanvas;

   public TheMIDlet() {
      instance = this;
      instance.maincanvas = new MainCanvas();
      display = Display.getDisplay(this);
   }

   public void startApp() {
      display.setCurrent(instance.maincanvas);
   }

   public void pauseApp() {
   }

   public void destroyApp(boolean unconditional) {
   }

   public static void quitApp() {
      MainCanvas.runing = false;
      instance.destroyApp(true);
      instance.notifyDestroyed();
      instance = null;
   }
}
