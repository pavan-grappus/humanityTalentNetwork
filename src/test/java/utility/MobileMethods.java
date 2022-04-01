package utility;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileMethods {

	Reports logger;

	public MobileMethods(Reports logger) {
		this.logger = logger;
	}

	public AppiumDriver<MobileElement> mobileDriver;

	public void launchApplication() {

		try {

			try {
				mobileDriver.quit();
			} catch (Exception e) {
				// TODO: handle exception
			}
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "Galaxy M11");
			cap.setCapability("udid", "R9ZN60MSNVD");
			cap.setCapability("platformVersion", "11");
			cap.setCapability("appPackage", "com.humanity.talent.dev");
			cap.setCapability("appActivity", "com.humanity.talent.MainActivity");
			cap.setCapability("platformName", "Android");

			URL url = new URL("http://127.0.0.1:4723/wd/hub");

			this.mobileDriver = new AppiumDriver<MobileElement>(url, cap);
			mobileDriver.resetApp();
			logger.LogPass("Launched the Mobile Application Successfully");
		} catch (Exception e) {
			logger.LogFail("Unable to launch the Application got the error " + e.getMessage());
		}
	}

	public MobileElement findWebElement(By path) {

		double starttime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(mobileDriver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(path));
			return mobileDriver.findElement(path);

		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			double endtime = System.currentTimeMillis();
			logger.LogFail("Element is NOT Visible in the Page after waiting for " + String.valueOf(endtime - starttime)
					+ "milliseconds" + " got the Exception message as " + e);
			throw new NoSuchElementException("Element is not visible in the Page");
		}
	}

	public boolean click(By path, String Note) {

		MobileElement element = null;
		try {
			element = findWebElement(path);
			element.click();
			logger.LogPass(mobileDriver, "Clicked on the Elemennt : " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException exctemp) {
			logger.LogFail(mobileDriver,
					"Failed to Click on the Element : " + Note + " got the Exception message " + exctemp);
			return false;
		}
	}

	public boolean setText(By path, String text, String Note) {

		try {
			MobileElement element = findWebElement(path);
			element.clear();
			element.sendKeys(text);

			logger.LogPass(mobileDriver, "Cleared the Field and Entered the Text : " + text + " -> " + Note);
			return true;
		} catch (ElementNotVisibleException | StaleElementReferenceException | TimeoutException
				| NoSuchElementException e) {
			logger.LogFail(mobileDriver,
					"Unable to Enter the Text " + text + " in the path " + Note + "got the Exception " + e);
			return false;
		}
	}
}
