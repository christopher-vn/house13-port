import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MySprite {
   public static final int ByCollideXY = 0;
   public static final int ByCollideArea = 1;
   public static final int ByCollideRadius = 2;
   public Image image;
   public int positionX;
   public int positionY;
   public int numFrames;
   public int currentFrame;
   public int[][] framesInfo;
   public boolean visible = true;
   public int DX;
   public int DY;
   public int collideX = 0;
   public int collideY = 0;
   public int collideWidth = 0;
   public int collideHeight = 0;
   public int collideArea = 0;
   public int collideRadius = 0;
   public int state = 0;
   public int fan = -1;
   public int fanNum = 0;

   public MySprite(Image image, int numFrames) throws Exception {
      this.image = image;
      this.numFrames = numFrames;
      this.framesInfo = new int[numFrames][4];

      for(int i = 0; i < numFrames; ++i) {
         this.framesInfo[i][0] = i * image.getWidth() / numFrames;
         this.framesInfo[i][1] = 0;
         this.framesInfo[i][2] = image.getWidth() / numFrames;
         this.framesInfo[i][3] = image.getHeight();
      }

      this.collideWidth = this.framesInfo[0][2];
      this.collideHeight = this.framesInfo[0][3];
      this.currentFrame = 0;
   }

   public void draw(Graphics g) {
      if (this.visible) {
         g.setClip(this.positionX, this.positionY, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3]);
         g.drawImage(this.image, this.positionX - this.framesInfo[this.currentFrame][0], this.positionY - this.framesInfo[this.currentFrame][1], 0);
         g.setClip(0, 0, MainCanvas.WIDTH, MainCanvas.HEIGHT);
      }

   }

   public void draw(Graphics g, int i) {
      if (this.visible) {
         switch(i) {
         case 0:
            g.setClip(this.positionX, this.positionY, this.framesInfo[this.currentFrame][3], this.framesInfo[this.currentFrame][2]);
            g.drawRegion(this.image, 0, 0, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3], 5, this.positionX - this.framesInfo[this.currentFrame][0], this.positionY - this.framesInfo[this.currentFrame][1], 0);
            g.setClip(0, 0, MainCanvas.WIDTH, MainCanvas.HEIGHT);
            break;
         case 1:
            g.setClip(this.positionX, this.positionY, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3]);
            g.drawRegion(this.image, 0, 0, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3], 3, this.positionX - this.framesInfo[this.currentFrame][0], this.positionY - this.framesInfo[this.currentFrame][1], 0);
            g.setClip(0, 0, MainCanvas.WIDTH, MainCanvas.HEIGHT);
            break;
         case 2:
            g.setClip(this.positionX, this.positionY, this.framesInfo[this.currentFrame][3], this.framesInfo[this.currentFrame][2]);
            g.drawRegion(this.image, 0, 0, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3], 6, this.positionX - this.framesInfo[this.currentFrame][0], this.positionY - this.framesInfo[this.currentFrame][1], 0);
            g.setClip(0, 0, MainCanvas.WIDTH, MainCanvas.HEIGHT);
            break;
         case 3:
            g.setClip(this.positionX, this.positionY, this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3]);
            g.drawRegion(this.image, this.framesInfo[this.currentFrame][0], this.framesInfo[this.currentFrame][1], this.framesInfo[this.currentFrame][2], this.framesInfo[this.currentFrame][3], 2, this.positionX, this.positionY, 0);
            g.setClip(0, 0, MainCanvas.WIDTH, MainCanvas.HEIGHT);
         }
      }

   }

   public void step() {
      if (this.visible) {
         this.positionX += this.DX;
         this.positionY += this.DY;
      }

   }

   public boolean collidesWith(MySprite sprite) {
      return this.collidesWith(sprite, 0);
   }

   public boolean collidesWith(MySprite sprite, int CheckType) {
      if (this.visible && sprite.visible) {
         int inX;
         int inY;
         if (CheckType == 1) {
            inX = sprite.positionX + sprite.framesInfo[sprite.currentFrame][2] - this.positionX > this.positionX + this.framesInfo[this.currentFrame][2] - sprite.positionX ? this.positionX + this.framesInfo[this.currentFrame][2] - sprite.positionX : sprite.positionX + sprite.framesInfo[sprite.currentFrame][2] - this.positionX;
            inY = sprite.positionY + sprite.framesInfo[sprite.currentFrame][3] - this.positionY > this.positionY + this.framesInfo[this.currentFrame][3] - sprite.positionY ? this.positionY + this.framesInfo[this.currentFrame][3] - sprite.positionY : sprite.positionY + sprite.framesInfo[sprite.currentFrame][3] - this.positionY;
            return inX > 0 && inY > 0 && inX * inY > this.collideArea + sprite.collideArea;
         } else if (CheckType == 0) {
            return sprite.positionX + sprite.collideX + sprite.collideWidth > this.positionX + this.collideX && this.positionX + this.collideX + this.collideWidth > sprite.positionX + sprite.collideX && sprite.positionY + sprite.collideY + sprite.collideHeight > this.positionY + this.collideY && this.positionY + this.collideY + this.collideHeight > sprite.positionY + sprite.collideY;
         } else {
            inX = Math.abs(this.positionX + this.framesInfo[this.currentFrame][2] / 2 - sprite.positionX - (sprite.framesInfo[sprite.currentFrame][2] + 1) / 2);
            inY = Math.abs(this.positionY + this.framesInfo[this.currentFrame][3] / 2 - sprite.positionY - (sprite.framesInfo[sprite.currentFrame][3] + 1) / 2);
            return inX * inX + inY * inY < (this.collideRadius + sprite.collideRadius) * (sprite.collideRadius + sprite.collideRadius);
         }
      } else {
         return false;
      }
   }

   public boolean collidesWith(Image image, int x, int y) {
      return this.collidesWith(image, x, y, 1);
   }

   public boolean collidesWith(Image image, int x, int y, int CheckType) {
      if (!this.visible) {
         return false;
      } else {
         int inX;
         int inY;
         if (CheckType == 1) {
            inX = x + image.getWidth() - this.positionX > this.positionX + this.framesInfo[this.currentFrame][2] - x ? this.positionX + this.framesInfo[this.currentFrame][2] - x : x + image.getWidth() - this.positionX;
            inY = y + image.getHeight() - this.positionY > this.positionY + this.framesInfo[this.currentFrame][3] - y ? this.positionY + this.framesInfo[this.currentFrame][3] - y : y + image.getHeight() - this.positionY;
            return inX > 0 && inY > 0 && inX * inY > this.collideArea;
         } else if (CheckType == 0) {
            return x + image.getWidth() > this.positionX && this.positionX + this.framesInfo[this.currentFrame][2] - this.collideX > x && y + image.getHeight() > this.positionY && this.positionY + this.framesInfo[this.currentFrame][3] - this.collideY > y;
         } else {
            inX = Math.abs(this.positionX + this.framesInfo[this.currentFrame][2] / 2 - x - (image.getWidth() + 1) / 2);
            inY = Math.abs(this.positionY + this.framesInfo[this.currentFrame][3] / 2 - y - (image.getHeight() + 1) / 2);
            return inX * inX + inY * inY < this.collideRadius * this.collideRadius;
         }
      }
   }
}
