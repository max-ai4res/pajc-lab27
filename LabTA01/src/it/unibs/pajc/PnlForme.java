package it.unibs.pajc;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JPanel;

public class PnlForme extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int w = getWidth();
		int h = getHeight();
		double r = Math.min(w, h) * 0.4;
		
		//g2.translate(w/2, h/2);
		
		Random rnd = new Random();
		for(int i=0; i<5; i++) {
			g2.translate(rnd.nextInt(100), rnd.nextInt(100));
			fiore(g2, r, 10);
		}
	}
	
	protected void fiore(Graphics2D g2, double r, int n) {
		Ellipse2D petalo = new Ellipse2D.Double(0, -r * 0.18, r, r * 0.36);
		g2.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.45f));
		
		g2.setColor(Color.blue);
		
		for(int i=0; i<n; i++) {
			g2.setColor(Color.getHSBColor(0.75f + 0.25f*i/n, 0.7f, 1f));
			g2.fill(petalo);
			g2.rotate(2*Math.PI / n);
		}
		
		g2.setComposite(AlphaComposite.SrcOver);
		g2.setColor(Color.YELLOW);
		g2.fill(new Ellipse2D.Double(-r*0.10, -r*0.10, r*0.2, r*0.2));
	}
	
}
