package br.com.julianograciano.test.unitario;

import org.testng.annotations.Test;

import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testng.DataDriven;

/**
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestDataFromExcel {

	/**
	 * Validar leitura da planilha excel.
	 */
	@Test(priority = 1, dataProviderClass = DataDriven.class, dataProvider = "testDataExcel")
	public void testExcel(TestData massaDados) {

	}
}
