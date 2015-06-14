package ocr;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.*;
import javax.swing.*;

import java.util.*;

import javax.*;

import java.awt.image.*;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;


public class Myimage {
	 static BufferedImage image;
	 static boolean[][] flag;
	 
	 public void turntogrey(){
    	 BufferedImage img = image;
    	 int i,j;
    	 for(i=0;i<img.getWidth();i++){
    		 for(j=0;j<img.getHeight();j++){
    			 Color clr= new Color(img.getRGB(i, j));
    			 int avg;
    			 avg=((7)*clr.getBlue()+(72)*clr.getGreen()+(21)*clr.getRed())/100;
    			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
    		 }
    	 }
    	 
     }
	 
	 public void turntobinary(int thresh){
		 BufferedImage img = image;
    	 int i,j;
    	 int w,h;
    	 w=img.getWidth();
    	 h=img.getHeight();
    	 for(i=0;i<img.getWidth();i++){
    		 for(j=0;j<img.getHeight();j++){
    			 Color clr= new Color(img.getRGB(i, j));
    			 int avg;
    			 avg=clr.getBlue();
    			 if(avg<clr.getGreen())avg=clr.getGreen();
    			 if(avg<clr.getRed())avg=clr.getRed();
    			 
    			 if(avg<thresh&&i>20&&j>20&&i<w-20&&j<h-20){
    			   image.setRGB(i,j,new Color(0,0,0).getRGB());
    			 }
    			 else{
    				 image.setRGB(i,j,new Color(255,255,255).getRGB());
    			 }
    		 }
    	 }
	 }
	 
	 public void turntobin2(){
    	 int i,j;
    	 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
    	 
    	 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
    	
    	 int T,s,t,min,max;
    	 for(j=0;j<h;j++){
    		 for(i=0;i<w;i++){
    			s=0; t=0; min=678; max=0;
    			for(int i1=i-5;i1<=i+5;i1++){
    			 for(int j1=j-5;j1<=j+5;j1++){
    			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
    				 Color clr= new Color(tmpi.getRGB(i1, j1));
    			     s=s+clr.getGreen();
    			    // if(clr.getRed()>max)max=clr.getRed();
    			    // if(clr.getRed()<min)min=clr.getRed();
    			     t++;
    			    }
    			 }
    			}
    			T=s/t;
    			T-=7;
    			//System.out.println(T + " "+ (max-T));
    			Color clr= new Color(image.getRGB(i, j));
			    if(clr.getGreen()<=T){
			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
			    }
			    else{
			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
			    }
    		 } 
    	 }
    	
	 }
	 
	 public void turntobin4(){
    	 int i,j;
    	 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
    	 boolean[][] flags=new boolean[w][h]; 
    	 for(i=0;i<w;i++){
    		 for(j=0;j<h;j++){
    			 flags[i][j]=false;
    		 }
    	 }
    	 
    	 
    	 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
    	
    	 int T,s,t,min,max;
    	 for(j=1;j<h-1;j++){
    		 for(i=1;i<w-1;i++){
    			 
    		  if(flags[i][j]==false){	 
    			s=0; t=0; min=678; max=0;
    			for(int i1=i-5;i1<=i+5;i1++){
    			 for(int j1=j-5;j1<=j+5;j1++){
    			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
    				 Color clr= new Color(tmpi.getRGB(i1, j1));
    			     s=s+clr.getGreen();
    			    // if(clr.getRed()>max)max=clr.getRed();
    			    // if(clr.getRed()<min)min=clr.getRed();
    			     t++;
    			    }
    			 }
    			}
    			T=s/t;
    			T-=8;
    			//System.out.println(T + " "+ (max-T));
    			
    			for(int a=i-1;a<=i+1;a++){
    			  for(int b=j-1;b<=j+1;b++){
    			    if(a>=0&&a<w&&b>=0&&b<h){
    				 Color clr= new Color(image.getRGB(a, b));
			         if(clr.getGreen()<=T){
			    	     image.setRGB(a,b,new Color(0,0,0).getRGB());
			         }
			         else{
			    	     image.setRGB(a,b,new Color(255,255,255).getRGB());
			         }
			         flags[a][b]=true;
    			    }
    			  }
    			}
    		  }
    		 } 
    	 }
    	
	 }

	 public void turntobin5(){
    	 int i,j;
    	 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
    	 int T[][]=new int[w][h];
    	 int[][] L=new int[w][h];
    	 int[][] A=new int[w][h];
    	 
    	 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
    	
		 Color c;
		 for(i=0;i<w;i++){
			 for(j=0;j<h;j++){
				 c=new Color(tmpi.getRGB(i, j));
				 A[i][j]=c.getRed();
			 }
		 }
		 
		 int s,t;
    	 for(i=0;i<w;i++){
    		 for(j=0;j<=5;j++){
    			 s=0; t=0; 
    			if(j==5){
    			  for(int j1=j-5;j1<=j+5;j1++){
    				 if(i>=0&&i<w&&j1>=0&&j1<h){
         			     s=s+A[i][j1];
         			 }
    			  }
    			  L[i][j]=s; 
    			}
    			
    			s=0; t=0;
     			for(int i1=i-5;i1<=i+5;i1++){
     			 for(int j1=j-5;j1<=j+5;j1++){
     			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
     			     s=s+A[i1][j1];
     			     t++;
     			    }
     			 }
     			}
     			T[i][j]=s;
     			
     			 Color clr= new Color(image.getRGB(i, j));
  			    if(clr.getGreen()<=(T[i][j]/t-7)){
  			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
  			    }
  			    else{
  			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
  			    }
     			
    		 }
    	 }
    	 for(i=0;i<w;i++){
    		 for(j=h-6;j<h;j++){
    			if(j==h-6){
    			   s=0; t=0; 
     			   for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			   }
     			   L[i][j]=s; 
    			}
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      			T[i][j]=s;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T[i][j]/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    }
    		 }
    	 }
    	 
    	 for(i=0;i<=5;i++){
    		 for(j=6;j<h-6;j++){
    			
    			 s=0; t=0; 
     			for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			}
     			L[i][j]=s; 
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      			T[i][j]=s;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T[i][j]/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    }
    		 }
    	 }
    	 
    	 for(i=w-6;i<w;i++){
    		 for(j=6;j<h-6;j++){
    			
    			 s=0; t=0; 
     			for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			}
     			L[i][j]=s; 
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      			T[i][j]=s;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T[i][j]/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    } 
    		 }
    	 }
    	 
    	 for(i=6;i<w-6;i++){
    		 for(j=6;j<h-6;j++){
    			 L[i][j]=L[i][j-1]+A[i][j+5]-A[i][j-6];
    		 }
    	 }
    	 
    	 for(i=6;i<w-6;i++){
    		 for(j=6;j<h-6;j++){
    			 
    			 T[i][j]=T[i-1][j]+L[i+5][j]-L[i-6][j];
    			 Color clr= new Color(image.getRGB(i, j));
 			    if(clr.getGreen()<=T[i][j]/121-7){
 			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
 			    }
 			    else{
 			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
 			    }
    		 }
    	 }
    	 
    	
	 }
	 
	 public void turntobin6(){
    	 int i,j;
    	 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
    	 int T[][]=new int[w][h];
    	 int[][] L=new int[w][h];
    	 int[][] A=new int[w][h];
    	 boolean[][] flag=new boolean[w][h];
    	 
    	 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
    	
		 Color c;
		 for(i=0;i<w;i++){
			 for(j=0;j<h;j++){
				 c=new Color(tmpi.getRGB(i, j));
				 A[i][j]=c.getRed();
				 flag[i][j]=false;
			 }
		 }
		 
		 int s,t;
    	 for(i=0;i<w;i++){
    		 for(j=0;j<=5;j++){
    			 s=0; t=0; 
    			if(j==5){
    			  for(int j1=j-5;j1<=j+5;j1++){
    				 if(i>=0&&i<w&&j1>=0&&j1<h){
         			     s=s+A[i][j1];
         			 }
    			  }
    			  L[i][j]=s; 
    			}
    			
    			s=0; t=0;
     			for(int i1=i-5;i1<=i+5;i1++){
     			 for(int j1=j-5;j1<=j+5;j1++){
     			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
     			     s=s+A[i1][j1];
     			     t++;
     			    }
     			 }
     			}
     			T[i][j]=s;
     			int T1=s;
     			if(s/t<50)T1=200*t;
     			Color clr= new Color(image.getRGB(i, j));
  			    if(clr.getGreen()<=(T1/t-7)){
  			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
  			    }
  			    else{
  			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
  			    }
     			
    		 }
    	 }
    	 for(i=0;i<w;i++){
    		 for(j=h-6;j<h;j++){
    			if(j==h-6){
    			   s=0; t=0; 
     			   for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			   }
     			   L[i][j]=s; 
    			}
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      		
      			T[i][j]=s;
     			int T1=s;
     			if(s/t<50)T1=200*t;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T1/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    }
    		 }
    	 }
    	 
    	 for(i=0;i<=5;i++){
    		 for(j=6;j<h-6;j++){
    			
    			 s=0; t=0; 
     			for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			}
     			L[i][j]=s; 
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      			T[i][j]=s;
      			int T1=s;
     			if(s/t<50)T1=200*t;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T1/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    }
    		 }
    	 }
    	 
    	 for(i=w-6;i<w;i++){
    		 for(j=6;j<h-6;j++){
    			
    			 s=0; t=0; 
     			for(int j1=j-5;j1<=j+5;j1++){
     				 if(i>=0&&i<w&&j1>=0&&j1<h){
          			     s=s+A[i][j1];
          			 }
     			}
     			L[i][j]=s; 
    			 
    			 s=0; t=0; 
      			for(int i1=i-5;i1<=i+5;i1++){
      			 for(int j1=j-5;j1<=j+5;j1++){
      			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
      			     s=s+A[i1][j1];
      			     t++;
      			    }
      			 }
      			}
      			T[i][j]=s;
      			int T1=s;
     			if(s/t<50)T1=200*t;
      			 Color clr= new Color(image.getRGB(i, j));
   			    if(clr.getGreen()<=T1/t-7){
   			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
   			    }
   			    else{
   			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
   			    } 
    		 }
    	 }
    	 
    	 for(i=6;i<w-6;i++){
    		 for(j=6;j<h-6;j++){
    			 L[i][j]=L[i][j-1]+A[i][j+5]-A[i][j-6];
    		 }
    	 }
    	 
    	 for(i=6;i<w-6;i++){
    		 for(j=6;j<h-6;j++){
    			 
    			 T[i][j]=T[i-1][j]+L[i+5][j]-L[i-6][j];
    			 Color clr= new Color(image.getRGB(i, j));
    			 int T1=T[i][j];
      			if(T1/121<50)T1=200*121;
 			    if(clr.getGreen()<=T1/121-7){
 			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
 			    }
 			    else{
 			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
 			    }
    		 }
    	 }
    	 
    	
	 }
	 
	 
	 public void turntobin3(){
    	 int i,j;
    	 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
    	 
    	 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
    	
    	 int T,s,t;
    	 for(j=0;j<h;j++){
    		 for(i=0;i<w;i++){
    			s=0; t=0;
    			for(int i1=i-6;i1<=i+6;i1++){
    			 for(int j1=j-6;j1<=j+6;j1++){
    			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
    				 Color clr= new Color(tmpi.getRGB(i1, j1));
    			     s=s+clr.getGreen();
    			     t++;
    			    }
    			 }
    			}
    			T=s/t;t=0;
    			for(int i1=i-6;i1<=i+6;i1++){
       			 for(int j1=j-6;j1<=j+6;j1++){
       			    if(i1>=0&&i1<w&&j1>=0&&j1<h){
       				 Color clr= new Color(tmpi.getRGB(i1, j1));
       			    // s=s+clr.getGreen();
       			     t=t+((T-clr.getGreen())*(T-clr.getGreen()));
       			    }
       			 }
       			}
    			System.out.println(h-j);
    			//System.out.println(t);
    			if(t<4500){T=0;}
    			T-=7;
    			//if(T>0){T=0;}
    			Color clr= new Color(image.getRGB(i, j));
			    if(clr.getGreen()<=T){
			    	image.setRGB(i,j,new Color(0,0,0).getRGB());
			    }
			    else{
			    	image.setRGB(i,j,new Color(255,255,255).getRGB());
			    }
    		 } 
    	 }
    	
	 }
	 
	 public void poweren(){
		 double c,Gam,s;
		 c=1; Gam=2;
		 int i,j;
    	 for(i=0;i<image.getWidth();i++){
    		 for(j=0;j<image.getHeight();j++){
    			 Color clr= new Color(image.getRGB(i, j));
    			 double r;
    			 r=clr.getRed();
    			 s=r*r;
    			 s/=255;
    			// System.out.println(s);
    			 int avg;
    			 avg= (int) s;
    			 if(avg>255)avg=255;
    			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
    		 }
    	 }
		 
	 }
	 
	 public void histo(){
		 long[] freq=new long[256];
		 int i,j;
		 int w,h;
    	 w=image.getWidth();
    	 h=image.getHeight();
		 for( i=0;i<256;i++){
			 freq[i]=0;
		 }
    	 for(i=0;i<image.getWidth();i++){
    		 for(j=0;j<image.getHeight();j++){
    			 Color clr= new Color(image.getRGB(i, j));
    			freq[clr.getGreen()]+=1;
    		 }
    	 }
		 long cdfmin=-1,sum=0;
		 long[] cdf= new long[256];
		 for( i=0;i<256;i++){
			 sum=sum+freq[i];
			 cdf[i]=sum;
			 if(cdfmin==-1&&cdf[i]!=0){cdfmin=cdf[i];}
		 }
		 int[] H=new int[256];
		 for( i=0;i<256;i++){
			 H[i]=(int)(cdf[i]-cdfmin)*255;
			 H[i]/=(w*h-cdfmin);
		 }
		 for(i=0;i<image.getWidth();i++){
    		 for(j=0;j<image.getHeight();j++){
    			 Color clr= new Color(image.getRGB(i, j));
    			 int a;
    			 a=H[clr.getGreen()];
    			 image.setRGB(i,j,new Color(a,a,a).getRGB());
    		 }
    	 }
		 
	 }
	 
	 public void histo2(int x, int y,int w, int h){
		 long[] freq=new long[256];
		 int i,j;
		 int H=image.getHeight();
		 int W=image.getWidth();
		 for( i=0;i<256;i++){
			 freq[i]=0;
		 }
    	 for(i=x;i<x+w;i++){
    		 for(j=y;j<y+h;j++){
                 if(i>=0&&i<W&&j>=0&&j<H){
    			   Color clr= new Color(image.getRGB(i, j));
    			   freq[clr.getGreen()]+=1;
                 }
    		 }
    	 }
		 long cdfmin=-1,sum=0;
		 long[] cdf= new long[256];
		 for( i=0;i<256;i++){
			 sum=sum+freq[i];
			 cdf[i]=sum;
			 if(cdfmin==-1&&cdf[i]!=0){cdfmin=cdf[i];}
		 }
		 int[] His=new int[256];
		 for( i=0;i<256;i++){
			 His[i]=(int)(cdf[i]-cdfmin)*255;
			 His[i]/=(w*h-cdfmin);
		 }
		 for(i=x;i<x+w;i++){
    		 for(j=y;j<y+h;j++){
    			if(i>=0&&i<W&&j>=0&&j<H){
    			 Color clr= new Color(image.getRGB(i, j));
    			 int a;
    			 a=His[clr.getGreen()];
    			 image.setRGB(i,j,new Color(a,a,a).getRGB());
    			}
    		 }
    	 }
		 
	 }
	 
	/* public void median(){
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
		 
		 Grid G=new Grid();
		 for(int i=1;i<image.getWidth()-1;i++){
			 for(int j=1;j<image.getHeight()-1;j++){
	    		int s=0;
	    		G.size=0;
	    		for(int y=0;y<3;y++){
	    			for(int z=0;z<3;z++){
	    				Color c=new Color(tmpi.getRGB(i+z-1,j+y-1));
	    				G.add(c.getGreen());
	    			}
	    		}
	    		s=G.median();
	    		image.setRGB(i, j, new Color(s,s,s).getRGB());
	    	 }
	     }
		 
	 }*/
	 
	 public void dilate(){
		int i,j;
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		
	     
	     for(i=0;i<image.getWidth();i++){
	    	 for(j=0;j<image.getHeight();j++){
	    		 image.setRGB(i,j,new Color(255,255,255).getRGB());
	    	 }
	     }
	     for(i=1;i<image.getWidth()-1;i++){
	    	 for(j=1;j<image.getHeight()-1;j++){
	    		 Color c= new Color(tmpi.getRGB(i, j));
	    		 if(c.getBlue()<5){
	    			 image.setRGB(i,j,new Color(0,0,0).getRGB());
	    			 image.setRGB(i-1,j,new Color(0,0,0).getRGB());
	    			 image.setRGB(i,j-1,new Color(0,0,0).getRGB());
	    			 image.setRGB(i+1,j,new Color(0,0,0).getRGB());
	    			 image.setRGB(i,j+1,new Color(0,0,0).getRGB());
	    		 }
	    			 
	    	 }
	     }
	     
	 }
	 
	 public void dilate8(){
		 int i,j;
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		
	     
	     for(i=0;i<image.getWidth();i++){
	    	 for(j=0;j<image.getHeight();j++){
	    		 image.setRGB(i,j,new Color(255,255,255).getRGB());
	    	 }
	     }
	     
	     for(i=1;i<image.getWidth()-1;i++){
	    	 for(j=1;j<image.getHeight()-1;j++){
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
	 
	 public void erode(){
			int i,j;
			 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
			    Graphics g = tmpi.getGraphics();
			    g.drawImage(image, 0, 0, null);
			    g.dispose();
			
		     
		     for(i=0;i<image.getWidth();i++){
		    	 for(j=0;j<image.getHeight();j++){
		    		 image.setRGB(i,j,new Color(255,255,255).getRGB());
		    	 }
		     }
		     for(i=1;i<image.getWidth()-1;i++){
		    	 for(j=1;j<image.getHeight()-1;j++){
		    		 Color c1,c2,c3,c4,c5;
		    		 c1= new Color(tmpi.getRGB(i, j));
		    		 c2= new Color(tmpi.getRGB(i-1, j));
		    		 c3= new Color(tmpi.getRGB(i, j-1));
		    		 c4= new Color(tmpi.getRGB(i+1, j));
		    		 c5= new Color(tmpi.getRGB(i, j+1));
		    		 if(c1.getRed()==0&&c2.getRed()==0&&c3.getRed()==0&&c4.getRed()==0&&c5.getRed()==0){
		    			 image.setRGB(i,j,new Color(0,0,0).getRGB());
		    		 }
		    		 else{
		    			 image.setRGB(i,j,new Color(255,255,255).getRGB());
		    		 }
		    		 
		    	 }
		     }
		     
	}
	 
	 public void mask(){
		
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
		 
		 int[][] M=new int[3][3];
		 int A;
		 M[0][0]=2; M[1][0]=2; M[2][0]=0; 
		 M[0][1]=2; M[1][1]=0; M[2][1]=-2;
		 M[0][2]=0; M[1][2]=-2; M[2][2]=-2; 
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		int s=0;
	    		for(int y=0;y<3;y++){
	    			for(int z=0;z<3;z++){
	    				Color c=new Color(tmpi.getRGB(i+z-1,j+y-1));
	    				s+=(c.getGreen()*M[z][y]);
	    			}
	    		}
	    		if(s<0){s=(-1)*s;}
	    		if(s>=256){s=255;}
	    		image.setRGB(i, j, new Color(s,s,s).getRGB());
	    	 }
	     }
	 } 
	 
	 public void background()
	 {
		
		int x=0;int y=0;

		 flag= new boolean[image.getWidth()][5000];
		 for(int i=0;i<image.getWidth();i++){
			   for(int j=0;j<image.getHeight();j++){
				  flag[i][j]=false;
			   }
		 }
	 
	 
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
		 
		 int[] xcor=new int [8000000];
		 int[] ycor=new int [8000000];
		 xcor[0]=0;
		 ycor[0]=0;
		 int k=0;int c=0;
		  /*  for(i=0;i<20;i++)
		    {
		    	for(j=0;j<20;j++)
		    	{
		    		x=i*60;y=j*60;
		    		// recurse(tmpi,x,y,flag,x+60,y+60);
		    	}
		    }*/
		// recurse(tmpi,x,y,flag);
		 int t=5;
		 Color c1,c2,c3,c4,c5;
		 for(;k!=-1;)
		 {   System.out.println(k+" a");
			c=0;x=xcor[k];y=ycor[k];
			c1= new Color(tmpi.getRGB(x, y));
			flag [xcor[k]][ycor[k]]=true;
if((x-1>=0)&&(x-1<image.getWidth())&&(y>=0)&&(y<image.getHeight())&&(flag[x-1][y]==false))
{
	c2= new Color(tmpi.getRGB(x-1, y));
	 if(((c1.getBlue()-c2.getBlue())<t)&&((c1.getBlue()-c2.getBlue())>(-t)))
	 {
		 image.setRGB(x-1,y,new Color(255,255,255).getRGB());
		 c++;k++;
		 xcor[k]=x-1;ycor[k]=y;
		 flag[x-1][y]=true;
	 }
}

if((x+1>=0)&&(x+1<image.getWidth())&&(y>=0)&&(y<image.getHeight())&&(flag[x+1][y]==false))
{
	c3= new Color(tmpi.getRGB(x+1, y));
	 if(((c1.getBlue()-c3.getBlue())<t)&&((c1.getBlue()-c3.getBlue())>(-t)))
	 {
		 image.setRGB(x+1,y,new Color(255,255,255).getRGB());
		 c++;k++;
		 xcor[k]=x+1;ycor[k]=y;
		 flag[x+1][y]=true;
	 }
}


if((x>=0)&&(x<image.getWidth())&&(y-1>=0)&&(y-1<image.getHeight())&&(flag[x][y-1]==false))
{
	c4= new Color(tmpi.getRGB(x, y-1));
	 if(((c1.getBlue()-c4.getBlue())<t)&&((c1.getBlue()-c4.getBlue())>(-t)))
	 {
		 image.setRGB(x,y-1,new Color(255,255,255).getRGB());
		 c++;k++;
		 xcor[k]=x;ycor[k]=y-1;
		 flag[x][y-1]=true;
	 }
}

if((x>=0)&&(x<image.getWidth())&&(y+1>=0)&&(y+1<image.getHeight())&&(flag[x][y+1]==false))
{
	c5= new Color(tmpi.getRGB(x, y+1));
	 if(((c1.getBlue()-c5.getBlue())<t)&&((c1.getBlue()-c5.getBlue())>(-t)))
	 {
		 image.setRGB(x,y+1,new Color(255,255,255).getRGB());
		 c++;k++;
		 xcor[k]=x;ycor[k]=y+1;
		 flag[x][y+1]=true;
	 }
}
	
		 if(c==0){
			 k--;
		 }
		 
		 }
		 
		 return ;
	 }
	 
	 
	 public void horizontal(int w)
		{
			
		
			//newwidth=(int)newidth;
			float nw,ow;
			ow=image.getWidth();
			nw=(float)(w);
			
			 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
			    Graphics g = tmpi.getGraphics();
			    g.drawImage(image, 0, 0, null);
			    g.dispose();
			    
			    
			image = new BufferedImage(w, image.getHeight(), image.getType());
			int i=0,j=0,k=0;
			float y1=0,y2=0,x1;
			float val=0,ip=0;
			Color c1,c2;
			 System.out.println(ow+" "+nw+" "+ip);
			
		for(i=0;i<image.getWidth();i++)
		{
			for(j=0;j<image.getHeight();j++)
			{ //System.out.println(i+" "+j+" "+image.getHeight()+" "+image.getWidth());
				ip=i*ow;
				ip=ip/nw;
				k=(int)(ip);
				x1=(float)(k);
				if(x1<0){x1=0;}
				if(k>=tmpi.getWidth()-1){k=tmpi.getWidth()-2;}
				if(k<0){k=0;}
				//System.out.println(k+" is k "+"    "+tmpi.getWidth());
				c2= new Color(tmpi.getRGB(k+1, j));
				c1= new Color(tmpi.getRGB(k, j));
				y2=c2.getBlue();
				y1=c1.getBlue();
				//System.out.println(ip+" "+k+" "+y1+" "+y2);
				val=y1+((y2-y1)*(ip-x1));
				k=(int)(val);
				if(k<0){k=0;}
				if(k>255){k=255;}
				image.setRGB(i,j,new Color(k,k,k).getRGB());
			}
		}
			//System.out.println("hello world");
			return ;
		}
		
		public void vertical(int v)
		{
			
		
			//newwidth=(int)newidth;
			float nv,ov;
			ov=image.getHeight();
			nv=(float)(v);
			
			 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
			    Graphics g = tmpi.getGraphics();
			    g.drawImage(image, 0, 0, null);
			    g.dispose();
			    
			    
			image = new BufferedImage(image.getWidth(), v, image.getType());
			int i=0,j=0,k=0;
			float y1=0,y2=0,x1;
			float val=0,ip=0;
			Color c1,c2;
			 System.out.println(ov+" imp "+nv+" "+ip);
			
		for(j=0;j<image.getHeight();j++)
		{
			for(i=0;i<image.getWidth();i++)
			{ System.out.println(j+" "+i+" "+image.getHeight()+" "+image.getWidth());
				ip=j*ov;
				ip=ip/nv;
				k=(int)(ip);
				x1=(float)(k);
				if(x1<0){x1=0;}
				if(k>(tmpi.getHeight()-2)){k=tmpi.getHeight()-2;}
				System.out.println(ip+"  lol "+k+" "+y1+" "+y2);
				c2= new Color(tmpi.getRGB(i, k+1));
				c1= new Color(tmpi.getRGB(i, k));
				y2=c2.getBlue();
				y1=c1.getBlue();
				val=y1+((y2-y1)*(ip-x1));
				k=(int)(val);
				if(k<0){k=0;}
				if(k>255){k=255;}
				image.setRGB(i,j,new Color(k,k,k).getRGB());
			}
		}
			//System.out.println("hello world");
			return ;
		}
	 
	 public void setimg(BufferedImage img){
		 image = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		 Graphics g = image.getGraphics();
		 g.drawImage(img, 0, 0, null);
		 g.dispose();
		 
		 flag= new boolean[img.getWidth()][img.getHeight()];
		 for(int i=0;i<img.getWidth();i++){
			   for(int j=0;j<img.getHeight();j++){
				  flag[i][j]=false;
			   }
		 }
	 }
	 
	 public boolean check(int i,int j){
		 Color c=new Color(image.getRGB(i, j));
		 if(flag[i][j]==false&&c.getGreen()==0){
			 return true;
		 }
		 return false;
	 }
	 
	 public BufferedImage getimg(){
		 return image;
	 }
	 
	 public void getchar(int x1,int x2,int y1,int y2)
	 {
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		    
		    
		image = new BufferedImage(x2-x1, y2-y1, image.getType());
		int i=0,j=0,p;
		Color c1;
		
		for(i=0;i<(x2-x1);i++)
		{
			for(j=0;j<(y2-y1);j++)
			{
				c1= new Color(tmpi.getRGB(i+x1, j+y1));
				p=c1.getBlue();
				image.setRGB(i,j,new Color(p,p,p).getRGB());
			}
		}
		
		return ;
	 }
	 
}
