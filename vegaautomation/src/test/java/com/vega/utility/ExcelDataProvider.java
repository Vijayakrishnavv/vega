package com.vega.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;

	public ExcelDataProvider()
	{
		File f = new File("./Testdata/Vega_TestData.xlsx");
		try
		{
			FileInputStream fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);


		}
		catch(Exception e)
		{
			System.out.println("Something wrong with file..." +e.getMessage());
		}
	}



	public String getStringData(int sheetIndex, int row, int column) {

		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();

	}

	public String getStringData(String sheetName, int row, int column) {

		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();

	}

	public double getNumericData(String sheetName, int row, int column) {

		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();

	}

	public double getNumericData(int sheetIndex, int row, int column) {

		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getNumericCellValue();

	}
	
	public int getIntegerData(String sheetName, int row, int column) {

		return (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();

	}

	public int getIntegerData(int sheetIndex, int row, int column) {

		return (int) wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getNumericCellValue();

	}

	
}
