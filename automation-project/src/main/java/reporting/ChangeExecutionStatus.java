package reporting;

import helpers.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

import browsersetup.BaseClass;

public class ChangeExecutionStatus extends BaseClass
{	
	public void changeExecutionStatusOfInprogressExecution() throws InterruptedException, SQLException
	{
		String dbname=utilities.ReadProperties.getProperty(databasePropertie, location, "databasename");
		List<String> executionID = DatabaseHelper.CreateDataListForAListOfRows(utilities.ReadProperties.getProperty(databasePropertie, location, "sql_execution_details"), "EXECUTION_ID", dbname, "Local");
		DatabaseHelper.updateQuery(utilities.ReadProperties.getProperty(databasePropertie, location, "sql_Exec_Status")+Integer.parseInt(executionID.get(0))+";", dbname, "Local");
	}
	
	public static void main(String[] args) throws InterruptedException, SQLException
	{
		new ChangeExecutionStatus().changeExecutionStatusOfInprogressExecution();

	}

}
