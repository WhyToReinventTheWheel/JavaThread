# DeadLock










# Thread DumpExample
```
2018-07-01 19:56:03
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.171-b11 mixed mode):

"DestroyJavaVM" #18 prio=5 os_prio=0 tid=0x0000000002642800 nid=0x1c0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"Thread-3" #17 prio=5 os_prio=0 tid=0x00000000197f4800 nid=0x60c waiting for monitor entry [0x000000001a75f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.mk.DeadLock$ThreadDemo2.run(DeadLock.java:45)
	- waiting to lock <0x00000000d5c4c720> (a java.lang.Object)
	- locked <0x00000000d5c4c730> (a java.lang.Object)

   Locked ownable synchronizers:
	- None

"Thread-2" #16 prio=5 os_prio=0 tid=0x000000001980a000 nid=0x2bf0 waiting for monitor entry [0x000000001a65f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.mk.DeadLock$ThreadDemo1.run(DeadLock.java:27)
	- waiting to lock <0x00000000d5c4c730> (a java.lang.Object)
	- locked <0x00000000d5c4c720> (a java.lang.Object)

   Locked ownable synchronizers:
	- None

"JMX server connection timeout 15" #15 daemon prio=5 os_prio=0 tid=0x00000000196f7000 nid=0x2778 in Object.wait() [0x000000001a35f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000d65450f0> (a [I)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:168)
	- locked <0x00000000d65450f0> (a [I)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None

"RMI Scheduler(0)" #14 daemon prio=5 os_prio=0 tid=0x00000000196f0800 nid=0x794 waiting on condition [0x000000001a25f000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000000d60fcba0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None

"RMI TCP Connection(1)-192.168.1.160" #13 daemon prio=5 os_prio=0 tid=0x00000000196da800 nid=0x984 runnable [0x000000001a15e000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x00000000d64d02a8> (a java.io.BufferedInputStream)
	at java.io.FilterInputStream.read(FilterInputStream.java:83)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:555)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:835)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$3/1433798108.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- <0x00000000d6383a40> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"RMI TCP Accept-0" #12 daemon prio=5 os_prio=0 tid=0x00000000196cb800 nid=0x1dd8 runnable [0x0000000019f5e000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000000d62db280> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.management.jmxremote.LocalRMIServerSocketFactory$1.accept(LocalRMIServerSocketFactory.java:52)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None

"Service Thread" #10 daemon prio=9 os_prio=0 tid=0x00000000189c0000 nid=0x26ac runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"C1 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001731b000 nid=0xa90 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x0000000018963000 nid=0x23e0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x0000000018962800 nid=0x1ee0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x00000000172fa800 nid=0x2150 runnable [0x0000000018e1e000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
	at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
	at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
	- locked <0x00000000d5c90528> (a java.io.InputStreamReader)
	at java.io.InputStreamReader.read(InputStreamReader.java:184)
	at java.io.BufferedReader.fill(BufferedReader.java:161)
	at java.io.BufferedReader.readLine(BufferedReader.java:324)
	- locked <0x00000000d5c90528> (a java.io.InputStreamReader)
	at java.io.BufferedReader.readLine(BufferedReader.java:389)
	at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

   Locked ownable synchronizers:
	- None

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x00000000172cc800 nid=0x1bf8 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x00000000172cb800 nid=0x1054 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x00000000172b9000 nid=0x9b8 in Object.wait() [0x000000001862f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000d5b08ed0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
	- locked <0x00000000d5b08ed0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:212)

   Locked ownable synchronizers:
	- None

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000002734000 nid=0x2b98 in Object.wait() [0x000000001852e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000d5b06bf8> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000000d5b06bf8> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

   Locked ownable synchronizers:
	- None

"VM Thread" os_prio=2 tid=0x0000000017297000 nid=0x1518 runnable 

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000002658800 nid=0x1118 runnable 

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x000000000265a000 nid=0x24ac runnable 

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x000000000265b800 nid=0x2bf8 runnable 

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x000000000265d000 nid=0x20c4 runnable 

"VM Periodic Task Thread" os_prio=2 tid=0x0000000018a2d000 nid=0x754 waiting on condition 

JNI global references: 333


Found one Java-level deadlock:
=============================
"Thread-3":
  waiting to lock monitor 0x000000000273e5f8 (object 0x00000000d5c4c720, a java.lang.Object),
  which is held by "Thread-2"
"Thread-2":
  waiting to lock monitor 0x000000000273bc08 (object 0x00000000d5c4c730, a java.lang.Object),
  which is held by "Thread-3"

Java stack information for the threads listed above:
===================================================
"Thread-3":
	at com.mk.DeadLock$ThreadDemo2.run(DeadLock.java:45)
	- waiting to lock <0x00000000d5c4c720> (a java.lang.Object)
	- locked <0x00000000d5c4c730> (a java.lang.Object)
"Thread-2":
	at com.mk.DeadLock$ThreadDemo1.run(DeadLock.java:27)
	- waiting to lock <0x00000000d5c4c730> (a java.lang.Object)
	- locked <0x00000000d5c4c720> (a java.lang.Object)

Found 1 deadlock.
```
