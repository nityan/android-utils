package com.nityankhanna.androidutils.system;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

/**
 * Created by Nityan Khanna on 11/10/13.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public enum FileMode {

	/**
	 * Append mode.
	 */
	MODE_APPEND(Context.MODE_APPEND),

	/**
	 * Database open flag: when set, the database is
	 * opened with write-ahead logging enabled by default.
	 */
	MODE_ENABLE_WRITE_AHEAD_LOGGING(Context.MODE_ENABLE_WRITE_AHEAD_LOGGING),

	/**
	 * SharedPreference loading flag: when set, the file on disk will be checked for modification
	 * even if the shared preferences instance is already loaded in this process.
	 */
	MODE_MULTI_PROCESS(Context.MODE_MULTI_PROCESS),

	/**
	 * File creation mode: the default mode, where the created file can only be accessed
	 * by the calling application (or all applications sharing the same user ID).
	 */
	MODE_PRIVATE(Context.MODE_PRIVATE);

	private int value;

	private FileMode(int value) {
		this.value = value;
	}

	/**
	 * Gets the value of the file mode.
	 *
	 * @return Returns an int as the file mode.
	 */
	public int getValue() {
		return value;
	}
}
