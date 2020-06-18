package pageobjects.webportal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;

public class AmazonLoginEmailPage extends BaseClass
{
	
	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonLoginEmailPage(WebDriver driver) throws InterruptedException
	{
/*		this.driver = driver;
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(this.driver, 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);*/
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}
	
	@FindBy(id="ap_email")
	public WebElement inputEmail;
	
	@FindBy(xpath="//input[@id='continue'][@aria-labelledby='continue-announce']")
	public WebElement continueButton;
	
	public AmazonLoginEmailPage enterEmailAddress(String email) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(inputEmail)).clear();
		inputEmail.sendKeys(email);
		return new AmazonLoginEmailPage(getDriver());
	}
	
	public AmazonLoginPasswordPage clickOnContinueButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(continueButton)).click();;
		return new AmazonLoginPasswordPage(getDriver());
	}
	
	public AmazonHomePage amazonIndiaLogin(String userName, String password) throws InterruptedException
	{
		AmazonLoginEmailPage amazonLoginEmailPage = enterEmailAddress(userName);
		AmazonLoginPasswordPage amazonLoginPasswordPage = amazonLoginEmailPage.clickOnContinueButton();
		amazonLoginPasswordPage = amazonLoginPasswordPage.enterPassword(password);
		AmazonHomePage amazonHomePage = amazonLoginPasswordPage.clickOnLoginButton();
		return new AmazonHomePage(getDriver());
	}
	
	public AmazonHomePage amazonUSLogin(String userName, String password) throws InterruptedException
	{
		AmazonLoginEmailPage amazonLoginEmailPage = enterEmailAddress(userName);
		//AmazonLoginPasswordPage amazonLoginPasswordPage = amazonLoginEmailPage.clickOnContinueButton();
		AmazonLoginPasswordPage amazonLoginPasswordPage = new AmazonLoginPasswordPage(getDriver());
		amazonLoginPasswordPage = amazonLoginPasswordPage.enterPassword(password);
		AmazonHomePage amazonHomePage = amazonLoginPasswordPage.clickOnLoginButton();
		return amazonHomePage;
	}

}
