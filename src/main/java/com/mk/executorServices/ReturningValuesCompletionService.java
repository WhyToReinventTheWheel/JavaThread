package com.mk.executorServices;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReturningValuesCompletionService {
	
	public static void main(String[] args) {
		ExecutorService execService = Executors.newCachedThreadPool();
		
		CompletionService<TaskResult<Integer, Integer>> tasks = new ExecutorCompletionService<>(execService);
		
		tasks.submit(new callableTaskEx(2));
		tasks.submit(new RunnableTaskEx(), new TaskResult<Integer, Integer>(100, 999));
		
		execService.shutdown();

		for (int i = 0; i < 2; i++) {
			try {
				Future<TaskResult<Integer, Integer>> result=tasks.take();
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Main thread ends here...");
	}
	
	private static class callableTaskEx implements Callable<TaskResult<Integer, Integer>> {
		static private int taskId;
		private int a;
		
		public callableTaskEx(int a) {
			this.a = a;
			taskId++;
		}

		@Override
		public TaskResult<Integer, Integer> call() throws Exception {
			TimeUnit.MILLISECONDS.sleep(4);
			return new TaskResult<Integer, Integer>(taskId, 2*a);
		}
	}
	
	private static class RunnableTaskEx implements Runnable {
		@Override
		public void run() {
			System.out.println("##### RunnableTaskEx ##########");
		}
	}
	
	private static  class TaskResult<S, R> {
		private S taskId;
		private R result;
		
		public TaskResult(S taskId, R result) {
			this.taskId = taskId;
			this.result = result;
		}

		@Override
		public String toString() {
			return "~~~~~TaskResult [taskId=" + taskId + ", result=" + result + "]~~~~~";
		}
	}
}
