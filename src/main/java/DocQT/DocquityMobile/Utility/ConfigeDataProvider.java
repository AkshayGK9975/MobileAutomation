package DocQT.DocquityMobile.Utility;

/**
 * @author yogesh.shinde
 * 
 */

import com.github.javafaker.Faker;

import DocQT.DocquityMobile.Enum.PlatForm;

public class ConfigeDataProvider extends PageClassObject {

	public static String UserDirectoryPath = System.getProperty("user.dir");
	public static String AppiumServerPath = TestUtils.getStringConfigData("AppiumServerPath");
	public static String AppPath = TestUtils.getStringConfigData("AppPath");
	public static String IpAdress = TestUtils.getStringConfigData("IpAdress");
	public static String Url = TestUtils.getStringConfigData("Url");
	public static int Port = TestUtils.getNumericConfigData("Port");
	public static String Country = TestUtils.getStringConfigData("Country");
	public static String Otp = TestUtils.getStringConfigData("Otp");
	public static String AndroidDeviceName = TestUtils.getStringConfigData("DeviceName");
	public static String IosDeviceName = TestUtils.getStringConfigData("DeviceName");
	public static String Platform_Android = PlatForm.ANDROID.toString();

	public static String MobileNumber1 = TestUtils.getStringConfigData("MobileNumber1");
	public static String MobileNumber2 = TestUtils.getStringConfigData("MobileNumber2");
	public static String MobileNumber3 = TestUtils.getStringConfigData("MobileNumber3");
	public static String MobileNumber4 = TestUtils.getStringConfigData("MobileNumber4");
	public static String MobileNumber5 = TestUtils.getStringConfigData("MobileNumber5");

	public static String RandomNumber = TestUtils.randomNumber();
	public static String MRNNumber = TestUtils.randomNumber();

	public static Faker faker = new Faker();
	public static String FirstName = faker.name().firstName();
	public static String MiddleName = faker.name().firstName();
	public static String LastName = faker.name().lastName();
	public static String Email = FirstName + "@gmail.com";

}
