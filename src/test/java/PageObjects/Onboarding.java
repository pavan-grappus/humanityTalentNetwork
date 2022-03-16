package PageObjects;

import org.openqa.selenium.By;

public class Onboarding {

	public By Company_button = By.xpath("//button/span[text()='Company']");

	public By addNewCompany = By.xpath("//button/span[text()='Add New Company']");

	public By enterCompany_input = By.xpath("//input[@placeholder = 'Enter company name here.']");

	public By conifrm_Button = By.xpath("//button/span[text()='Confirm']");
}
