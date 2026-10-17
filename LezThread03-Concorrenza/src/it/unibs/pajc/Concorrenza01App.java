package it.unibs.pajc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Concorrenza01App {

	public static void main(String[] args) 
			throws InterruptedException {

		ExecutorService ex = Executors.newFixedThreadPool(20);
		Counter c = new Counter();
		
		for(int i=0; i<10_000; i++)
			ex.submit(()-> {c.inc(); c.inc_count_base();});
		
		ex.shutdown();
		ex.awaitTermination(10, TimeUnit.SECONDS);
		System.out.print(""+c.count+", "+c.count_base);
	}

}
