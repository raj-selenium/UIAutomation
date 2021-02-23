package testModules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import base.Page;
import dataProviders.MenuNavigationTestData;
import pages.Header;
import pages.LoginPage;



public class HeaderTest extends BaseTest{
	
	private static final Logger logger = LogManager.getLogger(HeaderTest.class.getName());
	
	@Test(dependsOnMethods = {"menuNavigationTest"})
	public void logoutTest() {

		LoginPage loginPage = new Header(driver).logout();
		Assert.assertTrue(loginPage.waitForUrlToBe(loginPage.getURL()));
	}
	
	@Test(dataProvider = "menuNavigationTestData", dataProviderClass = MenuNavigationTestData.class )
	public void menuNavigationTest(String menuToNavigate, String expectedPartOfUrl) {

		Page page = new Header(driver).goToPageByMenuNavigation(menuToNavigate);
		Assert.assertTrue(page.waitUntilUrlContains(expectedPartOfUrl));			 								   	
	}
	
	@BeforeClass
	public void login() {
		
		logger.info("BeforClass - Login");
		new LoginPage(driver).load()
				 		     .login(username, password);
	}
	

}
