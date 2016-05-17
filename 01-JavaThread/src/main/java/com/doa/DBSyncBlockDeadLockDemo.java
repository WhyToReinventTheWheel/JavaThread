package com.doa;

//Synchronization lock occur on object so keep make this class singleton for demo
public class DBSyncBlockDeadLockDemo {
	private static final DBSyncBlockDeadLockDemo instnace = new DBSyncBlockDeadLockDemo();
	private DBSyncBlockDeadLockDemo() {
	}

	public static DBSyncBlockDeadLockDemo getInstance() {
		return instnace;
	}

	
	
	private int data = 0;
	Object lock1=new Object();
	Object lock2=new Object();
	public void write(int data,String threadDetail) throws InterruptedException {
		synchronized(lock1){
			System.out.println("**** WriteBlock  Start: lock1 : Data->" + data +"    :"+threadDetail);
			synchronized(lock2){
				System.out.println("**** WriteBlock  Start: lock2 : Data->" + data +"    :"+threadDetail);
				Thread.sleep(500);
				this.data = data;
				System.out.println("**** WriteBlock  End: lock2 : Data->" + data +"    :"+threadDetail);
			}
			System.out.println("**** WriteBlock  End: lock1 : Data->" + data +"    :"+threadDetail);
		}
	}

	public int read(String threadDetail) throws InterruptedException {
		synchronized(lock2){
			System.out.println("ReadBlock : Start :lock2 Data->" + data+"    :"+threadDetail);
			synchronized(lock1){
				System.out.println("ReadBlock : Start :lock1 Data->" + data+"    :"+threadDetail);
				Thread.sleep(500);
				System.out.println("ReadBlock : End :lock1 Data->" + data+"    :"+threadDetail);
			}
			System.out.println("ReadBlock : End :lock2 Data->" + data+"    :"+threadDetail);
			return data;
		}
	}
}
