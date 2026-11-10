package it.unibs.pajc;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Sessione implements Runnable {
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Azione> commandMap = new HashMap<String, Azione>();
	
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
			
			out.println("Benvenuto " + nome);
			
			String riga;
			while((riga=in.readLine())!=null) {
				log(riga);
				out.println(riga);
				
				if("/time".equals(riga))
					out.println(System.nanoTime());
				
				if("/exit".equals(riga))
					break;
			}
			
			log("client disconnesso");
		} catch(IOException e) {
			
		} finally {
			chiudi();
		}
	}
	
	private String nome;
	private boolean login() throws IOException {
		out.println("Inserisci il tuo nome");
		String nome;
		boolean loginOk = false;
		while((nome=in.readLine()) != null && !loginOk) {
			if(nome.length() < 4) {
				out.println("il nome deve essere di almeno 4 caratteri...");
				continue;
			}
				
			
			loginOk = true;
		}
		this.nome = nome;
		return true;
	}
	
	private void log(String msg) {
		System.out.println(msg);
	}
	
	
	protected void chiudi() {
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
	
}
