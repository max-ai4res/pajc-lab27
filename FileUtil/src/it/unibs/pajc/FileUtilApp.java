package it.unibs.pajc;

import java.util.Map;

public class FileUtilApp {

	public static void main(String[] args) {
		String fname = "./src/it/unibs/pajc/FileUtilApp.java";
		
		SyntaxHighlighter java = new SyntaxHighlighter(
				Map.of("public", Ansi.BLUE, "static", Ansi.RED)
				);
		
		FileUtil.print(fname, (s, n) -> java.colora(s));
		
		
		//FileUtil.print(fname);
		
		//FileUtil.print(fname, (s, n) -> String.format("%04d  %s", n, s));
		/*
		FileUtil.print(fname, (s, n) -> {
			var snumber = String.format("%04d", n);
			snumber = n%2 == 0 ? Ansi.YELLOW.paint(snumber) :
				Ansi.RED.paint(snumber);
			return snumber + "  " + s;
		});
		*/
		
		//FileUtil.dump("./src/it/unibs/pajc/FileUtilApp.java");
		//printAsciiTable();
		
	}
	
	
	
	/*
	public static void printAsciiTable() {
		for(int i=0; i<=127; i++) {
			if(i % 16 == 0) {
				System.out.printf("\n%4X ", i);
			}
			
			System.out.printf("%c ", 
					(i > 32 & i < 127) ? i : '.');
		}
		
	}
 */
	
	/*
	 * 0000	00	01	02	03	04	05	...
	 * 0010	16  17	18	19	20	21	...
	 * 
	 * 
	 */
}
