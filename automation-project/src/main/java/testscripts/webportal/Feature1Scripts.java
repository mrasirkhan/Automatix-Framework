package testscripts.webportal;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.webportal.GooglePage;
import reporting.ListenerClass;
import verify.Verify;

@Listeners(ListenerClass.class)
public class Feature1Scripts extends BaseClass
{
/*	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Firm A","Module 1"})
	@Parameters({ "environment", "automationType" })
	public void automationScriptOne(String environment, String automationType) throws InterruptedException
	{
		GooglePage googlepage = new GooglePage(getDriver());
		String pageTitle = googlepage.getPageTitle();
		Assert.assertEquals("Google", pageTitle);
	}*/
	
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Module 1"})
	@Parameters({ "environment", "automationType" })
	public void automationScriptOne(String environment, String automationType) throws InterruptedException
	{
		if("Test".equals("Test"))
			setAssertMessage("First Test Verification Passed",1);
		Verify.verifyEquals("Test", "Test");
		if("Test".equals("Test2"))
			setAssertMessage("Second Test Verification Failed",2);
		Verify.verifyEquals("Test", "Test2");
		if("Test3".equals("Test3"))
			setAssertMessage("Third Test Verification Passed",3);
		Verify.verifyEquals("Test3", "Test3");
		if("Test".equals("Test4"))
			setAssertMessage("Fourth Test Verification Failed",4);
		Verify.verifyEquals("Test", "Test4");
		Assert.assertFalse(Verify.verifyFailure(), "There were some Failures as shown above.");
		
	}
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Firm A","Module 1"})
	@Parameters({ "environment", "automationType" })
	public void automationScriptTwo(String environment, String automationType) throws InterruptedException
	{
		if("Test".equals("Test"))
			setAssertMessage("First Test Verification Passed",1);
		Verify.verifyEquals("Test", "Test");
		if("Test".equals("Test2"))
			setAssertMessage("Second Test Verification Failed",2);
		Verify.verifyEquals("Test", "Test2");
		if("Test3".equals("Test3"))
			setAssertMessage("Third Test Verification Passed",3);
		Verify.verifyEquals("Test3", "Test3");
		if("Test".equals("Test4"))
			setAssertMessage("Fourth Test Verification Failed",4);
		Verify.verifyEquals("Test", "Test4");
		Assert.assertFalse(Verify.verifyFailure(), "There were some Failures as shown above.");
		
	}

}
