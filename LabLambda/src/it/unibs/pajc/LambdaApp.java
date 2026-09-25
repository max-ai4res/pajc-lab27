package it.unibs.pajc;

public class LambdaApp {

	static long cronometraConcatenazione() {
		long t0 = System.nanoTime();
		
		String s = "";
		for(int i=0; i<100_000; i++) {
			s += "x";
		}
		
		long t1 = System.nanoTime();
		
		return (t1-t0) / 1_000_000;
	}
	
	static long cronometra(Runnable task) {
		long t0 = System.nanoTime();
		
		task.run();
		
		long t1 = System.nanoTime();
		return (t1-t0) / 1_000_000;
	}
	
	static void misura(String desc, Runnable task) {
		System.out.printf("%s: %dms\n", desc, cronometra(task));
	}
	
	public static void main(String[] args) {
		int ntest = 1_000_000;
		
		misura("Concatenazione con +=", () -> {
			String s = "";
			for(int i=0; i<100_000; i++) s += "x";
		});
		
		misura("StringBuilder", () -> {
			var sb = new StringBuilder();
			for(int i=0; i<ntest; i++) sb.append("x");
		});
		
		misura("StringBuffer", () -> {
			var sb = new StringBuffer();
			for(int i=0; i<ntest; i++) sb.append("x");
		});
	}

}
