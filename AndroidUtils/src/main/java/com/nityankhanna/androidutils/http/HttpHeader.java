package com.nityankhanna.androidutils.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

/**
 * Created by Nityan Khanna on 11/10/13.
 */

/**
 * Represents a Http Header.
 */
public class HttpHeader implements Header {

	private String name;
	private String value;

	/**
	 * Initializes a new instance of the HttpHeader class.
	 */
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

	/**
	 * Gets the value of the header.
	 *
	 * @return Returns the value of the header.
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * Gets the elements of the header.
	 *
	 * @return Returns an array of header elements.
	 *
	 * @throws ParseException
	 */
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

	/**
	 * Sets the name of the header.
	 *
	 * @param name The name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the value of the header.
	 *
	 * @param value The value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}