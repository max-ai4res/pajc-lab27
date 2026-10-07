package it.unibs.pajc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadApp03 {
	
	public static void main(String[] args) {
		System.out.println("avvio...");
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.submit(new MyTask("task 1", 10, 300));
		executor.submit(new MyTask("task 2", 10, 200));
		executor.submit(new MyTask("task 3", 10, 100));
		executor.submit(new MyTask("task 4", 10, 250));
		executor.submit(new MyTask("task 5", 1000, 100));
		
		executor.shutdown();
		try {	
			if(!executor.awaitTermination(5, TimeUnit.SECONDS))
				executor.shutdownNow();
			
		} catch(InterruptedException ex) {
	        // ripristina l'interrupt del main thread
	        executor.shutdownNow();
	        Thread.currentThread().interrupt();
		}
		
		System.out.println("fine...");
	}
	
	/*--------------------------------
	shutdown()
    	↓
	non accetta nuovi task
	ma lascia finire quelli esistenti

	awaitTermination(15s)
    	↓
	aspetta al massimo 15 secondi

	shutdownNow()
    	↓
	manda interrupt() ai task ancora attivi

	task
    	↓
	DEVE reagire all'interrupt 
	--------------------------------*/

}
