package ocr;

public class word {
	
	int[] a=new int[50000];
    static boolean[] flag=new boolean[50000];
	int size;
	static int n;
	word(int noofcharac)
	{  int i;
		for(i=0;i<noofcharac;i++)
		{
			flag[i]=false;
		}
		size=0;
		n=173;
		return ;
	}
	
	word()
	{
		size=0;
		return ;
	}
	
	public void addcharacter(int index)
	{ 
		a[size]=index;
		size++;
		return ;
	}
	
	public int abs(int x)
	{
		if(x<0){return -x;}
		else{return x;}
	}
	
	public void secsearch(int index,Char[] charar,section[][] secarray,int secx,int secy)
	{   int x,y;
	     x=charar[index].x+(charar[index].width/2);
	     y=charar[index].y+(charar[index].height/2);
	     
	    int xcor,ycor,ind,val;
	    int i;
	    for(i=0;i<secarray[secx][secy].size;i++)
	    {   
	    	ind=secarray[secx][secy].a[i];
	    	 
	    	xcor=charar[ind].x+(charar[ind].width/2);
		     ycor=charar[ind].y+(charar[ind].height/2);
	    	if(flag[ind]==true){
	    		continue;
	    	}
	    	else
	    	{
	    		
	    		if(abs(y-ycor)<60){
	    			val=abs(abs(x-xcor)-((charar[index].width+charar[ind].width)/2));
	    		}
	    		else{
	    			val=10000;
	    		}
	    	
	    			    		if(val<14){
	    			//System.out.println(" found  "+ind);
	    			addcharacter(ind);
	    			
	    			flag[ind]=true;
	    		}
	    	}
	    }
	    return ;
	}
	
	public int find(int i,int ws,word[] wordarray)
	{   int p,q;
		int pos;
		for(p=0;p<ws;p++)
		{
			for(q=0;q<wordarray[p].size;q++)
			{
				if(i==wordarray[p].a[q]){
					return p;
				}
			}
		}
		
		return -1;
	}
	
	public void search(int index,Char[] charar,section[][] secarray)
	{   int secx,secy,bsize;
	int i,j;
	//System.out.println(index+" is index"+flag[index]);
	
	flag[index]=true;
	  	addcharacter(index);
	  	secx=charar[index].x+(charar[index].width/2);
	  	secy=charar[index].y+(charar[index].height/2);
	  	bsize=secarray[0][0].blocksize;
	  	secx=secx/bsize;
	  	secy=secy/bsize;
	  	// x=charar[ind].x+(charar[ind].width/2);
	  	 //y=charar[ind].y+(charar[ind].height/2);
	  	for(i=secx-1;i<secx+2;i++)
	  	{
	  		for(j=secy-1;j<secy+2;j++)
	  		{
	  			if((i>=0)&&(i<secarray[0][0].g)&&(j>=0)&&(j<secarray[0][0].h))
	  		  	{   // secarray[i][j].print(charar);
	  		  		secsearch(index,charar,secarray,i,j);
	  		  	}
	  		}
	  	}
	  	
	  	return ;
	}
	
	public void searchword(int index,Char[] charar,section[][] secarray)
	{
		int[] b=new int[2000];
		int ind,val,in,i,j,p,bsize;
		int s=1;
		int check=0;
		int x,y,c;
		int xcor,ycor;
		int secx,secy;
		b[0]=index;
		flag[index]=true;
		addcharacter(index);
		for(;s!=check;check++)
		{
			ind=b[check];
			secx=charar[ind].x+(charar[ind].width/2);
		  	secy=charar[ind].y+(charar[ind].height/2);
		  	bsize=secarray[0][0].blocksize;
		  	secx=secx/bsize;
		  	secy=secy/bsize;
		  	 x=charar[ind].x+(charar[ind].width/2);
		  	 y=charar[ind].y+(charar[ind].height/2);
		  	for(i=secx-1;i<secx+2;i++)
		  	{
		  		for(j=secy-1;j<secy+2;j++)
		  		{p=0;System.out.println("s is"+s+"check is"+check+"i is"+i+"j is"+j+"p is"+p);
		  			if((i>=0)&&(i<secarray[0][0].g)&&(j>=0)&&(j<secarray[0][0].h))
		  			{for(p=0;p<secarray[i][j].size;p++)
		  			{  //  System.out.println("s is"+s+"check is"+check+"i is"+i+"j is"+j+"p is"+p);
		  				in=secarray[i][j].a[p];
		  				 xcor=charar[in].x+(charar[in].width/2);
		  			  	 ycor=charar[in].y+(charar[in].height/2);
		  			  	 if(flag[in]==true)
		  			  	 {
		  			  		 continue;
		  			  	 }
		  			  	 else
		  			  	 {
		  			  		if(abs(y-ycor)<50){
		  		    			val=abs(x-xcor)-((charar[ind].width+charar[in].width)/2);
		  		    		}
		  		    		else{
		  		    			val=10000;
		  		    		}
		  		    	
		  		    			    		if((val<16)){
		  		    			//System.out.println(" found  "+ind);
		  		    			addcharacter(in);
		  		    			b[s]=in;
		  		    			s++;
		  		    			flag[in]=true;
		  		    		}
		  			  	 }
		  			}}
		  		}
		   }
		}
	}
	
	public void searchchar(int index,Char[] charar,word[] w,int pos)
	{
		int[] b=new int[100];
		int ind,in,i;
		int s=1;
		int check=0;
		int x,y,c,d;
		int xcor,ycor;
		b[0]=index;
		flag[index]=true;
		addcharacter(index);
		for(;s!=check;check++)
		{  System.out.println(s+" is s and check is"+check);
		     ind=b[check];
		     if((charar[ind].height*charar[ind].width)<=300)
		     {
		    	 c=0;System.out.println("turned out to be small");
		     }
		     else{
		    	 c=1;System.out.println("turned out to be big");
		     }
		     
		     x=charar[ind].x+(charar[ind].width/2);
		     for(i=0;i<w[pos].size;i++)
		     {
		    	 in=w[pos].a[i];
		    	 xcor=charar[in].x+(charar[in].width/2);
		    	 if((charar[in].height*charar[in].width)<=300)
			     {
			    	 d=0;System.out.println("d turned out to be small");
			     }
			     else{
			    	 d=1;System.out.println("d turned out to be big");
			     }
	             if(flag[in]==true){
	            	 System.out.println(" flag true");
	            	 continue;
	             }
	             else{
	            	 if(c==1){
	            		 System.out.println(abs(charar[ind].x-charar[in].x)+" amusing");
	            		 if(abs(charar[ind].x-charar[in].x)<11)
	            		 {
	            			 addcharacter(in);
	            			 b[s]=in;
	            			 s++;
	            			 flag[in]=true;
	            			 continue;
	            		 }
	            		 if(d==0)
	            		 {
	            			 if((abs(x-xcor)-5)<((charar[ind].width+charar[in].width)/2))
	            			 { System.out.println("sundar lal");
	            				 addcharacter(in);
	            				 b[s]=in;
	            				 s++;
	            				 flag[in]=true;
	            				 continue;
	            			 } 
	            		 }
	            		 
	            	 }
	            	 else{
	            		 System.out.println(abs(charar[ind].x-charar[in].x)+" amusing");
	            		 if(abs(charar[ind].x-charar[in].x)<5)
	            		 {
	            			 addcharacter(in);
	            			 b[s]=in;
	            			 s++;
	            			 flag[in]=true;
	            		 }
	            		 else{ 
	            			 if(abs(x-xcor)<((charar[ind].width+charar[in].width)/2))
	            			 { System.out.println("sundar");
	            				 addcharacter(in);
	            				 b[s]=in;
	            				 s++;
	            				 flag[in]=true;
	            			 }
	            		 }
	            	 }
	             }
		     }
		}
		return ;
	}
	
	public void searchchar1(int index,Char[] charar,word[] w,int pos)
	{
		int[] b=new int[100];
		int ind,in,i;
		int s=1;
		int check=0;
		int x,y,c,d;
		int xcor,ycor;
		b[0]=index;
		flag[index]=true;
		addcharacter(index);
		for(;s!=check;check++)
		{  System.out.println(s+" is s and check is"+check);
		     ind=b[check];
		    
		     
		     x=charar[ind].x+(charar[ind].width/2);
		     for(i=0;i<w[pos].size;i++)
		     {
		    	 in=w[pos].a[i];
		    	 xcor=charar[in].x+(charar[in].width/2);
		    	
	             if(flag[in]==true){
	            	 System.out.println(" flag true");
	            	 continue;
	             }
	             else{
	            	
	            	 if(abs(x-xcor)<((charar[ind].width+charar[in].width)/2)-4)
        			 { System.out.println("sundar");
        				 addcharacter(in);
        				 b[s]=in;
        				 s++;
        				 flag[in]=true;
        				 continue;
        			 }
	            	 if(abs(charar[ind].x-charar[in].x)<5)
            		 {
            			 addcharacter(in);
            			 b[s]=in;
            			 s++;
            			 flag[in]=true;
            			 continue;
            		 }
	             }
		     }
		}
		return ;
	}
	
	public void mark(Char[] charar){
		
        n*=113;
        
		for(int i=0;i<size;i++){
			(charar[a[i]]).mark7(n);
		}
		n=n%283;
		System.out.println("wordsize"+ a[0]);
	}
	public int gxmin(Char[] charar)
	{
		int i,j=charar[a[0]].x;
		for(i=0;i<size;i++)
		{
			if(j>charar[a[i]].x)
			{
				j=charar[a[i]].x;
			}
		}
		return j;
	}
	
	public int gymin(Char[] charar)
	{
		int i,j=charar[a[0]].y;
		for(i=0;i<size;i++)
		{
			if(j>charar[a[i]].y)
			{
				j=charar[a[i]].y;
			}
		}
		return j;
	}
	
	public int gxmax(Char[] charar)
	{
		int i,j,k;
		j=charar[a[0]].x+charar[a[0]].width;
		for(i=0;i<size;i++)
		{
			k=charar[a[i]].x+charar[a[i]].width;
			if(j<k)
			{
				j=k;
			}
		}
		return j;
	}
	
	
	
	public int gymax(Char[] charar)
	{
		int i,j,k;
		j=charar[a[0]].y+charar[a[0]].height;
		for(i=0;i<size;i++)
		{
			k=charar[a[i]].y+charar[a[i]].height;
			if(j<k)
			{
				j=k;
			}
		}
		return j;
	}
	
	public int meanh(Char[] charar)
	{
		int i,c=0;
		for(i=0;i<size;i++)
		{
			c=c+charar[a[i]].height;
		}
		c=c/size;
		return c;
	}
	
	public int meanw(Char[] charar)
	{
		int i,c=0;
		for(i=0;i<size;i++)
		{
			c=c+charar[a[i]].width;
		}
		c=c/size;
		return c;
	}
	
    public int medianh(Char[] charar )
    {
    int k,i,j;
    k=size/2;
 int c;   
    
    for(i=0;i<size;i++)
    {   c=0;
    	for(j=0;j<size;j++)
    	{
    		if(charar[a[j]].height<charar[a[i]].height)
			{
				c++;
			}
		if((charar[a[j]].height==charar[a[i]].height)&&(j<i))
		{
			c++;
		}
    	}
    	
    	if(c==k){
    		return charar[a[i]].height;
    	}
    }
    return -1;
    }
    
    
    
    public int medianw(Char[] charar )
    {
    int k,i,j;
    k=size/2;
 int c;   
    
    
    for(i=0;i<size;i++)
    {   c=0;
    	for(j=0;j<size;j++)
    	{
    		
    			
    		
    		
    			if(charar[a[j]].width<charar[a[i]].width)
    			{
    				c++;
    			}
    		if((charar[a[j]].width==charar[a[i]].width)&&(j<i))
    		{
    			c++;
    		}
    		
    	}
    	//System.out.println(i+" lolwa  "+c+" adsf "+k);
    	if(c==k){
    		return charar[a[i]].width;
    	}
    }
    return -1;
    }
    
    
    
}
