package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import interfaces.Interfaces;

public class NewCategoriesPageAction extends CommonAction {
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	public NewCategoriesPageAction(){
		readXmlFile("newCategoriesElement");
	}

	/**
	 * input name
	 * 
	 * @param title
	 */
	public void inputTitle(String title) {
		sendkeys(readElement(Interfaces.NewCategoriesPage.inputTitle), title);
	}

	/**
	 * click btn save & close
	 */
	public void clickSaveClose() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-save"));
	}
	
	public static void main (String args[]) {
	}
	
}
