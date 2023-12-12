package DocQT.DocquityMobile.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import DocQT.DocquityMobile.Utility.TestUtils;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//android.widget.Button)[1]")
	private WebElement DRAWER_MENU;

	public void verifyDrawerMenuDisplay() {
		boolean displayed = TestUtils.waitForVisibilityOf(driver, DRAWER_MENU, "verifyDrawerMenuDisplay").isDisplayed();
		Assert.assertTrue(displayed);
	}

}
