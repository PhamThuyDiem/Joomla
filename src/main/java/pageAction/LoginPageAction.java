package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class LoginPageAction extends CommonAction{
	
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;
	
	/**
	 * read xml file
	 */
	public LoginPageAction() {
		readXmlFile("loginPageElement");
	}
	
	/**
	 * negative index page
	 * @param path
	 */
	public void indexPage(String path) {
		Configure.driver.get(path);
	}
	
	/**
	 * input user name
	 * @param user
	 */
	public void inputUsername(String user) {
		sendkeys(readElement(Interfaces.LoginPage.txtUsername), user);
	}

	/**
	 * input password
	 * @param password
	 */
	public void inputPassword(String password) {
		sendkeys(readElement(Interfaces.LoginPage.txtPasswd), password);
	}
	
	/**
	 * click btn login
	 */
	public void clickLogin() {
		click(readElement(Interfaces.LoginPage.btnLogin));
	}
	
	/**
	 * input username and password
	 * @param user
	 * @param password
	 */
	public void inputUsernamePassword(String user, String password) {
		inputUsername(user);
		inputPassword(password);
	}
}
