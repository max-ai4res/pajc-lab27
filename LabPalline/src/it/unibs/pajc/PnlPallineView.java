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
	
	private int nextId = 0;
	private final List<Pallina> palline = new ArrayList<>();
	private final Random rnd = new Random();
	private final Timer timer = new Timer(15, e -> stepNext());

	public void stepNext() {
		int w = getWidth(); int h = getHeight();
		for(Pallina p: palline)
			p.stepNext(w, h);
		
		repaint();
	}
	
	public void aggiungi(double x, double y) {
		int id = nextId++;
		Vec2 posizione = new Vec2(x, y);
		double velocita = 4 * rnd.nextDouble() + 2;
		double angolo = Math.PI * 2 * rnd.nextDouble();
		
		Vec2 velocita_vettore = new Vec2(velocita*Math.cos(angolo),
				velocita*Math.sin(angolo));
		
		int raggio = 8 + rnd.nextInt(20);
		
		Pallina p = new Pallina(id, posizione, velocita_vettore, raggio);
		palline.add(p);
		repaint();
	}
	
	public PnlPallineView() {
		setBackground(Color.white);
		
		for(int i=0; i<15; i++) {
			aggiungi(100+rnd.nextInt(400), 100 + rnd.nextInt(300));
		}
		
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
				
		for(Pallina p: palline)
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





