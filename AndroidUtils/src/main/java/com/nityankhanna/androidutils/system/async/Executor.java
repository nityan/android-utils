package com.nityankhanna.androidutils.system.async;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */
interface Executor {

	java.util.concurrent.Executor getAsyncService();

	java.util.concurrent.Executor getSerialService();
}
