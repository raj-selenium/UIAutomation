package dataProviders;

import org.testng.annotations.DataProvider;

import dataInfo.LoginInfo;
import utils.ExcelUtil;

public class LoginTestData {

	
	@DataProvider(name = "loginPassTestData")
	public Object[][] loginPassTestData() {
		
		return loginTestData("LoginPassTD");
	}
	
	@DataProvider(name = "loginFailTestData")
	public Object[][] loginFailTestData() {
		
		return loginTestData("LoginFailTD");		
	}
	
	
	public Object[][] loginTestData(String sheetName){
		ExcelUtil excel = ExcelUtil.getInstance("TestData.xlsx", sheetName);
		
		Object [][] loginData = new Object[excel.getMaxRows()][1];
		
		System.out.println(excel.getMaxRows());
		
		for(int i = 0; i < excel.getMaxRows(); i++) {
			String uname = excel.getCellValue(i+1,0);
			String pass = excel.getCellValue(i+1,1);
			String expected = excel.getCellValue(i+1,2);
			
			loginData[i][0] = new LoginInfo(uname, pass, expected);
		}
		excel.closeWB();
		
		return loginData;
	}

}
