package com.doa;

//Synchronization lock occur on object so keep make this class singleton for demo
public class DBSyncBlockDemo {
	private int data = 0;
	Object lock=new Object();
	private static final DBSyncBlockDemo instnace = new DBSyncBlockDemo();

	private DBSyncBlockDemo() {
	}

	public static DBSyncBlockDemo getInstance() {
		return instnace;
	}

	public void write(int data,String threadDetail) throws InterruptedException {
		synchronized(lock){
			System.out.println("**** WriteBlock  : Start : Data->" + data +"    :"+threadDetail);
			Thread.sleep(500);
			this.data = data;
			System.out.println("**** WriteBlock  : End : Data->" + data+"    :"+threadDetail);
		}
	}

	public int read(String threadDetail) throws InterruptedException {
		synchronized(lock){
			System.out.println("ReadBlock : Start : Data->" + data+"    :"+threadDetail);
			Thread.sleep(500);
			System.out.println("ReadBlock : End : Data->" + data+"    :"+threadDetail);
			return data;
		}
	}
}
