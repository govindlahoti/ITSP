import java.awt.image.BufferedImage;


public class Compfinder {
    Components[] A;
	int size;
    
	void findcomps(BufferedImage img){
	   Components[] newA=new Components[7000];
	   int k=0;
	   int w=img.getWidth();
	   int h=img.getHeight();
	   Components c=new Components();
	   c.setimage(img);
	   for(int j=0;j<h;j++){
		   for(int i=0;i<w;i++){
			   if(c.check(i,j)){
				   Components tmpc=new Components();
				   tmpc.explore(i, j);
				   if(tmpc.elegible()){
					   if(k<7000){
						   newA[k]=tmpc;
						   k++;
					   }
					   else{
						   System.out.println("error code 101");
					   }
				   }
			   }
		   }
	   }
	   A=new Components[k];
	   for(int i=0;i<k;i++){
		   A[i]=newA[i];
	   }
	   size=k;
	   System.out.println(size);
   }
   
   void filter(){
	   boolean[] fl=new boolean[size];
	   for(int i=0;i<size;i++){
		   fl[i]=true;
	   }
	   int[] arr=new int[size];
	   for(int i=0;i<size;i++){
		   int tmps=0;
		   for(int j=0;j<size;j++){
			  if(j!=i){
				  if(A[j].x>A[i].x&&A[j].y>A[i].y){
					  if((A[j].x+A[j].width)<(A[i].x+A[i].width)&&(A[j].y+A[j].height)<(A[i].y+A[i].height)){
					     arr[tmps]=j;
					     tmps++;
					  }
				  }
			  }
		   }
		   if(tmps<3){
			   for(int y=0;y<tmps;y++){
				   fl[arr[y]]=false;
			   }
		   }
		   else{
			   fl[i]=false;
		   }
	   }
	   
	   int t=0;
	   for(int i=0;i<size;i++){
		   if(fl[i]==true)t++;
	   }
	   Components[] tmpA=new Components[t];
	   int k=0;
	   for(int i=0;i<size;i++){
		   if(fl[i]==true){
		     tmpA[k]=A[i];
		     k++;
		   }
	   }
	   A=new Components[t];
	   for(int i=0;i<t;i++){
		   A[i]=tmpA[i];
		  // A[i].mark();
	   }
	   size=t;
   }
   
   void binarize(BufferedImage img){
	   A[0].setimage(img);
	   for(int i=0;i<size;i++){
		  A[i].binarize();
	   }
	   A[0].removebackgr();
   }
   
   void binarize2(BufferedImage img){
	   A[0].setimage(img);
	   for(int i=0;i<size;i++){
		  // A[i].turntogrey();
		   A[i].binarize2();
	   }
	   A[0].removebackgr();
   }
   
   void mark(){
	   for(int i=0;i<size;i++){
		   A[i].mark();
	   }
   }
   
}
