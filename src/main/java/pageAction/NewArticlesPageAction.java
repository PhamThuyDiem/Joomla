package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import interfaces.Interfaces;

public class NewArticlesPageAction extends CommonAction {

	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	public NewArticlesPageAction() {
		readXmlFile("newArticlesPageElement");
	}

	/**
	 * input title
	 * 
	 * @param title
	 */
	public void inputTitle(String title) {
		sendkeys(readElement(Interfaces.NewArticles.txtTitle), title);
	}

	/**
	 * input new title
	 * 
	 * @param title
	 */
	public void inputTitleNew(String title) {
		isElementDisplay(readElement(Interfaces.NewArticles.txtTitle));
		click(readElement(Interfaces.NewArticles.txtTitle));
		sendkeys(readElement(Interfaces.NewArticles.txtTitle), title);
	}

	/**
	 * click cbb category
	 */
	public void clickCbbCategory() {
		click(readElement(Interfaces.NewArticles.cbbCategory));
	}

	/**
	 * select option in category
	 */
	public void selectCategory1(String category) {
		click(readElement(Interfaces.NewArticles.selectCategory, category));
	}

	/**
	 * select Category
	 */
	public void selectCategory(String category) {
		clickCbbCategory();
		selectCategory1(category);
	}

	/**
	 * click content
	 */
	public void clickContent() {
		switchToFrame(readElement(Interfaces.NewArticles.ifContent));
		click(readElement(Interfaces.NewArticles.tbContent));
		switchBack();
	}

	/**
	 * type content
	 * 
	 * @param content
	 */
	public void typeContent(String content) {
		switchToFrame(readElement(Interfaces.NewArticles.ifContent));
		sendkeys(readElement(Interfaces.NewArticles.tbContent), content);
		switchBack();
	}

	/**
	 * 
	 * @param content
	 */
	public void inputContent(String content) {
		clickContent();
		typeContent(content);
	}

	/**
	 * click btn save & close
	 */
	public void clickSaveClose() {
		click(readElement(Interfaces.NewArticles.toolbar, "toolbar-save"));
	}

	/**
	 * click btn save
	 */
	public void clickSave() {
		click(readElement(Interfaces.NewArticles.toolbar, "toolbar-apply"));
	}

	/**
	 * select status
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		click(readElement(Interfaces.NewArticles.cbbStatus));
		click(readElement(Interfaces.NewArticles.seclectStatus, status));
	}

	/**
	 * verify check saved successfully
	 * 
	 * @return
	 */
	public boolean checkSavedSuccessfully() {
		if ((isElementDisplay(readElement(Interfaces.NewArticles.alertSuccess, "Article saved.")))) {
			return true;
		}
		return false;
	}

	/**
	 * select access
	 * 
	 * @param access
	 */
	public void selectAccess(String access) {
		click(readElement(Interfaces.NewArticles.cbbAccess));
		click(readElement(Interfaces.NewArticles.selectAccess, access));
	}

	/**
	 * click btn image
	 */
	public void clickImage() {
		scrollElement(readElement(Interfaces.NewArticles.jformTxtTitle));
		click(readElement(Interfaces.NewArticles.clickImage));
	}

	/**
	 * choose image
	 */
	public void chooseImage() {
		scrollElement(readElement(Interfaces.NewArticles.jformTxtTitle));
		
		focus(readElement(Interfaces.NewArticles.ifImage));
		click(readElement(Interfaces.NewArticles.ifImage));
		switchToFrame(readElement(Interfaces.NewArticles.ifImage));
		switchBack();
		focus(readElement(Interfaces.NewArticles.ifImage));
		click(readElement(Interfaces.NewArticles.ifImage));
		switchToFrame(readElement(Interfaces.NewArticles.ifImage));
		
		focus(readElement(Interfaces.NewArticles.fsUpload));
		System.out.println("focus");
		click(readElement(Interfaces.NewArticles.fsUpload));
		switchToFrame(readElement(Interfaces.NewArticles.fsUpload));

		click(readElement(Interfaces.NewArticles.chooseImage,"powered_by.png"));
		switchBack();
	}

	/**
	 * click insert
	 */
	public void clickInsert() {
		focus(readElement(Interfaces.NewArticles.ifImage));
		switchToFrame(readElement(Interfaces.NewArticles.ifImage));
		click(readElement(Interfaces.NewArticles.btnInsertImage));
		switchBack();
	}
}
