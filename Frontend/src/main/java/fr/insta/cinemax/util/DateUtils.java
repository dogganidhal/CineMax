package fr.insta.cinemax.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public enum DateFormat {
		LONG,
		SHORT
	}

	public static String formatDate(Date date, DateFormat format) {
		String dateFormat = "";
		switch (format) {
			case LONG:
				dateFormat = "EEEE dd/MM/yyyy HH'h'mm";
				break;
			case SHORT:
				dateFormat = "dd/MM/yyyy";
				break;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

}
