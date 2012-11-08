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
    private int north, south, west, east;
    private BinaryImageConnectivity binaryImageConnectivity;
    private List boundaries;
    private BufferedImage image;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private List<List> list;

    public TracingBoundary(ImageProcessing image) {

        this.image = image.getImage();
        this.image2 = ImageProcessing.deepCopy(this.image);
        this.image3 = ImageProcessing.deepCopy(this.image);
        this.image4 = ImageProcessing.deepCopy(this.image);

        this.list = new ArrayList();
        this.generalitation(this.image);
        this.generalitation(this.image2);
        this.generalitation(this.image3);
        this.generalitation(this.image4);
    }

    public void findBiggestObject() {
        int max = 0;
        int index = 0;
        this.north = this.image.getHeight();
        this.west = 0;
        this.east = this.image.getWidth();
        this.south = 0;
        int x;
        int y;
        String[] pixels;
        this.traceBoundary();
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).size() > max) {
                max = this.list.get(i).size();
                index = i;

            }
        }

        for (int i = 0; i < this.list.get(index).size(); i++) {
            pixels = list.get(index).get(i).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);
            if (y < this.north) {
                this.north = y;
            }
            if (y > this.south) {
                this.south = y;
            }
            if (x < this.east) {
                this.east = x;
            }
            if (x > this.west) {
                this.west = x;
            }
            this.image2.setRGB(x, y, Color.RED.getRGB());
        }

    }

    private void generalitation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = height - 5; y < height; y++) {
            this.generaliationX(y, image);
        }
        for (int y = 0; y < 5; y++) {
            this.generaliationX(y, image);
        }
        for (int x = width - 5; x < width; x++) {
            this.generaliationX(x, image);
        }
        for (int x = 0; x < 5; x++) {
            this.generaliationX(x, image);
        }
    }

    private void generaliationX(int y, BufferedImage image) {
        int width = this.image.getWidth();
        for (int x = 0; x < width; x++) {
            this.image.setRGB(x, y, Color.BLACK.getRGB());
        }
    }

    private void generaliationY(int x, BufferedImage image) {
        int height = this.image.getWidth();
        for (int y = 0; x < height; x++) {
            this.image.setRGB(x, y, Color.BLACK.getRGB());
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
        int botPixel;
        int nextPixel;
        int xPixel;
        List<String> set;
        boolean search;
        int currentPixel;
        int dir;
        int toDir;
        int currentP;
        int counter = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                xPixel = this.image.getRGB(x, y);
                prevPixel = (this.prevPixel(x, width)) ? this.image.getRGB(x - 1, y) : Color.WHITE.getRGB();
                topPixel = (this.topPixel(y, height)) ? this.image.getRGB(x, y - 1) : Color.WHITE.getRGB();
                botPixel = (this.botPixel(y, height)) ? this.image.getRGB(x, y + 1) : Color.WHITE.getRGB();
                nextPixel = (this.nextPixel(x, width)) ? this.image.getRGB(x + 1, y) : Color.WHITE.getRGB();
                if (BinaryImage.isWhite(xPixel) && BinaryImage.isBlack(prevPixel) && BinaryImage.isBlack(topPixel) && BinaryImage.isWhite(botPixel) && BinaryImage.isWhite(nextPixel) && !this.binaryImageConnectivity.cekNeighBourHood(x, y, this.image)) {
                    search = true;
                    set = new ArrayList();
                    set.add(x + "," + y);
                    dir = 7;
                    //System.out.println(x + "," + y);
                    while (search) {
                        if (dir % 2 == 0) {
                            toDir = (dir + 7) % 8;
                        } else {
                            toDir = (dir + 6) % 8;

                        }
                        toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, this.image);
                        if (toPixel[0] != -1 && toPixel[1] != -1) {
                            currentPixel = this.image.getRGB(toPixel[0], toPixel[1]);
                        } else {
                            currentPixel = this.image.getRGB(x + 1, y + 1);
                            System.out.print("CEK");
                            //break;
                        }
                        while (!BinaryImage.isWhite(currentPixel)) {
                            toDir++;
                            if (toDir > 7) {
                                toDir = 0;
                            }
                            toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, this.image);
                            //System.out.println(toPixel[0] + "," + toPixel[1]);
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
                    if (this.list.size() > 0) {
                        this.drawResult(this.list, Color.YELLOW.getRGB());
                    }
                }
            }
        }
    }

    public void determineHole() {
        List<String> set;
        this.list = new ArrayList();
        int dir;
        int toDir;
        int[] toPixel = new int[2];
        int xPixel;
        int currentPixel;
        int prevPixel;
        int topPixel;
        int botPixel;
        int nextPixel;
        int curP;
        boolean search;
       this.west-=5;
        System.out.println("North" + this.north + "South" + this.south + "East" + this.east + "West" + this.west);
        for (int y = this.north; y < this.south; y++) {
            for (int x = this.east; x < this.west; x++) {
                xPixel = this.image2.getRGB(x, y);
                prevPixel = (this.prevPixel(x, this.west)) ? this.image2.getRGB(x - 1, y) : Color.WHITE.getRGB();
                topPixel = (this.topPixel(y, this.south)) ? this.image2.getRGB(x, y - 1) : Color.WHITE.getRGB();
                botPixel = (this.botPixel(y, this.south)) ? this.image2.getRGB(x, y + 1) : Color.WHITE.getRGB();
                nextPixel = (this.nextPixel(x, this.west)) ? this.image2.getRGB(x + 1, y) : Color.WHITE.getRGB();
                if ((BinaryImage.isBlack(xPixel) && BinaryImage.isWhite(prevPixel) && BinaryImage.isWhite(topPixel))) {
                    search = true;
                    set = new ArrayList();
                    set.add(x + "," + y);
                    System.out.println("xx");
                    System.out.println(set.get(0));
                    dir = 7;
                    while (search) {
                        if (dir % 2 == 0) {
                            toDir = (dir + 7) % 8;
                        } else {
                            toDir = (dir + 6) % 8;

                        }
                        toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, this.west, this.south);
                        if (toPixel[0] != -1 && toPixel[1] != -1) {
                            currentPixel = this.image3.getRGB(toPixel[0], toPixel[1]);
                        } else {
                            currentPixel = this.image3.getRGB(x + 1, y + 1);
                   
                           
                            
                            break;
                        }
                        while (!BinaryImage.isBlack(currentPixel)) {
                           // System.out.println("CEK2");
                            toDir++;
                            if (toDir > 7) {
                                toDir = 0;
                            }
                            toPixel = this.binaryImageConnectivity.neighBourHood(toDir, x, y, this.west, this.south);
                           // System.out.println("AWW" + this.south + "," + y + this.west + "," + x + "todir=" + toDir);
                            currentPixel = this.image3.getRGB(toPixel[0], toPixel[1]);

                        }


                        dir = toDir;

                        x = toPixel[0];
                        y = toPixel[1];

                        set.add(toPixel[0] + "," + toPixel[1]);


                        if (set.size() > 2) {
                            curP = set.size() - 1;
                            if ((set.get(curP).equals(set.get(1))) && (set.get(curP - 1).equals(set.get(0)))) {


                          
                                
                                search = false;
                                list.add(set);



                            }
                        }

                    }
                    if (this.list.size() > 0) {
                        this.drawHole(this.list, Color.BLUE.getRGB());
                    }
                }


            }




        }
        this.drawAllResult(Color.BLUE.getRGB());
    }

    public void TracingBoundary() {
    }

    private void drawHole(List<List> list, int color) {
        int index = list.size() - 1;
        int x;
        int y;
        String[] pixels;
        for (int i = 0; i < list.get(index).size(); i++) {

            pixels = list.get(index).get(i).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);

            this.image2.setRGB(x, y, color);
            // System.out.println(x+","+y);

        }
    }

    private void drawResult(List<List> list, int color) {
        int index = list.size() - 1;
        int x;
        int y;
        String[] pixels;
        for (int i = 0; i < list.get(index).size(); i++) {
            pixels = list.get(index).get(i).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);

            this.image.setRGB(x, y, color);

        }
    }

    private void drawAllResult(int color) {
        int index = this.list.size();
        int x;
        int y;
        String[] pixels;
        for (int i = 0; i < index; i++) {
            for (int h = 0; h < this.list.get(i).size(); h++) {
                pixels = list.get(i).get(h).toString().split(",");
                x = Integer.parseInt(pixels[0]);
                y = Integer.parseInt(pixels[1]);



                this.image4.setRGB(x, y, color);
            }
        }

    }

    private boolean prevPixel(int x, int width) {

        if (x - 1 >= 0 && x - 1 < width) {
            return true;
        }
        return false;
    }

    private boolean topPixel(int y, int height) {
        if (y - 1 >= 0 && y - 1 < height) {
            return true;
        }
        return false;
    }

    private boolean botPixel(int y, int height) {
        if (y + 1 >= 0 && y + 1 < height) {
            return true;
        }
        return false;
    }

    private boolean nextPixel(int x, int width) {
        if (x - 1 >= 0 && x + 1 < width) {
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage getImage() {
        super.setImage(this.image2);
        return super.getImage();
    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(this.image2);
        super.writeImage(path, nameImage);
    }

    public void writeImageHole(String path, String nameImage) {
        super.setImage(this.image4);
        super.writeImage(path, nameImage);
    }
}
