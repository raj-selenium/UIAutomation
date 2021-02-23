package base;


import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import element.Element;


public class BasePage implements Page {
	
	private static final Logger logger = LogManager.getLogger(BasePage.class.getName());
	
	protected final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	String userDir = System.getProperty("user.dir");
	public Element element;
	
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
	}
	
	@Override
	public boolean waitForUrlToBe(String url) {
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	
	
	@Override
	public boolean waitUntilUrlContains(String partOfUrl) {
		return wait.until(ExpectedConditions.urlContains(partOfUrl));
	}
	
	public boolean waitUntilPageLoadComplete() {
		return wait.until(driver ->
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}
	
	@Override
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void clickAndWaitForPageReload2(WebElement element) {
		int oldHash = driver.findElement(By.tagName("html")).hashCode();
		element.click();
		wait.until(new Function<WebDriver, Boolean>() {
			  			public Boolean apply(WebDriver driver) {
			  					return (driver.findElement(By.tagName("html")).hashCode() != oldHash)
			  							&& ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");}
				  });	
	}
	
	
	public void delay(long time) {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

