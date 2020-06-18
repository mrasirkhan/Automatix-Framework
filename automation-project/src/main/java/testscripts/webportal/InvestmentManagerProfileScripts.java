package testscripts.webportal;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import browsersetup.BaseClass;
import pageobjects.webportal.LoginPage;

@Listeners(ListenerClass.class)
public class InvestmentManagerProfileScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Sanity","Firm A","Role Access"})
	@Parameters({ "environment", "clientName" })
	public void verifyInvestmentManagerProfile(String environment, String clientName) throws InterruptedException
	{
		LoginPage loginpage = new LoginPage(getDriver());
		String userName = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		loginpage.EnterLoginID(userName);
		loginpage.EnterPassword(ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1"));
		/*HomePage homepage = loginpage.ClickLoginButton();
		//homepage = homepage.searchBySpecificText("Client Name", "All", "Jane");
		homepage = homepage.searchBySpecificText(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2", 1), 
				 								 ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2", 2));
		homepage = homepage.clickSearchButton();
		homepage = homepage.openClientDetails(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 3));
		SummaryPage summarypage =  homepage.openSummaryTab();
		if(summarypage.getAccountsTableHeaderOnSummaryPage().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 4)))
			setAssertMessage("User with User ID as "+userName+" has access to Account Table",1);
		Verify.verifyEquals(summarypage.getAccountsTableHeaderOnSummaryPage(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 4));
		summarypage = summarypage.selectAnAccountUnderAccounts(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 5));
		ActivityPage activitypage = homepage.openActivityTabAndClickOn(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 6));
		if(activitypage.getAboutActivityHeader().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 7)))
			setAssertMessage("User with User ID as "+userName+" has access to About Activity Page",2);
		Verify.verifyEquals(activitypage.getAboutActivityHeader(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 7));
		PropertiesPage propertiespage = homepage.openPropertiesTab();
		if(propertiespage.getAccountPropertiesHeader().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 8)))
			setAssertMessage("User with User ID as "+userName+" has access to Account Properties Page",3);
		Verify.verifyEquals(propertiespage.getAccountPropertiesHeader(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 8));
		activitypage = homepage.openActivityTabAndClickOn(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 9));
		if(activitypage.getCashProjectionHeader().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 10)))
			setAssertMessage("User with User ID as "+userName+" has access to Cash Projection Page",4);
		Verify.verifyEquals(activitypage.getCashProjectionHeader(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 10));
		activitypage = homepage.openActivityTabAndClickOn(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 11));
		if(activitypage.getTransactionInquiryHeader().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 12)))
			setAssertMessage("User with User ID as "+userName+" has access to Transaction Enquiry Page",5);
		Verify.verifyEquals(activitypage.getTransactionInquiryHeader(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 12));
		homepage = activitypage.closeTransactionInquiryPage();
		propertiespage = homepage.openPropertiesTab();
		propertiespage = propertiespage.clickOnAnAccountProperty(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 13));
		if(propertiespage.getSubHeaderOfAccountPropertiesPage().equals(ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 14)))
			setAssertMessage("User with User ID as "+userName+" has access to Strategy Page",6);
		Verify.verifyEquals(propertiespage.getSubHeaderOfAccountPropertiesPage(), ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1", 14));
		Assert.assertFalse(Verify.verifyFailure(), "There were some Failures as shown above.");*/
	}

}
