/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 *
 * @author prasetyaUtama
 */
public class CropImage extends ImageProcessing {

    private ImageProcessing image;
    private BufferedImage imageCrop;
    private Map<String, Integer> direction;

    public CropImage(ImageProcessing image, Map<String, Integer> direction) {
        this.image = image;
        this.direction = direction;
        this.crop();
    }

    private void crop() {
        int Ny = (int) direction.get("Ny");
        int Sy = (int) direction.get("Sy");
        int Ex = (int) direction.get("Ex");
        int Wx = (int) direction.get("Wx");
        int x = Ex;
        int y = Ny;
        int w = Wx - Ex;
        int h = Sy - Ny;
       // System.out.println(x+" "+y+" "+w+" "+h);
        this.imageCrop = this.image.getImage().getSubimage(x, y, w, h);
        super.setImage(this.imageCrop);
        
    }
    
    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }
    
    @Override
    public void writeImage(String path, String nameImage) {
        super.setImage(this.imageCrop);
        super.writeImage(path, nameImage);
    }
}
