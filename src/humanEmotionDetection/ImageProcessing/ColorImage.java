/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.ImageProcessing;

import java.awt.Color;

/**
 *
 * @author OI
 */
public class ColorImage {
    public static boolean isBlue(int RGB){
        if(RGB == Color.BLUE.getRGB()){
            return true;
        }
        return false;
    }
}
