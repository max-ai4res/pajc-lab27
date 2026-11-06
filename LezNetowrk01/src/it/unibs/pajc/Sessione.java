package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Sessione implements Runnable {
	private Socket client;
	
	public Sessione(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
		System.out.println("Client connesso: ");// + client.getRemoteSocketAddress());
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
				client.getInputStream(), 
				StandardCharsets.UTF_8));
		
		PrintWriter out = new PrintWriter(
				client.getOutputStream(), true, 
				StandardCharsets.UTF_8);
		
		out.println("Benevenuto! scrivi qualcosa e rispondo...");
		
		String riga;
		while((riga = in.readLine()) != null) {
			System.out.println("MSG: " + riga);
			out.println(riga.toUpperCase());
			
			if("/exit".equals(riga))
				break;
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
