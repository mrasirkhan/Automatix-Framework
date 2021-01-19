package browsersetup;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.text.Utilities;

import org.apache.log4j.BasicConfigurator;
import org.aspectj.apache.bcel.classfile.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import testdata.generictestdata.ConfigurationData;
import utilities.ReadProperties;

public class BaseClass 
{
	private WebDriver driver;
	public WebDriverWait wait;
	private List<String> assertMessage;
	private String automationType;
	private AppiumDriverLocalService appiumDriverLocalService;
	private AndroidDriver androidDriver;
	private IOSDriver iOSDriver;
	//Mobile Application : Configuration Data	
	
//	public Logger LOG = LoggerFactory.getLogger(BaseClass.class);
	
	 public static final String configPropertie = "config.properties"; 
	 public static final String UIPropertie = "UI.properties"; 
	 public static final String databasePropertie = "database.properties"; 
	 public static final String filenamesPropertie = "filenames.properties"; 
	 public static final String webservicesPropertie = "webservices.properties"; 
	 public static final String location = "\\properties\\";//xyx.properties;
	 
	 public static final String environmentName1 ="AMAZON US PROD ENV";
	 public static final String environmentName2 ="AMAZON INDIA PROD ENV"; 
	 public static final String environmentName3 ="LENSKART PROD ENV";
	 public static final String environmentName4 ="SPECSAVERS PROD ENV";
	 public static final String environmentName5 ="AMAZON WEB SERVICES";
	 public static final String environmentName6 ="ANDROID AMAZON APP";
	 public static final String environmentName7 ="IOS SPEAKIT APP";
	 
	 public static final String automationTypeName1 ="MOBILE";
	 public static final String automationTypeName2 ="WEB PORTAL";
	 public static final String automationTypeName3 ="WEB SERVICES";
	 public static final String automationTypeName4 ="FIRM C";
	 
	 public static final String databasename=utilities.ReadProperties.getProperty(databasePropertie, location, "databasename");
	 public static final String dbenvironment=utilities.ReadProperties.getProperty(databasePropertie, location, "dbEnvironment");

	 public static final String testcasedataFileName =utilities.ReadProperties.getProperty(filenamesPropertie, location, "Testcasesdata"); 
	 
	public AppiumDriverLocalService getAppiumDriverLocalService() 
	{
		return appiumDriverLocalService;
	}

	public void setAppiumDriverLocalService(AppiumDriverLocalService appiumDriverLocalService) {
		this.appiumDriverLocalService = appiumDriverLocalService;
	}

	/*@BeforeSuite(alwaysRun=true)
	@Parameters({ "browser","environment","automationType" })
	public void startAppiumServer(String browser,String environment, String automationType) throws InterruptedException, MalformedURLException 
	{
		try {
			if(automationType.equals("Mobile") && browser.equals("Android"))
			{
				setAppiumDriverLocalService(AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
						.usingDriverExecutable(new File(nodeJSPath))
						.withLogFile(new File("target/test.log"))));
				getAppiumDriverLocalService().start();
			}
		} catch (AppiumServerHasNotBeenStartedLocallyException e) {
			// TODO Auto-generated catch block
			System.out.println("*****************************************");
			System.out.println("Faced Issue in Starting Appium Server");
			System.out.println("*****************************************");
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun=true)
	@Parameters({ "browser","environment","automationType" })
	public void stopAppiumServer(String browser,String environment, String automationType) throws InterruptedException, MalformedURLException 
	{
		try {
			if(automationType.equals("Mobile") && browser.equals("Android"))
			{
				getAppiumDriverLocalService().stop();
				WindowsUtils.killByName("adb.exe");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*****************************************");
			System.out.println("Faced Issue in Closing Appium Server");
			System.out.println("*****************************************");
			e.printStackTrace();
		}
	}*/


	public IOSDriver getiOSDriver() {
		return iOSDriver;
	}

	public void setiOSDriver(IOSDriver iOSDriver) {
		this.iOSDriver = iOSDriver;
	}

	//@BeforeMethod(timeOut = 300000, alwaysRun=true)
	@BeforeMethod(alwaysRun=true)
	@Parameters({ "browser","environment","automationType" })
	public void setup(String browser,String environment, String automationType) throws InterruptedException, MalformedURLException 
	{
		this.setAutomationType(automationType);
		if(automationType.equals(utilities.ReadProperties.getProperty(UIPropertie, location, "automationType1")))
		{
			Setup setup = new Setup();
			driver = setup.setupBrowser(browser,environment,automationType);
		}
		else if(automationType.equals(utilities.ReadProperties.getProperty(UIPropertie, location, "automationType2")))
		{
			Setup setup = new Setup();
			if(browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser4")))
				androidDriver = setup.setupAndroidDevice(browser,environment,automationType);
			else if(browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser5")))
				iOSDriver = setup.setupiOSDevice(browser, environment, automationType);
		}
	}

	public void setAssertMessage(String message, int verificationNumber)
	{
		try
		{
			assertMessage.add(message);
		} 
		catch (Exception e) 
		{
			assertMessage = new ArrayList<String>();
			assertMessage.add(message);
		}
	}

	public List<String> getAssertMessage()
	{
		return assertMessage;
	}

	//@AfterMethod(timeOut = 300000, alwaysRun=true)
	@AfterMethod(alwaysRun=true)
	@Parameters({ "browser","automationType" })
	public void quit(String browser, String automationType) throws InterruptedException 
	{
		try
		{//assertMessage.removeAll(assertMessage);
			if((!assertMessage.isEmpty()) && (!(assertMessage==null)))
			{
				assertMessage.clear();
			}
		}
		catch (Exception e)
		{
			assertMessage = new ArrayList<String>();
			assertMessage.addAll(assertMessage);
		}
		finally
		{
			tearDown(browser,automationType);
		}
	}


	/*@Parameters({ "automationType" })
	public String setupClientDB(String automationType) throws InterruptedException 
	{
		String dbName = null;
		switch(automationType.toUpperCase())
		{
		case "SEI" :
			dbName = "coredb";
			break;
		case "BREWIN" :
			dbName = "brewindb";
			break;
		case "PBGB" :
			dbName = "pbgbdb";
			break;
		case "WHIRELAND" :
			dbName = "whidb";
			break;
		default :
			Assert.fail("Database for " + automationType + "dosen't exsists");
		}
		return dbName;
	}*/

	private void tearDown(String browser, String automationType)
	{
		try 
		{
			if(automationType.equals(utilities.ReadProperties.getProperty(UIPropertie, location, "automationType1")))
			{
				driver.close();
				driver.quit();
			}

			if(automationType.equals(utilities.ReadProperties.getProperty(UIPropertie, location, "automationType2")))
			{
				//getAndroidDriver().closeApp();
				if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser4")))
				{
					getAndroidDriver().quit();
					WindowsUtils.killByName(utilities.ReadProperties.getProperty(filenamesPropertie, location, "adb"));
				}
				if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser5")))
				{
					getiOSDriver().quit();
				}
				//getAndroidDriver().quit();
				//getAppiumDriverLocalService().stop();
			}
			/*			if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("Firefox")) 
				WindowsUtils.tryToKillByName("firefox.exe");
			else if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("chrome"))
				WindowsUtils.tryToKillByName("chrome.exe");
			else if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("InternetExplorer"))
				WindowsUtils.tryToKillByName("iexplore.exe");*/
		}
		catch (Exception n) 
		{
			/*			if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("Firefox")) 
				WindowsUtils.tryToKillByName("firefox.exe");
			else if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("chrome"))
				WindowsUtils.tryToKillByName("chrome.exe");
			else if(WindowsUtils.thisIsWindows() && browser.equalsIgnoreCase("InternetExplorer"))
				WindowsUtils.tryToKillByName("iexplore.exe");*/

			System.out.println("*********************************************");
			System.out.println("Problem Closing Driver");
			System.out.println("*********************************************");
			n.printStackTrace();
		}
	}

	public WebDriver getDriver() 
	{
		return driver;
	}

	public void setDriver(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public String getAutomationType() {
		return automationType;
	}

	public void setAutomationType(String automationType) {
		this.automationType = automationType;
	}
	
	/*
	 * public String getClientName() { return automationType; }
	 * 
	 * public void setClientName(String automationType) { this.automationType =
	 * automationType; }
	 */

	public AndroidDriver getAndroidDriver() {
		return androidDriver;
	}

	public void setAndroidDriver(AndroidDriver androidDriver) {
		this.androidDriver = androidDriver;
	}

}