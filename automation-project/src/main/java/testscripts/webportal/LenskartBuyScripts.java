package testscripts.webportal;

import java.awt.Window;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import browsersetup.BaseClass;
import pageobjects.webportal.AmazonCartPage;
import pageobjects.webportal.AmazonDeliveryAddressPage;
import pageobjects.webportal.AmazonDeliveryOptionsPage;
import pageobjects.webportal.AmazonHomePage;
import pageobjects.webportal.AmazonLoginEmailPage;
import pageobjects.webportal.AmazonLoginPasswordPage;
import pageobjects.webportal.AmazonProductDetailPage;
import pageobjects.webportal.AmazonSearchResultPage;
import pageobjects.webportal.LenskartPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class LenskartBuyScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Lenskart"})
	@Parameters({ "environment", "clientName" })
	public void buyFrame(String environment, String clientName) throws InterruptedException
	{
		LenskartPage lenskartPage= new LenskartPage(getDriver());
		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		//System.out.println(userName);
		try {
			Thread.sleep(4000);
			lenskartPage.clickRejectOffer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lenskartPage.clickLogIn();
		lenskartPage.clickMyAccount();
		lenskartPage.enterEmailPhone(userName);
		lenskartPage.clickProceed();
		lenskartPage.enterPassword(password);
		lenskartPage.ClickLoginButton();
		Thread.sleep(4000);
		//System.out.println("............................Trying to click on view range.................");
		
		/*JavascriptExecutor js = (JavascriptExecutor)getDriver();
    	js.executeAsyncScript("window.scrollBy(0,900)", "");*/
    	Thread.sleep(4000);
    	//lenskartPage.clickLogIn();
		lenskartPage.clickViewRange1();
		//System.out.println("....................clicked on view range.........................");
		
		Thread.sleep(4000);
		lenskartPage.clickImage1();
		Thread.sleep(4000);
		lenskartPage.clickImage();
		Thread.sleep(4000);
		getDriver().navigate().back();
		Thread.sleep(4000);
		//getDriver().navigate().back();
		getDriver().navigate().to("http://www.lenskart.com/");
		Thread.sleep(4000);
		lenskartPage.clickViewRange2();
		Thread.sleep(4000);
		lenskartPage.clickImage1();
		Thread.sleep(4000);
		lenskartPage.clickImage();
		Thread.sleep(4000);
		lenskartPage.clickBuyNow();
		Thread.sleep(4000);
		lenskartPage.clickProceedToCheckout();
		Thread.sleep(4000);
		try {
			lenskartPage.clickCloseAdd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lenskartPage.enterFirstName("Sunil");
		//Thread.sleep(2000);
		lenskartPage.enterLastName("Yadav");
		lenskartPage.enterMobile("9820381951");
		lenskartPage.clickMale();
		lenskartPage.enterPostCode("400710");
		lenskartPage.enterAddress1("Mahape");
		lenskartPage.enterDistrict("Navi Mumbai");
		lenskartPage.selectState("Maharashtra");
		
		lenskartPage.clickContinue();
		Thread.sleep(4000);
		
		
	}

}
