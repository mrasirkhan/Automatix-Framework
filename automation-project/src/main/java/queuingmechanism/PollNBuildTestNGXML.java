package queuingmechanism;

import helpers.DatabaseHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

import browsersetup.BaseClass;
import reporting.DumpReportToDB;

public class PollNBuildTestNGXML extends BaseClass
{
	 String tblExecutionXML="EXECUTION_XML";
	 String tblExecutionID="EXECUTION_ID";
	 
	@SuppressWarnings("resource")
	public void pollAndBuildTestNGXML() throws InterruptedException, SQLException
	{
		System.out.println("Inside Poll and Build");
		String xmlFileContent = null;
		List<String> xmlFiles = DatabaseHelper.CreateDataListForAListOfRows(utilities.ReadProperties.getProperty(databasePropertie, location, "sql_execution_notstarted"), tblExecutionXML, databasename, dbenvironment);
		List<String> executionID = DatabaseHelper.CreateDataListForAListOfRows(utilities.ReadProperties.getProperty(databasePropertie, location, "sql_execution_notstarted"), tblExecutionID, databasename, dbenvironment);
		if(xmlFiles.size() != 0)
		{
			xmlFileContent = xmlFiles.get(0);
			try
			{
				String path = DumpReportToDB.class.getClassLoader().getResource("./").getPath();
				//path = path.replace("bin", "src");
				File txtFile = new File(path + utilities.ReadProperties.getProperty(filenamesPropertie, location, "runnertxtfile"));
				Writer output = null;
				output = new BufferedWriter(new FileWriter(txtFile));
				File xmlFile = new File(path + utilities.ReadProperties.getProperty(filenamesPropertie, location, "runnerXMLfile"));
				output = new BufferedWriter(new FileWriter(xmlFile));
				output.write(xmlFileContent);
				output.close();
				DatabaseHelper.updateQuery(utilities.ReadProperties.getProperty(databasePropertie, location, "sql_updateExec_Status")+Integer.parseInt(executionID.get(0))+";", databasename, dbenvironment);
				System.out.println("Executed");
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, SQLException
	{
		new PollNBuildTestNGXML().pollAndBuildTestNGXML();

	}

}
