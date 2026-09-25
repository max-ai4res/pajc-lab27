package it.unibs.pajc;

public enum Ansi {
	BOLD(1), UNDERLINE(2), REVERSE(7), RESET(0),
	RED(31), GREEN(32), YELLOW(33), BLUE(34), MAGENTA(35), CYAN(36);
	
	private final int code;
	Ansi(int code) { this.code = code; }
	
	public String code() { return "\u001B[" + code + "m"; };
	public String paint(String s) { return code() + s + RESET.code(); }
	
}
