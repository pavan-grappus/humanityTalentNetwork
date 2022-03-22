package PageObjects;

import org.openqa.selenium.By;

public class users {

	/*
	 * User Activity counts
	 */

	public By membersCount = By.xpath("//p[text()='Members']/following-sibling::p");

	public By invitedUsersCount = By.xpath("//p[text()='Invited Users']/following-sibling::p");
	public By registeredCount = By.xpath("//p[text()='Registered']/following-sibling::p");
	public By avgDaysToRegisterCount = By.xpath("//p[text()='Average Days To Register']/following-sibling::p");

	/*
	 * JOINED
	 */

	public By joined_button = By.xpath("//button//span[text()='Joined']");

	public By joinedName_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-jcwpoC eUajUM'])[" + order + "]//h3");
	}

	public By joinedEmailAddress_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-jcwpoC eUajUM'])[" + order + "]//p");
	}

	public By joinedTitle_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-carFqZ jujGOG'])[" + order + "]//p[1]");
	}

	public By joinedOrganization_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-carFqZ jujGOG'])[" + order + "]//p[2]");
	}

	public By joinedEndorsedBy_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-iTVJFM gwKOGx'])[" + order + "]//p");
	}

	public By joinedInvitedOn(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-iBzEeX frkCUf'])[" + order + "]//p");
	}

	public By joinedJoinedOn(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-0']//div[@class='sc-efHYUO eUjBcG'])[" + order + "]//p");
	}

	/*
	 * Invited
	 */

	public By invited_Button = By.xpath("//button//span[text()='Invited']");

	public By invitedHeaders_text = By.xpath("//div[@id='simple-tabpanel-1']//div[@class='sc-bYwzuL daSkHC']//p");

	public By invitedName_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-cxNHIi hMqCrD'])[" + order + "]//h3");
	}

	public By invitedEmailAddress_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-cxNHIi hMqCrD'])[" + order + "]//p");
	}

	public By invitedTitle_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-lmgQwP kWhCyT'])[" + order + "]//p[1]");
	}

	public By invitedOrganization_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-lmgQwP kWhCyT'])[" + order + "]//p[2]");
	}

	public By invitedEndorsedBy_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-iJCRrE dihuFm'])[" + order + "]//p[1]");
	}

	public By invitedInvitedOn_Table(Object order) {
		return By.xpath("(//div[@id='simple-tabpanel-1']//div[@class='sc-giAqHp iLNtjU'])[" + order + "]//p[1]");
	}

	public By resendInvite_Button(String emailAddress, int order) {
		return By.xpath(
				"(//p[text()='" + emailAddress + "']//following::button//span[text()='Resend Invite'])[" + order + "]");
	}

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
