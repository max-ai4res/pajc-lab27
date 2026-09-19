package it.unibs.pajc;

import java.util.HashMap;

public class CalcCLIApp {

	public static void main(String[] args) {
		int a = 12;
		int b = 2;
		String chOp = "^"; // +, -, *, /
		
		HashMap<String, BinaryOperator> opMap = new HashMap<>();
		opMap.put("+", new Somma());
		opMap.put("-", new Sottrazione());
		opMap.put("*", new Prodotto());
		opMap.put("/", new Divisione());
		opMap.put("^", new Potenza());
		
		
		// ---
		BinaryOperator op = opMap.get(chOp);
		
		int res = op.eval(a, b);
		System.out.printf("%d %s %d = %d", a, chOp, b, res);
		

	}

}
