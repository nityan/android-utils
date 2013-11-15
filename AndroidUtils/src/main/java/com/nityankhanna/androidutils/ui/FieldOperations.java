package com.nityankhanna.androidutils.ui;

/**
 * Created by Nityan Khanna on 12/10/13.
 */

/**
 * An interface containing methods to perform operations on text fields.
 */
public interface FieldOperations {

	int badValue = -65536;

	void clear();

	int getTextAsInt();

	double getTextAsDouble();

	String getTextAsString();

	void setGone();

	void setHidden(boolean flag);

	void setText(boolean value);

	void setText(double value);

	void setText(long value);

	void setText(String value);

}
