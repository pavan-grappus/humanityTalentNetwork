package runnerFiles;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class HTN_Users_Modal extends BeforeRun {

	@BeforeTest
	public void userLoginToTheApplication() {
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName, "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		gm.waitforElementVisible(us.addNewUser_button, 10, "addNewUser_button");
		gm.hold(5);
	}

	public void openAddNewUserModal() {
		gm.click(us.addNewUser_button, "addNewUser_button");
		gm.waitforElementVisible(userModal.confirmButton, 5, "confirmButton");
	}

	public void refresh() {
		gm.refresh("User Management page", 10);
	}

	public void selectCompany(String compname, int order) {
		gm.setText(userModal.companyInput, compname, "companyInput");
		gm.hold(5);
		gm.click(userModal.companySearchResult(order), "companySearchResult(" + order + ")");
		gm.hold(2);

	}

	@Test(enabled = false, priority = 1)
	public void validatingTheAddNewUserModalWindow() {

		gm.StartTest("User Validates The UI of Add New User Modal Window Default View", "");

		refresh();
		openAddNewUserModal();

		/*
		 * Header
		 */
		gm.verifyElementText(userModal.dialogHeader, "Add New User", "dialogHeader");

		/*
		 * close button
		 */
		gm.verifyElementVisible(userModal.dialogCloseIcon, "dialogCloseIcon");
		gm.verifyElementAttributeValue(userModal.dialogCloseIconImage, "src",
				"https://admin-dev.humanitytalent.net/static/media/cross.536ab879.svg", "dialogCloseIconImage");
		gm.verifyElementClickable(userModal.dialogCloseIcon, "dialogCloseIcon");

		/*
		 * Name
		 */

		gm.verifyElementText(userModal.nameLabel, "NAME", "nameLabel");

		/*
		 * email
		 */

		gm.verifyElementText(userModal.emailLabel, "EMAIL", "emailLabel");
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");
		/*
		 * company
		 */

		gm.verifyElementText(userModal.companyLabel, "COMPANY", "companyLabel");
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 1, "noSearchFound_errorText");
		/*
		 * Confirm Button
		 */

		gm.verifyElementText(userModal.confirmButton, "Confirm", "confirmButton");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");

		gm.EndTest();
	}

	@Test(enabled = false, priority = 11)
	public void validatingTheCloseButton_modal() {
		gm.StartTest("User should be able to close the Add New User Modal by clicking on close button", "");

		refresh();
		openAddNewUserModal();
		gm.click(userModal.dialogCloseIcon, "dialogCloseIcon");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.confirmButton, 5, "confirmButton");

		gm.EndTest();
	}

	@Test(enabled = false, priority = 21)
	public void validateTheNameTextField_modal() {

		// Test 1
		gm.StartTest("Name TextBox Field type, Value, Placeholder, CSS default Validation", "");

		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.nameInput, "type", "text", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "placeholder", "Enter name here", "nameInput");
		validateCSSproperty(userModal.nameInput, HTN_LoginPage.inputFieldDefaultCSSValues, "nameInput");

		gm.EndTest();

		// Test 2

		gm.StartTest("Name field should be blank by default", "");

		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Name Text field", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "qwertyuiopasdfghjklzxcvbnm", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "qwertyuiopasdfghjklzxcvbnm", "nameInput");

		gm.EndTest();

		// Test 4

		gm.StartTest("Name field value should show blank when Typed and cleared the Field by backspace", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "qwertyuiopasdfghjklzxcvbnm", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "qwertyuiopasdfghjklzxcvbnm", "nameInput");
		gm.clearbyBackspace(userModal.nameInput, "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "placeholder", "Enter name here", "nameInput");

		gm.EndTest();

		// Test 5

		gm.StartTest("Name field value should NOT be cleared when Typed and clicked on backspace only once", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "qwert", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "qwert", "nameInput");
		gm.presskeys(userModal.nameInput, Keys.BACK_SPACE, "BACK_SPACE", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "qwer", "nameInput");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Name field", "");

		refresh();
		openAddNewUserModal();
		gm.click(userModal.nameInput, "nameInput");
		gm.presskeys(userModal.nameInput, Keys.BACK_SPACE, "BACK_SPACE", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");
		gm.verifyElementAttributeValue(userModal.nameInput, "placeholder", "Enter name here", "nameInput");

		gm.EndTest();

	}

	@Test(enabled = false, priority = 31)
	public void validateTheEmailTextField_Modal() {

		// Test 1
		gm.StartTest("Email TextBox Field type, Value, Placeholder, CSS default Validation", "");

		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.emailInput, "type", "email", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "placeholder", "Enter email address here", "nameInput");
		validateCSSproperty(userModal.emailInput, HTN_LoginPage.inputFieldDefaultCSSValues, "nameInput");

		gm.EndTest();

		// Test 2

		gm.StartTest("Email field should be blank by default", "");
		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Email Text field", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "qwertyuiopasdfghjklzxcvbnm", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "qwertyuiopasdfghjklzxcvbnm", "emailInput");

		gm.EndTest();

		// Test 4

		gm.StartTest("Email field value should show blank when Typed and cleared the Field by backspace", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "qwertyuiopasdfghjklzxcvbnm", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "qwertyuiopasdfghjklzxcvbnm", "emailInput");
		gm.clearbyBackspace(userModal.emailInput, "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "placeholder", "Enter email address here", "emailInput");

		gm.EndTest();

		// Test 5

		gm.StartTest("Email field value should NOT be cleared when Typed and clicked on backspace only once", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "qwert", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "qwert", "emailInput");
		gm.presskeys(userModal.emailInput, Keys.BACK_SPACE, "BACK_SPACE", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "qwer", "emailInput");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Email field", "");

		refresh();
		openAddNewUserModal();
		gm.click(userModal.emailInput, "emailInput");
		gm.presskeys(userModal.emailInput, Keys.BACK_SPACE, "BACK_SPACE", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "placeholder", "Enter email address here", "emailInput");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 41)
	public void validateTheCompanyTextField_Modal() {

		// Test 1
		gm.StartTest("Company TextBox Field type, Value, Placeholder, CSS default Validation", "");

		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.companyInput, "type", "search", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "placeholder", "Search company name here",
				"companyInput");
		validateCSSproperty(userModal.companyInput, HTN_LoginPage.inputFieldDefaultCSSValues, "companyInput");

		gm.EndTest();

		// Test 2

		gm.StartTest("Company field should be blank by default", "");

		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Company Text field", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwertyuiopasdfghjklzxcvbnm", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwertyuiopasdfghjklzxcvbnm", "companyInput");

		gm.EndTest();

		// Test 4

		gm.StartTest("Company field value should show blank when Typed and cleared the Field by backspace", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwertyuiopasdfghjklzxcvbnm", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwertyuiopasdfghjklzxcvbnm", "companyInput");
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "placeholder", "Search company name here",
				"companyInput");

		gm.EndTest();

		// Test 5

		gm.StartTest("Company field value should NOT be cleared when Typed and clicked on backspace only once", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwert", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwert", "companyInput");
		gm.presskeys(userModal.companyInput, Keys.BACK_SPACE, "BACK_SPACE", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwer", "companyInput");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Company field", "");
		refresh();
		openAddNewUserModal();
		gm.click(userModal.companyInput, "companyInput");
		gm.presskeys(userModal.companyInput, Keys.BACK_SPACE, "BACK_SPACE", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "placeholder", "Search company name here",
				"companyInput");
		gm.EndTest();

		// Test 7

		gm.StartTest("User should be able to clear the Field values by clicking the company clear button", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwert", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwert", "companyInput");
		gm.hold(5);
		gm.click(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.hold(5);
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");

		gm.EndTest();

		// Test 8
		gm.StartTest("User should Not see the company field clear button when value is empty", "");
		refresh();
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.verifyElementNotPresent(userModal.companyInputClear_Button, 5, "companyInputClear_Button");
		gm.EndTest();

		// Test 9
		gm.StartTest("User should see the company clear button when some text is typed in the Field", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwert", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwert", "companyInput");
		gm.hold(5);
		gm.verifyElementVisible(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.EndTest();

		// Test 10
		gm.StartTest("User should NOT see the company clear button when some value is added and cleared", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "qwertyuiopasdfghjklzxcvbnm", "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "qwertyuiopasdfghjklzxcvbnm", "companyInput");
		gm.verifyElementVisible(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.verifyElementNotPresent(userModal.companyInputClear_Button, 5, "companyInputClear_Button");

		gm.EndTest();

		// Test 11
		gm.StartTest("Company search results should not be Visible when field clear button was clicked", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google", "companyInput");
		gm.hold(5);
		gm.verifyElementVisible(userModal.companySearchResult("position()"), "companySearchResults");
		gm.click(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.companySearchResult("position()"), 1, "companySearchResults");
		gm.EndTest();

		// Test 12
		gm.StartTest("Company search results should not be Visible when cleared the field using Backspace", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google", "companyInput");
		gm.hold(5);
		gm.verifyElementVisible(userModal.companySearchResult("position()"), "companySearchResults");
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.companySearchResult("position()"), 1, "companySearchResults");
		gm.EndTest();

		// Test 13
		gm.StartTest("Company search results should be Visible when cleared only one char using the Backspace", "");

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google", "companyInput");
		gm.hold(5);
		gm.verifyElementVisible(userModal.companySearchResult("position()"), "companySearchResults");
		gm.presskeys(userModal.companyInput, Keys.BACK_SPACE, "BACK_SPACE", "companyInput");
		gm.hold(2);
		gm.verifyElementVisible(userModal.companySearchResult("position()"), "companySearchResults");
		gm.EndTest();
	}

	// FunctionTest case

	@Test(enabled = false, priority = 51)
	public void validatingTheconfirmButton_FunctionTestcase()

	{

		gm.StartTest("Add New User Confirm button CSS validation in disabled state", "");
		refresh();
		openAddNewUserModal();
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "background-color", "rgba(204, 204, 204, 1)",
				"confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "color", "rgba(102, 102, 102, 1)", "confirmButton");
		gm.EndTest();

		gm.StartTest("Add New User Confirm button CSS validation in Enabled state", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.emailInput, gm.getCurrentTime("ddMMyyyyhhmmss", "GMT") + "@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.verifyElementClickable(userModal.confirmButton, "confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "background-color", "rgba(181, 248, 93, 1)", "confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "color", "rgba(8, 42, 88, 1)", "confirmButton");
		gm.EndTest();

		//// Test 3
		gm.StartTest("Add New User Confirm button should be in disabled state by default", "");
		refresh();
		openAddNewUserModal();
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "background-color", "rgba(204, 204, 204, 1)",
				"confirmButton");
		gm.verifyElementCSSValue(userModal.confirmButton, "color", "rgba(102, 102, 102, 1)", "confirmButton");
		gm.EndTest();

		/// Test 4

		gm.StartTest("Add new User confirm button should not be enabled if only UserName is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "weyrtew", "nameInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only Valid email is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "testing@grappus.com", "emailInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only InValid email is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "ajshdjkas@jksd.com.hfkjds@jsdhj.com", "emailInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only company is filled", "");
		refresh();
		openAddNewUserModal();
		selectCompany("Google", 1);
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if FreeText company is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test 1", "companyInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and email is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.emailInput, "testing@grappus.coim", "emailInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and company is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		selectCompany("Google", 1);
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and FreeText company is filled",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.companyInput, "Google Test", "companyInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if Email and company is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "testing@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if Email and FreeText company is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, "testing@grappus.com", "emailInput");
		gm.setText(userModal.companyInput, "Google Test", "companyInput");
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest(
				"Add new User confirm button should not be enabled if UserName, Email and Company is filled and company Field is cleared",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "Testing", "nameInput");
		gm.setText(userModal.emailInput, "testing@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.verifyElementClickable(userModal.confirmButton, "confirmButton");
		gm.click(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.hold(2);
		gm.verifyElementisNOTClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

		gm.StartTest("Add new User confirm button should be ENABLED if UserName, Email and company is filled", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.emailInput, gm.getCurrentTime("ddMMyyyyhhmmss", "GMT") + "@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.verifyElementClickable(userModal.confirmButton, "confirmButton");
		gm.EndTest();

	}

	@Test(enabled = false, priority = 61)
	public void valueFieldsinModaluponreopen() {

		gm.StartTest(
				"After filling values and clicking on close button the field values should be empty upon reopening the Add New User Modal ",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.emailInput, "testing@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.click(userModal.dialogCloseIcon, "dialogCloseIcon");
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.EndTest();

		gm.StartTest(
				"After Sucessfully Adding New User the field values should be empty after Inviting and upon reopen the Add New User Modal ",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, "TestName", "nameInput");
		gm.setText(userModal.emailInput, gm.getCurrentTime("ddMMyyyyhhmmss", "GMT") + "@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.click(userModal.confirmButton, "confirmButton");
		validateModalNotificationMessage("Success", "User invited successfully");
		openAddNewUserModal();
		gm.verifyElementAttributeValue(userModal.nameInput, "value", "", "nameInput");
		gm.verifyElementAttributeValue(userModal.emailInput, "value", "", "emailInput");
		gm.verifyElementAttributeValue(userModal.companyInput, "value", "", "companyInput");
		gm.EndTest();

		gm.StartTest(
				"After Not Sucessfully Adding New User the field values should be empty after Inviting and upon reopen the Add New User Modal ",
				"");
		gm.logSkip("This need to be handeled manulayy");
		gm.EndTest();

	}

	@Test(enabled = false, priority = 71)
	public void validatingNoResultFoundErrorMessagewithCompany() {

		gm.StartTest("No result found - Company should not be visible by default", "");
		refresh();
		openAddNewUserModal();
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 2, "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest("Verify the Text No search Result when Invalid company name is entered", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test Test Test", "companyInput");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.companySearchResult(1), 1, "companySearchResults");
		gm.verifyElementVisible(userModal.noSearchFound_errorText, "noSearchFound_errorText");
		gm.verifyElementText(userModal.noSearchFound_errorText, "No result found", "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest("No result found - Company should be visible if search results are not found", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test Test Test", "companyInput");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.companySearchResult(1), 1, "companySearchResults");
		gm.verifyElementVisible(userModal.noSearchFound_errorText, "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest("No result found - Company should NOT be visible if search results are found", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google", "companyInput");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 2, "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest(
				"No result found - Company should NOT be visible if Invalid value is added, cleared and Valid value is entered",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test Test Test", "companyInput");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.companySearchResult(1), 1, "companySearchResults");
		gm.verifyElementVisible(userModal.noSearchFound_errorText, "noSearchFound_errorText");
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.setText(userModal.companyInput, "Google", "companyInput");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 2, "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest(
				"No result found - Company should NOT be visible if Invalid value is added and cleared using button",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test Test Test", "companyInput");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.companySearchResult(1), 1, "companySearchResults");
		gm.verifyElementVisible(userModal.noSearchFound_errorText, "noSearchFound_errorText");
		gm.click(userModal.companyInputClear_Button, "companyInputClear_Button");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 2, "noSearchFound_errorText");
		gm.EndTest();

		gm.StartTest(
				"No result found - Company should NOT be visible if Invalid value is added and cleared using Backspace",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google Test Test Test", "companyInput");
		gm.hold(5);
		gm.verifyElementNotPresent(userModal.companySearchResult(1), 1, "companySearchResults");
		gm.verifyElementVisible(userModal.noSearchFound_errorText, "noSearchFound_errorText");
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.hold(2);
		gm.verifyElementNotPresent(userModal.noSearchFound_errorText, 2, "noSearchFound_errorText");
		gm.EndTest();
	}

	public void circularValidationComapnysearch() {
		gm.waitforElementVisible(userModal.companyCircular_Button, 2, "companyCircular_Button");
		gm.verifyElementVisible(userModal.companyCircular_Button, "companyCircular_Button");
		gm.verifyElementNotPresent(userModal.companyInputClear_Button, 1, "companyInputClear_Button");
		gm.waitForElementNotPresent(userModal.companyCircular_Button, 10, "companyCircular_Button");
		gm.verifyElementVisible(userModal.companyInputClear_Button, "companyInputClear_Button");
	}

	@Test(enabled = false, priority = 120)
	public void validatingTheCompanySearchCirular() {

		gm.StartTest("Company Name Search Inprogress Validation when Text is Enetred", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "Google", "companyInput");
		circularValidationComapnysearch();
		gm.EndTest();

		gm.StartTest("Company Name Search Inprogress circular Validation when character is Enetred", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "g", "companyInput");
		circularValidationComapnysearch();
		gm.EndTest();

		gm.StartTest("Company Name Search Inprogress circular Validation when word is Entered and cleared a character",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "google", "companyInput");
		circularValidationComapnysearch();
		gm.presskeys(userModal.companyInput, Keys.BACK_SPACE, "Backspace", "CompanyInput");
		circularValidationComapnysearch();
		gm.EndTest();

		gm.StartTest(
				"Company Name Search Inprogress circular Validation when word is Entered and another character is Entered",
				"");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "google", "companyInput");
		circularValidationComapnysearch();
		try {
			gm.findWebElement(userModal.companyInput).sendKeys("A");
			gm.logPass("Pressed the KEY A");
		} catch (Exception e) {
			gm.logFail("Unable to press the Key A");
		}
		circularValidationComapnysearch();
		gm.EndTest();

		gm.StartTest("Company Name Search Inprogress circular Validation when Text is cleared", "");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, "google", "companyInput");
		circularValidationComapnysearch();
		gm.clearbyBackspace(userModal.companyInput, "companyInput");
		gm.verifyElementNotPresent(userModal.companyCircular_Button, 5, "companyCircular_Button");
		gm.EndTest();

	}
	/*
	 * TDM Testing
	 */

	@Test(enabled = false, priority = 121, dataProviderClass = EnvironmentDetails.class, dataProvider = "validEmailAddress")
	public void validatingInvalidEmailAddressErrorMessagewithValidEmails_TDM(String ValidEmailAddress) {
		gm.StartTest("validatingInvalidEmailAddressErrorMessagewithValidEmails_TDM", "");
		gm.loginfo(
				"Validating the error message is not Visible when entered the  Email address -> " + ValidEmailAddress);

		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, ValidEmailAddress, "emailInput");
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");
		gm.EndTest();
	}

	@Test(enabled = false, priority = 131, dataProviderClass = EnvironmentDetails.class, dataProvider = "InvalidEmailAddress")
	public void validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM(String InValidEmailAddress) {
		gm.StartTest("validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM", "");
		gm.loginfo("Validating the error message is not Visible when entered the  Email address -> "
				+ InValidEmailAddress);
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.emailInput, InValidEmailAddress, "emailInput");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.verifyElementText(lp.invalidEmailAddress_ErrorMessage, "Invalid email address",
				"invalidEmailAddress_ErrorMessage");

		gm.EndTest();
	}

	@Test(enabled = false, priority = 141, dataProviderClass = EnvironmentDetails.class, dataProvider = "validSearchResultCompanynames")
	public void validatingTheCompanysearchField(String Companytext) {

		gm.StartTest("Company Name Search Result Validation", "");
		gm.loginfo("Validating Company Names usign the Text ->" + Companytext);
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.companyInput, Companytext, "companyInput");
		gm.waitforElementVisible(userModal.companyCircular_Button, 2, "companyCircular_Button");
		gm.waitForElementNotPresent(userModal.companyCircular_Button, 10, "companyCircular_Button");
		gm.waitforElementVisible(userModal.companyInputClear_Button, 2, "companyInputClear_Button");
		gm.verifyListofElementsContainsinnerText(userModal.companySearchResult("position()"), Companytext,
				"companySearchResult");
		gm.EndTest();

	}

	@Test(enabled = false, priority = 142)
	public void validatingTheCustomCreatedCompanysearchField() {
		gm.StartTest("User check if the Cutsom added Company name is Visisble when searched", "");

		String companyName = gm.getCurrentTime("ddmmyyyhhmmss", "GMT");
		refresh();
		gm.click(dash.onboarding, "onboarding");
		gm.hold(5);
		gm.waitforElementVisible(onboarding.Company_button, 5, "Company_button");
		gm.click(onboarding.Company_button, "Company_button");
		gm.hold(5);
		gm.click(onboarding.addNewCompany, "addNewCompany");
		gm.setText(onboarding.enterCompany_input, companyName, "enterCompany_input");
		gm.click(onboarding.conifrm_Button, "conifrm_Button");
		validateModalNotificationMessage("Success", "New company added successfully.");
		gm.click(dash.users, "Users");
		gm.hold(5);
		openAddNewUserModal();
		gm.setText(userModal.companyInput, companyName, "companyInput");
		gm.hold(2);
		gm.waitForElementNotPresent(userModal.companyCircular_Button, 10, "companyCircular_Button");
		gm.waitforElementVisible(userModal.companyInputClear_Button, 2, "companyInputClear_Button");
		gm.verifyListofElementsContainsinnerText(userModal.companySearchResult("position()"), companyName,
				"companySearchResult");
		gm.EndTest();
	}

	@Test(enabled = true, priority = 151)
	public void sucessfullyInviteANewUser() {
		gm.StartTest("Sucessfully Add a New User from HTN Admin panel and Validate the Notification message", "");
		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		refresh();
		openAddNewUserModal();
		gm.setText(userModal.nameInput, name, "nameInput");
		gm.setText(userModal.emailInput, name + "@grappus.com", "emailInput");
		selectCompany("Google", 1);
		gm.click(userModal.confirmButton, "confirmButton");
		validateModalNotificationMessage("Success", "User invited successfully");
	}
}
