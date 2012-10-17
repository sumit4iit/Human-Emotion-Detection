/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author OI
 */
public class ImageProcessing {

    private BufferedImage image;
    private File file;

    public BufferedImage getImage() {
        return image;
    }

    public ImageProcessing() {
    }

    public ImageProcessing(String image) {
        this.file = new File(image);
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println(e);

        }
    }

    public ImageProcessing cloneImage() {
        return this;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
