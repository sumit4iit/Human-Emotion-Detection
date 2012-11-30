/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OI
 */
public class FeatureExtraction {

    private double N;
    private double S;
    private double E;
    private double W;
    private BufferedImage image;
    private List feature;
    private List<List> set;

    public FeatureExtraction(TracingBoundary traceResult) {
        this.set = traceResult.getList();
        this.image = traceResult.getImage();
    }

    public FeatureExtraction(ImageProcessing image) {
        this.image = image.getImage();
    }

    public void getDirection(List boundary) {
        String[] pixels;
        double x;
        double y;
        Map<String, Double> aMap;
        this.N = this.image.getHeight();
        this.W = 0;
        this.E = this.image.getWidth();
        this.S = 0;
        for (int h = 0; h < boundary.size(); h++) {
            pixels = boundary.get(h).toString().split(",");
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

    }

    public double getE() {
        return E;
    }

    public double getN() {
        return N;
    }

    public double getS() {
        return S;
    }

    public double getW() {
        return W;
    }

    private void computeFeature() {

        String[] pixels;
        double x;
        double y;
        double elongation;
        double location;
        double width;
        double widthE;
        double length;
        double a;
        double b;
        Map<String, Double> aMap;
        this.feature = new ArrayList();
        for (int i = 0; i < this.set.size(); i++) {
            this.N = this.image.getHeight();
            this.W = 0;
            this.E = this.image.getWidth();
            this.S = 0;
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
            width = this.S - this.N;
            length = this.W - this.E;
            a = this.image.getHeight() - this.N;
            b = this.image.getHeight();
            widthE = this.image.getWidth();
            //System.out.println(width + "," + length + "," + a + "," + b);
            elongation = width / length;
            location = a / b;
            length = length / widthE;
            aMap = new HashMap<String, Double>();
            aMap.put("elongation", Double.valueOf(elongation));
            aMap.put("location", Double.valueOf(location));
            aMap.put("length", Double.valueOf(length));
            this.feature.add(aMap);
        }
    }

    public List getFeature() {
        this.computeFeature();
        return feature;
    }
}
