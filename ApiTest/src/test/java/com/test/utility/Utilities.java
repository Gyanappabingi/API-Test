package com.test.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utilities {

	
	public static String path="C:\\Users\\VGSL-SW010\\eclipse-workspace\\ApiTest\\src\\test\\java\\com\\test\\testdata\\testdatas.xlsx";
	
	public static Object[][] getExcelData(String sheetname) throws IOException{
		FileInputStream file=new FileInputStream(path);
		Workbook book=WorkbookFactory.create(file);
		Sheet sheet=book.getSheet(sheetname);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];	
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static Object[][] getusername() throws IOException{
		FileInputStream file=new FileInputStream(path);
		Workbook book=WorkbookFactory.create(file);
		Sheet sheet=book.getSheetAt(0);
		int rowcount=sheet.getLastRowNum();
		Object[][] data=new Object[rowcount][1];
		for(int i=0;i<rowcount;i++) {
			Row row=sheet.getRow(i+1);
			Cell cell=row.getCell(1);
			if(cell!=null) {
				String value=cell.getStringCellValue();
				data[i][0]=value;
			}
			
		}
		book.close();
		return data;
		
				
	}
}
