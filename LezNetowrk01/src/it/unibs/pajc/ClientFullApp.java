package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientFullApp {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {	
		
		String host = "www.unibs.it";//"localhost";
		int porta = 80;//1234;
		
		try (Socket server = new Socket(host, porta)) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					server.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter out = new PrintWriter(
					server.getOutputStream(), true, StandardCharsets.UTF_8);
			
			BufferedReader tastiera = new BufferedReader(
					new InputStreamReader(
					System.in));
			
			ExecutorService exec = Executors.newFixedThreadPool(2);
			
			Future<?> lettore = exec.submit(() -> {
				String riga;
				while((riga = in.readLine()) != null)
					System.out.println(riga);
				return null;
			});
			
			Future<?> scrittore = exec.submit(() -> {
				String riga;
				while((riga = tastiera.readLine()) != null)
					out.println(riga);
				return null;
			});
			
			lettore.get();
				
		}

		System.out.print("connessione chiusa");
	}
}
