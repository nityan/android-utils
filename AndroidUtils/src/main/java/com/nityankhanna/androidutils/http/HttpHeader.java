package com.nityankhanna.androidutils.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

/**
 * Created by Nityan Khanna on 11/10/13.
 */
public class HttpHeader implements Header {

	private String name;
	private String value;

	public HttpHeader() {
	}

	public HttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public HeaderElement[] getElements() throws ParseException {
		throw new ParseException("This method has not been implemented yet.");
	}
}
