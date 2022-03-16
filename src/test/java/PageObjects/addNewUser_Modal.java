package PageObjects;

import org.openqa.selenium.By;

public class addNewUser_Modal {

	public By dialogHeader = By.className("dialog__title-container");

	public By dialogCloseIcon = By.xpath("//div[@class='dialog__close-icon']/button");

	public By dialogCloseIconImage = By.xpath("//div[@class='dialog__close-icon']/button//img");

	public By nameLabel = By.xpath("//form//input[@name='name']/preceding::label[1]");

	public By nameInput = By.xpath("//form//input[@name='name']");

	public By emailLabel = By.xpath("//form//input[@name='email']/preceding::label[1]");

	public By emailInput = By.xpath("//form//input[@name='email']");

	public By companyLabel = By.xpath("//form//input[@placeholder='Enter company name']/preceding::label[1]");

	public By companyInput = By.xpath("//form//input[@placeholder='Enter company name']");

	public By companyInputClear_Button = By
			.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root']/*[local-name()='path']");

	public By companyCircular_Button = By
			.xpath("//*[local-name()='svg' and contains(@class,'Progress')]/*[local-name()='circle']");

	public By companySearchResult(Object order) {
		return By.xpath("(//div[@class='sc-bwcZwS kiFfNz']/h3)[" + order + "]");
	}

	public By noSearchFound_errorText = By.xpath("//p[contains(@class,'ErrorText')]");

	public By confirmButton = By.xpath("//form//button");
}
