package it.unibs.pajc;

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
	
	public void stepNext(int w, int h) {
		posizione = new Vec2(posizione.x() + velocita.x(),
				posizione.y() + velocita.y());
		
		if(posizione.x() - raggio < 0) {
			posizione = new Vec2(raggio, posizione.y());
			velocita = new Vec2(Math.abs(velocita.x()), velocita.y());
		} else if(posizione.x() + raggio > w) {
			posizione = new Vec2(w - raggio, posizione.y());
			velocita = new Vec2(-Math.abs(velocita.x()), velocita.y());
		}
		
		if(posizione.y() - raggio < 0) {
			posizione = new Vec2(posizione.x(), raggio);
			velocita = new Vec2(velocita.x(), Math.abs(velocita.y()));
		} else if (posizione.y() + raggio > h) {
			posizione = new Vec2(posizione.x(), h - raggio);
			velocita = new Vec2(velocita.x(), -Math.abs(velocita.y()));
		}
		
		
		
		
	}
}
