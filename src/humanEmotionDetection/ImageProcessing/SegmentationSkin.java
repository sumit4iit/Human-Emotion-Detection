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
public class SegmentationSkin extends ImageProcessing {

    private double R;
    private double G;
    private double B;
    private double Y;
    private double CB;
    private double CR;
    private BufferedImage image;

    public SegmentationSkin(ImageProcessing image) {
        this.image = image.getImage();

    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }

    public void segmentate() {
        int width;
        int height;
        int pixels;
        width = this.image.getWidth();
        height = this.image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels = this.image.getRGB(x, y);
                R = (pixels >> 16) & 0xff;
                G = (pixels >> 8) & 0xff;
                B = (pixels) & 0xff;
                Y = (int) ((0.299 * R) + (0.587 * G) + (0.114 * B));
                CB = (int) ((-0.168935 * R) + (-0.331665 * G) + (0.50059 * B));
                CR = (int) ((0.499813 * R) + (-0.418531 * G) + (-0.081282 * B));
                if ((Y > 120 && Y < 250) && (CB > -33.744 && CB < 8) && (CR > 3 && CR < 33)) {
                    this.image.setRGB(x, y, Color.WHITE.getRGB());

                } else {
                    this.image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(this.image);
        super.writeImage(path, nameImage);
    }

   

    
}
