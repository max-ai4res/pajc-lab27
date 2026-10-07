package it.unibs.pajc;

public class MyTask implements Runnable {
	
	private final String nome;
	private int nStep, delay;
	
	public MyTask(String nome, int nStep, int delay) {
		this.nome = nome;
		this.nStep = nStep;
		this.delay = delay;
	}
	
	public void run() {
		
		for(int i=0; i<nStep; i++) {
			System.out.printf("[%s], passo %2d [%s]\n",
					nome, i, Thread.currentThread().getName());
			
			try {
				Thread.sleep(delay);
			} catch(InterruptedException e) {
				System.out.println("Task Interrotto!");
				Thread.currentThread().interrupt();
				return;
			}		
		}	
	}
}
