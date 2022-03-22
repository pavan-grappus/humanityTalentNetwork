package runnerFiles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import MobileElements.SetupPassword;
import MobileElements.SignUpPage;
import MobileElements.WelcomePage;
import MobileElements.WelcomeVideo_Mobile;
import PageObjects.GeneralObjects;
import PageObjects.Onboarding;
import PageObjects.WelcomeVideo;
import PageObjects.addNewUser_Modal;
import PageObjects.dashboard;
import PageObjects.logginPage;
import PageObjects.users;
import resource.EnvironmentDetails;
import utility.GenericMethods;
import utility.MobileMethods;

public class BeforeRun {

	GenericMethods gm;
	MobileMethods mobile;
	GeneralObjects go = new GeneralObjects();
	logginPage lp = new logginPage();
	users us = new users();
	addNewUser_Modal userModal = new addNewUser_Modal();
	dashboard dash = new dashboard();
	Onboarding onboarding = new Onboarding();
	WelcomeVideo welcomeVideo = new WelcomeVideo();
	WelcomePage mobileWelcomePage = new WelcomePage();
	SignUpPage mobileSignUpPage = new SignUpPage();
	SetupPassword passwordPage = new SetupPassword();
	WelcomeVideo_Mobile welcomeVideoMobile = new WelcomeVideo_Mobile();

	@BeforeSuite(enabled = true)
	public void readingVariablesfromFile() throws IOException {

		gm = new GenericMethods(EnvironmentDetails.projectDirectory + "\\Driver\\",
				EnvironmentDetails.projectDirectory + "\\Reports\\", "HTN_");
		mobile = new MobileMethods(gm.logger);

	}

	public void launchNewBrowser() {

		try {
			gm.driver.quit();
		} catch (Exception e) {
		}
		gm.OpenBrowser(EnvironmentDetails.htnURL, "chromeinsecurecontent");
	}

	public void launchApplication() {

		mobile.launchApplication();
	}

	@BeforeTest(enabled = true)
	public void logintoApplication() {

		gm.StartTest("Launch the Browser", "Loggin to the Application");

		launchNewBrowser();
//		gm.waitforElementVisible(lp.emailid_input, 60, "Email ID Field");
//		gm.hold(5);
//		gm.setText(lp.emailid_input, "admin_ankit_dev@grappus.com", "Email ID Field");
//		gm.setText(lp.password_input, "Test@123", "Password Field");
//		gm.click(lp.Continue_button, "Continue");
//		gm.hold(10);
//
//		gm.verifyElementVisible(us.filters_Button, "Filters_Button");
		gm.EndTest();
	}

	@BeforeTest(enabled = false)
	public void loginbebuggmode() {

//		gm.StartTest("asdasd", "asdasqwrfasfsdfsdf");
//		gm.startindebugmode("https://develop.d3jv8axd97ss5j.amplifyapp.com/",
//				"E:\\eclipseWorkSpace\\HumanityHealth\\Driver\\chromedriver.exe");
		gm.runindebugmode("E:\\eclipseWorkSpace\\HumanityHealth\\Driver\\" + "chromedriver.exe");
		// logintoApplication();
	}

	@AfterMethod(enabled = true)
	public void closeTest() {
		// gm.closeBrowser();
		gm.EndTest();
	}

	@AfterTest(enabled = false)
	public void teardownReport() {
		gm.EndTest();
		gm.EndReports();
	}

	public void validateCSSproperty(By path, String cssProperty, String note) {

		List<String> list = Arrays.asList(cssProperty.trim().split(";\r\n|;"));

		for (String s : list) {
			String[] splitArray = s.split(":");
			String Key = splitArray[0].trim();
			String value = splitArray[1].trim();

			gm.verifyElementCSSValue(path, Key, value, note + " " + Key);

		}
	}

	public void signUpWithEmail(String emailAddress) {
		mobile.click(mobileWelcomePage.getStarted, "getStarted");

		mobile.setText(mobileSignUpPage.enterYourEmail, emailAddress, "enterYourEmail");

		mobile.click(mobileSignUpPage.getStarted, "getStarted");

		mobile.setText(passwordPage.createAPassword, "Test@123", "createAPassword");

		mobile.click(passwordPage.saveAndContinue, "saveAndContinue");

		mobile.click(welcomeVideoMobile.getStarted_button, "getStarted_button");

		mobile.click(welcomeVideoMobile.iPledge_button, "iPledge_button");

	}

	public void userLoginToTheWebApplication(String emailString, String passwordString) {
		gm.setText(lp.emailid_input, emailString, "emailid_input");
		gm.setText(lp.password_input, passwordString, "password_input");
		gm.click(lp.Continue_button, "Continue_button");
		gm.waitforElementVisible(us.addNewUser_button, 10, "addNewUser_button");
		gm.hold(5);
	}

	public void validateModalNotificationMessage(String modalHeader, String modalMesssage) {
		gm.waitforElementVisible(go.modalHeader, 10, "modalHeader");
		gm.verifyElementText(go.modalHeader, modalHeader, "modalHeader");
		gm.verifyElementText(go.modalSubText, modalMesssage, "modalSubText");
		gm.click(go.modalCloseButton, "modalCloseButton");
		gm.hold(2);
	}

	public int getInvitedPercentage(int members, int invited) {
		return Math.round(members * 100f / invited);
	}

}
