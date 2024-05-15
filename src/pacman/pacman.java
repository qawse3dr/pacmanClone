package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class pacman extends Canvas implements Runnable {
  private static final long serialVersionUID = 1L;
  public static final String TITLE = "Pacman";
  public static final int HEIGHT = 606;
  public static final int WIDTH = 455;
  private boolean win = false;
  public static int score = 0;
  public static boolean lose = false;
  private boolean running;
  private static BufferedImage jeff;
  public static Timer timer;
  public static boolean pause = false;

  private void tick() {
    if (!win) {
      Gameobjects.tickAll();

      if (timer != null) {
        timerChecker();
        timer.run();
      }
    }

    if (pill.numOfPills == 0) {

      win = true;
    } else if (pill.numOfPills == 100) {
      new cherry(13 * 16, 20 * 16);
    }
  }

  public static void reset() { // resets the game after a death
    for (int objIndex = 0; objIndex < Gameobjects.gameObjects.size();
         objIndex++) {
      if (Gameobjects.gameObjects.get(objIndex) instanceof player) {
        Gameobjects.gameObjects.get(objIndex).x = 13 * 16;
        Gameobjects.gameObjects.get(objIndex).y = 416;
        player.currentMove = movement.none;
        player.move = movement.none;
      } else if (Gameobjects.gameObjects.get(objIndex) instanceof Binky) {

        Gameobjects.gameObjects.get(objIndex).x = 13 * 16;
        Gameobjects.gameObjects.get(objIndex).y = 224;
      } else if (Gameobjects.gameObjects.get(objIndex) instanceof Pinky) {
        Gameobjects.gameObjects.get(objIndex).x = 13 * 16;
        Gameobjects.gameObjects.get(objIndex).y = 272;
      } else if (Gameobjects.gameObjects.get(objIndex) instanceof Inky) {
        Gameobjects.gameObjects.get(objIndex).x = 11 * 16;
        Gameobjects.gameObjects.get(objIndex).y = 272;
      } else if (Gameobjects.gameObjects.get(objIndex) instanceof Clyde) {
        Gameobjects.gameObjects.get(objIndex).x = 15 * 16;
        Gameobjects.gameObjects.get(objIndex).y = 272;
      }
    }
    initTimer();
    ghost.ghosts[2].start = false;
    ghost.ghosts[3].start = false;
    for (ghost g : ghost.ghosts) {
      g.direction = movement.none;
      g.turnAround = true;
      g.dead = false;
    }
  }

  public static void timerChecker() {
    if (timer.currentTime() == 84 || timer.currentTime() == 7 ||
        timer.currentTime() == 34 || timer.currentTime() == 59) {
      ghost.scatter = false;
    } else if (timer.currentTime() == 27 || timer.currentTime() == 54 ||
               timer.currentTime() == 79) {
      ghost.scatter = true;
      for (ghost g : ghost.ghosts) {
        g.turnAround = true;
      }
    } else if (timer.currentTime() == 2) {
      ghost.ghosts[2].start = true;
    } else if (timer.currentTime() == 4) {
      ghost.ghosts[3].start = true;
    }
  }

  public static void initTimer() {
    timer = new Timer();
    ghost.scatter = true;
  }

  // this will run when the game starts and set everything up
  private static void init() {
    try {
      jeff = ImageIO.read(pacman.class.getResource("images/jeft.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // new Grid();//making the grid
    new mapMaker();
    new Binky(13 * 16, 176);
    new Pinky(13 * 16, 272);
    new Clyde(15 * 16, 272);
    new Inky(11 * 16, 272);
    new player(13 * 16, 368); //,making the player
    // setting all the tasks for when the game starts
    initTimer();
    // making the points where play and ghost can turn
    // first row
    new turningPoint(16, 16, new movement[] {movement.right, movement.down});
    new turningPoint(
        96, 16, new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(12 * 16, 16,
                     new movement[] {movement.left, movement.down});
    new turningPoint(16 * 15, 16,
                     new movement[] {movement.right, movement.down});
    new turningPoint(
        21 * 16, 16,
        new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(26 * 16, 16,
                     new movement[] {movement.left, movement.down});
    // second row
    new turningPoint(
        16, 80, new movement[] {movement.up, movement.right, movement.down});
    new turningPoint(96, 80,
                     new movement[] {movement.up, movement.right, movement.left,
                                     movement.down});
    new turningPoint(
        9 * 16, 80,
        new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(
        12 * 16, 80,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(
        15 * 16, 80,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(
        18 * 16, 80,
        new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(21 * 16, 80,
                     new movement[] {movement.left, movement.right,
                                     movement.down, movement.up});
    new turningPoint(
        26 * 16, 80,
        new movement[] {movement.left, movement.down, movement.up});
    // third row
    new turningPoint(16, 128, new movement[] {movement.right, movement.up});
    new turningPoint(
        96, 128, new movement[] {movement.left, movement.down, movement.up});
    new turningPoint(9 * 16, 128, new movement[] {movement.right, movement.up});
    new turningPoint(12 * 16, 128,
                     new movement[] {movement.left, movement.down});
    new turningPoint(16 * 15, 128,
                     new movement[] {movement.down, movement.right});
    new turningPoint(18 * 16, 128, new movement[] {movement.left, movement.up});
    new turningPoint(
        21 * 16, 128,
        new movement[] {movement.down, movement.right, movement.up});
    new turningPoint(26 * 16, 128, new movement[] {movement.left, movement.up});
    // forth row
    new turningPoint(9 * 16, 176,
                     new movement[] {movement.right, movement.down});
    new turningPoint(
        12 * 16, 176,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(13 * 16, 176,
                     new movement[] {movement.left, movement.right});

    new turningPoint(
        15 * 16, 176,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(18 * 16, 176,
                     new movement[] {movement.left, movement.down});
    // fifth row
    new turningPoint(96, 224,
                     new movement[] {movement.left, movement.up, movement.right,
                                     movement.down});
    new turningPoint(
        9 * 16, 224,
        new movement[] {movement.left, movement.up, movement.down});
    new turningPoint(11 * 16, 224, new movement[] {movement.right});
    new turningPoint(13 * 16, 224, new movement[] {movement.up});
    new turningPoint(15 * 16, 224, new movement[] {movement.left});
    new turningPoint(
        18 * 16, 224,
        new movement[] {movement.up, movement.right, movement.down});
    new turningPoint(21 * 16, 224,
                     new movement[] {movement.left, movement.up, movement.right,
                                     movement.down});
    // 6th row
    new turningPoint(
        9 * 16, 272,
        new movement[] {movement.up, movement.right, movement.down});
    new turningPoint(
        18 * 16, 272,
        new movement[] {movement.left, movement.up, movement.down});
    // 7th row
    new turningPoint(16, 320, new movement[] {movement.right, movement.down});
    new turningPoint(96, 320,
                     new movement[] {movement.left, movement.up, movement.right,
                                     movement.down});
    new turningPoint(
        9 * 16, 320,
        new movement[] {movement.left, movement.up, movement.right});
    new turningPoint(12 * 16, 320,
                     new movement[] {movement.left, movement.down});
    new turningPoint(15 * 16, 320,
                     new movement[] {movement.right, movement.down});
    new turningPoint(
        18 * 16, 320,
        new movement[] {movement.left, movement.up, movement.right});
    new turningPoint(21 * 16, 320,
                     new movement[] {movement.left, movement.up, movement.right,
                                     movement.down});
    new turningPoint(26 * 16, 320,
                     new movement[] {movement.left, movement.down});

    // 8th row
    new turningPoint(16, 368, new movement[] {movement.up, movement.right});
    new turningPoint(48, 368, new movement[] {movement.left, movement.down});
    new turningPoint(
        96, 368, new movement[] {movement.up, movement.down, movement.right});
    new turningPoint(
        9 * 16, 368,
        new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(
        12 * 16, 368,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(13 * 16, 368,
                     new movement[] {movement.left, movement.right});
    new turningPoint(
        15 * 16, 368,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(
        18 * 16, 368,
        new movement[] {movement.left, movement.right, movement.down});
    new turningPoint(
        21 * 16, 368,
        new movement[] {movement.left, movement.down, movement.up});
    new turningPoint(24 * 16, 368,
                     new movement[] {movement.down, movement.right});
    new turningPoint(26 * 16, 368, new movement[] {movement.left, movement.up});
    // 9th row
    new turningPoint(16, 416, new movement[] {movement.down, movement.right});
    new turningPoint(
        48, 416, new movement[] {movement.up, movement.right, movement.left});
    new turningPoint(96, 416, new movement[] {movement.up, movement.left});
    new turningPoint(9 * 16, 416, new movement[] {movement.right, movement.up});
    new turningPoint(12 * 16, 416,
                     new movement[] {movement.down, movement.left});
    new turningPoint(15 * 16, 416,
                     new movement[] {
                         movement.down,
                         movement.right,
                     });
    new turningPoint(18 * 16, 416, new movement[] {movement.left, movement.up});
    new turningPoint(21 * 16, 416,
                     new movement[] {movement.right, movement.up});
    new turningPoint(
        24 * 16, 416,
        new movement[] {movement.up, movement.left, movement.right});
    new turningPoint(26 * 16, 416,
                     new movement[] {movement.left, movement.down});
    // 10th row
    new turningPoint(16, 464, new movement[] {movement.right, movement.up});
    new turningPoint(
        12 * 16, 464,
        new movement[] {movement.left, movement.right, movement.up});
    new turningPoint(
        15 * 16, 464,
        new movement[] {movement.left, movement.up, movement.right});
    new turningPoint(26 * 16, 464, new movement[] {movement.left, movement.up});
    try {
      mapBlock.mapSpriteSheet =
          ImageIO.read(pacman.class.getResource("images/MapSpriteSheet.png"));
      ghost.ghostSpriteSheet =
          ImageIO.read(pacman.class.getResource("images/ghostSpriteSheet.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {

      createBufferStrategy(3);

      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    if (!win && !lose) {
      Gameobjects.drawSprites(g);
      g.setColor(Color.blue);
      g.setFont(new Font("Arial", Font.BOLD, 16));
      g.drawString(String.valueOf(score), 5 * 16, 32);
      g.setFont(new Font("Arial", Font.BOLD, 24));
      g.drawString("HIGH  SCORE", 9 * 16, 20);
    } else if (lose) {
      g.setColor(Color.YELLOW);
      g.drawImage(jeff, 0, 0, WIDTH, HEIGHT, 0, 0, jeff.getWidth(),
                  jeff.getHeight(), null);
      g.setFont(new Font("Arial", Font.BOLD, 36));
      g.drawString("You Lose :(", WIDTH / 2 - 64, HEIGHT / 2 - 16);
    } else {
      g.setColor(Color.YELLOW);

      g.drawImage(jeff, 0, 0, WIDTH, HEIGHT, 0, 0, jeff.getWidth(),
                  jeff.getHeight(), null);
      g.setFont(new Font("Arial", Font.BOLD, 36));
      g.drawString("You Win!", WIDTH / 2 - 64, HEIGHT / 2 - 16);
    }
    g.setColor(Color.cyan);

    g.dispose();
    bs.show();
  }
  private void start() {

    if (running)
      return;
    running = true;
    new Thread(this, "game_Thread").start();
  }

  public void stop() {
    if (!running)
      return;
    running = false;
  }
  public void run() {
    double target = 30.0;
    double nsPerTick = 1000000000.0 / target;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.0;
    int fps = 0;
    int tps = 0;

    boolean canRender = false;
    while (running) {
      long now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      if (unprocessed >= 1.0) {
        tick();
        unprocessed--;
        tps++;
        canRender = true;
      } else
        canRender = false;
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (canRender) {
        render();
        fps++;
      }

      if (System.currentTimeMillis() - 1000 > timer) {
        timer += 1000;
        fps = 0;
        tps = 0;
      }
    }

    System.exit(0);
  }
  public static void main(String[] args) {
    final pacman Pacman = new pacman();
    JFrame frame = new JFrame(TITLE);
    frame.add(Pacman);
    frame.setSize(WIDTH, HEIGHT);
    frame.setResizable(false);
    frame.setFocusable(true);

    Events event = new Events(Pacman);
    frame.addKeyListener(event);

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.err.println("Exiting Game");
        Pacman.stop();
      }
    });

    frame.setLocationRelativeTo(frame);
    frame.setVisible(true);
    frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.pack();

    frame.setResizable(false);
    frame.requestFocus();

    Pacman.start();
    init(); // sets game up
  }
}
