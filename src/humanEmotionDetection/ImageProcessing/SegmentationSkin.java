/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

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
    public BufferedImage cloneImage() {
        return super.cloneImage();
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(BufferedImage image) {
        super.setImage(image);
    }
}
