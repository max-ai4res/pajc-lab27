package it.unibs.pajc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class Protocollo {
	public static HashMap<String, Azione> commandMap = new HashMap<String, Azione>();
	
	/*
	 * /utenti 	--> lista utenti
	 * /a 		--> invia a un utente
	 * /tutti 	--> invia a tutti
	 * /exit 	--> esce
	 * /help
	 * 
	 */
	
	static {
		azione("/utenti", 	0, (s, a) -> { s.elencoUtenti(); });
		azione("/a", 		2, (s, a) -> { s.inviaUtente(a[0], a[1]); });
		azione("/tutti", 	1, (s, a) -> { s.inviaTutti(a[0]); });
		azione("/exit", 	0, (s, a) -> {});
		azione("/time", 	0, (s, a) -> { s.invia(""+LocalDateTime.now());} );
		azione("/help", 	0, (s, a) -> { s.elencoComandi(); });
	}
	
	private static void azione(String comando, int nargomenti, 
			BiConsumer<Sessione, String[]> esegui) {
		commandMap.put(comando, new Azione(comando, nargomenti, esegui));
	}
}
