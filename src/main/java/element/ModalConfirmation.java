package element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ModalConfirmation extends Element{
	private static final Logger log = LogManager.getLogger(ModalConfirmation.class.getName());
	
	private By okbtn = By.cssSelector("[value='Ok']");
	private By cancelBtn = By.cssSelector("[value='Cancel']");
	
	public ModalConfirmation(WebDriver driver, WebElement modal) {
		super(driver, modal);
	}
	
	public void clickOk() {
		if(isDisplayed()) {	
			this.element.findElement(okbtn).click();
			log.debug("ModalConfirmation - clicked OK");
		}
		else log.debug("Modal is not Visible");
			
	}
	
	public void clickCancel() {
		if(isDisplayed()) {	
			this.element.findElement(cancelBtn).click();
			log.debug("ModalConfirmation - clicked Cancel");
		}
		else log.debug("Modal is not Visible");
			
	}

}
