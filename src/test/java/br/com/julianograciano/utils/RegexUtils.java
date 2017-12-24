package br.com.julianograciano.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manipulação de expressões regulares.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class RegexUtils {

	/**
	 * Retornar a primeira ocorrência de um grupo.
	 * 
	 * @param value
	 * @param pattern
	 * @return firstGroupOcorrence
	 */
	public static String getFirstGroupOcorrence(String value, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(value);

		String firstGroupOcorrence = null;
		while (m.find()) {
			int count = m.groupCount();
			if (count > 0) {
				firstGroupOcorrence = m.group(1);
				break;
			}
		}
		return firstGroupOcorrence;
	}
}
