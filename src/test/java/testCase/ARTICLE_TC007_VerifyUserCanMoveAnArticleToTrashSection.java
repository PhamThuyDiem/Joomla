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

public class ARTICLE_TC007_VerifyUserCanMoveAnArticleToTrashSection extends Configure{

	public LoginPageAction loginPageAction;
	public IndexPageAction indexPageAction;
	public ArticlesPageAction articlesPageAction;
	public NewArticlesPageAction newArticlesPageAction;

	private static Logger Log = Logger.getLogger(Logger.class.getName());

	@Test(dataProvider = "Authentication")
	public void login(String url, String user, String password, String title, String category, String status, String content) {

		Log.info("-----------TC_JOOMLA_ARTICLE_007-------------");
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
		
		Log.info("Step 13: Check on the recently added article's checkbox");
		articlesPageAction.checkboxArticle(title);

		Log.info("Step 14: Click on 'Trash' icon of the top right toolbar");
		articlesPageAction.clickTrash();
		
		Log.info("Step 15: Verify the confirm message is displayed");
		verifyTrue(articlesPageAction.checkTrashedSuccessfully());
		
		Log.info("Step 16: Select 'Trash' item of 'Status' dropdown list");
		articlesPageAction.clickSearchTools("Trashed");
		
		Log.info("Step 17: Verify the deleted article is displayed on the table grid");
		verifyTrue(articlesPageAction.checkMoveAnArticleToTrash(title));
		
		Reporter.log("Verify user can move an article to trash section");
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
				new Object[] { Constant.TC_JOOMLA_ARTICLE_007.getUrl, Constant.TC_JOOMLA_ARTICLE_007.txtUser,
						Constant.TC_JOOMLA_ARTICLE_007.txtPassword, Constant.TC_JOOMLA_ARTICLE_007.txtTitle,
						Constant.TC_JOOMLA_ARTICLE_007.cbbCategory, Constant.TC_JOOMLA_ARTICLE_007.cbbStatus, 
						Constant.TC_JOOMLA_ARTICLE_007.textContent }, };
	}
	
}
