package reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;





import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import verify.TestMethodErrorBuffer;
import browsersetup.BaseClass;


public class ListenerClass extends BaseClass implements ITestListener,IInvokedMethodListener{

	private String createDirectory()
	{
		File dir = new File(System.getProperty("user.dir") + "/Screenshots/" + new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime()));

		if(dir.exists())
		{
			System.out.println("A folder with name '"+new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime())+"' already exist");
		}
		else
		{
			dir.mkdir();
		}
		return dir.getPath();
	}

	public void onStart(ITestContext result) {
		System.out.println("Start Of Execution(TEST)->"+result.getName());
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test Started->"+result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		Object currentClass = result.getInstance();
		if(((BaseClass) currentClass).getAssertMessage() != null)
		{
			for(String eachString : ((BaseClass) currentClass).getAssertMessage())
				Reporter.log(eachString);
		}
		System.out.println("Test Pass->"+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed->"+result.getName());
		//WebDriver driver = BaseClass.driver;
		Object currentClass = result.getInstance();
		WebDriver driver = ((BaseClass) currentClass).getDriver();
		try
		{
			String path = createDirectory() + "\\";
			String file = path + result.getName() + "_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime()) +".png";
			//Implement a Switch Case Over
			/*switch(result.getInstanceName())
			{
			case "testscripts.TestScripts" :
				driver = testscripts.TestScripts.getDriver();
				break;
			case "testscripts.FundJourney" :
				driver = testscripts.FundJourney.getDriver();
				break;
			case "testscripts.DashboardScripts" :
				driver = testscripts.DashboardScripts.getDriver();
				break;
			case "testscripts.MyInvestmentPageScripts" :
				driver = testscripts.MyInvestmentPageScripts.getDriver();
				break;
			case "testscripts.ChartingPageScripts" :
				driver = testscripts.ChartingPageScripts.getDriver();
				break;
			case "testscripts.ActivityPageScripts" :
				driver = testscripts.ActivityPageScripts.getDriver();
				break;
			case "testscripts.DocumentsPageScripts" :
				driver = testscripts.DocumentsPageScripts.getDriver();
				break;
			case "testscripts.BuyFundScripts" :
				driver = testscripts.BuyFundScripts.getDriver();
				break;
			case "testscripts.BuyEquityScripts" :
				driver = testscripts.BuyEquityScripts.getDriver();
				break;
			case "testscripts.BuyETFScripts" :
				driver = testscripts.BuyETFScripts.getDriver();
				break;
			case "testscripts.BuyGovermentBondScripts" :
				driver = testscripts.BuyGovermentBondScripts.getDriver();
				break;
			case "testscripts.BuyCorporateBondScripts" :
				driver = testscripts.BuyCorporateBondScripts.getDriver();
				break;
			case "testscripts.SellFundScripts" :
				driver = testscripts.SellFundScripts.getDriver();
				break;
			case "testscripts.SellEquityScripts" :
				driver = testscripts.SellEquityScripts.getDriver();
				break;
			case "testscripts.SellETFScripts" :
				driver = testscripts.SellETFScripts.getDriver();
				break;
			case "testscripts.TopUpPageScripts" :
				driver = testscripts.TopUpPageScripts.getDriver();
				break;
			case "testscripts.DatabaseScripts" :
				driver = testscripts.DatabaseScripts.getDriver();
				break;
			case "testscripts.PortalAdminCSRScripts" :
				driver = testscripts.PortalAdminCSRScripts.getDriver();
				break;
			case "testscripts.WithdrawalScripts" :
				driver = testscripts.WithdrawalScripts.getDriver();
				break;
			default :
				System.out.println("No Driver Found");
			}*/	
			if(((BaseClass) currentClass).getClientName().equals("Web Portal"))
				ReportBaseClass.takeSnapShot(driver,file);
			if(((BaseClass) currentClass).getAssertMessage() != null)
			{
				for(String eachString : ((BaseClass) currentClass).getAssertMessage())
					Reporter.log(eachString);
			}
			if(((BaseClass) currentClass).getClientName().equals("Web Portal"))
				Reporter.log(file);
			else if(((BaseClass) currentClass).getClientName().equals("Web Services"))
				Reporter.log("NA");
			else if(((BaseClass) currentClass).getClientName().equals("Mobile"))
				Reporter.log("NA");
			//Reporter.log(file.replace("D:\\Repository\\d2cautomation", "\\"+"\\10.72.167.129"));
			//Reporter.log("<a href=/screenshots/" + file + "><img src=/screenshots/" + file + " style=width:250px;height:250px;/>" + file + "</a><br/>");

			if(!result.getName().equalsIgnoreCase("setup") && !result.getName().equalsIgnoreCase("quit"))
			{
				List<Throwable> lThrowable = TestMethodErrorBuffer.get();

				//if there are verification failures 
				if(lThrowable != null) //lThrowable.size() > 0
				{
					int size = lThrowable.size();

					StringBuffer failureMessage = new StringBuffer("Steps Failed (").append(size).append(")\n");
					StringBuffer fullStack = new StringBuffer();

					for(int i =0 ; i < size-1; i++)
					{	
						failureMessage.append("(").append(i+1).append(")").append(lThrowable.get(i).getClass().getName()).append(":").append(lThrowable.get(i).getMessage()).append("\n");						
						fullStack.append("Failure ").append(i+1).append(" of ").append(size).append("\n");	
						fullStack.append(Utils.stackTrace(lThrowable.get(i),false)[1]).append("\n");
					}

					fullStack.append("Failure ").append(size).append(" of ").append(size).append("\n");
					Throwable last = lThrowable.get(size-1);					
					failureMessage.append("(").append(size).append(")").append(last.getClass().getName()).append(":").append(last.getMessage()).append("\n\n");

					fullStack.append(last.toString());

					result.setThrowable(new Throwable(failureMessage.toString() + fullStack.toString()));
					result.getThrowable().setStackTrace(last.getStackTrace());


				}
				else
				{
					if(result.getThrowable() != null)
					{
						lThrowable = new ArrayList<Throwable>();
						lThrowable.add(result.getThrowable());
						int size = lThrowable.size();
						if(size == 1)
						{
							StringBuffer failureMessage = new StringBuffer("Steps Failed (").append(size).append(")\n");
							result.setThrowable(new Throwable(failureMessage.toString() + lThrowable.get(0).toString().replace("java.lang.AssertionError:", "")));
						}
					}
				}

				TestMethodErrorBuffer.remove(); // remove stale
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped->"+result.getName());
		TestMethodErrorBuffer.remove();
	}

	public void onFinish(ITestContext result) {
		System.out.println("END Of Execution(TEST)->"+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) 
	{
		// TODO Auto-generated method stub
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult iTResult) 
	{
		if(method.isTestMethod())
		{	
			if(!iTResult.getName().equalsIgnoreCase("setup") && !iTResult.getName().equalsIgnoreCase("quit"))
			{
				if(TestMethodErrorBuffer.get()!=null)
				{
					TestMethodErrorBuffer.set(new ArrayList<Throwable>());
					throw new RuntimeException("Stale error buffer detected!");
				}
				System.out.println("Error Buffer Cleared Before Invocation for -> "+iTResult.getName());
				//TestMethodErrorBuffer.set(new ArrayList<Throwable>()); // each test method will have its own error buffer
			}
		}

	}



}
