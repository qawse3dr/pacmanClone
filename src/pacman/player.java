package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class player extends Gameobjects {
  public Rectangle fullRect;
  public static AudioInputStream waka = null; // sound when eats a pill
  public static Clip clip = null;
  public static int currentX, currentY; // used for ghost
  public static movement currentMove = movement.none;
  public static movement move = movement.none;
  public static int lives = 2;
  public player(int x, int y) {

    if (clip == null) {
      try {
        clip = AudioSystem.getClip();
        waka = AudioSystem.getAudioInputStream(
            getClass().getResource("sounds/waka.wav"));
        clip.open(waka);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    Gameobjects.gameObjects.add(this);
    this.x = x;
    this.y = y + 3 * 16;
    speed = 4;
    availableMoves = new movement[] {movement.left, movement.right, movement.up,
                                     movement.down};
    spriteSheetX = 0;
    spriteSheetY = 0;
    this.fullRect = new Rectangle(x, y, 16, 16);
    this.rect = new Rectangle(x, y, 16, 16);
    try {
      this.image =
          ImageIO.read(getClass().getResource("images/pacmanSpriteSheet.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(image, (int)x - 6, (int)y - 6, (int)x + 22, (int)y + 22,
                spriteSheetX * 32, spriteSheetY * 32, spriteSheetX * 32 + 31,
                spriteSheetY * 32 + 31, null);
    if (lives > 0) {
      g.drawImage(image, 32, 34 * 16, 64, 34 * 16 + 32, 32, 32, 64, 64, null);
    }
    if (lives > 1) {
      g.drawImage(image, 64, 34 * 16, 64 + 32, 34 * 16 + 32, 32, 32, 64, 64,
                  null);
    }
  }

  @Override
  public void tick() {
    currentX = (int)x;
    currentY = (int)y;
    // portals

    if (x < -32) {
      x = 500;
    } else if (x > 500) {
      x = 0 - 32;
    }
    this.rect.x = (int)this.x;
    this.rect.y = (int)this.y;
    this.fullRect.x = (int)this.x;
    this.fullRect.y = (int)this.y;
    if (currentMove != movement.none) {
      walkTimer += 2;
    }
    if (walkTimer < 10) {
      spriteSheetX = 1;
    } else if (walkTimer < 20) {
      spriteSheetX = 2;
    } else if (walkTimer < 30) {
      spriteSheetX = 1;
    } else if (walkTimer < 40) {
      spriteSheetX = 0;
    } else {
      walkTimer = 0;
    }
    // checks if you are getting a pill
    for (int pillIndex = 0; pillIndex < pill.pills.size(); pillIndex++) {

      if (fullRect.intersects(pill.pills.get(pillIndex).rect)) {
        Gameobjects.gameObjects.remove(pill.pills.get(pillIndex));
        // plays waka sound
        try {
          clip.loop(1);
        } catch (Exception e) {
          ;
        }
        if (pill.pills.get(pillIndex).isBig) {
          pacman.score += 50;
          ghost.turnBlue();
          pacman.timer.pause(4);
          Timer timer = new Timer();
          timer.schedule(new TimerTask() {
            @Override
            public void run() {
              // TODO Auto-generated method stub
              ghost.turnNormal();
            }
          }, 4000);
        } else {
          pacman.score += 10;
        }
        pill.pills.remove(pill.pills.get(pillIndex));
        pill.numOfPills--;
      }
    }
    movement opMove; // the opposite direction that you are moving
    if (currentMove == movement.up) {
      opMove = movement.down;
    } else if (currentMove == movement.none) {
      opMove = movement.none;
    } else if (currentMove == movement.down) {
      opMove = movement.down;
    } else if (currentMove == movement.left) {
      opMove = movement.right;
    } else {
      opMove = movement.left;
    }
    availableMoves =
        new movement[] {currentMove, opMove}; // directions you can move
    for (int pointIndex = 0; pointIndex < turningPoint.turningPoints.size();
         pointIndex++) { // finds if you can go any other directions

      if (rect.contains(turningPoint.turningPoints.get(pointIndex).rect)) {

        availableMoves =
            turningPoint.turningPoints.get(pointIndex).availableMoves;
        boolean keepMoving = false;
        for (movement dir : availableMoves) {
          if (dir == currentMove) {
            keepMoving = true;
          }
        }
        if (!keepMoving) {
          currentMove = movement.none;
        }
      }
    }
    // checks if it can change directions

    for (movement moveDir : availableMoves) {

      if (moveDir == move) {
        currentMove = move;
      }
    }
    if (currentMove == movement.none) {
      ;
    } else if (currentMove == movement.left) {

      this.x -= speed;
      spriteSheetY = 1;

    } else if (currentMove == movement.right) {

      this.x += speed;
      spriteSheetY = 0;

    } else if (currentMove == movement.up) {

      this.y -= speed;
      spriteSheetY = 2;

    } else if (currentMove == movement.down) {
      this.y += speed;
      spriteSheetY = 3;
    }

    for (int objIndex = 0; objIndex < Gameobjects.gameObjects.size();
         objIndex++) {
      if (Gameobjects.gameObjects.get(objIndex) instanceof cherry) {
        if (Gameobjects.gameObjects.get(objIndex).rect.intersects(rect)) {
          Gameobjects.gameObjects.remove(Gameobjects.gameObjects.get(objIndex));
          pacman.score += 100;
        }
      }
    }
    if (ghost.ghosts[0] != null) {
      for (int ghostIndex = 0; ghostIndex < 4; ghostIndex++) {
        if (ghost.ghosts[ghostIndex].rect.intersects(this.rect)) {
          if (ghost.ghosts[ghostIndex].isBlue) {
            ghost.ghosts[ghostIndex].dead = true;
            pacman.score += 200;
          } else if (ghost.ghosts[ghostIndex].dead) {
            continue;
          } else {

            lives--;
            if (lives == -1) {
              pacman.lose = true;
            }
            if (!pacman.lose) {
              pacman.reset();
            }
          }
        }
      }
    }
  }
}
