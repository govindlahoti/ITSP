package algo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Letter{
	BufferedImage image;
	int value[][], distValue[][];
	char letter;
	int score;
	
	// Constructor
	Letter(BufferedImage img){
		image = img;
		letter = 'A';
		score = -999999;
		blackNWhite();
		obtainBinary();
		obtainDistValue();
		//enhance();
	
	}
	
	// Converts the coloured image to black and white
	private void blackNWhite(){
		for(int i=0; i<image.getWidth(); i++){
			for(int j=0; j<image.getHeight(); j++){		
				Color clr = new Color(image.getRGB(i, j));
				int avg = (clr.getRed()+clr.getBlue()+clr.getGreen())/3;
				image.setRGB(i, j, new Color(avg, avg, avg).getRGB());
				if(avg < 100)
					image.setRGB(i, j, new Color(0, 0, 0).getRGB());
				else
					image.setRGB(i, j, new Color(255, 255, 255).getRGB());
			}
		}
	}
	

	// enhance the image in order to identify it
	public void enhance(){
		int temp[][] = new int[image.getWidth()][image.getHeight()];
		for(int i=1; i<image.getWidth()-1; i++){
			for(int j=1; j<image.getHeight()-1; j++){
				if(value[i][j]!=0) { temp[i][j]=1; continue;}
				int score = value[i-1][j-1]+value[i-1][j]+value[i-1][j+1]+value[i][j-1]+value[i][j+1]+value[i+1][j-1]+value[i+1][j]+value[i+1][j+1];
				temp[i][j] = (score>1)?1:0;
			}
		}
		value = temp.clone();
	}
	
	// converts the image to 0-1
	private void obtainBinary(){
		value = new int[image.getWidth()][image.getHeight()];
		
		for(int i=0; i<image.getWidth(); i++){
			for(int j=0; j<image.getHeight(); j++){
				Color clr = new Color(image.getRGB(i, j));
				int avg = (clr.getRed()+clr.getBlue()+clr.getGreen())/3;
				
				if(avg > 120)
					value[i][j] = 0; //white
				else
					value[i][j] = 1; //black
			}
		}
		
	}
	
	// returns the score of match
	private int match(int stdValue[][]){
		int scr = 0;
		for(int i=0; i<image.getWidth(); i++){
			for(int j=0; j<image.getHeight(); j++){
				//scr += Math.pow((4 - Math.abs(value[i][j]-stdValue[i][j])), 2);
				scr += 1-Math.abs(value[i][j]-stdValue[i][j]);
			}
		}
		return scr;
	}
	
	private int match2(int stdDistValue[][]){
		int scr = 0;
		for(int i=0; i<image.getWidth(); i++){
			for(int j=0; j<image.getHeight(); j++){
				if(value[i][j]==1){
					scr += stdDistValue[i][j];
				}
			}
		}
		return scr;
	}
	// prints the array value[][]
	private void printValues(){
		for(int j=0; j<image.getHeight(); j++){
			for(int i=0; i<image.getWidth(); i++){
				System.out.print(value[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// prints the array distValue[][]
	private void printDistValues(){
		for(int j=0; j<image.getHeight(); j++){
			for(int i=0; i<image.getWidth(); i++){
				System.out.print(distValue[i][j] + " ");
			}
			System.out.println();
		}
	}
		
	public void setLetter(char c){
		letter = c;
	}
	
	public void evaluateAgainst(Letter stdLetters[]){
		for(int i=0; i<stdLetters.length; i++){
			int scr = match(stdLetters[i].getValue());
			int scr2 = match2(stdLetters[i].getDistValue());
			System.out.println(stdLetters[i].getLetter()+ " " + scr + " " + scr2); ///////both the scores are being printed here
			int c1= 1; 
			int c2= -2;
			scr = c1*scr + c2*scr2;/////////////////////////////////////////////MOHIT CHANGE C1 and C2 ACCORDINGLY
			if(scr > score){
				score = scr;
				letter = stdLetters[i].getLetter();
			}
		}
	}
	
	public char getLetter(){
		return letter;
	}
	
	private int[][] getValue(){
		return value;
	}
	
	private int[][] getDistValue(){
		return distValue;
	}
	
	
	private class Pos{
		private int x, y, val;
	};
	
	public Pos Pos(int q, int p, int r){ Pos t = new Pos(); t.x=q; t.y=p; t.val=r; return t;}
	private void obtainDistValue(){
		distValue = new int[image.getWidth()][image.getHeight()];
		LinkedList<Pos> queue = new LinkedList<Pos>();
		for(int i=0; i<image.getWidth(); i++) for(int j=0; j<image.getHeight(); j++) distValue[i][j]=200000;
		for(int i=0; i<image.getWidth(); i++){
			for(int j=0; j<image.getHeight(); j++){
				if(value[i][j]==1){queue.add(Pos(i, j, 0)); distValue[i][j]=0;}
			}
			while(!queue.isEmpty()){
				Pos t = queue.removeFirst();
				if(t.x!=image.getWidth()-1){ if(distValue[t.x+1][t.y]>t.val+1){distValue[t.x+1][t.y] = t.val+1; queue.add(Pos(t.x+1, t.y, t.val+1)); }}
				if(t.x!=0){ if(distValue[t.x-1][t.y]>t.val+1){distValue[t.x+-1][t.y] = t.val+1; queue.add(Pos(t.x-1, t.y, t.val+1)); }}
				if(t.y!=image.getHeight()-1){ if(distValue[t.x][t.y+1]>t.val+1){distValue[t.x][t.y+1] = t.val+1; queue.add(Pos(t.x, t.y+1, t.val+1)); }}
				if(t.y!=0){ if(distValue[t.x][t.y-1]>t.val+1){distValue[t.x][t.y-1] = t.val+1; queue.add(Pos(t.x, t.y-1, t.val+1)); }}
			}
		}
	}
	
	int min(int a, int b){ return (a<b)?a:b; }
}
