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
public class MarkImage extends ImageProcessing{

    private BufferedImage image;

    public MarkImage(ImageProcessing image) {
        this.image = image.getImage();
    }

    public void marking(List<String> list) {
        int x;
        int y;
        String[] pixels;
        for (int i = 0; i < list.size(); i++) {
            pixels = list.get(i).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);
            this.image.setRGB(x, y, Color.GREEN.getRGB());
        }
    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(image);
        super.writeImage(path, nameImage);
    }
    
    
}
