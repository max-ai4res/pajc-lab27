package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

public class PnlMandelbrot extends PnlViewport {
	private MandelbrotCntrl cntrl;
	
	public PnlMandelbrot(MandelbrotCntrl cntrl) {
		this.cntrl = cntrl;
		setViewport(new Rectangle2D.Double(-2., -1., 3, 2));
	}
	
	@Override
	public void setViewport(Double viewport) {
		super.setViewport(viewport);
		if(cntrl != null) {
			cntrl.update(viewport);
			setMandelbrotData(cntrl.getCurrentData());
		}
	}
	
	private Image mandelbrotImage;
	private void setMandelbrotData(double[][] data) {
		if(data == null)
			return;
		
		int xsize = data.length;
		int ysize = data[0].length;
		
		mandelbrotImage = new BufferedImage(xsize, ysize, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) mandelbrotImage.getGraphics();
		
		for(int i=0; i<xsize; i++) {
			for(int j=0; j<ysize; j++) {
				double v = data[j][i];
				int c = (int)(v*255);
				g2.setColor(new Color(c,c,c));
				g2.fillRect(i, j, 1, 1);
			}
		}
		
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D)g;
		
		if(mandelbrotImage != null)
			g2.drawImage(mandelbrotImage, 0, 0, getWidth(), getHeight(),  Color.white, null);
	}
	
}













