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
public class AppProductSearchScripts extends BaseClass
{
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Mobile","App Amazon Buy"})
	@Parameters({ "environment", "clientName" })
	public void searchProduct(String environment, String clientName) throws InterruptedException
	{
		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		MAmazonHomePage mAmazonHomePage = new MAmazonHomePage(getAndroidDriver());
		//mAmazonHomePage = mAmazonHomePage.clickSkipLoginButton();
		mAmazonHomePage = mAmazonHomePage.clickSignInButton();
		mAmazonHomePage = mAmazonHomePage.enterUserId(userName);
		mAmazonHomePage = mAmazonHomePage.clickContinueButton();
		mAmazonHomePage = mAmazonHomePage.enterPassword(password);
		mAmazonHomePage = mAmazonHomePage.clickSignInSubmitButton();
		//"iPhone 7 mobiles 32 GB"
		MAmazonSearchPage mAmazonSearchPage = mAmazonHomePage.searchProduct(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 2));
		//"Apple iPhone 7 (Black, 32GB)"
		MAmazonProductDetailPage mAmazonProductDetailPage = mAmazonSearchPage.selectProduct(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 3));
		String actualProductName = mAmazonProductDetailPage.getProductName();
		String expectedProductName = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 3);
		//Assert.assertEquals(actualProductName, expectedProductName);
	}

}
