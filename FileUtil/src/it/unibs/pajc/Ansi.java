package it.unibs.pajc;

public enum Ansi {
	RESET(0), BOLD(1), UNDERLINE(4), REVERSE(7),
    BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), MAGENTA(35), CYAN(36), WHITE(37),
    DIM(2), 
    BRIGHT_BLACK(90), BRIGHT_RED(91), BRIGHT_GREEN(92), BRIGHT_YELLOW(93),
    BRIGHT_BLUE(94), BRIGHT_MAGENTA(95), BRIGHT_CYAN(96), BRIGHT_WHITE(97);
	
	private final int code;
	Ansi(int code) { this.code = code; }
	
	public String code() { return "\u001B[" + code + "m"; };
	public String paint(String s) { return code() + s + RESET.code(); }
	
}
