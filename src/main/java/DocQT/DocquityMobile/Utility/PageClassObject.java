package DocQT.DocquityMobile.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import DocQT.DocquityMobile.Page.HomePage;
import DocQT.DocquityMobile.Page.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class PageClassObject {

	public static Logger LOGGER = LogManager.getLogger(Log.class);
	public static AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public static WebDriver webDriver;

	public static LoginPage loginPage;
	public static HomePage homePage;

	@BeforeMethod(alwaysRun = true)
	public void getObject() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

}
