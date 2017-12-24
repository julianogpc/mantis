package br.com.julianograciano.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Manipulação de datas.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class DateUtils {

	/**
	 * Transforma uma data em formato String em um objeto Date.
	 * 
	 * @param dateInString
	 * @param format
	 * @return data
	 */
	public static Date dateFromString(String dateInString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * Transforma um objeto Date em String formatada.
	 * 
	 * @param date
	 * @param format
	 * @return dateFormatInString
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(date);
	}
}
