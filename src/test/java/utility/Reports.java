package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	ExtentReports extent;
	ExtentTest extentTest;
	String extentreportfilename;
	String filepath;

	Reports(String fp, String extentreportfilename) {
		this.filepath = fp;
		this.extentreportfilename = extentreportfilename;

	}

	public void StartReports(boolean override) {
		extent = new ExtentReports(filepath + extentreportfilename + ".html", override);
		// extentTest = extent.startTest("Test Execution Results","Verify QA Pairs");
	}

	public void StartTest(String Testid, String TestName) {
		extentTest = extent.startTest(Testid, TestName);
	}

	public void EndTest() {
		extent.endTest(extentTest);
		extent.flush();
	}

	public void LogInfo(String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.INFO, info);
		} catch (NullPointerException e) {

		}
	}

	public void LogInfo(WebDriver driver, String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(capture(driver)) + info);
		} catch (NullPointerException e) {

		}
	}

	public void LogPass(WebDriver driver, String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.PASS, info);
		} catch (NullPointerException e) {

		}
	}

	public void LogSKIP(WebDriver driver, String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(capture(driver)) + info);
		} catch (NullPointerException e) {

		}
	}

	public void LogPass(String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.PASS, info);
		} catch (NullPointerException e) {

		}
	}

	public void LogFail(WebDriver driver, String info) {
		info = info.replace("\n", "<br/>");
		try {
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(capture(driver)) + info);
		} catch (java.lang.NullPointerException e) {
			System.out.println("<-----------Failed while capturign the Screenshort-------->");
			System.out.println(e);
			LogFail(info);
		}
	}

	public void LogFail(String info) {
		try {
			info = info.replace("\n", "<br/>");
			extentTest.log(LogStatus.FAIL, info);
		} catch (NullPointerException e) {

		}
	}

	public void LogWarning(WebDriver driver, String info) {
		try {
			info = info.replace("\n", "<br/>");

			extentTest.log(LogStatus.WARNING, extentTest.addScreenCapture(capture(driver)) + info);
		} catch (NullPointerException e) {

		}
	}

	public String capture(WebDriver driver) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File Dest = new File(filepath + System.currentTimeMillis() + ".png");
			String errflpath = ".\\" + Dest.getName();
			FileUtils.copyFile(scrFile, Dest);
			return errflpath;
		} catch (TimeoutException e) {
			return "";
		} catch (UnsupportedOperationException e) {
			return "";

		} catch (WebDriverException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}

	public void EndReports() {
		extent.endTest(extentTest);
		extent.flush();
		extent.close();
	}
}
