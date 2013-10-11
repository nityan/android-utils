package com.nityankhanna.androidutils.ui;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.nityankhanna.androidutils.defines.Constants;

/**
 * Created by Nityan Khanna on 20/09/13.
 */
public class EditTextField extends EditText {

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

	public String getContent() {

		Editable editable = getText();

		if (editable != null) {
			content = editable.toString();
		}

		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentAsInt() {

		Editable editable = getText();
		int value = -1;

		if (editable == null) {
			return value;
		} else {
			try {
				value = Integer.parseInt(editable.toString());
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
				value = -1;
			}
		}

		return value;
	}

	public double getContentAsDouble() {

		Editable editable = getText();
		double value = -1;

		if (editable == null) {
			return value;
		} else {
			try {
				value = Double.parseDouble(editable.toString());
			} catch (NumberFormatException ex) {
				Log.d(Constants.DEBUG, ex.getMessage());
				value = -1;
			}
		}

		return value;
	}

	public void clear() {
		this.setText("");
	}
}
