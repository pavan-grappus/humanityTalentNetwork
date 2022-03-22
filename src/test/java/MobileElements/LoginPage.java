package MobileElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage {

	AppiumDriver<MobileElement> driver;

	public LoginPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public MobileElement WelcomeBackHeader() {
		return driver
				.findElementByXPath("//android.view.View[@content-desc='Welcome back to Humanity Talent Network']");
	}

	public MobileElement enterEmailAndPassword() {
		return driver.findElementByXPath("//android.view.View[@content-desc='Enter your email and password']");
	}

	public MobileElement emailText() {
		return driver.findElementByXPath("//android.view.View[@content-desc='Email']");
	}

	public MobileElement enterYourEmail() {
		return driver.findElementByXPath("//*[@class = 'android.widget.EditText'][1]");
	}

	public MobileElement passwordText() {
		return driver.findElementByXPath("//android.view.View[@content-desc='Password']");
	}

	public MobileElement enterYourPassword() {
		return driver.findElementByXPath("//*[@class = 'android.widget.EditText'][2]");
	}

	public MobileElement showPassword() {
		return driver.findElementByXPath("//android.widget.EditText[2]/android.view.View[1]");
	}

	public MobileElement forgotYourPassword() {
		return driver.findElementByXPath("//android.view.View[@content-desc='Forgot your password? ']");
	}

	public MobileElement clickHere() {
		return driver.findElementByXPath("//android.widget.Button[@content-desc='Click Here']");
	}

	public MobileElement continueButton() {
		return driver.findElementByXPath("//android.view.View[@content-desc='Continue']");
	}
}
