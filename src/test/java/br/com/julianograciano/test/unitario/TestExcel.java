package br.com.julianograciano.test.unitario;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.julianograciano.testdata.Excel;

/**
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestExcel {

	/**
	 * Validar leitura da planilha excel.
	 */
	@Test(priority = 1)
	public void testExcel() {
		Excel excel = new Excel(new File("data/ST01.xlsx"));
		String data[] = null;
		int totalRows = excel.getTotalRows(0);
		for (int i = 0; i < totalRows; i++) {
			data = excel.getRowData(0, i);
			Assert.assertNotNull(data, "Não foram retornado dados da planilha excel.");
			for (int j = 0; j < data.length; j++) {
				System.out.print(data[j] + "\t");
			}
			System.out.println();
		}
	}
}
