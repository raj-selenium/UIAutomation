package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import base.Page;
import element.Element;

public class Header extends BasePage{
	private static final Logger logger = LogManager.getLogger(Header.class.getName());
	
	@FindBy(id="welcome")
	WebElement welcomeText;
	
	@FindBy(css="#welcome-menu>ul>li:last-child>a")
	WebElement logoutLink;

	@FindBy(className = "firstLevelMenu")
	List<WebElement> firstLevelMenus;

	@FindBy(css = ".firstLevelMenu+ul>li")
	List<WebElement> secondLevelMenus;

	@FindBy(css = ".firstLevelMenu+ul>li>ul>li")
	List<WebElement> thirdLevelMenus;

	public Header(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getWelcomeMsg() {
		Element element = new Element(driver, welcomeText);
		String welcomeMessage = element.waitForElementToBeVisible().getText();
		logger.info(String.format("Welcome Message: %s", welcomeMessage));
		return welcomeMessage;

	}

	public LoginPage logout() {
		Element welcomeElement = new Element(driver, welcomeText);
		welcomeElement.click();
		Element logoutElement = new Element(driver, logoutLink);
		logoutElement.click();
		return new LoginPage(driver);
	}

	/**
	 * @description Using this method the code can navigate to each page
	 * @param menus - as a string separated by comma and a space 
	 * 		  e.g: "Admin, User Management, Users"
	 * @return BasePage
	 */
	public Page goToPageByMenuNavigation(String menus) {

		// Splitting the menu and Converting the result String[] into a String List
		List<String> menuList = Arrays.asList(menus.split(", "));

		// allMenus - list of eachLevels of menu items
		List<List<WebElement>> allMenus = new ArrayList<List<WebElement>>(
				Arrays.asList(firstLevelMenus, secondLevelMenus, thirdLevelMenus));

		int len = menuList.size();

		for (int i = 0; i < len - 1; i++) {
			// The menu_element will be filtered from all the menus by the given menu_text
			// and then will move to the element
			Element menu = new Element(driver, Element.filterElementByText(allMenus.get(i), menuList.get(i)));
			menu.mouseHover();
		}

		Element lastMenu = new Element(driver, Element.filterElementByText(allMenus.get(len - 1), menuList.get(len - 1)));
		lastMenu.mouseHoverAndClick();


		return pageObject(menus);
	}

	public Page pageObject(String page) {
		Page newPage;
		switch(page) {
			case "Admin, User Management, Users": newPage =  new ViewSystemsUsersPage(driver); break;
			default: newPage = new BasePage(driver);
		
		} 
		return newPage;
	}

}
