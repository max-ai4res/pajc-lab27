package it.unibs.pajc;

import java.util.concurrent.Callable;

public class PrimeTester implements Callable<Long>{
	
	private long start;
	private long end;
	public long nprimi = 0;
	
	public PrimeTester(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	public Long call() {
		return countPrimi();
	}
	
	public long countPrimi() {
		for(long i=start; i<=end; i++) 
			if(isPrime(i))
				nprimi++;
		
		return nprimi;
	}
	
	public static boolean isPrime(long n) {
		if(n<=1) return false;
		if(n==2) return true;
		if(n%2 == 0) return false;
		
		long nmax = (long) Math.sqrt(n);
		
		for(long i=3; i<=nmax; i++) {
			if(n%i == 0)
				return false;
		}
		
		return true;
	}
}
