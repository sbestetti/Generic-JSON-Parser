package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateParser {
	
	public static Date parse(String input) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date(0);
		}
	}

}
