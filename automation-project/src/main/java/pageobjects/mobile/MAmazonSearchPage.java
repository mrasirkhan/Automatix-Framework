package pageobjects.mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import io.appium.java_client.android.AndroidDriver;

public class MAmazonSearchPage extends BaseClass
{
	public MAmazonSearchPage(AndroidDriver driver) 
	{
		setAndroidDriver(driver);
		//getAndroidDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(getAndroidDriver(), Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "webdriverwait")));
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getAndroidDriver(), 20), this);
	}


	By productList = By.id("in.amazon.mShop.android.shopping:id/item_title");


	public MAmazonProductDetailPage selectProduct(String productName)
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
		List<WebElement> productList = getAndroidDriver().findElements(By.id("in.amazon.mShop.android.shopping:id/item_title"));
		System.out.println("Avaliable list of products:" + productList.size());
		for (WebElement count : productList) 
		{
			if (count.getText().equals(productName)) 
			{
				count.click();
				break;
			}

		}
		return new MAmazonProductDetailPage(getAndroidDriver());
	}
}