package it.unibs.pajc;

import java.util.stream.*;

public class LabStreamFiboApp {

	public static void main(String[] args) {
		// Fn = Fn-1 + Fn-2
		
		LongStream fibs = Stream
				.iterate(new long[] {1, 1}, f -> new long[] {f[1], f[0]+f[1]})
				.mapToLong(f -> f[0]);
		
		fibs.limit(10).forEach(System.out::println);

	}

}
