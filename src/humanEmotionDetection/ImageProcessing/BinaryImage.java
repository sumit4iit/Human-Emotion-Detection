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
public class BinaryImage {

    public static boolean isBlack(int RGB) {
        if (RGB == Color.BLACK.getRGB()) {
            return true;
        }
        return false;
    }

    public static boolean isWhite(int RGB) {
        if (RGB == Color.WHITE.getRGB()) {
            return true;
        }
        return false;
    }
}
