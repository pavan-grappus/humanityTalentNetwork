package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class GenericMethods {

	public GenericMethods(String driverpath, String ReportsPath, String ReportName) {
		this.webdriverPath = driverpath;
		this.logger = new Reports(ReportsPath, ReportName);
		logger.StartReports(true);
	}

	public WebDriver driver;
	Reports logger;
	String webdriverPath;
	String driveFileName = "";

	public void StartTest(String TestTitle, String TestName) {

		logger.StartTest(TestTitle, TestName);
	}

	public void EndTest() {
		logger.EndTest();
	}

	public void EndReports() {
		logger.EndReports();
	}

	public void logPass(String message) {
		logger.LogPass(message);
	}

	public void loginfo(String message) {
		logger.LogInfo(message);
	}

	public void logSkip(String message) {
		logger.LogSKIP(driver, message);
	}

	public void logFail(String message) {
		logger.LogFail(message);
	}

	private boolean browsers(String Browser) {
		switch (Browser.toLowerCase()) {
		case "chrome": {
			this.driveFileName = "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", webdriverPath + driveFileName);
			this.driver = new ChromeDriver();
			return true;
		}

		case "chromeinsecurecontent": {

			this.driveFileName = "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", webdriverPath + driveFileName);

			// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-web-security");
			options.addArguments("--allow-running-insecure-content");
			// capabilities.setCapability("chrome.binary", "./src//lib//chromedriver");
			// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			this.driver = new ChromeDriver(options);

			return true;
		}

		case "chrome incog": {
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("--incognito");
			// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			// System.setProperty("webdriver.chrome.driver", webdriverPath +
			// "\\chromedriver.exe");
			// this.driver = new ChromeDriver(capabilities);
			this.driveFileName = "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", webdriverPath + driveFileName);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			this.driver = new ChromeDriver(options);

			return true;
		}

		case "chrome headless": {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			this.driveFileName = "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", webdriverPath + driveFileName);
			this.driver = new ChromeDriver(options);

			return true;
		}

		case "html": {

			return true;
		}

		case "edge": {
			this.driveFileName = "msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", webdriverPath + driveFileName);
			this.driver = new EdgeDriver();
			return true;
		}

		case "firefox": {
			this.driveFileName = "geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", webdriverPath + driveFileName);
			this.driver = new FirefoxDriver();
			return true;
		}

		case "firefox headless": {
			this.driveFileName = "geckodriver.exe";
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			System.setProperty("webdriver.gecko.driver", webdriverPath + driveFileName);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);

			this.driver = new FirefoxDriver(firefoxOptions);
			return true;
		}

		default: {
			return false;
		}

		}

	}

	public WebElement findWebElement(By path) {
		List<WebElement> ListElement = driver.findElements(path);

		if (ListElement.isEmpty()) {
			throw new NoSuchElementException("Element not Present " + path);
		}

		if (ListElement.size() > 0) {
			WebElement element = ListElement.get(0);
			if (checkWebElementsisDisplayed(element) == true) {
				return element;
			} else {
				throw new ElementNotVisibleException(element.toString() + " is not Visble in the Page");
			}

		}
		throw new ElementNotVisibleException(path + " Size is Zero");

	}

	private boolean checkWebElementsisDisplayed(WebElement Element) {

		if (Element.isDisplayed()) {
			try {
				if (Element.getAttribute("naturalWidth").equals("0")) {
					logger.LogFail(driver, Element.toString() + "Image is not loaded or is broken");
					return false;
				} else {
					return true;

				}
			} catch (NullPointerException ex) {
				return true;
			}
		} else {
			logger.LogFail(driver, Element.toString() + "is not Displayed in the Page");
			return false;
		}
	}

	public void startindebugmode(String URL, String driverpath) {
		try {
			Runtime.getRuntime().exec("chrome.exe -remote-debugging-port=9015");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hold(20);

		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:9015");
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public void runindebugmode(String driverpath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:9015");
		// options.addArguments("--incognito");

		driver = new ChromeDriver(options);

	}

	public boolean OpenBrowser(String URL, String Browser) {

		try {
			if (browsers(Browser)) {
				driver.get(URL);
				driver.manage().window().maximize();
				// Dimension dimension = new Dimension(1920, 1080);
				// driver.manage().window().setSize(dimension);

				logger.LogPass(driver, "Successfully Opened the URL : " + URL + "in the broswer : " + Browser);
				return true;
			} else {
				logger.LogFail(driver, "Failed to Opened the URL : " + URL + "in the broswer : " + Browser);
				return false;
			}
		} catch (Exception e) {
			logger.LogFail(driver,
					"Unable to Opened the URL : " + URL + "in the broswer got the Exception message as " + e);
			return false;
		}

	}

	public boolean closeBrowser() {

		try {

			driver.close();
			logger.LogPass(driver, "Successfully Closed the BROWSER");
			return true;

		} catch (Exception e) {
			logger.LogFail(driver, "Unable to Close the BROWSER got the Excception " + e);
			return false;
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			loginfo("Alert is Present");
			return true;
		} catch (NoAlertPresentException Ex) {
			loginfo("Alert is NOT Present");
			return false;
		}
	}

	public boolean AlertDismiss() {
		try {
			driver.switchTo().alert().dismiss();
			logPass("Sucessfully dismissed the Alert");
			return true;
		} catch (Exception e) {
			logFail("Unable to Dismiss the Alert got the Exception " + e);
			return false;
		}
	}

	public boolean Alertaccept() {
		try {
			driver.switchTo().alert().accept();
			logPass("Sucessfully Accepted the Alert");
			return true;
		} catch (Exception e) {
			logFail("Unable to Accept the Alert got the Exception " + e);
			return false;
		}
	}

	public boolean switchFrame(String framevalue) {
		try {
			driver.switchTo().frame(framevalue);
			logger.LogPass(driver, "Sucessfully switched the Frame " + framevalue);
			return true;
		} catch (NoSuchFrameException e) {

			logger.LogFail(driver, "Unable to switch the frame to " + framevalue + " No such frame present");
			return false;
		} catch (Exception e) {
			logger.LogFail(driver, "Unable to switch the frame to " + framevalue + " got the Exception message " + e);
			return false;
		}
	}

	public boolean VerifyAlertmessage(String expectedText, String Note) {
		try {
			String actualText = driver.switchTo().alert().getText();

			if (Objects.equals(actualText, expectedText)) {

				logger.LogPass(driver, Note + " Alert Text is matching and is Equal \n ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return true;
			} else {
				logger.LogFail(driver, Note + " Alert Text is NOT matching and is NOT Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedText);
				return true;
			}
		} catch (Exception e) {
			logFail("Unable to Verify the Alert Text got the Exception " + e);
			return false;
		}
	}

	public boolean click(By path, String Note) {

		WebElement element = null;
		try {
			element = findWebElement(path);
			element.click();
			logger.LogPass(driver, "Clicked on the Elemennt : " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			scrollToElement(driver, element);
			try {
				element.click();
				logger.LogPass(driver, "Clicked on the Elemennt : " + Note);
				return true;
			} catch (org.openqa.selenium.ElementClickInterceptedException exc) {
				logger.LogFail(driver,
						"Failed to Click on the Element : " + Note + " got the Exception message " + exc);
				return false;
			}
		}
	}

	public boolean clickifPresent(By path, String Note) {

		WebElement element = null;
		try {

			int countOfElements = driver.findElements(path).size();

			if (countOfElements > 0) {
				element = findWebElement(path);
				scrollToElement(driver, element);
				element.click();
				logger.LogPass(driver, "Element is Present and Clicked on the Elemennt : " + Note);
				return true;
			} else {
				logger.LogPass(driver, "Element is not present and hence not Clicked on the Elemennt : " + Note);
				return true;
			}
		} catch (Exception e) {

			logger.LogFail(driver,
					"clickifPresent: Failed to Click on the Element : " + Note + " got the Exception message " + e);
			return false;
		}
	}

	public void scrollToElement(WebDriver webDriver, WebElement webElement) {
		try {
			Actions builder = new Actions(webDriver);
			builder.moveToElement(webElement);
			builder.build().perform();
		} catch (Exception e) {
			logger.LogInfo(e.getMessage());
		}
		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		} catch (Exception e) {
			logger.LogInfo(e.getMessage());
		}
	}

	public boolean clickNormal(By path, String Note) throws InterruptedException {

		WebElement element = null;
		try {

			element = driver.findElement(path);
			element.click();
			logger.LogPass(driver, "Clicked on the Elemennt : " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			scrollToElement(driver, element);
			try {
				element.click();
				logger.LogPass(driver, "Clicked on the Elemennt : " + Note);
				return true;
			} catch (org.openqa.selenium.ElementClickInterceptedException exc) {
				logger.LogFail(driver,
						"Failed to Click on the Element : " + Note + " got the Exception message " + exc);
				return false;
			}
		}
	}

	public boolean waitforElementVisible(By path, int waitTime, String Note) {

		double starttime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(path));
			double endtime = System.currentTimeMillis();
			logger.LogPass(driver, Note + " is Visible in the Page after waiting for "
					+ String.valueOf(endtime - starttime) + "milliseconds");
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			double endtime = System.currentTimeMillis();
			logger.LogFail(driver, Note + " is NOT Visible in the Page after waiting for "
					+ String.valueOf(endtime - starttime) + "milliseconds" + " got the Exception message as " + e);
			return false;
		}

	}

	public boolean waitforElementVisible_info(By path, int waitTime, String Note) {

		double starttime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(path));
			double endtime = System.currentTimeMillis();
			logger.LogInfo(Note + " is Visible in the Page after waiting for " + String.valueOf(endtime - starttime)
					+ "milliseconds");
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			double endtime = System.currentTimeMillis();
			logger.LogInfo(driver, Note + " is NOT Visible in the Page after waiting for "
					+ String.valueOf(endtime - starttime) + "milliseconds" + " got the Exception message as " + e);
			return false;
		}

	}

	public boolean waitForPageLoaded(int waitTime, String Note) {

		double starttime = System.currentTimeMillis();
		double endtime = 0;

		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			});
			endtime = System.currentTimeMillis();
			return true;
		} catch (Throwable error) {
			logger.LogFail(driver, Note + " Page Load NOT completed waited for " + String.valueOf(endtime - starttime)
					+ "milliseconds" + " got the Exception message as " + error);
			return false;
		}

	}

	public void hold(int time) {

		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			///
		}
	}

	public String getText(By path, String Note) {
		String actualText;
		try {
			actualText = findWebElement(path).getText().trim();

		} catch (ElementNotVisibleException exctemp) {

			actualText = driver.findElement(path).getText().trim();

		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail("Got the Exception message when fetching the Text from " + Note + " :" + exctemp);
			actualText = null;
		} catch (WebDriverException e) {
			logger.LogFail("Got the Exception message " + e);
			actualText = null;
		}
		logger.LogInfo("The Fetched Text is :" + actualText + " from the path " + Note);
		return actualText;
	}

	public List<String> getListofElementsText(By path, String Note) {

		List<String> actualList = new ArrayList<String>();
		try {
			List<WebElement> elementsList = driver.findElements(path);

			for (WebElement element : elementsList) {
				actualList.add(element.getText().trim());
			}

		} catch (Exception exctemp) {
			actualList = null;
			logger.LogFail("Got the Exception message for " + Note + " when Fetching the List" + exctemp);

		}
		logger.LogInfo("The Fetched List from the path " + Note + "\n" + actualList);
		return actualList;
	}

	public boolean verifyPageTitle(String expectedText) {

		String actualText;
		try {
			actualText = driver.getTitle();

			if (Objects.equals(actualText, expectedText)) {
				logger.LogPass(driver, "Page Title Text is matching and is Equal \n ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver, "Page Title Text is NOT Matching and is NOT Equal \n  ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return false;
			}
		} catch (Exception e) {
			logger.LogFail(driver, "Unable to verify the Page Title got the Exception message " + e.toString());
			return false;
		}

	}

	public String getAttributeValue(By path, String attribute, String Note) {
		String actualText;
		try {
			actualText = findWebElement(path).getAttribute(attribute);

		} catch (ElementNotVisibleException exctemp) {

			actualText = driver.findElement(path).getText().trim();

		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail("Got the Exception message " + exctemp);
			actualText = null;
		} catch (WebDriverException e) {
			logger.LogFail("Got the Exception message " + e);
			actualText = null;
		} catch (java.lang.NullPointerException e) {
			actualText = null;
		}
		logger.LogInfo("The Fetched Attribute for " + attribute + " is :" + actualText + " from the path " + Note);
		return actualText;
	}

	public boolean verifyElementText(By path, String expectedText, String Note) {

		try {
			String actualText = findWebElement(path).getText().trim();

			if (Objects.equals(actualText, expectedText)) {
				logger.LogPass(driver, Note + " Text is matching and is Equal \n ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver, Note + " Text is <b>NOT MATCHING</b> and is NOT Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementClickable(By path, String Note) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 1);
			wait.until(ExpectedConditions.elementToBeClickable(path));

			logger.LogPass(driver, "The Element " + Note + " is Clickable");
			return true;

		} catch (Exception exctemp) {
			logger.LogFail(driver, "The Element " + Note + " is Not  Clickable got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementisNOTClickable(By path, String Note) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 1);
			wait.until(ExpectedConditions.elementToBeClickable(path));

			logger.LogFail(driver, "The Element " + Note + " is Clickable which is Expected to not be Clickable");

			return true;

		} catch (Exception exctemp) {
			logger.LogPass(driver, "The Element " + Note + " is Not  Clickable as Expected");
			return false;
		}

	}

	public boolean mouseHowerOnElement(By path, String Note) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(findWebElement(path)).perform();
			hold(2);
			logger.LogPass(driver, "Sucessfully moved towards the Element " + Note);
			return true;
		} catch (Exception e) {
			logger.LogFail(driver,
					"Failed to move towards the Element " + Note + " got the Exception message " + e.toString());
			return false;
		}
	}

	public boolean verifyElementText_VisiblityDisabled(By path, String expectedText, String Note) {

		try {
			String actualText = driver.findElement(path).getText().trim();

			if (Objects.equals(actualText, expectedText)) {
				logger.LogPass(driver,
						Note + " Visiblilty Disabled check for Text is matching and is Equal \n ActualText: "
								+ actualText + "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver, Note
						+ "  Visiblilty Disabled Check for Text is <b>NOT MATCHING</b> and is NOT Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementTextNOTEquals(By path, String NOTexpectedText, String Note) {

		try {
			String actualText = findWebElement(path).getText().trim();

			if (!Objects.equals(actualText, NOTexpectedText)) {
				logger.LogPass(driver, Note + " Text is NOT matching as Expected and is NOT Equal \n ActualText: "
						+ actualText + "\nNOT a Expected Text : " + NOTexpectedText);
				return true;
			} else {

				logger.LogFail(driver,
						Note + " Text is <b>MATCHING</b> and is Equal  which is not Expected \n ActualText: "
								+ actualText + "\nNOT a Expected Text : " + NOTexpectedText);
				return false;
			}

		} catch (ElementNotVisibleException exctemp) {
			logger.LogFail(driver,
					"Failed to Verify the Text NOT Equals : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Verify the Text NOT Equals : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifymatch(String actualText, String expectedText, String Note) {

		try {

			if (actualText.matches(expectedText)) {
				logger.LogPass(driver, Note + " Text is matching against the Regex and is Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver,
						Note + " Text is <b>NOT MATCHING</b> against the Regex and is NOT Equal \n ActualText: "
								+ actualText + "\nExpected Text : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (Exception exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementTextPresentinList(By path, List<String> expectedList, String Note) {

		try {
			String actualText = findWebElement(path).getText().trim();

			if (expectedList.contains(actualText)) {
				logger.LogPass(driver, Note + " Text is matching and is Equal \n ActualText: " + actualText
						+ "\nExpected List : " + expectedList);
				return true;
			} else {

				logger.LogFail(driver, Note + " Text is <b>NOT MATCHING</b> and is NOT Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedList);
				return false;
			}

		} catch (ElementNotVisibleException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		} catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementText_contains(By path, String expectedText, String Note) {

		try {
			String actualText = findWebElement(path).getText().trim();

			if (actualText.contains(expectedText)) {
				logger.LogPass(driver,
						Note + " Text is matching \n ActualText: " + actualText + "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver, Note + " Text is <b>NOT MATCHING</b> and is NOT Equal \n ActualText: "
						+ actualText + "\nExpected Text : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyTextisNotEmpty(By path, String Note) {

		try {
			String pageText = driver.findElement(path).getText();

			if (pageText == null || pageText.isEmpty() || pageText.contentEquals("")) {

				logger.LogFail(driver, "Text is Not present in the Path " + Note);
				return false;

			} else {
				logger.LogPass(driver, "Text is present in the Path " + Note + " and the Text Value is " + pageText);
				return true;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver, "Failed to Verify the Text : " + Note + " got the Exception message " + e);
			return false;
		}

	}

	public boolean verifyElementCSSValue(By path, String propertyName, String expectedText, String Note) {

		try {
			String actualText;
			try {
				actualText = findWebElement(path).getCssValue(propertyName).trim();
			} catch (java.lang.NullPointerException e) {
				actualText = null;
			}

			if (Objects.equals(actualText, expectedText)) {
				logger.LogPass(driver,
						Note + " CSSProperty :" + propertyName + "is matching and is Equal \n ActualCSSProperty Value: "
								+ actualText + "\nExpectedCSSProperty Value : " + expectedText);
				return true;
			} else {

				logger.LogFail(driver,
						Note + " CSSProperty :" + propertyName
								+ " is <b>NOT MATCHING</b> and is NOT Equal \n ActualCSSProperty Value: " + actualText
								+ "\nExpectedCSSProperty Value : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Verify the CSSProperty : " + propertyName + " for " + Note
					+ " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementAttributeValue(By path, String Attribute, String expectedText, String Note) {

		try {
			String actualText;
			try {
				actualText = findWebElement(path).getAttribute(Attribute).trim();
			} catch (java.lang.NullPointerException e) {
				actualText = null;
			}

			if (Objects.equals(actualText, expectedText)) {

				expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;
				logger.LogPass(driver,
						Note + " Attribute :" + Attribute + "is matching and is Equal \n ActualAttribute Value: "
								+ actualText + "\nExpectedAttribute Value : " + expectedText);
				return true;
			} else {

				expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;
				logger.LogFail(driver,
						Note + " Attribute :" + Attribute
								+ " is <b>NOT MATCHING</b> and is NOT Equal \n ActualAttribute Value: " + actualText
								+ "\nExpectedAttribute Value : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;
			logger.LogFail(driver, "Failed to Verify the Attribute : " + Attribute + " for " + Note
					+ " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyElementAttributeValue_NotDisplayed(By path, String Attribute, String expectedText,
			String Note) {

		try {
			String actualText;
			try {
				actualText = driver.findElement(path).getAttribute(Attribute).trim();
			} catch (java.lang.NullPointerException e) {
				actualText = null;
			}

			if (Objects.equals(actualText, expectedText)) {

				expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;

				logger.LogPass(driver,
						Note + " Attribute :" + Attribute + "is matching and is Equal \n ActualAttribute Value: "
								+ actualText + "\nExpectedAttribute Value : " + expectedText);
				return true;
			} else {

				expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;

				logger.LogFail(driver,
						Note + " Attribute :" + Attribute
								+ " is <b>NOT MATCHING</b> and is NOT Equal \n ActualAttribute Value: " + actualText
								+ "\nExpectedAttribute Value : " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {

			expectedText = StringUtils.equals(expectedText, "") ? "[BLANK]" : expectedText;

			logger.LogFail(driver, "Failed to Verify the Attribute : " + Attribute + " for " + Note
					+ " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyEqual(Object actualText, Object expectedText, String Note) {

		try {

			if (Objects.equals(actualText, expectedText)) {

				logger.LogPass(Note + " Text is matching and is Equal \n ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return true;
			} else {

				logger.LogFail(Note + " Text is <b>NOT MATCHING</b> and is NOT Equal \n ActualText: " + actualText
						+ "\nExpected Text : " + expectedText);
				return false;
			}

		} catch (Exception exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public static File getLastModifiedfile(String directoryFilePath) {

		File directory = new File(directoryFilePath);
		File[] files = directory.listFiles(File::isFile);
		long lastModifiedTime = Long.MIN_VALUE;
		File chosenFile = null;

		if (files != null) {
			for (File file : files) {
				if (file.lastModified() > lastModifiedTime) {
					chosenFile = file;
					lastModifiedTime = file.lastModified();
				}
			}
		}

		return chosenFile;
	}

	public boolean verifyLastDownloadedfileNameMatches(String filename) {

		try {
			String downloadPath = Paths.get(System.getProperty("user.home"), "Downloads").toString();

			String lastModifiedFilename = getLastModifiedfile(downloadPath).getName();

			if (lastModifiedFilename.startsWith(FilenameUtils.getBaseName(filename))
					&& lastModifiedFilename.endsWith(FilenameUtils.getExtension(filename))) {
				logger.LogPass(driver, "LastModifiedfileName MATCHES \n ActualFile Name : " + lastModifiedFilename
						+ "\nExpected FileName :" + filename);
				return true;
			} else {
				logger.LogFail(driver, "LastModifiedfileName NOT MATCHES \n ActualFile Name : " + lastModifiedFilename
						+ "\nExpected FileName :" + filename);
				return false;
			}
		} catch (Exception e) {

			logger.LogFail(driver, "Unable to Vaerify the LastModifiedfileNameMatch :" + filename
					+ " got the Exception message " + e.toString());
			return false;
		}

	}

	public boolean verifyMatchonFileNames(String ActualFilename, String Expectedfilename, String Note) {
		try {

			if (ActualFilename.startsWith(FilenameUtils.getBaseName(Expectedfilename))
					&& ActualFilename.endsWith(FilenameUtils.getExtension(Expectedfilename))) {
				logger.LogPass(driver, Note + " File Name and the Extension MATCHES \n ActualFile Name : "
						+ ActualFilename + "\nExpected FileName :" + Expectedfilename);
				return true;
			} else {
				logger.LogFail(driver, Note + " File Name or the Extension NOT MATCHES \n ActualFile Name : "
						+ ActualFilename + "\nExpected FileName :" + Expectedfilename);
				return false;
			}
		} catch (Exception e) {

			logger.LogFail(driver, Note + "Unable to Verify the MatchonFileNames :" + Expectedfilename
					+ " got the Exception message " + e.toString());
			return false;
		}
	}

	public boolean verifyListofElementText(By path, Object[] expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			Object[] actualText = new Object[element.size()];

			for (int i = 0; i < element.size(); i++) {
				actualText[i] = element.get(i).getText().trim();
			}

			if (Arrays.equals(actualText, expectedText)) {
				logger.LogPass(driver, "The List of Option are Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return true;
			} else {
				logger.LogFail(driver, "The List of Option are NOT Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyListofElementsinnerText_attr(By path, Object[] expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			Object[] actualText = new Object[element.size()];

			for (int i = 0; i < element.size(); i++) {
				actualText[i] = element.get(i).getAttribute("innerText").trim();
			}

			if (Arrays.equals(actualText, expectedText)) {
				logger.LogPass(driver, "The List of Option are Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return true;
			} else {
				logger.LogFail(driver, "The List of Option are NOT Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Fetch the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyTextpresentinListofElementsinnerText_attr(By path, String expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			List<String> actualText = new ArrayList<String>();

			for (int i = 0; i < element.size(); i++) {
				actualText.add(element.get(i).getAttribute("innerText").trim());
			}

			if (actualText.contains(expectedText)) {
				logger.LogPass(driver, "The Expected Text is Present in the List of Fetched elements " + Note
						+ "\n Actual Options Fetched: " + actualText + " \n Expected options: " + expectedText);
				return true;
			} else {
				logger.LogFail(driver, "The Expected Text is NOT Present in the List of Fetched elements " + Note
						+ "\n Actual Options Fetched: " + actualText + " \n Expected options: " + expectedText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Fetch the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyTextNOTpresentinListofElementsinnerText_attr(By path, String expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			List<String> actualText = new ArrayList<String>();

			for (int i = 0; i < element.size(); i++) {
				actualText.add(element.get(i).getAttribute("innerText").trim());
			}

			if (actualText.contains(expectedText)) {
				logger.LogFail(driver, "The UnExpected Text is Present in the List of Fetched elements " + Note
						+ "\n Actual Options Fetched: " + actualText + " \n UnExpected options: " + expectedText);
				return false;
			} else {

				logger.LogPass(driver, "The Expected Text is NOT Present in the List of Fetched elements " + Note
						+ "\n Actual Options Fetched: " + actualText + " \n UnExpected options: " + expectedText);
				return true;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Fetch the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyListofElementText_sorted(By path, Object[] expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			Object[] actualText = new Object[element.size()];

			for (int i = 0; i < element.size(); i++) {
				actualText[i] = element.get(i).getText().trim();
			}

			Arrays.sort(expectedText);
			Arrays.sort(actualText);

			if (Arrays.equals(actualText, expectedText)) {
				logger.LogPass(driver, "The List of Option are Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return true;
			} else {
				logger.LogFail(driver, "The List of Option are NOT Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualText) + " \n Expected options: " + Arrays.toString(expectedText));
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyTextPresentinListofElements(By path, List<String> expectedList, String Note) {

		try {

			List<WebElement> element = new ArrayList<WebElement>();

			element = driver.findElements(path);

			List<String> actualText_List = new ArrayList<String>();

			for (int i = 0; i < element.size(); i++) {
				actualText_List.add(element.get(i).getText().trim());
			}

			if (expectedList.isEmpty()) {
				logger.LogPass(driver,
						"verifyTextPresentinListofElements -> The Expected Values is Empty and hence Not compared with the Actual elemets fetched from Web for "
								+ Note + "\n Actual Options: " + actualText_List + " \n Expected options: "
								+ expectedList);
				return true;
			}

			if (expectedList.size() > actualText_List.size()) {
				logger.LogFail(driver,
						"verifyTextPresentinListofElements-> The List of Data Fetched from Web are less when compared with Expected List"
								+ Note + "\n Actual Data_Text: " + actualText_List + " \n Expected Data_Text: "
								+ expectedList);
				return false;
			}

			if (actualText_List.containsAll(expectedList)) {

				logger.LogPass(driver,
						"verifyTextPresentinListofElements -> All the Expected Values are present in the List fetched from Web for "
								+ Note + "\n Actual Options: " + actualText_List + " \n Expected options: "
								+ expectedList);
				return true;
			} else {

				logger.LogFail(driver,
						"verifyTextPresentinListofElements -> All the Expected Values are NOT present in the List fetched from Web for "
								+ Note + "\n Actual Options: " + actualText_List + " \n Expected options: "
								+ expectedList);
				return false;

			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException | NoSuchElementException
				| java.lang.NullPointerException exctemp) {
			logger.LogFail(driver,
					"Failed to Verify TextPresentinListofElements : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyTextNOTPresentinListofElements(By path, List<String> expectedList, String Note) {

		try {

			List<WebElement> element = new ArrayList<WebElement>();

			element = driver.findElements(path);

			List<String> actualText_List = new ArrayList<String>();

			for (int i = 0; i < element.size(); i++) {
				actualText_List.add(element.get(i).getText().trim());
			}

			if (actualText_List.isEmpty() && expectedList.isEmpty()) {
				logger.LogFail(driver,
						"verifyTextNOTPresentinListofElements -> The UNExpected Values and Actual  are Empty " + Note
								+ "\n Actual Options: " + actualText_List + " \n UNExpected options: " + expectedList);
				return false;
			}

			for (String expText : expectedList) {
				if (actualText_List.contains(expText)) {

					logger.LogFail(driver,
							"verifyTextNOTPresentinListofElements -> The UNExpected Values is present in the List fetched from Web for "
									+ Note + "\n Actual Options: " + actualText_List + " \n UNExpected options: "
									+ expectedList);
					return false;
				}
			}

			for (String actText : actualText_List) {
				if (expectedList.contains(actText)) {

					logger.LogFail(driver,
							"verifyTextNOTPresentinListofElements -> The UNExpected Values is present in the List fetched from Web for "
									+ Note + "\n Actual Options: " + actualText_List + " \n UNExpected options: "
									+ expectedList);
					return false;
				}
			}

			logger.LogPass(driver,
					"verifyTextNOTPresentinListofElements -> All the UNExpected Values are NOT present in the List fetched from Web for "
							+ Note + "\n Actual Options: " + actualText_List + " \n UNExpected options: "
							+ expectedList);
			return true;

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException | NoSuchElementException
				| java.lang.NullPointerException exctemp) {
			logger.LogFail(driver,
					"Failed to Verify TextPresentinListofElements : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyListofElementText(By path, String expectedText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			Object[] actualarrayText = new Object[element.size()];
			Object[] expectedarrayText = new Object[element.size()];

			for (int i = 0; i < element.size(); i++) {
				actualarrayText[i] = element.get(i).getText().trim();
				expectedarrayText[i] = expectedText;
			}

			if (Arrays.equals(actualarrayText, expectedarrayText)) {
				logger.LogPass(driver,
						"The List of Text Values are Matching in " + Note + "\n Actual Options: "
								+ Arrays.toString(actualarrayText) + " \n Expected options: "
								+ Arrays.toString(expectedarrayText));
				return true;
			} else {
				logger.LogFail(driver,
						"The List of Option are NOT Matching in " + Note + "\n Actual Options: "
								+ Arrays.toString(actualarrayText) + " \n Expected options: "
								+ Arrays.toString(expectedarrayText));
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean verifyListofElementsContainsaText(By path, String expectedContainsText, String Note) {

		try {

			List<WebElement> element = driver.findElements(path);

			List<String> actualarrayText = new ArrayList<String>();

			for (int i = 0; i < element.size(); i++) {
				actualarrayText.add(element.get(i).getText().trim());
			}

			boolean Valiator = true;
			for (String text : actualarrayText) {
				if (!text.toLowerCase().contains(expectedContainsText.toLowerCase())) {
					Valiator = false;
					break;
				}
			}

			if (element.size() == 0) {
				logger.LogWarning(driver, "The Size of the List is Empty in " + Note + "\n Actual Options: "
						+ actualarrayText + " \n Expected Contains Value: " + expectedContainsText);
				return false;
			}

			if (Valiator) {
				logger.LogPass(driver,
						"The List of Text Fetched contains the Expected Value in " + Note + "\n Actual Options: "
								+ actualarrayText + " \n Expected Contains Value: " + expectedContainsText);
				return true;
			} else {
				logger.LogFail(driver,
						"The List of Text Fetched Doesn't contains the Expected Value in " + Note
								+ "\n Actual Options: " + actualarrayText + " \n Expected Contains Value: "
								+ expectedContainsText);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(driver, "Failed to Validate if the Expected Text is present in List of Elements : " + Note
					+ " got the Exception message " + exctemp);
			return false;
		}

	}

	public boolean setText(By path, String text, String Note) {

		try {
			WebElement element = findWebElement(path);
			element.clear();
			element.sendKeys(text);

			logger.LogPass(driver, "Cleared the Field and Entered the Text : " + text + " -> " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver,
					"Unable to Enter the Text " + text + " in the path " + Note + "got the Exception " + e);
			return false;
		}
	}

	public boolean clear(By path, String Note) {

		try {
			findWebElement(path).clear();

			logger.LogPass(driver, "Cleared the Field : " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver, "Unable to clear the Field " + Note + " in the path got the Exception " + e);
			return false;
		}
	}

	public boolean clearbyBackspace(By path, String Note) {

		try {
			String selectAll = Keys.chord(Keys.CONTROL, "a");

			WebElement element = findWebElement(path);

			element.sendKeys(selectAll);
			element.sendKeys(Keys.BACK_SPACE);
			logger.LogPass(driver, "Cleared the Field : " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver, "Unable to clear the Field " + Note + " in the path got the Exception " + e);
			return false;
		}
	}

	public boolean verifyListofOptionPresentByLabel(By path, Object[] ExpectedOptions, String Note) {
		try {
			WebElement webElement = findWebElement(path);

			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();

			Object[] actualOptions = new Object[options.size()];

			for (int i = 0; i < options.size(); i++) {
				actualOptions[i] = options.get(i).getText().trim();
			}

			if (Arrays.equals(actualOptions, ExpectedOptions)) {
				logger.LogPass(driver, "The List of Option are Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualOptions) + " \n Expected options: " + Arrays.toString(ExpectedOptions));
				return true;
			} else {
				logger.LogFail(driver, "The List of Option are NOT Matching in " + Note + "\n Actual Options: "
						+ Arrays.toString(actualOptions) + " \n Expected options: " + Arrays.toString(ExpectedOptions));
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver,
					"Unable to Verify the List of Dropdown options Got the Exception messsage " + e.toString());
			return false;
		}

	}

	public boolean verifyOptionPresentByLabel(By path, String ExpectedOptions, String Note) {
		try {
			WebElement webElement = findWebElement(path);

			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();

			List<String> actualOptions = new ArrayList<String>();

			for (WebElement option : options) {
				actualOptions.add(option.getText().trim());
			}

			if (!actualOptions.isEmpty()) {
				if (actualOptions.contains(ExpectedOptions)) {
					logger.LogPass(driver, "The Option is Present in the List " + Note + "\n Actual Options: "
							+ actualOptions + " \n Expected options: " + ExpectedOptions);
					return true;
				} else {
					logger.LogFail(driver, "The Option is NOT Present in the List " + Note + "\n Actual Options: "
							+ actualOptions + " \n Expected options: " + ExpectedOptions);
					return false;
				}
			} else {
				logger.LogFail(driver, "The Option is NOT Present in the List " + Note + "\n Actual Options: "
						+ actualOptions + " \n Expected options: " + ExpectedOptions);
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver,
					"Unable to Verify the options present in the List Got the Exception messsage " + e.toString());
			return false;
		}

	}

	public boolean verifyOptionNOTPresentByLabel(By path, String ExpectedOptions, String Note) {
		try {
			WebElement webElement = findWebElement(path);

			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();

			List<String> actualOptions = new ArrayList<String>();

			for (WebElement option : options) {
				actualOptions.add(option.getText().trim());
			}

			if (!actualOptions.isEmpty()) {
				if (actualOptions.contains(ExpectedOptions)) {
					logger.LogFail(driver, ExpectedOptions + " is Present in the List of Options " + Note + "Options:"
							+ actualOptions);
					return false;
				} else {
					logger.LogPass(driver, ExpectedOptions + " is <b>NOT</b> Present in the List of Options " + Note
							+ "Options:" + actualOptions);
					return true;
				}
			} else {
				logger.LogPass(driver, ExpectedOptions + " is <b>NOT</b> Present in the List of Options " + Note
						+ "Options:" + actualOptions);
				return true;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver,
					"Unable to Verify the List of Dropdown options Got the Exception messsage " + e.toString());
			return false;
		}

	}

	public boolean selectOptionByLabel(By path, String text, String Note) {
		try {

			WebElement webElement = findWebElement(path);

			Select select = new Select(webElement);

			select.selectByVisibleText(text);

			logger.LogPass(driver, "Selected the Option " + text + " from the Dropdown " + Note);

			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Select the Option " + text
					+ " from the Dropdown Got the Exception messsage " + e.toString());

			return false;
		}

	}

	public boolean selectOptionByValue(By path, String value, String Note) {
		try {

			WebElement webElement = findWebElement(path);

			Select select = new Select(webElement);

			select.selectByValue(value);

			logger.LogPass(driver, "Selected the Option whose value is " + value + " from the Dropdown " + Note);

			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Select the Option whose value is " + value
					+ " from the Dropdown Got the Exception messsage " + e.toString());

			return false;
		}

	}

	public boolean check(By path, String Note) {
		try {

			WebElement webElement = driver.findElement(path);
			// findWebElement(path);
			if (!webElement.isSelected()) {
				scrollToElement(driver, webElement);
				webElement.click();
				logger.LogPass(driver, "Clicked on the Checkbox option and is Enabled " + Note);
				return true;
			} else {
				logger.LogPass(driver, "Checkbox is already Checked and is Enabled " + Note);
				return true;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException | NoSuchElementException
				| org.openqa.selenium.ElementClickInterceptedException e) {
			logger.LogFail(driver, "Failed to Click on the Checkbox " + Note + "-> " + e);
			return false;
		}

	}

	public boolean Uncheck(By path, String Note) {
		try {

			WebElement webElement = driver.findElement(path);// findWebElement(path);
			if (webElement.isSelected()) {
				scrollToElement(driver, webElement);
				webElement.click();
				logger.LogPass(driver, "Clicked on the Checkbox option and is Disabled " + Note);
				return true;
			} else {
				logger.LogPass(driver, "Checkbox is already UnChecked and is Disabled " + Note);
				return true;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException | NoSuchElementException
				| org.openqa.selenium.ElementClickInterceptedException e) {
			logger.LogFail(driver,
					"Failed to Click on the Checkbox " + Note + "/n Got the Exception " + e.getMessage());
			return false;
		}

	}

	public boolean verifyOptionSelectedByLabel(By path, String Expecetedtext, String Note) {
		try {

			WebElement webElement = findWebElement(path);
			Select select = new Select(webElement);
			WebElement selectedoption = select.getFirstSelectedOption();
			String ActualselectedoptionText = selectedoption.getText().trim();
			if (Objects.equals(ActualselectedoptionText, Expecetedtext)) {
				logger.LogPass(driver, "The Selected Option is Matching \nActual Option :" + ActualselectedoptionText
						+ "\nExpected Option :" + Expecetedtext);
				return true;

			} else {
				logger.LogFail(driver, "The Selected Option is NOT Matching \nActual Option :"
						+ ActualselectedoptionText + "\nExpected Option :" + Expecetedtext);
				return false;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Select the Option " + Expecetedtext
					+ " from the Dropdown Got the Exception messsage " + e.toString());
			return false;
		}

	}

	public boolean uploadFile(By path, String directoryFilepath, String FileName) throws InterruptedException {

		try {

			WebElement webelement = driver.findElement(path);
			webelement.sendKeys(directoryFilepath + FileName);
			hold(5);

			logger.LogPass(driver, "Uplaoded the File " + FileName);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Find the Element in the path" + path.toString());
			return false;
		}

	}

	public boolean uploadFileUsingAutoIT(String DirectoryFilepath, String FileName) {

		String filepath = DirectoryFilepath + FileName;
		filepath = "\"" + filepath + "\"";
		String autoITExecutable = System.getProperty("user.dir") + "\\AutoIT\\AUTOITScripts.exe";

		try {
			hold(5);
			Runtime.getRuntime().exec(autoITExecutable + " " + filepath);
			hold(5);

			logger.LogPass(driver, "Uploaded the File using AutoIT : " + FileName);
			return true;
		} catch (Exception e) {
			logger.LogFail(driver, "Unable to Upload the File got the Exception message : " + e.toString());
			return false;
		}

	}

	public boolean refresh(String PageName, int timeOut) {
		try {
			driver.navigate().refresh();

			if (waitForPageLoaded(timeOut, PageName)) {
				logger.LogPass(driver, "Refreshed the Page - " + PageName + " Sucessfully");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.LogFail(driver, "Unable to Refresh the Page - " + PageName + "got the Exception " + e);
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verifyElementNotPresent(By path, int timeout, String Note) {
		boolean elementNotFound = false;
		try {

			elementNotFound = new FluentWait<WebDriver>(driver).pollingEvery(500, TimeUnit.MILLISECONDS)
					.withTimeout(timeout, TimeUnit.SECONDS).until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver webDriver) {
							try {
								webDriver.findElement(path);
								return false;
							} catch (NoSuchElementException e) {
								return true;
							}
						}
					});
		} catch (Exception e) {
			// Catch do nothing
		}

		if (elementNotFound) {
			logger.LogPass(driver, Note + "Not found in the Page as Expected");
			return true;
		} else {
			logger.LogFail(driver, Note + " Found in the Page which is not Expected to be present");
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean waitForElementNotPresent(By locator, int timeOut, String Note) {
		double starttime = System.currentTimeMillis();
		boolean elementNotFound = false;
		try {

			elementNotFound = new FluentWait<WebDriver>(driver).pollingEvery(500, TimeUnit.MILLISECONDS)
					.withTimeout(timeOut, TimeUnit.SECONDS).until(new Function<WebDriver, Boolean>() {
						@Override
						public Boolean apply(WebDriver webDriver) {
							try {
								webDriver.findElement(locator);
								return false;
							} catch (NoSuchElementException e) {
								return true;
							}
						}
					});

		} catch (TimeoutException e) {
			// timeOut, do nothing
		}

		double endtime = System.currentTimeMillis();

		if (elementNotFound) {
			logger.LogPass(driver,
					"Element is Not Present after waiting for " + String.valueOf(endtime - starttime) + "milliseconds");
			return true;
		} else {
			logger.LogFail(driver, "Element is Still Present even after waiting for "
					+ String.valueOf(endtime - starttime) + "milliseconds");
			return false;
		}

	}

	public boolean verifyElementVisible(By path, String Note) {
		try {

			if (findWebElement(path) != null) {
				logger.LogPass(driver, Note + " is Present and Displayed in the Page");
				return true;
			} else {
				logger.LogFail(driver, Note + " is Not Present or Not Displayed in the Page");
				return false;
			}

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(driver,
					Note + " is Not Present or Not Displayed in the Page got the Exception " + e.toString());
			return false;
		}
	}

	public boolean verifyElementNOTVisible(By path, String Note) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 1);

			if (wait.until(ExpectedConditions.invisibilityOfElementLocated(path))) {
				logger.LogPass(driver, Note + " is not visible in the Page");
				return true;
			} else {
				logger.LogFail(driver, Note + " is Present and Visible in the Page");
				return false;
			}

		} catch (Exception e2) {
			logger.LogFail(driver,
					Note + " - Unable to verify element Not Displayed in the Page got the Exception " + e2);
			return false;
		}

	}

	public int getSizeofWebelements(By path, String Note) {

		List<WebElement> ListElement = driver.findElements(path);

		logger.LogInfo("Size of the element " + Note + "is : " + ListElement.size());
		return ListElement.size();
	}

	public boolean verifyElementChecked(By path, String Note) {
		try {
			WebElement webElement = findWebElement(path);

			if (webElement.isSelected()) {

				logger.LogPass(driver, Note + " is Checked");
				return true;
			} else {
				logger.LogFail(driver, Note + " is NOT Checked where it is Expecetd to be checked");
				return false;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Verify if the Elemnt is checked got Exception " + e.toString());
			return false;
		}
	}

	public boolean verifyElementNotChecked(By path, String Note) {
		try {
			WebElement webElement = findWebElement(path);

			if (webElement.isSelected()) {

				logger.LogFail(driver, Note + " is Checked where it is NOT Expecetd to be checked");
				return false;
			} else {
				logger.LogPass(driver, Note + " is NOT Checked");
				return true;
			}
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {

			logger.LogFail(driver, "Unable to Verify if the Elemnt is NOT checked got Exception " + e.toString());
			return false;
		}
	}

	public List<String> getListofFileNamesinDirectory(String directorypath) {

		List<String> returnfilenames = new ArrayList<String>();
		try {
			File dir = new File(directorypath);
			File[] dir_contents = dir.listFiles();

			for (int i = 0; i < dir_contents.length; i++) {
				returnfilenames.add(dir_contents[i].getName());
			}
		} catch (Exception e) {
			logger.LogFail("Unable to get the File Names from the Directory " + directorypath.toString());
			returnfilenames = null;
		}
		return returnfilenames;

	}

	public String getCurrentTime(String formate, String Timzone) {

		SimpleDateFormat TodaysDate = new SimpleDateFormat(formate); // "yyyy-MM-dd"
		TodaysDate.setTimeZone(TimeZone.getTimeZone(Timzone)); // "UTC"

		return TodaysDate.format(new Date());
	}

	public boolean presskeys(By path, Keys ke, String KeybuttonText, String Note) {
		try {

			findWebElement(path).sendKeys(ke);

			logger.LogPass(driver, "Pressed the KEY " + KeybuttonText + " on the Element:" + Note);
			return true;
		} catch (Exception e) {
			logger.LogFail(driver, "Failed to Press the KEY " + KeybuttonText + " on the  element:" + Note);
			return false;
		}

	}

	public List<String> symmetricDifferenceofValues(List<String> aList, List<String> bList) {

		List<String> CombinedList = new ArrayList<>(aList);
		CombinedList.addAll(bList);

		CombinedList.removeAll(intersectionofValues(aList, bList));

		return CombinedList;

	}

	public List<String> intersectionofValues(List<String> aList, List<String> bList) {

		List<String> intersection = new ArrayList<String>(aList);
		intersection.retainAll(bList);

		return intersection;

	}

}
