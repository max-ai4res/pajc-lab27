package it.unibs.pajc;

import java.util.HashMap;

public class CalcCLIApp {

	static BinaryOperator sommaScalata(int k) {
		// k * (a+b)
		BinaryOperator op = new BinaryOperator() {
			public int eval(int a, int b) {
				return k*(a+b);
			}
		};
		
		return op;
	}
	
	public static void main(String[] args) {
		int a = 12;
		int b = 2;
		int k = 3;
		String chOp = "+*3"; // +, -, *, /
		
		HashMap<String, BinaryOperator> opMap = new HashMap<>();
		
		BinaryOperator opSomma = new BinaryOperator() {
			public int eval(int a, int b) { return k*(a+b); }
		};
		
		
		System.out.println(opSomma.getClass().getName());
		
		opMap.put("+", opSomma);
		
		opMap.put("-", new BinaryOperator() 
			{ public int eval(int a, int b) {
				return a - b; }
			}
		);
		
		opMap.put("*", new Prodotto());
		opMap.put("/", new Divisione());
		opMap.put("^", new Potenza());
		opMap.put("+*3", sommaScalata(3));
		opMap.put("+*2", sommaScalata(2));
		
		
		// ---
		BinaryOperator op = opMap.get(chOp);
		
		int res = op.eval(a, b);
		System.out.printf("%d %s %d = %d", a, chOp, b, res);
		

	}

}
