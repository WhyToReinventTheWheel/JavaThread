# Condition
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	//Producer
	lock.lock();
	condition.await();
	lock.unlock();
	//Consumer
	lock.lock();
	condition.signal();
	lock.unlock();
	
# CountDownLatch
	CountDownLatch latch = new CountDownLatch(5); 
	
	try {
			latch.await();    // Waiting here to complete all task
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	// Thread
	public void run() {
		doWork();
		latch.countDown();   // Making down
	}