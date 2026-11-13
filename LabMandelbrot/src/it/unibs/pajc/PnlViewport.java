package it.unibs.pajc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PnlViewport extends JPanel {
	protected Rectangle2D.Double defaultViewport = 
			new Rectangle2D.Double(-1.5, -1.5, 3, 3);
	
	protected Rectangle2D.Double viewport;
	
	public void setViewport(Rectangle2D.Double viewport) {
		this.viewport = viewport;
		this.repaint();
	}

	public PnlViewport() {
		setViewport(defaultViewport);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Point o = worldToScreen(new Point2D.Double(0 , 0));
		int r = worldToScreen(1f);
		
		g2.fillOval(o.x-r, o.y-r, r*2, r*2);
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

}
