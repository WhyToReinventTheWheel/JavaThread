package com.mk.executors.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mk.common.ExceptionLeakingTask;
import com.mk.common.ThreadFactoryWithExceptionHandler;


public class HandlingExecutorUncaughtExceptionsDifferentlyPerThread {

	public static void main(String[] args) {
		ExecutorService execService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandler());
		
		execService.execute(new ExceptionLeakingTask());
		execService.execute(new ExceptionLeakingTask());

		execService.shutdown();
	}
}
