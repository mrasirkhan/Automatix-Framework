package utilities;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class WindowHandleSupport 
{
	public static String getCurrentWindowHandle(WebDriver driver)
	{
		return driver.getWindowHandle();
	}

/*	public static WebDriver getRequiredWindowDriver(WebDriver driver,String pageTitle) throws InterruptedException
	{
		WebDriver reuiredWindowDriver = null;
    	Thread.sleep(2000);
    	Set<String> windowHandles = driver.getWindowHandles();
    	for(String eachWindowHandle : windowHandles)
    	{
    		driver.switchTo().window(eachWindowHandle);
    		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    		String windowTitle = driver.getTitle();
    		if( windowTitle.startsWith(pageTitle)  )
    		{   			
    			reuiredWindowDriver = driver;
    			break;
    		}
    	}
    	return reuiredWindowDriver;
	}*/
	
	public static WebDriver getRequiredWindowDriverWithHandle(WebDriver driver,String pageHandle) throws InterruptedException
	{
		System.out.println("Testing");
		WebDriver reuiredWindowDriver = null;
    	Thread.sleep(2000);
    	Set<String> windowHandles = driver.getWindowHandles();
    	for(String eachWindowHandle : windowHandles)
    	{
    		driver.switchTo().window(eachWindowHandle);
    		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    		if( eachWindowHandle.equals(pageHandle)  )
    		{   			
    			reuiredWindowDriver = driver;
    			break;
    		}
    	}
    	return reuiredWindowDriver;
	}
	
	
	public static WebDriver getRequiredWindowDriverWithPageTitle(WebDriver driver,String pageTitle) throws InterruptedException
	{
		WebDriver reuiredWindowDriver = null;
    	Thread.sleep(2000);
    	String windowTitle = null;
    	Set<String> windowHandles = driver.getWindowHandles();
    	for(String eachWindowHandle : windowHandles)
    	{
    		driver.switchTo().window(eachWindowHandle);
    		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    		windowTitle = driver.getTitle();
    		if( windowTitle.equals(pageTitle) || windowTitle.startsWith(pageTitle) )
    		{   			
    			reuiredWindowDriver = driver;
    			break;
    		}
    	}
    	return reuiredWindowDriver;
	}
	
	public static WebDriver switchToTheRequiredHandle(String handle,WebDriver driver)
	{
		return driver.switchTo().window(handle);
	}

}
