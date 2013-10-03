package com.nityankhanna.androidutils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.nityankhanna.androidutils.defines.Constants;

/**
 * Created by Nityan on 23/09/13.
 */

/**
 * A custom TextView class.
 */
public class TextViewField extends TextView {

	public TextViewField(Context context) {
		super(context);
	}

	public TextViewField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextViewField(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public int getTextAsInt() {

		String text = (String) getText();
		int value = -1;

		if (text == null) {
			return value;
		} else {
			try {
				value = Integer.parseInt(text);
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
				value = -1;
			}
		}

		return value;
	}

	public double getTextAsDouble() {

		String text = (String) getText();
		double value = -1;

		if (text == null) {
			return value;
		} else {
			try {
				value = Double.parseDouble(text);
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
				value = -1;
			}
		}

		return value;
	}

	public void clear() {
		setText("");
	}
}
