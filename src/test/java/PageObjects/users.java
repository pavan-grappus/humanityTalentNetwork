package PageObjects;

import org.openqa.selenium.By;

public class users {

	public By filters_Button = By.xpath("//div[@class='filters-button']");

	public By filterSidebarHeader_Text = By.xpath("//*[@class='heading']");

	public By sectorReadMore_button = By.xpath("(//p[@class='MuiTypography-root show-text MuiTypography-body1'])[1]");

	public By functionReadMore_button = By.xpath("(//p[@class='MuiTypography-root show-text MuiTypography-body1'])[2]");

	public By FilterscheckBox_input(String CheckboxText) {
		return By.xpath("//span[@class='option-name'][text()='" + CheckboxText + "']/preceding-sibling::label//input");
	}

	public By addNewUser_button = By.xpath("//button[contains(@class,'add-user-btn')]");

	public By CheckedCheckboxes_List = By.xpath("//span[contains(@class,'Mui-checked')]//input");

	public By Apply_Button = By.xpath("//span[@class='MuiTypography-root'][text()='Apply']");

	public By ClearAll_Button = By.xpath("//span[@class='MuiTypography-root'][text()='Clear All']");

	public By JoinedList = By.xpath("//div[@class='sc-carFqZ hBWCUB']");

	public By JoinedORNewUsersList = By.xpath("(//div[@class='sc-carFqZ hBWCUB'])|(//h3[text()='No users found!'])");

	public By emailID_JoinedList_Text = By.xpath("//div[@class='sc-iTVJFM binHAo']//p");

}
