/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class TheMIDlet
extends MIDlet {
    public static TheMIDlet instance;
    public static Display display;
    public MainCanvas maincanvas;

    public TheMIDlet() {
        instance = this;
        TheMIDlet.instance.maincanvas = new MainCanvas();
        display = Display.getDisplay((MIDlet)this);
    }

    public void startApp() {
        display.setCurrent((Displayable)TheMIDlet.instance.maincanvas);
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
