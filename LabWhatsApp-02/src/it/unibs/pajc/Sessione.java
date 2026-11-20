package it.unibs.pajc;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Sessione implements Runnable {
	private static final ConcurrentHashMap<String, Sessione> sessioni = new ConcurrentHashMap<>();
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public Sessione(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			log("client conneso...");
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream(), StandardCharsets.UTF_8));
			out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);
			
			if(!login())
				return;
			
			sessioni.put(nome, this);
			
			out.println("Benvenuto " + nome);
			
			String riga;
			while((riga=in.readLine())!=null) {
				log(riga);
				
				elaboraRiga(riga);
				
			}
			
			log("client disconnesso");
		} catch(IOException e) {
			
		} finally {
			chiudi();
		}
	}
	
	private void elaboraRiga(String riga) {
		if(riga.isBlank()) return;
		if(!riga.startsWith("/")) return;
		
		String[] token = riga.strip().split(" ", 2);
		Azione a = Protocollo.commandMap.get(token[0]);
		if(a == null) { invia("comando non riconosciuto: " + token[0]); return; }
		
		if(a.nargs() == 0) {
			a.esegui().accept(this, null);
		} else {
			String[] args = elaboraArgomenti(token[1], a.nargs());
			if(args == null) { invia("args non validi: " + token[1]); return; }
			a.esegui().accept(this, args);
		}
	}
	
	private String[] elaboraArgomenti(String argomenti, int n) {
		if(n==0)
			return new String[] {argomenti};
		
		String[] args = argomenti.split(" ", n);
		if(args.length < n) return null;
		
		return args;
	}
	
	private String nome;
	private boolean login() throws IOException {
		invia("Inserisci il tuo nome");
		String nome;
		while((nome=in.readLine()) != null) {
			if(nome.length() < 4) {
				invia("il nome deve essere di almeno 4 caratteri...");
			} if(sessioni.containsKey(nome)) {
				invia("nome utente non disponibile");
			} else {
				break;
			}
		}
		this.nome = nome;
		return true;
	}
	
	private void log(String msg) {
		System.out.println(msg);
	}
		
	protected void chiudi() {
		sessioni.remove(nome);
		
		if(client == null) 
			return;
		
		try {
			in.close();
			out.close();
			client.close();
		} catch(IOException ex) {
			//
		}
		in = null;
		out = null;
		client = null;
		
	}

	public void invia(String msg) {
		out.println(msg);
		out.flush();
	}
	
	public void elencoComandi() {
		invia("\nElenco comandi utili:");
		for(String cmd: Protocollo.commandMap.keySet()) {
			invia(cmd);
		}	
	}

	public void elencoUtenti() {
		invia("\nElenco utenti:");
		for(String cmd: sessioni.keySet()) {
			invia(cmd);
		}
	}

	public void inviaUtente(String utente, String msg) {
		Sessione s = sessioni.get(utente);
		if(s == null) {
			invia("utente non trovato!");
			return;
		}
		
		s.invia(String.format("[%s] %s", nome, msg));
	}

	public void inviaTutti(String msg) {
		for(String utente: sessioni.keySet()) {
			if(nome.equals(utente))
				continue;
			inviaUtente(utente, msg);
		}
	}
	
}
