package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class CategoriesPageAction extends CommonAction{
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;
	public Configure configure;

	/**
	 * Read file Xml
	 */
	public CategoriesPageAction() {
		readXmlFile("categoriesPageElement");
	}

	/**
	 * click btn New
	 */
	public void clickNew() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.CategoriesPage.btnToolbar, "New"));
	}
	
	/**
	 * clear text box search
	 */
	public void clearTxtSearch() {
		Configure.waitElementDisplay(3000);
		sendkeys(readElement(Interfaces.CategoriesPage.txtSearch), "");
		click(readElement(Interfaces.CategoriesPage.btnSearch));
	}
	
	/**
	 * verify category successfully saved
	 * @param categoryName
	 * @return
	 */
	public boolean categorySuccessfullySaved(String categoryName) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.CategoriesPage.alertMessage)).equals("Category saved.");
		sendkeys(readElement(Interfaces.CategoriesPage.txtSearch), categoryName);
		click(readElement(Interfaces.CategoriesPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.CategoriesPage.displayCategory, categoryName));
		clearTxtSearch();
		return result && checkAlert;
	}
}
