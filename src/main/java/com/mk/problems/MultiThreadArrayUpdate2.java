package com.mk.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class TradeLock {
	private final int id; // Better make this ID immutable, help to find the element in array or map
							// ////KEY
	private int price;

	private final Lock lock = new ReentrantLock();

	public Lock getLock() {
		return lock;
	}

	public TradeLock(int id, int price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

public class MultiThreadArrayUpdate2 {

	static private List<TradeLock> tradeList = new ArrayList<>();

	public static void main(String[] args) {
		tradeList.add(new TradeLock(10, 1000));
		tradeList.add(new TradeLock(20, 2000));

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		executorService.execute(new ThreadDemo("Thread10::111---", 10, 1111));
		executorService.execute(new ThreadDemo("Thread20:::222---", 20, 2222));
		executorService.execute(new ThreadDemo("Thread10:::333---", 10, 1212));
		executorService.execute(new ThreadDemo("Thread20:::444---", 20, 2424));

		executorService.shutdown();
	}

	private static class ThreadDemo extends Thread {
		private String threadName;
		final private int tradeId;
		final private int newPrice;

		public ThreadDemo(String threadName, int tradeId, int newPrice) {
			this.threadName = threadName;
			this.tradeId = tradeId;
			this.newPrice = newPrice;
		}

		public void run() {
			TradeLock obj = null;
			for (int i = 0; i < tradeList.size(); i++) {
				if (tradeList.get(i).getId() == tradeId) {
					obj = tradeList.get(i);
				}
			}

			System.out.println(threadName + " Waiting for Lock..TradeId=" + obj.getId());
			obj.getLock().lock();

			System.out.println(threadName + " Holding lock. :TradeId=" + obj.getId() + ", OldPrice=" + obj.getPrice());
			obj.setPrice(newPrice);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName + " Release lock...TradeId=" + obj.getId());

			obj.getLock().unlock();
		}
	}

}
