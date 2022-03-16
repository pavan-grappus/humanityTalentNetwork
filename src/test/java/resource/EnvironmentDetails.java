package resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import utility.ExcelMethods;

public class EnvironmentDetails {

	public static String htnURL = "https://admin-dev.humanitytalent.net/";
	
	public static String SuperAdminUserName = "admin_ankit_dev@grappus.com";
	
	public static String SuperAdminPassword = "Test@123";

	public static String projectDirectory = System.getProperty("user.dir");

	public static String uploadFilesRootPath = System.getProperty("user.dir") + "\\" + "UploadFiles" + "\\";

	@DataProvider(name = "validEmailAddress")
	public Iterator<String> validEmailAddress() {

		List<String> array = new ArrayList<String>();
		ExcelMethods inputdata;
		try {
			inputdata = new ExcelMethods(projectDirectory, "\\HH Test cases.xlsx");
			inputdata.excelinputstreamopen();

			array = inputdata.getDatafromColumn("EmailIdsValidation", 0, true);

			inputdata.excelinputstreamclose();

			return array.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	@DataProvider(name = "InvalidEmailAddress")
	public Iterator<String> invalidEmailAddress() {

		List<String> array = new ArrayList<String>();
		ExcelMethods inputdata;
		try {
			inputdata = new ExcelMethods(projectDirectory, "\\HH Test cases.xlsx");
			inputdata.excelinputstreamopen();

			array = inputdata.getDatafromColumn("EmailIdsValidation", 1, true);

			inputdata.excelinputstreamclose();

			return array.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	@DataProvider(name = "validSearchResultCompanynames")
	public Iterator<String> validCompanyNames() {

		List<String> array = new ArrayList<String>();
		ExcelMethods inputdata;
		try {
			inputdata = new ExcelMethods(projectDirectory, "\\HH Test cases.xlsx");
			inputdata.excelinputstreamopen();

			array = inputdata.getDatafromColumn("CompanyNames", 0, true);

			inputdata.excelinputstreamclose();

			return array.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
