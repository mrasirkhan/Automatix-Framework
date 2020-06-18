package excel.util;

public class AutomationUtil
{

	public static boolean isWindowsOs()
	{
		String operatingSystem = System.getProperty("os.name");

		if (null != operatingSystem && operatingSystem.toLowerCase().contains("windows"))
		{
			System.out.println("Windows OS");
			return true;
		}
		System.out.println("OS not windows");
		return false;
	}

	public static String getBasePath()
	{
		if (isWindowsOs())
		{
			return System.getProperty("user.dir");
		}
		else
		{
			return "/automation/repository/automationrunner";
		}
	}
	
	
	public static String getAdhocTextFilePath(String basePath)
	{
		if (isWindowsOs())
		{
			return basePath + "/../automationrunner/run/adhocrun.txt";
		}
		else
		{
			return basePath+"/run/adhocrun.txt";
		}
	}
}
