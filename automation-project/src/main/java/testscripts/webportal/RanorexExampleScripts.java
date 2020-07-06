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
import pageobjects.webportal.RanorexPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class RanorexExampleScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Ranorex"})
	@Parameters({ "environment", "clientName" })
	public void ranorexEg(String environment, String clientName) throws InterruptedException
	{	
		RanorexPage ranorexPage= new RanorexPage(getDriver());
		//String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
	//	String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		//System.out.println(userName);
		
		ranorexPage.enterVIPDetailsInTheForm();
		ranorexPage.clickAddButton();
		ranorexPage.assertVIPCountIncreased();
	
		System.out.println("Test Run Successfull");
		
	}

}
