package com.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import beans.Client;
import beans.TestCase;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.MainController;

public class AllServiceHandler implements HttpHandler
{

	private static final String EMAILID = "email";
	private static final String PASSWORD = "password";


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
			Map<String, Object> dataMap = ParseQueryHandler.parseQuery(query, parameters);
			
			String contextUrl = httpExchange.getRequestURI().toString();
			String emailIdSession = null;
			if(contextUrl.contains("logout")){
				contextUrl=MainController.LOGIN_URL;
			}
			switch(contextUrl)
			{
				case MainController.LOGIN_URL:
				{
					response = buildResponseForLoginPage(httpExchange);
					break;
				}

				case MainController.VALIDATE_LOGIN_DETAILS:
				{
					emailIdSession = (String) httpExchange.getHttpContext().getAttributes().get(EMAILID);
					String emailId = (String) dataMap.get(EMAILID);
					String password = (String) dataMap.get(PASSWORD);
				
					if (null == emailId || emailId.isEmpty())
					{
						response = UserHandler.validateUser(emailIdSession, password, httpExchange);
					}
					else
					{
						response = UserHandler.validateUser(emailId, password, httpExchange);
					}
					break;
				}
				case MainController.ALL_TEST_CASE_URL:
				{
					response = TestCaseDetailsHandler.getAllTestCasesDetailsJson();
					break;
				}

				case MainController.ALL_RESULTS_URL:
				{
					response = ExecutionDetailsHandler.getAllResultsJson();
					break;
				}
				case MainController.DELETE_TEST_CASE_URL:
				{
					String s = (String) dataMap.get("uniqueId");
					long id = Long.parseLong(s);
					System.out.println("DELETE_TEST_CASE_URL " + id);
					
					response = TestCaseDetailsHandler.deleteTestCase(id);
					break;
				}
				case MainController.ADD_MANUAL_TEST_CASE_URL:
				{
					String jsonString = new Gson().toJson(dataMap);
					TestCase case1 = new Gson().fromJson(jsonString, TestCase.class);
					Set<Client> client = ClientHandler.getClient(dataMap);
					case1.setClients(client);
					response = TestCaseDetailsHandler.addNewRecords(case1);
					break;
				}
				case MainController.UPDATE_TEST_CASE_URL:
				{
					String jsonString = new Gson().toJson(dataMap);
					TestCase case1 = new Gson().fromJson(jsonString, TestCase.class);
					Set<Client> client = ClientHandler.getClient(dataMap);
					case1.setClients(client);
					response = TestCaseDetailsHandler.updateSelectedRecords(case1);
					break;
				}

				case MainController.FILTERED_TESTCASE_DATA_URL:
				{
					String clientId = (String) dataMap.get("selClientId");
					String testTypeId = (String) dataMap.get("selTestTypeId");
					String selModulesId = (String) dataMap.get("selModuleId");
					String selFeatureId = (String) dataMap.get("selFeatureId");
					response = TestCaseDetailsHandler.getFilteredTestCaseDetails(clientId, testTypeId, selModulesId, selFeatureId);
					break;
				}
				case MainController.CREATE_XML_URL:
				{
					response = ExecutionDetailsHandler.saveExecutionDetails(dataMap);
					break;
				}
				case MainController.CHARTING_DATA_URL:
				{
					response = ExecutionDetailsHandler.getSelectedExecutionDetails(dataMap);
					break;
				}
				
			}

			httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
			byte[] buf = new byte[1024];
			ByteArrayInputStream is = new ByteArrayInputStream(response.getBytes("Windows-1252"));
			int c = 0;
			while ((c = is.read(buf)) != -1)
			{
				os.write(buf, 0, c);
				os.flush();
			}
			is.close();
			os.flush();
			os.close();

		}
		catch (Exception e)
		{
			System.out.println("Execption "+e);
		}
		System.out.println("Completed !!!");
	}

	private String buildResponseForLoginPage(HttpExchange httpExchange)
	{

		StringBuilder contentBuilder = new StringBuilder();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("src/main/resources/html/login.html"));
			String str;
			while ((str = in.readLine()) != null)
			{
				contentBuilder.append(str);
			}
			httpExchange.getHttpContext().getAttributes().put(EMAILID, "");
		}
		catch (IOException e)
		{
		}
		String content = contentBuilder.toString();

		System.out.println(content);
		content = content.replaceAll("ERROR_MESSAGE", "");
		return content;

	}

	
}
