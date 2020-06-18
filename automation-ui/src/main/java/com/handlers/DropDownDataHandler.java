package com.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Browser;
import beans.Client;
import beans.EnvironmentDetails;
import beans.Feature;
import beans.FeatureResponseJson;
import beans.Module;
import beans.ModuleResponseJson;
import beans.Severity;
import beans.TestCase;
import beans.TestType;

import com.dao.BrowserDao;
import com.dao.ClientDao;
import com.dao.EnvironmentDao;
import com.dao.FeatureDao;
import com.dao.ModuleDao;
import com.dao.SeverityDao;
import com.dao.TestCaseDao;
import com.dao.TestTypeDao;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.MainController;

public class DropDownDataHandler implements HttpHandler
{

	@Override
	public void handle(HttpExchange httpExchange) throws IOException
	{
		try
		{
			String response = null;
			final OutputStream os = httpExchange.getResponseBody();

			InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
			Map<String, Object> parameters = new HashMap<String, Object>();
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			System.out.println("query------------ " + query);

			String contextUrl = httpExchange.getRequestURI().toString();
			System.out.println("--------DropDownDataHandler--------");
			Map<String, Object> dataMap = ParseQueryHandler.parseQuery(query, parameters);

			if (contextUrl.equals(MainController.GET_FEATURE_MODULE_DATA))
			{
				String selClientId = (String) dataMap.get("selClientId");
				String selTestTypeId = (String) dataMap.get("selTestTypeId");
				response = getFeatureAndModuleOnClientAndTestType(selClientId, selTestTypeId);
			}
			if (contextUrl.equals(MainController.GET_DROPDOWN_DATA_URL))
			{
				response = getDropDownResponseJsonData();
			}
			httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
			os.write(response.getBytes("Windows-1252"));
			os.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Completed");
	}

	public JSONObject getAllEnvironmentsData() throws JSONException
	{

		List<EnvironmentDetails> environmentList = EnvironmentDao.getAllEnvironments();
		System.out.println("-------getEnvironmentData ----------" + environmentList);
		JSONArray environmentJsonArray = new JSONArray();
		if (environmentList != null)
		{
			for (EnvironmentDetails environment : environmentList)
			{
				JSONObject environmentJson = new JSONObject();
				environmentJson.put("environmentId", environment.getEnvironmentId());
				environmentJson.put("environmentName", environment.getEnvironmentName());
				environmentJsonArray.put(environmentJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("environments", environmentJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);

		return jsonObj;
	}

	public JSONObject getAllTestTypesData() throws JSONException
	{
		List<TestType> testTypeList = TestTypeDao.getAllTestType();
		System.out.println("-------getAllTestTypesData ----------" + testTypeList);
		JSONArray testTypeJsonArray = new JSONArray();
		if (testTypeList != null)
		{
			for (TestType testType : testTypeList)
			{
				JSONObject testTypeJson = new JSONObject();
				testTypeJson.put("testTypeId", testType.getTestTypeId());
				testTypeJson.put("testTypeName", testType.getTestTypeName());
				testTypeJsonArray.put(testTypeJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("testTypes", testTypeJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	public JSONObject getAllBrowsersData() throws JSONException
	{
		List<Browser> browserList = BrowserDao.getAllBrowsers();
		System.out.println("-------getAllBrowsersData ----------" + browserList);
		JSONArray browserJsonArray = new JSONArray();
		if (browserList != null)
		{
			for (Browser browser : browserList)
			{
				JSONObject browserJson = new JSONObject();
				browserJson.put("browserId", browser.getBrowserId());
				browserJson.put("browserName", browser.getBrowserName());
				browserJsonArray.put(browserJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("browsers", browserJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	public JSONObject getAllClientsData() throws JSONException
	{
		List<Client> clientList = ClientDao.getAllClients();
		System.out.println("-------getAllClientsData ----------" + clientList);
		JSONArray clientJsonArray = new JSONArray();
		if (clientList != null)
		{
			for (Client client : clientList)
			{
				JSONObject clientJson = new JSONObject();
				clientJson.put("clientId", client.getClientId());
				clientJson.put("clientName", client.getClientName());
				clientJsonArray.put(clientJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("clients", null != clientJsonArray && clientJsonArray.length() > 0 ? clientJsonArray : null );
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	private JSONObject getAllModuleData() throws JSONException
	{
		List<Module> modulelist = ModuleDao.getAllModules();
		System.out.println("-------getAllModuleData ----------" + modulelist);
		JSONArray moduleJsonArray = new JSONArray();
		if (modulelist != null)
		{
			for (Module module : modulelist)
			{
				JSONObject moduleJson = new JSONObject();
				moduleJson.put("moduleId", module.getModuleId());
				moduleJson.put("moduleName", module.getModuleName());
				moduleJsonArray.put(moduleJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("modules", moduleJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	private JSONObject getAllSeverityData() throws JSONException
	{
		List<Severity> Severitylist = SeverityDao.getAllSeverity();
		System.out.println("-------getAllSeverityData ----------" + Severitylist);
		JSONArray severityJsonArray = new JSONArray();
		if (Severitylist != null)
		{
			for (Severity severity : Severitylist)
			{
				JSONObject severityJson = new JSONObject();
				severityJson.put("severityId", severity.getSeverityId());
				severityJson.put("severityName", severity.getSeverityName());
				severityJsonArray.put(severityJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("severities", severityJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	private JSONObject getAllFeatureData() throws JSONException
	{
		List<Feature> featurelist = FeatureDao.getAllFeatures();
		System.out.println("-------getAllFeatureData ----------" + featurelist);
		JSONArray featureJsonArray = new JSONArray();
		if (featurelist != null)
		{
			for (Feature feature : featurelist)
			{
				JSONObject featureJson = new JSONObject();
				featureJson.put("featureId", feature.getFeatureId());
				featureJson.put("featureName", feature.getFeatureName());
				featureJsonArray.put(featureJson);
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("features", featureJsonArray);
		System.out.println("-------- jsonObj--------- " + jsonObj);
		return jsonObj;
	}

	public String getDropDownResponseJsonData()
	{
		JSONArray jsonArray = new JSONArray();
		try
		{
			JSONObject environmentJson = getAllEnvironmentsData();
			JSONObject testTypesJson = getAllTestTypesData();
			JSONObject browserJson = getAllBrowsersData();
			JSONObject clientJson = getAllClientsData();
			JSONObject moduleJson = getAllModuleData();
			JSONObject featureJson = getAllFeatureData();
			JSONObject severityJson = getAllSeverityData();
			
			System.out.println("------------environmentJson ------------" + environmentJson);
			System.out.println("------------testTypesJson ------------" + testTypesJson);
			jsonArray.put(environmentJson);
			jsonArray.put(testTypesJson);
			jsonArray.put(browserJson);
			jsonArray.put(clientJson);
			jsonArray.put(moduleJson);
			jsonArray.put(featureJson);
			jsonArray.put(severityJson);
			System.out.println("-----------jsonArray -------------- " + jsonArray);
			
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonArray.toString();

	}

	public String getFeatureAndModuleOnClientAndTestType(String selClientId, String selTestTypeId)
	{
		JSONObject featureAndModuleJson = new JSONObject();
		HashSet<ModuleResponseJson> moduleResponseSet = new HashSet<ModuleResponseJson>();
		HashSet<FeatureResponseJson> featureResponseSet = new HashSet<FeatureResponseJson>();
		try
		{
			if (selClientId != null && selTestTypeId != null)
			{
				long clientId = Long.parseLong(selClientId);
				long testTypeId = Long.parseLong(selTestTypeId);
				Client selectedClientDetails = ClientDao.getClientWithId(clientId);

				if (selectedClientDetails != null)
				{
					for (TestCase test : selectedClientDetails.getTestcases())
					{
						if (test.isAutomationStatus())
						{
							if (test.getTestType()!=null && testTypeId==test.getTestType().getTestTypeId())
							{
								ModuleResponseJson moduleResponseJson = new ModuleResponseJson();
								moduleResponseJson.setModuleId(test.getModule().getModuleId());
								moduleResponseJson.setModuleName(test.getModule().getModuleName());
								moduleResponseJson.setClientId(clientId);
								moduleResponseJson.setTestTypeId(testTypeId);
								moduleResponseSet.add(moduleResponseJson);
								System.out.println(" moduleResponseSet==================" + moduleResponseSet);
								FeatureResponseJson featureResponseJson = new FeatureResponseJson();
								featureResponseJson.setFeatureId(test.getFeature().getFeatureId());
								featureResponseJson.setFeatureName(test.getFeature().getFeatureName());
								featureResponseJson.setModuleKeyId(test.getModule().getModuleId());
								featureResponseSet.add(featureResponseJson);
								System.out.println(" featureResponseSet==================" + featureResponseSet);
							}
						}
					}
				}
			}
			else
			{
				long testTypeId = Long.parseLong(selTestTypeId);
				List<TestCase> testCaseList = TestCaseDao.getAllTestcases();
				if (testCaseList != null)
				{
					for (TestCase test : testCaseList)
					{
						if (test.getTestType().getTestTypeId() == testTypeId)
						{
							ModuleResponseJson moduleResponseJson = new ModuleResponseJson();
							moduleResponseJson.setModuleId(test.getModule().getModuleId());
							moduleResponseJson.setModuleName(test.getModule().getModuleName());
							moduleResponseJson.setTestTypeId(testTypeId);
							moduleResponseSet.add(moduleResponseJson);

							FeatureResponseJson featureResponseJson = new FeatureResponseJson();
							featureResponseJson.setFeatureId(test.getFeature().getFeatureId());
							featureResponseJson.setFeatureName(test.getFeature().getFeatureName());
							featureResponseJson.setModuleKeyId(test.getModule().getModuleId());
							featureResponseSet.add(featureResponseJson);
							System.out.println(" featureResponseSet==================" + featureResponseSet);
						}
					}
				}
				
			}
			featureAndModuleJson.put("features", new Gson().toJson(featureResponseSet));
			featureAndModuleJson.put("modules", new Gson().toJson(moduleResponseSet));
			return featureAndModuleJson.toString();
		}

		catch (JSONException e)
		{
			System.out.println("----Exception in getTestcasesBasedOnSelectedClientAndTestType ---" + e);
		}

		return "Invalid Data";
	}

}
