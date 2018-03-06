package testCase;

import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.Configure;
import common.Constant;
import pageAction.ArticlesPageAction;
import pageAction.IndexPageAction;
import pageAction.LoginPageAction;
import pageAction.NewArticlesPageAction;

public class ARTICLE_TC012_VerifyUserCanPagingTheArticlesUsingThePagingControl extends Configure{
	public LoginPageAction loginPageAction;
	public IndexPageAction indexPageAction;
	public ArticlesPageAction articlesPageAction;
	public NewArticlesPageAction newArticlesPageAction;

	private static Logger Log = Logger.getLogger(Logger.class.getName());

	@Test(dataProvider = "Authentication")
	public void login(String url, String user, String password, String item) {

		Log.info("-----------TC_JOOMLA_ARTICLE_012-------------");
		Log.info("Step 1: Navigate to the URL: http://192.168.189.197:1081/joomla/administrator/");
		loginPageAction.indexPage(url);

		Log.info("Step 2 :Enter valid username into Username field");
		loginPageAction.inputUsername(user);

		Log.info("Step 3: Enter valid password into Password field");
		loginPageAction.inputPassword(password);

		Log.info("Step 4: Click on 'Log in' button");
		loginPageAction.clickLogin();

		Log.info("Step 5: Select Content > Article Manager");
		indexPageAction.openPage("Content>Articles");
		
		Log.info("Step 6: Select 5 item of the 'Display' dropdown list at the footer section of the article table");
		articlesPageAction.listLimit(item);
		
		Log.info("Step 7: Verify the article table is paging into 5 articles per page");
		verifyTrue(articlesPageAction.checkArticlesPerPage(item));
		
		Log.info("Step 8: Select All item of the 'Display' dropdown list at the footer section of the article table");
		articlesPageAction.listLimit("All");
		
		Log.info("Step 9: Verify all articles are displayed in one page");
		verifyTrue(articlesPageAction.allArticlesPerPage());

		Reporter.log("Verify user can paging the articles using the paging control");
	}

	@Parameters({ "browser", "timeOutPageLoad", "timeOutElementLoad" })
	@BeforeMethod
	public void beforeMethod(String browser, String timeOutPageLoad, String timeOutElementLoad) {
		configture(browser, timeOutPageLoad, timeOutElementLoad);
		loginPageAction = new LoginPageAction();
		indexPageAction = new IndexPageAction();
		articlesPageAction = new ArticlesPageAction();
		newArticlesPageAction = new NewArticlesPageAction();
	}

	@AfterMethod
	public void afterMethod() {
		Log.info("Close browser");
		closeBrowser();
	}

	@DataProvider(name = "Authentication")
	public Object[][] credentials() throws Exception {
		return new Object[][] {
				new Object[] { Constant.TC_JOOMLA_ARTICLE_012.getUrl, Constant.TC_JOOMLA_ARTICLE_012.txtUser,
						Constant.TC_JOOMLA_ARTICLE_012.txtPassword, Constant.TC_JOOMLA_ARTICLE_012.item}, };
	}
}
