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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		super.clone();
		throw new UnsupportedOperationException("The clone method is not supported on HttpHeader.");
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (!(object instanceof HttpHeader)) {
			return false;
		}

		HttpHeader header = (HttpHeader) object;

		return name.equals(header.name) && value.equals(header.value);
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + name.hashCode();
		result = 31 * result + value.hashCode();

		return result;
	}

	@Override
	public String toString() {
		return name + " " + value;
	}
}