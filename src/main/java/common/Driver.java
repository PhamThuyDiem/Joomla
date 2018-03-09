package common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
	public WebDriver driver;
	public WebDriverWait wait;

	/**
	 * setup browser
	 * 
	 * @param browser
	 */
	@SuppressWarnings("deprecation")
	public void setUp(String browser) {
		if (browser.equals("Firefox")) {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.191.228:4444/wd/hub"), capability);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (browser.equals("Chrome")) {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.191.228:4444/wd/hub"), capability);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.equals("IE")) {
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.191.228:4444/wd/hub"), capability);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Create a wait. All test and page classes use this wait.
		wait = new WebDriverWait(driver, 3000);

		// Maximize Window
		driver.manage().window().maximize();

	}

	/**
	 * delete coolies
	 */
	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * close browser
	 */
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

}
