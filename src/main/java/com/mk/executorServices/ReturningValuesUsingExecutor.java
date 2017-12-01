package com.mk.executorServices;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
public class ReturningValuesUsingExecutor {
	
	public static void main(String[] args) {
		System.out.println("Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool();
		Future<Integer> result1 = execService.submit(new CallableTaskEx(10));
		Future<?> result2       = execService.submit(new RunnableTaskEx());
		Future<Double> result3  = execService.submit(new RunnableTaskEx(), 999.888);
		
		execService.shutdown();
		
		try {
			System.out.println("Result-1 = " + result1.get());
			System.out.println("Result-2 = " + result2.get());
			System.out.println("Result-3 = " + result3.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main thread ends here...");
	}
	
	
	private static class CallableTaskEx implements Callable<Integer> {
		private int a;
		public CallableTaskEx(int a) {
			this.a = a;
		}
		
		@Override
		public Integer call() throws Exception {
			System.out.println("##### CallableTaskEx ##########");
			TimeUnit.MILLISECONDS.sleep(2);
			return 2* a;
		}
	}
	
	private static class RunnableTaskEx implements Runnable {
		@Override
		public void run() {
			System.out.println("##### RunnableTaskEx ##########");
		}
	}
}
