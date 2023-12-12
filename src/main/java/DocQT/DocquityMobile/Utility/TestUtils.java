package DocQT.DocquityMobile.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestUtils {

	public static int PAGE_LOAD_TIMEOUT = 100;
	public static int IMPLICIT_WAIT = 20;
	public ConfigeDataProvider configGet;
	private static int num1;
	private static int num2;

	public static String getMethodName() {
		Object ob = new Object() {
		};
		String MethodName = ob.getClass().getEnclosingMethod().getName();
		return MethodName;
	}

	public static void hideKeyboard(AndroidDriver driver) {
		driver.hideKeyboard();
	}

	public static void click(WebDriver driver, WebElement element, String LogMessege) {
		try {
			waitForVisibilityOf(driver, element);
			element.click();
			Log.info(LogMessege);
		} catch (Exception e) {
			Log.error("Test Failed at step : " + LogMessege + "  --  " + element);
			element.click();
		}
	}

	public static void sendKeys(WebDriver driver, WebElement element, String LogMessege, String enterValue) {
		try {
			waitForVisibilityOf(driver, element);
			element.clear();
			element.sendKeys(enterValue);
			Log.info(LogMessege + " : " + enterValue);
		} catch (Exception e) {
			Log.error("Test Failed at step : " + LogMessege + " : " + enterValue + "  --  " + element);
			element.sendKeys(enterValue);
		}
	}

	public static WebElement waitForVisibilityOf(WebDriver driver, WebElement element) {
		TestUtils.threadSleep(000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public static WebElement waitForVisibilityOf(WebDriver driver, WebElement element, String LogMessge) {
		try {
			TestUtils.threadSleep(000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info(LogMessge);
		} catch (Exception e) {
			Log.info("unable to : " + LogMessge + "  --  " + element);
		}

		return element;
	}

	public static WebElement waitShortTimeForVisibilityOf(WebDriver driver, WebElement element, int Seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public static WebElement waitShortTimeForVisibilityOf(WebDriver driver, WebElement element, int Seconds,
			String LogMessge) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info(LogMessge);
		} catch (Exception e) {
			Log.info("unable to : " + LogMessge + "  --  " + element);
		}
		return element;
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public static void longPress(AndroidDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public static void scrollDownTillElementDispaly(AndroidDriver driver, String text) {
		try {
			driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
			Log.info("scrollTillElement : " + text);
		} catch (Exception e) {
			driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
			Log.info("unable to scrollTillElement : " + text);
		}
	}

	public static void customScrollTillElementDisplay(AndroidDriver driver, WebElement element) {
		Log.info("customScrollTillElementDisplay");
		for (int i = 0; i <= 10; i++) {
			TestUtils.threadSleep(1000);
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .5);
			action.press(PointOption.point(middleOfX, startYCoordinate))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
					.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();

			try {
				element.isDisplayed();
				break;
			} catch (Exception e) {
				System.out.print("");
			}
		}

	}

	public static void ScrollUpTillElementDisplay(AndroidDriver driver, WebElement element) {
		Log.info("customScrollTillElementDisplay");
		for (int i = 0; i <= 10; i++) {
			TestUtils.threadSleep(1000);
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .2);
			int endYCoordinate = (int) (height * .7);
			action.press(PointOption.point(middleOfX, startYCoordinate))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
					.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();

			try {
				element.isDisplayed();
				break;
			} catch (Exception e) {
				System.out.print("");
			}
		}

	}

	public static void scrollByOffset(AndroidDriver driver, int NoOfPagesScroll) {
		Log.info("Scrolling by offset : " + NoOfPagesScroll);
		for (int i = 0; i < NoOfPagesScroll; i++) {
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .2);
			try {
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
			} catch (Exception e) {
				Log.info("Unable to scroll");
				break;
			}

		}
	}

	public static double convertStringIntoDouble(String StringValue) {
		Double DoubleValue = Double.parseDouble(StringValue);
		return DoubleValue;
	}

	public static void threadSleep(int MiliSeconds) {
		try {
			Thread.sleep(MiliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void calculatePageLoadTime(AndroidDriver driver) {
		Log.debug("Calculating the page load time");
		double loadTime = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		loadTime = (Double) js.executeScript(
				"return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
		Log.info("Page load time calculated in seconds is " + loadTime);
	}

	public static String getStringConfigData(String key) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/prod_config.Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String PostURL = prop.getProperty(key);
		return PostURL;
	}

	public static int getNumericConfigData(String key) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/prod_config.Properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String KeyValue = prop.getProperty(key);
		int intKeyValue = Integer.parseInt(KeyValue);
		return intKeyValue;
	}

	public static String getExcelData(int SheetNumber, int RowNumber, int ColumnNumber) {
		String path = "/src/test/resources/TestData/ExcelTestData.xlsx";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String TestData = wb.getSheetAt(SheetNumber).getRow(RowNumber).getCell(ColumnNumber).getStringCellValue();
			return TestData;
		} catch (Exception e) {
			double TestData_InDouble = wb.getSheetAt(SheetNumber).getRow(RowNumber).getCell(ColumnNumber)
					.getNumericCellValue();
			String TestData = String.valueOf(TestData_InDouble);
			String TestDataAr[] = TestData.split("\\.");
			return TestDataAr[0];
		}
	}

	public static void pressBackSpaceKey(AndroidDriver driver) {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public static void navigateBack(AndroidDriver driver) {
		driver.navigate().back();
	}

	public static void refresh(AndroidDriver driver) {
		driver.navigate().refresh();
	}

	public static void navigateForword(AndroidDriver driver) {
		driver.navigate().forward();
	}

	public static String getCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	public static void moveToPage(AndroidDriver driver, String appPackage, String appActivity) {
		Activity activity = new Activity(appPackage, appActivity);
//		activity.setAppWaitPackage(appPackage);
//		activity.setAppWaitActivity(appActivity);
		driver.startActivity(activity);
	}

	public static void waitForPage(AndroidDriver driver) {
		TestUtils.calculatePageLoadTime(driver);
	}

	public static String addSpaceBeforeUppercase(String input) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char inputInChar = input.charAt(i);
			if (Character.isUpperCase(inputInChar)) {
				result.append(" ");
			}
			result.append(inputInChar);
		}

		return result.toString();
	}

	public static String splitString(String inputToBeSplit, String SplittedExpectedData) {
		String result = null;
		String arr[] = inputToBeSplit.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].contains(SplittedExpectedData)) {
				result = arr[i];
			}
		}
		return result;
	}

	public static String randomNumber() {
		Random random = new Random();
		int a = random.nextInt(9999);
		if (a > 1000 & a != 0) {
			num1 = a;
		}
		if (num1 == 0) {
			num1 = 9999;
		}

		int c = random.nextInt(9999);
		if (c > 1000 & c != 0) {
			num2 = c;
		}
		if (num2 == 0) {
			num2 = 9999;
		}

		return num1 + "" + num2;
	}

	public static WebElement findByXpath(AndroidDriver driver, String Xpath, String LogMessege) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(Xpath));
			TestUtils.waitShortTimeForVisibilityOf(driver, element, 10);
			Log.info(LogMessege);
		} catch (Exception e) {
			Log.info("Test Failed at step : " + LogMessege);
			driver.findElement(By.xpath(Xpath));
		}
		return element;
	}

	public static void swipeLeft(AndroidDriver driver, WebElement FirstElement, WebElement SecondElement,
			String LogMessege, int noOfTimes) {
		Log.info(LogMessege);
		for (int i = 0; i < noOfTimes; i++) {
			TestUtils.threadSleep(1000);
			TouchAction action = new TouchAction(driver);
			int midOfY = 0;
			int fromXLocation = 0;
			int toXLocation = 0;

			try {
				midOfY = FirstElement.getLocation().y + (FirstElement.getSize().height / 2);
				fromXLocation = SecondElement.getLocation().x;
				toXLocation = FirstElement.getLocation().x;
				action.press(PointOption.point(fromXLocation, midOfY))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
			} catch (Exception e) {
				Log.info("Test Failed at step : " + LogMessege);
				action.press(PointOption.point(fromXLocation, midOfY))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
			}
		}
	}

	public static void swipeLeftTillElementDisplay(AndroidDriver driver, WebElement FirstElement,
			WebElement SecondElement, String LogMessege, WebElement ElementTillSwipe) {
		Log.info(LogMessege);
		for (int i = 0; i < 30; i++) {

			try {
				ElementTillSwipe.isDisplayed();
				TestUtils.threadSleep(3000);
				break;
			} catch (Exception e) {

				TestUtils.threadSleep(1000);
				TouchAction action = new TouchAction(driver);
				int midOfY = 0;
				int fromXLocation = 0;
				int toXLocation = 0;

				try {
					midOfY = FirstElement.getLocation().y + (FirstElement.getSize().height / 2);
					fromXLocation = SecondElement.getLocation().x;
					toXLocation = FirstElement.getLocation().x;
					action.press(PointOption.point(fromXLocation, midOfY))
							.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
							.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
				} catch (Exception e1) {
					Log.info("Test Failed at step : " + LogMessege);
				}

			}
		}
	}

	public static void swipeRight(AndroidDriver driver, WebElement FirstElement, WebElement SecondElement,
			String LogMessege, int noOfTimes) {
		Log.info(LogMessege);
		for (int i = 0; i < noOfTimes; i++) {
			TestUtils.threadSleep(1000);
			TouchAction action = new TouchAction(driver);
			int midOfY = 0;
			int fromXLocation = 0;
			int toXLocation = 0;

			try {
				midOfY = FirstElement.getLocation().y + (FirstElement.getSize().height / 2);
				fromXLocation = FirstElement.getLocation().x;
				toXLocation = SecondElement.getLocation().x;
				action.press(PointOption.point(fromXLocation, midOfY))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
			} catch (Exception e) {
				Log.info("Test Failed at step : " + LogMessege);
				action.press(PointOption.point(fromXLocation, midOfY))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
			}
		}
	}

	public static void assertTrue(boolean Actual) {
		Assert.assertTrue(Actual);
		Log.info("Expected [true] " + "Found " + "[" + Actual + "]");
	}

	public static void assertEquals(String Actual, String Expected) {
		Assert.assertEquals(Actual, Expected);
		Log.info("Expected [" + Expected + "] " + "Found " + "[" + Actual + "]");
	}

	public static void performEnterAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}

}
