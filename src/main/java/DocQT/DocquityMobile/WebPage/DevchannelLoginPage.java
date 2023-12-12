package DocQT.DocquityMobile.WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DocQT.DocquityMobile.Utility.TestUtils;

public class DevchannelLoginPage extends BasePage {

	public DevchannelLoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='pharmalogin-username']")
	private WebElement USERNAME;

	@FindBy(xpath = "//input[@id='pharmalogin-password']")
	private WebElement PASSWORD;

	@FindBy(xpath = "//button[@name='login-button']")
	private WebElement SIGNIN_BTN;

	public void enterUsername(String username) {
		TestUtils.sendKeys(driver, USERNAME, "enterUsername", username);
	}

	public void enterPassword(String password) {
		TestUtils.sendKeys(driver, PASSWORD, "enterPassword", password);
	}

	public void clickOnSignInButton() {
		TestUtils.click(driver, SIGNIN_BTN, "clickOnSignInButton");
	}

	public void loginDevChannel(String DevChannelURL, String Username, String Password) {
		driver.get(DevChannelURL);
		enterUsername(Username);
		enterPassword(Password);
		clickOnSignInButton();
	}

}