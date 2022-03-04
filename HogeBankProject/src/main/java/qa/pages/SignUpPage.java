package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


public class SignUpPage extends SeleniumDriverApi{

	@FindBy(tagName ="input")
	WebElement userName;
	
	@FindBy(xpath ="//input[@type = 'password']")
	WebElement passWord;
	
	@FindBy(xpath ="//*[text()='SIGNUP']")
	WebElement signUp;
	
	@FindBy(xpath ="//span")
	WebElement errMsg;
	
	@FindBy(xpath ="//button[.='Logout']")
	WebElement logout; 
	
	public SignUpPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.setDriver(driver);
	}
	
	
	public String signUp(String username, String password) {
		try {
			Reporter.log("Clicking on Signup Button");
			signUp.click();
			Reporter.log("SingUp With username " + username + " password " + password);
			userName.sendKeys(username);
			passWord.sendKeys(password);
			Reporter.log("Clicking on Signup Button");
			signUp.click();
			if (errMsg.isDisplayed()) {
				Reporter.log("Error message: " + errMsg.getText());
				userName.clear();
				passWord.clear();
				return errMsg.getText();
			} else {
				Reporter.log("Sign Up Success");
				logout.click();
				this.waitTime(5000);
				return "Success";
			}
		} catch (Exception exception) {
			Reporter.log("Exception occured  while doing SIgnup " + exception.getLocalizedMessage());
			return "Failed";
		}

	}
	
	
	
	
}
