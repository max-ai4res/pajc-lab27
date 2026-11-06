package it.unibs.pajc;

import java.awt.geom.Point2D;

public class Pallina {
	private final int id;
	private Vec2 posizione;
	private Vec2 velocita;
	private final double raggio;

	Pallina(int id, Vec2 posizione, Vec2 velocita, double raggio) {
		this.id = id;
		this.posizione = posizione;
		this.velocita = velocita;
		this.raggio = raggio;
	}
	
	public int id() { return id; }
	public Vec2 posizione() { return posizione; }
	public Vec2 velocita() { return velocita; }
	public double raggio() { return raggio; }
	
	public void stepNext(double w, double h) {
		double x = posizione.x(), y = posizione.y();
		double vx = velocita.x(), vy = velocita.y();
		
		x+=vx; y+=vy;
		
		if(x - raggio < 0) {
			x =raggio; 
			vx= Math.abs(vx); 
		} else if(posizione.x() + raggio > w) {
			x = w - raggio;
			vx= -Math.abs(vx);
		}
		
		if(y - raggio < 0) {
			y = raggio;
			vy= Math.abs(vy);
		} else if (posizione.y() + raggio > h) {
			y =  h - raggio;
			vy= -Math.abs(vy);
		}
		
		posizione = new Vec2(x, y);
		velocita = new Vec2(vx, vy);
		
		
	}
}
