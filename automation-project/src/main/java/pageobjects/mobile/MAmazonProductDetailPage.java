package pageobjects.mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import utilities.MobileUtilities;

public class MAmazonProductDetailPage extends BaseClass
{

	public MAmazonProductDetailPage(AndroidDriver driver) 
	{
		setAndroidDriver(driver);
		//getAndroidDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(getAndroidDriver(), 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getAndroidDriver(), 20), this);
	}


	@FindBy(xpath="//android.view.View[contains(@resource-id,'title_feature_div')]")
	WebElement productName;

	@FindBy(id="add-to-cart-button")
	WebElement addToCartButton;


	public String getProductName()
	{
		return wait.until(ExpectedConditions.visibilityOf(productName)).getAttribute("name");
	}

	public MAmazonProductDetailPage addProductToCart() throws InterruptedException
	{
		MobileUtilities.scrollDown(2,getAndroidDriver());
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
		return new MAmazonProductDetailPage(getAndroidDriver());
	}


}
