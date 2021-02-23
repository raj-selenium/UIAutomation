package element;

import org.openqa.selenium.WebElement;



public interface IElement {
	
	public boolean isDisplayed();
	
	public WebElement waitForElementToBeVisible();
	
	public WebElement waitForElementToBeClickable();
	
	public void inputText(String text);
	
	public void click();
	
	public void mouseHover();
	
	public void mouseHoverAndClick();
	
	public String getText();
	
	public void clickAndWaitForPageReload();
}
