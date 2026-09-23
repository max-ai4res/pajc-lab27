package it.unibs.pajc;

public class LezLambdaApp {

	static void ripeti(int n, CoseDaFare coseDaFare) {
		for(int i=0; i<n; i++) {
			coseDaFare.esegui(i);
		}
	}
	
	public static void main(String[] args) {
		Runnable r;
		
		ripeti(10, v -> System.out.println(v));
		ripeti(10, v -> { if(v%2==0) 
			System.out.println("pari: " + v); });
		
		

	}

}
