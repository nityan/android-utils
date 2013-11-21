package com.nityankhanna.androidutils.system;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

/**
 * Created by Nityan Khanna on 11/10/13.
 */
public enum FileMode {

	/**
	 * Append mode.
	 */
	MODE_APPEND,

	/**
	 * Database open flag: when set, the database is
	 * opened with write-ahead logging enabled by default.
	 */
	MODE_ENABLE_WRITE_AHEAD_LOGGING,

	/**
	 * SharedPreference loading flag: when set, the file on disk will be checked for modification
	 * even if the shared preferences instance is already loaded in this process.
	 */
	MODE_MULTI_PROCESS,

	/**
	 * File creation mode: the default mode, where the created file can only be accessed
	 * by the calling application (or all applications sharing the same user ID).
	 */
	MODE_PRIVATE;

	private FileMode() {
	}

	/**
	 * Gets the value of the file mode.
	 * @return Returns an int as the file mode.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public int getValue() {

		int value;

		switch (this) {

			case MODE_APPEND:
				value = Context.MODE_APPEND;
				break;

			case MODE_ENABLE_WRITE_AHEAD_LOGGING:
				value = Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;
				break;

			case MODE_MULTI_PROCESS:
				value = Context.MODE_MULTI_PROCESS;
				break;

			case MODE_PRIVATE:
				value = Context.MODE_PRIVATE;
				break;

			default:
				value = -1;
				break;
		}

		return value;
	}
}
