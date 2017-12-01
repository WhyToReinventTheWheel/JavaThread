package com.mk.executorServices;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService execService = Executors.newCachedThreadPool();

		Future<?> f1 = execService.submit(new RunnableTask());
		Future<Integer> f2 = execService.submit(new CallableTask());

		execService.shutdown();

		for (int i = 1; i <= 10; i++) {
			TimeUnit.SECONDS.sleep(1);

			System.out.println(i + " -> Is 'RunnableTask' done = " + f1.isDone());
			System.out.println(i + " -> Is 'CallableTask' done = " + f2.isDone());

		}

		System.out.println("RunnableTask' result = " + f1.get());
		System.out.println("CallableTask' result = " + f2.get());
		System.out.println("Main thread ends here...");
	}

	private static class RunnableTask implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("TICK TICK = " + i);

				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("***** RunnableTask = DONE ******");
		}
	}

	private static class CallableTask implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			TimeUnit.SECONDS.sleep(4);
			return 2;
		}
	}
}
