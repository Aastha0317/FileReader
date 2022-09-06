package FileReading.FileReading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	static String content[][] = null;
	static FileInputStream file = null;
	static XSSFWorkbook workbook = null;
	static XSSFSheet sheet = null;

	public static String[][] Read_Excel(String fileName, String sheetName) {
		try {
			file = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			int rowNum = sheet.getLastRowNum();
			workbook.close();
			content = Read_Excel(fileName, sheetName, rowNum);
			return content;
		} catch (FileNotFoundException e) {
			throw new FrameworkException("File " + fileName + " not found for reading.");
		} catch (IOException e) {
			throw new FrameworkException("Exception occured while reading " + fileName);
		} catch (Exception e) {
			throw new FrameworkException("Unknown Exception while reading " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}
	}

	public static String[][] Read_Excel(String fileName, String sheetName, int rowNum) {
		try {
			String content[][] = null;
			FileInputStream file = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet = null;
			int colNum = 0;

			file = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			colNum = sheet.getRow(0).getLastCellNum();
			content = new String[rowNum][colNum];

			for (int i = 0; i < rowNum; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				for (int j = 0; j < colNum; j++) {
					XSSFCell cell = row.getCell(j);
					String value;
					if (cell != null) {
						value = cell.getStringCellValue();
						content[i][j] = value;
					} else {
						content[i][j] = "";
					}
				}
			}

			workbook.close();
			return content;
		} catch (FileNotFoundException e) {
			throw new FrameworkException("File " + fileName + " not found for reading.");
		} catch (IOException e) {
			throw new FrameworkException("Exception occured while reading " + fileName);
		} catch (Exception e) {
			throw new FrameworkException("Unknown Exception while reading " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}

	}
}
