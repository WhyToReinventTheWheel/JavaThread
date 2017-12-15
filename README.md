# NamingThread
	String currentThreadName = Thread.currentThread().getName();
	new Thread(new Task(), "MyThread-1").start();
	Thread t2 = new Thread(new Task());
	t2.setName("MyThread-2");

# Synchronized
	public static synchronized void increment(); //Static 
	public synchronized void increment();  //Instance method
	
	synchronized (this) {        // On object 
	}
	
# ThreadLocal
	private static class MyRunnable implements Runnable {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
		public void run() {
			threadLocal.set((int) (Math.random() * 100D));
			System.out.println(threadLocal.get());    //Printing Data As per thread
		}
	}

# ReentrantLock
	private static Lock lock = new ReentrantLock();
	try {
		lock.lock();
		doWork();
	}finally {
		lock.unlock();
	}
	
# Semaphore
	semaphore maintains a set of permits
	* public Semaphore(int permits,boolean fair)
	* private Semaphore semaphore = new Semaphore(5, true);
	try {
		System.out.println("Before Lock");
		semaphore.acquire();
		doWork();
	} catch (InterruptedException e) {
		e.printStackTrace();
	} finally {
		semaphore.release();
	}

# WaitNotify
	public void produce() throws InterruptedException {
		synchronized (this) {
			wait(30000);
		}
	}
	public void consume() throws InterruptedException {
		synchronized (this) {
			//notify();
			notifyAll();
		}
	}
	
	
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