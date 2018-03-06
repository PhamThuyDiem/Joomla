package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class NewBannersPageAction extends CommonAction {
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	public NewBannersPageAction() {
		readXmlFile("newBannersPageElement");
	}

	/**
	 * input name
	 * 
	 * @param title
	 */
	public void inputName(String name) {
		sendkeys(readElement(Interfaces.NewBannerPage.txtName), name);
	}

	/**
	 * select category
	 * 
	 * @param category
	 */
	public void selectCategory(String category) {
		clickTab("Details");
		click(readElement(Interfaces.NewBannerPage.cbbCategory));
		String index = getDataOptionArrayIndex(readElement(Interfaces.NewBannerPage.optionSelectCategory, category));
		sendkeys(readElement(Interfaces.NewBannerPage.txtCategory), category);
		scrollElement(readElement(Interfaces.NewBannerPage.top));
		click(readElement(Interfaces.NewBannerPage.indexCategory ,index));	
	}

	/**
	 * click tab
	 * 
	 * @param tab
	 */
	public void clickTab(String tab) {
		click(readElement(Interfaces.NewBannerPage.tab, tab));
	}

	/**
	 * select client
	 * 
	 * @param client
	 */
	public void selectClient(String client) {
		clickTab("Banner Details");
		click(readElement(Interfaces.NewBannerPage.cbbClient));
		String index = getDataOptionArrayIndex(readElement(Interfaces.NewBannerPage.optionSelectClient, client));
		sendkeys(readElement(Interfaces.NewBannerPage.txtClient), client);
		scrollElement(readElement(Interfaces.NewBannerPage.top));
		click(readElement(Interfaces.NewBannerPage.indexClient ,index));	
		
//		click(readElement(Interfaces.NewBannerPage.selectClient, client)));
//		scrollElement(readElement(Interfaces.NewBannerPage.inputName)));
	}

	/**
	 * click btn save & close
	 */
	public void clickSaveClose() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-save"));
	}

	/**
	 * click btn Save
	 */
	public void clickSave() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-apply"));
	}

	/**
	 * click btn Save New
	 */
	public void clickSaveNew() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-save-new"));
	}

	/**
	 * click btn Help
	 * 
	 * @return
	 */
	public void clickHelp() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-help"));
	}

	/**
	 * click btn Save As Copy
	 */
	public void clickSaveAsCopy() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-save-copy"));
	}

	/**
	 * click btn Close
	 */
	public void clickClose() {
		click(readElement(Interfaces.NewCategoriesPage.btnToolbar, "toolbar-cancel"));
	}

	/**
	 * verify check banner successfully
	 * 
	 * @return
	 */
	public boolean checkBannerSuccessfully() {
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertSuccess, "Banner saved.")).equals("Banner saved.");
		result = getTitle().equals("Banners: Edit - joomla - Administration");
		return result && checkAlert;
	}

	/**
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		clickTab("Details");
		click(readElement(Interfaces.NewBannerPage.cbbStatus));
		click(readElement(Interfaces.NewBannerPage.optionSelectStatus, status));
	}

	/**
	 * verify check alert message
	 * 
	 * @return
	 */
	public boolean checkAlertMessage() {
		boolean checkAlert = getTextElement(readElement(Interfaces.NewBannerPage.textAlertMessage)).equals("Banner saved.");
		return checkAlert;
	}

	/**
	 * verify display new banner page
	 * 
	 * @return
	 */
	public boolean displayNewBannerPage() {
		Configure.waitElementDisplay(3000);
		boolean checkTitle = Configure.driver.getTitle().equals("Banners: New - joomla - Administration");
		return checkTitle;
	}

	/**
	 * verify check successfully saved shows and new banner page displays
	 * 
	 * @return
	 */
	public boolean checkSuccessfullySavedShowsAndNewBannerPageDisplays() {
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage)).equals("Banner saved.");
		System.out.println(checkAlert);
		System.out.println(displayNewBannerPage());
		return checkAlert;
	}

	/**
	 * verify display help window
	 * 
	 * @return
	 */
	public boolean displayHelpWindow() {
		Configure.waitElementDisplay(3000);
		return checkDisplayPopUp("Joomla! Help Screens");
	}

	/**
	 * verify check banner successfully saved and edit banner page displays
	 * 
	 * @return
	 */
	public boolean checkBannerSuccessfullySavedAndEditBannerPageDisplays() {
		Configure.waitElementDisplay(3000);
		boolean checkTitle = Configure.driver.getTitle().equals("Banners: Edit - joomla - Administration");
		return checkAlertMessage() && checkTitle;
	}

	/**
	 * verify message banner successfully saved
	 * @return
	 */
	public boolean checkBannerSuccessfullySaved() {
		boolean checkAlertMessage = checkAlertMessage();
		clickClose();
		return checkAlertMessage;
	}

	/**
	 * verify check color of name textbox changes to red
	 * @return
	 */
	public boolean checkColorOfNameTextboxChangesToRed() {
		boolean checkColor = getBackgroudColor(readElement(Interfaces.NewBannerPage.txtName)).equals("rgba(242, 222, 222, 1)");
		clickClose();
		return checkColor;
	}
	
	public static void main(String args[]) {
	}
}
