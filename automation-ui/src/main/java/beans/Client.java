package beans;

import java.util.HashSet;
import java.util.Set;

public class Client {
	
	private long clientId;
	private String clientName;
	
	private Set<TestCase> testcases=new HashSet<TestCase>(0);
	private Set<ExecutionDetails> executionDetails;	
	
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Set<TestCase> getTestcases()
	{
		return testcases;
	}
	@Override
	public String toString() {
		return "Client [clientName=" + clientName + "]";
	}
	public void setTestcases(Set<TestCase> testcases)
	{
		this.testcases = testcases;
	}
	public Set<ExecutionDetails> getExecutionDetails()
	{
		return executionDetails;
	}
	public void setExecutionDetails(Set<ExecutionDetails> executionDetails)
	{
		this.executionDetails = executionDetails;
	}
	
	
}
