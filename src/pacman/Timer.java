package pacman;

public class Timer {
  private int timePaused = 0;
  private double startTime;
  private int timeRan = 0;
  private boolean paused = false;
  private long unpauseTime;

  public Timer() {
    startTime = System.currentTimeMillis();
    timePaused = 0;
    timeRan = 0;
    paused = false;
  }
  public void run() {
    if (!paused) {
      if ((System.currentTimeMillis() - startTime - timePaused) / 1000 >
          timeRan) {
        timeRan++;
      }
    } else {
      if (unpauseTime < System.currentTimeMillis()) {
        paused = false;
      }
    }
  }
  public void pause(int seconds) {
    paused = true;
    timePaused = seconds * 1000;
    unpauseTime = System.currentTimeMillis() + seconds * 1000;
  }
  public int currentTime() { return timeRan; }
}
