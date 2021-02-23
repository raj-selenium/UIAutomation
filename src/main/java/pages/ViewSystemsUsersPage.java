package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataInfo.SearchUserInfo;
import element.AutoSuggestDD;
import element.DropDown;
import element.Element;
import element.ModalConfirmation;
import element.SearchBox;
import element.WebTable;


public class ViewSystemsUsersPage extends Header {
	
	//---------------------------------------------LOGGER------------------------------------------
	
	private static final Logger logger = LogManager.getLogger(ViewSystemsUsersPage.class.getName());
	
	//--------------------------------------------LOCATORS-----------------------------------------

	//-----------------------------LOCATORS - RELATED TO ADD, DELETE, EDIT-------------------------
	@FindBy(id = "btnAdd")
	private WebElement addBtn;

	@FindBy(id = "btnDelete")
	private WebElement deleteBtn;
	
	@FindBy(id = "resultTable")
	private WebElement resultTable;

	
	//--------------------------------------LOCATOR - MODAL_WINDOW---------------------------------
	@FindBy(id = "deleteConfModal")
	private WebElement modalConfirmWin;
	
	
	//------------------------------------LOCATORS - RELATED TO SEARCH-----------------------------
	@FindBy(id = "searchSystemUser_userName")
	private WebElement userNameTBox;

	@FindBy(id = "searchSystemUser_userType")
	private WebElement userRoleDD;

	@FindBy(id = "searchSystemUser_employeeName_empName")
	private WebElement employeeNameTBox;

	@FindBy(css = ".ac_results>ul>li")
	private List<WebElement> employeeIDs;

	@FindBy(id = "searchSystemUser_status")
	private WebElement employeeStatusDD;

	//----------------------------------------------ELEMENTS---------------------------------------
	private SearchBox searchBox = null;
	private Element uNameBox = null;
	private DropDown uRoleDD = null;
	private AutoSuggestDD empNameASDD = null;
	private DropDown statusDD = null;
	private WebTable table = null;
	
	//---------------------------------------------CONSTRUCTOR-------------------------------------
	public ViewSystemsUsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.searchBox = new SearchBox(driver);
		this.uNameBox = new Element(driver, userNameTBox);
		this.uRoleDD = new DropDown(driver, userRoleDD);
		this.empNameASDD = new AutoSuggestDD(driver, employeeNameTBox);
		this.statusDD = new DropDown(driver, employeeStatusDD);
		this.table = new WebTable(driver);
	}
	
	
	//---------------------------------------------METHODS-----------------------------------------
	
	//----------------------------------------SEARCH BOX METHODS-----------------------------------
	
	// INPUT USERNAME IN USERNAME TEXTBOX
	public ViewSystemsUsersPage inputUserName(String userName) {
		uNameBox.inputText(userName);
		return this;
	}
	
	// SELECTS USER ROLE FROM DROPDOWN
	public ViewSystemsUsersPage selectUserRole(String userRole) {
		uRoleDD.selectElementByText(userRole);
		return this;
	}
	
	
	// SELECTS EMPLOYEE NAME FROM AUTO SUGGEST DROPDOWN
	public ViewSystemsUsersPage selectEmployeeName(String employeeNameFull, String employeeNamePart) {
		empNameASDD.typeAndSelectElement(employeeIDs, employeeNameFull, employeeNamePart);
		return this;
	} 
	
	// SELECTS EMPLOYEE STATUS FROM DROPDOWN
	public ViewSystemsUsersPage selectEmployeeStatus(String status) {
		statusDD.selectElementByText(status);
		return this;
	}
	
	// CLICK SEARCH BUTTON
	public ViewSystemsUsersPage clickSearch() {
		searchBox.clickSearch();
		return this;
	}

	// CLICK RESET BUTTON
	public ViewSystemsUsersPage clickReset() {
		searchBox.clickReset();
		return this;
	}
	
	// SEARCHES EMPLOYEES BASED ON THE EMPLOYEEINFO
	public ViewSystemsUsersPage searchUser(SearchUserInfo empInfo) {

		inputUserName(empInfo.getUserName());
		
		
		String userRole = empInfo.getUserRole();
		if(userRole != null && userRole.length()>0) {
			selectUserRole(userRole);
		}
		else selectUserRole("All");
	
		
		selectEmployeeName(empInfo.getEmpNameFull(), empInfo.getEmpNamePart());
		
		String status = empInfo.getEmpStatus();
		if(status != null && status.length()>0) {
			selectEmployeeStatus(status);
		}
		else selectEmployeeStatus("All");
		
		clickSearch();
		logger.info("searchUser: "+ empInfo.toString());
		return this;
	}
	
	//----------------------------------------DELETE METHODS--------------------------------------
	
	// CLICK DELETE BUTTON
	public ViewSystemsUsersPage clickDelete() {
		deleteBtn.click();
		logger.info("Clicks Delete button");
		return this;
	}
	
	// CLICK THE CHECKBOX OF USER ROW TO DELETE
	public ViewSystemsUsersPage clickCheckBoxByUser(String userName) {
		table.clickCheckBoxOfRowByText("Username", userName);
		logger.info("Clicks checkbox by user: "+userName);
		return this;
	}
	
	
	// DELETE A USER BY USERNAME
	public ViewSystemsUsersPage deleteUser(String userName){
		clickCheckBoxByUser(userName).clickDelete();
		return this;
	}
	
	
	// DELETE MULTIPLE USERS BY GIVEN LIST OF USERNAMES
	public ViewSystemsUsersPage deleteMultipleUsers(List<String> userNames){
		for(String userName: userNames)
			clickCheckBoxByUser(userName);
		clickDelete();
		return this;
	}
	
	
	//----------------------------------------MODALCONFIRM METHODS--------------------------------

	// CLICKS OK IN MODAL CONFIRMATION DIALOG TO DELETE
	public ViewSystemsUsersPage clickDeleteOk() {
		new ModalConfirmation(driver, modalConfirmWin).clickOk();
		return this;
	}
	
	
	// CLICKS CANCEL IN MODAL CONFIRMATION DIALOG TO CANCEL DELETE
	public ViewSystemsUsersPage clickDeleteCancel() {
		new ModalConfirmation(driver, modalConfirmWin).clickCancel();
		return this;
	}
	
	
	//----------------------------------------EDIT USER METHODS-----------------------------------
	
	// CLICKS THE USER VALUE IN USERNAME COLUMN AND RETURN EDIT USER PAGE
	public void clickUserToEdit(String userName) {
		//TODO - return Edit system user Page
		table.clickCellByText("Username", userName);
	}
	
	//-----------------------------------------ADD USER METHODS-----------------------------------
	
	// CLICKS ADD BUTTON AND RETURNS ADD USER PAGE
	public void clickAdd() {
		//TODO - return Add system user Page
		addBtn.click();
	}
	
	
	//----------------------------------------VERIFICATION METHOD---------------------------------
	
	// VERIFIES THAT THE SERACH MATCHES WITH THE RESULT TABLE DATA
	public boolean verifyUserSearchResult(SearchUserInfo empInfo) {
		
		boolean result = true;
		if(table.noOfRows() != 0) { 
			boolean uNameMatches = table.verifyValuesInRowsMatches(2, empInfo.getUserName());
			boolean uRoleMatches = table.verifyValuesInRowsMatches(3, empInfo.getUserRole());
			boolean empNameMatches = table.verifyValuesInRowsMatches(4, empInfo.getEmpNameFull());
			boolean empStatusMatches = table.verifyValuesInRowsMatches(5, empInfo.getEmpStatus());
		
			result = uNameMatches && uRoleMatches && empNameMatches && empStatusMatches;	
		}
		logger.info("verifyUserSearchResult: rows -> "+table.noOfRows()+" result ->"+ result);
		return result;
	}

}
