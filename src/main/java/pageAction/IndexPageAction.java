package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonAction;
import common.Configure;
import interfaces.Interfaces;

public class IndexPageAction extends CommonAction {

	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;

	/**
	 * read file Xml
	 */
	public IndexPageAction() {
		readXmlFile("indexPageElement");
	}

	/**
	 * negative index page
	 * 
	 * @param path
	 */
	public void indexPage(String path) {
		Configure.driver.get(path);
	}


	/**
	 * open toggle
	 * 
	 * @param toggle
	 */
	public void openToggle(String toggle) {
		click(readElement(Interfaces.IndexPage.menu, toggle));
	}

	/**
	 * open toggle > panel
	 * 
	 * @param toggle
	 * @param panel
	 */
	public void openPanel(String toggle, String panel) {
		click(readElement(Interfaces.IndexPage.menu, toggle));
		click(readElement(Interfaces.IndexPage.openPanel1, toggle, panel));
	}

	/**
	 * open toggle > panel > panel
	 * 
	 * @param toggle
	 * @param panel1
	 * @param panel2
	 */
	public void openPanel1(String toggle, String panel1, String panel2) {
		click(readElement(Interfaces.IndexPage.menu, toggle));
		focus(readElement(Interfaces.IndexPage.openPanel1, toggle, panel1));

		if (Configure.browser.equals("IE"))
			getURL(getHref(readElement(Interfaces.IndexPage.openPanel2, toggle, panel1, panel2)));
		else
			click(readElement(Interfaces.IndexPage.openPanel2, toggle, panel1, panel2));

	}

	/**
	 * level panel
	 * 
	 * @param path
	 */
	public void openPage(String path) {
		Configure.waitElementDisplay(3000);
		int count = 0;
		String tmp3 = "";
		String tmp2 = "";
		String tmp1 = "";
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '>')
				count++;
		}
		if (count == 0) {
			tmp1 = path.split(">")[0];
			openToggle(tmp1);
		}
		if (count == 1) {
			tmp1 = path.substring(0, path.indexOf('>'));
			tmp2 = path.split(">")[1];
			openPanel(tmp1, tmp2);
		}

		if (count == 2) {
			tmp1 = path.substring(0, path.indexOf('>'));
			tmp2 = path.split(">")[1];
			tmp3 = path.split(">")[2];
			openPanel1(tmp1, tmp2, tmp3);
		}
	}

	public static void main(String args[]) {
	}
}