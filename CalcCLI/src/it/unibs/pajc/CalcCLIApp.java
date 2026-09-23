package it.unibs.pajc;

import java.util.HashMap;

public class CalcCLIApp {

	static BinaryOperator sommaScalata(int k) {
		// k * (a+b)
		BinaryOperator op = new BinaryOperator() {
			public double eval(double a, double b) {
				return k*(a+b);
			}
		};
		
		return op;
	}
	
	public static void main(String[] args) {
		double a = 12;
		double b = 2;
		int k = 3;
		String chOp = "+*3"; // +, -, *, /
		
		HashMap<String, BinaryOperator> opMap = new HashMap<>();
		
		BinaryOperator opSomma = new BinaryOperator() {
			public double eval(double a, double b) { return k*(a+b); }
		};
		
		
		System.out.println(opSomma.getClass().getName());
		
		opMap.put("+", (x, y) -> x + y );		
		opMap.put("-", (x, y) -> x - y );
		opMap.put("*", (x, y) -> x * y );
		opMap.put("/", (x, y) -> x / y );
		
		opMap.put("^", Math::pow);
		
		opMap.put("+*3", sommaScalata(3));
		opMap.put("+*2", sommaScalata(2));
		
		
		// ---
		BinaryOperator op = opMap.get(chOp);
		
		double res = op.eval(a, b);
		System.out.printf("%f %s %f = %f", a, chOp, b, res);
		

	}

}
