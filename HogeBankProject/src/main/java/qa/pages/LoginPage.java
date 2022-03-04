package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Reporter;



public class LoginPage extends SeleniumDriverApi {

	@FindBy(tagName ="input")
	WebElement userName;
	
	@FindBy(xpath ="//input[@type = 'password']")
	WebElement passWord;
	
	@FindBy(xpath ="//span")
	WebElement errMsg;

	@FindBy(xpath ="//button[.='LOGIN']")
	WebElement loginButton; 
	
	@FindBy(xpath ="//button[.='Logout']")
	WebElement logout; 
	
	SignUpPage sign;
	LoginPage login;
	

	

	public LoginPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
		this.setDriver(driver);
	}

	public String login(String username, String password) {
		try {
			Reporter.log("LogIn With username " + username + " password " + password);
			userName.sendKeys(username);
			passWord.sendKeys(password);
			loginButton.click();
			this.waitTime(2000);
			if (isExistsElement(this.getDriver(),"//span")) {
				Reporter.log("LogIn failed With error " + errMsg.getText());
				return errMsg.getText();
			}
			Reporter.log("LogIn Success");
			return "Success";
		} catch (Exception exception) {
			Reporter.log("Exception occured  while doing Login " + exception.getLocalizedMessage());
			return "Failed";
		}
	}
	
	
		

	
	
}
