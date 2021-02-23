package element;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTable extends Element{
	
	//-----------------------------------------WEBTABLE LOCATORS---------------------------------
	
	@FindAll({
		@FindBy(id="resultTable"),
		@FindBy(xpath="//table")
	})
	WebElement table;
	
	@FindBy(css="#resultTable tbody>tr")
	List<WebElement> rows;
	
	@FindBy(css="#resultTable>thead>tr>th")
	List<WebElement> columns;
	
	@FindBy(css="#resultTable>tbody>tr:nth-of-type(1)>td")
	List<WebElement> cells;
	
	@FindBy(css="#resultTable th>input[type='checkbox']")
	WebElement  headerCheckBox;
	
	@FindBy(css="#resultTable td>input[type='checkbox']")
	List<WebElement> allRowCheckBoxes;
	
	
	String cellsByColumnIndex = "tbody>tr>td:nth-of-type(%d)";
	
	
	//--------------------------------------------CONSTRUCTOR-------------------------------------
	
	public WebTable(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	//----------------------------------------------METHODS---------------------------------------
	
	// RETURN NO OF ROWS IN A TABLE
	
	public int noOfRows() {
		int _rows = rows.size();
		if(_rows == 1) {
			_rows = rows.get(0).getText().equals("No Records Found") ? 0:_rows;
		}
		return _rows;
	}
	
	// RETURN NO OF CELLS IN A ROW
	
	public int noOfCellsInARow() {
		return cells.size();
	}
	
	// RETURN COLUMN INDEX BY USING THE COLUMN HEADER
	
	public int getColumnNoByHeaderText(String columnHeader) {
		return getElementIndexByText(columns, columnHeader);
	}
	
	// RETURN ROW NO BY USING THE COLUMN HEADER AND THE CELL VALUE
	
	public int getRowNoByCellValue(String columnHeader, String text) {
		int col = getColumnNoByHeaderText(columnHeader);
		
		return getElementIndexByText(table.findElements(By
				.cssSelector(String.format(cellsByColumnIndex, col))), text);
	
	}
	
	// RETURN ROW NO BY USING THE COLUMN INDEX AND THE CELL VALUE
	
	public int getRowNoByCellValue(int columnHeaderIndex, String text) {
		return getElementIndexByText(table.findElements(By
				.cssSelector(String.format(cellsByColumnIndex, columnHeaderIndex))), text);
	
	}
	
	// CLICKS A CELL BY IT VALUE IN THE GIVEN COLUMN HEADER
	
	public void clickCellByText(String columnHeader, String text) {
		getRowsByCellValue(columnHeader, text).get(0).click();
	}
	
	// CLICKS A CELL BY IT VALUE IN THE GIVEN COLUMN INDEX
	
	public void clickCellByText(int columnHeaderIndex, String text) {
		
		filterElementByText(table.findElements(By
				.cssSelector(String.format(cellsByColumnIndex, columnHeaderIndex))), text).click();
	}
	
	// CLICKS THE ROW CHECKBOX BY IT VALUE IN THE GIVEN COLUMN
	
	public void clickCheckBoxOfRowByText(String columnHeader, String text) {
		int row = getRowNoByCellValue(columnHeader, text);
		
		allRowCheckBoxes.get(row).click();
	}
	
	
	// RETURNS ROWS ELEMENTS BY THE GIVEN CELL VALUE AND COLUMN HEADER
	
	public List<WebElement> getRowsByCellValue(String columnHeader, String text) {
		int col = getColumnNoByHeaderText(columnHeader);
		
		return filterElementsByText(table.findElements(By
				.cssSelector(String.format(cellsByColumnIndex, col))), text);
	}

	// RETURNS ROWS ELEMENTS BY THE GIVEN CELL VALUE AND COLUMN INDEX
	
	public List<WebElement> getRowsByCellValue(int columnHeaderIndex, String text) {
		
		return filterElementsByText(table.findElements(By
				.cssSelector(String.format(cellsByColumnIndex, columnHeaderIndex))), text);
	}
	
	// RETURNS TRUE IF THE GIVEN VALUE IS FOUND IN ALL THE CELLS OF GIVEN COLUMN
	public boolean verifyValuesInRowsMatches(int columnHeaderIndex, String text) {
		boolean matches = true;
		if(text != null && text.length()> 0) {
			matches = noOfRows() == getRowsByCellValue(columnHeaderIndex, text).size();
		}
		return matches;
	}
}
