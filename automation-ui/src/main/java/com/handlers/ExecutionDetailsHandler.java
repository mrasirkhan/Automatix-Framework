package com.handlers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Browser;
import beans.Client;
import beans.EnvironmentDetails;
import beans.ExecutionDetails;
import beans.RawResults;
import beans.TestCase;
import beans.TestType;

import com.dao.BrowserDao;
import com.dao.ClientDao;
import com.dao.EnvironmentDao;
import com.dao.ExecutionDetailsDao;
import com.dao.ResultsDao;
import com.dao.TestCaseDao;
import com.dao.TestTypeDao;

public class ExecutionDetailsHandler
{

	public static String saveExecutionDetails(Map<String, Object> selectedTestcaseData)
	{
		String loggedInUser = (String) selectedTestcaseData.get("email");
		String selBrowser = (String) selectedTestcaseData.get("selBrowser");
		String selEnvironment = (String) selectedTestcaseData.get("selEnvironment");
		String selTestTypeId = (String) selectedTestcaseData.get("selTestTypeId");
		String selClientId = (String) selectedTestcaseData.get("selClientId");
		String shareToEmails = (String) selectedTestcaseData.get("sendToEmailIds");
		String selTestId = (String) selectedTestcaseData.get("selTestId");
		ExecutionDetails executionDetails = new ExecutionDetails();

		String response = "";
		try
		{

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy kk:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("IST"));

			long testTypeId = Long.parseLong(selTestTypeId);
			long environmentId = Long.parseLong(selEnvironment);
			long browserId = Long.parseLong(selBrowser);

			TestType testType = TestTypeDao.getTestTypeWithId(testTypeId);
			EnvironmentDetails environment = EnvironmentDao.getEnvironmentWithId(environmentId);
			Browser browser = BrowserDao.getBrowserWithId(browserId);
			Client client = new Client();
			String xmlContent = CreateXml.createXml(selectedTestcaseData);
			System.out.println("----------xmlContent------------" + xmlContent);
			if (selClientId != null && selClientId != "")
			{
				long clientId = Long.parseLong(selClientId);
				client = ClientDao.getClientWithId(clientId);
				executionDetails.setClient(client);
			}
			else
			{
				executionDetails.setClient(null);
			}
			executionDetails.setExecStatus("Not Started");

			if (shareToEmails == null)
			{
				shareToEmails = "";
			}
			executionDetails.setExecutionXml(xmlContent);

			Set<TestCase> testCaseSet = new HashSet<TestCase>();
			if (selTestId != null)
			{
				for (String s : selTestId.split(","))
				{
					long testId = Long.parseLong(s);
					TestCase testCase = TestCaseDao.getTestCaseWithId(testId);
					if (testCase != null)
					{
						testCaseSet.add(testCase);
					}
				}
			}
			if (testType != null && environment != null && browser != null)
			{
				executionDetails.setLoggedInUser(loggedInUser);
				executionDetails.setShareId(shareToEmails);
				executionDetails.setTestType(testType);
				executionDetails.setEnvironment(environment);
				executionDetails.setBrowser(browser);
				ExecutionDetailsDao.saveExecutionDetails(testCaseSet, executionDetails);
				response = "success";
			}

		}
		catch (Exception e)
		{
			System.out.println("Exception in saveExecutionDetails " + e);
			response = "Failed";
		}
		return response;
	}

	public static String getSelectedExecutionDetails(Map<String, Object> dataMap)
	{
		String str = (String) dataMap.get("selExecutionId");
		if (str != null)
		{

			long selExecutionId = Long.parseLong(str);
			ExecutionDetails executionDetails = ExecutionDetailsDao.getExecutionDetails(selExecutionId);
			Set<RawResults> rawResults = executionDetails.getRawResults();
			Map<String, String> scriptStatusMap = new HashMap<String, String>();
			int executedModuleCount = 0;
			int executedFeatureCount = 0;
			int executedTestCaseCount = 0;
			int executedScriptCount = 0;
			long totalScriptTime = 0;
			long executionTimePerTestcase = 0;
			for (RawResults results : rawResults)
			{
				scriptStatusMap.put(results.getScriptName(), results.getStatus());
				totalScriptTime = totalScriptTime + results.getTotalScriptTime();
			}

			Set<String> selectedModuleSet = new HashSet<String>();
			Set<String> selectedFeatureSet = new HashSet<String>();
			int scriptPassCount = 0;
			int scriptFailCount = 0;
			int notRunCount = 0;
			Set<TestCase> executedTestcases = executionDetails.getTestCase();

			JSONArray jsonArray = new JSONArray();
			try
			{
				for (TestCase test : executedTestcases)
				{
					String script1 = test.getScriptName();
					if (scriptStatusMap.containsKey(script1))
					{
						scriptPassCount = getStatusCount(scriptStatusMap, script1, scriptPassCount, "PASS");
						scriptFailCount = getStatusCount(scriptStatusMap, script1, scriptFailCount, "FAIL");
					}
					else
					{
						notRunCount++;
					}
					selectedModuleSet.add(test.getModule().getModuleName());
					selectedFeatureSet.add(test.getFeature().getFeatureName());
					JSONObject executedTestCaseDetailsJson = new JSONObject();
					executedTestCaseDetailsJson.put("manualTestId", test.getManualTestId());
					executedTestCaseDetailsJson.put("testObjective", test.getTestObjective());
					executedTestCaseDetailsJson.put("moduleName", test.getModule().getModuleName());
					executedTestCaseDetailsJson.put("featureName", test.getFeature().getFeatureName());
					executedTestCaseDetailsJson.put("severity", test.getSeverity().getSeverityName());
					executedTestCaseDetailsJson.put("scriptName", script1);
					executedTestCaseDetailsJson.put("status", scriptStatusMap.get(script1));

					jsonArray.put(executedTestCaseDetailsJson);
				}
				executedModuleCount = selectedModuleSet.size();
				executedTestCaseCount = executedTestcases.size();
				executedFeatureCount = selectedFeatureSet.size();
				executedScriptCount = scriptStatusMap.size();
				String totalTime = "";
				if (totalScriptTime > 0 && executedTestCaseCount > 0)
				{
					//executionTimePerTestcase in seconds
					executionTimePerTestcase = totalScriptTime / (executedTestCaseCount);

					//totalScriptTime in mins
					long totalScriptTimeMinutes = totalScriptTime / 60;
					int inSec = (int) (totalScriptTime % 60);
					if (totalScriptTimeMinutes > 0)
					{
						totalTime = totalScriptTimeMinutes + " min " + inSec + " sec";
					}
					else
					{
						totalTime = inSec + " sec";
					}
				}
				Map<String, Set<String>> moduleAndScriptMap = createModuleAndScriptMap(selectedModuleSet, executedTestcases);
				JSONArray allModuleStatusDataArray = createModuleStatusData(scriptStatusMap, moduleAndScriptMap);
				Map<String, Set<String>> featureAndScriptMap = createFeatureAndScriptMap(selectedFeatureSet, executedTestcases);
				JSONArray allFeatureStatusDataArray = createFeatureStatusData(scriptStatusMap, featureAndScriptMap);
				JSONArray selectedExecutionScriptData = getSelectedExecutionScriptData(rawResults);

				JSONObject executionDetailsJson = new JSONObject();
				executionDetailsJson.put("testCaseResultsData", jsonArray);
				executionDetailsJson.put("PassScriptCount", scriptPassCount);
				executionDetailsJson.put("FailScriptCount", scriptFailCount);
				executionDetailsJson.put("NotRunScriptCount", notRunCount);
				executionDetailsJson.put("executedModuleCount", executedModuleCount);
				executionDetailsJson.put("executedTestCaseCount", executedTestCaseCount);
				executionDetailsJson.put("executedFeatureCount", executedFeatureCount);
				executionDetailsJson.put("executedScriptCount", executedScriptCount);
				executionDetailsJson.put("totalScriptTime", totalTime);
				executionDetailsJson.put("executionTimePerTestcase", executionTimePerTestcase);
				executionDetailsJson.put("AllModuleStatusData", allModuleStatusDataArray);
				executionDetailsJson.put("AllFeatureStatusData", allFeatureStatusDataArray);
				executionDetailsJson.put("ReportScriptData", selectedExecutionScriptData);
				System.out.println("------------------executionDetailsJson----------------" + executionDetailsJson);
				return executionDetailsJson.toString();
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return "No Data";
	}

	private static JSONArray createModuleStatusData(Map<String, String> scriptStatusMap, Map<String, Set<String>> moduleAndScriptMap) throws JSONException
	{
		JSONArray allModuleStatusDataArray = new JSONArray();
		for (String module : moduleAndScriptMap.keySet())
		{
			int modulePassCount = 0;
			int moduleFailCount = 0;
			int moduleNotRunCount = 0;
			Set<String> scriptSet = moduleAndScriptMap.get(module);
			for (String script : scriptSet)
			{
				if (scriptStatusMap.get(script) != null)
				{
					modulePassCount = getStatusCount(scriptStatusMap, script, modulePassCount, "PASS");
					moduleFailCount = getStatusCount(scriptStatusMap, script, moduleFailCount, "FAIL");
				}
				else
				{
					moduleNotRunCount++;
				}
			}
			JSONObject individualModuleStatusData = new JSONObject();
			individualModuleStatusData.put("Module", module);
			individualModuleStatusData.put("Pass", modulePassCount);
			individualModuleStatusData.put("Fail", moduleFailCount);
			individualModuleStatusData.put("Not Run", moduleNotRunCount);
			allModuleStatusDataArray.put(individualModuleStatusData);
		}
		return allModuleStatusDataArray;
	}

	private static Map<String, Set<String>> createModuleAndScriptMap(Set<String> selectedModuleSet, Set<TestCase> testcases)
	{
		Map<String, Set<String>> moduleAndScriptMap = new HashMap<String, Set<String>>();
		for (String moduleName : selectedModuleSet)
		{
			if (moduleName != null && moduleName != "")
			{
				Set<String> scriptNameSet = new HashSet<>();
				for (TestCase test : testcases)
				{
					if (test.getModule().getModuleName().equals(moduleName))
					{
						scriptNameSet.add(test.getScriptName());
					}
				}
				moduleAndScriptMap.put(moduleName, scriptNameSet);
			}
		}
		return moduleAndScriptMap;
	}

	private static int getStatusCount(Map<String, String> scriptStatusMap, String script, int count, String status)
	{
		if (scriptStatusMap.get(script).equalsIgnoreCase(status))
		{
			count++;
		}
		return count;
	}

	private static Map<String, Set<String>> createFeatureAndScriptMap(Set<String> selectedFeatureSet, Set<TestCase> testcases)
	{
		Map<String, Set<String>> featureAndScriptMap = new HashMap<String, Set<String>>();
		for (String featureName : selectedFeatureSet)
		{
			if (featureName != null && featureName != "")
			{
				Set<String> scriptNameSet = new HashSet<>();
				for (TestCase test : testcases)
				{
					if (test.getFeature().getFeatureName().equals(featureName))
					{
						scriptNameSet.add(test.getScriptName());
					}
				}
				featureAndScriptMap.put(featureName, scriptNameSet);
			}
		}
		return featureAndScriptMap;
	}

	private static JSONArray createFeatureStatusData(Map<String, String> scriptStatusMap, Map<String, Set<String>> featureAndScriptMap) throws JSONException
	{
		JSONArray allFeatureStatusDataArray = new JSONArray();
		for (String feature : featureAndScriptMap.keySet())
		{
			int featurePassCount = 0;
			int featureFailCount = 0;
			int featureNotRunCount = 0;
			Set<String> scriptSet = featureAndScriptMap.get(feature);
			for (String script : scriptSet)
			{
				if (scriptStatusMap.get(script) != null)
				{
					featurePassCount = getStatusCount(scriptStatusMap, script, featurePassCount, "PASS");
					featureFailCount = getStatusCount(scriptStatusMap, script, featureFailCount, "FAIL");
				}
				else
				{
					featureNotRunCount++;
				}
			}
			JSONObject individualFeatureStatusData = new JSONObject();
			individualFeatureStatusData.put("Feature", feature);
			individualFeatureStatusData.put("Pass", featurePassCount);
			individualFeatureStatusData.put("Fail", featureFailCount);
			individualFeatureStatusData.put("Not Run", featureNotRunCount);
			allFeatureStatusDataArray.put(individualFeatureStatusData);
		}
		return allFeatureStatusDataArray;
	}

	public static String getAllResultsJson() throws JSONException
	{
		List<ExecutionDetails> results = ResultsDao.getAllResults();
		//	List<Feature> features=TestCaseDao.getAllFeatures();

		System.out.println("-----getAllResultsJson ---- " + results);

		JSONArray resultsList = new JSONArray();
		if (!results.isEmpty())
		{
			for (ExecutionDetails result : results)
			{
				JSONObject resultsJson = new JSONObject();
				resultsJson.put("executionId", result.getExecutionId());
				resultsJson.put("initiatorId", result.getLoggedInUser());
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MMM-yyyy kk:mm:ss zzz", Locale.ENGLISH);
                String str = ft.format(result.getCurrentDate());
                resultsJson.put("date", str);
				resultsJson.put("environment", result.getEnvironment().getEnvironmentName());
				resultsJson.put("browser", result.getBrowser().getBrowserName());
				resultsJson.put("testType", result.getTestType().getTestTypeName());
				resultsJson.put("client", result.getClient().getClientName());
				resultsJson.put("sharedId", result.getShareId());
				resultsJson.put("status", result.getExecStatus());
				resultsList.put(resultsJson);
			}

			String resultsFinalJson = resultsList.toString();
			System.out.println("-----getAllResultsDetailsJson ---- " + resultsFinalJson);
			return resultsFinalJson;
		}

		return "No data";
	}

	private static JSONArray getSelectedExecutionScriptData(Set<RawResults> rawResults)
	{

		JSONArray rawResultsJsonArray = new JSONArray();
		for (RawResults rr : rawResults)
		{
			JSONObject resultsJson = new JSONObject();
			try
			{
				resultsJson.put("scriptName", rr.getScriptName());
				resultsJson.put("status", rr.getStatus());
				String testOutCome = rr.getTestOutcome().replace("\n", "<br>");
				resultsJson.put("testOutcome", testOutCome);

				resultsJson.put("screenshot", rr.getScreenshotPath());
//				String str = rr.getStartTime().toString();
//				resultsJson.put("startTime", str.substring(0, str.indexOf('.')));
				SimpleDateFormat ft1 = new SimpleDateFormat ("dd-MMM-yyyy kk:mm:ss zzz", Locale.ENGLISH);
                String str1 = ft1.format(rr.getStartTime());
                resultsJson.put("startTime", str1);
				
//				str = rr.getEndTime().toString();
//				resultsJson.put("endTime", str.substring(0, str.indexOf('.')));
				SimpleDateFormat ft2 = new SimpleDateFormat ("dd-MMM-yyyy kk:mm:ss zzz", Locale.ENGLISH);
                String str2 = ft2.format(rr.getEndTime());
                resultsJson.put("endTime", str2);
                
				resultsJson.put("totalScriptTime",rr.getTotalScriptTime());
				rawResultsJsonArray.put(resultsJson);
			}
			catch (JSONException e)
			{
				System.out.println("Exception in getSelectedExecutionScriptData() " + e);
			}

		}
		return rawResultsJsonArray;
	}
}
