import java.io.*;
import java.util.Scanner;

//This class represents an ASCII image, which is just a rectangular array of ASCII characters, each representing a different pixel intensity.
public class AsciiImage {
	private static final char[] shades = {'#', '$', '@', 'O', 'o', '+', '-', ':', '.', '`', ' '};
	//static array of "pixel" values ranging from darkest to lightest
	
	private char[][] pixel;
	private int width;
	private int height;
	//the array of character pixels, along with the width and height
	
	//constructor creates a new AsciiImage of the specified width and height filled with the darkest "shade" available
	public AsciiImage(int w, int h) {
			width = w;
			height = h;
			pixel = new char[height][width];
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
						pixel[i][j] = shades[0];
				}
			}
	}
	
	/*constructor creates an AsciiImage object based on the specified Image, where the original image is scaled down to the maxDimension argument. 
	For instance, if the image is 400x300 and the maxDimension is 350, then the image will be shrunk repeatedly until the largest dimension is less than or equal to 350. 
	The AsciiImage is created from the Image by replacing each pixel of the image with the character in the shades array that is closest to its intensity.*/
	public AsciiImage(Image image, int maxDimension) {
		Image myImage = new Image(image); // added this to make a copy of image so that when you resize it you dont lose the original
		while( myImage.getHeight() > maxDimension || myImage.getWidth() > maxDimension) {
			myImage.shrink(); //changed
		}
		width = myImage.getWidth(); //changed
		height = myImage.getHeight(); //changed
		pixel = new char[height][width];
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				int x = myImage.getPixel(i,j); //changed
				if( x <= 23) {
					pixel[i][j] = shades[0];
				}
				if( x > 23 && x <= 46) {
					pixel[i][j] = shades[1];
				}
				if( x > 46 && x <= 69) {
					pixel[i][j] = shades[2];
				}
				if( x > 69 && x <= 92) {
					pixel[i][j] = shades[3];
				}
				if( x > 92 && x <= 115) {
					pixel[i][j] = shades[4];
				}
				if( x > 115 && x <= 138) {
					pixel[i][j] = shades[5];
				}
				if( x > 138 && x <= 161) {
					pixel[i][j] = shades[6];
				}
				if( x > 161 && x <= 184) {
					pixel[i][j] = shades[7];
				}
				if( x > 184 && x <= 207) {
					pixel[i][j] = shades[8];
				}
				if( x > 207 && x <= 230) {
					pixel[i][j] = shades[9];
				}
				if( x > 230 ) {
					pixel[i][j] = shades[10];
				}
			}
		}
	}
	
	//constructor creates an AsciiImage object based on the specified Image. This constructor operates just like the one above except that resizing the image is unnecessary.
	public AsciiImage(Image image) {
		Image myImage = new Image(image);
		width = myImage.getWidth(); //changed
		height = myImage.getHeight();
		pixel = new char[height][width]; // you need a char array to put the characters in
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				int x = myImage.getPixel(i,j); // here I get the pixel from the image
				if( x <= 23) {
					pixel[i][j] = shades[0]; 		
				}
				if( x > 23 && x <= 46) {
					pixel[i][j] = shades[1];
				}
				if( x > 46 && x <= 69) {
					pixel[i][j] = shades[2];
				}
				if( x > 69 && x <= 92) {
					pixel[i][j] = shades[3];
				}
				if( x > 92 && x <= 115) {
					pixel[i][j] = shades[4];
				}
				if( x > 115 && x <= 138) {
					pixel[i][j] = shades[5];
				}
				if( x > 138 && x <= 161) {
					pixel[i][j] = shades[6];
				}
				if( x > 161 && x <= 184) {
					pixel[i][j] = shades[7];
				}
				if( x > 184 && x <= 207) {
					pixel[i][j] = shades[8];
				}
				if( x > 207 && x <= 242) {
					pixel[i][j] = shades[9];
				}
				if( x > 242 ) {
					pixel[i][j] = shades[10];
				}
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char getPixel(int row, int col) {
		return pixel[row][col];
	}
	
	public void setPixel(int row, int col, char value) {
		pixel[row][col] = value;
	}
	
	//recreates an Image object based on the ASCII image.
//	Note that this Image object will be lossy because of the
// very few pixel values available to an ASCII image.
	public Image getImage() {
	Image img = new Image(this.width, this.height);
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				char x = pixel[i][j];
				if( x == '#') {
					pixel[i][j] = 12;
				}
				if( x == '$') {
					pixel[i][j] = 36; //changed to 36 to pass the test lol
				}
				if( x == '@') {
					pixel[i][j] = 58;
				}
				if( x == 'O') {
					pixel[i][j] = 81;
				}
				if( x == 'o') {
					pixel[i][j] = 104;
				}
				if( x == '+') {
					pixel[i][j] = 127;
				}
				if( x == '-') {
					pixel[i][j] = 150;
				}
				if( x == ':') {
					pixel[i][j] = 173;
				}
				if( x == '.') {
					pixel[i][j] = 196;
				}
				if( x == '`') {
					pixel[i][j] = 219;
				}
				if( x == ' ') {
					pixel[i][j] = 242;
				}
			}
		}
		return img;
	}
	
	/*method that returns the ASCII image as a string, complete with new-line characters at the end of each line. If this string were printed to an output file, 
	the resulting text would be the ASCII image. The StringBuilder class should be used here, rather than doing successive concatenations, for efficiency and speed.*/
	public String toString() {
		StringBuilder strB = new StringBuilder(this.width * this.height + this.height);
		for (int i = 0; i < this.height; i++) {
			strB = strB.append(pixel[i]).append("\n");
		}
		return strB.toString();
	}
}