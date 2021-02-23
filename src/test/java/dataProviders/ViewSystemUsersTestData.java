package dataProviders;

import org.testng.annotations.DataProvider;

import dataInfo.SearchUserInfo;
import utils.ExcelUtil;

public class ViewSystemUsersTestData {
	
	@DataProvider(name="SearchUserTestData")
	public Object[][] searchUserTestData(){
		return searchUserTestData("SearchUserTD");
	}
	
	public Object[][] searchUserTestData(String sheetName){
		ExcelUtil excel = ExcelUtil.getInstance("TestData.xlsx", sheetName);
		
		Object [][] searchUserData = new Object[excel.getMaxRows()][1];
		
		System.out.println(excel.getMaxRows());
		
		for(int i = 0; i < excel.getMaxRows(); i++) {
			String uName = excel.getCellValue(i+1,0);
			String uRole = excel.getCellValue(i+1,1);
			String empNameFull = excel.getCellValue(i+1,2);
			String empNamePart = excel.getCellValue(i+1,3);
			String empStatus = excel.getCellValue(i+1,4);
			
			searchUserData[i][0] = new SearchUserInfo(uName, uRole, empNameFull, empNamePart, empStatus);
		}
		excel.closeWB();
		
		return searchUserData;
	}

}
