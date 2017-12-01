package com.mk.joins;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoiningExecutorThreads {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService execService = Executors.newCachedThreadPool();

		CountDownLatch doneSignal = new CountDownLatch(2);

		execService.execute(new CountDownLatchTask(doneSignal,"taskId1",1000));
		execService.execute(new CountDownLatchTask(doneSignal,"taskId2",500));
		
		execService.shutdown();

		try {
			doneSignal.await();
			System.out.println("GOT THE SIGNAL TO CONTINUE ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Main thread ends here...");
	}
}
