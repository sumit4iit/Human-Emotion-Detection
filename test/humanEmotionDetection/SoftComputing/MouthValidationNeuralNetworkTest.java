/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.SoftComputing;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author OI
 */
public class MouthValidationNeuralNetworkTest {

    @Test
    public void testRecognition() {
        double[] input = {0.473684211, 0.685840708, 0.10106383};
        MouthValidationNeuralNetwork instance = new MouthValidationNeuralNetwork(input);

        instance.recognition();
        System.out.println(instance.getOutput());
        // TODO review the generated test code and remove the default call to fail.

    }
}
