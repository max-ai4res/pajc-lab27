package it.unibs.pajc;

import java.awt.geom.Rectangle2D;

public class MandelbrotCntrl {
	private MandelbrotModel model;
	
	public MandelbrotCntrl(MandelbrotModel model) {
		this.model = model;
	}
	
	public void update(Rectangle2D.Double viewport) {
		
		model.eval(
				new Complex(viewport.getMinX(), viewport.getMinY()),
				new Complex(viewport.getMaxX(), viewport.getMaxY()),
				250);
		
	}

	public double[][] getCurrentData() {
		return model.getData();
	}

}
