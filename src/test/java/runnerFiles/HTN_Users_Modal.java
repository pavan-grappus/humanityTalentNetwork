package runnerFiles;

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

	@Test(enabled = true, priority = 1)
	public void validatingTheAddNewUserModalWindow() {

		gm.StartTest("User Validates The UI of Add New User Modal Window Default View", "");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 11)
	public void validatingTheCloseButton_modal() {
		gm.StartTest("User should be able to close the Add New User Modal by clicking on close button", "");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 21)
	public void validateTheNameTextField_modal() {

		// Test 1
		gm.StartTest("Name TextBox Field type, Value, Placeholder, CSS default Validation", "");

		gm.EndTest();

		// Test 2

		gm.StartTest("Name field should be blank by default", "");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Name Text field", "");

		gm.EndTest();

		// Test 4

		gm.StartTest("Name field value should show blank when Typed and cleared the Field by backspace", "");

		gm.EndTest();

		// Test 5

		gm.StartTest("Name field value should NOT be cleared when Typed and clicked on backspace only once", "");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Name field", "");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 31)
	public void validateTheEmailTextField_Modal() {

		// Test 1
		gm.StartTest("Email TextBox Field type, Value, Placeholder, CSS default Validation", "");

		gm.EndTest();

		// Test 2

		gm.StartTest("Email field should be blank by default", "");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Email Text field", "");

		gm.EndTest();

		// Test 4

		gm.StartTest("Email field value should show blank when Typed and cleared the Field by backspace", "");

		gm.EndTest();

		// Test 5

		gm.StartTest("Email field value should NOT be cleared when Typed and clicked on backspace only once", "");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Email field", "");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 41)
	public void validateTheCompanyTextField_Modal() {

		// Test 1
		gm.StartTest("Company TextBox Field type, Value, Placeholder, CSS default Validation", "");

		gm.EndTest();

		// Test 2

		gm.StartTest("Company field should be blank by default", "");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Company Text field", "");

		gm.EndTest();

		// Test 4

		gm.StartTest("Company field value should show blank when Typed and cleared the Field by backspace", "");

		gm.EndTest();

		// Test 5

		gm.StartTest("Company field value should NOT be cleared when Typed and clicked on backspace only once", "");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Company field", "");

		gm.EndTest();

		// Test 6

		gm.StartTest("User should be able to clear the Field values by clicking the company clear button", "");

		gm.EndTest();

		// Test 7
		gm.StartTest("User should be see the company clear button when value is empty", "");

		gm.EndTest();

		// Test 7
		gm.StartTest("User should be see the company clear button only when some value is added", "");

		gm.EndTest();

		// Test 8
		gm.StartTest("User should be see the company clear button only when some value is added", "");

		gm.EndTest();
	}

	// FunctionTest case

	@Test(enabled = true, priority = 51)
	public void validatingTheconfirmButton_FunctionTestcase()

	{
		gm.StartTest("Add New User Confirm button should be in disabled state by default", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only UserName is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only email is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if only company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if FreeText company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and email is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if UserName and FreeText company is filled",
				"");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if Email and company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should not be enabled if Email and FreeText company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should be ENABLED if UserName, Email and company is filled", "");

		gm.EndTest();

		gm.StartTest("Add new User confirm button should be ENABLED if UserName, Email and company is filled", "");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 61)
	public void valueFieldsinModaluponreopen() {

		gm.StartTest(
				"After filling values and clicking on close button the field values should be empty upon reopening the Add New User Modal ",
				"");

		gm.EndTest();

		gm.StartTest(
				"After Sucessfully Adding New User the field values should be empty upon reopen the Add New User Modal ",
				"");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 71)
	public void validatingNoResultFoundErrorMessagewithCompany() {

		gm.StartTest("No result found - Company should not be visible by default", "");
		gm.EndTest();

		gm.StartTest("No result found - Company should be visible if search results are not found", "");
		gm.EndTest();

		gm.StartTest("No result found - Company should be NOT visible if search results are found", "");
		gm.EndTest();

		gm.StartTest(
				"No result found - Company should be NOT visible if results are NOT found and Valid value is entered",
				"");
		gm.EndTest();

	}

	/*
	 * TDM Testing
	 */

	@Test(enabled = true, priority = 121, dataProviderClass = EnvironmentDetails.class, dataProvider = "validEmailAddress")
	public void validatingInvalidEmailAddressErrorMessagewithValidEmails_TDM(String ValidEmailAddress) {
		gm.StartTest("validatingInvalidEmailAddressErrorMessagewithValidEmails_TDM", "");
		gm.loginfo(
				"Validating the error message is not Visible when entered the  Email address -> " + ValidEmailAddress);

		gm.EndTest();
	}

	@Test(enabled = true, priority = 131, dataProviderClass = EnvironmentDetails.class, dataProvider = "InvalidEmailAddress")
	public void validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM(String ValidEmailAddress) {
		gm.StartTest("validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM", "");
		gm.loginfo(
				"Validating the error message is not Visible when entered the  Email address -> " + ValidEmailAddress);

		gm.EndTest();
	}

	@Test(enabled = true, priority = 141)
	public void validatingTheCompanysearchField() {

		gm.StartTest("Input full company name", "");
		gm.EndTest();

		gm.StartTest("Input One character", "");
		gm.EndTest();

		gm.StartTest("Input one word", "");
		gm.EndTest();

		gm.StartTest("Input middle word", "");
		gm.EndTest();

		gm.StartTest("Input numbers", "");
		gm.EndTest();

		gm.StartTest("Input two words", "");
		gm.EndTest();

		gm.StartTest("Input custom company name", "");
		gm.EndTest();

		gm.StartTest("Input in Uppercase", "");
		gm.EndTest();

		gm.StartTest("Input in lower case", "");
		gm.EndTest();
	}
}
