package qa.pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;


public class UserHomePage extends SeleniumDriverApi {

	UserHomePage home;
	SignUpPage sign;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Balance']//following::td")
	WebElement balHomePage;

	@FindBy(xpath = "//*[text()='残高']//following-sibling::span[1]")
	WebElement balDepPage;

	@FindBy(xpath = "//div/h2/a[text()='引き出す']")
	WebElement homeWithdrawOption;

	@FindBy(xpath = "//div/h2/a[text()='デポジット']")
	WebElement homeDepositOption;

	@FindBy(xpath = "//*[text()='取引料金']//following-sibling::span[1]")
	WebElement transFee;

	@FindBy(tagName = "input")
	WebElement depositAmount;

	@FindBy(xpath = "//span[contains(text(),'AFTER_TRANSACTION')]//following-sibling::span")
	WebElement afterTransaction;

	@FindBy(xpath = "//td[contains(text(),'Balance')]//following::td")
	WebElement balanceAmount;

	@FindBy(xpath = "//button[text()='Deposit']")
	WebElement depositButton;

	@FindBy(xpath = "//td[contains(text(),'Transactions')]/..//tbody/tr")
	List<WebElement> getTansactions;
	
	@FindBy(xpath = "//button[text()='Withdraw']")
	WebElement withdrawbutton;
	
	@FindBy(xpath ="//button[.='Logout']")
	WebElement logoutButton; 

	
	
	public UserHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.setDriver(driver);
	}

	public boolean deposit(double amount)  {
		boolean result =false;
		try {
			Reporter.log("In deposit");
			//get balance fom home page
			double homeBalance = Double.valueOf(balHomePage.getText());
			homeDepositOption.click();
			this.waitTime(2000);
			double depBalance = Double.valueOf(balDepPage.getText());
			
			//balance seen in home page matches with balance in deposit page
			Assert.assertEquals(homeBalance, depBalance, "Balance in Home Page and Deposit Page differs");
			// send deposit amount
			depositAmount.sendKeys(String.valueOf(amount));
			
			//calculating transaction fee
			double calcFee = amount * 30 / 100;

			if (calcFee == Double.valueOf(transFee.getText())) {
				Reporter.log("Transaction Fee is Correct : " + calcFee);
			}
			
			//calculating final_deposit_key
			double calcFinalDepAmount = amount - calcFee;
			double finalDep = Double.valueOf(afterTransaction.getText());
			
			//check calculated final_deposit_key with displayed
			if (finalDep == calcFinalDepAmount) {
				Reporter.log("Deposit Amount after Transaction Fee : " + calcFinalDepAmount);
			}
			
			//calculate total balance i.e. existing balance with deposited amount
			double calcBal = homeBalance + calcFinalDepAmount;
			depositButton.click();
			
			//user in home page
			//displayed balance after 10 seconds
			double balanceAfterDep =0l;
			long endTime = System.currentTimeMillis() + 10000;
			while (System.currentTimeMillis() <= endTime) {
			 balanceAfterDep = Double.valueOf(balanceAmount.getText());
				if (balanceAfterDep == calcBal) {
					result=true;
					break;
				}
			}
			
			//get the transaction details
			List<WebElement> transactions = getTansactions;
			for (WebElement e : transactions) {
				Reporter.log(e.getText());
			}
			Reporter.log("Total Balance after deposit in HomePage : " + balanceAfterDep);
			Reporter.log("Account Balance "+(result?"Successfully":"Failed")+" Updated in 10 Seconds : " +balanceAfterDep);
			return result;
		} catch (Exception exception) {
			Reporter.log("Error Occured : " + exception.getMessage());
			return false;
		}
	}

	public boolean withdrawal(double amount){
       boolean result=false;
		try {
			this.waitTime(2000);
			//get the homebalance value for futher calculation
			double homeBalance = Double.valueOf(balHomePage.getText());
			homeWithdrawOption.click();
			
			//user landed up in withdrawal page
			this.waitTime(2000);
			Reporter.log("\n\n********************************************************\n\n");
			
			//send the amount to be withdrawn
			depositAmount.sendKeys(String.valueOf(amount));
			
			//calculate the transation fee and compare with the displayed fee
			double calcFee = amount * 30 / 100;
			if (calcFee == Double.valueOf(transFee.getText())) {
				Reporter.log("Transaction Fee is Correct : " + calcFee);
			}
			
			//calculate the total amount to be withdrawan i.e. withdraw amount along with transaction fee
			double calcFinalWithdrawAmount = amount + calcFee;
			double finalWithdrawal = Double.valueOf(afterTransaction.getText());
			
			//check the calculated and disdplayed amount to be withdrawn (final_withdawal_key)
			if (finalWithdrawal == calcFinalWithdrawAmount) {
				Reporter.log("Withdrawal Amount with Transaction Fee : " + calcFinalWithdrawAmount);
			}
			
			//calculate the balance after withdrawal
			double calcBal = homeBalance - calcFinalWithdrawAmount;
			withdrawbutton.click();
			
			//user landed in homepage
			//display the balance after 10 seconds
			double balanceAfterWithdraw =0l;
			long endTime = System.currentTimeMillis() + 10000;
					while (System.currentTimeMillis() <=endTime) {
					 balanceAfterWithdraw = Double.valueOf(balanceAmount.getText());
						if (balanceAfterWithdraw == calcBal) {
							Reporter.log("Account Balance Updated in 10 Seconds : " + calcBal);
							result=true;
							break;
						}
						
					}
					
					//get the transaction details
	    	List<WebElement> transactions = getTansactions;
			for (WebElement e : transactions) {
				Reporter.log(e.getText());
			}
			Reporter.log("Total Balance after Withdraw in HomePage : " + balanceAfterWithdraw);
			Reporter.log("Account Balance "+(result?"Successfully":"Failed")+" to Updated in 10 Seconds : " +balanceAfterWithdraw);
			return result;
		} catch (Exception exception) {
			Reporter.log("Exception occured in WithDraw validation : " + exception.getLocalizedMessage());
			return false;
		}

	}
	
	public void logout()
	{
		logoutButton.click();
		this.waitTime(5000);
	}
}

