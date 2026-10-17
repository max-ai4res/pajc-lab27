package it.unibs.pajc;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LezTh02App {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
			System.out.println("avvio...");
			ExecutorService executor = Executors.newCachedThreadPool();
			
			ArrayList<Future<Long>> future = new ArrayList<>();
			
			future.add(executor.submit(new MyTask("task 1", 10, 300)));
			future.add(executor.submit(new MyTask("task 2", 10, 200)));
			future.add(executor.submit(new MyTask("task 3", 10, 100)));
			future.add(executor.submit(new MyTask("task 4", 10, 250)));
			future.add(executor.submit(new MyTask("task 5", 10, 100)));
			
			
			long totale=0;
			for(Future<Long> f: future) {
				totale += f.get();
			}
			
			
			System.out.println(totale);
			
			
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

}
