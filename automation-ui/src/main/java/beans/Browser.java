package beans;

import java.util.Set;

public class Browser {

	private long browserId;
	private String browserName;
	private Set<ExecutionDetails> executionDetails;	
	
	public long getBrowserId() {
		return browserId;
	}
	public void setBrowserId(long browserId) {
		this.browserId = browserId;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public Set<ExecutionDetails> getExecutionDetails()
	{
		return executionDetails;
	}
	public void setExecutionDetails(Set<ExecutionDetails> executionDetails)
	{
		this.executionDetails = executionDetails;
	}
	@Override
	public String toString() {
		return "Browser [browserName=" + browserName + "]";
	}

	
}
