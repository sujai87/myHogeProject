package qa.config;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> WEBDRIVER = new ThreadLocal<WebDriver>();
	private  WebDriver driver;

	public static void initialization() {

		try {
			if (Configuration.getConfigValue("browser").equalsIgnoreCase("Chrome"))
				System.setProperty("webdriver.chrome.driver",
						new File(ClassLoader.getSystemClassLoader().getResource("chromedriver.exe").toURI())
								.toString());
			WebDriver driver = WEBDRIVER.get();
			if (driver == null) {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				WEBDRIVER.set(driver);
			}
		} catch (Exception exception) {

		}
		
	}
	
	
	 

	public static WebDriver getDriver() {
			return WEBDRIVER.get();
	}
	
	public static void quitBroswer() {
		if (WEBDRIVER.get() != null) {
			WEBDRIVER.get().quit();
			
			
		}
	}

}
