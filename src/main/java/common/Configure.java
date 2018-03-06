package common;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Configure {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static int timeOutPageLoad;
	public static int timeOutElementLoad;
	public static String browser;

	/**
	 * choose browser, time out page load, time out element load
	 * 
	 * @param browser
	 * @param timeOutPageLoad
	 * @param timeOutElementLoad
	 */
	@SuppressWarnings("static-access")
	public void beforeTest(String browser, int timeOutPageLoad, int timeOutElementLoad) {
		this.timeOutPageLoad = timeOutPageLoad;
		this.timeOutElementLoad = timeOutElementLoad;
		this.browser = browser;
		Driver driver = new Driver();
		String chooseBrowser;
		switch (browser) {
		case "Chrome":
			chooseBrowser = "Chrome";
			break;
		case "Firefox":
			chooseBrowser = "Firefox";
			break;
		case "IE":
			chooseBrowser = "IE";
			break;
		default:
			chooseBrowser = "Chrome";
			break;
		}
		driver.setUp(chooseBrowser);
		Configure.driver = driver.driver;
		Configure.wait = driver.wait;
	}

	/**
	 * wait page load
	 * 
	 * @param timeOut
	 */
	public static void waitPageLoad(int timeOut) {
		Configure.driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * wait element display
	 * 
	 * @param timeOut
	 */
	public static void waitElementDisplay(int timeOut) {
		Configure.driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * close browser
	 */
	public void closeBrowser() {
		Configure.driver.close();
		Configure.driver.quit();
	}

	/**
	 * Wait with condition and timeout
	 * 
	 * @param timeOut
	 * @param condition
	 */
	@SuppressWarnings("unchecked")
	public static void waitCondition(int timeOut, Object condition) {
		Configure.wait.withTimeout(timeOut, TimeUnit.SECONDS);
		Configure.wait.until((Function<? super WebDriver, ExpectedConditions>) condition);
	}

	/**
	 * verify true
	 * 
	 * @param condition
	 * @return
	 */
	public boolean verifyTrue(Boolean condition) {
		try {
			Assert.assertTrue(condition);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * verify false
	 * 
	 * @param condition
	 * @return
	 */
	public boolean verifyFalse(Boolean condition) {
		try {
			Assert.assertFalse(condition);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * setup Test
	 * 
	 * @param browser
	 * @param timeOutPageLoad
	 * @param timeOutElementLoad
	 */
	public void configture(String browser, String timeOutPageLoad, String timeOutElementLoad) {
		int timeOutEle = Integer.parseInt(timeOutElementLoad);
		int timeOutPage = Integer.parseInt(timeOutPageLoad);
		beforeTest(browser, timeOutEle, timeOutPage);
		waitPageLoad(timeOutPage);
	}

	/**
	 * setup Test
	 */
	@SuppressWarnings("static-access")
	public void configture() {
		beforeTest(browser, this.timeOutElementLoad, this.timeOutPageLoad);
		waitPageLoad(this.timeOutPageLoad);
	}
	
}
