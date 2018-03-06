package testCase;

import org.testng.annotations.Test;

import common.Configure;
import common.Constant;
import pageAction.ArticlesPageAction;
import pageAction.IndexPageAction;
import pageAction.LoginPageAction;
import pageAction.NewArticlesPageAction;

import org.testng.annotations.BeforeMethod;

import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class ARTICLE_TC009_VerifyUserCanSearchForArticlesUsingTheFilterTextField extends Configure {
	public LoginPageAction loginPageAction;
	public IndexPageAction indexPageAction;
	public ArticlesPageAction articlesPageAction;
	public NewArticlesPageAction newArticlesPageAction;

	private static Logger Log = Logger.getLogger(Logger.class.getName());

	@Test(dataProvider = "Authentication")
	public void login(String url, String user, String password, String title, String category, String status,
			String content, String txtTitleFilter) {

		Log.info("-----------TC_JOOMLA_ARTICLE_009-------------");
		Log.info("Step 1: Navigate to the URL: http://192.168.189.197:1081/joomla/administrator/");
		loginPageAction.indexPage(url);

		Log.info("Step 2 :Enter valid username into Username field");
		loginPageAction.inputUsername(user);

		Log.info("Step 3: Enter valid password into Password field");
		loginPageAction.inputPassword(password);

		Log.info("Step 4: Click on 'Log in' button");
		loginPageAction.clickLogin();

		Log.info("Step 5: Select Content > Articles");
		indexPageAction.openPage("Content>Articles");

		Log.info("Step 6:Click on 'New' icon of the top right toolbar");
		articlesPageAction.clickNew();

		Log.info("Step 7: Enter a title on 'Title' field");
		newArticlesPageAction.inputTitle(title);

		Log.info("Step 8: Select an item from the 'Category' dropdown list");
		newArticlesPageAction.selectCategory(category);

		Log.info("Step 9: Select 'Published' item from 'Status' dropdown list");
		newArticlesPageAction.selectStatus(status);

		Log.info("Step 10: Enter value on 'Article Text' text area");
		newArticlesPageAction.inputContent(content);

		Log.info("Step 11: Click on 'Save & Close' icon of the top right toolbar");
		newArticlesPageAction.clickSaveClose();

		Log.info("Step 12: Verify the article is saved successfully");
		verifyTrue(articlesPageAction.checkSavedSuccessfully(title));

		Log.info("Step 13: Enter a title on 'Filter' text field");
		articlesPageAction.inputFilter(txtTitleFilter);
		
		Log.info("Step 14: Click on 'Search' button");
		articlesPageAction.clickSearch();
		
		Log.info("Step 15: Verify the titles of displayed articles are partially matched with the entered keyword");
		verifyTrue(articlesPageAction.checkTitleDisplay(txtTitleFilter));
		
		Reporter.log("Verify user can search for articles using the filter text field");
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
		return new Object[][] { new Object[] { Constant.TC_JOOMLA_ARTICLE_009.getUrl,
				Constant.TC_JOOMLA_ARTICLE_009.txtUser, Constant.TC_JOOMLA_ARTICLE_009.txtPassword,
				Constant.TC_JOOMLA_ARTICLE_009.txtTitle, Constant.TC_JOOMLA_ARTICLE_009.cbbCategory,
				Constant.TC_JOOMLA_ARTICLE_009.cbbStatus, Constant.TC_JOOMLA_ARTICLE_009.textContent,
				Constant.TC_JOOMLA_ARTICLE_009.txtTitleFilter}, };
	}
}
