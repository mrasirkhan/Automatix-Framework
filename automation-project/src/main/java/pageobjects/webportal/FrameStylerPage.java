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

public class FrameStylerPage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public FrameStylerPage(WebDriver driver) throws InterruptedException {
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

	@FindBy(xpath = "//button[@class='overlay-btn Btn Btn_swipe-top Btn_swipe-green-to-black']")
	public WebElement recordVideo;
	
	@FindBy(xpath = "//div[@class='dominant']")
	public WebElement dominant;
	
	@FindBy(xpath = "//div[@class='shape']")
	public WebElement shape;

	@FindBy(xpath = "//div[@id='slick-slide00']/a/img")
	public WebElement frame1;
	
	@FindBy(xpath = "//a[@id='view-details-link']")
	public WebElement viewDetailsLink;

	@FindBy(xpath = "//span[@class='close-btn-quickview glyphicon glyphicon-remove']")
	public WebElement closeButton;
	
	@FindBy(xpath = "//a[@class='btn btn-block btn-xl btn-primary find-store-btn']")
	public WebElement bookAnAppointment;
	
	@FindBy(xpath = "//div[@id='slick-slide21']/a/img")
	public WebElement frame2;
	
	@FindBy(xpath = "//*[@id='modal-frame__btn-fav']/span/span/i")
	public WebElement favorate1;
	
	@FindBy(xpath = "//*[@id='header']/div/div[1]/div/div/div[3]/ul/li[2]/a/span")
	public WebElement favorate2;
	
	
	public FrameStylerPage clickRecordVideo() throws InterruptedException {
		recordVideo.click();
		return new FrameStylerPage(getDriver());
	}

	public String getDominantText() throws InterruptedException {
		//JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), rejectOffer);
		// rejectOffer.click();
		return dominant.getText();
	}
	
	public String getShapeText() throws InterruptedException {
		//JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), rejectOffer);
		// rejectOffer.click();
		return shape.getText();
	}

	public FrameStylerPage clickFrame1() throws InterruptedException {
		//Thread.sleep(2000);
		frame1.click();
		// Thread.sleep(2000);
		/*
		 * Actions action = new Actions(getDriver());
		 * action.moveToElement(logIn).build().perform();
		 */
		// JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), logIn);
		return new FrameStylerPage(getDriver());
	}

	public FrameStylerPage clickViewDetails() throws InterruptedException {
		viewDetailsLink.click();
		return new FrameStylerPage(getDriver());
	}
	
	public FrameStylerPage clickBookAnAppointment() throws InterruptedException {
		bookAnAppointment.click();
		return new FrameStylerPage(getDriver());
	}
	
	public FrameStylerPage clickCloseButton() throws InterruptedException {
		closeButton.click();
		return new FrameStylerPage(getDriver());
	}
	
	public FrameStylerPage clickFrame2() throws InterruptedException {
		frame2.click();
		return new FrameStylerPage(getDriver());
	}
	
	public FrameStylerPage clickFavourate1() throws InterruptedException {
		favorate1.click();
		return new FrameStylerPage(getDriver());
	}
	public FrameStylerPage clickFavourate2() throws InterruptedException {
		favorate2.click();
		return new FrameStylerPage(getDriver());
	}
}
