package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class mapMaker {
  public BufferedImage mapImage;
  public mapMaker() {
    try {
      mapImage = ImageIO.read(getClass().getResource("images/mapimages.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int mapX = 0; mapX < mapImage.getWidth(); mapX++) {
      for (int mapY = 0; mapY < mapImage.getHeight(); mapY++) {
        Color tempColour = new Color(mapImage.getRGB(mapX, mapY));
        // left upper outer
        if (tempColour.getRed() == 0 && tempColour.getGreen() == 0 &&
            tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 0, 0);
        }
        // right upper outer
        else if (tempColour.getRed() == 64 && tempColour.getGreen() == 64 &&
                 tempColour.getBlue() == 64) {
          new mapBlock(mapX * 16, mapY * 16, 1, 0);
        } // left lower outer
        else if (tempColour.getRed() == 128 && tempColour.getGreen() == 128 &&
                 tempColour.getBlue() == 128) {
          new mapBlock(mapX * 16, mapY * 16, 0, 1);
        } // right lower outer
        else if (tempColour.getRed() == 255 && tempColour.getGreen() == 106 &&
                 tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 1, 1);
        } // left wall outer
        else if (tempColour.getRed() == 255 && tempColour.getGreen() == 216 &&
                 tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 2, 1);
        } // upper wall outer
        else if (tempColour.getRed() == 182 && tempColour.getGreen() == 255 &&
                 tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 2, 0);
        } // right wall outer
        else if (tempColour.getRed() == 0 && tempColour.getGreen() == 255 &&
                 tempColour.getBlue() == 255) {
          new mapBlock(mapX * 16, mapY * 16, 3, 1);
        } // bottom wall outer
        else if (tempColour.getRed() == 0 && tempColour.getGreen() == 38 &&
                 tempColour.getBlue() == 255) {
          new mapBlock(mapX * 16, mapY * 16, 3, 0);
        } // inner wall thin top
        else if (tempColour.getRed() == 128 && tempColour.getGreen() == 128 &&
                 tempColour.getBlue() == 128) {
          new mapBlock(mapX * 16, mapY * 16, 0, 1);
        } // inner wall thin right
        else if (tempColour.getRed() == 127 && tempColour.getGreen() == 255 &&
                 tempColour.getBlue() == 142) {
          new mapBlock(mapX * 16, mapY * 16, 2, 5);
        } // inner wall thin left
        else if (tempColour.getRed() == 80 && tempColour.getGreen() == 63 &&
                 tempColour.getBlue() == 127) {
          new mapBlock(mapX * 16, mapY * 16, 3, 4);
        }
        // inner wall tin bottom
        else if (tempColour.getRed() == 255 && tempColour.getGreen() == 127 &&
                 tempColour.getBlue() == 127) {
          new mapBlock(mapX * 16, mapY * 16, 3, 5);
        } else if (tempColour.getRed() == 63 && tempColour.getGreen() == 100 &&
                   tempColour.getBlue() == 127) {
          new mapBlock(mapX * 16, mapY * 16, 2, 4);
        }
        // Corner top left
        else if (tempColour.getRed() == 127 && tempColour.getGreen() == 255 &&
                 tempColour.getBlue() == 255) {
          new mapBlock(mapX * 16, mapY * 16, 0, 2);
        } // Corner Bottom left
        else if (tempColour.getRed() == 127 && tempColour.getGreen() == 89 &&
                 tempColour.getBlue() == 63) {
          new mapBlock(mapX * 16, mapY * 16, 0, 3);
        } else if (tempColour.getRed() == 28 && tempColour.getGreen() == 255 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 1, 2);
        } else if (tempColour.getRed() == 255 && tempColour.getGreen() == 178 &&
                   tempColour.getBlue() == 127) {
          new mapBlock(mapX * 16, mapY * 16, 1, 3);
        } else if (tempColour.getRed() == 255 && tempColour.getGreen() == 233 &&
                   tempColour.getBlue() == 127) {
          new pill(mapX * 16, mapY * 16, false);
        } else if (tempColour.getRed() == 255 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 220) {
          new pill(mapX * 16, mapY * 16, true);
        } else if (tempColour.getRed() == 1 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 0, 4);
        } else if (tempColour.getRed() == 1 && tempColour.getGreen() == 1 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 1, 4);
        } else if (tempColour.getRed() == 1 && tempColour.getGreen() == 1 &&
                   tempColour.getBlue() == 1) {
          new mapBlock(mapX * 16, mapY * 16, 0, 5);
        } else if (tempColour.getRed() == 1 && tempColour.getGreen() == 1 &&
                   tempColour.getBlue() == 2) {
          new mapBlock(mapX * 16, mapY * 16, 1, 5);
        } else if (tempColour.getRed() == 1 && tempColour.getGreen() == 2 &&
                   tempColour.getBlue() == 2) {
          new mapBlock(mapX * 16, mapY * 16, 0, 6);
        } else if (tempColour.getRed() == 2 && tempColour.getGreen() == 2 &&
                   tempColour.getBlue() == 2) {
          new mapBlock(mapX * 16, mapY * 16, 1, 6);
        } else if (tempColour.getRed() == 2 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 0, 7);
        } else if (tempColour.getRed() == 3 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 1, 7);
        } else if (tempColour.getRed() == 4 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 2, 7);
        } else if (tempColour.getRed() == 5 && tempColour.getGreen() == 0 &&
                   tempColour.getBlue() == 0) {
          new mapBlock(mapX * 16, mapY * 16, 3, 7);
        }
      }
    }
  }
  public void draw(Graphics g) {}
}
