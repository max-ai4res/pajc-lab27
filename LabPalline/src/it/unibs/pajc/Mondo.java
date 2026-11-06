package it.unibs.pajc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mondo {
	private final List<Pallina> palline = new ArrayList<>();
	private final double larghezza, altezza;
	private int nextId = 0;
	private final Random rnd = new Random();

	
	public Mondo(double larghezza, double altezza) {
		this.larghezza = larghezza;
		this.altezza = altezza;
		
		for(int i=0; i<15; i++) {
			aggiungi(100+rnd.nextDouble(larghezza), 100 + rnd.nextDouble(altezza));
		}
		
	}
	
	public List<Pallina> listPalline() { return palline; }
	
	public void stepNext() {
		for(Pallina p: palline)
			p.stepNext(larghezza, altezza);
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
	}
	
	
}
