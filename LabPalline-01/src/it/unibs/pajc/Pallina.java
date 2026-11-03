package it.unibs.pajc;

/**
 * Una pallina che si muove e rimbalza sui bordi.
 *
 * La pallina è un'entità che cambia nel tempo: il suo stato sta in campi
 * primitivi privati, modificabili solo da lei. Verso l'esterno posizione e
 * velocità escono come Vec2, valori immutabili: chi li legge riceve
 * un'istantanea e non può spostare la pallina per sbaglio.
 */
public class Pallina {
	private final int id;
	private final double raggio;
	private double x, y;	// posizione del centro
	private double vx, vy;	// velocità in pixel per passo

	Pallina(int id, Vec2 posizione, Vec2 velocita, double raggio) {
		this.id = id;
		this.raggio = raggio;
		this.x = posizione.x();
		this.y = posizione.y();
		this.vx = velocita.x();
		this.vy = velocita.y();
	}

	public int id() { return id; }
	public double raggio() { return raggio; }
	public Vec2 posizione() { return new Vec2(x, y); }	// istantanea, non un riferimento
	public Vec2 velocita() { return new Vec2(vx, vy); }

	public void stepNext(int w, int h) {
		x += vx;
		y += vy;

		// Math.abs e non il cambio di segno: se la pallina è ancora fuori
		// al passo dopo, la velocità resta rivolta verso l'interno.
		if (x < raggio)          { x = raggio;     vx =  Math.abs(vx); }
		else if (x > w - raggio) { x = w - raggio; vx = -Math.abs(vx); }

		if (y < raggio)          { y = raggio;     vy =  Math.abs(vy); }
		else if (y > h - raggio) { y = h - raggio; vy = -Math.abs(vy); }
	}
}
