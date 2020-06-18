package beans;

import java.util.Set;

public class Module {
	private long moduleId;
	private String moduleName;
	private Set<TestCase> testcases;
	
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Set<TestCase> getTestcases() {
		return testcases;
	}
	public void setTestcases(Set<TestCase> testcases) {
		this.testcases = testcases;
	}

	
	
}
