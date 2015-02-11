package com.nityankhanna.androidutils.system.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * A thread pool service that queues tasks to be executed.
 */
public class ThreadPool
{
	private static ThreadPool sharedInstance;
	private ThreadPoolExecutor service;

	private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
	{
		service = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory());
		service.allowCoreThreadTimeOut(true);
	}

	/**
	 * Returns a shared instance of the ThreadPool class.
	 *
	 * @return Returns a shared instance of the ThreadPool class.
	 */
	public static ThreadPool getInstance()
	{
		synchronized (ThreadPool.class)
		{
			if (sharedInstance == null)
			{
				sharedInstance = new ThreadPool(15, 20, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(15, true));
			}
		}

		return sharedInstance;
	}

	/**
	 * Clears the queue of the thread pool.
	 */
	public void clearQueue()
	{
		service.getQueue().clear();
	}

	/**
	 * Returns the max pool size of the ThreadPool.
	 *
	 * @return Returns the max pool size of the ThreadPool.
	 */
	public int getMaxPoolSize()
	{
		return service.getMaximumPoolSize();
	}

	/**
	 * Returns the active task count of the ThreadPool.
	 *
	 * @return Returns the active task count of the ThreadPool.
	 */
	public int getActiveTaskCount()
	{
		return service.getActiveCount();
	}

	/**
	 * Returns the queued task count of the thread pool.
	 *
	 * @return Returns the queued task count of the thread pool.
	 */
	public int getQueuedTaskCount()
	{
		return service.getQueue().size();
	}

	/**
	 * Returns the uncompleted task count of the ThreadPool.
	 *
	 * @return Returns the uncompleted task count of the ThreadPool.
	 */
	public int getUncompletedTaskCount()
	{
		return service.getQueue().size() - service.getActiveCount();
	}

	/**
	 * Removes this task from the executor's internal queue if it is present, thus causing it not to be run if it has not already started.
	 *
	 * @param runnable The task to remove.
	 */
	public boolean remove(Runnable runnable)
	{
		return service.remove(runnable);
	}

	/**
	 * Gets the rejected execution handler.
	 *
	 * @return The rejected execution handler.
	 */
	public RejectedExecutionHandler getRejectedExecutionHandler()
	{
		return service.getRejectedExecutionHandler();
	}

	/**
	 * Sets the rejected execution handler, to handle errors on when submitting and running tasks on the thread pool.
	 *
	 * @param rejectedExecutionHandler The rejected execution handler.
	 */
	public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler)
	{
		service.setRejectedExecutionHandler(rejectedExecutionHandler);
	}

	/**
	 * Submits a task to be run on a background thread pool.
	 *
	 * @param runnable The runnable to be run on a background thread.
	 */
	public void submit(Runnable runnable)
	{
		service.submit(runnable);
	}

	/**
	 * Terminates the thread pool.
	 * Defaults to 30 seconds for timeout.
	 * @throws InterruptedException Interrupted Exception
	 */
	public void terminateThreadPool() throws InterruptedException
	{
		terminateThreadPool(false, 30000, TimeUnit.MILLISECONDS);
	}

	/**
	 * Terminates the thread pool.
	 *
	 * @param shouldFinishQueue Should the pool wait for tasks to finish before terminating.
	 * Defaults to 30 seconds for timeout.
	 * @throws InterruptedException Interrupted Exception
	 */
	public void terminateThreadPool(boolean shouldFinishQueue) throws InterruptedException
	{
		terminateThreadPool(shouldFinishQueue, 30000, TimeUnit.MILLISECONDS);
	}

	/**
	 * Terminates the thread pool.
	 * @param shouldFinishQueue Should the pool wait for tasks to finish before terminating.
	 * @param timeout The time to wait before the thread pool shuts down.
	 * @param timeUnit The time unit.
	 * @throws InterruptedException
	 */
	public void terminateThreadPool(boolean shouldFinishQueue, int timeout, TimeUnit timeUnit) throws InterruptedException
	{
		if (shouldFinishQueue)
		{
			service.awaitTermination(timeout, timeUnit);
		}
		else
		{
			service.shutdownNow();
		}
	}
}