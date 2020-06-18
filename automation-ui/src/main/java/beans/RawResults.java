package beans;

import java.util.Date;


public class RawResults
{
	private long resultId;
	private String scriptName;
	private String status;
	private String testOutcome;
	private String screenshotPath;
	private Date startTime;
	private Date endTime;
	private long totalScriptTime;
	private ExecutionDetails executionDetails;
	

	public long getResultId() {
		return resultId;
	}

	public void setResultId(long resultId) {
		this.resultId = resultId;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestOutcome() {
		return testOutcome;
	}

	public void setTestOutcome(String testOutcome) {
		this.testOutcome = testOutcome;
	}

	public String getScreenshotPath() {
		return screenshotPath;
	}

	public void setScreenshotPath(String screenshotPath) {
		this.screenshotPath = screenshotPath;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	

	public long getTotalScriptTime()
	{
		return totalScriptTime;
	}

	public void setTotalScriptTime(long totalScriptTime)
	{
		this.totalScriptTime = totalScriptTime;
	}

	public String getScriptName()
	{
		return scriptName;
	}

	public void setScriptName(String scriptName)
	{
		this.scriptName = scriptName;
	}

	public ExecutionDetails getExecutionDetails()
	{
		return executionDetails;
	}

	public void setExecutionDetails(ExecutionDetails executionDetails)
	{
		this.executionDetails = executionDetails;
	}


}
