package com.nityankhanna.tests;

import android.util.Log;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

import com.nityankhanna.androidutils.DateTimeFormat;
import com.nityankhanna.androidutils.DateUtils;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class DateUtilsTest extends TestCase {

	private final String className = getClass().getSimpleName();

	public void testConvertToLocalTime() throws Exception {
	}

	public void testConvertToNewFormat() throws Exception {

		String stringDate = "06/15/2009 1:45:30 PM";

		String date = DateUtils.convertToNewFormat(DateUtils.stringToDate(stringDate, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_AM_PM), DateTimeFormat.DAY_MONTH_YEAR);

		assertNotNull("Date is null", date);
		assertEquals("Dates do not equal", "15/06/2009", date);

		Log.e(className, date);
	}

	public void testDateToString() throws Exception {

		Date date = Calendar.getInstance().getTime();

		String stringDate = DateUtils.dateToString(date, DateTimeFormat.MONTH_DAY_YEAR);

		assertEquals("Dates do not equal", "02/15/2014", stringDate);

		Log.e(className, date.toString());
		Log.e(className, stringDate);
	}

	public void testStringToDate() throws Exception {
		String stringDate = "06/15/2009 1:45:30 PM";
		Date date = DateUtils.stringToDate(stringDate, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_AM_PM);

		assertNotNull("Date is null", date);

		Log.e(className, stringDate);
		Log.e(className, date.toString());
	}
}
