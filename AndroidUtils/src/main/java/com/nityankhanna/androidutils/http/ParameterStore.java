package com.nityankhanna.androidutils.http;

import org.apache.http.params.BasicHttpParams;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public interface ParameterStore {

	void addParameter(String key, double value);

	void addParameter(String key, int value);

	void addParameter(String key, Object value);

	void addParameter(String key, String value);

	void addParameter(List<BasicHttpParams> params);

	BasicHttpParams getParameters();

	void removeParameter(String key);

	void removeAllParameters();
}
