package com.mk.problems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * BlockingQueue -> an interface that represents a queue that is thread safe Put
 * items or take items from it ...
 * 
 * For example: one thread putting items into the queue and another thread
 * taking items from it at the same time !!! We can do it with producer-consumer
 * pattern !!!
 * 
 * put() putting items to the queue take() taking items from the queue
 * 
 */


public class ConsumerProducerBlockingQueue {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		//BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		//BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);  // Can we unbounded, use seprate lock for head and tail 
		//BlockingQueue<String> queue = new PriorityBlockingQueue<>(10);
		//BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		
		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);

		new Thread(firstWorker).start();
		new Thread(secondWorker).start();

	}
	
	
	private static class FirstWorker implements Runnable {

		private BlockingQueue<String> blockingQueue;

		public FirstWorker(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		
		public void run() {
			int count = 0;
			try {
				while (true) {
					Thread.sleep(500);
					System.out.println("@@@@ Put Waiting...Size==" + blockingQueue.size());
					blockingQueue.put("Data:" + count);
					System.out.println("@@@@ Puted data ...Size==" + blockingQueue.size());
					count++;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class SecondWorker implements Runnable {

		private BlockingQueue<String> blockingQueue;

		public SecondWorker(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		
		public void run(){
			try {

				while (true) {
					Thread.sleep(5000);
					System.out.println("$$$ Take Waiting...");
					System.out.println("$$$ Taken Data:-- "+blockingQueue.take());
					System.out.println("$$$ Took data:-- Size==" + blockingQueue.size());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
