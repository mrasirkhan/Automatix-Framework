package pageobjects.webportal;

import static org.testng.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;

public class FusionHomePage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public FusionHomePage(WebDriver driver) throws InterruptedException {
		/*
		 * this.driver = driver;
		 * this.driver.manage().timeouts().pageLoadTimeout(30,
		 * TimeUnit.SECONDS); wait = new WebDriverWait(this.driver, 15);
		 * //PageFactory.initElements( driver, this);
		 * PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),
		 * this);
		 */
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), 15);
		// PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}

	

	@FindBy(xpath = "//a[@title='Navigator']")
	public WebElement menuNavigator;

	@FindBy(xpath = "//a[contains(@id,'pt1:_UIScmil3u')]")
	public WebElement accountOptions;
	
	@FindBy(id = "Confirm")
	public WebElement confirmSignOut;
	
	@FindBy(xpath = "//a[contains(@title,'Notifications')]")
	public WebElement notificationIcon;
	
	@FindBy(xpath = "//label[text()='Search']//preceding-sibling::input")
	public WebElement notificationSearchInput;
	
	@FindBy(xpath = "//button[@title='Search']")
	public WebElement searchNotificationButton;
	
	@FindBy(xpath = "//a[@title='Approve']")
	List<WebElement> approveLink;
	
	
	
	
	

	public FusionHomePage verifyLandingPage(String title) throws InterruptedException {
		
		String pageTitle = getDriver().getTitle();
		Assert.assertTrue(pageTitle.contains(title));
		
		return new FusionHomePage(getDriver());
	}

	public FusionHomePage navigateToPage(String linktext)throws InterruptedException{
		
		menuNavigator.click();
		try {
			clickOnLink(linktext);
	
		}catch(Exception e) {
			clickOnLink("More...");
			Thread.sleep(5000);
			Assert.assertTrue(getDriver().findElement(By.linkText(linktext)).isDisplayed());
			clickOnLink(linktext);
			Thread.sleep(5000);
		}
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage clickOnLink(String linktext)throws InterruptedException{
		
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText(linktext))));
			getDriver().findElement(By.linkText(linktext)).click();
			
	
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage signOutFromApplication(String signOutLink)throws InterruptedException{
		
		accountOptions.click();
		FusionHomePage homepage = clickOnLink(signOutLink);
		homepage = homepage.clickOnConfirmSignOut();
			
	
		return homepage;
	}
	
	public FusionHomePage clickOnConfirmSignOut()throws InterruptedException{
		
		confirmSignOut.click();
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage clickOnNotificationIcon()throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(notificationIcon));
		notificationIcon.click();
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage enterInvoiceNumberInNotificationSearchandSearch(String invoiceNumber)throws InterruptedException{
		
		notificationSearchInput.sendKeys(invoiceNumber);
		searchNotificationButton.click();
		Thread.sleep(3000);
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage clickOnApproveLink()throws InterruptedException{
		
		approveLink.get(0).click();
		return new FusionHomePage(getDriver());
	}
	
	public FusionHomePage searchAndApproveInvoiceFromNotification(String invoiceNumber)throws InterruptedException{
		
		FusionHomePage homepage = clickOnNotificationIcon();
		homepage = homepage.enterInvoiceNumberInNotificationSearchandSearch(invoiceNumber);
		homepage = homepage.clickOnApproveLink();
		
		return homepage;
	}
	
	
	
	
	
	
	
}
