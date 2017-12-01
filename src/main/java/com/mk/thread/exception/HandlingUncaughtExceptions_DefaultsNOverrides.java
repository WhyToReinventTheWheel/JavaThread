package com.mk.thread.exception;

import com.mk.common.ExceptionLeakingTask;
import com.mk.common.ThreadExceptionHandler;

public class HandlingUncaughtExceptions_DefaultsNOverrides {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("THE_DEFAULT_ONE"));

		Thread t1 = new Thread(new ExceptionLeakingTask(), "MyThread-1");

		Thread t2 = new Thread(new ExceptionLeakingTask(), "MyThread-2");
		t2.setUncaughtExceptionHandler(new ThreadExceptionHandler("Custom_Handler_1"));

		Thread t3 = new Thread(new ExceptionLeakingTask(), "MyThread-3");

		Thread t4 = new Thread(new ExceptionLeakingTask(), "MyThread-4");
		t4.setUncaughtExceptionHandler(new ThreadExceptionHandler("Custom_Handler_2"));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
