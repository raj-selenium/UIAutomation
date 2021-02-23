package element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;




public class DatePicker extends Element{

	private static final Logger logger = LogManager.getLogger(DatePicker.class.getName());
	private By yearLoc = By.className("ui-datepicker-month");
	private By monthLoc = By.className("ui-datepicker-month");
	private By daysLoc = By.cssSelector("//td/a");
	
	public DatePicker(WebDriver driver, WebElement datePicker) {
		super(driver, datePicker);
	}

	public void selectDate(String year, String month, String day) {
		
		if(isDisplayed()) {
			DropDown yearDD = new DropDown(driver, this.element.findElement(yearLoc));
			yearDD.selectElementByText(year);
			DropDown monthDD = new DropDown(driver, this.element.findElement(monthLoc));
			monthDD.selectElementByText(month);
			filterElementByText(this.element.findElements(daysLoc), day).click();
			logger.debug("Entering date: ---> "+ month+" "+day+", "+year);
		}
		else {
			logger.error("DatePicker is not displayed to enter " + month+" "+day+", "+year);
		}
	}
	
}
