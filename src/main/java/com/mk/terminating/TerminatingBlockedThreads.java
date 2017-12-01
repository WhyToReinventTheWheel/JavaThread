package com.mk.terminating;

import java.util.concurrent.TimeUnit;


public class TerminatingBlockedThreads {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new RunnableTask(), "MyThread-1");
		t1.start();
		TimeUnit.MILLISECONDS.sleep(3000);
		
		System.out.println("Interrupting ");
		t1.interrupt();
		
		System.out.println("Main thread ends here...");
	}
	
	private static class RunnableTask implements Runnable {

		@Override
		public void run() {
			System.out.println("##### RunnableTask #####");
			try {
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("##### RunnableTask InterruptedException  #####");
				e.printStackTrace();
			}
		}
	}
}
