package com.mk.basic;

public class Testing {

	public static void main(String[] args) {
		
		
		

		Thread t1 = new Thread(new Thread() {
			public void run() {
				System.out.println("Thread1");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Thread2");
			}
		});

		t1.start();
		t2.start();

	}
}