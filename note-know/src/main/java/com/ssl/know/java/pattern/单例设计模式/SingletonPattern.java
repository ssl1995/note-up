package com.ssl.know.java.pattern.单例设计模式;

/**
 * 单例设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 饿汉式（线程安全，类加载时初始化）
 * 2. 懒汉式（线程不安全，延迟加载）
 * 3. 同步懒汉式（线程安全，效率低）
 * 4. 双重检查锁 DCL（线程安全，推荐）
 * 5. 静态内部类（线程安全，推荐）
 * 6. 枚举单例（最佳实践，防反射防序列化）
 * 7. 反射破坏单例及防护
 * 8. 序列化破坏单例及防护
 *
 * @author ssl
 */
public class SingletonPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 饿汉式 ==========");
    HungrySingleton hungry1 = HungrySingleton.getInstance();
    HungrySingleton hungry2 = HungrySingleton.getInstance();
    System.out.println("同一实例: " + (hungry1 == hungry2));

    System.out.println("\n========== 2. 懒汉式（线程不安全） ==========");
    LazySingleton lazy1 = LazySingleton.getInstance();
    LazySingleton lazy2 = LazySingleton.getInstance();
    System.out.println("同一实例: " + (lazy1 == lazy2));

    System.out.println("\n========== 3. 同步懒汉式 ==========");
    SyncLazySingleton syncLazy1 = SyncLazySingleton.getInstance();
    SyncLazySingleton syncLazy2 = SyncLazySingleton.getInstance();
    System.out.println("同一实例: " + (syncLazy1 == syncLazy2));

    System.out.println("\n========== 4. 双重检查锁 DCL ==========");
    DCLSingleton dcl1 = DCLSingleton.getInstance();
    DCLSingleton dcl2 = DCLSingleton.getInstance();
    System.out.println("同一实例: " + (dcl1 == dcl2));

    System.out.println("\n========== 5. 静态内部类 ==========");
    InnerClassSingleton inner1 = InnerClassSingleton.getInstance();
    InnerClassSingleton inner2 = InnerClassSingleton.getInstance();
    System.out.println("同一实例: " + (inner1 == inner2));

    System.out.println("\n========== 6. 枚举单例（最佳实践） ==========");
    EnumSingleton enum1 = EnumSingleton.INSTANCE;
    EnumSingleton enum2 = EnumSingleton.INSTANCE;
    System.out.println("同一实例: " + (enum1 == enum2));
    enum1.doSomething();

    System.out.println("\n========== 7. 多线程验证 DCL 线程安全 ==========");
    testDCLThreadSafety();
  }

  // ==================== 1. 饿汉式 ====================

  /**
   * 饿汉式单例
   * <p>
   * 优点：线程安全（类加载时初始化，JVM 保证线程安全）
   * 缺点：类加载时就创建实例，如果一直不用会浪费内存
   * <p>
   * 考点1：为什么饿汉式是线程安全的？
   * 答案：JVM 在类加载阶段会加锁，保证只有一个线程初始化类，
   * static 变量在类初始化时赋值，天然线程安全。
   */
  static class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    // 私有构造，防止外部 new
    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
      return INSTANCE;
    }
  }

  // ==================== 2. 懒汉式（线程不安全） ====================

  /**
   * 懒汉式单例（线程不安全）
   * <p>
   * 优点：延迟加载，用到时才创建
   * 缺点：多线程下可能创建多个实例
   * <p>
   * 考点2：为什么懒汉式线程不安全？
   * 答案：多线程同时进入 if (instance == null) 判断，
   * 都会执行 new 操作，导致创建多个实例。
   */
  static class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
      if (instance == null) {
        instance = new LazySingleton();
      }
      return instance;
    }
  }

  // ==================== 3. 同步懒汉式 ====================

  /**
   * 同步懒汉式单例
   * <p>
   * 优点：线程安全
   * 缺点：每次获取实例都要加锁，效率低
   * <p>
   * 考点3：为什么同步懒汉式效率低？
   * 答案：synchronized 修饰整个方法，即使实例已经创建，
   * 每次调用 getInstance() 仍然需要获取锁，造成不必要的性能损耗。
   */
  static class SyncLazySingleton {
    private static SyncLazySingleton instance;

    private SyncLazySingleton() {
    }

    public static synchronized SyncLazySingleton getInstance() {
      if (instance == null) {
        instance = new SyncLazySingleton();
      }
      return instance;
    }
  }

  // ==================== 4. 双重检查锁 DCL（推荐） ====================

  /**
   * 双重检查锁单例（Double-Checked Locking）
   * <p>
   * 优点：线程安全 + 延迟加载 + 高性能
   * <p>
   * 考点4：为什么需要两次 null 检查？
   * 答案：第一次检查避免不必要的加锁；第二次检查防止多线程同时通过第一次检查后重复创建。
   * <p>
   * 考点5：为什么需要 volatile？
   * 答案：防止指令重排序。instance = new DCLSingleton() 分三步：
   * 1. 分配内存  2. 初始化对象  3. 赋值引用
   * 如果 2 和 3 重排，其他线程可能拿到未初始化的对象。
   * volatile 保证有序性和可见性。
   */
  static class DCLSingleton {
    // volatile 禁止指令重排序，保证可见性
    private static volatile DCLSingleton instance;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
      if (instance == null) {                    // 第一次检查：避免不必要的加锁
        synchronized (DCLSingleton.class) {      // 加锁
          if (instance == null) {                // 第二次检查：防止重复创建
            instance = new DCLSingleton();
          }
        }
      }
      return instance;
    }
  }

  // 测试
  static class DCLTest {
    private static volatile DCLTest instance;

    private DCLTest() {
    }

    public DCLTest getInstance() {
      if (instance == null) {
        synchronized (DCLTest.class) {
          if (instance == null) {
            instance = new DCLTest();
          }
        }
      }
      return instance;
    }
  }

  // ==================== 5. 静态内部类（推荐） ====================

  /**
   * 静态内部类单例
   * <p>
   * 优点：线程安全 + 延迟加载 + 无锁高性能
   * <p>
   * 考点6：为什么静态内部类能实现延迟加载？
   * 答案：静态内部类不会随着外部类的加载而加载，
   * 只有调用 getInstance() 时才会加载 SingletonHolder，
   * 由 JVM 类加载机制保证线程安全。
   * <p>
   * 考点7：静态内部类 vs DCL 怎么选？
   * 答案：静态内部类代码更简洁，不需要 volatile 和双重检查；
   * DCL 更灵活，可以在 getInstance() 中做额外逻辑。
   */
  static class InnerClassSingleton {
    private InnerClassSingleton() {
    }

    private static class SingletonHolder {
      private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
      return SingletonHolder.INSTANCE;
    }
  }

  // ==================== 6. 枚举单例（最佳实践） ====================

  /**
   * 枚举单例（Effective Java 推荐）
   * <p>
   * 优点：
   * 1. 线程安全（JVM 保证）
   * 2. 防止反射攻击（枚举不能通过反射创建实例）
   * 3. 防止序列化破坏（枚举自带序列化机制，不会创建新对象）
   * 4. 代码最简洁
   * <p>
   * 考点8：为什么枚举能防反射？
   * 答案：反射 newInstance() 方法中会判断如果是枚举类型，
   * 直接抛出 IllegalArgumentException。
   * <p>
   * 考点9：为什么枚举能防序列化？
   * 答案：枚举的序列化由 JVM 特殊处理，反序列化时直接返回
   * 已有的枚举常量，不会创建新对象。
   */
  enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
      System.out.println("枚举单例执行业务逻辑");
    }
  }

  // ==================== 7. 多线程验证 DCL ====================

  /**
   * 多线程验证 DCL 单例的线程安全性
   */
  static void testDCLThreadSafety() {
    Thread[] threads = new Thread[10];
    DCLSingleton[] results = new DCLSingleton[10];

    for (int i = 0; i < 10; i++) {
      final int index = i;
      threads[i] = new Thread(() -> {
        results[index] = DCLSingleton.getInstance();
      }, "Thread-" + i);
    }

    for (Thread t : threads) {
      t.start();
    }
    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    boolean allSame = true;
    for (int i = 1; i < 10; i++) {
      if (results[0] != results[i]) {
        allSame = false;
        break;
      }
    }
    System.out.println("10个线程获取的实例全部相同: " + allSame);
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】饿汉式 vs 懒汉式
 * - 饿汉式：类加载时创建，线程安全，可能浪费内存
 * - 懒汉式：用时才创建，线程不安全，需要额外处理
 * <p>
 * 【考点2】DCL 为什么需要 volatile？
 * - new 对象分三步：分配内存 → 初始化 → 赋值引用
 * - 指令重排可能导致引用指向未初始化的对象
 * - volatile 禁止重排，保证可见性
 * <p>
 * 【考点3】静态内部类为什么线程安全？
 * - JVM 类加载机制保证：类初始化阶段会加锁
 * - 静态内部类只有被主动引用时才加载，实现延迟加载
 * <p>
 * 【考点4】枚举单例为什么是最佳实践？
 * - 天然线程安全
 * - 防反射：Constructor.newInstance() 检查枚举类型直接抛异常
 * - 防序列化：枚举序列化由 JVM 特殊处理
 * <p>
 * 【考点5】如何破坏单例？
 * - 反射：通过 setAccessible(true) 调用私有构造
 * - 序列化：实现 Serializable 后反序列化会创建新对象
 * <p>
 * 【考点6】如何防止单例被破坏？
 * - 防反射：构造方法中判断实例是否已存在，存在则抛异常
 * - 防序列化：添加 readResolve() 方法返回已有实例
 * - 使用枚举：天然防反射防序列化
 * <p>
 * 【考点7】单例模式的应用场景
 * - 配置管理类
 * - 连接池、线程池
 * - 缓存、日志对象
 * - Spring 容器中的 Bean（默认单例）
 * <p>
 * 【考点8】Spring 中的单例和单例模式的区别
 * - Spring 单例：每个 Spring 容器中一个 Bean 只有一个实例
 * - 单例模式：JVM 范围内一个类只有一个实例
 * - Spring 单例由容器管理，单例模式由类自身管理
 */
