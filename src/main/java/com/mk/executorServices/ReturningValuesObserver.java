package com.mk.executorServices;

import java.util.concurrent.TimeUnit;

public class ReturningValuesObserver {

	public static void main(String[] args) {
		ValueReturningTaskB task1 = new ValueReturningTaskB(2, 3, new SumObserver());
		Thread t1 = new Thread(task1, "Thread-1");
		t1.start();
	}

	private interface ResultListener<T> {
		void notifyResult(T result);
	}

	private static class SumObserver implements ResultListener<Integer> {
		@Override
		public void notifyResult(Integer result) {
			System.out.println("Result = " + result);
		}
	}

	private static class ValueReturningTaskB implements Runnable {

		private int a;
		private int b;
		private int sum;

		private ResultListener<Integer> listener;

		public ValueReturningTaskB(int a, int b, ResultListener<Integer> listener) {
			this.a = a;
			this.b = b;
			this.listener = listener;
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sum = a + b;
			listener.notifyResult(sum);  //Notifying Observer
		}

	}
}
