package pacman;

import java.awt.image.BufferedImage;

public abstract class ghost extends Gameobjects {
  public static ghost[] ghosts = new ghost[4];
  public boolean isBlue = false;
  public static boolean scatter = true;
  public int targetX;
  public int targetY;
  public boolean turnAround = true;
  public boolean dead = false;
  public static BufferedImage ghostSpriteSheet;
  public boolean start = false; // will tell the ghost when they can start
  public movement direction = movement.none; // which way they are moving
  public String colour = "";
  public void currentSprite(ghost self) { // finding which way it should face
    if (self.dead) {
      self.spriteSheetY = 5;
    } else if (isBlue) {
      self.spriteSheetY = 4;
    } else if (colour.equals("red")) {

      self.spriteSheetY = 2;
    } else if (colour.equals("blue")) {
      self.spriteSheetY = 1;
    } else if (colour.equals("purple")) {
      self.spriteSheetY = 0;
    } else {
      self.spriteSheetY = 3;
    }
    if (self.direction == movement.none) {
      if (colour.equals("red")) {
        self.spriteSheetX = 0;
      } else if (colour.equals("blue")) {
        self.spriteSheetX = 3;
      } else if (colour.equals("purple")) {
        self.spriteSheetX = 1;
      } else {
        self.spriteSheetX = 2;
      }
    } else if (self.direction == movement.left) {
      self.spriteSheetX = 3;
    } else if (self.direction == movement.down) {
      self.spriteSheetX = 1;
    } else if (self.direction == movement.up) {
      self.spriteSheetX = 2;
    } else {
      self.spriteSheetX = 0;
    }
  }
  public static void turnBlue() {
    // Turn all the ghost blue
    for (ghost g : ghosts) {
      if (g != null) {
        g.isBlue = true;
      }
    }
  }
  public static void turnNormal() {
    for (ghost g : ghosts) {
      if (g != null) {
        g.isBlue = false;
      }
    }
  }
}
