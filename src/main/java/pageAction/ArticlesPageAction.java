package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import interfaces.Interfaces;

import common.CommonAction;
import common.Configure;

public class ArticlesPageAction extends CommonAction {

	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	/**
	 * Read file Xml
	 */
	public ArticlesPageAction() {
		readXmlFile("articlesPageElement");
	}

	/**
	 * Click btn new
	 */
	public void clickNew() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "New"));
	}

	/**
	 * clear text box search
	 */
	public void clearTxtSearch() {
		Configure.waitElementDisplay(3000);
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), "");
		click(readElement(Interfaces.ArticlesPage.btnSearch));
	}

	/**
	 * Verify user can create new article with valid information
	 * 
	 * @param title
	 * @return true/false
	 */
	public boolean checkSavedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("Article saved.");
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
		click(readElement(Interfaces.ArticlesPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title));
		clearTxtSearch();
		return result && checkAlert;
	}

	/**
	 * Verify user can edit an article
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkEditSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("Article saved.");
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
		click(readElement(Interfaces.ArticlesPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title));
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), "");
		click(readElement(Interfaces.ArticlesPage.btnSearch));
		return result && checkAlert;
	}

	/**
	 * click toggle
	 * 
	 * @param toggle
	 */
	public void openToggle(String toggle) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.IndexPage.menu, toggle));
	}

	/**
	 * click toggle > panel level 1 > panel level 2
	 * 
	 * @param toggle
	 * @param panel
	 */
	public void openPanel1(String toggle, String panel) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.menu, toggle));
		click(readElement(Interfaces.IndexPage.openPanel1, toggle, panel));
	}

	/**
	 * path toggle > panel level 1
	 * 
	 * @param path
	 */
	public void openPage(String path) {
		Configure.waitElementDisplay(3000);
		int count = 0;
		String tmp2 = "";
		String tmp1 = "";
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '>')
				count++;
		}
		if (count == 0) {
			tmp1 = path.split(">")[0];
			openToggle(tmp1);
		}
		if (count == 1) {
			tmp1 = path.substring(0, path.indexOf('>'));
			tmp2 = path.split(">")[1];
			openPanel1(tmp1, tmp2);
		}
	}

	/**
	 * check box by name article
	 * 
	 * @param title
	 */
	public void checkboxArticle(String title) {
		Configure.waitElementDisplay(3000);
		while (true) {
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.nameArticle, title))) {
				click(readElement(Interfaces.ArticlesPage.checkboxArticle, title));
				break;
			}
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination)))
				click(readElement(Interfaces.ArticlesPage.btnNextPagination));
			else
				break;
		}
	}

	/**
	 * click btn edit
	 */
	public void clickEdit() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Edit"));
	}

	/**
	 * click btn publish
	 */
	public void clickPublish() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Publish"));
	}

	/**
	 * click btn unpublish
	 */
	public void clickUnpublish() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Unpublish"));
	}

	/**
	 * Verify user can publish an unpublished article
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkPublishedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		if (getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article published.")) {
			sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
			click(readElement(Interfaces.ArticlesPage.btnSearch));
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title))
					&& isElementDisplay(readElement(Interfaces.ArticlesPage.iconPublish, title))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verify user can unpublish a published article
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkUnpublishedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		if (getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article unpublished.")) {
			sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
			click(readElement(Interfaces.ArticlesPage.btnSearch));
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title))
					&& isElementDisplay(readElement(Interfaces.ArticlesPage.iconUnpublish, title))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * click btn archive
	 */
	public void clickArchive() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Archive"));
	}

	/**
	 * Verify user can move an article to the archive
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkArchivedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		if (getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article archived.")) {
			return true;
		}
		return false;
	}

	/**
	 * click btn checkIn
	 */
	public void clickCheckIn() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Check-in"));
	}

	/**
	 * Verify user can check in an article
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkCheckedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		if (getTextElement(readElement(Interfaces.ArticlesPage.alertSuccess, "1 article checked in."))
				.equals("1 article checked in.")) {
			sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
			click(readElement(Interfaces.ArticlesPage.btnSearch));
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title))) {
				click(readElement(Interfaces.ArticlesPage.btnClear));
				return true;
			}
		}
		return false;
	}

	/**
	 * click SearchTools > option status
	 */
	public void clickSearchTools(String Status) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.cbbSearchTools));
		click(readElement(Interfaces.ArticlesPage.cbbSelectStatus));
		click(readElement(Interfaces.ArticlesPage.optionSelectStatus, Status));
	}

	/**
	 * Verify user can move an article to the archive
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkArchivedArticle(String title) {
		Configure.waitElementDisplay(3000);
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title)))
			return true;
		return false;
	}

	/**
	 * verify display icon checked out
	 * 
	 * @return
	 */
	public boolean iconCheckedOut() {
		Configure.waitElementDisplay(3000);
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.iconCheckedOut)))
			return true;
		return false;
	}

	/**
	 * click btn trash
	 */
	public void clickTrash() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Trash"));
	}

	/**
	 * Verify the confirm trash message is displayed
	 * 
	 * @return
	 */
	public boolean checkTrashedSuccessfully() {
		Configure.waitElementDisplay(3000);
		if (getTextElement(readElement(Interfaces.ArticlesPage.alertSuccess, "1 article trashed."))
				.equals("1 article trashed.")) {
			return true;
		}
		return false;
	}

	/**
	 * Verify user can move an article to trash section
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkMoveAnArticleToTrash(String title) {
		Configure.waitElementDisplay(3000);
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), title);
		click(readElement(Interfaces.ArticlesPage.btnSearch));
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title))) {
			return true;
		}
		return false;
	}

	/**
	 * click btn help
	 */
	public void clickHelp() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnToolbar, "Help"));
	}

	/**
	 * Verify the article's help window is displayed
	 * 
	 * @return
	 */
	public boolean displayHelpWindow() {
		Configure.waitElementDisplay(3000);
		return checkDisplayPopUp("Joomla! Help Screens");
	}

	/**
	 * input text filter
	 * 
	 * @param text
	 */
	public void inputFilter(String text) {
		Configure.waitElementDisplay(3000);
		sendkeys(readElement(Interfaces.ArticlesPage.txtSearch), text);
	}

	/**
	 * click btn Search
	 */
	public void clickSearch() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.btnSearch));
	}

	/**
	 * check name article Display
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkTitleDisplay(String title) {
		Configure.waitElementDisplay(3000);
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.txtNameArticle, title))) {
			return true;
		}
		return false;
	}

	/**
	 * click cbb Search
	 */
	public void clickSearchTools() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.cbbSearchTools));
	}

	/**
	 * option status
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		Configure.waitElementDisplay(3000);
		clickSearchTools();
		click(readElement(Interfaces.ArticlesPage.cbbSelectStatus));
		click(readElement(Interfaces.ArticlesPage.optionSelectStautus, status));
	}

	/**
	 * option category
	 * 
	 * @param category
	 */
	public void selectCategory(String category) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.txtSelectCategory));
		click(readElement(Interfaces.ArticlesPage.optionSelectCategory, category));
	}

	/**
	 * option access
	 * 
	 * @param access
	 */
	public void selectAccess(String access) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.txtSelectAccess));
		click(readElement(Interfaces.ArticlesPage.optionSelectAccess, access));
	}

	/**
	 * option author
	 * 
	 * @param author
	 */
	public void selectAuthor(String author) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.txtSelectAuthor));
		click(readElement(Interfaces.ArticlesPage.optionSelectAuthor, author));
	}

	/**
	 * display articles per page
	 * 
	 * @param item
	 */
	public void listLimit(String item) {
		Configure.waitElementDisplay(3000);
		isElementDisplay(readElement(Interfaces.ArticlesPage.cbbListLimit));
		click(readElement(Interfaces.ArticlesPage.cbbListLimit));
		String index = getDataOptionArrayIndex(readElement(Interfaces.ArticlesPage.selectListLimit, item));
		sendkeys(readElement(Interfaces.ArticlesPage.inputLimit), item);
		scrollElement(readElement(Interfaces.ArticlesPage.scrollTop));
		click(readElement(Interfaces.ArticlesPage.optionLimit, index));
	}

	/**
	 * Verify user can search for articles using the filter dropdown lists
	 * 
	 * @return true/false
	 */
	public boolean checkFilterDropdown(String checkStatusFilter, String checkCategoryFilter, String checkAccessFilter,
			String checkAuthorFilter) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		listLimit("All");
		int numberTitle = countElements(readElement(Interfaces.ArticlesPage.countTitle));
		for (int i = 0; i < numberTitle; i++) {
			String row = String.valueOf(i + 1);
			if (isElementDisplay(readElement(Interfaces.ArticlesPage.selectRowIcon, row, checkStatusFilter))
					&& isElementDisplay(readElement(Interfaces.ArticlesPage.selectRowCategory, row,
							"Category: " + checkCategoryFilter))
					&& isElementDisplay(readElement(Interfaces.ArticlesPage.selectRowAccess, row, checkAccessFilter))
					&& isElementDisplay(readElement(Interfaces.ArticlesPage.selectRowAuthor, row, checkAuthorFilter))) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * click id column
	 */
	public void clickIDColumn() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.headerLink, "ID"));
	}

	/**
	 * sort array
	 * 
	 * @param arr
	 */
	public static void sortArray(int[] arr) {
		int temp = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}

	/**
	 * verify check id ascending
	 * 
	 * @return
	 */
	public boolean checkIdAscending() {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		listLimit("All");
		String idString = "";
		int idNumber;
		String row = "";
		int numberTitle = countElements(readElement(Interfaces.ArticlesPage.countTitle));
		int arrayID[] = new int[numberTitle];
		int arrayCheck[] = new int[numberTitle];
		for (int i = 0; i < numberTitle; i++) {
			row = String.valueOf(i + 1);
			idString = Configure.driver.findElement(readElement(Interfaces.ArticlesPage.selectRowID, row)).getText();
			idNumber = Integer.parseInt(idString);
			arrayID[i] = idNumber;
			arrayCheck[i] = idNumber;
		}
		sortArray(arrayCheck);
		for (int i = 0; i < numberTitle; i++) {
			if (arrayID[i] == arrayCheck[i]) {
				row = String.valueOf(i + 1);
				result = true;
			}
		}
		return result;
	}

	/**
	 * verify check id descending
	 * 
	 * @return
	 */
	public boolean checkIdDescending() {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		String idString = "";
		int idNumber;
		String row = "";
		int numberTitle = countElements(readElement(Interfaces.ArticlesPage.countTitle));
		int arrayID[] = new int[numberTitle];
		int arrayCheck[] = new int[numberTitle];
		for (int i = 0; i < numberTitle; i++) {
			row = String.valueOf(i + 1);
			idString = Configure.driver.findElement(readElement(Interfaces.ArticlesPage.selectRowID, row)).getText();
			idNumber = Integer.parseInt(idString);
			arrayID[i] = idNumber;
			arrayCheck[i] = idNumber;
		}
		sortArray(arrayCheck);
		for (int i = 0; i < numberTitle; i++) {
			if (arrayID[i] == arrayCheck[numberTitle - i - 1]) {
				row = String.valueOf(i + 1);
				result = true;
			}
		}
		return result;
	}

	/**
	 * Verify the article table is paging into choose item articles per page
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkItemPerPage(String item) {
		Configure.waitElementDisplay(3000);
		int quantityItem = Integer.parseInt(item);
		if (countElements(readElement(Interfaces.ArticlesPage.countTitle)) == quantityItem)
			return true;
		return false;
	}

	/**
	 * verify check quantity of items displayed in table is changed all pages
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkArticlesPerPage(String item) {
		Configure.waitElementDisplay(3000);
		boolean checkItemPerPage = false;
		boolean checkQuantityItem = false;
		int pagination = pagination(readElement(Interfaces.ArticlesPage.paginationArticle));
		int quantityItem = Integer.parseInt(item);
		if (pagination > 0) {
			while (isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination)))
				if (isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination))) {
					scrollElement(readElement(Interfaces.ArticlesPage.btnNextPagination));
					click(readElement(Interfaces.ArticlesPage.btnNextPagination));
					if (isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination)))
						checkItemPerPage = checkItemPerPage(item);
				}
		}
		if (!isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination))) {
			if (countElements(readElement(Interfaces.ArticlesPage.countTitle)) <= quantityItem)
				checkQuantityItem = true;
		}
		return checkItemPerPage && checkQuantityItem;
	}

	/**
	 * Verify all articles are displayed in one page
	 * 
	 * @return
	 */
	public boolean allArticlesPerPage() {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		scrollBottom();
		if (!isElementDisplay(readElement(Interfaces.ArticlesPage.btnNextPagination)))
			result = true;
		return result;
	}

	/**
	 * click btn ordering column
	 */
	public void clickOrderingColumn() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.orderingColumn));
	}

	/**
	 * verify check position first when click ordering column
	 */
	public boolean checkPositionFirst = false;

	public boolean checkPositionFirst(String title, String titleNew) {
		Configure.waitElementDisplay(3000);
		int positionTitle = indexElement(readElement(Interfaces.ArticlesPage.indexElement, title));
		int positionTitleNew = indexElement(readElement(Interfaces.ArticlesPage.indexElement, titleNew));
		if (positionTitle > positionTitleNew)
			checkPositionFirst = true;
		return checkPositionFirst;
	}

	/**
	 * verify check position last when click ordering column
	 */
	public boolean checkPositionLast = false;

	public boolean checkPositionLast(String title, String titleNew) {
		Configure.waitElementDisplay(3000);
		int positionTitle = indexElement(readElement(Interfaces.ArticlesPage.indexElement, title));
		int positionTitleNew = indexElement(readElement(Interfaces.ArticlesPage.indexElement, titleNew));
		if (positionTitle < positionTitleNew)
			checkPositionLast = true;
		return checkPositionLast;
	}

	/**
	 * click ordering column First
	 * 
	 * @param title
	 * @param titleNew
	 * @return distance
	 */
	public boolean clickOrderingColumnFirst(String title, String titleNew) {
		Configure.waitElementDisplay(3000);
		listLimit("All");
		click(readElement(Interfaces.ArticlesPage.orderingColumn));
		return checkPositionFirst(title, titleNew);
	}

	/**
	 * click ordering column last
	 * 
	 * @param title
	 * @param titleNew
	 * @return distance
	 */
	public boolean clickOrderingColumnLast(String titleNew, String title) {
		Configure.waitElementDisplay(3000);
		listLimit("All");
		click(readElement(Interfaces.ArticlesPage.orderingColumn));
		return checkPositionLast(titleNew, title);
	}

	/**
	 * verify check changes position
	 * 
	 * @param title
	 * @param titleNew
	 * @return true/false
	 */
	public boolean checkChangesPosition() {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		if (checkPositionFirst && checkPositionLast)
			result = true;
		return result;
	}

	/**
	 * click Icon Pushlish
	 * 
	 * @param title
	 */
	public void clickIconPushlish(String title) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.iconPublish, title));
	}

	/**
	 * Verify user can publish an unpublished article
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkUnpublishedSuccess(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.iconUnpublish, title))
				&& getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article unpublished."))
			result = true;
		return result;
	}

	/**
	 * click icon unpushlish of title
	 * 
	 * @param title
	 */
	public void clickIconUnpushlish(String title) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.iconUnpublish, title));
	}

	/**
	 * verify check published success
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkPublishedSuccess(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.iconPublish, title))
				&& getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article published."))
			result = true;
		return result;
	}

	/**
	 * click icon unfeatured
	 * 
	 * @param title
	 */
	public void clickIconUnfeatured(String title) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.iconUnfeatured, title));
	}

	/**
	 * verify check featured successfully
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkFeaturedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.iconFeatured, title))
				&& getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article featured."))
			result = true;
		return result;
	}

	/**
	 * click icon featured
	 * 
	 * @param title
	 */
	public void clickIconFeatured(String title) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ArticlesPage.iconFeatured, title));
	}

	/**
	 * verify check unfeatured successfully
	 * 
	 * @param title
	 * @return
	 */
	public boolean checkUnfeaturedSuccessfully(String title) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		if (isElementDisplay(readElement(Interfaces.ArticlesPage.iconUnfeatured, title))
				&& getTextElement(readElement(Interfaces.ArticlesPage.alertMessage)).equals("1 article unfeatured."))
			result = true;
		return result;
	}

	/**
	 * verify check access level
	 * 
	 * @param title
	 * @param access
	 * @return
	 */
	public boolean checkAccessLevel(String title, String access) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkSave = checkSavedSuccessfully(title);
		result = getTextElement(readElement(Interfaces.ArticlesPage.accessRow, title, access)).equals("Public");
		return result && checkSave;
	}

	public static void main(String args[]) {

	}
}
