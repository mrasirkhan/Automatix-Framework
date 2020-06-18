package beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.json.Test;


public class ExecutionDetails
{
	private long executionId;
	private String loggedInUser;
	private String shareId;
	private TestType testType;
	private Browser browser;
	private EnvironmentDetails environment;
	private Client client;
	private String execStatus = "Not Started";	
	private Date currentDate=new Date();
	private Set<RawResults> rawResults=new HashSet<RawResults>();
	private Set<TestCase> testCase=new HashSet<TestCase>();
	private String executionXml;
	
	public long getExecutionId()
	{
		return executionId;
	}
	public void setExecutionId(long executionId)
	{
		this.executionId = executionId;
	}
	public Date getCurrentDate()
	{
		return currentDate;
	}
	public void setCurrentDate(Date currentDate)
	{
		this.currentDate = currentDate;

		SimpleDateFormat ft = new SimpleDateFormat ("dd/MMM/yyyy kk:mm:ss");
		ft.format(currentDate);
	}
	public String getLoggedInUser()
	{
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	public String getShareId()
	{
		return shareId;
	}
	
	public String getExecStatus()
	{
		return execStatus;
	}	
	
	public TestType getTestType()
	{
		return testType;
	}
	public void setTestType(TestType testType)
	{
		this.testType = testType;
	}

	public EnvironmentDetails getEnvironment()
	{
		return environment;
	}
	public void setEnvironment(EnvironmentDetails environment)
	{
		this.environment = environment;
	}
	public Client getClient()
	{
		return client;
	}
	public void setClient(Client client)
	{
		this.client = client;
	}
	public void setShareId(String shareId)
	{
		this.shareId = shareId;
	}
	public Browser getBrowser()
	{
		return browser;
	}
	public void setBrowser(Browser browser)
	{
		this.browser = browser;
	}
	public void setExecStatus(String execStatus)
	{
		this.execStatus = execStatus;
	}
	public Set<RawResults> getRawResults()
	{
		return rawResults;
	}
	public void setRawResults(Set<RawResults> rawResults)
	{
		this.rawResults = rawResults;
	}
	public Set<TestCase> getTestCase()
	{
		return testCase;
	}
	public void setTestCase(Set<TestCase> testCase)
	{
		this.testCase = testCase;
	}
	public String getExecutionXml()
	{
		return executionXml;
	}
	public void setExecutionXml(String executionXml)
	{
		this.executionXml = executionXml;
	}
	
	
	
	
	
}
