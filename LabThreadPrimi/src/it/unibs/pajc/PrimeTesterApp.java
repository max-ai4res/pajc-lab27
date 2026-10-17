package it.unibs.pajc;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PrimeTesterApp {

	public static void main(String[] args) throws 
		InterruptedException, ExecutionException {
		long tstart = System.nanoTime();
		
		long maxN = 100_000_000;
		int nThread = 64; //Runtime.getRuntime().availableProcessors();
		int nTask = 256;
		
		// ? primi 1..1Mln con 2 Thread...
		ExecutorService exec = Executors.newFixedThreadPool(nThread);
		ArrayList<Future<Long>> futureList = new ArrayList<Future<Long>>();
		
		long perTask = maxN / nTask;
		for(int i=0; i<nTask; i++) {
			long da = 1 + i*perTask;
			long a = (i==nTask-1) ? maxN : da + perTask - 1;
			futureList.add(exec.submit(new PrimeTester(da, a)));
		}
		
		exec.shutdown();
		long nprimi = 0;
		for(var f: futureList)
			nprimi += f.get();
		
		long dt = (System.nanoTime() - tstart) / 1_000_000;
		System.out.printf("primi: %d [%dms]", nprimi, dt);

	}

}
