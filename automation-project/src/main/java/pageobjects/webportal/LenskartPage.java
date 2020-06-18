package pageobjects.webportal;

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

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;

public class LenskartPage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public LenskartPage(WebDriver driver) throws InterruptedException {
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

	@FindBy(xpath = "//a[contains(@id,'close-inside')][@data-click='close']")
	// @FindBy(xpath="//*[@id='bx-element-703436-5Hfc75J']/button")
	public WebElement rejectOffer;

	// @FindBy(xpath="//a[@id='login-dropdown-header']")
	// public WebElement logIn;

	@FindBy(id = "headerLogin")
	public WebElement logIn;

	@FindBy(xpath = "//a[@title='My Account']")
	public WebElement myAccount;

	@FindBy(id = "email-phone")
	public WebElement emailPhone;

	@FindBy(id = "proceed")
	public WebElement proceed;

	@FindBy(id = "pass")
	public WebElement password;

	@FindBy(id = "send2")
	public WebElement logInButton;
	
	 @FindBy(xpath ="//*[@id='mainpagecontainer']/div/div/div[4]/div[1]/section/center/div/a[1]/div")
	 public WebElement viewRange1;

//	 @FindBy(xpath = "//div[contains(text(),'VIEW RANGE')]")
//	 public WebElement viewRange1;

	/*@FindBy(xpath = "//*[@id='mainpagecontainer']/div/div/div[4]/div[2]/section/center/div/a[2]/div")
	public WebElement viewRange1;*/

	// *[@id="mainpagecontainer"]/div/div/div[4]/div[2]/section/center/div/a[2]/div

	// a[@href='url']
	@FindBy(xpath = "//*[@id='grid-container']/div[1]/div/div[1]/div/div/div[1]/div[1]/div/div[2]/a/img")
	public WebElement image1;

	@FindBy(xpath = "//*[@id='ditto-thumbnail']/img[1]")
	public WebElement image;

	@FindBy(xpath = "//*[@id='mainpagecontainer']/div/div/div[4]/div[3]/section/center/div/a/div")
	public WebElement viewRange2;

	@FindBy(xpath = "")
	public WebElement image2;
	
	@FindBy(xpath = "//button[@unbxdattr='AddToCart']")
	public WebElement buyNow;
	
	@FindBy(xpath = "//i[@data-reactid='.0.1.0.0.0.0.2.1.3.1.0.0.1']")
	public WebElement proceed_To_Checkout;
	
	@FindBy(xpath = "//input[@name='firstname'][@class='form-control']")
	public WebElement firstName;
	
	@FindBy(xpath = "//input[@name='lastname'][@class='form-control']")
	public WebElement lastname;
	
	@FindBy(xpath = "//input[@name='mobile'][@class='form-control']")
	public WebElement mobile;

	@FindBy(xpath = "//input[@value='male']")
	public WebElement male;
	
	@FindBy(xpath = "//input[@name='zipcode'][@class='form-control']")
	public WebElement zipcode;
	
	@FindBy(xpath = "//textarea[@name='addresslineone'][@class='form-control']")
	public WebElement addresslineone;
	
	@FindBy(xpath = "//input[@name='district'][@class='form-control']")
	public WebElement district;
	
	@FindBy(xpath = "//select[@name='state'][@class='form-control']")
	public WebElement state;
	
	@FindBy(xpath = "//div[@class='continue-step']/button[@type='submit']")
	public WebElement Continue;
	
	@FindBy(xpath = "//span[@class='close']")
	public WebElement CloseAdd;

	public LenskartPage clickRejectOffer() throws InterruptedException {
		JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), rejectOffer);
		// rejectOffer.click();
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickLogIn() throws InterruptedException {
		Thread.sleep(3000);
		logIn.click();
		// Thread.sleep(2000);
		/*
		 * Actions action = new Actions(getDriver());
		 * action.moveToElement(logIn).build().perform();
		 */
		// JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), logIn);
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickMyAccount() throws InterruptedException {
		/*
		 * if(myAccount.isDisplayed()) myAccount.click();
		 */
		JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), myAccount);
		return new LenskartPage(getDriver());
	}

	public LenskartPage enterEmailPhone(String Username) throws InterruptedException {
		emailPhone.sendKeys(Username);
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickProceed() throws InterruptedException {
		proceed.click();
		return new LenskartPage(getDriver());
	}

	public LenskartPage enterPassword(String Password) throws InterruptedException {
		password.sendKeys(Password);
		return new LenskartPage(getDriver());
	}

	public LenskartPage ClickLoginButton() throws InterruptedException {
		logInButton.click();
		return new LenskartPage(getDriver());
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public LenskartPage clickViewRange1() throws InterruptedException {
		viewRange1.click();
		// JavaScriptExecutor.clickElementUsingJavaScript(getDriver(),
		// viewRange1);
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickImage1() throws InterruptedException {
		image1.click();
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickImage() throws InterruptedException {
		image.click();
		return new LenskartPage(getDriver());
	}

	public LenskartPage clickViewRange2() throws InterruptedException {
		viewRange2.click();
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage clickBuyNow() throws InterruptedException {
		buyNow.click();
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage clickProceedToCheckout() throws InterruptedException {
		proceed_To_Checkout.click();
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage enterFirstName(String name) throws InterruptedException {
		firstName.sendKeys(name);
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage enterLastName(String name) throws InterruptedException {
		lastname.sendKeys(name);
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage enterMobile(String name) throws InterruptedException {
		mobile.sendKeys(name);
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage clickMale() throws InterruptedException {
		male.click();
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage enterPostCode(String code) throws InterruptedException {
		zipcode.sendKeys(code);
		return new LenskartPage(getDriver());
	}
	public LenskartPage enterAddress1(String add) throws InterruptedException {
		addresslineone.sendKeys(add);
		return new LenskartPage(getDriver());
	}
	public LenskartPage enterDistrict(String name) throws InterruptedException {
		district.sendKeys(name);
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage selectState(String name) throws InterruptedException {
		Select s1= new Select(state);
		s1.selectByVisibleText(name);
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage clickContinue() throws InterruptedException {
		Continue.click();
		return new LenskartPage(getDriver());
	}
	
	public LenskartPage clickCloseAdd() throws InterruptedException {
		CloseAdd.click();
		return new LenskartPage(getDriver());
	}
}
