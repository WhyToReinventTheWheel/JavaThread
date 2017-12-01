package com.mk.executorServices;

// Main Thread also user thread
// Daemon Thread untill any user thread live


public class DaemonThreads {

	public static void main(String[] args) throws InterruptedException {
		
		///////////////////
		
		Thread t1 = new Thread(new Task(), "Thread-1");
		t1.setDaemon(true);
		t1.start();
		/////////////////////////
		
		Thread userThread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Thread t2 = new Thread(new Task(), "Thread-2");
				t2.setDaemon(true);
				t2.start();
				
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("================ User Thread11111111 End=========================");
			}
		});
		userThread1.start();
		
		Thread userThread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Thread t2 = new Thread(new Task(), "Thread-3");
				t2.setDaemon(true);
				t2.start();
				
				
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("========================== User Thread2222222222 End ========================================");
			}
		});
		
		userThread2.start();
		
		Thread.sleep(10000);
		
		
		System.out.println("@@@@@@@@@@@@@@@ Main Thread End @@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	private static class Task implements Runnable {

		@Override
		public void run() {
			boolean isRunningInDaemonThread = Thread.currentThread().isDaemon();
			String threadType = isRunningInDaemonThread ? "DAEMON" : "USER";
			String currentThreadName = Thread.currentThread().getName();
			
			System.out.println("threadType=" + threadType +", currentThreadName="+currentThreadName);
			
			for (int i=1; i<1000; i++) {
				System.out.println(currentThreadName +"-- TICK TICK " + i);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
