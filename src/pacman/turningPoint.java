package pacman;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class turningPoint extends Gameobjects {

  public static List<turningPoint> turningPoints = new ArrayList<turningPoint>();
  public turningPoint(int x, int y, movement[] posMove) {
    availableMoves = posMove;
    this.x = x;
    this.y = y + 3 * 16;
    this.rect = new Rectangle((int)this.x, (int)this.y, 16, 16);
    turningPoints.add(this);
    Gameobjects.gameObjects.add(this);
  }

  @Override
  public void draw(Graphics g) { }

  @Override
  public void tick() {}
}
