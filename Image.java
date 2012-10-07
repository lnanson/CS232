/*Image
This class represents a grayscale image. It is just a rectangular array of integers representing the pixel values. Three image manipulation methods should be provided�shrink, invert, and mirror.
Public Attributes
*/
import java.io.*;
import java.util.*;

public class Image {
	public enum Axis {HORIZONTAL, VERTICAL};
	// an enumeration representing the axis by which the image should be mirrored
	private int[][] pixel;
	private int width;
	private int height;
	// the width (number of columns) and height (number of rows) of the image

	public Image(int width, int height) { // constructor that creates a fully black (all 0s) image of the specified width and height
		this.width = width;
		this.height = height;
		pixel = new int[height][width];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				pixel[i][j] = 0;
			}
		}
	}
	
	public Image(Image image) { // constructor that creates a deep copy of the image passed to it
		
        width = image.getWidth();
        height = image.getHeight();
		pixel = new int[height][width];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				pixel[i][j] = image.getPixel(i,j);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPixel(int row, int col) {
		return pixel[row][col];
	}
	
	public void setPixel(int row, int col, int value) { 
		pixel[row][col] = value;
	}

	public void shrink() { 
		int x=0;
		int[][] smallImage = new int[height/2][width/2];
		for (int i=0; i<height/2; i=i+2) {
			for (int j=0; j<width/2; j=j+2) {
				x = (pixel[i*2][j*2]+pixel[i*2+1][j*2+1]+pixel[i*2][j*2+1]+pixel[i*2+1][j*2])/4;
				smallImage[i][j]= x;
			}
		}
		width = width/2;
		height = height/2;
		pixel = smallImage;
	}
	// halves the size of the image in both width and height by taking the 
	// average of the current pixel and its immediate east, southeast, and 
	// south neighbors and using that value as the pixel value for the smaller image
	public void invert() {
		for (int i=0; i<this.getHeight(); i++) {
			for (int j=0; j<this.getWidth(); j++) {
				pixel[i][j]=(255-pixel[i][j]);
			}
		}
	}
	// creates the ?negative? of the image by replacing each pixel value with its
	// inverted value. For example a pixel value of 0 would become 255, 
	// 100 would become 155, and 200 would become 55.
	public void mirror(Axis axis) {
		int y=this.getHeight();
		int x=this.getWidth();
		int[][] mirrored = new int[y][x];
		if (axis == Axis.VERTICAL) {
			for (int i=0; i<y; i++) {
				for (int j=0; j<x; j++) {
					mirrored[y-i-1][j] = pixel[i][j];
				}
			}
		}
		if (axis == Axis.HORIZONTAL) {
			for (int i=0; i<y; i++) {
				for (int j=0; j<x; j++) {
					mirrored[i][x-j-1] = pixel[i][j];
				}
			}
		}
		for (int i=0; i<y; i++) {
			for (int j=0; j<x; j++) {
				this.setPixel(i,j,mirrored[i][j]);
			}
		}
	}
	// ?flips? the image about the specified axis. For instance, if the axis were vertical,
	 // then the first column and last column would be swapped, the second and next-to-last 
	 // columns would be swapped, etc.
	 
	 public void darken() {
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				if (pixel[i][j] >=5)
					pixel[i][j]=pixel[i][j]-5;
				else
					pixel[i][j]=0;
			}
		}
	 }
	 
	 public void brighten() {
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				if (pixel[i][j] <=250)
					pixel[i][j]=pixel[i][j]+5;
				else
					pixel[i][j]=255;
			}
		}
	 }
	
	public void incContrast() {
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				if (pixel[i][j] <=85) {
					if (pixel[i][j] >= 5)
						pixel[i][j]=pixel[i][j]-5;
					else
						pixel[i][j] = 0;
				}
				if (pixel[i][j] >= 171) {
					if (pixel[i][j] <=250)
						pixel[i][j]=pixel[i][j]+5;
					else
						pixel[i][j] = 255;
				
				}
			}
		}
	}
	
	public void decContrast() {
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				if (pixel[i][j] <= 85) {
						pixel[i][j] = pixel[i][j]+5;
				}
				if (pixel[i][j] >= 171 {
						pixel[i][j] = pixel[i][j]-5;	
				}
			}
		}
	}
}
