package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		extentTest.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Log the failure in the report
		try {
			if (extentTest.get() != null) {
				extentTest.get().fail(result.getThrowable());
			}
		} catch (Exception e) {
			System.err.println(
					"Failed to log failure in the extent report for test: " + result.getMethod().getMethodName());
			e.printStackTrace();
		}

		// Attempt to retrieve the WebDriver instance
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			if (driver == null) {
				System.err.println("Driver is null for test: " + result.getMethod().getMethodName());
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.err.println("Error retrieving driver for test: " + result.getMethod().getMethodName());
			e.printStackTrace();
		}

		// Capture and attach the screenshot
		String filePath = null;
		try {
			if (driver != null) {
				filePath = getScreenShot(result.getMethod().getMethodName(), driver);
			} else {
				System.err.println("Cannot capture screenshot because driver is null for test: "
						+ result.getMethod().getMethodName());
			}
		} catch (IOException e) {
			System.err.println("Failed to capture screenshot for test: " + result.getMethod().getMethodName());
			e.printStackTrace();
		}

		// Add screenshot to the report
		try {
			if (filePath != null && extentTest.get() != null) {
				extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			}
		} catch (Exception e) {
			System.err.println(
					"Failed to attach screenshot to the report for test: " + result.getMethod().getMethodName());
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext context) {
		// Initialization or logging
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
