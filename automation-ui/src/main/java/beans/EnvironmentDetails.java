package beans;

import java.util.Set;

public class EnvironmentDetails {
	
	private long environmentId;
	private String environmentName;
	private Set<ExecutionDetails> executionDetails;	
	
	public long getEnvironmentId() {
		return environmentId;
	}
	public void setEnvironmentId(long environmentId) {
		this.environmentId = environmentId;
	}
	public String getEnvironmentName() {
		return environmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
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
		return "EnvironmentDetails [environmentName=" + environmentName + "]";
	}
	
	
}
