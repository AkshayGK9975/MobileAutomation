package DocQT.DocquityMobile.Test;

import org.testng.annotations.Test;

import DocQT.DocquityMobile.Utility.BaseTest;

public class LoginTest extends BaseTest {

	@Test(priority = 1, groups = { "RegressionTest" })
	public void verifySuccessfullLogin() {

		loginPage.login("Test01", "Test@12345", "anton-paar");

	}

}
