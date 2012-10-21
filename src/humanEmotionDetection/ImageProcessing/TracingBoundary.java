/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OI
 */
public class TracingBoundary extends ImageProcessing {

    private int[][] connected;
    private int north, south, weast, east;
    private BinaryImageConnectivity binaryImageConnectivity;
    private List boundaries;
    private BufferedImage image;
    private BufferedImage image2;
    private List<List> list;

    public TracingBoundary(ImageProcessing image) {
        this.image = image.getImage();
        this.list = new ArrayList();
    }

    public void findBiggestObject() {
        int max = 0;
        int index = 0;
        this.north = this.image.getHeight();
        this.weast = 0;
        this.east = this.image.getWidth();
        this.south = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).size() > max) {
                max = this.list.get(i).size();
                index = i;
            }
        }

        for (int i = 0; i < this.list.get(index).size(); i++) {
            String[] pixels = list.get(index).get(i).toString().split(",");
            int x = Integer.parseInt(pixels[0]);
            int y = Integer.parseInt(pixels[1]);
            if (y < this.north) {
                this.north = y;
            }
            if (y > this.south) {
                this.south = y;
            }
            if (x < this.east) {
                this.east = x;
            }
            if (x > this.weast) {
                this.weast = x;
            }
            this.image.setRGB(x, y, Color.YELLOW.getRGB());
        }
    }

    public void traceBoundary(ImageProcessing image) {
    }

    private void traceBoundary() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        this.binaryImageConnectivity = new BinaryImageConnectivity();

        int[] toPixel = new int[2];
        int prevPixel;
        int topPixel;
        int xPixel;
        List<String> set;
        boolean search;
        int currentPixel;
        int dir;
        int toDir;
        int currentP;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                xPixel = this.image.getRGB(x, y);
                prevPixel = (this.prevPixel(y, width)) ? this.image.getRGB(y - 1, x) : Color.WHITE.getRGB();
                topPixel = (this.prevPixel(x, width)) ? this.image.getRGB(y, x - 1) : Color.WHITE.getRGB();
                if (BinaryImage.isWhite(xPixel) && BinaryImage.isBlack(prevPixel) && BinaryImage.isBlack(topPixel) && !this.binaryImageConnectivity.cekNeighBourHood(x, y, width, height)) {
                    search = true;
                    set = new ArrayList();
                    set.add(x + "," + y);
                    dir = 7;
                    while (search) {
                        if (dir % 2 == 0) {
                            toDir = (dir + 7) % 8;
                        } else {
                            toDir = (dir + 6) % 8;

                        }
                        toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, width, height);
                        if (toPixel[0] != -1 && toPixel[1] != -1) {
                            currentPixel = this.image.getRGB(toPixel[0], toPixel[1]);
                        } else {
                            currentPixel = this.image.getRGB(x + 1, y + 1);
                            System.exit(0);
                        }
                        while (!BinaryImage.isWhite(currentPixel)) {
                            toDir++;
                            if (toDir > 7) {
                                toDir = 0;
                            }
                            toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, width, height);
                            currentPixel = this.image.getRGB(toPixel[0], toPixel[1]);

                        }


                        dir = toDir;

                        x = toPixel[0];
                        y = toPixel[1];
                        set.add(toPixel[0] + "," + toPixel[1]);


                        if (set.size() > 2) {
                            currentP = set.size() - 1;
                            if ((set.get(currentP).equals(set.get(1))) && (set.get(currentP - 1).equals(set.get(0)))) {


                                x = width;
                                search = false;
                                this.list.add(set);




                            }
                        }


                    }
                }
            }
        }
    }

    public void TracingBoundary() {
    }

    private void drawResult(List list, int color) {
    }

    private boolean prevPixel(int y, int width) {

        if (y - 1 >= 0 && y - 1 < width) {
            return true;
        }
        return false;
    }

    private boolean topPixel(int x, int width) {
        if (x - 1 >= 0 && x - 1 < width) {
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }
}
