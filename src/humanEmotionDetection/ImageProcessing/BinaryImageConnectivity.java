/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author OI
 */
public class BinaryImageConnectivity {

    private BufferedImage image;

    public static int[][] fourConnectivity() {
        int[][] connectivity = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}};
        return connectivity;
    }

    public static int[][] eightConnectivity() {
        int[][] connectivity = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}};
        return connectivity;

    }

    public static int[][] hConnectivityRL() {
        int[][] connectivity = {
            {1, 1, 1},
            {0, 1, 0},
            {1, 1, 1}};
        return connectivity;
    }

    public static int[][] hConnectivityTB() {
        int[][] connectivity = {
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1}};
        return connectivity;
    }

    public int[][] getNeighbourHood(ImageProcessing image, int x, int y) {
        BufferedImage images = image.getImage();
        int width = images.getWidth();
        int height = images.getHeight();

        int[][] neighbourHood = new int[3][3];
        neighbourHood[1][2] = ((y >= 0 && y < width) && (x + 1 < height)) ? images.getRGB(y + 1, x) : Color.BLACK.getRGB();
        neighbourHood[0][2] = ((y - 1 >= 0 && y - 1 < width) && (x + 1 >= 0 && x + 1 < height)) ? images.getRGB(x + 1, y - 1) : Color.BLACK.getRGB();
        neighbourHood[0][1] = ((y - 1 >= 0 && y - 1 < width) && (x >= 0 && x < height)) ? images.getRGB(x, y - 1) : Color.BLACK.getRGB();
        neighbourHood[0][0] = ((y - 1 >= 0 && y - 1 < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y - 1) : Color.BLACK.getRGB();
        neighbourHood[1][0] = ((y >= 0 && y < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y) : Color.BLACK.getRGB();
        neighbourHood[2][0] = ((y + 1 >= 0 && y + 1 < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y + 1) : Color.BLACK.getRGB();
        neighbourHood[2][1] = ((y + 1 >= 0 && y + 1 < width) && (x >= 0 && x < height)) ? images.getRGB(x, y + 1) : Color.BLACK.getRGB();
        neighbourHood[2][2] = ((y + 1 >= 0 && y + 1 < width) && (x + 1 >= 0 && x + 1 < height)) ? images.getRGB(x + 1, y + 1) : Color.BLACK.getRGB();

        return neighbourHood;
    }
}
