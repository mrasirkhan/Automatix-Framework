package com.readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.TestCase;

import com.util.AutomationUtil;

public class ReadExcel
{
	private static Object getCellValue(Cell cell)
	{
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				
				return cell.getStringCellValue();

			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();

			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
		}

		return null;
	}

	public static List<TestCase> readTestCasesFromExcelFile(String excelFilePath) throws IOException
	{
		List<TestCase> listTestCase = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Set<String>manualTestIdSet=new HashSet<String>();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Row row = firstSheet.getRow(0);
		int columnNumbers = row.getLastCellNum();
		for (int i = 1; i < firstSheet.getLastRowNum() + 1; i++)
		{
			row = firstSheet.getRow(i);
			TestCase testCase = new TestCase();
			for (int j = 0; j < columnNumbers; j++)
			{
				Object cellValue = "";
				Cell cell=row.getCell(j);
				if (cell != null)
				{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = getCellValue(cell);
					if (cellValue == null)
					{
						cellValue = "";
					}
				
				}
				switch(j)
				{
					case 0:
						testCase.setManualTestId(cellValue.toString());
					
						break;
					case 1:
						testCase.setTestObjective((String) cellValue);
						break;
					case 2:
						testCase.setTestDescription((String) cellValue);
						break;
					case 3:
						testCase.setSeverityName((String) cellValue);
						break;
					case 4:
						testCase.setModuleName((String) cellValue);
						break;
					case 5:
						testCase.setFeatureName((String) cellValue);
						break;
					case 6:

						testCase.setTestTypeName((String) cellValue);
						break;
					case 7:
						testCase.setRequirenmentNo((String) cellValue);
						break;
					case 8:
						
						if (cellValue != null && cellValue !="")
						{
							int flag = Integer.parseInt((String) cellValue);
							if (flag == 1)
							{
								testCase.setApplicableForAutomation(true);
							}
						}
						
						break;
					case 9:
						testCase.setScriptName((String) cellValue);
						break;
				}
			}
			listTestCase.add(testCase);
		}

		workbook.close();
		inputStream.close();

		return listTestCase;
	}

	private static Workbook getWorkbook(FileInputStream inputStream, File file) throws IOException
	{
		Workbook workbook = null;
		String excelFilePath = file.toString();
		if (excelFilePath.endsWith("xlsx"))
		{
			workbook = new XSSFWorkbook(inputStream);
		}
		else if (excelFilePath.endsWith("xls"))
		{
			workbook = new HSSFWorkbook(inputStream);
		}
		else
		{
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public static List<TestCase> readExcel(File file) throws IOException
	{
		String excelFilePath = file.toString();
		String path = AutomationUtil.getBasePath();

		System.out.println("Path " + path);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = getWorkbook(inputStream, file);
		List<TestCase> testcaseList = readTestCasesFromExcelFile(excelFilePath);
		System.out.println(testcaseList);
		return testcaseList;
	}
}
