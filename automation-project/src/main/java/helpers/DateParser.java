package helpers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import browsersetup.BaseClass;

public class DateParser extends BaseClass
{	
	public static String convertDBDateToFormat(String dBDate, String dateFormat) throws ParseException
	{
		// Convert input string into a date
		//dbdateFormat = "yyyy-MM-dd hh:mm:ss.S"
		DateFormat inputFormat = new SimpleDateFormat(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat1"));
		Date date = inputFormat.parse(dBDate);
		// Format date into output format
		//dateFormat="DD-MMMM-YYYY"
		DateFormat outputFormat = new SimpleDateFormat(dateFormat);
		String outputString = outputFormat.format(date);
		return outputString;
	}

	
	public static String convertSDFToDBDate(String sDFDate) throws ParseException
	{
		SimpleDateFormat input = new SimpleDateFormat(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat2"));
		SimpleDateFormat output = new SimpleDateFormat(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat3"));
		Date inputdate = input.parse(sDFDate);
		String formattedTime = output.format(inputdate);
/*		// Convert input string into a date
		//dbdateFormat = "yyyy-MM-dd hh:mm:ss.S"
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		//DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		Date date = inputFormat.parse(sDFDate);
		// Format date into output format
		//dateFormat="DD-MMMM-YYYY"
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		String outputString = outputFormat.format(sDFDate);*/
		return formattedTime;
	}
	
	
	public static String getFirstCollectionDate(LocalDate date, int collectionDay, String schemaName, String environment) throws InterruptedException, SQLException, ParseException 
	{
		List<String> bufferDays = DatabaseHelper.CreateDataListForAListOfRows(utilities.ReadProperties.getProperty(databasePropertie, location, "bufferDays"), "value", schemaName, environment);  
		List<String> holidays = DatabaseHelper.CreateDataListForAListOfRows(utilities.ReadProperties.getProperty(databasePropertie, location, "holidays"), "DATE", "commondb", environment);
		LocalDate result = date;
		LocalDate finalResultDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat5"));
		int addedDays = 0;
		while (addedDays < Integer.valueOf(bufferDays.get(0))) 
		{
			result = result.plusDays(1);
			if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(dtf.format(result)))) 
			{
				++addedDays;
			}
		}

		String collectionDate = date.getYear()+"-"+date.getMonthValue()+"-"+String.valueOf(collectionDay);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat6"));
		formatter = formatter.withLocale( Locale.UK );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		LocalDate collectionDateFormatted = LocalDate.parse(collectionDate, formatter);
		
		if(date.isAfter(collectionDateFormatted))
			collectionDateFormatted = collectionDateFormatted.plusMonths(1);

		if(collectionDateFormatted.isAfter(result))
		{
			finalResultDate = collectionDateFormatted;
			boolean isWeekEnd = false;
			do
			{
				if ((finalResultDate.getDayOfWeek() == DayOfWeek.SATURDAY || finalResultDate.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(dtf.format(finalResultDate)))) 
				{
					finalResultDate = finalResultDate.plusDays(1);
					isWeekEnd = true;
				}
				else
					isWeekEnd = false;
			}while(isWeekEnd);
		}
		else
		{
			finalResultDate = collectionDateFormatted.plusMonths(1);
			boolean isWeekEnd = false;
			do
			{
				if ((finalResultDate.getDayOfWeek() == DayOfWeek.SATURDAY || finalResultDate.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(dtf.format(finalResultDate)))) 
				{
					finalResultDate = finalResultDate.plusDays(1);
					isWeekEnd = true;
				}
				else
					isWeekEnd = false;
			}while(isWeekEnd);

		}
		dtf = DateTimeFormatter.ofPattern(utilities.ReadProperties.getProperty(configPropertie, location, "dateFormat7"));;
		return dtf.format(finalResultDate);
	}

}
