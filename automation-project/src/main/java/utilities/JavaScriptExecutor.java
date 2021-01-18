package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptExecutor 
{
	public static String getValueForJavaScript(WebDriver driver,String javaScript)
	{
		System.out.println("Testing");
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	return (String) js.executeScript(javaScript);
	}
	
	public static void clickElementUsingJavaScript(WebDriver driver,WebElement element)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", element);
	}
	
	public static void waitUntilPageLoad(WebDriver driver) throws InterruptedException
	{
		new WebDriverWait(driver,300).until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		Thread.sleep(2000);
	}

}
