package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Logger {
		
	public static void log(String msg) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current = new Date();
		String fullMessage = df.format(new Date()) + " - " + msg;
		
		try {
			FileWriter fw = new FileWriter("log.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(fullMessage);
			bw.close();
			System.out.println(fullMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}

}