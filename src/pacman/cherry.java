package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;


public class cherry extends Gameobjects {

  public cherry(int x, int y) {
    Gameobjects.gameObjects.add(this);
    this.x = x;
    this.y = y;
    this.rect = new Rectangle(x, y, 16, 16);
    try {
      this.image = ImageIO.read(getClass().getResource("images/cherry.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void draw(Graphics g) {

    g.drawImage(image, (int)x - 5, (int)y - 5, (int)x + 22, (int)y + 22, 0, 0,
                32, 32, null);
    g.drawImage(image, (int)24 * 16, (int)34 * 16, (int)24 * 16 + 22,
                (int)34 * 16 + 22, 0, 0, 32, 32, null);
  }

  @Override
  public void tick() {
  }
}
