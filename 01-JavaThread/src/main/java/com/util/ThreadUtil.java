package com.util;


public class ThreadUtil implements Runnable {
	int noOfRepetition;
	String threadName=new String();
	Task task;
	public ThreadUtil(Task task,String threadName,int noOfRepetition){
		this.task=task;
		this.threadName=threadName;
		this.noOfRepetition=noOfRepetition;
	}
	
	public void run() {
		int count=0;
		while( count < noOfRepetition){
			try {
				task.performTask(threadName +":" +"Count:"+ count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			count++;
		}
	}
}
