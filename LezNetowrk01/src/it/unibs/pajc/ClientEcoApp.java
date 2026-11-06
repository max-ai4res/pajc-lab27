package it.unibs.pajc;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientEcoApp {

	public static void main(String[] args) throws IOException {	
		String host = "localhost";
		int porta = 1234;
		
		try (Socket server = new Socket(host, porta)) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					server.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter out = new PrintWriter(
					server.getOutputStream(), true, StandardCharsets.UTF_8);
			
			BufferedReader tastiera = new BufferedReader(
					new InputStreamReader(
					System.in));
			
			System.out.println(in.readLine());
			String riga;
			while((riga = tastiera.readLine()) != null) {
				out.println(riga);
				String risposta = in.readLine();
				if(risposta == null) break;
				System.out.println("> " + risposta);
				if("/exit".equals(riga)) break;
			}
			
		}

	}

}
