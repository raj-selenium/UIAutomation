package testModules;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataInfo.LoginInfo;
import dataProviders.LoginTestData;
import pages.LoginPage;


public class LoginTest extends BaseTest{
	private static final Logger logger = LogManager.getLogger(LoginTest.class.getName());

	@Test(priority = 2, dataProvider = "loginPassTestData", dataProviderClass = LoginTestData.class)
	public void loginPassTest(LoginInfo data) {
		String actualWelcomeText = new LoginPage(driver).load()
														.login(data.getUsername(), data.getPassword())
														.getWelcomeMsg();
		logger.info(data.toString());		
		Assert.assertTrue(actualWelcomeText.startsWith(data.getExpected()));
	}
	
	
	@Test(priority = 1, dataProvider = "loginFailTestData", dataProviderClass = LoginTestData.class)
	public void loginFailTest(LoginInfo data) {
		
		String actualErrorMsg = new LoginPage(driver).load()
				 								   	 .inputUserName(data.getUsername())
				 								   	 .inputPassword(data.getPassword())
				 								   	 .clickLoginBtn()
				 								   	 .getErrorMsg();
		
		logger.info(data.toString());	
		Assert.assertTrue(actualErrorMsg.equals(data.getExpected()));
	}
	

}
