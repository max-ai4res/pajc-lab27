package it.unibs.pajc;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WhatsApp {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor();
		
		try(ServerSocket server = new ServerSocket(1234) ) {
			System.out.println("Server avviato...");
			
			while(true) {
				Socket client = server.accept();
				exec.submit(new Sessione(client));
			}
			
		} catch (IOException e) {
			System.out.println("Errore nel server");
			e.printStackTrace();
		}
		
		exec.close();
		
		System.out.println("Server chiuso!");
	}

}
