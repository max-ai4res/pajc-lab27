package it.unibs.pajc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PnlGraphics extends JPanel implements
	MouseListener, MouseMotionListener
{
	int count =0;
	private static final Color CHIARA = new Color(0xEEEED2);
	private static final Color SCURA = new Color(0x769656);
	private static final Color BACKGROUND = new Color(0x30302E);
	private int N = 8;
	private int lato_scacchiera, lato_cella, x0, y0;
	
	public PnlGraphics() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setBackground(BACKGROUND);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		
		int margine = 30;
		int lato = Math.min(w, h) - margine * 2;
		if(lato <= 0)
			return;
		
		lato_cella = lato / N;
		lato_scacchiera = lato_cella * N;
		x0 = (w - lato_scacchiera) / 2;
		y0 = (h - lato_scacchiera) / 2;
	
		drawBoard(g2, N, lato_cella, x0, y0);	

		g2.setColor(Color.red);
		// conversione screen -- world
		int cx = (currentMousePosition.x - x0) / lato_cella;
		int cy = (currentMousePosition.y - y0) / lato_cella;
		
		// conversione world -- screen
		int cella_x = cx * lato_cella + x0;
		int cella_y = cy * lato_cella + y0;
		
		g2.setStroke(new BasicStroke(3f));
		g2.drawRect(cella_x, cella_y, lato_cella, lato_cella);
		
		Point clickedPosition = worldToScreen(mouseClickedPosition);
		
		g2.fillOval(clickedPosition.x, clickedPosition.y, lato_cella, lato_cella);
	}
	
	protected Point screenToWorld(Point screen) {
		return new Point((screen.x - x0) / lato_cella,
				(screen.y - y0) / lato_cella);
	}
	
	protected Point worldToScreen(Point world) {
		return new Point(world.x*lato_cella + x0,
				world.y*lato_cella + y0);
	}

	private Point currentMousePosition = new Point(0,0);
	@Override
	public void mouseMoved(MouseEvent e) {
		currentMousePosition = e.getPoint();
		repaint();
	}
	
	private void drawBoard(Graphics2D g2, int N, int lato_cella, int x0, int y0) {
		int lato_scacchiera = lato_cella * N;
		for(int r=0; r < N; r++) {
			for(int c=0; c < N; c++) {
				g2.setColor((r+c) % 2 == 0 ? CHIARA : SCURA);
				g2.fillRect(x0 + c * lato_cella, y0 + r * lato_cella,
						lato_cella, lato_cella);
			}
		}
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		g2.setColor(CHIARA);
		FontMetrics fm = g2.getFontMetrics();
		
		for(int i=0; i < N; i++) {
			String colonna = String.valueOf((char) ('a' + i));
			int xc = x0 + i * lato_cella + 
					(lato_cella - fm.stringWidth(colonna)) / 2;			
			int yc = y0 + lato_scacchiera + fm.getAscent() + 4;			
			g2.drawString(colonna, xc, yc);
			
			String riga = String.valueOf(N - i);
			int xr = x0 - fm.stringWidth(riga) - 8;
			int yr = y0 + i * lato_cella +
					(lato_cella + fm.getAscent()) / 2;
			g2.drawString(riga, xr, yr);
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	private Point mouseClickedPosition = new Point(0,0);
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClickedPosition = screenToWorld(e.getPoint());
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
