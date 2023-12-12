package DocQT.DocquityMobile.WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DocQT.DocquityMobile.Utility.TestUtils;

public class DevchannelHomePage extends BasePage {

	public DevchannelHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='Clinical Notes']")
	private WebElement CLINICAL_NOTES;

	@FindBy(xpath = "//span[text()='CME']")
	private WebElement CME_DROPDOWN;

	@FindBy(xpath = "//i[@class='fa fa-pencil-square']")
	private WebElement DRAFT_OPTION;

	@FindBy(xpath = "//i[@class='fa fa-podcast']")
	private WebElement WEBINAR_DROPDOWN;

	@FindBy(xpath = "//span[text()='Webinar List']")
	private WebElement WEBINAR_LIST_OPTIONS;

	public void clickOnClinicalNotes() {
		TestUtils.click(driver, CLINICAL_NOTES, "clickOnClinicalNotes");
	}

	public void clickOnCMEDropdown() {
		TestUtils.click(driver, CME_DROPDOWN, "clickOnCMEDropdown");
	}

	public void clickOnDraftOption() {
		TestUtils.click(driver, DRAFT_OPTION, "clickOnDraftOption");
	}

	public void clickOnWebinarDropdown() {
		TestUtils.click(driver, WEBINAR_DROPDOWN, "clickOnWebinarDropdown");
	}

	public void clickOnWebinarListOption() {
		TestUtils.click(driver, WEBINAR_LIST_OPTIONS, "clickOnWebinarListOption");
	}

}