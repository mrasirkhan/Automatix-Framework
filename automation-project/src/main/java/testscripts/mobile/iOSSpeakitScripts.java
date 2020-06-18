package testscripts.mobile;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.mobile.MAmazonHomePage;
import pageobjects.mobile.MAmazonProductDetailPage;
import pageobjects.mobile.MAmazonSearchPage;
import pageobjects.mobile.SpeakitHomePage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class iOSSpeakitScripts extends BaseClass
{
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Mobile","Rate Restro"})
	@Parameters({ "environment", "clientName" })
	public void rateRestaurant(String environment, String clientName) throws InterruptedException
	{
/*		MAmazonHomePage mAmazonHomePage = new MAmazonHomePage(getAndroidDriver());
		mAmazonHomePage = mAmazonHomePage.clickSkipLoginButton();
		MAmazonSearchPage mAmazonSearchPage = mAmazonHomePage.searchProduct("iPhone 7 mobiles 32 GB");
		MAmazonProductDetailPage mAmazonProductDetailPage = mAmazonSearchPage.selectProduct("Apple iPhone 7 (Black, 32GB)");*/
		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		SpeakitHomePage speakitHomePage = new SpeakitHomePage(getiOSDriver());
		speakitHomePage = speakitHomePage.SignIn(userName, password);
		speakitHomePage = speakitHomePage.RateRestaurant();
		
	}

}
