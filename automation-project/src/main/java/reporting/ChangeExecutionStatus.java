package reporting;

import helpers.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

public class ChangeExecutionStatus 
{
	public void changeExecutionStatusOfInprogressExecution() throws InterruptedException, SQLException
	{
		List<String> executionID = DatabaseHelper.CreateDataListForAListOfRows("SELECT * FROM execution_details Where Exec_Status = 'In Progress' Order By Date", "EXECUTION_ID", "vitaminshopeeautomationdb", "Local");
		DatabaseHelper.updateQuery("Update execution_details Set Exec_Status = 'Completed' Where EXECUTION_ID = "+Integer.parseInt(executionID.get(0))+";", "vitaminshopeeautomationdb", "Local");
	}
	
	public static void main(String[] args) throws InterruptedException, SQLException
	{
		new ChangeExecutionStatus().changeExecutionStatusOfInprogressExecution();

	}

}
