package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateParser {
	
	public static Date parse(String input, String title) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(input);
		} catch (ParseException e) {
			Logger.log("WARNING: No release date available for game " + title);
			return new Date(0);
		}
	}

}
