package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class ClientsPageAction extends CommonAction {
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;
	public Configure configure = new Configure();

	/**
	 * read xml file
	 */
	public ClientsPageAction() {
		readXmlFile("clientsPageElement");
	}

	/**
	 * click btn New
	 */
	@SuppressWarnings("static-access")
	public void clickNew() {
		configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ClientsPage.btnToolbar, "New"));
	}
	
	/**
	 * clear text box search
	 */
	public void clearTxtSearch() {
		Configure.waitPageLoad(3000);
		sendkeys(readElement(Interfaces.ClientsPage.txtSearch), "");
		click(readElement(Interfaces.ClientsPage.btnSearch));
	}
	
	/**
	 * verify client successfully saved
	 * @return
	 */
	public boolean clientSuccessfullySaved(String clientName) {
		boolean result = false;
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.ClientsPage.alertMessage)).equals("Client saved.");
		sendkeys(readElement(Interfaces.ClientsPage.txtSearch), clientName);
		click(readElement(Interfaces.ClientsPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.ClientsPage.displayClient, clientName));
		clearTxtSearch();
		return result && checkAlert;
	}
	
	
}
