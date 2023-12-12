package DocQT.DocquityMobile.Utility;

/**
 * 
 *@author yogesh.shinde
 *
 */

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class Log {

	public static ExtentReports extent;
	public static ExtentTest extentLogger; // helps to generate logs in extent test report
	public static ExtentHtmlReporter reporter;
	private static final Logger LOGGER = LogManager.getLogger(Log.class);

	public static void info(String message) {
		String LogMessege = TestUtils.addSpaceBeforeUppercase(message);
		LOGGER.info(LogMessege);
		extentLogger.log(Status.INFO, LogMessege);
	}

	public static void warn(String message) {
		LOGGER.warn(message);
		extentLogger.log(Status.WARNING, message);
	}

	public static void error(String message) {
		LOGGER.error(message);
		extentLogger.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	public static void fatal(String message) {
		LOGGER.fatal(message);
		extentLogger.log(Status.FATAL, message);
	}

	public static void debug(String message) {
		LOGGER.debug(message);
		extentLogger.log(Status.DEBUG, message);
	}

	public static void initialiseExtentReport(Logger LOGGER) {
		LOGGER.debug("===========================      Configuring Extent Report      =============================");
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//Reports//ExtentReport.html");
		reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setDocumentTitle("Extent Report for Docquity Mobile");
		reporter.config().setReportName("Test Report");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(Log.reporter);
		extent.setSystemInfo("Project Name", "DocquityMobile Test");
		extent.setSystemInfo("Platform", "Android");
		extent.setSystemInfo("Test Environment", "Staging");
		extent.setSystemInfo("Test Suite", "Regression Tests");
		extent.setSystemInfo("Device", "Pixel 3");
		extent.setSystemInfo("Test Engineer", "Roshan , Yogesh");

	}

	public static void flushExtent(Logger LOGGER) {
		LOGGER.debug("<br>**************************         lounching app       **********************************");
		LOGGER.info("=============================      Flushing Extent Report      ===============================");
		extent.flush();
	}

	public static void afterMethodLogResult(Method method, ITestResult result, WebDriver driver) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			// code to capture screenshot after test step failed
			String temp = takeScreenshot(method.getName(), driver);
			extentLogger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			extentLogger.log(Status.FAIL,
					MarkupHelper.createLabel("TEST FAILED -- " + result.getName(), ExtentColor.RED));
			try {
//				HomePage.navigateToHomePage();
//				TestUtils.waitShortTimeForVisibilityOf(driver, HomePage.HOME_TAB, 2).isDisplayed();
			} catch (Exception e) {
				extentLogger.log(Status.FAIL, MarkupHelper.createLabel("App Crashed", ExtentColor.RED));
				BaseTest.lonchApp(ConfigeDataProvider.Platform_Android);
			}
		} else if (ITestResult.SUCCESS == result.getStatus()) {
//			HomePage.navigateToHomePage();
			extentLogger.log(Status.PASS,
					MarkupHelper.createLabel(" TEST PASSED -- " + result.getName(), ExtentColor.GREEN));
		} else {
//			HomePage.navigateToHomePage();
			extentLogger.log(Status.SKIP,
					MarkupHelper.createLabel(" TEST SKIPPED -- " + result.getName(), ExtentColor.ORANGE));
			extentLogger.skip(result.getThrowable());
		}
	}

	public static String takeScreenshot(String screenshotName, WebDriver driver) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy-hhmmss");
		String strDate = formatter.format(date);
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//Reports//FailedTestScreenShots//" + screenshotName + ".jpg";
		try {
			Files.copy(srcFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
