/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
            System.err.println(e);

        }
    }

    protected Object cloneImage() throws CloneNotSupportedException {
        return this.clone();
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }

    public void writeImage(BufferedImage Image, String path, String nameImage) {
        try {

            ImageIO.write(Image, "png", new File(path + nameImage));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
