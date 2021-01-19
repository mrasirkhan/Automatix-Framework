package pageobjects.webportal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersetup.BaseClass;

public class GooglePage extends BaseClass {

	WebDriver driver;
    WebDriverWait wait;

	public GooglePage(WebDriver driver)
	{
        this.driver = driver;
        this.driver.manage().timeouts().pageLoadTimeout(Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "pageLoadTimeout_PO")), TimeUnit.SECONDS);
        wait = new WebDriverWait(this.driver, Long.parseLong(utilities.ReadProperties.getProperty(configPropertie, location, "webdriverwait")));
		//PageFactory.initElements( driver, this);
		 PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	@FindBy(id="fsettl")
	public WebElement settingLink;
	
	@FindBy(linkText="Advanced search")
	public WebElement advancesearchLink;
	
	
	@FindBy(linkText="Search settings")
	public WebElement searchsettingLink;
	
	

	public GooglePage SettingLinkClick()
	{
		settingLink.click();
		return new GooglePage(driver);
	}
	
	
	public void advancesearchLinkClick()
	{
		advancesearchLink.click();
	}
	
	public GoogleSearch searchsettingLinkClick()
	{
		searchsettingLink.click();
		return new GoogleSearch(driver);
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}

}
