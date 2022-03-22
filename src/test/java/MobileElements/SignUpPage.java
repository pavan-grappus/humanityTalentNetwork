package MobileElements;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignUpPage {

	public By areYouEndorsedBySomeone = By.xpath("//android.view.View[starts-with(@content-desc, 'Are')][1]");

	public By emailendorsedOn = By.xpath("//android.view.View[starts-with(@content-desc, 'Enter the')][1]");

	public By emailText = By.xpath("//android.view.View[@content-desc = 'Email'][1]");

	public By enterYourEmail = By.xpath("//*[@class = 'android.widget.EditText']");

	public By looksLikeAInvalidEmail = By.xpath("//android.view.View[starts-with(@content-desc, 'This looks')]");

	public By getStarted = By.xpath("//android.view.View[@content-desc='Get started']");

}
