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

public class AmazonPaymentDetailsPage extends BaseClass
{
	
	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonPaymentDetailsPage(WebDriver driver) throws InterruptedException
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

	@FindBy(xpath="//input[@id='continue-top'][@value='Continue']")
	public WebElement continueButton;
	
	public AmazonPaymentGatewayPage clickOnContinueButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
		return new AmazonPaymentGatewayPage(getDriver());
	}

}
