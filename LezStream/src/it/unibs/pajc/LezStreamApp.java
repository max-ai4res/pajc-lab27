package it.unibs.pajc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LezStreamApp {
	
	
	public static void visualizza(IntStream data) {
		System.out.println("--> inizio visualizza");
		data.forEach(System.out::println);
		System.out.println("<-- fine visualizza");
	}
	
	public static void somma(IntStream data) {
		System.out.println("--> inizio visualizza");
		System.out.printf("Somma: %d\n", data.sum());
		System.out.println("<-- fine visualizza");
	}

	public static void main(String[] args) {
		Supplier<IntStream> supplierQuadrati = () -> {
		 	return IntStream
		 		.range(0, 20)
		 		.filter(x -> { 
		 			System.out.println("filter> " + x);
		 			return x % 2 == 0;	
		 		})
		 		.map(x -> x*x);
		};
			
			
		visualizza(supplierQuadrati.get());
		somma(supplierQuadrati.get());
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void main_stream_base(String[] args) {
		List<String> myList = List.of("a1","a2","a3", "b1", "c3", "c2");
		
		myList
			.stream()
			
			.filter(s -> {
				System.out.printf("FILTER: %s\n", s);
				return s.startsWith("c") || s.startsWith("C");	
			})
			
			.map(s -> {
				System.out.printf("MAP: %s\n", s);
				return s.toUpperCase();
			})
			.forEach(System.out::println);
	}
	
	
	public static void main__(String[] args) {
		List<String> myList = List.of("a1","a2","a3", "b1", "c3", "c2");
		List<String> stringheC = new ArrayList<String>();
		
		for(var s: myList) {
			if(s.startsWith("c"))
				stringheC.add(s);
		}
		
		List<String> maiuscole = new ArrayList<String>();
		for(var s: stringheC) {
			maiuscole.add(s.toUpperCase());
		}
		
		Collections.sort(maiuscole);
		
		for(var s: maiuscole) {
			System.out.println(s);
		}

	}

}
