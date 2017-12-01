package com.mk.basic;

public class YieldEx {

	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();

		for (int i = 0; i < 5; i++) {
			/* Whenever a thread calls java.lang.Thread.yield method, 
			it gives hint to the thread scheduler that it is ready to pause its execution. 
			Thread scheduler is free to ignore this hint.*/
			
			Thread.yield();

			// After execution of child Thread
			// main thread takes over
			System.out.println(Thread.currentThread().getName() + " in control");
		}

	}

	// MyThread extending Thread
	private static class MyThread extends Thread {
		public void run() {
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + " in control");
		}
	}

}
