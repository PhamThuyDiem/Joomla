package testCase;

import org.testng.annotations.Test;

import common.Configure;
import common.Constant;
import pageAction.BannersPageAction;
import pageAction.CategoriesPageAction;
import pageAction.ClientsPageAction;
import pageAction.IndexPageAction;
import pageAction.LoginPageAction;
import pageAction.NewBannersPageAction;
import pageAction.NewCategoriesPageAction;
import pageAction.NewClientsPageAction;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class BANNERS_TC005_VerifyThatUserCanArchiveABanner extends Configure{
	public LoginPageAction loginPageAction;
	public IndexPageAction indexPageAction;
	public ClientsPageAction clientsPageAction;
	public NewClientsPageAction newClientsPageAction;
	public CategoriesPageAction categoriesPageAction;
	public NewCategoriesPageAction newCategoriesPageAction;
	public BannersPageAction bannersPageAction;
	public NewBannersPageAction newBannersPageAction;

	private static Logger Log = Logger.getLogger(Logger.class.getName());

	@Test(dataProvider = "Authentication")
	public void login(String url, String user, String password, String clientName ,String contactName, String contactEmail, 
			String title, String bannerName, String category) {

		Log.info("-----------TC_JOOMLA_BANNERS_005-------------");
		Log.info("Step 1: Navigate to the URL: http://192.168.189.197:1081/joomla/administrator/");
		loginPageAction.indexPage(url);

		Log.info("Step 2 : Login with User Name and password");
		loginPageAction.inputUsernamePassword(user, password);

		Log.info("Step 3: Click Login in button");
		loginPageAction.clickLogin();

		Log.info("Step 4: Select Components -> Banners -> Clients");
		indexPageAction.openPage("Components>Banners>Clients");
		
		Log.info("Step 5: Click New button in the right top cornera");
		clientsPageAction.clickNew();
		
		Log.info("Step 6: Enter valid Client Name to Client Name textbox");
		newClientsPageAction.inputName(clientName);
		
		Log.info("Step 7: Enter valid Client Name to Client Name textbox");
		newClientsPageAction.inputContactName(contactName);
		
		Log.info("Step 8: Enter valid Contact Email to Contact Email textbox");
		newClientsPageAction.inputContactEmail(contactEmail);
		
		Log.info("Step 9: Click Save & Close button");
		newClientsPageAction.clickSaveClose();
		
		Log.info("Step 10: A message : Client successfully saved shows and new client is created");
		verifyTrue(clientsPageAction.clientSuccessfullySaved(clientName));
		
		Log.info("Step 11: Select Components -> Banners -> Categories");
		indexPageAction.openPage("Components>Banners>Categories");
		
		Log.info("Step 12: Click New button in the right top corner");
		categoriesPageAction.clickNew();
		
		Log.info("Step 13: Enter valid Category Name to Name textbox");
		newCategoriesPageAction.inputTitle(title);
		
		Log.info("Step 14: Click Save & Close button");
		newCategoriesPageAction.clickSaveClose();
		
		Log.info("Step 15: A message : Category successfully saved shows and new category is created");
		verifyTrue(categoriesPageAction.categorySuccessfullySaved(title));
		
		Log.info("Step 16: Select Components -> Banners -> Banners");
		indexPageAction.openPage("Components>Banners>Banners");
		
		Log.info("Step 17: Click New button in the right top corner");
		bannersPageAction.clickNew();
		
		Log.info("Step 18: Enter valid Name to Name textbox");
		newBannersPageAction.inputName(bannerName);
		
		Log.info("Step 19: Select a Category in Category dropdown list");
		newBannersPageAction.selectCategory(category);
		
		Log.info("Step 20: Select a Client in Client dropdown list");
		newBannersPageAction.selectClient(clientName);
		
		Log.info("Step 21: Click Save & Close button");
		newBannersPageAction.clickSaveClose();
		
		Log.info("Step 22: A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannersPageAction.checkBannerSuccessfullySaved(bannerName));
		
		Log.info("Step 23: Check the banner recently created");
		bannersPageAction.checkboxBanners(bannerName);
		
		Log.info("Step 24: Click Archive button in the right top corner");
		bannersPageAction.clickArchive();
		
		Log.info("Step 25: A message : 1 banner successfully archived shows");
		verifyTrue(bannersPageAction.checkBannerSuccessfullyArchived());
		
		Log.info("Step 26: Select Archive in Status dropdown list");
		bannersPageAction.selectStatus("Archive");
		
		Log.info("Step 27: Verify banner is archived");
		verifyTrue(bannersPageAction.checkBannerIsArchived(bannerName));
		
		Reporter.log("Verify that user can archive a banner");
	}

	@Parameters({ "browser", "timeOutPageLoad", "timeOutElementLoad" })
	@BeforeMethod
	public void beforeMethod(String browser, String timeOutPageLoad, String timeOutElementLoad) {
		configture(browser, timeOutPageLoad, timeOutElementLoad);
		loginPageAction = new LoginPageAction();
		indexPageAction = new IndexPageAction();
		clientsPageAction = new ClientsPageAction();
		newClientsPageAction = new NewClientsPageAction();
		categoriesPageAction = new CategoriesPageAction();
		newCategoriesPageAction = new NewCategoriesPageAction();
		bannersPageAction = new BannersPageAction();
		newBannersPageAction = new NewBannersPageAction();
	}

	@AfterMethod
	public void afterMethod() {
		Log.info("Close browser");
		closeBrowser();
	}

	@DataProvider(name = "Authentication")
	public Object[][] credentials() throws Exception {
		return new Object[][] { new Object[] { Constant.TC_JOOMLA_BANNERS_BANNERS_005.getUrl,
				Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtUser, Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtPassword,
				Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtClientName, Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtContactName,
				Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtContactEmail, Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtTitle,
				Constant.TC_JOOMLA_BANNERS_BANNERS_005.txtName, Constant.TC_JOOMLA_BANNERS_BANNERS_005.cbbCategory}, };
	}

}
