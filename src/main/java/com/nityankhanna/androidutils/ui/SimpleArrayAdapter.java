package com.nityankhanna.androidutils.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import com.nityankhanna.androidutils.system.async.ThreadPool;

import java.util.List;

/**
 * Created by Nityan Khanna on 15/11/13.
 */
public class SimpleArrayAdapter<T> extends ArrayAdapter<T>
{

	public SimpleArrayAdapter(Context context, int resource)
	{
		super(context, resource);
	}

	public SimpleArrayAdapter(Context context, int resource, int textViewResourceId)
	{
		super(context, resource, textViewResourceId);
	}

	public SimpleArrayAdapter(Context context, int resource, T[] objects)
	{
		super(context, resource, objects);
	}

	public SimpleArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects)
	{
		super(context, resource, textViewResourceId, objects);
	}

	public SimpleArrayAdapter(Context context, int resource, List<T> objects)
	{
		super(context, resource, objects);
	}

	public SimpleArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects)
	{
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public void notifyDataSetChanged()
	{
		if (!Looper.getMainLooper().getThread().equals(Thread.currentThread()))
		{
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(SimpleArrayAdapter.super::notifyDataSetChanged);
		}
		else
		{
			super.notifyDataSetChanged();
		}
	}
}
