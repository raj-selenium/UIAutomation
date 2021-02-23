package element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchBox {
	
	private static final Logger log = LogManager.getLogger(SearchBox.class.getName());
	
	@FindBy(id="resetBtn")
	WebElement resetBtn;
	
	@FindBy(id="searchBtn")
	WebElement searchBtn;

	private WebDriver driver;
	
	public SearchBox(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickSearch() {
		new Element(driver, searchBtn).clickAndWaitForPageReload();
		log.info("Clicked Search Button");
	}

	
	public void clickReset() {
		new Element(driver, resetBtn).clickAndWaitForPageReload();
		log.info("Clicked Reset Button");
	}

	
}
