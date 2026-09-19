package it.unibs.pajc;

public class FileUtilApp {

	public static void main(String[] args) {
		FileUtil.dump("./bin/it/unibs/pajc/FileUtilApp.class");
		//printAsciiTable();
		
	}
	
	public static void printAsciiTable() {
		for(int i=0; i<=127; i++) {
			if(i % 16 == 0) {
				System.out.printf("\n%4X ", i);
			}
			
			System.out.printf("%c ", 
					(i > 32 & i < 127) ? i : '.');
		}
		
	}

	
	/*
	 * 0000	00	01	02	03	04	05	...
	 * 0010	16  17	18	19	20	21	...
	 * 
	 * 
	 */
}
