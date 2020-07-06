package testscripts.webportal;

import java.awt.Window;
import java.util.HashMap;

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
import utilities.GenerateRandomValue;

@Listeners(ListenerClass.class)
public class EvosysFPTMergeInvoiceScripts extends BaseClass
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression","Web Portal","Finance Process Testing"})
	@Parameters({ "environment", "clientName" })
	public void fusionMergeInvoiceScripts(String environment, String clientName) throws InterruptedException
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
		
		//String headerInvoiceNumber;
		String headerInvoiceNumber = invoicePage.getRandomInvoiceNumber();
		String headerInvoiceGroup = "Group_"+headerInvoiceNumber;
		String invoicesToBeMerged = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), "TC2_data_1", 18);
		int invoiceCount = Integer.parseInt(invoicesToBeMerged);
		
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
		double totalInvoiceAmount = 0;
		HashMap invoiceNumbers = new HashMap();
		HashMap invoiceAmounts = new HashMap();
		for(int i = 1;i<=invoiceCount;i++) {
			
			String testdataID = "TC2_data_"+i;
			System.out.println(testdataID);
			String headerBU = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 1);
			String headerSupplier = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 2);
			
			String headerAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(),testdataID, 5);
			String headerDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 6);
			String lineAmount = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 7);
			String lineDistrubutionCombination = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 8);
			String lineTaxClassification = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 9);
			
			String accountingDate = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 10);
			String description = ExcelUtilities.getKeyValueFromExcelWithPosition("TestCaseData.xlsx", "HomePage_"+environment.toUpperCase(), testdataID, 11);
			
			
			invoicePage = invoicePage.enterInvoiceHeaders(headerBU, headerSupplier, headerInvoiceGroup, headerInvoiceNumber+"."+i, headerAmount, invoiceType, description, headerDate);
			invoicePage = invoicePage.selectExpandCollapseButtonForLines();
			//invoicePage = invoicePage.clickOnAddRowInLineItem();
			invoicePage = invoicePage.clickOnLineItemCoulumn();
			invoicePage = invoicePage.enterInvoiceLineItems(lineAmount, lineDistrubutionCombination, accountingDate, lineTaxClassification);
			invoicePage = invoicePage.validateInvoice(validateAction);
			invoicePage = invoicePage.postToLedgerInvoice(postToLedgerAction);
			if(i==invoiceCount) {
				invoicePage = invoicePage.saveAndCloseInvoice();
			}
			else {
				invoicePage = invoicePage.saveAndCreateNewInvoice();
			} 
			
			totalInvoiceAmount = Double.parseDouble(lineAmount) + totalInvoiceAmount;
		}
			
		System.out.println(totalInvoiceAmount);
		//System.out.println(invoiceAmounts);
		Thread.sleep(5000);
		//homepage.verifyLandingPage(invoiceWorkbenchTitle);
	
		//Part 2 FA 03
		
		invoicePage = invoicePage.createNewInvoiceTask(createMassAdditionsLink);
		invoicePage = invoicePage.submitMassAddition(assetBook);
		
		invoicePage = invoicePage.navigateToHomepage();
		Thread.sleep(5000);
		
		
	//	homepage.navigateToPage(AssetsLink);
		homepage.clickOnLink(fixedAssetsLink);
		homepage.clickOnLink(AssetsLink);
	//	headerInvoiceNumber = "Auto_NlyOxoRj";
	//	totalInvoiceAmount = 4000.00;
		assetsPage = assetsPage.selectQueryByExample();
		assetsPage = assetsPage.searchInvoice(headerInvoiceNumber);
		assetsPage = assetsPage.selectAllInvoices(invoiceCount);
		assetsPage = assetsPage.clickMergeButton();
		assetsPage = assetsPage.verifyInvoicesSelectedforMerge(invoiceCount, headerInvoiceNumber);
		assetsPage = assetsPage.verifyTotalAmount(totalInvoiceAmount);
		assetsPage = assetsPage.checkSumMergeUnitsCheckbox();
		assetsPage = assetsPage.clickOnSubmitMergeButton();
		assetsPage = assetsPage.selectInvoice();
		assetsPage = assetsPage.editInvoice();
		assetsPage = assetsPage.saveAndCloseAsset();
		
		
		System.out.println("Test Run Successfull");
		
	}
	
	
	

}
