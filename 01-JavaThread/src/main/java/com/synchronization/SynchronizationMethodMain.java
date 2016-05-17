package com.synchronization;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.doa.DBSyncMethodDemo;
import com.util.Task;
import com.util.ThreadUtil;

class WriteDBTask implements Task {
	Random rand = new Random();
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncMethodDemo.getInstance().write(rand.nextInt(100),taskDetail);
	}
}

class ReadDBTask implements Task {
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncMethodDemo.getInstance().read(taskDetail);
	}
}

public class SynchronizationMethodMain {
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(20);//creating a pool of 20 threads  
		for (int i = 0; i < 10; i++) {  
			executor.execute(new ThreadUtil(new WriteDBTask(),"Thread:"+i,5));
			executor.execute(new ThreadUtil(new WriteDBTask(),"Thread:"+i,5));
		}             
	}
}
