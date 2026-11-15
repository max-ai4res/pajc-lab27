package it.unibs.pajc;

import javax.swing.event.ChangeEvent;

public class MandelbrotModel extends BaseModel {
	private double[][] data; // tasso di divergenza
	
	public double[][] getData() { return data; }
	
	public void eval(Complex min, Complex max, int res) {
		double[][] tmpData = new double[res][res];
		
		double dre = Math.abs(min.re - max.re) / res;
		double dim = Math.abs(min.im - max.im) / res;
		
		for(int i=0; i<res; i++) {
			for(int j=0; j<res; j++) {
				
				Complex c = new Complex(min.re + j*dre, min.im + i*dim);
				tmpData[i][j] = fMandelbrot(c);
			}
		}
		
		synchronized (this) {
			this.data = tmpData;
			fireValuesChange(new ChangeEvent(this));
		}
	}
	
	// zn = zn-1 ^ 2 + C, z0 = C
	public double fMandelbrot(Complex c) {
		int maxi = 500;
		
		Complex z = new Complex(0,0);
		
		for(int i=0; i<maxi; i++) {
			z = z.sqr().sum(c);
			if(z.module2() > 1e5)
				return (maxi-i)/(double)maxi;
		}
		
		return 0.;
	}

	
}
