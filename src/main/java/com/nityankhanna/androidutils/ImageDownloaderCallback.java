package com.nityankhanna.androidutils;

import android.graphics.drawable.Drawable;

/**
 * Created by Nityan Khanna on 14/11/13.
 */

/**
 * Contains callback methods for downloading images.
 */
public interface ImageDownloaderCallback
{

	/**
	 * A delegate method called when the image download is cancelled.
	 */
	void onImageDownloadCancelled();

	/**
	 * A delegate method called when the image download is complete.
	 *
	 * @param drawable The drawable which was downloaded.
	 */
	void onImageDownloadComplete(Drawable drawable);
}
