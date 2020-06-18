package beans;

public class FeatureResponseJson
{
	private long featureId;
	private String featureName;
	private long moduleKeyId;
	public long getFeatureId()
	{
		return featureId;
	}
	public void setFeatureId(long featureId)
	{
		this.featureId = featureId;
	}
	public String getFeatureName()
	{
		return featureName;
	}
	public void setFeatureName(String featureName)
	{
		this.featureName = featureName;
	}
	public long getModuleKeyId()
	{
		return moduleKeyId;
	}

	
	public void setModuleKeyId(long moduleKeyId)
	{
		this.moduleKeyId = moduleKeyId;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureName == null) ? 0 : featureName.hashCode());
		return result;
	}
	
	@Override
	public String toString()
	{
		return "FeatureResponseJson [featureId=" + featureId + ", featureName=" + featureName + ", moduleKeyId=" + moduleKeyId + "]";
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
		FeatureResponseJson other = (FeatureResponseJson) obj;
		if (featureName == null)
		{
			if (other.featureName != null)
				return false;
		}
		else if (!featureName.equals(other.featureName))
			return false;
		return true;
	}
	
	
	
	
	
	
}
