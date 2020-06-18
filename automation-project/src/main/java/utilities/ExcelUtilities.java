package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtilities 
{
	static String projectPath = System.getProperty("user.dir");

	@SuppressWarnings("resource")
	public static String getExcelCellValue(String fileName,String sheetName,int rowNumber,int columnNumber) throws IOException
	{
		/*		String path = ExcelUtilities.class.getClassLoader().getResource("./").getPath();
		path = path.replace("bin", "src");
		//Create a object of File class to open xlsx file
		File file = new File(path + "../ExcelDataFiles/"+fileName);*/
		File file =    new File(projectPath + "\\ExcelDataFiles\\"+fileName);
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		String cellValue = null;
		Workbook excelWorkbook = null;
		//Find the file extension by spliting file name in substring and getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		//Check condition if the file is xlsx file
		if(fileExtensionName.equals(".xlsx"))
		{
			//If it is xlsx file then create object of XSSFWorkbook class
			excelWorkbook = new XSSFWorkbook(inputStream);
		}
		//Check condition if the file is xls file
		else if(fileExtensionName.equals(".xls"))
		{
			//If it is xls file then create object of XSSFWorkbook class
			excelWorkbook = new HSSFWorkbook(inputStream);
		}
		//Read sheet inside the workbook by its name
		Sheet excelSheet = excelWorkbook.getSheet(sheetName);
		//Find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		excelSheet.getRow(0).getCell(0);
		//Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount+ 1; i++)
		{
			if(i == rowNumber - 1)
			{
				Row row = excelSheet.getRow(i);
				//System.out.print(rowCount);
				//Create a loop to print cell values in a row
				for (int j = 0; j < row.getLastCellNum() + 1 ; j++)
				{
					if(j == columnNumber - 1)
					{
						//Print excel data in console
						try 
						{
							@SuppressWarnings("deprecation")
							int cel_Type = row.getCell(j).getCellType();                           
							switch(cel_Type) 
							{
							case 0: cellValue = String.valueOf((int)(row.getCell(j).getNumericCellValue()));
							break;
							case 1:	cellValue = row.getCell(j).getStringCellValue();
							break;
							case 2: SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							cellValue = sdf.format(row.getCell(j).getDateCellValue());
							break;
							case 4: cellValue = String.valueOf(row.getCell(j).getBooleanCellValue());
							break;
							default:
								cellValue = "";
							}
						} 
						catch (Exception e) 
						{
							cellValue = "";
						}
						break;
					}
				}
				break;
			}
		}
		return cellValue;
	}

	public static String getKeyValueFromExcelWithPosition(String fileName,String sheetName, String keyName,int positionNo) 
	{
		// Call readExcelFile() method by passing it location of xls
		// This method will load keys and values from xls to HashMap
		return getKeyValue(fileName,sheetName,keyName,positionNo);
	}

	public static String getKeyValueFromExcel(String fileName,String sheetName, String keyName) 
	{
		// Call readExcelFile() method by passing it location of xls
		// This method will load keys and values from xls to HashMap
		return getKeyValue(fileName,sheetName,keyName,1);
	}

	private static String getKeyValue(String fileName,String sheetName, String keyName,int positionNo)     
	{
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String keyValue = null;
		/*		int environmentNo = 0;
		switch(environment.toUpperCase())
		{
		case "INTEGRATION" :
			environmentNo = 1;
			break;
		case "UAT" :
			environmentNo = 2;
			break;
		default :
			Assert.fail(environment + "dosen't exsists");
		}*/

		try
		{                      
			File file =    new File(projectPath + "\\ExcelDataFiles\\"+fileName);
			// Create a FileInputStream by passing the location of excel
			FileInputStream input = new FileInputStream(file);

			Workbook excelWorkbook = null;
			//Find the file extension by spliting file name in substring and getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			//Check condition if the file is xlsx file
			if(fileExtensionName.equals(".xlsx"))
			{
				//If it is xlsx file then create object of XSSFWorkbook class
				excelWorkbook = new XSSFWorkbook(input);
			}
			//Check condition if the file is xls file
			else if(fileExtensionName.equals(".xls"))
			{
				//If it is xls file then create object of XSSFWorkbook class
				excelWorkbook = new HSSFWorkbook(input);
			}
			//Read sheet inside the workbook by its name
			Sheet excelSheet = excelWorkbook.getSheet(sheetName);

			//Find number of rows in excel file
			int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
			//Create a loop over all the rows of excel file to read it

			for (int i = 0; i < rowCount+1; i++) 
			{

				Row row = excelSheet.getRow(i);
				List<String> values = new ArrayList<String>();
				String key = null;
				String val = null;
				//Create a loop to print cell values in a row
				int collumnCounter = 1;
				for (int j = 0; j < row.getLastCellNum(); j++) 
				{
					//Print excel data in console
					if(collumnCounter==1)
					{
						try 
						{
							@SuppressWarnings("deprecation")
							int cel_Type = row.getCell(j).getCellType();                           
							switch(cel_Type) 
							{
							case 0: key = String.valueOf((int)(row.getCell(j).getNumericCellValue()));
							break;
							case 1:	key = row.getCell(j).getStringCellValue();
							break;
							case 2: SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							key = sdf.format(row.getCell(j).getDateCellValue());
							break;
							case 4: key = String.valueOf(row.getCell(j).getBooleanCellValue());
							break;
							default:
								key = "";
							}
						} 
						catch (Exception e) 
						{
							key = "";
						}
						collumnCounter++;
					}
					else
					{
						try 
						{
							@SuppressWarnings("deprecation")
							int cel_Type = row.getCell(j).getCellType();                           
							switch(cel_Type) 
							{
							case 0: val = String.valueOf((int)(row.getCell(j).getNumericCellValue()));
							break;
							case 1:	val = row.getCell(j).getStringCellValue();
							break;
							case 2: SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							val = sdf.format(row.getCell(j).getDateCellValue());
							break;
							case 4: val = String.valueOf(row.getCell(j).getBooleanCellValue());
							break;
							default:
								val = "";
							}
						} 
						catch (Exception e) 
						{
							val = "";
						}
						// extracting values from the cell2 
						values.add(val);
						// storing each properties into the HashMap
						collumnCounter++;
					}

				}
				map.put(key, values);
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		for(Map.Entry<String, List<String>> entry : map.entrySet()) 
		{
			if(entry.getKey().equals(keyName))
			{
				keyValue = entry.getValue().get(positionNo - 1);
				break;
			}
		}
		return keyValue;                
	}

	/*	public static void openExcel(String fileName) throws InterruptedException
	{
		//Create a object of File class to open xlsx file
		//File file =    new File(projectPath + "\\ExcelDataFiles\\"+fileName);
        try
        {  
        	//Desktop.getDesktop().open(file);
        	Runtime.getRuntime().exec("cmd /c start "+projectPath + "\\ExcelDataFiles\\"+fileName);
        	Thread.sleep(4000);
        }
        catch(IOException  e)
        {
            e.printStackTrace();
        } 
	}

	public static void closeExcel() throws InterruptedException, IOException
	{
        try
        {   Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
        	Thread.sleep(2000);  
        }
        catch(IOException  e)
        {
            e.printStackTrace();
        }
	}*/


}

