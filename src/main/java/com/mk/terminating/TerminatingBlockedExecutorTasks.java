package com.mk.terminating;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TerminatingBlockedExecutorTasks {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService execService = Executors.newCachedThreadPool();

		Future<?> f1 = execService.submit(new CallableTask(1));
		execService.shutdown();
		TimeUnit.SECONDS.sleep(10);
		f1.cancel(true);
		try {
			System.out.println("Main thread ends here...: " + f1.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static class CallableTask implements Callable<String> {
		private int id;

		public CallableTask(int id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			for (int i = 0; i < 100; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("count:" + i);
			}
			return "Id: " + this.id;
		}
	}

}
