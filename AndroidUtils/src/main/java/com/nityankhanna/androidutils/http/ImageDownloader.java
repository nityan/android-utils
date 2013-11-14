package com.nityankhanna.androidutils.http;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.nityankhanna.androidutils.system.ThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nityan Khanna on 13/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */
public class ImageDownloader {

	private ThreadPool threadPool = ThreadPool.getInstance();

	private ImageDownloader() {

	}

	public static Drawable loadImageFromUrlAsync(Context context, String url) {
		return (Drawable) new ImageDownloaderTask(context, url).loadInBackground();
	}

	private static class ImageDownloaderTask extends AsyncTaskLoader {

		private String url;

		private ImageDownloaderTask(Context context, String url) {
			super(context);
			this.url = url;
		}

		@Override
		public Object loadInBackground() {
			try {
				InputStream inputStream = (InputStream) new URL(url).getContent();

				BitmapFactory.Options options = new BitmapFactory.Options();

				options.inSampleSize = 10;

				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

				return new BitmapDrawable(Resources.getSystem(), bitmap);

			} catch (MalformedURLException e) {
				return null;
			} catch (IOException e) {
				return null;
			}
		}
	}
}
