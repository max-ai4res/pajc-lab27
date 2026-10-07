package it.unibs.pajc;

public class ThreadApp01 {

	public static void main(String[] args) {
		Runnable task = () -> {
			String nome = Thread.currentThread().getName();
			System.out.println("Th: " + nome);
		};
		System.out.println("...avvio...");
		//task.run();
		
		Thread thread = new Thread(task);
		thread.start();
		
		Thread thread2 = new Thread(task);
		thread2.start();
		
		System.out.println("...fine...");
	}

}
