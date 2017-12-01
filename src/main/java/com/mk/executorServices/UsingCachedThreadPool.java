package com.mk.executorServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class UsingCachedThreadPool {
	
	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());
		
		execService.execute(new Task());	//Task-1
		execService.execute(new Task());	//Task-2
		execService.execute(new Task());	//Task-3
		
		TimeUnit.SECONDS.sleep(5);
		
		execService.execute(new Task());	//Task-4
		execService.execute(new Task());	//Task-5
		execService.execute(new Task());	//Task-6
		execService.execute(new Task());	//Task-7
		execService.execute(new Task());	//Task-8
		
		execService.shutdown();
		
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
	
	
	private static class Task implements Runnable  {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
