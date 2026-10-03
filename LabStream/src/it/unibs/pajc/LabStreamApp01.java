package it.unibs.pajc;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.*;

public class LabStreamApp01 {
	// isPrimo
	
	public static boolean isPrimo(int n) {
		if(n<=1) return false;
		if(n==2) return true;
		if(n%2 == 0) return false;
		
		int dmax = (int)Math.sqrt(n);
		
		for(int d=3; d <= dmax; d+=2) {
			if(n % d == 0)
				return false;
		}
		
		return true;
	}

	public static void trasmetti(IntStream sequenza, int size) {
		sequenza
			.limit(size)
			.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		Supplier<IntStream> primi = () -> IntStream
			.iterate(1, n -> n + 1)	
			.filter(LabStreamApp01::isPrimo);
			
		trasmetti(primi.get(), 10);
		
		trasmetti(primi.get(), 20);
		
		//trasmetti(IntStream.iterate(0, n -> n+2), 10);
		
		/*
		// I primi 100 primi....
		List<Integer> list01 = IntStream
			.iterate(1, n -> n + 1)	
			.filter(LabStreamApp01::isPrimo)
			.limit(100)
			.boxed()
			.toList();
			//.forEach(System.out::println);
		
		System.out.println(list01);
		*/
		
		/*
		// Numeri primi tra 0..100
		IntStream
			.range(0, 100)
			//.filter(a -> isPrimo(a))
			.filter(LabStreamApp01::isPrimo)
			//.map(x -> x*x*x)
			.forEach(System.out::println);
		 */
	}

}
