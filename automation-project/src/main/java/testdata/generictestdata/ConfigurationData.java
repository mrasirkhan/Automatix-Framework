package testdata.generictestdata;

import org.testng.Assert;

import utilities.ExcelUtilities;

public class ConfigurationData 
{
	static String configurationFileName = "ConfigurationDetails.xlsx";
	
	
	public static String getUserDetails(String environment, String clientName, String keyName)
	{
		String fileName = "UserDetails.xlsx";
		
		String sheetName = null;
		switch(clientName.toUpperCase())
		{
		case "MOBILE" :
			sheetName = "Mobile";
			break;
		case "WEB PORTAL" :
			sheetName = "Web Portal";
			break;
		case "WEB SERVICES" :
			sheetName = "Web Services";
			break;
		case "FIRM C" :
			sheetName = "Firm C";
			break;
		default :
			Assert.fail("Database for " + clientName + " dosen't exsists");
		}
		
		int keyValueNumber = 0;
		switch(environment.toUpperCase())
		{
		case "AMAZON US PROD ENV" :
			keyValueNumber = 2;
			break;
		case "AMAZON INDIA PROD ENV" :
			keyValueNumber = 3;
			break;
		case "LENSKART PROD ENV" :
			keyValueNumber = 4;
			break;
		case "SPECSAVERS PROD ENV" :
			keyValueNumber = 5;
			break;
		case "AMAZON WEB SERVICES" :
			keyValueNumber = 2;
			break;
		case "ANDROID AMAZON APP" :
			keyValueNumber = 2;
			break;
		case "IOS SPEAKIT APP" :
			keyValueNumber = 3;
			break;
		default :
			Assert.fail(environment + "dosen't exsists");
		}
		return ExcelUtilities.getKeyValueFromExcelWithPosition(fileName, sheetName, keyName, keyValueNumber);
	}
	

	//URL Details
	public static String baseurlEnvironmentOne = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Amazon US Prod Env");
	public static String baseurlEnvironmentTwo = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Amazon India Prod Env");
	public static String baseurlEnvironmentThree = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Lenskart Prod Env");
	public static String baseurlEnvironmentFour = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Specsavers Prod Env");
	
	//Mobile Application : Configuration Data
	public static String nodeJSPath = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Node JS Path");
	public static String mobileDeviceName = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Device Name");
	public static String mobilePlatformVersion = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Platform Version");
	public static String appPackage = ExcelUtilities.getKeyValueFromExcelWithPosition(configurationFileName, "Config", "Mobile Application",1);
	public static String appActivity = ExcelUtilities.getKeyValueFromExcelWithPosition(configurationFileName, "Config", "Mobile Application",2);

	//Onboarding URL
	/*public static String onboardingSEIIntegartion = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI Onboarding Integration URL");
	public static String onboardingBrewinIntegartion = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin Onboarding Integration URL");
	public static String onboardingSEIUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI Onboarding UAT URL");
	public static String onboardingBrewinUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin Onboarding UAT URL");
	public static String onboardingSEIPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI Onboarding Preprod URL");
	public static String onboardingBrewinPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin Onboarding Preprod URL");
	//static String driverPath = System.getProperty("user.dir");*/
	
	//Driver Path Details
	public static String driverPathIE = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Internet Explorer Driver");	
	public static String driverPathChrome = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Chrome Driver");
	public static String driverPathFirefox = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Firefox Driver");
	
	
	

	/*public static String databaseHostNamebrewindbIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin Integration DB");
	public static String databaseHostNamecommondbIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Common Integration DB");
	public static String databaseHostNamecoredbIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI Integration DB");
	public static String databaseHostNamepbgbIntegartion = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "PBGB Integration DB");
	public static String databaseHostNameWHIrelanddbIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "WHIreland Integration DB");
	
	public static String databaseHostNamebrewindbUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin UAT DB");
	public static String databaseHostNamecommondbUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Common UAT DB");
	public static String databaseHostNamecoredbUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI UAT DB");
	public static String databaseHostNamepbgbUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "PBGB UAT DB");
	public static String databaseHostNameWHIrelandUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "WHIreland UAT DB");
	
	public static String databaseHostNamebrewindbPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Brewin Preprod DB");
	public static String databaseHostNamecommondbPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Common Preprod DB");
	public static String databaseHostNamecoredbPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "SEI Preprod DB");
	public static String databaseHostNamepbgbPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "PBGB Preprod DB");
	public static String databaseHostNameWHIrelandPreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "WHIreland Preprod DB");
	
	public static String databaseHostNameNewFirmImplementation = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "New Firm Implementation DB");

	public static String databaseUserNameIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "User Name Integration");
	public static String databasePasswordNameIntegration = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Password Integration");
	
	public static String databaseUserNameUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "User Name UAT");
	public static String databasePasswordNameUAT = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Password UAT");
	
	public static String databaseUserNamePreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "User Name Preprod");
	public static String databasePasswordNamePreprod = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Password Preprod");

	public static String databaseUserNameNewFirmImplementation = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "User Name New Firm Implementation");
	public static String databasePasswordNewFirmImplementation = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Password New Firm Implementation");*/
	
	public static String databaseHostNameautomationdb = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Automation DB");
	public static String databaseUserNameautomationdb = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Automation DB User Name");
	public static String databasePasswordautomationdb = ExcelUtilities.getKeyValueFromExcel(configurationFileName, "Config", "Automation DB Password");

}
