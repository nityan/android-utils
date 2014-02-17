package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */

/**
 * Represents an Http parameter.
 */
public class HttpParameter implements Parameter {

	private String name;
	private Object value;

	/**
	 * Initializes a new instance of the HttpParameter class.
	 */
	public HttpParameter() {
	}

	/**
	 * Initializes a new instance of the HttpParamter class with a name and value.
	 *
	 * @param name  The name.
	 * @param value The value.
	 */
	public HttpParameter(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (!(object instanceof HttpParameter)) {
			return false;
		}

		HttpParameter parameter = (HttpParameter) object;

		return name.equals(parameter.name) && value.equals(parameter.value);
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
		return "HttpParameter{" +
				"name='" + name + '\'' +
				", value=" + value +
				'}';
	}
}
