package com.nityankhanna.androidutils.ui;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public interface FieldOperations {

	int badValue = -65536;

	void clear();

	int getTextAsInt();

	double getTextAsDouble();

	String getTextAsString();

	void setHidden(boolean flag);
}
