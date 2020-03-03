package code;

/**
  This class holds the variables and methods necessary to
 * create a 2d array that holds a pixel in each space
 * with a set of coordinates unique to the fractal
 * being generated
 * @author Adam Wood
 * @author Evan Brown
 */

import java.util.ArrayList;

public class Main {

	/** Creates the fractals using its ranges
	 *  and creates the arraylist which will 
	 *  store them. Also defines the main
	 *  2d array and the x and y coordinates
	 */
	private double xCor, yCor;
	private int[][] escapeTimes;
	Pixel p1 = new Pixel();

	/**	Constructor which creates the array
	 * 	Iterates through 2D array of type Pixel, where each
	 *  element represents its own pixel with its own xCalc, yCalc, distance
	 *  Creates new pixel objects and adds them to the 2D array,
	 *  while passing arguments to the constructor of Pixel Class which will
	 *  assign the needed variables for the Escape Time Algorithms
	 */
	
	// new parameter send it to pixel change 255 
	public int[][] createEscapeTimes(Fractal fractal, int escDis, int maxEscTime, int threads, int pos){
		System.out.println(pos);
		int rows = 512/threads;
		if (pos==threads-1&&512%threads!=0){
			rows = 512-(rows*pos);
		}
		escapeTimes = new int[rows][512];
		double xincrement = fractal.xIncrement();	//The increments used for xCor and yCor when going through for-loop.	 
		double yincrement = fractal.yIncrement();
		xCor = fractal.getx1()+(fractal.getXRange()/threads)*pos;
		yCor = fractal.gety1();
		for(int c=0; c<escapeTimes[0].length; c++){
			for(int r=0; r<escapeTimes.length; r++){
				int e = p1.getPixel(xCor, yCor, fractal.getPos(), escDis, maxEscTime);
				escapeTimes[r][c] = e;
				xCor=xCor+xincrement;
			}
			yCor+=yincrement;
			xCor = fractal.getx1()+(fractal.getXRange()/threads)*pos;
		}
		return escapeTimes;
	}
}
