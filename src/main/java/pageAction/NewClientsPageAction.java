package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import interfaces.Interfaces;

public class NewClientsPageAction extends CommonAction {
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	public NewClientsPageAction() {
		readXmlFile("newClientsPageElement");
	}
	
	/**
	 * input name
	 * @param name
	 */
	public void inputName(String name) {
		sendkeys(readElement(Interfaces.NewClientsPage.inputName), name);
	}

	/**
	 * input contact name
	 * @param contactName
	 */
	public void inputContactName(String contactName) {
		sendkeys(readElement(Interfaces.NewClientsPage.inputContactName), contactName);
	}
	
	/**
	 * input contact email
	 * @param contactEmail
	 */
	public void inputContactEmail(String contactEmail) {
		sendkeys(readElement(Interfaces.NewClientsPage.inputContactEmail), contactEmail);
	}
	
	/**
	 * click btn save & close
	 */
	public void clickSaveClose() {
		click(readElement(Interfaces.NewClientsPage.btnToolbar, "toolbar-save"));
	}
	

}
