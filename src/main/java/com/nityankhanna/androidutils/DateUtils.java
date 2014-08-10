package com.nityankhanna.androidutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nityan Khanna on Jan 03 2014.
 */

/**
 * Contains utility methods for processing dates.
 */
public class DateUtils
{

	private static final String ERROR_MESSAGE = "An unknown error has occurred. Please view the stack trace for more information.";

	private DateUtils()
	{
	}

	/**
	 * Converts a date to local time.
	 *
	 * @param calendar     The date to convert.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the local date time as specified in the target format.
	 */
	public static String convertToLocalTime(Calendar calendar, DateTimeFormat targetFormat)
	{
		return convertToLocalTime(calendar.getTime(), targetFormat.getValue());
	}

	/**
	 * Converts a date to local time.
	 *
	 * @param date         The date to convert.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the local date time as specified in the targetFormat parameter.
	 */
	public static String convertToLocalTime(Date date, DateTimeFormat targetFormat)
	{
		return convertToLocalTime(date, targetFormat.getValue());
	}

	/**
	 * Converts a date to local time.
	 *
	 * @param date         The date to convert.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the local date time as specified in the targetFormat parameter.
	 */
	public static String convertToLocalTime(Date date, String targetFormat)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetFormat);
		long when;

		try
		{
			when = dateFormat.parse(dateToString(date, targetFormat)).getTime();

			Date localDate = new Date(when + TimeZone.getDefault().getRawOffset() + (TimeZone.getDefault().inDaylightTime(new Date())
					? TimeZone.getDefault().getDSTSavings() : 0));
			return convertToNewFormat(localDate, targetFormat);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}

	/**
	 * Converts a date to a new format.
	 *
	 * @param date         The date to format.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the date time as specified in the targetFormat parameter.
	 */
	public static String convertToNewFormat(Date date, DateTimeFormat targetFormat)
	{
		return dateToString(date, targetFormat);
	}

	/**
	 * Converts a date to a new format.
	 *
	 * @param date         The date to format.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the date time as specified in the targetFormat parameter.
	 */
	public static String convertToNewFormat(Date date, String targetFormat)
	{
		return dateToString(date, targetFormat);
	}

	/**
	 * Converts a date to a string.
	 *
	 * @param date         The date to convert.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the date time as specified in the targetFormat parameter.
	 */
	public static String dateToString(Date date, DateTimeFormat targetFormat)
	{
		return dateToString(date, targetFormat.getValue());
	}

	/**
	 * Converts a date to a string.
	 *
	 * @param date         The date to convert.
	 * @param targetFormat The new format of the date.
	 * @return Returns a string with the date time as specified in the targetFormat parameter.
	 */
	public static String dateToString(Date date, String targetFormat)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetFormat);
		return dateFormat.format(date);
	}

	/**
	 * Converts a string to a date.
	 *
	 * @param date          The string to convert.
	 * @param currentFormat The current date time format of the string to be converted.
	 * @return Returns a date.
	 */
	public static Date stringToDate(String date, DateTimeFormat currentFormat)
	{
		return stringToDate(date, currentFormat.getValue());
	}

	/**
	 * Converts a string to a date.
	 *
	 * @param date          The string to convert.
	 * @param currentFormat The current date time format of the string to be converted.
	 * @return Returns a date.
	 */
	public static Date stringToDate(String date, String currentFormat)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(currentFormat);

		try
		{
			return dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}
}
