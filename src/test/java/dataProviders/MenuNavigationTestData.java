package dataProviders;

import org.testng.annotations.DataProvider;

import utils.ExcelUtil;

public class MenuNavigationTestData {
	
	@DataProvider
	public Object[][] menuNavigationTestData(){
		return ExcelUtil.getInstance("TestData.xlsx", "MenuNavigationTD").getAllCellValues();
	}
}
