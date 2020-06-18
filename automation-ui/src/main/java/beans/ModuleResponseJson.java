package beans;

public class ModuleResponseJson
{
	private long moduleId;
	private String moduleName;
	private long clientId;
	private long testTypeId;

	public long getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(long moduleId)
	{
		this.moduleId = moduleId;
	}
	public String getModuleName()
	{
		return moduleName;
	}
	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}
	public long getClientId()
	{
		return clientId;
	}
	public void setClientId(long clientId)
	{
		this.clientId = clientId;
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
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + (int) (moduleId ^ (moduleId >>> 32));
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + (int) (testTypeId ^ (testTypeId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleResponseJson other = (ModuleResponseJson) obj;
		if (clientId != other.clientId)
			return false;
		if (moduleId != other.moduleId)
			return false;
		if (moduleName == null)
		{
			if (other.moduleName != null)
				return false;
		}
		else if (!moduleName.equals(other.moduleName))
			return false;
		if (testTypeId != other.testTypeId)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "ModuleResponseJson [moduleId=" + moduleId + ", moduleName=" + moduleName + ", clientId=" + clientId + ", testTypeId=" + testTypeId + "]";
	}
	
}
