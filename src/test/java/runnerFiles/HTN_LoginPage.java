package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class HTN_LoginPage extends BeforeRun {

	String inputFieldDefaultCSSValues = "height: 50px;\r\n" + "    width: 313px;\r\n" + "    padding: 15px 20px;\r\n"
			+ "    padding-top: 15px;\r\n" + "    padding-right: 20px;\r\n" + "    padding-bottom: 15px;\r\n"
			+ "    padding-left: 20px;\r\n" + "    border-radius: 25px;\r\n" + "    border-top-left-radius: 25px;\r\n"
			+ "    border-top-right-radius: 25px;\r\n" + "    border-bottom-right-radius: 25px;\r\n"
			+ "    border-bottom-left-radius: 25px;\r\n" + "    background-color: rgba(242, 243, 244, 1);\r\n"
			+ "    font-family: Inter;\r\n" + "    font-size: 16px;\r\n" + "    line-height: 20px;\r\n"
			+ "    letter-spacing: -0.1px;";

	String buttonDefaultCSSValues = "		 width: 315px;\r\n" + "	    height: 50px;\r\n"
			+ "	    padding-top: 17px;\r\n" + "	    padding-right: 132px;\r\n" + "	    padding-bottom: 17px;\r\n"
			+ "	    padding-left: 113px;\r\n" + "		border-top-left-radius: 35px;\r\n"
			+ "	    border-top-right-radius: 35px;\r\n" + "	    border-bottom-right-radius: 35px;\r\n"
			+ "	    border-bottom-left-radius: 35px;\r\n" + "	    background-color: rgba(181, 248, 93, 1);\r\n"
			+ "	    font-family: Inter;\r\n" + "	    font-size: 16px;\r\n" + "	    font-weight: 600;\r\n"
			+ "	    color: rgba(8, 42, 88, 1);";

	String EmailIDinputFieldTitleLabel = "font-family: InterBold;\r\n" + "    padding-top: 62.88px;\r\n"
			+ "    padding-left: 20px;\r\n" + "    margin-bottom: 5px;\r\n" + "    opacity: 0.5;\r\n"
			+ "    font-size: 12px;\r\n" + "    letter-spacing: 0.5px;";

	String PasswordTitleLabel = "font-family: InterBold;\r\n" + "    padding-top: 30px;\r\n"
			+ "    padding-left: 20px;\r\n" + "    margin-bottom: 5px;\r\n" + "    opacity: 0.5;\r\n"
			+ "    font-size: 12px;\r\n" + "    letter-spacing: 0.5px;";

	@BeforeClass
	public void waitforTheBrowserLoadComplete() {
		gm.waitforElementVisible(lp.emailid_input, 10, "emailid_input");
		gm.hold(5);
	}

	@Test(enabled = true, priority = 1)
	public void validateTheLoginPageTabDetails() {
		gm.StartTest("LoginPage Tab Icon", "LoginPage Tab Icon");

		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");

		gm.verifyPageTitle("Humanity Health Admin");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 11)
	public void validateTheLoginPageLeftPanelObject() {
		gm.StartTest("Validate The Left Panel Background color", "Validate The Left Panel Image objects");

		gm.verifyElementCSSValue(lp.leftSection, "width", "628.359px", "leftSection width");
		gm.verifyElementCSSValue(lp.leftSection, "min-height", "612px", "leftSection min-height");
		gm.verifyElementCSSValue(lp.leftSection, "background-color", "rgba(8, 42, 88, 1)",
				"leftSection background-color");

		gm.verifyElementAttributeValue(lp.leftpanelHTNLogo, "height", "73", "leftpanelHTNLogo height");
		gm.verifyElementAttributeValue(lp.leftpanelHTNLogo, "src",
				EnvironmentDetails.htnURL + "static/media/logo-icon.ab763d13.svg", "leftpanelHTNLogo src");

		gm.verifyElementAttributeValue(lp.leftpanelHTNName, "height", "73", "leftpanelHTNName height");
		gm.verifyElementAttributeValue(lp.leftpanelHTNName, "src",
				EnvironmentDetails.htnURL + "static/media/humanity-talent-network.72cb1432.svg",
				"leftpanelHTNName src");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 21)
	public void validateTheLoginPageRightPanelObject() {

		gm.StartTest("Validate The Right Panel Background color", "Validate The Right Panel Background color");

		gm.verifyElementCSSValue(lp.rightSection, "width", "737.625px", "rightSection width");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 31)
	public void validateTheRightPanelHeader() {
		gm.StartTest("Right Panel Header", "");

		gm.verifyElementText(lp.heading, "Welcome to Humanity Health\r\n" + "Network Admin Console", "Heading");

		validateCSSproperty(lp.heading,
				"font-family: P22Mackinac;\r\n" + "    font-size: 36px;\r\n" + "    font-weight: 500;\r\n"
						+ "    line-height: 50.04px;\r\n" + "    letter-spacing: -0.28px;\r\n"
						+ "    color: rgba(8, 42, 88, 1);",
				"heading");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 41)
	public void validateTheRightPanelDescription() {
		gm.StartTest("Right Panel Description", "");

		gm.verifyElementText(lp.subHeader, "Please login to continue", "subHeader");

		validateCSSproperty(lp.subHeader,
				"font-family: Inter;\r\n" + "    padding-top: 19px;\r\n" + "    opacity: 0.5;\r\n"
						+ "    font-size: 16px;\r\n" + "    font-weight: 500;\r\n" + "    line-height: 20px;\r\n"
						+ "    letter-spacing: -0.1px;\r\n" + "    color: rgba(33, 33, 35, 1);",
				"subHeader");

		gm.EndTest();
	}

	@Test(enabled = true,priority = 51)
	public void validateTheEmailLablel() {
		gm.StartTest("Email TextBox Label", "");

		gm.verifyElementText(lp.emailId_label, "EMAIL", "emailId_label");

		validateCSSproperty(lp.emailId_label, EmailIDinputFieldTitleLabel, "emailId_label");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 61)
	public void validateTheEmailTextField() {

		// Test 1
		gm.StartTest("Email Address TextBox Field", "Email Address Text Field");

		gm.verifyElementAttributeValue(lp.emailid_input, "type", "email", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "", "emailid_input Value");
		gm.verifyElementAttributeValue(lp.emailid_input, "placeholder", "Enter your email id", "emailid_input");
		validateCSSproperty(lp.emailid_input, inputFieldDefaultCSSValues, "emailid_input");

		gm.EndTest();

		// Test 2

		gm.StartTest("Email ID field should be blank by default", "");

		gm.refresh("Login Page", 10);
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "", "emailid_input Value");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Email ID Text field", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "qwertyuiopasdfghjklzxcvbnm", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "qwertyuiopasdfghjklzxcvbnm", "emailid_input value");

		gm.EndTest();

		// Test 4

		gm.StartTest("Email ID field value should show blank when Typed and cleared the Field by backspace", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "qwert", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "qwert", "emailid_input Value");
		gm.clearbyBackspace(lp.emailid_input, "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "", "emailid_input Value");
		gm.verifyElementAttributeValue(lp.emailid_input, "placeholder", "Enter your email id", "emailid_input");

		gm.EndTest();

		// Test 5

		gm.StartTest("Email ID field value should NOT be cleared when Typed and clicked on backspace only once", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "qwert", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "qwert", "emailid_input Value");
		gm.presskeys(lp.emailid_input, Keys.BACK_SPACE, "BACK_SPACE", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "qwer", "emailid_input Value");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Email ID field", "");

		gm.refresh("Login Page", 10);
		gm.click(lp.emailid_input, "emailid_input");
		gm.presskeys(lp.emailid_input, Keys.BACK_SPACE, "BACK_SPACE", "emailid_input");
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "", "emailid_input Value");
		gm.verifyElementAttributeValue(lp.emailid_input, "placeholder", "Enter your email id", "emailid_input");
		gm.EndTest();

	}

	@Test(enabled = true, priority = 71)
	public void validateThePasswordLablel() {
		gm.StartTest("Password TextBox Label", "");

		gm.verifyElementText(lp.password_label, "PASSWORD", "password_label");

		validateCSSproperty(lp.password_label, PasswordTitleLabel, "password_label");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 81)
	public void validateThePasswordTextField() {

		// Test 1
		gm.StartTest("Password TextBox Field", "Password Text Field");

		gm.verifyElementAttributeValue(lp.password_input, "type", "password", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "", "password_input Value");
		gm.verifyElementAttributeValue(lp.password_input, "placeholder", "Enter password", "password_input");
		validateCSSproperty(lp.password_input, inputFieldDefaultCSSValues, "password_input");

		gm.EndTest();

		// Test 2

		gm.StartTest("Password field should be blank by default", "");

		gm.refresh("Login Page", 10);
		gm.verifyElementAttributeValue(lp.password_input, "value", "", "password_input Value");

		gm.EndTest();

		// Test 3

		gm.StartTest("User should be able to see all the Typed Characters in Password Text field", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.password_input, "qwertyuiopasdfghjklzxcvbnm", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "qwertyuiopasdfghjklzxcvbnm",
				"password_input value");

		gm.EndTest();

		// Test 4

		gm.StartTest("Password field value should show blank when Typed and cleared the Field by backspace", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.password_input, "qwert", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "qwert", "password_input Value");
		gm.clearbyBackspace(lp.password_input, "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "", "password_input Value");
		gm.verifyElementAttributeValue(lp.password_input, "placeholder", "Enter password", "password_input");

		gm.EndTest();

		// Test 5

		gm.StartTest("Password field value should NOT be cleared when Typed and clicked on backspace only once", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.password_input, "qwert", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "qwert", "password_input Value");
		gm.presskeys(lp.password_input, Keys.BACK_SPACE, "BACK_SPACE", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "qwer", "password_input Value");

		gm.EndTest();

		// Test 6

		gm.StartTest("User clicks on Backspace when nothing is Typed in Password field", "");

		gm.refresh("Login Page", 10);
		gm.click(lp.password_input, "password_input");
		gm.presskeys(lp.password_input, Keys.BACK_SPACE, "BACK_SPACE", "password_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "", "password_input Value");
		gm.verifyElementAttributeValue(lp.password_input, "placeholder", "Enter password", "password_input");
		gm.EndTest();

	}

	@Test(enabled = true, priority = 91)
	public void validateTheContinueButton_Default() {
		gm.StartTest("Continue Button in Login Page", "Continue Button in Login Page");

		gm.verifyElementText(lp.Continue_button, "Continue", "Continue_button");

		validateCSSproperty(lp.Continue_button, buttonDefaultCSSValues, "Continue_button");

		gm.EndTest();
	}

	/*
	 * FUNCTIONAL TEST CASES
	 */

	@Test(enabled = true, priority = 101)
	public void validatingInvalidEmailAddressErrorMessage() {

		// Test 1

		gm.StartTest("Invalid Email Address message should not be Visible by default", "");

		gm.refresh("Login Page", 10);
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");

		gm.EndTest();

		// Test 2

		gm.StartTest("Invalid Email Address message SHOULD NOT BE VISIBLE when VALID Email address is Entered", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test@grappus.com", "emailid_input");
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");

		gm.EndTest();

		// Test 3

		gm.StartTest("Invalid Email Address message SHOULD BE VISIBLE when INVALID Email address is Entered", "");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test.com", "emailid_input");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.verifyElementText(lp.invalidEmailAddress_ErrorMessage, "Invalid email address",
				"invalidEmailAddress_ErrorMessage");

		gm.EndTest();

		// Test 4

		gm.StartTest(
				"Invalid Email Address message SHOULD NOT BE VISIBLE when INVALID Email address is Entered and cleared the Field",
				"");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test.com", "emailid_input");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.clearbyBackspace(lp.emailid_input, "emailid_input");
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");
		gm.EndTest();

		// Test 5

		gm.StartTest(
				"Invalid Email Address message SHOULD BE VISIBLE when INVALID Email address is Entered and cleared only one character using Backspace",
				"");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test.com", "emailid_input");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.presskeys(lp.emailid_input, Keys.BACK_SPACE, "BACK_SPACE", "emailid_input");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.EndTest();

	}

	@Test(enabled = true, priority = 111)
	public void validatingTheContinueButton_FunctionalTestcases() {

		// Test 1

		gm.StartTest("Continue button should be in Disabled state when Email ID and Password is not Entered", "");

		gm.refresh("Login Page", 10);
		gm.verifyElementAttributeValue(lp.emailid_input, "value", "", "emailid_input");
		gm.verifyElementAttributeValue(lp.password_input, "value", "", "password_input");
		gm.verifyElementisNOTClickable(lp.Continue_button, "Continue_button");

		gm.EndTest();

		// Test 2

		gm.StartTest(
				"Continue button should be in Disabled state when Valid Email ID is Entered and Password is not Entered",
				"");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test@grappus.com", "emailid_input");
		gm.verifyElementisNOTClickable(lp.Continue_button, "Continue_button");

		gm.EndTest();

		// Test 3

		gm.StartTest("Continue button should be in Disabled state when Email ID is NOT Entered and Password is Entered",
				"");

		gm.refresh("Login Page", 10);
		gm.setText(lp.password_input, "Test@123", "password_input");
		gm.verifyElementisNOTClickable(lp.Continue_button, "Continue_button");
		gm.EndTest();

		// Test 4

		gm.StartTest(
				"Continue button should be in Disabled state when INVALID Email ID is Entered and Password is Entered",
				"");

		gm.refresh("Login Page", 10);
		gm.setText(lp.emailid_input, "test.com", "emailid_input");
		gm.setText(lp.password_input, "Test@123", "password_input");
		gm.verifyElementisNOTClickable(lp.Continue_button, "Continue_button");

		gm.EndTest();

		// Test 5

		gm.StartTest("Continue button should be in Enabled state when Email ID and Password is Entered", "");

		gm.refresh("Login Page", 10);

		gm.setText(lp.emailid_input, "test@grappus.com", "emailid_input");
		gm.setText(lp.password_input, "Test@123", "password_input");
		gm.verifyElementClickable(lp.Continue_button, "Continue_button");

		gm.EndTest();

		// Test 6

		gm.StartTest(
				"Continue button should be in Enabled state when Email and Password are selected from Browser Crediential Manager",
				"");

		gm.loginfo("This need to be Tested manually");
		gm.logSkip("");
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
		gm.refresh("LoginPage", 10);

		gm.setText(lp.emailid_input, ValidEmailAddress, "emailid_input");
		gm.verifyElementNotPresent(lp.invalidEmailAddress_ErrorMessage, 1, "invalidEmailAddress_ErrorMessage");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 131, dataProviderClass = EnvironmentDetails.class, dataProvider = "InvalidEmailAddress")
	public void validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM(String ValidEmailAddress) {
		gm.StartTest("validatingInvalidEmailAddressErrorMessagewithINValidEmails_TDM", "");
		gm.loginfo(
				"Validating the error message is not Visible when entered the  Email address -> " + ValidEmailAddress);
		gm.refresh("LoginPage", 10);

		gm.setText(lp.emailid_input, ValidEmailAddress, "emailid_input");
		gm.verifyElementVisible(lp.invalidEmailAddress_ErrorMessage, "invalidEmailAddress_ErrorMessage");
		gm.verifyElementText(lp.invalidEmailAddress_ErrorMessage, "Invalid email address",
				"invalidEmailAddress_ErrorMessage");

		gm.EndTest();
	}

	@Test(enabled = true, priority = 141)
	public void validatingToastMessage() {

		// Test 1
		gm.StartTest("Wrong Email or Password - When Invalid email and Invalid password is Entered", "");

		gm.refresh("LoginPage", 10);
		gm.setText(lp.emailid_input, RandomStringUtils.randomAlphabetic(5) + "@grappus.com", "emailid_input");
		gm.setText(lp.password_input, RandomStringUtils.randomAlphanumeric(5), "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		validateModalNotificationMessage("Error", "Wrong email or password.");

		gm.EndTest();

		// Test 2
		gm.StartTest("Wrong Email or Password - When Valid email and InValid password is Entered", "");

		gm.refresh("LoginPage", 10);
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName, "emailid_input");
		gm.setText(lp.password_input, RandomStringUtils.randomAlphanumeric(5), "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		validateModalNotificationMessage("Error", "Wrong email or password.");

		gm.EndTest();

		// Test 3
		gm.StartTest("Wrong Email or Password - When InValid email and random Email's Valid password is Entered", "");

		gm.refresh("LoginPage", 10);
		gm.setText(lp.emailid_input, RandomStringUtils.randomAlphabetic(5) + "@grappus.com", "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		validateModalNotificationMessage("Error", "Wrong email or password.");

		gm.EndTest();

		// Test 4
		gm.StartTest("Wrong Email or Password - When Valid email and Valid password with Lowercases is Entered", "");

		gm.refresh("LoginPage", 10);
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName, "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword.toLowerCase(), "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		validateModalNotificationMessage("Error", "Wrong email or password.");

		gm.EndTest();

		// Test 5
		gm.StartTest("Wrong Email or Password - When Valid email and Valid password with Uppercase is Entered", "");

		gm.refresh("LoginPage", 10);
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName, "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword.toUpperCase(), "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		validateModalNotificationMessage("Error", "Wrong email or password.");

		gm.EndTest();

	}

	@Test(enabled = true, priority = 151)
	public void validatingLoginToApplication() {
		// Test 1
		gm.StartTest("Users Login -  When Valid email and Valid password is Entered", "");

		launchNewBrowser();
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName, "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		gm.verifyElementNotPresent(go.modalHeader, 5, "modalHeader");
		gm.waitforElementVisible(us.addNewUser_button, 10, "addNewUser_button");
		gm.EndTest();

		// Test 2
		gm.StartTest("Users Login -  When Valid email IN UPPERCASE and Valid password is Entered", "");

		launchNewBrowser();
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName.toUpperCase(), "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		gm.verifyElementNotPresent(go.modalHeader, 5, "modalHeader");
		gm.waitforElementVisible(us.addNewUser_button, 10, "addNewUser_button");

		gm.EndTest();

		// Test 3
		gm.StartTest("Users Login -  When Valid email IN LOWERCASE and Valid password is Entered", "");

		launchNewBrowser();
		gm.setText(lp.emailid_input, EnvironmentDetails.SuperAdminUserName.toLowerCase(), "emailid_input");
		gm.setText(lp.password_input, EnvironmentDetails.SuperAdminPassword, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		gm.verifyElementNotPresent(go.modalHeader, 5, "modalHeader");
		gm.waitforElementVisible(us.addNewUser_button, 10, "addNewUser_button");

		gm.EndTest();
	}

	public void validateModalNotificationMessage(String modalHeader, String modalMesssage) {
		gm.waitforElementVisible(go.modalHeader, 10, "modalHeader");
		gm.verifyElementText(go.modalHeader, modalHeader, "modalHeader");
		gm.verifyElementText(go.modalSubText, modalMesssage, "modalSubText");
		gm.click(go.modalCloseButton, "modalCloseButton");
		gm.hold(2);
	}

}
