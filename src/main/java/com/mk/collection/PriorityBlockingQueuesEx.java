package com.mk.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * It implements the BlockingQueue interface
 * 
 * 	- unbounded concurrent queue
 * 	- it uses the same ordering rules as the java.util.PriorityQueue class -> have to implement the COmparable interface
 * 			The comparable interface will determine what will the order in the queue
 * 
 * 			The priority can be the same compare() == 0 case
 * 
 *  - no null items !!!
 * 
 *
 */

public class PriorityBlockingQueuesEx {

	public static void main(String[] args) {
		
		//BlockingQueue<String> queue = new PriorityBlockingQueue<>(10);
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();

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

		@Override
		public void run() {
			try {
				blockingQueue.put("E");
				blockingQueue.put("H");
				blockingQueue.put("F");
	            Thread.sleep(1000);
	            System.out.println("------------ Putting A -------------");
	            blockingQueue.put("A");
	            Thread.sleep(1000);
	            System.out.println("------------ Putting B -------------");
	            blockingQueue.put("B");
	            Thread.sleep(1000);
	            System.out.println("------------ Putting c -------------");
	            blockingQueue.put("C");
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

		@Override
		public void run() {
			
			try {
				while(true) {
					System.out.println(blockingQueue.take());
					Thread.sleep(1000);
				}
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}
}
