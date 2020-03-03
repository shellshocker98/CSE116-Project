package gui;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import code.Fractal;
import code.Main;
import fractal.ColorModelFactory;
import fractal.ComputePool;
import fractal.FractalPanel;
import fractal.ThreadWorker;
import fractal.WorkerResult;

public class UserInterface {
	
	//Instance variables for the GUI 
	private static final Dimension DEFAULT_DIMENSION = new Dimension(512,512);
	private Fractal fractal; //Holds value for which fractal is being displayed
	private int escapeDistance, escapeTime, numColors, colorModel, threads; //Place holders and coordinates
	private Main m1; FractalPanel panel; ComputePool cp; //Imported Classes
	private JMenuBar bar; JMenu menu; JMenuItem item; JFrame frame; //J Classes
	private Fractal mandelBrot = new Fractal(-2.15, .6, -1.3, 1.3, 0); //Fractals and arrayList
	private Fractal julia = new Fractal(-1.7, 1.7, -1.0, 1.0, 1);
	private Fractal burningShip = new Fractal(-1.8, -1.7, -.08, .025, 2);
	private Fractal multiBrot = new Fractal(-1.0, 1.0, -1.3, 1.3, 3);
	private ArrayList<Fractal> list = new ArrayList<Fractal>();
	
	//Creates class 
	public static void main(String s[]){
		UserInterface _ui = new UserInterface();
		_ui.createGui();
	}
	
	//Creates GUI
	public void createGui(){
		list.add(mandelBrot); list.add(julia); list.add(burningShip); list.add(multiBrot);
		//Default Values
		escapeDistance=2;
		numColors = 256;
		colorModel = 0;
		frame = new JFrame("Fractal Panel");
		escapeTime = 255;
		threads = 4;
		fractal = mandelBrot;
		
		
		m1 = new Main();
		cp = new ComputePool();
		
		panel = new FractalPanel(DEFAULT_DIMENSION, ColorModelFactory.setModel(colorModel,numColors));
		panel.setLayout(new FlowLayout());
		
		menuBar();
		panel.add(bar);
		updateFractal();
		panel.addMouseListener(new ZoomListener());
		
		frame.setJMenuBar(bar);
		frame.add(panel);
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateFractal(){
		cp.clearPool();
		cp.changePanel(panel);
		ThreadWorker[] arr = new ThreadWorker[threads];
		for (int i=0; i<threads; i++){
			arr[i] = new ThreadWorker(fractal, escapeDistance, escapeTime, i, threads);
		}
		cp.generateFractal(512, arr);
	}
	
	public Fractal getList(int pos){
		return list.get(pos);
	}
	
	/**
	 * Creates the menu bar in the GUI which
	 * contains options for which fractal and
	 * Color Model to use. Also allows user to 
	 * either quit the program or choose a 
	 * different escape time and number of colors.
	 */
	public void menuBar(){
		
		bar = new JMenuBar();
		
		menu = new JMenu("File");
		item = new JMenuItem("Exit");
		item.addActionListener(new ExitAction());
		menu.add(item);
		bar.add(menu);
		
		menu = new JMenu("Fractal");
		item = new JMenuItem("MandelBrot");
		item.addActionListener(new ChangeFractalEvent(mandelBrot));
		menu.add(item);
		
		item = new JMenuItem("Julia");
		item.addActionListener(new ChangeFractalEvent(julia));
		menu.add(item);
		
		item = new JMenuItem("BurningShip");
		item.addActionListener(new ChangeFractalEvent(burningShip));
		menu.add(item);
		
		item = new JMenuItem("MultiBrot");
		item.addActionListener(new ChangeFractalEvent(multiBrot));
		menu.add(item);
		bar.add(menu);

		menu = new JMenu("Color");
		item = new JMenuItem("Rainbow");
		item.addActionListener(new ChangeColorModel(0));
		menu.add(item);
		item = new JMenuItem("Blue");
		item.addActionListener(new ChangeColorModel(1));
		menu.add(item);
		item = new JMenuItem("Gray");
		item.addActionListener(new ChangeColorModel(2));
		menu.add(item);
		item = new JMenuItem("Green");
		item.addActionListener(new ChangeColorModel(3));
		menu.add(item);
		item = new JMenuItem("Crazy");
		item.addActionListener(new ChangeColorModel(4));
		menu.add(item);
		bar.add(menu);
		
		menu = new JMenu("Constants");
		item = new JMenuItem("Choose new EscapeDistance");
		item.addActionListener(new DistanceChooser());
		menu.add(item);
		item = new JMenuItem("Choose new number of Colors");
		item.addActionListener(new ChangeNumShades());
		menu.add(item);
		item = new JMenuItem("Choose new Escape Time");
		item.addActionListener(new EscTimeChooser());
		menu.add(item);
		item = new JMenuItem("Choose new number of Threads");
		item.addActionListener(new ThreadChooser());
		menu.add(item);
		bar.add(menu);
		
		JButton reset = new JButton("Reset Zoom");
		reset.addActionListener(new resetZoom());
		reset.setFocusable(false);
		bar.add(reset);
	}
	
	//ActionListener that changes the fractal being displayed
	class ChangeFractalEvent implements ActionListener{
		Fractal frac;
		public ChangeFractalEvent(Fractal frac){
			this.frac = frac;
		}
		
		public void actionPerformed(ActionEvent e) {
			fractal = frac;
			updateFractal();
		}
	}
	
	//ActionListener that asks the user to input a new escape distance
	class DistanceChooser implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String ed = JOptionPane.showInputDialog(frame, "Input new Escape Distance", null);
			try{
				int j = Integer.parseInt(ed);
				if (j<0){
					JOptionPane.showMessageDialog(frame, "Must be an integer greater than 0");
				}
				else{
					escapeDistance=j;
					updateFractal();
				}
			}
			catch (NumberFormatException n){
				JOptionPane.showMessageDialog(frame, "Input must be of type Integer");
			}
		}
		
	}
	
	//ActionListener to change the max escape time
	class EscTimeChooser implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String ed = JOptionPane.showInputDialog(frame, "Input new Escape Time", null);
			try{
				int j = Integer.parseInt(ed);
				if(j<1 || j>255){
					JOptionPane.showMessageDialog(frame, "Must be an integer between 1 & 255");
				}
				else{
					escapeTime=j;
					updateFractal();
				}
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(frame, "Input must be of type Integer");
			}
		}		
	}
	
	//ActionListener that exits the program
	class ExitAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	//ActionListener that changes the color model
	class ChangeColorModel implements ActionListener{
		int c;
		public ChangeColorModel(int c){
			this.c = c;
		}
		public void actionPerformed(ActionEvent e){
			colorModel=c;
			panel.setIndexColorModel(ColorModelFactory.setModel(colorModel, numColors));
			panel.updateImage(cp.getFractal());
		}
	}
	
	//ActionListener that changes the number of color shades
	class ChangeNumShades implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String ed = JOptionPane.showInputDialog(frame, "Input new number of colors", null);
			try{
				int j = Integer.parseInt(ed);
				if (j<0){
					JOptionPane.showMessageDialog(frame, "Must be greater than 0");
				}
				else{
					numColors = j;
					panel.setIndexColorModel(ColorModelFactory.setModel(colorModel, numColors));
					updateFractal();
				}
			}
			catch (NumberFormatException n){
				JOptionPane.showMessageDialog(frame, "Input must be of type Integer");
			}
		}
	}
	
	//Resets the zoom 
	class resetZoom implements ActionListener{
		public void actionPerformed(ActionEvent e){
			fractal = list.get(fractal.getPos());
			panel.resetValues();
			updateFractal();
		}
	}
	
	//MouseListener that zooms in on a selected area
	class ZoomListener extends MouseAdapter{
		private Timer timer;
		private int xMax=781, yMax=724, x1, x2, y1, y2, c1, c2;
		public void mousePressed(MouseEvent e){
			Point p = MouseInfo.getPointerInfo().getLocation();
			x1=e.getX();
			y1=e.getY();
			c1=x1-p.x;
			c2=y1-p.y;
			timer = new Timer(10, new Action());
			timer.start();
		}
		public void mouseReleased(MouseEvent e){
			timer.stop();
			panel.resetValues();
			x2 = e.getX();
			y2 = e.getY();
			if (x1!=x2&&y1!=y2){
				math();
				updateFractal();
			}
		}
		//Performs the math necessary to calculate new ranges for the fractal
		public void math(){
			double a = fractal.getXRange()/xMax;
			double b = fractal.getYRange()/yMax;
			greaterThan();
			double newXRange1 = fractal.getx1()+(a*x1);
			double newYRange1 = fractal.gety1()+(b*y1);
			double newXRange2 = fractal.getx2()-(a*(xMax-x2));
			double newYRange2 = fractal.gety2()-(b*(yMax-y2));
			fractal = new Fractal(newXRange1, newXRange2, newYRange1, newYRange2, fractal.getPos());
		}
		//Decides which coordinate is greater as to insure that the fractal is oriented properly
		public void greaterThan(){
			if (x1>x2){
				int temp = x2;
				x2 = x1;
				x1 = temp;
			}
			if (y1>y2){
				int temp = y2;
				y2 = y1;
				y1 = temp;
			}
		}
		//Performs animation for selection box.
		class Action implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Point d = MouseInfo.getPointerInfo().getLocation();
				x2=d.x+c1;
				y2=d.y+c2;
				panel.setValues(x1, x2, y1, y2);
				panel.updateImage(cp.getFractal());
			}
		}
	}
	
	class ThreadChooser implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String ed = JOptionPane.showInputDialog(frame, "Input new number of Threads", null);
			try{
				int j = Integer.parseInt(ed);
				if (j<0){
					JOptionPane.showMessageDialog(frame, "Must be greater than 0");
				}
				else{
					threads = j;
				}
			}
			catch (NumberFormatException n){
				JOptionPane.showMessageDialog(frame, "Input must be of type Integer");
			}
		}
	}
}
