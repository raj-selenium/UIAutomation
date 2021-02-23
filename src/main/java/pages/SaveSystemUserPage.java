package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dataInfo.AddEditUserInfo;

public class SaveSystemUserPage extends Header {

	public SaveSystemUserPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="systemUser_userType")
	WebElement userRoleDD;
	
	@FindBy(id="systemUser_employeeName_empName")
	WebElement EmployeeName;
	
	@FindBy(id="systemUser_userName")
	WebElement userName;
	
	@FindBy(id="systemUser_status")
	WebElement statusDD;
	
	@FindBy(id="systemUser_password")
	WebElement password;
	
	@FindBy(id="systemUser_confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(id="btnSave")
	WebElement saveBtn;
	
	@FindBy(id="btnCancel")
	WebElement cancelBtn;
	
	@FindBy(css="input+span[for='systemUser_employeeName_empName']")
	WebElement errorEmployeeName;
	
	@FindBy(css="input+span[for='systemUser_userName']")
	WebElement errorUserName;
	
	@FindBy(css="input+span[for='systemUser_password']")
	WebElement errorPassword;
	
	@FindBy(css="input+span[for='systemUser_confirmPassword']")
	WebElement errorConfirmPassword;
	
	
	public void saveUser(AddEditUserInfo userInfo) {
		
	}

}
