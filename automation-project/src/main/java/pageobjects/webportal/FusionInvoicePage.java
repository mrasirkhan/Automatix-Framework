package pageobjects.webportal;

import static org.testng.Assert.assertFalse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import browsersetup.BaseClass;
import utilities.GenerateRandomValue;
import utilities.JavaScriptExecutor;

public class FusionInvoicePage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public FusionInvoicePage(WebDriver driver) throws InterruptedException {
		/*
		 * this.driver = driver;
		 * this.driver.manage().timeouts().pageLoadTimeout(30,
		 * TimeUnit.SECONDS); wait = new WebDriverWait(this.driver, 15);
		 * //PageFactory.initElements( driver, this);
		 * PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),
		 * this);
		 */
		setDriver(driver);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// JavaScriptExecutor.waitUntilPageLoad(getDriver());
		wait = new WebDriverWait(getDriver(), 15);
		// PageFactory.initElements( driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 20), this);
	}

	
	
	@FindBy(xpath = "//div[@title='Tasks']/a")
	public WebElement createInvoiceTask;
	
	@FindBy(xpath = "//div[@title='Search: Invoices']/a")
	public WebElement searchInvoice;
		
	@FindBy(xpath = "//*[text()='Business Unit']//parent::td//following-sibling::td//input")
	public WebElement businessUnitHeader;
	
	@FindBy(xpath = "//*[text()='Number']//parent::td//following-sibling::td/input")
	public WebElement numberInvoiceHeader;
	
	@FindBy(xpath = "//*[text()='Supplier']//parent::td//following-sibling::td/input")
	public WebElement supplierHeader;
	
	@FindBy(xpath = "//a[@title='Search: Supplier']")
	public WebElement supplierSearchIcon;
	
	@FindBy(xpath = "//*[text()='Supplier']//parent::td//following-sibling::td/table//..//input")
	public WebElement supplierNameInModal;
	
	@FindBy(xpath = "//button[text()='Search']")
	public WebElement Searchbutton;
	
	@FindBy(xpath = "//button[contains(@id,'DialogId::ok')]")
	public WebElement okButtonOnSearch;
	
	@FindBy(xpath = "//label[text()=' Supplier Number']/preceding-sibling::input")
	public WebElement supplierNumberFieldInModal;
	
	@FindBy(xpath = "//label[text()='Taxpayer ID']/parent::td/following-sibling::td//input")
	public WebElement taxPayerIdFieldInModal;
	
	@FindBy(xpath = "//td[text()='%1$s']//parent::tr")
	public WebElement selectRow;
	
	@FindBy(xpath = "//*[text()='Legal Entity']//parent::td//following-sibling::td/input")
	public WebElement legalEntityHeader;
	
	@FindBy(xpath = "//*[text()='Invoice Group']//parent::td//following-sibling::td/input")
	public WebElement invoiceGroupHeader;
	
	@FindBy(xpath = "//*[text()='Description']//parent::td//following-sibling::td/textarea")
	public WebElement descriptionHeader;
	
	@FindBy(xpath = "//*[text()='Amount']//parent::td//following-sibling::td/table//..//select")
	public WebElement currencyHeader;

	@FindBy(xpath = "//*[text()='Amount']//parent::td//following-sibling::td/table//..//input")
	public WebElement amountHeader;
	
	@FindBy(xpath = "//*[text()='Type']//parent::td//following-sibling::td/Select")
	public WebElement typeHeader;
	
	@FindBy(xpath = "//*[text()='Date']//parent::td//following-sibling::td/input")
	public WebElement dateHeader;
	
	@FindBy(xpath = "//*[text()='Payment Terms']//parent::td//following-sibling::td/input")
	public WebElement paymentTermsHeader;
	
	@FindBy(xpath = "//*[text()='Terms Date']//parent::td//following-sibling::td/input")
	public WebElement termsDateHeader;
	
	@FindBy(xpath = "//*[text()='Requester']//parent::td//following-sibling::td/input")
	public WebElement requesterHeader;
	
	@FindBy(xpath = "//a[@title='Search: Requester']")
	public WebElement requesterSearchIcon;
	
	@FindBy(xpath = "//*[text()='Name']//parent::td//following-sibling::td/table//..//input")
	public WebElement requesterNameInModal;
	
	@FindBy(xpath = "//button[text()='OK']")
	public List<WebElement> okButton;
	
	@FindBy(xpath = "//h1[text()='Lines']//parent::div//parent::td//preceding-sibling::td/a")
	public WebElement expandCollapseLine;
	
	@FindBy(xpath = "//table[@summary='Invoice Lines']//../span/label[text()='Amount']//preceding-sibling::input")
	public WebElement lineAmount;
	
	@FindBy(xpath = "//table[@summary='Invoice Lines']//../span/label[text()='Distribution Combination ID']//preceding-sibling::input")
	public WebElement lineDistributionID;
	
	@FindBy(xpath = "//table[@summary='Invoice Lines']//../span/label[text()='Tax Classification']//preceding-sibling::input")
	public WebElement lineTaxClasification;
	
	@FindBy(xpath = "//*[contains(text(),'Actions')]")
	public WebElement selectInvoiceActions;
	
	@FindBy(xpath = "//a/span[contains(text(),'and Close')]")
	public WebElement saveAndCloseButton;
	
	@FindBy(xpath = "//a/span[@text()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath = "//a/span[contains(text(),'Create Next')]")
	public WebElement saveandCreateNextButton;
	
	@FindBy(xpath = "//*[text()='View Accounting']")
	public WebElement viewAccountingButton;
	
	@FindBy(xpath = "//button[@accesskey = 'o']")
	public WebElement doneButton;
	
	//@FindBy(xpath = "//a[@title='Close']")
	//public WebElement cancelButton;
	
	@FindBy(xpath = "//label[text()='Accounting Date']//parent::td//following-sibling::td/input[contains(@id,'content')]")
	public WebElement accountingDateMassAddition;
	
	@FindBy(xpath = "//label[text()='Asset Book']//parent::td//following-sibling::td/Select")
	public WebElement assetBookMassAddition;
	
	@FindBy(xpath = "//a[@accesskey = 'm']")
	public WebElement submitButton;
	
	@FindBy(xpath = "//a[@accesskey = 'C']")
	public WebElement cancelButton;
	
	@FindBy(xpath = "//button[contains(@id,'confirmationPopup:confirmSubmitDialog::ok')]")
	public WebElement confirmationOkButton;
	
	@FindBy(xpath = "//a[@title = 'Home']")
	public WebElement homeButton;
	
	@FindBy(xpath ="//div[@title='Add Row']/a")
	public WebElement addRowToLineItem;
	
	@FindBy(xpath ="//td[text()='1']/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[2]")
	public WebElement lineAmountCoulmn;
	
	@FindBy(xpath ="//a[@title='Details']")
	List <WebElement> lineItemDetailsIcon;
	
	@FindBy(xpath ="//button[text()='Cancel']")
	public WebElement cancelButtonOnDetailsPOPup;
	
	@FindBy(xpath ="//a[text()='Distribution']")
	public WebElement distributionLinkOnLineItem;
	
	@FindBy(xpath ="//a[text()='Tax']")
	public WebElement taxLinkOnLineItem;
	
	@FindBy(xpath ="//input[contains(@id,'_FOTRaTj_id_1:1:q1:value00::content')]")
	public WebElement searchInvoiceNumberField;
	
	@FindBy(xpath ="//a[@accesskey='Q']")
	public WebElement invoiceStatus;
	
	@FindBy(xpath ="//td[text()='Approval']")
	public WebElement approvalAction;
	
	@FindBy(xpath ="//td[text()='Initiate']")
	public WebElement initiateAction;
	
	@FindBy(xpath ="//div[@id='_FOpt1:_FOr1:0:_FOSritemNode_payables_payables_invoices:0:MAnt2:1:pm1:r1:0:ap1:r7:1:r24:0:ta1::db']/table/tbody/tr[3]/td[2]/span")
	public WebElement invoiceApprovalState;
	
	
	
	

	/**
	 *  Method to navigate to invoice page from Invoice Workbench
	 * @param createInvoiceLink
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage createNewInvoiceTask(String createInvoiceLink) throws InterruptedException {
		FusionHomePage homepage = new FusionHomePage(getDriver());
		createInvoiceTask.click();
		homepage.clickOnLink(createInvoiceLink);
		
		return new FusionInvoicePage(getDriver());
	}
	
	/**
	 * Method to Enter Invoice header values
	 * @param headerBU
	 * @param headerSupplier
	 * @param headerInvoiceGrp
	 * @param invoiceNum
	 * @param headerAmount
	 * @param invoiceType
	 * @param invoiceDec
	 * @param invoiceDate
	 * @param requester
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage enterInvoiceHeaders(String headerBU,String headerSupplier, String headerInvoiceGrp,String invoiceNum, String headerAmount,String invoiceType,String invoiceDec,String invoiceDate) throws InterruptedException {
		
		FusionInvoicePage invoicePage = enterBusinessUnitHeader(headerBU);
		invoicePage = invoicePage.entersupplierHeader(headerSupplier);
		
		//invoicePage = invoicePage.enterSupplierHeaderfromWindow(headerSupplier);
		
		invoicePage = invoicePage.enterInvoiceGroupAndInvoiceNumberandAmountHeader(headerInvoiceGrp, invoiceNum, headerAmount);
		invoicePage = invoicePage.enterInvoiceDateHeader(invoiceDate);
		Thread.sleep(2000);
		invoicePage = invoicePage.selectInvoiceTypeHeader(invoiceType);
		invoicePage = invoicePage.enterInvoiceDescriptionHeader(invoiceDec);
		
	//	invoicePage = invoicePage.enterRequesterHeader(requester);
		Thread.sleep(5000);
		return invoicePage;
	}
	
	
	public FusionInvoicePage enterBusinessUnitHeader(String headerBU) throws InterruptedException {
		
		businessUnitHeader.clear();
		businessUnitHeader.sendKeys(headerBU);
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterSupplierHeaderfromWindow(String headerSupplier) throws InterruptedException {
		
		supplierSearchIcon.click();
		supplierNameInModal.sendKeys(headerSupplier);
		Searchbutton.click();
		Thread.sleep(3000);
		//WebElement supplierRow = getDriver().findElement(By.xpath("//td[text()='"+headerSupplier+"']/../../../../../parent::tr"));
		//Actions builder = new Actions( getDriver());
		//	builder.keyDown(Keys.CONTROL)
		//		.click(supplierRow)
		//		.keyUp(Keys.CONTROL).build().perform();
		
		//String supplierNumber = getDriver().findElement(By.xpath("//td[contains(text(),'"+headerSupplier+"')]/following-sibling::td[1]")).getText();
		//String taxPayer = getDriver().findElement(By.xpath("//td[contains(text(),'"+headerSupplier+"')]/following-sibling::td[2]")).getText();
		//supplierNumberFieldInModal.sendKeys(supplierNumber);
		//taxPayerIdFieldInModal.sendKeys(taxPayer);
		
		getDriver().findElement(By.xpath("//div[contains(@id,'InternalTableId::db')]")).click();
		okButtonOnSearch.click();
		
		Thread.sleep(3000);
		
		return new FusionInvoicePage(getDriver());
	}
	public FusionInvoicePage entersupplierHeader(String headerSupplier) throws InterruptedException {
		
		supplierHeader.sendKeys(headerSupplier);
		//Thread.sleep(3000);

		getDriver().findElement(By.xpath("//ul/li[contains(text(),'"+headerSupplier+"')]")).click();
		Thread.sleep(3000);
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterInvoiceGroupAndInvoiceNumberandAmountHeader(String headerInvoiceGrp, String invoiceNum,String headerAmount) throws InterruptedException {
		
		invoiceGroupHeader.clear();
		invoiceGroupHeader.sendKeys(headerInvoiceGrp);
		numberInvoiceHeader.sendKeys(invoiceNum);
		amountHeader.sendKeys(headerAmount);
			
		return new FusionInvoicePage(getDriver());
	}

	public FusionInvoicePage selectInvoiceTypeHeader(String invoiceType) throws InterruptedException {
		
		Select typeOfInvoice = new Select(typeHeader);
		typeOfInvoice.selectByVisibleText(invoiceType);
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterInvoiceDescriptionHeader(String invoiceDec) throws InterruptedException {
		
		descriptionHeader.sendKeys(invoiceDec);
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterInvoiceDateHeader(String invoiceDate) throws InterruptedException {
		
		dateHeader.clear();
		dateHeader.sendKeys(invoiceDate);
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterRequesterHeader(String requester) throws InterruptedException {
		
		requesterHeader.sendKeys(requester);

		getDriver().findElement(By.xpath("//ul/li[contains(text(),'"+requester+"')]")).click();
	//	Actions act = new Actions(getDriver());
	//	act.sendKeys(Keys.DOWN).build().perform();
	//	act.sendKeys(Keys.ENTER).build().perform();
		//act.moveToElement(invoiceGroupHeader);
		Thread.sleep(3000);
		return new FusionInvoicePage(getDriver());
	}
	
	
	
	/**
	 * Method to enter Invoice line items
	 * @param lineAmt
	 * @param distributionComboId
	 * @param accountingDate
	 * @param taxClassification
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage enterInvoiceLineItems(String lineAmt, String distributionComboId,String accountingDate, String taxClassification)throws InterruptedException{
		
	//	FusionInvoicePage invoicePage = selectExpandCollapseButtonForLines();
		FusionInvoicePage  invoicePage = enterLineAmountAndDistributionId(lineAmt, distributionComboId);
		invoicePage = invoicePage.enterTaxClassification(taxClassification);
		
		return invoicePage;
	}
	
	public FusionInvoicePage selectExpandCollapseButtonForLines() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(expandCollapseLine));
		//act.moveToElement(expandCollapseLine);
		expandCollapseLine.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterLineAmountAndDistributionId(String lineAmt, String distributionComboId ) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(lineAmount));
		lineAmount.sendKeys(lineAmt);
		
		distributionLinkOnLineItem.click();
		lineDistributionID.sendKeys(distributionComboId);
			
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterTaxClassification(String taxClassification) throws InterruptedException {
		
	//	JavascriptExecutor js = (JavascriptExecutor)getDriver(); 
	//	js.executeScript("arguments[0].scrollIntoView()", lineTaxClasification);
		taxLinkOnLineItem.click();
		lineTaxClasification.sendKeys(taxClassification);
		Thread.sleep(3000);	
		return new FusionInvoicePage(getDriver());
	}
	/**
	 * Method to select Invoice action from actions dropdown
	 * @param invoiceAction
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage selectInvoiceAction(String invoiceAction) throws InterruptedException {
		
		//Select act = new Select(selectInvoiceActions);
		//act.selectByVisibleText(invoiceAction);
		
		selectInvoiceActions.click();
		FusionHomePage homepagePage = clickOnInvoiceActionsLink(invoiceAction);
		
		return new FusionInvoicePage(getDriver());
	}
	
	/**
	 * Method to click on Invoice actions links
	 * @param linktext
	 * @return
	 * @throws InterruptedException
	 */
	public FusionHomePage clickOnInvoiceActionsLink(String linktext) throws InterruptedException {
		getDriver().findElement(By.xpath("//table[contains(id,ScrollContent)]//../td[text()='"+linktext+"']")).click();
		return new FusionHomePage(getDriver());
	}
	
	/**
	 * Method to validate the Invoice
	 * @param invoiceAction
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage validateInvoice(String invoiceAction) throws InterruptedException {
		FusionInvoicePage invoicePage = selectInvoiceAction(invoiceAction);
		Thread.sleep(10000);
		return invoicePage;
	}
	
	/**
	 * Method to Post to Ledger and view Accounting
	 * @param invocieAction
	 * @return
	 * @throws InterruptedException
	 */
	public FusionInvoicePage postToLedgerInvoice(String invoiceAction) throws InterruptedException {
		
		FusionInvoicePage invoicePage = selectInvoiceAction(invoiceAction);
		invoicePage = invoicePage.clickOnViewAccountingButton();
		invoicePage = invoicePage.clickOnDoneButton();
		
		
		return invoicePage;
	}
	
	public FusionInvoicePage clickOnViewAccountingButton() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(viewAccountingButton));
		viewAccountingButton.click();
			return new FusionInvoicePage(getDriver());
		}
	
public FusionInvoicePage clickOnDoneButton() throws InterruptedException {
		
	//Screenshot Required
			wait.until(ExpectedConditions.elementToBeClickable(doneButton));
			doneButton.click();
			return new FusionInvoicePage(getDriver());
		}
	
	
	public FusionInvoicePage saveAndCloseInvoice() throws InterruptedException {
	Thread.sleep(5000);
	wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseButton));
		saveAndCloseButton.click();
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage saveAndCreateNewInvoice() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(saveandCreateNextButton));
			saveandCreateNextButton.click();
			return new FusionInvoicePage(getDriver());
		}
	
	public String getCurrentDate() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date();   
	    String currentdate=formatter.format(date);
		return currentdate;
	}
	
	public FusionInvoicePage submitMassAddition(String assetBook) throws InterruptedException {
		
		FusionInvoicePage invoicePage = selectAccountingDateAndAssetBook(assetBook);
			invoicePage = invoicePage.clickOnSubmitAndConfirm();
			invoicePage = invoicePage.clickOnCancelButton();
			return invoicePage;
		}
	
	
	public FusionInvoicePage selectAccountingDateAndAssetBook(String assetBook) throws InterruptedException {
		
		accountingDateMassAddition.sendKeys(getCurrentDate());
	
		Select act = new Select(assetBookMassAddition);
		act.selectByVisibleText(assetBook);
		
		return new FusionInvoicePage(getDriver());
	}

	public FusionInvoicePage clickOnSubmitAndConfirm() throws InterruptedException {
		
		submitButton.click();
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(confirmationOkButton));
		confirmationOkButton.click();
	//	wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
	//	cancelButton.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
public FusionInvoicePage clickOnCancelButton() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
		cancelButton.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	
	public FusionInvoicePage navigateToHomepage() throws InterruptedException {
		
		homeButton.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	
	public FusionInvoicePage clickOnAddRowInLineItem() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(addRowToLineItem));
			addRowToLineItem.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	
	public FusionInvoicePage clickOnLineItemCoulumn() throws InterruptedException {
		//wait.until(ExpectedConditions.visibilityOf(lineAmountCoulmn));
//		try {
//		if(lineAmountCoulmn.isDisplayed()) {
//			//	lineAmountCoulmn.click();
//		JavascriptExecutor js = (JavascriptExecutor)getDriver(); 
//		js.executeScript("arguments[0].click()", lineAmountCoulmn);
//		}
//		}catch(Exception e) {
//			System.out.println("");
//		}
//		
		JavascriptExecutor js = (JavascriptExecutor)getDriver(); 
		js.executeScript("arguments[0].scrollIntoView()", lineItemDetailsIcon.get(0));
		wait.until(ExpectedConditions.elementToBeClickable(lineItemDetailsIcon.get(0)));
		lineItemDetailsIcon.get(0).click();
		wait.until(ExpectedConditions.elementToBeClickable(cancelButtonOnDetailsPOPup));
		cancelButtonOnDetailsPOPup.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	
	public String getRandomInvoiceNumber() {
	//	Random rand = new Random();
	  //    int upperbound = 10000;
	        //generate random values from 0-9999
	  //    int int_random = rand.nextInt(upperbound); 
		GenerateRandomValue randomVal = new GenerateRandomValue();
		String invoiceNumber = "Auto_"+randomVal.randomIdentifiers();
		return invoiceNumber;
	}
	
	public FusionInvoicePage clickOnSearchInvoiceButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchInvoice));
		searchInvoice.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage enterInvoiceNumberInSearchFieldAndSearch(String invoiceNumber) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(searchInvoiceNumberField));
		searchInvoiceNumberField.sendKeys(invoiceNumber);
		
		Searchbutton.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage verifyInvoiceStatus(String invoiceState) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(invoiceStatus));
		Assert.assertEquals(invoiceStatus.getText().trim(), invoiceState);
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage initiateInvoiceApproval() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(selectInvoiceActions));
		selectInvoiceActions.click();
		approvalAction.click();
		initiateAction.click();
		
		return new FusionInvoicePage(getDriver());
	}
	
	public FusionInvoicePage verifyInvoiceApprovalStatus(String invoiceApprovalStatus) throws InterruptedException {
		
		invoiceStatus.click();
		wait.until(ExpectedConditions.visibilityOf(invoiceApprovalState));
		Assert.assertEquals(invoiceApprovalState.getText().trim(), invoiceApprovalStatus);
		
		//String status = getDriver().findElement(By.xpath("//div[@id='_FOpt1:_FOr1:0:_FOSritemNode_payables_payables_invoices:0:MAnt2:1:pm1:r1:0:ap1:r7:1:r24:0:ta1::db']/table/tbody/tr[3]/td[2]/span")).getText();
		//System.out.println(status);
		
		return new FusionInvoicePage(getDriver());
	}
	
	
}
