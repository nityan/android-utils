package com.nityankhanna.androidutils.system.datetime;

import java.util.Calendar;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */
public enum DateTimeFormat {

	DAY_MONTH_YEAR("dd/MM/yyyy"),
	MONTH_DAY_YEAR("MM/dd/yyyy"),
	YEAR_MONTH_DAY("yyyy/MM/dd"),
	HOUR_MINUTE_SECOND_12("hh:mm:ss"),
	HOUR_MINUTE_SECOND_24("kk:mm:ss")
	;



	private String value;

	private DateTimeFormat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
