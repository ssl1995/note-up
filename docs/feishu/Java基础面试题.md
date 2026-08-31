# Java基础面试题
> 来源：https://ls8sck0zrg.feishu.cn/wiki/YiR7wOX25iZPFdkSsgocoeJ1nEb
> Java 基础学习指引：[Java基础学习指引]
# **Java语言**
### **Java语言有什么特点？**
**参考回答：**
> **Java是一门面向对象的编程语言，具备封装，继承，多态，抽象四大特性。**
> - 封装特性能够提高类的易用性，减少编程过程中代码出错的风险。
> - 继承最大的好处就是能够实现代码的复用。
> - 抽象更多的是能够让程序的设计和实现分离。
> - 多态最大的好处就是提高了程序的可扩展性。
> **Java具有平台独立性和移植性：**
> - Java有一句口号：`Write once, run anywhere`，一次编写、到处运行。这也是Java的魅力所在。而实现这种特性的正是Java虚拟机JVM。已编译的Java程序可以在任何带有JVM的平台上运行。你可以在windows平台编写代码，然后拿到linux上运行。只要你在编写完代码后，将代码编译成.class文件，再把class文件打成Java包，这个jar包就可以在不同的平台上运行了。
> **Java具有稳健性：**
> - Java是一个强类型语言，它允许扩展编译时检查潜在类型不匹配问题的功能。Java要求显式的方法声明，它不支持C风格的隐式声明。这些严格的要求保证编译程序能捕捉调用错误，这就导致更可靠的程序。
> - 异常处理是Java中使得程序更稳健的另一个特征。异常是某种类似于错误的异常条件出现的信号。使用`try/catch/finally`语句，程序员可以找到出错的处理代码，这就简化了出错处理和恢复的任务。
### Java与C++区别
**参考回答：[Java基础面试题]**
> - C++支持多继承，并且有指针的概念，由程序员自己管理内存；
> - Java是单继承，可以用接口实现多继承，Java不提供指针来直接访问内存，程序内存更加安全，并且Java有JVM⾃动内存管理机制，不需要程序员⼿动释放⽆⽤内存。
### Java是如何实现跨平台的？
**参考回答：**
> Java是通过JVM（Java虚拟机）实现跨平台的。
> JVM可以理解成一个软件，不同的平台有不同的版本。我们编写的Java代码，编译后会生成.class 文件（字节码文件）。Java虚拟机就是负责将字节码文件翻译成特定平台下的机器码，通过JVM翻译成机器码之后才能运行。不同平台下编译生成的字节码是一样的，但是由JVM翻译成的机器码却不一样。
> 只要在不同平台上安装对应的JVM，就可以运行字节码文件，运行我们编写的Java程序。因此，运行Java程序必须有JVM的支持，因为编译的结果不是机器码，必须要经过JVM的翻译才能执行。
### **JVM vs JDK vs JRE**
**参考回答：**
> **JVM**
> Java 虚拟机（JVM）是运行 Java 字节码的虚拟机。JVM 有针对不同系统的特定实现（Windows，Linux，macOS），目的是使用相同的字节码，它们都会给出相同的结果。字节码和不同系统的 JVM 实现是 Java 语言“一次编译，随处可以运行”的关键所在。
> **JVM 并不是只有一种！只要满足 JVM 规范，每个公司、组织或者个人都可以开发自己的专属 JVM。** 也就是说我们平时接触到的 HotSpot VM 仅仅是是 JVM 规范的一种实现而已。
> 除了我们平时最常用的 HotSpot VM 外，还有 J9 VM、Zing VM、JRockit VM 等 JVM 。维基百科上就有常见 JVM 的对比：[Comparison of Java virtual machines](https://en.wikipedia.org/wiki/Comparison_of_Java_virtual_machines) ，感兴趣的可以去看看。并且，你可以在 [Java SE Specifications](https://docs.oracle.com/javase/specs/index.html) 上找到各个版本的 JDK 对应的 JVM 规范。
> ![图片styleType图片展示的是Oracle官网上的Java SE Specifications页面，标题为“Java Language and Virtual Machine Specifications”。页面列出了Java SE 18和Java SE 17的规范文档，包括Java语言规范和Java虚拟机规范，分别有HTML和PDF格式的下载选项，还标注了部分规范的预览特性。该图片与文档中介绍JDK和JRE的内容相关，直观呈现了不同Java SE版本对应的规范文档信息。](https://feishu.cn/file/TMWZbMAnWojERAxCNrscKXtOnNH)
> **JDK 和 JRE**
> JDK 是 Java Development Kit 缩写，它是功能齐全的 Java SDK。它拥有 JRE 所拥有的一切，还有编译器（javac）和工具（如 javadoc 和 jdb）。它能够创建和编译程序。
> JRE 是 Java 运行时环境。它是运行已编译 Java 程序所需的所有内容的集合，包括 Java 虚拟机（JVM），Java 类库，java 命令和其他的一些基础构件。但是，它不能用于创建新程序。
> 如果你只是为了运行一下 Java 程序的话，那么你只需要安装 JRE 就可以了。如果你需要进行一些 Java 编程方面的工作，那么你就需要安装 JDK 了。但是，这不是绝对的。有时，即使您不打算在计算机上进行任何 Java 开发，仍然需要安装 JDK。例如，如果要使用 JSP 部署 Web 应用程序，那么从技术上讲，您只是在应用程序服务器中运行 Java 程序。那你为什么需要 JDK 呢？因为应用程序服务器会将 JSP 转换为 Java servlet，并且需要使用 JDK 来编译 servlet。
> 最后，总结一下JDK/JRE/JVM，他们三者的关系
> **JRE = JVM + Java 核心类库**
> **JDK = JRE + Java工具 + 编译器 + 调试器**
### **什么是字节码?采用字节码的好处是什么?**
**参考回答：**
> 在 Java 中，JVM 可以理解的代码就叫做字节码（即扩展名为 `.class` 的文件），它不面向任何特定的处理器，只面向虚拟机。Java 语言通过字节码的方式，在一定程度上解决了传统解释型语言执行效率低的问题，同时又保留了解释型语言可移植的特点。所以， Java 程序运行时相对来说还是高效的（不过，和 C++，Rust，Go 等语言还是有一定差距的），而且，由于字节码并不针对一种特定的机器，因此，Java 程序无须重新编译便可在多种不同操作系统的计算机上运行。
> **Java 程序从源代码到运行的过程如下图所示：**
> ![图片展示了Java程序从源代码到运行的过程。.java文件经javac编译生成.class文件，.class文件通过解释器和JIT（即时编译）转换为机器可理解的代码，最后运行在计算机上。其中，.class文件是关键中间产物，解释器逐行解释执行，执行速度相对较慢，这与上下文提到的Java程序运行时效率及字节码不针对特定机器需重新编译才能在不同操作系统上运行的内容相呼应。](https://feishu.cn/file/Qyv6b6XAooyPMex9VSecxRHinnb)
> 我们需要格外注意的是 `.class->机器码` 这一步。在这一步 JVM 类加载器首先加载字节码文件，然后通过解释器逐行解释执行，这种方式的执行速度会相对比较慢。而且，有些方法和代码块是经常需要被调用的(也就是所谓的热点代码)，所以后面引进了 JIT（just-in-time compilation） 编译器，而 JIT 属于运行时编译。当 JIT 编译器完成第一次编译后，其会将字节码对应的机器码保存下来，下次可以直接使用。而我们知道，机器码的运行效率肯定是高于 Java 解释器的。这也解释了我们为什么经常会说 **Java 是编译与解释共存的语言** 。
> HotSpot 采用了惰性评估(Lazy Evaluation)的做法，根据二八定律，消耗大部分系统资源的只有那一小部分的代码（热点代码），而这也就是 JIT 所需要编译的部分。JVM 会根据代码每次被执行的情况收集信息并相应地做出一些优化，因此执行的次数越多，它的速度就越快。JDK 9 引入了一种新的编译模式 AOT(Ahead of Time Compilation)，它是直接将热点代码编译成机器码，这样就避免了 JIT 预热等各方面的开销。JDK 支持分层编译和 AOT 协作使用。
### **为什么不全部使用 AOT 呢？**
**参考回答：**
> AOT 可以提前编译节省启动时间，那为什么不全部使用这种编译方式呢？
> 长话短说，这和 Java 语言的动态特性有千丝万缕的联系了。举个例子，CGLIB 动态代理使用的是 ASM 技术，而这种技术大致原理是运行时直接在内存中生成并加载修改后的字节码文件也就是 `.class` 文件，如果全部使用 AOT 提前编译，也就不能使用 ASM 技术了。为了支持类似的动态特性，所以选择使用 JIT 即时编译器。
### **为什么说 Java 语言“编译与解释并存”？**
**分析：**
其实这个问题我们讲字节码的时候已经提到过，因为比较重要，所以我们这里再提一下。
我们可以将高级编程语言按照程序的执行方式分为两种：

- **编译型** ：[编译型语言](https://zh.wikipedia.org/wiki/%E7%B7%A8%E8%AD%AF%E8%AA%9E%E8%A8%80) 会通过[编译器](https://zh.wikipedia.org/wiki/%E7%B7%A8%E8%AD%AF%E5%99%A8)将源代码一次性翻译成可被该平台执行的机器码。一般情况下，编译语言的执行速度比较快，开发效率比较低。常见的编译性语言有 C、C++、Go、Rust 等等。
- **解释型** ：[解释型语言](https://zh.wikipedia.org/wiki/%E7%9B%B4%E8%AD%AF%E8%AA%9E%E8%A8%80)会通过[解释器](https://zh.wikipedia.org/wiki/%E7%9B%B4%E8%AD%AF%E5%99%A8)一句一句的将代码解释（interpret）为机器代码后再执行。解释型语言开发效率比较快，执行速度比较慢。常见的解释性语言有 Python、JavaScript、PHP 等等。

![图片展示了编译型语言源代码与解释型语言源代码的处理流程。编译型语言源代码通过编译器一次性编译完毕，生成机器可理解的代码；解释型语言源代码则通过解释器一句一句解释，同样生成机器可理解的代码。该图与上文提到的Java语言“编译与解释并存”相呼应，直观呈现了编译型和解释型语言在代码处理方式上的区别，帮助理解Java语言在执行效率上的特点。](https://feishu.cn/file/Qk9ubIxUYoVGnbxwgIjcXYnWn8f)
[即时编译](https://zh.wikipedia.org/wiki/%E5%8D%B3%E6%99%82%E7%B7%A8%E8%AD%AF)
> 在常见的HotSpot虚拟机中，为了避免解释型语言带来的执行效率低问题，采用了即时编译JIT Compile(just in time compilation)技术，将运行频率很高的字节码直接编译为机器指令执行来提高性能。
> 相关阅读：[基本功 | Java 即时编译器原理解析及实践](https://tech.meituan.com/2020/10/22/java-jit-practice-in-meituan.html)
**参考回答：**
> **为什么说 Java 语言“编译与解释并存”？**
> 这是因为 Java 语言既具有编译型语言的特征，也具有解释型语言的特征。因为 Java 程序要经过先编译，后解释两个步骤，由 Java 编写的程序需要先经过编译步骤，生成字节码（`.class` 文件），这种字节码必须由 Java 解释器来解释执行。
> 在常见的HotSpot虚拟机中，为了避免解释型语言带来的执行效率低问题，采用了[即时编译](https://zh.wikipedia.org/wiki/%E5%8D%B3%E6%99%82%E7%B7%A8%E8%AD%AF) jit compile(just in time compilation)技术，将运行频率很高的字节码直接编译为机器指令执行来提高性能。
> 这就是我们为什么经常会说 Java 是编译与解释共存的语言的原因
### **Java 和 C++ 的区别?**
**参考回答：**
> 我知道很多人没学过 C++，但是面试官就是没事喜欢拿咱们 Java 和 C++ 比呀！没办法！！！就算没学过 C++，也要记下来。
> 虽然，Java 和 C++ 都是面向对象的语言，都支持封装、继承和多态，但是，它们还是有挺多不相同的地方：
> - Java 不提供指针来直接访问内存，程序内存更加安全
> - Java 的类是单继承的，C++ 支持多重继承；虽然 Java 的类不可以多继承，但是接口可以多继承。
> - Java 有自动内存管理垃圾回收机制(GC)，不需要程序员手动释放无用内存。
> - C ++同时支持方法重载和操作符重载，但是 Java 只支持方法重载（操作符重载增加了复杂性，这与 Java 最初的设计思想不符）。
> - ......
## **基本语法**
### **注释有哪几种形式？**
**参考回答：**
> Java 中的注释有三种：
> 1. **单行注释** ：通常用于解释方法内某单行代码的作用。
> 2. **多行注释** ：通常用于解释一段代码的作用。
> 3. **文档注释** ：通常用于生成 Java 开发文档。
**推荐学习：**
用的比较多的还是单行注释和文档注释，多行注释在实际开发中使用的相对较少。

![图片展示了Java代码示例，包含文档注释和单行注释。文档注释位于类方法`isStoreByValue()`上方，以`/**`开头，以`*/`结尾，内容为该方法的描述。单行注释则在`setBeanClassLoader`方法中，以`//`开头，对代码进行简要说明。图片与上下文紧密相关，直观呈现了文档中提到的单行注释和文档注释在实际代码中的形式，帮助理解注释在Java代码中的应用。](https://feishu.cn/file/Wf1Nb8olooptwqxBYzOcXm9RnFe)
在我们编写代码的时候，如果代码量比较少，我们自己或者团队其他成员还可以很轻易地看懂代码，但是当项目结构一旦复杂起来，我们就需要用到注释了。注释并不会执行(编译器在编译代码之前会把代码中的所有注释抹掉,字节码中不保留注释)，是我们程序员写给自己看的，注释是你的代码说明书，能够帮助看代码的人快速地理清代码之间的逻辑关系。因此，在写程序的时候随手加上注释是一个非常好的习惯。
《Clean Code》这本书明确指出：
> **代码的注释不是越详细越好。实际上好的代码本身就是注释，我们要尽量规范和美化自己的代码来减少不必要的注释。**
> **若编程语言足够有表达力，就不需要注释，尽量通过代码来阐述。**
> 举个例子：
> 去掉下面复杂的注释，只需要创建一个与注释所言同一事物的函数即可
> // check to see if the employee is eligible for full benefits  
> if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))
> 应替换为
> if (employee.isEligibleForFullBenefits())
### **标识符和关键字的区别是什么？**
**参考回答：**
> 在我们编写程序的时候，需要大量地为程序、类、变量、方法等取名字，于是就有了 **标识符** 。简单来说， **标识符就是一个名字** 。
> 有一些标识符，Java 语言已经赋予了其特殊的含义，只能用于特定的地方，这些特殊的标识符就是 **关键字** 。简单来说，**关键字是被赋予特殊含义的标识**符 。比如，在我们的日常生活中，如果我们想要开一家店，则要给这个店起一个名字，起的这个“名字”就叫标识符。但是我们店的名字不能叫“警察局”，因为“警察局”这个名字已经被赋予了特殊的含义，而“警察局”就是我们日常生活中的关键字。
### **Java 语言关键字有哪些？**
**参考回答：**
<sheet sheet-id="xMNRjk" token="CaFusECOFhgt23tc1ircWLlTnre"></sheet>
> Tips：所有的关键字都是小写的，在 IDE 中会以特殊颜色显示。
> `default` 这个关键字很特殊，既属于程序控制，也属于类，方法和变量修饰符，还属于访问控制。
> - 在程序控制中，当在 `switch` 中匹配不到任何情况时，可以使用 `default` 来编写默认匹配的情况。
> - 在类，方法和变量修饰符中，从 JDK8 开始引入了默认方法，可以使用 `default` 关键字来定义一个方法的默认实现。
> - 在访问控制中，如果一个方法前没有任何修饰符，则默认会有一个修饰符 `default`，但是这个修饰符加上了就会报错。
⚠️ 注意 ：虽然 `true`, `false`, 和 `null` 看起来像关键字但实际上他们是字面值，同时你也不可以作为标识符来使用。
官方文档：[https://docs.oracle.com/javase/tutorial/java/nutsandbolts/\_keywords.html](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html)

### **自增自减运算符**
**参考回答：**
> 在写代码的过程中，常见的一种情况是需要某个整数类型变量增加 1 或减少 1，Java 提供了一种特殊的运算符，用于这种表达式，叫做自增运算符（++)和自减运算符（--）。
> ++ 和 -- 运算符可以放在变量之前，也可以放在变量之后，当运算符放在变量之前时(前缀)，先自增/减，再赋值；当运算符放在变量之后时(后缀)，先赋值，再自增/减。
> 例如，当 `b = ++a` 时，先自增（自己增加 1），再赋值（赋值给 b）；当 `b = a++` 时，先赋值(赋值给 b)，再自增（自己增加 1）。也就是，++a 输出的是 a+1 的值，a++输出的是 a 值。
> 用一句口诀就是：“符号在前就先加/减，符号在后就后加/减”。
### **移位运算符**
**参考回答：**
移位运算符是最基本的运算符之一，几乎每种编程语言都包含这一运算符。移位操作中，被操作的数据被视为二进制数，移位就是将其向左或向右移动若干位的运算。
移位运算符在各种框架以及 JDK 自身的源码中使用还是挺广泛的，`HashMap`（JDK1.8） 中的 `hash` 方法的源码就用到了移位运算符：
```Java
static final int hash(Object key) {
    int h;
    // key.hashCode()：返回散列值也就是hashcode
    // ^ ：按位异或
    // >>>:无符号右移，忽略符号位，空位都以0补齐
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }
```
在 Java 代码里使用 `<<` 、 `>>` 和`>>>`转换成的指令码运行起来会更高效些。
掌握最基本的移位运算符知识还是很有必要的，这不光可以帮助我们在代码中使用，还可以帮助我们理解源码中涉及到移位运算符的代码。
Java 中有三种移位运算符：

- `<<` :左移运算符，向左移若干位，高位丢弃，低位补零。`x << 1`,相当于 x 乘以 2(不溢出的情况下)。
- `>>` :带符号右移，向右移若干位，高位补符号位，低位丢弃。正数高位补 0,负数高位补 1。`x >> 1`,相当于 x 除以 2。
- `>>>` :无符号右移，忽略符号位，空位都以 0 补齐。
由于 `double`，`float` 在二进制中的表现比较特殊，因此不能来进行移位操作。
移位操作符实际上支持的类型只有`int`和`long`，编译器在对`short`、`byte`、`char`类型进行移位前，都会将其转换为`int`类型再操作。

**如果移位的位数超过数值所占有的位数会怎样？**
当 int 类型左移/右移位数大于等于 32 位操作时，会先求余（%）后再进行左移/右移操作。也就是说左移/右移 32 位相当于不进行移位操作（32%32=0），左移/右移 42 位相当于左移/右移 10 位（42%32=10）。当 long 类型进行左移/右移操作时，由于 long 对应的二进制是 64 位，因此求余操作的基数也变成了 64。
也就是说：`x<<42`等同于`x<<10`，`x>>42`等同于`x>>10`，`x >>>42`等同于`x >>> 10`。
**左移运算符代码示例** ：
```Java
int i = -1;
System.out.println("初始数据： " + i);
System.out.println("初始数据对应的二进制字符串： " + Integer.toBinaryString(i));
i <<= 10;
System.out.println("左移 10 位后的数据 " + i);
System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));
```
输出：
```Java
初始数据： -1
初始数据对应的二进制字符串： 11111111111111111111111111111111
左移 10 位后的数据 -1024
左移 10 位后的数据对应的二进制字符 11111111111111111111110000000000
```
由于左移位数大于等于 32 位操作时，会先求余（%）后再进行左移操作，所以下面的代码左移 42 位相当于左移 10 位（42%32=10），输出结果和前面的代码一样。
```Java
int i = -1;
System.out.println("初始数据： " + i);
System.out.println("初始数据对应的二进制字符串： " + Integer.toBinaryString(i));
i <<= 42;
System.out.println("左移 10 位后的数据 " + i);
System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));
```
右移运算符使用类似，篇幅问题，这里就不做演示了。

### **continue、break 和 return 的区别是什么？**
**参考回答：**
> 在循环结构中，当循环条件不满足或者循环次数达到要求时，循环会正常结束。但是，有时候可能需要在循环的过程中，当发生了某种条件之后 ，提前终止循环，这就需要用到下面几个关键词：
> 1. `continue` ：指跳出当前的这一次循环，继续下一次循环。
> 2. `break` ：指跳出整个循环体，继续执行循环下面的语句。
> `return` 用于跳出所在方法，结束该方法的运行。return 一般有两种用法：
> 1. `return;` ：直接使用 return 结束方法执行，用于没有返回值函数的方法
> 2. `return value;` ：return 一个特定值，用于有返回值函数的方法
> 思考一下：下列语句的运行结果是什么？
```Java
public static void main(String[] args) {
        boolean flag = false;
        for (int i = 0; i <= 3; i++) {
            if (i == 0) {
                System.out.println("0");
            } else if (i == 1) {
                System.out.println("1");
                continue;
            } else if (i == 2) {
                System.out.println("2");
                flag = true;
            } else if (i == 3) {
                System.out.println("3");
                break;
            } else if (i == 4) {
                System.out.println("4");
            }
            System.out.println("xixi");
        }
        if (flag) {
            System.out.println("haha");
            return;
        }
        System.out.println("heihei");
    }
```
运行结果：
```Java
0
xixi
1
2
xixi
3
haha
```

### final, finally, finalize 的区别
**参考回答：**
> - final 用于修饰属性、方法和类, 分别表示属性不能被重新赋值，方法不可被覆盖，类不可被继承。
> - finally 是异常处理语句结构的一部分，一般以`try-catch-finally`出现，`finally`代码块表示总是被执行。
> - finalize 是Object类的一个方法，该方法一般由垃圾回收器来调用，当我们调用`System.gc()`方法的时候，由垃圾回收器调用`finalize()`方法，回收垃圾，JVM并不保证此方法总被调用。
###  final关键字的作用是什么？
**参考回答：**
> - final 修饰的类不能被继承。
> - final 修饰的方法不能被重写。
> - final 修饰的变量叫常量，常量必须初始化，初始化之后值就不能被修改。
## **变量**
### **成员变量与局部变量的区别？**
**参考回答：**
> - **语法形式** ：从语法形式上看，成员变量是属于类的，而局部变量是在代码块或方法中定义的变量或是方法的参数；成员变量可以被 `public`,`private`,`static` 等修饰符所修饰，而局部变量不能被访问控制修饰符及 `static` 所修饰；但是，成员变量和局部变量都能被 `final` 所修饰。
> - **存储方式** ：从变量在内存中的存储方式来看,如果成员变量是使用 `static` 修饰的，那么这个成员变量是属于类的，如果没有使用 `static` 修饰，这个成员变量是属于实例的。而对象存在于堆内存，局部变量则存在于栈内存。
> - **生存时间** ：从变量在内存中的生存时间上看，成员变量是对象的一部分，它随着对象的创建而存在，而局部变量随着方法的调用而自动生成，随着方法的调用结束而消亡。
> - **默认值** ：从变量是否有默认值来看，成员变量如果没有被赋初始值，则会自动以类型的默认值而赋值（一种情况例外:被 `final` 修饰的成员变量也必须显式地赋值），而局部变量则不会自动赋值。
### **静态变量有什么作用？**
**参考回答：**
> 静态变量可以被类的所有实例共享。无论一个类创建了多少个对象，它们都共享同一份静态变量。
> 通常情况下，静态变量会被 `final` 关键字修饰成为常量。
### **字符型常量和字符串常量的区别?**
**参考回答：**
> 1. **形式** : 字符常量是单引号引起的一个字符，字符串常量是双引号引起的 0 个或若干个字符。
> 2. **含义** : 字符常量相当于一个整型值( ASCII 值),可以参加表达式运算; 字符串常量代表一个地址值(该字符串在内存中存放位置)。
> 3. **占内存大小** ： 字符常量只占 2 个字节; 字符串常量占若干个字节。
> (**注意： `char` 在 Java 中占两个字节**)
## **方法**
### **什么是方法的返回值?方法有哪几种类型？**
**参考回答：**
> **方法的返回值** 是指我们获取到的某个方法体中的代码执行后产生的结果！（前提是该方法可能产生结果）。返回值的作用是接收出结果，使得它可以用于其他的操作！
> 我们可以按照方法的返回值和参数类型将方法分为下面这几种：
> **1.无参数无返回值的方法**
> ```Java
> public void f1() {
>     //......
> }
> // 下面这个方法也没有返回值，虽然用到了 return
> public void f(int a) {
>     if (...) {
>         // 表示结束方法的执行,下方的输出语句不会执行
>         return;
>     }
>     System.out.println(a);
> }
> ```
> **2.有参数无返回值的方法**
> ```Java
> public void f2(Parameter 1, ..., Parameter n) {
>     //......
> }
> ```
> **3.有返回值无参数的方法**
> ```Java
> public int f3() {
>     //......
>     return x;
> }
> ```
> **4.有返回值有参数的方法**
> ```Java
> public int f4(int a, int b) {
>     return a * b;
> }
> ```
### **静态方法为什么不能调用非静态成员?**
**参考回答：**
> 这个需要结合 JVM 的相关知识，主要原因如下：
> 1. 静态方法是属于类的，在类加载的时候就会分配内存，可以通过类名直接访问。而非静态成员属于实例对象，只有在对象实例化之后才存在，需要通过类的实例对象去访问。
> 2. 在类的非静态成员不存在的时候静态方法就已经存在了，此时调用在内存中还不存在的非静态成员，属于非法操作。
### **静态方法和实例方法有何不同？**
**参考回答：**
**1、调用方式**
在外部调用静态方法时，可以使用 `类名.方法名` 的方式，也可以使用 `对象.方法名` 的方式，而实例方法只有后面这种方式。也就是说，**调用静态方法可以无需创建对象** 。
不过，需要注意的是一般不建议使用 `对象.方法名` 的方式来调用静态方法。这种方式非常容易造成混淆，静态方法不属于类的某个对象而是属于这个类。
因此，一般建议使用 `类名.方法名` 的方式来调用静态方法。
```Java
public class Person {
    public void method() {
      //......
    }
    public static void staicMethod(){
      //......
    }
    public static void main(String[] args) {
        Person person = new Person();
        // 调用实例方法
        person.method();
        // 调用静态方法
        Person.staicMethod()
    }
}
```

**2、访问类成员是否存在限制**
静态方法在访问本类的成员时，只允许访问静态成员（即静态成员变量和静态方法），不允许访问实例成员（即实例成员变量和实例方法），而实例方法不存在这个限制。

### **重载和重写有什么区别？**
**分析：**
- **重载**
发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。
《Java 核心技术》这本书是这样介绍重载的：
> 如果多个方法(比如 `StringBuilder` 的构造方法)有相同的名字、不同的参数， 便产生了重载。
> StringBuilder sb = new StringBuilder();  
> StringBuilder sb2 = new StringBuilder("HelloWorld");
> 编译器必须挑选出具体执行哪个方法，它通过用各个方法给出的参数类型与特定方法调用所使用的值类型进行匹配来挑选出相应的方法。 如果编译器找不到匹配的参数， 就会产生编译时错误， 因为根本不存在匹配， 或者没有一个比其他的更好(这个过程被称为重载解析(overloading resolution))。
> Java 允许重载任何方法， 而不只是构造器方法。
综上：重载就是同一个类中多个同名方法根据不同的传参来执行不同的逻辑处理。

- **重写**
重写发生在运行期，是子类对父类的允许访问的方法的实现过程进行重新编写。
1. 方法名、参数列表必须相同，子类方法返回值类型应比父类方法返回值类型更小或相等，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类。
2. 如果父类方法访问修饰符为 `private/final/static` 则子类就不能重写该方法，但是被 `static` 修饰的方法能够被再次声明。
3. 构造方法无法被重写
综上：**重写就是子类对父类方法的重新改造，外部样子不能改变，内部逻辑可以改变。**
**方法的重写要遵循“两同两小一大”**（以下内容摘录自《疯狂 Java 讲义》 ）：

- “两同”即方法名相同、形参列表相同；
- “两小”指的是子类方法返回值类型应比父类方法返回值类型更小或相等，子类方法声明抛出的异常类应比父类方法声明抛出的异常类更小或相等；
- “一大”指的是子类方法的访问权限应比父类方法的访问权限更大或相等。
⭐️ 关于 **重写的返回值类型** 这里需要额外多说明一下，上面的表述不太清晰准确：如果方法的返回类型是 void 和基本数据类型，则返回值重写时不可修改。但是如果方法的返回值是引用类型，重写时是可以返回该引用类型的子类的。
```JSON
public class Hero {
    public String name() {
        return "超级英雄";
    }
}
public class SuperMan extends Hero{
    @Override
    public String name() {
        return "超人";
    }
    public Hero hero() {
        return new Hero();
    }
}
public class SuperSuperMan extends SuperMan {
    public String name() {
        return "超级超级英雄";
    }
    @Override
    public SuperMan hero() {
        return new SuperMan();
    }
}
```

**参考回答：**
> - 重载就是同样的一个方法能够根据输入数据的不同，做出不同的处理
> - 重写就是当子类继承自父类的相同方法，输入数据一样，但要做出有别于父类的响应时，你就要覆盖父类方法
### **什么是可变长参数？**
**参考回答：**
从 Java5 开始，Java 支持定义可变长参数，所谓可变长参数就是允许在调用方法时传入不定长度的参数。就比如下面的这个 `printVariable` 方法就可以接受 0 个或者多个参数。
```Java
public static void method1(String... args) {
   //......
}
另外，可变参数只能作为函数的最后一个参数，但其前面可以有也可以没有任何其他参数。
public static void method2(String arg1, String... args) {
   //......
}
```

**遇到方法重载的情况怎么办呢？会优先匹配固定参数还是可变参数的方法呢？**
答案是会优先匹配固定参数的方法，因为固定参数的方法匹配度更高。
我们通过下面这个例子来证明一下。
```Java
public class VariableLengthArgument {
    public static void printVariable(String... args) {
        for (String s : args) {
            System.out.println(s);
        }
    }
    public static void printVariable(String arg1, String arg2) {
        System.out.println(arg1 + arg2);
    }
    public static void main(String[] args) {
        printVariable("a", "b");
        printVariable("a", "b", "c", "d");
    }
}
```
输出：
```Java
ab
a
b
c
d
```
另外，Java 的可变参数编译后实际会被转换成一个数组，我们看编译后生成的 `class`文件就可以看出来了。
```Java
public class VariableLengthArgument {
    public static void printVariable(String... args) {
        String[] var1 = args;
        int var2 = args.length;
        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            System.out.println(s);
        }
    }
    // ......
}
```

# **基本数据类型**
### **Java 中的几种基本数据类型了解么？**
**参考回答：**
> Java 中有 8 种基本数据类型，分别为：
> - 6 种数字类型：
>   - 4 种整数型：`byte`、`short`、`int`、`long`
>   - 2 种浮点型：`float`、`double`
> - 1 种字符类型：`char`
> - 1 种布尔型：`boolean`。
> 这 8 种基本数据类型的默认值以及所占空间的大小如下：
> ![图片是一张表格，展示了Java中8种基本数据类型的相关信息。表格包含基本类型、位数、字节、默认值、取值范围5列。其中，byte、short、int、long、char、float、double分别对应8、16、32、64、16、32、64位，字节分别为1、2、4、8、2、4、8；默认值分别为0、0、0、0L、'u0000'、0f、0d；取值范围依次为-128 ~ 127、-32768 ~ 32767、-2147483648 ~ 2147483647、-9223372036854775808 ~ 92233720336854775807、0 ~ 65535、1.4E-45 ~ 3.4028235E38、4.9E-324 ~ 1.7976931348623157E308。](https://feishu.cn/file/Og8UbTeSToHJvHxnnaNccHHgnCf)
> 对于 `boolean`，官方文档未明确定义，它依赖于 JVM 厂商的具体实现。逻辑上理解是占用 1 位，但是实际中会考虑计算机高效存储因素。
> 另外，Java 的每种基本类型所占存储空间的大小不会像其他大多数语言那样随机器硬件架构的变化而变化。这种所占存储空间大小的不变性是 Java 程序比用其他大多数语言编写的程序更具可移植性的原因之一（《Java 编程思想》2.2 节有提到）。
> **注意：**
> 1. Java 里使用 `long` 类型的数据一定要在数值后面加上 **L**，否则将作为整型解析。
> 2. `char a = 'h'`char :单引号，`String a = "hello"` :双引号。
> 这八种基本类型都有对应的包装类分别为：`Byte`、`Short`、`Integer`、`Long`、`Float`、`Double`、`Character`、`Boolean` 。
**推荐学习**
[你真的理解 Java 的基础数据类型吗](https://ost.51cto.com/posts/547)

### **基本类型和包装类型的区别？**
**参考回答：**
> - 成员变量包装类型不赋值就是 `null` ，而基本类型有默认值且不是 `null`。
> - 包装类型可用于泛型，而基本类型不可以。
> - 基本数据类型的局部变量存放在 Java 虚拟机栈中的局部变量表中，基本数据类型的成员变量（未被 `static` 修饰 ）存放在 Java 虚拟机的堆中。包装类型属于对象类型，我们知道几乎所有对象实例都存在于堆中。
> - 相比于对象类型， 基本数据类型占用的空间非常小。
> **为什么说是几乎所有对象实例呢？** 这是因为 HotSpot 虚拟机引入了 JIT 优化之后，会对对象进行逃逸分析，如果发现某一个对象并没有逃逸到方法外部，那么就可能通过标量替换来实现栈上分配，而避免堆上分配内存
> ⚠️ 注意 ： **基本数据类型存放在栈中是一个常见的误区！** 基本数据类型的成员变量如果没有被 `static` 修饰的话（不建议这么使用，应该要使用基本数据类型对应的包装类型），就存放在堆中。
> class BasicTypeVar{  
>   private int x;  
> }
### 为什么需要包装类？
**参考回答：**
> Java 是一种面向对象语言，很多地方都需要使用对象而不是基本数据类型。比如，在集合类中，我们是无法将 int 、double 等类型放进去的。因为集合的容器要求元素是 Object 类型。
> 为了让基本类型也具有对象的特征，就出现了包装类型。相当于将基本类型包装起来，使得它具有了对象的性质，并且为其添加了属性和方法，丰富了基本类型的操作。
### **包装类型的缓存机制了解么？**
**参考回答：**
> Java 基本数据类型的包装类型的大部分都用到了缓存机制来提升性能。
> `Byte`,`Short`,`Integer`,`Long` 这 4 种包装类默认创建了数值 **[-128，127]** 的相应类型的缓存数据，`Character` 创建了数值在 **[0,127]** 范围的缓存数据，`Boolean` 直接返回 `True` or `False`。
> **Integer 缓存源码：**
> ```Java
> public static Integer valueOf(int i) {
>     if (i >= IntegerCache.low && i <= IntegerCache.high)
>         return IntegerCache.cache[i + (-IntegerCache.low)];
>     return new Integer(i);
> }
> private static class IntegerCache {
>     static final int low = -128;
>     static final int high;
>     static {
>         // high value may be configured by property
>         int h = 127;
>     }
> }
> ```
> **`Character` 缓存源码:**
> ```Java
> public static Character valueOf(char c) {
>     if (c <= 127) { // must cache
>       return CharacterCache.cache[(int)c];
>     }
>     return new Character(c);
> }
> private static class CharacterCache {
>     private CharacterCache(){}
>     static final Character cache[] = new Character[127 + 1];
>     static {
>         for (int i = 0; i < cache.length; i++)
>             cache[i] = new Character((char)i);
>     }
> }
> ```
> **`Boolean` 缓存源码：**
> ```Java
> public static Boolean valueOf(boolean b) {
>     return (b ? TRUE : FALSE);
> }
> ```
> 如果超出对应范围仍然会去创建新的对象，缓存的范围区间的大小只是在性能和资源之间的权衡。
> 两种浮点数类型的包装类 `Float`,`Double` 并没有实现缓存机制。
> ```Java
> Integer i1 = 33;
> Integer i2 = 33;
> System.out.println(i1 == i2);// 输出 true
> Float i11 = 333f;
> Float i22 = 333f;
> System.out.println(i11 == i22);// 输出 false
> Double i3 = 1.2;
> Double i4 = 1.2;
> System.out.println(i3 == i4);// 输出 false
> ```
> 下面我们来看一下问题。下面的代码的输出结果是 `true` 还是 `false` 呢？
> ```Java
> Integer i1 = 40;
> Integer i2 = new Integer(40);
> System.out.println(i1==i2);
> ```
> `Integer i1=40` 这一行代码会发生装箱，也就是说这行代码等价于 `Integer i1=Integer.valueOf(40)` 。因此，`i1` 直接使用的是缓存中的对象。而`Integer i2 = new Integer(40)` 会直接创建新的对象。
> 因此，答案是 `false` 。你答对了吗？
> 记住：**所有整型包装类对象之间值的比较，全部使用 equals 方法比较**。
> ![图片展示了Java中整型包装类对象值比较的强制规则。说明对于Integer var = ?在-128至127之间的赋值，Integer对象会在IntegerCache.cache产生，会复用已有对象，这个区间内的Integer值可直接使用==进行判断；但区间之外的数据会在堆上产生，不会复用已有对象，推荐使用equals方法进行判断。该图片与上文提到的包装类缓存机制相关，是对缓存机制中值比较规则的补充说明。](https://feishu.cn/file/XiE0b6ulDoXdeNxhT3QcLU58nr7)
**推荐学习：**
[面试官：你知道包装类的缓存机制吗？](https://mp.weixin.qq.com/s/vVwD3S-OdkhZBjx8PwIxbw)

### Integer和 int 有什么区别？
**参考回答：**
> int是Java中的原始数据类型，而Integer是int的包装类。Integer和 int 的区别：
> - 基本类型和引用类型：首先，int是一种基本数据类型，而Integer是一种引用类型。基本数据类型是Java中最基本的数据类型，它们是预定义的，不需要实例化就可以使用。而引用类型则需要通过实例化对象来使用。这意味着，使用int来存储一个整数时，不需要任何额外的内存分配，而使用Integer时，必须为对象分配内存。在性能方面，基本数据类型的操作通常比相应的引用类型快。
> - 自动装箱和拆箱：其次，Integer作为int的包装类，它可以实现自动装箱和拆箱。自动装箱是指将基本类型转化为相应的包装类类型，而自动拆箱则是将包装类类型转化为相应的基本类型。这使得Java程序员更加方便地进行数据类型转换。例如，当我们需要将int类型的值赋给Integer变量时，Java可以自动地将int类型转换为Integer类型。同样地，当我们需要将Integer类型的值赋给int变量时，Java可以自动地将Integer类型转换为int类型。
> - 空指针异常：另外，int变量可以直接赋值为0，而Integer变量必须通过实例化对象来赋值。如果对一个未经初始化的Integer变量进行操作，就会出现空指针异常。这是因为它被赋予了null值，而null值是无法进行自动拆箱的。
**推荐学习：**
[int和Integer有什么区别？](https://learn.lianglianglee.com/%e4%b8%93%e6%a0%8f/Java%20%e6%a0%b8%e5%bf%83%e6%8a%80%e6%9c%af%e9%9d%a2%e8%af%95%e7%b2%be%e8%ae%b2/07%20%20int%e5%92%8cInteger%e6%9c%89%e4%bb%80%e4%b9%88%e5%8c%ba%e5%88%ab%ef%bc%9f-%e6%9e%81%e5%ae%a2%e6%97%b6%e9%97%b4.md)

### **为什么还要保留int类型？**
**参考回答：**
> 包装类是引用类型，对象的引用和对象本身是分开存储的，而对于基本类型数据，变量对应的内存块直接存储数据本身。
> 因此，基本类型数据在读写效率方面，要比包装类高效。除此之外，在64位JVM上，在开启引用压缩的情况下，一个Integer对象占用16个字节的内存空间，而一个int类型数据只占用4字节的内存空间，前者对空间的占用是后者的4倍。
> 也就是说，不管是读写效率，还是存储效率，基本类型都比包装类高效。
### **自动装箱与拆箱了解吗？原理是什么？**
**参考回答：**
> **什么是自动拆装箱？**
> - **装箱**：将基本类型用它们对应的引用类型包装起来；
> - **拆箱**：将包装类型转换为基本数据类型；
> 举例：
> ```Java
> Integer i = 10;  //装箱
> int n = i;   //拆箱
> ```
> 上面这两行代码对应的字节码为：
> ```Java
>    L1
>     LINENUMBER 8 L1
>     ALOAD 0
>     BIPUSH 10
>     INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
>     PUTFIELD AutoBoxTest.i : Ljava/lang/Integer;
>    L2
>     LINENUMBER 9 L2
>     ALOAD 0
>     ALOAD 0
>     GETFIELD AutoBoxTest.i : Ljava/lang/Integer;
>     INVOKEVIRTUAL java/lang/Integer.intValue ()I
>     PUTFIELD AutoBoxTest.n : I
>     RETURN
> ```
> 从字节码中，我们发现装箱其实就是调用了 包装类的`valueOf()`方法，拆箱其实就是调用了 `xxxValue()`方法。
> 因此，
> - `Integer i = 10` 等价于 `Integer i = Integer.valueOf(10)`
> - `int n = i` 等价于 `int n = i.intValue()`;
> 注意：**如果频繁拆装箱的话，也会严重影响系统的性能。我们应该尽量避免不必要的拆装箱操作。**
> ```Java
> private static long sum() {
>     // 应该使用 long 而不是 Long
>     Long sum = 0L;
>     for (long i = 0; i <= Integer.MAX_VALUE; i++)
>         sum += i;
>     return sum;
> }
> ```
**推荐学习**
[自动装箱和拆箱 | JAVA8 官网笔记教程](https://zq99299.github.io/java-tutorial/java/data/autoboxing.html)

### **为什么浮点数运算的时候会有精度丢失的风险？**
**参考回答：**
> 浮点数运算精度丢失代码演示：
> float a = 2.0f - 1.9f;  
> float b = 1.8f - 1.7f;  
> System.out.println(a);// 0.100000024  
> System.out.println(b);// 0.099999905  
> System.out.println(a == b);// false
> 为什么会出现这个问题呢？
> 这个和计算机保存浮点数的机制有很大关系。我们知道计算机是二进制的，而且计算机在表示一个数字时，宽度是有限的，无限循环的小数存储在计算机时，只能被截断，所以就会导致小数精度发生损失的情况。这也就是解释了为什么浮点数没有办法用二进制精确表示。
> 就比如说十进制下的 0.2 就没办法精确转换成二进制小数：
> // 0.2 转换为二进制数的过程为，不断乘以 2，直到不存在小数为止，  
> // 在这个计算过程中，得到的整数部分从上到下排列就是二进制的结果。  
> 0.2 \* 2 = 0.4 -> 0  
> 0.4 \* 2 = 0.8 -> 0  
> 0.8 \* 2 = 1.6 -> 1  
> 0.6 \* 2 = 1.2 -> 1  
> 0.2 \* 2 = 0.4 -> 0（发生循环）  
> ...
**推荐学习：**
[为什么 0.1 + 0.2 不等于 0.3 ？](https://xiaolincoding.com/os/1_hardware/float.html)

### **如何解决浮点数运算的精度丢失问题？**
**参考回答：**
> `BigDecimal` 可以实现对浮点数的运算，不会造成精度丢失。通常情况下，大部分需要浮点数精确运算结果的业务场景（比如涉及到钱的场景）都是通过 `BigDecimal` 来做的。
> BigDecimal a = new BigDecimal("1.0");  
> BigDecimal b = new BigDecimal("0.9");  
> BigDecimal c = new BigDecimal("0.8");  
> BigDecimal x = a.subtract(b);  
> BigDecimal y = b.subtract(c);  
> System.out.println(x); /\* 0.1 */  
> System.out.println(y); /* 0.1 */  
> System.out.println(Objects.equals(x, y)); /* true \*/
### **超过 long 整型的数据应该如何表示？**
**参考回答：**
> 基本数值类型都有一个表达范围，如果超过这个范围就会有数值溢出的风险。
> 在 Java 中，64 位 long 整型是最大的整数类型。
> long l = Long.MAX_VALUE;  
> System.out.println(l + 1); // -9223372036854775808  
> System.out.println(l + 1 == Long.MIN_VALUE); // true
> `BigInteger` 内部使用 `int[]` 数组来存储任意大小的整形数据。
> 相对于常规整数类型的运算来说，`BigInteger` 运算的效率会相对较低。
# 面向对象基础
### 面向对象和面向过程的区别
**参考回答：**
> 两者的主要区别在于解决问题的方式不同：
> - 面向过程把解决问题的过程拆成一个个方法，通过一个个方法的执行解决问题。
> - 面向对象会先抽象出对象，然后用对象执行方法的方式解决问题。
> 另外，面向对象开发的程序一般更易维护、易复用、易扩展。
### 创建一个对象用什么运算符?对象实体与对象引用有何不同?
**参考回答：**
> new 运算符，new 创建对象实例（对象实例在堆内存中），对象引用指向对象实例（对象引用一般存放在栈或堆内存中）。
> 一个对象引用可以指向 0 个或 1 个对象（一根绳子可以不系气球，也可以系一个气球）;一个对象可以有 n 个引用指向它（可以用 n 条绳子系住一个气球）。
### 对象的相等和引用相等的区别
**参考回答：**
> - 对象的相等一般比较的是内存中存放的内容是否相等。
> - 引用相等一般比较的是他们指向的内存地址是否相等。
### 类的构造方法的作用是什么
**参考回答：**
> 构造方法是一种特殊的方法，主要作用是完成对象的初始化工作。
### 如果一个类没有声明构造方法，该程序能正确执行吗?
**参考回答：**
> 如果一个类没有声明构造方法，也可以执行！因为一个类即使没有声明构造方法也会有默认的不带参数的构造方法。如果我们自己添加了类的构造方法（无论是否有参），Java 就不会再添加默认的无参数的构造方法了，我们一直在不知不觉地使用构造方法，这也是为什么我们在创建对象的时候后面要加一个括号（因为要调用无参的构造方法）。如果我们重载了有参的构造方法，记得都要把无参的构造方法也写出来（无论是否用到），因为这可以帮助我们在创建对象的时候少踩坑。
### 构造方法有哪些特点？是否可被 override?
**参考回答：**
> 构造方法特点如下：
> - 名字与类名相同。
> - 没有返回值，但不能用 void 声明构造函数。
> - 生成类的对象时自动执行，无需调用。
> 构造方法不能被 override（重写）,但是可以 overload（重载）,所以你可以看到一个类中有多个构造函数的情况。
### 面向对象三大特征
**参考回答：**
> **封装**
> 封装是指把一个对象的状态信息（也就是属性）隐藏在对象内部，不允许外部对象直接访问对象的内部信息。但是可以提供一些可以被外界访问的方法来操作属性。就好像我们看不到挂在墙上的空调的内部的零件信息（也就是属性），但是可以通过遥控器（方法）来控制空调。如果属性不想被外界访问，我们大可不必提供方法给外界访问。但是如果一个类没有提供给外界访问的方法，那么这个类也没有什么意义了。就好像如果没有空调遥控器，那么我们就无法操控空凋制冷，空调本身就没有意义了（当然现在还有很多其他方法 ，这里只是为了举例子）。
> ```Java
> public class Student {
>     private int id;//id属性私有化
>     private String name;//name属性私有化
>     // 获取id的方法
>     public int getId() {
>         return id;
>     }
>     //设置id的方法
>     public void setId(int id) {
>         this.id = id;
>     }
>     //获取name的方法
>     public String getName() {
>         return name;
>     }
>     //设置name的方法
>     public void setName(String name) {
>         this.name = name;
>     }
> }
> ```
> **继承**
> 不同类型的对象，相互之间经常有一定数量的共同点。例如，小明同学、小红同学、小李同学，都共享学生的特性（班级、学号等）。同时，每一个对象还定义了额外的特性使得他们与众不同。例如小明的数学比较好，小红的性格惹人喜爱；小李的力气比较大。继承是使用已存在的类的定义作为基础建立新类的技术，新类的定义可以增加新的数据或新的功能，也可以用父类的功能，但不能选择性地继承父类。通过使用继承，可以快速地创建新的类，可以提高代码的重用，程序的可维护性，节省大量创建新类的时间 ，提高我们的开发效率。
> **关于继承如下 3 点请记住：**
> 1. 子类拥有父类对象所有的属性和方法（包括私有属性和私有方法），但是父类中的私有属性和方法子类是无法访问，**只是拥有**。
> 2. 子类可以拥有自己属性和方法，即子类可以对父类进行扩展。
> 3. 子类可以用自己的方式实现父类的方法。（以后介绍）。
> **多态**
> 多态，顾名思义，表示一个对象具有多种的状态，具体表现为父类的引用指向子类的实例。
> **多态的特点:**
> - 对象类型和引用类型之间具有继承（类）/实现（接口）的关系；
> - 引用类型变量发出的方法调用的到底是哪个类中的方法，必须在程序运行期间才能确定；
> - 多态不能调用“只在子类存在但在父类不存在”的方法；
> - 如果子类重写了父类的方法，真正执行的是子类覆盖的方法，如果子类没有覆盖父类的方法，执行的是父类的方法。
**推荐学习：**
[面向对象的三大基本特征和五大基本原则](https://segmentfault.com/a/1190000021898422)

### Java支持多继承吗？
**参考回答：**
> Java中，**类不支持**多继承，**接口才支持**多继承。
> 接口的作用是拓展对象功能。当一个子接口继承了多个父接口时，说明子接口拓展了多个功能。当一个类实现该接口时，就拓展了多个的功能。
> Java不支持多继承的原因：
> - 出于安全性的考虑，如果子类继承的多个父类里面有相同的方法或者属性，子类将不知道具体要继承哪个。
> - Java提供了接口和内部类以达到实现多继承功能，弥补单继承的缺陷。
### 什么是重写和重载
**分析**
概念性问题，按照自己的话说出来就好

**参考回答：**
> 方法重载(Overloading)是一个类中定义了多个方法名相同，而他们的参数的数量不同或数量相同而类型和次序不同，则称为方法的重载
> 方法重写(Overriding)是在子类存在方法与父类的方法的名字相同，而且参数的个数与类型一样，返回值也一样的方法,就称为重写
**推荐阅读**
[Java 重写(Override)与重载(Overload) | 菜鸟教程](https://www.runoob.com/java/java-override-overload.html)

### 接口和抽象类有什么共同点和区别？
**参考回答：**
> **共同点** ：
> - 都不能被实例化。
> - 都可以包含抽象方法。
> - 都可以有默认实现的方法（Java 8 可以用 `default` 关键字在接口中定义默认方法）。
> **区别** ：
> - 接口主要用于对类的行为进行约束，你实现了某个接口就具有了对应的行为。抽象类主要用于代码复用，强调的是所属关系。
> - 一个类只能继承一个类，但是可以实现多个接口。
> - 接口中的成员变量只能是 `public static final` 类型的，不能被修改且必须有初始值，而抽象类的成员变量默认 default，可在子类中被重新定义，也可被重新赋值。
### 深拷贝和浅拷贝区别了解吗？什么是引用拷贝？
**参考回答：**
> 关于深拷贝和浅拷贝区别，我这里先给结论：
> - **浅拷贝**：浅拷贝会在堆上创建一个新的对象（区别于引用拷贝的一点），不过，如果原对象内部的属性是引用类型的话，浅拷贝会直接复制内部对象的引用地址，也就是说拷贝对象和原对象共用同一个内部对象。
> - **深拷贝** ：深拷贝会完全复制整个对象，包括这个对象所包含的内部对象。
> 上面的结论没有完全理解的话也没关系，我们来看一个具体的案例！
> **浅拷贝**
> 浅拷贝的示例代码如下，我们这里实现了 `Cloneable` 接口，并重写了 `clone()` 方法。
> `clone()` 方法的实现很简单，直接调用的是父类 `Object` 的 `clone()` 方法。
> ```Java
> public class Address implements Cloneable {
>     private String name;
>     // 省略构造函数、Getter&Setter方法
>     @Override
>     public Address clone() {
>         try {
>             return (Address) super.clone();
>         } catch (CloneNotSupportedException e) {
>             throw new AssertionError();
>         }
>     }
> }
> public class Person implements Cloneable {
>     private Address address;
>     // 省略构造函数、Getter&Setter方法
>     @Override
>     public Person clone() {
>         try {
>             Person person = (Person) super.clone();
>             return person;
>         } catch (CloneNotSupportedException e) {
>             throw new AssertionError();
>         }
>     }
> }
> ```
> 测试 ：
> ```Java
> Person person1 = new Person(new Address("武汉"));
> Person person1Copy = person1.clone();
> // true
> System.out.println(person1.getAddress() == person1Copy.getAddress());
> ```
> 从输出结构就可以看出， `person1` 的克隆对象和 `person1` 使用的仍然是同一个 `Address` 对象。
> **深拷贝**
> 这里我们简单对 `Person` 类的 `clone()` 方法进行修改，连带着要把 `Person` 对象内部的 `Address` 对象一起复制。
> ```Java
> @Override
> public Person clone() {
>     try {
>         Person person = (Person) super.clone();
>         person.setAddress(person.getAddress().clone());
>         return person;
>     } catch (CloneNotSupportedException e) {
>         throw new AssertionError();
>     }
> }
> ```
> 测试 ：
> ```Java
> Person person1 = new Person(new Address("武汉"));
> Person person1Copy = person1.clone();
> // false
> System.out.println(person1.getAddress() == person1Copy.getAddress());
> ```
> 从输出结构就可以看出，虽然 `person1` 的克隆对象和 `person1` 包含的 `Address` 对象已经是不同的了。
> **那什么是引用拷贝呢？** 简单来说，引用拷贝就是两个不同的引用指向同一个对象。
> 我专门画了一张图来描述浅拷贝、深拷贝、引用拷贝：
> ![图片展示了引用拷贝、浅拷贝、深拷贝三种对象复制方式的内存结构。引用拷贝中，person1和person1Copy指向同一堆中的Person对象，Address对象也相同；浅拷贝时，person1和person1Copy指向同一堆中的Person对象，但各自有独立的Address对象；深拷贝时，person1和person1Copy各自指向堆中独立的Person对象，且各自有独立的Address对象。此图直观呈现了三种拷贝方式下对象在内存中的状态，与上下文对深拷贝、浅拷贝及引用拷贝的解释相呼应。](https://feishu.cn/file/ABkPbvd1MoDUGCxKgqbchc7FnAd)
### 向上转型，向下转型
**分析**
父类引用指向子类对象为向上转型，向上转型就是把子类对象直接赋给父类引用，不用强制转换。使用向上转型可以调用父类类型中的所有成员，不能调用子类类型中特有成员，最终运行效果看子类的具体实现
子类引用指向父类实例为向下转型，向下转型可以调用子类类型中所有的成员，不过需要注意的是如果父类引用指向的是子类对象，那么在向上转型的过程中是安全的，也就是编译是不会出错误。但是如果子类引用的是父类对象，那么在向下转型的过程中是不安全的，编译不会出错，但是运行时会出现我们开始提到的 Java 强制类型转换异常，一般使用 instanceof 运算符来避免出此类错误。

**参考回答：**
> 父类引用指向子类实例为向上转型，子类引用指向父类实例为向下转型
> 向上转型不用强转，但有类型丢失问题，向下转型要强转，但是有安全问题
**推荐学习**
[Java对象类型转换:向上转型和向下转型](http://c.biancheng.net/view/6503.html)

### Java 中是值传递还是引用传递，还是两者共存？
**分析**
直接说结论：JAVA传递的只有值

**参考回答：**
> JAVA传递的只有值
**推荐阅读**
[死磕面试系列，Java到底是值传递还是引用传递? ](https://heapdump.cn/article/4859141)

# Java 常见类
## Object
### Object 类的常见方法有哪些？
**参考回答：**
> getClass，clone，hashcode，equals，notify，notifyAll，wait，finalize，tostring
> 常用的
> hashCode 方法的作用是获取哈希码
> equals 方法的作用就是判断两个对象是否相等
> getClass 返回一个对象运行时的实例类
> tostring 返回该对象的字符串表示
```Java
/**
 * native 方法，用于返回当前运行时对象的 Class 对象，使用了 final 关键字修饰，故不允许子类重写。
 */
public final native Class<?> getClass()
/**
 * native 方法，用于返回对象的哈希码，主要使用在哈希表中，比如 JDK 中的HashMap。
 */
public native int hashCode()
/**
 * 用于比较 2 个对象的内存地址是否相等，String 类对该方法进行了重写以用于比较字符串的值是否相等。
 */
public boolean equals(Object obj)
/**
 * native 方法，用于创建并返回当前对象的一份拷贝。
 */
protected native Object clone() throws CloneNotSupportedException
/**
 * 返回类的名字实例的哈希码的 16 进制的字符串。建议 Object 所有的子类都重写这个方法。
 */
public String toString()
/**
 * native 方法，并且不能重写。唤醒一个在此对象监视器上等待的线程(监视器相当于就是锁的概念)。如果有多个线程在等待只会任意唤醒一个。
 */
public final native void notify()
/**
 * native 方法，并且不能重写。跟 notify 一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。
 */
public final native void notifyAll()
/**
 * native方法，并且不能重写。暂停线程的执行。注意：sleep 方法没有释放锁，而 wait 方法释放了锁 ，timeout 是等待时间。
 */
public final native void wait(long timeout) throws InterruptedException
/**
 * 多了 nanos 参数，这个参数表示额外时间（以纳秒为单位，范围是 0-999999）。 所以超时的时间还需要加上 nanos 纳秒。。
 */
public final void wait(long timeout, int nanos) throws InterruptedException
/**
 * 跟之前的2个wait方法一样，只不过该方法一直等待，没有超时时间这个概念
 */
public final void wait() throws InterruptedException
/**
 * 实例被垃圾回收器回收的时候触发的操作
 */
protected void finalize() throws Throwable { }
```

**推荐阅读**
[Java Object 类 | 菜鸟教程](https://www.runoob.com/java/java-object-class.html)

### == 和 equals() 的区别
**参考回答：**
> **`==`** 对于基本类型和引用类型的作用效果是不同的：
> - 对于基本数据类型来说，`==` 比较的是值。
> - 对于引用数据类型来说，`==` 比较的是对象的内存地址。
> 因为 Java 只有值传递，所以，对于 == 来说，不管是比较基本数据类型，还是引用数据类型的变量，其本质比较的都是值，只是引用类型变量存的值是对象的地址。
> **`equals()`** 不能用于判断基本数据类型的变量，只能用来判断两个对象是否相等。`equals()`方法存在于`Object`类中，而`Object`类是所有类的直接或间接父类，因此所有的类都有`equals()`方法。
> `Object` 类 `equals()` 方法：
> ```Java
> public boolean equals(Object obj) {
>      return (this == obj);
> }
> ```
> `equals()` 方法存在两种使用情况：
> - **类没有重写 `equals()`方法** ：通过`equals()`比较该类的两个对象时，等价于通过“==”比较这两个对象，使用的默认是 `Object`类`equals()`方法。
> - **类重写了 `equals()`方法** ：一般我们都重写 `equals()`方法来比较两个对象中的属性是否相等；若它们的属性相等，则返回 true(即，认为这两个对象相等)。
> 举个例子（这里只是为了举例。实际上，你按照下面这种写法的话，像 IDEA 这种比较智能的 IDE 都会提示你将 `==` 换成 `equals()` ）：
> ```Java
> String a = new String("ab"); // a 为一个引用
> String b = new String("ab"); // b为另一个引用,对象的内容一样
> String aa = "ab"; // 放在常量池中
> String bb = "ab"; // 从常量池中查找
> System.out.println(aa == bb);// true
> System.out.println(a == b);// false
> System.out.println(a.equals(b));// true
> System.out.println(42 == 42.0);// true
> ```
> `String` 中的 `equals` 方法是被重写过的，因为 `Object` 的 `equals` 方法是比较的对象的内存地址，而 `String` 的 `equals` 方法比较的是对象的值。
> 当创建 `String` 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 `String` 对象。
> `String`类`equals()`方法：
> ```Java
> public boolean equals(Object anObject) {
>     if (this == anObject) {
>         return true;
>     }
>     if (anObject instanceof String) {
>         String anotherString = (String) anObject;
>         int n = value.length;
>         if (n == anotherString.value.length) {
>             char v1[] = value;
>             char v2[] = anotherString.value;
>             int i = 0;
>             while (n-- != 0) {
>                 if (v1[i] != v2[i]) return false;
>                 i++;
>             }
>             return true;
>         }
>     }
>     return false;
> }
> ```
**推荐阅读**
[== 和 equals 的区别是什么_晚安丶的博客-CSDN博客](https://blog.csdn.net/william_munch/article/details/115373117)

### hashCode() 有什么用？
**参考回答：**
> `hashCode()` 的作用是获取哈希码（`int` 整数），也称为散列码。这个哈希码的作用是确定该对象在哈希表中的索引位置。
> `hashCode()`定义在 JDK 的 `Object` 类中，这就意味着 Java 中的任何类都包含有 `hashCode()` 函数。另外需要注意的是： `Object` 的 `hashCode()` 方法是本地方法，也就是用 C 语言或 C++ 实现的，该方法通常用来将对象的内存地址转换为整数之后返回。
> ```Java
> public native int hashCode();
> ```
> 散列表存储的是键值对(key-value)，它的特点是：**能根据“键”快速的检索出对应的“值”。这其中就利用到了散列码！（可以快速找到所需要的对象）**
### 为什么要有 hashCode？
**参考回答：**
> 其实， `hashCode()` 和 `equals()`都是用于比较两个对象是否相等。
> **那为什么 JDK 还要同时提供这两个方法呢？**
> 这是因为在一些容器（比如 `HashMap`、`HashSet`）中，有了 `hashCode()` 之后，判断元素是否在对应容器中的效率会更高（参考添加元素进`HashSet`的过程）！
> 我们在前面也提到了添加元素进`HashSet`的过程，如果 `HashSet` 在对比的时候，同样的 `hashCode` 有多个对象，它会继续使用 `equals()` 来判断是否真的相同。也就是说 `hashCode` 帮助我们大大缩小了查找成本。
> **那为什么不只提供 `hashCode()` 方法呢？**
> 这是因为两个对象的`hashCode` 值相等并不代表两个对象就相等。
> **那为什么两个对象有相同的 `hashCode` 值，它们也不一定是相等的？**
> 因为 `hashCode()` 所使用的哈希算法也许刚好会让多个对象传回相同的哈希值。越糟糕的哈希算法越容易碰撞，但这也与数据值域分布的特性有关（所谓哈希碰撞也就是指的是不同的对象得到相同的 `hashCode` )。
> 总结下来就是 ：
> - 如果两个对象的`hashCode` 值相等，那这两个对象不一定相等（哈希碰撞）。
> - 如果两个对象的`hashCode` 值相等并且`equals()`方法也返回 `true`，我们才认为这两个对象相等。
> - 如果两个对象的`hashCode` 值不相等，我们就可以直接认为这两个对象不相等。
> 相信大家看了我前面对 `hashCode()` 和 `equals()` 的介绍之后，下面这个问题已经难不倒你们了。
### 为什么重写 equals() 时必须重写 hashCode() 方法？
**参考回答：**
> 因为两个相等的对象的 `hashCode` 值必须是相等。也就是说如果 `equals` 方法判断两个对象是相等的，那这两个对象的 `hashCode` 值也要相等。
> 如果重写 `equals()` 时没有重写 `hashCode()` 方法的话就可能会导致 `equals` 方法判断是相等的两个对象，`hashCode` 值却不相等。
> **思考** ：重写 `equals()` 时没有重写 `hashCode()` 方法的话，使用 `HashMap` 等可能会出现什么问题。
> **总结** ：
> - `equals` 方法判断两个对象是相等的，那这两个对象的 `hashCode` 值也要相等。
> - 两个对象有相同的 `hashCode` 值，他们也不一定是相等的（哈希碰撞）。
> 更多关于 `hashCode()` 和 `equals()` 的内容可以查看：
**推荐学习：**
[Java hashCode() 和 equals()的若干问题解答](https://www.cnblogs.com/skywang12345/p/3324958.html)

## String
### String、StringBuffer、StringBuilder 的区别？
**参考回答：**
> **可变性**
> `String` 是不可变的（后面会详细分析原因）。
> `StringBuilder` 与 `StringBuffer` 都继承自 `AbstractStringBuilder` 类，在 `AbstractStringBuilder` 中也是使用字符数组保存字符串，不过没有使用 `final` 和 `private` 关键字修饰，最关键的是这个 `AbstractStringBuilder` 类还提供了很多修改字符串的方法比如 `append` 方法。
> ```Java
> abstract class AbstractStringBuilder implements Appendable, CharSequence {
>     char[] value;
>     public AbstractStringBuilder append(String str) {
>         if (str == null) return appendNull();
>         int len = str.length();
>         ensureCapacityInternal(count + len);
>         str.getChars(0, len, value, count);
>         count += len;
>         return this;
>     }
>     //...
> }
> ```
> **线程安全性**
> `String` 中的对象是不可变的，也就可以理解为常量，线程安全。`AbstractStringBuilder` 是 `StringBuilder` 与 `StringBuffer` 的公共父类，定义了一些字符串的基本操作，如 `expandCapacity`、`append`、`insert`、`indexOf` 等公共方法。`StringBuffer` 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。`StringBuilder` 并没有对方法进行加同步锁，所以是非线程安全的。
> **性能**
> 每次对 `String` 类型进行改变的时候，都会生成一个新的 `String` 对象，然后将指针指向新的 `String` 对象。`StringBuffer` 每次都会对 `StringBuffer` 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 `StringBuilder` 相比使用 `StringBuffer` 仅能获得 10%\~15% 左右的性能提升，但却要冒多线程不安全的风险。
> **对于三者使用的总结：**
> 1. 操作少量的数据: 适用 `String`
> 2. 单线程操作字符串缓冲区下操作大量数据: 适用 `StringBuilder`
> 3. 多线程操作字符串缓冲区下操作大量数据: 适用 `StringBuffer`
**推荐阅读**
[深入理解String、StringBuffer和StringBuilder类的区别](https://cloud.tencent.com/developer/article/1414756)

### String 为什么是不可变的?
**分析：**
`String` 类中使用 `final` 关键字修饰字符数组来保存字符串，~~所以`String` 对象是不可变的。~~
```Java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    private final char value[];
    //...
}
```
> 🐛 修正 ： 我们知道被 `final` 关键字修饰的类不能被继承，修饰的方法不能被重写，修饰的变量是基本数据类型则值不能改变，修饰的变量是引用类型则不能再指向其他对象。因此，`final` 关键字修饰的数组保存字符串并不是 `String` 不可变的根本原因，因为这个数组保存的字符串是可变的（`final` 修饰引用类型变量的情况）。
> `String` 真正不可变有下面几点原因：
> 1. 保存字符串的数组被 `final` 修饰且为私有的，并且`String` 类没有提供/暴露修改这个字符串的方法。
> 2. `String` 类被 `final` 修饰导致其不能被继承，进而避免了子类破坏 `String` 不可变。
> 相关阅读：[如何理解 String 类型值的不可变？ - 知乎提问open in new window](https://www.zhihu.com/question/20618891/answer/114125846)
> 补充（来自[issue 675open in new window](https://github.com/Snailclimb/JavaGuide/issues/675)）：在 Java 9 之后，`String` 、`StringBuilder` 与 `StringBuffer` 的实现改用 `byte` 数组存储字符串。
> ```Java
> public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
>     // @Stable 注解表示变量最多被修改一次，称为“稳定的”。
>     @Stable
>     private final byte[] value;
> }
> abstract class AbstractStringBuilder implements Appendable, CharSequence {
>     byte[] value;
> }
> ```
> **Java 9 为何要将 `String` 的底层实现由 `char[]` 改成了 `byte[]` ?**
> 新版的 String 其实支持两个编码方案： Latin-1 和 UTF-16。如果字符串中包含的汉字没有超过 Latin-1 可表示范围内的字符，那就会使用 Latin-1 作为编码方案。Latin-1 编码方案下，`byte` 占一个字节(8 位)，`char` 占用 2 个字节（16），`byte` 相较 `char` 节省一半的内存空间。
> JDK 官方就说了绝大部分字符串对象只包含 Latin-1 可表示的字符。
> ![图片为一段关于String类的动机说明。指出当前String类字符存储在char数组中，每个字符占用两个字节。大量应用数据表明字符串是堆使用的主要组成部分，且大多数字符串对象仅包含Latin - 1字符。此类字符仅需一个字节存储，因此此类String对象内部char数组一半空间未被使用。图片与上下文紧密相关，是对String类不可变特性的背景介绍。](https://feishu.cn/file/CqyybAJVEogONLxc4sIcj1j0ng2)
> 如果字符串中包含的汉字超过 Latin-1 可表示范围内的字符，`byte` 和 `char` 所占用的空间是一样的。
> 这是官方的介绍：https://openjdk.java.net/jeps/254 。
**参考回答：**
> **String如何实现不可变的？**
> 1. 保存字符串的数组被 final 修饰且为私有的，并且String 类没有提供/暴露修改这个字符串的方法。
> 2. String 类被 final 修饰导致其不能被继承，进而避免了子类破坏 String 不可变。
### 字符串拼接用“+” 还是 StringBuilder?
**参考回答：**
> Java 语言本身并不支持运算符重载，“+”和“+=”是专门为 String 类重载过的运算符，也是 Java 中仅有的两个重载过的运算符。
> ```Java
> String str1 = "he";
> String str2 = "llo";
> String str3 = "world";
> String str4 = str1 + str2 + str3;
> ```
> 上面的代码对应的字节码如下：
> ![图片展示了Java中使用“+”进行字符串拼接的字节码代码。其中，第9行创建了StringBuilder对象，第17行调用StringBuilder的append方法，第28行调用toString方法。该图片与上下文紧密相关，通过字节码代码直观呈现了字符串拼接时，编译器通过StringBuilder调用append方法实现拼接，拼接完成后再调用toString得到String对象的过程，解释了字符串拼接的实现机制。](https://feishu.cn/file/GenxbYs1ioPg2fxsbQDchpwmnYe)
> 可以看出，字符串对象通过“+”的字符串拼接方式，实际上是通过 `StringBuilder` 调用 `append()` 方法实现的，拼接完成之后调用 `toString()` 得到一个 `String` 对象 。
> 不过，在循环内使用“+”进行字符串的拼接的话，存在比较明显的缺陷：**编译器不会创建单个 `StringBuilder` 以复用，会导致创建过多的 `StringBuilder` 对象**。
> ```Java
> String[] arr = {"he", "llo", "world"};
> String s = "";
> for (int i = 0; i < arr.length; i++) {
>     s += arr[i];
> }
> System.out.println(s);
> ```
> `StringBuilder` 对象是在循环内部被创建的，这意味着每循环一次就会创建一个 `StringBuilder` 对象。
> ![图片展示了Java代码中循环内部创建`StringBuilder`对象的字节码。在第32行，`new #7 <java/lang/StringBuilder>`指令用于创建`StringBuilder`对象，箭头指向该行代码。第68行`goto 32 (-36)`指令表示循环结束返回到第32行。上下文提到在循环内使用“+”进行字符串拼接时，编译器不会创建单个`StringBuilder`以复用，会导致创建过多`StringBuilder`对象，此图直观呈现了循环内部创建`StringBuilder`对象的情况，与上下文内容紧密相关。](https://feishu.cn/file/Tw5ebrzWdoQ31ixzPHwcADoVnUe)
> 如果直接使用 `StringBuilder` 对象进行字符串拼接的话，就不会存在这个问题了。
> ```Java
> String[] arr = {"he", "llo", "world"};
> StringBuilder s = new StringBuilder();
> for (String value : arr) {
>     s.append(value);
> }
> System.out.println(s);
> ```
> ![图片展示的是Java代码中使用`StringBuilder`对象进行字符串拼接的字节码指令。从第23行开始，`new`指令创建`StringBuilder`对象，第24行`invokespecial`指令调用`StringBuilder`的构造方法，第28行`aload_1`指令加载`StringBuilder`对象，后续指令依次进行字符串拼接操作，如`invokevirtual`指令调用`append`方法等，最后`goto`指令跳转。该图片与上下文紧密相关，直观呈现了使用`StringBuilder`进行字符串拼接的字节码执行过程。](https://feishu.cn/file/J9QEbtsXeoFDYSxJ0Igcf0qLn6g)
> 如果你使用 IDEA 的话，IDEA 自带的代码检查机制也会提示你修改代码。
### String#equals() 和 Object#equals() 有何区别？
**参考回答：**
> `String` 中的 `equals` 方法是被重写过的，比较的是 String 字符串的值是否相等。 `Object` 的 `equals` 方法是比较的对象的内存地址。
### 字符串常量池的作用了解吗？
**参考回答：**
> 字符串常量池（String Pool）保存着所有字符串字面量（如`String s = "hello"`）以及通过`intern()`方法添加的字符串，这些字面量在编译时期就确定。字符串常量池位于堆内存中，专门用来存储字符串常量。
> 在创建字符串时，JVM首先会检查字符串常量池，如果该字符串已经存在池中，则返回其引用，如果不存在，则创建此字符串并放入池中，并返回其引用。
> 所以，字符串常量池主要目的是为了避免字符串的重复创建。
**推荐学习：**
更多关于字符串常量池的介绍可以看一下 [Java 内存区域详解](https://javaguide.cn/java/jvm/memory-area.html)这篇文章。

### String s1 = new String("abc");这句话创建了几个字符串对象？
**参考回答：**
> 会创建 1 或 2 个字符串对象。
> 1、如果字符串常量池中不存在字符串对象“abc”的引用，那么会在堆中创建 2 个字符串对象“abc”。
> 示例代码（JDK 1.8）：
> ```Java
> String s1 = new String("abc");
> ```
> 对应的字节码：
> ![图片展示了Java中String类构造方法的字节码代码。第0步在堆中创建一个String对象，此时未被初始化；第4步在堆中创建字符串“abc”并在字符串常量池中保存对应的引用；第6步调用构造方法对第0步创建的String对象赋值。该图片与上文介绍的String类构造方法字节码内容相关，直观呈现了字节码代码的执行过程，帮助理解String对象的创建和初始化机制。](https://feishu.cn/file/IPqgbHOmeoznElxAQmrch7ycneS)
> `ldc` 命令用于判断字符串常量池中是否保存了对应的字符串对象的引用，如果保存了的话直接返回，如果没有保存的话，会在堆中创建对应的字符串对象并将该字符串对象的引用保存到字符串常量池中。
> 2、如果字符串常量池中已存在字符串对象“abc”的引用，则只会在堆中创建 1 个字符串对象“abc”。
> 示例代码（JDK 1.8）：
> ```Java
> // 字符串常量池中已存在字符串对象“abc”的引用String 
> s1 = "abc";
> // 下面这段代码只会在堆中创建 1 个字符串对象“abc”
> String s2 = new String("abc");
> ```
> 对应的字节码：
> ![图片图片展示了Java中String类构造方法对应的字节码代码。其中，0位置的`ldc #2 <abc>`命令不会在堆中创建新的字符串对象“abc”，因为0位置已经执行上执行了一次`ldc`命令，已在堆中创建过一次字符串对象“abc”。7位置的`ldc #2 <abc>`同样不会在堆中创建新的字符串对象“abc”，因为0位置已执行过一次`ldc`命令。该图片与上文关于字符串常量池中已存在在堆中创建1个字符串对象“abc”的内容相呼应，直观呈现了相关字节码操作。](https://feishu.cn/file/XgJebaecdonlihx6KSvcvSOHnHg)
> 这里就不对上面的字节码进行详细注释了，7 这个位置的 `ldc` 命令不会在堆中创建新的字符串对象“abc”，这是因为 0 这个位置已经执行了一次 `ldc` 命令，已经在堆中创建过一次字符串对象“abc”了。7 这个位置执行 `ldc` 命令会直接返回字符串常量池中字符串对象“abc”对应的引用。
### intern 方法有什么作用?
**参考回答：**
> `String.intern()` 是一个 native（本地）方法，其作用是将指定的字符串对象的引用保存在字符串常量池中，可以简单分为两种情况：
> - 如果字符串常量池中保存了“字符相同”的字符串对象的引用，就直接返回该引用。
> - 如果字符串常量池中没有保存了对应的字符串对象的引用，那就在常量池中放入一个指向该字符串对象的引用并返回。
> 示例代码（JDK 1.8） :
> ```Java
> // 在堆中创建字符串对象”Java“
> // 将字符串对象”Java“的引用保存在字符串常量池中
> String s1 = "Java"; // 直接返回字符串常量池中字符串对象”Java“对应的引用
> String s2 = s1.intern(); // 直接返回常量池里的引用
> String s3 = new String("Java"); // 
> String s4 = s3.intern(); // 直接返回字符串常量池中字符串对象”Java“对应的引用
> // s1 和 s2 指向的是堆中的同一个对象
> System.out.println(s1 == s2); // true
> // s3 和 s4 指向的是堆中不同的对象
> System.out.println(s3 == s4); // false
> // s1 和 s4 指向的是堆中的同一个对象
> System.out.println(s1 == s4); //true
> ```
推荐学习：  
[常量池，intern相关问题，通俗易懂](https://blog.csdn.net/sunao1106/article/details/126550597)

### String 类型的变量和常量做“+”运算时发生了什么？
**参考回答：**
> 先来看字符串不加 `final` 关键字拼接的情况（JDK1.8）：
> ```Java
> String str1 = "str";
> String str2 = "ing";
> String str3 = "str" + "ing";
> String str4 = str1 + str2;
> String str5 = "string";
> System.out.println(str3 == str4);//false
> System.out.println(str3 == str5);//true
> System.out.println(str4 == str5);//false
> ```
> **注意** ：比较 String 字符串的值是否相等，可以使用 `equals()` 方法。 `String` 中的 `equals` 方法是被重写过的。 `Object` 的 `equals` 方法是比较的对象的内存地址，而 `String` 的 `equals` 方法比较的是字符串的值是否相等。如果你使用 `==` 比较两个字符串是否相等的话，IDEA 还是提示你使用 `equals()` 方法替换。
> ![图片展示了Java代码中关于字符串操作的代码片段及代码提示。代码中定义了两个字符串变量str1和str2，以及通过“+”运算符拼接得到的str3和str4。在输出str3和str4时，代码提示可将“==”替换为“equals()”或“null-safe 'equals()'”。该图片与上文提到的字符串常量池相关，通过代码示例直观呈现了字符串拼接操作在编译期和运行时的不同处理方式，以及编译器进行常量折叠优化时可能遇到的代码提示问题。](https://feishu.cn/file/IAg1bk7WAoUqajx7SmacDuQ3nhe)
> **对于编译期可以确定值的字符串，也就是常量字符串 ，jvm 会将其存入字符串常量池。并且，字符串常量拼接得到的字符串常量在编译阶段就已经被存放字符串常量池，这个得益于编译器的优化。**
> 在编译过程中，Javac 编译器（下文中统称为编译器）会进行一个叫做 **常量折叠(Constant Folding)** 的代码优化。《深入理解 Java 虚拟机》中是也有介绍到：
> ![图片展示了《深入理解Java虚拟机》中关于Java编译器常量折叠的内容。编译器在编译过程中，会对源代码进行优化，其中常量折叠是极少量的优化措施之一，代码优化几乎都在即时编译器中进行。图片中还呈现了编译器对源代码的优化示例，如将int a = 1; c = a + c;等语句转换为int d = a + c;等，以及char a = 'c'; c = a + c;等语句转换为char d = a + c;等。该图片与上下文紧密相关，是对上下文介绍的编译器优化内容的补充说明。](https://feishu.cn/file/YhLsbIhb8o5vwjxAuBXc8IMrndh)
> 常量折叠会把常量表达式的值求出来作为常量嵌在最终生成的代码中，这是 Javac 编译器会对源代码做的极少量优化措施之一(代码优化几乎都在即时编译器中进行)。
> 对于 `String str3 = "str" + "ing";` 编译器会给你优化成 `String str3 = "string";` 。
> 并不是所有的常量都会进行折叠，只有编译器在程序编译期就可以确定值的常量才可以：
> - 基本数据类型( `byte`、`boolean`、`short`、`char`、`int`、`float`、`long`、`double`)以及字符串常量。
> - `final` 修饰的基本数据类型和字符串变量
> - 字符串通过 “+”拼接得到的字符串、基本数据类型之间算数运算（加减乘除）、基本数据类型的位运算（<<、>>、>>> ）
> **引用的值在程序编译期是无法确定的，编译器无法对其进行优化。**
> 对象引用和“+”的字符串拼接方式，实际上是通过 `StringBuilder` 调用 `append()` 方法实现的，拼接完成之后调用 `toString()` 得到一个 `String` 对象 。
> ```Java
> String str4 = new StringBuilder().append(str1).append(str2).toString();
> ```
> 我们在平时写代码的时候，尽量避免多个字符串对象拼接，因为这样会重新创建对象。如果需要改变字符串的话，可以使用 `StringBuilder` 或者 `StringBuffer`。
> 不过，字符串使用 `final` 关键字声明之后，可以让编译器当做常量来处理。
> 示例代码：
> ```Java
> final String str1 = "str";final String str2 = "ing";// 下面两个表达式其实是等价的
> String c = "str" + "ing";
> // 常量池中的对象
> String d = str1 + str2; 
> // 常量池中的对象
> System.out.println(c == d);// true
> ```
> 被 `final` 关键字修改之后的 `String` 会被编译器当做常量来处理，编译器在程序编译期就可以确定它的值，其效果就相当于访问常量。
> 如果 ，编译器在运行时才能知道其确切值的话，就无法对其优化。
> 示例代码（`str2` 在运行时才能确定其值）：
> ```Java
> final String str1 = "str";
> final String str2 = getStr();
> String c = "str" + "ing";
> // 常量池中的对象
> String d = str1 + str2; 
> // 在堆上创建的新的对象
> System.out.println(c == d);// false
> ```
# 异常
### Java 的异常体系
**分析**
说出异常分类，清楚错误（error）和异常（exception） 的区别，运行时异常和编译异常的区别，可检查异常和不可检测异常区别

![图片展示了Java异常体系结构图。Throwable是异常的根类，分为Exception和Error两类。Exception下有RuntimeException和非运行时异常，其中RuntimeException包含IndexOutOfBoundsException、IllegalArgumentException等；非运行时异常有IOException、SQLException等。Error下有IOError、ThreadDeath、AssertionError。该图与文档中“Java的异常体系”部分内容相关，直观呈现了异常分类及继承关系，帮助理解异常分类及区别。](https://feishu.cn/file/Ii1PbvorLoB7tOxBS8ic8aZZn3e)

**参考回答：**
> 异常主要分为 error 和 exception 两种，都继承自 throwable，error 就是虚拟机异常，程序无法干预的致命错误，exception 分为两类，必须要显示处理的编译时异常，例如 EOFExcetion 和 FileNotFoundException，和不需要显示处理的运行时异常，如 NullPointException
**推荐阅读**
[Java 基础 - 异常机制详解](https://pdai.tech/md/java/basic/java-basic-x-exception.html)

### 常见的Exception有哪些？
**参考回答：**
> 程序本身可以捕获并且可以处理的异常。Exception 这种异常又分为两类：运行时异常和编译时异常。
> - **运行时异常**
> 都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
> 运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，即使没有用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。
> - **非运行时异常** （编译异常）
> 是RuntimeException以外的异常，类型上都属于Exception类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。
### throw和throws的区别？
**参考回答：**
> - **throw**：用于抛出一个具体的异常对象。
> - **throws**：用在方法签名中，用于声明该方法可能抛出的异常。子类方法抛出的异常范围更加小，或者根本不抛异常。
### Error和Exception的区别？
**参考回答：**
> **Error**：JVM 无法解决的严重问题，如栈溢出`StackOverflowError`、内存溢出`OOM`等。程序无法处理的错误。
> **Exception**：其它因编程错误或偶然的外在因素导致的一般性问题。可以在代码中进行处理。如：空指针异常、数组下标越界等。
### try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？
**参考回答：**
> 会。当try和catch中有return时，finally仍然会执行。
**推荐阅读：**
[【搞定面试官】try中有return，finally还会执行吗? ](https://juejin.cn/post/6844904016170713096)
[try-catch-finally语句中return的执行顺序思考](https://itimetraveler.github.io/2017/09/20/%E3%80%90Java%E3%80%91try-catch-finally%E8%AF%AD%E5%8F%A5%E4%B8%ADreturn%E7%9A%84%E6%89%A7%E8%A1%8C%E9%A1%BA%E5%BA%8F%E6%80%9D%E8%80%83/)

### finally 一定会被执行吗？
**参考回答：**
> 不一定。有以下两种情况finally不会被执行：
> - 程序未执行到try代码块
> - 如果当一个线程在执行 try 语句块或者 catch 语句块时被终止，与其相对应的 finally 语句块可能不会执行。还有更极端的情况，就是在线程运行 try 语句块或者 catch 语句块时，突然死机或者断电，finally 语句块肯定不会执行了。
**推荐阅读：**
[面试官：Java中的finally一定会被执行吗？](https://cloud.tencent.com/developer/article/1770139)

# 泛型
### 什么是泛型？有什么作用？
**分析：**
> **Java 泛型（Generics）** 是 JDK 5 中引入的一个新特性。使用泛型参数，可以增强代码的可读性以及稳定性。
> 编译器可以对泛型参数进行检测，并且通过泛型参数可以指定传入的对象类型。比如 `ArrayList<Person> persons = new ArrayList<Person>()` 这行代码就指明了该 `ArrayList` 对象只能传入 `Person` 对象，如果传入其他类型的对象就会报错。
> ```Java
> ArrayList<E> extends AbstractList<E>
> ```
> 并且，原生 `List` 返回类型是 `Object` ，需要手动转换类型才能使用，使用泛型后编译器自动转换。
**参考回答：**
> Java泛型是JDK 5中引⼊的⼀个新特性， 允许在定义类和接口的时候使⽤类型参数。声明的类型参数在使⽤时⽤具体的类型来替换。
> 泛型最⼤的好处是可以提⾼代码的复⽤性。以List接口为例，我们可以将String、 Integer等类型放⼊List中， 如不⽤泛型， 存放String类型要写⼀个List接口， 存放Integer要写另外⼀个List接口， 泛型可以很好的解决这个问题。
**推荐阅读**
[Java 基础 - 泛型机制详解](https://pdai.tech/md/java/basic/java-basic-x-generic.html)

### 泛型的使用方式有哪几种？
**参考回答：**
> 泛型一般有三种使用方式:**泛型类**、**泛型接口**、**泛型方法**。
> **1.泛型类**：
> ```Java
> //此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型//在实例化泛型类时，必须指定T的具体类型
> public class Generic<T> {
>     private T key;
>     public Generic(T key) {
>         this.key = key;
>     }
>     public T getKey() {
>         return key;
>     }
> }
> ```
> 如何实例化泛型类：
> ```Java
> Generic<Integer> genericInteger = new Generic<Integer>(123456);
> ```
> **2.泛型接口** ：
> ```Java
> public interface Generator<T> {public T method();}
> ```
> 实现泛型接口，不指定类型：
> ```Java
> class GeneratorImpl<T> implements Generator<T>{@Overridepublic T method() {return null;}}
> ```
> 实现泛型接口，指定类型：
> ```Java
> class GeneratorImpl<T> implements Generator<String>{@Overridepublic String method() {return "hello";}}
> ```
> **3.泛型方法** ：
> ```Java
> public static <E> void printArray(E[] inputArray) {
>     for (E element : inputArray) {
>         System.out.printf("%s ", element);
>     }
>     System.out.println();
> }
> ```
> 使用：
> ```Java
> // 创建不同类型数组： Integer, Double 和 CharacterInteger[] intArray = {1, 2, 3};
> String[] stringArray = {"Hello", "World"};
> printArray(intArray);
> printArray(stringArray);
> ```
> 注意: `public static < E > void printArray( E[] inputArray )` 一般被称为静态泛型方法;在 java 中泛型只是一个占位符，必须在传递类型后才能使用。类在实例化时才能真正的传递类型参数，由于静态方法的加载先于类的实例化，也就是说类中的泛型还没有传递真正的类型参数，静态的方法的加载就已经完成了，所以静态泛型方法是没有办法使用类上声明的泛型的。只能使用自己声明的 `<E>`
### 项目中哪里用到了泛型？
**参考回答：**
> - 自定义接口通用返回结果 `CommonResult<T>` 通过参数 `T` 可根据具体的返回类型动态指定结果的数据类型
> - 定义 `Excel` 处理类 `ExcelUtil<T>` 用于动态指定 `Excel` 导出的数据类型
> - 构建集合工具类（参考 `Collections` 中的 `sort`, `binarySearch` 方法）。
> - ......
# 反射
关于反射的详细解读，请看这篇文章 [Java 反射机制详解](https://pdai.tech/md/java/basic/java-basic-x-reflection.html) 。

### 什么是反射？
**参考回答：**
> 如果说大家研究过框架的底层原理或者咱们自己写过框架的话，一定对反射这个概念不陌生。反射之所以被称为框架的灵魂，主要是因为它赋予了我们在运行时分析类以及执行类中方法的能力。通过反射你可以获取任意一个类的所有属性和方法，你还可以调用这些方法和属性。
### 反射的优缺点？
**参考回答：**
> 反射可以让我们的代码更加灵活、为各种框架提供开箱即用的功能提供了便利。
> 不过，反射让我们在运行时有了分析操作类的能力的同时，也增加了安全问题，比如可以无视泛型参数的安全检查（泛型参数的安全检查发生在编译时）。另外，反射的性能也要稍差点，不过，对于框架来说实际是影响不大的。
**推荐阅读**：
[Java Reflection: Why is it so slow?open in new window](https://stackoverflow.com/questions/1392351/java-reflection-why-is-it-so-slow) 。

### 反射的应用场景？
**分析：**
> 像咱们平时大部分时候都是在写业务代码，很少会接触到直接使用反射机制的场景。但是！这并不代表反射没有用。相反，正是因为反射，你才能这么轻松地使用各种框架。像 Spring/Spring Boot、MyBatis 等等框架中都大量使用了反射机制。
> **这些框架中也大量使用了动态代理，而动态代理的实现也依赖反射。**
> 比如下面是通过 JDK 实现动态代理的示例代码，其中就使用了反射类 `Method` 来调用指定的方法。
> ```Java
> public class DebugInvocationHandler implements InvocationHandler {
>     /**
>      * 代理类中的真实对象
>      */
>     private final Object target;
>     public DebugInvocationHandler(Object target) {
>         this.target = target;
>     }
>     public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
>         System.out.println("before method " + method.getName());
>         Object result = method.invoke(target, args);
>         System.out.println("after method " + method.getName());
>         return result;
>     }
> }
> ```
> 另外，像 Java 中的一大利器 **注解** 的实现也用到了反射。
> 为什么你使用 Spring 的时候 ，一个`@Component`注解就声明了一个类为 Spring Bean 呢？为什么你通过一个 `@Value`注解就读取到配置文件中的值呢？究竟是怎么起作用的呢？
> 这些都是因为你可以基于反射分析类，然后获取到类/属性/方法/方法的参数上的注解。你获取到注解之后，就可以做进一步的处理。
**参考回答：**
> - JDBC连接数据库时使用`Class.forName()`通过反射加载数据库的驱动程序
> - SPI 调用机制
> - Web服务器中利用反射调用了Sevlet的`service`方法
> - JDK动态代理底层依赖反射实现
> - Spring Bean 管理
> - ORM 框架实体类初始化
### 动态代理的几种方式 
**分析**
**参考回答：**
> 两种，分别是JDK 动态代理机制和CGLIB 动态代理机制。**JDK 动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。** 另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。就二者的效率来说，大部分情况都是 JDK 动态代理更优秀。
# 注解
### 什么是注解？
**参考回答：**
> `Annotation` （注解） 是 Java5 开始引入的新特性，可以看作是一种特殊的注释，主要用于修饰类、方法或者变量，提供某些信息供程序在编译或者运行时使用。
> 注解本质是一个继承了`Annotation` 的特殊接口：
> ```Java
> @Target(ElementType.METHOD)
> @Retention(RetentionPolicy.SOURCE)
> public @interface Override {
> }
> public interface Override extends Annotation {
> }
> ```
> JDK 提供了很多内置的注解（比如 `@Override` 、`@Deprecated`），同时，我们还可以自定义注解。
### 注解的解析方法有哪几种？
**参考回答：**
> 注解只有被解析之后才会生效，常见的解析方法有两种：
> - **编译期直接扫描** ：编译器在编译 Java 代码的时候扫描对应的注解并处理，比如某个方法使用`@Override` 注解，编译器在编译的时候就会检测当前的方法是否重写了父类对应的方法。
> - **运行期通过反射处理** ：像框架中自带的注解(比如 Spring 框架的 `@Value` 、`@Component`)都是通过反射来进行处理的。
# SPI
关于 SPI 的详细解读，请看这篇文章 [Java SPI 机制详解](https://pdai.tech/md/java/advanced/java-advanced-spi.html) 。

### 什么是 SPI?
**参考回答：**
> SPI 即 Service Provider Interface ，字面意思就是：“服务提供者的接口”，我的理解是：专门提供给服务提供者或者扩展框架功能的开发者去使用的一个接口。
> SPI 将服务接口和具体的服务实现分离开来，将服务调用方和服务实现者解耦，能够提升程序的扩展性、可维护性。修改或者替换服务实现并不需要修改调用方。
> 很多框架都使用了 Java 的 SPI 机制，比如：Spring 框架、数据库加载驱动、日志接口、以及 Dubbo 的扩展实现等等。
![图片展示了Dubbo框架中SPI扩展实现的相关内容。标题为“SPI扩展实现”，下方列出多项扩展类型，包括协议扩展、调用拦截扩展、引用监听扩展、暴露监听扩展、集群扩展、路由扩展、负载均衡扩展、合并结果扩展、注册中心扩展、监控中心扩展、扩展点加载扩展、动态代理扩展等。该图片与文档中介绍Dubbo框架使用Java的SPI机制的内容相关，直观呈现了Dubbo框架中SPI扩展实现的多种类型。](https://feishu.cn/file/SDbWbBzOZo371qxQ8SHcFejlnAd)

### SPI 和 API 有什么区别？
**那 SPI 和 API 有啥区别？**
**参考回答：**
> 说到 SPI 就不得不说一下 API 了，从广义上来说它们都属于接口，而且很容易混淆。下面先用一张图说明一下：
> ![图片展示了API和SPI的简单图示。上图是API简单图示，调用方通过接口调用实现方的实现，实现方提供能力；下图是SPI简单图示，调用方通过接口调用实现方的实现，实现方提供服务。上下文介绍SPI概念，即服务调用方和服务实现方之间引入接口，调用方确定接口规则，不同厂商根据规则实现接口，提供服务。图片直观呈现了API和SPI的结构差异，辅助理解SPI的工作原理。](https://feishu.cn/file/DCgAbGigPoXXSaxgJw1cfP0Yn4g)
> 一般模块之间都是通过接口进行通讯，那我们在服务调用方和服务实现方（也称服务提供者）之间引入一个“接口”。
> 当实现方提供了接口和实现，我们可以通过调用实现方的接口从而拥有实现方给我们提供的能力，这就是 API ，这种接口和实现都是放在实现方的。
> 当接口存在于调用方这边时，就是 SPI ，由接口调用方确定接口规则，然后由不同的厂商去根据这个规则对这个接口进行实现，从而提供服务。
> 举个通俗易懂的例子：公司 H 是一家科技公司，新设计了一款芯片，然后现在需要量产了，而市面上有好几家芯片制造业公司，这个时候，只要 H 公司指定好了这芯片生产的标准（定义好了接口标准），那么这些合作的芯片公司（服务提供者）就按照标准交付自家特色的芯片（提供不同方案的实现，但是给出来的结果是一样的）。
### SPI 的优缺点？
**参考回答：**
> 通过 SPI 机制能够大大地提高接口设计的灵活性，但是 SPI 机制也存在一些缺点，比如：
> - 需要遍历加载所有的实现类，不能做到按需加载，这样效率还是相对较低的。
> - 当多个 `ServiceLoader` 同时 `load` 时，会有并发问题。
# IO
### BIO/NIO/AIO 有什么区别
**分析**
说清楚同步与异步，阻塞与非阻塞的区别

- **BIO: 同步阻塞式IO**   打个比喻您打了专车，在您没有到之前司机就在出发地等您上车。您上车之后司机专门送您到目的地。在这个例子中您扮演着IO中的网络事件，司机扮演着处理网络事件的线程。整个过程中您如果没有任何事件发生司机一直都在等待这就是同步阻塞。
- **NIO:同步非阻塞IO**   银行柜员在等待人办理银行业务，人们去银行后首先要到取号机上取号然后等待对应的柜台叫号。在这个例子中银行柜员扮演着selector，办理银行业务的人扮演者网络事件，而取号机扮演者register的作用，银行柜台就是channel。整个过程中当没有人来办业务时，柜员是可以去做其他事情这就是非阻塞。
- **AIO:异步非阻塞IO**   您点外卖后就去忙其他的事情了，等骑手把外卖送达后打电话告诉您外卖放外卖柜子里了；您闲下来的时候去取外卖。在这个例子里您扮演着处理网络事件的线程，外卖是网络事件；在这个过程中您可以在外卖还没有送来的时候做些其他的事情，等外卖送达后只是向您发送了一个外卖送达的事件。这就是异步和非阻塞。

**参考回答：**
> BIO （Blocking IO）同步阻塞 IO，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，可以通过线程池机制来改善。BIO方式适用于连接数目比较小且固定的架构，这种方式对服务端资源要求比较高，并发局限于应用中，在 JDK1.4 以前是唯一的 IO
> NIO （Non-blocking I/O）同步非阻塞 IO，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有 IO 请求时才启动一个线程进行处理。NIO 方式适用于连接数目多且连接比较短 (轻操作)的架构，比如聊天服务器，并发局限于应用中，编程比较复杂，JDK1.4 开始支持
> AIO (Asynchronous IO) 异步非阻塞 IO，服务器实现模式为一个有效请求一个线程，客户端的I0请求都是由操作系统先完成了再通知服务器启动线程进行处理。AIO方式适用于连接数目多且连接比较长(重操作)的架构，比如相册服务器，充分调用OS参与并发操作，编程比较复杂，JDK1.7 开始支持
**推荐阅读**
[Java基础学习指引]（Java I/O）

### 同步和异步的区别是什么？
**参考回答：**
> - 同步：调用者需要一直`主动等待`被调用者的结果。
> - 异步：调用者调用被调用者后，调用者不会立刻得到结果，在调用者发起调用后，被调用者通过状态、通知或通过回调函数，让调用者知道结果
### 阻塞和非阻塞的区别？
**参考回答：**
> 阻塞和非阻塞关注的是线程的状态。
> - 阻塞调用是指调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会恢复运行。
> - 非阻塞调用指在不能立刻得到结果之前，该调用不会阻塞当前线程。
### 序列化和反序列化是什么？
**参考回答：**
> - 序列化：把对象转换为字节序列的过程称为对象的序列化
> - 反序列化：把字节序列恢复为对象的过程称为对象的反序列化
**推荐阅读**
[Java基础学习指引]（Java I/O-序列化）

# 新特性
### Java8的新特性有哪些？
关注 LTS 版本（Java 8、 11、 17、 21）

**参考回答：**
> - Lambda 表达式：Lambda允许把函数作为一个方法的参数
> - Stream API ：新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
> - 默认方法：默认方法就是一个在接口里面有了一个实现的方法。
> - Optional 类 ：Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
> - Date Time API ：加强对日期与时间的处理。
> - ZGC：Java 11 最为瞩目的特性
> - 虚拟线程：Java 21
**推荐阅读：**
[Java基础学习指引]（Java 8 新特性）
