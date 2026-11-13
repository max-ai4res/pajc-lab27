package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PnlViewport extends JPanel implements 
	MouseListener, MouseMotionListener {
	
	protected Rectangle2D.Double defaultViewport = 
			new Rectangle2D.Double(-1.5, -1.5, 3, 3);
	
	protected Rectangle2D.Double viewport;
	
	public void setViewport(Rectangle2D.Double viewport) {
		this.viewport = viewport;
		this.repaint();
	}

	public PnlViewport() {
		setViewport(defaultViewport);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
		
		Point o = worldToScreen(new Point2D.Double(0 , 0));
		int r = worldToScreen(1f);
		
		g2.fillOval(o.x-r, o.y-r, r*2, r*2);
		
		
		if(mousePosition != null) {
			g2.setColor(Color.red);
			Point2D.Double world = screenToWorld(mousePosition);
			String str = String.format("x: %.2f, y: %.2f", world.x, world.y);
			g2.drawString(str, mousePosition.x, mousePosition.y);
		}
		

	}
	
	protected int worldToScreen(double worldSize) {
		return (int) (worldSize * Math.min(getWidth()/viewport.getWidth(), 
				getHeight() / viewport.getHeight()));
	}
	
	protected Point worldToScreen(Point2D.Double world) {
		int x = (int) ((world.x - viewport.getMinX()) * getWidth() / viewport.getWidth());
		int y = (int) ((world.y - viewport.getMinY()) * getHeight() / viewport.getHeight());
		return new Point(x,y);
	}
	
	protected Point2D.Double screenToWorld(Point screen) {
		double x = viewport.getMinX() + screen.x * viewport.getWidth() / getWidth();
		double y = viewport.getMinY() + screen.y * viewport.getHeight() / getHeight();
		return new Point2D.Double(x, y);
	}

	
	public void zoom(Point2D.Double p, double zoom) {
		double fwidth = viewport.getWidth() / zoom;
		double fheight = viewport.getHeight() / zoom;
		
		double rx = (p.x - viewport.getMinX()) / viewport.getWidth();
		double ry = (p.y - viewport.getMinY()) / viewport.getHeight();
		
		Rectangle2D.Double newViewport =  new Rectangle2D.Double(
				p.x - fwidth * rx,
				p.y - fheight * ry,
				fwidth,
				fheight);
		
		setViewport(newViewport);
	}
	

	private Point panStartMousePosition = null;
	private Rectangle2D.Double panStartViewport = null;
	@Override
	public void mouseDragged(MouseEvent e) {
		
		mousePosition = e.getPoint();
		
		if(panStartMousePosition == null) {
			panStartMousePosition = e.getPoint();
			panStartViewport = viewport;
			return;
		}
		
		int dxScreen = e.getX() - panStartMousePosition.x;
		int dyScreen = e.getY() - panStartMousePosition.y;
		
		double dx = dxScreen * panStartViewport.getWidth() / getWidth();
		double dy = dyScreen * panStartViewport.getHeight() / getHeight();
		
		Rectangle2D.Double newViewport = new Rectangle2D.Double(
				panStartViewport.x - dx,
				panStartViewport.y - dy,
				panStartViewport.getWidth(),
				panStartViewport.getHeight());
		
		setViewport(newViewport);
		
	}

	
	protected Point mousePosition;
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point2D.Double world = screenToWorld(e.getPoint());
		
		if(e.isShiftDown()) {
			zoom(world, 1./1.1f);
		} else {
			zoom(world, 1.1f);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panStartMousePosition = null;
		
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
