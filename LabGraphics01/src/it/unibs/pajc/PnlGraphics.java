package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PnlGraphics extends JPanel {
	int count =0;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		
		g.setColor(Color.red);
		g.fillOval(0, 0, w, h);
		
		g.setColor(Color.white);
		g.drawLine(0,0 , w, h);
		System.out.printf("paintComponent: %d [%s]\n",
				count++,
				Thread.currentThread().getName());
	}
}
