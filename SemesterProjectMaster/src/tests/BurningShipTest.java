package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import code.Fractal;
import code.Main;
import code.Pixel;
import fractal.ComputePool;
import fractal.ThreadWorker;
import gui.UserInterface;

public class BurningShipTest {

	ComputePool p = new ComputePool();
	private Fractal burningShip = new Fractal(-1.8, -1.7, -.08, .025, 2);

	@Test
	public void xCoordTest(){
		assertEquals(-1.776516634,m.createFractal(_ui.getList(2),2,255)[120][30].getxCoord(),.00000001);
	}
	
	@Test
	public void yCoordTest(){
		assertEquals(-.0738356164,m.createFractal(_ui.getList(2),2,255)[120][30].getyCoord(),.00000001);
	}
	
	@Test
	public void burningShipTest(){
		Pixel pix = new Pixel(-1.7443359374999874, -0.017451171875000338, 2, 2, 255);
		assertEquals(255, pix.getEscapeTime());
	}
	
	@Test
	public void burningShipEscapeTest(){
		Pixel[][] _ship = m.createFractal(_ui.getList(2),2, 255);
		for (int c=0; c<_ship.length; c++){
			for (int r=0; r<_ship[0].length; r++){
				assertFalse(_ship[r][c].getEscapeTime()==0||_ship[r][c].getEscapeTime()==1);
			}
		}
	}
	
	@Test 
	public void returnArrayTest(){
		ThreadWorker[] arr = new ThreadWorker[4];
		for (int i=0; i<4; i++){
			arr[i] = new ThreadWorker(burningShip, 2, 255, i, 4);
		}
		p.generateFractal(2048, arr);
		assertEquals(2048, p.getFractal().length);
		assertEquals(2048, p.getFractal()[0].length);
	}
	
	@Test
	public void EscapeDistanceTest(){
		Pixel pix = new Pixel(-1.6999999999999802, 0.0030136986301371603, 2, 3, 255);
		assertEquals(10, pix.getEscapeTime());
	}
	
	@Test
	public void EscapeTimeTest(){
		Pixel pix = new Pixel(-1.7443359374999874, -0.017451171875000338, 2, 2, 135);
		assertEquals(135, pix.getEscapeTime());
	}
	
	
}


