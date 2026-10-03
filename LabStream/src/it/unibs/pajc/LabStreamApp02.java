package it.unibs.pajc;

import java.util.stream.IntStream;

public class LabStreamApp02 {

	public static void printTabellina(int n) {
		for(int k=1; k<=n; k++) {
			System.out.println("--- tabellina del: " + k);
			String str = "x"+k+"=";
			int kk = k;
			IntStream
				.range(1, 11)
				.mapToObj(i -> i + str + i*kk)
				.forEach(System.out::println);
		}
	}
	
	public static void main(String[] args) {
		//printTabellina(12);
		printTabellina(5);
	}

}
