package element;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AutoSuggestDD extends Element{
	private static final Logger logger = LogManager.getLogger(AutoSuggestDD.class.getName());
	private Element textBox = null;
	private Element selectElement = null;
	
	private By empNamelist = By.className("ac_results");
	
	public AutoSuggestDD(WebDriver driver, WebElement textBox) {
		super(driver);
		this.textBox = new Element(driver, textBox);
	}
	
	public void typeAndSelectElement(List<WebElement> selectElements, String fullText, String partText) {
		 
		if(this.textBox.isDisplayed()) {
			String text = partText.isEmpty() ? fullText: partText;
			this.textBox.inputText(text);
			logger.debug("Type And Select Element: text - "+ (text.isEmpty() ? "Empty":text));
			
			if(!fullText.isEmpty()) {
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions
						.visibilityOfElementLocated(empNamelist));
				selectElement = new Element(driver, Element.filterElementByText(selectElements, fullText));
				selectElement.click();
			}
		}
		else {
			logger.error("typeAndSelectElement: Element is not Visible");
		}
	}


}
