import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class c {
   public static final int for = 0;
   public static final int g = 1;
   public static final int a = 2;
   public Image void;
   public int j;
   public int i;
   public int d;
   public int goto;
   public int[][] h;
   public boolean try = true;
   public int else;
   public int char;
   public int new = 0;
   public int int = 0;
   public int c = 0;
   public int long = 0;
   public int byte = 0;
   public int do = 0;
   public int case = 0;
   public int f = 0;
   public int e = 0;
   public int b = 0;
   public int k = 0;
   public int if = 0;
   public int null = 0;

   public c(Image var1, int var2) throws Exception {
      this.void = var1;
      this.d = var2;
      this.h = new int[var2][4];

      for(int var3 = 0; var3 < var2; ++var3) {
         this.h[var3][0] = var3 * var1.getWidth() / var2;
         this.h[var3][1] = 0;
         this.h[var3][2] = var1.getWidth() / var2;
         this.h[var3][3] = var1.getHeight();
      }

      this.goto = 0;
   }

   public void a(Graphics var1) {
      if (this.try) {
         if (this.d != 1) {
            var1.setClip(this.j, this.i, this.h[this.goto][2], this.h[this.goto][3]);
            var1.drawImage(this.void, this.j - this.h[this.goto][0], this.i - this.h[this.goto][1], 0);
            var1.setClip(0, 0, b.d1, b.cm);
         } else {
            var1.drawImage(this.void, this.j - this.h[this.goto][0], this.i - this.h[this.goto][1], 0);
         }
      }

   }

   public void a() {
      if (this.try) {
         this.j += this.else;
         this.i += this.char;
      }

   }

   public boolean a(c var1) {
      return this.a((c)var1, 0);
   }

   public boolean a(c var1, int var2) {
      if (this.try && var1.try) {
         int var3;
         int var4;
         if (var2 == 1) {
            var3 = var1.j + var1.h[var1.goto][2] - this.j > this.j + this.h[this.goto][2] - var1.j ? this.j + this.h[this.goto][2] - var1.j : var1.j + var1.h[var1.goto][2] - this.j;
            var4 = var1.i + var1.h[var1.goto][3] - this.i > this.i + this.h[this.goto][3] - var1.i ? this.i + this.h[this.goto][3] - var1.i : var1.i + var1.h[var1.goto][3] - this.i;
            return var3 > 0 && var4 > 0 && var3 * var4 > this.byte + var1.byte;
         } else if (var2 == 0) {
            return var1.j + var1.new + (var1.c == 0 ? var1.h[var1.goto][2] - var1.new : var1.c) > this.j + this.new && this.j + this.new + (this.c == 0 ? this.h[this.goto][2] - this.new : this.c) > var1.j + var1.new && var1.i + var1.int + (var1.long == 0 ? var1.h[var1.goto][3] - var1.int : var1.long) > this.i + this.int && this.i + this.int + (this.long == 0 ? this.h[this.goto][3] - this.int : this.long) > var1.i + var1.int;
         } else {
            var3 = Math.abs(this.j + this.h[this.goto][2] / 2 - var1.j - (var1.h[var1.goto][2] + 1) / 2);
            var4 = Math.abs(this.i + this.h[this.goto][3] / 2 - var1.i - (var1.h[var1.goto][3] + 1) / 2);
            return var3 * var3 + var4 * var4 < (this.do + var1.do) * (var1.do + var1.do);
         }
      } else {
         return false;
      }
   }

   public boolean a(Image var1, int var2, int var3) {
      return this.a(var1, var2, var3, 1);
   }

   public boolean a(Image var1, int var2, int var3, int var4) {
      if (!this.try) {
         return false;
      } else {
         int var5;
         int var6;
         if (var4 == 1) {
            var5 = var2 + var1.getWidth() - this.j > this.j + this.h[this.goto][2] - var2 ? this.j + this.h[this.goto][2] - var2 : var2 + var1.getWidth() - this.j;
            var6 = var3 + var1.getHeight() - this.i > this.i + this.h[this.goto][3] - var3 ? this.i + this.h[this.goto][3] - var3 : var3 + var1.getHeight() - this.i;
            return var5 > 0 && var6 > 0 && var5 * var6 > this.byte;
         } else if (var4 == 0) {
            return var2 + var1.getWidth() > this.j && this.j + (this.c == 0 ? this.h[this.goto][2] : this.c) - this.new > var2 && var3 + var1.getHeight() > this.i && this.i + this.h[this.goto][3] - this.int > var3;
         } else {
            var5 = Math.abs(this.j + this.h[this.goto][2] / 2 - var2 - (var1.getWidth() + 1) / 2);
            var6 = Math.abs(this.i + this.h[this.goto][3] / 2 - var3 - (var1.getHeight() + 1) / 2);
            return var5 * var5 + var6 * var6 < this.do * this.do;
         }
      }
   }

   public boolean a(int[] var1) {
      return this.a((int[])var1, 0);
   }

   public boolean a(int[] var1, int var2) {
      if (!this.try) {
         return false;
      } else {
         int var3;
         int var4;
         if (var2 == 1) {
            var3 = var1[0] + var1[2] - this.j > this.j + this.h[this.goto][2] - var1[0] ? this.j + this.h[this.goto][2] - var1[0] : var1[0] + var1[2] - this.j;
            var4 = var1[1] + var1[3] - this.i > this.i + this.h[this.goto][3] - var1[1] ? this.i + this.h[this.goto][3] - var1[1] : var1[1] + var1[3] - this.i;
            return var3 > 0 && var4 > 0 && var3 * var4 > this.byte;
         } else if (var2 == 0) {
            return var1[0] + var1[2] - 1 > this.j && this.j + (this.c == 0 ? this.h[this.goto][2] : this.c) + this.new > var1[0] && var1[1] + var1[3] - 25 > this.i && this.i + (this.long == 0 ? this.h[this.goto][3] : this.long) + this.int > var1[1];
         } else {
            var3 = Math.abs(this.j + this.h[this.goto][2] / 2 - var1[0] - (var1[2] + 1) / 2);
            var4 = Math.abs(this.i + this.h[this.goto][3] / 2 - var1[1] - (var1[3] + 1) / 2);
            return var3 * var3 + var4 * var4 < this.do * this.do;
         }
      }
   }
}
