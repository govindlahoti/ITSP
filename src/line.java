package ocr;

public class line {
int[] a=new int[2000];
int ypos;
int size;
line()
{
	size=0;
}

public void addcharacter(int index)
{
	a[size]=index;
	size++;
	return ;
}

public void sety(int ind)
{
	ypos=ind;
	return ;
}
}
