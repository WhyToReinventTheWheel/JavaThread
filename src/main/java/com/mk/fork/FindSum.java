package com.mk.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindSum {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(100);
		System.out.println(forkJoinPool.invoke(simpleRecursiveAction));
	}

	private static class SimpleRecursiveAction extends RecursiveTask<Integer> {
		private int simulatedWork;

		public SimpleRecursiveAction(int simulatedWork) {
			this.simulatedWork = simulatedWork;
		}

		@Override
		protected Integer compute() {
			if (simulatedWork > 10) {

				System.out.println("Parallel execution and split the tasks..." + simulatedWork);

				int index = simulatedWork / 2;
				
				SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(index);
				SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(index + (simulatedWork % 2));

				simpleRecursiveAction1.fork();
				simpleRecursiveAction2.fork();

				int solution = 0;
				solution = solution + simpleRecursiveAction1.join();
				solution = solution + simpleRecursiveAction2.join();

				return solution;
			} else {
				System.out.println("No need for parallel execution, sequential is OK for this task...");
				return 2 * simulatedWork;
			}
		}
	}
}
