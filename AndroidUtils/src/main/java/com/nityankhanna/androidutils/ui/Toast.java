package com.nityankhanna.androidutils.ui;

import android.content.Context;

/**
 * Created by Nityan Khanna on 29/06/13.
 */

/**
 * A simplified Toast class.
 */
public class Toast {

	/**
	 * Displays a Toast message with a long length.
	 *
	 * @param context The application context.
	 * @param text    The text to be displayed in the toast message.
	 */
	public static void displayLongToast(Context context, String text) {
		displayToast(context, text, android.widget.Toast.LENGTH_LONG);
	}

	/**
	 * Displays a Toast message with a short length.
	 *
	 * @param context The application context.
	 * @param text    The text to be displayed in the toast message.
	 */
	public static void displayShortToast(Context context, String text) {
		displayToast(context, text, android.widget.Toast.LENGTH_SHORT);
	}

	private static void displayToast(Context context, String text, int duration) {
		android.widget.Toast.makeText(context, text, duration).show();
	}


}
