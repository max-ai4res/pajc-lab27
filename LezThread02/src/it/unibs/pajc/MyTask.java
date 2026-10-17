package it.unibs.pajc;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Long> {
	
	private final String nome;
	private int nStep, delay;
	
	public MyTask(String nome, int nStep, int delay) {
		this.nome = nome;
		this.nStep = nStep;
		this.delay = delay;
	}
	
	public Long call() {
		long i=0;
		for(; i<nStep; i++) {
			System.out.printf("[%s], passo %2d [%s]\n",
					nome, i, Thread.currentThread().getName());
			
			try {
				Thread.sleep(delay);
			} 
			catch(InterruptedException e) {
				System.out.println("Task Interrotto!");
				//Thread.currentThread().interrupt();
				return i;
			}		
		}
		return i;
	}
}
