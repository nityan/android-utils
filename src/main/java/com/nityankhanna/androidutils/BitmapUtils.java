package com.nityankhanna.androidutils;

import android.graphics.Bitmap;

/**
 * Created by Nityan Khanna on Jan 19 2014.
 */

/**
 * A collection of methods for processing bitmaps.
 */
public class BitmapUtils
{
	private BitmapUtils()
	{
	}

	/**
	 * Scales a bitmap down.
	 *
	 * @param realImage    The image to be scaled.
	 * @param maxImageSize The max size of the image to scale to.
	 * @param filter       Should the scaling use a filter.
	 * @return Returns the scaled down bitmap.
	 */
	public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter)
	{
		float ratio = Math.min(maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());

		int width = Math.round(ratio * realImage.getWidth());
		int height = Math.round(ratio * realImage.getHeight());

		return Bitmap.createScaledBitmap(realImage, width, height, filter);
	}

	/**
	 * Scales a bitmap up.
	 *
	 * @param realImage    The image to be scaled.
	 * @param maxImageSize The max size of the image to scale to.
	 * @param filter       Should the scaling use a filter.
	 * @return Returns the scaled up bitmap.
	 */
	public static Bitmap scaleUp(Bitmap realImage, float maxImageSize, boolean filter)
	{
		float ratio = Math.max(maxImageSize * realImage.getWidth(), maxImageSize * realImage.getHeight());

		int width = Math.round(ratio * realImage.getWidth());
		int height = Math.round(ratio * realImage.getHeight());

		return Bitmap.createScaledBitmap(realImage, width, height, filter);
	}
}

