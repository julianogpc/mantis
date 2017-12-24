package br.com.julianograciano.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Minipulação de arquivos excel.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class Excel {

	private XSSFWorkbook workbook;

	public Excel(File file) {
		FileInputStream excelFile = null;
		try {
			excelFile = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getTotalRows(int index) {
		XSSFSheet sheet = workbook.getSheetAt(index);
		return sheet.getLastRowNum() + 1;
	}

	public int getTotalColumns(int index, int rownum) {
		XSSFSheet sheet = workbook.getSheetAt(index);
		return sheet.getRow(rownum).getLastCellNum();
	}

	public int getTotalSheets() {
		return workbook.getNumberOfSheets();
	}

	public String[] getRowData(int index, int rownum) {
		XSSFSheet sheet = workbook.getSheetAt(index);

		XSSFRow row = sheet.getRow(rownum);

		XSSFCell cell = null;
		int totalColumns = row.getLastCellNum();
		String[] data = new String[totalColumns];
		for (int i = 0; i < totalColumns; i++) {
			cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			data[i] = getValueFromCell(cell, "");
		}
		return data;
	}

	private String getValueFromCell(Cell cell, String dateFormat) {
		String text = null;

		switch (cell.getCellTypeEnum()) {
		case BLANK:
			text = String.valueOf(cell.getRichStringCellValue());
			break;
		case BOOLEAN:
			text = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			text = String.valueOf(cell.getErrorCellValue());
			break;
		case FORMULA:
			text = String.valueOf(cell.getRichStringCellValue());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				text = date.toString();
			} else {
				Double number = cell.getNumericCellValue();
				if (number.doubleValue() % 1 == 0 && number.doubleValue() <= Integer.MAX_VALUE)
					text = Integer.toString(number.intValue());
				else
					text = number.toString();
			}
			break;
		case STRING:
			text = cell.getStringCellValue();
			break;
		default:
			text = "";
			break;
		}

		return text;
	}

}
