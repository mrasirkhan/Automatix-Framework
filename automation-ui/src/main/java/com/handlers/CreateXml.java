package com.handlers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import beans.Browser;
import beans.Client;
import beans.EnvironmentDetails;
import beans.TestCase;
import beans.TestType;

import com.dao.BrowserDao;
import com.dao.ClientDao;
import com.dao.EnvironmentDao;
import com.dao.TestCaseDao;
import com.dao.TestTypeDao;
import com.util.AutomationUtil;

public class CreateXml
{

	public static String createXml(Map<String, Object> selectedDataMap) throws ParserConfigurationException, TransformerException, IOException
	{

		String selTestId = (String) selectedDataMap.get("selTestId");
		String selBrowser = (String) selectedDataMap.get("selBrowser");
		String selEnvironment = (String) selectedDataMap.get("selEnvironment");
		String selTestTypeId = (String) selectedDataMap.get("selTestTypeId");
		String selectAllFlag = (String) selectedDataMap.get("selectAllFlag");
		String selClientId = (String) selectedDataMap.get("selClientId");
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc.setXmlStandalone(true);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;

			transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMImplementation domImpl = doc.getImplementation();

			List<TestCase> filteredTestCase = getFilteredTestCaseList(selTestId);

			System.out.println("---------filteredTestCase--------" + filteredTestCase);
			DocumentType doctype = domImpl.createDocumentType("doctype", "", "http://testng.org/testng-1.0.dtd");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
			Attr attr;
			Element rootElement = doc.createElement("suite");
			if(Integer.valueOf(selClientId) != 2)
			{
				doc.appendChild(rootElement);
				attr = doc.createAttribute("name");
				attr.setValue("Adhoc Run Test Suite");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("configfailurepolicy");
				attr.setValue("continue");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("parallel");
				attr.setValue("methods");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("thread-count");
				attr.setValue("5");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("verbose");
				attr.setValue("3");
				rootElement.setAttributeNode(attr);
			}
			
			else
			{
				doc.appendChild(rootElement);
				attr = doc.createAttribute("name");
				attr.setValue("Adhoc Run Test Suite");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("configfailurepolicy");
				attr.setValue("continue");
				rootElement.setAttributeNode(attr);

				attr = doc.createAttribute("verbose");
				attr.setValue("3");
				rootElement.setAttributeNode(attr);
			}
			//For test tag
			if (selBrowser != null && selTestId != null && selEnvironment != null && filteredTestCase != null)
			{
				long browserId = Long.parseLong(selBrowser);
				Browser browser = BrowserDao.getBrowserWithId(browserId);
				long environmentId = Long.parseLong(selEnvironment);
				EnvironmentDetails environment = EnvironmentDao.getEnvironmentWithId(environmentId);
				Client client = new Client();
				String clientName = null;
				if (selClientId != null && selClientId != "")
				{
					long clientId = Long.parseLong(selClientId);
					client = ClientDao.getClientWithId(clientId);
					clientName = client.getClientName();
				}

				long testTypeId = Long.parseLong(selTestTypeId);
				TestType testType = TestTypeDao.getTestTypeWithId(testTypeId);
				String testTypeName = testType.getTestTypeName();

				if (filteredTestCase != null)
				{
					Element test = doc.createElement("test");
					rootElement.appendChild(test);
					attr = doc.createAttribute("name");
					attr.setValue("Test on " + browser.getBrowserName());
					test.setAttributeNode(attr);

					// For parameter tag
					Element parameter = doc.createElement("parameter");
					test.appendChild(parameter);
					attr = doc.createAttribute("name");
					attr.setValue("browser");
					parameter.setAttributeNode(attr);

					attr = doc.createAttribute("value");
					attr.setValue(browser.getBrowserName());
					parameter.setAttributeNode(attr);

					Element parameter2 = doc.createElement("parameter");
					test.appendChild(parameter2);
					attr = doc.createAttribute("name");
					attr.setValue("environment");
					parameter2.setAttributeNode(attr);

					attr = doc.createAttribute("value");
					attr.setValue(environment.getEnvironmentName());
					parameter2.setAttributeNode(attr);

					if (clientName != null)
					{
						Element parameter3 = doc.createElement("parameter");
						test.appendChild(parameter3);
						attr = doc.createAttribute("name");
						attr.setValue("clientName");
						parameter3.setAttributeNode(attr);
						attr = doc.createAttribute("value");
						attr.setValue(client.getClientName());
						parameter3.setAttributeNode(attr);
					}

					String cdataValue = "";
					if (selectAllFlag.equals("true"))
					{
						// For method-selectors tag
						Element methodSelectors = doc.createElement("method-selectors");
						test.appendChild(methodSelectors);

						//For method-selector tag
						Element methodSelector = doc.createElement("method-selector");
						methodSelectors.appendChild(methodSelector);

						// Script tag       
						Element script = doc.createElement("script");
						methodSelector.appendChild(script);
						attr = doc.createAttribute("language");
						attr.setValue("beanshell");
						script.setAttributeNode(attr);

						// Cdata section
						//        return groups.containsKey("Release 1") && groups.containsKey("Transaction");

						cdataValue = createCdataTag(filteredTestCase, clientName, testTypeName);
						CDATASection cdata = doc.createCDATASection(cdataValue);
						script.appendChild(cdata);
					}
					Element classes = doc.createElement("classes");
					test.appendChild(classes);

					Set<String> featureNameSet = new HashSet<String>();

					for (TestCase test1 : filteredTestCase)
					{
						featureNameSet.add(test1.getFeature().getFeatureName());
					}

					for (String feature : featureNameSet)
					{
						Element childClass = doc.createElement("class");
						classes.appendChild(childClass);
						attr = doc.createAttribute("name");
						String scriptName = feature.replaceAll("\\-", "").replaceAll("\\s+", "");
						String atomationType = clientName.replaceAll("\\s+", "").toLowerCase();
						attr.setValue("testscripts." + atomationType + "."+ scriptName + "Scripts");
						childClass.setAttributeNode(attr);

						if (selectAllFlag.equals("false"))
						{
							Set<String> scriptNameSet = getScriptNameOnFeature(filteredTestCase, feature);
							Element methodsTag = doc.createElement("methods");
							childClass.appendChild(methodsTag);
							if (!scriptNameSet.isEmpty())
							{
								for (String testName : scriptNameSet)
								{
									Element includeTag = doc.createElement("include");
									attr = doc.createAttribute("name");
									String methodName = testName.replaceAll("\\s+", "");
									attr.setValue(methodName);
									includeTag.setAttributeNode(attr);
									methodsTag.appendChild(includeTag);
								}
							}

						}
					}
					String xmlContent = writeXml(doc, transformer);
					return xmlContent;
				}
			}
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
			return "";
		}
		return "";

	}

	private static String createCdataTag(List<TestCase> testCaseList, String clientName, String testTypeName)
	{
		String cdataValue = "";
		Set<String> moduleNameSet = new HashSet<>();
		for (TestCase testCase : testCaseList)
		{
			String moduleName = testCase.getModule().getModuleName();
			if (moduleName != null)
			{
				moduleNameSet.add(testCase.getModule().getModuleName());
			}
		}
		for (String module : moduleNameSet)
		{

			if (module != null && cdataValue != "")
			{
				cdataValue = " || " + cdataValue;
			}
			if (clientName == null)
			{
				cdataValue = "groups.containsKey(" + '"' + module + '"' + ")" + " && " + "groups.containsKey(" + '"' + testTypeName + '"' + ")" + cdataValue;

			}
			else
			{
				cdataValue = "groups.containsKey(" + '"' + module + '"' + ")" + " && " + "groups.containsKey(" + '"' + testTypeName + '"' + ")" + " && " + "groups.containsKey(" + '"' + clientName + '"' + ")" + cdataValue;
			}
		}

		System.out.println("cdataValue== " + cdataValue);
		cdataValue = "return " + cdataValue + ";";
		return cdataValue;
	}

	private static String writeXml(Document doc, Transformer transformer) throws TransformerException, IOException
	{
		DOMSource source = new DOMSource(doc);
		try
		{
			String path = AutomationUtil.getBasePath();
			System.out.println("------filepath-----" + path);

			StringWriter writer = new StringWriter();
			StreamResult consoleResult = new StreamResult(writer);


			transformer.transform(source, consoleResult);

			return writer.toString();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in writeXml method " + e);
		}
		return null;
	}



	private static Set<String> getScriptNameOnFeature(List<TestCase> testCaseList, String feature)
	{

		Set<String> scriptNameSet = new HashSet<String>();
		for (TestCase test : testCaseList)
		{
			if (feature.equalsIgnoreCase(test.getFeature().getFeatureName()))
			{
				if (scriptNameSet != null)
				{
					scriptNameSet.add(test.getScriptName());
				}
			}
		}
		return scriptNameSet;
	}

	private static List<TestCase> getFilteredTestCaseList(String selTestId)
	{
		TestCase testcase = new TestCase();
		List<TestCase> filteredTestCase = new ArrayList<>();
		if (selTestId != null)
		{
			for (String testId : selTestId.split(","))
			{
				testcase = TestCaseDao.getTestCaseWithId(Long.parseLong(testId));
				if (testcase != null)
				{
					filteredTestCase.add(testcase);
				}
			}
		}
		return filteredTestCase;
	}
}
