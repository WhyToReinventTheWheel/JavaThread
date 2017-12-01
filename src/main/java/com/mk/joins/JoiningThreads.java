package com.mk.joins;

import java.util.concurrent.TimeUnit;


public class JoiningThreads {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Task("MyThread-1",500));
		Thread t2 = new Thread(new Task("MyThread-2",1000));
		Thread t3 = new Thread(new Task("MyThread-3",100));
		Thread t4 = new Thread(new Task("MyThread-4",2000));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		System.out.println("t1 thread ends here...");
		t2.join();
		System.out.println("t2 thread ends here...");
		t3.join();
		System.out.println("t3 thread ends here...");
		t4.join();
		System.out.println("t4 thread ends here...");
		
		System.out.println("Main thread ends here...");
	}
	
	
	public static class Task implements Runnable {
		private String taskId;
		private int sleep;
		
		@Override
		public void run() {
			for (int i = 1; i < 10; i++) {
				System.out.println("TICK TICK " + i + ", taskId = "+taskId );

				try {
					TimeUnit.MILLISECONDS.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public Task(String taskId,int sleep) {
			this.taskId = taskId;
			this.sleep=sleep;
		}
	}
}
