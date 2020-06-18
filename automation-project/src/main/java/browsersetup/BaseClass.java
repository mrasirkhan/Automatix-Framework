package browsersetup;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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

public class BaseClass 
{
	private WebDriver driver;
	public WebDriverWait wait;
	private List<String> assertMessage;
	private String clientName;
	private AppiumDriverLocalService appiumDriverLocalService;
	private AndroidDriver androidDriver;
	private IOSDriver iOSDriver;
	//Mobile Application : Configuration Data
	static String nodeJSPath = ConfigurationData.nodeJSPath;

	public AppiumDriverLocalService getAppiumDriverLocalService() 
	{
		return appiumDriverLocalService;
	}

	public void setAppiumDriverLocalService(AppiumDriverLocalService appiumDriverLocalService) {
		this.appiumDriverLocalService = appiumDriverLocalService;
	}

	/*@BeforeSuite(alwaysRun=true)
	@Parameters({ "browser","environment","clientName" })
	public void startAppiumServer(String browser,String environment, String clientName) throws InterruptedException, MalformedURLException 
	{
		try {
			if(clientName.equals("Mobile") && browser.equals("Android"))
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
	@Parameters({ "browser","environment","clientName" })
	public void stopAppiumServer(String browser,String environment, String clientName) throws InterruptedException, MalformedURLException 
	{
		try {
			if(clientName.equals("Mobile") && browser.equals("Android"))
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
	@Parameters({ "browser","environment","clientName" })
	public void setup(String browser,String environment, String clientName) throws InterruptedException, MalformedURLException 
	{
		this.setClientName(clientName);
		if(clientName.equals("Web Portal"))
		{
			Setup setup = new Setup();
			driver = setup.setupBrowser(browser,environment,clientName);
		}
		else if(clientName.equals("Mobile"))
		{
			Setup setup = new Setup();
			if(browser.equalsIgnoreCase("Android"))
				androidDriver = setup.setupAndroidDevice(browser,environment,clientName);
			else if(browser.equalsIgnoreCase("iOS"))
				iOSDriver = setup.setupiOSDevice(browser, environment, clientName);
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
	@Parameters({ "browser","clientName" })
	public void quit(String browser, String clientName) throws InterruptedException 
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
			tearDown(browser,clientName);
		}
	}


	/*@Parameters({ "clientName" })
	public String setupClientDB(String clientName) throws InterruptedException 
	{
		String dbName = null;
		switch(clientName.toUpperCase())
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
			Assert.fail("Database for " + clientName + "dosen't exsists");
		}
		return dbName;
	}*/

	private void tearDown(String browser, String clientName)
	{
		try 
		{
			if(clientName.equals("Web Portal"))
			{
				driver.close();
				driver.quit();
			}

			if(clientName.equals("Mobile"))
			{
				//getAndroidDriver().closeApp();
				if (browser.equalsIgnoreCase("Android")) 
				{
					getAndroidDriver().quit();
					WindowsUtils.killByName("adb.exe");
				}
				if (browser.equalsIgnoreCase("iOS")) 
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public AndroidDriver getAndroidDriver() {
		return androidDriver;
	}

	public void setAndroidDriver(AndroidDriver androidDriver) {
		this.androidDriver = androidDriver;
	}
}