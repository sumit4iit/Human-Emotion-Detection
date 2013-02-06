/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection;

import humanEmotionDetection.ImageProcessing.FeatureExtraction;
import humanEmotionDetection.ImageProcessing.ImageProcessing;
import humanEmotionDetection.ImageProcessing.TracingBoundary;
import humanEmotionDetection.SoftComputing.MouthValidationNeuralNetwork;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author OI
 */
public class MouthValidation {

    private MouthValidationNeuralNetwork mouthValidationNeuralNetwork;
    private FeatureExtraction feature;
    private TracingBoundary boundary;
    private ImageProcessing image;
    private int index = -1;

    public MouthValidation(ImageProcessing image, TracingBoundary boundary) {

        this.boundary = boundary;
        this.image = image;
    }

    public void evaluate() {
        Iterator candidatMouths;
        HashMap candidatMouth;
        double[] inputNetwork = new double[3];
        double max = 0;
        int i = 0;
        this.feature = new FeatureExtraction(this.boundary);
        candidatMouths = this.feature.getFeature().iterator();
        while (candidatMouths.hasNext()) {
            candidatMouth = (HashMap) candidatMouths.next();
            inputNetwork[0] = (double) candidatMouth.get("elongation");
            inputNetwork[1] = (double) candidatMouth.get("location");
            inputNetwork[2] = (double) candidatMouth.get("length");
            this.mouthValidationNeuralNetwork = new MouthValidationNeuralNetwork(inputNetwork);
            this.mouthValidationNeuralNetwork.recognition();
            System.out.println(this.mouthValidationNeuralNetwork.getOutput());
            if (max < this.mouthValidationNeuralNetwork.getOutput() && this.mouthValidationNeuralNetwork.getOutput() >= 0.9) {
                max = this.mouthValidationNeuralNetwork.getOutput();
                this.index = i;
            }
            i++;

        }



    }

    public Map<String, Integer> getDirection() {
        Map<String, Integer> direction = new HashMap<String, Integer>();
        this.feature = new FeatureExtraction(this.image);
        if (this.index == -1) {
            System.err.print("There isn't mouth");
            System.exit(0);
        }
        this.feature.getDirection(this.boundary.getList().get(this.index));
        int Ny = (int) this.feature.getNy();
        int Sy = (int) this.feature.getSy();
        int Ex = (int) this.feature.getEx();
        int Wx = (int) this.feature.getWx();

        int Nx = (int) this.feature.getNx();
        int Sx = (int) this.feature.getSx();
        int Ey = (int) this.feature.getEy();
        int Wy = (int) this.feature.getWy();

        int widthImage = (int) this.feature.getWidthImage();
        int heightImage = (int) this.feature.getHeightImage();

        direction.put("Ny", Integer.valueOf(Ny));
        direction.put("Sy", Integer.valueOf(Sy));
        direction.put("Ex", Integer.valueOf(Ex));
        direction.put("Wx", Integer.valueOf(Wx));

        direction.put("Nx", Integer.valueOf(Nx));
        direction.put("Sx", Integer.valueOf(Sx));
        direction.put("Ey", Integer.valueOf(Ey));
        direction.put("Wy", Integer.valueOf(Wy));

        direction.put("widthImage", Integer.valueOf(widthImage));
        direction.put("heightImage", Integer.valueOf(heightImage));
        return direction;
    }
}
