package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.PropertyReader;

/**
 * @author Rajkamal
 * @description contains common methods needed for all the TestCase classes
 * @note BaseTest class should be extended by all the Test Classes
 *
 */
public class BaseTest {

	
	
	private static final Logger logger = LogManager.getLogger(BaseTest.class.getName());

	public WebDriver driver;
	protected String projectPath = System.getProperty("user.dir");
	protected PropertyReader prop = PropertyReader.getInstance("config.properties");
	
	protected String username = prop.getValue("username");
	protected String password = prop.getValue("password");


	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional("Chrome") String browser) {
		
		logger.info("------> @BeforeTest ------> Running setUp");
		
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		
		logger.info(String.format("Opening %s browser and maximizing it", browser));
	}
	

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		logger.info("------> @AfterTest ------> Running tearDown");
		
		driver.quit();
		
		logger.info("Closing the driver");
	}


	public void takeScreenShot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String fileName = "Snap_"+d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File destFile = new File(projectPath + "/screenshots/" + fileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
			logger.info(String.format("Screenshot taken: %s", destFile.getName()));
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
	}

}