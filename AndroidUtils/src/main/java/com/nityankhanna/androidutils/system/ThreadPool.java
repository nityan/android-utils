package com.nityankhanna.androidutils.system;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * An ThreadPoolExecutor service that queues tasks to be executed.
 */
public class ThreadPool extends ThreadPoolExecutor implements RejectedExecutionHandler {

	private static ThreadPool sharedInstance;
	//private ThreadPoolExecutor service;

	public static final Executor EXECUTOR = getInstance();

	private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory());
		//service = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory());
		//service.allowCoreThreadTimeOut(true);
		allowCoreThreadTimeOut(true);
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

	/**
	 * Checks if the current thread is the main thread (The UI thread).
	 *
	 * @return Returns true is the current thread is the main thread.
	 */
	public static boolean isCurrentThreadMain() {
		return (Looper.getMainLooper().getThread() == Thread.currentThread());
	}

	/**
	 * Runs an item on the UI thread.
	 *
	 * @param runnable The runnable to run on the UI thread.
	 */
	public static void runOnUiThread(Runnable runnable) {

		if (runnable == null) {
			throw new IllegalArgumentException("The runnable parameter cannot be null");
		}

		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(runnable);
	}

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPool) {

		if (threadPool.isTerminated()) {
			Log.e("ANDROID_UTILS", "Cannot queue worker task, the thread pool is terminated.");
		} else {
			throw new RejectedExecutionException("Too many tasks have built up in the queue");
		}
	}

	/**
	 * Clears the queue of the thread pool.
	 */
	public void clearQueue() {
//		service.getQueue().clear();
		getQueue().clear();
	}

	/**
	 * Returns the max pool size of the ThreadPool.
	 *
	 * @return Returns the max pool size of the ThreadPool.
	 */
	public int getMaxPoolSize() {
//		return service.getMaximumPoolSize();
		return getMaximumPoolSize();
	}

	/**
	 * Returns the active task count of the ThreadPool.
	 *
	 * @return Returns the active task count of the ThreadPool.
	 */
	public int getActiveTaskCount() {
//		return service.getActiveCount();
		return getActiveCount();
	}

	/**
	 * Returns the queued task count of the thread pool.
	 *
	 * @return Returns the queued task count of the thread pool.
	 */
	public int getQueuedTaskCount() {
//		return service.getQueue().size();
		return getQueue().size();
	}

	/**
	 * Returns the uncompleted task count of the ThreadPool.
	 *
	 * @return Returns the uncompleted task count of the ThreadPool.
	 */
	public int getUncompletedTaskCount() {
//		return service.getQueue().size() - service.getActiveCount();
		return getQueue().size() - getActiveCount();
	}

	/**
	 * Submits a task to be run on a background thread pool.
	 *
	 * @param runnable The runnable to be run on a background thread.
	 */
	@NotNull
	@Override
	public Future<?> submit(Runnable runnable) {

		if (runnable == null) {
			throw new IllegalArgumentException("The runnable parameter cannot be null");
		}

//		service.submit(runnable);
		return super.submit(runnable);
	}

	/**
	 * Terminates the thread pool.
	 *
	 * @param timeout The timeout in milliseconds
	 *
	 * @throws InterruptedException
	 */
	public void terminateThreadPool(int timeout) throws InterruptedException {
//		service.awaitTermination(timeout, TimeUnit.MILLISECONDS);
		awaitTermination(timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * Terminate the thread pool.
	 *
	 * @param shouldFinishQueue Should the pool wait for tasks to finish before terminating.
	 *
	 * @throws InterruptedException
	 */
	public void terminateThreadPool(boolean shouldFinishQueue) throws InterruptedException {

		if (shouldFinishQueue) {
			terminateThreadPool(30000);
		} else {
//			service.shutdownNow();
			shutdownNow();
		}
	}
}