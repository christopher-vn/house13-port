/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.media.Manager
 *  javax.microedition.media.Player
 *  javax.microedition.media.control.VolumeControl
 */
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class MySound {
    public static boolean canSound = true;
    private VolumeControl vc;
    private Player instance;

    public MySound(String path) {
        this.instance = this.createSound(path);
    }

    public void play() {
        this.playSound(this.instance);
    }

    public Player createSound(String path) {
        try {
            InputStream in = this.getClass().getResourceAsStream("/" + path + ".mid");
            Player player = Manager.createPlayer((InputStream)in, (String)"audio/midi");
            player.realize();
            this.vc = (VolumeControl)player.getControl("VolumeControl");
            int Vo = this.vc.setLevel(70);
            return player;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stop() {
        if (this.instance != null) {
            try {
                this.instance.stop();
            }
            catch (Exception _ex) {
                System.out.println("Sounds.close() error0");
            }
            try {
                this.instance.deallocate();
            }
            catch (Exception _ex) {
                System.out.println("Sounds.close() error1");
            }
            try {
                this.instance.close();
            }
            catch (Exception _ex) {
                System.out.println("Sounds.close() error2");
            }
            this.instance = null;
        }
    }

    public void playSound(Player player) {
        if (player != null && canSound) {
            try {
                player.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
