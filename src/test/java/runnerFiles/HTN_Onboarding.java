package runnerFiles;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.springframework.expression.spel.ast.OpNE;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class HTN_Onboarding extends BeforeRun {

	public List<String> tabList = Arrays.asList("Sector", "Function", "Therapeutic", "Thought Questions", "Company",
			"Seniority Level", "Growth Sector");

	@BeforeTest
	public void superAdminUsersLoginsToTheApplication() {
		userLoginToTheWebApplication(EnvironmentDetails.SuperAdminUserName, EnvironmentDetails.SuperAdminPassword);
		navigateToOnboardingPage();
	}

	public void navigateToOnboardingPage() {

		gm.click(dash.onboarding, "onboarding");
		gm.waitforElementVisible(onboarding.AddNew_Button, 10, "AddNew_Button");
		gm.hold(5);
	}

	public void refreshOnboardingPage() {
		gm.refresh("Onboarding", 10);
		gm.hold(5);
	}

	@Test
	public void testcasesMethod() {

		gm.StartTest("Validate the URL, TITLE AND Icon of the Onboarding page After Navigation", "");
		validateWebpageTabIconAndTitle();
		gm.verifyPageURL(EnvironmentDetails.htnURL + "/dashboard/onboarding");
		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate the URL, TITLE AND Icon of the Onboarding page After Refresh", "");
		refreshOnboardingPage();
		validateWebpageTabIconAndTitle();
		gm.verifyPageURL(EnvironmentDetails.htnURL + "/dashboard/onboarding");
		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate The Text and Order of Top headers in the Onboaring page", "");
		gm.verifyListofElementText(onboarding.onboardingTablist, tabList.toArray(), "onboardingTablist");
		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate if the Tab List Buttons are Clickable in the Onboaring page", "");
		for (String tabButtonText : tabList) {
			gm.verifyElementClickable(onboarding.TablistButton(tabButtonText), "TablistButton(" + tabButtonText + ")");
		}
		gm.EndTest();

		/*
		 * 
		 */

		gm.StartTest("Validate The UI of the TAB Header Text when selected", "");
		gm.click(onboarding.sectorTabText, "sectorTabText");
		gm.hold(5);
		validateCSSProperty(onboarding.sectorTabText, "sectorTabText", "InterBold", "14px", "0.39998px", "1",
				"rgba(8, 42, 88, 1)", "rgba(0, 0, 0, 0)", "center");

		gm.EndTest();

		/*
		 * 
		 */

		gm.StartTest("Validate The UI of the TAB Header Text when UNselected", "");
		gm.click(onboarding.functionTabText, "functionTabText");
		gm.hold(5);

		validateCSSProperty(onboarding.sectorTabText, "sectorTabText", "InterMedium", "14px", "0.39998px", "0.5",
				"rgba(8, 42, 88, 1)", "rgba(0, 0, 0, 0)", "center");

		gm.EndTest();

		/*
		 * 
		 */

		gm.StartTest("Validate the loading icon when shifted from one Onboarding capability to other", "");
		refreshOnboardingPage();
		gm.verifyElementNotPresent(onboarding.loadingIcon, 1, "loadingIcon");
		gm.click(onboarding.functionTabButton, "functionTabButton");
		gm.verifyElementVisible(onboarding.loadingIcon, "loadingIcon");
		gm.verifyElementAttributeValue(onboarding.loadingIcon, "src",
				"https://admin-dev.humanitytalent.net/static/media/loader-hh.b08f3037.gif", "loadingIcon");
		gm.hold(10);
		gm.verifyElementNotPresent(onboarding.loadingIcon, 1, "loadingIcon");
		gm.EndTest();

		/*
		 * 
		 */

		gm.StartTest("Validate the UI of the Divider line for selected capability", "");
		refreshOnboardingPage();
		gm.click(onboarding.sectorTabText, "sectorTabText");
		gm.hold(5);
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-width", "2px", "sectorTabButton");
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-style", "solid", "sectorTabButton");
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-color", "rgba(8, 42, 88, 1)",
				"sectorTabButton");
		gm.EndTest();

		/*
		 * 
		 */

		gm.StartTest("Validate the UI of the Divider line for UNselected capability", "");
		gm.click(onboarding.functionTabText, "functionTabText");
		gm.hold(5);
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-width", "0px", "sectorTabButton");
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-style", "none", "sectorTabButton");
		gm.verifyElementCSSValue(onboarding.sectorTabButton, "border-bottom-color", "rgba(0, 0, 0, 0.87)",
				"sectorTabButton");
		gm.EndTest();

	}

	@Test
	public void validateSector() {
		/*
		 * 
		 */
		gm.StartTest("Validate the Showing All left header Text and CSS of sector", "");
		refreshOnboardingPage();
		gm.verifyElementText(onboarding.showingAllCount_Text, "Showing All Sectors", "showingAllCount_Text");

		validateCSSProperty(onboarding.showingAllCount_Text, "showingAllCount_Text", "InterBold", "14px", "-0.09px",
				"1", "rgba(33, 33, 35, 1)", "rgba(0, 0, 0, 0)", "start");

		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate the Count Found Match and CSS of sector", "");
		gm.verifymatch(gm.getText(onboarding.foundCount_Text, "foundCount_Text"), "\\d+ found", "foundCount_Text");

		validateCSSProperty(onboarding.foundCount_Text, "foundCount_Text", "Inter", "14px", "-0.09px", "0.5",
				"rgba(33, 33, 35, 1)", "rgba(0, 0, 0, 0)", "start");

		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate the search bar placeholder, css", "");

		refreshOnboardingPage();
		gm.verifyElementAttributeValue(onboarding.searchForany_field, "type", "search", "searchForany_field");
		gm.verifyElementAttributeValue(onboarding.searchForany_field, "value", "", "searchForany_field");
		gm.verifyElementAttributeValue(onboarding.searchForany_field, "placeholder", "Search for any sector",
				"searchForany_field");

		validateCSSProperty(onboarding.searchForany_field, "searchForany_field", "Roboto, Helvetica, Arial, sans-serif",
				"14px", "0.13132px", "1", "rgba(33, 33, 35, 1)", "rgba(0, 0, 0, 0)", "start");

		gm.setText(onboarding.searchForany_field, "Test123", "searchForany_field");
		gm.verifyElementAttributeValue(onboarding.searchForany_field, "value", "Test123", "searchForany_field");

		validateCSSProperty(onboarding.searchForany_field, "searchForany_field", "Roboto, Helvetica, Arial, sans-serif",
				"14px", "0.13132px", "1", "rgba(33, 33, 35, 1)", "rgba(0, 0, 0, 0)", "start");

		gm.EndTest();

		/*
		 * 
		 */
		gm.StartTest("Validate the Add New sector button and css", "");

		validateCSSProperty(onboarding.AddNew_ButtonText, "AddNew", "Inter, sans-serif", "14px", "0.39998px", "1",
				"rgba(8, 42, 88, 1)", "rgba(0, 0, 0, 0)", "center");

		gm.verifyElementCSSValue(onboarding.AddNew_Button, "background-color", "rgba(181, 248, 93, 1)",
				"AddNew_Button");

		gm.verifyElementClickable(onboarding.AddNew_Button, "AddNew_Button");
		gm.EndTest();

	}

	public void openAddNewmodalWindow() {
		gm.click(onboarding.AddNew_Button, "AddNew_Button");
		gm.waitforElementVisible(onboarding.modalConfirm_button, 5, "modalConfirm_button");
	}

	@Test(enabled = true)
	public void AddNewSectorModalValidation() {
		/*
		 * Modal Header
		 */

		gm.StartTest("Validate the Add new sector modal Headers Text and CSS", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementText(onboarding.modalHeader_Text, "Add New Sector", "modalHeader_Text");
		gm.EndTest();

		/*
		 * Modal SubHeader
		 */
		gm.StartTest("Validate the Add new sector modal SubHeaders Text and CSS", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementText(onboarding.modalSubHeader_Text, "to capabilities", "modalSubHeader_Text");
		gm.EndTest();

		/*
		 * Modal close button
		 */
		gm.StartTest("Validate the Add new sector modal Close button Image and click function", "");
		refreshOnboardingPage();
		gm.verifyElementNotPresent(onboarding.modalHeader_closeButton, 1, "modalHeader_closeButton");
		openAddNewmodalWindow();
		gm.verifyElementClickable(onboarding.modalHeader_closeButton, "modalHeader_closeButton");
		gm.verifyElementAttributeValue(onboarding.modalHeader_closeButton, "src",
				"https://admin-dev.humanitytalent.net/static/media/cross.536ab879.svg", "modalHeader_closeButton");
		gm.verifyElementVisible(onboarding.modalHeader_closeButton, "modalHeader_closeButton");
		gm.click(onboarding.modalHeader_closeButton, "modalHeader_closeButton");
		gm.verifyElementNotPresent(onboarding.modalHeader_closeButton, 1, "modalHeader_closeButton");
		gm.EndTest();

		/*
		 * Modal Text Field Header
		 */
		gm.StartTest("Validate the Add new sector modal - Name Field Header and CSS", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementText(onboarding.modalTextFieldHeader, "NAME", "modalTextFieldHeader");
		validateCSSProperty(onboarding.modalTextFieldHeader, "modalTextFieldHeader", "InterMedium", "12px", "0.5px",
				"0.5", "rgba(33, 33, 35, 1)", "rgba(0, 0, 0, 1)", "start");
		gm.EndTest();

		/*
		 * Modal Text Field Test 1
		 */
		gm.StartTest(
				"Validate the Add new sector modal - Name Text Field type, Value, Placeholder, CSS default Validation",
				"");
		validateTextFieldPropertys(onboarding.modalTextFieldInput, "modalTextFieldInput", "text",
				"Enter sector name here", "");
		validateCSSProperty(onboarding.modalTextFieldInput, "modalTextFieldInput", "InterMedium", "16px", "-0.1px", "1",
				"rgba(242, 243, 244, 1)", "rgba(242, 243, 244, 1)", "start");
		gm.EndTest();

		/*
		 * Test 2
		 */
		gm.StartTest("Field value should be balnk by default", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 3
		 */
		gm.StartTest("User should be able to see all the Typed Characters in Name Text field", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "qwertyuiopasdfghjklzxcvbnm", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "qwertyuiopasdfghjklzxcvbnm",
				"modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Name field value should show blank when Typed and cleared the Field by backspace", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "qwertyuiopasdfghjklzxcvbnm", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "qwertyuiopasdfghjklzxcvbnm",
				"modalTextFieldInput");
		gm.clearbyBackspace(onboarding.modalTextFieldInput, "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "placeholder", "Enter sector name here",
				"modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 5
		 */
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "qwertyuiopasdfghjklzxcvbnm", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "qwertyuiopasdfghjklzxcvbnm",
				"modalTextFieldInput");
		gm.presskeys(onboarding.modalTextFieldInput, Keys.BACK_SPACE, "BACK_SPACE", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("User clicks on Backspace when nothing is Typed in Name field", "");
		gm.click(onboarding.modalTextFieldInput, "modalTextFieldInput");
		gm.presskeys(userModal.nameInput, Keys.BACK_SPACE, "BACK_SPACE", "nameInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "placeholder", "Enter sector name here",
				"modalTextFieldInput");
		gm.EndTest();

		/*
		 * Modal Confirm Button Test 1
		 */

		gm.StartTest("Validate the Text and css of Confirm button in disabled state", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementisNOTClickable(onboarding.modalConfirm_button, "modalConfirm_button");
		gm.verifyElementText(onboarding.modalConfirm_button, "Confirm", "modalConfirm_button");
		validateCSSProperty(onboarding.modalConfirm_button, "modalConfirm_button", "Inter, sans-serif", "14px",
				"0.17136px", "1", "rgba(102, 102, 102, 1)", "rgba(204, 204, 204, 1)", "center");
		gm.EndTest();

		/*
		 * Test 2
		 */
		gm.StartTest("Validate the Text and css of Confirm button in Enabled state", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "Test", "modalTextFieldInput");
		gm.verifyElementClickable(onboarding.modalConfirm_button, "modalConfirm_button");
		gm.verifyElementText(onboarding.modalConfirm_button, "Confirm", "modalConfirm_button");
		validateCSSProperty(onboarding.modalConfirm_button, "modalConfirm_button", "Inter, sans-serif", "14px",
				"0.17136px", "1", "rgba(8, 42, 88, 1)", "rgba(181, 248, 93, 1)", "center");
		gm.EndTest();

		/*
		 * Test 3
		 */
		gm.StartTest("Confirm button should be in disabled state by default", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementisNOTClickable(onboarding.modalConfirm_button, "modalConfirm_button");
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Confirm button should not be enabled if Mandatory fields are Not filled", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.verifyElementisNOTClickable(onboarding.modalConfirm_button, "modalConfirm_button");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Confirm button should be enabled if Mandatory fields are filled", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "Test", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "Test", "modalTextFieldInput");
		gm.verifyElementClickable(onboarding.modalConfirm_button, "modalConfirm_button");
		gm.EndTest();

		/*
		 * valueFieldsinModaluponreopen Test 1
		 */

		gm.StartTest("Enter the values and close the modal and check the field values are empty upon reopen", "");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, "Test1", "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "Test1", "modalTextFieldInput");
		gm.click(onboarding.modalHeader_closeButton, "modalHeader_closeButton");
		gm.waitForElementNotPresent(onboarding.modalConfirm_button, 1, "modalConfirm_button");
		gm.verifyElementNotPresent(go.modalHeader, 5, "Notification Modal Header");
		openAddNewmodalWindow();
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 2
		 */
		gm.StartTest("Enter the values and save Sucessfully and check the field values are empty upon reopen", "");
		String sectorName = gm.getCurrentTime("ddMMYYYYhhmmss", "GMT");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		addANewCapability(sectorName);
		openAddNewmodalWindow();
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.EndTest();

		/*
		 * Test 3
		 */
		gm.StartTest("Enter the values and save UnSucessfully and check the field values are empty upon reopen", "");
		String sectorName1 = gm.getCurrentTime("ddMMYYYYhhmmss", "GMT");
		refreshOnboardingPage();
		openAddNewmodalWindow();
		addANewCapability(sectorName1);
		openAddNewmodalWindow();
		gm.setText(onboarding.modalTextFieldInput, sectorName1, "modalTextFieldInput");
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", sectorName1, "modalTextFieldInput");
		gm.click(onboarding.modalConfirm_button, "modalConfirm_button");
		validateModalNotificationMessage("Error", "Entity with similar data already exist.");
		gm.click(onboarding.modalHeader_closeButton, "modalHeader_closeButton");
		gm.waitForElementNotPresent(onboarding.modalConfirm_button, 1, "modalConfirm_button");
		gm.verifyElementNotPresent(go.modalHeader, 5, "Notification Modal Header");
		openAddNewmodalWindow();
		gm.verifyElementAttributeValue(onboarding.modalTextFieldInput, "value", "", "modalTextFieldInput");
		gm.EndTest();

	}

	public void addANewCapability(String sectorName1) {
		gm.setText(onboarding.modalTextFieldInput, sectorName1, "modalTextFieldInput");
		gm.click(onboarding.modalConfirm_button, "modalConfirm_button");
		validateModalNotificationMessage("Success", "New sector added successfully.");
	}

	public int getDisplayedCount() {
		return Integer.valueOf(gm.getText(onboarding.foundCount_Text, "foundCount_Text").replaceAll(" found", ""));
	}

	public void deactivateASector(String sectorName) {
		gm.click(onboarding.cardsDeactivate_Button(sectorName), "cardsDeactivate_Button(" + sectorName + ")");
		gm.waitforElementVisible(onboarding.modalYes_button, 2, "modalYes_button");
		gm.click(onboarding.modalYes_button, "modalYes_button");
		validateModalNotificationMessage("Success", "Sector deactivated successfully.");
	}

	public void activateASector(String sectorName) {
		gm.click(onboarding.cardsActivate_Button(sectorName), "cardsActivate_Button(" + sectorName + ")");
		gm.waitforElementVisible(onboarding.modalYes_button, 2, "modalYes_button");
		gm.click(onboarding.modalYes_button, "modalYes_button");
		validateModalNotificationMessage("Success", "Sector activated successfully.");
	}

	public void editASector(String sectorName) {
		gm.click(onboarding.cardsEdit_Button(sectorName), "cardsEdit_Button(" + sectorName + ")");
		gm.waitforElementVisible(onboarding.modalConfirm_button, 2, "modalConfirm_button");
		gm.click(onboarding.modalConfirm_button, "modalConfirm_button");
		validateModalNotificationMessage("Success", "Sector edited successfully.");
	}

	public void deleteASector(String sectorName) {
		gm.click(onboarding.cardsDelete_Button(sectorName), "cardsDelete_Button(" + sectorName + ")");
		gm.waitforElementVisible(onboarding.modalYes_button, 2, "modalYes_button");
		gm.click(onboarding.modalYes_button, "modalYes_button");
		validateModalNotificationMessage("Success", "Sector deleted successfully.");
	}

	@Test(enabled = false)
	public void FunctionalText() {
		/*
		 * Test 1
		 */
		gm.StartTest("Validate if the count is increasing when new sector is added", "");

		refreshOnboardingPage();
		int initialCount = getDisplayedCount();
		String sectorName = gm.getCurrentTime("ddMMYYYYhhmmss", "GMT");

		openAddNewmodalWindow();
		addANewCapability(sectorName);
		gm.verifyEqual(initialCount + 1, getDisplayedCount(), "Sector Counts");
		gm.EndTest();

		/*
		 * Test 2
		 */
		gm.StartTest("Validate if the count is same after disabled", "");
		gm.loginfo("Sector Name is " + sectorName);
		int countBeforeDeactivate = getDisplayedCount();
		deactivateASector(sectorName);
		gm.verifyEqual(countBeforeDeactivate, getDisplayedCount(), "Sector Counts");
		gm.EndTest();

		/*
		 * Test 3
		 */
		gm.StartTest("Validate if the count is same when activated back", "");
		gm.loginfo("Sector Name is " + sectorName);
		int countBeforeActivate = getDisplayedCount();
		activateASector(sectorName);
		gm.verifyEqual(countBeforeActivate, getDisplayedCount(), "Sector Counts");
		gm.EndTest();

		/*
		 * Test 4
		 */
		gm.StartTest("Validate if the count is same when edited", "");
		gm.loginfo("Sector Name is " + sectorName);
		int countBeforeEdit = getDisplayedCount();
		editASector(sectorName);
		gm.verifyEqual(countBeforeEdit, getDisplayedCount(), "Sector Counts");
		gm.EndTest();

		/*
		 * Test 5
		 */
		gm.StartTest("validate if the count is decreasing when deleted", "");
		gm.loginfo("Sector Name is " + sectorName);
		int countBeforedelete = getDisplayedCount();
		deleteASector(sectorName);
		gm.verifyEqual(countBeforedelete - 1, getDisplayedCount(), "Sector Counts");
		gm.EndTest();

		gm.StartTest("Valdiate the edit model window default view", "");
		gm.EndTest();

		gm.StartTest("Edit model window validation", "");
		gm.EndTest();

		gm.StartTest("Validate the delete model - No", "");
		gm.EndTest();

		gm.StartTest("Validate the delete model - Yes", "");
		gm.EndTest();

		gm.StartTest("Validate the deactivate model - No", "");
		gm.EndTest();

		gm.StartTest("Validate the activate model - Yes", "");
		gm.EndTest();

		gm.StartTest("Validate the UI of the active card", "");
		gm.EndTest();

		gm.StartTest("Validate the UI of the Deactivated card", "");
		gm.EndTest();

		gm.StartTest("Validate the UI of the Deactivated to Activated card", "");
		gm.EndTest();

		gm.StartTest("Validate if the sectors are in ASC order by default", "");
		gm.EndTest();

		gm.StartTest("Validate if the sectors are in ASC order when searched", "");
		gm.EndTest();

		gm.StartTest("Validate if the sectors are in ASC order when page is changed", "");
		gm.EndTest();

		gm.StartTest("Validate if the sectors are present in ASC order in app", "");
		gm.EndTest();

		gm.StartTest("Validate if All the Active sectors are visible in app (up to 30)", "");
		gm.EndTest();

		gm.StartTest("Validate if Deactive sectors are not visisble in app", "");
		gm.EndTest();

		gm.StartTest("Validate if deleted sectors are not visisble in app", "");
		gm.EndTest();

		gm.StartTest("Validate if the sector is edited then same is updated in user profile in app", "");
		gm.EndTest();

		gm.StartTest("Validate if the sector is edited then same is updated in user profile Web", "");
		gm.EndTest();

		gm.StartTest("Validate if the sector is deleted then same is updated in user profile in app", "");
		gm.EndTest();

		gm.StartTest("Validate if the sector is deleted then same is updated in user profile Web", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present by default", "");
		gm.EndTest();

		gm.StartTest("Pagination should not be present in app", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after search", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after added", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after edited", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after deleted", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after deactivated", "");
		gm.EndTest();

		gm.StartTest("Pagination should be present after activated", "");
		gm.EndTest();
	}

}
