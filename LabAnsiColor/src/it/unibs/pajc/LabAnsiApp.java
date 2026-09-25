package it.unibs.pajc;

import java.util.*;
import java.util.function.UnaryOperator;

public class LabAnsiApp {

	public static void main(String[] args) {
		System.out.println(Ansi.GREEN.paint("prova"));
		System.out.println("seconda riga");
		Map<String, UnaryOperator<String>> comandi = new TreeMap<>();
		
		comandi.put("red", s -> Ansi.RED.paint(s));
		comandi.put("blue", s -> Ansi.BLUE.paint(s));
		comandi.put("green", s -> Ansi.GREEN.paint(s));
		comandi.put("bold", s -> Ansi.BOLD.paint(s));
		comandi.put("underline", s -> Ansi.UNDERLINE.paint(s));
		comandi.put("reverse", s -> Ansi.REVERSE.paint(s));
		comandi.put("upper", String::toUpperCase); // s -> s.toUpperCase()
		
		
		// red pajc --> pajc scritto in rosso
		
		var in = new Scanner(System.in);
		while(in.hasNextLine()) {
			String[] parti = in.nextLine().split(" ", 2);
			String arg = parti.length > 1 ? parti[1] : "";
			String cmd = parti.length > 0 ? parti[0] : "";
			
			if("".equals(cmd)) continue;
			if("quit".equals(cmd)) break;
			if("help".equals(cmd)) 
				{ System.out.println(comandi.keySet()); continue; };
			
			
			UnaryOperator<String> op = comandi.get(cmd);
			System.out.println(op == null ? "comando sconosciuto" :
				op.apply(arg));
			
			
		}
		
		System.out.println(".fine.");
		
	}

}
