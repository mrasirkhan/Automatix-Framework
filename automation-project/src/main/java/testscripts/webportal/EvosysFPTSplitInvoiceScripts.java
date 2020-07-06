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
public class EvosysFPTSplitInvoiceScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Finance Process Testing"})
	@Parameters({ "environment", "clientName" })
	public void fusionSplitInvoiceScripts(String environment, String clientName) throws InterruptedException
	{	
		FusionLoginPage loginPage= new FusionLoginPage(getDriver());
		FusionHomePage homepage = new FusionHomePage(getDriver());
		FusionInvoicePage invoicePage = new FusionInvoicePage(getDriver());
		FusionAssetsPage assetsPage = new FusionAssetsPage(getDriver());
		
		String username = ConfigurationData.getUserDetails(environment, clientName, "Automation Username 1");
		String password = ConfigurationData.getUserDetails(environment, clientName, "Automation Password 1");
		
		String homepageTitle = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 1);
		String invoiceLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 2);;
		String invoiceWorkbenchTitle = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 3);
		String createInvoiceLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 4);
		String validateAction = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 5);
		String postToLedgerAction = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 6);
		String createMassAdditionsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 7);
		String AssetsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 8);
		String fixedAssetsLink = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_parameter", 9);
		
		
		
		String headerBU = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 1);
		String headerSupplier = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 2);
		String headerInvoiceGroup = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 3);
		String headerInvoiceNumber = invoicePage.getRandomInvoiceNumber();
		String headerAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 5);
		String headerDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 6);
		String lineAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 7);
		String lineDistrubutionCombination = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 8);
		String lineTaxClassification = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 9);
		
		String accountingDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 13);
		String description = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 14);
		String inserviceDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 15);
		String assetCategory = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 16);
		String assetKey = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 17);
		String units = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 18);
		String employeeName = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 19);
		String location = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 20);
		String assetBook = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC1_data", 24);
		String invoiceType = "Standard";

		
		loginPage.loginIntoApplication(username, password);
		homepage = homepage.verifyLandingPage(homepageTitle);
		
		homepage = homepage.navigateToPage(invoiceLink);
		homepage.verifyLandingPage(invoiceWorkbenchTitle);
		
		invoicePage = invoicePage.createNewInvoiceTask(createInvoiceLink);
		invoicePage = invoicePage.enterInvoiceHeaders(headerBU, headerSupplier, headerInvoiceGroup, headerInvoiceNumber, headerAmount, invoiceType, description, headerDate);
		invoicePage = invoicePage.enterRequesterHeader(employeeName);
		invoicePage = invoicePage.selectExpandCollapseButtonForLines();
		//invoicePage = invoicePage.clickOnLineItemCoulumn();
		invoicePage = invoicePage.enterInvoiceLineItems(lineAmount, lineDistrubutionCombination, accountingDate, lineTaxClassification);
		invoicePage = invoicePage.validateInvoice(validateAction);
	
		invoicePage = invoicePage.postToLedgerInvoice(postToLedgerAction);
		
		invoicePage = invoicePage.saveAndCloseInvoice();
		Thread.sleep(5000);
		//homepage.verifyLandingPage(invoiceWorkbenchTitle);
	
		//Part 2 FA 02
		
		invoicePage = invoicePage.createNewInvoiceTask(createMassAdditionsLink);
		invoicePage = invoicePage.submitMassAddition(assetBook);
		
		invoicePage = invoicePage.navigateToHomepage();
		Thread.sleep(5000);
		
		//homepage.navigateToPage(AssetsLink);
		homepage.clickOnLink(fixedAssetsLink);
		homepage.clickOnLink(AssetsLink);
		assetsPage = assetsPage.selectQueryByExample();
		assetsPage = assetsPage.searchAndEditInvoice(headerInvoiceNumber);
		
		assetsPage = assetsPage.editSourceLinesInAssets(description, inserviceDate, assetCategory, assetKey, units, location, employeeName);
		assetsPage = assetsPage.searchAndSplitInvoice(headerInvoiceNumber);
		assetsPage = assetsPage.verifyInvoiceIsSlipted(headerInvoiceNumber, units);
		
		System.out.println("Test Run Successfull");
		
	}
	
	
	

}
