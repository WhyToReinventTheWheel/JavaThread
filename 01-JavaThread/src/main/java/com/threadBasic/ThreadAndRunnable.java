package com.threadBasic;

class TestThread implements Runnable{
	public void run() {
		int count=0;
		while(count < 10){
			System.out.println("Thread Count : "+count);
			count++;
		}
	}
}

public class ThreadAndRunnable 
{
    public static void main( String[] args )
    {
    	Thread th=new Thread(new TestThread());
    	th.start();
        System.out.println( "Main End!" );
    }
}
