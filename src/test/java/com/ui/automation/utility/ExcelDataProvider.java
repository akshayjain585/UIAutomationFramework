package com.ui.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;

	public ExcelDataProvider() throws IOException {

		//Create the object of File class and provide the file path since File class should have the parameterised constructor with filepath as an arguement. 
		File src = new File("./TestData/TestData.xlsx"); 		
		
		//Need a FileInputStream class which converts the excel file into raw text
		FileInputStream fis = new FileInputStream(src);

		//Need XSSFWorkbook class to read xlsx file
		wb = new XSSFWorkbook(fis);				
	}

	public String getStringData(String sheetName, int rowNum, int colNum) {
		return wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
	}

	//Method overloading with the above method as using different datatype in arguments with same number of arguments and same method name.
	public String getStringData(int sheetIndex, int rowNum, int colNum) {
		return wb.getSheetAt(sheetIndex).getRow(rowNum).getCell(colNum).getStringCellValue();
	}

}
