package beans;

import java.util.HashSet;
import java.util.Set;

public class TestCase
{
	private long id;
	private String manualTestId;
	private String testObjective;
	private String testDescription;
	private String requirenmentNo="";
	private boolean applicableForAutomation;
	private String scriptName="";
	private boolean automationStatus;
	private String severityName;
	private String moduleName;
	private String featureName;
	private String testTypeName;
	private TestType testType;
	private Module module;
	private Feature feature;
	private Severity severity;
	private long featureId;
	private long moduleId;
	private long severityId;
	private long testTypeId;
	private Set<Client>clients=new HashSet<Client>();
	private Set<ExecutionDetails>executionDetails=new HashSet<ExecutionDetails>(); 

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getManualTestId()
	{
		return manualTestId;
	}

	public void setManualTestId(String manualTestId)
	{
		this.manualTestId = manualTestId;
	}

	public String getTestObjective()
	{
		return testObjective;
	}

	public void setTestObjective(String testObjective)
	{
		this.testObjective = testObjective;
	}

	public String getTestDescription()
	{
		return testDescription;
	}

	public void setTestDescription(String testDescription)
	{
		this.testDescription = testDescription;
	}

	public boolean getApplicableForAutomation()
	{
		return applicableForAutomation;
	}

	public String getRequirenmentNo()
	{
		return requirenmentNo;
	}

	public void setRequirenmentNo(String requirenmentNo)
	{
		this.requirenmentNo = requirenmentNo;
	}

	public boolean isApplicableForAutomation()
	{
		return applicableForAutomation;
	}

	public void setApplicableForAutomation(boolean applicableForAutomation)
	{
		this.applicableForAutomation = applicableForAutomation;
	}

	public String getScriptName()
	{
		return scriptName;
	}

	public void setScriptName(String scriptName)
	{
		this.scriptName = scriptName;
	}

	public boolean isAutomationStatus()
	{
		return automationStatus;
	}

	public void setAutomationStatus(boolean automationStatus)
	{
		this.automationStatus = automationStatus;
	}

	public String getSeverityName()
	{
		return severityName;
	}

	public void setSeverityName(String severityName)
	{
		this.severityName = severityName;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getFeatureName()
	{
		return featureName;
	}

	public void setFeatureName(String featureName)
	{
		this.featureName = featureName;
	}

	public String getTestTypeName()
	{
		return testTypeName;
	}

	public void setTestTypeName(String testTypeName)
	{
		this.testTypeName = testTypeName;
	}

	public Severity getSeverity()
	{
		return severity;
	}

	public void setSeverity(Severity severity)
	{
		this.severity = severity;
	}

	public Module getModule()
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}

	public Feature getFeature()
	{
		return feature;
	}

	public void setFeature(Feature feature)
	{
		this.feature = feature;
	}

	public TestType getTestType()
	{
		return testType;
	}

	public void setTestType(TestType testType)
	{
		this.testType = testType;
	}

	public Set<Client> getClients()
	{
		return clients;
	}

	public void setClients(Set<Client> clients)
	{
		this.clients = clients;
	}

	public Set<ExecutionDetails> getExecutionDetails()
	{
		return executionDetails;
	}

	public void setExecutionDetails(Set<ExecutionDetails> executionDetails)
	{
		this.executionDetails = executionDetails;
	}

	public long getFeatureId()
	{
		return featureId;
	}

	public void setFeatureId(long featureId)
	{
		this.featureId = featureId;
	}

	public long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(long moduleId)
	{
		this.moduleId = moduleId;
	}

	public long getSeverityId()
	{
		return severityId;
	}

	public void setSeverityId(long severityId)
	{
		this.severityId = severityId;
	}

	public long getTestTypeId()
	{
		return testTypeId;
	}

	public void setTestTypeId(long testTypeId)
	{
		this.testTypeId = testTypeId;
	}

	@Override
	public String toString()
	{
		return "TestCase [id=" + id + ", manualTestId=" + manualTestId + ", testObjective=" + testObjective + ", testDescription=" + testDescription + ", requirenmentNo=" + requirenmentNo + ", applicableForAutomation=" + applicableForAutomation + ", scriptName=" + scriptName + ", automationStatus=" + automationStatus + ", severityName=" + severityName + ", moduleName=" + moduleName + ", featureName=" + featureName + ", testTypeName=" + testTypeName + ", testType=" + testType + ", module=" + module + ", feature=" + feature + ", severity=" + severity + ", featureId=" + featureId + ", moduleId=" + moduleId + ", severityId=" + severityId + ", testTypeId=" + testTypeId + ", clients=" + clients + ", executionDetails=" + executionDetails + "]";
	}

}
