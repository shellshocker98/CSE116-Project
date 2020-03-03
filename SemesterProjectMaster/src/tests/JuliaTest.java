package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Main;
import code.Pixel;
import gui.UserInterface;

public class JuliaTest {

	Main m = new Main();
	UserInterface _ui = new UserInterface();
	
	@Test
	public void xCoordTest(){
		assertEquals(.2960861057,m.createFractal(_ui.getList(1),2,255)[300][300].getxCoord(),.00000001);
	}
	
	@Test
	public void yCoordTest(){
		assertEquals(.1741682975,m.createFractal(_ui.getList(1),2,255)[300][300].getyCoord(),.00000001);
	}
	
	@Test 
	public void NeverExceedDistanceTest(){
		Pixel pix = new Pixel(1.0492187499999897, -0.234375, 1, 2, 255);
		assertEquals(255, pix.getEscapeTime());
	}
	
	@Test
	public void OneLoopTest(){
		Pixel pix = new Pixel(1.6933593749999853, 0.9765625, 1, 2, 255);
		assertEquals(1, pix.getEscapeTime());
	}
	
	@Test 
	public void returnArrayTest(){
		m.createFractal(_ui.getList(1),2,255);
		assertEquals(512 , m.getFractalRowSize());
		assertEquals(512, m.getFractalColSize());
	}
	
	@Test
	public void EscapeDistanceTest(){
		Pixel pix = new Pixel(1.4538160469667272, -0.13502935420743645, 1, 3, 255);
		assertEquals(10,pix.getEscapeTime());
	}
	
	@Test
	public void EscapeTimeTest(){
		Pixel pix = new Pixel(1.0492187499999897, -0.234375, 1, 2, 135);
		assertEquals(135, pix.getEscapeTime());
	}
}


