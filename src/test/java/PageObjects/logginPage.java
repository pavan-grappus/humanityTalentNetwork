package PageObjects;

import org.openqa.selenium.By;

public class logginPage {

	public By leftSection = By.xpath("//div[@class='left-section']");

	public By rightSection = By.xpath("//div[@class='right-section']");

	public By leftpanelHTNLogo = By.xpath("//div[@class='htn-logo']/img[1]");

	public By leftpanelHTNName = By.xpath("//div[@class='htn-logo']/img[2]");

	public By heading = By.xpath("//h1[contains(@class,'heading')]");

	public By subHeader = By.xpath("//p[contains(@class,'description')]");

	public By emailId_label = By.xpath("//input[@name='email']/preceding-sibling::p");

	public By emailid_input = By.xpath("//input[@name='email']");

	public By password_input = By.xpath("//input[@name='password']");

	public By password_label = By.xpath("//input[@name='password']/preceding-sibling::p");

	public By Continue_button = By.xpath("//button[@type='submit']");

	public By invalidEmailAddress_ErrorMessage = By.xpath("//p[contains(@class,'Mui-error')]");
}
