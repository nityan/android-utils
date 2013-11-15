package com.nityankhanna.androidutils.http;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.webkit.URLUtil;

import com.nityankhanna.androidutils.system.ThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Nityan Khanna on 13/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */
public class ImageDownloader {

	private static ImageDownloaderCallback imageDownloaderCallback;

	private ImageDownloader() {
	}

	public static Drawable loadImageFromUrl(String url) throws IOException {
		return downloadImage(url);
	}

	public static void loadImageFromUrlAsync(String url, ImageDownloaderCallback callback) {
		imageDownloaderCallback = callback;
		new ImageDownloaderTask(url);
	}

	private static Drawable downloadImage(String url) throws IOException {
		InputStream inputStream = (InputStream) new URL(url).getContent();

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inSampleSize = 10;

		Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

		return new BitmapDrawable(Resources.getSystem(), bitmap);
	}

	private static class ImageDownloaderTask extends AsyncTask<String, Void, Drawable> {

		private String url;

		private ImageDownloaderTask(String url) {
			this.url = url;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (!URLUtil.isValidUrl(url)) {
				throw new IllegalArgumentException("Bad url" + url);
			}
		}

		@Override
		protected Drawable doInBackground(String... strings) {

			try {
				return downloadImage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Drawable drawable) {
			super.onPostExecute(drawable);
			imageDownloaderCallback.onImageDownloadComplete(drawable);
		}
	}

}
