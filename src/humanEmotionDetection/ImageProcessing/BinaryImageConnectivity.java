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

    public final int[][] fourConnectivity() {
        int[][] connectivity = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}};
        return connectivity;
    }

    public final int[][] eightConnectivity() {
        int[][] connectivity = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}};
        return connectivity;

    }

    public final int[][] hConnectivityRL() {
        int[][] connectivity = {
            {1, 1, 1},
            {0, 1, 0},
            {1, 1, 1}};
        return connectivity;
    }

    public final int[][] hConnectivityTB() {
        int[][] connectivity = {
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1}};
        return connectivity;
    }

    public int[][] getNeighbourHood(BufferedImage image, int x, int y,int RGB) {
        BufferedImage images = image;
        int width = images.getWidth();
        int height = images.getHeight();

        int[][] neighbourHood = new int[3][3];
        neighbourHood[1][2] = ((y >= 0 && y < width) && (x + 1 < height)) ? images.getRGB(y + 1, x) : RGB;
        neighbourHood[0][2] = ((y - 1 >= 0 && y - 1 < width) && (x + 1 >= 0 && x + 1 < height)) ? images.getRGB(x + 1, y - 1) : RGB;
        neighbourHood[0][1] = ((y - 1 >= 0 && y - 1 < width) && (x >= 0 && x < height)) ? images.getRGB(x, y - 1) : RGB;
        neighbourHood[0][0] = ((y - 1 >= 0 && y - 1 < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y - 1) : RGB;
        neighbourHood[1][0] = ((y >= 0 && y < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y) : RGB;
        neighbourHood[2][0] = ((y + 1 >= 0 && y + 1 < width) && (x - 1 >= 0 && x - 1 < height)) ? images.getRGB(x - 1, y + 1) : RGB;
        neighbourHood[2][1] = ((y + 1 >= 0 && y + 1 < width) && (x >= 0 && x < height)) ? images.getRGB(x, y + 1) : RGB;
        neighbourHood[2][2] = ((y + 1 >= 0 && y + 1 < width) && (x + 1 >= 0 && x + 1 < height)) ? images.getRGB(x + 1, y + 1) : RGB;

        return neighbourHood;
    }

    public int convert(int value, int colorOpponent, int color) {
        if (value == 0) {
            return colorOpponent;
        } else {
            return color;
        }
    }
}
