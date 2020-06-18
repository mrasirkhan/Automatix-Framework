package pageobjects.mobile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.webportal.AmazonDeliveryAddressPage;

public class MAmazonHomePage extends BaseClass
{

	//AndroidDriver driver;
	//AndroidDriver<WebElement> driver;

	public MAmazonHomePage(AndroidDriver driver) 
	{
		setAndroidDriver(driver);
		//getAndroidDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(getAndroidDriver(), 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getAndroidDriver(), 20), this);
	}


	@FindBy(id="in.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement searchTextField;
	
	@FindBy(id="in.amazon.mShop.android.shopping:id/skip_sign_in_button")
	WebElement skipLoginButton;
	
	@FindBy(id="in.amazon.mShop.android.shopping:id/sign_in_button")
	WebElement singInButton;
	
	@FindBy(id="ap_email_login")
	WebElement emailLoginTextBox;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(id="ap_password")
	WebElement passwordTextBox;
	
	@FindBy(id="signInSubmit")
	WebElement singInSubmitButton;
	
	By productSuggestionWebElementList = By.id("in.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text");
	
	public MAmazonHomePage clickSkipLoginButton() throws InterruptedException
	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(skipLoginButton)).click();
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}
	
	public MAmazonHomePage clickSignInButton() throws InterruptedException
	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(singInButton)).click();
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}
	
	public MAmazonHomePage enterUserId(String userID) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(emailLoginTextBox)).clear();
		emailLoginTextBox.sendKeys(userID);
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}
	
	public MAmazonHomePage clickContinueButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}

	public MAmazonHomePage enterPassword(String password) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox)).clear();
		passwordTextBox.sendKeys(password);
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}
	
	public MAmazonHomePage clickSignInSubmitButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(singInSubmitButton)).click();
		//skipLoginButton.click();
		return new MAmazonHomePage(getAndroidDriver());
	}

	public MAmazonSearchPage searchProduct(String productName)
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchTextField)).click();
		new MAmazonHomePage(getAndroidDriver());
		wait.until(ExpectedConditions.elementToBeClickable(searchTextField)).sendKeys(productName);
		new MAmazonHomePage(getAndroidDriver());
		//wait.until(ExpectedConditions.elementToBeClickable(searchTextField)).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productSuggestionWebElementList));
		List<WebElement> productSuggestionList = getAndroidDriver().findElements(productSuggestionWebElementList);
		productSuggestionList.get(0).click();
		return new MAmazonSearchPage(getAndroidDriver());
	}


}
