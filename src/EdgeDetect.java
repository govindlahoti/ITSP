import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class EdgeDetect {
    BufferedImage image;
    int w,h;
    int[][] theta;
    int[][] A;
    
    void setimg(BufferedImage img){
    	image=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
    	Graphics g=image.getGraphics();
    	g.drawImage(img, 0, 0, null);
    	g.dispose();
    	w=image.getWidth();
    	h=image.getHeight();
    	theta=new int[w][h];
    	A=new int[w][h];
    	System.out.println(w+" "+h);
    }
	
    public void turntogrey(){//function used for grey scale conversion
   	 int i,j;
   	 for(i=0;i<w;i++){
   		 for(j=0;j<h;j++){
   			 Color clr= new Color(image.getRGB(i, j));
   			 int avg;//co-efficients are different for red green blue as it increases the contrast
   			 avg=((100)*clr.getBlue()+(0)*clr.getGreen()+(0)*clr.getRed())/100;
   			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
   		 }
   	 }
   	}
    
    public void turntored(){//function used for grey scale conversion
      	 int i,j;
      	 for(i=0;i<w;i++){
      		 for(j=0;j<h;j++){
      			 Color clr= new Color(image.getRGB(i, j));
      			 int avg;//co-efficients are different for red green blue as it increases the contrast
      			 avg=((0)*clr.getBlue()+(0)*clr.getGreen()+(100)*clr.getRed())/100;
      			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
      		 }
      	 }
    }
    
    public void turntogreen(){//function used for grey scale conversion
     	 int i,j;
     	 for(i=0;i<w;i++){
     		 for(j=0;j<h;j++){
     			 Color clr= new Color(image.getRGB(i, j));
     			 int avg;//co-efficients are different for red green blue as it increases the contrast
     			 avg=((0)*clr.getBlue()+(100)*clr.getGreen()+(0)*clr.getRed())/100;
     			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
     		 }
     	 }
    }
    
    public void turntoblue(){//function used for grey scale conversion
     	 int i,j;
     	 for(i=0;i<w;i++){
     		 for(j=0;j<h;j++){
     			 Color clr= new Color(image.getRGB(i, j));
     			 int avg;//co-efficients are different for red green blue as it increases the contrast
     			 avg=((100)*clr.getBlue()+(0)*clr.getGreen()+(0)*clr.getRed())/100;
     			 image.setRGB(i,j,new Color(avg,avg,avg).getRGB());
     		 }
     	 }
   }
    
    void GBlur(){
    	int [][] M=new int[5][5];
    	M[0][0]=2 ; M[1][0]=4 ; M[2][0]=5 ; M[3][0]=4 ; M[4][0]=2 ;
    	M[0][1]=4 ; M[1][1]=9 ; M[2][1]=12 ; M[3][1]=9 ; M[4][1]=4 ;
    	M[0][2]=5 ; M[1][2]=12 ; M[2][2]=15 ; M[3][2]=12 ; M[4][2]=5 ;
    	M[0][3]=4 ; M[1][3]=9 ; M[2][3]=12 ; M[3][3]=9 ; M[4][3]=4 ;
    	M[0][4]=2 ; M[1][4]=4 ; M[2][4]=5 ; M[3][4]=4 ; M[4][4]=2 ;
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    		   int s=0,t=0;
    		   for(int i1=i-2;i1<=i+2;i1++){
    		     	for(int j1=j-2;j1<=j+2;j1++){
    				  if(i1>=0&&i1<w&&j1>=0&&j1<h){
    					Color c=new Color(image.getRGB(i1, j1));
    					s+=c.getBlue()*M[i1-i+2][j1-j+2];
    					t+=M[i1-i+2][j1-j+2];
       				  }
   					}
   				}
    		   s/=t;
    		   image.setRGB(i, j, new Color(s,s,s).getRGB());
    		}
    	}
    }
    
    int classify(double x){
    	if(x>67.5||x<-67.5)return 90;
    	if(x<67.5&&x>22.5)return 45;
    	if(x>-67.5&&x<-22.5)return -45;
    	return 0;
    }
    
    public void Sobelop(){
		
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		 Graphics g = tmpi.getGraphics();
		 g.drawImage(image, 0, 0, null);
		 g.dispose();
		 
		 int[][] M=new int[3][3];
		 int[][] Ax=new int[image.getWidth()][image.getHeight()];
		  
		 M[0][0]=-1; M[1][0]=0; M[2][0]=1; 
		 M[0][1]=-2; M[1][1]=0; M[2][1]=2;
		 M[0][2]=-1; M[1][2]=0; M[2][2]=1;
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		int s=0;
	    		for(int y=0;y<3;y++){
	    			for(int z=0;z<3;z++){
	    				Color c=new Color(tmpi.getRGB(i+z-1,j+y-1));
	    				s+=(c.getGreen()*M[z][y]);
	    			}
	    		}
	    		Ax[i][j]=s;
	    	 }
	     }
		 
		 int[][] Ay=new int[image.getWidth()][image.getHeight()];
		 M[0][0]=1; M[1][0]=2; M[2][0]=1; 
		 M[0][1]=0; M[1][1]=0; M[2][1]=0;
		 M[0][2]=-1; M[1][2]=-2; M[2][2]=-1; 
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		int s=0;
	    		for(int y=0;y<3;y++){
	    			for(int z=0;z<3;z++){
	    				Color c=new Color(tmpi.getRGB(i+z-1,j+y-1));
	    				s+=(c.getGreen()*M[z][y]);
	    			}
	    		}
	    		Ay[i][j]=s;
	    	    if(Ax[i][j]==0){
	    	        theta[i][j]=90;
	    	    }
	    	    else{
	    	    	theta[i][j]=(int) Math.toDegrees(Math.atan(Ay[i][j]/Ax[i][j]));
	    	    	theta[i][j]=classify(theta[i][j]);
	    	    }
	    	 }
	     }
		 
		 int min=12345,max=0;
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		Ax[i][j]= (int) Math.sqrt(Ax[i][j]*Ax[i][j]+Ay[i][j]*Ay[i][j]);
	    		if(Ax[i][j]<min)min=Ax[i][j];
	    		if(Ax[i][j]>max)max=Ax[i][j];
	    	 }
	     }
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		Ax[i][j]=(Ax[i][j]-min)*255;
	    		Ax[i][j]/=(max-min);
	    	 }
	     }
		 for(int i=1;i<image.getWidth()-1;i++){
	    	 for(int j=1;j<image.getHeight()-1;j++){
	    		 
	    		 image.setRGB(i, j, new Color(Ax[i][j],Ax[i][j],Ax[i][j]).getRGB());
	    		 A[i][j]=Ax[i][j];
	    	 }
	     }
		 
		 
	 } 
    
    public void merge(BufferedImage img){
    	int i,j;
      	 for(i=0;i<w;i++){
      		 for(j=0;j<h;j++){
      			Color clr1= new Color(image.getRGB(i, j));
      			Color clr2= new Color(img.getRGB(i, j));
      			int n=clr1.getRed();
      			if(clr2.getRed()>n)n=clr2.getRed();
      			img.setRGB(i, j, new Color(n,n,n).getRGB());
      			
      		 }
      	 }
    }
    
    void nonmaxsup(){
    	
    	for(int i=1;i<image.getWidth()-1;i++){
    		for(int j=1;j<image.getHeight()-1;j++){
    			switch(theta[i][j]){
    			case 90:
    				if(A[i][j]>=A[i][j-1]&&A[i][j]>=A[i][j+1]){
    				}
    				else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				}
    				break;
    			case 0:
    				if(A[i][j]>=A[i-1][j]&&A[i][j]>=A[i+1][j]){
    				}
    				else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				}
    				break;
    			case 45:
    				if(A[i][j]>=A[i+1][j-1]&&A[i][j]>=A[i-1][j+1]){
    				}
    				else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				}
    				break;
    			case -45:
    				if(A[i][j]>=A[i-1][j-1]&&A[i][j]>=A[i+1][j+1]){
    				}
    				else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				}
    				break;
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
     
    public void thresh(BufferedImage img){
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			Color c=new Color(img.getRGB(i, j));
    			if(c.getBlue()<100){
    				img.setRGB(i, j, new Color(255,255,255).getRGB());
    			}
    			else{
    					img.setRGB(i, j, new Color(0,0,0).getRGB());
    			}
    		}
    	}
    }
    
    void doublethresh(){
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			Color c=new Color(image.getRGB(i, j));
    			if(c.getBlue()>80){
    				image.setRGB(i, j, new Color(255,255,255).getRGB());
    			}
    			else{
    				if(c.getBlue()>45){
    					image.setRGB(i, j, new Color(100,100,100).getRGB());
    				}
    				else{
    				    image.setRGB(i, j, new Color(0,0,0).getRGB());
    				}
    			}
    		}
    	}
    }
    
    void histeresis(){
    	boolean[][] tflag=new boolean[w][h];
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			tflag[i][j]=false;
    		}
    	}
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			Color c=new Color(image.getRGB(i, j));
    			if(c.getRed()==255&&tflag[i][j]==false){
    				int[] X=new int[50000];
    				int[] Y=new int[50000];
    				int size=1;
    				X[0]=i; Y[0]=j;
    				while(size!=0){
    					int tmpx,tmpy;
    					tmpx=X[size-1]; tmpy=Y[size-1];
    					int i1;
    					for( i1=tmpx-1;i1<=tmpx+1;i1++){
    						for(int j1=tmpy-1;j1<=tmpy+1;j1++){
    							if(i1>=0&&i1<w&&j1>=0&&j1<h){
    								if(tflag[i1][j1]==false){
    									Color c2=new Color(image.getRGB(i1,j1));
    									if(c2.getRed()>0){
    										image.setRGB(i1, j1, new Color(255,255,255).getRGB());
    										tflag[i1][j1]=true;
    										X[size]=i1;
    										Y[size]=j1; size++; i1=400003; j1=400003;
    									}
    								}
    							}
    						}
    					}
    					if(i1<400000){size--;}
    				}
    			}
    		}
    	}
    	
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			Color c=new Color(image.getRGB(i, j));
    			if(c.getBlue()!=255){
    				image.setRGB(i, j, new Color(0,0,0).getRGB());
    			}
    		}
    	}
    }
    
    //used to dilate a binary image(N-8 is used)
	 public void dilate8(){
		 int i,j;
		 BufferedImage tmpi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		    Graphics g = tmpi.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		
	     
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
	 
    void invert(){
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			Color c=new Color(image.getRGB(i, j));
    			if(c.getBlue()==0){
    				image.setRGB(i, j, new Color(255,255,255).getRGB());
    			}
    			else{
    				image.setRGB(i, j, new Color(0,0,0).getRGB());
    			}
    		}
    	}
    }
    
    
    BufferedImage getimg(){
    	return image;
    }
    
}
