package com.synchronization;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.doa.DBSyncBlockDemo;
import com.util.Task;
import com.util.ThreadUtil;

class WriteDBTaskBlock implements Task {
	Random rand = new Random();
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncBlockDemo.getInstance().write(rand.nextInt(100),taskDetail);
	}
}

class ReadDBTaskBlock implements Task {
	public void performTask(String taskDetail) throws InterruptedException{
		DBSyncBlockDemo.getInstance().read(taskDetail);
	}
}

public class SynchronizationBlockMain {
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(20);//creating a pool of 20 threads  
		for (int i = 0; i < 1; i++) {  
			WriteDBTaskBlock test=new WriteDBTaskBlock();
			executor.execute(new ThreadUtil(test,"WriteThread:"+i,5));
			executor.execute(new ThreadUtil(test,"ReadThread:"+i,5));
		}             
	}
}
