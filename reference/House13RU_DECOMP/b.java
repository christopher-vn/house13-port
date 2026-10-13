import com.nokia.mid.ui.FullCanvas;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class b extends FullCanvas implements Runnable {
   public static int d1;
   public static int cm;
   private static int V;
   private static int a;
   private static int aN;
   private static int dU;
   private static int d;
   private static int bc;
   private static int cJ;
   private static int cc;
   private static String dg;
   private static String K;
   private static final byte dW = 0;
   private static final byte ca = 2;
   private static final byte d7 = 3;
   private static final byte cb = 1;
   private static final byte df = 4;
   public static final int bT = 0;
   public static final int long = 1;
   public static final int dZ = 3;
   public static final int ef = 2;
   public static int o = 0;
   private static final byte aK = 1;
   private static final byte aH = 2;
   private static final byte aF = 3;
   private static final byte aE = 4;
   private static final byte aD = 5;
   private static final byte aC = 6;
   private static final byte aB = 7;
   private static final byte aA = 8;
   private static final byte az = 9;
   private static final byte bP = 10;
   private static final byte bN = 11;
   private static final byte bK = 12;
   private static final byte bJ = 13;
   private static final byte bH = 14;
   private static final byte bF = 15;
   private static final byte bD = 16;
   private static final byte bC = 17;
   private static final byte bB = 18;
   private static final byte bA = 19;
   private static final byte bt = 20;
   private static final byte bs = 21;
   private static final byte bq = 22;
   private static final byte bo = 23;
   private static final byte bm = 24;
   private static final byte bl = 25;
   private static final byte bk = 26;
   private static final byte bi = 27;
   private static final byte co = -1;
   private static final Font dD = Font.getFont(0, 0, 8);
   private int di;
   private int db;
   private int dV;
   private Image d8;
   private Image av;
   private Image t;
   private Thread bv;
   private boolean ar;
   private String[] eh;
   private String[] j;
   private String[] i;
   private String[] h;
   private byte b0;
   private int bz;
   private int bO;
   private int dA;
   private int cQ;
   private boolean dI;
   private boolean aR;
   private Image s;
   public Graphics cO;
   public static boolean aI = true;
   private String c4;
   private a bR;
   private a b9;
   private a cC;
   private a bx;
   private a eg;
   private c x;
   private byte dc;
   private c a8;
   private int[] aQ;
   private c[] c5;
   private int void;
   private c ab;
   private String bf;
   private String m;
   private boolean dt;
   private boolean ac;
   private boolean bn;
   private boolean Z;
   private boolean c9;
   private Image bI;
   private Image bG;
   public boolean cP;
   public byte dY;
   private Image dx;
   private Image dw;
   private Image dv;
   private boolean goto;
   private byte if;
   private int dM;
   private int dK;
   private boolean at;
   private boolean ct;
   private boolean cs;
   private boolean q;
   private boolean c6;
   private Image ah;
   private int bU;
   private int b8;
   private int b7;
   private boolean H;
   private boolean du;
   private int e;
   private Image bQ;
   private Image bX;
   private Image dG;
   private int bZ;
   private int bj;
   private Image aT;
   private Image dT;
   private Image au;
   private int a9;
   private boolean dm;
   private boolean cv;
   private boolean d0;
   private int dO;
   private int cn;
   private int cR;
   private boolean ee;
   private Image dN;
   private Image aO;
   private int am;
   private int dd;
   private int c3;
   private Image bL;
   private Image as;
   private int cy;
   private boolean dh;
   private Image bS;
   private Image bW;
   private Image dP;
   private Image da;
   private Image dS;
   private Image dq;
   private Image char;
   private int[][] cI;
   private int[][] cG;
   private int[][] cF;
   private int[][] cD;
   private int[][] cB;
   private int[][] cA;
   private int[][] cz;
   private int[][] cx;
   private int[][] cw;
   private int[][] cu;
   private int[][] c0;
   private int[][] cZ;
   private int[][] cY;
   private int[][] cX;
   private int[][] cW;
   private int[][] cV;
   private int[][] cU;
   private int[][] cT;
   private Image dy;
   private Image aM;
   private int[][] dL;
   private Image bM;
   private int S;
   private boolean b2;
   private boolean b1;
   private boolean g;
   private boolean l;
   private boolean v;
   private int f;
   private int ce;
   private Image O;
   private Image N;
   private Image M;
   private Image L;
   private Image J;
   private Image G;
   private Image F;
   private Image aq;
   private Image ap;
   private Image ao;
   private Image an;
   private Image al;
   private Image ak;
   private Image C;
   private Image U;
   private Image ay;
   private Image Q;
   private Image else;
   private Image B;
   private Image cL;
   private Image bu;
   private Image T;
   private Image u;
   private Image aY;
   private Image b4;
   private Image ec;
   private Image d5;
   private Image bp;
   private Image c1;
   private Image b6;
   private Image dk;
   private Image ae;
   private Image cH;
   private Image cN;
   private Image aP;
   private Image bh;
   private Image X;
   private Image br;
   private Image aw;
   private Image bb;
   private Image cM;
   private Image aa;
   private Image be;
   private Image d9;
   private Image aJ;
   private Image dJ;
   private Image bY;
   private Image dE;
   private Image D;
   private Image af;
   private boolean de;
   private boolean dj;
   private boolean case;
   private boolean dB;
   private boolean w;
   private boolean ei;
   private boolean Y;
   private boolean bg;
   private boolean c7;
   private boolean I;
   private boolean cd;
   private boolean R;
   private boolean aS;
   private boolean P;
   private boolean dC;
   private boolean a5;
   private boolean dF;
   private boolean ai;
   private boolean aL;
   private boolean cr;
   private boolean cE;
   private boolean d4;
   private boolean ea;
   private boolean aG;
   private boolean b5;
   private boolean ba;
   private boolean cj;
   private boolean ch;
   private boolean cg;
   private boolean cf;
   private boolean W;
   private boolean d6;
   private boolean dQ;
   private boolean c;
   private boolean b;
   private boolean b3;
   private boolean r;
   private boolean do;
   private boolean dn;
   private int ed;
   private int E;
   private int cq;
   private int dR;
   private int dr;
   private boolean try;
   private Image aj;
   private c bV;
   private boolean cl;
   private int ag;
   private Image aU;
   private int A;
   private int z;
   private int new;
   private int int;
   private int ad;
   private boolean byte;
   private boolean cp;
   private boolean dz;
   private boolean ds;
   private boolean ax;
   private Image a7;
   private int dp;
   private int a4;
   private int a3;
   private int a2;
   private int a1;
   private int a0;
   private int aX;
   private int aW;
   private int aV;
   private boolean dX;
   private boolean c8;
   private boolean a6;
   private boolean cK;
   private int d2;
   private int dH;
   private int eb;
   private int bE;
   private boolean aZ;
   private Image ci;
   private int n;
   private int cS;
   private int bw;
   private boolean bd;
   private boolean by;
   private boolean k;
   private boolean c2;
   private boolean dl;
   private boolean d3;
   private Image y;
   private int for;
   private int null;
   private Image p;
   private int ck;

   public b() {
      this.di = dD.getHeight();
      this.db = dD.stringWidth("В");
      this.dV = 0;
      this.dI = false;
      this.aR = false;
      this.c4 = "";
      this.b8 = 52;
      this.b7 = 108;
      this.bZ = 1;
      this.dO = 0;
      this.cn = 0;
      this.dd = 56;
      this.c3 = 56;
      this.cy = 0;
      this.E = 0;
      this.cq = 0;
      this.dR = 0;
      this.dr = 0;
      this.z = 1;
      this.new = 0;
      this.int = 0;
      this.byte = false;
      this.aZ = false;

      try {
         d1 = 240;
         cm = 320;
         V = 8;
         a = -1;
         aN = -2;
         dU = -3;
         d = -4;
         bc = -5;
         cJ = -6;
         cc = -7;
         this.t();
         this.f();
         this.s = Image.createImage(d1, cm);
         this.cO = this.s.getGraphics();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      try {
         this.r();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void new() {
      this.c4 = TheMIDlet.if.getAppProperty("SettingName");
      if (this.c4.equals("MOTOV600")) {
         d1 = 176;
         cm = 208;
         a = this.getKeyCode(1);
         aN = this.getKeyCode(6);
         dU = this.getKeyCode(2);
         d = this.getKeyCode(5);
         bc = this.getKeyCode(8);
         cJ = 22;
         cc = 21;
      } else if (this.c4.equals("MOTOE398")) {
         d1 = 176;
         cm = 204;
         a = this.getKeyCode(1);
         aN = this.getKeyCode(6);
         dU = this.getKeyCode(2);
         d = this.getKeyCode(5);
         bc = this.getKeyCode(8);
         cJ = -22;
         cc = -21;
         dg = "Правая программная";
         K = "Левая программная";
      } else if (this.c4.equals("K700C")) {
         d1 = 176;
         cm = 220;
         a = this.getKeyCode(1);
         aN = this.getKeyCode(6);
         dU = this.getKeyCode(2);
         d = this.getKeyCode(5);
         bc = this.getKeyCode(8);
         cJ = -6;
         cc = -7;
      } else if (this.c4.equals("PanasonicX800")) {
         d1 = 176;
         cm = 208;
         a = this.getKeyCode(1);
         aN = this.getKeyCode(6);
         dU = this.getKeyCode(2);
         d = this.getKeyCode(5);
         bc = -5;
         cJ = -6;
         cc = -6;
         dg = "Левая программная";
         K = "Левая программная";
      }

   }

   private void t() {
      try {
         this.a8 = new c(this.if("hand"), 4);
         this.k();

         for(this.dK = 0; this.dK < 3; ++this.dK) {
            this.a8.h[this.dK][0] = this.dK * 21;
            this.a8.h[this.dK][1] = 0;
            this.a8.h[this.dK][2] = 21;
            this.a8.h[this.dK][3] = 30;
         }

         this.a8.goto = 0;
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.b0 = -1;
      this.bz = 0;
      this.bO = 0;
      this.dA = 0;
      this.cQ = 0;
      aI = true;
      this.ar = false;
      this.dm = true;
      this.a9 = 10;
      this.x();
   }

   private void q() {
      if (this.ah == null && !this.c6) {
         this.ah = this.if("jiantou1");
      }

      if (!this.c6) {
         if (!this.at) {
            this.cO.drawRegion(this.ah, 12, 0, 15, 12, 0, 105 + this.b8, 110 + this.b7, 0);
            this.cO.drawRegion(this.ah, 39, 4, 24, 15, 0, 100 + this.b8, 120 + this.b7, 0);
         }

         if (!this.ct) {
            this.cO.drawRegion(this.ah, 63, 19, 24, 15, 0, 100 + this.b8, 168 + this.b7, 0);
            this.cO.drawRegion(this.ah, 12, 27, 15, 12, 0, 105 + this.b8, 183 + this.b7, 0);
         }

         if (!this.cs) {
            this.cO.drawRegion(this.ah, 63, 4, 24, 15, 0, 70 + this.b8, 150 + this.b7, 0);
            this.cO.drawRegion(this.ah, 0, 12, 12, 15, 0, 58 + this.b8, 150 + this.b7, 0);
         }

         if (!this.q) {
            this.cO.drawRegion(this.ah, 39, 19, 24, 15, 0, 130 + this.b8, 150 + this.b7, 0);
            this.cO.drawRegion(this.ah, 27, 12, 12, 15, 0, 153 + this.b8, 150 + this.b7, 0);
         }

         if (++this.bU / 4 % 2 == 0) {
            if (this.bU > 20) {
               this.c6 = true;
               this.bU = 20;
            }

            this.cO.drawRegion(this.ah, 12, 0, 15, 12, 0, 105 + this.b8, 110 + this.b7, 0);
            this.cO.drawRegion(this.ah, 87, 4, 24, 15, 0, 100 + this.b8, 120 + this.b7, 0);
            this.cO.drawRegion(this.ah, 12, 27, 15, 12, 0, 105 + this.b8, 183 + this.b7, 0);
            this.cO.drawRegion(this.ah, 111, 19, 24, 15, 0, 100 + this.b8, 168 + this.b7, 0);
            this.cO.drawRegion(this.ah, 0, 12, 12, 15, 0, 58 + this.b8, 150 + this.b7, 0);
            this.cO.drawRegion(this.ah, 111, 4, 24, 15, 0, 70 + this.b8, 150 + this.b7, 0);
            this.cO.drawRegion(this.ah, 27, 12, 12, 15, 0, 153 + this.b8, 150 + this.b7, 0);
            this.cO.drawRegion(this.ah, 87, 19, 24, 15, 0, 130 + this.b8, 150 + this.b7, 0);
         }
      } else {
         this.ah = null;
      }

   }

   private void k() {
      this.a8.j = (d1 - this.a8.h[this.a8.goto][2]) / 2 + 40;
      this.a8.i = cm - 80;
      this.a8.else = 0;
      this.a8.char = 0;
      this.a8.goto = 0;
   }

   private void c() {
      this.ar = false;
      this.bz = 0;
      this.aR = false;
      this.dc = 1;
      this.aQ = new int[16];
      this.c5 = new c[14];
      this.bf = "";
      this.m = "";
      o = 0;
      this.dt = false;
      this.goto = false;
      this.if = 0;
      this.cP = false;
      this.dY = 60;
      this.cR = cm - 10;
      this.ee = false;
      this.dL = new int[11][3];
      this.dL[0][0] = 223;
      this.dL[0][1] = 220;
      this.dL[0][2] = 122;
      this.dL[1][0] = 213;
      this.dL[1][1] = 211;
      this.dL[1][2] = 162;
      this.dL[2][0] = 223;
      this.dL[2][1] = 220;
      this.dL[2][2] = 122;
      this.dL[3][0] = 234;
      this.dL[3][1] = 229;
      this.dL[3][2] = 81;
      this.dL[4][0] = 244;
      this.dL[4][1] = 237;
      this.dL[4][2] = 39;
      this.dL[5][0] = 213;
      this.dL[5][1] = 211;
      this.dL[5][2] = 162;
      this.dL[6][0] = 223;
      this.dL[6][1] = 220;
      this.dL[6][2] = 122;
      this.dL[7][0] = 234;
      this.dL[7][1] = 229;
      this.dL[7][2] = 81;
      this.dL[8][0] = 244;
      this.dL[8][1] = 237;
      this.dL[8][2] = 39;
      this.dL[9][0] = 213;
      this.dL[9][1] = 211;
      this.dL[9][2] = 162;
      this.dL[10][0] = 223;
      this.dL[10][1] = 220;
      this.dL[10][2] = 122;
   }

   public void x() {
      if (null == this.bv) {
         this.bv = new Thread(this);
         this.bv.start();
      }

   }

   private void a(int var1) {
      try {
         if (a.do) {
            switch(var1) {
            case 0:
               if (this.bR == null) {
                  this.bR = new a("menu");
               }
               break;
            case 1:
               if (this.cC == null) {
                  this.cC = new a("get");
               }
               break;
            case 2:
               if (this.bx == null) {
                  this.bx = new a("wrong");
               }
               break;
            case 3:
               if (this.eg == null) {
                  this.eg = new a("correct");
               }
               break;
            case 4:
               if (this.b9 == null) {
                  this.b9 = new a("mapbg");
               }
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void new(int var1) {
      try {
         switch(var1) {
         case 0:
            if (this.bR != null && a.do) {
               this.bR.a();
            }
            break;
         case 1:
            if (this.cC != null && a.do) {
               this.cC.a();
            }
            break;
         case 2:
            if (this.bx != null && a.do) {
               this.bx.a();
            }
            break;
         case 3:
            if (this.eg != null && a.do) {
               this.eg.a();
            }
            break;
         case 4:
            if (this.b9 != null && a.do) {
               this.b9.a();
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void do(int var1) {
      if (!a.do) {
         switch(var1) {
         case 0:
            if (this.bR != null) {
               this.bR.if();
               this.bR = null;
            }
            break;
         case 1:
            if (this.cC != null) {
               this.cC.if();
               this.cC = null;
            }
            break;
         case 2:
            if (this.bx != null) {
               this.bx.if();
               this.bx = null;
            }
            break;
         case 3:
            if (this.eg != null) {
               this.eg.if();
               this.eg = null;
            }
            break;
         case 4:
            if (this.b9 != null) {
               this.b9.if();
               this.b9 = null;
            }
         }
      }

   }

   private void int(int var1) {
      switch(var1) {
      case 0:
         if (this.bR != null) {
            this.bR.if();
            this.bR = null;
         }
         break;
      case 1:
         if (this.cC != null) {
            this.cC.if();
            this.cC = null;
         }
         break;
      case 2:
         if (this.bx != null) {
            this.bx.if();
            this.bx = null;
         }
         break;
      case 3:
         if (this.eg != null) {
            this.eg.if();
            this.eg = null;
         }
         break;
      case 4:
         if (this.b9 != null) {
            this.b9.if();
            this.b9 = null;
         }
      }

   }

   public void if() {
      this.c5 = null;
      this.ab = null;
      this.dx = null;
      this.dw = null;
      this.O = null;
      this.a8.h[0][0] = 4;
      this.a8.h[0][1] = 1;
      this.a8.h[0][2] = 16;
      this.a8.h[0][3] = 29;
      this.a8.goto = 0;
      this.k();
      this.for();
      this.eh = null;
      this.j = null;
      this.i = null;
      this.ck = 0;
      this.dm = true;
      this.a9 = 10;
      this.am = 0;
      System.gc();
   }

   public void paint(Graphics var1) {
      try {
         switch(this.b0) {
         case -1:
            this.o();
            break;
         case 0:
            this.d();
            break;
         case 1:
            this.l();
            break;
         case 2:
            this.else();
         }

         if (this.ac || this.c9 || this.bn || this.Z) {
            if (this.a8.goto != 2) {
               ++this.a8.goto;
            } else if (this.a8.goto == 2) {
               --this.a8.goto;
            }

            if (this.r) {
               this.a8.goto = 0;
            }
         }

         if (this.dp == 2) {
            this.keyPressed(56);
         }

         this.dI = true;
         var1.setClip(0, 0, d1, cm);
         var1.drawImage(this.s, 0, 0, 0);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      System.gc();
   }

   public void keyPressed(int var1) {
      if (this.ai) {
         this.ai = false;
      }

      try {
         if (this.dI) {
            switch(this.b0) {
            case -1:
            default:
               break;
            case 0:
               this.for(var1);
               break;
            case 1:
               this.char(var1);
               break;
            case 2:
               this.byte(var1);
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void for(int var1) {
      this.bQ = null;
      this.b0 = 1;
   }

   private void char(int var1) {
      if (this.cv) {
         if (var1 == a || var1 == 50) {
            if (this.bO != 3 && this.bO != 1) {
               if (this.bO == 2) {
                  a.do = !a.do;
               } else if (this.bO == 0) {
                  this.dm = false;
                  this.a9 = 0;
                  --this.dV;
                  if (this.dV < 0) {
                     this.dV = 4;
                  }
               }
            } else {
               --this.dA;
            }
         }

         if ((var1 == d || var1 == 54) && this.bO == 0) {
         }

         if (var1 == aN || var1 == 56) {
            if (this.bO != 3 && this.bO != 1) {
               if (this.bO == 2) {
                  a.do = !a.do;
               } else if (this.bO == 0) {
                  this.dm = false;
                  this.a9 = 0;
                  this.dV = (this.dV + 1) % 5;
               }
            } else {
               ++this.dA;
            }
         }

         if ((var1 == dU || var1 == 52) && this.bO == 0) {
         }

         if (var1 == bc || var1 == cJ || var1 == 53) {
            if (this.dV == 0) {
               this.byte();
               this.dI = false;
               this.b0 = 2;
               this.c();
            } else if (this.dV == 3) {
               if (this.bO == 0) {
                  this.bO = 3;
                  return;
               }

               if (this.bO == 0) {
                  this.eh = null;
                  this.j = null;
                  this.i = null;
                  this.h = null;
               }
            } else if (this.dV == 2) {
               if (this.bO == 0) {
                  this.bO = 2;
                  return;
               }
            } else if (this.dV == 1) {
               if (this.bO == 0) {
                  this.bO = 1;
                  return;
               }

               if (this.bO == 0) {
                  this.eh = null;
                  this.j = null;
                  this.i = null;
                  this.h = null;
               }
            } else if (this.dV == 4) {
               aI = false;
               if (this.bR != null) {
                  this.bR.if();
               }

               TheMIDlet.a();
            }
         }

         if (var1 == bc || var1 == cc || var1 == 53) {
            if (this.dV == 2 && this.bO == 2) {
               this.bO = 0;
            }

            if (this.dV == 3 && this.bO == 3) {
               this.bO = 0;
               this.eh = null;
               this.j = null;
               this.i = null;
               this.h = null;
               this.dA = 0;
            }

            if (this.dV == 1 && this.bO == 1) {
               this.bO = 0;
               this.eh = null;
               this.j = null;
               this.i = null;
               this.h = null;
               this.dA = 0;
            }
         }
      }

   }

   private void byte(int var1) {
      if (this.goto || this.ee || var1 != bc && var1 != cJ && var1 != 53) {
         if (!this.goto && this.ee) {
            ++this.if;
            if (this.if == 4) {
               this.goto = true;
               this.ee = false;
               this.dx = null;
               this.dw = null;
               this.dv = null;
               this.eh = null;
               this.aQ[31] = 1;
               System.gc();
            }

         } else if (this.aQ[15] == 10) {
            this.if();
            this.dI = false;
            this.b0 = 1;
            this.bO = 0;
         } else {
            if (var1 == bc && !this.aR && !this.du && !this.H) {
               var1 = 53;
            }

            if (var1 != cJ && var1 != bc || !this.du && !this.H) {
               if (var1 == cc && this.try) {
                  this.try = false;
                  this.ag = 0;
                  this.bV = null;
                  this.aQ[8] = 0;
                  this.aQ[9] = 0;
                  this.aQ[10] = 0;
                  this.aQ[11] = 0;
                  this.aQ[12] = 0;
                  this.aQ[13] = 0;
                  this.aQ[14] = 0;
               }

               if (!this.c6) {
                  if (this.dp == 0) {
                     if (var1 == 53 || var1 == cc) {
                        this.dp = 1;
                     }
                  } else if (this.dp == 1 && (var1 == 53 || var1 == cc)) {
                     this.dp = 2;
                  }
               }

               if (!this.c6 && this.dH == 3 && (var1 == 53 || var1 == cc)) {
                  this.dX = true;
               }

               if (!this.c6 && this.c8 && (var1 == 53 || var1 == cc)) {
                  this.dp = 3;
               }

               if ((var1 == cc && !this.k || var1 == bc) && this.aQ[6] != 7 && !this.du && !this.H && this.dz && this.g && this.c6 && !this.k && !this.aR) {
                  this.aR = true;
                  this.du = false;
                  this.H = false;
               } else {
                  if (var1 == cc && this.k) {
                     this.k = false;
                     this.n = 0;
                  }

                  if (this.aR) {
                     if (var1 == cJ || var1 == bc || var1 == 53) {
                        if (o == 0) {
                           this.aR = false;
                           return;
                        }

                        if (o == 2) {
                           this.H = true;
                           this.aR = false;
                           return;
                        }

                        if (o == 1) {
                           this.du = true;
                           this.aR = false;
                           return;
                        }

                        if (o == 3) {
                           this.if();
                           this.dI = false;
                           this.b0 = 1;
                           this.bO = 0;
                           return;
                        }
                     }

                     if (var1 == a || var1 == 50) {
                        --o;
                        if (o < 0) {
                           o = 3;
                        }
                     }

                     if (var1 == aN || var1 == 56) {
                        ++o;
                        if (o > 3) {
                           o = 0;
                        }
                     }
                  }

                  if (this.H) {
                     if (var1 == a || var1 == 50) {
                        a.do = !a.do;
                     }

                     if (var1 == aN || var1 == 56) {
                        a.do = !a.do;
                     }
                  }

                  if (this.bf.equals("Неправильное использование!")) {
                     this.cP = false;
                     this.a8.goto = 0;
                     this.int(2);
                  }

                  this.bf = "";
                  this.m = "";
                  this.int(1);
                  this.int(3);
                  if (this.dt && !this.aR && !this.du && !this.H) {
                     this.if(var1);
                  } else {
                     if (this.a8.goto == 0 && var1 == 53 && this.cP) {
                        this.bf = "Неправильное использование!";
                     } else {
                        this.bf = "";
                     }

                     if (var1 == 49 && !this.aR && !this.du && !this.H && this.aQ[6] < 7 && !this.try && this.dz && this.g && this.c6) {
                        this.dt = true;
                        switch(this.ck) {
                        case 1:
                           this.void = 0;
                           break;
                        case 2:
                        case 3:
                           this.void = 1;
                           break;
                        case 4:
                           this.void = 2;
                           break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                           this.void = 3;
                        }
                     }

                     if (var1 == bc) {
                     }

                     if (!this.aR && !this.du && !this.H) {
                        if (this.dc == 1) {
                           this.try(var1);
                        }

                        this.case(var1);
                     }

                     if (this.bf.equals("Неправильное использование!")) {
                        this.cP = false;
                        this.a8.goto = 0;
                     } else if ((this.dt || !this.bf.startsWith("Быть")) && !this.dt && this.bf.startsWith("Использовать")) {
                     }

                     if (var1 == 35) {
                     }
                  }

               }
            } else {
               this.aR = true;
               this.du = false;
               this.H = false;
            }
         }
      } else {
         this.byte = true;
         this.z = 500;
         this.new = 176;
         this.aZ = false;
         this.bE = 41;
         this.bQ = null;
         this.aT = null;
         this.as = null;
      }
   }

   private void g() {
      if (this.H) {
         this.cO.setColor(1, 1, 1);
         this.cO.fillRect(0, 0, d1, cm);
         if (this.au == null) {
            this.au = this.if("shou");
         }

         this.cO.setFont(dD);
         this.cO.setColor(100, 100, 255);
         this.cO.drawString("Настройка звука", d1 / 2 - dD.stringWidth("Настройка звука") / 2, 8, 0);
         this.cO.drawString("____________", d1 / 2 - dD.stringWidth("____________") / 2, 16, 0);
         if (a.do) {
            this.cO.setColor(255, 255, 255);
            this.cO.drawString("Звук:  ДА", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 - 18, 0);
            this.cO.drawImage(this.au, (d1 - dD.stringWidth("Звук:  ДА")) / 2 + 68 + 32, cm / 2 - 16, 0);
            this.cO.setColor(200, 200, 200);
            this.cO.drawString("Звук:  НЕТ", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 + 8, 0);
         } else {
            this.int(4);
            this.int(0);
            this.int(1);
            this.int(2);
            this.int(3);
            this.cO.setColor(200, 200, 200);
            this.cO.drawString("Звук:  ДА", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 - 18, 0);
            this.cO.setColor(255, 255, 255);
            this.cO.drawString("Звук:  НЕТ", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 + 8, 0);
            this.cO.drawImage(this.au, (d1 - dD.stringWidth("Звук:  ДА")) / 2 + 68 + 32, cm / 2 + 8, 0);
         }
      }

   }

   private void int() {
      if (this.du) {
         this.cO.setColor(0, 0, 0);
         this.cO.fillRect(0, 0, d1, cm);
         this.cO.setFont(dD);
         this.cO.setColor(100, 100, 255);
         this.cO.drawString(" ИНФОРМАЦИЯ", (d1 - dD.stringWidth(" ИНФОРМАЦИЯ")) / 2, 5, 0);
         this.cO.setColor(255, 255, 255);
         this.cO.drawString("В игре НЕТ сохранения!", 3, 15 + this.di, 0);
         this.cO.drawString("2,4,6,8- перемещение", 3, 20 + 2 * this.di, 0);
         this.cO.drawString("\"5\"- выбор действий ", 3, 22 + 3 * this.di, 0);
         this.cO.drawString("\"1\"- список предметов", 3, 24 + 4 * this.di, 0);
      }

   }

   private void void() {
      if (!this.dt && !this.aR) {
         this.long();
      }

   }

   private void case(int var1) {
      if (this.a8 != null) {
         if (!this.try) {
            if (var1 == a && !this.d6 && this.g && this.c6 || var1 == 50 && !this.d6 && this.g && this.c6) {
               this.d6 = true;
               this.k = false;
               this.n = 0;
               this.dQ = true;
               this.c = false;
               this.b = false;
               this.b3 = false;
               this.cP = false;

               for(this.dK = 0; this.dK < 3; ++this.dK) {
                  this.a8.h[this.dK][0] = this.dK * 21;
                  this.a8.h[this.dK][1] = 30;
                  this.a8.h[this.dK][2] = 21;
                  this.a8.h[this.dK][3] = 29;
               }

               this.a8.char = -5;
               this.bn = true;
               if (this.ce == 2) {
                  this.S = 2;
               }

               if (this.ce == 6) {
                  this.ed = 2;
               }

               if (this.ce == 13 && this.dl) {
                  this.d3 = true;
               }
            } else if (var1 == dU && !this.d6 && this.g && this.c6 || var1 == 52 && !this.d6 && this.g && this.c6) {
               this.d6 = true;
               this.k = false;
               this.n = 0;
               this.dQ = false;
               this.c = false;
               this.b = true;
               this.b3 = false;
               this.cP = false;

               for(this.dK = 0; this.dK < 3; ++this.dK) {
                  this.a8.h[this.dK][0] = this.dK * 21;
                  this.a8.h[this.dK][1] = 59;
                  this.a8.h[this.dK][2] = 21;
                  this.a8.h[this.dK][3] = 30;
               }

               this.a8.else = -5;
               this.Z = true;
               if (this.ce == 2) {
                  this.S = 2;
               }

               if (this.ce == 6) {
                  this.ed = 2;
               }

               if (this.ce == 13 && this.dl) {
                  this.d3 = true;
               }
            } else if (var1 == aN && !this.d6 && this.g && this.c6 || var1 == 56 && !this.d6 && this.g && this.c6) {
               this.d6 = true;
               this.k = false;
               this.n = 0;
               this.dQ = false;
               this.c = true;
               this.b = false;
               this.b3 = false;
               this.cP = false;

               for(this.dK = 0; this.dK < 3; ++this.dK) {
                  this.a8.h[this.dK][0] = this.dK * 21;
                  this.a8.h[this.dK][1] = 0;
                  this.a8.h[this.dK][2] = 21;
                  this.a8.h[this.dK][3] = 30;
               }

               this.a8.char = 5;
               this.ac = true;
               if (this.ce == 6) {
                  this.ed = 2;
               }

               if (this.ce == 2) {
                  this.S = 2;
               }

               if (this.ce == 13 && this.dl) {
                  this.d3 = true;
               }
            } else if (var1 == d && !this.d6 && this.g && this.c6 || var1 == 54 && !this.d6 && this.g && this.c6) {
               this.d6 = true;
               this.k = false;
               this.n = 0;
               this.dQ = false;
               this.c = false;
               this.b = false;
               this.b3 = true;
               this.cP = false;

               for(this.dK = 0; this.dK < 3; ++this.dK) {
                  this.a8.h[this.dK][0] = this.dK * 21;
                  this.a8.h[this.dK][1] = 89;
                  this.a8.h[this.dK][2] = 21;
                  this.a8.h[this.dK][3] = 28;
               }

               this.a8.else = 5;
               this.c9 = true;
               if (this.ce == 6) {
                  this.ed = 2;
               }

               if (this.ce == 2) {
                  this.S = 2;
               }

               if (this.ce == 13 && this.dl) {
                  this.d3 = true;
               }
            }

            this.p();
         }

      }
   }

   public void keyReleased(int var1) {
      if (this.dI) {
         if (this.a8 != null) {
            switch(this.b0) {
            case 2:
               this.d6 = false;
               this.r = false;
               this.ac = false;
               this.bn = false;
               this.Z = false;
               this.c9 = false;
               this.a8.goto = 0;
               this.a8.else = 0;
               this.a8.char = 0;
            case 1:
            default:
            }
         }
      }
   }

   public void run() {
      long var1 = 0L;
      long var3 = 0L;
      boolean var5 = false;

      while(aI) {
         var1 = System.currentTimeMillis();
         if (!this.ar) {
            ++this.e;
            if (this.aZ) {
               ++this.bE;
            }

            this.repaint();
            this.serviceRepaints();
         }

         var3 = System.currentTimeMillis();

         try {
            Thread.sleep(100L - (var3 - var1) > 0L ? 100L - (var3 - var1) : 0L);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

   }

   private Image if(String var1) {
      try {
         return Image.createImage(String.valueOf((new StringBuffer("/")).append(var1).append(".png")));
      } catch (Exception var3) {
         System.out.println("load image " + var1 + " error !");
         return null;
      }
   }

   private void o() {
      if (null == this.d8) {
         this.d8 = this.if("sp");
      }

      if (null == this.av) {
         this.av = this.if("flash");
      }

      ++this.bz;
      if (this.bz < 10) {
         this.cO.setColor(255, 255, 255);
         this.cO.fillRect(0, 0, d1, cm);
         this.cO.drawImage(this.av, (d1 - this.av.getWidth()) / 2, (cm - this.av.getHeight()) / 2, 0);
      } else if (this.bz < 16) {
         this.cO.setColor(255, 255, 255);
         this.cO.fillRect(0, 0, d1, cm);
         this.cO.drawImage(this.d8, (d1 - this.d8.getWidth()) / 2, (cm - this.d8.getHeight()) / 2, 0);
      } else {
         this.bz = 0;
         this.d8 = null;
         this.av = null;
         ++this.b0;
      }

   }

   private void d() {
      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      if (this.bX == null) {
         this.bX = this.if("LOGO1");
      }

      if (this.bQ == null) {
         this.bQ = this.if("LOGO");
      }

      if (this.dG == null) {
         this.dG = this.if("logore");
      }

      this.cO.drawRegion(this.bQ, 0, 0, 240, 320, 0, 0, 0, 0);
      this.int(4);
      this.a(0);
      this.new(0);
      this.cO.setColor(255, 255, 255);
      this.cO.setFont(dD);
      if (this.e / 20 % 2 == 0 && this.bZ == 1) {
         if (this.bZ == 1) {
            this.cO.drawRegion(this.bQ, 240, 0, 240, 320, 0, 0, 0, 0);
            this.bZ = 0;
         }
      } else if (this.e / 10 % 2 == 0 && ++this.bj / 10 % 2 == 0) {
         this.cO.drawRegion(this.bQ, 240, 0, 240, 320, 0, 0, 0, 0);
      }

      this.cO.drawImage(this.dG, (d1 - 111) / 2, cm - 27 - 15, 0);
      this.cO.drawImage(this.bX, (d1 - 148) / 2, 10, 0);
   }

   private void l() {
      this.a8.try = true;
      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      if (this.bO == 0) {
         if (this.aT == null) {
            this.aT = this.if("menuback");
         }

         if (this.dT == null) {
            this.dT = this.if("caidan");
         }

         if (this.au == null) {
            this.au = this.if("shou");
         }

         this.aU = null;
         this.cO.setClip(0, 0, d1, cm);
         this.cO.drawImage(this.aT, (d1 - this.aT.getWidth()) / 2, 0, 0);
         this.int(4);
         this.a(0);
         this.new(0);
         this.cO.setFont(dD);
         if (this.dO < 73) {
            this.cO.drawRegion(this.dT, 0, 0, 136, 186, 0, (d1 - 136) / 2, -40 + (this.dO += 6), 0);
         } else {
            this.cO.drawRegion(this.dT, 0, 0, 136, 186, 0, (d1 - 136) / 2, 33, 0);
            this.dO = 208;
         }

         if (this.dO > 73 && !this.cv) {
            if (this.cn < 36) {
               this.cO.drawImage(this.au, 240 - (this.cn += 4), 46, 0);
            } else {
               this.cv = true;
            }
         }

         if (this.dO > 73 && this.cv) {
            switch(this.dV) {
            case 0:
               this.cO.drawImage(this.au, 200, 46, 0);
               this.cO.drawRegion(this.dT, 136, 7, 94, 26, 0, 72, 40, 0);
               break;
            case 1:
               this.cO.drawImage(this.au, 200, 83, 0);
               this.cO.drawRegion(this.dT, 136, 43, 94, 26, 0, 72, 76, 0);
               break;
            case 2:
               this.cO.drawImage(this.au, 200, 121, 0);
               this.cO.drawRegion(this.dT, 136, 80, 94, 26, 0, 72, 113, 0);
               break;
            case 3:
               this.cO.drawImage(this.au, 200, 157, 0);
               this.cO.drawRegion(this.dT, 136, 118, 94, 26, 0, 72, 151, 0);
               break;
            case 4:
               this.cO.drawImage(this.au, 200, 195, 0);
               this.cO.drawRegion(this.dT, 136, 154, 94, 26, 0, 72, 187, 0);
            }
         }
      } else if (this.bO == 2) {
         this.b();
      } else if (this.bO == 3) {
         this.case();
      } else if (this.bO == 1) {
         this.goto();
      }

   }

   private void m() {
      this.cO.setColor(0);
      this.cO.fillRect(0, 0, d1, cm);
      if (this.cR > cm - 6 * this.di - this.di) {
         if (this.dx == null) {
         }
      } else if (this.cR <= cm - 6 * this.di - this.di && this.cR > cm - 18 * this.di - this.di) {
         this.dx = null;
         System.gc();
         if (this.dw == null) {
         }
      } else {
         this.dN = null;
         this.aO = null;
         this.ee = true;
         this.dw = null;
         if (this.dv == null) {
         }

         switch(this.if) {
         case 0:
            this.do("Эмма камня, как в передней части, выше, крэк,");
            break;
         case 1:
            this.do("Достаточно быть мечом в трещины, как меч? Трудные");
            break;
         case 2:
            this.do("Дорожная она имеет ничего общего с этой скалы, Эмма будет на меч");
            break;
         case 3:
            this.do("Добавлена в трещины после громкий шум, открыл дверь дворца ... ...");
         }
      }

   }

   private void v() {
      if (this.dN == null) {
         this.dN = this.if("jindu");
      }

      if (this.aO == null) {
         this.aO = this.if("jindu1");
      }

      this.cO.drawImage(this.dN, (d1 - 176) / 2, 0, 0);
      this.cO.setColor(0, 0, 0);
      this.cO.drawImage(this.aO, (d1 - 113) / 2, (cm - 13) / 2 + 70, 0);
      this.cO.fillRect((d1 - 113) / 2 - this.dd++, (cm - 13) / 2 + 70, 113, 15);
      this.cO.fillRect((d1 - 113) / 2 + this.c3++, (cm - 13) / 2 + 70, 113, 15);
   }

   private int do(String[] var1, int var2, int var3, int var4, int var5, Font var6) {
      int var7 = var4;
      byte var8 = 1;
      this.cO.setFont(var6);
      if (var4 <= 0) {
         var7 = 0;
      }

      if (var7 >= var1.length - 1) {
         var7 = var1.length - 1;
      }

      for(int var9 = 0; var9 < var5 && var9 + var7 < var1.length; ++var9) {
         if (var3 + var9 * (dD.getHeight() + var8) < 124 - this.di) {
            this.cO.setColor(0, 0, 0);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 125 - this.di) {
            this.cO.setColor(67, 67, 66);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 127 - this.di) {
            this.cO.setColor(131, 131, 129);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 129 - this.di) {
            this.cO.setColor(148, 147, 146);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 131 - this.di) {
            this.cO.setColor(163, 162, 160);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 133 - this.di) {
            this.cO.setColor(179, 179, 177);
         } else if (var3 + var9 * (dD.getHeight() + var8) < 135 - this.di) {
            this.cO.setColor(197, 196, 192);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 - 9 - this.di) {
            this.cO.setColor(246, 245, 242);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 - 7 - this.di) {
            this.cO.setColor(183, 183, 179);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 - 5 - this.di) {
            this.cO.setColor(152, 152, 150);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 - 3 - this.di) {
            this.cO.setColor(152, 152, 150);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 - 1 - this.di) {
            this.cO.setColor(130, 130, 127);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 1 - this.di) {
            this.cO.setColor(130, 130, 127);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 3 - this.di) {
            this.cO.setColor(90, 90, 89);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 5 - this.di) {
            this.cO.setColor(80, 80, 78);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 7 - this.di) {
            this.cO.setColor(80, 80, 78);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 9 - this.di) {
            this.cO.setColor(66, 66, 65);
         } else if (var3 + var9 * (dD.getHeight() + var8) < cm - 12 + 11 - this.di) {
            this.cO.setColor(66, 66, 65);
         }

         this.cO.drawString(var1[var9 + var7], var2, var3 + var9 * (dD.getHeight() + var8), 0);
      }

      return var7;
   }

   private void case() {
      try {
         if (this.eh == null) {
            String var1 = "          ПОМОЩЬ \n Перед  вами  классичес  кая игра/квест о загадочном приключении девуш ки Шизуко в чужом  странном доме.Помогите бедняжке с честью избавить  ся от кошмаров и ужасов злоключений.";
            var1 = var1 + "\n        УПРАВЛЕНИЕ \n";
            var1 = var1 + "\"2\",\"4\",\"6\",\"8\"- перемещение по дому \n";
            var1 = var1 + "\"5\"- выбор действия \n ";
            var1 = var1 + "\"1\"- перечень предметов для использования \n";
            var1 = var1 + "Правый \"soft\"-  вызов     меню игры \n";
            var1 = var1 + "Левый \"soft\"- отмена действий \n";
            var1 = var1 + "В   ИГРЕ  ОТСУТСТВУЕТ        СОХРАНЕНИЕ \n";
            this.eh = this.a(var1, d1 - 20, dD);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.z();
      this.cO.setFont(dD);
      this.cO.setColor(100, 100, 255);
      this.dA = this.for(this.eh, 10, 30, this.dA, 11, dD);
   }

   private void b() {
      this.cO.setColor(1, 1, 1);
      this.cO.fillRect(0, 0, d1, cm);
      if (this.au == null) {
         this.au = this.if("shou");
      }

      this.z();
      this.cO.setFont(dD);
      this.cO.setColor(100, 100, 255);
      if (a.do) {
         this.cO.setColor(255, 255, 255);
         this.a("Звук:  ДА", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 - 18, 0);
         this.cO.drawImage(this.au, (d1 - dD.stringWidth("Звук:  ДА")) / 2 + 68 + 32, cm / 2 - 16, 0);
         this.cO.setColor(200, 200, 200);
         this.a("Звук:  НЕТ", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 + 8, 0);
      } else {
         this.cO.setColor(200, 200, 200);
         this.a("Звук:  ДА", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 - 18, 0);
         this.cO.setColor(255, 255, 255);
         this.a("Звук:  НЕТ", (d1 - dD.stringWidth("Звук:  ДА")) / 2, cm / 2 + 8, 0);
         this.cO.drawImage(this.au, (d1 - dD.stringWidth("Звук:  ДА")) / 2 + 68 + 32, cm / 2 + 8, 0);
         this.do(0);
      }

   }

   private void a(Graphics var1, Image var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      if (var2 != null) {
         int var10 = var2.getWidth();
         int var11 = var2.getHeight();

         for(int var12 = 0; var12 < var11; ++var12) {
            var1.setClip(var8, var9 + var12, var10, 1);
            var1.drawRegion(var2, var3, var4, var5, var6, var7, var8, var9 - var11 + 2 * var12, 20);
         }

      }
   }

   private void z() {
      if (this.as == null) {
         this.as = this.if("lingjian");
      }

      this.cO.drawImage(this.aT, (d1 - this.aT.getWidth()) / 2, 0, 0);
      this.cO.drawRegion(this.as, 0, 0, 19, 20, 2, 2, 2, 0);
      this.cO.drawRegion(this.as, 0, 0, 19, 20, 0, d1 - 21, 2, 0);
      this.cO.drawRegion(this.as, 0, 0, 19, 20, 4, 2, 298, 0);
      this.cO.drawRegion(this.as, 19, 2, 5, 17, 0, 4, 23, 0);
      this.cO.drawRegion(this.as, 19, 2, 5, 17, 0, 232, 23, 0);
      this.a(this.cO, this.as, 0, 0, 19, 20, 0, d1 - 21, 297);
      this.a(this.cO, this.as, 19, 2, 5, 17, 0, 4, 282);
      this.a(this.cO, this.as, 19, 2, 5, 17, 0, 232, 282);
      this.cO.setClip(0, 0, d1, cm);

      int var1;
      for(var1 = 0; var1 < 28; ++var1) {
         this.cO.drawRegion(this.as, 20, 10, 3, 9, 0, 5, 40 + var1 * 9, 0);
         this.cO.drawRegion(this.as, 20, 10, 3, 9, 0, 233, 40 + var1 * 9, 0);
      }

      if (!this.dh) {
         if (this.cy < 126) {
            this.cy += 6;
         } else {
            this.dh = true;
         }
      } else {
         this.cy -= 6;
         if (this.cy == 0) {
            this.dh = false;
         }
      }

      this.cO.drawRegion(this.as, 24, 17, 11, 2, 0, 229, 56 + this.cy, 0);
      this.cO.drawRegion(this.as, 24, 17, 11, 2, 0, 1, 153 - this.cy, 0);

      for(var1 = 0; var1 < 40; ++var1) {
         this.cO.drawRegion(this.as, 0, 2, 5, 4, 0, 21 + var1 * 5, 4, 0);
         this.cO.drawRegion(this.as, 0, 2, 5, 4, 0, 21 + var1 * 5, 312, 0);
      }

   }

   private void goto() {
      try {
         if (this.eh == null) {
            String var1 = "  Название игры: \n     \"Дом №13\" \n";
            var1 = var1 + "         v1.0 \n   Перевод V.P.K \n        E-mail: \n vova2const@mail.ru \n ";
            var1 = var1 + "    Разработчик: \n    Vendor, Китай \n    E-mail: game \n  0311kf@126.com";
            this.eh = this.a(var1, d1 - 50, dD);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.z();
      this.cO.setFont(dD);
      this.cO.setColor(100, 100, 255);
      this.dA = this.for(this.eh, 28, 80, 0, V, dD);
   }

   private void a(String var1, int var2, int var3, int var4) {
      this.cO.setColor(149, 147, 144);
      this.cO.drawString(var1, var2 + 1, var3, 0);
      this.cO.drawString(var1, var2 - 1, var3, 0);
      this.cO.drawString(var1, var2, var3 + 1, 0);
      this.cO.drawString(var1, var2, var3 - 1, 0);
      this.cO.setColor(255, 255, 255);
      this.cO.drawString(var1, var2, var3, 0);
   }

   private int for(String[] var1, int var2, int var3, int var4, int var5, Font var6) {
      int var7 = var4;
      byte var8 = 2;
      this.cO.setFont(var6);
      if (var4 <= 0) {
         var7 = 0;
      }

      if (var7 >= var1.length - 1 - 2) {
         var7 = var1.length - 1 - 2;
      }

      this.cO.setColor(255, 255, 255);

      for(int var9 = 0; var9 < var5 && var9 + var7 < var1.length; ++var9) {
         this.a(var1[var9 + var7], var2, var3 + var9 * (dD.getHeight() + var8), 0);
      }

      return var7;
   }

   private int a(String[] var1, int var2, int var3, int var4, int var5, Font var6) {
      int var7 = var4;
      byte var8 = 2;
      this.cO.setFont(var6);
      if (var4 <= 0) {
         var7 = 0;
      }

      if (var7 >= var1.length - 1 - 2) {
         var7 = var1.length - 1 - 2;
      }

      this.cO.setColor(255, 255, 255);

      for(int var9 = 0; var9 < var5 && var9 + var7 < var1.length; ++var9) {
         this.cO.drawString(var1[var9 + var7], var2, var3 + var9 * (dD.getHeight() + var8), 0);
      }

      return var7;
   }

   private int if(String[] var1, int var2, int var3, int var4, int var5, Font var6) {
      int var7 = var4;
      byte var8 = 2;
      this.cO.setFont(var6);
      if (var4 <= 0) {
         var7 = 0;
      }

      if (var7 >= var1.length - 1) {
         var7 = var1.length - 1;
      }

      this.cO.setColor(255, 255, 255);

      for(int var9 = 0; var9 < var5 && var9 + var7 < var1.length; ++var9) {
         this.cO.drawString(var1[var9 + var7], var2, var3 + var9 * (dD.getHeight() + var8), 0);
      }

      return var7;
   }

   public void byte() {
      this.bZ = 1;
      this.bj = 0;
      this.eh = null;
      this.bQ = null;
      this.dG = null;
      this.bX = null;
      this.aT = null;
      this.as = null;
      this.dO = 0;
      this.cn = 0;
      this.cv = false;
      this.dT = null;
      this.au = null;
      System.gc();
   }

   private void f() {
      this.cI = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 0, 240, 190}, {31, 120, 67, 82}, {0, 300, 133, 20}, {188, 300, 52, 20}, {0, 315, 240, 5}};
      this.cG = new int[][]{{232, 0, 8, 320}, {0, 300, 133, 20}, {188, 300, 52, 20}, {0, 0, 240, 133}, {0, 0, 8, 176}, {0, 240, 8, 80}, {0, 300, 133, 20}, {182, 108, 32, 48}, {120, 195, 78, 24}, {134, 219, 47, 7}, {0, 315, 240, 5}};
      this.cF = new int[][]{{0, 292, 240, 28}, {0, 0, 175, 161}, {175, 0, 7, 179}, {232, 0, 8, 179}};
      this.cD = new int[][]{{0, 292, 240, 28}, {0, 0, 240, 161}, {0, 0, 8, 320}};
      this.cB = new int[][]{{0, 0, 240, 161}, {0, 292, 175, 28}, {175, 280, 7, 40}, {232, 280, 8, 40}};
      this.cA = new int[][]{{0, 292, 240, 28}, {0, 0, 240, 161}, {0, 0, 8, 320}};
      this.cz = new int[][]{{0, 292, 240, 28}, {0, 0, 240, 161}, {0, 277, 8, 43}};
      this.cx = new int[][]{{0, 292, 240, 28}, {0, 0, 240, 161}, {232, 0, 8, 320}};
      this.cw = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 300, 45, 20}, {100, 300, 140, 20}, {141, 271, 99, 49}, {0, 0, 240, 130}, {0, 0, 90, 170}, {112, 117, 29, 52}, {168, 117, 29, 53}, {142, 124, 25, 24}};
      this.cu = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 0, 240, 127}, {8, 98, 57, 43}, {116, 94, 62, 47}, {182, 112, 50, 32}, {25, 191, 62, 37}, {0, 300, 176, 20}};
      this.c0 = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 0, 240, 146}, {8, 127, 25, 71}, {103, 129, 55, 38}, {202, 157, 38, 22}, {183, 280, 57, 40}, {52, 300, 188, 20}, {0, 315, 240, 5}};
      this.cZ = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 0, 240, 152}, {22, 132, 32, 42}, {89, 132, 118, 35}, {0, 300, 114, 20}, {169, 300, 71, 20}};
      this.cY = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 300, 92, 20}, {146, 300, 94, 20}, {0, 0, 240, 129}, {13, 118, 57, 36}, {103, 91, 37, 54}, {171, 103, 35, 42}};
      this.cX = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 300, 70, 20}, {136, 300, 104, 20}, {0, 315, 240, 5}, {8, 202, 18, 86}, {180, 242, 42, 33}, {0, 0, 240, 113}, {37, 79, 62, 48}, {131, 104, 44, 41}, {190, 81, 31, 46}};
      this.cW = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 0, 240, 119}, {51, 113, 19, 27}, {0, 300, 180, 20}, {43, 177, 29, 53}, {95, 177, 44, 53}, {171, 177, 28, 53}};
      this.cV = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 300, 79, 20}, {142, 300, 98, 20}, {0, 0, 240, 114}, {35, 113, 43, 37}, {103, 84, 108, 44}, {174, 224, 42, 31}};
      this.cU = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 315, 240, 5}, {0, 300, 91, 20}, {146, 300, 94, 20}, {0, 0, 240, 111}, {17, 102, 60, 61}, {176, 101, 48, 20}, {200, 114, 16, 19}, {159, 172, 63, 38}, {216, 238, 24, 33}, {8, 238, 18, 82}, {115, 104, 43, 28}};
      this.cT = new int[][]{{0, 0, 8, 320}, {232, 0, 8, 320}, {0, 300, 240, 20}, {0, 0, 240, 112}, {0, 0, 41, 217}, {8, 78, 103, 51}, {196, 100, 33, 31}, {8, 254, 32, 32}, {68, 270, 76, 17}, {198, 258, 42, 26}};
   }

   private void y() {
      this.cO.setColor(255, 255, 255);
      int var1;
      if (this.ce == 0) {
         for(var1 = 0; var1 < this.cI.length; ++var1) {
            this.cO.drawRect(this.cI[var1][0], this.cI[var1][1], this.cI[var1][2], this.cI[var1][3]);
         }
      }

      if (this.ce == 1) {
         for(var1 = 0; var1 < this.cG.length; ++var1) {
            this.cO.drawRect(this.cG[var1][0], this.cG[var1][1], this.cG[var1][2], this.cG[var1][3]);
         }
      }

      if (this.ce == 2) {
         for(var1 = 0; var1 < this.cF.length; ++var1) {
            this.cO.drawRect(this.cF[var1][0], this.cF[var1][1], this.cF[var1][2], this.cF[var1][3]);
         }
      }

      if (this.ce == 3) {
         for(var1 = 0; var1 < this.cD.length; ++var1) {
            this.cO.drawRect(this.cD[var1][0], this.cD[var1][1], this.cD[var1][2], this.cD[var1][3]);
         }
      }

      if (this.ce == 4) {
         for(var1 = 0; var1 < this.cB.length; ++var1) {
            this.cO.drawRect(this.cB[var1][0], this.cB[var1][1], this.cB[var1][2], this.cB[var1][3]);
         }
      }

      if (this.ce == 5) {
         for(var1 = 0; var1 < this.cA.length; ++var1) {
            this.cO.drawRect(this.cA[var1][0], this.cA[var1][1], this.cA[var1][2], this.cA[var1][3]);
         }
      }

      if (this.ce == 6) {
         for(var1 = 0; var1 < this.cz.length; ++var1) {
            this.cO.drawRect(this.cz[var1][0], this.cz[var1][1], this.cz[var1][2], this.cz[var1][3]);
         }
      }

      if (this.ce == 7) {
         for(var1 = 0; var1 < this.cx.length; ++var1) {
            this.cO.drawRect(this.cx[var1][0], this.cx[var1][1], this.cx[var1][2], this.cx[var1][3]);
         }
      }

      if (this.ce == 8) {
         for(var1 = 0; var1 < this.cw.length; ++var1) {
            this.cO.drawRect(this.cw[var1][0], this.cw[var1][1], this.cw[var1][2], this.cw[var1][3]);
         }
      }

      if (this.ce == 9) {
         for(var1 = 0; var1 < this.cu.length; ++var1) {
            this.cO.drawRect(this.cu[var1][0], this.cu[var1][1], this.cu[var1][2], this.cu[var1][3]);
         }
      }

      if (this.ce == 10) {
         for(var1 = 0; var1 < this.c0.length; ++var1) {
            this.cO.drawRect(this.c0[var1][0], this.c0[var1][1], this.c0[var1][2], this.c0[var1][3]);
         }
      }

      if (this.ce == 11) {
         for(var1 = 0; var1 < this.cZ.length; ++var1) {
            this.cO.drawRect(this.cZ[var1][0], this.cZ[var1][1], this.cZ[var1][2], this.cZ[var1][3]);
         }
      }

      if (this.ce == 12) {
         for(var1 = 0; var1 < this.cY.length; ++var1) {
            this.cO.drawRect(this.cY[var1][0], this.cY[var1][1], this.cY[var1][2], this.cY[var1][3]);
         }
      }

      if (this.ce == 13) {
         for(var1 = 0; var1 < this.cX.length; ++var1) {
            this.cO.drawRect(this.cX[var1][0], this.cX[var1][1], this.cX[var1][2], this.cX[var1][3]);
         }
      }

      if (this.ce == 14) {
         for(var1 = 0; var1 < this.cW.length; ++var1) {
            this.cO.drawRect(this.cW[var1][0], this.cW[var1][1], this.cW[var1][2], this.cW[var1][3]);
         }
      }

      if (this.ce == 15) {
         for(var1 = 0; var1 < this.cV.length; ++var1) {
            this.cO.drawRect(this.cV[var1][0], this.cV[var1][1], this.cV[var1][2], this.cV[var1][3]);
         }
      }

      if (this.ce == 16) {
         for(var1 = 0; var1 < this.cU.length; ++var1) {
            this.cO.drawRect(this.cU[var1][0], this.cU[var1][1], this.cU[var1][2], this.cU[var1][3]);
         }
      }

      if (this.ce == 17) {
         for(var1 = 0; var1 < this.cT.length; ++var1) {
            this.cO.drawRect(this.cT[var1][0], this.cT[var1][1], this.cT[var1][2], this.cT[var1][3]);
         }
      }

   }

   private void p() {
      c var10000;
      int var1;
      int[] var2;
      if (this.ce == 0) {
         for(var1 = 0; var1 < this.cI.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cI[var1][0], this.cI[var1][1], this.cI[var1][2], this.cI[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 1) {
         for(var1 = 0; var1 < this.cG.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cG[var1][0], this.cG[var1][1], this.cG[var1][2], this.cG[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 2) {
         for(var1 = 0; var1 < this.cF.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cF[var1][0], this.cF[var1][1], this.cF[var1][2], this.cF[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 3) {
         for(var1 = 0; var1 < this.cD.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cD[var1][0], this.cD[var1][1], this.cD[var1][2], this.cD[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 4) {
         for(var1 = 0; var1 < this.cB.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cB[var1][0], this.cB[var1][1], this.cB[var1][2], this.cB[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 5) {
         for(var1 = 0; var1 < this.cA.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cA[var1][0], this.cA[var1][1], this.cA[var1][2], this.cA[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 6) {
         for(var1 = 0; var1 < this.cz.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cz[var1][0], this.cz[var1][1], this.cz[var1][2], this.cz[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 7) {
         for(var1 = 0; var1 < this.cx.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cx[var1][0], this.cx[var1][1], this.cx[var1][2], this.cx[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 8) {
         for(var1 = 0; var1 < this.cw.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cw[var1][0], this.cw[var1][1], this.cw[var1][2], this.cw[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 9) {
         for(var1 = 0; var1 < this.cu.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cu[var1][0], this.cu[var1][1], this.cu[var1][2], this.cu[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 10) {
         for(var1 = 0; var1 < this.c0.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.c0[var1][0], this.c0[var1][1], this.c0[var1][2], this.c0[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 11) {
         for(var1 = 0; var1 < this.cZ.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cZ[var1][0], this.cZ[var1][1], this.cZ[var1][2], this.cZ[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 12) {
         for(var1 = 0; var1 < this.cY.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cY[var1][0], this.cY[var1][1], this.cY[var1][2], this.cY[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 13) {
         for(var1 = 0; var1 < this.cX.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cX[var1][0], this.cX[var1][1], this.cX[var1][2], this.cX[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 14) {
         for(var1 = 0; var1 < this.cW.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cW[var1][0], this.cW[var1][1], this.cW[var1][2], this.cW[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 15) {
         for(var1 = 0; var1 < this.cV.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cV[var1][0], this.cV[var1][1], this.cV[var1][2], this.cV[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 16) {
         for(var1 = 0; var1 < this.cU.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cU[var1][0], this.cU[var1][1], this.cU[var1][2], this.cU[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

      if (this.ce == 17) {
         for(var1 = 0; var1 < this.cT.length; ++var1) {
            this.a8.int = 25;
            this.a8.long = 5;
            var2 = new int[]{this.cT[var1][0], this.cT[var1][1], this.cT[var1][2], this.cT[var1][3]};
            this.a8.a();
            if (this.a8.a(var2)) {
               var10000 = this.a8;
               var10000.j -= this.a8.else;
               var10000 = this.a8;
               var10000.i -= this.a8.char;
               this.a8.else = 0;
               this.a8.char = 0;
               this.r = true;
            }

            var10000 = this.a8;
            var10000.j -= this.a8.else;
            var10000 = this.a8;
            var10000.i -= this.a8.char;
         }
      }

   }

   private void long() {
      int var1 = this.a8.j;
      int var2 = this.a8.i;
      if (this.ce == 4) {
         if (var1 > -20 && var1 < -3 && var2 > 0 && var2 < cm && !this.cP) {
            this.ce = 5;
            this.a8.j = var1 + 228;
            this.a8.i = var2;
            return;
         }

         if (var1 > 232 && var1 < 240 && var2 > 0 && var2 < cm && !this.cP) {
            this.ce = 6;
            this.a8.j = var1 - 228;
            this.a8.i = var2;
            return;
         }

         if (var2 > 300 && var2 < cm && !this.cP) {
            this.ce = 2;
            this.a8.j = var1;
            this.a8.i = 5;
            return;
         }
      }

      if (this.ce == 2 && var1 > -20 && var1 < -3 && var2 > 0 && var2 < cm && !this.cP) {
         this.ce = 3;
         this.a8.j = var1 + 228;
         this.a8.i = var2;
      } else if (this.ce == 1 && var1 > -20 && var1 < -3 && !this.cP) {
         this.ce = 2;
         this.a8.j = 203;
         this.a8.i = 193;
      } else {
         if (this.ce == 2) {
            if (var1 > 232 && var1 < 240 && !this.cP) {
               if (this.N == null) {
                  this.N = this.if("back2");
               }

               this.ce = 1;
               this.S = 0;
               this.a8.j = 3;
               this.a8.i = 193;
               return;
            }

            if (var2 > -20 && var2 < 0 && !this.cP) {
               this.ce = 4;
               this.a8.j = var1;
               this.a8.i = 280;
               return;
            }
         }

         if (this.ce == 3 && var1 > 232 && var1 < 240 && var2 > 0 && var2 < cm && !this.cP) {
            this.ce = 2;
            this.a8.j = var1 - 228;
            this.a8.i = var2;
         } else if (this.ce == 5 && var1 > 232 && var1 < 240 && var2 > 0 && var2 < cm && !this.cP) {
            this.ce = 4;
            this.a8.j = var1 - 228;
            this.a8.i = var2;
         } else {
            if (this.ce == 6) {
               if (var1 > -20 && var1 < -3 && var2 > 0 && var2 < cm && !this.cP) {
                  this.ce = 4;
                  this.a8.j = var1 + 228;
                  this.a8.i = var2;
                  return;
               }

               if (var1 > 232 && var1 < 240 && var2 > 0 && var2 < cm && !this.cP) {
                  this.ce = 7;
                  this.a8.j = var1 - 228;
                  this.a8.i = var2;
                  return;
               }
            }

            if (this.ce == 7 && var1 > -20 && var1 < -3 && var2 > 0 && var2 < cm && !this.cP) {
               this.ce = 6;
               this.a8.j = var1 + 228;
               this.a8.i = var2;
            } else {
               this.p();
               this.a8.a();
               if (this.cP) {
                  this.a8.goto = 0;
               }

               this.a8.a(this.cO);
               if (this.aQ[15] != 10) {
                  this.null();
               }

               if (this.ce == 14) {
                  if (this.aa == null) {
                     this.aa = this.if("dmap");
                  }

                  if (this.dy == null) {
                     this.dy = this.if("fanhui");
                  }

                  if (this.ai) {
                     this.cO.drawImage(this.aa, (d1 - 81) / 2, (cm - 71) / 2 + 13, 0);
                     this.cO.drawImage(this.dy, 0, 310, 0);
                     if (this.e / 6 % 2 == 0) {
                        this.cO.setColor(255, 0, 0);
                        this.cO.drawArc(112, 177, 4, 4, 0, 360);
                        this.cO.setColor(255, 255, 255);
                        this.cO.fillRect(114, 179, 1, 1);
                     }
                  }
               }

               if (this.dp == 3) {
                  this.cO.setColor(0, 0, 0);
                  if (this.a4 < 40) {
                     this.a4 += 3;
                  }

                  if (this.a4 >= 4 && this.a3 < 40) {
                     this.a3 += 3;
                  }

                  if (this.a3 >= 5 && this.a2 < 40) {
                     this.a2 += 3;
                  }

                  if (this.a2 >= 4 && this.a1 < 40) {
                     this.a1 += 3;
                  }

                  if (this.a1 >= 5 && this.a0 < 40) {
                     this.a0 += 3;
                  }

                  if (this.a0 >= 4 && this.aX < 40) {
                     this.aX += 3;
                  }

                  if (this.aX >= 5 && this.aW < 40) {
                     this.aW += 3;
                  }

                  if (this.aW >= 4 && this.aV < 40) {
                     this.aV += 3;
                  }

                  if (this.aV >= 40) {
                     this.cK = true;
                  }

                  this.cO.fillRect(0, 0 + this.a4, 240, 40 - this.a4);
                  this.cO.fillRect(0, 40 + this.a3, 240, 40 - this.a3);
                  this.cO.fillRect(0, 80 + this.a2, 240, 40 - this.a2);
                  this.cO.fillRect(0, 120 + this.a1, 240, 40 - this.a1);
                  this.cO.fillRect(0, 160 + this.a0, 240, 40 - this.a0);
                  this.cO.fillRect(0, 200 + this.aX, 240, 40 - this.aX);
                  this.cO.fillRect(0, 240 + this.aW, 240, 40 - this.aW);
                  this.cO.fillRect(0, 280 + this.aV, 240, 40 - this.aV);
                  if (!this.cK) {
                     this.a8.j = (d1 - this.a8.h[this.a8.goto][2]) / 2 + 40 + 3;
                     this.a8.i = cm - 80;
                     this.a8.goto = 0;
                  }

                  if (this.cK) {
                     this.cl = true;
                  }
               }

            }
         }
      }
   }

   private void i() {
      if (this.au == null) {
         this.au = this.if("shou");
      }

      if (this.aM == null) {
         this.aM = this.if("caidan1");
      }

      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      this.cO.drawRegion(this.aM, 0, 0, 136, 149, 0, (d1 - 136) / 2, (cm - 149) / 2, 0);
      switch(o) {
      case 0:
         this.cO.drawRegion(this.aM, 136, 6, 94, 26, 0, 74, 92, 0);
         this.cO.drawImage(this.au, 193, 97, 0);
         break;
      case 1:
         this.cO.drawRegion(this.aM, 136, 44, 94, 26, 0, 74, 130, 0);
         this.cO.drawImage(this.au, 193, 136, 0);
         break;
      case 2:
         this.cO.drawRegion(this.aM, 136, 80, 94, 26, 0, 74, 166, 0);
         this.cO.drawImage(this.au, 193, 172, 0);
         break;
      case 3:
         this.cO.drawRegion(this.aM, 136, 116, 94, 26, 0, 74, 202, 0);
         this.cO.drawImage(this.au, 193, 208, 0);
      }

   }

   private void else() {
      if (!this.goto) {
         this.m();
         if (!this.ee) {
            if (!this.byte) {
               this.h();
               if (this.z >= 280) {
                  this.byte = true;
               }
            } else {
               this.do();
               if (this.bE > 40) {
                  this.v();
                  if (this.dd == 115) {
                     this.aZ = false;
                     this.bQ = null;
                     this.aT = null;
                     this.as = null;
                     this.goto = true;
                     this.dd = 113;
                     this.c3 = 113;
                  }
               }
            }
         }
      } else {
         this.cO.setColor(0);
         this.cO.fillRect(0, 0, d1, cm);
         if (this.dc == 1) {
            this.int(0);
            this.a(4);
            this.new(4);
            this.w();
         }

         this.void();
         if (this.dt) {
            this.j();
         }

         if (this.aR) {
            this.i();
         }
      }

      int var1;
      if (this.bf.length() > 0 && !this.aR) {
         for(var1 = 0; var1 < this.dL.length; ++var1) {
            this.cO.setColor(this.dL[var1][0], this.dL[var1][1], this.dL[var1][2]);
         }

         this.cO.setColor(0, 0, 0);
         if (!this.a6) {
            this.cO.fillRect(0, cm - 50, d1, 66);
         } else if (!this.d4) {
            this.cO.fillRect(0, cm - 60, d1, 66);
         } else {
            this.cO.fillRect(0, cm - 60 + 10, d1, 51);
         }

         this.do(this.bf);
      }

      if (this.m.length() > 0 && !this.aR) {
         for(var1 = 0; var1 < this.dL.length; ++var1) {
            this.cO.setColor(this.dL[var1][0], this.dL[var1][1], this.dL[var1][2]);
         }

         this.cO.setColor(0, 0, 0);
         this.cO.fillRect(0, cm - 60 + 10, d1, 51);
         this.a(this.m);
      }

      if (this.H) {
         this.g();
      }

      if (this.du) {
         this.int();
      }

   }

   private void a() {
      this.dN = null;
      this.aO = null;
      switch(this.ce) {
      case 0:
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.O == null) {
            this.O = this.if("back1");
         }

         if (this.O != null) {
            this.cO.drawImage(this.O, 0, 0, 0);
         }
         break;
      case 1:
         this.O = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.N == null) {
            this.N = this.if("back2");
         }

         if (this.N != null) {
            this.cO.drawImage(this.N, 0, 0, 0);
         }
         break;
      case 2:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 3:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 4:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 5:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 6:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 7:
         this.O = null;
         this.N = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.M == null) {
            this.M = this.if("back3");
         }

         if (this.M != null) {
            this.cO.drawImage(this.M, 0, 0, 0);
         }
         break;
      case 8:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.G == null) {
            this.G = this.if("back8");
         }

         if (this.G != null) {
            this.cO.drawImage(this.G, 0, 0, 0);
         }
         break;
      case 9:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.F == null) {
            this.F = this.if("back9");
         }

         if (this.F != null) {
            this.cO.drawImage(this.F, 0, 0, 0);
         }
         break;
      case 10:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.aq == null) {
            this.aq = this.if("back10");
         }

         if (this.aq != null) {
            this.cO.drawImage(this.aq, 0, 0, 0);
         }
         break;
      case 11:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.ap == null) {
            this.ap = this.if("back11");
         }

         if (this.ap != null) {
            this.cO.drawImage(this.ap, 0, 0, 0);
         }
         break;
      case 12:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.ao == null) {
            this.ao = this.if("back12");
         }

         if (this.ao != null) {
            this.cO.drawImage(this.ao, 0, 0, 0);
         }
         break;
      case 13:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.al = null;
         this.ak = null;
         if (this.an == null) {
            this.an = this.if("back13");
         }

         if (this.an != null) {
            this.cO.drawImage(this.an, 0, 0, 0);
         }
         break;
      case 14:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.ak = null;
         if (this.al == null) {
            this.al = this.if("back14");
         }

         if (this.al != null) {
            this.cO.drawImage(this.al, 0, 0, 0);
         }
         break;
      case 15:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         if (this.ak == null) {
            this.ak = this.if("back15");
         }

         if (this.ak != null) {
            this.cO.drawImage(this.ak, 0, 0, 0);
         }
         break;
      case 16:
         this.O = null;
         this.N = null;
         this.M = null;
         this.J = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.L == null) {
            this.L = this.if("back4");
         }

         if (this.L != null) {
            this.cO.drawImage(this.L, 0, 0, 0);
         }
         break;
      case 17:
         this.O = null;
         this.N = null;
         this.M = null;
         this.L = null;
         this.G = null;
         this.F = null;
         this.aq = null;
         this.ap = null;
         this.ao = null;
         this.an = null;
         this.al = null;
         this.ak = null;
         if (this.J == null) {
            this.J = this.if("back5");
         }

         if (this.J != null) {
            this.cO.drawImage(this.J, 0, 0, 0);
         }
      }

   }

   private void char() {
      if (this.bM == null) {
         this.bM = this.if("jiantou");
      }

      switch(this.ce) {
      case 0:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 95, 193, 0);
         break;
      case 1:
         if (this.ce == 1) {
            this.cq = 1;
         }

         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 95, 193, 0);
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 2, -13, 110, 0);
         break;
      case 2:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 2, -13, 135, 0);
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 3:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 4:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 2, -13, 135, 0);
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 5:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 6:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 2, -13, 135, 0);
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 0, 164, 135, 0);
         break;
      case 7:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 2, -12, 135, 0);
         break;
      case 8:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 45, 195, 0);
         break;
      case 9:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 135, 195, 0);
         break;
      case 10:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 15, 195, 0);
         break;
      case 11:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 95, 195, 0);
         break;
      case 12:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 75, 195, 0);
         break;
      case 13:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 55, 195, 0);
         break;
      case 14:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 135, 195, 0);
         break;
      case 15:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 75, 195, 0);
         break;
      case 16:
         this.cO.drawRegion(this.bM, 0, 0, 25, 26, 4, 75, 195, 0);
      }

   }

   private void n() {
      switch(this.ce) {
      case 0:
         this.b4 = null;
         this.bp = null;
         this.c1 = null;
         if (this.u == null) {
            this.u = this.if("kaigui");
         }

         if (this.aY == null) {
            this.aY = this.if("shouji");
         }
         break;
      case 1:
         this.u = null;
         this.aY = null;
         this.bu = null;
         this.else = null;
         this.B = null;
         this.cL = null;
         this.T = null;
         this.bb = null;
         if (this.b4 == null) {
            this.b4 = this.if("shaizi");
         }

         if (this.bp == null) {
            this.bp = this.if("gui2");
         }

         if (this.c1 == null) {
            this.c1 = this.if("shuyao");
         }
         break;
      case 2:
         this.C = null;
         this.b4 = null;
         this.bp = null;
         this.c1 = null;
         this.be = null;
         this.d9 = null;
         if (this.else == null) {
            this.else = this.if("lou");
         }

         if (this.B == null) {
            this.B = this.if("men");
         }

         if (this.cL == null) {
            this.cL = this.if("menkuang");
         }

         if (this.bu == null) {
            this.bu = this.if("paaizi");
         }

         if (this.T == null) {
            this.T = this.if("loub");
         }
         break;
      case 3:
         this.cN = null;
         this.aP = null;
         this.bh = null;
         this.X = null;
         this.aJ = null;
         if (this.U == null) {
            this.U = this.if("dong");
         }

         if (this.C == null) {
            this.C = this.if("kuangtpong");
         }
         break;
      case 4:
         this.ec = null;
         this.d5 = null;
         this.dk = null;
         this.ae = null;
         this.cH = null;
         break;
      case 5:
         this.cM = null;
         this.Q = null;
         break;
      case 6:
         this.b6 = null;
         break;
      case 7:
         this.br = null;
         this.aw = null;
         break;
      case 8:
         this.dJ = null;
         this.bY = null;
         this.dE = null;
         this.D = null;
         if (this.cM == null) {
            this.cM = this.if("gz");
         }

         if (this.Q == null) {
            this.Q = this.if("kamen");
         }
         break;
      case 9:
         if (this.b6 == null) {
            this.b6 = this.if("shuji");
         }
         break;
      case 10:
         this.b4 = null;
         this.bp = null;
         this.c1 = null;
         if (this.bb == null) {
            this.bb = this.if("weiyao");
         }
         break;
      case 11:
         this.C = null;
         if (this.cN == null) {
            this.cN = this.if("wei");
         }

         if (this.aP == null) {
            this.aP = this.if("guis");
         }

         if (this.bh == null) {
            this.bh = this.if("bang");
         }

         if (this.X == null) {
            this.X = this.if("shise");
         }
         break;
      case 12:
         if (this.br == null) {
            this.br = this.if("guika");
         }

         if (this.aw == null) {
            this.aw = this.if("qian");
         }
         break;
      case 13:
         if (this.ec == null) {
            this.ec = this.if("kguizi");
         }

         if (this.d5 == null) {
            this.d5 = this.if("shuzi");
         }

         if (this.dk == null) {
            this.dk = this.if("dang");
         }

         if (this.ae == null) {
            this.ae = this.if("chouti");
         }

         if (this.cH == null) {
            this.cH = this.if("sez");
         }
         break;
      case 14:
         if (this.be == null) {
            this.be = this.if("xmap");
         }

         if (this.d9 == null) {
            this.d9 = this.if("diban");
         }
         break;
      case 15:
         this.C = null;
         if (this.aJ == null) {
            this.aJ = this.if("nkai");
         }
         break;
      case 16:
         this.ay = null;
         this.cM = null;
         this.Q = null;
         if (this.dJ == null) {
            this.dJ = this.if("k");
         }

         if (this.bY == null) {
            this.bY = this.if("kc");
         }

         if (this.dE == null) {
            this.dE = this.if("s");
         }

         if (this.D == null) {
            this.D = this.if("sk");
         }
         break;
      case 17:
         this.dJ = null;
         this.bY = null;
         this.dE = null;
         this.D = null;
         if (this.ay == null) {
            this.ay = this.if("mao");
         }
      }

   }

   private void null() {
      switch(this.ce) {
      case 0:
         this.cO.drawRegion(this.O, 0, 292, 134, 28, 0, 0, 292, 0);
         this.cO.drawRegion(this.O, 186, 292, 54, 28, 0, 186, 292, 0);
         break;
      case 1:
         this.cO.drawRegion(this.N, 120, 184, 5, 14, 0, 120, 184, 0);
         this.cO.drawRegion(this.N, 134, 188, 48, 10, 0, 134, 188, 0);
         this.cO.drawRegion(this.N, 193, 184, 5, 14, 0, 193, 184, 0);
         this.cO.drawRegion(this.N, 0, 292, 134, 28, 0, 0, 292, 0);
         this.cO.drawRegion(this.N, 187, 292, 53, 28, 0, 187, 292, 0);
         if (this.try && this.aQ[15] == 0) {
            if (this.ci == null) {
               this.ci = this.if("fanhui");
            }

            this.cO.drawImage(this.ci, 0, 310, 0);
         }
         break;
      case 2:
         this.cO.drawRegion(this.M, 0, 277, 240, 43, 0, 0, 277, 0);
         break;
      case 3:
         this.cO.drawRegion(this.M, 0, 277, 240, 43, 0, 0, 277, 0);
         break;
      case 4:
         this.cO.drawRegion(this.M, 0, 277, 174, 43, 0, 0, 277, 0);
         break;
      case 5:
         this.cO.drawRegion(this.M, 0, 277, 240, 43, 0, 0, 277, 0);
         break;
      case 6:
         this.cO.drawRegion(this.M, 0, 277, 240, 43, 0, 0, 277, 0);
         break;
      case 7:
         this.cO.drawRegion(this.M, 0, 277, 240, 43, 0, 0, 277, 0);
         break;
      case 8:
         this.cO.drawRegion(this.G, 0, 288, 46, 32, 0, 0, 288, 0);
         this.cO.drawRegion(this.G, 100, 288, 140, 32, 0, 100, 288, 0);
         this.cO.drawRegion(this.G, 141, 264, 74, 24, 0, 141, 264, 0);
         this.cO.drawRegion(this.G, 163, 259, 32, 12, 0, 163, 259, 0);
         break;
      case 9:
         this.cO.drawRegion(this.F, 25, 179, 62, 23, 0, 25, 179, 0);
         this.cO.drawRegion(this.F, 0, 289, 176, 31, 0, 0, 289, 0);
         break;
      case 10:
         this.cO.drawRegion(this.aq, 52, 289, 188, 31, 0, 52, 289, 0);
         this.cO.drawRegion(this.aq, 182, 268, 58, 21, 0, 182, 268, 0);
         break;
      case 11:
         this.cO.drawRegion(this.ap, 0, 288, 115, 32, 0, 0, 288, 0);
         this.cO.drawRegion(this.ap, 168, 288, 72, 32, 0, 168, 288, 0);
         break;
      case 12:
         this.cO.drawRegion(this.ao, 0, 288, 93, 32, 0, 0, 288, 0);
         this.cO.drawRegion(this.ao, 146, 288, 94, 32, 0, 146, 288, 0);
         break;
      case 13:
         this.cO.drawRegion(this.an, 0, 288, 71, 32, 0, 0, 288, 0);
         this.cO.drawRegion(this.an, 136, 288, 104, 32, 0, 136, 288, 0);
         this.cO.drawRegion(this.an, 0, 192, 26, 15, 0, 0, 192, 0);
         this.cO.drawRegion(this.an, 180, 227, 42, 21, 0, 180, 227, 0);
         break;
      case 14:
         this.cO.drawRegion(this.al, 0, 288, 180, 32, 0, 0, 288, 0);
         this.cO.drawRegion(this.al, 43, 166, 29, 22, 0, 43, 166, 0);
         this.cO.drawRegion(this.al, 94, 166, 46, 22, 0, 94, 166, 0);
         this.cO.drawRegion(this.al, 170, 166, 29, 22, 0, 170, 166, 0);
         break;
      case 15:
         this.cO.drawRegion(this.ak, 0, 287, 79, 33, 0, 0, 287, 0);
         this.cO.drawRegion(this.ak, 142, 287, 98, 33, 0, 142, 287, 0);
         this.cO.drawRegion(this.ak, 175, 211, 41, 21, 0, 175, 211, 0);
         break;
      case 16:
         this.cO.drawRegion(this.L, 0, 286, 91, 34, 0, 0, 286, 0);
         this.cO.drawRegion(this.L, 144, 286, 96, 34, 0, 144, 286, 0);
         this.cO.drawRegion(this.L, 0, 225, 13, 9, 0, 0, 225, 0);
         this.cO.drawRegion(this.L, 0, 234, 25, 16, 0, 0, 234, 0);
         this.cO.drawRegion(this.L, 227, 225, 13, 8, 0, 227, 225, 0);
         this.cO.drawRegion(this.L, 217, 233, 23, 8, 0, 217, 233, 0);
         this.cO.drawRegion(this.L, 158, 163, 64, 17, 0, 158, 163, 0);
         break;
      case 17:
         this.cO.drawRegion(this.J, 0, 287, 240, 33, 0, 0, 287, 0);
         this.cO.drawRegion(this.J, 0, 240, 41, 22, 0, 0, 240, 0);
         this.cO.drawRegion(this.J, 198, 248, 31, 13, 0, 198, 248, 0);
      }

   }

   private void u() {
      switch(this.ce) {
      case 0:
         if (this.cE) {
            this.b2 = true;
            this.cO.drawImage(this.u, 57, 138, 0);
            if (!this.d4) {
               this.cO.drawImage(this.aY, 65, 185, 0);
            }
         }

         if (this.a8.j > 47 && this.a8.j < 67 && this.a8.i > 175 && this.a8.i < 185 && !this.b2 && this.dQ) {
            this.bf = "-Интересно,что в шкафу?..Чтобы открыть,нажми\"5\"";
         }

         if (this.a8.j > 47 && this.a8.j < 67 && this.a8.i > 175 && this.a8.i < 185 && this.b2 && this.dQ && !this.v) {
            this.bf = "-Ух,ты!Ключ!.. Чтобы         получить,нажми \"5\"";
         }
         break;
      case 1:
         this.b1 = true;
         if (!this.ea) {
            this.cO.drawImage(this.b4, 136, 212, 0);
         }

         if (this.dB) {
            this.cO.drawImage(this.bp, 175, 129, 0);
            if (!this.w) {
               this.cO.drawImage(this.c1, 193, 140, 0);
            }
         }
         break;
      case 2:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 42, 65, 0);
         this.cO.drawImage(this.cL, 0, 58, 0);
         this.cO.drawRegion(this.else, 0, 0, 66, 128, 0, 174, 51, 0);
         this.cO.drawRegion(this.else, 0, 0, 66, 51, 0, 174, 0, 0);
         this.cO.drawImage(this.bu, 122, 81, 0);
         this.cO.drawRegion(this.T, 0, 0, 7, 16, 0, 130, 86, 0);
         this.cO.drawRegion(this.T, 17, 0, 11, 16, 0, 138, 86, 0);
         break;
      case 3:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 42, 65, 0);
         this.cO.drawImage(this.cL, 0, 58, 0);
         this.cO.drawRegion(this.cL, 101, 0, 75, 7, 0, 176, 58, 0);
         if (this.aG) {
            this.cO.drawImage(this.U, 121, 106, 0);
         } else {
            this.cO.drawImage(this.C, 121, 106, 0);
         }
         break;
      case 4:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 42, 65, 0);
         this.cO.drawImage(this.cL, 0, 58, 0);
         this.cO.drawRegion(this.cL, 101, 0, 75, 7, 0, 176, 58, 0);
         this.cO.drawRegion(this.else, 66, 93, 66, 35, 0, 174, 285, 0);
         this.cO.drawRegion(this.else, 66, 93, 66, 6, 0, 174, 279, 0);
         this.cO.drawRegion(this.else, 66, 93, 66, 6, 0, 174, 277, 0);
         this.cO.drawImage(this.bu, 122, 81, 0);
         this.cO.drawRegion(this.T, 7, 0, 10, 16, 0, 130, 86, 0);
         this.cO.drawRegion(this.T, 17, 0, 11, 16, 0, 138, 86, 0);
         break;
      case 5:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 42, 65, 0);
         this.cO.drawImage(this.cL, 0, 58, 0);
         this.cO.drawRegion(this.cL, 101, 0, 75, 7, 0, 176, 58, 0);
         break;
      case 6:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 106, 65, 0);
         this.cO.drawImage(this.cL, 64, 58, 0);
         this.cO.drawRegion(this.cL, 101, 0, 75, 7, 0, 0, 58, 0);
         break;
      case 7:
         this.cO.drawRegion(this.B, 0, 0, 51, 97, 0, 106, 65, 0);
         this.cO.drawImage(this.cL, 64, 58, 0);
         this.cO.drawRegion(this.cL, 101, 0, 75, 7, 0, 0, 58, 0);
         break;
      case 8:
         if (this.ba) {
            this.cO.drawImage(this.Q, 22, 102, 0);
         }

         this.cO.drawImage(this.cM, 143, 125, 0);
         break;
      case 9:
         if (!this.Y) {
            this.cO.drawImage(this.b6, 214, 128, 0);
         }
         break;
      case 10:
         if (!this.a5) {
            this.cO.drawImage(this.bb, 105, 139, 0);
         }
         break;
      case 11:
         this.cO.drawImage(this.cN, 88, 132, 0);
         this.cO.drawRegion(this.X, 0, 0, 14, 12, 0, 189, 133, 0);
         if (this.R) {
            this.cO.drawRegion(this.X, 14, 0, 12, 12, 0, 190, 134, 0);
         }

         if (this.I) {
            this.cO.drawImage(this.aP, 27, 150, 0);
            if (!this.cd) {
               this.cO.drawImage(this.bh, 30, 154, 0);
            }
         }
         break;
      case 12:
         if (this.P) {
            this.cO.drawImage(this.br, 106, 119, 0);
            if (!this.dC) {
               this.cO.drawImage(this.aw, 118, 121, 0);
            }
         }
         break;
      case 13:
         if (this.case) {
            this.cO.drawImage(this.ec, 191, 97, 0);
            this.cO.drawImage(this.d5, 192, 105, 0);
         }

         if (!this.bg) {
            this.cO.drawImage(this.dk, 44, 111, 0);
         } else {
            this.cO.drawImage(this.ae, 135, 131, 0);
            if (!this.c7) {
               this.cO.drawImage(this.cH, 145, 133, 0);
            }
         }
         break;
      case 14:
         if (this.a8.j > 98 && this.a8.j < 118 && this.a8.i > 208 && this.a8.i < 218 && !this.ai && this.dQ && !this.by && !this.cP) {
            this.bf = "-Какая-то карта... Ничего     не понимаю...";
         }

         this.cO.drawImage(this.be, 112, 207, 0);
         if (this.aL) {
            this.cO.drawRegion(this.d9, 0, 0, 19, 15, 0, 145, 125, 0);
            if (!this.cr) {
               this.cO.drawRegion(this.d9, 19, 0, 5, 7, 0, 152, 128, 0);
            }
         }
         break;
      case 15:
         if (this.b5) {
            this.cO.drawImage(this.aJ, 58, 130, 0);
         }
         break;
      case 16:
         if (this.cj) {
            this.cO.drawImage(this.dJ, 114, 80, 0);
         }

         if (this.ch) {
            this.cO.drawImage(this.bY, 161, 182, 0);
            if (!this.cf) {
               this.cO.drawImage(this.D, 170, 187, 0);
            }
         }

         if (this.cg) {
            this.cO.drawImage(this.D, 133, 72, 0);
            this.cO.drawImage(this.dE, 133, 72, 0);
         }
         break;
      case 17:
         if (!this.W) {
            this.cO.drawImage(this.ay, 92, 108, 0);
         }
      }

   }

   private void w() {
      this.g = true;
      int var1 = this.a8.j;
      int var2 = this.a8.i;
      this.a();
      this.n();
      this.u();
      this.dz = true;
      if (!this.cl) {
         this.A();
      }

      if (this.dz && this.cl) {
         this.q();
      }

      this.s();
      if (this.aQ[15] == 10) {
         this.e();
         ++this.cQ;
         if (this.A > 270) {
            this.A = 0;
            this.if();
            this.dI = false;
            this.b0 = 1;
            this.bO = 0;
            return;
         }
      }

   }

   private void e() {
      try {
         if (this.aU == null) {
            this.aU = this.if("js");
         }

         if (this.i == null) {
            String var1 = "-Наконец-то!..Свобода! Но что это?!.Я не могу выйти!...Какая-то неведомая сила держит и не отпускает меня!..Я снова услышала голос...Жуткий неприятный смех...Я боялась оглянуться,но с ужасом понимала- Он приближается!..Он тут,рядом,за моей спиной!.Я не хочу умирать!..";
            this.i = this.a(var1, d1 - 16, dD);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.cO.setFont(dD);
      this.a8.try = false;
      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      this.cO.setClip(0, 20, d1, 272);
      this.cO.setColor(0, 128, 255);
      if (this.am < cm - 30) {
         this.cO.setColor(255, 255, 255);
         if (++this.A % 20 == 0) {
            this.A += 2;
         }

         if (this.A < 400) {
            this.cQ = this.do(this.i, 10, 272 - this.A, this.dA, 14, dD);
         }

         this.cO.drawImage(this.aU, (d1 - 176) / 2, 20, 0);
      }

   }

   private void h() {
      try {
         if (this.bQ == null) {
            this.bQ = this.if("LOGO");
         }

         if (this.aT == null) {
            this.aT = this.if("menuback");
         }
      } catch (Exception var3) {
      }

      try {
         if (this.j == null) {
            String var1 = "Меня зовут Шизуко.4 года назад я закончила школу благородных девиц и устроилась работать на черкизовский рынок.Вчера утром мне позвонили друзья и пригласили на отдых.Я нехотя согласилась,и вечером,в пятницу 13-го,мы   выехали.По дороге у нас сломалась машина и мы  заблудились в лесу.В полночь мы вышли к одному странному    неприветливому дому...";
            this.j = this.a(var1, d1 - 16, dD);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.cO.setFont(dD);
      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      if (this.z < 800) {
         this.z += 2;
         this.dA = this.do(this.j, 10, 272 - this.z, this.dA, 50, dD);
      }

      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, 115);
      if (this.z > 99) {
         this.cp = true;
      }

      if (this.z < 99) {
         this.cO.drawRegion(this.bQ, 51, 135, 156, 90, 0, (d1 - 156) / 2, 12, 0);
      }

      if (this.z > 99) {
         this.cp = true;
         this.cO.drawRegion(this.aT, 84, 230, 156, 90, 0, (d1 - 156) / 2, 12, 0);
         this.ds = true;
      }

      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 300, d1, 20);
      this.cO.setColor(255, 255, 255);
      if (this.a7 == null) {
         this.a7 = this.if("fanhui");
      }

      this.cO.drawImage(this.a7, 0, 310, 0);
   }

   private void do() {
      this.aZ = true;
      this.byte = true;
      this.z = 500;
      this.new = 176;
      this.aZ = false;
      this.bE = 41;
      this.bQ = null;
      this.aT = null;
      this.as = null;
      this.cO.setColor(0, 0, 0);
      this.cO.fillRect(0, 0, d1, cm);
      this.cO.setColor(255, 255, 255);
      if (this.bE < 10) {
         this.cO.drawString("Ах...", 144, 212, 0);
      } else if (this.bE < 20) {
         this.cO.drawString("Господи...", 144, 212, 0);
      } else if (this.bE < 30) {
         this.cO.drawString("Больно...", 144, 212, 0);
      }

   }

   private void A() {
      switch(this.dp) {
      case 0:
         this.bf = "-Где я?..Ох,как болит голова...Ничего не помню...";
         break;
      case 1:
         this.bf = "-Где все?.Почему так тихо?.Ау-у!Что делать?";
         break;
      case 2:
         if (this.d2 != 9) {
            ++this.d2;
            this.c6 = true;
         }

         if (this.d2 == 9) {
            if (this.dH != 3) {
               ++this.dH;
               this.c6 = false;
            }

            if (this.dH == 3) {
               if (!this.dX) {
                  this.bf = "Дверь закрыта.Помню,мы вошли в дом.Удар.Темнота";
               } else {
                  this.bf = "Я не понимаю,что происходит? Чей это дом?!.";
                  this.c8 = true;
               }
            }
         }
      }

   }

   private void try() {
      try {
         if (this.h == null) {
            String var1 = "Как я могу покинуть эту комнату? ";
            this.h = this.a(var1, d1 - 16, dD);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      if (this.eb < 300 && !this.dz) {
         this.cO.setFont(dD);
         this.cO.setClip(0, cm - 60 + 10, d1, 51);
         this.cO.setColor(0, 0, 0);
         this.cO.fillRect(0, cm - 60 + 10, d1, 51);
         this.dA = this.for(this.h, 10, 208 - ++this.eb, this.dA, 50, dD);
         this.cO.setColor(0, 0, 0);
         this.cO.fillRect(0, 198, d1, 10);
         this.cO.fillRect(0, cm - 60 + 10, d1, 10);
         if (this.eb > 110 && !this.dz) {
            this.dz = true;
            this.eb = 110;
         }
      }

   }

   private void s() {
      if (this.try && this.aQ[15] == 0) {
         if (this.aj == null) {
            this.aj = this.if("password");
         }

         this.cO.drawRegion(this.aj, 0, 0, 116, 35, 0, (d1 - 116) / 2, (cm - 35) / 2, 0);
         switch(this.ag) {
         case 1:
            this.cO.drawRegion(this.aj, 129, 4, 8, 26, 0, 74, 147, 0);
            break;
         case 2:
            this.cO.drawRegion(this.aj, 129, 4, 8, 26, 0, 95, 147, 0);
            break;
         case 3:
            this.cO.drawRegion(this.aj, 129, 4, 8, 26, 0, 116, 147, 0);
            break;
         case 4:
            this.cO.drawRegion(this.aj, 129, 4, 8, 26, 0, 137, 147, 0);
            break;
         case 5:
            this.cO.drawRegion(this.aj, 129, 4, 8, 26, 0, 159, 147, 0);
            this.cO.drawRegion(this.aj, 116, 11, 13, 13, 0, 156, 153, 0);
         case 6:
         }

         try {
            if (this.bV == null) {
               this.bV = new c(this.if("num"), 10);
            }

            if (this.ag >= 1) {
            }

            this.bV.j = 74;
            this.bV.i = 155;
            this.bV.goto = this.aQ[9] % 10;
            if (this.ag >= 2) {
               this.bV.a(this.cO);
            }

            this.bV.j = 95;
            this.bV.i = 155;
            this.bV.goto = this.aQ[10] % 10;
            if (this.ag >= 3) {
               this.bV.a(this.cO);
            }

            this.bV.j = 116;
            this.bV.i = 155;
            this.bV.goto = this.aQ[11] % 10;
            if (this.ag >= 4) {
               this.bV.a(this.cO);
            }

            this.bV.j = 137;
            this.bV.i = 155;
            this.bV.goto = this.aQ[12] % 10;
            if (this.ag >= 5) {
               this.bV.a(this.cO);
            }
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

   }

   private void try(int var1) {
      int var2 = this.a8.j;
      int var3 = this.a8.i;
      if (this.ce == 0 && var1 == 53 && this.a8.goto == 0) {
         if (!this.cP && this.a8.j > 122 && this.a8.j < 162 && this.a8.i == 165 && !this.l && !this.b1 && this.dQ && this.d4 && !this.bd && !this.k) {
            this.bf = "Чтобы открыть,нажми \"1\" и выбери ключ";
            this.k = true;
            return;
         }

         if (!this.cP && this.a8.j > 122 && this.a8.j < 162 && this.a8.i == 165 && !this.b1 && this.dQ && !this.d4 && !this.bd && !this.k) {
            this.bf = "-Дверь закрыта!..И как я отсюда выйду?";
            this.k = true;
            return;
         }
      }

      if (this.ce == 12 && var1 == 53 && this.a8.goto == 0 && var2 > 171 && var2 < 206 && var3 > 102 && var3 < 154 && !this.cP && this.dQ && !this.k) {
         this.bf = "-У меня такое чувство,что мной кто-то управляет";
         this.k = true;
      } else if (this.ce == 15 && var1 == 53 && this.a8.goto == 0 && var2 > 98 && var2 < 193 && var3 > 98 && var3 < 108 && !this.cP && this.dQ && !this.k) {
         this.bf = "-У меня такое чувство,что мной кто-то управляет";
         this.k = true;
      } else if (this.ce == 17 && var1 == 53 && this.a8.goto == 0 && var2 > 136 && var2 < 166 && var3 > 53 && var3 < 83 && !this.cP && this.dQ && !this.k) {
         this.bf = "-У меня такое чувство,что мной кто-то управляет";
         this.k = true;
      } else if (this.ce == 8 && var1 == 53 && this.a8.goto == 0 && var2 == 143 && var3 == 123 && !this.cP && this.dQ && !this.c2 && !this.k) {
         this.bf = "Ура!..Молоток! Я бы взяла,но он подпирает тумбу";
         this.k = true;
      } else {
         if (this.ce == 1 && var1 == 53 && this.a8.goto == 0) {
            if (var2 > 128 && var2 < 168 && var3 > 198 && var3 < 213 && !this.cP && this.dQ && !this.k && !this.ea) {
               if (!this.ea) {
                  this.ea = true;
                  this.a(this.if("shaizi1"), 3, 2, 0, 0, 25, 13);
                  this.bf = "Я взяла большой ключ.";
                  this.k = true;
               }

               this.k = true;
               return;
            }

            if (var2 > 128 && var2 < 168 && var3 > 198 && var3 < 213 && !this.cP && this.dQ && !this.k && this.ea) {
               this.bf = "-Ого!..Сколько тараканов?!И ни одного сухарика";
               this.k = true;
               return;
            }
         }

         if (this.ce == 10 && var1 == 53 && this.a8.goto == 0) {
            if (var2 > 8 && var2 < 41 && var3 > 127 && var3 < 200 && !this.cP && !this.k && this.b) {
               this.bf = "Ничего интересного";
               this.k = true;
               return;
            }

            if (var2 > 103 && var2 < 159 && var3 > 130 && var3 < 167 && !this.cP && !this.k) {
               this.bf = "Ничего интересного";
               this.k = true;
               return;
            }

            if (var2 > 201 && var2 < 232 && var3 > 151 && var3 < 181 && !this.cP && !this.k) {
               this.bf = "Вот люди!..Посрать - посрали,а смывать кто будет?";
               this.k = true;
               return;
            }
         }

         if (this.ce == 11 && var1 == 53 && this.a8.goto == 0 && var2 > 88 && var2 < 168 && var3 > 132 && var3 < 175 && !this.cP && this.dQ && !this.k) {
            this.bf = "Что за старые ящики?Всюду пыль,один без крышки";
            this.k = true;
         } else {
            if (this.ce == 9 && var1 == 53 && this.a8.goto == 0) {
               if (var2 > 8 && var2 < 64 && var3 > 98 && var3 < 151 && !this.cP && this.dQ && !this.k) {
                  this.bf = "Странная библиотека...  Одна медицина...";
                  this.k = true;
                  return;
               }

               if (var2 > 116 && var2 < 173 && var3 > 98 && var3 < 151 && !this.cP && this.dQ && !this.k) {
                  this.bf = "Странная библиотека...  Одна медицина...";
                  this.k = true;
                  return;
               }

               if (var2 > 25 && var2 < 87 && var3 > 195 && var3 < 242 && !this.cP && this.dQ && !this.k) {
                  this.bf = "Странная библиотека...  Одна медицина...";
                  this.k = true;
                  return;
               }
            }

            if (this.ce == 13 && var1 == 53 && this.a8.goto == 0 && var2 > 36 && var2 < 100 && var3 > 78 && var3 < 146 && !this.cP && this.dQ && !this.dl && !this.k) {
               if (this.n == 0) {
                  this.bf = "-Книги...\"Большая медицинская энциклопедия\".";
               }

               if (this.n == 1) {
                  this.bf = "Тут не хватает несколько     томов...";
               }

               if (this.n == 2) {
                  this.bf = "На книгах нет пыли...Ими  иногда пользуются";
               }

               if (this.n == 3) {
                  this.bf = "Интересно,каких книг не хватает?..";
               }

               if (this.n == 4) {
                  this.n = 0;
                  this.k = true;
               }

               if (this.n != 4) {
                  ++this.n;
               } else if (this.n == 4) {
                  this.n = 0;
               }

            } else {
               if (this.ce == 0) {
                  this.cq = 0;
                  if (var1 == 53 && this.a8.goto == 0) {
                     if (var2 > 122 && var2 < 162 && var3 > 0 && var3 < 170 && !this.cP && this.dQ && this.bd) {
                        this.ce = 1;
                        this.a8.j = 148;
                        this.a8.i = 278;
                     }

                     if (var2 > 47 && var2 < 67 && var3 > 175 && var3 < 185 && this.dQ) {
                        this.cE = true;
                        ++this.E;
                     }

                     if (var2 > 47 && var2 < 67 && var3 > 175 && var3 < 185 && this.cE && this.E == 2 && !this.d4 && this.dQ && !this.k) {
                        this.d4 = true;
                        this.v = true;
                        this.a(this.if("shouji1"), 2, 2, 0, 0, 15, 27);
                        this.bf = "Ключ!..    Интересно,от чего он?";
                        this.E = 0;
                        this.k = true;
                        return;
                     }

                     if (var2 > 127 && var2 < 172 && var3 > 270 && var3 < 320 && !this.W && !this.cP && this.c && !this.k) {
                        this.bf = "Дверь закрыта... ";
                        this.k = true;
                        return;
                     }
                  }
               }

               if (this.ce == 1 && var1 == 53 && this.a8.goto == 0 && !this.cP) {
                  if (var2 > 13 && var2 < 43 && var3 > 0 && var3 < 113 && !this.cP && this.dQ) {
                     this.ce = 10;
                     this.a8.j = 18;
                     this.a8.i = 278;
                  }

                  if (var2 > 173 && var2 < 203 && var3 > 0 && var3 < 138 && !this.dB && this.dQ) {
                     this.try = true;
                  }

                  if (var2 > 173 && var2 < 203 && var3 > 0 && var3 < 138 && this.dB && !this.w && this.dQ && !this.k) {
                     this.w = true;
                     this.a(this.if("shu"), 4, 2, 0, 0, 23, 11);
                     this.bf = "-Хм...Еще один ключ...Пожалуй возьму, пригодится";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 2 && var1 == 53 && this.a8.goto == 0 && var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && !this.cP && this.dQ) {
                  this.ce = 14;
                  this.a8.j = 193;
                  this.a8.i = 278;
               }

               if (this.ce == 4 && var1 == 53 && this.a8.goto == 0 && var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && !this.cP && this.dQ) {
                  this.ce = 13;
                  this.a8.j = 93;
                  this.a8.i = 278;
               }

               if (this.ce == 6 && var1 == 53 && this.a8.goto == 0) {
               }

               if (this.ce == 5 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && this.dF && !this.cP && this.dQ) {
                     this.ce = 8;
                     this.a8.j = 58;
                     this.a8.i = 278;
                  }

                  if (var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && !this.dF && !this.cP && this.dQ && !this.k) {
                     if (this.n == 0) {
                        this.bf = "-Закрыто...Тихо! По-моему там кто-то есть... ";
                     }

                     if (this.n == 1) {
                        this.bf = "Я четко слышала шорох...Ой,   мне страшно!..";
                     }

                     if (this.n == 2) {
                        this.n = 0;
                        this.k = true;
                     }

                     if (this.n != 2) {
                        ++this.n;
                     } else if (this.n == 2) {
                        this.n = 0;
                     }

                     return;
                  }
               }

               if (this.ce == 3 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && this.dj && !this.cP && this.dQ) {
                     this.ce = 11;
                     this.a8.j = 133;
                     this.a8.i = 278;
                  } else if (var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && !this.dj && !this.cP && this.dQ && !this.k) {
                     this.bf = "-Упс...Дверь закрыта...Нужен ключ";
                     this.k = true;
                     return;
                  }

                  if (var2 > 133 && var2 < 168 && var3 > 133 && var3 < 143 && this.aG && !this.cP && this.dQ) {
                     this.ce = 15;
                     this.a8.j = 98;
                     this.a8.i = 283;
                  }
               }

               if (this.ce == 15 && var1 == 53 && this.a8.goto == 0 && var2 > 48 && var2 < 73 && var3 > 123 && var3 < 133 && this.dQ) {
                  this.b5 = true;
               }

               if (this.ce == 6 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && this.ei && !this.cP && this.dQ) {
                     this.ce = 9;
                     this.a8.j = 193;
                     this.a8.i = 278;
                  }

                  if (var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && !this.ei && !this.cP && this.dQ && !this.k) {
                     this.bf = "Дверь закрыта...Из-под двери повеяло холодом";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 7 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && this.aS && !this.cP && this.dQ) {
                     this.ce = 12;
                     this.a8.j = 108;
                     this.a8.i = 278;
                     return;
                  }

                  if (var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && !this.aS && !this.cP && this.dQ && !this.k) {
                     if (this.n == 0) {
                        this.bf = "Открыть невозможно.У двери нет ручки...";
                     }

                     if (this.n == 1) {
                        this.bf = "Нужна ручка.Любая.Дверная,от крышки,коробки";
                     }

                     if (this.n == 2) {
                        this.n = 0;
                        this.k = true;
                     }

                     if (this.n != 2) {
                        ++this.n;
                     } else if (this.n == 2) {
                        this.n = 0;
                     }

                     return;
                  }
               }

               if (this.ce == 1 && var1 == 53 && this.a8.goto == 0 && var2 > 127 && var2 < 172 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                  this.ce = 0;
                  this.a8.j = 142;
                  this.a8.i = 170;
               }

               if (this.ce == 2) {
                  this.cq = 0;
                  if (var1 == 53 && this.a8.goto == 0 && var2 > 138 && var2 < 176 && var3 > 103 && var3 < 208 && !this.cP && this.b3) {
                     this.ce = 1;
                     this.S = 0;
                     this.a8.j = 3;
                     this.a8.i = 98;
                  }
               }

               if (this.ce == 3 && var1 == 53 && this.a8.goto == 0 && var2 > 138 && var2 < 176 && var3 > 95 && var3 < 208 && !this.cP && this.b3) {
                  this.ce = 2;
                  this.a8.j = 3;
                  this.a8.i = 123;
               }

               if (this.ce == 5 && var1 == 53 && this.a8.goto == 0) {
               }

               if (this.ce == 10) {
                  this.cq = 0;
                  if (var1 == 53 && this.a8.goto == 0) {
                     if (var2 > 0 && var2 < 38 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                        this.ce = 1;
                        this.a8.j = 28;
                        this.a8.i = 113;
                     }

                     if (var2 > 88 && var2 < 118 && var3 > 130 && var3 < 168 && !this.a5 && this.dQ && !this.k || var2 > 73 && var2 < 111 && var3 > 130 && var3 < 164 && !this.a5 && this.b3 && !this.k) {
                        this.a5 = true;
                        this.a(this.if("ys"), 10, 2, 0, 0, 23, 11);
                        this.bf = "-А вот еще один ключ...Кто их тут оставил?";
                        this.k = true;
                        return;
                     }
                  }
               }

               if (this.ce == 8 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 43 && var2 < 83 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 5;
                     this.a8.j = 53;
                     this.a8.i = 143;
                  }

                  if (var2 > 18 && var2 < 48 && var3 > 143 && var3 < 453 && !this.cP && this.dQ && this.ba) {
                     this.ce = 16;
                     this.a8.j = 108;
                     this.a8.i = 278;
                     return;
                  }

                  if (var2 > 18 && var2 < 48 && var3 > 143 && var3 < 453 && !this.ba && this.dQ && !this.k) {
                     this.bf = "Дверь закрыта...-По-моему за мной наблюдают";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 15 && var1 == 53 && this.a8.goto == 0 && var2 > 73 && var2 < 123 && var3 > 270 && var3 < 320 && this.aG && !this.cP && this.c) {
                  this.ce = 3;
                  this.a8.j = 143;
                  this.a8.i = 143;
               }

               if (this.ce == 17 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 114 && var2 < 173 && var3 > 66 && var3 < 140 && !this.cP) {
                     this.ce = 16;
                     this.a8.j = 123;
                     this.a8.i = 108;
                     return;
                  }

                  if (var2 > 72 && var2 < 112 && var3 > 98 && var3 < 138 && !this.W && this.dQ && !this.k) {
                     this.W = true;
                     this.a(this.if("mao1"), 14, 2, 0, 0, 23, 15);
                     this.bf = "Тот же голос: -Ты всеравно отсюда не выйдешь!";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 16 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 88 && var2 < 128 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 8;
                     this.a8.j = 33;
                     this.a8.i = 153;
                  }

                  if (var2 > 108 && var2 < 143 && var3 > 103 && var3 < 113 && !this.cj && this.dQ && !this.cP && !this.k) {
                     this.cj = true;
                     this.bf = "Сердитый  голос: -Хватит ломать дом, дура!!!";
                     this.k = true;
                     return;
                  }

                  if (var2 > 153 && var2 < 173 && var3 > 183 && var3 < 193 && this.dQ) {
                     this.ch = true;
                     ++this.dr;
                  }

                  if (var2 > 153 && var2 < 173 && var3 > 183 && var3 < 193 && this.ch && !this.cf && this.dr == 2 && this.dQ && !this.k) {
                     this.cf = true;
                     this.dr = 0;
                     this.a(this.if("shengzi"), 13, 2, 0, 0, 34, 20);
                     this.bf = "-Господи,мыло и веревка!.Лучше возьму веревку";
                     this.k = true;
                     return;
                  }

                  if (var2 > 108 && var2 < 143 && var3 > 103 && var3 < 113 && this.cj && this.cg && !this.cP && this.dQ) {
                     this.ce = 17;
                     this.a8.j = 123;
                     this.a8.i = 97;
                  }
               }

               if (this.ce == 9 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 173 && var2 < 240 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 6;
                     this.a8.j = 115;
                     this.a8.i = 145;
                  }

                  if (var2 > 198 && var2 < 239 && var3 > 116 && var3 < 150 && !this.Y && this.dQ && !this.k) {
                     this.a(this.if("shuji1"), 5, 2, 0, 0, 33, 31);
                     this.bf = "-А вот и книги!..\"Анатомия человеческого тела\"...";
                     this.Y = true;
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 11 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 108 && var2 < 153 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 3;
                     this.a8.j = 53;
                     this.a8.i = 143;
                  }

                  if (var2 > 22 && var2 < 54 && var3 > 132 && var3 < 181 && this.I && !this.cd && this.dQ && !this.k) {
                     this.cd = true;
                     this.a(this.if("q"), 7, 2, 0, 0, 40, 9);
                     this.bf = "Что за хрень?И куда её?Возьму,может пригодиться";
                     this.k = true;
                     return;
                  }

                  if (var2 > 182 && var2 < 208 && var3 > 132 && var3 < 175 && this.R && this.I && this.cd && this.dQ && !this.k) {
                     this.R = false;
                     this.a(this.if("hse"), 8, 2, 0, 0, 12, 19);
                     this.bf = "Вы решили отломать ручку от крышки";
                     this.I = false;
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 12 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 88 && var2 < 128 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 7;
                     this.a8.j = 115;
                     this.a8.i = 145;
                  }

                  if (var2 > 102 && var2 < 140 && var3 > 90 && var3 < 156 && this.dQ) {
                     this.P = true;
                     if (!this.dC) {
                        ++this.dR;
                     }
                  }

                  if (var2 > 102 && var2 < 140 && var3 > 90 && var3 < 156 && this.P && !this.dC && this.dR == 2 && this.dQ && !this.k) {
                     this.dC = true;
                     this.dR = 0;
                     this.a(this.if("qq"), 9, 2, 0, 0, 9, 10);
                     this.bf = "-Какая большая и красивая монета!..Возьму себе";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 13 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 68 && var2 < 118 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 4;
                     this.a8.j = 53;
                     this.a8.i = 145;
                  }

                  if (var2 > 190 && var2 < 223 && var3 > 82 && var3 < 140 && this.case && !this.k) {
                     if (this.n == 0) {
                        this.bf = "-Фу-у!..Ну и запах в этом шкафу!";
                     }

                     if (this.n == 1) {
                        this.bf = "-Какие-то цифры...Что бы это значило?...";
                     }

                     if (this.n == 2) {
                        this.n = 0;
                        this.k = true;
                     }

                     if (this.n != 2) {
                        ++this.n;
                     } else if (this.n == 2) {
                        this.n = 0;
                     }

                     return;
                  }

                  if (var2 > 190 && var2 < 223 && var3 > 82 && var3 < 140 && !this.case && this.dQ) {
                     this.case = true;
                  } else if (var2 > 190 && var2 < 223 && var3 > 82 && var3 < 140 && this.case && this.dQ) {
                     this.case = false;
                  }

                  if (var2 > 130 && var2 < 176 && var3 > 102 && var3 < 153 && this.bg && !this.c7 && this.dQ && !this.k) {
                     this.c7 = true;
                     this.a(this.if("se1"), 6, 2, 0, 0, 22, 16);
                     this.bf = "Найдена крышка от какого- то предмета...";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 14 && var1 == 53 && this.a8.goto == 0) {
                  if (var2 > 178 && var2 < 240 && var3 > 270 && var3 < 320 && !this.cP && this.c) {
                     this.ce = 2;
                     this.a8.j = 53;
                     this.a8.i = 143;
                  }

                  if (var2 > 133 && var2 < 153 && var3 > 113 && var3 < 123 && this.aL && !this.cr && this.dQ && !this.k) {
                     this.cr = true;
                     this.a(this.if("dianchi"), 12, 2, 0, 0, 8, 16);
                     this.bf = "В тайнике дома вы обнаружили аккумулятор";
                     this.k = true;
                     return;
                  }
               }

               if (this.ce == 14 && var1 == 53 && this.a8.goto == 0 && var2 > 98 && var2 < 118 && var3 > 203 && var3 < 218 && this.dQ && !this.cP) {
                  this.by = true;
                  if (this.bw == 0) {
                     this.ai = true;
                  }

                  if (this.bw == 1) {
                     this.ai = false;
                  }

                  if (this.bw != 1) {
                     ++this.bw;
                  } else if (this.bw == 1) {
                     this.bw = 0;
                  }

               } else {
                  if (var1 == 53 && this.a8.goto == 0 && !this.cP) {
                     this.k = false;
                     if (!this.k) {
                        this.bf = "";
                        this.n = 0;
                     }
                  }

                  if (var1 == 53) {
                     if (this.ce == 3 && this.a8.goto == 0 && this.cP && var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && this.dQ && this.c5[this.void].case == 3) {
                        this.a(3, 2);
                        this.bf = "Большой ключ подошел... Дверь открылась";
                        this.dj = true;
                     }

                     if (this.ce == 0 && this.a8.goto == 0 && this.cP) {
                        if (var2 > 127 && var2 < 172 && var3 > 270 && var3 < 320 && this.c && !this.k && this.c5[this.void].case == 14) {
                           this.a(14, 2);
                           this.aQ[15] = 10;
                        }

                        if (var2 > 127 && var2 < 162 && var3 > 0 && var3 < 170 && this.c5[this.void].case == 2) {
                           this.a(2, 2);
                           this.bd = true;
                           this.l = true;
                           this.bf = "Теперь дверь открыта. Чтобы войти,нажми \"5\"";
                        }
                     }

                     if (this.ce == 16 && this.a8.goto == 0 && this.cP && var2 > 108 && var2 < 143 && var3 > 103 && var3 < 113 && this.cj && this.dQ && this.c5[this.void].case == 13) {
                        this.a(13, 2);
                        this.bf = "Снова голос: -Будешь вешаться - возьми мыло!..";
                        this.cg = true;
                     }

                     if (this.ce == 5 && this.a8.goto == 0 && this.cP && var2 > 33 && var2 < 78 && var3 > 0 && var3 < 143 && this.dQ && this.c5[this.void].case == 10) {
                        this.a(10, 2);
                        this.bf = "Так вот откуда зеленый ключ.Дверь открылась";
                        this.dF = true;
                     }

                     if (this.ce == 8 && this.a8.goto == 0 && this.cP && var2 == 143 && var3 == 123 && this.dQ && this.c5[this.void].case == 7) {
                        this.a(7, 2);
                        this.bf = "Классно я придумала заменить молоток хренью!";
                        this.a(this.if("chuizi"), 11, 2, 0, 0, 28, 28);
                        this.c2 = true;
                     }

                     if (this.ce == 14 && this.a8.goto == 0 && this.cP && var2 > 133 && var2 < 153 && var3 > 113 && var3 < 123 && this.dQ && !this.aL && this.c5[this.void].case == 11) {
                        this.bf = "Ого!..Сильна малышка!Так и дом развалить можно";
                        this.aL = true;
                        this.cP = false;
                        this.dn = true;
                     }

                     if (this.ce == 3 && this.a8.goto == 0 && this.cP && var2 > 133 && var2 < 168 && var3 > 133 && var3 < 143 && !this.aG && this.dQ && this.c5[this.void].case == 11) {
                        this.bf = "Ого!..Сильна малышка!Так и дом развалить можно";
                        this.aG = true;
                        this.cP = false;
                     }

                     if (this.ce == 6 && this.a8.goto == 0 && this.cP && var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && this.dQ && this.c5[this.void].case == 4) {
                        this.a(4, 2);
                        this.bf = "Дверь со скрипом открылась";
                        this.ei = true;
                     }

                     if (this.ce == 7 && this.a8.goto == 0 && this.cP && var2 > 100 && var2 < 135 && var3 > 0 && var3 < 143 && this.dQ && this.c5[this.void].case == 8) {
                        this.a(8, 2);
                        this.bf = "Вы приделали ручку,и потянули дверь на себя...";
                        this.aS = true;
                     }

                     if (this.ce == 11 && this.a8.goto == 0 && this.cP && var2 > 182 && var2 < 208 && var3 > 132 && var3 < 175 && this.dQ && this.c5[this.void].case == 6) {
                        this.a(6, 2);
                        this.bf = "-Ловко я сообразила!..И снова секреты...";
                        this.R = true;
                        this.I = true;
                     }

                     if (this.ce == 13) {
                        if (this.a8.goto == 0 && this.cP && var2 > 36 && var2 < 100 && var3 > 78 && var3 < 146 && this.dQ && this.c5[this.void].case == 5) {
                           this.dl = true;
                           this.a(5, 2);
                           this.bf = "-Упс..Поставила книги и ящик открылся...";
                           this.bg = true;
                           return;
                        }

                        if (this.a8.goto == 0 && !this.cP && var2 > 36 && var2 < 100 && var3 > 78 && var3 < 146 && this.dQ && this.dl && !this.d3) {
                           this.bf = "А дом-то с секретом...Не нравится мне это";
                           this.d3 = true;
                           return;
                        }
                     }

                     if (this.ce == 15 && this.a8.goto == 0 && this.cP) {
                        if (var2 > 48 && var2 < 73 && var3 > 123 && var3 < 133 && this.b5 && this.dQ && this.c5[this.void].case == 12) {
                           this.a(12, 2);
                           this.bf = "Компьютер включился,но запустить не удалось";
                           this.do = true;
                        }

                        if (var2 > 48 && var2 < 73 && var3 > 123 && var3 < 133 && this.b5 && this.dQ && this.do && this.c5[this.void].case == 9) {
                           this.a(9, 2);
                           this.bf = "Компьютер проглотил монету и вы вошли на SEclub";
                           this.ba = true;
                        }
                     }
                  }

                  if (var1 == cJ && this.try) {
                     this.try = false;
                     this.ag = 0;
                     this.aQ[11] = 0;
                     this.aQ[9] = 0;
                     this.aQ[10] = 0;
                     this.aQ[12] = 0;
                     this.aQ[13] = 0;
                  }

                  if (this.try) {
                     ++this.ag;
                     if (this.ag == 1) {
                        if (var1 == 48) {
                           this.aQ[8] = 0;
                        } else if (var1 == 49) {
                           this.aQ[8] = 1;
                        } else if (var1 == 50) {
                           this.aQ[8] = 2;
                        } else if (var1 == 51) {
                           this.aQ[8] = 3;
                        } else if (var1 == 52) {
                           this.aQ[8] = 4;
                        } else if (var1 == 53) {
                           this.aQ[8] = 5;
                        } else if (var1 == 54) {
                           this.aQ[8] = 6;
                        } else if (var1 == 55) {
                           this.aQ[8] = 7;
                        } else if (var1 == 56) {
                           this.aQ[8] = 8;
                        } else if (var1 == 57) {
                           this.aQ[8] = 9;
                        }
                     } else if (this.ag == 2) {
                        if (var1 == 48) {
                           this.aQ[9] = 0;
                        } else if (var1 == 49) {
                           this.aQ[9] = 1;
                        } else if (var1 == 50) {
                           this.aQ[9] = 2;
                        } else if (var1 == 51) {
                           this.aQ[9] = 3;
                        } else if (var1 == 52) {
                           this.aQ[9] = 4;
                        } else if (var1 == 53) {
                           this.aQ[9] = 5;
                        } else if (var1 == 54) {
                           this.aQ[9] = 6;
                        } else if (var1 == 55) {
                           this.aQ[9] = 7;
                        } else if (var1 == 56) {
                           this.aQ[9] = 8;
                        } else if (var1 == 57) {
                           this.aQ[9] = 9;
                        }
                     } else if (this.ag == 3) {
                        if (var1 == 48) {
                           this.aQ[10] = 0;
                        } else if (var1 == 49) {
                           this.aQ[10] = 1;
                        } else if (var1 == 50) {
                           this.aQ[10] = 2;
                        } else if (var1 == 51) {
                           this.aQ[10] = 3;
                        } else if (var1 == 52) {
                           this.aQ[10] = 4;
                        } else if (var1 == 53) {
                           this.aQ[10] = 5;
                        } else if (var1 == 54) {
                           this.aQ[10] = 6;
                        } else if (var1 == 55) {
                           this.aQ[10] = 7;
                        } else if (var1 == 56) {
                           this.aQ[10] = 8;
                        } else if (var1 == 57) {
                           this.aQ[10] = 9;
                        }
                     } else if (this.ag == 4) {
                        if (var1 == 48) {
                           this.aQ[11] = 0;
                        } else if (var1 == 49) {
                           this.aQ[11] = 1;
                        } else if (var1 == 50) {
                           this.aQ[11] = 2;
                        } else if (var1 == 51) {
                           this.aQ[11] = 3;
                        } else if (var1 == 52) {
                           this.aQ[11] = 4;
                        } else if (var1 == 53) {
                           this.aQ[11] = 5;
                        } else if (var1 == 54) {
                           this.aQ[11] = 6;
                        } else if (var1 == 55) {
                           this.aQ[11] = 7;
                        } else if (var1 == 56) {
                           this.aQ[11] = 8;
                        } else if (var1 == 57) {
                           this.aQ[11] = 9;
                        }
                     } else if (this.ag == 5) {
                        if (var1 == 48) {
                           this.aQ[12] = 0;
                        } else if (var1 == 49) {
                           this.aQ[12] = 1;
                        } else if (var1 == 50) {
                           this.aQ[12] = 2;
                        } else if (var1 == 51) {
                           this.aQ[12] = 3;
                        } else if (var1 == 52) {
                           this.aQ[12] = 4;
                        } else if (var1 == 53) {
                           this.aQ[12] = 5;
                        } else if (var1 == 54) {
                           this.aQ[12] = 6;
                        } else if (var1 == 55) {
                           this.aQ[12] = 7;
                        } else if (var1 == 56) {
                           this.aQ[12] = 8;
                        } else if (var1 == 57) {
                           this.aQ[12] = 9;
                        }
                     } else if (this.ag == 6) {
                     }

                     if (this.ag == 6) {
                        if (this.aQ[9] != 9 || this.aQ[10] != 8 || this.aQ[11] != 1 || this.aQ[12] != 9 || var1 != 53) {
                           if (!this.k) {
                              this.bf = "PIN-код неверный";
                           }

                           this.try = false;
                           this.ag = 0;
                           this.aQ[11] = 0;
                           this.aQ[9] = 0;
                           this.aQ[10] = 0;
                           this.aQ[12] = 0;
                           this.aQ[13] = 0;
                           this.k = true;
                           return;
                        }

                        this.try = false;
                        this.dB = true;
                        this.ag = 0;
                        this.bf = "На дне пустого сейфа лежал маленький ключик";
                     }
                  }

               }
            }
         }
      }
   }

   private void for() {
      this.dd = 56;
      this.c3 = 56;
      this.byte = false;
      this.y = null;
      this.z = 1;
      this.new = 0;
      this.eb = 0;
      this.cp = false;
      this.dz = false;
      this.ce = 0;
      this.ad = 0;
      this.ds = false;
      this.ax = false;
      this.bM = null;
      this.aZ = false;
      this.bE = 0;
      this.dp = 0;
      this.a4 = 0;
      this.a3 = 0;
      this.a2 = 0;
      this.a1 = 0;
      this.a0 = 0;
      this.aX = 0;
      this.aW = 0;
      this.aV = 0;
      this.dX = false;
      this.c8 = false;
      this.a6 = false;
      this.cK = false;
      this.d2 = 0;
      this.dH = 0;
      this.eb = 0;
      this.bE = 0;
      this.aZ = false;
      this.cl = false;
      this.O = null;
      this.N = null;
      this.M = null;
      this.L = null;
      this.J = null;
      this.G = null;
      this.F = null;
      this.aq = null;
      this.ap = null;
      this.ao = null;
      this.an = null;
      this.al = null;
      this.ak = null;
      this.bS = null;
      this.bW = null;
      this.dP = null;
      this.da = null;
      this.dS = null;
      this.dq = null;
      this.char = null;
      this.U = null;
      this.ay = null;
      this.Q = null;
      this.C = null;
      this.else = null;
      this.B = null;
      this.cL = null;
      this.bu = null;
      this.T = null;
      this.u = null;
      this.aY = null;
      this.b4 = null;
      this.ec = null;
      this.d5 = null;
      this.bp = null;
      this.c1 = null;
      this.b6 = null;
      this.dk = null;
      this.ae = null;
      this.cH = null;
      this.cN = null;
      this.aP = null;
      this.bh = null;
      this.X = null;
      this.br = null;
      this.aw = null;
      this.bb = null;
      this.cM = null;
      this.aa = null;
      this.be = null;
      this.d9 = null;
      this.aJ = null;
      this.dJ = null;
      this.bY = null;
      this.dE = null;
      this.D = null;
      this.af = null;
      this.dj = false;
      this.case = false;
      this.dB = false;
      this.w = false;
      this.de = false;
      this.ei = false;
      this.Y = false;
      this.bg = false;
      this.c7 = false;
      this.I = false;
      this.cd = false;
      this.R = false;
      this.aS = false;
      this.bd = false;
      this.by = false;
      this.P = false;
      this.dC = false;
      this.a5 = false;
      this.dF = false;
      this.ai = false;
      this.aL = false;
      this.cr = false;
      this.v = false;
      this.bw = 0;
      this.cE = false;
      this.d4 = false;
      this.ea = false;
      this.aG = false;
      this.l = false;
      this.b5 = false;
      this.ba = false;
      this.cj = false;
      this.ch = false;
      this.cg = false;
      this.cf = false;
      this.W = false;
      this.d6 = false;
      this.dQ = false;
      this.c = false;
      this.b = false;
      this.b3 = false;
      this.r = false;
      this.do = false;
      this.ed = 0;
      this.E = 0;
      this.cq = 0;
      this.dR = 0;
      this.dr = 0;
      this.A = 0;
      this.aU = null;
      this.S = 0;
      this.at = false;
      this.ct = false;
      this.cs = false;
      this.q = false;
      this.c6 = false;
      this.bU = 0;
      this.c2 = false;
      this.ah = null;
      this.dl = false;
      this.d3 = false;
      this.n = 0;
      this.bZ = 1;
      this.bj = 0;
      this.b2 = false;
      this.b1 = false;
      this.g = false;
      this.try = false;
      this.aj = null;
      this.ci = null;
      this.bV = null;
      this.ag = 0;
      this.f = 0;
   }

   private void a(String var1) {
      if (var1.length() != 0) {
         this.cO.setFont(dD);
         this.cO.setColor(250, 223, 134);
         this.cO.drawString(var1, (d1 - dD.stringWidth(var1)) / 2, cm - 50 + 10, 0);
      }
   }

   private void do(String var1) {
      if (this.y == null) {
         this.y = this.if("anja");
      }

      if (var1.length() != 0) {
         this.cO.setFont(dD);
         this.cO.setColor(250, 223, 134);
         String var2 = "";
         String var3 = "";
         String var4 = "";
         int var5 = 0;

         try {
            label71:
            while(true) {
               var2 = var1.substring(0, var5 + 1);
               if (dD.stringWidth(var2) > d1 - 2 * this.db || var5 >= var1.length() - 1 || var1.charAt(var5) == '\n') {
                  var1 = var1.substring(var5 + 1, var1.length());
                  var5 = 0;
                  if (var1.length() > 0) {
                     while(true) {
                        var3 = var1.substring(0, var5 + 1);
                        if (dD.stringWidth(var3) > d1 - 2 * this.db || var5 >= var1.length() - 1 || var1.charAt(var5) == '\n') {
                           var1 = var1.substring(var5 + 1, var1.length());
                           var5 = 0;
                           break;
                        }

                        ++var5;
                     }
                  }

                  if (var1.length() > 0) {
                     while(true) {
                        var4 = var1.substring(0, var5 + 1);
                        if (dD.stringWidth(var4) > d1 - 2 * this.db || var5 >= var1.length() - 1 || var1.charAt(var5) == '\n') {
                           break label71;
                        }

                        ++var5;
                     }
                  }
                  break;
               }

               ++var5;
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }

         this.cO.drawString(var2, this.db - 4 - 3, cm - 55 + 10, 0);
         this.cO.drawString(var3, this.db - 4 - 3, cm - this.di - 4, 0);
         if (this.e % 2 == 0) {
            this.cO.drawImage(this.y, d1 - 12, 309, 0);
         } else {
            this.cO.drawImage(this.y, d1 - 12, 308, 0);
         }

      }
   }

   public String[] a(String var1, int var2, Font var3) {
      String[] var4 = null;
      Vector var5 = new Vector();
      int var6 = 0;
      int var7 = var1.length();
      boolean var8 = false;
      int var9 = 0;

      do {
         int var11 = 0;
         boolean var10 = false;

         int var12;
         for(var12 = var9; var9 < var7; ++var9) {
            if (var1.charAt(var9) == '\n') {
               ++var9;
               var10 = true;
               break;
            }

            var11 += var3.stringWidth("" + var1.charAt(var9));
            if (var11 > var2) {
               break;
            }
         }

         ++var6;
         if (var10) {
            var5.addElement(var1.substring(var12, var9 - 1));
         } else {
            var5.addElement(var1.substring(var12, var9));
         }
      } while(var9 < var7);

      var4 = new String[var6];
      var5.copyInto(var4);
      return var4;
   }

   private void a(Image var1, int var2, int var3) {
      this.a(var1, var2, var3, 0, 0, var1.getWidth(), var1.getHeight());
   }

   private void a(Image var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      for(int var8 = 0; var8 < this.c5.length; ++var8) {
         if (this.c5[var8] == null) {
            this.aQ[var2] = var3;

            try {
               this.c5[var8] = new c(var1, 1);
               this.c5[var8].case = var2;
               this.c5[var8].h[0][0] = var4;
               this.c5[var8].h[0][1] = var5;
               this.c5[var8].h[0][2] = var6;
               this.c5[var8].h[0][3] = var7;
               this.c5[var8].new = this.c5[var8].h[0][0];
               this.c5[var8].int = this.c5[var8].h[0][1];
               this.c5[var8].c = this.c5[var8].h[0][2];
               this.c5[var8].long = this.c5[var8].h[0][3];
               this.c5[var8].i = 130 - this.c5[var8].h[0][3] / 2 - 2 - 5 + 122;
            } catch (Exception var10) {
               var10.printStackTrace();
            }
            break;
         }
      }

   }

   private void a(int var1, int var2) {
      this.for = var1;
      this.null = var2;
      int var3 = 0;

      int var4;
      for(var4 = 0; var4 < this.c5.length && this.c5[var4] != null; ++var4) {
         if (this.c5[var4].case == var1) {
            var3 = var4;
            break;
         }
      }

      for(var4 = var3 + 1; var4 < this.c5.length; ++var4) {
         if (this.c5[var4] == null) {
            this.c5[var4 - 1] = null;
            break;
         }

         this.c5[var4 - 1] = this.c5[var4];
      }

      this.bf = "";
      this.aQ[var1] = var2;
      this.a8.goto = 0;
      this.cP = false;
      this.void = -1;
   }

   private void j() {
      if (this.void != -1 && this.c5[this.void] == null) {
         this.void = -1;
      }

      if (this.void == -1 && this.c5[0] != null) {
         this.void = 0;
      }

      if (this.void != -1 && this.c5[this.void] != null) {
         switch(this.c5[this.void].case) {
         case 1:
            this.m = "Крюк";
            break;
         case 2:
            this.m = "Используйте клавишу \"5\".";
            break;
         case 3:
            this.m = "Большой ключ";
            break;
         case 4:
            this.m = "Маленький ключ";
            break;
         case 5:
            this.m = "Книги";
            break;
         case 6:
            this.m = "Крышка";
            break;
         case 7:
            this.m = "Хреновина";
            break;
         case 8:
            this.m = "Ручка от крышки";
            break;
         case 9:
            this.m = "Монета";
            break;
         case 10:
            this.m = "Зеленый ключ";
            break;
         case 11:
            this.m = "Молоток";
            break;
         case 12:
            this.m = "Аккумулятор";
            break;
         case 13:
            this.m = "Веревка";
            break;
         case 14:
            this.m = "Золотой ключик";
            break;
         default:
            this.m = "Пожалуйста, выберите пункты.";
         }
      }

      if (this.p == null) {
         this.p = this.if("toolback1");
      }

      if (this.ab == null) {
         try {
            this.ab = new c(this.if("toolback"), 1);
            this.ab.i = 218;
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      this.ab.a(this.cO);
      if (this.void != -1) {
         this.c5[this.void].j = d1 / 2 - this.c5[this.void].h[0][2] / 2;
         this.c5[this.void].a(this.cO);
         if (this.void > 0 && this.c5[this.void - 1] != null) {
            this.c5[this.void - 1].j = d1 / 2 - 85 - this.c5[this.void - 1].h[0][2] / 2;
            this.c5[this.void - 1].a(this.cO);
         }

         if (this.void > 1 && this.c5[this.void - 2] != null) {
            this.c5[this.void - 2].j = d1 / 2 - 140 - this.c5[this.void - 2].h[0][2] / 2;
            this.c5[this.void - 2].a(this.cO);
         }

         if (this.void < this.c5.length - 1 && this.c5[this.void + 1] != null) {
            this.c5[this.void + 1].j = d1 / 2 + 85 - this.c5[this.void + 1].h[0][2] / 2;
            this.c5[this.void + 1].a(this.cO);
         }

         if (this.void < this.c5.length - 2 && this.c5[this.void + 2] != null) {
            this.c5[this.void + 2].j = d1 / 2 + 140 - this.c5[this.void + 2].h[0][2] / 2;
            this.c5[this.void + 2].a(this.cO);
         }

         this.cO.drawImage(this.p, (d1 - 76) / 2 - 3, this.ab.i + 4, 0);
      }

   }

   private void if(String var1, int var2, int var3, int var4) {
      this.cO.setColor(149, 147, 144);
      this.cO.drawString(var1, var2 + 1, var3, 0);
      this.cO.drawString(var1, var2 - 1, var3, 0);
      this.cO.drawString(var1, var2, var3 + 1, 0);
      this.cO.drawString(var1, var2, var3 - 1, 0);
      this.cO.setColor(0, 0, 0);
      this.cO.drawString(var1, var2, var3, 0);
   }

   private void if(int var1) {
      if (var1 == 49 && this.dz && this.g && this.c6) {
         this.dt = false;
         this.cP = false;
         this.a8.goto = 0;
      } else if (var1 != 52 && var1 != dU) {
         if (var1 != 54 && var1 != d) {
            if ((var1 == 53 || var1 == bc) && this.void != -1) {
               this.a8.goto = 0;
               this.cP = true;
               this.dt = false;
            }
         } else if (this.c5[this.void + 1] != null) {
            ++this.void;
         }
      } else if (this.void > 0) {
         --this.void;
      }

   }

   private void r() throws Exception {
   }
}
