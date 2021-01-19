package testscripts.webservices;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import reporting.ListenerClass;
import utilities.WebServicesUtilities;

@Listeners(ListenerClass.class)
public class PortalWebServicesScripts extends BaseClass
{

	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Services","Amazon Portal"})
	@Parameters({ "environment", "automationType" })
	public void verifyAmazonWebServiceURL(String environment, String automationType) throws InterruptedException, ClientProtocolException, IOException
	{
		int statusCode = WebServicesUtilities.getStatusCode(utilities.ReadProperties.getProperty(UIPropertie, location,"webServicesEnvironment1"));
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Services","Amazon Portal"})
	@Parameters({ "environment", "automationType" })
	public void verifyNonAmazonWebServiceURL(String environment, String automationType) throws InterruptedException, ClientProtocolException, IOException
	{
		int statusCode = WebServicesUtilities.getStatusCode(utilities.ReadProperties.getProperty(UIPropertie, location,"webServicesEnvironment1"));
		Assert.assertEquals(statusCode, 403);
	}
	
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Services","Amazon Portal"})
	@Parameters({ "environment", "automationType" })
	public void verifyAmazonWebServiceURLNegativeScenario(String environment, String automationType) throws InterruptedException, ClientProtocolException, IOException
	{
		int statusCode = WebServicesUtilities.getStatusCode(utilities.ReadProperties.getProperty(UIPropertie, location,"webServicesEnvironment1"));
		Assert.assertEquals(statusCode, 404);
	}
}
