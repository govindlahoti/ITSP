package ocr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

import java.util.*;

import javax.*;

import java.awt.image.*;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;


public class Char extends Myimage{
    int x,y;//co ordinates of left top corner
    int height,width; 
    int x0,y0;//any point inside character
    BufferedImage ch;
    
    Char(){
    	height=0;
    	width=0;
    	ch=null;
    }
    
    void explore(int a,int b){
    	int tmpx,tmpy,maxx,maxy,minx,miny;
    	maxx = a;  x0=a; y0=b;
    	minx = a;
    	maxy = b;
    	miny = b;
    	int h,w;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[90000];
    	int[] Y=new int[90000];
    	X[0]=a; Y[0]=b;
    	flag[a][b]=true;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0&&flag[i][j]==false){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			flag[i][j]=true;
    	        			if(j<miny)miny=j;
    	        			if(j>maxy)maxy=j;
    	        			if(i<minx)minx=i;
    	        			if(i>maxx)maxx=i;
    	        			size++;
    	        			i=40001; j=40001;
    	        		}
    				}
    			}
    		}
    		if(i<40000){
    			size--;
    		}
    	}
    	
    	x=minx; y=miny;
    	width=maxx-minx+1;
    	height=maxy-miny+1;
    }
    
    void dexplore(){
    	int tmpx,tmpy;
    	int h,w;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[90000];
    	int[] Y=new int[90000];
    	X[0]=x0; Y[0]=y0;
    	flag[x0][y0]=false;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0&&flag[i][j]==true){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			flag[i][j]=false;
    	        			size++;
    	        			i=40001; j=40001;
    	        		}
    				}
    			}
    		}
    		if(i<40000){
    			size--;
    		}
    	}
    	
    }
    
    void mark(){
    	for(int i=x;i<x+width;i++){
    		image.setRGB(i, y, new Color(0,0,255).getRGB());
    		image.setRGB(i, y+height, new Color(0,0,255).getRGB());
    	}
    	for(int j=y;j<y+height;j++){
    		image.setRGB(x, j, new Color(0,0,255).getRGB());
    		image.setRGB(x+width, j, new Color(0,0,255).getRGB());
    	}
    }
    
    void mark2(int u, int v){
    	int h,w,tmpx,tmpy;
    	int p,q;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[90000];
    	int[] Y=new int[90000];
    	X[0]=x0; Y[0]=y0;
    	p=u;q=v;
    	q=q%254;
    	q=q+1;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			size++;
    	        			if(p==0){
    	        			image.setRGB(i, j, new Color(2,q,0).getRGB());}
    	        			if(p==1){
    	        				
        	        			image.setRGB(i, j, new Color(0,q,0).getRGB());}
    	        			if(p==2){
        	        			image.setRGB(i, j, new Color(0,2,q).getRGB());}
    	        			i=40001; j=40001;
    	        		}
    				}
    			}
    		}
    		if(i<40000){
    			size--;
    		}
    	}

    }
    
    void mark7(int u){
    	int h,w,tmpx,tmpy;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[900000];
    	int[] Y=new int[900000];
    	X[0]=x0; Y[0]=y0;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			size++;
    	        			if(u%3==0){
    	        			image.setRGB(i, j, new Color(0,200,0).getRGB());}
    	        			if(u%3==1){
    	        				
        	        			image.setRGB(i, j, new Color(0,200,0).getRGB());}
    	        			if(u%3==2){
        	        			image.setRGB(i, j, new Color(0,200,0).getRGB());}
    	        			i=tmpx+8; j=tmpy+8;
    	        		}
    				}
    			}
    		}
    		if(i<tmpx+5){
    			size--;
    		}
    	}

    }
    
    void mark3(BufferedImage im){
    	
    	int h,w,tmpx,tmpy;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[90000];
    	int[] Y=new int[90000];
    	X[0]=x0; Y[0]=y0;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			size++;
    	        			im.setRGB(i, j, new Color(0,0,0).getRGB());
    	        			image.setRGB(i, j, new Color(0,200,0).getRGB());
    	        			i=40001; j=40001;
    	        		}
    				}
    			}
    		}
    		if(i<40000){
    			size--;
    		}
    	}

    }
    
    public void write(){
    	ch=new BufferedImage(width+1,height+1,image.getType());
    	for(int i=0;i<width+1;i++){
    		for(int j=0;j<height+1;j++){
    			ch.setRGB(i, j, new Color(255,255,255).getRGB());
    		}
    	}
    	
    	
    	int h,w,tmpx,tmpy;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[90000];
    	int[] Y=new int[90000];
    	X[0]=x0; Y[0]=y0;
    	while(size!=0){
    		int i,j;
    		tmpx=X[size-1];
    		tmpy=Y[size-1];
    		for( i=tmpx-1;i<=tmpx+1;i++){
    			for( j=tmpy-1;j<=tmpy+1;j++){
    				if((i!=tmpx||j!=tmpy)&&i>=0&&i<w&&j>=0&&j<h){
    					Color c= new Color(image.getRGB(i,j));
    	    			if(c.getGreen()==0){
    	        			X[size]=i;
    	        			Y[size]=j;
    	        			size++;
    	        			ch.setRGB(i-x, j-y, new Color(0,0,0).getRGB());
    	        			image.setRGB(i, j, new Color(0,200,0).getRGB());
    	        			i=40001; j=40001;
    	        		}
    				}
    			}
    		}
    		if(i<40000){
    			size--;
    		}
    	}
    }
    
    public int size(){
    	return height*width;
    }
}
