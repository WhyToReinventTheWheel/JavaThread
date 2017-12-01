package com.mk.executors.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mk.common.ExceptionLeakingTask;
import com.mk.common.ThreadExceptionHandler;
import com.mk.common.ThreadFactoryWithExceptionHandlerAlternator;

public class HandlingExecutorUncaughtExceptions_DefaultsNOverrides {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("THE_DEFAULT_ONE"));
		ExecutorService execService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandlerAlternator());

		execService.execute(new ExceptionLeakingTask());
		execService.execute(new ExceptionLeakingTask());
		execService.shutdown();
	}
}
