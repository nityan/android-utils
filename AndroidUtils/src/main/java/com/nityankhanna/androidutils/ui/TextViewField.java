package com.nityankhanna.androidutils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nityan on 23/09/13.
 */

/**
 * A custom TextView class.
 */
public class TextViewField extends TextView implements FieldOperations {

	public TextViewField(Context context) {
		super(context);
	}

	public TextViewField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextViewField(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void clear() {
		setText("");
	}

	@Override
	public int getTextAsInt() {

		String text = String.valueOf(getText());

		int value = badValue;

		if (text == null) {
			return value;
		} else {
			value = Integer.parseInt(text.toString());
		}

		return value;
	}

	@Override
	public double getTextAsDouble() {

		String text = String.valueOf(getText());

		double value = badValue;

		if (text == null) {
			return value;
		} else {
			value = Double.parseDouble(text.toString());
		}

		return value;
	}

	@Override
	public String getTextAsString() {

		String text = String.valueOf(getText());

		if (text != null && !text.isEmpty()) {
			return text;
		}

		return null;
	}

	@Override
	public void setGone() {
		setVisibility(GONE);
	}

	@Override
	public void setHidden(boolean flag) {
		if (flag) {
			setVisibility(INVISIBLE);
		} else {
			setVisibility(VISIBLE);
		}
	}

	@Override
	public void setText(boolean value) {
		setText(String.valueOf(value));
	}

	@Override
	public void setText(double value) {
		setText(String.valueOf(value));
	}

	@Override
	public void setText(long value) {
		setText(String.valueOf(value));
	}

	@Override
	public void setText(String value) {
		super.setText(value);
	}
}
