package com.nityankhanna.androidutils.async;

import android.os.Looper;
import android.util.Log;

import com.nityankhanna.androidutils.defines.Constants;
import com.nityankhanna.androidutils.exceptions.InvalidArgumentException;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * An ThreadPoolExecutor service that queues tasks to be executed.
 */
public class ThreadPool implements RejectedExecutionHandler {

	private static ThreadPool sharedInstance;
	private ThreadPoolExecutor service;

	private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		service = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), this);
	}

	/**
	 * Returns a shared instance of the ThreadPool class.
	 *
	 * @return Returns a shared instance of the ThreadPool class.
	 */
	public static ThreadPool getInstance() {

		synchronized (ThreadPool.class) {

			if (sharedInstance == null) {
				sharedInstance = new ThreadPool(15, 20, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(15, true));
			}
		}

		return sharedInstance;
	}

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPool) {

		if (threadPool.isTerminated()) {
			Log.d(Constants.DEBUG, "Cannot queue worker task, the thread pool is terminated.");
		}
	}

	/**
	 * Returns the core pool size of the ThreadPool.
	 *
	 * @return Returns the core pool size of the ThreadPool.
	 */
	public int getCorePoolSize() {
		return service.getCorePoolSize();
	}

	/**
	 * Returns the max pool size of the ThreadPool.
	 *
	 * @return Returns the max pool size of the ThreadPool.
	 */
	public int getMaxPoolSize() {
		return service.getMaximumPoolSize();
	}

	/**
	 * Returns the active task count of the ThreadPool.
	 *
	 * @return Returns the active task count of the ThreadPool.
	 */
	public int getActiveTaskCount() {
		return service.getActiveCount();
	}

	/**
	 * Returns the queued task count of the thread pool.
	 *
	 * @return Returns the queued task count of the thread pool.
	 */
	public int getQueuedTaskCount() {
		return service.getQueue().size();
	}

	/**
	 * Returns the uncompleted task count of the ThreadPool.
	 *
	 * @return Returns the uncompleted task count of the ThreadPool.
	 */
	public int getUncompletedTaskCount() {
		return service.getQueue().size() - service.getActiveCount();
	}

	/**
	 * Checks if the current thread is the main thread (The UI thread).
	 *
	 * @return Returns true is the current thread is the main thread.
	 */
	public boolean isCurrentThreadMain() {
		return (Looper.getMainLooper().getThread() == Thread.currentThread());
	}

	/**
	 * Queues a worker item to be run on a background thread pool.
	 *
	 * @param runnable The runnable to run in the background.
	 */
	public void queueWorkerItem(@NotNull Runnable runnable) {
		service.submit(runnable);
	}
}
