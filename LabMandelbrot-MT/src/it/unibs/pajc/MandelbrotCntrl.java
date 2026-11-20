package it.unibs.pajc;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MandelbrotCntrl {
	private MandelbrotModel model;
	private ExecutorService exec = Executors.newFixedThreadPool(6);
	private ArrayList<Future<?>> pendingTask = new ArrayList<Future<?>>();
	private int[] resolutions = new int[] { 50, 100, 250, 500, 1000, 2000};
	
	public MandelbrotCntrl(MandelbrotModel model) {
		this.model = model;
	}
	
	public void update(Rectangle2D.Double viewport) {
		if(model == null)
			return;
		
		for(var f: pendingTask)
			f.cancel(true);
			
		for(int res: resolutions) {
			Runnable task = () -> model.eval(
				new Complex(viewport.getMinX(), viewport.getMinY()),
				new Complex(viewport.getMaxX(), viewport.getMaxY()),
				res);
			
			pendingTask.add(exec.submit(task));
		}
	}

	public double[][] getCurrentData() {
		return model.getData();
	}

}
