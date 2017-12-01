package com.mk.joins;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTask implements Runnable {

	private CountDownLatch doneCountLatch;
	private String taskId;
	private int sleep;
	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			System.out.println("TICK TICK " + i + ", taskId = "+taskId );

			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (doneCountLatch != null) {
			doneCountLatch.countDown();
			System.out.println("***** LATCH COUNT = " + doneCountLatch.getCount() + ", taskId = "+taskId );
		}
	}

	public CountDownLatchTask(CountDownLatch doneCountLatch,String taskId,int sleep) {
		this.doneCountLatch = doneCountLatch;
		this.taskId = taskId;
		this.sleep=sleep;
	}
}
