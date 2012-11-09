/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.SoftComputing;

import java.io.File;
import java.util.List;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

/**
 *
 * @author OI
 */
public class MouthValidationNeuralNetwork {

    private static final String encogFile = "trainset.eg";
    private double[] input;
    private double output;

    public void recognition() {
        BasicNetwork network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(encogFile));
        MLData trainingSet = new BasicNeuralData(this.input);
        this.output = network.compute(trainingSet).getData(0);

    }

    public MouthValidationNeuralNetwork(double[] input) {
        this.input = input;

    }

    public double getOutput() {
        return output;
    }
}
