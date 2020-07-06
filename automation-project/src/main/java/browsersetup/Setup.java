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
	static String baseurlEnvironmentOne = ConfigurationData.baseurlEnvironmentOne;
	static String baseurlEnvironmentTwo = ConfigurationData.baseurlEnvironmentTwo;
	static String baseurlEnvironmentThree = ConfigurationData.baseurlEnvironmentThree;
	static String baseurlEnvironmentFour = ConfigurationData.baseurlEnvironmentFour;
	static String baseurlEnvironmentFive = ConfigurationData.baseurlEnvironmentFive;
	static String baseurlEnvironmentSix = ConfigurationData.baseurlEnvironmentSix;
	
	// Mobile Application : Configuration Data
	static String nodeJSPath = ConfigurationData.nodeJSPath;
	static String mobileDeviceName = ConfigurationData.mobileDeviceName;
	static String mobilePlatformVersion = ConfigurationData.mobilePlatformVersion;
	static String appPackage = ConfigurationData.appPackage;
	static String appActivity = ConfigurationData.appActivity;

	private WebDriver driver = null;
	private AndroidDriver androidDriver = null;
	private IOSDriver iOSDriver = null;
	// static String driverPath = System.getProperty("user.dir");
	static String driverPathIE = ConfigurationData.driverPathIE;
	static String driverPathChrome = ConfigurationData.driverPathChrome;
	static String driverPathFirefox = ConfigurationData.driverPathFirefox;

	@SuppressWarnings("deprecation")
	public WebDriver setupBrowser(String browser, String environment, String clientName)
			throws InterruptedException, MalformedURLException {
		if (browser.equalsIgnoreCase("Firefox")) {

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
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
		} else if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--incognito");
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase("Internet Explorer")) {
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
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		/*
		 * //System.setProperty("webdriver.ie.driver", driverPath
		 * +"\\exe\\IEDriverServer.exe");
		 * System.setProperty("webdriver.ie.driver", driverPath); driver = new
		 * Internet ExplorerDriver();
		 * //System.setProperty("webdriver.chrome.driver", driverPath); //driver
		 * = new ChromeDriver(); //driver = new FirefoxDriver();
		 */
		switch (environment.toUpperCase()) {
		case "AMAZON US PROD ENV":
			driver.navigate().to(baseurlEnvironmentOne);
			break;
		case "AMAZON INDIA PROD ENV":
			driver.navigate().to(baseurlEnvironmentTwo);
			break;
		case "LENSKART PROD ENV":
			driver.navigate().to(baseurlEnvironmentThree);
			break;
		case "SPECSAVERS PROD ENV":
			driver.navigate().to(baseurlEnvironmentFour);
			break;
		case "RANOREX TEST ENV":
			driver.navigate().to(baseurlEnvironmentFive);
			break;
		case "EVOSYS TEST ENV":
			driver.navigate().to(baseurlEnvironmentSix);
			break;
		case "AMAZON WEB SERVICES":
			// driver.navigate().to(baseurlEnvironmentThree);
			break;
		case "ANDROID AMAZON APP":
			// keyValueNumber = 2;
			break;
		case "IOS SPEAKIT APP":
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
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		// JavaScriptExecutor.waitUntilPageLoad(driver);
		driver.manage().window().maximize();
		return driver;
	}

	public AndroidDriver setupAndroidDevice(String browser, String environment, String clientName)
			throws InterruptedException {

		if (browser.equalsIgnoreCase("Android")) {
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

				androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				// driver.manage().timeouts().implicitlyWait(10,
				// TimeUnit.SECONDS);
				androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	public IOSDriver setupiOSDevice(String browser, String environment, String clientName)
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
		capabilities.setCapability("deviceName", "iPhone");
		capabilities.setCapability("udid", "f3123c934dad6b5b326dff90fb05ab6b9d9e6e86");
		capabilities.setCapability("app", "/Users/GTC/Downloads/speakIt.ipa");
		capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability("platformName", "iOS");

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
		capabilities.setCapability("autoAcceptAlerts", "true");

		// timeout

		// start the session with host/port. Make sure it matches with Appium
		// server you start at
		iOSDriver = new IOSDriver(new URL("http://192.168.15.54:4728/wd/hub"), capabilities);
		// iOSDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		return iOSDriver;

	}

}
