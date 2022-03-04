package qa.test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import qa.config.DriverFactory;


public class BaseClass {

	
	@BeforeTest(alwaysRun=true)
	public void setUp() {
	
		DriverFactory.initialization();

	}

	@AfterTest(alwaysRun=true)
	public void destroy() {
		DriverFactory.quitBroswer();
	}
	
	
	
	
	
	

}
