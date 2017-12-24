package br.com.julianograciano.testng;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import br.com.julianograciano.testdata.Excel;
import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testdata.TestDataExcel;

/**
 * 
 * Provém massa de dados para um método de teste.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class DataDriven {

	/**
	 * Massa de dados de um arquivo excel.
	 * 
	 * @param context
	 * @param method
	 * @return testData
	 * @throws Exception
	 */
	@DataProvider
	public static Object[][] testDataExcel(ITestContext context, Method method) throws Exception {
		Object[][] testArray = null;

		// Parâmetro testData obtido do XML do TestNG
		File file = new File(context.getCurrentXmlTest().getParameter("testData"));

		// Se o arquivo não exister cria retonar o data provider com apenas um objeto para teste 
		if (file.exists()) {
			testArray = getObjectArray(file);
		} else {
			testArray = new Object[][] { { new TestDataExcel() } };
		}

		return testArray;
	}

	private static Object[][] getObjectArray(File file) {
		int index = 0;

		Excel excel = new Excel(file);

		String keys[] = excel.getRowData(index, 0);

		int totalRows = excel.getTotalRows(index);

		Object[][] testArray = new Object[totalRows - 1][1];

		for (int i = 1; i < totalRows; i++) {
			String values[] = excel.getRowData(index, i);

			String[][] arr = merge(keys, values);
			TestData testData = new TestDataExcel(arr);

			testArray[i - 1][0] = testData;
		}

		return testArray;

	}

	private static String[][] merge(String keys[], String values[]) {
		String arr[][] = new String[2][keys.length];
		for (int i = 0; i < keys.length; i++) {
			arr[0][i] = keys[i];
			arr[1][i] = values[i];
		}
		return arr;
	}
}
