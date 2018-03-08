package common;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import common.Configure;

public class CommonAction {
	public WebDriver driver;
	public WebDriverWait wait;
	public static WebElement element;
	public Document configFile;
	public Configure configure;


	/**
	 * read Xml file
	 * 
	 * @param location
	 */
	public void readXmlFile(String location) {
		try {
			File configFile = new File("src/main/java/interfaces/" + location + ".xml");
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			this.configFile = dBuilder.parse(configFile);
		} catch (Exception e) {
			System.out.println(e.toString());
			this.configFile = null;
		}
	}

	/**
	 * read element
	 * 
	 * @param name
	 * @param var
	 * @return
	 */
	public By readElement(String name, String... var) {
		By path = null;
		try {
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
			XPathExpression expr = xpath.compile("//Element[@name=\"" + name + "\"]");
			Element nNode = (Element) expr.evaluate(configFile, XPathConstants.NODE);
			String content = nNode.getElementsByTagName("Address").item(0).getTextContent();
			String by = nNode.getElementsByTagName("By").item(0).getTextContent();
			for (int i = 0; i < var.length; i++) {
				content = content.replace("{" + i + "}", var[i]);
			}
			switch (by) {
			case "ByXPath":
				return path = By.xpath(content);
			case "ByCssSelector":
				return path = By.cssSelector(content);
			case "ByID":
				return path = By.id(content);
			case "ByName":
				return path = By.name(content);
			}
			return path;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * event click
	 * 
	 * @param path
	 */
	public void click(By path) {
		if (Configure.browser.equals("IE")) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Configure.waitElementDisplay(3000);
		ExpectedConditions.presenceOfElementLocated(path);
		element = Configure.driver.findElement(path);
		if (!Configure.browser.equals("Chrome")) {
			new Actions(Configure.driver).moveToElement(element).perform();
			Configure.waitCondition(30, ExpectedConditions.elementToBeClickable(path));
		}
		try {
			element.click();
		}
		catch (Exception e) {
			// TODO: handle exception
			scrollElement(path);
			scrollElement();
			element.click();
		}
		
	}
	
	/**
	 * focus into element
	 * @param path
	 */
	public void focus(By path) {
		if (Configure.browser.equals("IE")) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Configure.waitElementDisplay(30);
		ExpectedConditions.presenceOfElementLocated(path);
		element = Configure.driver.findElement(path);
		new Actions(Configure.driver).moveToElement(element).perform();
	}

	/**
	 * input text
	 * 
	 * @param path
	 * @param values
	 */
	public void sendkeys(By path, String values) {
		
		if (Configure.browser.equals("IE")) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Configure.waitElementDisplay(30);
		Configure.waitCondition(30, ExpectedConditions.presenceOfElementLocated(path));
		element = Configure.driver.findElement(path);
		if (Configure.browser.equals("IE")) {
			new Actions(Configure.driver).moveToElement(element).perform();
			Configure.waitCondition(3000, ExpectedConditions.elementToBeClickable(path));
		}
		element.clear();
		element.sendKeys(values);
	}

	/**
	 * get URL
	 * 
	 * @param path
	 */
	public void getURL(String path) {
		Configure.waitPageLoad(30);
		Configure.driver.get(path);
	}

	/**
	 * Verify is element display
	 * 
	 * @param path
	 * @return
	 */
	public boolean isElementDisplay(By path) {
		try {
			if (Configure.browser.equals("IE"))
				Thread.sleep(3000);
			Configure.waitElementDisplay(30);
			Configure.waitCondition(30, ExpectedConditions.presenceOfElementLocated(path));
			element = Configure.driver.findElement(path);
			if (!Configure.browser.equals("Chrome")) {
				new Actions(Configure.driver).moveToElement(element).perform();
				Configure.waitCondition(30, ExpectedConditions.elementToBeClickable(path));
			}
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * Waiting for until satisfying conditions
	 * 
	 * @param timeOut
	 *            for waiting
	 * @param condition
	 * @return Result of satisfying conditions in timeOut
	 */
	@SuppressWarnings("unchecked")
	public void wait(int timeOut, Object condition) {
		Configure.wait.withTimeout(timeOut, TimeUnit.SECONDS);
		Configure.wait.until((Function<? super WebDriver, ExpectedConditions>) condition);
	}

	/**
	 * switch to frame by path
	 * 
	 * @param path
	 */
	public void switchToFrame(By path) {
		Configure.waitElementDisplay(30);
		WebElement element = Configure.driver.findElement(path);
		Configure.driver.switchTo().frame(element);
	}

	/**
	 * switch to frame by index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		Configure.driver.switchTo().frame(index);
	}

	/**
	 * switch to frame by name
	 * 
	 * @param name
	 */
	public void switchToFrame(String name) {
		Configure.driver.switchTo().frame(name);
	}

	/**
	 * switch back
	 */
	public void switchBack() {
		Configure.driver.switchTo().defaultContent();
	}
	
	/**
	 * verify check display popup
	 * @param title
	 * @return true/false
	 */
	public boolean checkDisplayPopUp(String title) {
		boolean result = false;
		String MainWindow = Configure.driver.getWindowHandle();
		// To handle all new opened window.
		Set<String> s1 = Configure.driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				// Switching to Child window
				Configure.driver.switchTo().window(ChildWindow);
				// Closing the Child Window.
				Configure.driver.getTitle().equals(title);
				Configure.driver.close();
				result = true;
			}
		}
		// Switching to Parent window i.e Main Window.
		Configure.driver.switchTo().window(MainWindow);
		return result;
	}

	/**
	 * count elements in page
	 * @param path
	 * @return
	 */
	public int countElements(By path) {
		return Configure.driver.findElements(path).size();
	}
	
	/**
	 * quantity pagination
	 * @param path
	 * @return
	 */
	public int pagination(By path) {
		return Configure.driver.findElements(path).size()-4;
	}
	
	/**
	 * scroll element
	 * @param path
	 */
	public void scrollElement(By path) {
		element = Configure.driver.findElement(path);
		((JavascriptExecutor) Configure.driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/**
	 * scroll bottom
	 */
	public void scrollBottom() {
		((JavascriptExecutor) Configure.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
	}
	
	/**
	 * scroll position
	 */
	public void scrollElement() {
		((JavascriptExecutor) Configure.driver).executeScript("window.scrollBy(0,-200)", "");
	}
	
	/**
	 * scroll top
	 */
	public void scrollTop() {
		((JavascriptExecutor) Configure.driver).executeScript("window.scroll(0,0)", "");
	}
	
	/**
	 * index element in table
	 * @param path
	 * @return
	 */
	public int indexElement(By path) {
		return Configure.driver.findElements(path).size()+1;
	}
	
	/**
	 * get text element
	 * @param path
	 * @return
	 */
	public String getTextElement(By path) {
		element = Configure.driver.findElement(path);
		return element.getText();
	}
	
	/**
	 * get title of page
	 * @return
	 */
	public String getTitle() {
		return Configure.driver.getTitle();
	}
	
	/**
	 * get background color
	 * @param path
	 * @return
	 */
	public String getBackgroudColor(By path) {
		String color = Configure.driver.findElement(path).getCssValue("background-color");
		return color;
	}
	
	/**
	 * get href in path
	 * @param path
	 * @return
	 */
	public String getHref(By path) {
		element = Configure.driver.findElement(path);
		return element.getAttribute("href");
	}
	
	/**
	 * get data option array index
	 * @param path
	 * @return
	 */
	public String getDataOptionArrayIndex(By path) {
		element = Configure.driver.findElement(path);
		return element.getAttribute("data-option-array-index");
	}
}
