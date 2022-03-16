package runnerFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ExcelMethods;

public class UserListingFilterSection extends BeforeRun {

	public void refreshUserPage() {
		gm.refresh("User Listing Page", 20);
		gm.waitforElementVisible(us.JoinedORNewUsersList, 20, "JoinedORNewUsersList");

	}

	public void openFilterSection() {
		gm.click(us.filters_Button, "Filters_Button");
		gm.hold(5);
		gm.waitforElementVisible(us.Apply_Button, 5, "Apply_Button");

	}

	public void checkFilterCheckboxOptionsandApplyFilter(List<String> SectorandFunctionCheckboxValues,
			boolean uncheckfilters) {
		gm.clickifPresent(us.sectorReadMore_button, "sectorReadMore");
		gm.clickifPresent(us.functionReadMore_button, "functionReadMore");

		if (uncheckfilters) {
			int countofChecked = gm.getSizeofWebelements(us.CheckedCheckboxes_List, "CheckedCheckboxes_List");

			for (int i = 0; i < countofChecked; i++) {
				gm.Uncheck(us.CheckedCheckboxes_List, "Unchecked Option :" + i);
			}
		}
		for (String checkBoxValue : SectorandFunctionCheckboxValues) {
			gm.check(us.FilterscheckBox_input(checkBoxValue), "Checkbox : " + checkBoxValue);
		}

		clicksOnApplybutton();
	}

	public void clicksOnApplybutton() {
		gm.click(us.Apply_Button, "Apply_Button");
		gm.waitforElementVisible(us.JoinedORNewUsersList, 20, "JoinedORNewUsersList");
	}

	@Test(priority = 1, enabled = true, dataProvider = "UserApplyingFiltersandValidatingEmailIds_DP")
	public void UserRefreshthenApplyFiltersandValidatesEmailIds(List<String> SectorandFunctionCheckboxValues,
			List<String> ExpectedEmailIds, List<String> UNExpectedEmailIds) {

		gm.StartTest("Filter :" + SectorandFunctionCheckboxValues, "");

		refreshUserPage();

		openFilterSection();
		checkFilterCheckboxOptionsandApplyFilter(SectorandFunctionCheckboxValues, false);

		gm.verifyTextPresentinListofElements(us.emailID_JoinedList_Text, ExpectedEmailIds, "emailID_JoinedList_Text");
		gm.verifyTextNOTPresentinListofElements(us.emailID_JoinedList_Text, UNExpectedEmailIds,
				"emailID_JoinedList_Text");
		gm.EndTest();
	}

	@Test(priority = 2, enabled = false, dataProvider = "UserApplyingFiltersandValidatingEmailIds_DP")
	public void UserClearAllthenApplyFilterandValidatesEmailIds(List<String> SectorandFunctionCheckboxValues,
			List<String> ExpectedEmailIds, List<String> UNExpectedEmailIds) {

		gm.StartTest("Filter :" + SectorandFunctionCheckboxValues, "");

		openFilterSection();

		gm.click(us.ClearAll_Button, "ClearAll_Button");
		gm.waitforElementVisible(us.JoinedORNewUsersList, 20, "JoinedORNewUsersList");

		openFilterSection();
		checkFilterCheckboxOptionsandApplyFilter(SectorandFunctionCheckboxValues, false);

		gm.verifyTextPresentinListofElements(us.emailID_JoinedList_Text, ExpectedEmailIds, "emailID_JoinedList_Text");
		gm.verifyTextNOTPresentinListofElements(us.emailID_JoinedList_Text, UNExpectedEmailIds,
				"emailID_JoinedList_Text");
		gm.EndTest();

	}

	@Test(priority = 3, enabled = false, dataProvider = "UserApplyingFiltersandValidatingEmailIds_DP")
	public void UserUnchecksthenApplyFilterandValidatesEmailIds(List<String> SectorandFunctionCheckboxValues,
			List<String> ExpectedEmailIds, List<String> UNExpectedEmailIds) {

		gm.StartTest("Filter :" + SectorandFunctionCheckboxValues, "");

		openFilterSection();
		checkFilterCheckboxOptionsandApplyFilter(SectorandFunctionCheckboxValues, true);

		gm.verifyTextPresentinListofElements(us.emailID_JoinedList_Text, ExpectedEmailIds, "emailID_JoinedList_Text");
		gm.verifyTextNOTPresentinListofElements(us.emailID_JoinedList_Text, UNExpectedEmailIds,
				"emailID_JoinedList_Text");
		gm.EndTest();

	}

	@Test(priority = 4, enabled = false, dataProvider = "UserApplyingFiltersandValidatingEmailIds_DP")
	public void UserReapplystheExistingAppliedFiltersandValidatesEmailIds(List<String> SectorandFunctionCheckboxValues,
			List<String> ExpectedEmailIds, List<String> UNExpectedEmailIds) {

			
		gm.StartTest("Filter :" + SectorandFunctionCheckboxValues, "");

		refreshUserPage();

		openFilterSection();
		checkFilterCheckboxOptionsandApplyFilter(SectorandFunctionCheckboxValues, false);

		openFilterSection();
		clicksOnApplybutton();

		gm.verifyTextPresentinListofElements(us.emailID_JoinedList_Text, ExpectedEmailIds, "emailID_JoinedList_Text");
		gm.verifyTextNOTPresentinListofElements(us.emailID_JoinedList_Text, UNExpectedEmailIds,
				"emailID_JoinedList_Text");
		gm.EndTest();

	}

	@DataProvider(name = "UserApplyingFiltersandValidatingEmailIds_DP")
	public Iterator<Object[]> UserApplyingFiltersandValidatingEmailIds_DP() throws IOException {
		List<Object[]> array = new ArrayList<Object[]>();

		ExcelMethods inputdata = new ExcelMethods("E:\\eclipseWorkSpace\\HumanityHealth\\", "HH Test cases.xlsx");

		inputdata.excelinputstreamopen();

		List<String> checkboxeValues = inputdata.getDatafromColumn("Test1", 0, true);
		List<String> expectedEmailIDS = inputdata.getDatafromColumn("Test1", 1, true);
		List<String> UnexpectedEmailIDS = inputdata.getDatafromColumn("Test1", 2, true);

		for (int i = 0; i < checkboxeValues.size(); i++) {

			List<String> expectedEmailIDSArray = new ArrayList<String>();

			List<String> UnexpectedEmailIDSArray = new ArrayList<String>();

			if (!expectedEmailIDS.get(i).contentEquals("")) {
				expectedEmailIDSArray = Arrays.asList(expectedEmailIDS.get(i).split(", "));
			} else {
				expectedEmailIDSArray = Arrays.asList();
			}

			if (!UnexpectedEmailIDS.get(i).contentEquals("")) {
				UnexpectedEmailIDSArray = Arrays.asList(UnexpectedEmailIDS.get(i).split(", "));
			} else {
				UnexpectedEmailIDSArray = Arrays.asList();
			}

			array.add(new Object[] { Arrays.asList(checkboxeValues.get(i).split(", ")), expectedEmailIDSArray,
					UnexpectedEmailIDSArray });
		}

		inputdata.excelinputstreamclose();

		return array.iterator();
	}
	
	

}
