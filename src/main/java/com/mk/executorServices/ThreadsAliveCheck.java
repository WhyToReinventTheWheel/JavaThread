package com.mk.executorServices;

import java.util.concurrent.TimeUnit;


public class ThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		Thread t1 = new Thread(new Task(), "MyThread-1");
		Thread t2 = new Thread(new Task(), "MyThread-2");
		
		boolean t1IsAlive = t1.isAlive();
		boolean t2IsAlive = t2.isAlive();
		
		System.out.println("[" + currentThreadName + "]BEFORE STARTING: Is '" + t1.getName() + "' alive = " + t1IsAlive);
		System.out.println("[" + currentThreadName + "]BEFORE STARTING: Is '" + t2.getName() + "' alive = " + t2IsAlive);
		
		t1.start();
		t2.start();
		
		while(true) {
			TimeUnit.MILLISECONDS.sleep(600);
			
			t1IsAlive = t1.isAlive();
			t2IsAlive = t2.isAlive();
			
			System.out.println("[" + currentThreadName + "] Is '" + t1.getName() + "' alive = "	+ t1IsAlive);
			System.out.println("[" + currentThreadName + "] Is '" + t2.getName() + "' alive = "	+ t2IsAlive);
			
			if (!t1IsAlive && !t2IsAlive) {
				break;
			}
			
		}
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
	
	static private class Task implements Runnable {

		@Override
		public void run() {
			double sleepTime =  Math.random();
			System.out.println("=========== Sleeptime:"+sleepTime);
			
			for (int i=10; i>0; i--) {
				try {
					System.out.println(Thread.currentThread().getName() +",Working :"+i);
					TimeUnit.MILLISECONDS.sleep((long)(sleepTime * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
