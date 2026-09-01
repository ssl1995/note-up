package com.ssl.know.java.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型 - 完整实现
 * <p>
 * 面试考点：
 * 1. wait/notify 机制
 * 2. synchronized 关键字
 * 3. ReentrantLock + Condition
 * 4. BlockingQueue（推荐）
 * 5. 虚假唤醒问题
 * 6. 线程池的使用
 *
 * @author ssl
 */
public class ProducerConsumerModel {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("========== 1. wait/notify 实现 ==========");
    testWaitNotify();

    Thread.sleep(2000);

    System.out.println("\n========== 2. ReentrantLock + Condition 实现 ==========");
    testLockCondition();

    Thread.sleep(2000);

    System.out.println("\n========== 3. BlockingQueue 实现（推荐）==========");
    testBlockingQueue();
  }

  // ==================== 方式1：wait/notify ====================

  /**
   * 共享缓冲区 - wait/notify 版本
   */
  static class WaitNotifyBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public WaitNotifyBuffer(int capacity) {
      this.capacity = capacity;
    }

    /**
     * 生产方法
     * 考点1：为什么用 while 而不是 if？
     * 答案：防止虚假唤醒（spurious wakeup）
     */
    public synchronized void produce(int item) throws InterruptedException {
      // 考点2：为什么要在同步块中检查条件？
      // 答案：wait 会释放锁，醒来后需要重新检查条件
      while (queue.size() == capacity) {
        System.out.println("【wait/notify】队列已满，生产者等待...");
        wait();  // 释放锁，进入等待队列
      }

      queue.offer(item);
      System.out.println("【wait/notify】生产: " + item + ", 当前队列大小: " + queue.size());

      // 考点3：为什么用 notifyAll 而不是 notify？
      // 答案：notify 只唤醒一个线程，可能唤醒同类线程导致死锁
      notifyAll();  // 唤醒所有等待线程
    }

    /**
     * 消费方法
     */
    public synchronized int consume() throws InterruptedException {
      while (queue.isEmpty()) {
        System.out.println("【wait/notify】队列为空，消费者等待...");
        wait();
      }

      int item = queue.poll();
      System.out.println("【wait/notify】消费: " + item + ", 当前队列大小: " + queue.size());

      notifyAll();
      return item;
    }
  }

  static void testWaitNotify() throws InterruptedException {
    WaitNotifyBuffer buffer = new WaitNotifyBuffer(3);

    // 生产者线程
    Thread producer = new Thread(() -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.produce(i);
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Producer-WaitNotify");

    // 消费者线程
    Thread consumer = new Thread(() -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.consume();
          Thread.sleep(200);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Consumer-WaitNotify");

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();
  }

  // ==================== 方式2：ReentrantLock + Condition ====================

  /**
   * 共享缓冲区 - Lock/Condition 版本
   * <p>
   * 考点4：Lock 相比 synchronized 的优势？
   * - 可中断的锁获取（lockInterruptibly）
   * - 尝试非阻塞获取锁（tryLock）
   * - 多个 Condition 实现更精细的线程调度
   */
  static class LockConditionBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();

    // 考点5：为什么需要两个 Condition？
    // 答案：分离生产者和消费者的等待队列，避免唤醒无关线程
    private final Condition notFull = lock.newCondition();   // 队列不满（生产者等待条件）
    private final Condition notEmpty = lock.newCondition();  // 队列不空（消费者等待条件）

    public LockConditionBuffer(int capacity) {
      this.capacity = capacity;
    }

    public void produce(int item) throws InterruptedException {
      lock.lock();
      try {
        while (queue.size() == capacity) {
          System.out.println("【Lock】队列已满，生产者等待...");
          notFull.await();  // 生产者等待"队列不满"条件
        }

        queue.offer(item);
        System.out.println("【Lock】生产: " + item + ", 当前队列大小: " + queue.size());

        notEmpty.signal();  // 唤醒等待"队列不空"的消费者
      } finally {
        lock.unlock();
      }
    }

    public int consume() throws InterruptedException {
      lock.lock();
      try {
        while (queue.isEmpty()) {
          System.out.println("【Lock】队列为空，消费者等待...");
          notEmpty.await();  // 消费者等待"队列不空"条件
        }

        int item = queue.poll();
        System.out.println("【Lock】消费: " + item + ", 当前队列大小: " + queue.size());

        notFull.signal();  // 唤醒等待"队列不满"的生产者
        return item;
      } finally {
        lock.unlock();
      }
    }
  }

  static void testLockCondition() throws InterruptedException {
    LockConditionBuffer buffer = new LockConditionBuffer(3);

    Thread producer = new Thread(() -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.produce(i);
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Producer-Lock");

    Thread consumer = new Thread(() -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.consume();
          Thread.sleep(200);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Consumer-Lock");

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();
  }

  // ==================== 方式3：BlockingQueue（推荐）====================

  /**
   * 共享缓冲区 - BlockingQueue 版本
   * <p>
   * 考点6：为什么推荐 BlockingQueue？
   * - 无需手动处理锁和条件变量
   * - 内部已经处理好线程安全问题
   * - 代码简洁，不易出错
   * <p>
   * 考点7：BlockingQueue 的常见实现类？
   * - ArrayBlockingQueue：有界数组队列
   * - LinkedBlockingQueue：有界/无界链表队列
   * - PriorityBlockingQueue：优先级队列
   * - SynchronousQueue：不存储元素的队列
   * - DelayQueue：延迟队列
   */
  static class BlockingQueueBuffer {
    private final BlockingQueue<Integer> queue;

    public BlockingQueueBuffer(int capacity) {
      // 有界队列，防止内存溢出
      this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void produce(int item) throws InterruptedException {
      // put 方法：如果队列满，阻塞等待
      queue.put(item);
      System.out.println("【BlockingQueue】生产: " + item + ", 当前队列大小: " + queue.size());
    }

    public int consume() throws InterruptedException {
      // take 方法：如果队列空，阻塞等待
      int item = queue.take();
      System.out.println("【BlockingQueue】消费: " + item + ", 当前队列大小: " + queue.size());
      return item;
    }
  }

  static void testBlockingQueue() throws InterruptedException {
    BlockingQueueBuffer buffer = new BlockingQueueBuffer(3);

    // 使用线程池管理线程
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // 生产者任务
    Runnable producerTask = () -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.produce(i);
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    // 消费者任务
    Runnable consumerTask = () -> {
      try {
        for (int i = 1; i <= 5; i++) {
          buffer.consume();
          Thread.sleep(200);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    executor.submit(producerTask);
    executor.submit(consumerTask);

    executor.shutdown();
    while (!executor.isTerminated()) {
      Thread.sleep(100);
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】wait/notify  vs  Condition
 * - wait/notify：与 synchronized 配合，一个等待队列
 * - Condition：与 Lock 配合，可创建多个等待队列，更灵活
 * <p>
 * 【考点2】为什么用 while 而不是 if？
 * - 防止虚假唤醒（spurious wakeup）
 * - 线程被唤醒后需要重新检查条件
 * <p>
 * 【考点3】notify  vs  notifyAll
 * - notify：随机唤醒一个等待线程，可能唤醒同类线程导致死锁
 * - notifyAll：唤醒所有等待线程，安全但效率稍低
 * <p>
 * 【考点4】synchronized  vs  ReentrantLock
 * - synchronized：JVM 层面，自动释放锁，代码简洁
 * - ReentrantLock：API 层面，可中断、可超时、公平锁、多 Condition
 * <p>
 * 【考点5】BlockingQueue 的优势
 * - 封装了锁和条件变量，使用简单
 * - 有界队列防止内存溢出
 * - 支持阻塞操作（put/take）和超时操作（offer/poll）
 * <p>
 * 【考点6】生产者消费者模型的应用场景
 * - 线程池任务队列
 * - 消息队列（MQ）
 * - 日志异步处理
 * - 数据流水线处理
 * <p>
 * 【考点7】相关面试题
 * - 如何实现线程安全的单例模式？（双重检查锁）
 * - 如何实现线程池？（核心就是生产者消费者模型）
 * - 阻塞队列的实现原理？
 * - 什么是虚假唤醒？如何避免？
 */
