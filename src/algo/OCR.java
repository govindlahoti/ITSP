package algo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OCR {
	public static void main(String[] args) throws IOException {

		Letter stdLetters[] = new Letter[26];
		for(int i=0; i<stdLetters.length; i++){
			stdLetters[i] = new Letter(ImageIO.read(new File("pic/"+(char)('A'+i)+".png")));
			stdLetters[i].setLetter((char)('A'+i));
		}
		
		BufferedImage image = ImageIO.read(new File("pic/test.jpg"));
		Letter test = new Letter(image);
		//test.enhance();
		test.evaluateAgainst(stdLetters);
		System.out.println("ANSWER " +test.getLetter());
	}

}

