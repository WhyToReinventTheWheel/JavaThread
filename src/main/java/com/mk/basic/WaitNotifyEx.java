package com.mk.basic;

public class WaitNotifyEx {
	private static class Processor {

		public void produce() throws InterruptedException {
			synchronized (this) {
				System.out.println("We are in the producer method...");
				wait(30000);
				System.out.println("Again producer method...");
			}
		}

		public void consume() throws InterruptedException {
			Thread.sleep(1000);
			synchronized (this) {
				System.out.println("Consumer method start");
				//notify();
				notifyAll();
				System.out.println("Consumer method before sleep");
				Thread.sleep(3000);
			}
			System.out.println("Consumer method End ");
		}
	}

	public static void main(String[] args) {
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}