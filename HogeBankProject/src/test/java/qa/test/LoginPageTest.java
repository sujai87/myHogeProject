package qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.config.Configuration;
import qa.config.DriverFactory;
import qa.pages.LoginPage;

public class LoginPageTest extends BaseClass  {
	
	
	
	@DataProvider(name="validatingLoginData")
	public String[][] getInvalidData()
	{
		String invalidData[][] = {
				{"Test One","Qwert1234","User name cannot contain whitespaces"},//white space in username
				{"", "Qwert1234","User name cannot be blank"}, //empty username
				{"user1","qwert1234","Password must contain uppercase letters"},//password check - No capital letter
				{"user1","","Password cannot be less than 8 characters"},	//empty password
				{"user1","Qwertyasd","Password must contain numbers"},//password check - No numbers
				{"user1","Qwer123","Password cannot be less than 8 characters"},//password check - less than 8 characters
				{"user1","Qwerty123456789qwerty123456789123","Password cannot be longer than 32 characters"},//password check - Larger than 32 characters
				{"user1","Qwert123","Password cannot be less than 8 characters"},//password check - Exact 8 characters
				{"user1","Qwerty123456789qwerty12345678912","Password cannot be longer than 32 characters"},//password check - Exact 32 characters
				
				};
				
		return invalidData;
		
	}
	
	
	/***
	 * Description:Method to perform login.
	 * 
	 * @author sujai
	 * 
	 */
	@Test(dependsOnMethods = {"qa.test.SignUpPageTest.userSignUpTest" })
	public void userloginTest() throws InterruptedException {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.openBrowser(Configuration.getConfigValue("url")+Configuration.getConfigValue("loginPath"));
		loginPage.waitTime(5000);
		String result = loginPage.login(Configuration.getConfigValue("uname"),
				Configuration.getConfigValue("pwd"));
		Assert.assertTrue(result.equalsIgnoreCase("Success"), "Login Failed");
		

	}
	
	/***
	 * Description:Method to validate  for InValid user and password Login.
	 * 
	 * @author sujai
	 * 
	 */
	@Test(groups = { "sanityTest" },dataProvider = "validatingLoginData")
	public void invalidUserAccountValidationTest(String uname, String password, String errorMessage)
	
	{
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.openBrowser(Configuration.getConfigValue("url")+Configuration.getConfigValue("loginPath"));
		loginPage.waitTime(5000);
		String result = loginPage.login(uname,	password);
		Assert.assertTrue(result.equalsIgnoreCase(errorMessage),"Login Error Msg validation failed");
	}
	
	
}
