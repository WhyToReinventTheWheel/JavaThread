package com.mk.executorServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamingExecutorThreads {
	
	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());
		
		execService.execute(new Task());
		execService.execute(new Task());
		execService.execute(new Task());
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
	
	private static class NamedThreadsFactory implements ThreadFactory {
		
		private static int count = 0;
		private static String NAME = "MyThread-";
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r, NAME + ++count);
			return t;
		}
	}
	
	
	private static class Task implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
		}
	}
}
