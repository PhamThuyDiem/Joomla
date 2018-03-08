package pageAction;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class BannersPageAction extends CommonAction {

	public Configure configure;

	/**
	 * read xml file
	 */
	public BannersPageAction() {
		readXmlFile("bannersPageElement");
	}

	/**
	 * click btn New
	 */
	public void clickNew() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.ClientsPage.btnToolbar, "New"));
	}

	/**
	 * clear text box search
	 */
	public void clearTxtSearch() {
		Configure.waitElementDisplay(3000);
		sendkeys(readElement(Interfaces.ClientsPage.txtSearch), "");
		click(readElement(Interfaces.ClientsPage.btnSearch));
	}
	
	/**
	 * click id column
	 */
	public void clickIDColumn() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.BannersPage.headerLink, "ID"));
	}

	/**
	 * click sub menu clients
	 */
	public void clickSubMenuClients() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.BannersPage.subMenu, "Clients"));
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
	 * Items are sorted ascending by ID in banner table
	 * 
	 * @return
	 */
	public boolean checkIdAscending() {
		boolean result = false;
		listLimit("All");
		String idString = "";
		int idNumber;
		String row = "";
		int numberTitle = countElements(readElement(Interfaces.BannersPage.countRowBanners));
		int arrayID[] = new int[numberTitle];
		int arrayCheck[] = new int[numberTitle];
		for (int i = 0; i < numberTitle; i++) {
			row = String.valueOf(i + 1);
			idString = getTextElement(readElement(Interfaces.ArticlesPage.selectRowID, row));
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
	 * Items are sorted descending by ID in banner table
	 * 
	 * @return
	 */
	public boolean checkIdDescending() {
		boolean result = false;
		listLimit("All");
		String idString = "";
		int idNumber;
		String row = "";
		int numberTitle = countElements(readElement(Interfaces.BannersPage.countRowBanners));
		int arrayID[] = new int[numberTitle];
		int arrayCheck[] = new int[numberTitle];
		for (int i = 0; i < numberTitle; i++) {
			row = String.valueOf(i + 1);
			idString = getTextElement(readElement(Interfaces.ArticlesPage.selectRowID, row));
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
	 * Verify a message : "Client saved." shows and new client is created
	 * 
	 * @return
	 */
	public boolean clientSuccessfullySaved(String clientName) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.ClientsPage.alertMessage))
				.equals("Client saved.");
		sendkeys(readElement(Interfaces.ClientsPage.txtSearch), clientName);
		click(readElement(Interfaces.ClientsPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.ClientsPage.displayClient, clientName));
		clearTxtSearch();
		return result && checkAlert;
	}

	/**
	 * A message : "Category saved." shows and new category is created
	 * @param banner
	 * @return
	 */
	public boolean checkBannerSuccessfullySaved(String banner) {
		boolean result = false;
		Configure.waitPageLoad(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("Banner saved.");
		sendkeys(readElement(Interfaces.BannersPage.txtSearch), banner);
		click(readElement(Interfaces.BannersPage.btnSearch));
		result = isElementDisplay(readElement(Interfaces.BannersPage.textBannerName, banner));
		clearTxtSearch();
		return result && checkAlert;
	}

	/**
	 * search by text
	 * 
	 * @param banner
	 */
	public void searchText(String text) {
		Configure.waitPageLoad(3000);
		sendkeys(readElement(Interfaces.BannersPage.txtSearch), text);
		click(readElement(Interfaces.BannersPage.btnSearch));
	}

	/**
	 * senkey textbox Search
	 * 
	 * @param text
	 */
	public void inputSearch(String text) {
		Configure.waitPageLoad(3000);
		sendkeys(readElement(Interfaces.BannersPage.txtSearch), text);
	}

	/**
	 * click btn Search
	 */
	public void clickSearch() {
		Configure.waitPageLoad(3000);
		click(readElement(Interfaces.BannersPage.btnSearch));
	}

	/**
	 * check new banner is created and matched with entered data
	 * 
	 * @param banner
	 * @param category
	 * @param client
	 * @return
	 */
	public boolean checkBannerMatchInputData(String banner, String category, String client) {
		Configure.waitElementDisplay(3000);
		searchText(banner);
		boolean checkBannerName = getTextElement(readElement(Interfaces.BannersPage.textBannerName, banner))
				.equals(banner);
		boolean checkCategory = getTextElement(readElement(Interfaces.BannersPage.textCategoryName, banner, "Category: " + category))
						.equals("Category: " + category);
		boolean checkClient = getTextElement(readElement(Interfaces.BannersPage.nameClient, banner, client))
				.equals(client);
		boolean checkStatus = isElementDisplay(readElement(Interfaces.BannersPage.iconUnpublish, banner));
		return checkBannerName && checkCategory && checkClient && checkStatus;
	}

	/**
	 * verify check banner successfully save
	 * 
	 * @return
	 */
	public boolean checkBannerSuccessfullySave(String banner, String category, String client) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		result = checkBannerSuccessfullySaved(banner);
		boolean checkBannerMatchInputData = checkBannerMatchInputData(banner, category, client);
		return result && checkBannerMatchInputData;
	}

	/**
	 * choose list limit
	 * 
	 * @param item
	 */
	public void listLimit(String item) {
		scrollTop();
		isElementDisplay(readElement(Interfaces.BannersPage.cbbListLimit));
		click(readElement(Interfaces.BannersPage.cbbListLimit));
		String index = getDataOptionArrayIndex(readElement(Interfaces.BannersPage.optionSelectListLimit, item));
		sendkeys(readElement(Interfaces.BannersPage.txtLimit),item);
		//scrollElement(readElement(Interfaces.BannersPage.top));
		scrollTop();
		click(readElement(Interfaces.BannersPage.optionLimit ,index));	
	}

	/**
	 * click checkbox banner
	 * 
	 * @param banner
	 */
	public void checkboxBanners(String banner) {
		Configure.waitElementDisplay(3000);
		searchText(banner);
		click(readElement(Interfaces.BannersPage.checkboxBanners, banner));
		scrollTop();
	}

	/**
	 * click btn Unpublish
	 */
	public void clickUnpublish() {
		Configure.waitElementDisplay(3000);
		scrollTop();
		click(readElement(Interfaces.BannersPage.btnUnpublish));
	}

	/**
	 * click btn Archive
	 */
	public void clickArchive() {
		Configure.waitElementDisplay(3000);
		scrollTop();
		click(readElement(Interfaces.BannersPage.btnArchive));
	}

	/**
	 * click btn CheckIn
	 */
	public void clickCheckIn() {
		Configure.waitElementDisplay(3000);
		scrollTop();
		click(readElement(Interfaces.BannersPage.btnCheckIn));
	}

	/**
	 * click btn Trash
	 */
	public void clickTrash() {
		Configure.waitElementDisplay(3000);
		scrollTop();
		click(readElement(Interfaces.BannersPage.btnTrash));
	}

	/**
	 * verify banner successfully unpublished
	 * 
	 * @param banner
	 * @return
	 */
	public boolean bannerSuccessfullyUnpublished(String banner) {
		Configure.waitElementDisplay(3000);
		boolean result = false;
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("1 banner unpublished.");
		searchText(banner);
		result = isElementDisplay(readElement(Interfaces.BannersPage.iconUnpublish, banner));
		return result && checkAlert;
	}

	/**
	 * verify banner successfully archived
	 * 
	 * @return
	 */
	public boolean checkBannerSuccessfullyArchived() {
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("1 banner archived.");
		return checkAlert;
	}

	/**
	 * verify check banner successfully sent to trash
	 * 
	 * @return
	 */
	public boolean checkBannerSuccessfullySentToTrash() {
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("1 banner trashed.");
		return checkAlert;
	}

	/**
	 * click btn Search
	 */
	public void clickSearchTools() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.BannersPage.cbbSearchTools));
	}

	/**
	 * select status
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		Configure.waitElementDisplay(3000);
		clearTxtSearch();
		clickSearchTools();
		click(readElement(Interfaces.BannersPage.cbbSelectStatus));
		click(readElement(Interfaces.BannersPage.optionSelectStatus, status));
	}

	/**
	 * verify check banner is archived
	 * 
	 * @param banner
	 * @return
	 */
	public boolean checkBannerIsArchived(String banner) {
		Configure.waitElementDisplay(3000);
		searchText(banner);
		boolean checkBanner = isElementDisplay(readElement(Interfaces.BannersPage.textBannerName, banner));
		clearTxtSearch();
		return checkBanner;
	}

	/**
	 * click btn Help
	 */
	public void clickHelp() {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.BannersPage.btnHelp));
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
	 * verify display text
	 * 
	 * @param text
	 * @return
	 */
	public boolean displayText(String text) {
		Configure.waitElementDisplay(3000);
		boolean checkBannerName = getTextElement(readElement(Interfaces.BannersPage.textBannerName, text))
				.equals(text);
		return checkBannerName;
	}

	/**
	 * select client name
	 * 
	 * @param client
	 */
	public void selectClient(String client) {
		Configure.waitElementDisplay(3000);
		clickSearchTools();
		click(readElement(Interfaces.BannersPage.cbbClient));
		String index = getDataOptionArrayIndex(readElement(Interfaces.BannersPage.optionClient, client));
		sendkeys(readElement(Interfaces.BannersPage.inputClient), client);
		scrollElement(readElement(Interfaces.BannersPage.top));
		click(readElement(Interfaces.BannersPage.indexOptionClient ,index));
	}

	/**
	 * select category name
	 * 
	 * @param client
	 */
	public void selectCategory(String category) {
		Configure.waitElementDisplay(3000);
		click(readElement(Interfaces.BannersPage.cbbCategory));
		String index = getDataOptionArrayIndex(readElement(Interfaces.BannersPage.optionCategory, category));
		sendkeys(readElement(Interfaces.BannersPage.inputCategory), category);
		scrollElement(readElement(Interfaces.BannersPage.top));
		click(readElement(Interfaces.BannersPage.indexOptionCategory ,index));
	}

	/**
	 * verify created banner displays
	 * @param banner
	 * @return
	 */
	public boolean checkCreatedBannerDisplays(String banner) {
		Configure.waitElementDisplay(3000);
		listLimit("All");
		boolean checkBanner = isElementDisplay(readElement(Interfaces.BannersPage.textBannerName, banner));
		return checkBanner;
	}

	/**
	 * verify check display banner and locked 
	 * @param banner
	 * @return
	 */
	public boolean checkDisplayBannerAndLocked(String banner) {
		Configure.waitElementDisplay(3000);
		searchText(banner);
		boolean checkLocked = isElementDisplay(readElement(Interfaces.BannersPage.iconCheckedOut, banner));
		return checkCreatedBannerDisplays(banner) && checkLocked;
	}

	/**
	 * verify check banner successfully checked in and unlock
	 * 
	 * @param banner
	 * @return
	 */
	public boolean checkBannerSuccessfullyCheckedInAndUnlock(String banner) {
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("1 banner checked in.");
		boolean checkUnLocked = !isElementDisplay(readElement(Interfaces.BannersPage.iconCheckedOut, banner));
		return checkAlert && checkUnLocked;
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
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("Banner saved.");
		return checkAlert && displayNewBannerPage();
	}

	/**
	 * verify check new banners match input data
	 * 
	 * @param banner
	 * @param category
	 * @param client
	 * @return
	 */
	public boolean checkNewBannerMatchInputData(String banner, String category, String client) {
		Configure.waitElementDisplay(3000);
		searchText(banner);
		boolean checkBannerName = getTextElement(readElement(Interfaces.BannersPage.textBannerName, banner))
				.equals(banner);
		boolean checkCategory = getTextElement(readElement(Interfaces.BannersPage.textCategoryName, banner, "Category: " + category))
						.equals("Category: " + category);
		boolean checkClient = getTextElement(readElement(Interfaces.BannersPage.nameClient, banner, client))
				.equals(client);
		boolean result = checkBannerName && checkCategory && checkClient;
		return result;
	}

	/**
	 * verify A message : "Banner successfully saved" shows. verify Two new banners
	 * are created and matched with entered data
	 * 
	 * @param banner
	 * @param bannerNew
	 * @return
	 */
	public boolean checkTwoNewBannersAreCreatedAndMatchedEnteredData(String banner, String bannerNew, String category,
			String client) {
		Configure.waitElementDisplay(3000);
		boolean checkAlert = getTextElement(readElement(Interfaces.BannersPage.textAlertMessage))
				.equals("Banner saved.");
		boolean checkBanner = checkNewBannerMatchInputData(banner, category, client);
		boolean checkNewBanner = checkNewBannerMatchInputData(bannerNew, category, client);
		return checkAlert && checkBanner && checkNewBanner;

	}

	/**
	 * verify check new banner is created with out replacing the old banner
	 * 
	 * @param banner
	 * @param bannerEdit
	 * @return
	 */
	public boolean checkNewBannerIsCreatedWithoutReplacingTheOldBanner(String banner, String bannerEdit) {
		searchText(banner);
		boolean checkBanner = displayText(banner);
		searchText(bannerEdit);
		boolean checkBannerEdit = displayText(bannerEdit);
		return checkBanner && checkBannerEdit;
	}

	/**
	 * verify check banner is not created
	 * 
	 * @param client
	 * @param category
	 * @return
	 */
	public boolean checkBannerIsNotCreated(String client, String category) {
		boolean result = false;
		selectClient(client);
		selectCategory(category);
		result = isElementDisplay(readElement(Interfaces.BannersPage.textNoMatchingResult));
		return result;
	}

	/**
	 * verify check item per page
	 * @param item
	 * @return
	 */
	public boolean checkItemPerPage(String item) {
		int quantityItem = Integer.parseInt(item);
		if (countElements(readElement(Interfaces.BannersPage.rowBanners)) == quantityItem)
			return true;
		return false;
	}

	/**
	 * verify check quantity of items displayed in table is changed
	 * @param item
	 * @return
	 */
	public boolean checkQuantityOfItemsDisplayedInTableIsChanged(String item) {
		Configure.waitElementDisplay(3000);
		boolean checkItemPerPage = false;
		boolean checkQuantityItem = false;
		int pagination = pagination(readElement(Interfaces.BannersPage.countPaginationBanner));
		int quantityItem = Integer.parseInt(item);
		if (pagination > 0) {
			while (isElementDisplay(readElement(Interfaces.BannersPage.btnNextPagination)))
				if (isElementDisplay(readElement(Interfaces.BannersPage.btnNextPagination))) {
					scrollElement(readElement(Interfaces.BannersPage.btnNextPagination));
					click(readElement(Interfaces.BannersPage.btnNextPagination));
					if (isElementDisplay(readElement(Interfaces.BannersPage.btnNextPagination)))
						checkItemPerPage = checkItemPerPage(item);
				}
		}
		if (!isElementDisplay(readElement(Interfaces.BannersPage.btnNextPagination))) {
			if (countElements(readElement(Interfaces.BannersPage.rowBanners)) <= quantityItem)
				checkQuantityItem = true;
		}
		return checkItemPerPage && checkQuantityItem;
	}
	
	/**
	 * Verify check client page displays"
	 */
	public boolean checkClientPageDisplays() {
		Configure.waitElementDisplay(3000);
		boolean checkTitle = Configure.driver.getTitle().equals("Banners: Clients - joomla - Administration");
		return checkTitle;
	}
}
