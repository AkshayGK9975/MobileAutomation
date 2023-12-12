package DocQT.DocquityMobile.WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author yogesh.shinde
 *
 */

public abstract class BasePage {

	public static WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
