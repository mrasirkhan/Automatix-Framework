package pageobjects.webportal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;

public class AmazonHomePage extends BaseClass
{

	/*	WebDriver driver;
	WebDriverWait wait;*/

	public AmazonHomePage(WebDriver driver) throws InterruptedException
	{
/*		this.driver = driver;
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(this.driver, 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);*/
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), 15);
		//PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}

	@FindBy(id="searchDropdownBox")
	public WebElement mainSelectList;

	@FindBy(id="twotabsearchtextbox")
	public WebElement searchTextBox;

	@FindBy(xpath="//input[@type='submit'][@value='Go']")
	public WebElement mainSearchButton;
	
	@FindBy(xpath="//a[@data-nav-role='signin']/span[2]")
	public WebElement signIn;


	public AmazonHomePage selectCategoryFromList(String categoryName) throws InterruptedException
	{
		String selectValue = null;
		switch (categoryName.toLowerCase()) 
		{
		case "all categories":  selectValue = "aps";
		break;
		case "amazon fashion":  selectValue = "fashion";
		break;
		case "amazon global store":  selectValue = "amazon-global-store";
		break;
		case "amazon pantry":  selectValue = "pantry";
		break;
		case "appliances":  selectValue = "appliances";
		break;
		case "apps & games":  selectValue = "mobile-apps";
		break;
		case "baby":  selectValue = "baby";
		break;
		case "beauty":  selectValue = "beauty";
		break;
		case "books":  selectValue = "stripbooks";
		break;
		case "car & motorbike":  selectValue = "automotive";
		break;
		case "clothing & accessories":  selectValue = "apparel";
		break;
		case "collectibles":  selectValue = "collectibles";
		break;
		case "computers & accessories":  selectValue = "computers";
		break;
		case "electronics":  selectValue = "electronics";
		break;
        default: selectValue = "aps";
        break;
		}
		Select mainList = new Select(mainSelectList);
		mainList.selectByValue("search-alias="+selectValue);
		return new AmazonHomePage(getDriver());
	}


	public AmazonHomePage searchFor(String searchText) throws InterruptedException
	{
		searchTextBox.clear();
		searchTextBox.sendKeys(searchText);
		return new AmazonHomePage(getDriver()); 
	}

	public AmazonSearchResultPage searchItem() throws InterruptedException
	{
		mainSearchButton.click();
		return new AmazonSearchResultPage(getDriver());
	}
	
	public AmazonLoginEmailPage clickOnSignIn() throws InterruptedException
	{
		//wait.until(ExpectedConditions.visibilityOf(signIn)).click();
		JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), signIn);
		return new AmazonLoginEmailPage(getDriver());
	}

	public String getPageTitle()
	{
		return getDriver().getTitle();
	}

}
