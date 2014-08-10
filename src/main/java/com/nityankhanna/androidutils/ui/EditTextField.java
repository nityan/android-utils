package com.nityankhanna.androidutils.ui;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Nityan Khanna on 20/09/13.
 */
public class EditTextField extends EditText implements Field
{

	public EditTextField(Context context)
	{
		super(context);
	}

	public EditTextField(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public EditTextField(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public void clear()
	{
		setText("");
	}

	@Override
	public int getTextAsInt()
	{

		Editable editable = getText();

		int value = badValue;

		if (editable == null)
		{
			return value;
		}
		else
		{
			value = Integer.parseInt(editable.toString());
		}

		return value;
	}

	@Override
	public double getTextAsDouble()
	{

		Editable editable = getText();

		double value = badValue;

		if (editable == null)
		{
			return value;
		}
		else
		{
			value = Double.parseDouble(editable.toString());
		}

		return value;
	}

	@Override
	public String getTextAsString()
	{

		Editable editable = getText();

		String value = null;

		if (editable != null)
		{
			value = editable.toString();
		}

		return value;
	}

	@Override
	public void setGone()
	{
		setVisibility(GONE);
	}

	@Override
	public void setHidden(boolean flag)
	{
		if (flag)
		{
			setVisibility(INVISIBLE);
		}
		else
		{
			setVisibility(VISIBLE);
		}
	}

	@Override
	public void setText(boolean value)
	{
		setText(String.valueOf(value));
	}

	@Override
	public void setText(double value)
	{
		setText(String.valueOf(value));
	}

	@Override
	public void setText(long value)
	{
		setText(String.valueOf(value));
	}

	@Override
	public void setText(String value)
	{
		super.setText(value);
	}
}
