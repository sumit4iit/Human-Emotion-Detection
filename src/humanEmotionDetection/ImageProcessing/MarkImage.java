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
        int N = (int) direction.get("N");
        int S = (int) direction.get("S");
        int E = (int) direction.get("E");
        int W = (int) direction.get("W");
        for (int i = N; i < S; i++) {
            this.image.setRGB(E, i, this.RGB);
            this.image.setRGB(W, i, this.RGB);
        }
        for (int i = E; i < W; i++) {
            this.image.setRGB(i, N, this.RGB);
            this.image.setRGB(i, S, this.RGB);
        }

    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(image);
        super.writeImage(path, nameImage);
    }
}
