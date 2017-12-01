package com.mk.executorServices;

import java.util.concurrent.TimeUnit;

public class ReturningValuesFromThread {

	public static void main(String[] args) {
		ValueReturningTaskA task1 = new ValueReturningTaskA(2, 3);
		Thread t1 = new Thread(task1, "Thread-1");
		t1.start();
		System.out.println("Result-1 = " + task1.getSum());
	}

	private static class ValueReturningTaskA implements Runnable {
		private int a;
		private int b;
		private int sum;

		private volatile boolean done = false;

		public ValueReturningTaskA(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sum = a + b;
			done = true;

			synchronized (this) {
				this.notifyAll();   /// Notify all thread waiting for result
			}
		}

		public int getSum() {
			if (!done) {
				synchronized (this) {
					try {
						System.out.println("[" + Thread.currentThread().getName() + "] ==== WAITING for result ");
						this.wait();     // Hold for result calculation 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			return sum;
		}
	}
}
