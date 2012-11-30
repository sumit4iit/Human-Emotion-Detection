/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection;

import humanEmotionDetection.ImageProcessing.*;
import humanEmotionDetection.SoftComputing.MouthValidationNeuralNetwork;
import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OI
 */
public class HumanEmotionDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String pathFile = "src/humanEmotionDetection/image/";
        String imageOrigin = "test12.jpg";
        String imageSegment = "test12_01_seg.png";
        String imageDilation = "test12_02_dil.png ";
        String imageErotion = "test12_03_er.png";
        String imageBiggestObject = "test12_04_biggest.png";
        String imageHoles = "test12_05_holes.png";
        String imageMarking = "test12_06_mark.png";
        File file = new File(pathFile + imageOrigin);
        File path = new File(pathFile);
        ImageProcessing image;
        ImageProcessing image2;


        SegmentationSkin segment;
        Morphological morphologicalOperator;
        TracingBoundary boundary;
        MouthValidation mouthValidation;
        MarkImage mark;



        URL url = null;
        URL pathImage = null;
        try {

            url = file.toURL();
            pathImage = path.toURL();
        } catch (MalformedURLException e) {
        }

        //set path of image and get it
        file = new File(url.getFile());
        path = new File(pathImage.getFile());
        String imagePath = file.getPath();
        String imagePathSave = path.getPath();

        //segmentation skin
        image = new ImageProcessing(imagePath);
        image2 = new ImageProcessing(imagePath);
        segment = new SegmentationSkin(image);
        segment.segmentate();
        segment.writeImage(imagePathSave, imageSegment);

        //dilation
        image.setImage(segment.getImage());
        morphologicalOperator = new Morphological(image);
        try {

            morphologicalOperator.erotion(1);
            morphologicalOperator.writeImage(imagePathSave, imageErotion);
            morphologicalOperator.dilation(2);
            morphologicalOperator.writeImage(imagePathSave, imageDilation);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HumanEmotionDetection.class.getName()).log(Level.SEVERE, null, ex);
        }

        //find bigest object
        image.setImage(morphologicalOperator.getImage());
        boundary = new TracingBoundary(image);
        boundary.findBiggestObject();
        boundary.writeImage(imagePathSave, imageBiggestObject);
        boundary.determineHole();
        boundary.writeImageHole(imagePathSave, imageHoles);


        mouthValidation = new MouthValidation(image2, boundary);
        mouthValidation.evaluate();

        mark = new MarkImage(image2, Color.orange.getRGB());
        mark.marking(mouthValidation.getDirection());
        mark.writeImage(imagePathSave, imageMarking);


        //marking
//        image.setImage(boundary.getImage());
//        List<String> list = new ArrayList();
//        list.add("253,212");
//        list.add("253,213");
//        list.add("253,214");
////        list.add("102,138");
////        list.add("104,169");
////        list.add("106,170");
////        list.add("106,170");
////        list.add("108,170");
////        list.add("109,170");
////        list.add("111,170");
////        list.add("112,170");
//
//        MarkImage mark = new MarkImage(image);
//        mark.marking(list);
//        mark.writeImage(imagePathSave, imageMarking);



    }
}
