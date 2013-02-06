/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanEmotionDetection.model;

import java.util.Map;

/**
 *
 * @author prasetyaUtama
 */
public class Mouth {

    private double elongation;
    private double sinB;
    private double sinE;
    private double widthL;
    private double heightL;

    public Mouth(Map direction) {
        int widthMouth;
        int lengthMouth;
        
        double halfLengthMouth;
        
        //sin b
        double a,b,c;
        
        //sin e
        double d,e,f;
      
        //North
        int Nx = (int) direction.get("Nx");
        int Ny = (int) direction.get("Ny");
        
        //South
        int Sx = (int) direction.get("Sx");
        int Sy = (int) direction.get("Sy");
        
        //East
        int Ex = (int) direction.get("Ex");
        int Ey = (int) direction.get("Ey");
        
        //West
        int Wx = (int) direction.get("Wx");
        int Wy = (int) direction.get("Wy");
        
        //Image Information
        int widthImage = (int) direction.get("widthImage");
        int heightImage = (int) direction.get("heightImage");
        
        widthMouth = Sy - Ny;
        lengthMouth = Ex - Wx;
        halfLengthMouth = lengthMouth/2;
        this.elongation = widthMouth/lengthMouth;
        
        this.widthL = lengthMouth / widthImage;
        this.heightL = widthMouth / heightImage;
        
        //count sin b
        a = halfLengthMouth - Wx;
        b = Sy- Wy;
        c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
        this.sinB = Math.asin(c) * 57.29586;
        
         d = Ex - halfLengthMouth;
         e = Ey - Ny;
         f = Math.sqrt(Math.pow(d,2)+Math.pow(e,2));
         this.sinE = Math.asin(f) * 57.29586;
        
        
    }
}
