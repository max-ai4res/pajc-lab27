package it.unibs.pajc;

public class Counter {
	private final Object lock_inc = new Object();
	private final Object lock_inc_base = new Object();
	
	int count = 0;
	int count_base = 0;
	
	void inc_count_base() {
		
		synchronized(lock_inc_base)  {
			this.count_base++;
		}
	}
	
	void inc2() {
		synchronized (lock_inc) {
			
	
		this.inc();
		this.inc();
		}
	}
	
	void inc() { 
		// sezione di codice complesso parallelizzabile
		synchronized(lock_inc) {
			count++;
		}
	}
}
