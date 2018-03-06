package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			DesiredCapabilities d = new DesiredCapabilities();
			d.setCapability("marionette", false); // to disable marionette, by default true
			driver = new FirefoxDriver(d); // to disable marionette, by default true
		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			driver = new InternetExplorerDriver();
			caps.setCapability("ignoreZoomSetting", true);
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
