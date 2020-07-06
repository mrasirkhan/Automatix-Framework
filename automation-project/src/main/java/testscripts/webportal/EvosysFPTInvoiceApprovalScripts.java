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
import pageobjects.webportal.FusionAssetsPage;
import pageobjects.webportal.FusionHomePage;
import pageobjects.webportal.FusionInvoicePage;
import pageobjects.webportal.FusionLoginPage;
import pageobjects.webportal.RanorexPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;

@Listeners(ListenerClass.class)
public class EvosysFPTInvoiceApprovalScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Finance Process Testing"})
	@Parameters({ "environment", "clientName" })
	public void fusioInvoiceApprovalScripts(String environment, String clientName) throws InterruptedException
	{	
		FusionLoginPage loginPage= new FusionLoginPage(getDriver());
		FusionHomePage homepage = new FusionHomePage(getDriver());
		FusionInvoicePage invoicePage = new FusionInvoicePage(getDriver());
		FusionAssetsPage assetsPage = new FusionAssetsPage(getDriver());
		
		String username = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		
		String approver1Username = ConfigurationData.getUserDetails(environment, clientName, "Approver 1 Username");
		String approver1Password = ConfigurationData.getUserDetails(environment, clientName, "Approver 1 Password");
		
		String approver2Username = ConfigurationData.getUserDetails(environment, clientName, "Approver 2 Username");
		String approver2Password = ConfigurationData.getUserDetails(environment, clientName, "Approver 2 Password");
		
		String homepageTitle = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 1);
		String invoiceLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 2);;
		String invoiceWorkbenchTitle = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 3);
		String createInvoiceLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 4);
		String validateAction = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 5);
		String postToLedgerAction = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 6);
		String createMassAdditionsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 7);
		String AssetsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 8);
		String fixedAssetsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 9);
		String signOutLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 10);
		String invoiceStatus = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 11);
		String invoiceApprovalInitiated = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 12);
		
		
		
		String headerBU = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 1);
		String headerSupplier = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 2);
		String headerInvoiceGroup = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 3);
		String headerInvoiceNumber = invoicePage.getRandomInvoiceNumber();
		String headerAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 5);
		String headerDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 6);
		String lineAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 7);
		String lineDistrubutionCombination = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 8);
		String lineTaxClassification = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 9);
		
		String accountingDate = invoicePage.getCurrentDate();
		String description = "Test approval invoice";
	//	String inserviceDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 15);
	//	String assetCategory = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 16);
	//	String assetKey = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 17);
	//	String units = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 18);
	//	String employeeName ="";
	//	String location = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 20);
	//	String assetBook = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC3_data", 24);
		String invoiceType = "Standard";

	
		loginPage.loginIntoApplication(username, password);
		homepage = homepage.verifyLandingPage(homepageTitle);
		
		homepage = homepage.navigateToPage(invoiceLink);
		homepage.verifyLandingPage(invoiceWorkbenchTitle);
		
		invoicePage = invoicePage.createNewInvoiceTask(createInvoiceLink);
		invoicePage = invoicePage.enterInvoiceHeaders(headerBU, headerSupplier, headerInvoiceGroup, headerInvoiceNumber, headerAmount, invoiceType, description, headerDate);
		invoicePage = invoicePage.selectExpandCollapseButtonForLines();
		//invoicePage = invoicePage.clickOnLineItemCoulumn();
		invoicePage = invoicePage.enterInvoiceLineItems(lineAmount, lineDistrubutionCombination, accountingDate, lineTaxClassification);
		invoicePage = invoicePage.validateInvoice(validateAction);
	
		invoicePage = invoicePage.postToLedgerInvoice(postToLedgerAction);
		
		invoicePage = invoicePage.saveAndCloseInvoice();
		Thread.sleep(5000);
		homepage.signOutFromApplication(signOutLink);
	
		//String headerInvoiceNumber = "Auto_97C7zq";
		loginPage.loginIntoApplication(username, password);
		//homepage = homepage.verifyLandingPage(homepageTitle);
		
		homepage = homepage.navigateToPage(invoiceLink);
		homepage.verifyLandingPage(invoiceWorkbenchTitle);
		
		invoicePage = invoicePage.clickOnSearchInvoiceButton();
		invoicePage = invoicePage.enterInvoiceNumberInSearchFieldAndSearch(headerInvoiceNumber);
		homepage.clickOnLink(headerInvoiceNumber);
		
		invoicePage = invoicePage.verifyInvoiceStatus(invoiceStatus);
		invoicePage = invoicePage.initiateInvoiceApproval();
		invoicePage = invoicePage.verifyInvoiceApprovalStatus(invoiceApprovalInitiated);
		homepage.signOutFromApplication(signOutLink);
		
		
	//	headerInvoiceNumber = "Auto_bSqffV";
		//Approve by approver one
		loginPage.loginIntoApplication(approver1Username, approver1Password);
		homepage = homepage.searchAndApproveInvoiceFromNotification(headerInvoiceNumber);
		Thread.sleep(5000);
		homepage = homepage.signOutFromApplication(signOutLink);
		
		//Approve by approver two
		loginPage.loginIntoApplication(approver2Username, approver2Password);
		homepage = homepage.searchAndApproveInvoiceFromNotification(headerInvoiceNumber);
		Thread.sleep(5000);
		homepage = homepage.signOutFromApplication(signOutLink);
		
		
		//Verify Approved Status after login with Initiator
		loginPage.loginIntoApplication(username, password);
		
		homepage = homepage.navigateToPage(invoiceLink);
		homepage.verifyLandingPage(invoiceWorkbenchTitle);
		
		invoicePage = invoicePage.clickOnSearchInvoiceButton();
		invoicePage = invoicePage.enterInvoiceNumberInSearchFieldAndSearch(headerInvoiceNumber);
		homepage.clickOnLink(headerInvoiceNumber);
		
		invoicePage = invoicePage.verifyInvoiceStatus(invoiceStatus);
		invoicePage = invoicePage.verifyInvoiceApprovalStatus("Workflow approved");
		
		System.out.println("Test Run Successfull");
		
	}
	
	
	

}
