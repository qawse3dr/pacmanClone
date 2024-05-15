package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Gameobjects {
  public static List<Gameobjects> gameObjects = new ArrayList<Gameobjects>();
  public double x, y; // x and y of object
  public int spriteSheetX,
      spriteSheetY;      // This will keep track of what sprite should be shown
  public Rectangle rect; // Their hit box
  public BufferedImage image; // Their image
  public byte walkTimer = 0;
  public int getx() { return (int)x; } // getter and setters
  public movement[] availableMoves;
  public int gety() { return (int)y; }
  public double speed;
  public void setx(int x) { this.x = x; }
  public void sety(int y) { this.y = y; }
  public Rectangle getRect() { return rect; }
  public void setRect(Rectangle rect) { this.rect = rect; }
  public abstract void draw(Graphics g);
  public abstract void tick();
  public static void drawSprites(Graphics g) {
    for (int spriteIndex = 0; spriteIndex < gameObjects.size(); spriteIndex++) {
      gameObjects.get(spriteIndex).draw(g); // draws all sprites
    }
  }
  public static void tickAll() {
    for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
      gameObjects.get(objIndex).tick();
    }
  }
  public static boolean colide(Gameobjects object) {
    for (Gameobjects hitboxs : gameObjects) {
      if (hitboxs.rect.intersects(object.rect) && hitboxs != object) {
        return true;
      }
    }
    return false;
  }
}
