import java.io.*;
import java.util.Scanner;

//This class represents an ASCII image, which is just a rectangular array of ASCII characters, each representing a different pixel intensity.
class AsciiImage {
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

	}
	
	//constructor creates an AsciiImage object based on the specified Image. This constructor operates just like the one above except that resizing the image is unnecessary.
	public AsciiImage(Image image) {

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
	
	//recreates an Image object based on the ASCII image. Note that this Image object will be lossy because of the very few pixel values available to an ASCII image.
	public Image getImage() {
	
	}
	
	/*method that returns the ASCII image as a string, complete with new-line characters at the end of each line. If this string were printed to an output file, 
	the resulting text would be the ASCII image. The StringBuilder class should be used here, rather than doing successive concatenations, for efficiency and speed.*/
	public String toString() {
		StringBuilder strB = new StringBuilder(this.width * this.height + this.height);
		for (int i = 0; i < this.height; i++) {
			strB = strB.append(this.pixel[i]).append("\n");
		}
		return strB.toString();
	}
}