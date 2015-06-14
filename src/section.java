package ocr;

public class section {

	int[] a=new int[30];
	static int blocksize;
	static int g,h;
	
	int size;
	section (int b,int gs,int hs)
	{    blocksize=b;
	g=gs;
	h=hs;
		size=0;
		return ;
	}
	
	public void print(Char[] charar)
	{int i;
		for(i=0;i<size;i++)
		{
			charar[a[i]].mark2(1,200);
		}return ;
	}
	
	}
