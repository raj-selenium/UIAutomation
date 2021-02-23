package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import element.Element;


public class LoginPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

	private final String URL = BASE_URL + "index.php/auth/login";

	// locators
	@FindBy(id = "txtUsername")
	private WebElement usernameTBox;

	@FindBy(id = "txtPassword")
	private WebElement passwordTBox;

	@FindBy(id = "btnLogin")
	private WebElement loginBtn;

	@FindBy(id = "spanMessage")
	private WebElement errorText;

	/**
	 * Constructor
	 * 
	 * @param driver - pass the driver as argument from the test case
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @description Loads the LoginPage, by using the get(URL)
	 * @return LoginPage
	 */
	public LoginPage load() {
		driver.get(BASE_URL);
		logger.info(String.format("Loading url: %s", BASE_URL));
		return new LoginPage(driver);
	}

	/**
	 * @description inputs username in the username textbox in the LoginPage
	 * @return LoginPage
	 */
	public LoginPage inputUserName(String username) {
		Element usernameBox = new Element(driver, usernameTBox);
		usernameBox.inputText(username);
		logger.info(String.format("input username: %s", username));
		return new LoginPage(driver);
	}

	/**
	 * @description inputs password in the password textbox in the LoginPage
	 * @return LoginPage
	 */
	public LoginPage inputPassword(String password) {
		Element passBox = new Element(driver, passwordTBox);
		passBox.inputText(password);
		logger.info(String.format("input password: %s", password));
		return new LoginPage(driver);
	}

	/**
	 * @description clicks Login button in the LoginPage
	 * @return LoginPage
	 */
	public LoginPage clickLoginBtn() {
		Element login = new Element(driver, loginBtn);
		login.click();
		logger.info("Clicked Login Button");
		return new LoginPage(driver);
	}

	/**
	 * @description inputs username, password and clicks Login button in the
	 *              LoginPage
	 * @return Header
	 */
	public Header login(String username, String password) {
		inputUserName(username);
		inputPassword(password);
		clickLoginBtn();
		return new Header(driver);
	}

	public String getURL() {
		return URL;
	}

	/**
	 * @description gets the error message in the LoginPage
	 * @return error Message as String
	 */
	public String getErrorMsg() {
		Element errorElement = new Element(driver, errorText);
		String errorMessage = errorElement.waitForElementToBeVisible().getText();
		logger.info(String.format("Error Message: %s", errorMessage));
		return errorMessage;
	}

}
