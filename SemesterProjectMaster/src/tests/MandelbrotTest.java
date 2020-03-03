package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Main;
import code.Pixel;
import gui.UserInterface;

public class MandelbrotTest {
	
	Main m = new Main();
	UserInterface _ui = new UserInterface();
	
	@Test
	public void xCoordTest(){
		assertEquals(-1.880919765,m.createFractal(_ui.getList(0),2,255)[50][200].getxCoord(),.00000001);
	}
	
	@Test
	public void yCoordTest(){
		assertEquals(-.2823874755,m.createFractal(_ui.getList(0),2,255)[50][200].getyCoord(),.00000001);
	}
	
	@Test 
	public void NeverExceedDistanceTest(){
		Pixel pix = new Pixel(0.3207031250000001, -0.07109374999999386, 0, 2, 255); 
		assertEquals(255, pix.getEscapeTime());
	}
	
	@Test
	public void OneLoopTest(){
		Pixel pix = new Pixel(0.5946289062500001, 1.2949218750000122, 0, 2, 255);
		assertEquals(1, pix.getEscapeTime());
	}

	@Test 
	public void returnArrayTest(){
		m.createFractal(_ui.getList(0),2,255);
		assertEquals(512 , m.getFractalRowSize());
		assertEquals(512, m.getFractalColSize());
	}
	
	@Test 
	public void EscapeDistanceTest(){
		Pixel pix = new Pixel(0.46007827788650374, -0.3383561643835661, 0, 3, 255);
		assertEquals(10, pix.getEscapeTime());
	}
	
	@Test
	public void EscapeTimeTest(){
		Pixel pix = new Pixel(0.3207031250000001, -0.07109374999999386, 0, 2, 135);
		assertEquals(135, pix.getEscapeTime());
	}
}


