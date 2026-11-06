package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PnlPallineView extends JPanel {
	private final Mondo mondo;
	private final Timer timer = new Timer(15, e -> stepNext());

	public void stepNext() {
		mondo.stepNext();
		repaint();
	}
	
	
	public PnlPallineView(Mondo mondo) {
		this.mondo = mondo;
		setBackground(Color.white);
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
				
		for(Pallina p: mondo.listPalline())
			paintPallina(g2, p);
		
	}

	private void paintPallina(Graphics2D g2, Pallina p) {
		g2.setColor(Color.blue);
		g2.fill(new Ellipse2D.Double(
				p.posizione().x() - p.raggio(),
				p.posizione().y() - p.raggio(),
				p.raggio() * 2,
				p.raggio() * 2
				));
		
		g2.setColor(new Color(255,255,255, 90));
		g2.fill(new Ellipse2D.Double(
				p.posizione().x() - p.raggio() * 0.5,
				p.posizione().y() - p.raggio() * 0.5,
				p.raggio() * 0.6,
				p.raggio() * 0.45
				));
	}
}





