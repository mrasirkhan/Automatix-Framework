package testscripts.mobile;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.mobile.MAmazonHomePage;
import pageobjects.mobile.MAmazonProductDetailPage;
import pageobjects.mobile.MAmazonSearchPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class AppProductBuyScripts extends BaseClass
{
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Mobile","App Amazon Buy"})
	@Parameters({ "environment", "automationType" })
	public void buyProduct(String environment, String automationType) throws InterruptedException
	{
/*		MAmazonHomePage mAmazonHomePage = new MAmazonHomePage(getAndroidDriver());
		mAmazonHomePage = mAmazonHomePage.clickSkipLoginButton();
		MAmazonSearchPage mAmazonSearchPage = mAmazonHomePage.searchProduct("iPhone 7 mobiles 32 GB");
		MAmazonProductDetailPage mAmazonProductDetailPage = mAmazonSearchPage.selectProduct("Apple iPhone 7 (Black, 32GB)");*/
		String userName = ConfigurationData.getUserDetails(environment, automationType, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, automationType, "Automation Password 1");
		MAmazonHomePage mAmazonHomePage = new MAmazonHomePage(getAndroidDriver());
		//mAmazonHomePage = mAmazonHomePage.clickSkipLoginButton();
		mAmazonHomePage = mAmazonHomePage.clickSignInButton();
		mAmazonHomePage = mAmazonHomePage.enterUserId(userName);
		mAmazonHomePage = mAmazonHomePage.clickContinueButton();
		mAmazonHomePage = mAmazonHomePage.enterPassword(password);
		mAmazonHomePage = mAmazonHomePage.clickSignInSubmitButton();
		//"iPhone 7 mobiles 32 GB"
		MAmazonSearchPage mAmazonSearchPage = mAmazonHomePage.searchProduct(ExcelUtilities.getKeyValueFromExcelWithPosition(testcasedataFileName, "HomePage_"+environment.toUpperCase(), "TC2", 2));
		//"Apple iPhone 7 (Black, 32GB)"
		MAmazonProductDetailPage mAmazonProductDetailPage = mAmazonSearchPage.selectProduct(ExcelUtilities.getKeyValueFromExcelWithPosition(testcasedataFileName, "HomePage_"+environment.toUpperCase(), "TC2", 3));
		String actualProductName = mAmazonProductDetailPage.getProductName();
		mAmazonProductDetailPage = mAmazonProductDetailPage.addProductToCart();
/*		String expectedProductName = "Apple iPhone 7 (Black, 32GB)";
		Assert.assertEquals(expectedProductName, actualProductName);*/
	}
	

}
