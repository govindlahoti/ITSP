import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        BufferedImage img=null;
        try{
   	     img= ImageIO.read(new File("img14.jpg"));
   	    }catch (IOException e) {	
   	    }
        
        CannyEdge detector=new CannyEdge();
        BufferedImage image=detector.detectedge(img);
        //image contains binary image that has only edges
        
        ImageProcessor I=new ImageProcessor();
        I.dilate8(image);
        I.erode(image);
        I.dilate8(image);
        //for large images we get better results after doing above three steps
        
        System.out.println(1);
        
        Compfinder C=new Compfinder();
        C.findcomps(image);
        C.filter();
        //finds connected components thus discovering objects     
        
        //C.mark();
        System.out.println(2);
        
        image=I.turntogrey(img);
        C.binarize2(image);
        //now "image" has final binary image
        
        try {
		    File outputfile = new File("saved.jpg");
		    ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) { 
		}
        System.out.println("ok");
	}

}
