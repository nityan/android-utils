package com.nityankhanna.androidutils.ui;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.nityankhanna.androidutils.Constants;

/**
 * Created by Nityan Khanna on 20/09/13.
 */
public class EditTextField extends EditText implements Field {

	private String content;

	public EditTextField(Context context) {
		super(context);
	}

	public EditTextField(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EditTextField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void clear() {
		setText("");
	}

	@Override
	public int getTextAsInt() {

		Editable editable = getText();

		int value = badValue;

		if (editable == null) {
			return value;
		} else {
			try {
				value = Integer.parseInt(editable.toString());
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
			}
		}

		return value;
	}

	@Override
	public double getTextAsDouble() {

		Editable editable = getText();

		double value = badValue;

		if (editable == null) {
			return value;
		} else {
			try {
				value = Double.parseDouble(editable.toString());
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
			}
		}

		return value;
	}

	@Override
	public String getTextAsString() {

		Editable editable = getText();

		if (editable != null) {
			content = editable.toString();
		}

		return content;
	}

	@Override
	public void setHidden(boolean flag) {
		if (flag) {
			setVisibility(INVISIBLE);
		} else {
			setVisibility(VISIBLE);
		}
	}
}
