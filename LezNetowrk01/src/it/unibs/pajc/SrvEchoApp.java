package it.unibs.pajc;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SrvEchoApp {

	public static void main(String[] args) throws IOException {
		
		try (ServerSocket server = new ServerSocket(1234)) {
			System.out.println("Server avviato...");
			while(true) {
				Socket client = server.accept();
				new Thread(new Sessione(client)).start();
			}
			
			
		}
	}

}
