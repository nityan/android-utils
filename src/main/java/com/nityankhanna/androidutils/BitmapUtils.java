package com.nityankhanna.androidutils;

import android.graphics.Bitmap;

/**
 * Created by Nityan Khanna on Jan 19 2014.
 */
public class BitmapUtils
{

	private BitmapUtils()
	{
	}

	public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter)
	{
		float ratio = Math.min(maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());

		int width = Math.round(ratio * realImage.getWidth());
		int height = Math.round(ratio * realImage.getHeight());

		return Bitmap.createScaledBitmap(realImage, width, height, filter);
	}

	public static Bitmap scaleUp(Bitmap realImage, float maxImageSize, boolean filter)
	{
		float ratio = Math.max(maxImageSize * realImage.getWidth(), maxImageSize * realImage.getHeight());

		int width = Math.round(ratio * realImage.getWidth());
		int height = Math.round(ratio * realImage.getHeight());

		return Bitmap.createScaledBitmap(realImage, width, height, filter);
	}
}

