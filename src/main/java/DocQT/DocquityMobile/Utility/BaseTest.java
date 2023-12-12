package DocQT.DocquityMobile.Utility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest extends ConfigeDataProvider {

	@BeforeTest(alwaysRun = true)
	public void configureAppium() throws MalformedURLException {
		Log.initialiseExtentReport(LOGGER);
		LOGGER.debug("<br>X");
		LOGGER.debug("<br>X ************************        lounching app       ************************** ");
		LOGGER.debug("<br>X");
		lonchApp(Platform_Android);
		System.out.println("Session ID:" + driver.getSessionId());
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		Log.extentLogger = Log.extent.createTest(method.getName());
		TestUtils.threadSleep(3000);
		LOGGER.debug("<br>X");
		LOGGER.debug("$$$$$$$$$$$$$$$  S-t-a-r-t-s -> Test -> " + method.getName() + "      $$$$$$$$$$$$$$$$$$");
		LOGGER.debug("<br>X");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method, ITestResult result) throws IOException {
		Log.afterMethodLogResult(method, result, driver);
		LOGGER.debug("<br>X");
		LOGGER.debug("$$$$$$$$$$$$$$$$  E---N---D -> Test -> " + method.getName() + "    $$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("<br>X");
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		LOGGER.debug("<br>X");
		LOGGER.debug("<br>X");
		LOGGER.debug("******************************         Driver Quit       **********************************");
		LOGGER.debug("<br>X");
		driver.quit();
//		service.stop();
		Log.flushExtent(LOGGER);
	}

	public static void lonchApp(String PlatFormName) {
		URL url = null;
		DesiredCapabilities caps;
		switch (PlatFormName) {
		case "ANDROID":
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, AndroidDeviceName);
			caps.setCapability(MobileCapabilityType.APP, UserDirectoryPath + AppPath);
			caps.setCapability("automationName", "UiAutomator2");
			caps.setCapability("newCommandTimeout", 1200);

			try {
				url = new URL(Url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new AndroidDriver(url, caps);
			break;

		case "IOS":
			caps = new DesiredCapabilities();
			caps.setCapability("platformName", "IOS");
			caps.setCapability("app",
					"/Users/hirak/Library/Developer/Xcode/DerivedData/UIKitCatalog-ckfjyojdoiibmedbaqvwhrebegcv/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
			caps.setCapability("deviceName", "Hirak");
			caps.setCapability("automationName", "XCUITest");
			caps.setCapability("platformVersion", "16.2");
			caps.setCapability("newCommandTimeout", 300);
			break;

		default:
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, AndroidDeviceName);
			caps.setCapability(MobileCapabilityType.APP, UserDirectoryPath + AppPath);
			caps.setCapability("automationName", "UiAutomator2");
			caps.setCapability("newCommandTimeout", 600);
			try {
				url = new URL(Url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new AndroidDriver(url, caps);
			break;
		}

		TestUtils.threadSleep(5000);

	}

	public static void relonch() {
		URL url = null;
		UiAutomator2Options options = new UiAutomator2Options();
		options.setApp(System.getProperty("user.dir") + AppPath);
		options.setDeviceName(AndroidDeviceName);
		try {
			url = new URL(Url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver = new AndroidDriver(url, options);

	}

	public static void lonchBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--incognito");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		webDriver = new ChromeDriver(options);
		webDriver.get("https://website.docquity.com/");
	}

	public static void quitBrowser() {
		webDriver.close();
	}

}
