package com.nityankhanna.androidutils.http;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public interface HttpParameterStore {

	/**
	 * Adds an HttpParameter to the collection.
	 *
	 * @param parameter The HttpParameter to add.
	 */
	void addParameter(HttpParameter parameter);

	/**
	 * Adds an HttpParameter to the collection at the specified index.
	 *
	 * @param index     The index.
	 * @param parameter The HttpParameter to add.
	 */
	void addParameter(int index, HttpParameter parameter);

	/**
	 * Gets a list of the parameters.
	 *
	 * @return Returns a list of parameters.
	 */
	List<HttpParameter> getParameters();

	/**
	 * Removes a parameter.
	 *
	 * @param parameter The HttpParameter to be removed.
	 */
	void removeParameter(HttpParameter parameter);

	/**
	 * Removes the parameter at the specified index.
	 *
	 * @param index The index.
	 */
	void removeParameter(int index);

	/**
	 * Removes all the parameters from the collection.
	 */
	void removeAllParameters();
}
