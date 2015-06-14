import java.awt.Color;
import java.awt.image.BufferedImage;


/*
 * It takes in put as a binary image found out by canny edge detector
 * and explores all connected components inside it.
 */

public class Components {
	int x,y;//co-ordinates of left top corner
    int height,width; //Height and Width in pixels of component
    int x0,y0;//any point inside character
    int nofp;//number of pixels contained in the component
    static BufferedImage image;//common image of all components
    static boolean[][] flag;
    int[] ax;//these arrays store boundary of character 
    int[] ay;
    
    
    void setimage(BufferedImage img){
    	int w,h;
    	w=img.getWidth();
    	h=img.getHeight();
    	/*image=new BufferedImage(w,h,img.getType());
    	Graphics g=image.getGraphics();
    	g.drawImage(img, 0, 0, null);
    	g.dispose();*/
    	image=img;
       
    	flag=new boolean[w][h];
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			flag[i][j]=false;
    		}
    	}
    }
    
    boolean check(int i,int j){
    	Color c=new Color(image.getRGB(i, j));
    	if(c.getBlue()==0&&flag[i][j]==false){
    		return true;
    	}
    	return false;
    }
    
    void explore(int a,int b){
    	int[] tmpax=new int[90000];
    	int[] tmpay=new int[90000];
    	tmpax[0]=a; tmpay[0]=b;
    	int tmpx,tmpy,maxx,maxy,minx,miny;
    	maxx = a;  x0=a; y0=b;
    	minx = a;
    	maxy = b;
    	miny = b;
    	int h,w; nofp=1;
    	h=image.getHeight();
    	w=image.getWidth();
    	int size=1;
    	int[] X=new int[900000];
    	int[] Y=new int[900000];
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
    	        		    tmpax[nofp]=i; tmpay[nofp]=j; nofp++;
    	        			if(j<miny)miny=j;
    	        			if(j>maxy)maxy=j;
    	        			if(i<minx)minx=i;
    	        			if(i>maxx)maxx=i;
    	        			size++;
    	        			i=4000001; j=4000001;
    	        		}
    				}
    			}
    		}
    		if(i<4000000){
    			size--;
    		}
    	}
    	
    	x=minx; y=miny;
    	width=maxx-minx+1;
    	height=maxy-miny+1;
    	ax=new int[nofp];
    	ay=new int[nofp];
    	for(int i=0;i<nofp;i++){
    		ax[i]=tmpax[i];
    		ay[i]=tmpay[i];
    	}
    	
    }

    
    void mark(){
    	for(int i=x+1;i<x+width-1;i++){
    		image.setRGB(i,y+1,new Color(200,0,0).getRGB());
    		image.setRGB(i,y+height-1,new Color(200,0,0).getRGB());
    	}
    	for(int j=y+1;j<y+height-1;j++){
    		image.setRGB(x+1,j,new Color(200,0,0).getRGB());
    		image.setRGB(x+width-1,j,new Color(200,0,0).getRGB());
    	}
    }

    /*
     * used to ignore connected components which are certainly not a character based on 
     * their size , aspect ratio, etc.
     */
    public boolean elegible(){
    	double a;
    	a=(double) height/width;
    	if(a<0.05||a>20)return false;
    	if(nofp<15)return false;
    	if(width>=image.getWidth()/3||height>=image.getHeight()/3){
    		//return false;
    	}
    	return true;
    }
    
    void binarize(){
    	int w,h;
    	w=image.getWidth(); h=image.getHeight();
    	int sx,sx2;
    	sx=0; sx2=0;
    	for(int i=x;i<x+width;i++){
    		for(int j=y;j<y+height;j++){
    		   Color c=new Color(image.getRGB(i, j));
    		   sx+=c.getBlue();
    		   sx2+=(c.getGreen())*(c.getBlue());
    	    }
    	}
    	int N=width*height;
    	int T=sx/N;
    	T-=((int) Math.sqrt(sx2/N-(sx/N)*(sx/N)))/5;//thershold
    	
    	int[] arr=new int[12];
    	int tsize=0;
    	int tx,ty;
    	Color c;
    	    	
    	tx=x-1; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y+height;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y+height;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	
    	for(int i=0;i<tsize;i++){
    		int min=i;
    		for(int j=i+1;j<tsize;j++){
    			if(arr[j]<arr[min])min=j;
    		}
    		int z=arr[i];
    		arr[i]=arr[min];
    		arr[min]=z;
    	}
    	//now median of arr i.e.arr[tsize/2] is background intensity
    	if(arr[tsize/2]>=T){
    		for(int i=x;i<x+width;i++){
    			for(int j=y;j<y+height;j++){
    				c=new Color(image.getRGB(i, j));
    				  if(c.getRed()>T){
    					image.setRGB(i, j, new Color(255,255,255).getRGB());
    				  }
    				  else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				  }	
    				  flag[i][j]=true;
    			}
    		}
    	}
    	else{
    		for(int i=x;i<x+width;i++){
    			for(int j=y;j<y+height;j++){
    				c=new Color(image.getRGB(i, j));
    				  if(c.getRed()<=T){
    					image.setRGB(i, j, new Color(255,255,255).getRGB());
    				  }
    				  else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				  }	
    				  flag[i][j]=true;
    			}
    		}
    	}
    	
    }
    
    void turntogrey(){
    	int varr,varg,varb;
    	int sxr=0,sx2r=0,sxg=0,sx2g=0,sxb=0,sx2b=0;
    	int N=height*width;
    	for(int i=x;i<x+width;i++){
    		for(int j=y;j<y+height;j++){
    			Color c=new Color(image.getRGB(i, j));
    			sxr+=c.getRed(); sx2r+=(c.getRed())*(c.getRed());
    			sxg+=c.getGreen(); sx2g+=(c.getGreen())*(c.getGreen());
    			sxb+=c.getBlue(); sx2b+=(c.getBlue())*(c.getBlue());
    		}
    	}
    	varr=sx2r*N-(sxr)*(sxr);
    	varg=sx2g*N-(sxg)*(sxg);
    	varb=sx2b*N-(sxb)*(sxb);
    	char max;
    	if(varr>varg){
    		if(varr>varb)max='r';
    		else{
    			max='b';
    		}
    	}
    	else{
    		if(varg>varb)max='g';
    		else{
    			max='b';
    		}
    	}
    	for(int i=x;i<x+width;i++){
    		for(int j=y;j<y+height;j++){
    			Color c=new Color(image.getRGB(i, j));
    			int r,g,b;
    			r=c.getRed(); g=c.getGreen(); b=c.getBlue();
    			if(max=='r'){
    				int t=(72*r+21*g+7*b)/100;
    				image.setRGB(i, j, new Color(t,t,t).getRGB());
    			}
    			if(max=='g'){
    				int t=(72*g+21*r+7*b)/100;
    				image.setRGB(i, j, new Color(t,t,t).getRGB());
    			}
    			if(max=='b'){
    				int t=(72*b+21*g+7*r)/100;
    				image.setRGB(i, j, new Color(t,t,t).getRGB());
    			}
    		}
    	}
    }
    
    
    void binarize2(){
    	int F,s=0;
    	Color c;
    	int h=image.getHeight();
    	int w=image.getWidth();
    	for(int i=0;i<nofp;i++){
    		c=new Color(image.getRGB(ax[i], ay[i]));
    		s+=c.getBlue();
    	}
    	F=s/nofp;//foreground intensity
    	
    	int B;//background intensity
    	int[] arr=new int[12];
    	int tsize=0;
    	int tx,ty;
    	    	
    	tx=x-1; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width; ty=y-1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x-1; ty=y+height;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width; ty=y+height+1;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	tx=x+width+1; ty=y+height;
    	if(tx>=0&&tx<w&&ty>=0&&ty<h){
    		c=new Color(image.getRGB(tx, ty));
    		arr[tsize]=c.getBlue();
    		tsize++;
    	}
    	
    	for(int i=0;i<tsize;i++){
    		int min=i;
    		for(int j=i+1;j<tsize;j++){
    			if(arr[j]<arr[min])min=j;
    		}
    		int z=arr[i];
    		arr[i]=arr[min];
    		arr[min]=z;
    	}
    	//now median of arr i.e.arr[tsize/2] is background intensity
    	B=arr[tsize/2];
    	
    	if(F<B){
    		for(int i=x;i<x+width;i++){
    			for(int j=y;j<y+height;j++){
    			  if(flag[i][j]==false){
    				c=new Color(image.getRGB(i, j));
    				  if(c.getRed()<=F){
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				  }
    				  else{
    					image.setRGB(i, j, new Color(255,255,255).getRGB());
    				  }	
    				  flag[i][j]=true;
    			  }
    			}
    		}
    	}
    	else{
    		for(int i=x;i<x+width;i++){
    			for(int j=y;j<y+height;j++){
    				if(flag[i][j]==false){  
    				  c=new Color(image.getRGB(i, j));
    				  if(c.getRed()<=F){
    					image.setRGB(i, j, new Color(255,255,255).getRGB());
    				  }
    				  else{
    					image.setRGB(i, j, new Color(0,0,0).getRGB());
    				  }	
    				  flag[i][j]=true;
    				}
    			}
    		}
    	}
    	
    }
    
    void removebackgr(){
    	int w,h;
    	w=image.getWidth(); h=image.getHeight();
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			if(flag[i][j]==false){
    			  image.setRGB(i, j, new Color(255,255,255).getRGB());
    			}
    		}
    	}
    }
    
    
}
