/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author OI
 */
public class ImageProcessing {

    private BufferedImage image;
    private File file;

    public BufferedImage getImage() {
        return this.image;
    }

    public ImageProcessing() {
    }

    public ImageProcessing(String image) {
        this.file = new File(image);
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImage(ImageProcessing image) {
        this.image = image.getImage();
    }

    public void writeImage(String path, String nameImage) {
        try {

            ImageIO.write(this.image, "png", new File(path + "\\" + nameImage));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static BufferedImage deepCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
