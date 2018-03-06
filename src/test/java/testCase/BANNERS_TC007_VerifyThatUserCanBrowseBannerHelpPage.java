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

public class BANNERS_TC007_VerifyThatUserCanBrowseBannerHelpPage extends Configure{
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
	public void login(String url, String user, String password) {

		Log.info("-----------TC_JOOMLA_BANNERS_007-------------");
		Log.info("Step 1: Navigate to the URL: http://192.168.189.197:1081/joomla/administrator/");
		loginPageAction.indexPage(url);

		Log.info("Step 2 : Login with User Name and password");
		loginPageAction.inputUsernamePassword(user, password);

		Log.info("Step 3: Click Login in button");
		loginPageAction.clickLogin();

		Log.info("Step 4: Select Components -> Banners -> Banners");
		indexPageAction.openPage("Components>Banners>Banners");

		Log.info("Step 5: Click Help button in the right top corner");
		bannersPageAction.clickHelp();

		Log.info("Step 6: Banner help page appears");
		verifyTrue(bannersPageAction.displayHelpWindow());

		Reporter.log("Verify that user can browse Banner help page");
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
		return new Object[][] { new Object[] { Constant.TC_JOOMLA_BANNERS_BANNERS_007.getUrl,
				Constant.TC_JOOMLA_BANNERS_BANNERS_007.txtUser, Constant.TC_JOOMLA_BANNERS_BANNERS_007.txtPassword}, };
	}

}
