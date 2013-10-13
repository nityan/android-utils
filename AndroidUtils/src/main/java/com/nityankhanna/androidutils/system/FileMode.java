package com.nityankhanna.androidutils.system;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

/**
 * Created by Nityan Khanna on 11/10/13.
 */
public enum FileMode {
	MODE_APPEND,
	MODE_ENABLE_WRITE_AHEAD_LOGGING,
	MODE_MULTI_PROCESS,
	MODE_PRIVATE;

	private FileMode() {

	}

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
