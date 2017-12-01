package com.mk.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * This is an unbounded BlockingQueue of objects that implement the Delayed interface

 * - DelayQueue keeps the elements internally until a certain delay has expired
 * 
 * - an object can only be taken from the queue when its delay has expired !!! -
 * 
 * We cannot place null items in the queue - The queue is sorted so that the
 * object at the head has a delay that has expired for the longest time.
 * 
 * If no delay has expired, then there is no head element and poll( ) will
 * return null
 * 
 * size() return the count of both expired and unexpired items !!!
 *
 */

public class DelayQueuesExampleEx {

	public static void main(String[] args) {

		BlockingQueue<Worker> blockingQueue = new DelayQueue<Worker>();

		try {
			blockingQueue.put(new Worker(1000, "This is message #1"));
			blockingQueue.put(new Worker(10000, "This is message #2"));
			blockingQueue.put(new Worker(4000, "This is message #3"));
			blockingQueue.put(new Worker(6000, "This is message #4"));
			blockingQueue.put(new Worker(500, "This is message #5"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (!blockingQueue.isEmpty()) {
			try {
				System.out.println(blockingQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Worker implements Delayed {

		private long duration;
		private String message;

		public Worker(long duration, String message) {
			this.duration = System.currentTimeMillis() + duration;
			this.message = message;
		}

		@Override
		public int compareTo(Delayed otherDelayed) {
			if (this.duration < ((Worker) otherDelayed).getDuration()) {
				return -1;
			}

			if (this.duration > ((Worker) otherDelayed).getDuration()) {
				return 1;
			}

			return 0;
		}

		@Override
		public long getDelay(TimeUnit timeUnit) {
			return timeUnit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		public long getDuration() {
			return duration;
		}

		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {
			return this.message;
		}
	}
	
}


