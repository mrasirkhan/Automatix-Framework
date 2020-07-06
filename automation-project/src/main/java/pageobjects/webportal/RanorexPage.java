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

public class RanorexPage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public RanorexPage(WebDriver driver) throws InterruptedException {
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

	

	@FindBy(id = "FirstName")
	public WebElement fnameTextBox;
	
	@FindBy(id = "LastName")
	public WebElement lnameTextBox;
	
	@FindBy(xpath = "//input[@id='Gender' and @value='male']")
	public WebElement genderCheckBox;
	
	@FindBy(id = "Category")
	public WebElement category;
	
	@FindBy(id = "Add")
	public WebElement addButton;
	
	@FindBy(id = "count")
	public WebElement counter;
	
	String vipCount;
	

	public RanorexPage enterVIPDetailsInTheForm() throws InterruptedException {
		
		Thread.sleep(3000);
		vipCount = counter.getText();
		
		fnameTextBox.sendKeys("Vaibhav");
		lnameTextBox.sendKeys("Badhe");
		
		genderCheckBox.click();
		
		Select option = new Select(category);
		option.selectByVisibleText("Music");
		
		return new RanorexPage(getDriver());
	}

	public RanorexPage clickAddButton ()throws InterruptedException{
		addButton.click();
		return new RanorexPage(getDriver());
	}
	
	public RanorexPage assertVIPCountIncreased()throws InterruptedException{
		
		String vipCountAfterUpdate = counter.getText();
		Assert.assertTrue(!vipCountAfterUpdate.equals(vipCount));
		System.out.println("VIP Counter After update: "+vipCountAfterUpdate);
		return new RanorexPage(getDriver());
	}
}
