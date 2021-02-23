package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class ExcelUtil {
	
	private static String path = System.getProperty("user.dir")+"/src/test/resources/data/";
	private Workbook workbook;
	private Sheet sheet;
	
	
	private ExcelUtil(Workbook wb, Sheet sh) {
		this.workbook = wb;
		this.sheet = sh;
	}
	
	public static ExcelUtil getInstance(String workbookName, String sheetName){
		
		File file = new File(path+workbookName);
		FileInputStream fis = null;
		Workbook wb = null;
		Sheet sh = null;
		
		try {
			fis = new FileInputStream(file);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return new ExcelUtil(wb, sh);

	}
	
	public int getMaxRows() {
		if(this.sheet!=null)
			return this.sheet.getLastRowNum();
		else
			return 0;
	}
	
	public int getMaxCols() {
		if(this.sheet!=null)
			return this.sheet.getRow(0).getLastCellNum();
		else
			return 0;
	}
	
	public String getCellValue(int row, int col) {
		if(getMaxRows()>=row && getMaxCols()>=col) {
			System.out.println((row)+","+col+"-->"+this.sheet.getRow(row).getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
			return this.sheet.getRow(row).getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
		}else
			return "Invalid cell";
	}
	
	public Object[][] getAllCellValues(){
		// Find number of rows in excel file
		int rowCount = this.getMaxRows();
		int colCount = this.getMaxCols();
				
		Object[][] data = new String[rowCount][colCount];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount; i++) {
			Row row = this.sheet.getRow(i+1);
			for (int j = 0; j < colCount; j++) {
				
				data[i][j] = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
				System.out.println((i)+","+j+"-->"+data[i][j]);
			}
		}
		
		return data;
	}
	
	public void closeWB(){
		try {
			this.workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
	