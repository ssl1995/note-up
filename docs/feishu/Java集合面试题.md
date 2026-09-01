# Java集合面试题
<readonly-block type="isv"></readonly-block>
<callout emoji="📌"><p>Java 集合学习指引：<cite doc-id="HOsqwdua7iuttnk9DXBch1Jmn6g" file-type="wiki" title="Java集合框架学习指引" type="doc"></cite></p></callout>
## **集合概述（重要）**
### 常用的集合分类以及他们的区别？
**分析：**
需要对整个集合框架有个整体认识之后，理解记忆。大方向 Collection & Map 两类，聊一下不同集合类的特点
**参考回答：**
<callout emoji="📌">
- List(对付顺序的好帮手): 存储的元素是有序的、可重复的。
- Set(注重独一无二的性质): 存储的元素不可重复的。
- Queue(实现排队功能的叫号机): 按特定的排队规则来确定先后顺序，存储的元素是有序的、可重复的。
- Map(用 key 来搜索的专家): 使用键值对（key-value）存储，类似于数学上的函数 y=f(x)，"x" 代表 key，"y" 代表 value，key 是无序的、不可重复的，value 是无序的、可重复的，每个键最多映射到一个值。
</callout>
**推荐学习：**
[Collection 类关系图](https://pdai.tech/md/java/collection/java-collection-all.html)
### 你最常用的集合实现类有哪些？
**参考回答**
<callout comment-refs="c2" emoji="📌">
1. **ArrayList：** 动态数组，实现了List接口，支持动态增长。
2. **LinkedList：** 双向链表，也实现了List接口，支持快速的插入和删除操作。
3. **HashMap：** 基于哈希表的Map实现，存储键值对，通过键快速查找值。
4. **HashSet：** 基于HashMap实现的Set集合，用于存储唯一元素。
5. **TreeMap：** 基于红黑树实现的有序Map集合，可以按照键的顺序进行排序。
6. **LinkedHashMap：** 基于哈希表和双向链表实现的Map集合，保持插入顺序或访问顺序。
7. **PriorityQueue：** 优先队列，可以按照比较器或元素的自然顺序进行排序。
</callout>
### 哪些集合类是线程安全的？
**分析**
通过学习推荐资料之后，记忆几个典型的并发安全集合，面试官问这个问题一般是期望把话题引到Java并发的考察上面，例如Hashtable，HashMap， ConcurrentHashMap之间的比较，尤其重要。这个问题就先整体回答下，其他等面试官细问再说。
**参考回答**
<callout emoji="📌">
Vector、Hashtable、Stack 都是线程安全的，而像 HashMap 则是非线程安全的，不过在 JDK 1.5 之后随着 Java. util. concurrent 并发包的出现，它们也有了自己对应的线程安全类，比如 HashMap 对应的线程安全类就是 ConcurrentHashMap。
</callout>
**学习推荐：**
[Collection 类关系图](https://pdai.tech/md/java/collection/java-collection-all.html)
### 什么是 fail-fast，什么是 fail-safe
**分析**
需要了解并发操作集合时的错误处理机制，以及 fail-safe 为什么叫失败安全（COW）
**回答**
<callout comment-refs="c6" emoji="📌">
fail-safe 和 fail-fast 是多线程并发操作集合时的一种失败处理机制
fail-fast 表示快速失败，在集合遍历过程中，一旦发现容器中的数据被修改了，会立刻抛出ConcurrentModificationException 异常，从而导致遍历失败
fail-safe 表示失败安全，也就是在这种机制下，出现集合元素的修改，不会抛出 ConcurrentModificationException
</callout>
**推荐阅读**
[Fail-safe机制与Fail-fast机制分别有什么作用 ](https://www.cnblogs.com/mic112/p/16106261.html)
### 那么（fast-fail）快速失败机制底层是怎么实现的呢？
**参考回答：**
<callout emoji="📌">
迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。集合在被遍历期间如果内容发生变化，就会改变modCount的值。当迭代器使用hasNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedModCount值，是的话就返回遍历；否则抛出异常，终止遍历。      看异常ConcurrentModificationException，JDK中是这么介绍该异常的：当检测到一个并发的修改，就可能会抛出该异常，一些迭代器的实现会抛出该异常，以便可以快速失败。但是你不可以为了便捷而依赖该异常，而应该仅仅作为一个程序的侦测。
</callout>
### Collection 和 Collections 有什么区别？
**分析：**
**参考回答：**
<callout emoji="📌">
- Collection 是一个集合接口，它提供了对集合对象进行基本操作的通用接口方法，比如 List、Set 等都是它的子类。
- Collections 是一个包装类，包含了很多静态方法，不能被实例化，就像一个工具类，比如提供的排序方法： Collections. sort(list)。
</callout>
**推荐学习：**
[Java中Collection和Collections的区别](https://blog.csdn.net/suwu150/article/details/52714213)
### List、Set、Map 之间的区别是什么？
**分析：**
**参考回答：**
![图片中 addCriterion()</qa>中展示了List、Set、Map三者之间的区别。在继承接口方面，两者均继承Collection接口，Map继承Map接口。常见实现类中，List有ArrayList、LinkedList、Vector等，Set有HashSet、LinkedHashSet、TreeSet等，Map有HashMap、HashTable等。常见方法方面，List有add()、remove()等，Set有add()、remove()等，Map有put����](https://feishu.cn/file/R0kqbj2Ioo8lpfxyoxIcpN48nhV)
**推荐学习：**
[Java中 List、Set、Map 之间的区别](https://blog.csdn.net/u012102104/article/details/79235938)
### 集合遍历的方法有哪些？
<callout emoji="📌">
在Java中，集合的遍历方法主要有以下几种：
</callout>
> - **普通 for 循环：** 可以使用带有索引的普通 for 循环来遍历 List。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> for (int i = 0; i < list.size(); i++) {
>     String element = list.get(i);
>     System.out.println(element);
> }
> ```
>
> - **增强 for 循环（for-each循环）：** 用于循环访问数组或集合中的元素。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> for (String element : list) {
>     System.out.println(element);
> }
> ```
>
> - **Iterator 迭代器：** 可以使用迭代器来遍历集合，特别适用于需要删除元素的情况。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> Iterator<String> iterator = list.iterator();
> while(iterator.hasNext()) {
>     String element = iterator.next();
>     System.out.println(element);
> }
> ```
>
> - **ListIterator 列表迭代器：** ListIterator是迭代器的子类，可以双向访问列表并在迭代过程中修改元素。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> ListIterator<String> listIterator= list.listIterator();
> while(listIterator.hasNext()) {
>     String element = listIterator.next();
>     System.out.println(element);
> }
> ```
>
> - **使用 forEach 方法：** Java 8引入了 forEach 方法，可以对集合进行快速遍历。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> list.forEach(element -> System.out.println(element));
> ```
>
> - **Stream API：** Java 8的Stream API提供了丰富的功能，可以对集合进行函数式操作，如过滤、映射等。
>
> ```Java
> List<String> list = new ArrayList<>();
> list.add("A");
> list.add("B");
> list.add("C");
>
> list.stream().forEach(element -> System.out.println(element));
> ```
>
> 这些是常用的集合遍历方法，根据情况选择合适的方法来遍历和操作集合。
### 迭代器 Iterator 是什么？
**分析：**
**参考回答：**
> Iterator 接口提供遍历任何 Collection 的接口。我们可以从一个 Collection 中使用迭代器方法来获取迭代器实例。迭代器取代了 Java 集合框架中的 Enumeration，迭代器允许调用者在迭代过程中移除元素。
>
> 1. Iterator 怎么使用？有什么特点？
>
> Iterator 使用代码如下：
>
> ```Java
> List < String > list = new ArrayList < > ();
> Iterator < String > it = list.iterator();
> while (it.hasNext()) {
>     String obj = it.next();
>     System.out.println(obj);
> }
> ```
>
> Iterator 的特点是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 ConcurrentModificationException 异常。
**推荐学习：**
[Java中Iterator(迭代器)实现原理](https://www.cnblogs.com/xiongmozhou/p/10085105.html)
### 怎么确保一个集合不能被修改?
**参考回答：**
<callout emoji="📌">
可以使用 Collections. unmodifiableCollection(Collection c) 方法来创建一个只读集合，这样改变 集合的任何操作都会抛出 Java. lang. UnsupportedOperationException 异常。
</callout>
> 示例代码如下:
>
> ```Java
> List<String> list = new ArrayList<>();
> list. add("x");
> Collection<String> clist = Collections. unmodifiableCollection(list);
> clist. add("y");  // 运行时此行报错
> System. out. println(list. size());
>
> ```
## List（重要）
### 讲一下java里面List的几种实现？
**参考回答：**
<callout emoji="📌">
**几种实现？**
- ArrayList： 是应用更加广泛的动态数组实现，它本身不是线程安全的，所以性能要好很多。与 Vector 近似，ArrayList 也是可以根据需要调整容量，不过两者的调整逻辑有所区别，Vector 在扩容时会提高 1 倍，而 ArrayList 则是增加 50%。
- LinkedList： 顾名思义是 Java 提供的双向链表，所以它不需要像上面两种那样调整容量，它也不是线程安全的。
- Vector：Vector 和 ArrayList 类似，也实现了 List 接口。但是， Vector 中的主要方法都是 synchronized 方法，即通过互斥同步方式保证操作的线程安全。在多线程环境下，如果需要对集合进行写操作，可以考虑使用Vector来保证线程安全。
- Stack ： 也是一个同步容器，它的方法也用 synchronized 进行了同步，它实际上是继承于 Vector 类。Stack堆栈在数据结构中有特定的应用场景，后进先出（LIFO）
</callout>
### ArrayList 和 Array（数组）的区别？
**分析：**
理解记忆，面试的时候能回答部分差别即可，不用背。
**参考回答：**
<callout emoji="📌">
- ArrayList 内部基于动态数组实现，比 Array（静态数组） 使用起来更加灵活：
- ArrayList会根据实际存储的元素动态地扩容，而 Array 被创建之后就不能改变它的长度了。
- ArrayList 允许你使用泛型来确保类型安全，Array 则不可以。
- ArrayList 中只能存储对象。对于基本类型数据，需要使用其对应的包装类（如 Integer、Double 等）。Array 可以直接存储基本类型数据，也可以存储对象。
- ArrayList 支持插入、删除、遍历等常见操作，并且提供了丰富的 API 操作方法，比如 add()、remove()等。Array 只是一个固定长度的数组，只能按照下标访问其中的元素，不具备动态添加、删除元素的能力。
- ArrayList创建时不需要指定大小，而Array创建时必须指定大小。
</callout>
**推荐学习：**
[Java中Arraylist和Array有什么区别](https://worktile.com/kb/ask/37415.html)
### ArrayList 和 Vector 的区别是什么？
**分析：**
可以从线程安全，性能，扩容方面进行回答。
**参考回答：**
<callout emoji="📌">
- 线程安全：Vector 使用了 Synchronized 来实现线程同步，是线程安全的，而 ArrayList 是非线程安全的。
- 性能：ArrayList 在性能方面要优于 Vector。
- 扩容：ArrayList 和 Vector 都会根据实际的需要动态的调整容量，只不过在 Vector 扩容每次会增加 1 倍，而 ArrayList 只会增加 50%。
</callout>
**推荐学习：**
[Arraylist与Vector的区别](https://zhuanlan.zhihu.com/p/28241176)
### ArrayList 与 LinkedList 区别?
**分析：**
**参考回答：**
<callout emoji="📌">
**是否保证线程安全：** ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
**底层数据结构：** ArrayList 底层使用的是 **Object 数组**；LinkedList 底层使用的是 **双向链表** 数据结构（JDK1.6 之前为循环链表，JDK1.7 取消了循环。注意双向链表和双向循环链表的区别，下面有介绍到！）
**插入和删除是否受元素位置的影响：**
- ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。 比如：执行add(E e)方法的时候， ArrayList 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是 O(1)。但是如果要在指定位置 i 插入和删除元素的话（add(int index, E element)），时间复杂度就为 O(n)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。
- LinkedList 采用链表存储，所以在头尾插入或者删除元素不受元素位置的影响（add(E e)、addFirst(E e)、addLast(E e)、removeFirst()、 removeLast()），时间复杂度为 O(1)，如果是要在指定位置 i 插入和删除元素的话（add(int index, E element)，remove(Object o),remove(int index)）， 时间复杂度为 O(n) ，因为需要先移动到指定位置再插入和删除。
**是否支持快速随机访问：** LinkedList 不支持高效的随机元素访问，而 ArrayList（实现了 RandomAccess 接口） 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index)方法)。
**内存空间占用：** ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。
</callout>
**推荐学习：**
[Java中ArrayList与LinkedList的区别](https://zhuanlan.zhihu.com/p/33141246)
### ArrayList 和 LinkedList 的应用场景？
**参考回答：**
<callout emoji="📌">
- ArrayList适用于需要频繁访问集合元素的场景。它基于数组实现，可以通过索引快速访问元素，因此在按索引查找、遍历和随机访问元素的操作上具有较高的性能。当需要频繁访问和遍历集合元素，并且集合大小不经常改变时，推荐使用ArrayList
- LinkedList适用于频繁进行插入和删除操作的场景。它基于链表实现，插入和删除元素的操作只需要调整节点的指针，因此在插入和删除操作上具有较高的性能。当需要频繁进行插入和删除操作，或者集合大小经常改变时，可以考虑使用LinkedList。
**补充：**
- Vector特性与ArrayList基本类似，所以在使用ArrayList场景基础上，如果还需要增加并发安全的考虑，可以选择Vector，但性能不高
- Stack：Stack堆栈在数据结构中有特定的应用场景，后进先出（LIFO），在这些场景下可以考虑使用Stack来实现相应的功能。
</callout>
### 说一说ArrayList 扩容机制
**分析：**
**参考回答：**
<callout comment-refs="c33" emoji="📌">
ArrayList是List接口的实现类，它是支持根据需要而动态增长的数组。java中标准数组是定长的，在数组被创建之后，它们不能被加长或缩短。这就意味着在创建数组时需要知道数组的所需长度，但有时我们需要动态程序中获取数组长度。ArrayList就是为此而生的,但是它不是线程安全的，外ArrayList按照插入的顺序来存放数据
1. ArrayList扩容发生在add()方法调用的时候， 调用ensureCapacityInternal()来扩容的，通过方法calculateCapacity(elementData, minCapacity)获取需要扩容的长度:
2. ensureExplicitCapacity方法可以判断是否需要扩容：
3. ArrayList扩容的关键方法grow():获取到ArrayList中elementData数组的内存空间长度 扩容至原来的1.5倍
4. 调用Arrays.copyOf方法将elementData数组指向新的内存空间时newCapacity的连续空间，从此方法中我们可以清晰的看出其实ArrayList扩容的本质就是计算出新的扩容数组的size后实例化，并将原有数组内容复制到新数组中去。
</callout>
**推荐学习：**
[面试官让我说一说ArrayList的扩容机制](https://blog.csdn.net/weixin_42462804/article/details/108726206)
### 如何实现数组和 List 之间的转换？
**分析：**
**参考回答：**
> - zz数组转 List：使用 Arrays. asList(array) 进行转换。
> - List 转数组：使用 List 自带的 toArray() 方法。
>
> 代码示例：
>
> ```Java
> // list to array
> List < String > list = new ArrayList < String > ();
> list.add("叶痕秋");
> list.add("的诗情画意");
> list.toArray();
> // array to list
> String[] array = new String[] {
>     "王磊", "的诗情画意"
> };
> Arrays.asList(array);
> ```
### ArrayList线程安全吗？把ArrayList变成线程安全有哪些方法？
**参考回答：**
<callout comment-refs="c41" emoji="📌">
不是线程安全的，ArrayList变成线程安全的方式有：
- 使用Collections类的synchronizedList方法将ArrayList包装成线程安全的List：`List<String> synchronizedList = Collections.synchronizedList(arrayList);`
- 使用CopyOnWriteArrayList 或 Vector等并发安全的类代替ArrayList，
</callout>
### 为什么 ArrayList 的 elementData 加上 transient 修饰?
**解析：**
> ArrayList 中的数组定义如下:
>
> ![图片展示了ArrayList中elementData的定义。elementData是一个非私有的transient Object\[\]数组，用于存储ArrayList的元素。其容量即为数组长度，当首次添加元素时，若空的ArrayList的elementData为DEFAULTCAPACITY_EMPTY_ELEMENTDATA，将扩展为DEFAULT_CAPACITY。该图片与上文解析ArrayList中elementData加上transient修饰的内容相关，直观呈现了elementData的定义及特性。](https://feishu.cn/file/ZNVabXoyEoSj7mxlggxcwoK2nGh)
>
> 再看一下 ArrayList 的定义:
>
> ```Java
> public class ArrayList<E> extends AbstractList<E>
>      implements List<E>, RandomAccess, Cloneable, java.io.Serializable
> ```
>
> 可以看到 ArrayList 实现了 Serializable 接口，这意味着 ArrayList 支持序列化。transient 的作用是说不希望 elementData 数组直接被序列化，而是重写了 writeObject 实现:
>
> ```Java
> private void writeObject(java.io.ObjectOutputStream s) throws
> java.io.IOException{
>       // Write out element count, and any hidden stuff*
>       int expectedModCount = modCount;
>       s.defaultWriteObject();
>       // Write out array length*
>       s.writeInt(elementData.length);
>       // Write out all elements in the proper order.*
>       for (int i=0; i<size; i++)
>           s.writeObject(elementData[i]);
>       if (modCount != expectedModCount) {
>           throw new ConcurrentModificationException();
> }
> ```
>
> - 每次序列化时，先调用 defaultWriteObject() 方法序列化 ArrayList 中的非 transient 元素，然后 遍历 elementData，只序列化已存入的元素，这样既加快了序列化的速度，又减小了序列化之后 的文件大小。
**参考回答：**
<callout emoji="📌">
transient 的作用是说不希望 elementData 数组直接被序列化，而是重写了 writeObject 实现：
- 每次序列化时，先调用 defaultWriteObject() 方法序列化 ArrayList 中的非 transient 元素，然后 遍历 elementData，只序列化已存入的元素，这样既加快了序列化的速度，又减小了序列化之后 的文件大小。
</callout>
## Set
### Set集合有什么特点？如何实现key无重复的？
**参考回答：**
<callout emoji="📌">
- **set集合特点**：Set集合中的元素是唯一的，不会出现重复的元素。
- **set实现原理**：Set集合通过内部的数据结构（如哈希表、红黑树等）来实现key的无重复。当向Set集合中插入元素时，会先根据元素的hashCode值来确定元素的存储位置，然后再通过equals方法来判断是否已经存在相同的元素，如果存在则不会再次插入，保证了元素的唯一性。
</callout>
### Comparable 和 Comparator 的区别
**分析：**
**参考回答：**
<callout comment-refs="c56" emoji="📌">
Comparable 接口和 Comparator 接口都是 Java 中用于排序的接口，它们在实现类对象之间比较大小、排序等方面发挥了重要作用：
- Comparable 接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
- Comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序
一般我们需要对一个集合使用自定义排序时，我们就要重写compareTo()方法或compare()方法，当我们需要对某一个集合实现两种排序方式，比如一个 song 对象中的歌名和歌手名分别采用一种排序方法的话，我们可以重写compareTo()方法和使用自制的Comparator方法或者以两个 Comparator 来实现歌名排序和歌星名排序，第二种代表我们只能使用两个参数版的 Collections.sort().
</callout>
**推荐学习：**
[Java中Comparable和Comparator区别小结 ](https://www.cnblogs.com/xujian2014/p/5215082.html#_label2)
### 说一下 HashSet 的实现原理？
**分析：**
**参考回答：**
<callout comment-refs="c61" emoji="📌">
HashSet 是基于 HashMap 实现的，HashSet 底层使用 HashMap 来保存所有元素，因此 HashSet 的实现比较简单，相关 HashSet 的操作，基本上都是直接调用底层 HashMap 的相关方法来完成，HashSet 不允许重复的值。
</callout>
**推荐学习：**
[Java HashSet的实现原理详解](https://blog.csdn.net/guoweimelon/article/details/50804799)
### HashSet如何检查重复?HashSet是如何保证数据不可重复的?
**参考回答：**
<callout emoji="📌">
- 向HashSet 中add ()元素时，判断元素是否存在的依据，不仅要比较hash值，同时还要结合equles 方法比较。
- HashSet 中的add ()方法会使用HashMap 的put()方法。
- HashMap 的 key 是唯一的，由源码可以看出 HashSet 添加进去的值就是作为HashMap 的key， 并且在HashMap中如果K/V相同时，会用新的V覆盖掉旧的V，然后返回旧的V。所以不会重复。
</callout>
### 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同
分析：
**参考回答：**
<callout comment-refs="c68" emoji="📌">
- HashSet、LinkedHashSet 和 TreeSet 都是 Set 接口的实现类，都能保证元素唯一，并且都不是线程安全的。
- HashSet、LinkedHashSet 和 TreeSet 的主要区别在于底层数据结构不同。HashSet 的底层数据结构是哈希表（基于 HashMap 实现）。LinkedHashSet 的底层数据结构是链表和哈希表，元素的插入和取出顺序满足 FIFO。TreeSet 底层数据结构是红黑树，元素是有序的，排序的方式有自然排序和定制排序。
- 底层数据结构不同又导致这三者的应用场景不同。HashSet 用于不需要保证元素插入和取出顺序的场景，LinkedHashSet 用于保证元素的插入和取出顺序满足 FIFO 的场景，TreeSet 用于支持对元素自定义排序规则的场景。
</callout>
**推荐学习：**
[比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同](https://cloud.tencent.com/developer/article/2348753)
## Queue
### Queue 与 Deque 的区别
**分析：**
可以简单从设计目标
**参考回答：**
<callout emoji="📌">
1. **设计目标**：
- `Queue`（队列）被设计为遵循FIFO（First-In-First-Out，先进先出）原则的数据结构，这意味着元素按照它们被添加的顺序进行处理。
- `Deque`（双端队列，Double-Ended Queue的缩写）则支持在两端添加和移除元素。它既可以作为FIFO队列使用，也可以作为LIFO（Last-In-First-Out，后进先出）栈使用。
2. **操作**：
- `Queue` 接口定义了基本的队列操作，如 `add`、`offer`（添加元素），`remove`、`poll`（移除元素），以及 `peek`（查看但不移除头部元素）。
- `Deque` 接口扩展了 `Queue` 的功能，提供了在队列两端进行操作的方法，例如 `push`、`pop`、`addFirst`、`addLast`、`offerFirst`、`offerLast`、`removeFirst`、`removeLast`、`pollFirst`、`pollLast`等。
3. **特殊队列类型**：
- `Queue` 接口有几个特殊类型的实现，如 `PriorityQueue`（优先队列，元素根据其自然顺序或者比较器定义的顺序进行处理）和 `ConcurrentLinkedQueue`（线程安全的队列，适用于多线程环境）。
- `Deque` 接口的实现类，如 `LinkedList` 和 `ArrayDeque`，通常用于需要灵活地在两端添加或移除元素的场景。
</callout>
**推荐学习：**
[Queue 与 Deque 的区别](https://developer.aliyun.com/article/1356686)
### 在 Queue 中 poll()和 remove()有什么区别？
**分析：**
**参考回答：**
> - 相同点：都是返回第一个元素，并在队列中删除返回的对象。
> - 不同点：如果没有元素 poll()会返回 null，而 remove()会直接抛出 NoSuchElementException 异常。
>
> 代码示例：
>
> ```Java
> Queue<String> queue = new LinkedList<String>();
> queue. offer("string");
> // add
> System. out. println(queue. poll());
> System. out. println(queue. remove());
> System. out. println(queue. size());
> ```
**推荐学习：**
[Queue 中 remove() 和 poll() 区别](https://blog.csdn.net/meism5/article/details/89884257)
### ArrayDeque 与 LinkedList 的区别
**分析：**
**参考回答：**
<callout emoji="📌">
ArrayDeque 和 LinkedList 都实现了 Deque 接口，两者都具有队列的功能，但两者有什么区别呢？
- ArrayDeque 是基于可变长的数组和双指针来实现，而 LinkedList 则通过链表来实现。
- ArrayDeque 不支持存储 NULL 数据，但 LinkedList 支持。
- ArrayDeque 是在 JDK1.6 才被引入的，而LinkedList 早在 JDK1.2 时就已经存在。
- ArrayDeque 插入时可能存在扩容过程, 不过均摊后的插入操作依然为 O(1)。虽然 LinkedList 不需要扩容，但是每次插入数据时均需要申请新的堆空间，均摊性能相比更慢。
从性能的角度上，选用 ArrayDeque 来实现队列要比 LinkedList 更好。
</callout>
**推荐学习：**
[ArrayDeque 与 LinkedList 的区别](https://cloud.tencent.com/developer/article/2348751)
### 说一说 PriorityQueue
**分析：**
**参考回答：**
<callout emoji="📌">
PriorityQueue 是在 JDK1.5 中被引入的, 其与 Queue 的区别在于元素出队顺序是与优先级相关的，即总是优先级最高的元素先出队。
这里列举其相关的一些要点：
- PriorityQueue 利用了二叉堆的数据结构来实现的，底层使用可变长的数组来存储数据
- PriorityQueue 通过堆元素的上浮和下沉，实现了在 O(logn) 的时间复杂度内插入元素和删除堆顶元素。
- PriorityQueue 是非线程安全的，且不支持存储 NULL 和 non-comparable 的对象。
- PriorityQueue 默认是小顶堆，但可以接收一个 Comparator 作为构造参数，从而来自定义元素优先级的先后。
PriorityQueue 在面试中可能更多的会出现在手撕算法的时候，典型例题包括堆排序、求第 K 大的数、带权图的遍历等，所以需要会熟练使用才行。
</callout>
**推荐学习：**
[PriorityQueue解析](https://pdai.tech/md/java/collection/java-collection-PriorityQueue.html)
## Map（重要）
### 讲一下HashMap 的工作原理？
**分析：**
这题一般只是作为话题切入点，切换到HashMap的考察，所以这个问题笼统回答即可。等面试官后续追问再回答细节。
**参考回答：**
<callout emoji="📌">
HashMap的工作原理基于哈希表，这是一种使用哈希函数将键映射到存储位置的数据结构。哈希表通过计算键的哈希值，并将其转化为数组索引，从而快速定位键值对的存储位置。在理想情况下，哈希函数能将键均匀分布到哈希表中，以最小化哈希冲突。然而，当多个键哈希到同一位置时，就需要解决哈希冲突。
在Java 1.7中，HashMap主要使用链表来解决哈希冲突，将具有相同哈希值的键值对链接在一起。
但在Java 1.8中，为了进一步提高性能，当链表长度和数组长度超过一定阈值时，链表会转换为红黑树。红黑树作为一种自平衡的二叉搜索树，能够在哈希冲突较多时提供更快的查找、插入和删除操作。因此，HashMap结合哈希表、链表和红黑树的原理，实现了高效的键值对存储和查找功能。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
[HashMap 源码解析](https://pdai.tech/md/java/collection/java-map-HashMap&HashSet.html)
### HashMap key可以为null吗？
**参考回答：**
<callout emoji="📌">
可以为 null。
- hashMap中使用hash()方法来计算key的哈希值，当key为空时，直接令key的哈希值为0，不走key.hashCode()方法；
- hashMap虽然支持key和value为null，但是null作为key只能有一个，null作为value可以有多个；
- 因为hashMap中，如果key值一样，那么会覆盖相同key值的value为最新，所以key为null只能有一个。
</callout>
![图片展示的是Java中HashMap类的hash方法代码。当key等于null时，不走hashCode()方法，直接返回0；否则，先调用key的hashCode()方法获取哈希值h，再对h进行右移16位操作，最后对h和h右移16位后的结果进行异或运算。该代码与上文提到的HashMap中使用hash()方法计算key哈希值，以及key为null只能有一个等知识点相关，直观呈现了相关逻辑。](https://feishu.cn/file/CjI2bbCumokh9cx7lLLc2zQJnUb)
### Java8 Hashmap做了哪些优化？
**参考回答：**
<callout comment-refs="c95" emoji="📌">
1. 数组+链表改成了数组+链表或红黑树
2. 链表的插入方式从头插法改成了尾插法
3. 扩容的时候1.7需要对原数组中的元素进行重新hash定位在新数组的位置，1.8采用更简单的判断逻辑，位置不变或索引+旧容量大小；
4. 在插入时，1.7先判断是否需要扩容，再插入，1.8先进行插入，插入完成再判断是否需要扩容；
</callout>
### 讲一下HashMap 的put流程？
**分析：**
JDK1.8 put 流程图
<whiteboard token="Y4m7w9OLghZlx8bWnzQcNfuhn9g"></whiteboard>
**参考回答：**
<callout comment-refs="c104" emoji="📌">
1. 判断键值对数组table是否为空或为null，是则执行resize()进行扩容；
2. 根据键值key计算hash值得到插入的数组索引i，如果table[i]==null，直接新建节点添加，转向⑥，如果table[i]不为空，转向③；
3. 判断table[i]的首个元素是否和key一样，如果相同直接覆盖value，否则转向④，这里的相同指的是hashCode以及equals；
4. 判断table[i] 是否为treeNode，即table[i] 是否是红黑树，如果是红黑树，则直接走树中插入键值对流程，否则转向5；
5. 遍历table[i]链表，遍历过程中若发现key已经存在直接覆盖value，如果key不存在则在链表中插入节点。插入以后判断链表长度是否大于8，大于8的话把链表转换为红黑树（同时满足容量条件）。
6. 插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
[Java 8系列之重新认识HashMap](https://tech.meituan.com/2016/06/24/java-hashmap.html)
### HashMap 的长度为什么是 2 的幂次方
**分析：**
**参考回答：**
<callout emoji="📌">
因为我们在计算数组下标的时候，一般是采用取模的方法。例如 hash%length;  但是“取模运算 %”相对于“位与运算 a&n”是更慢的，所以如果能用“位与运算”替代取模运算的话，就会带来一定的性能优化。
但是，要用“位与运算&”替代“取模运算%”，需要满足一定的条件，那就是：
- 只有当length 为 2的n次方的时候，hash % length  =  hash & (length-1) 才成立
这也就解释了 HashMap 的长度为什么是 2 的幂次方。
</callout>
**推荐学习：**
[为什么HashMap的长度是2的整数次幂？](https://zhuanlan.zhihu.com/p/91636401)
### HashMap**默认负载因子为什么是 0.75 这个值呢？设太大和太小有什么问题？**
**参考回答：**
<callout emoji="📌">
这是因为对于使用链表法的哈希表来说，查找一个元素的平均时间是 O(1+n)，这里的 n 指的是遍历链表的长度，因此负载因子越大，对空间的利用就越充分，这就意味着链表的长度越长，查找效率也就越低。如果设置的加载因子太小，那么哈希表的数据将过于稀疏，对空间造成严重浪费。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### 默认容量为什么默认是16呢？怎么不是4？不是8？
**参考回答：**
<callout emoji="📌">
关于这个默认容量的选择，JDK并没有给出官方解释，那么这应该就是个经验值，既然一定要设置一个默认的2^n 作为初始值，那么就需要在效率和内存使用上做一个权衡。这个值既不能太小，也不能太大。太小了就有可能频繁发生扩容，影响效率。太大了又浪费空间，不划算。所以，16就作为一个经验值被采用了。
</callout>
### **Java 8 链表转红黑树和红黑树转链表为什么是8 和 6？**
**参考回答：**
<callout emoji="📌">
红黑树中的TreeNode是链表中的Node所占空间的2倍，虽然红黑树的查找效率为o(logN)，要优于链表的o(N)，但是当链表长度比较小的时候，即使全部遍历，时间复杂度也不会太高。故，要寻找一种时间和空间的平衡，即在链表长度达到一个阈值之后再转换为红黑树。
- 之所以是8：是因为Java的源码贡献者在进行大量实验发现，默认的随机hashcode碰撞次数的泊松分布有关，hash碰撞发生8次的概率已经降低到了0.00000006，几乎为不可能事件，如果真的碰撞发生了8次，那么这个时候说明由于元素本身和hash函数的原因（我们是可以重写hashcode方的）。此时的链表性能已经已经很差了，操作的hash碰撞的可能性非常大了，后续可能还会继续发生hash碰撞。所以，在这种极端的情况下才会把链表转换为红黑树，链表转换为红黑树也是需要消耗性能的，为了挽回性能，权衡之下，才使用红黑树，提高性能的，大部分情况下hashMap还是使用链表
- 红黑树转链表的阈值为6：主要是因为，如果也将该阈值设置8，那么当hash碰撞个数在8左右时，会反生链表和红黑树的不停相互激荡转换，白白浪费资源。中间有个差值7可以防止链表和树之间的频繁转换
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### 了解的哈希冲突解决方法有哪些？
**参考回答：**
<callout emoji="📌">
- 链接法：使用链表或其他数据结构来存储冲突的键值对，将它们链接在同一个哈希桶中。
- 开放寻址法：在哈希表中找到另一个可用的位置来存储冲突的键值对，而不是存储在链表中。常见的开放寻址方法包括线性探测、二次探测和双重散列。
- 再哈希法（Rehashing）：当发生冲突时，使用另一个哈希函数再次计算键的哈希值，直到找到一个空槽来存储键值对。
- 哈希桶扩容：当哈希冲突过多时，可以动态地扩大哈希桶的数量，重新分配键值对，以减少冲突的概率。
</callout>
### **Java 8 为什么是红黑树不是平衡树AVL？**
**参考回答：**
<callout emoji="📌">
AVL树更加严格平衡，因此可以提供更快的查找效果。但是带来的代价就是添加/删除速度更慢，因为会进行更多次数的旋转平衡操作。  红黑树在添加/删除/查找方面取了折中，表现都相对较好。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### **Java 8  为什么这里把key的hashcode取出来，然后把它右移16位，然后取异或？**
**参考回答：**
<callout emoji="📌">
因为int是4个字节，也就是32位，大概是有40亿的空间，如果哈希函数运用的比较松散，一般是很难出现哈希碰撞的。但是现实中一个长度为40亿的数组内存是放不下的并且HashMap在扩容前的数组的默认初始值为16，因此直接拿Hashcode值来用是不现实的。因此需要做一些运算。我们右移16位也即是把高位的数据右移到低位的16位，然后与自己做异或，那就是把高位和低位的数据进行混合，以此来加大低位的随机性，同时混合后的低位掺杂了高位的特征，这样高位的信息也被变相保存了下来。这么做主要是从速度，功效和质量来考虑的。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### HashMap什么时候进行扩容？
**参考回答：**
<callout emoji="📌">
HashMap进行扩容取决于以下两个元素：
- Capacity：HashMap当前长度。
- LoadFactor：负载因子，默认值0.75f。
当Map中的元素个数（包括数组，链表和红黑树中）超过了16\*0.75=12之后开始扩容。
当然内部也会有一些其他导致扩容的情况：例如java8中，当某个链表长度达到8，并且当前数组长度小于64时，也会触发扩容。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### HashMap中为什么需要扩容呢？
<callout emoji="📌">
如果一直不进行扩容的话，链表就会越来越长，这样查找的效率很低，因为链表的长度很大（当然最新版本使用了红黑树后会改进很多），扩容之后，将原来链表数组的每一个链表分成奇偶两个子链表分别挂在新链表数组的散列位置，这样就减少了每个链表的长度，增加查找效率。
</callout>
### Java8 HashMap扩容机制？
**参考回答：**
<callout emoji="📌">
hashMap默认的负载因子是0.75，即如果hashmap中的元素个数超过了总容量75%，则会触发扩容，扩容分为两个步骤：
- **第1步**是对哈希表长度的扩展（2倍）
- **第2步**是将旧哈希表中的数据放到新的哈希表中。
如我们从16扩展为32时，具体的变化如下所示：
</callout>
![代码/伪代码 @@@@ 图片展示了一段代码/伪代码内容，其中包含“n - 1”以及“hash1”“hash2”相关的字符序列，右侧有箭头指向部分字符序列，可能是对代码/伪代码中特定内容的指示。](https://feishu.cn/file/PfNWbjdH3ozpxbxGS5Zc1ORKnZg)
<callout emoji="📌">
因此元素在重新计算hash之后，因为n变为2倍，那么n-1的mask范围在高位多1bit(红色)，因此新的index就会发生这样的变化：
</callout>
![图片展示了HashMap扩容时元素索引变化的示例。原位置元素的哈希值为0101，对应索引5；扩容后容量变为32，哈希值变为10101，对应索引21。其中，16变为2 * 16，红圈标识的16即oldCap。扩容时，若原哈希值新增的bit是0，则索引不变；若为1，则索引变为原索引加oldCap，即21 = 5 + 16。该图与上文介绍的扩容时元素索引变化原理相呼应，直观呈现了扩容后元素索引的计算过程。](https://feishu.cn/file/XaVgbbCOwoXG3fxHfQSc8odLnYf)
<callout emoji="📌">
因此，我们在扩充HashMap的时候，不需要重新计算hash，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”。可以看看下图为16扩充为32的resize示意图：
</callout>
![流程图 @@@@ 图片展示了一个流程结构，图中有多个方块和箭头，方块上标有数字，从0到31，箭头指示了流程走向，部分方块被圈出，可能是关键步骤或节点。但因缺少上下文，无法明确其具体含义及与标题等的关系。](https://feishu.cn/file/HVfAbQk9ro0IQqxvpgacDfxBndf)
<callout emoji="📌">
这个设计确实非常的巧妙，既省去了重新计算hash值的时间，而且同时，由于新增的1bit是0还是1可以认为是随机的，因此resize的过程，均匀的把之前的冲突的节点分散到新的bucket了。
</callout>
**推荐学习：**
<cite doc-id="DWbWwQMjpiLDYjkclSWcCfTvnPf" file-type="wiki" title="Map: HashMap底层原理是什么？" type="doc"></cite>
### HashMap可以实现同步吗？
**参考回答：**
<callout emoji="📌">
- HashMap本身不具备同步能力的，也是并发不安全的
- HashMap可以通过下面的语句进行同步：Map m = Collections.synchronizeMap(hashMap);
- 但是以上实现方式效率较低，推荐使用效率更高的ConcurrentHashMap
</callout>
### 往Hashmap存25个元素，会扩容几次？
**参考回答：**
<callout emoji="📌">
当插入 20 个元素时，HashMap 的扩容过程如下：
**初始容量**：16
- 插入第 1 到第 12 个元素时，不需要扩容。
- 插入第 13 个元素时，达到负载因子限制，需要扩容。此时，HashMap 的容量从 16 扩容到 32。
**扩容后的容量**：32
- 插入第 14 到第 24 个元素时，不需要扩容。
- 插入第25个元素时，需要扩容第2次，此时，HashMap 的容量从 32 扩容到 64。
因此，总共会进行两次扩容。
</callout>
### HashMap 多线程操作导致死循环问题
**分析：**
HashMap本身就不建议并发情况下使用，所以HashMap在并发情况下运行会有什么问题是根本分析不完的，我个人认为也没有意义。但有的面试官就喜欢问一些典型的并发问题，比如头插尾插导致的死循环问题。所以大家还是需要了解。
**参考回答：**
<callout emoji="📌">
JDK1.7 及之前版本的 HashMap 在多线程环境下扩容操作可能存在死循环问题，这是由于当一个桶位中有多个元素需要进行扩容时，多个线程同时对链表进行操作，头插法可能会导致链表中的节点指向错误的位置，从而形成一个环形链表，进而使得查询元素的操作陷入死循环无法结束。
为了解决这个问题，JDK1.8 版本的 HashMap 采用了尾插法而不是头插法来避免链表倒置，使得插入的节点永远都是放在链表的末尾，避免了链表中的环形结构。但是还是不建议在多线程下使用 HashMap，因为多线程下使用 HashMap 还是会存在数据覆盖等问题。并发环境下，推荐使用 ConcurrentHashMap 。
</callout>
**推荐学习：**
[多线程环境下，HashMap 为什么会出现死循环？ ](https://www.cnblogs.com/javastack/p/15241043.html)
### HashMap 和 HashTable 有什么区别？
**分析：**
**参考回答：**
<callout emoji="📌">
1. HashMap 是线程不安全的，HashTable 是线程安全的；
2. 由于线程安全，所以 HashTable 的效率比不上 HashMap；
3. HashMap最多只允许一条记录的键为null，允许多条记录的值为null，而 HashTable不允许；
4. HashMap 默认初始化数组的大小为16，HashTable 为 11，前者扩容时，扩大两倍，后者扩大两倍+1
5. HashMap 需要重新计算 hash 值，而 HashTable 直接使用对象的 hashCode
</callout>
**推荐学习：**
[HashMap和HashTable到底哪不同？ ](https://www.cnblogs.com/xinzhao/p/5644175.html)
### 为啥我们重写equals方法的时候需要重写hashCode方法呢？
**参考回答：**
<callout emoji="📌">
hashmap中value的查找是通过 key 的 hashcode 来查找，通过 hashcode 计算找到对象下标（桶）地址后会用 equals 比较你传入的对象和 hashmap 中的 key 对象是否相同。
所以，如果equals判断相等的对象具有不同的哈希码，它们可能会被放置在不同的桶中，从而导致查找时无法找到预期的对象，造成数据一致性问题。
</callout>
**推荐学习：**
[为什么重写equals方法的时候必须要重写hashCode方法？](https://juejin.cn/post/7024410950841139207)
### LinkedHashMap是什么？怎么实现的？
**分析：**
![图片展示了`LinkedHashMap`结构图，包含`buckets`数组和`header`节点。`buckets`数组中部分单元格存储`Entry`对象，如`key | value | X`。`header`节点通过`after`和`before`引用与`buckets`数组中的`Entry`对象相连，形成双向链表。图中还标注了`capacity`容量，以及查询时比较次数为1、2、3的箭头指向。该图直观呈现了`LinkedHashMap`基于拉链式散列结构及额外双向链表的特性，与上文对其结构和功能的分析相呼应。](https://feishu.cn/file/XeRCbhvQ4o3XMvx5KuRc0meSn9g)
**回答：**
<callout comment-refs="c153" emoji="📌">
`LinkedHashMap` 继承自 `HashMap`，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，`LinkedHashMap` 在上面结构的基础上，将节点额外链接成了一条双向链表（节点增加了 before 和 after 引用），使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
</callout>
**推荐学习：**
[图解LinkedHashMap原理](https://www.jianshu.com/p/8f4f58b4b8ab)
### HashTable线程安全是怎么实现的？
**参考回答：**
<callout emoji="📌">
我们可以看一下部分源码，HashTable中的几乎所有公共的方法都是synchronized的，而有些方法也是在内部通过synchronized代码来实现的。所以HashTable的方法都是并发安全的，但是并发性能并不高。
（在Java中，可以使用synchronized关键字来标记一个方法或者代码块，当某个线程调用该对象的synchronized方法或者访问synchronized代码块时，这个线程便获得了该对象的锁，其他线程暂时无法访问这个方法，只有等待这个方法执行完毕或者代码块执行完毕，这个线程才会释放该对象的锁，其他线程才能执行这个方法或者代码块。）
</callout>
```Java
public synchronized V put(K key, V value) {
.....
}
public synchronized V remove(Object key) {
......
}
public synchronized V get(Object key) {
......
}
.....
```
### 什么是TreeMap？
**参考回答：**
<callout emoji="📌">
TreeMap 是一个有序的key-value集合，它是通过红黑树实现的。 TreeMap基于红黑树(Red-Black tree)实现。该映射根据其键的自然顺序进行排序，或者根据 创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。 TreeMap是线程非同步的。
</callout>
### HashMap，LinkedHashMap，TreeMap 有什么区别？
**分析：**
这种对比的问题，能基于自己的理解，回答几个点就行。没办法回答全的。这里就简单说下各自的特点。
**参考回答：**
<callout emoji="📌">
- HashMap 参考其他问题；
- LinkedHashMap 保存了记录的插入顺序，在用 Iterator 遍历时，先取到的记录肯定是先插入的；
- TreeMap 实现 SortedMap 接口，能够把它保存的记录根据键排序（默认按键值升序排序，也可以指定排序的比较器）
</callout>
**推荐学习：**
[对比分析HashMap、LinkedHashMap、TreeMap](https://www.cnblogs.com/LiaHon/p/11249560.html)
### 为什么 ConcurrentHashMap 比 HashTable 效率要高？
答：
<callout emoji="📌">
**底层数据结构：** JDK1.7 的 ConcurrentHashMap 底层采用 **分段的数组+链表** 实现，JDK1.8 采用的数据结构跟 HashMap1.8 的结构一样，数组+链表/红黑二叉树。Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 **数组+链表** 的形式，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
**实现线程安全的方式：**
- 在 JDK1.7 的时候，ConcurrentHashMap 对整个桶数组进行了分割分段(Segment，分段锁)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率。
- 到了 JDK1.8 的时候，ConcurrentHashMap 已经摒弃了 Segment 的概念，而是直接用 Node 数组+链表+红黑树的数据结构来实现，并发控制使用 synchronized 和 CAS 来操作。（JDK1.6 以后 synchronized 锁做了很多优化） 整个看起来就像是优化过且线程安全的 HashMap，虽然在 JDK1.8 中还能看到 Segment 的数据结构，但是已经简化了属性，只是为了兼容旧版本；
- **Hashtable(同一把锁)** :使用 synchronized 来保证线程安全，效率非常低下。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞或轮询状态，如使用 put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。
</callout>