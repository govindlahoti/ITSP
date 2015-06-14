import java.awt.image.BufferedImage;

/*
 * It takes input as a colored image and outputs
 * a binary highlighting its all edges.
 */
		

public class CannyEdge {
    BufferedImage detectedge(BufferedImage img){
    	EdgeDetect image=new EdgeDetect();
        BufferedImage mainimg=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        
        for(int y=0;y<3;y++){
          image.setimg(img);
          if(y==0)image.turntored();
          if(y==1)image.turntogreen();
          if(y==2)image.turntoblue();
          image.GBlur();
          image.Sobelop();
          image.nonmaxsup();
          image.merge(mainimg);
        }
        
        //image.thresh(mainimg);
        image.setimg(mainimg);
        //image.turntobin5();
        image.doublethresh();
        image.histeresis();
        image.invert();
        
        //image.dilate8();
        return image.getimg();
    }
}
