package pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class mapBlock extends Gameobjects {
  private int spriteX, spriteY;
  public static BufferedImage mapSpriteSheet;
  public mapBlock(int x, int y, int spriteX, int spriteY) {
    Gameobjects.gameObjects.add(this);
    this.x = x;
    this.y = y;
    this.spriteX = spriteX;
    this.spriteY = spriteY;
  }
  @Override
  public void draw(Graphics g) {
    g.drawImage(mapSpriteSheet, (int)x, (int)y, (int)x + 16, (int)y + 16,
                8 * spriteX, 8 * spriteY, 8 * spriteX + 8, 8 + spriteY * 8,
                null);
  }

  @Override
  public void tick() {
  }
}
