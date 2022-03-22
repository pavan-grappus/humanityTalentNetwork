package MobileElements;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SplashScreen {

	AppiumDriver<MobileElement> driver;

	public SplashScreen(AppiumDriver<MobileElement> driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public MobileElement HTNlogo() {
		return driver.findElement(By.xpath("//android.widget.ImageView[1]"));
	}

}
