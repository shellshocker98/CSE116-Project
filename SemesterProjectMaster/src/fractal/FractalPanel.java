package fractal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FractalPanel
extends JPanel {
    private static final Dimension DEFAULT_DIMENSION = new Dimension(512,512);
    private IndexColorModel colorModel;
    private BufferedImage fractal;
    private int x1,x2,y1,y2;

    public FractalPanel() {
        this(DEFAULT_DIMENSION, FractalPanel.getDefaultColorModel());
    }

    public FractalPanel(Dimension d, IndexColorModel cMod) {
        super(null);
        this.setSize(d);
        this.colorModel = cMod;
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        this.setPreferredSize(d);
        this.fractal = new BufferedImage(d.width, d.height, 1);
        this.fractal.setAccelerationPriority(0.7f);
    }

    private static IndexColorModel getDefaultColorModel() {
        byte[] reds = new byte[101];
        byte[] greens = new byte[101];
        byte[] blues = new byte[101];
        int i = 0;
        while (i < reds.length - 1) {
            int rgb = Color.HSBtoRGB((float)i / 100.0f, 0.6f, 1.0f);
            reds[i] = (byte)((rgb & 16711680) >> 16);
            greens[i] = (byte)((rgb & 65280) >> 8);
            blues[i] = (byte)(rgb & 255);
            ++i;
        }
        IndexColorModel retVal = new IndexColorModel(8, 101, reds, greens, blues);
        return retVal;
    }

    public boolean saveImage(SaveFormat format, String fileName) {
        String extension = format.name().toLowerCase();
        File outputFile = new File(String.valueOf(fileName) + "." + extension);
        try {
            ImageIO.write((RenderedImage)this.fractal, extension, outputFile);
            return true;
        }
        catch (IOException e) {
            System.err.println("ERROR: Could not output fractal image");
            e.printStackTrace();
            System.err.println();
            return false;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.fractal.getWidth() != this.getWidth() || this.fractal.getHeight() != this.getHeight()) {
            Image drawMe = this.fractal.getScaledInstance(this.getWidth(), this.getHeight(), 4);
            g.drawImage(drawMe, 0, 0, Color.LIGHT_GRAY, null);
        } else {
            g.drawImage(this.fractal, 0, 0, Color.LIGHT_GRAY, null);
        }
        g.setColor(Color.YELLOW);
        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y2, x2, y2);
        g.drawLine(x1, y1, x1, y2);
        g.drawLine(x2, y1, x2, y2);
    }
    
    public void setValues(int x1, int x2, int y1, int y2){
    	this.x1=x1;
    	this.x2=x2;
    	this.y1=y1;
    	this.y2=y2;
    }
    
    public void resetValues(){
    	setValues(0,0,0,0);
    }

    public void setIndexColorModel(IndexColorModel newModel) {
        this.colorModel = newModel;
    }

    public int getImageWidth() {
        return this.fractal.getWidth();
    }

    public int getImageHeight() {
        return this.fractal.getHeight();
    }

    public void updateImage(int[][] escapeSteps) {
        int repeatX = this.fractal.getWidth() / escapeSteps.length;
        int r = 0;
        int[][] arrn = escapeSteps;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int[] row = arrn[n2];
            int repeatY = this.fractal.getHeight() / row.length;
            int c = 0;
            int[] arrn2 = row;
            int n3 = arrn2.length;
            int n4 = 0;
            while (n4 < n3) {
                int value = arrn2[n4];
                int rgb = this.colorModel.getRGB(value);
                int i = 0;
                while (i < repeatX) {
                    int j = 0;
                    while (j < repeatY) {
                        this.fractal.setRGB(r + i, c + j, rgb);
                        ++j;
                    }
                    ++i;
                }
                c += repeatY;
                ++n4;
            }
            r += repeatX;
            ++n2;
        }
        this.repaint();
    }

    public static enum SaveFormat {
        GIF,
        PNG,
        JPG;
        

        private SaveFormat(String string2, int n2) {
        }
    }

}


