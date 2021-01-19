package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import browsersetup.BaseClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ReadProperties;


public class DatabaseHelper extends BaseClass
{	
	static String hostautomationdb = utilities.ReadProperties.getProperty(databasePropertie, location, "hostDatabase");   //ConfigurationData.databaseHostNameautomationdb;
	static String userNameautomationdb =utilities.ReadProperties.getProperty(databasePropertie, location, "dbUsername");  // ConfigurationData.databaseUserNameautomationdb;
	static String passwordautomationdb =utilities.ReadProperties.getProperty(databasePropertie, location, "dbPassword");  // ConfigurationData.databasePasswordautomationdb;	

	/*
	 * Execute a DDL Query
	 */
	/*
	public static void executeDDLQuery(String query,String schemaName) throws InterruptedException, SQLException
	{
		Thread.sleep(2000);
		Statement stm = createDBConnection(schemaName);
		try
		{        
			stm.executeUpdate(query);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			stm.close();
		}
	}
	 */

	/*
	 * Updates a Table
	 */
	public static void updateQuery(String query,String schemaName,String environment) throws InterruptedException, SQLException
	{
		Thread.sleep(2000);
		Statement stm = createDBConnection(schemaName,environment);
		try
		{        
			stm.executeUpdate(query);
		}

		finally
		{
			stm.close();
		}
	}


	/*
	 * Returns a list of value for the database rows for the specified column (Multiple Rows & Single Column)
	 */
	public static List<String> CreateDataListForAListOfRows(String query,String tablecolumnname,String schemaName,String environment) throws InterruptedException, SQLException
	{
		List<String> dataList = new ArrayList<String>();
		Thread.sleep(2000);

		Statement stm = createDBConnection(schemaName,environment);

		try
		{        
			ResultSet rst;
			rst = stm.executeQuery(query);
			while (rst.next()) 
			{
				dataList.add(rst.getString(tablecolumnname));
			}
		}

		finally
		{
			stm.close();
		}
		return dataList;
	}

	/*
	 * Returns a List of value for the database columns specified in the List (Single Row & Multiple Columns)
	 */
	public static List<String> CreateDataListForAListOfColumns(String query,List<String> tablecolumnnames,String schemaName, String environment) throws InterruptedException, SQLException
	{
		List<String> dataList = new ArrayList<String>();
		Thread.sleep(2000);       

		Statement stm = createDBConnection(schemaName,environment);

		try
		{        
			ResultSet rst;
			rst = stm.executeQuery(query);
			while (rst.next()) 
			{
				for(String eachString : tablecolumnnames)
				{
					dataList.add(rst.getString(eachString));
				}
			}
		}

		finally
		{
			stm.close();
		}
		return dataList;
	}

	/*
	 * Returns a List of value for the database columns specified in the List (Multiple Rows & Multiple Columns)
	 */
	public static List<ArrayList<String>> createMulipleColumnDataListForMultipleRowsInDB(String query,List<String> tablecolumnnames,String schemaName, String environment) throws InterruptedException, SQLException
	{
		List<ArrayList<String>> dataListMultipleRow = new ArrayList<ArrayList<String>>();
		Thread.sleep(2000);       

		Statement stm = createDBConnection(schemaName,environment);

		try
		{        
			ResultSet rst;
			rst = stm.executeQuery(query);
			while (rst.next()) 
			{
				ArrayList<String> dataListSingleRow = new ArrayList<String>();
				for(String eachString : tablecolumnnames)
				{
					dataListSingleRow.add(rst.getString(eachString));
				}
				dataListMultipleRow.add(dataListSingleRow);
			}
		}

		finally
		{
			stm.close();
		}
		return dataListMultipleRow;
	}

	private static Statement createDBConnection(String schemaName,String environment) throws SQLException
	{
		Connection connection = null;
		if(environment.contains(utilities.ReadProperties.getProperty(databasePropertie, location, "dbEnvironment")))
		{
			try
			{
				connection = DriverManager.getConnection(hostautomationdb,userNameautomationdb,passwordautomationdb);
			}
			catch(Exception e)
			{
				System.out.println("There was a problem while establish a connection : " + e);
			}
		}
		return connection.createStatement();
	}
	
	/*public static Properties databaseprops = null;
	static 
	{
		databaseprops = ReadProperties.load(database,location);
	}
	public static String getPropertyDB(Properties props,String key)
	{		
		return props.getProperty(key) ;
		
	}*/
}
