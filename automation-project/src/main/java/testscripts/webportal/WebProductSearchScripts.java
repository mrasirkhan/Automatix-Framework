package testscripts.webportal;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.webportal.AmazonHomePage;
import pageobjects.webportal.AmazonLoginEmailPage;
import pageobjects.webportal.AmazonLoginPasswordPage;
import pageobjects.webportal.AmazonSearchResultPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class WebProductSearchScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Web Amazon Buy"})
	@Parameters({ "environment", "automationType", "browser" })
	public void searchProduct(String environment, String automationType, String browser) throws InterruptedException
	{
		AmazonHomePage amazonHomePage = new AmazonHomePage(getDriver());
		AmazonLoginEmailPage amazonLoginEmailPage = amazonHomePage.clickOnSignIn();
		String userName = ConfigurationData.getUserDetails(environment, automationType, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, automationType, "Automation Password 1");
/*		if(environment.equals("Amazon India"))
			amazonHomePage = amazonLoginEmailPage.amazonIndiaLogin(userName, password);
		else if(environment.equals("Amazon US"))
			amazonHomePage = amazonLoginEmailPage.amazonUSLogin(userName, password);*/
		amazonHomePage = amazonLoginEmailPage.amazonIndiaLogin(userName, password);
/*		String userName = ConfigurationData.getUserDetails(environment, automationType, "Automation Username 1");
		amazonLoginPage = amazonLoginPage.enterEmailAddress(userName);
		AmazonLoginPasswordPage amazonLoginPasswordPage = amazonLoginPage.clickOnContinueButton();
		String password = ConfigurationData.getUserDetails(environment, automationType, "Automation Password 1");
		amazonLoginPasswordPage = amazonLoginPasswordPage.enterPassword(password);
		amazonHomePage = amazonLoginPasswordPage.clickOnLoginButton();*/
		
		
		amazonHomePage = amazonHomePage.selectCategoryFromList(ExcelUtilities.getKeyValueFromExcelWithPosition(utilities.ReadProperties.getProperty(filenamesPropertie, location, "Testcasesdata"), "HomePage_"+environment.toUpperCase(), "TC1", 1));
		amazonHomePage = amazonHomePage.searchFor(ExcelUtilities.getKeyValueFromExcelWithPosition(utilities.ReadProperties.getProperty(filenamesPropertie, location, "Testcasesdata"), "HomePage_"+environment.toUpperCase(), "TC1", 2));
		AmazonSearchResultPage amazonSearchResultPage = amazonHomePage.searchItem();
		String productDetails = ExcelUtilities.getKeyValueFromExcelWithPosition(utilities.ReadProperties.getProperty(filenamesPropertie, location, "Testcasesdata"), "HomePage_"+environment.toUpperCase(), "TC1", 3);
		boolean productAvailable = amazonSearchResultPage.selectProduct(productDetails,environment);
		Assert.assertEquals(productAvailable, true, productDetails + " not Available");
	}

}
