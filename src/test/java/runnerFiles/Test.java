package runnerFiles;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test {

	public static void main(String[] args) {

		ExtentReports ex = new ExtentReports("E:\\eclipseWorkSpace\\testJava\\target\\Spark\\spark.html", true);

		ExtentTest test = ex.startTest("Test1", "");
		test.log(LogStatus.PASS, "pass");

		ExtentTest testchild = ex.startTest("TestChild1");
		testchild.log(LogStatus.FAIL, "Fail");

		ExtentTest testchild2 = ex.startTest("TestChild2");
		testchild2.log(LogStatus.WARNING, "WARNING");

		ExtentTest testchild3 = ex.startTest("TestChild3");
		testchild3.log(LogStatus.FATAL, "FATAL");

		
		test.appendChild(testchild);
		test.appendChild(testchild2);
		test.appendChild(testchild3);

		ex.flush();

	}

}
