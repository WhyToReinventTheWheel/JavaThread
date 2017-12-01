package com.mk.terminating;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TerminatingAllExecutorTasks {

	public static void main(String[] args) throws Exception {
		ExecutorService execService = Executors.newCachedThreadPool();

		RunnableTask runnableTask = new RunnableTask();
		CallableTask callableTask = new CallableTask(1);

		execService.execute(runnableTask);
		Future<String> futureCallable = execService.submit(callableTask);
		execService.shutdownNow();

		/*
		 * Blocks until all tasks have completed execution after a shutdown request, or
		 * the timeout occurs, or the current thread is interrupted, whichever happens
		 * first. Parameters: timeout - the maximum time to wait unit - the time unit of
		 * the timeout argument
		 */

		System.out.println(execService.awaitTermination(5000, TimeUnit.MILLISECONDS));
		try {
			System.out.print("] 'callableTask' Result = " + futureCallable.get());
		} catch (ExecutionException ee) {
			System.out.println("] 'callableTask Result = Got ExecutionException. The cause is : \n");
			ee.getCause().printStackTrace();
		}

		System.out.println("] Main thread ends here...");
	}

	private static class CallableTask implements Callable<String> {
		private int id;

		public CallableTask(int id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			Thread.sleep(10000);
			return "Id: " + this.id;
		}
	}

	private static class RunnableTask implements Runnable {

		@Override
		public void run() {
			System.out.println("##### RunnableTask #####");
			try {
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("##### RunnableTask InterruptedException  #####");
				//e.printStackTrace();
				throw new ArithmeticException();
			}
		}
	}
}
