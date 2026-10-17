package it.unibs.pajc;

public class LavoroApp {

	public static void main(String[] args) throws InterruptedException {
		Lavoro l = new Lavoro();
		new Thread(l::esegui).start();
		Thread.currentThread().sleep(500);
		
		l.fermati = true;

	}

}
