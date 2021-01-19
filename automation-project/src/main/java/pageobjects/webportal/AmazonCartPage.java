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

public class AmazonCartPage extends BaseClass
{
	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonCartPage(WebDriver driver) throws InterruptedException
	{
/*		this.driver = driver;
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(this.driver, 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);*/
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "pageLoadTimeout_PO")), TimeUnit.SECONDS);
		//JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "webdriverwait")));
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}

	@FindBy(id="hlb-ptc-btn-native")
	public WebElement proceedToCheckOutButton;
	
	public AmazonDeliveryAddressPage clickOnProceedToCheckOutButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutButton)).click();
		return new AmazonDeliveryAddressPage(getDriver());
	}

}
