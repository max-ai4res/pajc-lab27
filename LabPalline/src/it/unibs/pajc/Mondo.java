package it.unibs.pajc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mondo {
	private final List<Pallina> palline = new ArrayList<>();
	private final double larghezza, altezza; // metri
	private int nextId = 0;
	private final Random rnd = new Random();
	private double g = 9.81; // m/s^2

	public double larghezza() { return larghezza; }
	public double altezza() { return altezza; }
	
	public Mondo(double larghezza, double altezza) {
		this.larghezza = larghezza;
		this.altezza = altezza;
		
		for(int i=0; i<15; i++) {
			aggiungi(100+rnd.nextDouble(larghezza), 100 + rnd.nextDouble(altezza));
		}
		
	}
	
	public List<Pallina> listPalline() { return palline; }
	
	private long time = 0;
	
	public void stepNext() {
		long timeNow = System.nanoTime();
		long dt_ns = (time == 0) ? 0 : timeNow - time;
		time = timeNow;
		double dt = dt_ns/(double)1e9; // dt in secondi
		dt *= 10;
	
		for(Pallina p: palline)
			p.stepNext(g, dt, larghezza, altezza);
	
		for(int i=0; i<palline.size(); i++) {
			for(int j=i+1; j < palline.size(); j++) {
				gestisciUrto(palline.get(i), palline.get(j));
			}
		}
	}
	
	private void gestisciUrto(Pallina a, Pallina b) {
		double dx = a.posizione().x() - b.posizione().x();
		double dy = a.posizione().y() - b.posizione().y();
		
		double d = Math.sqrt(dx*dx + dy*dy);
		double dmin = a.raggio() + b.raggio();
		
		if(d >= dmin)
			return;
		
		a.invertiVelocita();
		b.invertiVelocita();
	}
	
	public void aggiungi(double x, double y) {
		int id = nextId++;
		Vec2 posizione = new Vec2(x, y);
		double velocita = 40 * rnd.nextDouble() + 2;
		double angolo = Math.PI * 2 * rnd.nextDouble();
		
		Vec2 velocita_vettore = new Vec2(velocita*Math.cos(angolo),
				velocita*Math.sin(angolo));
		
		int raggio = 8 + rnd.nextInt(20);
		
		Pallina p = new Pallina(id, posizione, velocita_vettore, raggio);
		palline.add(p);
	}
	
	
}
