package beans;

import java.util.Set;

public class Feature {
	
	private long featureId;
	private String featureName;
	
	private Set<TestCase> testcases;
	
	public Feature() {}
	
	public Feature(long featureId, String featureName, Set<TestCase> testcases) {
		super();
		this.featureId = featureId;
		this.featureName = featureName;
		this.testcases = testcases;
	}
	public long getFeatureId() {
		return featureId;
	}
	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public Set<TestCase> getTestcases() {
		return testcases;
	}
	public void setTestcases(Set<TestCase> testcases) {
		this.testcases = testcases;
	}

	@Override
	public String toString() {
		return "Feature [featureName=" + featureName + "]";
	}

	

	
	
	
}
