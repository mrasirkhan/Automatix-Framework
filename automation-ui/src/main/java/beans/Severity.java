package beans;

import java.util.Set;

public class Severity {
	private long severityId;
	private String severityName;
	
	private Set<TestCase> testcases;
	
	public long getSeverityId() {
		return severityId;
	}
	public void setSeverityId(long severityId) {
		this.severityId = severityId;
	}
	public String getSeverityName() {
		return severityName;
	}
	public void setSeverityName(String severityName) {
		this.severityName = severityName;
	}
	
	
	public Set<TestCase> getTestcases() {
		return testcases;
	}
	public void setTestcases(Set<TestCase> testcases) {
		this.testcases = testcases;
	}
	
	
	
	
	
	
}
