package it.unibs.pajc;

@FunctionalInterface
public interface RowFormatter {
	String format(String row, int n);
}
