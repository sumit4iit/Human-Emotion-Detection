/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author OI
 */
public class Morphological extends ImageProcessing {

    private List candidatOperator;
    private int iteration;
    private int[][] connected;
    private BinaryImageConnectivity binaryImageConnectivity;
    private BufferedImage image;
    private BufferedImage image2;

    public Morphological(ImageProcessing image) {
        this.image = image.getImage();


    }

    public void dilation(int iteration) throws CloneNotSupportedException {
        this.iteration = iteration;


        for (int i = 0; i < this.iteration; i++) {

           
            this.image2 = ImageProcessing.deepCopy(this.image);
            this.dilate();         
            this.image = ImageProcessing.deepCopy(this.image2);

        }
    }

    public void erotion(int iteration) throws CloneNotSupportedException {
        this.iteration = iteration;


        for (int i = 0; i < this.iteration; i++) {
           this.image2 = ImageProcessing.deepCopy(this.image);
            this.erote();         
            this.image = ImageProcessing.deepCopy(this.image2);


        }
    }

    private void dilate() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int currentPixel;
        this.binaryImageConnectivity = new BinaryImageConnectivity();
        this.connected = this.binaryImageConnectivity.eightConnectivity();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                currentPixel = this.image.getRGB(x, y);
                if (BinaryImage.isBlack(currentPixel)) {
                    //X0
                    if ((y >= 0 && y < height) && (x + 1 < width)) {
                        this.image2.setRGB(x + 1, y, this.binaryImageConnectivity.convert(this.connected[1][2], this.image.getRGB(x + 1, y), Color.BLACK.getRGB()));

                    }
                   
                  
                    //X1
                    if ((y - 1 >= 0 && y - 1 < height) && (x + 1 < width)) {
                        this.image2.setRGB(x + 1, y - 1, this.binaryImageConnectivity.convert(this.connected[0][2], this.image.getRGB(x + 1, y - 1), Color.BLACK.getRGB()));

                    }

                    //X2
                    if ((y - 1 >= 0 && y - 1 < height) && (x >= 0 && x < width)) {
                        this.image2.setRGB(x, y - 1, this.binaryImageConnectivity.convert(this.connected[0][1], this.image.getRGB(x, y - 1), Color.BLACK.getRGB()));
                    }
                    //X3
                    if ((y - 1 >= 0 && y - 1 < height) && (x - 1 >= 0 && x - 1 < width)) {
                        this.image2.setRGB(x - 1, y - 1, this.binaryImageConnectivity.convert(this.connected[0][0], this.image.getRGB(x - 1, y - 1), Color.BLACK.getRGB()));
                    }

                    //X4

                    if ((y >= 0 && y < height) && (x - 1 >= 0 && x - 1 < width)) {
                        this.image2.setRGB(x - 1, y, this.binaryImageConnectivity.convert(this.connected[1][0], this.image.getRGB(x - 1, y), Color.BLACK.getRGB()));
                    }
                    //X5

                    if ((y + 1 >= 0 && y + 1 < height) && (x - 1 >= 0 && x - 1 < width)) {
                        this.image2.setRGB(x - 1, y + 1, this.binaryImageConnectivity.convert(this.connected[2][0], this.image.getRGB(x - 1, y + 1), Color.BLACK.getRGB()));
                    }

                   // X6
                    if ((y + 1 >= 0 && y + 1 < height) && (x >= 0 && x < width)) {
                        this.image2.setRGB(x, y + 1, this.binaryImageConnectivity.convert(this.connected[2][1], this.image.getRGB(x, y + 1), Color.BLACK.getRGB()));
                    }
                    //X7
                    if ((y + 1 >= 0 && y + 1 < height) && (x + 1 >= 0 && x + 1 < width)) {
                        this.image2.setRGB(x + 1, y + 1, this.binaryImageConnectivity.convert(this.connected[2][2], this.image.getRGB(x + 1, y + 1), Color.BLACK.getRGB()));
                    }
                }

            }
        }
    }

    private void erote() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int currentPixel;
        this.binaryImageConnectivity = new BinaryImageConnectivity();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                currentPixel = this.image.getRGB(x, y);
                this.connected = this.binaryImageConnectivity.getNeighbourHood(this.image, x, y, Color.BLACK.getRGB());
                if (BinaryImage.isBlack(currentPixel)) {
                    if ((BinaryImage.isWhite(this.connected[0][2]) && BinaryImage.isWhite(this.connected[0][1]) && BinaryImage.isWhite(this.connected[0][0]) && BinaryImage.isWhite(this.connected[2][0]) && BinaryImage.isWhite(this.connected[2][1]) && BinaryImage.isWhite(this.connected[2][2]))
                            || (BinaryImage.isWhite(this.connected[1][2]) && BinaryImage.isWhite(this.connected[0][2]) && BinaryImage.isWhite(this.connected[0][0]) && BinaryImage.isWhite(this.connected[1][0]) && BinaryImage.isWhite(this.connected[2][0]) && BinaryImage.isWhite(this.connected[2][2]))) {
                        this.image2.setRGB(x, y, Color.WHITE.getRGB());

                    }
                }
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        super.setImage(this.image);
        return super.getImage();
    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(this.image);
        super.writeImage(path, nameImage);
    }
}
