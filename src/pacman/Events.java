package pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Events extends KeyAdapter {
  pacman Pacman;
  public Events(pacman Pacman) { this.Pacman = Pacman; }
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case 65:
    case 37:
      player.move = movement.left;
      break;
    case 68:
    case 39:
      player.move = movement.right;
      break;
    case 87:
    case 38:
      player.move = movement.up;
      break;
    case 83:
    case 40:
      player.move = movement.down;
      break;
    }
  }
}
