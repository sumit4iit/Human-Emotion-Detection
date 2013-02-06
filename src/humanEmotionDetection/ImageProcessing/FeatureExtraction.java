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

    private double Nx,Ny;
    private double Sx,Sy;
    private double Ex,Ey;
    private double Wx,Wy;
    private int heightImage,widthImage;
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
        this.widthImage = this.image.getWidth();
        this.heightImage = this.image.getHeight();
        this.Ny = this.image.getHeight();
        this.Wx = 0;
        this.Ex = this.image.getWidth();
        this.Sy = 0;
        for (int h = 0; h < boundary.size(); h++) {
            pixels = boundary.get(h).toString().split(",");
            x = Integer.parseInt(pixels[0]);
            y = Integer.parseInt(pixels[1]);
            if (y < this.Ny) {
                this.Ny = y;
                this.Nx = x;
            }
            if (y > this.Sy) {
                this.Sy = y;
                this.Sx = x;
            }
            if (x < this.Ex) {
                this.Ex = x;
                this.Ey = y;
            }
            if (x > this.Wx) {
                this.Wx = x;
                this.Wy = y;
            }
        }

    }

    public double getEx() {
        return Ex;
    }

    public double getNy() {
        return Ny;
    }

    public double getSy() {
        return Sy;
    }

    public double getWx() {
        return Wx;
    }

    public double getNx() {
        return Nx;
    }

    public double getSx() {
        return Sx;
    }

    public double getEy() {
        return Ey;
    }

    public double getWy() {
        return Wy;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public int getWidthImage() {
        return widthImage;
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
            this.Ny = this.image.getHeight();
            this.Wx = 0;
            this.Ex = this.image.getWidth();
            this.Sy = 0;
            for (int h = 0; h < this.set.get(i).size(); h++) {
                pixels = this.set.get(i).get(h).toString().split(",");
                x = Integer.parseInt(pixels[0]);
                y = Integer.parseInt(pixels[1]);
                if (y < this.Ny) {
                    this.Ny = y;
                }
                if (y > this.Sy) {
                    this.Sy = y;
                }
                if (x < this.Ex) {
                    this.Ex = x;
                }
                if (x > this.Wx) {
                    this.Wx = x;
                }
            }
            width = this.Sy - this.Ny;
            length = this.Wx - this.Ex;
            a = this.image.getHeight() - this.Ny;
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
