package com.nityankhanna.androidutils;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */

/**
 * Represents a collection of date time formats for easy use.
 */
public enum DateTimeFormat
{
	/**
	 * Day month year. dd/MM/yyyy.
	 */
	DAY_MONTH_YEAR("dd/MM/yyyy"),

	/**
	 * Month day year. MM/dd/yyyy.
	 */
	MONTH_DAY_YEAR("MM/dd/yyyy"),

	/**
	 * Year month day. yyyy/MM/dd.
	 */
	YEAR_MONTH_DAY("yyyy/MM/dd"),

	/**
	 * Hour minute second 12 hour time format.
	 * hh:mm:ss.
	 */
	HOUR_MINUTE_SECOND_12("hh:mm:ss"),

	/**
	 * Hour minute second 24 hour time format.
	 * kk:mm:ss.
	 */
	HOUR_MINUTE_SECOND_24("kk:mm:ss"),

	/**
	 * Hour minute second AM/PM.
	 * hh:mm:ss aa.
	 */
	HOUR_MINUTE_SECOND_AM_PM("hh:mm:ss aa"),

	/**
	 * Day month year hour minute second 12 hour time format.
	 * dd/MM/yyyy hh:mm:ss.
	 */
	DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_12("dd/MM/yyyy hh:mm:ss"),

	/**
	 * Day month year hour minute second 24 hour time format.
	 * dd/MM/yyyy kk:mm:ss.
	 */
	DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_24("dd/MM/yyyy kk:mm:ss"),

	/**
	 * Month day year hour minute second 12 hour time format.
	 * MM/dd/yyyy hh:mm:ss.
	 */
	MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_12("MM/dd/yyyy hh:mm:ss"),

	/**
	 * Month day year hour minute second 24 hour time format.
	 * MM/dd/yyyy kk:mm:ss.
	 */
	MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_24("MM/dd/yyyy kk:mm:ss"),

	/**
	 * Year month day hour minute second 12 hour time format.
	 * yyyy/MM/dd hh:mm:ss.
	 */
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_12("yyyy/MM/dd hh:mm:ss"),

	/**
	 * Year month day hour minute second 24 hour time format.
	 * yyyy/MM/dd kk:mm:ss.
	 */
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_24("yyyy/MM/dd kk:mm:ss"),

	/**
	 * Day month year hour minute second AM/PM.
	 * dd/MM/yyyy hh:mm:ss aa.
	 */
	DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_AM_PM("dd/MM/yyyy hh:mm:ss aa"),

	/**
	 * Month day year hour minute second AM/PM.
	 * MM/dd/yyyy hh:mm:ss aa.
	 */
	MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_AM_PM("MM/dd/yyyy hh:mm:ss aa"),

	/**
	 * Year month dayhour minute second AM/PM.
	 * yyyy/MM/dd hh:mm:ss aa.
	 */
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_AM_PM("yyyy/MM/dd hh:mm:ss aa");

	private String value;

	private DateTimeFormat(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the value of the date time format.
	 *
	 * @return Returns the value of the date time format.
	 */
	public String getValue()
	{
		return value;
	}
}
