package runnerFiles;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class HTN_Users extends BeforeRun {

	String sponsorSubText = "You earned your seat at the table." + "\n" + "We help you land it.";
	String message = "The Humanity Talent Network (HTN) unlocks career accelerating opportunities and introductions for underrepresented healthcare leaders and entrepreneurs.";

	public void userInfoDataValidation(String Username, String sponsorName, String design, int TotalCounts) {

		gm.verifyElementText(welcomeVideo.sponsorIntro, "Hello " + Username + ",\n" + "\n" + sponsorName + ",\n"
				+ design + "\n" + "sponsored you for the Humanity Talent Network.", "sponsorIntro");

		gm.verifyEqual(
				gm.getSizeofWebelements(welcomeVideo.sponsorScrollUserName("position()"), "sponsorScrollUserName"),
				TotalCounts, "sponsorScrollUserName");
		gm.verifyEqual(gm.getSizeofWebelements(welcomeVideo.sponsorScrollcompany("position()"), "sponsorScrollcompany"),
				TotalCounts, "sponsorScrollcompany");

	}

	public void usersInfoDataNonSponsoredValidaton() {
		gm.verifyElementAttributeValue(welcomeVideo.htnLogo, "src", "https://video-dev.humanitytalent.net/Logo.png",
				"HTN Logo");
		gm.verifyElementText(welcomeVideo.errordesc,
				"You have not been sponsored yet, Please check the invite shared on your email address.", "errordesc");

		gm.verifyElementCSSValue(By.xpath("//body"), "background-color", "rgba(224, 224, 224, 1)", "Body background");
		gm.verifyElementCSSValue(welcomeVideo.errordesc, "color", "rgba(33, 33, 35, 1)", "errordesc");

		gm.verifyElementNotPresent(welcomeVideo.contentSection, 1, "contentSection");
		gm.verifyElementNotPresent(welcomeVideo.videoSection, 1, "videoSection");
		gm.verifyElementNotPresent(welcomeVideo.sponsorIntro, 1, "sponsorIntro");
		gm.verifyElementNotPresent(welcomeVideo.sponsorScrollcompany(1), 1, "Sponsors Scroll profile");
		gm.verifyElementNotPresent(welcomeVideo.qrcodeImage, 1, "qrcodeImage");
	}

	public void scrollSection(int order, String sponsorName, String design, String position) {

		gm.verifyElementText(welcomeVideo.sponsorScrollUserName(order), sponsorName, "sponsorScrollUserName");
		gm.verifyElementText(welcomeVideo.sponsorScrollcompany(order), design + " at " + position,
				"sponsorScrollcompany");

	}

	@BeforeTest
	public void superAdminUsersLoginsToTheApplication() {
		userLoginToTheWebApplication(EnvironmentDetails.SuperAdminUserName, EnvironmentDetails.SuperAdminPassword);
	}

	public void navigateToUserPage() {

		gm.navigateToPage(EnvironmentDetails.htnURL + "/dashboard/users");
		gm.hold(5);
	}

	public void navigateToVideoPage_desktopView(String emailAddress) {

		String url = EnvironmentDetails.htnURL + "/desktopView.html?email=" + emailAddress;
		gm.navigateToPage(url);
		gm.hold(5);
	}

	public void navigateToVideoPage_mobileView(String emailAddress) {

		gm.navigateToPage(EnvironmentDetails.htnURL + "/mobileView.html?email=" + emailAddress);
		gm.hold(5);
	}

	public String sponsorAUser(String name, String emailAddress, String compname) {
		int order = 1;

		gm.click(us.addNewUser_button, "addNewUser_button");
		gm.waitforElementVisible(userModal.confirmButton, 5, "confirmButton");

		gm.setText(userModal.nameInput, name, "nameInput");
		gm.setText(userModal.emailInput, emailAddress, "emailInput");

		gm.setText(userModal.companyInput, compname, "companyInput");
		gm.hold(5);
		gm.click(userModal.companySearchResult(order), "companySearchResult(" + order + ")");
		gm.hold(2);
		gm.click(userModal.confirmButton, "confirmButton");
		validateModalNotificationMessage("Success", "User invited successfully");
		return emailAddress;
	}

	@Test(priority = 0, enabled = true)
	public void generalValidationOfUserPage() {

		gm.StartTest("By default After login User should be landed in Users page", "");
		validateWebpageTabIconAndTitle();
		gm.verifyPageURL(EnvironmentDetails.htnURL + "/dashboard/users");
		gm.EndTest();

	}

	@Test(priority = 1, enabled = true)
	public void validateTheUIofwelcomeVideoWebPage() {

		gm.StartTest("Validate the UI and data of the Welcome Video Page Desktop View", "");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		String sponsorName = "Ankit 1 Seniaray";
		String design = "Health Advisory at Ank";
		navigateToUserPage();
		sponsorAUser(name, emailAddress, "Google");

		/*
		 * Desktop View
		 */
		navigateToVideoPage_desktopView(emailAddress);
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");

		gm.verifyPageTitle("Humanity Health");

		gm.verifyElementAttributeValue(welcomeVideo.htnLogo, "src", "https://video-dev.humanitytalent.net/Logo.png",
				"HTN Logo");

		gm.verifyElementText(welcomeVideo.sponsorIntro, "Hello " + name + ",\n" + "\n" + sponsorName + ",\n" + design
				+ "\n" + "sponsored you for the Humanity Talent Network.", "sponsorIntro");
		gm.verifyElementText(welcomeVideo.sponsorMessage, sponsorSubText, "sponsorMessage");
		gm.verifyElementText(welcomeVideo.description, message, "description");

		gm.verifyElementText(welcomeVideo.qrHeader, "Download the app now!", "qrHeader");
		gm.verifyElementAttributeValue(welcomeVideo.qrcodeImage, "src",
				"https://video-dev.humanitytalent.net/QR-Code.png", "qrcodeImage");
		gm.verifyElementText(welcomeVideo.qrTitle, "Available on:", "qrTitle");
		gm.verifyElementAttributeValue(welcomeVideo.iOSImage, "src", "https://video-dev.humanitytalent.net/Apple.png",
				"iOSImage");
		gm.verifyElementText(welcomeVideo.iOSImage, "iOS", "iOSImage");

		gm.verifyElementAttributeValue(welcomeVideo.androidImage, "src",
				"https://video-dev.humanitytalent.net/Bugdroid.png", "androidImage");
		gm.verifyElementText(welcomeVideo.androidImage, "Android", "androidImage");

		gm.verifyElementCSSValue(welcomeVideo.contentSection, "width", "648px", "contentSection");
		gm.verifyElementCSSValue(welcomeVideo.contentSection, "height", "612px", "contentSection");
		gm.verifyElementCSSValue(welcomeVideo.videoSection, "width", "648px", "videoSection");
		gm.verifyElementCSSValue(welcomeVideo.videoSection, "height", "612px", "videoSection");

		gm.verifyElementNotPresent(welcomeVideo.joinNow_button, 1, "joinNow_button");
		gm.EndTest();

		/*
		 * Mobile View
		 */
		gm.StartTest("Validate the UI and data of the Welcome Video Page Mobile View", "");
		navigateToVideoPage_mobileView(emailAddress);
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");

		gm.verifyPageTitle("Humanity Health");

		gm.verifyElementAttributeValue(welcomeVideo.htnLogo, "src", "https://video-dev.humanitytalent.net/Logo.png",
				"HTN Logo");
		gm.verifyElementText(welcomeVideo.sponsorIntro, "Hello " + name + ",\n" + "\n" + sponsorName + ",\n" + design
				+ "\n" + "sponsored you for the Humanity Talent Network.", "sponsorIntro");
		gm.verifyElementText(welcomeVideo.sponsorMessage, sponsorSubText, "sponsorMessage");
		gm.verifyElementText(welcomeVideo.description, message, "description");

		gm.verifyElementVisible(welcomeVideo.joinNow_button, "joinNow_button");
		gm.verifyElementClickable(welcomeVideo.joinNow_button, "joinNow_button");

		gm.verifyElementNotPresent(welcomeVideo.qrHeader, 1, "qrHeader");
		gm.verifyElementNotPresent(welcomeVideo.qrcodeImage, 1, "qrcodeImage");
		gm.verifyElementNotPresent(welcomeVideo.qrTitle, 1, "qrTitle");
		gm.verifyElementNotPresent(welcomeVideo.iOSImage, 1, "iOSImage");
		gm.verifyElementNotPresent(welcomeVideo.androidImage, 1, "androidImage");

		gm.EndTest();

	}

	@Test(priority = 2, enabled = true)
	public void EndorsementWelcomeVideoValidation() {
		gm.StartTest("Validate the UI and data of the Welcome Video Page for Single(Endorsement) - Invite", "");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		navigateToUserPage();
		sponsorAUser(name, emailAddress, "Google");

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		gm.EndTest();
	}

	@Test(priority = 3, enabled = true)
	public void EndorsementWelcomeVideo_1() {

		gm.StartTest("Validate the UI and data of the Welcome Video Page for Multiple(Endorsement) - Invite", "");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		navigateToUserPage();
		sponsorAUser(name + "_1", emailAddress, "Google");
		sponsorAUser(name + "_2", emailAddress, "Google");

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		gm.EndTest();
	}

	@Test(priority = 4, enabled = true)
	public void nonInvitedemail() {
		gm.StartTest("Validate the UI and data of the Welcome Video Page for NON invited email Desktop View", "");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";

		navigateToVideoPage_desktopView(emailAddress);
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");
		gm.verifyPageTitle("Humanity Health");

		usersInfoDataNonSponsoredValidaton();
		gm.EndTest();

		gm.StartTest("Validate the UI and data of the Welcome Video Page for NON invited email Mobile View", "");

		navigateToVideoPage_mobileView(emailAddress);
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");
		gm.verifyPageTitle("Humanity Health");

		usersInfoDataNonSponsoredValidaton();

		gm.EndTest();
	}

	@Test(priority = 4, enabled = true)
	public void removingMandatoryParameterinInviteEmail() {

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";

		gm.StartTest("Validating the UI of the Welcome Video page when Mandatory URL field - View is removed", "");
		navigateToUserPage();
		sponsorAUser(name, emailAddress, "Google");

		gm.navigateToPage("https://video-dev.humanitytalent.net/?email=" + emailAddress);
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");
		gm.verifyPageTitle("Humanity Health");
		usersInfoDataNonSponsoredValidaton();
		gm.EndTest();

		gm.StartTest("Validating the UI of the Welcome Video page when Mandatory URL field - Email is removed", "");
		navigateToUserPage();
		sponsorAUser(name, emailAddress, "Google");

		gm.navigateToPage("https://video-dev.humanitytalent.net/desktopView.html");
		gm.verifyElementAttributeValue_NotDisplayed(go.TabIcon, "href",
				"https://admin-dev.humanitytalent.net/favicon.ico", "TabIcon");
		gm.verifyPageTitle("Humanity Health");
		usersInfoDataNonSponsoredValidaton();

		gm.EndTest();

	}

	@Test(priority = 5, enabled = true)
	public void invitedEmailWithAdditionalParameter() {
		gm.StartTest(
				"Validate the UI and data of the Welcome Video Page for invited email with Additional URL Parameters",
				"");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		navigateToUserPage();
		sponsorAUser(name, emailAddress, "Google");

		/*
		 * Test 1
		 */
		navigateToVideoPage_desktopView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		/*
		 * Test 2
		 */

		navigateToVideoPage_desktopView(
				emailAddress + "?sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		/*
		 * Test 3
		 */

		navigateToVideoPage_desktopView(
				emailAddress + "?sectorExpertise.id=3456345?jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		gm.EndTest();
	}

	@Test(priority = 6, enabled = true)
	public void NoninvitedEmailWithAdditionalParameter() {
		gm.StartTest(
				"Validate the UI and data of the Welcome Video Page for NON invited email with Additional URL Parameters",
				"");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";

		/*
		 * Test 1
		 */
		navigateToVideoPage_desktopView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		navigateToVideoPage_mobileView(
				emailAddress + "&sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		/*
		 * Test 2
		 */

		navigateToVideoPage_desktopView(
				emailAddress + "?sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		navigateToVideoPage_mobileView(
				emailAddress + "?sectorExpertise.id=3456345&jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		/*
		 * Test 3
		 */
		navigateToVideoPage_desktopView(
				emailAddress + "?sectorExpertise.id=3456345?jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		navigateToVideoPage_mobileView(
				emailAddress + "?sectorExpertise.id=3456345?jobFunctionExpertise.id=e8957349753845");
		usersInfoDataNonSponsoredValidaton();

		gm.EndTest();
	}

	@Test(priority = 7, enabled = true)
	public void inviteAndResendInviteSingleEndorsement() {
		gm.StartTest(
				"Validate the UI and data of the Welcome Video Page for Single(Endorsement) - Invited and Resends Invite",
				"");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		String compname = "Google";

		navigateToUserPage();
		sponsorAUser(name, emailAddress, compname);

		gm.click(us.invited_Button, "invited_Button");
		gm.hold(2);
		gm.click(us.resendInvite_Button(emailAddress, 1), "resendInvite_Button");
		validateModalNotificationMessage("Success", "Invite resent successfully.");

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		gm.EndTest();
	}

	@Test(priority = 8, enabled = true)
	public void inviteAndResendInviteMultipleEndorsement() {
		gm.StartTest(
				"Validate the UI and data of the Welcome Video Page for Multiple(Endorsement) - Invited and Resends Invite",
				"");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";

		navigateToUserPage();
		sponsorAUser(name + "_1", emailAddress, "Google");
		sponsorAUser(name + "_2", emailAddress, "Google");

		gm.click(us.invited_Button, "invited_Button");
		gm.hold(2);
		gm.click(us.resendInvite_Button(emailAddress, 1), "resendInvite_Button");
		validateModalNotificationMessage("Success", "Invite resent successfully.");

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToUserPage();
		gm.click(us.invited_Button, "invited_Button");
		gm.hold(2);
		gm.click(us.resendInvite_Button(emailAddress, 2), "resendInvite_Button");
		validateModalNotificationMessage("Success", "Invite resent successfully.");

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name + "_2", "Ankit 1 Seniaray & Ankit 1 Seniaray",
				"Health Advisory at Ank & Health Advisory at Ank", 2);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");
		scrollSection(2, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		gm.EndTest();

	}

	@Test(priority = 9, enabled = true)
	public void invitedEmailTemplate() {

		gm.StartTest("Validate if User Has Received the Invited email", "");
		gm.EndTest();

		gm.StartTest("Validate if User has received only one Invited email", "");
		gm.EndTest();

		gm.StartTest("Validate UI and data in the Invited email received", "");
		gm.EndTest();

		gm.StartTest("Validate if User has received the Invite Reminder email - Resend Invite", "");
		gm.EndTest();

		gm.StartTest("Validate if User has received only one Invite Reminder email - Resend Invite", "");
		gm.EndTest();

	}

	@Test(priority = 10, enabled = true)
	public void test1() {

		gm.StartTest("Validate the Columns of Invited Table", "");

		navigateToUserPage();
		gm.click(us.invited_Button, "invited_Button");
		gm.hold(2);
		gm.verifyListofElementText(us.invitedHeaders_text,
				Arrays.asList("NAME & EMAIL", "TITLE & ORGANISATION", "ENDORSED BY", "INVITED ON", "STATUS").toArray(),
				"invitedHeaders_text");
		gm.verifyElementCSSValue(us.invitedHeaders_text, "color", "rgba(0, 0, 0, 0.87)", "invitedHeaders_text");

		gm.EndTest();
	}

	public void inviteAndValidateTheWelcomeVideoAndInviteList(String LoginemailAddress, String Loginpassword) {
		launchNewBrowser();
		userLoginToTheWebApplication(LoginemailAddress, Loginpassword);

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String date = gm.getCurrentTime("dd MMM, yyyy", "GMT");
		String emailAddress = name + "@grappus.com";
		String company = "Google";
		navigateToUserPage();
		sponsorAUser(name, emailAddress, company);

		navigateToVideoPage_desktopView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToVideoPage_mobileView(emailAddress);
		userInfoDataValidation(name, "Ankit 1 Seniaray", "Health Advisory at Ank", 1);
		scrollSection(1, "Ankit 1 Seniaray", "Health Advisory", "Ank");

		navigateToUserPage();
		gm.click(us.invited_Button, "invited_Button");
		gm.verifyElementText(us.invitedName_Table(1), name, "invitedName_Table");
		gm.verifyElementText(us.invitedEmailAddress_Table(1), emailAddress, "invitedEmailAddress_Table");
		gm.verifyElementText(us.invitedTitle_Table(1), "N/A", "invitedTitle_Table");
		gm.verifyElementText(us.invitedOrganization_Table(1), company, "invitedOrganization_Table");
		gm.verifyElementText(us.invitedEndorsedBy_Table(1), "ankit@grappus.com", "invitedEndorsedBy_Table");
		gm.verifyElementText(us.invitedInvitedOn_Table(1), date, "invitedInvitedOn_Table");

	}

	@Test(priority = 11, enabled = true)
	public void test22() {
		gm.StartTest("SuperAdmin should be able to Invite a User and see the resepective data in Invited table list",
				"");

		inviteAndValidateTheWelcomeVideoAndInviteList(EnvironmentDetails.SuperAdminUserName,
				EnvironmentDetails.SuperAdminPassword);
		gm.EndTest();
	}

	@Test(priority = 12, enabled = true)
	public void test23() {
		gm.StartTest("Admin should be able to Invite a User and see the resepective data in Invited table list", "");
		inviteAndValidateTheWelcomeVideoAndInviteList(EnvironmentDetails.AdminUserName,
				EnvironmentDetails.AdminPassword);
		gm.EndTest();

	}

	@Test(priority = 13, enabled = true)
	public void test2() {
		gm.StartTest("Invited Users count should be Incremented", "");

		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String emailAddress = name + "@grappus.com";
		String company = "Google";

		launchNewBrowser();
		userLoginToTheWebApplication(EnvironmentDetails.SuperAdminUserName, EnvironmentDetails.SuperAdminPassword);

		int memebrsCount_Before = Integer.valueOf(gm.getText(us.membersCount, "membersCount"));
		int invitedUsersCount_Before = Integer.valueOf(gm.getText(us.invitedUsersCount, "invitedUsersCount"));

		float avgDaysToRegisterCount_Before = Float
				.valueOf(gm.getText(us.avgDaysToRegisterCount, "avgDaysToRegisterCount"));

		sponsorAUser(name, emailAddress, company);

		int memebrsCount_After = Integer.valueOf(gm.getText(us.membersCount, "membersCount"));
		int invitedUsersCount_After = Integer.valueOf(gm.getText(us.invitedUsersCount, "invitedUsersCount"));
		int registeredCount_After = Integer
				.valueOf(gm.getText(us.registeredCount, "registeredCount").replaceAll("%", ""));
		float avgDaysToRegisterCount_After = Float
				.valueOf(gm.getText(us.avgDaysToRegisterCount, "avgDaysToRegisterCount"));

		gm.verifyEqual(memebrsCount_Before, memebrsCount_After, "memebrsCount");
		gm.verifyEqual(invitedUsersCount_Before + 1, invitedUsersCount_After, "invitedUsersCount");
		gm.verifyEqual(getInvitedPercentage(memebrsCount_After, invitedUsersCount_After), registeredCount_After,
				"registeredCount");
		gm.verifyEqual(avgDaysToRegisterCount_Before, avgDaysToRegisterCount_After, "avgDaysToRegisterCount");

		gm.EndTest();

	}

	@Test
	public void test3() {
		gm.StartTest("HTN Admin should not invite the same User again and again", "");
		gm.EndTest();

		gm.StartTest("Different HTN Admins should be able to Invite the same Users again and again", "");
		gm.EndTest();

		gm.StartTest("HTN Admin should not invite the User who has completed the Onboarding", "");
		gm.EndTest();
	}

	@Test(priority = 14, enabled = true)
	public void test5() {
		gm.StartTest("Invited User should be able to Signup in App Successfully", "");
		String name = gm.getCurrentTime("ddMMyyyyhhmmss", "GMT");
		String date = gm.getCurrentTime("dd MMM, yyyy", "GMT");
		String emailAddress = name + "@grappus.com";
		navigateToUserPage();

		int memebrsCount_Before = Integer.valueOf(gm.getText(us.membersCount, "membersCount"));
		int invitedUsersCount_Before = Integer.valueOf(gm.getText(us.invitedUsersCount, "invitedUsersCount"));
		int registeredCount_Before = Integer
				.valueOf(gm.getText(us.registeredCount, "registeredCount").replaceAll("%", ""));

		sponsorAUser(name, emailAddress, "Google");

		launchApplication();
		signUpWithEmail(emailAddress);
		gm.EndTest();

		/*
		 * Test 1
		 */
		gm.StartTest("Once Invited User is Onboared, The Users should be visible in the Joined list", "");

		navigateToUserPage();
		gm.click(us.joined_button, "joined_button");
		gm.hold(2);

		gm.verifyElementText(us.joinedName_Table(1), "N/A", "joinedName_Table");
		gm.verifyElementText(us.joinedEmailAddress_Table(1), emailAddress, "joinedEmailAddress_Table");
		gm.verifyElementText(us.joinedTitle_Table(1), "N/A", "joinedTitle_Table");
		gm.verifyElementText_VisiblityDisabled(us.joinedOrganization_Table(1), "", "joinedOrganization_Table");
		gm.verifyElementText(us.joinedEndorsedBy_Table(1), "ankit@grappus.com", "joinedEndorsedBy_Table");
		gm.verifyElementText(us.joinedInvitedOn(1), date, "joinedInvitedOn");
		gm.verifyElementText(us.joinedJoinedOn(1), date, "joinedJoinedOn");

		gm.EndTest();

		/*
		 * Test 2
		 */
		gm.StartTest("Once Invited User is Onboared, The Users should be removed from the Invited list", "");
		navigateToUserPage();
		gm.click(us.invited_Button, "invited_Button");
		gm.hold(2);
		gm.verifyElementTextNOTEquals(us.invitedName_Table(1), name, "invitedName_Table");
		gm.verifyElementTextNOTEquals(us.invitedEmailAddress_Table(1), emailAddress, "invitedEmailAddress_Table");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Once Invited User is Onboared, The Invited Users count should not reduce", "");
		navigateToUserPage();

		int invitedUsersCount_After = Integer.valueOf(gm.getText(us.invitedUsersCount, "invitedUsersCount"));

		gm.verifyEqual(invitedUsersCount_Before + 1, invitedUsersCount_After, "invitedUsersCount");

		gm.EndTest();

		/*
		 * Test 4
		 */
		gm.StartTest("Once Invited User is Onboared, The members count should increase", "");
		navigateToUserPage();

		int memebrsCount_After = Integer.valueOf(gm.getText(us.membersCount, "membersCount"));
		invitedUsersCount_After = Integer.valueOf(gm.getText(us.invitedUsersCount, "invitedUsersCount"));
		int registeredCount_After = Integer
				.valueOf(gm.getText(us.registeredCount, "registeredCount").replaceAll("%", ""));

		gm.verifyEqual(memebrsCount_Before + 1, memebrsCount_After, "memebrsCount");
		gm.verifyEqual(invitedUsersCount_Before + 1, invitedUsersCount_After, "invitedUsersCount");
		gm.verifyEqual(registeredCount_Before, registeredCount_After, "registeredCount");

		gm.EndTest();
	}

	@Test
	public void test6() {
		gm.StartTest("Search with Name", "");
		gm.EndTest();

		gm.StartTest("Serch with User email", "");
		gm.EndTest();

		gm.StartTest("Search with Endorsed by", "");
		gm.EndTest();

	}

}
