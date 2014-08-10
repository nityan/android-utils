package com.nityankhanna.androidutils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nityan on 2014-08-10.
 */
public class DateUtilsTest
{
	@Before
	public void setUp()
	{

	}

	@After
	public void tearDown()
	{

	}

	@Test
	public void convertToLocalTime_ValidData_DD_MM_YYYY()
	{

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getMadridTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR);

		System.out.println("convertToLocalTime_ValidData_DD_MM_YYYY expected: " + expected);
		System.out.println("convertToLocalTime_ValidData_DD_MM_YYYY actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA()
	{

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getTokyoTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_AM_PM);

		System.out.println("convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA expected: " + expected);
		System.out.println("convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	private Date getMadridTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		String madridTime = sdf.format(testValue.getTime());

		System.out.println("Madrid time: " + madridTime);

		return sdf.getCalendar().getTime();
	}

	private Date getTokyoTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		sdf.setTimeZone(TimeZone.getTimeZone("Tokyo/Japan"));

		String tokyoTime = sdf.format(testValue.getTime());

		System.out.println("Tokyo time: " + tokyoTime);

		return sdf.getCalendar().getTime();
	}
}
