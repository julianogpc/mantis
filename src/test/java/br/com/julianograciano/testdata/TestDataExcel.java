package br.com.julianograciano.testdata;

import java.util.LinkedHashMap;

/**
 * Manipulação de massa de dados através de uma arquivo excel.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class TestDataExcel implements TestData {

	private LinkedHashMap<String, String> hm;

	public TestDataExcel() {
		hm = new LinkedHashMap<String, String>();
	}

	public TestDataExcel(String[][] arr) {
		this();
		for (int i = 0; i < arr[0].length; i++) {
			hm.put(arr[0][i], arr[1][i]);
		}
	}

	public String get(String key) {
		String value = hm.get(key);
		return value;
	}

	public void set(String key, String value) {
		hm.put(key, value);
	}

	public String toString() {
		return hm.toString();

	}
}
