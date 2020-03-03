package code;

/**
 * This class creates a Fractal object that
 * holds the unique ranges of each fractal
 * and calculates the increment of the x and
 * y coordinates.
 * @author Evan Brown
 * 
 *
 */

public class Fractal {

	//The x and y ranges for the corresponding fractal.
	private double xrange1, xrange2, yrange1, yrange2;
	int pos;

	/**
	 * This Class is here to create a Fractal object that holds 
	 * its x and y ranges for easier access. It also calculates
	 * what increment will need to be used for the 2d array.
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public Fractal(double x1, double x2, double y1, double y2, int pos){
		xrange1=x1;
		xrange2=x2;
		yrange1=y1;
		yrange2=y2;
		this.pos=pos;
	}

	/**
	 * Accessor for first x range used
	 * @return x range lower end
	 */
	public double getx1(){
		return xrange1;
	}
	
	public double getx2(){
		return xrange2;
	}
	/**
	 * Accessor for first y range used
	 * @return y range lower end
	 */
	public double gety1(){
		return yrange1;
	}
	
	public double gety2(){
		return yrange2;
	}
	/**
	 * Incrementor used to assign coordinates in the array
	 * @return x increment amount
	 */
	public double xIncrement(){
		return (xrange2-xrange1)/511;
	}
	/**
	 * Incrementor used to assign coordinates in the array
	 * @return y increment amount
	 */
	public double yIncrement(){
		return (yrange2-yrange1)/511;
	}	
	
	public double getXRange(){
		return xrange2-xrange1;
	}
	
	public double getYRange(){
		return yrange2-yrange1;
	}
	
	public int getPos(){
		return pos;
	}
}



