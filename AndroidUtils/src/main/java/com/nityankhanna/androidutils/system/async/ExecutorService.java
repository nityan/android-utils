package com.nityankhanna.androidutils.system.async;

import android.os.AsyncTask;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */
class ExecutorService implements Executor {

	@Override
	public java.util.concurrent.Executor getAsyncService() {
		return AsyncTask.THREAD_POOL_EXECUTOR;
	}

	@Override
	public java.util.concurrent.Executor getSerialService() {
		return AsyncTask.SERIAL_EXECUTOR;
	}
}
