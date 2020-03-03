package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Main;
import code.Pixel;
import gui.UserInterface;

public class MultibrotTest {

	Main m = new Main();
	UserInterface _ui = new UserInterface();

	@Test
	public void xCoordTest(){
		assertEquals(-.9412915851,m.createFractal(_ui.getList(3),2,255)[15][511].getxCoord(),.00000001);
	}
	
	@Test
	public void yCoordTest(){
		assertEquals(1.3,m.createFractal(_ui.getList(3),2,255)[15][511].getyCoord(),.00000001);
	}
	
	@Test
	public void NeverEsceedsDistanceTest(){
		Pixel pix = new Pixel(0.5859375, 0.24375000000000108, 3, 2, 255);
		assertEquals(255, pix.getEscapeTime());
	}
	
	@Test
	public void OneLoopTest(){
		Pixel pix = new Pixel(0.9921875, 1.05625, 0, 2, 255);
		assertEquals(1, pix.getEscapeTime());
	}
	
	@Test 
	public void returnArrayTest(){
		m.createFractal(_ui.getList(3),2,255);
		assertEquals(512 , m.getFractalRowSize());
		assertEquals(512, m.getFractalColSize());
	}
	
	@Test
	public void EscapeDistanceTest(){
		Pixel pix = new Pixel(0.7025440313111545, -0.5520547945205528, 3, 3, 255);
		assertEquals(10,pix.getEscapeTime());
	}
	
	@Test
	public void EscapeTimeTest(){
		Pixel pix = new Pixel(0.5859375, 0.24375000000000108, 3, 2, 135);
		assertEquals(135, pix.getEscapeTime());
	}
}


