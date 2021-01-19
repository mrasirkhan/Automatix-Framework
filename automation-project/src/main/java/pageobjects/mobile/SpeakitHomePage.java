package pageobjects.mobile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import io.appium.java_client.ios.IOSDriver;

public class SpeakitHomePage extends BaseClass
{
	private Logger log = Logger.getLogger(SpeakitHomePage.class.getName());

	public SpeakitHomePage(IOSDriver driver) 
	{
		setiOSDriver(driver);
		//getAndroidDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(getiOSDriver(), Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "webdriverwait")));
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getiOSDriver(), 20), this);
	}

	@FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[3]/XCUIElementTypeTextField[1]")
	public WebElement mobileno;
	
	@FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[4]/XCUIElementTypeSecureTextField[1]")
	public WebElement password;

	@FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]")
	public WebElement signInButton;
	
	@FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[2]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeButton[1]")
	public WebElement SelectRestaurant;

	@FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")
	public WebElement Next;
	
	@FindBy(xpath = " //XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]")
	public WebElement Exit;


	public SpeakitHomePage SignIn(String user, String userPassword) throws InterruptedException 
	{
		Thread.sleep(4000);
		mobileno.sendKeys(user);
		password.sendKeys(userPassword);
		signInButton.click();
		Thread.sleep(9000);
		try 
		{
			getiOSDriver().switchTo().alert().accept();
		} 
		catch (Exception e) 
		{
			System.out.println("No Alert Visible");
		}
		
		return new SpeakitHomePage(getiOSDriver());
		
	}
	
	public SpeakitHomePage RateRestaurant() throws InterruptedException 
	{
		Thread.sleep(5000);
		SelectRestaurant.click();
		Thread.sleep(3000);
		Next.click();
		Next.click();
		Next.click();
		Next.click();
		Next.click();
		Next.click();
		Next.click();
		Next.click();
		Exit.click();
		
		return new SpeakitHomePage(getiOSDriver());
	}
}
