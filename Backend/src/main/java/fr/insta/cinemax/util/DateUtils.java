package fr.insta.cinemax.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public enum DateFormat {
		LONG,
		SHORT,
		SHORT_DASHED
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
			case SHORT_DASHED:
				dateFormat = "dd-MM-yy";
				break;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	public static String getDayOfWeekString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
		return simpleDateFormat.format(date);
	}

	public static Integer dayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Integer month(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static Integer year(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

}
