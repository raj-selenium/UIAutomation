package element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DropDown extends Element{
	private static final Logger log = LogManager.getLogger(DropDown.class.getName());

	
	public DropDown(WebDriver driver, WebElement dropDown) {
		super(driver, dropDown);
	}
	
	public void selectElementByText(String text) {
		if(isDisplayed()) {
			Select select = new Select(this.element);
			select.selectByVisibleText(text);
			log.debug("select Element By Text: text -" +(!text.isEmpty() ? text:"Empty"));
	
		}
		else {
			log.error("select Element By Text: text -" +(!text.isEmpty() ? text:"Empty"));
			log.debug("Element is not Visible");
			}
		}

}
