import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class ImageProcessor {
	BufferedImage turntogrey(BufferedImage img){//function used for grey scale conversion
	   	 int i,j;
	   	 int w,h;
	   	 w=img.getWidth(); h=img.getHeight();
	   	 BufferedImage image=new BufferedImage(w,h,img.getType());
	   	 for(i=0;i<w;i++){
	   		 for(j=0;j<h;j++){
	   			 Color clr= new Color(img.getRGB(i, j));
	   			 int avg;//co-efficients are different for red green blue as it increases the contrast
	   			 avg=((7)*clr.getBlue()+(71)*clr.getGreen()+(22)*clr.getRed())/100;
	   			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
	   		 }
	   	 }
	   	 
	   	 return image;
	}
	
	void dilate8(BufferedImage image){
		 int i,j;
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		
	     int w,h;
	     w=image.getWidth(); h=image.getHeight();
	     for(i=0;i<w;i++){
	    	 for(j=0;j<h;j++){
	    		 image.setRGB(i,j,new Color(255,255,255).getRGB());
	    	 }
	     }
	     
	     for( i=0;i<w;i++){
	    	 image.setRGB(i, 0, new Color(255,255,255).getRGB());
	    	 image.setRGB(i, h-1, new Color(255,255,255).getRGB());
	     }
	     for( j=0;j<h;j++){
	    	 image.setRGB(0, j, new Color(255,255,255).getRGB());
	    	 image.setRGB(w-1, j, new Color(255,255,255).getRGB());
	     }
	     
	     
	     for(i=1;i<w-1;i++){
	    	 for(j=1;j<h-1;j++){
	    		 Color c=new Color(tmpi.getRGB(i, j));
	    		  if(c.getBlue()==0){
	    		   for(int y=i-1;y<=i+1;y++){
	    			 for(int z=j-1;z<=j+1;z++){
	    				 if(i!=y||j!=z){
	    					 image.setRGB(y, z, new Color(0,0,0).getRGB());
	    				 }
	    			 }
	    		   }
	    		  }
	    	 }
	     }
	     
	 }
	
	public void erode(BufferedImage image){
		int i,j;
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		
	     int w,h;
	     w=image.getWidth(); h=image.getHeight();
		    
	     for(i=0;i<image.getWidth();i++){
	    	 for(j=0;j<image.getHeight();j++){
	    		 image.setRGB(i,j,new Color(255,255,255).getRGB());
	    	 }
	     }
	     for(i=1;i<image.getWidth()-1;i++){
	    	 for(j=1;j<image.getHeight()-1;j++){
	    		 Color c;
	    		 boolean flag=false;
	    		 for(int i1=i-1;i1<=i+1;i1++){
	    			 for(int j1=j-1;j1<=j+1;j1++){
	    				 if(i1>=0&&i1<w&&j1>=0&&j1<h){
	    					 if(i1!=i||j1!=j){
	    						 c=new Color(tmpi.getRGB(i1, j1));
	    						 if(c.getRed()!=0){
	    							 flag=true;
	    							 break;
	    						 }
	    					 }
	    				 }
	    				 if(flag==true)break;
	    			 }
	    		 }
	    		 if(flag==false){
	    			 image.setRGB(i,j,new Color(0,0,0).getRGB());
	    		 }
	    		 else{
	    			 image.setRGB(i,j,new Color(255,255,255).getRGB());
	    		 }
	    		 
	    	 }
	     }
	     
}
}
