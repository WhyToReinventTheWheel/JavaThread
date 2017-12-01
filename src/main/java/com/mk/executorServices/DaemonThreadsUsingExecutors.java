package com.mk.executorServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mk.common.NamedThreadsFactory;

public class DaemonThreadsUsingExecutors {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService execService = Executors.newCachedThreadPool(new DaemonThreadsFactory());

		execService.execute(new Task("Task1"));
		execService.execute(new Task("Task2"));

		execService.shutdown();
		Thread.sleep(3000);
		System.out.println("@@@@@@@@@@@@ Main thread ends here @@@@@@@@@@@@@");
	}

	private static class DaemonThreadsFactory extends NamedThreadsFactory {
		private static int count = 0;

		public Thread newThread(Runnable r) {
			Thread t = super.newThread(r);
			count++;
			if (count % 2 == 0) {
				t.setDaemon(true);
			}
			return t;
		}
	}

	private static class Task implements Runnable {
		private String taskId;

		public Task(String taskId) {
			this.taskId = taskId;
		}

		@Override
		public void run() {
			boolean isRunningInDaemonThread = Thread.currentThread().isDaemon();
			String threadType = isRunningInDaemonThread ? "DAEMON" : "USER";
			String currentThreadName = Thread.currentThread().getName();

			System.out.println(
					"TaskId=" + taskId + " ,threadType=" + threadType + ", currentThreadName=" + currentThreadName);

			int counter = 20;
			if(isRunningInDaemonThread) {
				counter = 100;
			}
			
			for (int i = 1; i < counter; i++) {
				System.out.println("TaskId=" + taskId + ", " + currentThreadName + "-- TICK TICK " + i);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
