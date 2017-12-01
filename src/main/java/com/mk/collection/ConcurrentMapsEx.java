package com.mk.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapsEx {

	public static void main(String[] args) {
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

		FirstWorker firstWorker = new FirstWorker(map);
		SecondWorker secondWorker = new SecondWorker(map);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
	}
	
	private static class FirstWorker implements Runnable {
		private ConcurrentMap<String, Integer> map;
		public FirstWorker(ConcurrentMap<String, Integer> map) {
			this.map = map;
		}

		@Override
		public void run() {
			try {
				map.put("B",1);
				map.put("H",2);
			    Thread.sleep(500);
				map.put("F",3);
	            map.put("A",4);
	            map.put("E",5);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }	
		}
	}

	private static class SecondWorker implements Runnable {
		private ConcurrentMap<String, Integer> map;
		public SecondWorker(ConcurrentMap<String, Integer> map) {
			this.map = map;
		}

		@Override
		public void run() {
			try {
			   Thread.sleep(1000);
	            System.out.println(map.get("A"));
	            System.out.println(map.get("E"));
	            System.out.println(map.get("C"));
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	
}
