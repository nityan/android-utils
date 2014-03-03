package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on 11/10/13.
 */

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

/**
 * Represents an Http Header.
 */
public class HttpHeader implements Header {

	private String name;
	private String value;

	public HttpHeader() {
	}

	/**
	 * Initializes a new instance of the HttpHeader class with a name and value.
	 *
	 * @param name  The name.
	 * @param value The value.
	 */
	public HttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the name of the header.
	 *
	 * @return Returns the name of the header.
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value of the header.
	 *
	 * @return Returns the value of the header.
	 */
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public HeaderElement[] getElements() throws ParseException {
		return new HeaderElement[0];
	}

	public void setValue(String value) {
		this.value = value;
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
		return "HttpHeader{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}