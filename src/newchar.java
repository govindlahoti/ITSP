package ocr;

public class newchar {
    
         int[] a=new int[2000];
         char[] ch=new char[2000];
         int size;
         int[] xmin=new int[2000];
         int[] ymin=new int[2000];
         int[] xmax=new int[2000];
         int[] ymax=new int[2000];
	   newchar()
	   {
		   size=0;
	   }
	    
	   public void swap(int i,int j)
	   {
		   int temp;
		   temp=a[i];
		   a[i]=a[j];
		   a[j]=temp;
		   return ;
	   }
	   public void sort(word[] nw,Char[] charar)
	   {
		   int i,j;
		  int  min,pos;
		   for(i=0;i<size;i++)
		   {     pos=i;
		         min=nw[a[i]].gxmin(charar);
			   for(j=i;j<size;j++)
			   {
				   if(min>nw[a[j]].gxmin(charar))
				   {
					   pos=j;
				   }
			   }
			   swap(i,pos);
		   }
		   return ;
	   }
	   
	   public void fill(word[] nw,Char[] charar)
	   {
		   int i;
		   for(i=0;i<size;i++)
		   {
			   xmin[i]=nw[a[i]].gxmin(charar);
			   ymin[i]=nw[a[i]].gymin(charar);
			   xmax[i]=nw[a[i]].gxmax(charar);
			   ymax[i]=nw[a[i]].gymax(charar);
		   }
		   
		   return ;
		   
	   }
	   
	   public void addcharacter(int index)
	   {
		   a[size]=index;
		   size++;
		   return ;
	   }
	   public int gxmin()
	   {
		   int i;
		   int m=xmin[0];
		   for(i=0;i<size;i++)
		   {
			if(m>xmin[i])
			{
				m=xmin[i];
			}
		   }
		  return m; 
	   }
	   
	   public int gymin()
	   {
		   int i;
		   int m=ymin[0];
		   for(i=0;i<size;i++)
		   {
			if(m>ymin[i])
			{
				m=ymin[i];
			}
		   }
		  return m; 
	   }
	   
	   public int gxmax()
	   {
		   int i;
		   int m=xmax[0];
		   for(i=0;i<size;i++)
		   {
			if(m<xmax[i])
			{
				m=xmax[i];
			}
		   }
		  return m; 
	   }
	   
	   public int abs(int i)
	   {
		   if(i<0){return -i;}
		   else{
			   return i;
		   }
	   }
	   
	   public int gymax()
	   {
		   int i;
		   int m=ymax[0];
		   for(i=0;i<size;i++)
		   {
			if(m<ymax[i])
			{
				m=ymax[i];
			}
		   }
		  return m; 
	   }
	   
	   public int gw()
	   {
		   return abs(gxmax()-gxmin());
	   }
	   
	   public int gh()
	   {
		   return  abs(gymax()-gymin());
	   }
	   
	   public int gxcent()
	   {
		   return (gxmin()+gxmax())/2;
	   }
	   public int gycent()
	   {
		   return (gymin()+gymax())/2;
	   }
	   
	   public int compare(int i,int a,newchar[] nc)
	   {
		   if(abs(nc[a].gxcent()-nc[i].gxcent())>(abs(nc[a].gw()+nc[i].gw())/2))
		   {
			   if(abs(nc[a].gycent()-nc[i].gycent())<(abs(nc[a].gh()+nc[i].gh())/2))
			   {
				   return 1;
			   }
		   }
		   return 0;
	   }
	  
    }
