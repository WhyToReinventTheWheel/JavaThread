package com.mk.basic;

import java.util.concurrent.TimeUnit;

public class NamingThread {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		new Thread(new Task(), "MyThread-1").start();
		
		Thread t2 = new Thread(new Task());
//		t2.setName("MyThread-2");
		t2.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.setName("MyThread-2");
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
	
	
	
	private static class Task implements Runnable {
		@Override
		public void run() {
			for (int i=10; i>0; i--) {
				System.out.println(Thread.currentThread().getName() + ", Count:-"+ i);
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
