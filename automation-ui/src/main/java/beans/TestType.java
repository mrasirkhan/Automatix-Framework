package beans;

import java.util.Set;

public class TestType {
	private long testTypeId;
	private String testTypeName;
	private Set<TestCase> testcases;
	private Set<ExecutionDetails> executionDetails;	
	
	public long getTestTypeId() {
		return testTypeId;
	}
	public void setTestTypeId(long testTypeId) {
		this.testTypeId = testTypeId;
	}
	public String getTestTypeName() {
		return testTypeName;
	}
	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}
	public Set<TestCase> getTestcases() {
		return testcases;
	}
	public void setTestcases(Set<TestCase> testcases) {
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
