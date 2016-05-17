package com.synchronization;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.doa.DBSyncBlockDeadLockDemo;
import com.util.Task;
import com.util.ThreadUtil;

class WriteDBDeadLockTaskBlock implements Task {
	Random rand = new Random();
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncBlockDeadLockDemo.getInstance().write(rand.nextInt(100),taskDetail);
	}
}

class ReadDBDeadLockTaskBlock implements Task {
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncBlockDeadLockDemo.getInstance().read(taskDetail);
	}
}

public class SynchronizationBlockMainDeadLock {
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(20);//creating a pool of 20 threads  
		for (int i = 0; i < 10; i++) {  
			executor.execute(new ThreadUtil(new WriteDBDeadLockTaskBlock(),"WriteThread:"+i,5));
			executor.execute(new ThreadUtil(new ReadDBDeadLockTaskBlock(),"ReadThread:"+i,5));
		}             
	}
}
