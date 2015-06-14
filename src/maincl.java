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

public class maincl {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   BufferedImage img=null;
	   int i,j;
	   int num=138;
	   
		try{
	     img= ImageIO.read(new File("maxfont.jpg"));
	    }catch (IOException e) {
	    }
		
	 // System.out.println(img.getHeight());
	 // System.out.println(img.getWidth());
		
		BufferedImage newimg=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
		for(i=0;i<img.getWidth();i++){
		   for(j=0;j<img.getHeight();j++){
			  newimg.setRGB(i, j, new Color(255,255,255).getRGB());
		   }
		 }
		
		
	  Myimage image=new Myimage();
	   image.setimg(img);
	   image.turntogrey();
	  //image.histo();
	  //for(i=0;i<img.getWidth();i=i+100){
		//   for(j=0;j<img.getHeight();j=j+100){
			//   image.histo2(i, j, 100, 100);
		   //}
	   //}
	   
	   
	  //image.poweren();
	  //image.mask();
	  //image.median();
	  //image.background();
	  //image.turntobinary(120);
	  //image.turntobin2();
	    image.turntobin5();
	  //image.turntobin4();
	  //image.turntobin6();
	  //image.median();
	  //image.turntobin3();
	  //image.dilate();
	  //image.dilate8();
	  //image.dilate8();
	  //image.erode();
	  //image.dilate();
	  //image.erode();
	  //image.dilate();
	  //image.dilate();
	  
	  int k=0;
	   Char[] charar=new Char[5000];
	   for(i=0;i<img.getWidth();i++){
		   for(j=0;j<img.getHeight();j++){
			   if(image.check(i, j)==true){
				  if(k==1994){System.out.println("e");break;}
				   charar[k]=new Char();
				   (charar[k]).explore(i, j);
				   if((charar[k].size())>100){  
				     
                     //if(k==num){(charar[k]).write();}
					   k++;
				   }
			   }
		   }
	   }
	   
	 //zxcvbnm
	   int blocksize=100;
	  int gs,hs,gss,hss;
	  gs=img.getWidth();
	  hs=img.getHeight();
	  gs=gs/blocksize;
	  hs=hs/blocksize;
	  gs++;
	  hs++;
	  
	  section[][] secarray=new section[gs][hs];
	  
	  for(i=0;i<gs;i++)
	  {
		  for(j=0;j<hs;j++)
		  {
			 secarray[i][j]=new section(blocksize,gs,hs);
		  }
	  }
	  
	 
	
	  
	  for(i=0;i<k;i++)
	  {
		  gss=charar[i].x + (charar[i].width/2);
		  hss=charar[i].y + (charar[i]. height/2);
		  gss=gss/blocksize;
		  hss=hss/blocksize;
		
		  secarray[gss][hss].a[secarray[gss][hss].size]=i;
		  secarray[gss][hss].size++;
	  }
	  
	  
	  int ws=0;
	word[] w= new word[2000];
	w[0]=new word(k);
	for(i=1;i<1000;i++)
	{
		w[i]=new word();
	}
	
	w[0].searchword(0, charar, secarray);
	ws++;
	for(i=1;i<k;i++)
	{
		if(w[0].flag[i]==true){
			continue;
		}
		else{
			w[ws].searchword(i, charar, secarray);
			ws++;
		}
	}
	/*w[0].search(0, charar, secarray);ws=1;
	for(i=1;i<k;i++)
	{   System.out.println(i+" "+ws);
		if(w[0].flag[i]==true){
		if(w[0].find(i,ws,w)!=-1)
		{
			w[w[0].find(i,ws,w)].search(i, charar, secarray);
		}
	}
	else{
		w[ws].search(i, charar, secarray);
		ws++;
	}
	}*/
	
	 for(i=0;i<ws;i++)
	 { // System.out.println("wordsize "+ws+"  "+i+" no of charac"+w[i].size);
		// w[i].mark(charar);
	 }
	//w[191].mark(charar);
	 
	int nws=0;
	word[] nw=new word[2000];
	nw[0]=new word(k);
	for(i=1;i<2000;i++)
	{
		nw[i]=new word();
	}
	
	nw[0].searchchar(0, charar, w, 0);
	nws++;
	for(i=1;i<k;i++)
	{
		if(nw[0].flag[i]==true)
		{
			continue;
		}
		else{
			nw[nws].searchchar1(i, charar, w,w[0].find(i, ws, w) );
			nws++;
		}
	}
	int h;
	for(i=0;i<nws;i++)
	{
		//nw[i].mark(charar);
	}
	
	newchar[] nc=new newchar[ws];
	int ncs=ws;
	
	for(i=0;i<ws;i++)
	{    
		nc[i]=new newchar();
	}
	
	for(i=0;i<nws;i++)
	{    
		h=nw[i].find(nw[i].a[0], ws, w);
		nc[h].addcharacter(i);
	//	System.out.println("h is"+h+" i is "+i);
	}
	
	for(i=0;i<ncs;i++)
	{
		nc[i].sort(nw, charar);
	}
	for(i=0;i<ncs;i++)
	{
		nc[i].fill(nw,charar);
	}
	
	int[] sorted=new int[ncs];

     boolean[] f=new boolean[ncs];
     for(i=0;i<ncs;i++)
     {
    	 f[i]=false;
     }
     int min=50000,pos=0;
     
    
     
     for(i=0;i<ncs;i++)
     {min=50000;
    	 for(j=0;j<ncs;j++)
    	 {
    		if(f[j]==false)
    		{
    			if(min>nc[j].xmin[0])
    			{
    				min=nc[j].xmin[0];
    				pos=j;
    			}
    		}
    	 }
    	 f[pos]=true;
    	 sorted[i]=pos;
    	// System.out.println(pos+" abcd");
     }
	
   
     
   line[] sentence=new line [2000];
   for(i=0;i<2000;i++)
   {
	   sentence[i]=new line();
   }
     for(i=0;i<ncs;i++)
     {
    	 f[i]=false;
     }
     
    
     
     int sentsize=0;
     int a=0,b=0;
     for(;b<ncs;sentsize++)
     {
    	 for(i=0;i<ncs;i++)
    	 {
    		 if(f[i]==false)
    		 {
    			 a=i;
    			 break;
    		 }
    	 }
    	 System.out.println(a+" problem  "+sentsize+"  "+b+"  "+f[a]+"  "+ncs);
    	 sentence[sentsize].addcharacter(a);
    	 f[a]=true;
    	 for(i=0;i<ncs;i++)
    	 {
    		 if(f[i]==true)
    		 {
    			 continue;
    		 }
    		 
    		 if(nc[sorted[a]].compare(sorted[i],sorted[a],nc)==1)
    		 {
    			 sentence[sentsize].addcharacter(i);
                   f[i]=true;
                   a=i;
    		 }
    	 }
    	 b=0;
    	 for(i=0;i<ncs;i++)
    	 {if(f[i]==true){
    		b++;}
    	 }
     }
     
   
     
     for(i=0;i<sentsize;i++)
     {
    sentence[i].sety(nc[sorted[sentence[i].a[0]]].gymin());
     }
     
     int[] sortedsentence=new int[sentsize];
     
     for(i=0;i<sentsize;i++)
     {
    	 f[i]=false;
     }
     
     min=50000;
     for(i=0;i<sentsize;i++)
     {min=50000;
    	 for(j=0;j<sentsize;j++)
    	 {
    		 if(f[j]==false)
    		 {
    			 if(min>sentence[j].ypos)
    			 {
    				 pos=j;
    				 min=sentence[j].ypos;
    			 }
    		 }
    	 }
    	 f[pos]=true;
    	 sortedsentence[i]=pos;
     }
     
   int  t=sortedsentence[8];int u;
   /*for(i=0;i<sentence[t].size;i++)
   {      u=sorted[sentence[t].a[i]];
	   for(j=0;j<nc[sentence[t].a[i]].size;j++)
	   {
		   //nw[nc[u].a[j]].mark(charar);
	   }
   }
   
  */
   
   int x1,x2,y1,y2;
   u=sentence[sortedsentence[0]].a[0];
   // qwerty
   //fill u,t values . u corresponds to word, t corresponds to 't' th letter of word
   //  eg u=65,t=2  means 3rd letter of 65 th word. check word and letter by below method
   //getchar crops the corresponding letter.
   u=12;t=0;
   x1=nc[sorted[u]].xmin[t];
   x2=nc[sorted[u]].xmax[t];
   y1=nc[sorted[u]].ymin[t];
   y2=nc[sorted[u]].ymax[t];
     
     image.getchar(x1, x2, y1, y2);
     image.horizontal(30);
     image.vertical(30);
     
   //qwerty
     // alpha is no. of word,beta is the no. of letter
     //eg, alpha=65,beta=2  means color 3rd letter of 65h word.
     
   int alpha,beta;
   alpha=87;
   beta=2;
     nw[nc[alpha].a[beta]].mark(charar);
	   try {
		    File outputfile = new File("saved.jpg");
		    ImageIO.write(image.getimg(), "jpg", outputfile);
		} catch (IOException e) {
	      
		}
	   
	   
	  
	//zxcvbnm 
	 
	   try {
		    File outputfile = new File("noise free binary.jpg");
		    ImageIO.write(newimg, "jpg", outputfile);
		} catch (IOException e) {
	      
		}

	   System.out.println("ok");
	   
	}

}
