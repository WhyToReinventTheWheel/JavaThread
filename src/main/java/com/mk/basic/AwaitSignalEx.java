package com.mk.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class AwaitSignalEx {

	private static class Worker {

		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		// private List<Integer> list = new ArrayList<>();

		public void produce() throws InterruptedException {
			lock.lock();
			System.out.println("Producer method...");
			condition.await();
			System.out.println("Producer method again");
			lock.unlock();
		}

		public void consume() throws InterruptedException {
			Thread.sleep(2000);
			lock.lock();
			System.out.println("Consumer method...");
			condition.signal();
			Thread.sleep(5000);
			System.out.println("Consumer method Lock Complete");
			lock.unlock();
			Thread.sleep(5000);
			System.out.println("Consumer method after lock Complete");
		
		}
	}
	
	
	public static void main(String[] args) {

		final Worker worker = new Worker();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

	}
}