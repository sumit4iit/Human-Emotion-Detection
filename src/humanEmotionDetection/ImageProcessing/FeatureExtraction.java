/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OI
 */
public class FeatureExtraction extends ImageProcessing {

    private int N;
    private int S;
    private int E;
    private int W;
    private BufferedImage image;
    private List feature;
    private List<List> set;

    public FeatureExtraction(TracingBoundary traceResult) {
        this.set = new ArrayList();
        set = traceResult.getList();
    }

    private void computeFeature() {
        this.N = this.image.getHeight();
        this.W = 0;
        this.E = this.image.getWidth();
        this.S = 0;
        String[] pixels;
        int x;
        int y;
        int elongation;
        int location;
        int length;
        this.feature = new ArrayList();
        for (int i = 0; i < this.set.size(); i++) {
            for (int h = 0; h < this.set.get(i).size(); h++) {
                pixels = this.set.get(i).get(h).toString().split(",");
                x = Integer.parseInt(pixels[0]);
                y = Integer.parseInt(pixels[1]);
                if (y < this.N) {
                    this.N = y;
                }
                if (y > this.S) {
                    this.S = y;
                }
                if (x < this.E) {
                    this.E = x;
                }
                if (x > this.W) {
                    this.W = x;
                }
            }
            elongation = (this.S - this.N) / (this.W - this.E);
            location = (this.image.getHeight() - this.N) / this.image.getHeight();
            length = this.image.getHeight();
            this.feature.add(elongation + "," + location + "," + length);
        }
    }

    public List<List> getFeature() {
        return feature;
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }

    @Override
    public void writeImage(String path, String nameImage) {
        super.writeImage(path, nameImage);
    }
}
