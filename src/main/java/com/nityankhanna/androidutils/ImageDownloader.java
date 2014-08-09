package com.nityankhanna.androidutils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Nityan Khanna on 13/11/13.
 */

/**
 * A utility class for downloading images.
 */
public class ImageDownloader
{

	private static ImageDownloaderTask imageDownloaderTask;

	private ImageDownloader()
	{
	}

	/**
	 * Downloads an image in the background.
	 *
	 * @param url      The url of the image.
	 * @param callback The callback.
	 */
	public static void loadImageFromUrlAsync(String url, ImageDownloaderCallback callback)
	{
		imageDownloaderTask = new ImageDownloaderTask(url, callback);
		imageDownloaderTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	/**
	 * Downloads an image in the background.
	 *
	 * @param url       The url of the image.
	 * @param imageView The image view to set the image in.
	 */
	public static void loadImageFromUrlAsync(String url, ImageView imageView)
	{
		imageDownloaderTask = new ImageDownloaderTask(url, imageView);
		imageDownloaderTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	public static void cancelImageDownload()
	{
		cancelImageDownload(true);
	}

	public static void cancelImageDownload(boolean mayInterruptIfRunning)
	{
		imageDownloaderTask.cancel(mayInterruptIfRunning);
	}

	private static class ImageDownloaderTask extends AsyncTask<String, Void, Drawable>
	{

		private String url;
		private ImageView imageView;
		private ImageDownloaderCallback callback;

		private ImageDownloaderTask(String url)
		{
			this.url = url;
		}

		private ImageDownloaderTask(String url, ImageView imageView)
		{
			this(url);
			this.imageView = imageView;
		}

		private ImageDownloaderTask(String url, ImageDownloaderCallback callback)
		{
			this(url);
			this.callback = callback;
		}

		@Override
		protected Drawable doInBackground(String... strings)
		{

			try
			{
				return downloadImage(url);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Drawable drawable)
		{
			super.onPostExecute(drawable);

			if (callback == null)
			{
				imageView.setImageDrawable(drawable);
			} else
			{
				callback.onImageDownloadComplete(drawable);
			}
		}

		@Override
		protected void onCancelled()
		{
			super.onCancelled();

			if (callback != null)
			{
				callback.onImageDownloadCancelled();
			}
		}

		/**
		 * Downloads an image.
		 *
		 * @param url The url of the image.
		 * @return Returns the drawable image.
		 * @throws IOException
		 */
		private Drawable downloadImage(String url) throws IOException
		{
			InputStream inputStream = (InputStream) new URL(url).getContent();

			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

			inputStream.close();

			return new BitmapDrawable(Resources.getSystem(), bitmap);
		}
	}
}
