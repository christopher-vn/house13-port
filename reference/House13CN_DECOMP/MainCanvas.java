import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MainCanvas extends Canvas implements Runnable {
   public static int WIDTH;
   public static int HEIGHT;
   private static int SHOW_ROW;
   private static int KEYCODE_UP;
   private static int KEYCODE_DOWN;
   private static int KEYCODE_LEFT;
   private static int KEYCODE_RIGHT;
   private static int KEYCODE_FIRE;
   private static int KEYCODE_SOFTKEY_LEFT;
   private static int KEYCODE_SOFTKEY_RIGHT;
   private static String Help_String_queding;
   private static String Help_String_quxiao;
   private static final byte MAIN_KEY_START = 0;
   private static final byte MAIN_KEY_SETTING = 2;
   private static final byte MAIN_KEY_HELP = 3;
   private static final byte MAIN_KEY_ABOUT = 1;
   private static final byte MAIN_KEY_EXIT = 4;
   public static final int GAME_CANCLE = 0;
   public static final int GAME_HELP = 1;
   public static final int GAME_EXIT = 3;
   public static final int GAME_MUSIC = 2;
   public static int GAME_CURRENT = 0;
   private static final byte ROOM_1 = 1;
   private static final byte ROOM_2 = 2;
   private static final byte ROOM_3 = 3;
   private static final byte ROOM_4 = 4;
   private static final byte ROOM_5 = 5;
   private static final byte ROOM_6 = 6;
   private static final byte ROOM_7 = 7;
   private static final byte ROOM_8 = 8;
   private static final byte ROOM_9 = 9;
   private static final byte ROOM_10 = 10;
   private static final byte ROOM_11 = 11;
   private static final byte ROOM_12 = 12;
   private static final byte ROOM_13 = 13;
   private static final byte ROOM_14 = 14;
   private static final byte ROOM_15 = 15;
   private static final byte ROOM_16 = 16;
   private static final byte ROOM_17 = 17;
   private static final byte ROOM_18 = 18;
   private static final byte ROOM_19 = 19;
   private static final byte ROOM_20 = 20;
   private static final byte ROOM_21 = 21;
   private static final byte ROOM_22 = 22;
   private static final byte ROOM_23 = 23;
   private static final byte ROOM_24 = 24;
   private static final byte ROOM_25 = 25;
   private static final byte ROOM_26 = 26;
   private static final byte ROOM_27 = 27;
   private static final byte ROOM_WaiBian = -1;
   private static final Font afont = Font.getFont(0, 0, 0);
   private int fH;
   private int fW;
   private int currentKeyCode;
   private Image flashImage_;
   private Image flashM_;
   private Image logoImage_;
   private Thread thread_;
   private boolean pause;
   private String[] strsToShow;
   private String[] strsToShow1;
   private String[] strsToShow2;
   private String[] strsToShow3;
   private byte drawStatus_;
   private int drawCount_;
   private int menuStatus_;
   private int menuCount_;
   private int menuCount_1;
   private boolean canChange;
   private boolean canExit;
   private Image imageBuffer;
   public Graphics gr;
   public static boolean runing = true;
   private String SettingName;
   private MySound logo_Back;
   private MySound jieshu_Back;
   private MySound get_M;
   private MySound wrong_M;
   private MySound correct_M;
   private MySprite menu;
   private byte currentRoom;
   private MySprite hand;
   private int[] toolStates;
   private MySprite[] tool;
   private int currentTool;
   private MySprite toolback;
   private String stringForShow;
   private String toolStringToshow;
   private boolean showTool;
   private boolean xia;
   private boolean shang;
   private boolean zuo;
   private boolean you;
   private Image obj1;
   private Image obj2;
   public boolean usingTool;
   public byte publicToolMidHeight;
   private Image pre1;
   private Image pre2;
   private Image pre3;
   private boolean preDisplayed;
   private byte preCount;
   private int i;
   private int j;
   private boolean fashang;
   private boolean faxia;
   private boolean fazuo;
   private boolean fayou;
   private boolean zong;
   private Image jiantou1;
   private int sid;
   private boolean isMusic;
   private boolean isHelp;
   private int Timer;
   private Image logo_;
   private Image logo12;
   private Image logore;
   private int js;
   private int js1;
   private Image menuback;
   private Image caidan;
   private Image shou;
   private int menusetX;
   private boolean isOff;
   private boolean shoud;
   private boolean cad;
   private int caxia;
   private int shoudw;
   private int preTop;
   private boolean isShowStr;
   private Image kaishi1;
   private Image jindu1;
   private int movePreY;
   private int juli;
   private int juli11;
   private Image helpback;
   private Image lingjian;
   private int isd;
   private boolean tur;
   private Image yug;
   private Image chuang;
   private Image guizi;
   private Image guide;
   private Image zhuoz;
   private Image shafa;
   private Image chuzi;
   private Image fanhuie;
   private Image caidan1;
   private int[][] color;
   private Image jiantou;
   private int rs;
   private boolean kaishij1;
   private boolean kaishij2;
   private boolean jianpan;
   private boolean ssk;
   private boolean huodeya;
   private int fang;
   private int offsetX;
   private Image back1;
   private Image back2;
   private Image back3;
   private Image back4;
   private Image back5;
   private Image back8;
   private Image back9;
   private Image back10;
   private Image back11;
   private Image back12;
   private Image back13;
   private Image back14;
   private Image back15;
   private Image kuangtpong;
   private Image dong;
   private Image mao;
   private Image kamen;
   private Image lou;
   private Image men;
   private Image menkuang;
   private Image paaizi;
   private Image loub;
   private Image kaigui;
   private Image shouji;
   private Image shaizi;
   private Image kguizi;
   private Image shuzi;
   private Image gui2;
   private Image shuyao;
   private Image shuji;
   private Image dang;
   private Image chouti;
   private Image sez;
   private Image wei;
   private Image guis;
   private Image bang;
   private Image shise;
   private Image guika;
   private Image qian;
   private Image weiyao;
   private Image gz;
   private Image dmap;
   private Image xmap;
   private Image diban;
   private Image nkai;
   private Image k;
   private Image kc;
   private Image s;
   private Image sk;
   private Image dian;
   private boolean linshi;
   private boolean shishai;
   private boolean kguizi1;
   private boolean gui22;
   private boolean shuyao1;
   private boolean shishai1;
   private boolean shuji1;
   private boolean dang1;
   private boolean sez1;
   private boolean guis1;
   private boolean bang1;
   private boolean shise1;
   private boolean shiyongs;
   private boolean guika1;
   private boolean qian1;
   private boolean weiyao1;
   private boolean fangmen;
   private boolean dmap1;
   private boolean diban1;
   private boolean dianchi;
   private boolean kaigui1;
   private boolean shouji1;
   private boolean shaizi1;
   private boolean zale;
   private boolean nkai1;
   private boolean kongmen;
   private boolean k1;
   private boolean k2;
   private boolean k3;
   private boolean k4;
   private boolean mao1;
   private boolean an;
   private boolean pshang;
   private boolean pxia;
   private boolean pzuo;
   private boolean pyou;
   private boolean qingchu;
   private boolean tou;
   private boolean zhi;
   private int bian;
   private int sh;
   private int sh1;
   private int sh1i;
   private int sh1ii;
   private boolean isOutpasswordkuang;
   private Image password_;
   private MySprite numpass_;
   private boolean kaishigd;
   private int keydownNum;
   private Image jsh;
   private int st;
   private int st1;
   private int sw;
   private int sw2;
   private int sw3;
   private int layer2;
   private boolean sw1;
   private boolean hu;
   private boolean zimua;
   private boolean hun;
   private boolean huns;
   private Image fanhui;
   private int bianl;
   private int xg1;
   private int xg2;
   private int xg3;
   private int xg4;
   private int xg5;
   private int xg6;
   private int xg7;
   private int xg8;
   private boolean wzdh;
   private boolean ksdi;
   private boolean linxi;
   private boolean kisi;
   private int buchang;
   private int bide;
   private int swq;
   private int jish;
   private boolean fsi;
   private Image fanwi;
   private int shuodi;
   private int sed;
   private int isdew;
   private boolean shiyong;
   private boolean ditu;
   private boolean guanzi;
   private boolean chuis;
   private boolean shengsd;
   private boolean sdfer;
   private Image anja;
   private int stateIndex1;
   private int stateValue1;
   private Image toolkuang;
   private int toolNums;

   public MainCanvas() {
      this.fH = afont.getHeight();
      this.fW = afont.stringWidth("中");
      this.currentKeyCode = 0;
      this.canChange = false;
      this.canExit = false;
      this.SettingName = "";
      this.js = 1;
      this.caxia = 0;
      this.shoudw = 0;
      this.juli = 56;
      this.juli11 = 56;
      this.isd = 0;
      this.sh = 0;
      this.sh1 = 0;
      this.sh1i = 0;
      this.sh1ii = 0;
      this.sw = 1;
      this.sw2 = 0;
      this.sw3 = 0;
      this.sw1 = false;
      this.fsi = false;

      try {
         this.setFullScreenMode(true);
         WIDTH = 176;
         HEIGHT = 208;
         SHOW_ROW = 8;
         KEYCODE_UP = this.getKeyCode(1);
         KEYCODE_DOWN = this.getKeyCode(6);
         KEYCODE_LEFT = this.getKeyCode(2);
         KEYCODE_RIGHT = this.getKeyCode(5);
         KEYCODE_FIRE = this.getKeyCode(8);
         KEYCODE_SOFTKEY_LEFT = -21;
         KEYCODE_SOFTKEY_RIGHT = -22;
         this.inIt();
         this.imageBuffer = Image.createImage(WIDTH, HEIGHT);
         this.gr = this.imageBuffer.getGraphics();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      try {
         this.jbInit();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void getSettingInfo() {
      this.SettingName = TheMIDlet.instance.getAppProperty("SettingName");
      if (this.SettingName.equals("MOTOV600")) {
         WIDTH = 176;
         HEIGHT = 208;
         KEYCODE_UP = this.getKeyCode(1);
         KEYCODE_DOWN = this.getKeyCode(6);
         KEYCODE_LEFT = this.getKeyCode(2);
         KEYCODE_RIGHT = this.getKeyCode(5);
         KEYCODE_FIRE = this.getKeyCode(8);
         KEYCODE_SOFTKEY_LEFT = 22;
         KEYCODE_SOFTKEY_RIGHT = 21;
      } else if (this.SettingName.equals("MOTOE398")) {
         WIDTH = 176;
         HEIGHT = 204;
         KEYCODE_UP = this.getKeyCode(1);
         KEYCODE_DOWN = this.getKeyCode(6);
         KEYCODE_LEFT = this.getKeyCode(2);
         KEYCODE_RIGHT = this.getKeyCode(5);
         KEYCODE_FIRE = this.getKeyCode(8);
         KEYCODE_SOFTKEY_LEFT = -22;
         KEYCODE_SOFTKEY_RIGHT = -21;
         Help_String_queding = "右软键";
         Help_String_quxiao = "左软键";
      } else if (this.SettingName.equals("K700C")) {
         WIDTH = 176;
         HEIGHT = 220;
         KEYCODE_UP = this.getKeyCode(1);
         KEYCODE_DOWN = this.getKeyCode(6);
         KEYCODE_LEFT = this.getKeyCode(2);
         KEYCODE_RIGHT = this.getKeyCode(5);
         KEYCODE_FIRE = this.getKeyCode(8);
         KEYCODE_SOFTKEY_LEFT = -6;
         KEYCODE_SOFTKEY_RIGHT = -7;
      } else if (this.SettingName.equals("PanasonicX800")) {
         WIDTH = 176;
         HEIGHT = 208;
         KEYCODE_UP = this.getKeyCode(1);
         KEYCODE_DOWN = this.getKeyCode(6);
         KEYCODE_LEFT = this.getKeyCode(2);
         KEYCODE_RIGHT = this.getKeyCode(5);
         KEYCODE_FIRE = -5;
         KEYCODE_SOFTKEY_LEFT = -6;
         KEYCODE_SOFTKEY_RIGHT = -6;
         Help_String_queding = "左软键";
         Help_String_quxiao = "左软键";
      }

   }

   private void inIt() {
      try {
         this.hand = new MySprite(this.loadImage("hand"), 4);
         this.handIni();

         for(this.j = 0; this.j < 3; ++this.j) {
            this.hand.framesInfo[this.j][0] = this.j * 21;
            this.hand.framesInfo[this.j][1] = 0;
            this.hand.framesInfo[this.j][2] = 21;
            this.hand.framesInfo[this.j][3] = 30;
         }

         this.hand.currentFrame = 0;
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.drawStatus_ = -1;
      this.drawCount_ = 0;
      this.menuStatus_ = 0;
      this.menuCount_ = 0;
      this.menuCount_1 = 0;
      runing = true;
      this.pause = false;
      this.isOff = true;
      this.menusetX = 10;
      this.start();
   }

   private void jiantoud() {
      if (this.jiantou1 == null && !this.zong) {
         this.jiantou1 = this.loadImage("jiantou1");
      }

      if (!this.zong) {
         if (!this.fashang) {
            this.gr.drawRegion(this.jiantou1, 12, 0, 15, 12, 0, 105, 110, 0);
            this.gr.drawRegion(this.jiantou1, 39, 4, 24, 15, 0, 100, 120, 0);
         }

         if (!this.faxia) {
            this.gr.drawRegion(this.jiantou1, 63, 19, 24, 15, 0, 100, 168, 0);
            this.gr.drawRegion(this.jiantou1, 12, 27, 15, 12, 0, 105, 183, 0);
         }

         if (!this.fazuo) {
            this.gr.drawRegion(this.jiantou1, 63, 4, 24, 15, 0, 70, 150, 0);
            this.gr.drawRegion(this.jiantou1, 0, 12, 12, 15, 0, 58, 150, 0);
         }

         if (!this.fayou) {
            this.gr.drawRegion(this.jiantou1, 39, 19, 24, 15, 0, 130, 150, 0);
            this.gr.drawRegion(this.jiantou1, 27, 12, 12, 15, 0, 153, 150, 0);
         }

         if (++this.sid / 4 % 2 == 0) {
            if (this.sid > 20) {
               this.zong = true;
               this.sid = 20;
            }

            this.gr.drawRegion(this.jiantou1, 12, 0, 15, 12, 0, 105, 110, 0);
            this.gr.drawRegion(this.jiantou1, 87, 4, 24, 15, 0, 100, 120, 0);
            this.gr.drawRegion(this.jiantou1, 12, 27, 15, 12, 0, 105, 183, 0);
            this.gr.drawRegion(this.jiantou1, 111, 19, 24, 15, 0, 100, 168, 0);
            this.gr.drawRegion(this.jiantou1, 0, 12, 12, 15, 0, 58, 150, 0);
            this.gr.drawRegion(this.jiantou1, 111, 4, 24, 15, 0, 70, 150, 0);
            this.gr.drawRegion(this.jiantou1, 27, 12, 12, 15, 0, 153, 150, 0);
            this.gr.drawRegion(this.jiantou1, 87, 19, 24, 15, 0, 130, 150, 0);
         }
      } else {
         this.jiantou1 = null;
      }

   }

   private void handIni() {
      this.hand.positionX = (WIDTH - this.hand.framesInfo[this.hand.currentFrame][2]) / 2 + 20;
      this.hand.positionY = HEIGHT - 80;
      this.hand.DX = 0;
      this.hand.DY = 0;
      this.hand.currentFrame = 0;
   }

   private void gameIni() {
      this.pause = false;
      this.drawCount_ = 0;
      this.canExit = false;
      this.currentRoom = 1;
      this.toolStates = new int[16];
      this.tool = new MySprite[14];
      this.stringForShow = "";
      this.toolStringToshow = "";
      GAME_CURRENT = 0;
      this.showTool = false;
      this.preDisplayed = false;
      this.preCount = 0;
      this.usingTool = false;
      this.publicToolMidHeight = 60;
      this.preTop = HEIGHT - 10;
      this.isShowStr = false;
      this.color = new int[11][3];
      this.color[0][0] = 223;
      this.color[0][1] = 220;
      this.color[0][2] = 122;
      this.color[1][0] = 213;
      this.color[1][1] = 211;
      this.color[1][2] = 162;
      this.color[2][0] = 223;
      this.color[2][1] = 220;
      this.color[2][2] = 122;
      this.color[3][0] = 234;
      this.color[3][1] = 229;
      this.color[3][2] = 81;
      this.color[4][0] = 244;
      this.color[4][1] = 237;
      this.color[4][2] = 39;
      this.color[5][0] = 213;
      this.color[5][1] = 211;
      this.color[5][2] = 162;
      this.color[6][0] = 223;
      this.color[6][1] = 220;
      this.color[6][2] = 122;
      this.color[7][0] = 234;
      this.color[7][1] = 229;
      this.color[7][2] = 81;
      this.color[8][0] = 244;
      this.color[8][1] = 237;
      this.color[8][2] = 39;
      this.color[9][0] = 213;
      this.color[9][1] = 211;
      this.color[9][2] = 162;
      this.color[10][0] = 223;
      this.color[10][1] = 220;
      this.color[10][2] = 122;
   }

   public void start() {
      if (null == this.thread_) {
         this.thread_ = new Thread(this);
         this.thread_.start();
      }

   }

   private void createBackMid(int i) {
      try {
         if (MySound.canSound) {
            switch(i) {
            case 0:
               if (this.logo_Back == null) {
                  this.logo_Back = new MySound("menu");
               }
               break;
            case 1:
               if (this.get_M == null) {
                  this.get_M = new MySound("get");
               }
               break;
            case 2:
               if (this.wrong_M == null) {
                  this.wrong_M = new MySound("wrong");
               }
               break;
            case 3:
               if (this.correct_M == null) {
                  this.correct_M = new MySound("correct");
               }
               break;
            case 4:
               if (this.jieshu_Back == null) {
                  this.jieshu_Back = new MySound("mapbg");
               }
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void playBackMid(int i) {
      try {
         switch(i) {
         case 0:
            if (this.logo_Back != null && MySound.canSound) {
               this.logo_Back.play();
            }
            break;
         case 1:
            if (this.get_M != null && MySound.canSound) {
               this.get_M.play();
            }
            break;
         case 2:
            if (this.wrong_M != null && MySound.canSound) {
               this.wrong_M.play();
            }
            break;
         case 3:
            if (this.correct_M != null && MySound.canSound) {
               this.correct_M.play();
            }
            break;
         case 4:
            if (this.jieshu_Back != null && MySound.canSound) {
               this.jieshu_Back.play();
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void closedMid(int i) {
      if (!MySound.canSound) {
         switch(i) {
         case 0:
            if (this.logo_Back != null) {
               this.logo_Back.stop();
               this.logo_Back = null;
            }
            break;
         case 1:
            if (this.get_M != null) {
               this.get_M.stop();
               this.get_M = null;
            }
            break;
         case 2:
            if (this.wrong_M != null) {
               this.wrong_M.stop();
               this.wrong_M = null;
            }
            break;
         case 3:
            if (this.correct_M != null) {
               this.correct_M.stop();
               this.correct_M = null;
            }
            break;
         case 4:
            if (this.jieshu_Back != null) {
               this.jieshu_Back.stop();
               this.jieshu_Back = null;
            }
         }
      }

   }

   private void changeClosedMid(int i) {
      switch(i) {
      case 0:
         if (this.logo_Back != null) {
            this.logo_Back.stop();
            this.logo_Back = null;
         }
         break;
      case 1:
         if (this.get_M != null) {
            this.get_M.stop();
            this.get_M = null;
         }
         break;
      case 2:
         if (this.wrong_M != null) {
            this.wrong_M.stop();
            this.wrong_M = null;
         }
         break;
      case 3:
         if (this.correct_M != null) {
            this.correct_M.stop();
            this.correct_M = null;
         }
         break;
      case 4:
         if (this.jieshu_Back != null) {
            this.jieshu_Back.stop();
            this.jieshu_Back = null;
         }
      }

   }

   public void FreeGameSourse() {
      this.tool = null;
      this.toolback = null;
      this.pre1 = null;
      this.pre2 = null;
      this.back1 = null;
      this.hand.framesInfo[0][0] = 4;
      this.hand.framesInfo[0][1] = 1;
      this.hand.framesInfo[0][2] = 16;
      this.hand.framesInfo[0][3] = 29;
      this.hand.currentFrame = 0;
      this.handIni();
      this.freeRoom1();
      this.strsToShow = null;
      this.strsToShow1 = null;
      this.strsToShow2 = null;
      this.toolNums = 0;
      this.isOff = true;
      this.menusetX = 10;
      this.movePreY = 0;
      System.gc();
   }

   public void paint(Graphics g) {
      try {
         switch(this.drawStatus_) {
         case -1:
            this.drawFlashScreen();
            break;
         case 0:
            this.drawXLogo();
            break;
         case 1:
            this.drawMainScreen();
            break;
         case 2:
            this.drawGameScreen();
         }

         if (this.xia || this.you || this.shang || this.zuo) {
            if (this.hand.currentFrame != 2) {
               ++this.hand.currentFrame;
            } else if (this.hand.currentFrame == 2) {
               --this.hand.currentFrame;
            }

            if (this.qingchu) {
               this.hand.currentFrame = 0;
            }
         }

         if (this.bianl == 2) {
            this.keyPressed(56);
         }

         this.canChange = true;
         g.setClip(0, 0, WIDTH, HEIGHT);
         g.drawImage(this.imageBuffer, 0, 0, 0);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      System.gc();
   }

   public void keyPressed(int keyCode) {
      if (this.dmap1) {
         this.dmap1 = false;
      }

      try {
         if (this.canChange) {
            switch(this.drawStatus_) {
            case -1:
            default:
               break;
            case 0:
               this.onXLogo(keyCode);
               break;
            case 1:
               this.onMainScreen(keyCode);
               break;
            case 2:
               this.onGameScreen(keyCode);
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void onXLogo(int keyCode) {
      this.logo_ = null;
      this.drawStatus_ = 1;
   }

   private void onMainScreen(int keyCode) {
      if (this.shoud) {
         if (keyCode == KEYCODE_UP || keyCode == 50) {
            if (this.menuStatus_ != 3 && this.menuStatus_ != 1) {
               if (this.menuStatus_ == 2) {
                  MySound.canSound = !MySound.canSound;
               } else if (this.menuStatus_ == 0) {
                  this.isOff = false;
                  this.menusetX = 0;
                  --this.currentKeyCode;
                  if (this.currentKeyCode < 0) {
                     this.currentKeyCode = 4;
                  }
               }
            } else {
               --this.menuCount_;
            }
         }

         if ((keyCode == KEYCODE_RIGHT || keyCode == 54) && this.menuStatus_ == 0) {
         }

         if (keyCode == KEYCODE_DOWN || keyCode == 56) {
            if (this.menuStatus_ != 3 && this.menuStatus_ != 1) {
               if (this.menuStatus_ == 2) {
                  MySound.canSound = !MySound.canSound;
               } else if (this.menuStatus_ == 0) {
                  this.isOff = false;
                  this.menusetX = 0;
                  this.currentKeyCode = (this.currentKeyCode + 1) % 5;
               }
            } else {
               ++this.menuCount_;
            }
         }

         if ((keyCode == KEYCODE_LEFT || keyCode == 52) && this.menuStatus_ == 0) {
         }

         if (keyCode == KEYCODE_FIRE || keyCode == KEYCODE_SOFTKEY_LEFT || keyCode == 53) {
            if (this.currentKeyCode == 0) {
               this.FreeMenuSourse();
               this.canChange = false;
               this.drawStatus_ = 2;
               this.gameIni();
            } else if (this.currentKeyCode == 3) {
               if (this.menuStatus_ == 0) {
                  this.menuStatus_ = 3;
                  return;
               }

               if (this.menuStatus_ == 0) {
                  this.strsToShow = null;
                  this.strsToShow1 = null;
                  this.strsToShow2 = null;
                  this.strsToShow3 = null;
               }
            } else if (this.currentKeyCode == 2) {
               if (this.menuStatus_ == 0) {
                  this.menuStatus_ = 2;
                  return;
               }
            } else if (this.currentKeyCode == 1) {
               if (this.menuStatus_ == 0) {
                  this.menuStatus_ = 1;
                  return;
               }

               if (this.menuStatus_ == 0) {
                  this.strsToShow = null;
                  this.strsToShow1 = null;
                  this.strsToShow2 = null;
                  this.strsToShow3 = null;
               }
            } else if (this.currentKeyCode == 4) {
               runing = false;
               if (this.logo_Back != null) {
                  this.logo_Back.stop();
               }

               TheMIDlet.quitApp();
            }
         }

         if (keyCode == KEYCODE_FIRE || keyCode == KEYCODE_SOFTKEY_RIGHT || keyCode == 53) {
            if (this.currentKeyCode == 2 && this.menuStatus_ == 2) {
               this.menuStatus_ = 0;
            }

            if (this.currentKeyCode == 3 && this.menuStatus_ == 3) {
               this.menuStatus_ = 0;
               this.strsToShow = null;
               this.strsToShow1 = null;
               this.strsToShow2 = null;
               this.strsToShow3 = null;
               this.menuCount_ = 0;
            }

            if (this.currentKeyCode == 1 && this.menuStatus_ == 1) {
               this.menuStatus_ = 0;
               this.strsToShow = null;
               this.strsToShow1 = null;
               this.strsToShow2 = null;
               this.strsToShow3 = null;
               this.menuCount_ = 0;
            }
         }
      }

   }

   private void onGameScreen(int keyCode) {
      if (this.preDisplayed || this.isShowStr || keyCode != KEYCODE_FIRE && keyCode != KEYCODE_SOFTKEY_LEFT && keyCode != 53) {
         if (!this.preDisplayed && this.isShowStr) {
            ++this.preCount;
            if (this.preCount == 4) {
               this.preDisplayed = true;
               this.isShowStr = false;
               this.pre1 = null;
               this.pre2 = null;
               this.pre3 = null;
               this.strsToShow = null;
               this.toolStates[31] = 1;
               System.gc();
            }

         } else if (this.toolStates[15] == 10) {
            this.FreeGameSourse();
            this.canChange = false;
            this.drawStatus_ = 1;
            this.menuStatus_ = 0;
         } else {
            if (keyCode == KEYCODE_FIRE && !this.canExit && !this.isHelp && !this.isMusic) {
               keyCode = 53;
            }

            if (keyCode != KEYCODE_SOFTKEY_LEFT && keyCode != KEYCODE_FIRE || !this.isHelp && !this.isMusic) {
               if (keyCode == KEYCODE_SOFTKEY_RIGHT && this.isOutpasswordkuang) {
                  this.isOutpasswordkuang = false;
                  this.keydownNum = 0;
                  this.numpass_ = null;
                  this.toolStates[8] = 0;
                  this.toolStates[9] = 0;
                  this.toolStates[10] = 0;
                  this.toolStates[11] = 0;
                  this.toolStates[12] = 0;
                  this.toolStates[13] = 0;
                  this.toolStates[14] = 0;
               }

               if (!this.zong) {
                  if (this.bianl == 0) {
                     if (keyCode == 53 || keyCode == KEYCODE_SOFTKEY_RIGHT) {
                        this.bianl = 1;
                     }
                  } else if (this.bianl == 1 && (keyCode == 53 || keyCode == KEYCODE_SOFTKEY_RIGHT)) {
                     this.bianl = 2;
                  }
               }

               if (!this.zong && this.bide == 3 && (keyCode == 53 || keyCode == KEYCODE_SOFTKEY_RIGHT)) {
                  this.wzdh = true;
               }

               if (!this.zong && this.ksdi && (keyCode == 53 || keyCode == KEYCODE_SOFTKEY_RIGHT)) {
                  this.bianl = 3;
               }

               if ((keyCode == KEYCODE_SOFTKEY_RIGHT && !this.guanzi || keyCode == KEYCODE_FIRE) && this.toolStates[6] != 7 && !this.isHelp && !this.isMusic && this.zimua && this.jianpan && this.zong && !this.guanzi && !this.canExit) {
                  this.canExit = true;
                  this.isHelp = false;
                  this.isMusic = false;
               } else {
                  if (keyCode == KEYCODE_SOFTKEY_RIGHT && this.guanzi) {
                     this.guanzi = false;
                     this.shuodi = 0;
                  }

                  if (this.canExit) {
                     if (keyCode == KEYCODE_SOFTKEY_LEFT || keyCode == KEYCODE_FIRE || keyCode == 53) {
                        if (GAME_CURRENT == 0) {
                           this.canExit = false;
                           return;
                        }

                        if (GAME_CURRENT == 2) {
                           this.isMusic = true;
                           this.canExit = false;
                           return;
                        }

                        if (GAME_CURRENT == 1) {
                           this.isHelp = true;
                           this.canExit = false;
                           return;
                        }

                        if (GAME_CURRENT == 3) {
                           this.FreeGameSourse();
                           this.canChange = false;
                           this.drawStatus_ = 1;
                           this.menuStatus_ = 0;
                           return;
                        }
                     }

                     if (keyCode == KEYCODE_UP || keyCode == 50) {
                        --GAME_CURRENT;
                        if (GAME_CURRENT < 0) {
                           GAME_CURRENT = 3;
                        }
                     }

                     if (keyCode == KEYCODE_DOWN || keyCode == 56) {
                        ++GAME_CURRENT;
                        if (GAME_CURRENT > 3) {
                           GAME_CURRENT = 0;
                        }
                     }
                  }

                  if (this.isMusic) {
                     if (keyCode == KEYCODE_UP || keyCode == 50) {
                        MySound.canSound = !MySound.canSound;
                     }

                     if (keyCode == KEYCODE_DOWN || keyCode == 56) {
                        MySound.canSound = !MySound.canSound;
                     }
                  }

                  if (this.stringForShow.equals("无效使用！")) {
                     this.usingTool = false;
                     this.hand.currentFrame = 0;
                     this.changeClosedMid(2);
                  }

                  this.stringForShow = "";
                  this.toolStringToshow = "";
                  this.changeClosedMid(1);
                  this.changeClosedMid(3);
                  if (this.showTool && !this.canExit && !this.isHelp && !this.isMusic) {
                     this.onTool(keyCode);
                  } else {
                     if (this.hand.currentFrame == 0 && keyCode == 53 && this.usingTool) {
                        this.stringForShow = "无效使用！";
                     } else {
                        this.stringForShow = "";
                     }

                     if (keyCode == 49 && !this.canExit && !this.isHelp && !this.isMusic && this.toolStates[6] < 7 && !this.isOutpasswordkuang && this.zimua && this.jianpan && this.zong) {
                        this.showTool = true;
                        switch(this.toolNums) {
                        case 1:
                           this.currentTool = 0;
                           break;
                        case 2:
                        case 3:
                           this.currentTool = 1;
                           break;
                        case 4:
                           this.currentTool = 2;
                           break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                           this.currentTool = 3;
                        }
                     }

                     if (keyCode == KEYCODE_FIRE) {
                     }

                     if (!this.canExit && !this.isHelp && !this.isMusic) {
                        if (this.currentRoom == 1) {
                           this.onRoom1(keyCode);
                        }

                        this.onHand(keyCode);
                     }

                     if (this.stringForShow.equals("无效使用！")) {
                        this.usingTool = false;
                        this.hand.currentFrame = 0;
                     } else if ((this.showTool || !this.stringForShow.startsWith("得到")) && !this.showTool && this.stringForShow.startsWith("使用")) {
                     }

                     if (keyCode == 35) {
                     }
                  }

               }
            } else {
               this.canExit = true;
               this.isHelp = false;
               this.isMusic = false;
            }
         }
      } else {
         this.sw1 = true;
         this.sw = 500;
         this.sw2 = 176;
         this.fsi = false;
         this.jish = 41;
         this.logo_ = null;
         this.menuback = null;
         this.lingjian = null;
      }
   }

   private void drawExitMusic() {
      if (this.isMusic) {
         this.gr.setColor(1, 1, 1);
         this.gr.fillRect(0, 0, WIDTH, HEIGHT);
         if (this.shou == null) {
            this.shou = this.loadImage("shou");
         }

         this.gr.setFont(afont);
         this.gr.setColor(100, 100, 255);
         this.gr.drawString("声音设置", WIDTH / 2 - afont.stringWidth("声音设置") / 2, 8, 0);
         this.gr.drawString("____________", WIDTH / 2 - afont.stringWidth("____________") / 2, 16, 0);
         if (MySound.canSound) {
            this.gr.setColor(255, 255, 255);
            this.gr.drawString("打开声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 - 18, 0);
            this.gr.drawImage(this.shou, (WIDTH - afont.stringWidth("打开声音")) / 2 + 68, HEIGHT / 2 - 16, 0);
            this.gr.setColor(200, 200, 200);
            this.gr.drawString("关闭声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 + 8, 0);
         } else {
            this.changeClosedMid(4);
            this.changeClosedMid(0);
            this.changeClosedMid(1);
            this.changeClosedMid(2);
            this.changeClosedMid(3);
            this.gr.setColor(200, 200, 200);
            this.gr.drawString("打开声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 - 18, 0);
            this.gr.setColor(255, 255, 255);
            this.gr.drawString("关闭声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 + 8, 0);
            this.gr.drawImage(this.shou, (WIDTH - afont.stringWidth("打开声音")) / 2 + 68, HEIGHT / 2 + 8, 0);
         }
      }

   }

   private void drawExitHelp() {
      if (this.isHelp) {
         this.gr.setColor(0, 0, 0);
         this.gr.fillRect(0, 0, WIDTH, HEIGHT);
         this.gr.setFont(afont);
         this.gr.setColor(100, 100, 255);
         this.gr.drawString("帮 助 信 息", (WIDTH - afont.stringWidth("帮 助 信 息")) / 2, 5, 0);
         this.gr.setColor(255, 255, 255);
         this.gr.drawString("提示:", 3, 15 + this.fH, 0);
         this.gr.drawString("1键:查看物品工具栏", 3, 20 + 2 * this.fH, 0);
         this.gr.drawString("2/4/6/8方向键移动", 3, 22 + 3 * this.fH, 0);
         this.gr.drawString("5键:使用道具", 3, 24 + 4 * this.fH, 0);
      }

   }

   private void drawPublicRoom() {
      if (!this.showTool && !this.canExit) {
         this.drawHand();
      }

   }

   private void onHand(int keyCode) {
      if (this.hand != null) {
         if (!this.isOutpasswordkuang) {
            if (keyCode == KEYCODE_UP && !this.an && this.jianpan && this.zong || keyCode == 50 && !this.an && this.jianpan && this.zong) {
               this.an = true;
               this.guanzi = false;
               this.shuodi = 0;
               this.pshang = true;
               this.pxia = false;
               this.pzuo = false;
               this.pyou = false;
               this.usingTool = false;

               for(this.j = 0; this.j < 3; ++this.j) {
                  this.hand.framesInfo[this.j][0] = this.j * 21;
                  this.hand.framesInfo[this.j][1] = 30;
                  this.hand.framesInfo[this.j][2] = 21;
                  this.hand.framesInfo[this.j][3] = 29;
               }

               this.hand.DY = -5;
               this.shang = true;
               if (this.offsetX == 2) {
                  this.rs = 2;
               }

               if (this.offsetX == 6) {
                  this.bian = 2;
               }

               if (this.offsetX == 13 && this.shengsd) {
                  this.sdfer = true;
               }
            } else if ((keyCode != KEYCODE_LEFT || this.an || !this.jianpan || !this.zong) && (keyCode != 52 || this.an || !this.jianpan || !this.zong)) {
               if ((keyCode != KEYCODE_DOWN || this.an || !this.jianpan || !this.zong) && (keyCode != 56 || this.an || !this.jianpan || !this.zong)) {
                  if (keyCode == KEYCODE_RIGHT && !this.an && this.jianpan && this.zong || keyCode == 54 && !this.an && this.jianpan && this.zong) {
                     this.an = true;
                     this.guanzi = false;
                     this.shuodi = 0;
                     this.pshang = false;
                     this.pxia = false;
                     this.pzuo = false;
                     this.pyou = true;
                     this.usingTool = false;

                     for(this.j = 0; this.j < 3; ++this.j) {
                        this.hand.framesInfo[this.j][0] = this.j * 21;
                        this.hand.framesInfo[this.j][1] = 89;
                        this.hand.framesInfo[this.j][2] = 21;
                        this.hand.framesInfo[this.j][3] = 28;
                     }

                     this.hand.DX = 5;
                     this.you = true;
                     if (this.offsetX == 6) {
                        this.bian = 2;
                     }

                     if (this.offsetX == 2) {
                        this.rs = 2;
                     }

                     if (this.offsetX == 13 && this.shengsd) {
                        this.sdfer = true;
                     }
                  }
               } else {
                  this.an = true;
                  this.guanzi = false;
                  this.shuodi = 0;
                  this.pshang = false;
                  this.pxia = true;
                  this.pzuo = false;
                  this.pyou = false;
                  this.usingTool = false;

                  for(this.j = 0; this.j < 3; ++this.j) {
                     this.hand.framesInfo[this.j][0] = this.j * 21;
                     this.hand.framesInfo[this.j][1] = 0;
                     this.hand.framesInfo[this.j][2] = 21;
                     this.hand.framesInfo[this.j][3] = 30;
                  }

                  this.hand.DY = 5;
                  this.xia = true;
                  if (this.offsetX == 6) {
                     this.bian = 2;
                  }

                  if (this.offsetX == 2) {
                     this.rs = 2;
                  }

                  if (this.offsetX == 13 && this.shengsd) {
                     this.sdfer = true;
                  }
               }
            } else {
               this.an = true;
               this.guanzi = false;
               this.shuodi = 0;
               this.pshang = false;
               this.pxia = false;
               this.pzuo = true;
               this.pyou = false;
               this.usingTool = false;

               for(this.j = 0; this.j < 3; ++this.j) {
                  this.hand.framesInfo[this.j][0] = this.j * 21;
                  this.hand.framesInfo[this.j][1] = 59;
                  this.hand.framesInfo[this.j][2] = 21;
                  this.hand.framesInfo[this.j][3] = 30;
               }

               this.hand.DX = -5;
               this.zuo = true;
               if (this.offsetX == 2) {
                  this.rs = 2;
               }

               if (this.offsetX == 6) {
                  this.bian = 2;
               }

               if (this.offsetX == 13 && this.shengsd) {
                  this.sdfer = true;
               }
            }
         }

      }
   }

   public void keyReleased(int keyCode) {
      if (this.canChange) {
         if (this.hand != null) {
            switch(this.drawStatus_) {
            case 2:
               this.an = false;
               this.qingchu = false;
               this.xia = false;
               this.shang = false;
               this.zuo = false;
               this.you = false;
               this.hand.currentFrame = 0;
               this.hand.DX = 0;
               this.hand.DY = 0;
            case 1:
            default:
            }
         }
      }
   }

   public void run() {
      long startTime = 0L;
      long finishTime = 0L;
      boolean var5 = false;

      while(runing) {
         startTime = System.currentTimeMillis();
         if (!this.pause) {
            ++this.Timer;
            if (this.fsi) {
               ++this.jish;
            }

            this.repaint();
            this.serviceRepaints();
         }

         finishTime = System.currentTimeMillis();

         try {
            Thread.sleep(100L - (finishTime - startTime) > 0L ? 100L - (finishTime - startTime) : 0L);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

   }

   private Image loadImage(String name) {
      try {
         return Image.createImage(String.valueOf((new StringBuffer("/")).append(name).append(".png")));
      } catch (Exception var3) {
         System.out.println("load image " + name + " error !");
         return null;
      }
   }

   private void drawFlashScreen() {
      if (null == this.flashImage_) {
         this.flashImage_ = this.loadImage("sp");
      }

      if (null == this.flashM_) {
         this.flashM_ = this.loadImage("flash");
      }

      ++this.drawCount_;
      if (this.drawCount_ < 10) {
         this.gr.setColor(255, 255, 255);
         this.gr.fillRect(0, 0, WIDTH, HEIGHT);
         this.gr.drawImage(this.flashM_, (WIDTH - this.flashM_.getWidth()) / 2, (HEIGHT - this.flashM_.getHeight()) / 2, 0);
      } else if (this.drawCount_ < 16) {
         this.gr.setColor(255, 255, 255);
         this.gr.fillRect(0, 0, WIDTH, HEIGHT);
         this.gr.drawImage(this.flashImage_, (WIDTH - this.flashImage_.getWidth()) / 2, (HEIGHT - this.flashImage_.getHeight()) / 2, 0);
      } else {
         this.drawCount_ = 0;
         this.flashImage_ = null;
         this.flashM_ = null;
         ++this.drawStatus_;
      }

   }

   private void drawXLogo() {
      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      if (this.logo12 == null) {
         this.logo12 = this.loadImage("LOGO1");
      }

      if (this.logo_ == null) {
         this.logo_ = this.loadImage("LOGO");
      }

      if (this.logore == null) {
         this.logore = this.loadImage("logore");
      }

      this.gr.drawRegion(this.logo_, 0, 0, 176, 208, 0, 0, 0, 0);
      this.changeClosedMid(4);
      this.createBackMid(0);
      this.playBackMid(0);
      this.gr.setColor(255, 255, 255);
      this.gr.setFont(afont);
      if (this.Timer / 20 % 2 == 0 && this.js == 1) {
         if (this.js == 1) {
            this.gr.drawRegion(this.logo_, 176, 0, 176, 208, 0, 0, 0, 0);
            this.js = 0;
         }
      } else if (this.Timer / 10 % 2 == 0 && ++this.js1 / 10 % 2 == 0) {
         this.gr.drawRegion(this.logo_, 176, 0, 176, 208, 0, 0, 0, 0);
      }

      this.gr.drawImage(this.logore, (WIDTH - 109) / 2, HEIGHT - 27 - 5, 0);
      this.gr.drawImage(this.logo12, (WIDTH - 119) / 2, 10, 0);
   }

   private void drawMainScreen() {
      this.hand.visible = true;
      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      if (this.menuStatus_ == 0) {
         if (this.menuback == null) {
            this.menuback = this.loadImage("menuback");
         }

         if (this.caidan == null) {
            this.caidan = this.loadImage("caidan");
         }

         if (this.shou == null) {
            this.shou = this.loadImage("shou");
         }

         this.jsh = null;
         this.gr.setClip(0, 0, 176, 220);
         this.gr.drawImage(this.menuback, (WIDTH - this.menuback.getWidth()) / 2, 0, 0);
         this.changeClosedMid(4);
         this.createBackMid(0);
         this.playBackMid(0);
         this.gr.setFont(afont);
         if (this.caxia < 73) {
            this.gr.drawRegion(this.caidan, 0, 0, 101, 141, 0, (WIDTH - 101) / 2, -40 + (this.caxia += 6), 0);
         } else {
            this.gr.drawRegion(this.caidan, 0, 0, 101, 141, 0, (WIDTH - 101) / 2, 33, 0);
            this.caxia = 208;
         }

         if (this.caxia > 73 && !this.shoud) {
            if (this.shoudw < 36) {
               this.gr.drawImage(this.shou, 176 - (this.shoudw += 4), (HEIGHT - 14) / 2 - 58, 0);
            } else {
               this.shoud = true;
            }
         }

         if (this.caxia > 73 && this.shoud) {
            switch(this.currentKeyCode) {
            case 0:
               this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 - 58, 0);
               this.gr.drawRegion(this.caidan, 101, 9, 72, 20, 0, (WIDTH - 72) / 2 - 1, (HEIGHT - 20) / 2 - 58, 0);
               break;
            case 1:
               this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 - 26, 0);
               this.gr.drawRegion(this.caidan, 101, 38, 72, 20, 0, (WIDTH - 72) / 2 - 1, (HEIGHT - 20) / 2 - 29, 0);
               break;
            case 2:
               this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2, 0);
               this.gr.drawRegion(this.caidan, 101, 65, 72, 20, 0, (WIDTH - 72) / 2 - 1, (HEIGHT - 20) / 2 - 2, 0);
               break;
            case 3:
               this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 + 27, 0);
               this.gr.drawRegion(this.caidan, 101, 93, 72, 20, 0, (WIDTH - 72) / 2 - 1, (HEIGHT - 20) / 2 + 26, 0);
               break;
            case 4:
               this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 + 58, 0);
               this.gr.drawRegion(this.caidan, 101, 122, 72, 19, 0, (WIDTH - 72) / 2 - 1, (HEIGHT - 20) / 2 + 55, 0);
            }
         }
      } else if (this.menuStatus_ == 2) {
         this.MainScreenDrawSetting();
      } else if (this.menuStatus_ == 3) {
         this.MainScreenDrawHelp();
      } else if (this.menuStatus_ == 1) {
         this.MainScreenDrawAbout();
      }

   }

   private void drawGamePre() {
      this.gr.setColor(0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      if (this.preTop > HEIGHT - 6 * this.fH - this.fH) {
         if (this.pre1 == null) {
         }
      } else if (this.preTop <= HEIGHT - 6 * this.fH - this.fH && this.preTop > HEIGHT - 18 * this.fH - this.fH) {
         this.pre1 = null;
         System.gc();
         if (this.pre2 == null) {
         }
      } else {
         this.kaishi1 = null;
         this.jindu1 = null;
         this.isShowStr = true;
         this.pre2 = null;
         if (this.pre3 == null) {
         }

         switch(this.preCount) {
         case 0:
            this.drawstringToScreen("一块石头出现在艾玛面前，上面有一道裂缝，"); // Перед Эммой появился камень с трещиной на нем，
            break;
         case 1:
            this.drawstringToScreen("足够将一把剑插进去的裂缝，宝剑怎么了？难"); // Достаточно вставить меч в щель, чтобы понять, что случилось с мечом?сложный
            break;
         case 2:
            this.drawstringToScreen("道它和这块石头有什么关系，艾玛将宝剑对准那个"); // Я не знаю, какое это имеет отношение к этому камню, - Эмма указала мечом на него.
            break;
         case 3:
            this.drawstringToScreen("裂缝插了进去一阵巨响过后，宫殿的大门开启了……"); // Щель была вставлена, и после громкого шума ворота дворца открылись.……
         }
      }

   }

   private void drawPreStr() {
      if (this.kaishi1 == null) {
         this.kaishi1 = this.loadImage("jindu");
      }

      if (this.jindu1 == null) {
         this.jindu1 = this.loadImage("jindu1");
      }

      this.gr.drawImage(this.kaishi1, 0, 0, 0);
      this.gr.setColor(0, 0, 0);
      this.gr.drawImage(this.jindu1, (WIDTH - 113) / 2, (HEIGHT - 13) / 2 + 70, 0);
      this.gr.fillRect(31 - this.juli++, 165, 113, 15);
      this.gr.fillRect(31 + this.juli11++, 165, 113, 15);
   }

   private int drawStringPre(String[] str, int left, int top, int rowIndex, int rowCountToDraw, Font font) {
      int returnValue = rowIndex;
      int rowair = 1;
      this.gr.setFont(font);
      if (rowIndex <= 0) {
         returnValue = 0;
      }

      if (returnValue >= str.length - 1) {
         returnValue = str.length - 1;
      }

      for(int i = 0; i < rowCountToDraw && i + returnValue < str.length; ++i) {
         if (top + i * (afont.getHeight() + rowair) < 124 - this.fH) {
            this.gr.setColor(0, 0, 0);
         } else if (top + i * (afont.getHeight() + rowair) < 125 - this.fH) {
            this.gr.setColor(67, 67, 66);
         } else if (top + i * (afont.getHeight() + rowair) < 127 - this.fH) {
            this.gr.setColor(131, 131, 129);
         } else if (top + i * (afont.getHeight() + rowair) < 129 - this.fH) {
            this.gr.setColor(148, 147, 146);
         } else if (top + i * (afont.getHeight() + rowair) < 131 - this.fH) {
            this.gr.setColor(163, 162, 160);
         } else if (top + i * (afont.getHeight() + rowair) < 133 - this.fH) {
            this.gr.setColor(179, 179, 177);
         } else if (top + i * (afont.getHeight() + rowair) < 135 - this.fH) {
            this.gr.setColor(197, 196, 192);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 - 9 - this.fH) {
            this.gr.setColor(246, 245, 242);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 - 7 - this.fH) {
            this.gr.setColor(183, 183, 179);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 - 5 - this.fH) {
            this.gr.setColor(152, 152, 150);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 - 3 - this.fH) {
            this.gr.setColor(152, 152, 150);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 - 1 - this.fH) {
            this.gr.setColor(130, 130, 127);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 1 - this.fH) {
            this.gr.setColor(130, 130, 127);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 3 - this.fH) {
            this.gr.setColor(90, 90, 89);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 5 - this.fH) {
            this.gr.setColor(80, 80, 78);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 7 - this.fH) {
            this.gr.setColor(80, 80, 78);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 9 - this.fH) {
            this.gr.setColor(66, 66, 65);
         } else if (top + i * (afont.getHeight() + rowair) < HEIGHT - 12 + 11 - this.fH) {
            this.gr.setColor(66, 66, 65);
         }

         this.gr.drawString(str[i + returnValue], left, top + i * (afont.getHeight() + rowair), 0);
      }

      return returnValue;
   }

   private void MainScreenDrawHelp() {
      try {
         if (this.strsToShow == null) {
            String temp = "[游戏名称]\n三更灵异事件\n[开发商]\n北京无限乐游科技有限公司\n[内容简介]\n阴霾下暗藏汹涌，大多数人相信这个世界上是有鬼的。每个阴暗角落的灵魂都可能经历过一个凄惨绝望令人遗憾的故事。这些死者的鬼魂和那些活着的生者，都是故事的主角，谁都不能逃脱命运的诅咒。";
            // [Название игры]\n Еще три сверхъестественных события \n
            // [Разработчик]\n Beijing Infinite Music Tour Technology Co., Ltd.\n
            // [Введение в содержание]\n Большинство людей верят, что в этом мире существуют призраки, скрытые и бушующие под покровом тумана.
            // [Душа, живущая в каждом темном уголке, возможно, пережила несчастную, отчаянную и достойную сожаления историю.Призраки умерших и те, кто еще жив, являются главными действующими лицами этой истории, и никто не может избежать проклятия судьбы.
            temp = temp + "\n[操作说明]\n";
            temp = temp + "1键:查看物品工具栏\n";
            temp = temp + "2键或上:向上移动\n";
            temp = temp + "8键或下:向下移动\n";
            temp = temp + "4键或左:向左移动\n";
            temp = temp + "6键或右:向右移动\n";
            temp = temp + "5键:使用道具\n";
            this.strsToShow = this.format(temp, WIDTH - 20, afont);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.beijings();
      this.gr.setFont(afont);
      this.gr.setColor(100, 100, 255);
      this.menuCount_ = this.drawString(this.strsToShow, 10, 30, this.menuCount_, 7, afont);
   }

   private void MainScreenDrawSetting() {
      this.gr.setColor(1, 1, 1);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      if (this.shou == null) {
         this.shou = this.loadImage("shou");
      }

      this.beijings();
      this.gr.setFont(afont);
      this.gr.setColor(100, 100, 255);
      if (MySound.canSound) {
         this.gr.setColor(255, 255, 255);
         this.heidrawString("打开声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 - 18, 0);
         this.gr.drawImage(this.shou, (WIDTH - afont.stringWidth("打开声音")) / 2 + 68, HEIGHT / 2 - 16, 0);
         this.gr.setColor(200, 200, 200);
         this.heidrawString("关闭声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 + 8, 0);
      } else {
         this.gr.setColor(200, 200, 200);
         this.heidrawString("打开声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 - 18, 0);
         this.gr.setColor(255, 255, 255);
         this.heidrawString("关闭声音", (WIDTH - afont.stringWidth("打开声音")) / 2, HEIGHT / 2 + 8, 0);
         this.gr.drawImage(this.shou, (WIDTH - afont.stringWidth("打开声音")) / 2 + 68, HEIGHT / 2 + 8, 0);
         this.closedMid(0);
      }

   }

   private void vConvert(Graphics g, Image img, int zx, int zy, int zk, int zg, int zs, int x, int y) {
      if (img != null) {
         int w = img.getWidth();
         int h = img.getHeight();

         for(int i = 0; i < h; ++i) {
            g.setClip(x, y + i, w, 1);
            g.drawRegion(img, zx, zy, zk, zg, zs, x, y - h + 2 * i, 20);
         }

      }
   }

   private void beijings() {
      if (this.lingjian == null) {
         this.lingjian = this.loadImage("lingjian");
      }

      this.gr.drawImage(this.menuback, (WIDTH - this.menuback.getWidth()) / 2, 0, 0);
      this.gr.drawRegion(this.lingjian, 0, 0, 19, 20, 2, 2, 2, 0);
      this.gr.drawRegion(this.lingjian, 0, 0, 19, 20, 0, WIDTH - 21, 2, 0);
      this.gr.drawRegion(this.lingjian, 0, 0, 19, 20, 4, 2, 182, 0);
      this.gr.drawRegion(this.lingjian, 19, 2, 5, 17, 0, 4, 23, 0);
      this.gr.drawRegion(this.lingjian, 19, 2, 5, 17, 0, 168, 23, 0);
      this.vConvert(this.gr, this.lingjian, 0, 0, 19, 20, 0, WIDTH - 21, 181);
      this.vConvert(this.gr, this.lingjian, 19, 2, 5, 17, 0, 4, 170);
      this.vConvert(this.gr, this.lingjian, 19, 2, 5, 17, 0, 168, 170);
      this.gr.setClip(0, 0, WIDTH, HEIGHT);

      int i;
      for(i = 0; i < 15; ++i) {
         this.gr.drawRegion(this.lingjian, 20, 10, 3, 9, 0, 5, 40 + i * 9, 0);
         this.gr.drawRegion(this.lingjian, 20, 10, 3, 9, 0, 169, 40 + i * 9, 0);
      }

      if (!this.tur) {
         if (this.isd < 94) {
            this.isd += 6;
         } else {
            this.tur = true;
         }
      } else {
         this.isd -= 6;
         if (this.isd == 0) {
            this.tur = false;
         }
      }

      this.gr.drawRegion(this.lingjian, 24, 17, 11, 2, 0, 165, 56 + this.isd, 0);
      this.gr.drawRegion(this.lingjian, 24, 17, 11, 2, 0, 1, 153 - this.isd, 0);

      for(i = 0; i < 27; ++i) {
         this.gr.drawRegion(this.lingjian, 0, 2, 5, 4, 0, 21 + i * 5, 4, 0);
         this.gr.drawRegion(this.lingjian, 0, 2, 5, 4, 0, 21 + i * 5, 196, 0);
      }

   }

   private void jiaju() {
      if (this.offsetX == 4 && (this.pxia || this.pyou || this.pzuo)) {
         this.gr.drawRegion(this.lou, 65, 93, 8, 35, 0, 116, 175, 0);
      }

      if (this.offsetX == 16) {
         this.gr.drawRegion(this.back4, 129, 96, 29, 14, 0, 129, 96, 0);
         this.gr.drawRegion(this.back4, 162, 148, 6, 8, 0, 162, 148, 0);
         this.gr.drawRegion(this.back4, 8, 118, 6, 10, 0, 8, 118, 0);
      }

      if (this.offsetX == 15) {
         this.gr.drawRegion(this.back15, 128, 104, 39, 14, 0, 128, 104, 0);
      }

      if (this.offsetX == 10) {
         this.gr.drawRegion(this.back10, 120, 151, 49, 8, 0, 120, 151, 0);
      }

      if (this.offsetX == 8) {
         this.gr.drawRegion(this.back8, 91, 153, 74, 11, 0, 91, 153, 0);
      }

      if (this.offsetX == 13) {
         this.gr.drawRegion(this.back13, 5, 112, 21, 63, 0, 5, 112, 0);
         this.gr.drawRegion(this.back13, 125, 121, 44, 17, 0, 125, 121, 0);
      }

      if (this.offsetX == 1 && !this.isOutpasswordkuang) {
         this.gr.drawRegion(this.back2, 81, 101, 5, 15, 0, 81, 101, 0);
         this.gr.drawRegion(this.back2, 95, 105, 47, 11, 0, 95, 105, 0);
         this.gr.drawRegion(this.back2, 154, 101, 5, 15, 0, 154, 101, 0);
      }

      if (this.offsetX == 17) {
         this.gr.drawRegion(this.back5, 54, 153, 15, 21, 0, 54, 153, 0);
         this.gr.drawRegion(this.back5, 74, 153, 15, 21, 0, 74, 153, 0);
         this.gr.drawRegion(this.back5, 5, 128, 37, 44, 0, 5, 128, 0);
         this.gr.drawRegion(this.back5, 136, 140, 31, 35, 0, 136, 140, 0);
      }

   }

   private void MainScreenDrawAbout() {
      try {
         if (this.strsToShow == null) {
            String temp = "北京无限乐游科技有限公司发行\n";
            temp = temp + "客服电话: 8008038259\n";
            temp = temp + "客服邮箱: game0311kf@126.com\n";
            this.strsToShow = this.format(temp, WIDTH - 50, afont);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.beijings();
      this.gr.setFont(afont);
      this.gr.setColor(100, 100, 255);
      this.menuCount_ = this.drawString(this.strsToShow, 28, 40, this.menuCount_, SHOW_ROW, afont);
   }

   private void heidrawString(String str, int x, int y, int k) {
      this.gr.setColor(149, 147, 144);
      this.gr.drawString(str, x + 1, y, 0);
      this.gr.drawString(str, x - 1, y, 0);
      this.gr.drawString(str, x, y + 1, 0);
      this.gr.drawString(str, x, y - 1, 0);
      this.gr.setColor(255, 255, 255);
      this.gr.drawString(str, x, y, 0);
   }

   private int drawString(String[] str, int left, int top, int rowIndex, int rowCountToDraw, Font font) {
      int returnValue = rowIndex;
      int rowair = 2;
      this.gr.setFont(font);
      if (rowIndex <= 0) {
         returnValue = 0;
      }

      if (returnValue >= str.length - 1 - 2) {
         returnValue = str.length - 1 - 2;
      }

      this.gr.setColor(255, 255, 255);

      for(int i = 0; i < rowCountToDraw && i + returnValue < str.length; ++i) {
         this.heidrawString(str[i + returnValue], left, top + i * (afont.getHeight() + rowair), 0);
      }

      return returnValue;
   }

   private int drawStrings(String[] str, int left, int top, int rowIndex, int rowCountToDraw, Font font) {
      int returnValue = rowIndex;
      int rowair = 2;
      this.gr.setFont(font);
      if (rowIndex <= 0) {
         returnValue = 0;
      }

      if (returnValue >= str.length - 1 - 2) {
         returnValue = str.length - 1 - 2;
      }

      this.gr.setColor(255, 255, 255);

      for(int i = 0; i < rowCountToDraw && i + returnValue < str.length; ++i) {
         this.gr.drawString(str[i + returnValue], left, top + i * (afont.getHeight() + rowair), 0);
      }

      return returnValue;
   }

   private int drawStringPass(String[] str, int left, int top, int rowIndex, int rowCountToDraw, Font font) {
      int returnValue = rowIndex;
      int rowair = 2;
      this.gr.setFont(font);
      if (rowIndex <= 0) {
         returnValue = 0;
      }

      if (returnValue >= str.length - 1) {
         returnValue = str.length - 1;
      }

      this.gr.setColor(255, 255, 255);

      for(int i = 0; i < rowCountToDraw && i + returnValue < str.length; ++i) {
         this.gr.drawString(str[i + returnValue], left, top + i * (afont.getHeight() + rowair), 0);
      }

      return returnValue;
   }

   public void FreeMenuSourse() {
      this.js = 1;
      this.js1 = 0;
      this.strsToShow = null;
      this.logo_ = null;
      this.logore = null;
      this.logo12 = null;
      this.menuback = null;
      this.lingjian = null;
      this.caxia = 0;
      this.shoudw = 0;
      this.shoud = false;
      this.caidan = null;
      this.shou = null;
      System.gc();
   }

   private void drawHand() {
      if (this.offsetX == 0) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 113 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 0) {
               this.qingchu = true;
               this.hand.DX = 0;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 83 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.kaigui1) {
               if (this.hand.positionX > 0 && this.hand.positionX < 85 && this.hand.positionY > 0 && this.hand.positionY < 118) {
                  this.hand.DX = 0;
                  this.qingchu = true;
               }
            } else if (this.hand.positionX > 0 && this.hand.positionX < 70 && this.hand.positionY > 0 && this.hand.positionY < 118) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0 && (this.hand.positionX > 0 && this.hand.positionX < 80 && this.hand.positionY > 155 && this.hand.positionY < 208 || this.hand.positionX > 115 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208 || this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208)) {
            this.hand.DY = 0;
            this.qingchu = true;
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 110) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.kaigui1) {
               if (this.hand.positionX > 0 && this.hand.positionX < 80 && this.hand.positionY > 0 && this.hand.positionY < 123) {
                  this.hand.DY = 0;
                  this.qingchu = true;
               }
            } else if (this.hand.positionX > 0 && this.hand.positionX < 65 && this.hand.positionY > 0 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 1) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 58 && this.hand.positionX < 138 && this.hand.positionY > 83 && this.hand.positionY < 110) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 138 && this.hand.positionY > 103 && this.hand.positionY < 123) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 68 && this.hand.positionX < 208 && this.hand.positionY > 0 && this.hand.positionY < 58) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 0 && this.hand.positionY > 0 && this.hand.positionY < 86) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX + this.hand.DX <= 0 && this.hand.positionY > 118 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 83 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 83 && this.hand.positionX < 148 && this.hand.positionY > 103 && this.hand.positionY < 123) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 78 && this.hand.positionY > 155 && this.hand.positionY < 208 || this.hand.positionX > 115 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208 || this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 63 && this.hand.positionX < 208 && this.hand.positionY > 78 && this.hand.positionY < 100) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 58) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 78 && this.hand.positionX < 138 && this.hand.positionY > 90 && this.hand.positionY < 128) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 63 && this.hand.positionX < 208 && this.hand.positionY > 90 && this.hand.positionY < 118) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 208 && this.hand.positionY > 0 && this.hand.positionY < 63) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 2) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > 93 && this.hand.positionX < 123 && this.hand.positionY > 0 && this.hand.positionY < 108) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > WIDTH - 20 && this.hand.positionX < WIDTH && this.hand.positionY > 0 && this.hand.positionY < 108) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX <= -10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 98 && this.hand.positionX < 128 && this.hand.positionY > -10 && this.hand.positionY < 108) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0 && this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
            this.hand.DY = 0;
            this.qingchu = true;
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 108 && this.hand.positionY > 0 && this.hand.positionY < 100) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 98 && this.hand.positionX < 123 && this.hand.positionY > 0 && this.hand.positionY < 110) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 208 && this.hand.positionY > -10 && this.hand.positionY < 0) {
               this.offsetX = 4;
               this.hand.positionX = 138;
               this.hand.positionY = 168;
            }
         }
      }

      if (this.offsetX == 3 || this.offsetX == 5) {
         if (this.hand.DX < 0 && this.hand.positionX + this.hand.DX <= 0) {
            this.hand.DX = 0;
            this.qingchu = true;
         }

         if (this.hand.DY > 0 && this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
            this.hand.DY = 0;
            this.qingchu = true;
         }

         if (this.hand.DY < 0 && this.hand.positionY < 100) {
            this.hand.DY = 0;
            this.qingchu = true;
         }
      }

      if (this.offsetX == 6) {
         if (this.hand.DX < 0 && this.hand.positionX <= -10) {
            this.hand.DX = 0;
            this.qingchu = true;
         }

         if (this.hand.DY > 0 && this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
            this.hand.DY = 0;
            this.qingchu = true;
         }

         if (this.hand.DY < 0 && this.hand.positionY < 100) {
            this.hand.DY = 0;
            this.qingchu = true;
         }
      }

      if (this.offsetX == 7) {
         if (this.hand.DX > 0 && this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
            this.hand.DX = 0;
            this.qingchu = true;
         }

         if (this.hand.DX < 0 && this.hand.positionX <= -10) {
            this.hand.DX = 0;
            this.qingchu = true;
         }

         if (this.hand.DY > 0 && this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
            this.hand.DY = 0;
            this.qingchu = true;
         }

         if (this.hand.DY < 0 && this.hand.positionY < 100) {
            this.hand.DY = 0;
            this.qingchu = true;
         }
      }

      if (this.offsetX == 4) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] + 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > WIDTH - 20 && this.hand.positionX < WIDTH && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX <= -10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 128 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 123 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.offsetX = 2;
               this.hand.positionX = 138;
               this.hand.positionY = -2;
            }
         }

         if (this.hand.DY < 0 && this.hand.positionY < 100) {
            this.hand.DY = 0;
            this.qingchu = true;
         }
      }

      if (this.offsetX == 10) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 58 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 113 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 108) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 98 && this.hand.positionX < 176 && this.hand.positionY > 130 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 28 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 38 && this.hand.positionY > 0 && this.hand.positionY < 133) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 33 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 103 && this.hand.positionX < 176 && this.hand.positionY > 124 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 78) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 33 && this.hand.positionY > 0 && this.hand.positionY < 133) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 63 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 118 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 113) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 14) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 38 && this.hand.positionX < 98 && this.hand.positionY > 56 && this.hand.positionY < 118) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 113 && this.hand.positionX < 176 && this.hand.positionY > 55 && this.hand.positionY < 118) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 63 && this.hand.positionY > 0 && this.hand.positionY < 53) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 43 && this.hand.positionY > 0 && this.hand.positionY < 118) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 43 && this.hand.positionX < 108 && this.hand.positionY > 56 && this.hand.positionY < 118) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 28 && this.hand.positionY > 123 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 128 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 123 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 43 && this.hand.positionX < 103 && this.hand.positionY > 48 && this.hand.positionY < 118) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 118 && this.hand.positionX < 176 && this.hand.positionY > 48 && this.hand.positionY < 118) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 23 && this.hand.positionY > 118 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 53) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 33 && this.hand.positionY > 0 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 43 && this.hand.positionX < 103 && this.hand.positionY > 93 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 0 && this.hand.positionY < 58) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 118 && this.hand.positionX < 176 && this.hand.positionY > 93 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 11) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 43 && this.hand.positionY > 0 && this.hand.positionY < 83) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 83 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 78 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 113 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 73) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 38 && this.hand.positionY > 0 && this.hand.positionY < 88 || this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 83) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 8) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 58 && this.hand.positionX < 80 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 113 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 68 && this.hand.positionX < 176 && this.hand.positionY > 133 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 63 && this.hand.positionX < 176 && this.hand.positionY > 148 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 63 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 80 && this.hand.positionX < 113 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 28 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 23 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 68 && this.hand.positionX < 176 && this.hand.positionY > 143 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 176 && this.hand.positionY > 128 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 56) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 63 && this.hand.positionX < 108 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 118 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 13) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 68 && this.hand.positionX < 138 && this.hand.positionY > 0 && this.hand.positionY < 63 || this.hand.positionX > 68 && this.hand.positionX < 98 && this.hand.positionY > 0 && this.hand.positionY < 73) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 103 && this.hand.positionX < 176 && this.hand.positionY > 103 && this.hand.positionY < 148) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 68 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 138 && this.hand.positionY > 0 && this.hand.positionY < 73) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 28 && this.hand.positionY > 93 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 43 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 38 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 175 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 23 && this.hand.positionY > 88 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 98 && this.hand.positionY < 133) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 73 && this.hand.positionX < 133 && this.hand.positionY > 0 && this.hand.positionY < 78) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 103 && this.hand.positionY < 153) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 9) {
         if (this.hand.DX > 0 && (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10 || this.hand.positionX > 43 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 68)) {
            this.hand.DX = 0;
            this.qingchu = true;
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 73 && this.hand.positionY > 78 && this.hand.positionY < 128 || this.hand.positionX > 0 && this.hand.positionX < 38 && this.hand.positionY > 0 && this.hand.positionY < 68 || this.hand.positionX > 0 && this.hand.positionX < 128 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (!this.shuji1 && this.hand.positionX > 0 && this.hand.positionX < 93 && this.hand.positionY > 98 && this.hand.positionY < 128) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 123 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 68 && this.hand.positionY > 73 && this.hand.positionY < 80) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (!this.shuji1 && this.hand.positionX > 0 && this.hand.positionX < 88 && this.hand.positionY > 93 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 58) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 68 && this.hand.positionY > 83 && this.hand.positionY < 133 || this.hand.positionX > 48 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 73 || this.hand.positionX > 0 && this.hand.positionX < 33 && this.hand.positionY > 0 && this.hand.positionY < 73) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (!this.shuji1 && this.hand.positionX > 0 && this.hand.positionX < 88 && this.hand.positionY > 98 && this.hand.positionY < 133) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 12) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 88 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3 || this.hand.positionX > 0 && this.hand.positionX < 73 && this.hand.positionY > 0 && this.hand.positionY < 98 || this.hand.positionX > 0 && this.hand.positionX < 28 && this.hand.positionY > 128 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 63 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 93 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 23 && this.hand.positionY > 123 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 88) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 68 && this.hand.positionY > 0 && this.hand.positionY < 103) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 15) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 70 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 63) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 100 && this.hand.positionX < 176 && this.hand.positionY > 83 && this.hand.positionY < 123) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 93 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 65 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 65 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 98 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 105 && this.hand.positionX < 176 && this.hand.positionY > 78 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 58) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 105 && this.hand.positionX < 176 && this.hand.positionY > 100 && this.hand.positionY < 128) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 60 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 75 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 16) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 103 && this.hand.positionX < 176 && this.hand.positionY > 78 && this.hand.positionY < 123) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 88 && this.hand.positionX < 176 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 128 && this.hand.positionX < 176 && this.hand.positionY > 123 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 128 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 63) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 63 && this.hand.positionY > 0 && this.hand.positionY < 88) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 65 && this.hand.positionY > 158 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 28 && this.hand.positionY > 93 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 170 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 93 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 73 && this.hand.positionY < 123) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 23 && this.hand.positionY > 88 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 133 && this.hand.positionX < 176 && this.hand.positionY > 118 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 58 && !this.k1) {
               this.hand.DY = 0;
               this.qingchu = true;
            } else if (this.hand.positionY < 78 && this.k1) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 108 && this.hand.positionX < 176 && this.hand.positionY > 100 && this.hand.positionY < 128) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 58 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 133 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      if (this.offsetX == 17) {
         if (this.hand.DX > 0) {
            if (this.hand.positionX > WIDTH - this.hand.framesInfo[this.hand.currentFrame][2] - 10) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 110 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 63) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 110 && this.hand.positionX < 176 && this.hand.positionY > 118 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DX < 0) {
            if (this.hand.positionX + this.hand.DX <= 3) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 45 && this.hand.positionY > 103 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 95 && this.hand.positionY > 128 && this.hand.positionY < 208) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 45 && this.hand.positionY > 0 && this.hand.positionY < 93) {
               this.hand.DX = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 80 && this.hand.positionY > 0 && this.hand.positionY < 63) {
               this.hand.DX = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY > 0) {
            if (this.hand.positionX > 0 && this.hand.positionX < 176 && this.hand.positionY > 155 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 40 && this.hand.positionY > 98 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 90 && this.hand.positionY > 123 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 115 && this.hand.positionX < 176 && this.hand.positionY > 113 && this.hand.positionY < 208) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }

         if (this.hand.DY < 0) {
            if (this.hand.positionY < 58) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 75 && this.hand.positionY > 0 && this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 115 && this.hand.positionX < 176 && this.hand.positionY > 0 && this.hand.positionY < 68) {
               this.hand.DY = 0;
               this.qingchu = true;
            }

            if (this.hand.positionX > 0 && this.hand.positionX < 40 && this.hand.positionY > 0 && this.hand.positionY < 98) {
               this.hand.DY = 0;
               this.qingchu = true;
            }
         }
      }

      int zx = this.hand.positionX;
      int zy = this.hand.positionY;
      if (this.offsetX == 4) {
         if (zx > -20 && zx < -3 && zy > 0 && zy < HEIGHT && !this.usingTool) {
            this.offsetX = 5;
            this.hand.positionX = zx + 160;
            this.hand.positionY = zy;
            return;
         }

         if (zx > 166 && zx < 240 && zy > 0 && zy < HEIGHT && !this.usingTool) {
            this.offsetX = 6;
            this.hand.positionX = zx - 164;
            this.hand.positionY = zy;
            return;
         }
      }

      if (this.offsetX == 2 && zx > -20 && zx < -3 && zy > 0 && zy < HEIGHT && !this.usingTool) {
         this.offsetX = 3;
         this.hand.positionX = zx + 160;
         this.hand.positionY = zy;
      } else if (this.offsetX == 1 && zx > -20 && zx < -3 && zy > 73 && zy < 123 && !this.usingTool) {
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         this.offsetX = 2;
         this.hand.positionX = 153;
         this.hand.positionY = 123;
      } else if (this.offsetX == 2 && zx > 166 && zx < 240 && zy > 103 && zy < 208 && !this.usingTool) {
         if (this.back2 == null) {
            this.back2 = this.loadImage("back2");
         }

         this.offsetX = 1;
         this.rs = 0;
         this.hand.positionX = 3;
         this.hand.positionY = 98;
      } else if (this.offsetX == 3 && zx > 166 && zx < 240 && zy > 0 && zy < HEIGHT && !this.usingTool) {
         this.offsetX = 2;
         this.hand.positionX = zx - 164;
         this.hand.positionY = zy;
      } else if (this.offsetX == 5 && zx > 166 && zx < 240 && zy > 0 && zy < HEIGHT && !this.usingTool) {
         this.offsetX = 4;
         this.hand.positionX = zx - 164;
         this.hand.positionY = zy;
      } else {
         if (this.offsetX == 6) {
            if (zx > -20 && zx < -3 && zy > 0 && zy < HEIGHT && !this.usingTool) {
               this.offsetX = 4;
               this.hand.positionX = zx + 160;
               this.hand.positionY = zy;
               return;
            }

            if (zx > 166 && zx < 240 && zy > 0 && zy < HEIGHT && !this.usingTool) {
               this.offsetX = 7;
               this.hand.positionX = zx - 164;
               this.hand.positionY = zy;
               return;
            }
         }

         if (this.offsetX == 7 && zx > -20 && zx < -3 && zy > 0 && zy < HEIGHT && !this.usingTool) {
            this.offsetX = 6;
            this.hand.positionX = zx + 160;
            this.hand.positionY = zy;
         } else {
            this.hand.step();
            if (this.usingTool) {
               this.hand.currentFrame = 0;
            }

            this.hand.draw(this.gr);
            this.jiaju();
            if (this.toolStates[15] != 10) {
               this.beijig();
            }

            if (this.offsetX == 14) {
               if (this.dmap == null) {
                  this.dmap = this.loadImage("dmap");
               }

               if (this.fanhuie == null) {
                  this.fanhuie = this.loadImage("fanhui");
               }

               if (this.dmap1) {
                  this.gr.drawImage(this.dmap, (WIDTH - 81) / 2, (HEIGHT - 71) / 2 + 13, 0);
                  this.gr.drawImage(this.fanhuie, 0, 194, 0);
                  if (this.Timer / 6 % 2 == 0) {
                     this.gr.setColor(255, 0, 0);
                     this.gr.drawArc(81, 120, 4, 4, 0, 360);
                     this.gr.setColor(255, 255, 255);
                     this.gr.fillRect(83, 122, 1, 1);
                  }
               }
            }

            if (this.bianl == 3) {
               this.gr.setColor(0, 0, 0);
               if (this.xg1 < 26) {
                  this.xg1 += 3;
               }

               if (this.xg1 >= 4 && this.xg2 < 26) {
                  this.xg2 += 3;
               }

               if (this.xg2 >= 5 && this.xg3 < 26) {
                  this.xg3 += 3;
               }

               if (this.xg3 >= 4 && this.xg4 < 26) {
                  this.xg4 += 3;
               }

               if (this.xg4 >= 5 && this.xg5 < 26) {
                  this.xg5 += 3;
               }

               if (this.xg5 >= 4 && this.xg6 < 26) {
                  this.xg6 += 3;
               }

               if (this.xg6 >= 5 && this.xg7 < 26) {
                  this.xg7 += 3;
               }

               if (this.xg7 >= 4 && this.xg8 < 26) {
                  this.xg8 += 3;
               }

               if (this.xg8 >= 26) {
                  this.kisi = true;
               }

               this.gr.fillRect(0, 0 + this.xg1, 176, 26 - this.xg1);
               this.gr.fillRect(0, 26 + this.xg2, 176, 26 - this.xg2);
               this.gr.fillRect(0, 52 + this.xg3, 176, 26 - this.xg3);
               this.gr.fillRect(0, 78 + this.xg4, 176, 26 - this.xg4);
               this.gr.fillRect(0, 104 + this.xg5, 176, 26 - this.xg5);
               this.gr.fillRect(0, 130 + this.xg6, 176, 26 - this.xg6);
               this.gr.fillRect(0, 156 + this.xg7, 176, 26 - this.xg7);
               this.gr.fillRect(0, 182 + this.xg8, 176, 26 - this.xg8);
               if (!this.kisi) {
                  this.hand.positionX = (WIDTH - this.hand.framesInfo[this.hand.currentFrame][2]) / 2 + 20 + 3;
                  this.hand.positionY = HEIGHT - 80;
                  this.hand.currentFrame = 0;
               }

               if (this.kisi) {
                  this.kaishigd = true;
               }
            }

         }
      }
   }

   private void drawQuit() {
      if (this.shou == null) {
         this.shou = this.loadImage("shou");
      }

      if (this.caidan1 == null) {
         this.caidan1 = this.loadImage("caidan1");
      }

      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      this.gr.drawRegion(this.caidan1, 0, 0, 101, 111, 0, 37, 48, 0);
      switch(GAME_CURRENT) {
      case 0:
         this.gr.drawRegion(this.caidan1, 101, 7, 70, 20, 0, (WIDTH - 70) / 2 - 1, (HEIGHT - 20) / 2 - 43, 0);
         this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 - 43, 0);
         break;
      case 1:
         this.gr.drawRegion(this.caidan1, 101, 35, 70, 20, 0, (WIDTH - 70) / 2 - 1, (HEIGHT - 20) / 2 - 16, 0);
         this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 - 13, 0);
         break;
      case 2:
         this.gr.drawRegion(this.caidan1, 101, 63, 70, 20, 0, (WIDTH - 70) / 2 - 1, (HEIGHT - 20) / 2 + 13, 0);
         this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 + 13, 0);
         break;
      case 3:
         this.gr.drawRegion(this.caidan1, 101, 91, 70, 20, 0, (WIDTH - 70) / 2 - 1, (HEIGHT - 20) / 2 + 41, 0);
         this.gr.drawImage(this.shou, (WIDTH - 29) / 2 + 67, (HEIGHT - 14) / 2 + 41, 0);
      }

   }

   private void drawGameScreen() {
      if (!this.preDisplayed) {
         this.drawGamePre();
         if (!this.isShowStr) {
            if (!this.sw1) {
               this.kaishi();
               if (this.sw >= 260) {
                  this.sw1 = true;
               }
            } else {
               this.kongzhiren();
               if (this.jish > 40) {
                  this.drawPreStr();
                  if (this.juli == 115) {
                     this.fsi = false;
                     this.logo_ = null;
                     this.menuback = null;
                     this.lingjian = null;
                     this.preDisplayed = true;
                     this.juli = 113;
                     this.juli11 = 113;
                  }
               }
            }
         }
      } else {
         this.gr.setColor(0);
         this.gr.fillRect(0, 0, WIDTH, HEIGHT);
         if (this.currentRoom == 1) {
            this.changeClosedMid(0);
            this.createBackMid(4);
            this.playBackMid(4);
            this.drawRoom1();
         }

         this.drawPublicRoom();
         if (this.showTool) {
            this.drawTool();
         }

         if (this.canExit) {
            this.drawQuit();
         }
      }

      int i;
      if (this.stringForShow.length() > 0 && !this.canExit) {
         for(i = 0; i < this.color.length; ++i) {
            this.gr.setColor(this.color[i][0], this.color[i][1], this.color[i][2]);
         }

         this.gr.setColor(0, 0, 0);
         if (!this.linxi) {
            this.gr.fillRect(0, HEIGHT - 50, WIDTH, 66);
         } else if (!this.shouji1) {
            this.gr.fillRect(0, HEIGHT - 60, WIDTH, 66);
         } else {
            this.gr.fillRect(0, HEIGHT - 60 + 10, WIDTH, 51);
         }

         this.drawstringToScreen(this.stringForShow);
      }

      if (this.toolStringToshow.length() > 0 && !this.canExit) {
         for(i = 0; i < this.color.length; ++i) {
            this.gr.setColor(this.color[i][0], this.color[i][1], this.color[i][2]);
         }

         this.gr.setColor(0, 0, 0);
         this.gr.fillRect(0, HEIGHT - 60 + 10, WIDTH, 51);
         this.drawToolstring(this.toolStringToshow);
      }

      if (this.isMusic) {
         this.drawExitMusic();
      }

      if (this.isHelp) {
         this.drawExitHelp();
      }

   }

   private void beijig() {
      switch(this.offsetX) {
      case 0:
         this.gr.drawRegion(this.back1, 0, 176, 82, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back1, 135, 176, 41, 32, 0, 135, 176, 0);
         break;
      case 1:
         this.gr.drawRegion(this.back2, 0, 176, 82, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back2, 135, 176, 41, 32, 0, 135, 176, 0);
         if (this.isOutpasswordkuang && this.toolStates[15] == 0) {
            if (this.fanwi == null) {
               this.fanwi = this.loadImage("fanhui");
            }

            this.gr.drawImage(this.fanwi, 0, 194, 0);
         }
         break;
      case 2:
         this.gr.drawRegion(this.back3, 0, 175, 176, 33, 0, 0, 175, 0);
         break;
      case 3:
         this.gr.drawRegion(this.back3, 0, 175, 176, 33, 0, 0, 175, 0);
         break;
      case 4:
         this.gr.drawRegion(this.back3, 0, 175, 119, 33, 0, 0, 175, 0);
         break;
      case 5:
         this.gr.drawRegion(this.back3, 0, 175, 176, 33, 0, 0, 175, 0);
         break;
      case 6:
         this.gr.drawRegion(this.back3, 0, 175, 176, 33, 0, 0, 175, 0);
         break;
      case 7:
         this.gr.drawRegion(this.back3, 0, 175, 176, 33, 0, 0, 175, 0);
         break;
      case 8:
         this.gr.drawRegion(this.back8, 0, 176, 28, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back8, 86, 176, 90, 32, 0, 86, 176, 0);
         break;
      case 9:
         this.gr.drawRegion(this.back9, 0, 176, 124, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back9, 3, 100, 65, 21, 0, 3, 100, 0);
         break;
      case 10:
         this.gr.drawRegion(this.back10, 52, 176, 124, 32, 0, 52, 176, 0);
         break;
      case 11:
         this.gr.drawRegion(this.back11, 0, 176, 82, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back11, 136, 176, 40, 32, 0, 136, 176, 0);
         break;
      case 12:
         this.gr.drawRegion(this.back12, 0, 176, 60, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back12, 114, 176, 62, 32, 0, 114, 176, 0);
         break;
      case 13:
         this.gr.drawRegion(this.back13, 0, 176, 41, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back13, 94, 176, 82, 32, 0, 94, 176, 0);
         break;
      case 14:
         this.gr.drawRegion(this.back14, 0, 176, 124, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back14, 61, 77, 46, 9, 0, 61, 77, 0);
         this.gr.drawRegion(this.back14, 137, 77, 31, 10, 0, 137, 77, 0);
         break;
      case 15:
         this.gr.drawRegion(this.back15, 0, 176, 60, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back15, 114, 176, 62, 32, 0, 114, 176, 0);
         break;
      case 16:
         this.gr.drawRegion(this.back4, 0, 176, 60, 32, 0, 0, 176, 0);
         this.gr.drawRegion(this.back4, 114, 176, 62, 32, 0, 114, 176, 0);
         break;
      case 17:
         this.gr.drawRegion(this.back5, 0, 176, 176, 32, 0, 0, 176, 0);
      }

   }

   private void publicRoom1() {
      this.kaishi1 = null;
      this.jindu1 = null;
      switch(this.offsetX) {
      case 0:
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back1 == null) {
            this.back1 = this.loadImage("back1");
         }

         if (this.back1 != null) {
            this.gr.drawImage(this.back1, 0, 0, 0);
         }
         break;
      case 1:
         this.back1 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back2 == null) {
            this.back2 = this.loadImage("back2");
         }

         if (this.back2 != null) {
            this.gr.drawImage(this.back2, 0, 0, 0);
         }
         break;
      case 2:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 3:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 4:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 5:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 6:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 7:
         this.back1 = null;
         this.back2 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back3 == null) {
            this.back3 = this.loadImage("back3");
         }

         if (this.back3 != null) {
            this.gr.drawImage(this.back3, 0, 0, 0);
         }
         break;
      case 8:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back8 == null) {
            this.back8 = this.loadImage("back8");
         }

         if (this.back8 != null) {
            this.gr.drawImage(this.back8, 0, 0, 0);
         }
         break;
      case 9:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back9 == null) {
            this.back9 = this.loadImage("back9");
         }

         if (this.back9 != null) {
            this.gr.drawImage(this.back9, 0, 0, 0);
         }
         break;
      case 10:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back10 == null) {
            this.back10 = this.loadImage("back10");
         }

         if (this.back10 != null) {
            this.gr.drawImage(this.back10, 0, 0, 0);
         }
         break;
      case 11:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back11 == null) {
            this.back11 = this.loadImage("back11");
         }

         if (this.back11 != null) {
            this.gr.drawImage(this.back11, 0, 0, 0);
         }
         break;
      case 12:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back12 == null) {
            this.back12 = this.loadImage("back12");
         }

         if (this.back12 != null) {
            this.gr.drawImage(this.back12, 0, 0, 0);
         }
         break;
      case 13:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back13 == null) {
            this.back13 = this.loadImage("back13");
         }

         if (this.back13 != null) {
            this.gr.drawImage(this.back13, 0, 0, 0);
         }
         break;
      case 14:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back15 = null;
         if (this.back14 == null) {
            this.back14 = this.loadImage("back14");
         }

         if (this.back14 != null) {
            this.gr.drawImage(this.back14, 0, 0, 0);
         }
         break;
      case 15:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         if (this.back15 == null) {
            this.back15 = this.loadImage("back15");
         }

         if (this.back15 != null) {
            this.gr.drawImage(this.back15, 0, 0, 0);
         }
         break;
      case 16:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back5 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back4 == null) {
            this.back4 = this.loadImage("back4");
         }

         if (this.back4 != null) {
            this.gr.drawImage(this.back4, 0, 0, 0);
         }
         break;
      case 17:
         this.back1 = null;
         this.back2 = null;
         this.back3 = null;
         this.back4 = null;
         this.back8 = null;
         this.back9 = null;
         this.back10 = null;
         this.back11 = null;
         this.back12 = null;
         this.back13 = null;
         this.back14 = null;
         this.back15 = null;
         if (this.back5 == null) {
            this.back5 = this.loadImage("back5");
         }

         if (this.back5 != null) {
            this.gr.drawImage(this.back5, 0, 0, 0);
         }
      }

   }

   private void jiantou() {
      if (this.jiantou == null) {
         this.jiantou = this.loadImage("jiantou");
      }

      switch(this.offsetX) {
      case 0:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 95, 193, 0);
         break;
      case 1:
         if (this.offsetX == 1) {
            this.sh1 = 1;
         }

         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 95, 193, 0);
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 2, -13, 110, 0);
         break;
      case 2:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 2, -13, 135, 0);
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 3:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 4:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 2, -13, 135, 0);
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 5:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 6:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 2, -13, 135, 0);
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 7:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 2, -12, 135, 0);
         break;
      case 8:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 45, 195, 0);
         break;
      case 9:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 135, 195, 0);
         break;
      case 10:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 15, 195, 0);
         break;
      case 11:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 95, 195, 0);
         break;
      case 12:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 75, 195, 0);
         break;
      case 13:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 55, 195, 0);
         break;
      case 14:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 135, 195, 0);
         break;
      case 15:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 75, 195, 0);
         break;
      case 16:
         this.gr.drawRegion(this.jiantou, 0, 0, 25, 26, 4, 75, 195, 0);
      }

   }

   private void fangjainw() {
      switch(this.offsetX) {
      case 0:
         this.shaizi = null;
         this.gui2 = null;
         this.shuyao = null;
         if (this.kaigui == null) {
            this.kaigui = this.loadImage("kaigui");
         }

         if (this.shouji == null) {
            this.shouji = this.loadImage("shouji");
         }
         break;
      case 1:
         this.kaigui = null;
         this.shouji = null;
         this.paaizi = null;
         this.lou = null;
         this.men = null;
         this.menkuang = null;
         this.loub = null;
         this.weiyao = null;
         if (this.shaizi == null) {
            this.shaizi = this.loadImage("shaizi");
         }

         if (this.gui2 == null) {
            this.gui2 = this.loadImage("gui2");
         }

         if (this.shuyao == null) {
            this.shuyao = this.loadImage("shuyao");
         }
         break;
      case 2:
         this.kuangtpong = null;
         this.shaizi = null;
         this.gui2 = null;
         this.shuyao = null;
         this.xmap = null;
         this.diban = null;
         if (this.lou == null) {
            this.lou = this.loadImage("lou");
         }

         if (this.men == null) {
            this.men = this.loadImage("men");
         }

         if (this.menkuang == null) {
            this.menkuang = this.loadImage("menkuang");
         }

         if (this.paaizi == null) {
            this.paaizi = this.loadImage("paaizi");
         }

         if (this.loub == null) {
            this.loub = this.loadImage("loub");
         }
         break;
      case 3:
         this.wei = null;
         this.guis = null;
         this.bang = null;
         this.shise = null;
         this.nkai = null;
         if (this.dong == null) {
            this.dong = this.loadImage("dong");
         }

         if (this.kuangtpong == null) {
            this.kuangtpong = this.loadImage("kuangtpong");
         }
         break;
      case 4:
         this.kguizi = null;
         this.shuzi = null;
         this.dang = null;
         this.chouti = null;
         this.sez = null;
         break;
      case 5:
         this.gz = null;
         this.kamen = null;
         break;
      case 6:
         this.shuji = null;
         break;
      case 7:
         this.guika = null;
         this.qian = null;
         break;
      case 8:
         this.k = null;
         this.kc = null;
         this.s = null;
         this.sk = null;
         if (this.gz == null) {
            this.gz = this.loadImage("gz");
         }

         if (this.kamen == null) {
            this.kamen = this.loadImage("kamen");
         }
         break;
      case 9:
         if (this.shuji == null) {
            this.shuji = this.loadImage("shuji");
         }
         break;
      case 10:
         this.shaizi = null;
         this.gui2 = null;
         this.shuyao = null;
         if (this.weiyao == null) {
            this.weiyao = this.loadImage("weiyao");
         }
         break;
      case 11:
         this.kuangtpong = null;
         if (this.wei == null) {
            this.wei = this.loadImage("wei");
         }

         if (this.guis == null) {
            this.guis = this.loadImage("guis");
         }

         if (this.bang == null) {
            this.bang = this.loadImage("bang");
         }

         if (this.shise == null) {
            this.shise = this.loadImage("shise");
         }
         break;
      case 12:
         if (this.guika == null) {
            this.guika = this.loadImage("guika");
         }

         if (this.qian == null) {
            this.qian = this.loadImage("qian");
         }
         break;
      case 13:
         if (this.kguizi == null) {
            this.kguizi = this.loadImage("kguizi");
         }

         if (this.shuzi == null) {
            this.shuzi = this.loadImage("shuzi");
         }

         if (this.dang == null) {
            this.dang = this.loadImage("dang");
         }

         if (this.chouti == null) {
            this.chouti = this.loadImage("chouti");
         }

         if (this.sez == null) {
            this.sez = this.loadImage("sez");
         }
         break;
      case 14:
         if (this.xmap == null) {
            this.xmap = this.loadImage("xmap");
         }

         if (this.diban == null) {
            this.diban = this.loadImage("diban");
         }
         break;
      case 15:
         this.kuangtpong = null;
         if (this.nkai == null) {
            this.nkai = this.loadImage("nkai");
         }
         break;
      case 16:
         this.mao = null;
         this.gz = null;
         this.kamen = null;
         if (this.k == null) {
            this.k = this.loadImage("k");
         }

         if (this.kc == null) {
            this.kc = this.loadImage("kc");
         }

         if (this.s == null) {
            this.s = this.loadImage("s");
         }

         if (this.sk == null) {
            this.sk = this.loadImage("sk");
         }
         break;
      case 17:
         this.k = null;
         this.kc = null;
         this.s = null;
         this.sk = null;
         if (this.mao == null) {
            this.mao = this.loadImage("mao");
         }
      }

   }

   private void fangjian() {
      switch(this.offsetX) {
      case 0:
         if (this.kaigui1) {
            this.kaishij1 = true;
            this.gr.drawImage(this.kaigui, 40, 73, 0);
            if (!this.shouji1) {
               this.gr.drawImage(this.shouji, 51, 117, 0);
            }
         }

         if (this.hand.positionX > 25 && this.hand.positionX < 55 && this.hand.positionY == 118 && !this.kaishij1 && this.pshang) {
            this.stringForShow = "按5键打开柜子.";
         }

         if (this.hand.positionX > 25 && this.hand.positionX < 55 && this.hand.positionY == 118 && this.kaishij1 && this.pshang && !this.huodeya) {
            this.stringForShow = "“发现一把钥匙”按5键获得";
         }
         break;
      case 1:
         this.kaishij2 = true;
         if (!this.shaizi1) {
            this.gr.drawImage(this.shaizi, 98, 123, 0);
         }

         if (this.gui22) {
            this.gr.drawImage(this.gui2, 131, 54, 0);
            if (!this.shuyao1) {
               this.gr.drawImage(this.shuyao, 150, 62, 0);
            }
         }
         break;
      case 2:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 20, 20, 0);
         this.gr.drawImage(this.menkuang, -22, 15, 0);
         this.gr.drawRegion(this.lou, 0, 0, 60, 128, 0, 116, 0, 0);
         this.gr.drawImage(this.paaizi, 81, 50, 0);
         this.gr.drawRegion(this.loub, 0, 0, 7, 16, 0, 90, 55, 0);
         this.gr.drawRegion(this.loub, 17, 0, 11, 16, 0, 97, 55, 0);
         break;
      case 3:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 42, 20, 0);
         this.gr.drawImage(this.menkuang, 0, 15, 0);
         if (this.zale) {
            this.gr.drawImage(this.dong, 95, 64, 0);
         } else {
            this.gr.drawImage(this.kuangtpong, 97, 61, 0);
         }
         break;
      case 4:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 42, 20, 0);
         this.gr.drawImage(this.menkuang, 0, 15, 0);
         this.gr.drawRegion(this.lou, 65, 93, 67, 35, 0, 116, 175, 0);
         this.gr.drawImage(this.paaizi, 103, 50, 0);
         this.gr.drawRegion(this.loub, 7, 0, 10, 16, 0, 112, 55, 0);
         this.gr.drawRegion(this.loub, 17, 0, 11, 16, 0, 119, 55, 0);
         break;
      case 5:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 42, 20, 0);
         this.gr.drawImage(this.menkuang, 0, 15, 0);
         break;
      case 6:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 83, 20, 0);
         this.gr.drawRegion(this.menkuang, 0, 0, 176, 104, 2, 0, 15, 0);
         break;
      case 7:
         this.gr.drawRegion(this.men, 0, 0, 51, 97, 0, 83, 20, 0);
         this.gr.drawRegion(this.menkuang, 0, 0, 176, 104, 2, 0, 15, 0);
         break;
      case 8:
         if (this.kongmen) {
            this.gr.drawImage(this.kamen, 10, 45, 0);
         }

         this.gr.drawImage(this.gz, 112, 65, 0);
         break;
      case 9:
         if (!this.shuji1) {
            this.gr.drawImage(this.shuji, 72, 130, 0);
         }
         break;
      case 10:
         if (!this.weiyao1) {
            this.gr.drawImage(this.weiyao, 84, 82, 0);
         }
         break;
      case 11:
         this.gr.drawImage(this.wei, 40, 66, 0);
         this.gr.drawRegion(this.shise, 0, 0, 14, 12, 0, 140, 68, 0);
         if (this.shise1) {
            this.gr.drawRegion(this.shise, 14, 0, 12, 12, 0, 141, 68, 0);
         }

         if (this.guis1) {
            this.gr.drawImage(this.guis, 13, 83, 0);
            if (!this.bang1) {
               this.gr.drawImage(this.bang, 15, 87, 0);
            }
         }
         break;
      case 12:
         if (this.guika1) {
            this.gr.drawImage(this.guika, 83, 78, 0);
            if (!this.qian1) {
               this.gr.drawImage(this.qian, 93, 80, 0);
            }
         }
         break;
      case 13:
         if (this.kguizi1) {
            this.gr.drawImage(this.kguizi, 136, 50, 0);
            this.gr.drawImage(this.shuzi, 137, 55, 0);
         }

         if (!this.dang1) {
            this.gr.drawImage(this.dang, 31, 66, 0);
         } else {
            this.gr.drawImage(this.chouti, 93, 80, 0);
            if (!this.sez1) {
               this.gr.drawImage(this.sez, 103, 82, 0);
            }
         }
         break;
      case 14:
         if (this.hand.positionX > 43 && this.hand.positionX < 103 && this.hand.positionY > 100 && this.hand.positionY < 128 && !this.dmap1 && this.pshang && !this.ditu && !this.usingTool) {
            this.stringForShow = "在此位置按5键可查看地图";
         }

         this.gr.drawImage(this.xmap, 78, 120, 0);
         if (this.diban1) {
            this.gr.drawRegion(this.diban, 0, 0, 19, 15, 0, 110, 75, 0);
            if (!this.dianchi) {
               this.gr.drawRegion(this.diban, 19, 0, 5, 7, 0, 117, 77, 0);
            }
         }
         break;
      case 15:
         if (this.nkai1) {
            this.gr.drawImage(this.nkai, 43, 87, 0);
         }
         break;
      case 16:
         if (this.k1) {
            this.gr.drawImage(this.k, 77, 43, 0);
         }

         if (this.k2) {
            this.gr.drawImage(this.kc, 130, 115, 0);
            if (!this.k4) {
               this.gr.drawImage(this.sk, 137, 120, 0);
            }
         }

         if (this.k3) {
            this.gr.drawImage(this.sk, 100, 35, 0);
            this.gr.drawImage(this.s, 100, 35, 0);
         }
         break;
      case 17:
         if (!this.mao1) {
            this.gr.drawImage(this.mao, 60, 65, 0);
         }
      }

   }

   private void drawRoom1() {
      this.jianpan = true;
      int zx = this.hand.positionX;
      int zy = this.hand.positionY;
      this.publicRoom1();
      this.jiantou();
      this.fangjainw();
      this.fangjian();
      this.zimua = true;
      if (!this.kaishigd) {
         this.kaishichu();
      }

      if (this.zimua && this.kaishigd) {
         this.jiantoud();
      }

      this.shuju();
      if (this.toolStates[15] == 10) {
         this.jieshuh();
         ++this.menuCount_1;
         if (this.st > 270) {
            this.st = 0;
            this.FreeGameSourse();
            this.canChange = false;
            this.drawStatus_ = 1;
            this.menuStatus_ = 0;
            return;
         }
      }

   }

   private void jieshuh() {
      try {
         if (this.jsh == null) {
            this.jsh = this.loadImage("js");
         }

         if (this.strsToShow2 == null) {
            String temp = "     终于……逃出来了……\n\n\n     天近清晨，静子坐在湖边的长椅上无神地凝视着湖水。阳光从细碎的树叶缝隙中照射在她脸上，微风从她脸上抚过，眼前的湖水就像雾般飘浮不定。在她的心灵深处，红色的伤痕依稀可见。";
            this.strsToShow2 = this.format(temp, WIDTH - 16, afont);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.gr.setFont(afont);
      this.hand.visible = false;
      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      this.gr.setClip(0, 20, 176, 160);
      this.gr.setColor(0, 128, 255);
      if (this.movePreY < HEIGHT - 30) {
         this.gr.setColor(255, 255, 255);
         if (++this.st % 20 == 0) {
            ++this.st;
         }

         if (this.st < 400) {
            this.menuCount_1 = this.drawStringPre(this.strsToShow2, 10, 160 - this.st, this.menuCount_, 14, afont);
         }

         this.gr.drawImage(this.jsh, 0, 20, 0);
      }

   }

   private void kaishi() {
      try {
         if (this.logo_ == null) {
            this.logo_ = this.loadImage("LOGO");
         }

         if (this.menuback == null) {
            this.menuback = this.loadImage("menuback");
         }
      } catch (Exception var3) {
      }

      try {
         if (this.strsToShow1 == null) {
            String temp = "     落日的黄昏，幽暗的密林，崎岖不平的山道，静子和姐姐失足滑落至山沟间.\n\n     静子无法忍受这种折磨，眼一黑便晕了过去.不知过了多久，静子睁开了双眼... ";
            this.strsToShow1 = this.format(temp, WIDTH - 16, afont);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.gr.setFont(afont);
      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      if (this.sw < 800) {
         this.sw += 2;
         this.menuCount_ = this.drawStringPre(this.strsToShow1, 10, 160 - this.sw, this.menuCount_, 50, afont);
      }

      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, 115);
      if (this.sw > 99) {
         this.hu = true;
      }

      if (this.sw < 99) {
         this.gr.drawRegion(this.logo_, 185, 82, 156, 87, 0, (WIDTH - 156) / 2, 15, 0);
      }

      if (this.sw > 99) {
         this.hu = true;
         this.gr.drawRegion(this.menuback, 10, 111, 156, 87, 0, (WIDTH - 156) / 2, 15, 0);
         this.hun = true;
      }

      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 188, WIDTH, 20);
      this.gr.setColor(255, 255, 255);
      if (this.fanhui == null) {
         this.fanhui = this.loadImage("fanhui");
      }

      this.gr.drawImage(this.fanhui, 0, 194, 0);
   }

   private void kongzhiren() {
      this.fsi = true;
      this.gr.setColor(0, 0, 0);
      this.gr.fillRect(0, 0, WIDTH, HEIGHT);
      this.gr.setColor(255, 255, 255);
      if (this.jish < 10) {
         this.gr.drawString("啊", 80, 100, 0);
      } else if (this.jish < 20) {
         this.gr.drawString("啊..", 80, 100, 0);
      } else if (this.jish < 30) {
         this.gr.drawString("啊......", 80, 100, 0);
      }

   }

   private void kaishichu() {
      switch(this.bianl) {
      case 0:
         this.stringForShow = "这里到底是什么地方,怎么感觉这么的诡异.";
         break;
      case 1:
         this.stringForShow = "我想我还是赶紧离开这里为妙";
         break;
      case 2:
         if (this.buchang != 9) {
            ++this.buchang;
            this.zong = true;
         }

         if (this.buchang == 9) {
            if (this.bide != 3) {
               ++this.bide;
               this.zong = false;
            }

            if (this.bide == 3) {
               if (!this.wzdh) {
                  this.stringForShow = "喀嚓...喀嚓...";
               } else {
                  this.stringForShow = "糟糕,门被反锁住了,我得想办法离开这里..";
                  this.ksdi = true;
               }
            }
         }
      }

   }

   private void zhongtu() {
      try {
         if (this.strsToShow3 == null) {
            String temp = "我要如何才能离开这诡异的房间呢?得找到一些道具,5号键可以拿到我想要的道具.1号键可以打开我的道具栏";
            this.strsToShow3 = this.format(temp, WIDTH - 16, afont);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      if (this.swq < 300 && !this.zimua) {
         this.gr.setFont(afont);
         this.gr.setClip(0, HEIGHT - 60 + 10, WIDTH, 51);
         this.gr.setColor(0, 0, 0);
         this.gr.fillRect(0, HEIGHT - 60 + 10, WIDTH, 51);
         this.menuCount_ = this.drawString(this.strsToShow3, 10, 208 - ++this.swq, this.menuCount_, 50, afont);
         this.gr.setColor(0, 0, 0);
         this.gr.fillRect(0, 198, WIDTH, 10);
         this.gr.fillRect(0, HEIGHT - 60 + 10, WIDTH, 10);
         if (this.swq > 110 && !this.zimua) {
            this.zimua = true;
            this.swq = 110;
         }
      }

   }

   private void shuju() {
      if (this.isOutpasswordkuang && this.toolStates[15] == 0) {
         if (this.password_ == null) {
            this.password_ = this.loadImage("password");
         }

         this.gr.drawRegion(this.password_, 0, 0, 116, 35, 0, (WIDTH - 116) / 2, (HEIGHT - 35) / 2, 0);
         switch(this.keydownNum) {
         case 1:
            this.gr.drawRegion(this.password_, 129, 4, 8, 26, 0, 42, 91, 0);
            break;
         case 2:
            this.gr.drawRegion(this.password_, 129, 4, 8, 26, 0, 63, 91, 0);
            break;
         case 3:
            this.gr.drawRegion(this.password_, 129, 4, 8, 26, 0, 83, 91, 0);
            break;
         case 4:
            this.gr.drawRegion(this.password_, 129, 4, 8, 26, 0, 104, 91, 0);
            break;
         case 5:
            this.gr.drawRegion(this.password_, 129, 4, 8, 26, 0, 126, 91, 0);
            this.gr.drawRegion(this.password_, 116, 11, 13, 13, 0, 124, 97, 0);
         case 6:
         }

         try {
            if (this.numpass_ == null) {
               this.numpass_ = new MySprite(this.loadImage("num"), 10);
            }

            this.numpass_.positionX = 69;
            this.numpass_.positionY = 107;
            this.numpass_.currentFrame = this.toolStates[8] % 10;
            if (this.keydownNum >= 1) {
            }

            this.numpass_.positionX = 41;
            this.numpass_.positionY = 100;
            this.numpass_.currentFrame = this.toolStates[9] % 10;
            if (this.keydownNum >= 2) {
               this.numpass_.draw(this.gr);
            }

            this.numpass_.positionX = 61;
            this.numpass_.positionY = 100;
            this.numpass_.currentFrame = this.toolStates[10] % 10;
            if (this.keydownNum >= 3) {
               this.numpass_.draw(this.gr);
            }

            this.numpass_.positionX = 83;
            this.numpass_.positionY = 100;
            this.numpass_.currentFrame = this.toolStates[11] % 10;
            if (this.keydownNum >= 4) {
               this.numpass_.draw(this.gr);
            }

            this.numpass_.positionX = 103;
            this.numpass_.positionY = 100;
            this.numpass_.currentFrame = this.toolStates[12] % 10;
            if (this.keydownNum >= 5) {
               this.numpass_.draw(this.gr);
            }
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

   }

   private void onRoom1(int keyCode) {
      int zx = this.hand.positionX;
      int zy = this.hand.positionY;
      if (this.offsetX == 0 && keyCode == 53 && this.hand.currentFrame == 0) {
         if (!this.usingTool && this.hand.positionX > 83 && this.hand.positionX < 118 && this.hand.positionY == 108 && !this.ssk && !this.kaishij2 && this.pshang && this.shouji1 && !this.shiyong && !this.guanzi) {
            this.stringForShow = "按1键打开道具栏后，按5键使用选择的道具";
            this.guanzi = true;
            return;
         }

         if (!this.usingTool && this.hand.positionX > 83 && this.hand.positionX < 118 && this.hand.positionY == 108 && !this.kaishij2 && this.pshang && !this.shouji1 && !this.shiyong && !this.guanzi) {
            this.stringForShow = "喀嚓....这门已经被锁住了.";
            this.guanzi = true;
            return;
         }
      }

      if (this.offsetX == 12 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 128 && zx < 163 && zy > 59 && zy < 101 && !this.usingTool && this.pshang && !this.guanzi) {
         this.stringForShow = "找不到我想要的东西了.";
         this.guanzi = true;
      } else if (this.offsetX == 15 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 97 && zx < 167 && zy > 43 && zy < 77 && !this.usingTool && this.pshang && !this.guanzi) {
         this.stringForShow = "找不到我想要的东西了.";
         this.guanzi = true;
      } else if (this.offsetX == 17 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 136 && zx < 166 && zy > 53 && zy < 83 && !this.usingTool && this.pshang && !this.guanzi) {
         this.stringForShow = "找不到我想要的东西了.";
         this.guanzi = true;
      } else if (this.offsetX == 8 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 112 && zx < 135 && zy > 57 && zy < 79 && !this.usingTool && this.pshang && !this.chuis && !this.guanzi) {
         this.stringForShow = "桌子下边好象有什么东西...";
         this.guanzi = true;
      } else {
         if (this.offsetX == 1 && keyCode == 53 && this.hand.currentFrame == 0) {
            if (zx > 88 && zx < 149 && zy > 101 && zy < 135 && !this.usingTool && this.pshang && !this.guanzi) {
               this.stringForShow = "桌子上布满了灰尘,看样子已经荒废很久了.";
               this.guanzi = true;
               return;
            }

            if (zx > 91 && zx < 125 && zy > 34 && zy < 78 && !this.usingTool && this.pshang && !this.guanzi) {
               this.stringForShow = "这里有些杂物,但是没有我需要的.";
               this.guanzi = true;
               return;
            }
         }

         if (this.offsetX == 10 && keyCode == 53 && this.hand.currentFrame == 0) {
            if (zx > 9 && zx < 45 && zy > 76 && zy < 134 && !this.usingTool && !this.guanzi) {
               this.stringForShow = "找不到我想要的东西了";
               this.guanzi = true;
               return;
            }

            if (zx > 97 && zx < 127 && zy > 73 && zy < 115 && !this.usingTool && !this.guanzi) {
               this.stringForShow = "找不到我想要的东西了";
               this.guanzi = true;
               return;
            }

            if (zx > 133 && zx < 168 && zy > 93 && zy < 116 && !this.usingTool && !this.guanzi) {
               this.stringForShow = "这马桶真是脏的可以,我还是忍耐一下好了..";
               this.guanzi = true;
               return;
            }
         }

         if (this.offsetX == 11 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 41 && zx < 133 && zy > 50 && zy < 79 && !this.usingTool && this.pshang && !this.guanzi) {
            this.stringForShow = "奇怪的柜子...";
            this.guanzi = true;
         } else {
            if (this.offsetX == 14 && keyCode == 53 && this.hand.currentFrame == 0) {
               if (zx > 8 && zx < 45 && zy > 49 && zy < 175 && !this.usingTool && this.pzuo && !this.guanzi) {
                  this.stringForShow = "找不到我想要的东西了";
                  this.guanzi = true;
                  return;
               }

               if (zx > 37 && zx < 64 && zy > 49 && zy < 78 && !this.usingTool && this.pshang && !this.guanzi) {
                  this.stringForShow = "找不到我想要的东西了";
                  this.guanzi = true;
                  return;
               }
            }

            if (this.offsetX == 9 && keyCode == 53 && this.hand.currentFrame == 0) {
               if (zx > 8 && zx < 34 && zy > 56 && zy < 83 && !this.usingTool && this.pshang && !this.guanzi) {
                  this.stringForShow = "琳琅满目的各种书籍";
                  this.guanzi = true;
                  return;
               }

               if (zx > 65 && zx < 126 && zy > 56 && zy < 84 && !this.usingTool && this.pshang && !this.guanzi) {
                  this.stringForShow = "琳琅满目的各种书籍";
                  this.guanzi = true;
                  return;
               }

               if (zx > 9 && zx < 64 && zy > 109 && zy < 144 && !this.usingTool && this.pshang && !this.guanzi) {
                  this.stringForShow = "琳琅满目的各种书籍";
                  this.guanzi = true;
                  return;
               }

               if (zx > 132 && zx < 164 && zy > 56 && zy < 84 && !this.usingTool && this.pshang && !this.guanzi) {
                  this.stringForShow = "找不到我想要的东西了";
                  this.guanzi = true;
                  return;
               }
            }

            if (this.offsetX == 13 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 29 && zx < 84 && zy > 44 && zy < 76 && !this.usingTool && this.pshang && !this.shengsd && !this.guanzi) {
               if (this.shuodi == 0) {
                  this.stringForShow = "哇...这不是盗墓小说<古宝魅影>吗?我找";
               }

               if (this.shuodi == 1) {
                  this.stringForShow = "很久了,没想到竟在这里找到了.....";
               }

               if (this.shuodi == 2) {
                  this.stringForShow = "奇怪?怎么<古宝魅影之圣杯堂>系列不见了,";
               }

               if (this.shuodi == 3) {
                  this.stringForShow = "我得去仔细查找一下.....";
               }

               if (this.shuodi == 4) {
                  this.shuodi = 0;
                  this.guanzi = true;
               }

               if (this.shuodi != 4) {
                  ++this.shuodi;
               } else if (this.shuodi == 4) {
                  this.shuodi = 0;
               }

            } else {
               if (this.offsetX == 0) {
                  this.sh1 = 0;
                  if (keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 83 && zx < 115 && zy > 103 && zy < 113 && !this.usingTool && this.pshang && this.shiyong) {
                        this.offsetX = 1;
                        this.hand.positionX = 98;
                        this.hand.positionY = 168;
                     }

                     if (zx > 20 && zx < 55 && zy > 0 && zy < 123 && this.pshang) {
                        this.kaigui1 = true;
                        ++this.sh;
                     }

                     if (zx > 20 && zx < 55 && zy > 0 && zy < 123 && this.kaigui1 && this.sh == 2 && !this.shouji1 && this.pshang && !this.guanzi) {
                        this.shouji1 = true;
                        this.huodeya = true;
                        this.CreateTool(this.loadImage("shouji1"), 2, 2, 0, 0, 15, 27);
                        this.stringForShow = "找到一把钥匙.";
                        this.sh = 0;
                        this.guanzi = true;
                        return;
                     }

                     if (zx > 80 && zx < 115 && zy > 153 && zy < 208 && !this.mao1 && !this.usingTool && this.pxia && !this.guanzi) {
                        this.stringForShow = "喀嚓....门被反锁了,我得想办法逃离这里....";
                        this.guanzi = true;
                        return;
                     }
                  }
               }

               if (this.offsetX == 1 && keyCode == 53 && this.hand.currentFrame == 0 && this.sh1 == 1 && !this.usingTool) {
                  if (zx > 0 && zx < 13 && zy > 73 && zy < 123 && this.pzuo) {
                     this.offsetX = 2;
                     this.hand.positionX = 153;
                     this.hand.positionY = 123;
                  }

                  if (zx > 0 && zx < 33 && zy > 48 && zy < 58 && !this.usingTool && this.pshang) {
                     this.offsetX = 10;
                     this.hand.positionX = 18;
                     this.hand.positionY = 173;
                  }

                  if (zx > 63 && zx < 113 && zy > 98 && zy < 128 && !this.shaizi1 && this.pshang && !this.guanzi || zx > 63 && zx < 113 && zy > 98 && zy < 123 && !this.shaizi1 && this.pyou && !this.guanzi) {
                     this.shaizi1 = true;
                     this.CreateTool(this.loadImage("shaizi1"), 3, 2, 0, 0, 25, 13);
                     this.stringForShow = "拿到一把色子形状的钥匙";
                     this.guanzi = true;
                     return;
                  }

                  if (zx > 133 && zx < 176 && zy > 0 && zy < 63 && !this.gui22 && this.pshang) {
                     this.isOutpasswordkuang = true;
                  }

                  if (zx > 133 && zx < 176 && zy > 0 && zy < 63 && this.gui22 && !this.shuyao1 && this.pshang && !this.guanzi) {
                     this.shuyao1 = true;
                     this.CreateTool(this.loadImage("shu"), 4, 2, 0, 0, 23, 11);
                     this.stringForShow = "得到书房钥匙";
                     this.guanzi = true;
                     return;
                  }
               }

               if (this.offsetX == 2 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 0 && zx < 23 && zy > 93 && zy < 133 && !this.usingTool && this.pzuo && this.rs == 2) {
                     this.offsetX = 3;
                     this.rs = 0;
                     this.hand.positionX = 153;
                     this.hand.positionY = 123;
                  }

                  if (zx > 13 && zx < 53 && zy > 83 && zy < 108 && !this.usingTool && this.pshang) {
                     this.offsetX = 14;
                     this.hand.positionX = 138;
                     this.hand.positionY = 168;
                  }
               }

               if (this.offsetX == 4 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 0 && zx < 23 && zy > 93 && zy < 133 && !this.usingTool && this.pzuo) {
                     this.offsetX = 5;
                     this.hand.positionX = 148;
                     this.hand.positionY = 123;
                  }

                  if (zx > 138 && zx < 176 && zy > 93 && zy < 139 && !this.usingTool && this.pyou) {
                     this.offsetX = 6;
                     this.hand.positionX = 3;
                     this.hand.positionY = 123;
                     return;
                  }

                  if (zx > 33 && zx < 73 && zy > 83 && zy < 108 && !this.usingTool && this.pshang) {
                     this.offsetX = 13;
                     this.hand.positionX = 58;
                     this.hand.positionY = 168;
                  }
               }

               if (this.offsetX == 6 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 0 && zx < 23 && zy > 93 && zy < 133 && !this.usingTool && this.pzuo) {
                     this.offsetX = 4;
                     this.bian = 0;
                     this.hand.positionX = 148;
                     this.hand.positionY = 123;
                  }

                  if (zx > 138 && zx < 176 && zy > 93 && zy < 139 && this.bian == 2 && !this.usingTool && this.pyou) {
                     this.offsetX = 7;
                     this.hand.positionX = 3;
                     this.hand.positionY = 123;
                     return;
                  }
               }

               if (this.offsetX == 5 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 33 && zx < 73 && zy > 83 && zy < 108 && this.fangmen && !this.usingTool && this.pshang) {
                     this.offsetX = 8;
                     this.hand.positionX = 48;
                     this.hand.positionY = 168;
                  }

                  if (zx > 33 && zx < 73 && zy > 83 && zy < 108 && !this.fangmen && !this.usingTool && this.pshang && !this.guanzi) {
                     if (this.shuodi == 0) {
                        this.stringForShow = "糟糕..这个房间的门也被锁上了.看来我离开";
                     }

                     if (this.shuodi == 1) {
                        this.stringForShow = "这里得费一番周折了";
                     }

                     if (this.shuodi == 2) {
                        this.shuodi = 0;
                        this.guanzi = true;
                     }

                     if (this.shuodi != 2) {
                        ++this.shuodi;
                     } else if (this.shuodi == 2) {
                        this.shuodi = 0;
                     }

                     return;
                  }
               }

               if (this.offsetX == 3 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 33 && zx < 73 && zy > 83 && zy < 108 && this.shishai && !this.usingTool && this.pshang) {
                     this.offsetX = 11;
                     this.hand.positionX = 98;
                     this.hand.positionY = 168;
                  } else if (zx > 33 && zx < 73 && zy > 83 && zy < 108 && !this.shishai && !this.usingTool && this.pshang && !this.guanzi) {
                     this.stringForShow = "喀嚓...这门被上锁了,我得去找到钥匙才行...";
                     this.guanzi = true;
                     return;
                  }

                  if (zx > 103 && zx < 143 && zy > 83 && zy < 108 && this.zale && !this.usingTool && this.pshang) {
                     this.offsetX = 15;
                     this.hand.positionX = 78;
                     this.hand.positionY = 168;
                  }
               }

               if (this.offsetX == 15 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 35 && zx < 55 && zy > 0 && zy < 93 && this.pshang) {
                  this.nkai1 = true;
               }

               if (this.offsetX == 6 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 73 && zx < 113 && zy > 83 && zy < 108 && this.shishai1 && !this.usingTool && this.pshang) {
                     this.offsetX = 9;
                     this.hand.positionX = 138;
                     this.hand.positionY = 168;
                  }

                  if (zx > 73 && zx < 113 && zy > 83 && zy < 108 && !this.shishai1 && !this.usingTool && this.pshang && !this.guanzi) {
                     this.stringForShow = "“咔嚓”书房被锁上了，我需要一把钥匙";
                     this.guanzi = true;
                     return;
                  }
               }

               if (this.offsetX == 7 && keyCode == 53 && this.hand.currentFrame == 0) {
                  if (zx > 73 && zx < 113 && zy > 83 && zy < 108 && this.shiyongs && !this.usingTool && this.pshang) {
                     this.offsetX = 12;
                     this.hand.positionX = 78;
                     this.hand.positionY = 168;
                  }

                  if (zx > 73 && zx < 113 && zy > 83 && zy < 108 && !this.shiyongs && !this.usingTool && this.pshang && !this.guanzi) {
                     if (this.shuodi == 0) {
                        this.stringForShow = "???房门的钥匙孔里有卡住的断钥匙,我得去";
                     }

                     if (this.shuodi == 1) {
                        this.stringForShow = "找到另一半才行..";
                     }

                     if (this.shuodi == 2) {
                        this.shuodi = 0;
                        this.guanzi = true;
                     }

                     if (this.shuodi != 2) {
                        ++this.shuodi;
                     } else if (this.shuodi == 2) {
                        this.shuodi = 0;
                     }

                     return;
                  }
               }

               if (this.offsetX == 1 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 73 && zx < 118 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                  this.offsetX = 0;
                  this.hand.positionX = 98;
                  this.hand.positionY = 108;
               }

               if (this.offsetX == 2) {
                  this.sh1 = 0;
                  if (keyCode == 53 && this.hand.currentFrame == 0 && zx > 138 && zx < 176 && zy > 103 && zy < 208 && !this.usingTool && this.pyou) {
                     this.offsetX = 1;
                     this.rs = 0;
                     this.hand.positionX = 3;
                     this.hand.positionY = 98;
                  }
               }

               if (this.offsetX == 3 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 138 && zx < 176 && zy > 95 && zy < 208 && !this.usingTool && this.pyou) {
                  this.offsetX = 2;
                  this.hand.positionX = 3;
                  this.hand.positionY = 123;
               }

               if (this.offsetX == 5 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 138 && zx < 176 && zy > 95 && zy < 208 && !this.usingTool && this.pyou) {
                  this.offsetX = 4;
                  this.hand.positionX = 3;
                  this.hand.positionY = 123;
               }

               if (this.offsetX == 7 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 0 && zx < 23 && zy > 95 && zy < 208 && !this.usingTool && this.pzuo) {
                  this.offsetX = 6;
                  this.hand.positionX = 148;
                  this.hand.positionY = 123;
               } else {
                  if (this.offsetX == 10) {
                     this.sh1 = 0;
                     if (keyCode == 53 && this.hand.currentFrame == 0) {
                        if (zx > 0 && zx < 38 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                           this.offsetX = 1;
                           this.hand.positionX = 18;
                           this.hand.positionY = 53;
                        }

                        if (zx > 58 && zx < 88 && zy > 0 && zy < 93 && !this.weiyao1 && this.pshang && !this.guanzi || zx > 58 && zx < 88 && zy > 0 && zy < 88 && !this.weiyao1 && this.pyou && !this.guanzi) {
                           this.weiyao1 = true;
                           this.CreateTool(this.loadImage("ys"), 10, 2, 0, 0, 23, 11);
                           this.stringForShow = "得到卧房钥匙";
                           this.guanzi = true;
                           return;
                        }
                     }
                  }

                  if (this.offsetX == 8 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 18 && zx < 68 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 5;
                        this.hand.positionX = 58;
                        this.hand.positionY = 98;
                     }

                     if (zx > 0 && zx < 35 && zy > 0 && zy < 93 && this.kongmen && !this.usingTool && this.pshang) {
                        this.offsetX = 16;
                        this.hand.positionX = 78;
                        this.hand.positionY = 168;
                     }

                     if (zx > 0 && zx < 35 && zy > 0 && zy < 93 && !this.kongmen && this.pshang && !this.guanzi) {
                        this.stringForShow = "咔嚓,门被锁上了";
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 15 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 53 && zx < 98 && zy > 153 && zy < 208 && this.zale && !this.usingTool && this.pxia) {
                     this.offsetX = 3;
                     this.hand.positionX = 118;
                     this.hand.positionY = 98;
                  }

                  if (this.offsetX == 17 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 80 && zx < 110 && zy > 0 && zy < 68 && !this.usingTool) {
                        this.offsetX = 16;
                        this.hand.positionX = 88;
                        this.hand.positionY = 73;
                     }

                     if (zx > 40 && zx < 65 && zy > 50 && zy < 68 && !this.mao1 && this.pshang || zx > 40 && zx < 65 && zy > 50 && zy < 68 && !this.mao1 && this.pyou && !this.guanzi) {
                        this.mao1 = true;
                        this.CreateTool(this.loadImage("mao1"), 14, 2, 0, 0, 23, 15);
                        this.stringForShow = "得到带有铃铛的钥匙";
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 16 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 53 && zx < 98 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 8;
                        this.hand.positionX = 23;
                        this.hand.positionY = 88;
                     }

                     if (zx > 70 && zx < 105 && zy > 68 && zy < 78 && !this.k1 && this.pshang && !this.usingTool && !this.guanzi) {
                        this.k1 = true;
                        this.stringForShow = "发现一个暗门";
                        this.guanzi = true;
                        return;
                     }

                     if (zx > 120 && zx < 145 && zy > 118 && zy < 128 && this.pshang) {
                        this.k2 = true;
                        ++this.sh1ii;
                     }

                     if (zx > 120 && zx < 145 && zy > 118 && zy < 128 && this.k2 && !this.k4 && this.sh1ii == 2 && this.pshang && !this.guanzi) {
                        this.k4 = true;
                        this.sh1ii = 0;
                        this.CreateTool(this.loadImage("shengzi"), 13, 2, 0, 0, 34, 20);
                        this.stringForShow = "拿到绳子";
                        this.guanzi = true;
                        return;
                     }

                     if (zx > 70 && zx < 105 && zy > 68 && zy < 78 && this.k1 && this.k3 && !this.usingTool && this.pshang) {
                        this.offsetX = 17;
                        this.hand.positionX = 83;
                        this.hand.positionY = 58;
                     }
                  }

                  if (this.offsetX == 9 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 118 && zx < 170 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 6;
                        this.hand.positionX = 98;
                        this.hand.positionY = 98;
                     }

                     if (zx > 58 && zx < 88 && zy > 123 && zy < 133 && !this.shuji1 && this.pshang && !this.guanzi || zx > 58 && zx < 88 && zy > 93 && zy < 123 && !this.shuji1 && this.pxia && !this.guanzi || zx > 70 && zx < 93 && zy > 103 && zy < 123 && !this.shuji1 && this.pzuo && !this.guanzi) {
                        this.CreateTool(this.loadImage("shuji1"), 5, 2, 0, 0, 33, 31);
                        this.stringForShow = "<古宝魅影之圣杯堂>原来再这里总算找到了..."; // <Зал Святого Грааля Призрака Древнего сокровища> Оказалось, что я наконец-то нашел его здесь...
                        this.shuji1 = true;
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 11 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 73 && zx < 118 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 3;
                        this.hand.positionX = 53;
                        this.hand.positionY = 98;
                     }

                     if (zx > 0 && zx < 23 && zy > 0 && zy < 88 && this.guis1 && !this.bang1 && this.pshang && !this.guanzi) {
                        this.bang1 = true;
                        this.CreateTool(this.loadImage("q"), 7, 2, 0, 0, 40, 9);
                        this.stringForShow = "这真是个奇怪的东西，一定会有什么用处的."; // Это действительно странная вещь, но от нее определенно будет какая-то польза.
                        this.guanzi = true;
                        return;
                     }

                     if (zx > 128 && zx < 143 && zy > 0 && zy < 83 && this.shise1 && this.guis1 && this.bang1 && this.pshang && !this.guanzi) {
                        this.shise1 = false;
                        this.CreateTool(this.loadImage("hse"), 8, 2, 0, 0, 12, 19);
                        this.stringForShow = "拿到带有划痕的色子";
                        this.guis1 = false;
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 12 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 53 && zx < 98 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 7;
                        this.hand.positionX = 98;
                        this.hand.positionY = 98;
                     }

                     if (zx > 73 && zx < 98 && zy > 0 && zy < 88 && this.pshang) {
                        this.guika1 = true;
                        if (!this.qian1) {
                           ++this.sh1i;
                        }
                     }

                     if (zx > 73 && zx < 98 && zy > 0 && zy < 88 && this.guika1 && !this.qian1 && this.sh1i == 2 && this.pshang && !this.guanzi) {
                        this.qian1 = true;
                        this.sh1i = 0;
                        this.CreateTool(this.loadImage("qq"), 9, 2, 0, 0, 9, 10);
                        this.stringForShow = "找到硬币";
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 13 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 33 && zx < 78 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 4;
                        this.hand.positionX = 58;
                        this.hand.positionY = 98;
                     }

                     if (zx > 128 && zx < 176 && zy > 0 && zy < 68 && this.kguizi1 && !this.guanzi) {
                        if (this.shuodi == 0) {
                           this.stringForShow = "9819？";
                        }

                        if (this.shuodi == 1) {
                           this.stringForShow = "这里怎么会出现一组数字，一定有什么含义..";
                        }

                        if (this.shuodi == 2) {
                           this.shuodi = 0;
                           this.guanzi = true;
                        }

                        if (this.shuodi != 2) {
                           ++this.shuodi;
                        } else if (this.shuodi == 2) {
                           this.shuodi = 0;
                        }

                        return;
                     }

                     if (zx > 128 && zx < 176 && zy > 0 && zy < 68 && !this.kguizi1 && this.pshang) {
                        this.kguizi1 = true;
                     } else if (zx > 128 && zx < 176 && zy > 0 && zy < 68 && this.kguizi1 && this.pshang) {
                        this.kguizi1 = false;
                     }

                     if (zx > 83 && zx < 103 && zy > 0 && zy < 78 && this.dang1 && !this.sez1 && this.pshang && !this.guanzi) {
                        this.sez1 = true;
                        this.CreateTool(this.loadImage("se1"), 6, 2, 0, 0, 22, 16);
                        this.stringForShow = "找到色子";
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 14 && keyCode == 53 && this.hand.currentFrame == 0) {
                     if (zx > 113 && zx < 176 && zy > 153 && zy < 208 && !this.usingTool && this.pxia) {
                        this.offsetX = 2;
                        this.hand.positionX = 38;
                        this.hand.positionY = 98;
                     }

                     if (zx > 103 && zx < 118 && zy > 53 && zy < 73 && this.diban1 && !this.dianchi && this.pshang && !this.guanzi) {
                        this.dianchi = true;
                        this.CreateTool(this.loadImage("dianchi"), 12, 2, 0, 0, 8, 16);
                        this.stringForShow = "得到电池";
                        this.guanzi = true;
                        return;
                     }
                  }

                  if (this.offsetX == 14 && keyCode == 53 && this.hand.currentFrame == 0 && zx > 43 && zx < 103 && zy > 100 && zy < 128 && this.pshang && !this.usingTool) {
                     this.ditu = true;
                     if (this.isdew == 0) {
                        this.dmap1 = true;
                     }

                     if (this.isdew == 1) {
                        this.dmap1 = false;
                     }

                     if (this.isdew != 1) {
                        ++this.isdew;
                     } else if (this.isdew == 1) {
                        this.isdew = 0;
                     }

                  } else {
                     if (keyCode == 53 && this.hand.currentFrame == 0 && !this.usingTool) {
                        this.guanzi = false;
                        if (!this.guanzi) {
                           this.stringForShow = "";
                           this.shuodi = 0;
                        }
                     }

                     if (keyCode == 53) {
                        if (this.offsetX == 3 && this.hand.currentFrame == 0 && this.usingTool && zx > 33 && zx < 73 && zy > 83 && zy < 108 && this.pshang && this.tool[this.currentTool].state == 3) {
                           this.freeToolByIndex(3, 2);
                           this.stringForShow = "使用色子钥匙";
                           this.shishai = true;
                        }

                        if (this.offsetX == 0 && this.hand.currentFrame == 0 && this.usingTool) {
                           if (zx > 0 && zx < 176 && zy > 153 && zy < 208 && this.pxia && !this.guanzi && this.tool[this.currentTool].state == 14) {
                              this.freeToolByIndex(14, 2);
                              this.toolStates[15] = 10;
                           }

                           if (zx > 83 && zx < 115 && zy > 103 && zy < 113 && this.tool[this.currentTool].state == 2) {
                              this.freeToolByIndex(2, 2);
                              this.shiyong = true;
                              this.ssk = true;
                              this.stringForShow = "喀嚓门打开了,按5键进入";
                           }
                        }

                        if (this.offsetX == 16 && this.hand.currentFrame == 0 && this.usingTool && zx > 70 && zx < 105 && zy > 68 && zy < 78 && this.k1 && this.pshang && this.tool[this.currentTool].state == 13) {
                           this.freeToolByIndex(13, 2);
                           this.stringForShow = "使用绳子";
                           this.k3 = true;
                        }

                        if (this.offsetX == 5 && this.hand.currentFrame == 0 && this.usingTool && zx > 33 && zx < 73 && zy > 83 && zy < 108 && this.pshang && this.tool[this.currentTool].state == 10) {
                           this.freeToolByIndex(10, 2);
                           this.stringForShow = "使用钥匙";
                           this.fangmen = true;
                        }

                        if (this.offsetX == 8 && this.hand.currentFrame == 0 && this.usingTool && zx > 105 && zx < 125 && zy > 0 && zy < 68 && this.pshang && this.tool[this.currentTool].state == 7) {
                           this.freeToolByIndex(7, 2);
                           this.stringForShow = "钩到一把锤子";
                           this.CreateTool(this.loadImage("chuizi"), 11, 2, 0, 0, 28, 28);
                           this.chuis = true;
                        }

                        if (this.offsetX == 14 && this.hand.currentFrame == 0 && this.usingTool && zx > 103 && zx < 118 && zy > 53 && zy < 73 && this.pshang && !this.diban1 && this.tool[this.currentTool].state == 11) {
                           this.stringForShow = "使用锤子";
                           this.diban1 = true;
                           this.usingTool = false;
                           this.zhi = true;
                        }

                        if (this.offsetX == 3 && this.hand.currentFrame == 0 && this.usingTool && zx > 103 && zx < 143 && zy > 83 && zy < 108 && !this.zale && this.pshang && this.tool[this.currentTool].state == 11) {
                           this.stringForShow = "使用锤子";
                           this.zale = true;
                           this.usingTool = false;
                        }

                        if (this.offsetX == 6 && this.hand.currentFrame == 0 && this.usingTool && zx > 73 && zx < 113 && zy > 83 && zy < 108 && this.pshang && this.tool[this.currentTool].state == 4) {
                           this.freeToolByIndex(4, 2);
                           this.stringForShow = "使用书房钥匙";
                           this.shishai1 = true;
                        }

                        if (this.offsetX == 7 && this.hand.currentFrame == 0 && this.usingTool && zx > 73 && zx < 113 && zy > 83 && zy < 108 && this.pshang && this.tool[this.currentTool].state == 8) {
                           this.freeToolByIndex(8, 2);
                           this.stringForShow = "使用带有划痕的色子";
                           this.shiyongs = true;
                        }

                        if (this.offsetX == 11 && this.hand.currentFrame == 0 && this.usingTool && zx > 128 && zx < 143 && zy > 0 && zy < 83 && this.pshang && this.tool[this.currentTool].state == 6) {
                           this.freeToolByIndex(6, 2);
                           this.stringForShow = "哦..原来这里也是个机关...";
                           this.shise1 = true;
                           this.guis1 = true;
                        }

                        if (this.offsetX == 13) {
                           if (this.hand.currentFrame == 0 && this.usingTool && zx > 18 && zx < 48 && zy > 0 && zy < 68 && this.pshang && this.tool[this.currentTool].state == 5) {
                              this.shengsd = true;
                              this.freeToolByIndex(5, 2);
                              this.stringForShow = "喀嚓...原来这里是个机关.听说<古宝魅影>";
                              this.dang1 = true;
                              return;
                           }

                           if (this.hand.currentFrame == 0 && !this.usingTool && zx > 18 && zx < 48 && zy > 0 && zy < 68 && this.pshang && this.shengsd && !this.sdfer) {
                              this.stringForShow = "系列已经做成手机游戏了..."; // Эта серия была превращена в мобильную игру...
                              this.sdfer = true;
                              return;
                           }
                        }

                        if (this.offsetX == 15 && this.hand.currentFrame == 0 && this.usingTool) {
                           if (zx > 35 && zx < 55 && zy > 0 && zy < 93 && this.nkai1 && this.pshang && this.tool[this.currentTool].state == 12) {
                              this.freeToolByIndex(12, 2);
                              this.stringForShow = "计算机已通电，怎么还是无法启动."; // Компьютер включен, почему он не может запуститься? (перевод с Кит)
                              this.tou = true;
                           }

                           if (zx > 35 && zx < 55 && zy > 0 && zy < 93 && this.nkai1 && this.pshang && this.tou && this.tool[this.currentTool].state == 9) {
                              this.freeToolByIndex(9, 2);
                              this.stringForShow = "喀嚓...哪里发出的声音..好象是门锁的声音"; // Щелчок... Откуда доносился этот звук?.Похоже на звук открываемого дверного замка
                              this.kongmen = true;
                           }
                        }
                     }

                     if (keyCode == KEYCODE_SOFTKEY_LEFT && this.isOutpasswordkuang) {
                        this.isOutpasswordkuang = false;
                        this.keydownNum = 0;
                        this.toolStates[11] = 0;
                        this.toolStates[9] = 0;
                        this.toolStates[10] = 0;
                        this.toolStates[12] = 0;
                        this.toolStates[13] = 0;
                     }

                     if (this.isOutpasswordkuang) {
                        ++this.keydownNum;
                        if (this.keydownNum == 1) {
                           if (keyCode == 48) {
                              this.toolStates[8] = 0;
                           } else if (keyCode == 49) {
                              this.toolStates[8] = 1;
                           } else if (keyCode == 50) {
                              this.toolStates[8] = 2;
                           } else if (keyCode == 51) {
                              this.toolStates[8] = 3;
                           } else if (keyCode == 52) {
                              this.toolStates[8] = 4;
                           } else if (keyCode == 53) {
                              this.toolStates[8] = 5;
                           } else if (keyCode == 54) {
                              this.toolStates[8] = 6;
                           } else if (keyCode == 55) {
                              this.toolStates[8] = 7;
                           } else if (keyCode == 56) {
                              this.toolStates[8] = 8;
                           } else if (keyCode == 57) {
                              this.toolStates[8] = 9;
                           }
                        } else if (this.keydownNum == 2) {
                           if (keyCode == 48) {
                              this.toolStates[9] = 0;
                           } else if (keyCode == 49) {
                              this.toolStates[9] = 1;
                           } else if (keyCode == 50) {
                              this.toolStates[9] = 2;
                           } else if (keyCode == 51) {
                              this.toolStates[9] = 3;
                           } else if (keyCode == 52) {
                              this.toolStates[9] = 4;
                           } else if (keyCode == 53) {
                              this.toolStates[9] = 5;
                           } else if (keyCode == 54) {
                              this.toolStates[9] = 6;
                           } else if (keyCode == 55) {
                              this.toolStates[9] = 7;
                           } else if (keyCode == 56) {
                              this.toolStates[9] = 8;
                           } else if (keyCode == 57) {
                              this.toolStates[9] = 9;
                           }
                        } else if (this.keydownNum == 3) {
                           if (keyCode == 48) {
                              this.toolStates[10] = 0;
                           } else if (keyCode == 49) {
                              this.toolStates[10] = 1;
                           } else if (keyCode == 50) {
                              this.toolStates[10] = 2;
                           } else if (keyCode == 51) {
                              this.toolStates[10] = 3;
                           } else if (keyCode == 52) {
                              this.toolStates[10] = 4;
                           } else if (keyCode == 53) {
                              this.toolStates[10] = 5;
                           } else if (keyCode == 54) {
                              this.toolStates[10] = 6;
                           } else if (keyCode == 55) {
                              this.toolStates[10] = 7;
                           } else if (keyCode == 56) {
                              this.toolStates[10] = 8;
                           } else if (keyCode == 57) {
                              this.toolStates[10] = 9;
                           }
                        } else if (this.keydownNum == 4) {
                           if (keyCode == 48) {
                              this.toolStates[11] = 0;
                           } else if (keyCode == 49) {
                              this.toolStates[11] = 1;
                           } else if (keyCode == 50) {
                              this.toolStates[11] = 2;
                           } else if (keyCode == 51) {
                              this.toolStates[11] = 3;
                           } else if (keyCode == 52) {
                              this.toolStates[11] = 4;
                           } else if (keyCode == 53) {
                              this.toolStates[11] = 5;
                           } else if (keyCode == 54) {
                              this.toolStates[11] = 6;
                           } else if (keyCode == 55) {
                              this.toolStates[11] = 7;
                           } else if (keyCode == 56) {
                              this.toolStates[11] = 8;
                           } else if (keyCode == 57) {
                              this.toolStates[11] = 9;
                           }
                        } else if (this.keydownNum == 5) {
                           if (keyCode == 48) {
                              this.toolStates[12] = 0;
                           } else if (keyCode == 49) {
                              this.toolStates[12] = 1;
                           } else if (keyCode == 50) {
                              this.toolStates[12] = 2;
                           } else if (keyCode == 51) {
                              this.toolStates[12] = 3;
                           } else if (keyCode == 52) {
                              this.toolStates[12] = 4;
                           } else if (keyCode == 53) {
                              this.toolStates[12] = 5;
                           } else if (keyCode == 54) {
                              this.toolStates[12] = 6;
                           } else if (keyCode == 55) {
                              this.toolStates[12] = 7;
                           } else if (keyCode == 56) {
                              this.toolStates[12] = 8;
                           } else if (keyCode == 57) {
                              this.toolStates[12] = 9;
                           }
                        } else if (this.keydownNum == 6) {
                        }

                        if (this.keydownNum == 6) {
                           if (this.toolStates[9] != 9 || this.toolStates[10] != 8 || this.toolStates[11] != 1 || this.toolStates[12] != 9 || keyCode != 53) {
                              if (!this.guanzi) {
                                 this.stringForShow = "密码错误";
                              }

                              this.isOutpasswordkuang = false;
                              this.keydownNum = 0;
                              this.toolStates[11] = 0;
                              this.toolStates[9] = 0;
                              this.toolStates[10] = 0;
                              this.toolStates[12] = 0;
                              this.toolStates[13] = 0;
                              this.guanzi = true;
                              return;
                           }

                           this.isOutpasswordkuang = false;
                           this.gui22 = true;
                           this.keydownNum = 0;
                           this.stringForShow = "硕大的保险柜只存放一把钥匙,真是奇怪.";
                        }
                     }

                  }
               }
            }
         }
      }
   }

   private void freeRoom1() {
      this.juli = 56;
      this.juli11 = 56;
      this.sw1 = false;
      this.anja = null;
      this.sw = 1;
      this.sw2 = 0;
      this.swq = 0;
      this.hu = false;
      this.zimua = false;
      this.offsetX = 0;
      this.layer2 = 0;
      this.hun = false;
      this.huns = false;
      this.jiantou = null;
      this.fsi = false;
      this.jish = 0;
      this.bianl = 0;
      this.xg1 = 0;
      this.xg2 = 0;
      this.xg3 = 0;
      this.xg4 = 0;
      this.xg5 = 0;
      this.xg6 = 0;
      this.xg7 = 0;
      this.xg8 = 0;
      this.wzdh = false;
      this.ksdi = false;
      this.linxi = false;
      this.kisi = false;
      this.buchang = 0;
      this.bide = 0;
      this.swq = 0;
      this.jish = 0;
      this.fsi = false;
      this.kaishigd = false;
      this.back1 = null;
      this.back2 = null;
      this.back3 = null;
      this.back4 = null;
      this.back5 = null;
      this.back8 = null;
      this.back9 = null;
      this.back10 = null;
      this.back11 = null;
      this.back12 = null;
      this.back13 = null;
      this.back14 = null;
      this.back15 = null;
      this.yug = null;
      this.chuang = null;
      this.guizi = null;
      this.guide = null;
      this.zhuoz = null;
      this.shafa = null;
      this.chuzi = null;
      this.dong = null;
      this.mao = null;
      this.kamen = null;
      this.kuangtpong = null;
      this.lou = null;
      this.men = null;
      this.menkuang = null;
      this.paaizi = null;
      this.loub = null;
      this.kaigui = null;
      this.shouji = null;
      this.shaizi = null;
      this.kguizi = null;
      this.shuzi = null;
      this.gui2 = null;
      this.shuyao = null;
      this.shuji = null;
      this.dang = null;
      this.chouti = null;
      this.sez = null;
      this.wei = null;
      this.guis = null;
      this.bang = null;
      this.shise = null;
      this.guika = null;
      this.qian = null;
      this.weiyao = null;
      this.gz = null;
      this.dmap = null;
      this.xmap = null;
      this.diban = null;
      this.nkai = null;
      this.k = null;
      this.kc = null;
      this.s = null;
      this.sk = null;
      this.dian = null;
      this.shishai = false;
      this.kguizi1 = false;
      this.gui22 = false;
      this.shuyao1 = false;
      this.linshi = false;
      this.shishai1 = false;
      this.shuji1 = false;
      this.dang1 = false;
      this.sez1 = false;
      this.guis1 = false;
      this.bang1 = false;
      this.shise1 = false;
      this.shiyongs = false;
      this.shiyong = false;
      this.ditu = false;
      this.guika1 = false;
      this.qian1 = false;
      this.weiyao1 = false;
      this.fangmen = false;
      this.dmap1 = false;
      this.diban1 = false;
      this.dianchi = false;
      this.huodeya = false;
      this.isdew = 0;
      this.kaigui1 = false;
      this.shouji1 = false;
      this.shaizi1 = false;
      this.zale = false;
      this.ssk = false;
      this.nkai1 = false;
      this.kongmen = false;
      this.k1 = false;
      this.k2 = false;
      this.k3 = false;
      this.k4 = false;
      this.mao1 = false;
      this.an = false;
      this.pshang = false;
      this.pxia = false;
      this.pzuo = false;
      this.pyou = false;
      this.qingchu = false;
      this.tou = false;
      this.bian = 0;
      this.sh = 0;
      this.sh1 = 0;
      this.sh1i = 0;
      this.sh1ii = 0;
      this.st = 0;
      this.jsh = null;
      this.rs = 0;
      this.fashang = false;
      this.faxia = false;
      this.fazuo = false;
      this.fayou = false;
      this.zong = false;
      this.sid = 0;
      this.chuis = false;
      this.jiantou1 = null;
      this.shengsd = false;
      this.sdfer = false;
      this.shuodi = 0;
      this.js = 1;
      this.js1 = 0;
      this.kaishij1 = false;
      this.kaishij2 = false;
      this.jianpan = false;
      this.isOutpasswordkuang = false;
      this.password_ = null;
      this.fanwi = null;
      this.numpass_ = null;
      this.keydownNum = 0;
      this.fang = 0;
   }

   private void drawToolstring(String stringToShow) {
      if (stringToShow.length() != 0) {
         this.gr.setFont(afont);
         this.gr.setColor(250, 223, 134);
         this.gr.drawString(stringToShow, (WIDTH - afont.stringWidth(stringToShow)) / 2, HEIGHT - 50 + 10, 0);
      }
   }

   private void drawstringToScreen(String strToShow) {
      if (this.anja == null) {
         this.anja = this.loadImage("anja");
      }

      if (strToShow.length() != 0) {
         this.gr.setFont(afont);
         this.gr.setColor(250, 223, 134);
         String line1 = "";
         String line2 = "";
         String line3 = "";
         int tmpint = 0;

         try {
            label71:
            while(true) {
               line1 = strToShow.substring(0, tmpint + 1);
               if (afont.stringWidth(line1) > WIDTH - 2 * this.fW || tmpint >= strToShow.length() - 1 || strToShow.charAt(tmpint) == '\n') {
                  strToShow = strToShow.substring(tmpint + 1, strToShow.length());
                  tmpint = 0;
                  if (strToShow.length() > 0) {
                     while(true) {
                        line2 = strToShow.substring(0, tmpint + 1);
                        if (afont.stringWidth(line2) > WIDTH - 2 * this.fW || tmpint >= strToShow.length() - 1 || strToShow.charAt(tmpint) == '\n') {
                           strToShow = strToShow.substring(tmpint + 1, strToShow.length());
                           tmpint = 0;
                           break;
                        }

                        ++tmpint;
                     }
                  }

                  if (strToShow.length() > 0) {
                     while(true) {
                        line3 = strToShow.substring(0, tmpint + 1);
                        if (afont.stringWidth(line3) > WIDTH - 2 * this.fW || tmpint >= strToShow.length() - 1 || strToShow.charAt(tmpint) == '\n') {
                           break label71;
                        }

                        ++tmpint;
                     }
                  }
                  break;
               }

               ++tmpint;
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }

         this.gr.drawString(line1, this.fW - 4 - 3, HEIGHT - 55 + 10, 0);
         this.gr.drawString(line2, this.fW - 4 - 3, HEIGHT - this.fH - 4, 0);
         if (this.Timer % 2 == 0) {
            this.gr.drawImage(this.anja, WIDTH - 12, 193, 0);
         } else {
            this.gr.drawImage(this.anja, WIDTH - 12, 192, 0);
         }

      }
   }

   public String[] format(String text, int maxWidth, Font ft) {
      String[] result = null;
      Vector tempR = new Vector();
      int lines = 0;
      int len = text.length();
      int index0 = false;
      int index1 = 0;

      do {
         int widthes = 0;
         boolean wrap = false;

         int index0;
         for(index0 = index1; index1 < len; ++index1) {
            if (text.charAt(index1) == '\n') {
               ++index1;
               wrap = true;
               break;
            }

            widthes += ft.stringWidth("" + text.charAt(index1));
            if (widthes > maxWidth) {
               break;
            }
         }

         ++lines;
         if (wrap) {
            tempR.addElement(text.substring(index0, index1 - 1));
         } else {
            tempR.addElement(text.substring(index0, index1));
         }
      } while(index1 < len);

      result = new String[lines];
      tempR.copyInto(result);
      return result;
   }

   private void CreateTool(Image image, int stateIndex, int stateValue) {
      this.CreateTool(image, stateIndex, stateValue, 0, 0, image.getWidth(), image.getHeight());
   }

   private void CreateTool(Image image, int stateIndex, int stateValue, int px, int py, int pw, int ph) {
      for(int i = 0; i < this.tool.length; ++i) {
         if (this.tool[i] == null) {
            this.toolStates[stateIndex] = stateValue;

            try {
               this.tool[i] = new MySprite(image, 1);
               this.tool[i].state = stateIndex;
               this.tool[i].framesInfo[0][0] = px;
               this.tool[i].framesInfo[0][1] = py;
               this.tool[i].framesInfo[0][2] = pw;
               this.tool[i].framesInfo[0][3] = ph;
               this.tool[i].collideX = this.tool[i].framesInfo[0][0];
               this.tool[i].collideY = this.tool[i].framesInfo[0][1];
               this.tool[i].collideWidth = this.tool[i].framesInfo[0][2];
               this.tool[i].collideHeight = this.tool[i].framesInfo[0][3];
               this.tool[i].positionY = 130 - this.tool[i].framesInfo[0][3] / 2 - 2 - 5 + 10;
            } catch (Exception var10) {
               var10.printStackTrace();
            }
            break;
         }
      }

   }

   private void freeToolByIndex(int stateIndex, int stateValue) {
      this.stateIndex1 = stateIndex;
      this.stateValue1 = stateValue;
      int index = 0;

      int i;
      for(i = 0; i < this.tool.length && this.tool[i] != null; ++i) {
         if (this.tool[i].state == stateIndex) {
            index = i;
            break;
         }
      }

      for(i = index + 1; i < this.tool.length; ++i) {
         if (this.tool[i] == null) {
            this.tool[i - 1] = null;
            break;
         }

         this.tool[i - 1] = this.tool[i];
      }

      this.stringForShow = "";
      this.toolStates[stateIndex] = stateValue;
      this.hand.currentFrame = 0;
      this.usingTool = false;
      this.currentTool = -1;
   }

   private void drawTool() {
      if (this.currentTool != -1 && this.tool[this.currentTool] == null) {
         this.currentTool = -1;
      }

      if (this.currentTool == -1 && this.tool[0] != null) {
         this.currentTool = 0;
      }

      if (this.currentTool != -1 && this.tool[this.currentTool] != null) {
         switch(this.tool[this.currentTool].state) {
         case 1:
            this.toolStringToshow = "钩子";
            break;
         case 2:
            this.toolStringToshow = "按5号键使用钥匙";
            break;
         case 3:
            this.toolStringToshow = "色子钥匙";
            break;
         case 4:
            this.toolStringToshow = "书房钥匙";
            break;
         case 5:
            this.toolStringToshow = "书籍";
            break;
         case 6:
            this.toolStringToshow = "色子";
            break;
         case 7:
            this.toolStringToshow = "怪东西";
            break;
         case 8:
            this.toolStringToshow = "带有划痕的色子";
            break;
         case 9:
            this.toolStringToshow = "硬币";
            break;
         case 10:
            this.toolStringToshow = "卧房钥匙";
            break;
         case 11:
            this.toolStringToshow = "锤子";
            break;
         case 12:
            this.toolStringToshow = "电池";
            break;
         case 13:
            this.toolStringToshow = "绳子";
            break;
         case 14:
            this.toolStringToshow = "带有铃铛的钥匙";
            break;
         default:
            this.toolStringToshow = "请选择道具.";
         }
      }

      if (this.toolkuang == null) {
         this.toolkuang = this.loadImage("toolback1");
      }

      if (this.toolback == null) {
         try {
            this.toolback = new MySprite(this.loadImage("toolback"), 1);
            this.toolback.positionY = 106;
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      for(int i = 0; i <= WIDTH / this.toolback.framesInfo[0][2]; ++i) {
         this.toolback.positionX = i * this.toolback.framesInfo[0][2];
         this.toolback.draw(this.gr);
      }

      if (this.currentTool != -1) {
         this.tool[this.currentTool].positionX = WIDTH / 2 - this.tool[this.currentTool].framesInfo[0][2] / 2;
         this.tool[this.currentTool].draw(this.gr);
         if (this.currentTool > 0 && this.tool[this.currentTool - 1] != null) {
            this.tool[this.currentTool - 1].positionX = WIDTH / 2 - 55 - this.tool[this.currentTool - 1].framesInfo[0][2] / 2;
            this.tool[this.currentTool - 1].draw(this.gr);
         }

         if (this.currentTool > 1 && this.tool[this.currentTool - 2] != null) {
            this.tool[this.currentTool - 2].positionX = WIDTH / 2 - 110 - this.tool[this.currentTool - 2].framesInfo[0][2] / 2;
            this.tool[this.currentTool - 2].draw(this.gr);
         }

         if (this.currentTool < this.tool.length - 1 && this.tool[this.currentTool + 1] != null) {
            this.tool[this.currentTool + 1].positionX = WIDTH / 2 + 55 - this.tool[this.currentTool + 1].framesInfo[0][2] / 2;
            this.tool[this.currentTool + 1].draw(this.gr);
         }

         if (this.currentTool < this.tool.length - 2 && this.tool[this.currentTool + 2] != null) {
            this.tool[this.currentTool + 2].positionX = WIDTH / 2 + 110 - this.tool[this.currentTool + 2].framesInfo[0][2] / 2;
            this.tool[this.currentTool + 2].draw(this.gr);
         }

         this.gr.drawImage(this.toolkuang, (WIDTH - 56) / 2, this.toolback.positionY + 4, 0);
      }

   }

   private void daojuziti(String str, int x, int y, int k) {
      this.gr.setColor(149, 147, 144);
      this.gr.drawString(str, x + 1, y, 0);
      this.gr.drawString(str, x - 1, y, 0);
      this.gr.drawString(str, x, y + 1, 0);
      this.gr.drawString(str, x, y - 1, 0);
      this.gr.setColor(0, 0, 0);
      this.gr.drawString(str, x, y, 0);
   }

   private void onTool(int keyCode) {
      if (keyCode == 49 && this.zimua && this.jianpan && this.zong) {
         this.showTool = false;
         this.usingTool = false;
         this.hand.currentFrame = 0;
      } else if (keyCode != 52 && keyCode != KEYCODE_LEFT) {
         if (keyCode != 54 && keyCode != KEYCODE_RIGHT) {
            if ((keyCode == 53 || keyCode == KEYCODE_FIRE) && this.currentTool != -1) {
               this.hand.currentFrame = 0;
               this.usingTool = true;
               this.showTool = false;
            }
         } else if (this.tool[this.currentTool + 1] != null) {
            ++this.currentTool;
         }
      } else if (this.currentTool > 0) {
         --this.currentTool;
      }

   }

   private void jbInit() throws Exception {
   }
}
