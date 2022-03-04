package qa.pages;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Reporter;

import qa.config.Configuration;

public class SeleniumDriverApi {

	private WebDriver driver;

	public SeleniumDriverApi() {
		super();

	}

	public void quitBroswer() {
		if (driver != null) {
			driver.close();;
			
		}
	}

	public boolean openBrowser(String url) {
		if (driver != null) {
			Reporter.log("Opening Browser " + url);
			driver.get(url);
			return true;
		}
		return false;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public SeleniumDriverApi(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public  void initialisation() {
		try {
			if (Configuration.getConfigValue("browser").equalsIgnoreCase("Chrome"))
				System.setProperty("webdriver.chrome.driver",
						new File(ClassLoader.getSystemClassLoader().getResource("chromedriver.exe").toURI())
								.toString());
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         
		} catch (Exception exception) {

		}
	}
	
	
	
	public void waitTime(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public boolean isExistsElement(WebDriver driver, String xpath) {
	        try {
	              driver.findElement(By.xpath(xpath));
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	        return true;
	    }
	
}
