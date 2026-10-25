package it.unibs.pajc;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PnlGraphics extends JPanel {
	int count =0;
	private static final Color CHIARA = new Color(0xEEEED2);
	private static final Color SCURA = new Color(0x769656);
	private static final Color BACKGROUND = new Color(0x30302E);
	
	public PnlGraphics() {
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
		int N = 8;
		int lato_cella = lato / N;
		int lato_scacchiera = lato_cella * N;
		int x0 = (w - lato_scacchiera) / 2;
		int y0 = (h - lato_scacchiera) / 2;
		
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
		
		
		
		
		
		/*for(int i=0; i<=N; i++) {
			g2.drawLine(x0 + i*lato_cella, y0 + 0, 
					x0 + i*lato_cella, y0 + lato_scacchiera);
			g2.drawLine(x0 + 0, y0 + i*lato_cella, 
					x0 + lato_scacchiera, y0 + i*lato_cella);
		}*/
		
		
	}
}
