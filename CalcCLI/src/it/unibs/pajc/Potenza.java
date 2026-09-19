package it.unibs.pajc;

public class Potenza implements BinaryOperator {
	public int eval(int a, int b) {
		return (int) Math.pow(a, b);
	}
}
