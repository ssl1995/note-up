# Java并发面试题
<readonly-block type="isv"></readonly-block>
<callout emoji="📌"><p>Java并发学习指引：<cite doc-id="JxJnwoORPiLmw1kIU8AcutLJnHh" file-type="wiki" title="Java并发编程学习指引" type="doc"></cite></p></callout>
## 并发基础（重要）
### 为什么要使用并发编程
**分析：**
**参考回答：**
<callout emoji="📌">
提升多核CPU的利用率：一般来说一台主机上的会有多个CPU核心，我们可以创建多个线程，理论上讲操作系统可以将多个线程分配给不同的CPU去执行，每个CPU执行一个线程，这样就提高了CPU的使用效率，如果使用单线程就只能有一个CPU核心被使用。
比如当我们在网上购物时，为了提升响应速度，需要拆分，减库存，生成订单等等这些操作，就可以进行拆分利用多线程的技术完成。面对复杂业务模型，并行程序会比串行程序更适应业务需求，而并发编程更能吻合这种业务拆分。
简单来说就是：
- 充分利用多核CPU的计算能力；
- 方便进行业务拆分，提升应用性能
</callout>
### 并发编程有什么缺点
**参考回答：**
<callout emoji="📌">
并发编程的目的就是为了能提高程序的执行效率，提高程序运行速度，但是并发编程并不总是能提高程序运行速度的，而且并发编程可能会遇到很多问题，比如：内存泄漏、上下文切换、线程安全、死锁等问题。
</callout>
### 并发编程三个重要特性是什么？
<callout emoji="📌">
- 原子性：原子，即一个不可再被分割的颗粒。原子性指的是一个或多个操作要么全部执行成功要么全部执行失败。
- 可见性：一个线程对共享变量的修改,另一个线程能够立刻看到。（synchronized,volatile）
- 有序性：程序执行的顺序按照代码的先后顺序执行。（处理器可能会对指令进行重排序）
</callout>
### 在 Java 程序中怎么保证多线程的运行安全？
**参考回答：**
<callout comment-refs="c11" emoji="📌">
出现线程安全问题的原因一般都是三个原因：
- 线程切换带来的原子性问题 解决办法：使用多线程之间同步synchronized或使用锁(lock)。
- 缓存导致的可见性问题 解决办法：synchronized、volatile、LOCK，可以解决可见性问题
- 重排序优化带来的有序性问题 解决办法：Happens-Before 规则可以解决有序性问题
</callout>
推荐学习：
<cite doc-id="BydHwUqTCiWYsjks6vlc8w5JnPy" file-type="wiki" title="Java并发理论基础" type="doc"></cite>
[ java 程序中怎么保证多线程的运行安全？](https://blog.csdn.net/meism5/article/details/90266334)
### 并行和并发的区别
**分析**
试着用自己的话讲出来
**回答**
<callout emoji="📌">
并行：同一时间多个处理器同时处理多个任务
并发：单位时间，一个处理器处理多个任务，按时间片轮流处理多个任务
</callout>
**推荐阅读**
[并发与并行的区别是什么？](https://www.zhihu.com/question/33515481)
### 什么是进程，是什么线程
**分析**
多总结，多尝试用自己的话表达
**回答**
<callout emoji="📌">
进程是对运行时程序的封装，是系统进行资源分配的的基本单位，实现了操作系统的并发； 线程是进程的子任务，是CPU调度的基本单位，用于保证程序的并发性，实现进程内部的并发；线程是操作系统可识别的最小执行和调度单位。
</callout>
**推荐阅读**
[进程、线程基础知识](https://xiaolincoding.com/os/4_process/process_base.html)
### 什么是上下文切换?
**参考回答：**
<callout emoji="📌">
多线程编程中一般线程的个数都大于 CPU 核心的个数，而一个 CPU 核心在任意时刻只能被一个线程使用，为了让这些线程都能得到有效执行，CPU 采取的策略是为每个线程分配时间片并轮转的形式。当一个线程的时间片用完的时候就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。
概括来说就是：当前任务在执行完 CPU 时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换回这个任务时，可以再加载这个任务的状态。任务从保存到再加载的过程就是一次上下文切换。
上下文切换通常是计算密集型的。也就是说，它需要相当可观的处理器时间，在每秒几十上百次的切换中，每次切换都需要纳秒量级的时间。所以，上下文切换对系统来说意味着消耗大量的 CPU 时间，事实上，可能是操作系统中时间消耗最大的操作。
Linux 相比与其他操作系统（包括其他类 Unix 系统）有很多的优点，其中有一项就是，其上下文切换和模式切换的时间消耗非常少。
</callout>
**推荐学习：**
[一文让你明白上下文切换](https://zhuanlan.zhihu.com/p/52845869)
### 守护线程和用户线程有什么区别呢？
<callout emoji="📌">
- 用户 (User) 线程：运行在前台，执行具体的任务，如程序的主线程、连接网络的子线程等都是用户线程
- 守护 (Daemon) 线程：运行在后台，为其他前台线程服务。也可以说守护线程是 JVM 中非守护线程的 “佣人”。一旦所有用户线程都结束运行，守护线程会随 JVM 一起结束工作
</callout>
[JAVA用户线程&守护线程的区别](https://blog.csdn.net/dream_broken/article/details/8913563)
### 什么是线程死锁？死锁相关面试题
**参考回答：**
<callout emoji="📌">
**什么是死锁：**
- 死锁是指两个或两个以上的进程（线程）在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程（线程）称为死锁进程（线程）。
- 多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。
- 如下图所示，线程 A 持有资源 2，线程 B 持有资源 1，他们同时都想申请对方的资源，所以这两个线程就会互相等待而进入死锁状态。
**形成死锁的四个必要条件是什么？**
- 互斥条件：在一段时间内某资源只由一个进程占用。如果此时还有其它进程请求资源，就只能等待，直至占有资源的进程用毕释放。
- 占有且等待条件：指进程已经保持至少一个资源，但又提出了新的资源请求，而该资源已被其它进程占有，此时请求进程阻塞，但又对自己已获得的其它资源保持不放。
- 不可抢占条件：别人已经占有了某项资源，你不能因为自己也需要该资源，就去把别人的资源抢过来。
- 循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系。（比如一个进程集合，A在等B，B在等C，C在等A）
**如何避免线程死锁？**
我们只要破坏产生死锁的四个条件中的其中一个就可以了。
1. **破坏互斥条件：**这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
2. **破坏请求与保持条件：**一次性申请所有的资源。
3. **破坏不剥夺条件：**占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
4. **破坏循环等待条件：**靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。
</callout>
![关系图/图谱 @@@@ 图片展示了线程一和线程二与锁A、锁B之间的关系。线程一拥有锁A并试图获取锁B，线程二拥有锁B并试图获取锁A。此图可能用于解释多线程编程中死锁产生的原因，即两个或多个线程相互等待对方释放锁。](https://feishu.cn/file/LD8rbehbyoOaNWxhXq4cVPacn7b)
**推荐学习：**
[什么是线程死锁？形成条件是什么？如何避免？](https://cloud.tencent.com/developer/article/1635594)
### Java 线程有几种状态，分别是啥
**分析**
Java 线程枚举状态以官方为主
![图片展示了Java线程的6种状态，分别是NEW（未启动）、RUNNABLE（运行态）、BLOCKED（阻塞态）、WAITING（等待态）、TIMED_WAITING（超时等待态）和TERMINATED（终止态）。每个状态前有对应英文标识。文档分析了Java线程状态，图片是对状态定义的直观呈现，与上下文紧密相关，为理解Java线程状态提供了清晰的参考。](https://feishu.cn/file/LHHobMUvmo3REBxj8jYccsj8nnf)
**回答**
<callout emoji="📌">
在 java thread state 枚举类中定义了 6 种线程状态，他们分别是，新建状态 New，运行态 Runnable，阻塞态 Blocked，等待态 Waiting，延迟等待态 Timed Walting 和终止状态 Terminated
> Java 中没有把 Runnable 拆成 Runnable 和 Ready 两种状态
</callout>
**推荐阅读**
[Thread.State (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html)
### 线程状态如何流转
**分析**
![图片展示了Java线程状态变迁图。初始状态为New，通过Thread.start()进入Runnable状态。Runnable状态可因系统调度进入Running状态，也可因等待锁进入Blocked状态，还可因等待其他线程通知进入Waiting状态，或因超时等待进入Timed Waiting状态。Running状态可因系统调度回到Runnable状态，也可因执行完成进入Terminated状态。Blocked状态可因获取到锁回到Runnable状态，也可因等待进入synchronized方法或块进入Terminated状态。Waiting状态可因收到通知回到Runnable状态，也可因超时回到Runnable状态。](https://feishu.cn/file/KWlQbztCtonCwixomrwcv5Dnnae)
**回答**
<callout emoji="📌">
新建状态（New）通过 start 方法到达运行状态（Runnable）
运行状态（Runnable）等待锁进入阻塞状态（Blocked）获得锁回到运行状态（Runnable）
运行状态（Runnable）等待其他线程通知进入等待状态（Waiting）收到通知回到运行状态（Runnable）超时等待（Time Waiting）同理
结束进入终止状态（Terminated）
</callout>
**推荐阅读**
[说说线程的状态流转 - 掘金](https://juejin.cn/post/7077389851376484388)
### Java创建线程的方式
<callout emoji="📌">
**1、继承 Thread 类**
通过继承 Thread 类，并重写它的 run 方法，我们就可以创建一个线程。
- 首先定义一个类来继承 Thread 类，重写 run 方法。
- 然后创建这个子类对象，并调用 start 方法启动线程。
#### **2、实现 Runnable 接口**
通过实现 Runnable ，并实现 run 方法，也可以创建一个线程。
- 首先定义一个类实现 Runnable 接口，并实现 run 方法。
- 然后创建 Runnable 实现类对象，并把它作为 target 传入 Thread 的构造函数中
- 最后调用 start 方法启动线程。
#### **3、实现 Callable 接口，并结合 Future 实现**
- 首先定义一个 Callable 的实现类，并实现 call 方法。call 方法是带返回值的。
- 然后通过 FutureTask 的构造方法，把这个 Callable 实现类传进去。
- 把 FutureTask 作为 Thread 类的 target ，创建 Thread 线程对象。
- 通过 FutureTask 的 get 方法获取线程的执行结果。
</callout>
参考学习：
[java创建线程的三种方式及其对比](https://blog.csdn.net/longshengguoji/article/details/41126119)
### 说一下 runnable 和 callable 有什么区别
**参考回答：**
<callout emoji="📌">
**相同点：**
- 都是接口
- 都可以编写多线程程序
- 都采用Thread.start()启动线程
**主要区别：**
- Runnable 接口 run 方法无返回值；Callable 接口 call 方法有返回值，是个泛型，和Future、FutureTask配合可以用来获取异步执行的结果
- Runnable 接口 run 方法只能抛出运行时异常，且无法捕获处理；Callable 接口 call 方法允许抛出异常，可以获取异常信息 注：Callalbe接口支持返回执行结果，需要调用FutureTask.get()得到，此方法会阻塞主线程的继续往下执行，如果不调用不会阻塞。
</callout>
**推荐学习：**
[java中Runnable和Callable的区别](https://juejin.cn/post/6844904086832152590)
### 什么是 Callable 和 Future? 什么是 FutureTask?
**参考回答：**
<callout emoji="📌">
- Callable 接口类似于 Runnable，从名字就可以看出来了，但是 Runnable 不会返回结果，并且无法抛出返回结果的异常，而 Callable 功能更强大一些，被线程执行后，可以返回值，这个返回值可以被 Future 拿到，也就是说，Future 可以拿到异步执行任务的返回值。
- Future 接口表示异步任务，是一个可能还没有完成的异步任务的结果。所以说 Callable用于产生结果，Future 用于获取结果。
- FutureTask 表示一个异步运算的任务。FutureTask 里面可以传入一个 Callable 的具体实现类，可以对这个异步运算的任务的结果进行等待获取、判断是否已经完成、取消任务等操作。只有当运算完成的时候结果才能取回，如果运算尚未完成 get 方法将会阻塞。一个 FutureTask 对象可以对调用了 Callable 和 Runnable 的对象进行包装，由于 FutureTask 也是Runnable 接口的实现类，所以 FutureTask 也可以放入线程池中。
</callout>
**推荐学习：**
[Java多线程-Callable,Future, FutureTask](https://juejin.cn/post/6844903774985650183)
### sleep() 和 wait() 有什么区别？
**参考回答：**
<callout emoji="📌">
`两者都可以暂停线程的执行`
- 类的不同：sleep() 是 Thread线程类的静态方法，wait() 是 Object类的方法。
- 是否释放锁：sleep() 不释放锁；wait() 释放锁。
- 用途不同：Wait 通常被用于线程间交互/通信，sleep 通常被用于暂停执行。
- 用法不同：wait() 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或者 notifyAll() 方法。sleep() 方法执行完成后，线程会自动苏醒。或者可以使用wait(long timeout)超时后线程会自动苏醒。
</callout>
**推荐学习：**
[sleep() 和 wait() 有什么区别？](https://blog.csdn.net/catoop/article/details/8305872)
### 为什么线程通信的方法 wait(), notify()和 notifyAll()被定义在 Object 类里？
**参考回答：**
<callout emoji="📌">
wait、notify、notifyAll 被设计在 Object 类中的原因是，Java 提供的锁是对象级的而不是线程级的，每个对象都有个锁，而线程是可以获得这个对象的， 因此线程需要等待某些锁，那么只要调用对象中的wait()方法便可以了。
有的人会说，既然是线程放弃对象锁，那也可以把wait()定义在Thread类里面啊，新定义的线程继承于Thread类，也不需要重新定义wait()方法的实现。然而，这样做有一个非常大的问题，一个线程完全可以持有很多锁，你一个线程放弃锁的时候，到底要放弃哪个锁？当然了，这种设计并不是不能实现，只是管理起来更加复杂。
</callout>
### 为什么 wait(), notify()和 notifyAll()必须在同步方法或者同步块中被调用？
**参考回答：**
<callout comment-refs="c78" emoji="📌">
当一个线程需要调用对象的 wait()方法的时候，这个线程必须拥有该对象的锁，接着它就会释放这个对象锁并进入等待状态直到其他线程调用这个对象上的 notify()方法。同样的，当一个线程需要调用对象的 notify()方法时，也会先获取到对象的锁，然后执行 notify,  最后再释放这个对象的锁，以便其他在等待的线程就可以得到这个对象锁。由于所有的这些方法都需要线程持有对象的锁，这样就只能通过同步来实现，所以他们只能在同步方法或者同步块中被调用。
</callout>
### 线程的 sleep()方法和 yield()方法有什么区别？
**参考回答：**
<callout emoji="📌">
（1） sleep()方法给其他线程运行机会时不考虑线程的优先级，因此会给低优先级的线程以运行的机会；yield()方法只会给相同优先级或更高优先级的线程以运行的机会；
（2） 线程执行 sleep()方法后转入阻塞（超时等待状态），而执行 yield()方法后转入就绪（ready）状态；
（3）sleep()方法声明抛出 InterruptedException，而 yield()方法没有声明任何异常；
（4）sleep()方法比 yield()方法（跟操作系统 CPU 调度相关）具有更好的可移植性，通常不建议使用yield()方法来控制并发线程的执行。
</callout>
### 如何停止一个正在运行的线程？
**参考回答：**
<callout emoji="📌">
在java中有以下3种方法可以终止正在运行的线程：
1. 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
2. 使用stop方法强行终止，但是不推荐这个方法，因为stop和suspend及resume一样都是过期作废的方法。
3. 使用interrupt方法中断线程。
</callout>
### Java 中 interrupted 和 isInterrupted 方法的区别？
**参考回答：**
<callout emoji="📌">
interrupt：用于中断线程。调用该方法的线程的状态为将被置为”中断”状态。
 注意：线程中断仅仅是置线程的中断状态位，不会停止线程。需要用户自己去监视线程的状态为并做处理。支持线程中断的方法（也就是线程中断后会抛出interruptedException 的方法）就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。
 interrupted：是静态方法，查看当前中断信号是true还是false并且清除中断信号。如果一个线程被中断了，第一次调用 interrupted 则返回 true，第二次和后面的就返回 false 了。
isInterrupted：是可以返回当前中断信号是true还是false，与interrupted最大的差别是，isInterruputed不会清除中断信号，而interrupted会清除中断信号“。
</callout>
### 什么是阻塞式方法？
**参考回答：**
<callout emoji="📌">
阻塞式方法是指程序会一直等待该方法完成期间不做其他事情，ServerSocket 的accept()方法就是一直等待客户端连接。这里的阻塞是指调用结果返回之前，当前线程会被挂起，直到得到结果之后才会返回。此外，还有异步和非阻塞式方法在任务完成前就返回。
</callout>
### Java 中你怎样唤醒一个阻塞的线程？
**参考回答：**
<callout emoji="📌">
首先 ，wait()、notify() 方法是针对对象的，调用任意对象的 wait()方法都将导致线程阻塞，阻塞的同时也将释放该对象的锁，相应地，调用任意对象的 notify()方法则将随机解除该对象阻塞的线程，但它需要重新获取该对象的锁，直到获取成功才能往下执行；
其次，wait、notify 方法必须在 synchronized 块或方法中被调用，并且要保证同步块或方法的锁对象与调用 wait、notify 方法的对象是同一个，如此一来在调用 wait 之前当前线程就已经成功获取某对象的锁，执行 wait 阻塞后当前线程就将之前获取的对象锁释放。
</callout>
### notify() 和 notifyAll() 有什么区别？
**参考回答：**
<callout emoji="📌">
如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。
notifyAll() 会唤醒所有的线程，notify() 只会唤醒一个线程。
 notifyAll() 调用后，会将全部线程由等待池移到锁池，然后参与锁的竞争，竞争成功则继续执行，如果不成功则留在锁池等待锁被释放后再次参与竞争。而 notify()只会唤醒一个线程，具体唤醒哪一个线程由虚拟机控制。
</callout>
### Java 如何实现多线程之间的通讯和协作？
**参考回答：**
<callout emoji="📌">
可以通过中断和共享变量的方式实现线程间的通讯和协作
比如说最经典的生产者-消费者模型：当队列满时，生产者需要等待队列有空间才能继续往里面放入商品，而在等待的期间内，生产者必须释放对临界资源（即队列）的占用权。因为生产者如果不释放对临界资源的占用权，那么消费者就无法消费队列中的商品，就不会让队列有空间，那么生产者就会一直无限等待下去。因此，一般情况下，当队列满时，会让生产者交出对临界资源的占用权，并进入挂起状态。然后等待消费者消费了商品，然后消费者通知生产者队列有空间了。同样地，当队列空时，消费者也必须等待，等待生产者通知它队列中有商品了。这种互相通信的过程就是线程间的协作。
Java中线程通信协作的最常见方式：
1. syncrhoized加锁的线程的Object类的wait()/notify()/notifyAll()
2. ReentrantLock类加锁的线程的Condition类的await()/signal()/signalAll()
线程间直接的数据交换：
- 通过管道进行线程间通信：字节流、字符流
</callout>
### 同步方法和同步块，哪个是更好的选择？
**参考回答：**
<callout emoji="📌">
- 同步块是更好的选择，因为它不会锁住整个对象（当然你也可以让它锁住整个对象）。同步方法会锁住整个对象，哪怕这个类中有多个不相关联的同步块，这通常会导致他们停止执行并需要等待获得这个对象上的锁。
- 同步块更要符合开放调用的原则，只在需要锁住的代码块锁住相应的对象，这样从侧面来说也可以避免死锁。
`请知道一条原则：``同步的范围越小越好。`
</callout>
### 什么是线程同步和线程互斥，有哪几种实现方式？
**参考回答：**
<callout emoji="📌">
当一个线程对共享的数据进行操作时，应使之成为一个”原子操作“，即在没有完成相关操作之前，不允许其他线程打断它，否则，就会破坏数据的完整性，必然会得到错误的处理结果，这就是线程的同步。
在多线程应用中，考虑不同线程之间的数据同步和防止死锁。当两个或多个线程之间同时等待对方释放资源的时候就会形成线程之间的死锁。为了防止死锁的发生，需要通过同步来实现线程安全。
线程互斥是指对于共享的进程系统资源，在各单个线程访问时的排它性。当有若干个线程都要使用某一共享资源时，任何时刻最多只允许一个线程去使用，其它要使用该资源的线程必须等待，直到占用资源者释放该资源。线程互斥可以看成是一种特殊的线程同步。
线程间的同步方法大体可分为两类：用户模式和内核模式。顾名思义，内核模式就是指利用系统内核对象的单一性来进行同步，使用时需要切换内核态与用户态，而用户模式就是不需要切换到内核态，只在用户态完成操作。
用户模式下的方法有：原子操作（例如一个单一的全局变量），临界区。内核模式下的方法有：事件，信号量，互斥量。
实现线程同步的方法
- 同步代码方法：sychronized 关键字修饰的方法
- 同步代码块：sychronized 关键字修饰的代码块
- 使用特殊变量域volatile实现线程同步：volatile关键字为域变量的访问提供了一种免锁机制
- 使用重入锁实现线程同步：reentrantlock类是可重入、互斥、实现了lock接口的锁他与sychronized方法具有相同的基本行为和语义
</callout>
### 在监视器(Monitor)内部，是如何做线程同步的？程序应该做哪种级别的同步？
**参考回答：**
<callout emoji="📌">
在 java 虚拟机中，监视器和锁在Java虚拟机中是一块使用的。监视器监视一块同步代码块，确保一次只有一个线程执行同步代码块。每一个监视器都和一个对象引用相关联。线程在获取锁之前不允许执行同步代码。
一旦方法或者代码块被 synchronized 修饰，那么这个部分就放入了监视器的监视区域，确保一次只能有一个线程执行该部分的代码，线程在获取锁之前不允许执行该部分的代码
另外 java 还提供了显式监视器( Lock )和隐式监视器( synchronized )两种锁方案
</callout>
### 如果你提交任务时，核心线程数已达到配置的数量，这时会发生什么
**参考回答**
<callout emoji="📌">
有俩种可能：
- （1）如果使用的是无界队列 LinkedBlockingQueue，也就是无界队列的话，没关系，继续添加任务到阻塞队列中等待执行，因为 LinkedBlockingQueue 可以近乎认为是一个无穷大的队列，可以无限存放任务
- （2）如果使用的是有界队列比如 ArrayBlockingQueue，任务首先会被添加到ArrayBlockingQueue 中，ArrayBlockingQueue 满了，会根据maximumPoolSize 的值增加线程数量，如果增加了线程数量还是处理不过来，ArrayBlockingQueue 继续满，那么则会使用拒绝策略RejectedExecutionHandler 处理满了的任务，默认是 AbortPolicy
</callout>
### 在 Java 程序中怎么保证多线程的运行安全？
**参考回答**
<callout emoji="📌">
方法一：使用安全类，比如 java.util.concurrent 下的类，使用原子类AtomicInteger
方法二：使用自动锁 synchronized。
方法三：使用手动锁 Lock。手动锁 Java 示例代码如下：
</callout>
```Java
Lock lock = new ReentrantLock();
lock. lock();
try {
    System. out. println("获得锁");
} catch (Exception e) {
    // TODO: handle exception
} finally {
    System. out. println("释放锁");
    lock. unlock();
}
复制代码
```
### 你对线程优先级的理解是什么？
**参考回答**
<callout emoji="📌">
每一个线程都是有优先级的，一般来说，高优先级的线程在运行时会具有优先权，但这依赖于线程调度的实现，这个实现是和操作系统相关的(OS dependent)。我们可以定义线程的优先级，但是这并不能保证高优先级的线程会在低优先级的线程前执行。线程优先级是一个 int 变量(从 1-10)，1 代表最低优先级，10 代表最高优先级。
Java 的线程优先级调度会委托给操作系统去处理，所以与具体的操作系统优先级有关，如非特别需要，一般无需设置线程优先级。
当然，如果你真的想设置优先级可以通过setPriority()方法设置，但是设置了不一定会改变，这个是不准确的
</callout>
### 线程类的构造方法、静态块是被哪个线程调用的
**参考回答：**
<callout emoji="📌">
这是一个非常刁钻和狡猾的问题。请记住：线程类的构造方法、静态块是被 new这个线程类所在的线程所调用的，而 run 方法里面的代码才是被线程自身所调用的。
如果说上面的说法让你感到困惑，那么我举个例子，假设 Thread2 中 new 了Thread1，main 函数中 new 了 Thread2，那么：
- （1）Thread2 的构造方法、静态块是 main 线程调用的，Thread2 的 run()方法是Thread2 自己调用的
- （2）Thread1 的构造方法、静态块是 Thread2 调用的，Thread1 的 run()方法是Thread1 自己调用的
</callout>
### Java 中怎么获取一份线程 dump 文件？你如何在 Java 中获取线程堆栈？
**参考回答：**
<callout emoji="📌">
Dump文件是进程的内存镜像。可以把程序的执行状态通过调试器保存到dump文件中。
在 Linux 下，你可以通过命令 jstack -l  PID （Java 进程的进程 ID）来获取 Java应用的 dump 文件。
在 Windows 下，你可以按下 Ctrl + Break 来获取。这样 JVM 就会将线程的 dump 文件打印到标准输出或错误文件中，它可能打印在控制台或者日志文件中，具体位置依赖应用的配置。
</callout>
### 一个线程运行时发生异常会怎样？
**参考回答：**
<callout emoji="📌">
如果异常没有被捕获该线程将会停止执行。
Thread.UncaughtExceptionHandler是用于处理未捕获异常造成线程突然中断情况的一个内嵌接口。当一个未捕获异常将造成线程中断的时候，JVM 会使用 Thread.getUncaughtExceptionHandler()来查询线程的 UncaughtExceptionHandler 并将线程和异常作为参数传递给 handler 的 uncaughtException()方法进行处理。
</callout>
### Java 线程数过多会造成什么异常？
**参考回答：**
<callout emoji="📌">
- 线程的生命周期开销非常高
- 消耗过多的 CPU
- 资源如果可运行的线程数量多于可用处理器的数量，那么有线程将会被闲置。大量空闲的线程会占用许多内存，给垃圾回收器带来压力，而且大量的线程在竞争 CPU资源时还将产生其他性能的开销。
- 降低JVM稳定性
- 在可创建线程的数量上存在一个限制，这个限制值将随着平台的不同而不同，并且承受着多个因素制约，包括 JVM 的启动参数、Thread 构造函数中请求栈的大小，以及底层操作系统对线程的限制等。如果破坏了这些限制，那么可能抛出OutOfMemoryError 异常。
</callout>
### 多线程的常用方法
**参考回答：**
<sheet sheet-id="2HRvTL" token="A563sfs6Fh4ZdLtFd47cBuW1ned"></sheet>
### 介绍一下 ThreadLocal
**参考回答：**
<callout emoji="📌">
Thread类有两个变量：threadLocals和inheritableThreadLocals
这两个变量默认为null，只有当该线程调用了ThreadLocal类的get/set方法时才会创建他们，而调用ThreadLocal的get/set实际上是调用ThreadLocalMap的get/set
ThreadLocalMap可理解成给ThreadLocal定制化的HashMap
最终的变量放在了线程的ThreadLocalMap中，而不是ThreadLocal中，ThreadLocal只是对其进行封装，向其传递变量值
**用一个场景分析ThreadLocal的get/set流程：**
首先在所有线程外部创建一个共享的ThreadLocal对象，记为TL1。在一个线程中调用TL1.get()时，首先获取到当前线程对象，记为t，然后判断t.threadLocals是否为null，如果为null，就在t中创建一个新的ThreadLocalMap对象赋值给t.threadLocals，并将<TL1, null>插入其中，最后get方法返回null；如果不为null，则尝试获取threadLocals中TL1所在的键值对，如果该键值对为null，则向threadLocals中通过set方法插入<TL1, null>，最后返回null，如果键值对不为null，则返回键值对中的值。
调用set方法时，流程和get基本一致，只是从读变成了写。
这样就可以实现不同线程访问同一个ThreadLocal（TL1）能拿到各自向其中存放的值
</callout>
![图片展示了ThreadLocal在多线程环境下的数据存储结构。每个线程（Thread1、Thread2等）都有一个ThreadLocalMap，其中包含多个ThreadLocal及其对应的value。图中以绿色突出显示了ThreadLocal1和ThreadLocal2，它们分别对应value1和value2。ThreadLocal1和ThreadLocal2在不同线程中存储不同的值，体现了ThreadLocal在不同线程间数据隔离的特点，与文档中介绍ThreadLocal实现不同线程访问同一ThreadLocal能拿到各自存放值的内容相呼应。](https://feishu.cn/file/Hls8b3sdCoSFAYxwzDEcKeP2nBb)
### ThreadLocal 内存泄露问题了解吗
**分析**
ThreadLocal 内存泄露也是个老生常谈的问题了，网上部分资料，包括好多面试官都把这个问题出现的主要原因归结为 ThreadLocalMaps 里 Entry Key（ThreadLocal 对象本身）使用了弱引用导致的，但是我们仔细看看引用结构，脑补一下内存泄露的场景就不难发现，真正导致内存泄露的主要原因，其实是 Thread 强引用 ThreadLocalMaps，如果 Thread 一直存在，ThreadLocalMaps Entry 中的 value 这个强引用一直存在，不被回收才是导致发生内存泄露的真正原因
**回答**
<callout emoji="📌">
因为 ThreadLocal 本身不存储对象，是调用 Thread 中的 ThreadLocalMaps 来保存，而 Thread 强引用 ThreadLocalMaps 对象，如果 Thread 对象生命周期过长，不能及时被回收，就会导致 ThreadLocalMaps 对象里 Entry 的 value 存在内存泄露的可能
当然 ThreadLocalMaps 在设计的时候也考虑过这个问题，所以 ThreadLocalMaps Key 采用了弱引用的方式，并且在 set、remove、rehash 的时候会主动清理 ThreadLocalMaps 中 Key 为 Null 的 value，但是如果已经不在 Thread 中使用 ThreadLocal 了 set、remove、rehash 方法也不会被调用，与此同时，如果这个线程又一直存活、不终止的话，那么一ThreadLocalMaps 中的 Value 强引用就会一直存在，也就避免不了 Value 的内存泄漏
</callout>
**推荐阅读**
[内存泄漏——为何每次用完 ThreadLocal 都要调用 remove()](https://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Java%20%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%2078%20%E8%AE%B2-%E5%AE%8C/47%20%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E2%80%94%E2%80%94%E4%B8%BA%E4%BD%95%E6%AF%8F%E6%AC%A1%E7%94%A8%E5%AE%8C%20ThreadLocal%20%E9%83%BD%E8%A6%81%E8%B0%83%E7%94%A8%20remove()%EF%BC%9F.md)
### 为什么用ThreadLocal不用线程成员变量？
**参考回答：**
<callout emoji="📌">
如果用成员变量，那么成员变量必须在Thread里，不能在Runnable里，因为一个Runnable对象可以被多个Thread执行。
而如果在Thread中添加成员变量，就要加强Thread和Runnable的耦合，将Thread作为Runnable的成员变量，并在Runnable中调用具体的Thread变量，如果执行Runnable的Thread可能有很多子类，不同子类有不同的成员变量，则要在run方法中进行复杂处理，扩展性较低，不利于维护。而ThreadLocal就是将成员变量统一为一个Map放到线程里。
</callout>
## Java并发理论（Volatile/Synchronized/CAS）（重要）
### 线程之间如何通信及线程之间如何同步
**参考回答：**
<callout emoji="📌">
- 在并发编程中，我们需要处理两个关键问题：线程之间如何通信及线程之间如何同步。通信是指线程之间以如何来交换信息。一般线程之间的通信机制有两种：共享内存和消息传递。同步是指程序中用于控制不同线程间操作发生相对顺序的机制。
- Java的并发采用的是共享内存模型，Java线程之间的通信总是隐式进行，整个通信过程对程序员完全透明。如果编写多线程程序的Java程序员不理解隐式进行的线程之间通信的工作机制，很可能会遇到各种奇怪的内存可见性问题。
- 在共享内存并发模型里，同步是显式进行的。程序员必须显式指定某个方法或者某段代码需要在线程之间互斥锁执行。
</callout>
### （补充）Java内存模型（JMM）
**参考回答：**
> - 共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。
> - 从下图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：
>
>   1. 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。
>   2. 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。
>
> ![流程图 @@@@ 图片展示了多线程环境下共享变量的存储模型。线程A和线程B分别有本地内存A和B，其中存有共享变量的副本，它们与主内存通过JMM控制进行数据交互，主内存中存有共享变量。](https://feishu.cn/file/AqHlbrZIVodD4hxIuuScwrEsncb)
>
>
>
> **下面通过示意图来说明线程之间的通信**
>
> ![流程图 @@@@ 图片展示了线程A和线程B之间的通信流程。线程A和线程B分别有本地内存，其中x初始值都为1。线程A向线程B发送消息，二者通过主内存进行交互，图中标注了步骤1和步骤2表示数据流向主内存的过程。](https://feishu.cn/file/OwCxberz3oMAGExTfVectkm7nLb)
> 总结：什么是Java内存模型：java内存模型简称jmm，定义了一个线程对另一个线程可见。共享变量存放在主内存中，每个线程都有自己的本地内存，当多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程安全问题。
**推荐学习：**
[Java内存模型图](https://blog.csdn.net/Your_heart_private/article/details/105566207)
[JVM 基础 - Java 内存模型](https://pdai.tech/md/java/jvm/java-jvm-x-introduce.html)
[Java内存模型（JMM）总结](https://zhuanlan.zhihu.com/p/29881777)
### Happens-Before 原则
**分析**
理解 JMM 模型如何保证并发安全性，知道指令重排，理解 happens-before 原则
**回答**
<callout emoji="📌">
happens-before 字面意思就是先行发生，你可以理解为 A happens before B，就是 A 发生在 B 之前
happens-before（HB） 是在 JMM 中的一个很重要的规则，即一个操作的结果对于另一个操作是可见的，用来指定两个操作之间的执行顺序
</callout>
**推荐阅读**
[阿里面试题:Java 并发编程之 happens-before 规则](https://blog.51cto.com/u_11812862/3002287)
### Java 怎么进行并发控制？
**参考回答：**
> 并发控制中的锁一般有两种，悲观锁和乐观锁，一般来说悲观锁是基于Monitor实现的，这在Java中分别对应synchronized关键字和AQS。乐观锁是基于CAS+自旋来实现的，Java中用的比较多的是并发原子类。
>
> **synchronized**
>
> synchronized修饰的方法或代码块同一时间只能被一个线程执行。
>
> 一般有三种使用方法：
>
> 1. 修饰实例方法：调用某对象的该方法前获取该对象实例的锁
> 2. 修饰静态方法：调用某对象的该方法前获取该类的锁。
> 3. 两个线程分别执行同一个对象synchronized修饰的实例方法和静态方法时不会发生互斥，因为锁的资源不同，一个锁了对象实例，一个锁了类。
> 4. 锁对象，修饰代码块：synchronized(对象的引用)锁的是对象实例，synchronized(类.class)锁的是类
>
> 尽量不要使用 synchronized(String a) 因为 JVM 中，字符串常量池具有缓存功能！
>
> synchronized不能修饰构造方法，也没必要修饰，构造方法本身就是线程安全的。
>
> 底层原理：尝试获取对象的monitor，monitor已被其他线程占用时，获取失败，该线程进入EntrySet。占有monitor时调用wait()进入WaitSet。调用notify()时从WaitSet里随机选一个线程唤醒，调用notifyAll时唤醒WaitSet里所有线程。
>
> **AQS**
>
> AQS全称是AbstractQueuedSynchronizer，它是Java中用来构建锁和同步器的基础框架，可以用于实现诸如ReentrantLock、Semaphore、CountDownLatch等多种同步工具。
>
> ![图片展示了A addCriterion图片展示了AQS（AbstractQueuedSynchronizer）中CLH队列（VOL addCriterion图片展示了AQS（AbstractQueuedSynchronizer）中CLH队列（FIFO）的结构。图中有一个资源（V addCriterion图片展示了AQS（AbstractQueuedSynchronizer）中CLH队列（FIFO）的结构。图中有一个资源（state），其上方有两条虚线分别指向head和tail。head指向占用线程1，tail指向等待线程2，等待线程2再指向等待线程3，依此类推，直到等待线程n。该图与上下文介绍的AQS主要依赖双向链表和volatile](https://feishu.cn/file/Tuanb4aoaofZu8xYwQTcz6oenFb)
>
> AQS主要依赖于一个双向链表和一个volatile类型的整数state来实现同步控制。该整数state用来表示同步状态，一般情况下，state=0表示没有线程占用同步资源，state>0表示有线程占用同步资源，比如ReentrantLock可以允许一个线程多次获得锁，每次state值加一。
>
> AQS的主要方法有下面几个：
>
> - acquire()：该方法用来获取同步状态，如果同步状态被占用，则线程将被加入等待队列中。
> - acquireInterruptibly()：与acquire()类似，但是该方法允许中断操作。
> - tryAcquire()：该方法用来尝试获取同步状态，如果成功则返回true，否则返回false。
> - release()：该方法用来释放同步状态，并唤醒等待队列中的线程。
> - acquireShared()：该方法用来获取共享式同步状态，如果同步状态被占用，则线程将被加入等待队列中。
> - releaseShared()：该方法用来释放共享式同步状态，并唤醒等待队列中的线程。
>
> AQS实现同步的关键在于，它提供了一个基于FIFO队列的等待队列，通过将等待线程加入等待队列中，然后在释放同步状态的时候，从等待队列中唤醒等待线程，从而实现了同步机制。
>
> AQS的实现主要有两种方式：独占式（Exclusive）和共享式（Shared）。独占式是指只有一个线程可以占用同步资源，比如ReentrantLock，而共享式是指多个线程可以同时占用同步资源，比如CountDownLatch。在AQS中，这两种方式的实现是基本相同的，区别在于获取和释放同步状态的方式不同。
>
> 以上是AQS的基本实现方式，它是Java中构建锁和同步器的核心框架，为各种同步工具的实现提供了强大的基础支持。
### synchronized 关键字
**分析**
回答 synchronized 作用，特性，实现方式，可以适当埋点，让面试官追问
**回答**
<callout emoji="📌">
synchronized 关键字是 Java 用来解决多个线程之间共享资源竞争问题，synchronized 同步语句块实现的是 monitorrenter 和 monitorexit 执行，其中，monitorrenter 指令指向同步代码块的开始位置，monitorexit 指令则指向同步代码块的结束位置，synchronized 关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行
早期的 synchronized 依赖操作系统实现，属于重量级所，JDK 1.6 以后对其进行了大量的优化
</callout>
**推荐阅读**
[关键字: synchronized详解](https://pdai.tech/md/java/thread/java-thread-x-key-synchronized.html)
### 说说自己是怎么使用 synchronized 关键字，在项目中用到了吗
**参考回答：**
<callout emoji="📌">
**synchronized关键字最主要的三种使用方式：**
- 修饰实例方法: 作用于当前对象实例加锁，进入同步代码前要获得当前对象实例的锁
- 修饰静态方法: 也就是给当前类加锁，会作用于类的所有对象实例，因为静态成员不属于任何一个实例对象，是类成员（ static 表明这是该类的一个静态资源，不管new了多少个对象，只有一份）。所以如果一个线程A调用一个实例对象的非静态 synchronized 方法，而线程B需要调用这个实例对象所属类的静态 synchronized 方法，是允许的，不会发生互斥现象，因为访问静态 synchronized 方法占用的锁是当前类的锁，而访问非静态 synchronized 方法占用的锁是当前实例对象锁。
- 修饰代码块: 指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
总结： synchronized 关键字加到 static 静态方法和 synchronized(class)代码块上都是给 Class 类上锁。synchronized 关键字加到实例方法上是给对象实例上锁。尽量不要使用 synchronized(String a) 因为JVM中，字符串常量池具有缓存功能！
</callout>
### 说一下 synchronized 底层实现原理？
**参考回答：**
<callout emoji="📌">
Synchronized的语义底层是通过一个monitor（监视器锁）的对象来完成，每个对象有一个监视器锁(monitor)。每个Synchronized修饰过的代码当它的monitor被占用时就会处于锁定状态并且尝试获取monitor的所有权 ，过程：
1、如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。
2、如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.
3、如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。
`synchronized是可以通过 反汇编指令 javap命令，查看相应的字节码文件。`
</callout>
### synchronized可重入的原理
**参考回答：**
<callout emoji="📌">
重入锁是指一个线程获取到该锁之后，该线程可以继续获得该锁。底层原理维护一个计数器，当线程获取该锁时，计数器加一，再次获得该锁时继续加一，释放锁时，计数器减一，当计数器值为0时，表明该锁未被任何线程所持有，其它线程可以竞争获取锁。
</callout>
### 什么是自旋
**参考回答：**
<callout emoji="📌">
- 很多 synchronized 里面的代码只是一些很简单的代码，执行时间非常快，此时等待的线程都阻塞可能是一种不太值得的操作，因为线程阻塞涉及到用户态和内核态切换的问题。既然 synchronized 里面的代码执行得非常快，不妨让等待锁的线程不要被阻塞，而是在 synchronized 的边界做忙循环，这就是自旋。如果做了多次循环发现还没有获得锁，再阻塞，这样可能是一种更好的策略。
- 忙循环：就是程序员用循环让一个线程等待，不像传统方法wait(), sleep() 或 yield() 它们都放弃了CPU控制，而忙循环不会放弃CPU，它就是在运行一个空循环。这么做的目的是为了保留CPU缓存，在多核系统中，一个等待线程醒来的时候可能会在另一个内核运行，这样会重建缓存。为了避免重建缓存和减少等待重建的时间就可以使用它了。
</callout>
### 多线程中 synchronized 锁升级的原理是什么？
**参考回答：**
<callout emoji="📌">
synchronized 锁升级原理：在锁对象的对象头里面有一个 threadid 字段，在第一次访问的时候 threadid 为空，jvm 让其持有偏向锁，并将 threadid 设置为其线程 id，再次进入的时候会先判断 threadid 是否与其线程 id 一致，如果一致则可以直接使用此对象，如果不一致，则升级偏向锁为轻量级锁，通过自旋循环一定次数来获取锁，执行一定次数之后，如果还没有正常获取到要使用的对象，此时就会把锁从轻量级升级为重量级锁，此过程就构成了 synchronized 锁的升级。
`锁的升级的目的：锁升级是为了``减低了锁带来的性能消耗``。在 Java 6 之后优化 synchronized 的实现方式，使用了偏向锁升级为轻量级锁再升级到重量级锁的方式，从而减低了锁带来的性能消耗。`
- 偏向锁，顾名思义，它会偏向于第一个访问锁的线程，如果在运行过程中，同步锁只有一个线程访问，不存在多线程争用的情况，则线程是不需要触发同步的，减少加锁／解锁的一些CAS操作（比如等待队列的一些CAS操作），这种情况下，就会给线程加一个偏向锁。 如果在运行过程中，遇到了其他线程抢占锁，JVM会消除它身上的偏向锁，将锁恢复到标准的轻量级锁。
- 轻量级锁是由偏向所升级来的，偏向锁运行在一个线程进入同步块的情况下，当第二个线程加入锁争用的时候，偏向锁就会升级为轻量级锁；
- 重量级锁是synchronized ，是 Java 虚拟机中最为基础的锁实现。在这种状态下，Java 虚拟机会阻塞加锁失败的线程，并且在目标锁被释放的时候，唤醒这些线程。
</callout>
**推荐阅读**
[synchronized底层如何实现?什么是锁的升级、降级](https://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Java%20%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF%E9%9D%A2%E8%AF%95%E7%B2%BE%E8%AE%B2/16%20%20synchronized%E5%BA%95%E5%B1%82%E5%A6%82%E4%BD%95%E5%AE%9E%E7%8E%B0%EF%BC%9F%E4%BB%80%E4%B9%88%E6%98%AF%E9%94%81%E7%9A%84%E5%8D%87%E7%BA%A7%E3%80%81%E9%99%8D%E7%BA%A7%EF%BC%9F-%E6%9E%81%E5%AE%A2%E6%97%B6%E9%97%B4.md)
### 线程 B 怎么知道线程 A 修改了变量
**参考回答：**
<callout emoji="📌">
（1）volatile 修饰变量
（2）synchronized 修饰修改变量的方法
（3）wait/notify
</callout>
### 当一个线程进入一个对象的 synchronized 方法 A 之后，其它线程是否可进入此对象的 synchronized 方法 B？
**参考回答：**
<callout emoji="📌">
不能。其它线程只能访问该对象的非同步方法，同步方法则不能进入。因为非静态方法上的 synchronized 修饰符要求执行方法时要获得对象的锁，如果已经进入A 方法说明对象锁已经被取走，那么试图进入 B 方法的线程就只能在等锁池（注意不是等待池哦）中等待对象的锁。
</callout>
### synchronized、volatile、CAS 比较
**参考回答：**
<callout emoji="📌">
（1）synchronized 是悲观锁，属于抢占式，会引起其他线程阻塞。
（2）volatile 提供多线程共享变量可见性和禁止指令重排序优化。
（3）CAS 是基于冲突检测的乐观锁（非阻塞）
</callout>
**推荐学习**
[并发编程中的synchronized、volatile和CAS操作详解](https://refblogs.com/article/582)
### synchronized 和 Lock 有什么区别？
**参考回答：**
<callout emoji="📌">
- 首先synchronized是Java内置关键字，在JVM层面，Lock是个接口；
- 原理区别： synchronized 是内置锁，由 JVM 实现获取锁和释放锁的原理，还分为偏向锁、轻量级锁、重量级锁。Lock 根据实现不同，有不同的原理，例如 ReentrantLock 内部是通过 AQS 来获取和释放锁的。
- 用法区别：synchronized 可以给类、方法、代码块加锁；而 lock 只能给代码块加锁。synchronized 不需要手动获取锁和释放锁，使用简单，发生异常会自动释放锁，不容易造成死锁；而 lock 需要自己加锁和释放锁，如果使用不当没有 unLock()去释放锁就会造成死锁。
- synchronized 锁只能同时被一个线程拥有，但是 Lock 锁没有这个限制，例如在读写锁中的读锁，是可以同时被多个线程持有的，可是 synchronized 做不到。
- synchronized 锁不够灵活，一旦 synchronized 锁已经被某个线程获得了，此时其他线程如果还想获得，那它只能被阻塞，直到持有锁的线程运行完毕或者发生异常从而释放这个锁。如果持有锁的线程持有很长时间才释放，那么整个程序的运行效率就会降低，而且如果持有锁的线程永远不释放锁，那么尝试获取锁的线程只能永远等下去。相比之下，Lock 类在等锁的过程中，如果使用的是 lockInterruptibly 方法，那么如果觉得等待的时间太长了不想再继续等待，可以中断退出，也可以用 tryLock() 等方法尝试获取锁，如果获取不到锁也可以做别的事，更加灵活。
- 性能区别：在 Java 5 以及之前，synchronized 的性能比较低，但是到了 Java 6 以后，发生了变化，因为 JDK 对 synchronized 进行了很多优化，比如自适应自旋、锁消除、锁粗化、轻量级锁、偏向锁等，所以后期的 Java 版本里的 synchronized 的性能并不比 Lock 差。
</callout>
**推荐学习：**[22 synchronized 和 Lock 孰优孰劣，如何选择?](https://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Java%20%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%2078%20%E8%AE%B2-%E5%AE%8C/22%20synchronized%20%E5%92%8C%20Lock%20%E5%AD%B0%E4%BC%98%E5%AD%B0%E5%8A%A3%EF%BC%8C%E5%A6%82%E4%BD%95%E9%80%89%E6%8B%A9%EF%BC%9F.md)
### synchronized 和 Lock 如何选择？
**参考回答：**
<callout emoji="📌">
1. 如果能不用最好既不使用 Lock 也不使用 synchronized。因为在许多情况下你可以使用 java.util.concurrent 包中的机制，它会为你处理所有的加锁和解锁操作，也就是推荐优先使用工具类来加解锁。
2. 如果 synchronized 关键字适合你的程序， 那么请尽量使用它，这样可以减少编写代码的数量，减少出错的概率。因为一旦忘记在 finally 里 unlock，代码可能会出很大的问题，而使用 synchronized 更安全。
3. 如果特别需要 Lock 的特殊功能，比如尝试获取锁、可中断、超时功能等，才使用 Lock。
</callout>
### synchronized 和 ReentrantLock 区别是什么？
**参考回答：**
<callout emoji="📌">
synchronized 和 ReentrantLock 都是 Java 中提供的可重入锁，二者的主要区别有以下 5 个：
- 用法不同：synchronized 可以用来修饰普通方法、静态方法和代码块，而 ReentrantLock 只能用于代码块。
- 获取锁和释放锁的机制不同：synchronized 是自动加锁和释放锁的，而 ReentrantLock 需要手动加锁和释放锁。
- 锁类型不同：synchronized 是非公平锁，而 ReentrantLock 默认为非公平锁，也可以手动指定为公平锁。
- 响应中断不同：ReentrantLock 可以响应中断，解决死锁的问题，而 synchronized 不能响应中断。
- 底层实现不同：synchronized 是 JVM 层面通过监视器实现的，而 ReentrantLock 是基于 AQS 实现的。
</callout>
**推荐阅读**：[面试突击:synchronized和ReentrantLock有什么区别?](https://www.51cto.com/article/707239.html)
### volatile 关键字的作用
**参考回答：**
<callout emoji="📌">
- 对于可见性，Java 提供了 volatile 关键字来保证可见性和禁止指令重排。 volatile 确保一个线程的修改能对其他线程是可见的。当一个共享变量被 volatile 修饰时，它会保证修改的值会立即被更新到主内存中，当有其他线程需要读取时，它会去内存中读取新值。
- 从实践角度而言，volatile 的一个重要作用就是和 CAS 结合，保证了原子性，详细的可以参见 java.util.concurrent.atomic 包下的类，比如 AtomicInteger。
</callout>
### Java 中能创建 volatile 数组吗？
**参考回答：**
<callout emoji="📌">
能，Java 中可以创建 volatile 类型数组，不过只是一个指向数组的引用，而不是整个数组。意思是，如果改变引用指向的数组，将会受到 volatile 的保护，但是如果多个线程同时改变数组的元素，volatile 标示符就不能起到之前的保护作用了。
</callout>
### volatile 变量和 atomic 变量有什么不同？
**参考回答：**
<callout emoji="📌">
- volatile 变量可以确保可见性但并不能保证原子性。例如用 volatile 修饰 count 变量，那么 count++ 操作就不是原子性的。
- 而 AtomicInteger 类提供的 atomic 方法可以让这种操作具有原子性如getAndIncrement()方法会原子性的进行增量操作把当前值加一，其它数据类型和引用变量也可以进行相似操作。
</callout>
### volatile 能使得一个非原子操作变成原子操作吗？
**参考回答：**
<callout emoji="📌">
- 关键字volatile的主要作用是使变量在多个线程间可见，但无法保证原子性，对于多个线程访问同一个实例变量需要加锁进行同步。
- 虽然volatile只能保证可见性不能保证原子性，但用volatile修饰long和double可以保证其操作原子性。
</callout>
### synchronized 和 volatile 的区别是什么？
**参考回答：**
<callout emoji="📌">
- synchronized 表示只有一个线程可以获取作用对象的锁，执行代码，阻塞其他线程。
- volatile 表示变量在 CPU 的寄存器中是不确定的，必须从主存中读取。保证多线程环境下变量的可见性；禁止指令重排序。
**区别**
- volatile 是变量修饰符；synchronized 可以修饰方法，代码块。
- volatile 仅能实现变量的修改可见性，不能保证原子性；而 synchronized 则可以保证变量的修改可见性和原子性。
- volatile 不会造成线程的阻塞；synchronized 可能会造成线程的阻塞。
- volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用 synchronized 关键字的场景还是更多一些。
</callout>
### Lock 接口和synchronized 对比同步它有什么优势？
**参考回答：**
<callout emoji="📌">
Lock 接口比同步方法和同步块提供了更具扩展性的锁操作。他们允许更灵活的结构，可以具有完全不同的性质，并且可以支持多个相关类的条件对象。
它的优势有：
（1）可以使锁更公平
（2）可以使线程在等待锁的时候响应中断
（3）可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间
（4）可以在不同的范围，以不同的顺序获取和释放锁
整体上来说 Lock 是 synchronized 的扩展版，Lock 提供了无条件的、可轮询的(tryLock 方法)、定时的(tryLock 带参方法)、可中断的(lockInterruptibly)、可多条件队列的(newCondition 方法)锁操作。另外 Lock 的实现类基本都支持非公平锁(默认)和公平锁，synchronized 只支持非公平锁，当然，在大部分情况下，非公平锁是高效的选择。
</callout>
### 乐观锁和悲观锁的理解及如何实现，有哪些实现方式？
**参考回答：**
<callout emoji="📌">
- 悲观锁：总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁。传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。再比如 Java 里面的同步原语 synchronized 关键字的实现也是悲观锁。
- 乐观锁：顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量，像数据库提供的类似于 write_condition 机制，其实都是提供的乐观锁。在 Java中 java.util.concurrent.atomic 包下面的原子变量类就是使用了乐观锁的一种实现方式 CAS 实现的。
</callout>
### 什么是 CAS
**分析**
回答出 CAS 操作的原子性，系统硬件级别提供支持
**回答**
<callout emoji="📌">
CAS 即 CompareAndSwap，Java 中可以通过 CAS 操作来保证原子性，原子性就是不可被中断的一些列操作或者一个操作，简单来说就是一系列操作，要么全部完成，要么失败，不能被中断。
CAS主要包含三个参数（V，expect，update）, V 表示要更新的变量（内存值）、 expect 表示预期值（旧值）、 update 表示新值。算法流程是首先比较 V 和 expect 的值，如果相等，将 update 值赋值给V，如果不相等说明有其他线程对该变量做了更新。这个参数有的地方也会用（V，A，B）表示，其中A表示预期值，B表示新值。
当多个线程同时操作一个共享变量时，只有一个线程可以对变量进行成功更新，其他线程均会失败，但是失败并不会被挂起，进行再次尝试，也就是自旋。Java 中的自旋锁就是利用 CAS 来实现的
</callout>
**推荐阅读**
[什么是CAS](https://www.mianshi.online/multi-thread-cas.html)
[Java CAS 原理详解](https://www.cnblogs.com/huansky/p/15746624.html)
### CAS 的会产生什么问题？
**参考回答：**
<callout emoji="📌">
1、ABA 问题：
比如说一个线程 one 从内存位置 V 中取出 A，这时候另一个线程 two 也从内存中取出 A，并且 two 进行了一些操作变成了 B，然后 two 又将 V 位置的数据变成 A，这时候线程 one 进行 CAS 操作发现内存中仍然是 A，然后 one 操作成功。尽管线程 one 的 CAS 操作成功，但可能存在潜藏的问题。从 Java1.5 开始 JDK 的 atomic包里提供了一个类 AtomicStampedReference 来解决 ABA 问题。
2、循环时间长开销大：
对于资源竞争严重（线程冲突严重）的情况，CAS 自旋的概率会比较大，从而浪费更多的 CPU 资源，效率低于 synchronized。
3、只能保证一个共享变量的原子操作：
当对一个共享变量执行操作时，我们可以使用循环 CAS 的方式来保证原子操作，但是对多个共享变量操作时，循环 CAS 就无法保证操作的原子性，这个时候就可以用锁。
</callout>
### 什么是原子类
**参考回答：**
<callout emoji="📌">
- java.util.concurrent.atomic包：是原子类的小工具包，支持在单个变量上解除锁的线程安全编程 原子变量类相当于一种泛化的 volatile 变量，能够支持原子的和有条件的读-改-写操作。
- 比如：AtomicInteger 表示一个int类型的值，并提供了 get 和 set 方法，这些 Volatile 类型的int变量在读取和写入上有着相同的内存语义。它还提供了一个原子的 compareAndSet 方法（如果该方法成功执行，那么将实现与读取/写入一个 volatile 变量相同的内存效果），以及原子的添加、递增和递减等方法。AtomicInteger 表面上非常像一个扩展的 Counter 类，但在发生竞争的情况下能提供更高的可伸缩性，因为它直接利用了硬件对并发的支持。
`简单来说就是原子类来实现CAS无锁模式的算法`
</callout>
### 原子类的常用类
**参考回答：**
<callout emoji="📌">
- AtomicBoolean
- AtomicInteger
- AtomicLong
- AtomicReference
</callout>
### 说一下 Atomic的原理？
**参考回答：**
<callout emoji="📌">
- Atomic包中的类基本的特性就是在多线程环境下，当有多个线程同时对单个（包括基本类型及引用类型）变量进行操作时，具有排他性，即当多个线程同时对该变量的值进行更新时，仅有一个线程能成功，而未成功的线程可以向自旋锁一样，继续尝试，一直等到执行成功。
</callout>
### 死锁与活锁的区别，死锁与饥饿的区别？
**参考回答：**
<callout emoji="📌">
死锁：是指两个或两个以上的进程（或线程）在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力作用，它们都将无法推进下去。
活锁：任务或者执行者没有被阻塞，由于某些条件没有满足，导致一直重复尝试，失败，尝试，失败。
活锁和死锁的区别在于，处于活锁的实体是在不断的改变状态，这就是所谓的“活”， 而处于死锁的实体表现为等待；活锁有可能自行解开，死锁则不能。
饥饿：一个或者多个线程因为种种原因无法获得所需要的资源，导致一直无法执行的状态。
Java 中导致饥饿的原因：
1、高优先级线程吞噬所有的低优先级线程的 CPU 时间。
2、线程被永久堵塞在一个等待进入同步块的状态，因为其他线程总是能在它之前持续地对该同步块进行访问。
3、线程在等待一个本身也处于永久等待完成的对象(比如调用这个对象的 wait 方法)，因为其他线程总是被持续地获得唤醒。
</callout>
## 线程池（重要）
### 什么是线程池？为什么要用线程池？
**参考回答：**
<callout emoji="📌">
答：Java中的线程池是运用场景最多的并发框架，几乎所有需要异步或并发执行任务的程序都可以使用线程池。在开发过程中，合理地使用线程池能够带来许多好处。
- 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
- 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
- 提高线程的可管理性。线程是稀缺资源，如果无限制地创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一分配、调优和监控。但是，要做到合理利用
</callout>
### 核心参数有哪些？
**分析：**
![图片展示了ThreadPoolExecutor构造函数的代码。该函数接收7个参数，分别是核心线程数、线程池最大线程数、空闲线程存活时间、时间单位、阻塞队列、线程工厂和拒绝策略。核心线程数和最大线程数用于控制线程池的规模，空闲线程存活时间用于管理空闲线程，阻塞队列用于保存任务，线程工厂用于创建线程，拒绝策略用于处理超出最大线程数的任务。此构造函数是创建线程池的基础，与上文介绍的线程池构造函数参数内容相呼应。](https://feishu.cn/file/OAFYbpYqao7RUjxyfp8cikx7nOf)
**参考回答：**
<callout emoji="📌">
线程池的构造函数有7个参数：
- corePoolSize：核心线程数
- maximumPoolSize：线程池能创建线程的最大个数
- keepAliveTime：空闲线程存活时间
- unit：时间单位，为keepAliveTime指定时间单位
- workQueue：阻塞队列，用于保存任务的阻塞队列
- threadFactory：创建线程的工程类
- handler：饱和策略（拒绝策略）
</callout>
### 线程池的种类，区别和使用场景
**分析**
**参考回答：**
<callout emoji="📌">
1. newCachedThreadPool
newCachedThreadPool创建一个可缓存线程池，如果当前线程池的长度超过了处理的需要时，它可以灵活的回收空闲的线程，当需要增加时， 它可以灵活的添加新的线程，而不会对池的长度作任何限制。
使用场景：执行很多短期异步的小程序。
1. newFixedThreadPool
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。定长线程池的大小最好根据系统资源进行设置。
使用场景：执行长期的任务，性能好很多。
1. newScheduledThreadPool
创建一个固定长度的线程池，而且支持定时的以及周期性的任务执行。
使用场景：周期性执行任务的场景。
1. newSingleThreadExecutor
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它，他必须保证前一项任务执行完毕后才能执行后一项。保证所有任务按照指定顺序执行。
使用场景：适合多个任务顺序执行的场景，不需要考虑并发问题。
</callout>
**推荐学习**
[线程池的种类，区别和使用场景](https://www.cnblogs.com/sachen/p/7401959.html)
### 线程池的拒绝策略有哪些
**分析**
**参考回答：**
<callout emoji="📌">
- AbortPolicy, 默认
该策略是线程池的默认策略。使用该策略时，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
- DiscardPolicy
这个策略和AbortPolicy的silent版本，如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。
- DiscardOldestPolicy
这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。 因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
- CallerRunsPolicy
使用此策略，如果添加到线程池失败，那么调用线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干。
</callout>
### 在 Java 中 Executor 和 Executors 的区别？
**参考回答：**
<callout emoji="📌">
Executors 工具类的不同方法按照我们的需求创建了不同的线程池，来满足业务的需求。
Executor 接口对象能执行我们的线程任务。
ExecutorService 接口继承了 Executor 接口并进行了扩展，提供了更多的方法我们能获得任务执行的状态并且可以获取任务的返回值。
</callout>
### **线程池都有哪些状态？**
**分析：**
![图片展示了Java线程池状态转换图。从RUNNING状态出发，可通过shutDown()或shutDownNow()转换为SHUTDOWN或STOP状态；SHUTDOWN状态下，当阻塞队列为空且工作线程数为0时，可转换为TIDYING状态；TIDYING状态执行terminated()方法后，最终转换为TERMINATED状态。此图与文档中线程池状态分析内容相关，直观呈现了各状态间的转换关系。](https://feishu.cn/file/BIxibM37RoeHXlxBdjScqnbYn3e)
**参考回答：**
<callout emoji="📌">
- RUNNING：这是最正常的状态，接受新的任务，处理等待队列中的任务。
- SHUTDOWN：不接受新的任务提交，但是会继续处理等待队列中的任务。
- STOP：不接受新的任务提交，不再处理等待队列中的任务，中断正在执行任务的线程。
- TIDYING：所有的线程都销毁了，workerCount 为 0，线程池的状态在转换为 TIDYING 状态时，会执行钩子方法 terminated()。
- TERMINATED：terminated()方法结束后，线程池的状态就会变成这个。
</callout>
### 线程池中 submit() 和 execute() 方法有什么区别？
**参考回答：**
<callout emoji="📌">
- 相同点：
  - 相同点就是都可以开启线程执行池中的任务。
- 不同点：
  - 接收参数：execute()只能执行 Runnable 类型的任务。submit()可以执行 Runnable 和 Callable 类型的任务。
  - 返回值：submit()方法可以返回持有计算结果的 Future 对象，而execute()没有
  - 异常处理：submit()方便Exception处理
</callout>
### 分析线程池的实现原理和线程的调度过程
**分析**
**参考回答：**
<callout comment-refs="c367" emoji="📌">
提交一个任务到线程池中，线程池的处理流程如下：
1. 如果当前运行的线程数小于核心线程数，那么就会新建一个线程来执行任务。
2. 如果当前运行的线程数等于或大于核心线程数，并且任务队列没满，那么就把该任务放入到任务队列里等待执行。
3. 如果向任务队列满了，但是当前运行的线程数是小于最大线程数的，就新建一个线程来执行任务。
4. 如果当前运行的线程数已经等同于最大线程数了，新建线程将会使当前运行的线程超出最大线程数，那么当前任务会被拒绝，调用拒绝策略方法。
</callout>
### 线程池的最大线程数目根据什么确定
**分析**
**参考回答：**
<callout emoji="📌">
线程池用来执行CPU密集型任务时，设置线程数为cpu核心数+1，这样可以使得每个线程都在执行任务。用来执行IO密集型任务时，大部分线程都阻塞，所以设置线程数为2\*cpu核数。
</callout>
### 线程池如何调优
**分析**
**参考回答：**
<callout emoji="📌">
1. CPU 密集型任务配置尽可能小的线程，cpu核数+1。
2. IO 密集型任务则由于线程并不是一直在执行任务，则配置尽可能多的线程，如2\*cpu核数。
3. 混合型任务，如果可以拆分，则将其拆分成一个 CPU 密集型任务和一个 IO 密集型任务。只要这两个任务执行的时间相差不是太大，那么分解后并发执行的吞吐率要高于串行执行的吞吐率；如果这两个任务执行时间相差太大，则没必要进行分解。
4. 优先级不同的任务可以使用优先级队列 PriorityBlockingQueue 来处理，它可以让优先级高的任务先得到执行。
5. 执行时间不同的任务可以交给不同线程池来处理，或者也可以使用优先级队列，让执行时间短的任务先执行。
6. 依赖数据库连接池的任务，因为线程提交 SQL 后需要等待数据库返回结果，线程数应该设置得较大，这样才能更好的利用 CPU。
7. 建议使用有界队列，有界队列能增加系统的稳定性和预警能力。可以根据需要设大一点，比如几千。使用无界队列，线程池的队列就会越来越大，有可能会撑满内存，导致整个系统不可用。
</callout>
### 线程池如何实现动态修改？
**参考回答：**
<callout emoji="📌">
1. 首先线程池提供了部分setter方法可以设置线程池的参数；
   1. 修改核心线程数，最大线程数，空闲线程停留时间，拒绝策略等。
   2. 可以将线程池的配置参数放入配置中心，当需要调整的时候，去配置中心修改就行。
2. 什么时候修改呢？
   1. 这里需要监控报警策略，获取线程池状态指标，当指标判定为异常之后进行报警
   2. 分析指标异常原因，评估处理策略，最后通过上述线程池提供的接口进行动态修改。（可以将动态配置）
</callout>
**推荐学习：**
[Java线程池实现原理及其在美团业务中的实践](https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html)
### 使用无界队列的线程池会导致什么问题？
**参考回答：**
<callout emoji="📌">
例如newFixedThreadPool使用了无界的阻塞队列LinkedBlockingQueue，如果线程获取一个任务后，任务的执行时间比较长，会导致队列的任务越积越多，导致机器内存使用不停飙升，最终导致OOM。
</callout>
### 线程池的问题，如果线程池当前处于空闲的状态，核心线程数量是不会被销毁的，那这几个核心线程处于什么状态？为什么处于这个状态？
**分析**
这里面试官其实想问你线程复用的逻辑，以及对线程状态机的理解，知不知道都要有一个分析思考的过程，不要一上来就回答答案或者说不知道，瞎猜
**参考回答：**
<callout emoji="📌">
首先线程本身创建和销毁都是成本比较高的，那就排除 new 和 terminated 状态，没有任务运行排除 runnable 状态，剩下阻塞和等待，因为线程不会销毁需要一直等待执行任务，超时等待也不太可能，最后同步锁才会进入阻塞状态，所以我猜是一直等待
</callout>
**推荐阅读**
![图片展示了线程池中线程的运行状态。左侧列出多个TCP连接等线程，右侧突出显示“pool-1-thread-1”线程，其状态为“java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@5ce487df上的WAITING”。该图片与上文面试题相关，用于辅助说明线程池中处于空闲状态的核心线程可能处于等待状态，直观呈现了线程的运行状态，帮助理解面试题中线程复用逻辑及状态机理解。](https://feishu.cn/file/LwqvbPpuHoPjkOxAyC1cIgmHnve)
![图片展示的是Java线程池中getTask方法的部分代码。代码中，getTask方法首先判断上次poll()是否超时。接着循环检查线程状态，若处于SHUTDOWN、STOP状态或工作队列为空，则减少核心线程数并返回null。再判断是否允许核心线程超时，若最大线程数大于等于核心线程数且核心线程数大于1且工作队列为空，则减少核心线程数。最后，通过workQueue.poll()或workQueue.take()获取Runnable任务，若获取到的任务为null则循环继续。该代码与上文线程池核心线程状态分析相关，体现了线程复用逻辑。](https://feishu.cn/file/V5YKbIAoeo7Gkfxiw8ncyDxWn1d)
![图片展示的是Java线程池相关代码片段，其中关键部分是`private final Condition notEmpty = takeLock.newCondition();`。`notEmpty`是一个Condition对象，由`takeLock`对象调用`newCondition()`方法创建。`takeLock`可能是一个ReentrantLock对象，用于线程同步。该代码片段与上下文讨论的线程池问题相关，可能涉及线程池中线程状态的控制，如线程等待执行任务等场景。](https://feishu.cn/file/RErZbSHzWoIrQwxWTb7cyx8Rnqc)
![图片展示的是Java线程池中一个代码片段，其中“notEmpty.await();”部分被绿色箭头突出显示。该代码位于“while (count.get() == 0) {”循环内部，当线程池中任务数量为0时，线程会调用notEmpty对象的await()方法，进入阻塞状态。此代码与上文面试题中线程池处于空闲状态时核心线程状态分析相关，体现了线程处于等待执行任务状态的逻辑。](https://feishu.cn/file/CMnYbkSuNoMMjfxLfX2c1FkunDb)
## Java中的锁（重要）
### Lock 接口和synchronized同步 对比它有什么优势？
**参考回答：**
<callout emoji="📌">
Lock 接口比同步方法和同步块提供了更具扩展性的锁操作。他们允许更灵活的结构，可以具有完全不同的性质，并且可以支持多个相关类的条件对象。
它的优势有：
（1）可以使锁更公平
（2）可以使线程在等待锁的时候响应中断
（3）可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间
（4）可以在不同的范围，以不同的顺序获取和释放锁
整体上来说 Lock 是 synchronized 的扩展版，Lock 提供了无条件的、可轮询的(tryLock 方法)、定时的(tryLock 带参方法)、可中断的(lockInterruptibly)、可多条件队列的(newCondition 方法)锁操作。另外 Lock 的实现类基本都支持非公平锁(默认)和公平锁，synchronized 只支持非公平锁，当然，在大部分情况下，非公平锁是高效的选择。
</callout>
### 怎么理解Lock与AQS的关系？
**参考回答：**
<callout emoji="📌">
Lock是面向锁的使用者的，他定义了使用者与锁的交互接口，隐藏了实现细节。而AQS是面向锁的实现者的，它简化了锁的实现方式，屏蔽了同步状态的管理，线程的排队，等待与唤醒等底层操作。锁和同步器很好的隔离 了使用者和实现者所需关注的领域。
</callout>
### 什么是 AQS
**分析**
先谈作用和引用
**回答**
<callout emoji="📌">
AQS 是多线程同步器，它是 J.U.C 包中多个组件的底层实现，如 Lock、CountDownLatch、Semaphore 等都用到了 AQS
从本质上来说，AQS 提供了两种锁机制，分别是排它锁，和共享锁
排它锁，就是存在多线程竞争同一共享资源时，同一时刻只允许一个线程访问该共享资源，也就是多个线程中只能有一个线程获得锁资源，比如 Lock 中的 ReentrantLock 重入锁实现就是用到了 AQS 中的排它锁功能
共享锁也称为读锁，就是在同一时刻允许多个线程同时获得锁资源，比如CountDownLatch 和 Semaphore 都是用到了 AQS 中的共享锁功能
</callout>
**推荐阅读**
[JUC锁: 锁核心类AQS详解](https://pdai.tech/md/java/thread/java-thread-x-lock-AbstractQueuedSynchronizer.html)
[AQS 详解](https://javaguide.cn/java/concurrent/aqs.html)
### AQS 是怎么实现同步管理的? 底层数据结构？
**参考回答：**
<callout emoji="📌">
AQS主要依赖于一个双向链表和一个volatile类型的整数state来实现同步控制。该整数state用来表示同步状态，一般情况下，state=0表示没有线程占用同步资源，state>0表示有线程占用同步资源，state>1表示同步资源已经被争用了多次，比如ReentrantLock可以允许一个线程多次获得锁，每次state值加1。
AQS实现同步的关键在于，它提供了一个基于FIFO队列的等待队列，通过将等待线程加入等待队列中，然后在释放同步状态的时候，从等待队列中唤醒等待线程，从而实现了同步机制。
AQS的实现主要有两种方式：独占式（Exclusive）和共享式（Shared）。独占式是指只有一个线程可以占用同步资源，比如ReentrantLock，而共享式是指多个线程可以同时占用同步资源，比如CountDownLatch。在AQS中，这两种方式的实现是基本相同的，区别在于获取和释放同步状态的方式不同。
</callout>
### AQS有哪些核心的方法?
**参考回答：**
<callout emoji="📌">
一共三类方法
第一类：3个访问和修改同步状态的方法
第二类：5个可重写方法
第三类：9个模版方法
三类方法关系：实现一个同步组件时，使用者继承AbstractQueuedSynchronizer并重写5个指定的方法（第二类）。重写同步器指定的方法时，需要使用同步器提供的3个方法来访问或修改同步状态（第一类）。最后将AQS组合在自定义同步组件的实现中，并调用其9个模板方法（第三类）和 5个重写过的方法来实现，另外模板方法会调用使用者重写的方法。
</callout>
### ReentrantLock和Synchronized的对比?
**参考回答：**
<callout emoji="📌">
**相同点**：
- synchronized 和 ReentrantLock 都是用来保护资源线程安全的。
- 都可以保证可见性。
- synchronized 和 ReentrantLock 都拥有可重入的特点
**不同点**：
- 加解锁控制差别：synchronized的加锁和解锁是由Jvm实现的（内置锁），而ReentrantLock的加解锁需要手动控制，通过lock() 和 unlock(), 一般会把unlock操作放入finally块来解锁，以防忘记解锁。
- synchronized 锁不够灵活：synchronized一个线程获取锁之后，其他线程想要获取锁只能等待，只能进入阻塞状态，直到持有锁的线程释放这个锁，可能这个等待过程会持续很久。相比之下，ReentrantLock可以使用lockInterruptibly方法，不想等了可以中断退出，也可以使用tryLock获取锁，能获取就获取，不能获取线程也可以去干别的事情，更加灵活。
- 是否可以设置公平/非公平：公平锁是指多个线程在等待同一个锁时，根据先来后到的原则依次获得锁。ReentrantLock 等 Lock 实现类可以根据自己的需要来设置公平或非公平，synchronized 则不能设置。
- 其他：例如实现方式的不同等。
</callout>
### 什么是可重入，什么是可重入锁?
**参考回答：**
<callout emoji="📌">
“可重入锁”概念是：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。
</callout>
### 公平锁和非公平锁有什么区别？
**参考回答：**
<callout emoji="📌">
- **公平锁** : 锁被释放之后，先申请的线程先得到锁。性能较差一些，因为公平锁为了保证时间上的绝对顺序，上下文切换更频繁。
- **非公平锁**：锁被释放之后，后申请的线程可能会先获取到锁，是随机或者按照其他优先级排序的。性能更好，但可能会导致某些线程永远无法获取到锁。
</callout>
### 为什么非公平锁比公平锁性能更好？
**参考回答：**
<callout emoji="📌">
- **公平锁执行流程**：获取锁时，先将线程自己添加到同步队列的队尾并休眠，当某线程用完锁之后，会去唤醒同步队列中队首的线程尝试去获取锁，锁的使用顺序也就是队列中的先后顺序，在整个过程中，线程会从运行状态切换到休眠状态，再从休眠状态恢复成运行状态，但线程每次休眠和恢复都需要从用户态转换成内核态，而这个状态的转换是比较慢的，所以公平锁的执行速度会比较慢。
- **非公平锁执行流程**：当线程获取锁时，会先通过 CAS 尝试获取锁，如果获取成功就直接拥有锁，如果获取锁失败才会进入同步队列，等待下次尝试获取锁。这样做的好处是，获取锁不用遵循先到先得的规则，从而避免了一次线程休眠和恢复的操作，这样就加速了程序的执行效率。
</callout>
### ReentrantLock是如何实现公平锁的? 非公平锁的?
**参考回答：**
<callout emoji="📌">
ReentrantLock类内部总共存在Sync、NonfairSync、FairSync三个类，NonfairSync与FairSync类继承自Sync类，Sync类继承自AbstractQueuedSynchronizer抽象类。
非公平锁是ReentrantLock的默认实现。公平锁对比非公平锁的实现差异主要体现在tryAcquire方法（获取锁）这里。非公平锁（NonfairSync）的tryAcquire实现直接调用了父类Sync中的nonfairTryAcquire。
而公平锁tryAcquire的唯一不同的点为判断条件多了hasQueuedPredecessors()方法，即加入了同步队列中当前节点是否有前驱节点的判断，如果该 方法返回true，则表示有线程比当前线程更早地请求获取锁，因此需要等待前驱线程获取并释 放锁之后才能继续获取锁（这就叫公平）。
</callout>
### ReentrantReadWriteLock 是什么？
**参考回答：**
<callout emoji="📌">
答：`ReentrantReadWriteLock` 实现了 `ReadWriteLock` ，是一个可重入的读写锁，既可以保证多个线程同时读的效率，同时又可以保证有写入操作时的线程安全。
`ReentrantReadWriteLock` 其实是两把锁，一把是 `WriteLock` (写锁)，一把是 `ReadLock`（读锁） 。读锁是共享锁，写锁是独占锁。读锁可以被同时读，可以同时被多个线程持有，而写锁最多只能同时被一个线程持有。
</callout>
### 共享锁和独占锁有什么区别？
**参考回答：**
<callout emoji="📌">
- 共享锁：一把锁可以被多个线程同时获得。
- 独占锁：一把锁只能被一个线程获得。
</callout>
### 线程持有读锁还能获取写锁吗？
**参考回答：**
<callout comment-refs="c443" emoji="📌">
在线程持有读锁的情况下：**该线程**不能取得写锁(因为获取写锁的时候，如果发现当前的读锁被占用，就马上获取失败，不管读锁是不是被当前线程持有)。
在线程持有写锁的情况下：**该线程**可以继续获取读锁（获取读锁时如果发现写锁被占用，只有写锁没有被当前线程占用的情况才会获取失败）。
</callout>
### 什么是锁的升降级? RentrantReadWriteLock为什么不支持锁升级?
**参考回答：**
<callout emoji="📌">
**写锁可以降级为读锁，但是读锁却不能升级为写锁**。这是因为读锁升级为写锁会引起线程的争夺，毕竟写锁属于是独占锁，这样的话，会影响性能。另外，还可能会有死锁问题发生。举个例子：假设两个线程的读锁都想升级写锁，则需要对方都释放自己锁，而双方都不释放，就会产生死锁。
</callout>
### **ReentrantReadWriteLock底层读写状态如何设计的?**
**参考回答：**
<callout emoji="📌">
答：高16位为读锁，低16位为写锁。
</callout>
## 并发安全容器/并发工具类（重要）
### ConcurrentHashMap 和 Hashtable 的区别？
**参考回答：**
<callout emoji="📌">
ConcurrentHashMap 和 Hashtable 的区别主要体现在实现线程安全的方式上不同。
**底层数据结构：** JDK1.7 的 ConcurrentHashMap 底层采用 **分段的数组+链表** 实现，JDK1.8 采用的数据结构跟 HashMap1.8 的结构一样，数组+链表/红黑二叉树。Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 **数组+链表** 的形式，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
**实现线程安全的方式（重要）：**
在 JDK1.7 的时候，ConcurrentHashMap 对整个桶数组进行了分割分段(Segment，分段锁)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率。
到了 JDK1.8 的时候，ConcurrentHashMap 已经摒弃了 Segment 的概念，而是直接用 Node 数组+链表+红黑树的数据结构来实现，并发控制使用 synchronized 和 CAS 来操作。（JDK1.6 以后 synchronized 锁做了很多优化） 整个看起来就像是优化过且线程安全的 HashMap，虽然在 JDK1.8 中还能看到 Segment 的数据结构，但是已经简化了属性，只是为了兼容旧版本；
**Hashtable(同一把锁)** :使用 synchronized 来保证线程安全，效率非常低下。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞或轮询状态，如使用 put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。
</callout>
### **ConcurrentHashMap JDK1.7实现的原理是什么?**
**参考回答：**
<callout emoji="📌">
- 首先将数据分为一段一段（这个“段”就是 Segment）的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据时，其他段的数据也能被其他线程访问。
- **ConcurrentHashMap 是由 Segment 数组结构和 HashEntry 数组结构组成**。
- Segment 继承了 ReentrantLock,所以 Segment 是一种可重入锁，扮演锁的角色。HashEntry 用于存储键值对数据。
- 一个 ConcurrentHashMap 里包含一个 Segment 数组，Segment 的个数一旦**初始化就不能改变**。 Segment 数组的大小默认是 16，也就是说默认可以同时支持 16 个线程并发写。
- Segment 的结构和 HashMap 类似，是一种数组和链表结构，一个 Segment 包含一个 HashEntry 数组，每个 HashEntry 是一个链表结构的元素，每个 Segment 守护着一个 HashEntry 数组里的元素，当对 HashEntry 数组的数据进行修改时，必须首先获得对应的 Segment 的锁。也就是说，对同一 Segment 的并发写入会被阻塞，不同 Segment 的写入是可以并发执行的。
</callout>
### **ConcurrentHashMap JDK1.8实现的原理是什么?**
**参考回答：**
<callout emoji="📌">
JDK1.8 ConcurrentHashMap 取消了 Segment 分段锁，采用 Node + CAS + synchronized 来保证并发安全。数据结构跟 HashMap 1.8 的结构类似，数组+链表/红黑二叉树。Java 8 在链表长度超过一定阈值8（同时满足容量》=64）时将链表（寻址时间复杂度为 O(N)）转换为红黑树（寻址时间复杂度为 O(log(N))）。
Java 8 中，锁粒度更细，synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发，就不会影响其他 Node 的读写，效率大幅提升。
</callout>
### **ConcurrentHashMap JDK1.7的实现和1.8的实现有什么区别?**
**参考回答：**
<callout emoji="📌">
- **线程安全实现方式**：JDK 1.7 采用 `Segment` 分段锁来保证安全， `Segment` 是继承自 `ReentrantLock`。JDK1.8 放弃了 `Segment` 分段锁的设计，采用 `Node + ``CAS`` + synchronized` 保证线程安全，锁粒度更细，`synchronized` 只锁定当前链表或红黑二叉树的首节点。
- **Hash 碰撞解决方法** : JDK 1.7 采用拉链法，JDK1.8 采用拉链法结合红黑树（链表长度超过一定阈值时，将链表转换为红黑树）。
- **并发度**：JDK 1.7 最大并发度是 Segment 的个数，默认是 16。JDK 1.8 最大并发度是 Node 数组的大小，并发度更大。
</callout>
### **JDK1.8**中，ConCurrentHashmap什么情况下链表才会转换成红黑树进行存储？
**参考回答：**
<callout emoji="📌">
答：链表长度大于等于8，且数组长度大于等于64。并非一开始就创建红黑树结构，如果当前Node数组长度小于阈值`MIN_TREEIFY_CAPACITY`，默认为64，先通过扩大数组容量为原来的两倍以缓解单个链表元素过大的性能问题。
</callout>
### **JDK1.8**中，ConcurrentHashmap的put过程是怎样的？
**参考回答：**
<callout emoji="📌">
整体流程跟HashMap比较类似，大致是以下几步：
- 如果桶数组未初始化，则初始化；
- 如果待插入的元素所在的桶为空，则尝试把此元素直接插入到桶的第一个位置；
- 如果正在扩容，则当前线程一起加入到扩容的过程中；
- 如果待插入的元素所在的桶不为空且没在迁移元素，则锁住这个桶；
- 如果当前桶中元素以链表方式存储，则在链表中寻找该元素或者插入元素；
- 如果当前桶中元素以红黑树方式存储，则在红黑树中寻找该元素或者插入元素；
- 如果元素存在，则覆盖旧值；
- 如果元素不存在，整个Map的元素个数加1，并检查是否需要扩容；
</callout>
### ConcurrentHashMap的get方法是否要加锁，为什么？
**参考回答：**
<callout emoji="📌">
答：不需要。get方法不涉及对变量的修改，所以会导致并发下可能处问题的原因就是读共享变量的可见性问题。而ConcurrentHashMap中，对get方法中用到的共享变量都使用volatil关键字修饰，所以整个get方法不加锁也不会有问题。
</callout>
###  ConcurrentHashMap默认初始容量是多少？
**参考回答：**
<callout emoji="📌">
答：初始容量为16
</callout>
###  ConCurrentHashmap 的key，value是否可以为null？
**参考回答：**
<callout emoji="📌">
答：不行。如果key或者value为null会抛出空指针异常。（原因是因为没办法解决get返回值为null时的二义性问题，即没办法确定是存储的值本身为null，还是说值不存在）；
注意：HashMap 允许使用 null 作为值和键。（因为HashMap只能单线程下使用，所以hashmap可以用containsKey来二次判断，排除二义性问题）
</callout>
### 存储在ConcurrentHashmap中每个节点是什么样的，有哪些变量？
**参考回答：**
<callout emoji="📌">
答：它是实现`Map.Entry<K,V>`接口。里面存放了hash，key，value，以及next节点。它的value和next节点是用volatile进行修饰，可以保证多线程之间的可见性。
</callout>
### 什么是**BlockingQueue**?
**参考回答：**
<callout emoji="📌">
阻塞队列(BlockingQueue)是一个支持两个附加操作的队列。这两个附加的操作支持阻塞的插入和移除方法。
1. 支持阻塞的插入方法:意思是当队列满时，队列会阻塞插入元素的线程，直到队列不满。
2. 支持阻塞的移除方法:意思是在队列为空时，获取元素的线程会等待队列变为非空。
</callout>
### 你了解的阻塞队列有哪些?
**参考回答：**
<callout emoji="📌">
- **ArrayBlockingQueue**:一个由数组结构组成的有界阻塞队列。
- **LinkedBlockingQueue**:一个由链表结构组成的阻塞队列。此队列创建时可以不指定容量大小，默认是Integer.MAX_VALUE，也就是无界的。但也可以指定队列大小，从而成为有界的。
- **PriorityBlockingQueue**:一个支持优先级排序的无界阻塞队列。 默认情况下元素采取自然顺序 升序排列。也可以自定义类实现compareTo()方法来指定元素排序规则。
- **DelayQueue**:一个使用优先级队列实现的无界阻塞队列。 队列使用PriorityQueue来实现。队 列中的元素必须实现Delayed接口，在创建元素时可以指定多久才能从队列中获取当前元素。 只有在延迟期满时才能从队列中提取元素。（常用在缓存有效期，定时任务调度等场景）
- **SynchronousQueue**:一个不存储元素的阻塞队列。每一个put操作必须等待一个take操作， 否则不能继续添加元素。队列本身并不存储任何元素，非常适合传递性场景。
- **LinkedTransferQueue**: 一个由链表结构组成的单向无界阻塞队列。它设计了一种直接在生产者和消费者之间传输元素的机制，称为“transfer”。当生产者调用transfer(e)方法时，它会阻塞直到有一个消费者接收该元素。适用于需要高效地在生产者和消费者之间直接传输数据的场景，尤其是当生产者和消费者之间的速度大致匹配时
- **LinkedBlockingDeque**:一个由链表结构组成的双向阻塞队列。所谓双向队列指的是可以 从队列的两端插入和移出元素。相比其他的阻塞队列，LinkedBlockingDeque多了addFirst、 addLast、offer-First、offerLast、peekFirst和peekLast等方法。双向阻塞队列可以 运用在“工作窃取”模式中
</callout>
### ArrayBlockingQueue 和 LinkedBlockingQueue 有什么区别？
**参考回答：**
<callout emoji="📌">
`答：`ArrayBlockingQueue 和 LinkedBlockingQueue 是 Java 并发包中常用的两种阻塞队列实现，它们都是线程安全的。不过，不过它们之间也存在下面这些区别：
- 底层实现：ArrayBlockingQueue 基于数组实现，而 LinkedBlockingQueue 基于链表实现。
- 是否有界：ArrayBlockingQueue 是有界队列，必须在创建时指定容量大小。LinkedBlockingQueue 创建时可以不指定容量大小，默认是Integer.MAX_VALUE，也就是无界的。但也可以指定队列大小，从而成为有界的。
- 锁是否分离： ArrayBlockingQueue中的锁是没有分离的，即生产和消费用的是同一个锁；LinkedBlockingQueue中的锁是分离的，即生产用的是putLock，消费是takeLock，这样可以防止生产者和消费者线程之间的锁争夺。
- 内存占用：ArrayBlockingQueue 需要提前分配数组内存，而 LinkedBlockingQueue 则是动态分配链表节点内存。这意味着，ArrayBlockingQueue 在创建时就会占用一定的内存空间，且往往申请的内存比实际所用的内存更大，而LinkedBlockingQueue 则是根据元素的增加而逐渐占用内存空间。
</callout>
### 如果队列是空的，消费者会一直等待，当生产者添加元素时，消费者是如何知道当前队列有元素的呢?
**参考回答：**
<callout emoji="📌">
答：**使用通知模式实现**。所谓通知模式，当消费者从空的队列获取元素时会阻塞住消费者，此时如果生产者放了一个元素进入队列，则需要通知阻塞住消费者当前有元素可取。同理当生产者往满的队列里添加元素时会阻塞住生产者，当消费者消费了一个队列中的元素后，会通知生产者当前队列可用。通过查看JDK源码发现部分阻塞队列使用了Condition来实现。
</callout>
### CountDownLatch，CyclicBarrier，Semaphore，Exchanger了解吗？
**参考回答：**
<callout emoji="📌">
- CountDownLatch：倒计数器。允许一个或多个线程等待其他线程完成操作。
- CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一 组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
- Semaphore（信号量）：是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
- Exchanger（交换者）：是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。
</callout>
### CyclicBarrier和CountDownLatch有什么区别？
**参考回答：**
<callout emoji="📌">
1. CyclicBarrier是可重用的，其中的线程会等待所有的线程完成任务。届时，屏障将被拆除，并可以选择性地做一些特定的动作。CountDownLatch是一次性的，不同的线程在同一个计数器上工作，直到计数器为0.
2. CyclicBarrier面向的是线程数；CountDownLatch面向的是任务数。
3. 在使用CyclicBarrier时，你必须在构造中指定参与协作的线程数，这些线程必须调用await()方法；使用CountDownLatch时，则必须要指定任务数，至于这些任务由哪些线程完成无关紧要。
4. CyclicBarrier可以在所有的线程释放后重新使用；CountDownLatch在计数器为0时不能再使用。
</callout>