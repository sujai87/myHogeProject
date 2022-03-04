package qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.config.DriverFactory;
import qa.pages.UserHomePage;

public class UserHomePageTest extends BaseClass {
	
	/***
	 * Description:Method to perform Deposit amount. 1.singup user. 2.Log in user 3.Deposit money
	 4.Validating Balance updating in 10 seconds 5.validating Transaction Details.
	 * 
	 * @author sujai
	 * @throws Exception
	 */
	@Test(groups = { "sanityTest" },dependsOnMethods = {"qa.test.LoginPageTest.userloginTest" })
	public void DespositTest() throws InterruptedException {	
		UserHomePage page = new UserHomePage(DriverFactory.getDriver());
		Assert.assertTrue(page.deposit(2000),"Failed in Deposit amount");
	}
	
	/***
	 * Description:Method to perform Withdraw amount. 1.singup user. 2.Log in user 3.Withdraw money
	 4.Validating Balance updating in 10 seconds 5.validating Transaction Details.
	 * 
	 * @author sujai
	 * @throws Exception
	 */
	@Test(groups = { "sanityTest" },dependsOnMethods = {"qa.test.LoginPageTest.userloginTest" })
	public void withDrawTest() throws InterruptedException {
		UserHomePage page = new UserHomePage(DriverFactory.getDriver());
		Assert.assertTrue(page.withdrawal(2000),"Failed in Withdraw amount");
		
		page.logout();
		
	}

}
