package it.unibs.pajc;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.util.function.Consumer;

public class FileUtil {
	public static void dump(String fname) {
		int i = 0;
		StringBuilder ascii = new StringBuilder();
		try(
				DataInputStream in = new DataInputStream(
					new BufferedInputStream(
					new FileInputStream(fname)));
		) {
			while(true) {
				if(i % 16 == 0) {
					System.out.printf(" %s\n%04X ", ascii.toString(), i);
					ascii.setLength(0);
				}
				
				byte b = in.readByte();
				System.out.printf("%02X ", b);
				ascii.append((b >= 32 && b < 127) ? (char)b : '.');
				i++;
			}
			
			
		} catch(EOFException eof) {
			
			int missing = (16 - i % 16);
			for(int k=0; k<missing; k++) {
				System.out.print("   ");
			}
			
			System.out.printf(" %s", ascii);
 			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
