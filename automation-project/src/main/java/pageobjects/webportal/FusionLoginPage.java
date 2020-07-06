package pageobjects.webportal;

import static org.testng.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

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

public class FusionLoginPage extends BaseClass {


	public FusionLoginPage(WebDriver driver) throws InterruptedException {
		
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), 15);
		// PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}

	
	@FindBy(id = "userid")
	public WebElement userName;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	public WebElement signInButton;
	

	public FusionLoginPage loginIntoApplication(String username, String passwd) throws InterruptedException {
		
		Thread.sleep(3000);

		userName.sendKeys(username);
		
		password.sendKeys(passwd);
		
		signInButton.click();
		
		return new FusionLoginPage(getDriver());
	}

}
