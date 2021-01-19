package browsersetup;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import testdata.generictestdata.ConfigurationData;
import utilities.JavaScriptExecutor;

public class Setup extends BaseClass {
	
	static String baseurlEnvironmentOne = utilities.ReadProperties.getProperty(UIPropertie, location,"webEnvironment1");//getProperty(UIprops, "AMAZONUSPRODENV");//ConfigurationData.baseurlEnvironmentOne;
	static String baseurlEnvironmentTwo = utilities.ReadProperties.getProperty(UIPropertie, location, "webEnvironment2");//ConfigurationData.baseurlEnvironmentTwo;
	static String baseurlEnvironmentThree = utilities.ReadProperties.getProperty(UIPropertie, location, "webEnvironment3");//ConfigurationData.baseurlEnvironmentThree;
	static String baseurlEnvironmentFour = utilities.ReadProperties.getProperty(UIPropertie, location, "webEnvironment4");//ConfigurationData.baseurlEnvironmentFour;

	// Mobile Application : Configuration Data
	static String nodeJSPath = utilities.ReadProperties.getProperty(filenamesPropertie, location,"NodeJSPath");//ConfigurationData.nodeJSPath;
	static String mobileDeviceName =utilities.ReadProperties.getProperty(UIPropertie, location, "andDeviceName"); //ConfigurationData.mobileDeviceName;
	static String mobilePlatformVersion = utilities.ReadProperties.getProperty(UIPropertie, location, "AndropidDeandVersionviceName");//ConfigurationData.mobilePlatformVersion;
	static String appPackage = utilities.ReadProperties.getProperty(UIPropertie, location, "androidEnvironment1_Package");//ConfigurationData.appPackage;
	static String appActivity = utilities.ReadProperties.getProperty(UIPropertie, location, "androidEnvironment1_Activity");//ConfigurationData.appActivity;

	private WebDriver driver = null;
	private AndroidDriver androidDriver = null;
	private IOSDriver iOSDriver = null;
	
	static String driverPath =System.getProperty("user.dir");// getProperty(filenameprops, "systemdir");
	static String driverPathIE = driverPath+utilities.ReadProperties.getProperty(configPropertie, location, "IEDriver");//ConfigurationData.driverPathIE;
	static String driverPathChrome = driverPath+utilities.ReadProperties.getProperty(configPropertie, location, "chromDriver");//driverPath+ ConfigurationData.driverPathChrome;//ConfigurationData.driverPathChrome;
	static String driverPathFirefox = driverPath+utilities.ReadProperties.getProperty(configPropertie, location, "firfoxDriver");//ConfigurationData.driverPathFirefox;
	String implicitWait=utilities.ReadProperties.getProperty(configPropertie, location, "implicitWait");

	@SuppressWarnings("deprecation")
	public WebDriver setupBrowser(String browser, String environment, String automationType)
			throws InterruptedException, MalformedURLException {
		if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser2"))) {

			// File pathToFirefoxBinary = new File("C:\\Program Files\\Mozilla
			// Firefox\\firefox.exe");
			// FirefoxBinary firefoxbin = new
			// FirefoxBinary(pathToFirefoxBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.startup.homepage", "about:blank");
			firefoxProfile.setPreference("startup.homepage_welcome_url", "about:blank");
			firefoxProfile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
			// firefoxProfile.setEnableNativeEvents(true);

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			// capabilities.setCapability(FirefoxDriver.BINARY, firefoxbin);
			capabilities.setCapability("marionette", true);
			capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			// System.setProperty("webdriver.gecko.driver", driverPathFirefox);
			// driver = new FirefoxDriver(firefoxbin, firefoxProfile,
			// capabilities);
			// driver = new FirefoxDriver(firefoxbin, firefoxProfile);
			// driver = new FirefoxDriver(capabilities);
			driver = new RemoteWebDriver(new URL(utilities.ReadProperties.getProperty(UIPropertie, location, "node1")), capabilities);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(implicitWait), TimeUnit.SECONDS);

			/*
			 * System.setProperty("webdriver.gecko.driver", driverPathFirefox);
			 * 
			 * File pathToFirefoxBinary = new
			 * File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			 * FirefoxBinary firefoxbin = new
			 * FirefoxBinary(pathToFirefoxBinary); FirefoxProfile firefoxProfile
			 * = new FirefoxProfile();
			 * firefoxProfile.setPreference("browser.startup.homepage",
			 * "about:blank");
			 * firefoxProfile.setPreference("startup.homepage_welcome_url",
			 * "about:blank"); firefoxProfile.setPreference(
			 * "startup.homepage_welcome_url.additional", "about:blank");
			 * firefoxProfile.setEnableNativeEvents(true);
			 * 
			 * DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			 * capabilities.setCapability("marionette", true);
			 * capabilities.setCapability(FirefoxDriver.BINARY, firefoxbin);
			 * capabilities.setCapability(FirefoxDriver.PROFILE,
			 * firefoxProfile);
			 * 
			 * //driver = new FirefoxDriver(firefoxbin, firefoxProfile,
			 * capabilities); ////driver = new FirefoxDriver(firefoxbin,
			 * firefoxProfile); driver = new FirefoxDriver(capabilities);
			 * //driver = new RemoteWebDriver(new
			 * URL("http://localhost:5566/wd/hub"), capabilities);
			 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 */
		} else if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser1"))) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			driver = new ChromeDriver(capabilities);
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:5566/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(implicitWait), TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser3"))) {
			System.setProperty("webdriver.ie.driver", driverPathIE);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			capabilities.setCapability("EnableNativeEvents", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);
			capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("disable-popup-blocking", true);
			capabilities.setCapability("enablePersistentHover", true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("browserstack.ie.enablePopups", "false");
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "ignore");
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, "false");
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability("browserstack.ie.noFlash", "true");

			// If IE fail to work, please remove this line and remove enable
			// protected mode for all the 4 zones from Internet options
			// driver = new InternetExplorerDriver(dc);
			driver = new RemoteWebDriver(new URL(utilities.ReadProperties.getProperty(UIPropertie, location, "node1")), capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(implicitWait), TimeUnit.SECONDS);
		}

		/*
		 * //System.setProperty("webdriver.ie.driver", driverPath
		 * +"\\exe\\IEDriverServer.exe");
		 * System.setProperty("webdriver.ie.driver", driverPath); driver = new
		 * Internet ExplorerDriver();
		 * //System.setProperty("webdriver.chrome.driver", driverPath); //driver
		 * = new ChromeDriver(); //driver = new FirefoxDriver();
		 */
	//	System.out.println(props.getProperty("ProdEnv"));
		switch (environment.toUpperCase()) {
		case environmentName1:
			driver.navigate().to(baseurlEnvironmentOne);
			break;
		case environmentName2:
			driver.navigate().to(baseurlEnvironmentTwo);
			break;
		case environmentName3:
			driver.navigate().to(baseurlEnvironmentThree);
			break;
		case environmentName4:
			driver.navigate().to(baseurlEnvironmentFour);
			break;
		case environmentName5:
			// driver.navigate().to(baseurlEnvironmentThree);
			break;
		case environmentName6:
			// keyValueNumber = 2;
			break;
		case environmentName7:
			// keyValueNumber = 3;
			break;
		default:
			Assert.fail(environment + "dosen't exsists");
		}
		/*
		 * if(environment.contains("Environment 1"))
		 * driver.navigate().to("Environment 1 URL"); else
		 * if(environment.contains("Environment 2"))
		 * driver.navigate().to("Environment 2 URL"); else
		 * if(environment.contains("Environment 3"))
		 * driver.navigate().to("Environment 3 URL"); else
		 * if(environment.contains("Environment 4"))
		 * driver.navigate().to("Environment 4 URL"); else
		 * driver.navigate().to("Default URL");
		 */
		/*
		 * if(browser.equalsIgnoreCase("Firefox"))
		 * JavaScriptExecutor.waitUntilPageLoad(driver); else
		 * driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		 */
		String pageload=utilities.ReadProperties.getProperty(configPropertie, location, "pageLoadTimeout");
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageload), TimeUnit.SECONDS);
		// JavaScriptExecutor.waitUntilPageLoad(driver);
	//	driver.manage().window().maximize();
		return driver;
	}
	
	public AndroidDriver setupAndroidDevice(String browser, String environment, String automationType)
			throws InterruptedException {

		if (browser.equalsIgnoreCase(utilities.ReadProperties.getProperty(UIPropertie, location, "browser4"))) {
			/*
			 * AppiumDriverLocalService appiumDriverLocalService =
			 * AppiumDriverLocalService .buildService(new
			 * AppiumServiceBuilder().usingPort(4723) .usingDriverExecutable(new
			 * File("C:/Program Files/nodejs/node.exe")) .withLogFile(new
			 * File("target/test.log")));
			 */
			setAppiumDriverLocalService(AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
					.usingDriverExecutable(new File(nodeJSPath)).withLogFile(new File("target/test.log"))));
			getAppiumDriverLocalService().start();

			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileDeviceName);
			// capabilities.setCapability(MobileCapabilityType.PLATFORM,
			// "Windows");
			// capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
			// "Android");

			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);

			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVersion);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);

			capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

			try {

				androidDriver = new AndroidDriver(new URL(utilities.ReadProperties.getProperty(UIPropertie, location, "node2")), capabilities);
				// driver.manage().timeouts().implicitlyWait(10,
				// TimeUnit.SECONDS);
				androidDriver.manage().timeouts().implicitlyWait(Long.parseLong(implicitWait), TimeUnit.SECONDS);
			} catch (Exception me) {
				me.printStackTrace();
			}
		}

		/*
		 * //System.setProperty("webdriver.ie.driver", driverPath
		 * +"\\exe\\IEDriverServer.exe");
		 * System.setProperty("webdriver.ie.driver", driverPath); driver = new
		 * Internet ExplorerDriver();
		 * //System.setProperty("webdriver.chrome.driver", driverPath); //driver
		 * = new ChromeDriver(); //driver = new FirefoxDriver();
		 */
		/*
		 * if(environment.contains("Environment 1"))
		 * driver.navigate().to("Environment 1 URL"); else
		 * if(environment.contains("Environment 2"))
		 * driver.navigate().to("Environment 2 URL"); else
		 * if(environment.contains("Environment 3"))
		 * driver.navigate().to("Environment 3 URL"); else
		 * if(environment.contains("Environment 4"))
		 * driver.navigate().to("Environment 4 URL"); else
		 * driver.navigate().to("Default URL");
		 */

		// androidDriver.manage().timeouts().pageLoadTimeout(300,
		// TimeUnit.SECONDS);
		// androidDriver.manage().window().maximize();
		return androidDriver;
	}

	public IOSDriver setupiOSDevice(String browser, String environment, String automationType)
			throws MalformedURLException, InterruptedException {

		// AppiumDriverLocalService service = AppiumDriverLocalService
		// .buildService(new
		// AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(4727));
		// service.start();
		//
		// if (service == null || !service.isRunning()) {
		// throw new AppiumServerHasNotBeenStartedLocallyException("An appium
		// server node is not started!");
		// }

		// AppiumDriverLocalService appium =
		// AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		// .withAppiumJS(new
		// File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
		// .usingPort(4727).withIPAddress("127.0.0.1"));
		// appium.start();
		// Map<String, String> env = new HashMap<>(System.getenv());
		// env.put("PATH", "/usr/local/bin:" + env.get("PATH"));
		// AppiumServiceBuilder builder = new AppiumServiceBuilder()
		// .withIPAddress("127.0.0.1")
		// .usingPort(4723)
		// .withEnvironment(env)
		// .usingDriverExecutable(new File("/usr/local/bin/node"))
		// .withAppiumJS(new
		// File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
		//
		// AppiumDriverLocalService service =
		// AppiumDriverLocalService.buildService(builder);
		// service.start();
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION,"1.6.5");

		// Set the platform name
		capabilities.setCapability("deviceName", utilities.ReadProperties.getProperty(UIPropertie, location, "iOSDeviceName1"));
		capabilities.setCapability("udid", utilities.ReadProperties.getProperty(UIPropertie, location, "udid"));
		capabilities.setCapability("app", utilities.ReadProperties.getProperty(UIPropertie, location, "app"));
		capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability("platformName",  utilities.ReadProperties.getProperty(UIPropertie, location, "iOSDevice"));

		// Set Version for simulator
		// capabilities.setCapability("platformVersion", "11.1");

		// Set the device Name
		// capabilities.setCapability("deviceName", "iPhone X");

		// Set device name

		// set UDID for iPhone 7

		// capabilities.setCapability("fullReset", "true");

		// Set ipa app file path

		// bundle ID
		// capabilities.setCapability("bundleID","ppp.SafariLauncher");
		// capabilities.setCapability("browserName","Safari");

		// capabilities.setCapability("nativeWebTap","true");
		// capabilities.setCapability("startIWDP","true");
		capabilities.setCapability("autoAcceptAlerts", true);

		// timeout

		// start the session with host/port. Make sure it matches with Appium
		// server you start at
		iOSDriver = new IOSDriver(new URL(utilities.ReadProperties.getProperty(UIPropertie, location, "node3")), capabilities);
		// iOSDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		return iOSDriver;

	}

}
