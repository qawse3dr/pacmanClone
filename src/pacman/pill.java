package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class pill extends Gameobjects {

  public static int numOfPills =
      -1; // set at a default of -1 so game doesn't when game launches
  public boolean isBig = false;
  public static List<pill> pills = new ArrayList<pill>();
  public pill(int x, int y, boolean isBig) {

    if (numOfPills > 0) {
      numOfPills++;
    } else {
      numOfPills = 1;
    }
    this.isBig = isBig;
    pills.add(this);
    Gameobjects.gameObjects.add(this);
    this.x = x;
    this.y = y;
    rect = new Rectangle(x, y, 10, 10);
  }
  @Override
  public void draw(Graphics g) {
    if (!isBig) {
      g.drawImage(mapBlock.mapSpriteSheet, (int)x, (int)y, (int)x + 16,
                  (int)y + 16, 16, 6 * 8, 24, 6 * 8 + 8, null);
    } else {
      g.drawImage(mapBlock.mapSpriteSheet, (int)x, (int)y, (int)x + 16,
                  (int)y + 16, 24, 6 * 8, 32, 6 * 8 + 8, null);
    }
  }

  @Override
  public void tick() {}
}
