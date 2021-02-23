package element;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Element implements IElement {
	
	//--------------------------------------FIELDS-----------------------------------------

	protected WebDriver driver = null;
	protected WebElement element = null;
	
	private static final Logger logger = LogManager.getLogger(Element.class.getName());

	//------------------------------------CONSTRUCTORS-------------------------------------
	
	public Element(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
	}
	
	public Element(WebDriver driver) {
		this.driver = driver;
	}
	
	//-------------------------------------METHODS-----------------------------------------
	
	// WAIT UNTIL THE ELEMENT IS VISIBLE AND RETURN TRUE IF DISPLAYED 
	public boolean isDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			this.element = wait.until(ExpectedConditions.visibilityOf(this.element));
		} catch(TimeoutException ex) {
			logger.error("Element --> isDisplayed: "+ex.getMessage());
		}
		boolean displayed = this.element.isDisplayed();
		logger.debug("isDisplayed: "+ displayed);
		return displayed;
	}	
	
	// FILTER AN ELEMENT FROM GIVEN ELEMENTS BASED ON THE GIVEN TEXT
	public static WebElement filterElementByText(List<WebElement> elements, String text) {
		
		WebElement _element = null;
		try {
			if(elements.size() == 1)
				_element = elements.get(0);
			else if(elements.size() > 1)	
				_element = filterElementsByText(elements, text).get(0);	
		}
		catch(IndexOutOfBoundsException ex) {
			logger.error("filterElementByText: "+ex.getMessage());
		}
		logger.debug("filterElementByText: element is " 
				+ ((_element == null) ? "null":_element.getTagName()) + " and text is "+text);
		return _element;
	}
	
	
	// FILTER ALL ELEMENTS THAT MATCH THE GIVEN TEXT FROM GIVEN ELEMENTS
	public static List<WebElement> filterElementsByText(List<WebElement> elements, String text) {
		List<WebElement> _elements = new ArrayList<WebElement>();
		try {
			for(WebElement e: elements) {
				if(e.getText().equals(text)) {
					_elements.add(e);
					logger.debug("e text->" +e.getText());
				}
			}
		}
		catch(Exception ex) {
			logger.error("filterElementsByText: "+ex.getMessage());
		}
		logger.debug("filterElementsByText: input elements- "+elements.size()+ " filtered: "+_elements.size());
		return _elements;
	}
	
	
	// GIVES THE INDEX OF ELEMENT THAT MATCH THE GIVEN TEXT FROM GIVEN ELEMENTS
	public static int getElementIndexByText(List<WebElement> elements, String text) {
		int position = -1;
		for(int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getText().equals(text)) {
				position = i;
				break;
			}
		}
		logger.debug("getElementIndexByText: input elements- "+elements.size()
				+ " filtered index position: "+position);
		return position;
	}
	
	// WAIT FOR THE ELEMENT TO BE VISIBLE
	public WebElement waitForElementToBeVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		try {
			wait.until(ExpectedConditions.visibilityOf(this.element));
		}catch (TimeoutException ex) {
			logger.error("waitForElementToBeVisible: "+ ex.getMessage());
		}
		logger.debug("waitForElementToBeVisible: "+ this.element.isEnabled());
		return this.element;
	}

	
	// WAIT FOR THE ELEMENT TO BE CLICKABLE
	public WebElement waitForElementToBeClickable() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(this.element));
		}catch (TimeoutException ex) {
			logger.error("waitForElementToBeClickable: "+ ex.getMessage());
		}
		logger.debug("waitForElementToBeVisible: "+ this.element.isEnabled());
		return this.element;
	}
	
	// CLEARS AND INPUTS THE GIVEN TEXT INSIDE A TEXTBOX
	public void inputText(String text) {
		waitForElementToBeVisible();
		this.element.clear();
		this.element.sendKeys(text);
		logger.debug("inputText: "+ text);
	}
	
	// WAIT FOR THE ELEMENT TO BE CLICKABLE AND CLICKS IT
	public void click() {
		waitForElementToBeClickable().click();
	}
	
	// MOVES THE MOUSE ON THE ELEMENT
	public void mouseHover() {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.element).build().perform();
		logger.debug("mouseHover on element: tagname - " + this.element.getTagName() 
				+ ", text - "+this.element.getText());
	}
	
	// MOVES THE MOUSE AND LEFT CLICKS ON THE ELEMENT
	public void mouseHoverAndClick() {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.element).click().build().perform();
		logger.debug("mouseHover and click on element: tagname - " + this.element.getTagName() 
		+ ", text - "+this.element.getText());
	}
	
	// GETS THE TEXT OF THE ELEMENT AFTER THE ELEMENT IS VISIBLE
	public String getText() {
		String text = waitForElementToBeVisible().getText();
		logger.debug("getText: text- "+text);
		return text;
	}
	
	// CLICKS AND WAITS FOR THE PAGE TO RELOAD
	public void clickAndWaitForPageReload() {
		int oldHash = element.hashCode();
		element.click();
		
		logger.debug("oldHash: "+oldHash+", newHash: "+element.hashCode());
		delay(2000);

	}
	
	public void delay(long time) {
		
		try {
			Thread.sleep(time);
			logger.debug("delay time: milliseconds- "+time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
