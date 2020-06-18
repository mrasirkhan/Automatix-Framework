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

public class AmazonLoginPasswordPage extends BaseClass
{
	
	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonLoginPasswordPage(WebDriver driver) throws InterruptedException
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
	
	@FindBy(id="ap_password")
	public WebElement inputPassword;
	
	@FindBy(xpath="//input[@id='signInSubmit'][@aria-labelledby='a-autoid-0-announce']")
	public WebElement loginButton;
	
	public AmazonLoginPasswordPage enterPassword(String password) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(inputPassword)).clear();
		inputPassword.sendKeys(password);
		return new AmazonLoginPasswordPage(getDriver());
	}
	
	public AmazonHomePage clickOnLoginButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
		return new AmazonHomePage(getDriver());
	}

}
