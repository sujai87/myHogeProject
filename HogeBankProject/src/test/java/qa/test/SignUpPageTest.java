package qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.config.Configuration;
import qa.config.DriverFactory;
import qa.pages.SignUpPage;

public class SignUpPageTest extends BaseClass  {
	

	
	
	@DataProvider(name="validatingSignUpData")
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
	
	@DataProvider(name="ValidUserData")
	public String[][] getValidData()
	{
		String validData[][] = {
				{"UserOne","Qwert1234"},
				{"Navaneeth","euWyy3435"},//valid username and password
				
				};
				
		return validData;
		
	}
	
	/***
	 * Description:Method to validate  for InValid user and password signup.
	 * 
	 * @author sujai
	 * 
	 */
	@Test(groups = { "sanityTest" },dataProvider = "validatingSignUpData")
	public void signUpInvalidUserPwdValidationTest(String uname, String pwd, String errorMessage)
	{
		SignUpPage signUpPage = new SignUpPage(DriverFactory.getDriver());
		signUpPage.openBrowser(Configuration.getConfigValue("url")+Configuration.getConfigValue("signupPath"));
		signUpPage.waitTime(3000);
		String result = signUpPage.signUp(uname, pwd);
		Assert.assertTrue(result.equalsIgnoreCase(errorMessage), "Signup Error Msg validation failed");

	}
	
	/***
	 * Description:Method to perform Sigup user.
	 * 
	 * @author sujai
	 * 
	 */
	@Test(enabled = true)
	public void userSignUpTest()
	{
		SignUpPage signUpPage = new SignUpPage(DriverFactory.getDriver());
		signUpPage.openBrowser(Configuration.getConfigValue("url")+Configuration.getConfigValue("signupPath"));
		String result = signUpPage.signUp(Configuration.getConfigValue("uname"), Configuration.getConfigValue("pwd"));
		Assert.assertTrue(result.equalsIgnoreCase("Success"), "Signup Failed");

	}

}
