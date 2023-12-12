package DocQT.DocquityMobile.Page;

import org.openqa.selenium.support.PageFactory;

import DocQT.DocquityMobile.Utility.ConfigeDataProvider;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author akshay.kanchangire
 *
 */

public abstract class BasePage extends ConfigeDataProvider {

	public static AndroidDriver driver;

	public BasePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
