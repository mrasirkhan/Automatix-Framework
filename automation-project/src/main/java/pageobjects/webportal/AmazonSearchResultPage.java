package pageobjects.webportal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;
import utilities.WindowHandleSupport;

public class AmazonSearchResultPage extends BaseClass
{
	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonSearchResultPage(WebDriver driver) throws InterruptedException
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

	public boolean selectProduct(String productName,String environment) throws InterruptedException
	{
		boolean productAvailableflag = false;
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[@data-attribute='"+productName+"']")));
			WebElement element = getDriver().findElement(By.xpath("//h2[@data-attribute='"+productName+"']"));
			JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), element);
			Thread.sleep(2000);
			if(environment.equals("Amazon India Prod Env"))
				setDriver(WindowHandleSupport.getRequiredWindowDriverWithPageTitle(getDriver(), productName));
			productAvailableflag = true;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			productAvailableflag = false;
		}
		new AmazonProductDetailPage(getDriver());
		return productAvailableflag;
	}

}
