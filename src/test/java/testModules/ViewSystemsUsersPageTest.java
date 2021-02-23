package testModules;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import base.BaseTest;
import dataInfo.SearchUserInfo;
import dataProviders.ViewSystemUsersTestData;
import pages.Header;
import pages.LoginPage;
import pages.ViewSystemsUsersPage;
import utils.PropertyReader;


public class ViewSystemsUsersPageTest extends BaseTest {
	
	private static final Logger logger = LogManager.getLogger(ViewSystemsUsersPageTest.class.getName());
	PropertyReader prop = PropertyReader.getInstance("config.properties");
	
	String username = prop.getValue("username");
	String password = prop.getValue("password");
	String menu = prop.getValue("ViewSystemsUsersPage");
	
	@Test(dataProvider = "SearchUserTestData", dataProviderClass = ViewSystemUsersTestData.class)
	public void checkSearch(SearchUserInfo empInfo) { 
		logger.trace("---------->Started Check Search");
		boolean result = new ViewSystemsUsersPage(driver)
								.searchUser(empInfo)
								.verifyUserSearchResult(empInfo);
								
		new ViewSystemsUsersPage(driver).clickReset();
		
		Assert.assertTrue(result);
	}
	
	
	
	@BeforeClass
	public void navigateToSystemUsersPage() {
		
		System.out.println(username+", "+password+", "+ menu);
		new LoginPage(driver)
		  .load()
		  .login(username, password)
		  .goToPageByMenuNavigation(menu);
	}
	
	@AfterClass
	public void doLogout() {
		new Header(driver).logout();
	}

}
