package testscripts.webportal;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.webportal.GooglePage;
import pageobjects.webportal.GoogleSearch;
import reporting.ListenerClass;

@Listeners(ListenerClass.class)
public class Feature2Scripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Firm A","Module 1"})
	@Parameters({ "environment", "automationType" })
	public void automationScriptTwo(String environment, String automationType) throws InterruptedException
	{
		GooglePage googlepage = new GooglePage(getDriver());
		googlepage = googlepage.SettingLinkClick();
		GoogleSearch googlesearch = googlepage.searchsettingLinkClick();
		googlesearch = googlesearch.SafeSearchClick();
		googlesearch = googlesearch.instanceResultClick();
		googlesearch = googlesearch.ResultOpen();
		googlesearch = googlesearch.Save();
		Assert.assertTrue(googlesearch.acceptAlertMessage(), "Setting Not Saved");
	}

}
