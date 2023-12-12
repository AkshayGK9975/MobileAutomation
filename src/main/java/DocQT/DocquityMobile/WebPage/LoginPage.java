package DocQT.DocquityMobile.WebPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DocQT.DocquityMobile.Utility.TestUtils;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()=' Accept']")
	private WebElement ACCEPT_COOKIES;

	@FindBy(xpath = "//img[contains(@class,'ml-4 cursor-')]")
	private WebElement CANCEL_COOKIES;

	@FindBy(xpath = "//input[@id='mobile_no']")
	private WebElement MOBILE_NUMBER;

	@FindBy(xpath = "//div[@class='c-btn border-right']")
	private WebElement COUNTRY_DROPDOWN;

	@FindBy(xpath = "//span[text()='India']")
	private WebElement COUNTRY_INDIA;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement COUNTRY_SEARCH_BOX;

	@FindBy(xpath = "(//li[@class='pure-checkbox'])[1]")
	private WebElement FIRST_COUNTRY;

	@FindBy(xpath = "//div[text()=' +1 ']")
	private WebElement DEFAULT_COUNTRY;

	@FindBy(xpath = "//button[@id='requestOtpClick']")
	private WebElement REQUEST_OTP;

	@FindBy(xpath = "//input[contains(@id, 'otp_0')]")
	private WebElement INTER_OTP_BOX1;

	@FindBy(xpath = "//input[contains(@id, 'otp_1')]")
	private WebElement INTER_OTP_BOX2;

	@FindBy(xpath = "//input[contains(@id, 'otp_2')]")
	private WebElement INTER_OTP_BOX3;

	@FindBy(xpath = "//input[contains(@id, 'otp_3')]")
	private WebElement INTER_OTP_BOX4;

	@FindBy(id = "1")
	private WebElement OTP_TEXTFIELD;

	@FindBy(xpath = "//button[@id='requestOtpClick']")
	private WebElement VERIFY_BTN;

	@FindBy(xpath = "//button[@class='btn btn-success px-4 py-2 cursor-pointer suspicious-activity-button']")
	private WebElement SUBMIT_BTN;

	@FindBy(xpath = "//button[contains(text(),'Okay ')]")
	private WebElement OKAY_BTN;

	@FindBy(xpath = "//p[text()='Your account has temporarily suspended for 30 minutes.'] | //p[text()='Session expired. Please try again']")
	private WebElement SESSION_EXPIRED_OR_TERMINATE;

	@FindBy(xpath = "//span[text()='Home']")
	private WebElement HOME_TAB;

	public void clickAcceptCookiesButton() {
		TestUtils.click(driver, ACCEPT_COOKIES, "clickAcceptCookiesButton");
	}

	public void clickOnCancelCookies() {
		TestUtils.click(driver, CANCEL_COOKIES, "clickOnCancelCookies");
	}

	public void enterMobileNumber(final String mobileNumber) {
		TestUtils.sendKeys(driver, MOBILE_NUMBER, "enterMobileNumber", mobileNumber);

	}

	public void selectCountry(String country) {
		TestUtils.click(driver, COUNTRY_DROPDOWN, "clickOnCountryDropdown");
		TestUtils.sendKeys(driver, COUNTRY_SEARCH_BOX, "enterCountryName", country);
		TestUtils.click(driver, FIRST_COUNTRY, "selectCountry");
	}

	public void clickRequestOTP() {
		TestUtils.click(driver, REQUEST_OTP, "clickRequestOTP");
	}

	public void enterOTP(final String otp1, final String otp2, final String otp3, final String otp4) {
		try {
			TestUtils.waitShortTimeForVisibilityOf(driver, INTER_OTP_BOX1, 5, "enterOTP").isDisplayed();
		} catch (Exception a) {
			try {
				for (int i = 2; i <= 5; i++) {
					if (SESSION_EXPIRED_OR_TERMINATE.isDisplayed()) {
						MOBILE_NUMBER.clear();
						enterMobileNumber("930708090" + i + Keys.ENTER);
						TestUtils.threadSleep(3000);
					}
				}
			} catch (Exception b) {
				System.out.println("session expired ");
			}
		} finally {
			TestUtils.waitForVisibilityOf(driver, INTER_OTP_BOX1).sendKeys(otp1);
			TestUtils.waitForVisibilityOf(driver, INTER_OTP_BOX2).sendKeys(otp2);
			TestUtils.waitForVisibilityOf(driver, INTER_OTP_BOX3).sendKeys(otp3);
			TestUtils.waitForVisibilityOf(driver, INTER_OTP_BOX4).sendKeys(otp4);
		}

	}

	public void clickOnVerifyOtpBtn() {
		TestUtils.click(driver, VERIFY_BTN, "clickOnVerifyOtpBtn");
	}

	public void verifyDefaultUserCountry(String expectedCountryCode) {
		String ACTUAL_COUNTRY_CODE = TestUtils.waitForVisibilityOf(driver, COUNTRY_DROPDOWN).getText();
		TestUtils.assertEquals(ACTUAL_COUNTRY_CODE, expectedCountryCode);
	}

	public void clickOnHomeTab() {
		TestUtils.click(driver, HOME_TAB, "clickOnHomeTab");
	}

	public void loginWithValidCredential(final String number, final String country, final String otp1,
			final String otp2, final String otp3, final String otp4) {
		enterMobileNumber(number);
		selectCountry(country);
		clickRequestOTP();
		enterOTP(otp1, otp2, otp3, otp4);
		clickOnVerifyOtpBtn();
		clickOnHomeTab();
	}

}