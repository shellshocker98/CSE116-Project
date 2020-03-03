package code;

/**
 * This class creates a pixel object that is placed
 * in a spot in the 2d array. This also contains all
 * of the unique algorithms for each fractal to 
 * calculate each pixel's escape time.
 * @author Adam Wood
 * @author Evan Brown
 */

public class Pixel {

	//Defines the variables needed for the pixel object
	private double xCalc, yCalc, xCoord, yCoord;
	private int passes;
	private double dist;
	private int pos;
	private double xFinal, yFinal;

	/** Creates the Pixel object, passing in the x and y coordinate values 
	 * based on its position in the 2D array.
	 * Sets instance variables equal to parameters (Association Relationship)
	 * Calculates distance and later will update the x and y coordinates if
	 * needed.
	 * Also passes the position in the ArrayList list, which determines
	 * which fractal is being used.
	 */
	public int getPixel(double first, double second, int position, int escDis, int maxEscTime){
		pos = position;
		xCoord = first;
		yCoord = second;
		xCalc = first;
		yCalc = second;
		dist = Math.sqrt(((xCalc)*(xCalc)) + ((yCalc)*(yCalc)));
		passes = 0;

		while(dist<=escDis && passes<maxEscTime){
			sendToSet(pos);
			passes++;
			dist = Math.sqrt(((xFinal*xFinal) + (yFinal*yFinal)));
			xCalc = xFinal;
			yCalc = yFinal;
		}
		return passes;
	}

	/** This method is so that we can pass in an integer number (0-3)
	 * and based on it, we will then calculate and update xCalc and
	 * yCalc based on its value which will then determine the equation
	 * we use for updating and calculating
	 * Also assigns the instance variables to the new updated value
	 * 
	 */
	public void sendToSet(int setDirector){
		if(setDirector==0){
			mandelbrotSet();
		}
		else if(setDirector==1){
			juliaSet();
		}
		else if(setDirector==2){
			burningShipSet();
		}
		else{
			multibrotSet();
		}
	}

	/**
	 * Algorithm for the Mandelbrot Set
	 */
	public void mandelbrotSet(){
		xFinal = (xCalc*xCalc) - (yCalc*yCalc) + xCoord;
		yFinal = 2 * xCalc * yCalc + yCoord;
	}
	/**
	 * Algorithm for the Julia Set
	 */
	public void juliaSet(){
		xFinal = (xCalc*xCalc) - (yCalc*yCalc) - 0.72689;
		yFinal = 2 * xCalc * yCalc + 0.188887;
	}
	/**
	 * Algorithm for the Burning Ship Set
	 */
	public void burningShipSet(){
		xFinal = (xCalc*xCalc) - (yCalc*yCalc) + xCoord;
		yFinal = Math.abs(2 * xCalc * yCalc) + yCoord;
	}
	/**
	 * Algorithm for the Multibrot Set
	 */
	public void multibrotSet(){
		xFinal = (xCalc*xCalc*xCalc) - (3 * xCalc * (yCalc*yCalc)) + xCoord;
		yFinal = (3 * xCalc * xCalc * yCalc) - (yCalc*yCalc*yCalc) + yCoord;
	}
	
	/**
	 * Accessor 
	 * @return x coordinate 
	 */
	public double getxCoord(){
		return xCoord;
	}
	/**
	 * Accessor 
	 * @return y coordinate
	 */
	public double getyCoord(){
		return yCoord;
	}
}



