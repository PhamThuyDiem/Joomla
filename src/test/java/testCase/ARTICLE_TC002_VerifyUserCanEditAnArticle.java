package testCase;

import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.Constant;
import common.Configure;
import pageAction.ArticlesPageAction;
import pageAction.IndexPageAction;
import pageAction.LoginPageAction;
import pageAction.NewArticlesPageAction;

public class ARTICLE_TC002_VerifyUserCanEditAnArticle extends Configure{
	public LoginPageAction loginPageAction;
	public IndexPageAction indexPageAction;
	public ArticlesPageAction articlesPageAction;
	public NewArticlesPageAction newArticlesPageAction;

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	
	@Parameters({ "browser", "timeOutPageLoad", "timeOutElementLoad" })
	@BeforeMethod
	public void beforeMethod(String browser, String timeOutPageLoad, String timeOutElementLoad) {
		configture(browser, timeOutPageLoad, timeOutElementLoad);
		loginPageAction = new LoginPageAction();
		indexPageAction = new IndexPageAction();
		articlesPageAction = new ArticlesPageAction();
		newArticlesPageAction = new NewArticlesPageAction();
	}
	
	@Test(dataProvider = "Authentication")
	public void login(String url, String user, String password, String title, String category,String content, String newTitle,
			String newCategory, String newContent) {

		Log.info("-----------TC_JOOMLA_ARTICLE_002-------------");
		Log.info("Step 1: Navigate to the URL: http://192.168.189.197:1081/joomla/administrator/");
		loginPageAction.indexPage(url);

		Log.info("Step 2 :Enter valid username into Username field");
		loginPageAction.inputUsername(user);

		Log.info("Step 3: Enter valid password into Password field");
		loginPageAction.inputPassword(password);

		Log.info("Step 4: Click on 'Log in' button");
		loginPageAction.clickLogin();

		Log.info("Step 5: Select Content > Articles");
		articlesPageAction.openPage("Content>Articles");

		Log.info("Step 6:Click on 'New' icon of the top right toolbar");
		articlesPageAction.clickNew();

		Log.info("Step 7: Enter a title on 'Title' field");
		newArticlesPageAction.inputTitle(title);

		Log.info("Step 8: Select an item from the 'Category' dropdown list");
		newArticlesPageAction.selectCategory(category);

		Log.info("Step 9: Enter value on 'Article Text' text area");
		newArticlesPageAction.inputContent(content);

		Log.info("Step 10: Click on 'Save & Close' icon of the top right toolbar");
		newArticlesPageAction.clickSaveClose();

		Log.info("Step 11: Verify the article is saved successfully");
		verifyTrue(articlesPageAction.checkSavedSuccessfully(title));

		Log.info("Step 12: Select Content > Articles");
		articlesPageAction.openPage("Content>Articles");
		
		Log.info("Step 13: Check on the recently added article's checkbox");
		articlesPageAction.checkboxArticle(title);
		
		Log.info("Step 14: Click on 'Edit' icon of the top right toolbar");
		articlesPageAction.clickEdit();
		
		Log.info("Step 15: Enter a new title on 'Title' text field");
		newArticlesPageAction.inputTitleNew(newTitle);
		
		Log.info("Step 16: Select a new item from the 'Category' dropdown list");
		newArticlesPageAction.selectCategory(newCategory);
		
		Log.info("Step 17: Enter value on 'Article Text' text area");
		newArticlesPageAction.inputContent(newContent);
		
		Log.info("Step 18: Click on 'Save & Close' icon of the top right toolbar");
		newArticlesPageAction.clickSaveClose();
		
		Log.info("Step 19: Verify the article is saved successfully");
		verifyTrue(articlesPageAction.checkEditSuccessfully(newTitle));
		
		Reporter.log("Verify user can edit an article");
	}

	@AfterMethod
	public void afterMethod() {
		Log.info("Close browser");
		closeBrowser();
	}

	@DataProvider(name = "Authentication")
	public Object[][] credentials() throws Exception {
		return new Object[][] { new Object[] { Constant.TC_JOOMLA_ARTICLE_002.getUrl,
				Constant.TC_JOOMLA_ARTICLE_002.txtUser, Constant.TC_JOOMLA_ARTICLE_002.txtPassword,
				Constant.TC_JOOMLA_ARTICLE_002.txtTitle, Constant.TC_JOOMLA_ARTICLE_002.cbbCategory ,
				Constant.TC_JOOMLA_ARTICLE_002.textContent, Constant.TC_JOOMLA_ARTICLE_002.txtTitleNew,
				Constant.TC_JOOMLA_ARTICLE_002.cbbCategoryNew, Constant.TC_JOOMLA_ARTICLE_002.contentNew}, };
	}
}
