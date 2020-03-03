package fractal;

import java.awt.Color;
import java.awt.image.IndexColorModel;

/**
 * This class contains the code necessary to build the color models for three
 * different sets of fractal coloring. Each factory method takes in the number
 * of color required and build the appropriate IndexColorModel object.
 * 
 * @author Matthew Hertz
 *
 */
public class ColorModelFactory {

  /**
   * Create a color model that contains the colors of the rainbow. This is the
   * model to which FractalPanel defaults and the one used to create the demo
   * images from phase #1. The number of colors included in the model is set by
   * the parameter. This should be 1 more than the maximum number of steps for
   * the fractal.
   * 
   * @param numColors
   *          Number of different color shades to use.
   * @return Color model that shows a selection of colors chosen because Prof.
   *         Hertz finds the combination pretty.
   */
  public static IndexColorModel createRainbowColorModel(int numColors) {
    byte[] reds = new byte[numColors];
    byte[] greens = new byte[numColors];
    byte[] blues = new byte[numColors];
    for (int i = 0; i < reds.length - 1; i++) {
      int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
      reds[i] = (byte) ((rgb & 0xFF0000) >> 16);
      greens[i] = (byte) ((rgb & 0xFF00) >> 8);
      blues[i] = (byte) (rgb & 0xFF);
    }
    IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
    return retVal;
  }

  /**
   * Create a color model that contains different shades of blue. The number of
   * colors included in the model is set by the parameter. This should be 1 more
   * than the maximum number of steps for the fractal.
   * 
   * @param numColors
   *          Number of different color shades to use.
   * @return Color model that shows all of the different possible shades of
   *         blue.
   */
  public static IndexColorModel createBlueColorModel(int numColors) {
    byte[] reds = new byte[numColors];
    byte[] greens = new byte[numColors];
    byte[] blues = new byte[numColors];
    for (int i = 0; i < reds.length - 1; i++) {
      blues[i] = (byte) ((Math.log10(i) / Math.log10(blues.length)) * 256);
      greens[i] = 0;
      reds[i] = 0;
    }
    IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
    return retVal;
  }

  /**
   * Create a color model that contains different shades of gray. The number of
   * colors included in the model is set by the parameter. This should be 1 more
   * than the maximum number of steps for the fractal.
   * 
   * @param numColors
   *          Number of different color shades to use.
   * @return Color model that shows all of the different possible shades of gray
   *         from pure black to pure white.
   */
  public static IndexColorModel createGrayColorModel(int numColors) {
    byte[] reds = new byte[numColors];
    byte[] greens = new byte[numColors];
    byte[] blues = new byte[numColors];
    for (int i = 0; i < reds.length - 1; i++) {
      reds[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);
      greens[i] = reds[i];
      blues[i] = reds[i];
    }
    IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
    return retVal;
  }
  
  public static IndexColorModel createGreenColorModel(int numColors) {
	  byte[] reds = new byte[numColors];
	  byte[] greens = new byte[numColors];
	  byte[] blues = new byte[numColors];
	  for (int i = 0; i < reds.length-1; i++){
		  greens[i] = (byte) ((Math.log10(i) / Math.log10(greens.length)) * 256);
		  blues[i] = 0;
		  reds[i] = 0;
	  }
	  IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
	  return retVal;
  }
  
  public static IndexColorModel createCrazyColorModel(int numColors){
	  Color[] colors = {Color.red, Color.green, Color.blue,
			    Color.cyan, Color.magenta, Color.yellow,
			    Color.white, Color.black};
			    byte[] reds = new byte[8];
			    byte[] greens = new byte[8];
			    byte[] blues = new byte[8];
			    for (int i = 0; i < colors.length; i++) {
			      reds[i] = (byte) colors[i].getRed();
			      greens[i] = (byte) colors[i].getGreen();
			      blues[i] = (byte) colors[i].getBlue();
			    }
			    IndexColorModel cm = new IndexColorModel(3, reds.length, reds, greens, blues);
			    return cm;
  }
  
  /**
   * Method that takes two ints, one
   * for the numColors and one 
   * to decide which colorModel to 
   * generate. Used in UI for easier
   * colorModel generation.
   * @param i
   * @param numColors
   * @return
   */
  public static IndexColorModel setModel(int i, int numColors){
	  if (i==0){
		  return createRainbowColorModel(numColors);
	  }
	  else if (i==1){
		  return createBlueColorModel(numColors);
	  }
	  else if (i==2){
		  return createGrayColorModel(numColors);
	  }
	  else if (i==3){
		  return createGreenColorModel(numColors);
	  }
	  else if (i==4){
		  return createCrazyColorModel(numColors);
	  }
	  return null;
  }
}