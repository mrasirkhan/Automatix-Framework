package testscripts.webportal;

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
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class WebProductBuyScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Web Amazon Buy"})
	@Parameters({ "environment", "clientName" })
	public void buyProduct(String environment, String clientName) throws InterruptedException
	{
		AmazonHomePage amazonHomePage = new AmazonHomePage(getDriver());
		AmazonLoginEmailPage amazonLoginEmailPage = amazonHomePage.clickOnSignIn();
		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		/*if(environment.equals("Amazon India"))
			amazonHomePage = amazonLoginEmailPage.amazonIndiaLogin(userName, password);
		else if(environment.equals("Amazon US"))
			amazonHomePage = amazonLoginEmailPage.amazonUSLogin(userName, password);*/
		
		amazonHomePage = amazonLoginEmailPage.amazonIndiaLogin(userName, password);
		
/*		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		amazonLoginPage = amazonLoginPage.enterEmailAddress(userName);
		AmazonLoginPasswordPage amazonLoginPasswordPage = amazonLoginPage.clickOnContinueButton();
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		amazonLoginPasswordPage = amazonLoginPasswordPage.enterPassword(password);
		amazonHomePage = amazonLoginPasswordPage.clickOnLoginButton();*/
		amazonHomePage = amazonHomePage.selectCategoryFromList(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2", 1));
		amazonHomePage = amazonHomePage.searchFor(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2", 2));
		AmazonSearchResultPage amazonSearchResultPage = amazonHomePage.searchItem();
		String productDetails = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2", 3);
		boolean productAvailable = amazonSearchResultPage.selectProduct(productDetails,environment);
		if(productAvailable)
		{	
			AmazonProductDetailPage amazonProductDetailPage = new AmazonProductDetailPage(getDriver());
			AmazonCartPage amazonCartPage = amazonProductDetailPage.addToCart();
			AmazonDeliveryAddressPage amazonDeliveryAddressPage = amazonCartPage.clickOnProceedToCheckOutButton();
			AmazonDeliveryOptionsPage amazonDeliveryOptionsPage = amazonDeliveryAddressPage.clickOnDeliverToThisAddressButton();
			//AmazonPaymentDetailsPage amazonPaymentDetailsPage = amazonDeliveryOptionsPage.clickOnContinueButton();
			Thread.sleep(5000);
		}
		else
		{
			Assert.fail(productDetails + " Not Found");
		}
	}

}
