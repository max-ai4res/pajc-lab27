package it.unibs.pajc;

public class Lavoro {
	volatile boolean fermati = false;
	
	void esegui() {
		long k=0;
		while(!fermati) {
			k++;
			if(k%1000==0) 
				System.out.printf("%d\n",k);
		}
	}
}
