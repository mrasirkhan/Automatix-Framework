package com.handlers;

import hibernate.HibernateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Client;
import beans.Feature;
import beans.Module;
import beans.Severity;
import beans.TestCase;
import beans.TestType;

import com.dao.ClientDao;
import com.dao.FeatureDao;
import com.dao.ModuleDao;
import com.dao.SeverityDao;
import com.dao.TestCaseDao;
import com.dao.TestTypeDao;
import com.readExcel.ReadExcel;

public class TestCaseDetailsHandler
{
	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";
	private static final String MISSING_FIELD_MSG = "Missing value in column";

	public static String deleteTestCase(long id)
	{

		TestCaseDao.deleteTestCases(id);
		System.out.println("deleteTestCase " + id);
		return "success";
	}

	public static String updateSelectedRecords(TestCase testCase)
	{
		try
		{
			TestCaseDao.updateTestCases(testCase);
			return "Success";
		}
		catch (Exception e)
		{
			System.out.println("Exception in updateSelectedRecords()"+e);
		}
		return "failed";
	}

	public static String addNewRecords(TestCase testCase)
	{
		String response="";
		try
		{
			boolean exists = TestCaseDao.isManualTestIdExist(testCase.getManualTestId());
			if (!exists)
			{
				if (testCase.getScriptName() != null && testCase.getScriptName() != "")
				{
					testCase.setApplicableForAutomation(true);
					testCase.setAutomationStatus(true);
				}
				Severity severity = SeverityDao.getSeverityWithId(testCase.getSeverityId());
				Module module = ModuleDao.getModuleWithId(testCase.getModuleId());
				Feature feature = FeatureDao.getFeatureWithId(testCase.getFeatureId());
				TestType testType = TestTypeDao.getTestTypeWithId(testCase.getTestTypeId());
				testCase.setSeverity(severity);
				testCase.setModule(module);
				testCase.setFeature(feature);
				testCase.setTestType(testType);

				response=TestCaseDao.saveTestCase(testCase);
			}
			else
			{
				response= "Manual Test Id Already exists";
			}
		}
		catch (Exception e)
		{
			System.err.println("Exception in addNewRecords()" + e);
			response=FAILED;
		}
		return response;
	}

	public static String getAllTestCasesDetailsJson() throws JSONException
	{
		List<TestCase> testCases = TestCaseDao.getAllTestcases();
		JSONArray testCaseList = new JSONArray();
		if (!testCases.isEmpty())
		{
			for (TestCase test : testCases)
			{
				JSONObject testCase = new JSONObject();
				testCase.put("id", test.getId());
				testCase.put("manualTestId", test.getManualTestId());
				testCase.put("testObjective", test.getTestObjective());
				testCase.put("testDescription", test.getTestDescription());
				testCase.put("requirenmentNo", test.getRequirenmentNo());
				testCase.put("applicableForAutomation", test.getApplicableForAutomation());
				testCase.put("automationStatus", test.isAutomationStatus());

				testCase.put("severity", test.getSeverity().getSeverityName());
				if (test.getTestType() == null)
				{
					testCase.put("testType", "");
					testCase.put("testTypeId", "");

				}
				else
				{
					testCase.put("testType", test.getTestType().getTestTypeName());
					testCase.put("testTypeId", test.getTestType().getTestTypeId());

				}
				if (test.getModule() == null)
				{
					testCase.put("module", "");
					testCase.put("moduleId", "");
				}
				else
				{
					testCase.put("module", test.getModule().getModuleName());
					testCase.put("moduleId", test.getModule().getModuleId());
				}
				String featureId = "";

				if (test.getFeature() != null)
				{
					testCase.put("featureId", test.getFeature().getFeatureId());
					testCase.put("feature", test.getFeature().getFeatureName());

				}
				else
				{
					testCase.put("featureId", "");
					testCase.put("feature", "");
				}

				testCase.put("scriptName", test.getScriptName());
				testCase.put("severityId", test.getSeverity().getSeverityId());
				List<String> clientsList = new ArrayList();
				for (Client client : test.getClients())
				{
					String str = Long.toString(client.getClientId());
					clientsList.add(str);
				}
				testCase.put("clients", clientsList);
				testCaseList.put(testCase);
			}

			String testCaseJson = testCaseList.toString();
			System.out.println("-----getAllTestCasesDetailsJson ---- " + testCaseJson);
			HibernateUtil.closeSession();
			return testCaseJson;
		}
		return "No data";
	}

	public static String getFilteredTestCaseDetails(String clientId, String testTypeId, String moduleId, String featureId)
	{
		long selTestTypeId = Long.parseLong(testTypeId);
		JSONArray testCaseJsonArray = new JSONArray();
		Client selectedClientDetails = new Client();
		Set<TestCase> testCasesSet = new HashSet<TestCase>();
		if (clientId != null && clientId != "")
		{
			long selClientId = Long.parseLong(clientId);
			selectedClientDetails = ClientDao.getClientWithId(selClientId);
			testCasesSet = selectedClientDetails.getTestcases();
		}
		else
		{
			List<TestCase> testcaseList = TestCaseDao.getAllTestcases();
			if (!testcaseList.isEmpty())
			{
				testCasesSet = new HashSet<TestCase>(testcaseList);
			}
		}
		try
		{
			for (TestCase test : testCasesSet)
			{
				JSONObject testCaseJsonObj = new JSONObject();
				for (String mod : moduleId.split(","))
				{
					long selModuleId = Long.parseLong(mod.trim());

					for (String feature : featureId.split(","))
					{
						long selFeatureId = Long.parseLong(feature.trim());
						if (test.getTestType() != null && test.getModule() != null && test.getFeature() != null)
						{
							if (test.getTestType().getTestTypeId() == selTestTypeId && test.getModule().getModuleId() == selModuleId && test.getFeature().getFeatureId() == selFeatureId)
							{
								if (test.isAutomationStatus())
								{
									testCaseJsonObj.put("testId", test.getId());
									testCaseJsonObj.put("manualTestId", test.getManualTestId());
									testCaseJsonObj.put("testCaseObjective", test.getTestObjective());
									testCaseJsonObj.put("feature", test.getFeature().getFeatureName());
									testCaseJsonObj.put("severity", test.getSeverity().getSeverityName());
									testCaseJsonObj.put("module", test.getModule().getModuleName());
									testCaseJsonObj.put("testType", test.getTestType().getTestTypeName());
									testCaseJsonArray.put(testCaseJsonObj);
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("----Exception in getFilteredTestCaseDetails ---" + e);
		}
		System.out.println("----------testCaseJsonArray-------------" + testCaseJsonArray);
		return testCaseJsonArray.toString();
	}

	public static String validateUploadedTestcases(File file)
	{
		String response = null;
		try
		{
			List<TestCase> testCaseList = ReadExcel.readExcel(file);

			if (!testCaseList.isEmpty())
			{
				response = checkDuplicateManualTestIdInExcel(testCaseList);
				if (response.equalsIgnoreCase(SUCCESS))
				{
					response = validateManualTestId(testCaseList);
					if (response.equalsIgnoreCase(SUCCESS))
					{
						response = validateSeverityName(testCaseList);
						if (response.equalsIgnoreCase(SUCCESS))
						{
							response = validateModuleName(testCaseList);
							if (response.equalsIgnoreCase(SUCCESS))
							{
								response = validateTestTypeName(testCaseList);
								if (response.equalsIgnoreCase(SUCCESS))
								{
									response = validateFeatureName(testCaseList);
									if (response.equalsIgnoreCase(SUCCESS))
									{
										response = setTestCaseDetails(testCaseList);

									}
								}
							}
						}
					}
				}
				return response;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in validateUploadedTestcases " + e);
			return "Failed";
		}
		return "Failed";
	}

	private static String setTestCaseDetails(List<TestCase> testCaseList)
	{
		Map<String, Feature> featureMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		Map<String, Severity> severityMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		Map<String, TestType> testTypeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		Map<String, Module> moduleMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

		List<Feature> featureList = FeatureDao.getAllFeatures();
		List<Severity> severityList = SeverityDao.getAllSeverity();
		List<TestType> testTypeList = TestTypeDao.getAllTestType();
		List<Module> moduleList = ModuleDao.getAllModules();
		String savedMessage = "";
		for (Feature feature : featureList)
		{
			featureMap.put(feature.getFeatureName().trim(), feature);
		}
		for (TestType testType : testTypeList)
		{
			testTypeMap.put(testType.getTestTypeName().trim(), testType);
		}
		for (Module module : moduleList)
		{
			moduleMap.put(module.getModuleName().trim(), module);
		}
		for (Severity severity : severityList)
		{
			severityMap.put(severity.getSeverityName().trim(), severity);
		}

		try
		{
			for (TestCase testcase : testCaseList)
			{
				TestCase test = new TestCase();
				test.setManualTestId(testcase.getManualTestId());
				test.setClients(testcase.getClients());
				test.setModule(moduleMap.get(testcase.getModuleName().trim()));

				test.setTestType(testTypeMap.get(testcase.getTestTypeName().trim()));
				test.setFeature(featureMap.get(testcase.getFeatureName().trim()));
				test.setSeverity(severityMap.get(testcase.getSeverityName().trim()));
				test.setRequirenmentNo(testcase.getRequirenmentNo());

				test.setTestObjective(testcase.getTestObjective());
				test.setScriptName(testcase.getScriptName());
				if (testcase.getTestDescription() == null || testcase.getTestDescription() == "" || testcase.getTestDescription().equals("\n"))
				{
					test.setTestDescription("");
				}
				else
				{
					test.setTestDescription(testcase.getTestDescription());
				}
				if (testcase.getTestObjective() == null || testcase.getTestObjective() == "")
				{
					test.setTestObjective("");
				}
				if (testcase.getScriptName() != null && testcase.getScriptName() != "")
				{

					test.setAutomationStatus(true);
					test.setApplicableForAutomation(true);

				}
				savedMessage = TestCaseDao.saveTestCase(test);

			}
			HibernateUtil.closeSession();
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println("Exception in setTestCaseDetails()" + e);
		}

		return FAILED;
	}

	public static String getInvalidRecordRowNumber(int rowCount, String inValidRecordRowNumber)
	{
		if (inValidRecordRowNumber != "")
		{
			inValidRecordRowNumber = inValidRecordRowNumber + ",";
		}
		inValidRecordRowNumber = inValidRecordRowNumber + rowCount;
		return inValidRecordRowNumber;
	}

	private static String validateManualTestId(List<TestCase> testCaseList)
	{
		boolean exists = false;
		String inValidRecordRowNumber = "";
		String message = null;

		int rowCount = 1;
		for (TestCase test : testCaseList)
		{
			rowCount++;
			if (test.getManualTestId() != null && test.getManualTestId() != "")
			{
				exists = TestCaseDao.isManualTestIdExist(test.getManualTestId());
				if (exists)
				{
					inValidRecordRowNumber = getInvalidRecordRowNumber(rowCount, inValidRecordRowNumber);
				}
			}
			else
			{
				message = MISSING_FIELD_MSG;
				return message;
			}
		}
		if (inValidRecordRowNumber == "")
		{
			message = SUCCESS;
		}
		else
		{
			message = "Manual Test Id found in row no." + inValidRecordRowNumber + " already exists";
		}
		return message;
	}

	private static String validateSeverityName(List<TestCase> testCaseList)
	{
		String inValidRecordRowNumber = "";
		String message = null;
		int rowCount = 1;
		boolean validSeverity = false;
		for (TestCase test : testCaseList)
		{
			rowCount++;
			if (test.getSeverityName() != null && test.getSeverityName() != "")
			{
				validSeverity = SeverityDao.isValidSeverityName(test.getSeverityName());
				if (!validSeverity)
				{
					inValidRecordRowNumber = getInvalidRecordRowNumber(rowCount, inValidRecordRowNumber);
				}
			}
			else
			{
				message = MISSING_FIELD_MSG;
				return message;
			}
		}
		if (inValidRecordRowNumber == "")
		{
			message = SUCCESS;
		}
		else
		{
			message = "Invalid Severity name found in row no. " + inValidRecordRowNumber;
		}
		return message;
	}

	private static String validateModuleName(List<TestCase> testCaseList)
	{
		String inValidRecordRowNumber = "";
		String message = null;
		int rowCount = 1;
		boolean validModule = false;
		for (TestCase test : testCaseList)
		{
			rowCount++;
			if (test.getModuleName() != null && test.getModuleName() != "")
			{
				validModule = ModuleDao.isValidModuleName(test.getModuleName());
				if (!validModule)
				{
					inValidRecordRowNumber = getInvalidRecordRowNumber(rowCount, inValidRecordRowNumber);
				}
			}
			else
			{
				message = MISSING_FIELD_MSG;
				return message;
			}
		}
		if (inValidRecordRowNumber == "")
		{
			message = SUCCESS;
		}
		else
		{
			message = "Invalid Module name found in row no. " + inValidRecordRowNumber;
		}
		return message;
	}

	private static String validateTestTypeName(List<TestCase> testCaseList)
	{
		String inValidRecordRowNumber = "";
		String message = null;
		int rowCount = 1;
		boolean validTestType = false;
		for (TestCase test : testCaseList)
		{
			rowCount++;
			if (test.getTestTypeName() != null && test.getTestTypeName() != "")
			{
				validTestType = TestTypeDao.isValidTestTypeName(test.getTestTypeName());
				if (!validTestType)
				{
					inValidRecordRowNumber = getInvalidRecordRowNumber(rowCount, inValidRecordRowNumber);
				}
			}

		}
		if (inValidRecordRowNumber == "")
		{
			message = SUCCESS;
		}
		else
		{
			message = "Invalid TestType name found in row no. " + inValidRecordRowNumber;
		}
		return message;
	}

	private static String checkDuplicateManualTestIdInExcel(List<TestCase> testCaseList)
	{
		Set<String> manualTestIdSet = new HashSet<String>();
		String duplicateId = "";
		for (TestCase testcase : testCaseList)
		{
			if (null != testcase.getManualTestId() && manualTestIdSet.contains(testcase.getManualTestId()))
			{
				duplicateId = duplicateId + " " + testcase.getManualTestId();
			}
			else
			{
				manualTestIdSet.add(testcase.getManualTestId());
			}
		}
		if (duplicateId != "")
		{
			return "Duplicate Manual Test Ids found in Excel:" + duplicateId;
		}
		else
		{
			return SUCCESS;
		}
	}

	private static String validateFeatureName(List<TestCase> testCaseList)
	{
		String inValidRecordRowNumber = "";
		String message = null;
		int rowCount = 1;
		boolean validFeature = false;
		for (TestCase test : testCaseList)
		{
			rowCount++;
			if (test.getFeatureName() != null && test.getFeatureName() != "")
			{
				validFeature = FeatureDao.isValidFeatureName(test.getFeatureName());
				if (!validFeature)
				{
					inValidRecordRowNumber = getInvalidRecordRowNumber(rowCount, inValidRecordRowNumber);
				}
			}

			else
			{
				message = MISSING_FIELD_MSG;
				return message;
			}
		}
		if (inValidRecordRowNumber == "")
		{
			message = SUCCESS;
		}
		else
		{
			message = "Invalid Feature name found in row no. " + inValidRecordRowNumber;
		}
		return message;
	}
}
