package com.mk.basic;

import java.util.concurrent.Exchanger;

/**
 * With the help of Exchanger -> two threads can exchange objects
 * 
 * exchange() -> exchanging objects is done via one of the two exchange()
 * methods
 * 
 * 	For example: genetic algorithms, training neural networks
 *
 */

public class ExchangerEx {

	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();

		new Thread(new FirstWorker(exchanger)).start();
		new Thread(new SecondWorker(exchanger)).start();
	}
	
	private static class FirstWorker implements Runnable {
		private int counter;
		private Exchanger<Integer> exchanger;

		public FirstWorker(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			while (true) {
				counter = counter + 1;
				System.out.println("FirstWorker incremented the counter1: " + counter);
				
				try {
					counter = exchanger.exchange(counter);
					System.out.println("FirstWorker incremented the counter2: " + counter);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class SecondWorker implements Runnable {
		private int counter;
		private Exchanger<Integer> exchanger;

		public SecondWorker(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			while (true) {
				counter = counter - 1;
				System.out.println("SecondWorker decremented the counter1: " + counter);
				try {
					Thread.sleep(5000);
					counter = exchanger.exchange(counter);
					System.out.println("SecondWorker decremented the counter2: " + counter);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}