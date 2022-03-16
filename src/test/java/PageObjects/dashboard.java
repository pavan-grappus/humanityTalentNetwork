package PageObjects;

import org.openqa.selenium.By;

public class dashboard {

	public By users = By.xpath("//ul/a[contains(@href,'users')]");
	
	public By onboarding = By.xpath("//ul/a[contains(@href,'onboarding')]");
	
}
