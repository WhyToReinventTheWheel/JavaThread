package com.mk.problems;

import java.util.ArrayList;
import java.util.List;

class Trade {
	private final int id; // Better make this ID immutable, help to find the element in array or map
							// ////KEY
	private int price;

	public Trade(int id, int price) {
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

public class MultiThreadArrayUpdate {

	static private List<Trade> tradeList = new ArrayList<Trade>();

	public static void main(String[] args) {
		tradeList.add(new Trade(10, 1000));
		tradeList.add(new Trade(20, 2000));

		ThreadDemo t1 = new ThreadDemo("Thread10::111---", 10, 1111);
		t1.start();

		ThreadDemo t2 = new ThreadDemo("Thread20:::222---", 20, 2222);
		t2.start();

		ThreadDemo t3 = new ThreadDemo("Thread10:::333---", 10, 1212);
		t3.start();

		ThreadDemo t4 = new ThreadDemo("Thread20:::444---", 20, 2424);
		t4.start();
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
			Trade obj = null;
			for (int i = 0; i < tradeList.size(); i++) {
				if (tradeList.get(i).getId() == tradeId) {
					obj = tradeList.get(i);
				}
			}

			System.out.println(threadName + " Waiting for Lock..TradeId=" + obj.getId());
			synchronized (obj) {
				System.out.println(
						threadName + " Holding lock. :TradeId=" + obj.getId() + ", OldPrice=" + obj.getPrice());
				obj.setPrice(newPrice);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName + " Release lock...TradeId=" + obj.getId());
			}
		}
	}

}
