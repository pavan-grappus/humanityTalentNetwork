package MobileElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class InvalidSignUp {

	AppiumDriver<MobileElement> driver;

	public InvalidSignUp(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public MobileElement emailSearchImage() {
		return driver.findElementByXPath("//android.widget.ImageView[2]");
	}

	public MobileElement cantFindHeaderText() {
		return driver.findElementByAccessibilityId("//android.view.View[1]/android.view.View[1]");
	}

	public MobileElement invalidEmailAddress() {
		return driver.findElementByXPath("//android.view.View[2]");
	}

	public MobileElement cantFindBodyText() {
		return driver.findElementByXPath("//android.view.View[3]");
	}

	public MobileElement tryAnotherEmail() {
		return driver.findElementByXPath("//android.view.View[4]");
	}
}
