package it.unibs.pajc;

import java.util.Map;

public class SyntaxHighlighter {
	private Map<String, Ansi> parole;
	
	public SyntaxHighlighter(Map<String, Ansi> parole) {
		this.parole = parole;
	}
	
	public String colora(String riga) {
		for(var e: parole.entrySet()) {
			riga = evidenzia(riga, e.getKey(), e.getValue());
		}
		
		return riga;
	}

	public String evidenzia(String riga, String key, Ansi colore) {
		var sb = new StringBuilder();
		int da = 0, pos;
		
		while((pos = riga.indexOf(key, da))>=0) {
			int fine = pos + key.length();
			sb.append(riga, da, pos).append(colore.paint(key));
			da = fine;
		}
		
		return sb.append(riga.substring(da)).toString();
		
	}
	
}
