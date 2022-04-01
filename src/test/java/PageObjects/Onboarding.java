package PageObjects;

import org.openqa.selenium.By;

public class Onboarding {

	public By onboardingTablist = By.xpath("//div[@role='tablist']//button//span");

	public By sectorTabButton = TablistButton("Sector");

	public By functionTabButton = TablistButton("Function");

	public By sectorTabText = TablistText("Sector");

	public By functionTabText = TablistText("Function");

	public By loadingIcon = By.xpath("//img[@alt='loading...']");

	public By TablistText(String buttonText) {
		return By.xpath("//div[@role='tablist']//span[text()='" + buttonText + "']");
	}

	public By TablistButton(String buttonText) {
		return By.xpath("//div[@role='tablist']//span[text()='" + buttonText + "']/parent::button");
	}

	public By showingAllCount_Text = By.xpath("//div[starts-with(@id,'simple-tabpanel')]//h2");

	public By foundCount_Text = By.xpath("//div[starts-with(@id,'simple-tabpanel')]//p");

	public By searchForany_field = By.xpath("//input[@type='search']");

	public By AddNew_ButtonText = By.xpath("//div[@class='sc-eCApnc fAVkeA']/button/span");
	public By AddNew_Button = By.xpath("//div[@class='sc-eCApnc fAVkeA']/button");

	public By AllcardsText(Object index) {
		return By.xpath("(//div[@class='sc-amkrK gdJPMO']//h3)[" + index + "]");
	}

	public By cardsDeactivate_Button(String text) {
		return By.xpath("//h3[text()='" + text + "']/parent::div/following-sibling::div/button//img[@alt='block']");
	}

	public By cardsActivate_Button(String text) {
		return By.xpath("//h3[text()='" + text + "']/parent::div/following-sibling::div/button//img[@alt='block']");
	}

	
	public By cardsDelete_Button(String text) {
		return By.xpath("//h3[text()='" + text + "']/parent::div/following-sibling::div/button//img[@alt='delete']");
	}

	public By cardsEdit_Button(String text) {
		return By.xpath("//h3[text()='" + text + "']/parent::div/following-sibling::div/button//img[@alt='edit']");
	}

	public By modalHeader_Text = By.xpath("//div[@class='dialog-header']//h2");
	public By modalSubHeader_Text = By.xpath("//div[@class='dialog-header']//p");

	public By modalHeader_closeButton = By.xpath("//div[@class='dialog-header']//button//img");

	public By modalBody_Text = By.xpath("//div[@class='MuiDialogContent-root']/p");

	public By modalTextFieldHeader = By.xpath("//div[@class='MuiDialogContent-root']//label");

	public By modalTextFieldInput = By.xpath("//div[@class='MuiDialogContent-root']//input");

	public By modalYes_button = By.xpath("//div[@class='MuiDialogContent-root']//span[text()='Yes']/parent::button");

	public By modalNo_button = By.xpath("//div[@class='MuiDialogContent-root']//span[text()='No']/parent::button");

	public By modalConfirm_button = By
			.xpath("//div[@class='MuiDialogContent-root']//span[text()='Confirm']/parent::button");

	public By previousPage = By.xpath("//ul[@class='MuiPagination-ul']//button[@aria-label='Go to previous page']");

	public By goToPage(Object pageNumber) {
		return By.xpath("//ul[@class='MuiPagination-ul']//button[@aria-label='Go to page " + pageNumber + "']");
	}

	public By nextPage = By.xpath("//ul[@class='MuiPagination-ul']//button[@aria-label='Go to next page']");

	/*
	 * 
	 */
	public By Company_button = By.xpath("//button/span[text()='Company']");

	public By addNewCompany = By.xpath("//button/span[text()='Add New Company']");

	public By enterCompany_input = By.xpath("//input[@placeholder = 'Enter company name here.']");

	public By conifrm_Button = By.xpath("//button/span[text()='Confirm']");
}
