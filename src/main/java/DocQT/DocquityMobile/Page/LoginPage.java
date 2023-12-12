package DocQT.DocquityMobile.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DocQT.DocquityMobile.Utility.TestUtils;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {

	public LoginPage(AndroidDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.Button[@content-desc='Get Started']")
	private WebElement GET_STARTED_BUTTON;

	@FindBy(xpath = "(//android.widget.EditText[@text=''])[1]")
	private WebElement USERNAME;

	@FindBy(xpath = "(//android.widget.EditText[@text=''])[1]")
	private WebElement PASSWORD;

	@FindBy(xpath = "(//android.widget.EditText[@text=''])[1]")
	private WebElement COMPANY_ID;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Sign In']")
	private WebElement SIGN_IN_BUTTON;

	@FindBy(xpath = "//android.widget.Button[@text='While using the app']")
	private WebElement WHILE_USING_APP;

	@FindBy(xpath = "//android.widget.Button[@text='Only this time']")
	private WebElement ONLY_THIS_TIME;

	@FindBy(xpath = "//android.widget.Button[@text='Don’t allow']")
	private WebElement DONT_ALLOW;

	public void clickOnGetStartedButton() {
		TestUtils.click(driver, GET_STARTED_BUTTON, "clickOnGetStartedButton");
	}

	public void enterUsername(String UserName) {
		TestUtils.click(driver, USERNAME, "enterUsername");
		TestUtils.sendKeys(driver, USERNAME, "loginWithValidCredential", UserName);
		TestUtils.hideKeyboard(driver);
	}

	public void enterPassword(String Password) {
		TestUtils.click(driver, PASSWORD, "enterPassword");
		TestUtils.sendKeys(driver, PASSWORD, "loginWithValidCredential", Password);
		TestUtils.hideKeyboard(driver);
	}

	public void enterCompanyId(String CompanyId) {
		TestUtils.click(driver, COMPANY_ID, "enterCompanyId");
		TestUtils.sendKeys(driver, COMPANY_ID, "loginWithValidCredential", CompanyId);
		TestUtils.hideKeyboard(driver);
	}

	public void clickOnSignButton() {
		TestUtils.click(driver, SIGN_IN_BUTTON, "clickOnSignButton");
	}

	public void clickOnWhileUsingApp() {
		TestUtils.click(driver, WHILE_USING_APP, "clickOnWhileUsingApp");
	}

	public void clickOnOnlyThisTime() {
		TestUtils.click(driver, ONLY_THIS_TIME, "clickOnOnlyThisTime");
	}

	public void clickOnDontAllow() {
		TestUtils.click(driver, DONT_ALLOW, "clickOnDontAllow");
	}

	public void login(String UserName, String Password, String CompanyId) {
		clickOnGetStartedButton();
		enterUsername(UserName);
		enterPassword(Password);
		enterCompanyId(CompanyId);
		clickOnSignButton();
		clickOnWhileUsingApp();
		homePage.verifyDrawerMenuDisplay();
	}

}
