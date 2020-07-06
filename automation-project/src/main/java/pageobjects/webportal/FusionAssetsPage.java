package pageobjects.webportal;

import static org.testng.Assert.assertFalse;

import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import browsersetup.BaseClass;
import utilities.JavaScriptExecutor;

public class FusionAssetsPage extends BaseClass {

	/*
	 * WebDriver driver; WebDriverWait wait;
	 */

	public FusionAssetsPage(WebDriver driver) throws InterruptedException {
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

	

	@FindBy(xpath = "//a[text()='View']")
	public WebElement viewSelection;
	
	@FindBy(xpath = "//td[text()='Query By Example']//parent::tr")
	public WebElement queryByExampleOption;
	
	@FindBy(xpath = "//div[contains(@id,'table2::ch::d2')]//tr[2]/th[1]/span/input")
	public WebElement invoiceNumQuery;
	
	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement actionSelection;
	
	@FindBy(xpath = "//tr[contains(@id,'editSourceLine')]/td[text()='Edit']")
	public WebElement editAction;
	
	@FindBy(xpath = "//label[text()='Description']//parent::td//following-sibling::td//input")
	public WebElement assetDescription;
	
	@FindBy(xpath = "//input[contains(@id,'DatePlacedInService::content')]")
	public WebElement dateplacedInservice;
	
	@FindBy(xpath = "//input[contains(@id,':categoryFlexFieldCS::content')]")
	public WebElement assetCatogoryfield;
	
	@FindBy(xpath = "//label[text()='Asset Key']//parent::td//following-sibling::td//input")
	public WebElement assetKeyfield;
	
	
	@FindBy(xpath = "//label[text()='In physical inventory']//preceding-sibling::input")
	public WebElement inPhysicalInventoryCheckbox;
	
	@FindBy(xpath = "//label[text()='In use']//preceding-sibling::input")
	public WebElement inUseCheckbox;
	
	@FindBy(xpath = "//input[contains(@id,'DistrUnits::content')]")
	public WebElement unitInputfield;
	
	@FindBy(xpath = "//input[contains(@id,'employeeNameId::content')]")
	public WebElement employeeNamefield;
	
	@FindBy(xpath = "//label[text()='Location']//preceding-sibling::input")
	public WebElement locationfield;
	
	@FindBy(xpath = "//a[@accesskey='S']")
	public WebElement saveAndCloseAssets;
	
	@FindBy(xpath = "//button[text()='Split']")
	public WebElement splitButton;
	
	@FindBy(xpath = "//button[contains(@id,'SplitOKBtn')]")
	public WebElement splitOKbutton;
	
	@FindBy(xpath = "//button[@accesskey = 'K']")
	public WebElement splitConfirm;
	
	@FindBy(xpath = "//button[text()='Merge']")
	public WebElement mergeButton;

	@FindBy(xpath = "//button[@accesskey = 'm']")
	public WebElement submitMerge;
	
	@FindBy(xpath = "//span[text()='Total']/../parent::tr/../parent::table/parent::td/following-sibling::td[6]//span")
	public WebElement totalAfterMerge;
	
	@FindBy(xpath = "//label[text()='Sum merged units']//preceding-sibling::input")
	public WebElement sumMergedUnitsCheckbox;
	
	@FindBy(xpath = "//table[@summary='Select Parent']//tr")
	List <WebElement> invoicesTobeMerged;
	
	
	public FusionAssetsPage verifyLandingPage(String title) throws InterruptedException {
		
		String pageTitle = getDriver().getTitle();
		Assert.assertTrue(pageTitle.contains(title));
		
		return new FusionAssetsPage(getDriver());
	}

	public FusionAssetsPage selectQueryByExample()throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(viewSelection));
		viewSelection.click();
		if(queryByExampleOption.getAttribute("aria-checked").equalsIgnoreCase("false")) {
			queryByExampleOption.click();
		}
		return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage searchInvoiceOnAssetPage(String invoiceNumber)throws InterruptedException{
		
		FusionAssetsPage fusionAssets = searchInvoice(invoiceNumber);
		fusionAssets = fusionAssets.selectInvoice();
		
		
		return fusionAssets;
	}
	
	public FusionAssetsPage searchInvoice(String invoiceNumber)throws InterruptedException{
		
		invoiceNumQuery.sendKeys(invoiceNumber);
		Thread.sleep(2000);
		invoiceNumQuery.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
        
	return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage selectInvoice()throws InterruptedException{
		
		List<WebElement> tableRows = getDriver().findElements(By.xpath("//table[@summary='Source Lines']//tr"));
		Actions builder = new Actions( getDriver());
		builder.keyDown(Keys.CONTROL)
		       .click(tableRows.get(0))
		       .keyUp(Keys.CONTROL).build().perform();
		
		Thread.sleep(3000);
        
	return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage selectAllInvoices(int invoiceCount)throws InterruptedException{
		
		List<WebElement> tableRows = getDriver().findElements(By.xpath("//table[@summary='Source Lines']/tbody/tr"));
		
		for(int i =0;i<invoiceCount;i++){
		
			if(tableRows.get(i).getAttribute("class").contains("Selected")==false){
				Actions builder = new Actions( getDriver());
				builder.keyDown(Keys.CONTROL)
						.click(tableRows.get(i))
						.keyUp(Keys.CONTROL).build().perform();
			}
		}
	
		
		Thread.sleep(3000);
        
	return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage searchAndEditInvoice(String invoiceNumber)throws InterruptedException{
		
		FusionAssetsPage assetPage = searchInvoiceOnAssetPage(invoiceNumber);
		assetPage = assetPage.editInvoice();
	        
		return assetPage;
	}
	
	public FusionAssetsPage editInvoice()throws InterruptedException{
		
		wait.until(ExpectedConditions.elementToBeClickable(actionSelection));
		actionSelection.click();
        editAction.click();
        
	return new FusionAssetsPage(getDriver());
}
	
	
	public FusionAssetsPage editSourceLinesInAssets(String assetDes, String inServiceDt, String assetCategory, String assetKey, String units, String location, String empName)throws InterruptedException{
		
		
		
		FusionAssetsPage assetPage = enterSourceLineFields(assetDes, inServiceDt, assetCategory, assetKey);
		assetPage = assetPage.selectCheckBoxesforSourceLines();
		assetPage = assetPage.enterUnitsLocationAndEmployeeName(units, empName, location);
		assetPage = assetPage.saveAndCloseAsset();

		return assetPage;
	}
	
	public FusionAssetsPage enterSourceLineFields(String assetDes, String inServiceDt, String assetCategory, String assetKey )throws InterruptedException{
		
		assetDescription.sendKeys(assetDes);
		dateplacedInservice.clear();
		dateplacedInservice.sendKeys(inServiceDt);
		assetCatogoryfield.sendKeys(assetCategory);
	
		assetKeyfield.sendKeys(assetKey);
		Thread.sleep(3000);
		
        
	return new FusionAssetsPage(getDriver());
	}
	
	
	public FusionAssetsPage selectCheckBoxesforSourceLines()throws InterruptedException{
		
	//Actions actions = new Actions(getDriver());
			//actions.moveToElement(inPhysicalInventoryCheckbox).click().build().perform();
			
			 JavascriptExecutor js = (JavascriptExecutor)getDriver();
			 js.executeScript("arguments[0].click();", inPhysicalInventoryCheckbox);
			
			//inPhysicalInventoryCheckbox.click();
			
			 js.executeScript("arguments[0].click();", inUseCheckbox);
			
			//inUseCheckbox.click();
			
		
        
	return new FusionAssetsPage(getDriver());
	}


	public FusionAssetsPage enterUnitsLocationAndEmployeeName(String units, String empName, String location)throws InterruptedException{
		
		unitInputfield.clear();
		unitInputfield.sendKeys(units);
		employeeNamefield.sendKeys(empName);
		locationfield.sendKeys(location);
		
	    
	return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage saveAndCloseAsset()throws InterruptedException{
		
		saveAndCloseAssets.click();
		Thread.sleep(5000);  
	return new FusionAssetsPage(getDriver());
	}

	
	public FusionAssetsPage searchAndSplitInvoice(String invoiceNumber)throws InterruptedException{
		
		FusionAssetsPage assetPage = searchInvoiceOnAssetPage(invoiceNumber);
		assetPage = assetPage.splitInvoice();
        
	return assetPage;
	}
	
	public FusionAssetsPage splitInvoice()throws InterruptedException{
		
		wait.until(ExpectedConditions.elementToBeClickable(splitButton));
		splitButton.click();
        splitOKbutton.click();
        splitConfirm.click();
	return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage verifyInvoiceIsSlipted(String invoiceNumber, String units)throws InterruptedException{
		Thread.sleep(3000);
		
		List<WebElement> tableRows = getDriver().findElements(By.xpath("//table[@summary='Source Lines']//tr/td/span/a[text()='"+invoiceNumber+"']"));
		String rowcount = String.valueOf(tableRows.size());
		Assert.assertTrue(rowcount.equals(units));
		
		JavascriptExecutor js = (JavascriptExecutor)getDriver(); 
		js.executeScript("arguments[0].scrollIntoView()", tableRows.get(tableRows.size()-1));
        
	return new FusionAssetsPage(getDriver());
	}

	public FusionAssetsPage mergeInvoices() throws InterruptedException {
		// TODO Auto-generated method stub
		FusionAssetsPage assetPage = clickMergeButton();
	
		assetPage = assetPage.clickOnSubmitMergeButton();
		return assetPage;
	}
	
	
	public FusionAssetsPage clickMergeButton() throws InterruptedException {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.elementToBeClickable(mergeButton));
		mergeButton.click();
		Thread.sleep(5000);
		return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage clickOnSubmitMergeButton() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(submitMerge));
		submitMerge.click();
		
		return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage verifyTotalAmount(double totalAmount) throws InterruptedException {
		// TODO Auto-generated method stub
		String afterMergeTotal = totalAfterMerge.getText().replace(",", "");
		Assert.assertEquals(afterMergeTotal, String.format("%.2f", totalAmount));
		
		return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage checkSumMergeUnitsCheckbox() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		 js.executeScript("arguments[0].click();", sumMergedUnitsCheckbox);
		
		return new FusionAssetsPage(getDriver());
	}
	
	public FusionAssetsPage verifyInvoicesSelectedforMerge(int invoiceCount, String invoiceName) throws InterruptedException {
		
	//	for(int i=1;i<=invoiceCount;i++) {
			
	//		getDriver().findElement(By.xpath("//td[@title='"+invoiceName+"."+i+"']")).isDisplayed();
		//	wait.until(ExpectedConditions.visibilityOf((WebElement) By.xpath("//td[@title='"+invoiceName+"."+i+"']")));
	//	}
		
		Assert.assertTrue(invoicesTobeMerged.size()==invoiceCount);
		
		return new FusionAssetsPage(getDriver());
	}
	
	
}