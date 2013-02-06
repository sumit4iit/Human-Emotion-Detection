/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OI
 */
public class MarkImage extends ImageProcessing {

    private BufferedImage image;
    private int RGB;

    public MarkImage(ImageProcessing image, int RGB) {
        this.image = image.getImage();
        this.RGB = RGB;
    }

    public void marking(List<String> list) {
        int x;
        int y;
        String[] pixels;
        for (int i = 0; i < list.size(); i++) {
            pixels = list.get(i).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);
            this.image.setRGB(x, y, this.RGB);
        }
    }

    public void marking(Map direction) {
        int Ny = (int) direction.get("Ny");
        int Sy = (int) direction.get("Sy");
        int Ex = (int) direction.get("Ex");
        int Wx = (int) direction.get("Wx");
        for (int i = Ny; i < Sy; i++) {
            this.image.setRGB(Ex, i, this.RGB);
            this.image.setRGB(Wx, i, this.RGB);
        }
        for (int i = Ex; i < Wx; i++) {
            this.image.setRGB(i, Ny, this.RGB);
            this.image.setRGB(i, Sy, this.RGB);
        }

    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(image);
        super.writeImage(path, nameImage);
    }
}
