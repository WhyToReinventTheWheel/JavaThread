package com.doa;

//Synchronization lock occur on object so keep make this class singleton for demo
public class DBSyncMethodDemo {
	private int data = 0;
	private static DBSyncMethodDemo instnace = new DBSyncMethodDemo();

	private DBSyncMethodDemo() {
	}

	public static DBSyncMethodDemo getInstance() {
		return instnace;
	}

	synchronized public void write(int data ,String threadDetail) throws InterruptedException {
		System.out.println("**** Write  : Start : Data->" + data +"    :"+threadDetail);
		Thread.sleep(500);
		this.data = data;
		System.out.println("**** Write  : End : Data->" + data+"    :"+threadDetail);
	}

	synchronized public int read(String threadDetail) throws InterruptedException {
		System.out.println("Read : Start : Data->" + data+"    :"+threadDetail);
		Thread.sleep(500);
		System.out.println("Read : End : Data->" + data+"    :"+threadDetail);
		return data;
	}
}
