package utilities;

import browsersetup.BaseClass;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MobileUtilities extends BaseClass
{
	
	public static void scrollDown(int scrollCount, AndroidDriver driver) 
	{
		int pressX = driver.manage().window().getSize().width / 2;
		int bottomY = driver.manage().window().getSize().height * 4 / 5;
		int topY = driver.manage().window().getSize().height / 8;
		for(int i=0;i < scrollCount; i++)
			scroll(pressX, bottomY, pressX, topY, driver);
	}

	private static void scroll(int fromX, int fromY, int toX, int toY, AndroidDriver driver) 
	{
		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		touchAction.longPress(fromX, fromY).moveTo(toX, toY).release().perform();
	}

}
