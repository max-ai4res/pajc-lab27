package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PnlMouse extends JPanel 
	implements MouseMotionListener, MouseListener {

	private final Deque<Point> scia = new ArrayDeque<Point>();
	private int LUNGHEZZA = 60;
	private float tinta = 0.55f;
	
	public PnlMouse() {
		//MouseAdapter mouseAdapter = new MouseAdapter() {};
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		Timer t = new Timer(25, e -> rimuovi());
		t.start();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int i = 0, n = scia.size(); 
		for(Point p: scia) {
			float eta = (float)(i+1) / n;
			float r = 4 + 26 * eta;
			Color c = Color.getHSBColor((tinta + 0.3f*eta) % 1f, 0.9f, 1f);
			g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(),
					(int)(255*eta)));
			//g2.fillOval(p.x-r, p.y-r, r*2, r*2);
			g2.fill(new Ellipse2D.Double(p.x-r, p.y-r, r*2, r*2));
			
			i++;
		}
	}

	
	private void aggiungi(Point p) {
		scia.addLast(p);
		if(scia.size() > LUNGHEZZA)
			scia.removeFirst();
		repaint();
	}
	
	private void rimuovi() {
		if(scia.size()<=1)
			return;
		
		scia.removeFirst();
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		aggiungi(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		aggiungi(e.getPoint());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		tinta = (tinta + 0.13f) % 1f;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
