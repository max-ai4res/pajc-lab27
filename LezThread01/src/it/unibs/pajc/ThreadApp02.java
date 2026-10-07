package it.unibs.pajc;

public class ThreadApp02 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("avvio....");
		MyTask task1 = new MyTask("task 1", 5, 300);
		MyTask task2 = new MyTask("task 2", 10, 200);
		
		Thread th1 = new Thread(task1);
		Thread th2 = new Thread(task2);
		
		th1.start();
		th2.start();
		
		th1.join();
		th2.join();
		
		System.out.println("fine....");
	}
}
