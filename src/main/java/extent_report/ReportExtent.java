package extent_report;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import screenshot.Photo;

public class ReportExtent {

	static ExtentReports extent = new ExtentReports("Reports\\extentReport\\ExtentReport.html", true);
	static ExtentTest test;

	public static void generateReport(WebDriver driver, String s, String name, String status) throws Exception {
		test = extent.startTest(s);
		if (status.equals("PASS")) {
			if (s.equals("closeBrowser")) {
				test.log(LogStatus.PASS, "TEST CASE PASSED");
				extent.flush();
				extent.endTest(test);
			} else {
				String screenshotPath = Photo.getScreenhot(driver, name);
				test.log(LogStatus.PASS, "TEST CASE PASSED", test.addScreenCapture(screenshotPath));
				extent.flush();
				extent.endTest(test);
			}

		} else {
			test.log(LogStatus.FAIL, "TEST CASE FAILED");
			extent.flush();
			extent.endTest(test);
		}
	}

}
