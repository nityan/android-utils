package com.nityankhanna.androidutils;

import org.apache.log4j.Logger;
import org.junit.Assert;
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
	private static final Logger logger = Logger.getLogger(DateUtilsTest.class.getSimpleName());

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_DD_MM_YYYY()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR);
	}

	@Test
	public void convertToLocalTime_ValidData_DD_MM_YYYY()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getMadridTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR);

		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY expected: " + expected);
		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_DD_MM_YYYY_HH_MM_SS_AA()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_AM_PM);
	}

	@Test
	public void convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getTokyoTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_AM_PM);

		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA expected: " + expected);
		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY_HH_MM_SS_AA actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_DD_MM_YYYY_KK_MM_SS()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_AM_PM);
	}

	@Test
	public void convertToLocalTime_ValidData_DD_MM_YYYY_KK_MM_SS()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getMoscowTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_24);

		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY_KK_MM_SS expected: " + expected);
		logger.info("convertToLocalTime_ValidData_DD_MM_YYYY_KK_MM_SS actual: " + actual);
	}

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_MM_DD_YYYY()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR);
	}

	@Test
	public void convertToLocalTime_ValidData_MM_DD_YYYY()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getNewZealandTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR);

		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY expected: " + expected);
		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_MM_DD_YYYY_HH_MM_SS_AA()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_AM_PM);
	}

	@Test
	public void convertToLocalTime_ValidData_MM_DD_YYYY_HH_MM_SS_AA()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getTokyoTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_AM_PM);

		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY_HH_MM_SS_AA expected: " + expected);
		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY_HH_MM_SS_AA actual: " + actual);

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void convertToLocalTime_NullData_MM_DD_YYYY_KK_MM_SS()
	{
		Date nonLocalTime = null;
		DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_24);
	}

	@Test
	public void convertToLocalTime_ValidData_MM_DD_YYYY_KK_MM_SS()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");

		String expected = simpleDateFormat.format(Calendar.getInstance().getTime());

		Date nonLocalTime = getMoscowTime();

		String actual = DateUtils.convertToLocalTime(nonLocalTime, DateTimeFormat.MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_24);

		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY_KK_MM_SS expected: " + expected);
		logger.info("convertToLocalTime_ValidData_MM_DD_YYYY_KK_MM_SS actual: " + actual);
	}

	private Date getMadridTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		String madridTime = simpleDateFormat.format(testValue.getTime());

		logger.info("Madrid time: " + madridTime);

		return simpleDateFormat.getCalendar().getTime();
	}

	private Date getMoscowTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

		String moscowTime = simpleDateFormat.format(testValue.getTime());

		logger.info("Moscow time: " + moscowTime);

		return simpleDateFormat.getCalendar().getTime();
	}

	private Date getNewZealandTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));

		String madridTime = simpleDateFormat.format(testValue.getTime());

		logger.info("New Zealand time: " + madridTime);

		return simpleDateFormat.getCalendar().getTime();
	}

	private Date getTokyoTime()
	{
		Calendar testValue = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

		String tokyoTime = simpleDateFormat.format(testValue.getTime());

		logger.info("Tokyo time: " + tokyoTime);

		return simpleDateFormat.getCalendar().getTime();
	}
}
