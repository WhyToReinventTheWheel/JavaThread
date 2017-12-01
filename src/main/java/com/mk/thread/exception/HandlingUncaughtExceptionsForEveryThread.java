package com.mk.thread.exception;

import com.mk.common.ExceptionLeakingTask;
import com.mk.common.ThreadExceptionHandler;

public class HandlingUncaughtExceptionsForEveryThread {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		new Thread(new ExceptionLeakingTask(), "MyThread-1").start();
		new Thread(new ExceptionLeakingTask(), "MyThread-2").start();
	}
}
