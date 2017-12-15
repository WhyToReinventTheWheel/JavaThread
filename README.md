# Synchronized collections vs Concurrent collections
	* http://javarevisited.blogspot.com/2016/05/what-is-difference-between-synchronized.html
	* http://javarevisited.blogspot.sg/2011/04/difference-between-concurrenthashmap.html
	* http://javarevisited.blogspot.sg/2013/02/concurrenthashmap-in-java-example-tutorial-working.html
	* ConcurrentModificationException 
	 Collections.synchronizedMap()
	Synchronized collections
		* low performance, scalability
		* ConcurrentModificationException
		* Synchronized collections like synchronized HashMap, Hashtable, HashSet, Vector, 
			and synchronized ArrayList are much slower than their concurrent counterparts
	Concurrent collections
		* ConcurrentHashMap, CopyOnWriteArrayList, and CopyOnWriteHashSet
		* Unlike Hashtable and Synchronized Map, it never locks whole Map, 
			instead, it divides the map into segments and locking is done on those.

# ForkJoin 
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(100);
		System.out.println(forkJoinPool.invoke(simpleRecursiveAction));
	}

	private static class SimpleRecursiveAction extends RecursiveTask<Integer> {
		private int simulatedWork;

		public SimpleRecursiveAction(int simulatedWork) {
			this.simulatedWork = simulatedWork;
		}

		@Override
		protected Integer compute() {
			if (simulatedWork > 10) {
				System.out.println("Parallel execution and split the tasks..." + simulatedWork);
				int index = simulatedWork / 2;
				SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(index);
				SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(index + (simulatedWork % 2));

				simpleRecursiveAction1.fork();
				simpleRecursiveAction2.fork();

				int solution = 0;
				solution = solution + simpleRecursiveAction1.join();
				solution = solution + simpleRecursiveAction2.join();
				return solution;
			} else {
				System.out.println("No need for parallel execution, sequential is OK for this task...");
				return 2 * simulatedWork;
			}
		}
	}

# Thread Pool
	ExecutorService executorService = Executors.newFixedThreadPool(5);
	ExecutorService executorService = Executors.newCachedThreadPool();
	ExecutorService executorService = Executors.newSingleThreadExecutor();

	* executorService.execute(Runnable command)
	
	* <T> Future<T> submit(Callable<T> task)
	* Future<?> submit(Runnable task)

# Callable
	
	ExecutorService executorService = Executors.newFixedThreadPool(2);
	Future<String> future = executorService.submit(new Processor());
	System.out.println(future.get());  //This will hold execution unit task done 
	executorService.shutdown();
	
	private static class Processor implements Callable<String> {
		@Override
		public String call() throws Exception {
			return "HelloCallable";
		}
	}
	
# How to control thread related properties in Executors framework
	ExecutorService execService = Executors.newCachedThreadPool(new DaemonThreadsFactory());
	execService.execute(new Task("Task1"));
	execService.execute(new Task("Task2"));
	execService.shutdown();
	
	//We need to use thread factory here 
	private class DaemonThreadsFactory extends NamedThreadsFactory {
		private static int count = 0;
		public Thread newThread(Runnable r) {
			Thread t = super.newThread(r);
			t.setDaemon(true);
			return t;
		}
	}
	

	
	
# CompletionService 
	ExecutorService execService = Executors.newCachedThreadPool();
	CompletionService<TaskResult<Integer, Integer>> 
				tasks = new ExecutorCompletionService<>(execService);
	tasks.submit(new callableTaskEx(2));
	tasks.submit(new RunnableTaskEx(), new TaskResult<Integer, Integer>(100, 999));
	execService.shutdown();
	for (int i = 0; i < 2; i++) {
		try {
			Future<TaskResult<Integer, Integer>> result=tasks.take();
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
	
		}
	}
	
# DaemonThreads
	* Main Thread also user thread
	* Daemon Thread untill any user thread live
	* Automatically Kill when all user thread die 
	
	Thread t1 = new Thread(new Task(), "Thread-1");
	t1.setDaemon(true);
	t1.start();
	
# NamingThread
	String currentThreadName = Thread.currentThread().getName();
	new Thread(new Task(), "MyThread-1").start();
	Thread t2 = new Thread(new Task());
	t2.setName("MyThread-2");
	
# Join 
	Thread t1 = new Thread(new Task("MyThread-1",500));
	Thread t2 = new Thread(new Task("MyThread-2",1000));
	t1.start();
	t2.start();
	
	t1.join();
	System.out.println("t1 thread ends here...");
	t2.join();
	System.out.println("t2 thread ends here...");
	System.out.println("Main thread ends here...");
	
# Yield
	Whenever a thread calls java.lang.Thread.yield method, 
	it gives hint to the thread scheduler that it is ready to pause its execution. 
	Thread scheduler is free to ignore this hint.
	
	Thread.yield();

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
	
# ReadWriteLock  
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	// Read critical section
	readWriteLock.readLock().lock();
		// multiple readers can enter this section
		// if not locked for writing, and not writers waiting
		// to lock for writing.
	readWriteLock.readLock().unlock();

	// Write critical section
	readWriteLock.writeLock().lock();
		// only one writer can enter this section,
		// and only if no threads are currently reading.
	readWriteLock.writeLock().unlock();
	
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
	
	// Main Thread
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
	
# Collection 
	BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
	BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);  // Can we unbounded, use seprate lock for head and tail 
	
	* It uses the same ordering rules as the java.util.PriorityQueue class -> have 
	  to implement the COmparable interface The comparable interface will determine 
	  what will the order in the queue
	  The priority can be the same compare() == 0 case
		
	BlockingQueue<String> queue = new PriorityBlockingQueue<>(10);
	BlockingQueue<String> queue = new PriorityBlockingQueue<>();
	
	//Producer
	public void run() {
		try {
			while (true) {
				blockingQueue.put("Data:" + count);
			}
		} catch (InterruptedException e) {
		
		}
	}
	
	//Consumer
	public void run(){
		try {
			while (true) {
				System.out.println("Taken Data:-- "+blockingQueue.take());
			}
		} catch (InterruptedException e) {
		}
	}