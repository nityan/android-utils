package com.nityankhanna.androidutils;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */
public enum DateTimeFormat {
	DAY_MONTH_YEAR("dd/MM/yyyy"),
	MONTH_DAY_YEAR("MM/dd/yyyy"),
	YEAR_MONTH_DAY("yyyy/MM/dd"),
	HOUR_MINUTE_SECOND_12("hh:mm:ss"),
	HOUR_MINUTE_SECOND_24("kk:mm:ss"),
	DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_12("dd/MM/yyyy hh:mm:ss"),
	DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_24("dd/MM/yyyy kk:mm:ss"),
	MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_12("MM/dd/yyyy hh:mm:ss"),
	MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_24("MM/dd/yyyy kk:mm:ss"),
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_12("yyyy/MM/dd hh:mm:ss"),
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_24("yyyy/MM/dd kk:mm:ss");

	private String value;

	private DateTimeFormat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
