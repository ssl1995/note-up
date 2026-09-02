# SSM 全家桶面试题
Java Spring 全家桶学习指引：（核心知识篇）
## Spring 概述
### 什么是 Spring 框架？
**分析：**
Spring 是一个轻量级、非入侵式的控制反转 （IoC） 和面向切面 （AOP） 的框架。
Spring 是很多模块的集合，使用这些模块可以很方便地协助我们进行开发。这些模块是核心容器、数据访问/集成、Web、AOP（面向切面编程）、工具、消息和测试模块。比如 Core Container 中的 Core 组件是 Spring 所有组件的核心，Beans 组件和 Context 组件是实现 IOC 和 DI 的基础，AOP 组件用来实现面向切面编程。
Spring 官网列出的 Spring 的 6 个特征：
- 核心技术：依赖注入（DI），AOP，事件（Events），资源，i18n，验证，数据绑定，类型转换，SpEL。
- 测试：模拟对象，TestContext 框架，Spring MVC 测试，WebTestClient。
- 数据访问：事务，DAO 支持，JDBC，ORM，编组 XML。
- Web 支持：Spring MVC 和 Spring WebFlux Web 框架。
- 集成：远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存。
- 语言：Kotlin，Groovy，动态语言
**回答：**
Spring 框架是 Java 开发里特别常用的一个开源框架，它主要是为了简化企业级应用开发。核心思想是控制反转和面向切面编程，控制反转就是把对象创建和依赖管理的工作交给框架来做，不用我们自己手动 new 对象，这样代码耦合度能降低不少。面向切面编程呢，可以把日志、事务这些重复的代码抽出来，单独管理，让业务逻辑更清晰。
Spring 提供了很多功能，像 IOC 容器负责对象管理，AOP 支持横切逻辑，还有对事务管理、数据库访问这些的支持，集成其他框架也很方便，比如 MyBatis、Hibernate 这些。
### **使用 Spring 框架的好处是什么？**
**分析：**
- 轻量：Spring 是轻量的，基本的版本大约 2MB。
- 控制反转：Spring 通过控制反转实现了松散耦合，对象们给出它们的依赖，而不是创建或查找依赖的对象们。
- 面向切面的编程（AOP）：Spring 支持面向切面的编程，并且把应用业务逻辑和系统服务分开。
- 容器：Spring 包含并管理应用中对象的生命周期和配置。
- MVC 框架：Spring 的 WEB 框架是个精心设计的框架，是 Web 框架的一个很好的替代品。
- 事务管理：Spring 提供一个持续的事务管理接口，可以扩展到上至本地事务下至全局事务（JTA）。
- 异常处理：Spring 提供方便的 API 把具体技术相关的异常（比如由 JDBC，Hibernate or JDO 抛出的）转化为一致的 unchecked 异常。
**回答：**
Spring 是现在 Java 开发里常用的框架，用它主要是能让开发更简单、代码耦合低、效率还高。它有个 IOC 容器，能帮我们管理对象创建和依赖关系，不用自己写一堆 new 对象的代码，这样改代码的时候不用到处找依赖改，框架会自动处理对象间的关系，代码耦合度低，维护起来方便。还有 AOP，像日志、事务、权限这些重复的逻辑，可以抽出来统一处理，不用每个业务方法里都写，代码会更干净。事务管理也很方便，加个注解就能控制事务，不用手动写 begin、commit、rollback 这些代码，减少出错。另外它生态特别全，想整合 MyBatis、Redis 这些框架，直接用 Spring 提供的工具就行，不用自己写整合逻辑。总的来说，用 Spring 能少写很多重复代码，把精力放在业务逻辑上，项目也更好维护。
### **Spring 框架有哪些模块？**
**分析：**
最主要的七大模块：
- **Spring Core**：提供了框架的基本组成部分，包括控制反转（Inversion of Control，IOC）和依赖注入（Dependency Injection，DI）功能。
- **Spring Beans**：提供了 BeanFactory，是工厂模式的一个经典实现，Spring 将管理对象称为 Bean。
- **Spring Context**：构建于 core 封装包基础上的 context 封装包，提供了一种框架式的对象访问方法。
- **Spring JDBC**：提供了一个 JDBC 的抽象层，消除了烦琐的 JDBC 编码和数据库厂商特有的错误代码解析， 用于简化 JDBC。
- **Spring AOP**：提供了面向切面的编程实现，让你可以自定义拦截器、切点等。
- **Spring Web**：提供了针对 Web 开发的集成特性，例如文件上传，利用 servlet listeners 进行 ioc 容器初始化和针对 Web 的 ApplicationContext。
- **Spring Test**：主要为测试提供支持的，支持使用 JUnit 或 TestNG 对 Spring 组件进行单元测试和集成测试。
**回答：**
Spring 框架主要有几个核心模块。首先是 Core Container，这是最基础的部分，里面的 IoC 容器负责管理对象的创建和依赖关系，所有其他模块都要靠它来运行。然后是 AOP 模块，支持面向切面编程，能处理像事务管理、日志记录这些横切在业务逻辑里的功能，不用在业务代码里重复写。数据访问方面有 Data Access/Integration 模块，包含 JDBC 支持简化数据库操作，还能集成 MyBatis、Hibernate 这些 ORM 框架，另外事务管理也在这里，保证数据操作的一致性。Web 模块里最常用的是 Spring MVC，用来处理 Web 请求，从接收请求到返回响应都靠它，现在还有 WebFlux 支持响应式 Web 开发。安全方面有 Spring Security，专门管认证和授权，控制谁能访问应用里的资源。最后是测试模块，Spring Test 能和 JUnit 这些测试工具集成，方便做单元测试和集成测试，不用手动搭复杂的测试环境。这些模块互相配合，能覆盖从基础到应用开发的大部分需求。
### **Spring mvc 和 Struts 2 的区别？**
**分析：**
 Spring mvc 特性如下：
- 具备 IOC/DI、AOP 等通用能力，提高研发效率
- 除了支持 Web 层建设以外，还提供了 J2EE 整体服务
- 方便与其他不同技术结合使用，如 Hibernate、MyBatis 等
- Spring 拦截机制是方法级别
Struts 特性如下：
- 是一个基于 MVC 模式的一个 Web 层的处理
- Struts 拦截机制是类级别
**回答：**
Spring MVC 和 Struts 2 的区别主要在架构设计、请求处理和数据绑定这些方面。
- Spring MVC 是基于方法的，每个请求对应 Controller 里的一个方法，方法参数直接接收请求数据；Struts 2 是基于类的，整个 Action 类处理请求，通过成员变量接收数据，这样可能有线程安全问题。
- 处理流程上，Spring MVC 用 DispatcherServlet 统一分发请求，流程更简洁；Struts 2 靠 Filter 拦截请求，还要经过 ValueStack 这些处理，流程相对复杂，性能可能稍差。
- 数据绑定方面，Spring MVC 直接在方法参数上用注解就能绑定，比如@RequestParam；Struts 2 得通过 Action 类的成员变量，依赖 OGNL 表达式，配置起来麻烦些。
- 另外 Spring MVC 和 Spring 框架整合自然，依赖注入方便；Struts 2 要整合 Spring 还得额外配，没那么顺畅。
### **Spring 中的设计模式有哪些？**
**分析：**
- **工厂模式** : Spring 容器本质是一个大工厂，使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
- **代理模式** : Spring AOP 功能功能就是通过代理模式来实现的，分为动态代理和静态代理。
- **单例模式** : Spring 中的 Bean 默认都是单例的，这样有利于容器对 Bean 的管理。
- **模板模式** : Spring 中 JdbcTemplate、RestTemplate 等以 Template 结尾的对数据库、网络等等进行操作的模板类，就使用到了模板模式。
- **观察者模式**： Spring 事件驱动模型就是观察者模式很经典的一个应用。
- **适配器模式** :Spring AOP 的增强或通知 （Advice） 使用到了适配器模式、Spring MVC 中也是用到了适配器模式适配 Controller。
- **策略模式**：Spring 中有一个 Resource 接口，它的不同实现类，会根据不同的策略去访问资源。
**回答：**
Spring 里常用的设计模式有不少，我重点说几个核心的。
- 首先是工厂模式，Spring 的 IoC 容器就是典型的工厂实现，像 BeanFactory 和 ApplicationContext，负责创建和管理 Bean 对象，我们不用自己 new，直接从容器拿，这就是工厂模式的应用。
- 然后是单例模式，Spring 默认的 Bean 都是单例的，通过单例注册表机制保证一个 Bean 在容器里只有一个实例，避免重复创建浪费资源。
- 代理模式也很关键，AOP 功能主要靠它实现，比如 JDK 动态代理和 CGLIB 代理，用来在目标方法前后加日志、事务这些横切逻辑，不用修改原有代码。
- 还有策略模式，比如 Resource 接口，不同资源类型有不同实现，像 ClassPathResource、FileSystemResource，用的时候根据场景选对应的策略就行。
- 模板方法模式也常用，比如 JdbcTemplate，把连接数据库、关闭资源这些固定流程封装好，我们只需要写具体的 SQL 查询逻辑，减少重复代码。
这些模式让 Spring 框架更灵活、扩展性更好，用起来也更高
## Spring IOC
### 什么是 IOC？
**分析：**
IoC 即控制反转（Inversion of Control，缩写为 IoC）。IoC 又称为依赖倒置原则（设计模式六大原则之一），它的要点在于：程序要依赖于抽象接口，不要依赖于具体实现。它的作用就是用于降低代码间的耦合度。
IoC 的实现方式有两种：
- **依赖注入**（Dependency Injection，简称 DI）：不通过 `new()` 的方式在类内部创建依赖类对象，而是将依赖的类对象在外部创建好之后，通过构造器、函数参数等方式传递（或注入）给类使用。
- **依赖查找**（Dependency Lookup）：容器中的受控对象通过容器的 API 来查找自己所依赖的资源和协作对象。
Spring IoC 是 IoC 的一种实现。DI 是 Spring IoC 的主要实现原则。
**回答：**
IOC 就是控制反转，是 Spring 框架里很核心的思想。传统开发的时候，我们创建对象、管理对象之间的依赖，都是自己写代码控制的，比如需要哪个对象就 new 一个，或者手动设置依赖关系。
但 IOC 不一样，它把这种控制权交给了容器，由容器来负责对象的创建、装配，还有对象之间依赖关系的管理。我们不用再手动 new 对象了，需要的时候直接从容器里拿就行。
这样做最大的好处就是降低了代码之间的耦合度，比如要换个依赖的对象，不用改业务代码，改一下配置或者注解就行，开发起来更灵活，维护也方便。Spring 里的 IOC 容器像 ApplicationContext，就是干这个事的，帮我们管理 Bean 的生命周期和依赖。
### 谈谈你对 IOC 的理解？
**分析：**
Spring IOC（Inversion of Control，控制反转）是 Spring 框架的核心，它实现了一种基于容器的对象管理机制。在 Spring IOC 中，控制权由应用程序代码转移到了 Spring 框架中，Spring 框架负责创建对象、管理对象之间的依赖关系、调用对象的方法等操作，应用程序只需要声明需要使用的对象和依赖关系，无需自己负责对象的创建和管理，从而实现了控制反转。
在 Spring IOC 中，容器负责创建和管理对象，容器根据配置文件或者注解中的信息，自动创建和管理对象之间的依赖关系，然后将这些对象注入到应用程序中。应用程序只需要声明需要使用的对象和依赖关系，通过注入的方式获取这些对象，从而避免了硬编码和耦合性的问题。
Spring IOC 的主要实现方式是通过依赖注入（Dependency Injection，DI）来实现的。依赖注入是指在对象创建的过程中，自动注入该对象所依赖的其他对象，从而构建对象之间的依赖关系。Spring IOC 支持多种依赖注入的方式，如构造函数注入、Setter 方法注入、字段注入等。
总的来说，Spring IOC 提供了一种松耦合、可重用、可维护的编程模式，使得应用程序更加容易开发、测试和扩展。通过使用 Spring IOC，应用程序可以更加关注业务逻辑，而不需要过多关注对象的创建和管理。
**回答：**
IOC 就是控制反转，简单说就是把对象的创建和依赖管理的控制权力交出去。传统开发里，我们要自己 new 对象，还要手动处理对象之间的依赖关系，比如 A 依赖 B，就得在 A 里自己创建 B 的实例。但 IOC 模式下，这些工作都交给容器来做了，像 Spring 里的 IOC 容器，它会帮我们创建对象、管理对象生命周期，还会自动把依赖的对象注入进来。
具体实现主要靠依赖注入，就是容器在创建对象的时候，把它需要的其他对象通过构造器或者 setter 方法传进去，我们不用再手动写代码去创建依赖。这样做最大的好处是降低了代码之间的耦合度，对象之间不用硬编码依赖关系，后续修改或者替换对象时，改配置就行，不用动业务代码，维护起来方便，测试也简单。
### 什么是依赖注入？依赖注入有哪些实现方式？
**分析：**
所谓依赖注⼊，是**指程序运⾏过程中，如果需要调⽤另⼀个对象协助时，⽆须在代码中创建被调⽤者，** 
**⽽是依赖于外部的注⼊**。
Spring 的依赖注⼊对调⽤者和被调⽤者⼏乎没有任何要求，完全⽀持对 POJO 之间依赖关系的管理
**依赖注入有如下方式：**
- setter 注⼊，可选的注⼊⽅式，好处是在有变更的情况下，可以重新注⼊。 
- 构造器注⼊，Spring 倡导构造函数注⼊，因为构造器注⼊返回给客户端使⽤的时候⼀定是完整的。 
- Autowired 注⼊，就是平⽇我们⽤ @Autowired 标记字段 
- 接⼝回调注⼊，就是实现 Spring 定义的⼀些内建接⼝，例如 BeanFactoryAware，会进⾏ BeanFactory 的注⼊，不提倡。 
**回答：**
依赖注入简单说就是，一个对象需要的其他对象，不是自己内部创建，而是由外部提供进来的方式。这样做能降低对象之间的耦合，让代码更灵活，方便测试和维护。
常见的实现方式有两种:
- 一种是构造器注入，就是通过对象的构造方法把依赖传进去，适合那些必须的依赖。
- 另一种是 Setter 注入，通过对象的 set 方法来设置依赖，适合可选的依赖。
这两种方式用得比较多，能满足大部分场景。
### IoC 的实现原理是什么？
**分析：**
Spring IoC 的实现原理可以分为两个步骤：
1）扫描和解析配置文件或注解信息，将其转换为内部的对象定义和依赖关系；
2）根据对象定义和依赖关系，使用反射机制动态创建和初始化对象，并将对象注入到需要使用它们的地方。
具体来说，Spring IoC 的实现过程如下：
1. 读取配置文件或解析注解信息，将其转换为内部的对象定义和依赖关系。在 Spring 中，可以使用 XML 文件或注解来配置对象和依赖关系。Spring 通过解析配置文件或注解信息，将其转换为内部的对象定义和依赖关系（BeanDefinition）放到容器（BeanFactory）中。对象定义包括对象的类型、属性、构造函数等信息，依赖关系包括对象之间的依赖关系、依赖注入方式等信息。
2. 实例化 bean 对象：Spring 会根据对象定义的类型和构造函数信息，使用反射机制来创建对象。
3. 设置属性：实例化后的仍然是一个原生的状态，并没有进行依赖注入。 这一步 Spring 根据 BeanDefinition 中的信息进行属性填充，依赖注入。 
4. 调用 Aware 接口：Spring 会检测该对象是否实现了 xxxAware 接口，如果有会在这里执行完成。Aware 主要是能获取到 Spring 容器中的一些资源，然后可以供后续步骤，例如初始化阶段使用。
5. BeanPostProcessor 前置处理：postProcessBeforeInitialzation 方法。上述几个步骤后，bean 对象已经被正确构造，但如果想要对象被初始化前再进行一些自定义的处理，就可以通过 BeanPostProcessor 接口的该方法来实现。
6. 初始化阶段：该阶段 Spring 首先会看是否是实现了 InitializingBean 接口的 afterPropertiesSet 方法以及是否有自定义的 init-method 等，如果有会进行调用执行。
7. BeanPostProcessor 后置处理：postProcessAfterInitialzation 方法。当前正在初始化的 bean 对象会被传递进来，我们就可以对这个 bean 作任何处理，与前面前置处理相对的，这个函数会在 InitialzationBean 完成后执行，因此称为后置处理。
8. bean 初始化完成可以被使用了。
总的来说，Spring IOC 的实现原理是通过反射机制动态创建对象，依赖注入，对象初始化。通过解耦对象之间的依赖关系，使得应用程序更加灵活、可维护、可扩展。
**回答：**
简单说，IoC 就是控制反转，核心是把对象的创建和依赖管理交给容器来做。以前写代码时，需要哪个对象就得自己 new，还得手动处理依赖关系，比如 A 依赖 B，就得先 new B 再传给 A，这样不仅麻烦，代码耦合度还高。
用了 IoC 容器后，我们只需要告诉容器要哪些对象、它们之间有什么依赖，比如通过 XML 配置或者注解说明。容器会先解析这些配置，生成对象的定义信息，然后在合适的时候创建对象，并且自动把依赖的对象注入进去，比如通过构造器传参或者调用 setter 方法。这样对象之间不用自己管理依赖，都是容器处理，耦合度降低，代码也更好维护。
### **怎么理解 Spring 中的 IOC 容器？**
**分析：**
Spring IOC 就是把创建对象的权利交给框架去控制，而不需要人为的去创建，这样就实现了可插拔式的接口编程，有效地降低代码的耦合度，降低了扩展和维护的成本。
比如，去某地旅游不再用自己亲自为订购 A 酒店还是 B 酒店而发愁了，只需要把住店的需求告诉给某个托管平台，这个托管平台就会帮你订购一个既便宜又舒适的酒店，而这个帮你订购酒店的行为就可以称之为控制反转。
**回答：**
Spring 的 IOC 容器其实就是控制反转的具体实现，简单说就是把对象创建和依赖管理的事儿从我们自己写的代码里移出去，交给容器来管。以前我们用对象的时候得自己 new，还得手动处理对象之间的依赖关系，现在这些活儿都让 IOC 容器干了。
容器会根据配置（像 XML 或者注解）在启动的时候创建好需要的对象，也就是 Bean，还会把它们之间的依赖关系处理好，比如一个 Service 依赖 Dao，容器会自动把 Dao 对象注入到 Service 里。
我们用的时候直接从容器里拿现成的 Bean 就行，不用操心怎么创建和组装。这样做的好处就是降低了代码之间的耦合，以后改配置或者换实现类都方便，维护起来也简单。
### **怎么理解 Spring 中的依赖注入？**
**分析：**
依赖注入是指组件之间的依赖关系由容器在运行期决定，即由容器动态的将某个依赖关系注入到组件之中。依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。
**回答：**
依赖注入是 Spring 的核心特性，主要解决对象依赖管理的问题。以前开发时，一个类需要用另一个类的对象，得自己手动 new 出来，这样写耦合度很高，改起来特别麻烦。依赖注入就是让 Spring 容器来管理这些依赖对象，不用我们自己创建，容器会自动把需要的对象给到需要的类里。
具体来说，主要有两种注入方式：构造器注入和 Setter 注入。构造器注入是通过类的构造方法把依赖对象传进去，Setter 注入就是通过 set 方法传。Spring 容器启动的时候，会根据配置（像 XML 或者注解）把所有需要的对象都创建好，处理好它们之间的依赖关系，等哪个类需要依赖了，容器直接把依赖对象给它。
这么做最大的好处就是降低了类之间的耦合，类不用自己管依赖怎么来，只需要声明需要什么就行。而且方便测试，想换个依赖的实现，改下配置就行，不用改代码。整体上让代码更灵活，维护起来也简单多了。
### **IoC 和 DI 有什么关系？**
**分析：**
IoC 和 DI 都是 Spring 框架中的核心概念，它们的区别在于：
- **IoC（Inverse of Control，控制反转）**：它是一种思想，主要解决程序设计中的对象依赖关系管理问题。在 IoC 思想中，对象的创建权反转给第三方容器，由容器进行对象的创建及依赖关系的管理。
- **DI（Dependency Injection，依赖注入）**：它是 IoC 思想的具体实现方式之一，用于实现 IoC。在 Spring 中，依赖注入是指：在对象创建时，由容器自动将依赖对象注入到需要依赖的对象中。
简单来说，它们的关系是：
- IoC 是一种思想、理念，定义了对象创建和依赖关系处理的方式。
- DI 是 IoC 思想的具体实现方式之一，实际提供对象依赖关系的注入功能。
所以 IoC 是更基础和广义的概念，DI 可以说是 IoC 的一种实现手段。大多数情况下，我们提到 IoC 的时候，其实意味着 DI，因为 DI 已经是 IoC 最常见和广泛使用的实现方式了。
例如在 Spring 框架中：
- IoC 体现为 Spring 容器承担了对象创建及依赖关系管理的控制权。
- DI 体现为 Spring 容器通过构造方法注入、Setter 方法注入等方式，将依赖对象注入到需要依赖的对象中。
所以综上，IoC 和 DI 之间的关系可以这样理解：
- IoC 是理论，DI 是实践。
- IoC 是思想，DI 是手段。
- IoC 是整体，DI 是部分。
**回答：**
IoC 和 DI 关系挺紧密的。
IoC 叫控制反转，简单说就是以前写代码时，对象创建和依赖关系都是自己在代码里控制，比如一个类需要另一个类，就自己 new 出来。有了 IoC 之后，这个控制权就交给容器了，不用自己管对象怎么创建、依赖怎么处理。
DI 是依赖注入，它是实现 IoC 的具体方式。容器在创建对象的时候，会把这个对象需要依赖的其他对象自动“注入”进去，不用我们手动创建依赖实例。
所以 IoC 是一种思想，核心就是反转控制权，而 DI 是这种思想的具体实现手段，通过注入依赖来完成控制反转。简单讲，IoC 是目的，DI 是达成这个目的的方法，两者相辅相成，没有 DI，IoC 的思想就落不了地。
## Spring Bean
### 什么是 Bean？
**分析：**
在 Spring 中，构成应用程序主体由 Spring IoC 容器管理的对象称为 Bean。Bean 是由 Spring IoC 容器实例化、装配和管理的对象。 Bean 以及它们之间的依赖关系反映在容器使用的配置元数据中。
Spring IoC 容器本身，并不能识别配置的元数据。为此，要将这些配置信息转为 Spring 能识别的格式——`BeanDefinition` 对象。
`BeanDefinition` 是 Spring 中定义 Bean 的配置元信息接口，它包含：
- Bean 类名
- Bean 行为配置元素，如：作用域、自动绑定的模式、生命周期回调等
- 其他 Bean 引用，也可称为合作者（Collaborators）或依赖（Dependencies）
- 配置设置，如 Bean 属性（Properties）
总结⼀下： 
1. Bean 是对象，⼀个或者多个不限定。 
2. Bean 托管在 Spring 中⼀个叫 IoC 的容器中。 
3. 我们的程序是由⼀个个 Bean 构成的。
**回答：**
简单说，Bean 就是 Java 里的一种对象，但不是随便写的类，得符合一定规范，比如属性一般设成私有，然后通过 getter 和 setter 方法来访问和修改。平时开发里常用它来封装数据，比如存用户信息、订单数据这些。在 Spring 这类框架里，Bean 更常见，它不是我们自己 new 出来的，而是由框架的容器管理，容器会负责创建对象、初始化，甚至销毁，我们不用操心这些细节，直接用就行。这样做的好处是方便组件复用，也让代码解耦，开发更灵活。所以 Bean 其实就是按一定规则写的 Java 对象，在框架里被统一管理，帮我们简化开发。
### Bean 生命周期是什么？
**分析：**
**单例对象：** singleton 
总结：单例对象的生命周期和容器相同 
**多例对象：** prototype 
出生：使用对象时 spring 框架为我们创建 
活着：对象只要是在使用过程中就一直活着 
死亡：当对象长时间不用且没有其它对象引用时，由 java 的垃圾回收机制回收
IOC 容器初始化加载 Bean 流程：
```Java
@Override
public void refresh() throws BeansException, IllegalStateException {
    synchronized (this.startupShutdownMonitor) {
        // 第一步:刷新前的预处理
        prepareRefresh();
        
        //第二步: 获取BeanFactory并注册到 BeanDefitionRegistry
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
        
        // 第三步:加载BeanFactory的预准备工作(BeanFactory进行一些设置，比如context的类加载器等)
        prepareBeanFactory(beanFactory);
        
        try {
            // 第四步:完成BeanFactory准备工作后的前置处理工作
            postProcessBeanFactory(beanFactory);
            
            // 第五步:实例化BeanFactoryPostProcessor接口的Bean
            invokeBeanFactoryPostProcessors(beanFactory);
            
            // 第六步:注册BeanPostProcessor后置处理器，在创建bean的后执行
            registerBeanPostProcessors(beanFactory);
            
            // 第七步:初始化MessageSource组件(做国际化功能;消息绑定，消息解析);
            initMessageSource();
            
            // 第八步:注册初始化事件派发器
            initApplicationEventMulticaster();
            
            // 第九步:子类重写这个方法，在容器刷新的时候可以自定义逻辑
            onRefresh();
            
            // 第十步:注册应用的监听器。就是注册实现了ApplicationListener接口的监听器
            registerListeners();
            
            //第十一步:初始化所有剩下的非懒加载的单例bean 初始化创建非懒加载方式的单例Bean实例(未设置属性)
            finishBeanFactoryInitialization(beanFactory);
            
            //第十二步: 完成context的刷新。主要是调用LifecycleProcessor的onRefresh()方法，完成创建
            finishRefresh();
        }
        //...
    }
}
```
总结：
**四个阶段**
- 实例化 Instantiation
- 属性赋值 Populate
- 初始化 Initialization
- 销毁 Destruction
**多个扩展点**
- 影响多个 Bean
  - BeanPostProcessor
  - InstantiationAwareBeanPostProcessor
- 影响单个 Bean
  - Aware
**完整流程**
1. 实例化一个 Bean－－也就是我们常说的 **new**；
2. 按照 Spring 上下文对实例化的 Bean 进行配置－－**也就是 IOC 注入**；
3. 如果这个 Bean 已经实现了 BeanNameAware 接口，会调用它实现的 setBeanName（String）方法，也就是根据就是 Spring 配置文件中 **Bean 的 id 和 name 进行传递；**
4. 如果这个 Bean 已经实现了 BeanFactoryAware 接口，会调用它实现 setBeanFactory（BeanFactory）也就是 Spring 配置文件配置的 **Spring 工厂自身进行传递**；
5. 如果这个 Bean 已经实现了 ApplicationContextAware 接口，会调用 setApplicationContext（ApplicationContext）方法，和 4 传递的信息一样但是因为 ApplicationContext 是 BeanFactory 的子接口，所以**更加灵活；**
6. 如果这个 Bean 关联了 BeanPostProcessor 接口，将会调用 postProcessBeforeInitialization（）方法，BeanPostProcessor 经常被用作是 Bean 内容的更改，由于这个是在 Bean 初始化结束时调用那个的方法，也可以被应用于**内存或缓存技术；**
7. 如果 Bean 在 Spring 配置文件中配置了 init-method 属性会自动调用其配置的初始化方法。
8. 如果这个 Bean 关联了 BeanPostProcessor 接口，将会调用 postProcessAfterInitialization（），**打印日志或者三级缓存技术里面的 bean 升级；**
9. 以上工作完成以后就可以应用这个 Bean 了，那这个 Bean 是一个 Singleton 的，所以一般情况下我们调用同一个 id 的 Bean 会是在内容地址相同的实例，当然在 Spring 配置文件中也可以配置非 Singleton，这里我们不做赘述。
10. 当 Bean 不再需要时，会经过清理阶段，如果 Bean 实现了 DisposableBean 这个接口，或者根据 spring 配置的 destroy-method 属性，调用实现的 destroy（）方法
推荐阅读 ：[如何记忆 Spring Bean 的生命周期？](https://chaycao.github.io/2020/02/15/%E5%A6%82%E4%BD%95%E8%AE%B0%E5%BF%86Spring-Bean%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.html)
**回答：**
Bean 的生命周期就是从创建到销毁的整个过程。
首先是实例化，也就是通过构造器创建 Bean 对象。然后是属性赋值，给 Bean 的属性设置值，包括依赖注入其他 Bean。
接下来是初始化，这时候会先调用实现了 Aware 接口的方法，比如获取 Bean 名称、容器信息这些；之后执行 InitializingBean 接口的 afterPropertiesSet 方法，或者自定义的 init-method 方法完成初始化操作。
初始化完成后 Bean 就可以正常使用了。最后当容器关闭时，会进行销毁，先调用 DisposableBean 接口的 destroy 方法，或者自定义的 destroy-method 方法，释放资源，完成整个生命周期。
整个过程由 Spring 容器管理，从创建到能用再到销毁，一步步按顺序来。
### 什么是 FactoryBean？ 
**分析：**
FactoryBean 是 Spring 所提供的⼀种较灵活的创建 Bean 的⽅式，可以通过实现 FactoryBean 接⼝中的 getObject（）⽅法来返回⼀个对象，这个对象就是最终的 Bean 对象。
如果⼀个对象实现了这接⼝，那它就成为⼀种特殊的 Bean，注册到 IOC 容器之后，如果调⽤ getBean 获取得到的其实是 FactoryBean#getObject（） ⽅法返回的结果。 
**回答：**
FactoryBean 是 Spring 框架里的一个接口，主要用来创建那些创建过程比较复杂的对象。平时我们定义的普通 Bean，Spring 是直接实例化的，但如果某个对象创建步骤多，比如需要配置很多参数或者依赖其他组件，这时候用 FactoryBean 就更合适。它本身也是个 Bean，但我们从 Spring 容器里获取的不是它自己，而是它的 getObject 方法返回的对象。
它有几个核心方法，getObject 就是返回我们要的实例，getObjectType 告诉 Spring 这个实例的类型，isSingleton 用来指定是不是单例。如果想获取 FactoryBean 本身，得在 Bean 名称前面加个&符号，不然拿到的就是它创建的对象。总的来说，就是把复杂对象的创建逻辑封装起来，让配置更简洁。
### **BeanFactory 和  FactoryBean 区别是什么？**
**分析：**
BeanFactory 和 FactoryBean 是 Spring 框架中的两个关键概念，用于创建和管理 Bean 实例。
BeanFactory 是 Spring 的基本容器，负责创建和管理 Bean 实例的，而 FactoryBean 是一个特殊的 Bean（实现了 FactoryBean 接口）负责创建其他 Bean 实例，并提供一些初始化 Bean 的设置。
下面是 BeanFactory 和 FactoryBean 之间的一些关键区别：
1. **功能**：BeanFactory 是一个容器，负责管理和创建 Bean 实例，处理依赖关系和属性注入等操作。FactoryBean 是一个接口，定义了创建 Bean 的规范和逻辑，它负责创建其他 Bean 实例。
2. **使用方式**：BeanFactory 使用配置文件或注解来定义 Bean 和它们之间的关系，它使用延迟初始化策略，即只有在需要时才创建 Bean 实例。FactoryBean 通常在 Spring 配置文件中配置，并由 BeanFactory 负责实例化和管理。
3. **创建的对象**：BeanFactory 创建和管理普通的 Bean 实例，而 FactoryBean 创建其他 Bean 实例。
4. **灵活性**：FactoryBean 具有更高的灵活性，因为它允许自定义的逻辑来创建和配置 Bean 实例。FactoryBean 的实现类可以根据特定的条件选择性地创建不同的 Bean 实例，或者在创建 Bean 之前进行一些初始化操作。这使得 FactoryBean 在某些情况下比 BeanFactory 更加强大和可扩展。
5. **返回类型**：BeanFactory 返回的是 Bean 实例本身，而 FactoryBean 返回的是由 FactoryBean 创建的 Bean 实例。因此，当使用 FactoryBean 时，需要通过调用 getObject（） 方法来获取创建的 Bean 实例。
**回答：**
BeanFactory 是 Spring 里最基础的 IoC 容器接口，它的主要作用就是管理 Bean 的生命周期，包括创建、存储、获取和销毁 Bean 这些操作。平时我们用的 ApplicationContext 就是它的具体实现类，应用里需要获取 Bean 的时候，都是通过它来拿的。
FactoryBean 就不一样了，它本身是个特殊的 Bean，实现了这个接口的类，主要作用是定制化创建其他 Bean。简单说，它自己是容器里的一个 Bean，但它能干的是生成别的 Bean 实例。当你从 Spring 容器里获取一个 FactoryBean 类型的 Bean 时，默认拿到的不是 FactoryBean 本身，而是它生产出来的那个 Bean。如果非要获取 FactoryBean 自己，得在 Bean 的名字前面加个&符号才行。
所以核心区别就是，BeanFactory 是容器接口，负责管理所有 Bean；FactoryBean 是个工厂 Bean，专门用来创建特定 Bean 的，它自己也是被 BeanFactory 管理的对象之一。
### BeanFactory 和 ApplicationContext 的关系是什么？
**分析：**
BeanFactory 和 ApplicationContext 是 Spring 框架中的两个重要的容器。它们都用于管理 Spring Bean 对象，但是它们在功能上有一些不同点。
- BeanFactory 是 Spring 框架中最基本的容器，它提供了最基础的 IOC 和 DI 的支持，它的主要功能是用于创建、管理和查找 Bean 对象。BeanFactory 只是个接口，并不是 IOC 容器的具体实现， 它为其他具体的 IOC 容器提供了最基本的规范，例如 DefaultListableBeanFactory，ApplicationContext 等容器实现或容器接口都是基于 BeanFactory，再在其基础之上附加了其他的功能，原始的 BeanFactory 无法支持 spring 的许多插件，如 AOP 功能、Web 应用等。原始 BeanFactory 是延时加载，也就是说在容器启动时不会注入 bean，而是在需要使用 bean 的时候，才会对该 bean 进行加载实例化。
- ApplicationContext 接口是基于 BeanFactory 扩展而来，也是一个容器接口，具有 BeanFactory 所有的功能，同时继承了 MessageSource，所以提供了更完整的框架功能，支持国际化、资源文件访问、载入多个上下文配置文件，使得每一个上下文都专注于一个特定层次，提供在监听器中注册 bean 事件。ApplicationContext 是预加载，在容器启动的时候一次性加载所有的 bean，所以运行的时候速度相对 BeanFactory 比较快，缺点就是耗内存。
总的来说，BeanFactory 是 Spring 框架中最基本的容器，提供最基础的 IOC 和 DI 的支持；而 ApplicationContext 是在 BeanFactory 的基础上扩展而来的，提供了更多的功能和特性。ApplicationContext 是 Spring 框架中使用较为广泛的容器。
**回答：**
BeanFactory 是 Spring 最基础的 IOC 容器，主要负责 Bean 的创建、管理和获取这些核心功能，比如通过 getBean 方法获取 Bean 实例。ApplicationContext 是 BeanFactory 的子接口，它继承了 BeanFactory 的所有基础功能，同时还扩展了很多实用特性，像支持国际化处理、事件发布机制、自动加载配置资源，还有 AOP 和事务管理的集成等。
所以在实际开发中，我们基本都用 ApplicationContext，因为它功能更全面，使用起来更方便，而 BeanFactory 更偏向底层，一般很少直接使用。简单说就是 ApplicationContext 是 BeanFactory 的增强版，继承并扩展了它的能力。
### Bean 的作用域有哪些？
**分析：**
从官⽹，我们很容易可以得知，最新版本⼀共有六种作⽤域： 
- **singleton** : IoC 容器中只有唯一的 bean 实例。Spring 中的 bean 默认都是单例的，是对单例设计模式的应用。
- **prototype** : 每次获取都会创建一个新的 bean 实例。也就是说，连续 `getBean()` 两次，得到的是不同的 Bean 实例。
- **request** （仅 Web 应用可用）: 每一次 HTTP 请求都会产生一个新的 bean（请求 bean），该 bean 仅在当前 HTTP request 内有效。
- **session** （仅 Web 应用可用） : 每一次来自新 session 的 HTTP 请求都会产生一个新的 bean（会话 bean），该 bean 仅在当前 HTTP session 内有效。
- **application/global-session** （仅 Web 应用可用）：每个 Web 应用在启动时创建一个 Bean（应用 Bean），该 bean 仅在当前应用启动时间内有效。
- **websocket** （仅 Web 应用可用）：每一次 WebSocket 会话产生一个新的 bean。
默认作用域是 singleton，多个线程访问同一个 bean 时会存在线程不安全问题。
**保障线程安全方法：**
1. 在 Bean 对象中尽量避免定义可变的成员变量（不太现实）；
2. 在类中定义⼀个 ThreadLocal 成员变量，将需要的可变成员变量保存在 ThreadLocal 中；
**ThreadLocal:** 
每个线程中都有一个自己的 ThreadLocalMap 类对象，可以将线程自己的对象保持到其中，各管各的，线程可以正确的访问到自己的对象。
将一个共用的 ThreadLocal 静态实例作为 key，将不同对象的引用保存到不同线程的 ThreadLocalMap 中，然后**在线程执行的各处通过这个静态 ThreadLocal 实例的 get（）方法取得自己线程保存的那个对象**，避免了将这个对象作为参数传递的麻烦。
**回答：**
Bean 的作用域就是 Spring 容器创建和管理 Bean 实例的范围。
- 最常用的是 singleton，这是默认的，整个 Spring 容器里只创建一个 Bean 实例，不管你获取多少次都是同一个。
- 然后是 prototype，每次从容器里获取 Bean 的时候都会创建一个新的实例，用完就不管了。
- 在 web 应用里还有几个常用的，request 作用域就是每个 HTTP 请求会创建一个 Bean 实例，请求处理完就销毁；session 作用域是每个用户会话对应一个 Bean 实例，用户会话结束就销毁；application 作用域是整个 web 应用共享一个 Bean 实例，跟 web 应用的生命周期一致。
这些作用域根据实际需要选，比如无状态的 Bean 用 singleton，有状态的可能用 prototype 或者 request、session。
### Bean 是线程安全的吗？
**分析：**
Spring 框架中的 Bean 是否线程安全，取决于其作用域和状态。
我们这里以最常用的两种作用域 prototype 和 singleton 为例介绍。几乎所有场景的 Bean 作用域都是使用默认的 singleton ，重点关注 singleton 作用域即可。
prototype 作用域下，每次获取都会创建一个新的 bean 实例，不存在资源竞争问题，所以不存在线程安全问题。singleton 作用域下，IoC 容器中只有唯一的 bean 实例，可能会存在资源竞争问题（取决于 Bean 是否有状态）。如果这个 bean 是有状态的话，那就存在线程安全问题（有状态 Bean 是指包含可变的成员变量的对象）。
不过，大部分 Bean 实际都是无状态（没有定义可变的成员变量）的（比如 Dao、Service），这种情况下， Bean 是线程安全的。
对于有状态单例 Bean 的线程安全问题，常见的有两种解决办法：
1. 在 Bean 中尽量避免定义可变的成员变量。
2. 在类中定义一个 `ThreadLocal` 成员变量，将需要的可变成员变量保存在 `ThreadLocal` 中（推荐的一种方式）。
**回答：**
Bean 是不是线程安全的，得看具体情况。Spring 里的 Bean 默认是单例的，就是整个应用里只有一个实例，多个线程会共用这个实例。如果单例 Bean 里有成员变量，而且这些变量会被修改，那多个线程同时操作的时候就可能出问题，比如两个线程改同一个变量，数据可能不对。
但如果单例 Bean 是无状态的，就是没有成员变量，或者成员变量只读，那多个线程调用也没事，因为没有能改的共享数据。
另外，如果 Bean 是原型作用域，每次用的时候都会创建新的实例，每个线程用自己的实例，这种情况一般就不会有线程安全问题。所以总的来说，Bean 是否线程安全，主要看它的作用域和有没有共享的可变状态。
### 将⼀个类声明为 Spring 的 Bean 的注解有哪些？
**分析：**
我们⼀般使⽤ @Autowired 注解⾃动装配 bean，要想把类标识成可⽤于 @Autowired 注解⾃动装配 
的 bean 的类，采⽤以下注解可实现： 
- @Component ：通⽤的注解，可标注任意类为 Spring 组件。如果⼀个 Bean 不知道属于哪个层， 可以使⽤@Component 注解标注。 
- @Repository : 对应持久层即 Dao 层，主要⽤于数据库相关操作。 
- @Service : 对应服务层，主要涉及⼀些复杂的逻辑，需要⽤到 Dao 层。 
- @Controller : 对应 Spring MVC 控制层，主要⽤户接受⽤户请求并调⽤ Service 层返回数据给前端⻚⾯
**回答：**
在 Spring 里，把类声明成 Bean 常用的注解主要有这么几个。最基础的是@Component，一般通用类用这个注解就行。
然后有三个专门的衍生注解，@Controller 用在控制器层处理请求，@Service 用在服务层处理业务逻辑，@Repository 用在数据访问层操作数据库，这三个其实和@Component 作用一样，只是更明确对应的层，方便代码维护。
另外还有@Bean 注解，这个通常在@Configuration 配置类里用，手动定义 Bean，比如引入第三方类的时候就用@Bean 来声明。这些注解加在类上或者配置方法上，Spring 启动时会自动扫描并创建对应的 Bean 实例，管理它们的生命周期。
### 注入 Bean 的注解有哪些？
**分析：**
Spring 内置的 `@Autowired` 以及 JDK 内置的 `@Resource` 和 `@Inject` 都可以用于注入 Bean。
<sheet sheet-id="gwNfpN" token="RCAssZRnlhFO0htbnKSc0igsnss"></sheet>
`@Autowired` 和`@Resource`使用的比较多一些。
**回答：**
注入 Bean 常用的注解主要有@Autowired、@Resource 和@Inject 这几个。
- @Autowired 是 Spring 自带的，用得最多，默认按类型去容器里找 Bean 来注入，如果同一个类型有多个 Bean，就得搭配@Qualifier 注解指定具体名称。
- @Resource 是 JDK 自带的注解，不属于 Spring，它默认按名称匹配，找不到的话才会按类型，也可以直接用 name 属性指定要注入的 Bean 名称。
- @Inject 是 Java EE 的注解，功能和@Autowired 差不多，也是按类型注入，但需要导入相关依赖，要是有多个同类型 Bean，得用@Named 注解来指定名称。
这几个就是平时开发里最常用的注入 Bean 的注解了。
### @Autowired 底层的实现原理是什么？
**分析：**
**Spring 中的 @Autowired 注解是通过依赖注入（DI）实现的**，依赖注入是一种设计模式，它将对象的创建和依赖关系的管理从应用程序代码中分离出来，使得应用程序更加灵活和可维护。
具体来说，当 Spring 容器启动时，它会扫描应用程序中的所有 Bean，并将它们存储在一个 BeanFactory 中。当应用程序需要使用某个 Bean 时，Spring 容器会自动将该 Bean 注入到应用程序中。
但再往底层说，DI 是通过 Java 反射机制实现的。具体来说，当 Spring 容器需要注入某个 Bean 时，它会使用 Java 反射机制来查找符合条件的 Bean，并将其注入到应用程序中。
所以说，@Autowired 注解是通过 DI 的方式，底层通过 Java 的反射机制来实现的。
**回答：**
@Autowired 是 Spring 实现依赖注入的注解，底层主要靠 IOC 容器和后置处理器来实现。
当 Spring 启动时，IOC 容器会先初始化所有 Bean。在 Bean 创建过程中，会有个属性注入的阶段，这时候 AutowiredAnnotationBeanPostProcessor 这个后置处理器就会工作。它会扫描 Bean 里带有@Autowired 注解的字段或方法，然后去容器里找需要注入的 Bean。查找的时候先按类型匹配，如果容器里有多个同类型的 Bean，再按属性名或方法参数名去匹配名称。找到对应的 Bean 后，就把它注入到当前 Bean 的属性或方法参数里。如果注解的 required 属性是 true（默认），找不到匹配的 Bean 就会报错；如果是 false，找不到就留空。整个过程就是通过后置处理器在 Bean 初始化阶段完成依赖的查找和注入。
### 说说@Autowired 和@Resource 注解的区别？
**分析：**
- `@Autowired` 是 Spring 提供的注解，`@Resource` 是 JDK 提供的注解。
- `Autowired` 默认的注入方式为`byType`（根据类型进行匹配），`@Resource`默认注入方式为 `byName`（根据名称进行匹配）。
- 当一个接口存在多个实现类的情况下，`@Autowired` 和`@Resource`都需要通过名称才能正确匹配到对应的 Bean。`Autowired` 可以通过 `@Qualifier` 注解来显式指定名称，`@Resource`可以通过 `name` 属性来显式指定名称。
- `@Autowired` 支持在构造函数、方法、字段和参数上使用。`@Resource` 主要用于字段和方法上的注入，不支持在构造函数或参数上使用。
**回答：**
@Autowired 和@Resource 都是依赖注入用的，但有几个主要区别。
首先来源不一样，@Autowired 是 Spring 自带的注解，@Resource 是 JDK 自带的。注入方式上，@Autowired 默认按类型匹配 Bean，如果有多个相同类型的 Bean，就得配合@Qualifier 注解指定具体名称；@Resource 默认先按名称匹配，找不到的话再按类型，也能直接用 name 属性指定要注入的 Bean 名称。
另外，@Autowired 有个 required 属性，设为 false 时找不到 Bean 也不会报错，@Resource 没有这个属性，但可以通过 name 或 type 指定具体要注入的 Bean。主要区别就是来源和注入时的匹配方式不同。
### 什么是 Spring 的三级缓存？
**分析：**
 Spring 的三级缓存，一般指的是 Spring 单例注册表 `DefaultSingletonBeanRegistry` 中的三个本地缓存：
- **一级缓存 singletonObjects**：用于存放已经完成初始化的单例 Bean。
- **二级缓存 earlySingletonObjects**：用于存放在某个 Bean 初始化过程中，由于循环依赖而在真正完成初始化之前就提前保留的早期 Bean。
- **三级缓存 singletonFactories**：用于存放生成代理对象的临时代理工厂，当出现循环依赖的时候，如果有 Bean 需要生成代理，则会从此获取代理对象，并暴露到二级缓存中。
当创建 Bean 的时候，三级缓存分别在三个阶段被使用到：
- 当创建 Bean 实例后，Spring 会通过 `addSingletonFactory` 方法**向三级缓存添加一个 ObjectFactory**，而当调用它的 getObject 方法时，最终将会调用 `getEarlyBeanReference` 方法创建一个代理对象。
- 当对 Bean 进行依赖注入和初始化时，如果存在循环依赖，那么这个阶段 Spring 会从三级缓存中获取 ObjectFactory 并创建代理对象，并在此后将获得的代理对象添加到二级缓存，然后将 ObjectFactory 从三级缓存删除。
- 若 Bean 存在循环依赖，那么当 Bean 完成初始化后，将会主动调用一次 `getSingleton` 从二级缓存中获取代理对象，然后返回给调用方，此后该 Bean 将会被重新注册到一级缓存中，此时 Spring 会主动清除二级和三级缓存。
**回答：**
Spring 的三级缓存主要是用来解决 Bean 循环依赖问题的。一级缓存叫 singletonObjects，放的是完全初始化好的单例 Bean；二级是 earlySingletonObjects，存的是提前暴露的还没完全初始化的 Bean；三级是 singletonFactories，放的是 Bean 工厂，能生成提前暴露的 Bean 引用。
比如 A 依赖 B，B 又依赖 A 这种循环依赖场景。Spring 创建 A 时，先实例化 A，这时候 A 还没完全初始化，就把 A 的工厂放到三级缓存里。接着 A 要填充属性，发现依赖 B，就去创建 B。B 实例化后填充属性，发现依赖 A，这时候就从三级缓存拿 A 的工厂，生成 A 的早期引用，放到二级缓存，然后 B 就能用这个早期 A 完成初始化，之后 B 被放到一级缓存。这时候 A 继续初始化，等 A 完全初始化好，就从二级缓存移到一级缓存里。这样就解决了循环依赖问题。
### 为什么需要 Spring 的三级缓存？
**分析：**
由于 Spring 的代理发生在 Bean 初始化完成后，当存在循环依赖时，由于进行依赖注入的 Bean 尚未初始化，因此被进行依赖注入的 Bean 实际上获得的是尚未代理的原始 Bean，此时对其来说被依赖 Bean 的 AOP 实际上是失效的。
为了解决这个问题，**Spring 需要让尚未初始化的 Bean 在进行依赖注入时也能够被代理**，因此需要**尽可能早的为其指定代理方法**，不过由于并不是所有的 Bean 都需要进行代理，并且生成代理的过程本身也会触发相关 Bean 的加载（比如 Advice 与 Advisor 等相关组件），所以这个**真正创建代理对象的时机又要尽可能的延迟**。
出于这种“提前占位，延迟代理”的原则，Spring 最终选择通过 ObjectFactory 来实现创建代理这个操作的延迟执行，并额外增加了第三级缓存来保存 ObjectFactory，通过让第二级缓存只允许通过第三级缓存获取数据的方式，保证了在当通过依赖注入获取一个尚未完成初始化的 Bean 时，也能正确的获取到被代理的 Bean。
**回答：**
Spring 三级缓存主要是解决 Bean 的循环依赖问题。一级缓存放已经完全初始化好的 Bean，二级缓存放提前暴露的实例，三级缓存放 Bean 工厂。
当出现循环依赖，比如 A 依赖 B、B 又依赖 A 时，A 实例化后会先放进三级缓存的工厂里。B 创建时需要 A，就从三级缓存拿工厂生成 A 的早期对象，如果 A 需要 AOP 代理，这里会直接生成代理对象，然后放到二级缓存，B 就能用这个早期对象完成初始化。之后 A 再用 B 完成自己的初始化，最后放进一级缓存。
要是没有三级缓存，直接用二级缓存存原始对象，后面 A 生成代理的话，B 拿到的还是原始对象就不对了。所以三级缓存是为了在循环依赖时正确处理 AOP 代理，保证依赖的对象是正确的。
### Spring 如何解决循环依赖问题？
**分析：**
Spring 循环依赖问题指的是在 Spring 容器中出现相互依赖的情况，即两个或多个 Bean 之间相互依赖，形成了一个循环依赖链。例如，Bean A 依赖 Bean B，Bean B 又依赖 Bean A，这就构成了一个循环依赖。
Spring 是通过三级缓存解决循环依赖问题的，基本思路是：在 Bean 创建过程中，将正在创建的 Bean 对象放入一个专门用于缓存正在创建中的 Bean 对象的缓存池中，当后续创建其他 Bean 对象时，若需要依赖于该缓存池中正在创建的 Bean，则直接使用缓存池中的 Bean 对象，而不是重新创建一个新的 Bean 对象。
具体而言，Spring 通过三级缓存解决循环依赖问题的步骤如下：
1. Spring 在创建 Bean 对象时，首先从一级缓存（singletonObjects）中查找是否存在已经创建完成的 Bean 对象，若存在则直接返回该 Bean 对象；
2. 若一级缓存中不存在该 Bean 对象，则从二级缓存（earlySingletonObjects）中查找是否存在该 Bean 对象的早期对象（还没进行属性填充和初始化的半成品对象），若存在则返回早期对象；
3. 若二级缓存中也不存在该 Bean 对象的早期对象，则将正在创建的 Bean 对象放入三级缓存（singletonFactories）中，并在创建过程中进行依赖注入，即为该 Bean 对象注入依赖的其他 Bean 对象。此时，如果其他 Bean 对象中依赖了正在创建的 Bean 对象，Spring 将直接从三级缓存中获取正在创建的 Bean 对象，而不是重新创建一个新的 Bean 对象。
4. 当 Bean 对象创建完成后，Spring 将其从三级缓存中移除，并将其加入一级缓存中，以便下次获取该 Bean 对象时直接从一级缓存中获取。
推荐阅读：
- [Spring 是如何解决循环依赖的？ ](https://www.zhihu.com/question/438247718/answer/1908173247)
- [一文详解 Spring Bean 循环依赖](https://mp.weixin.qq.com/s/dSRQBSG42MYNa992PvtnJA)
**回答：**
Spring 解决循环依赖主要靠三级缓存机制。当创建单例 Bean 时，会先实例化对象，这时候对象还没完成属性注入和初始化，就先把它的早期对象放到缓存里。比如 A 依赖 B，B 又依赖 A，创建 A 时，A 实例化后先放缓存，接着去创建 B；B 实例化后也放缓存，B 需要注入 A 时，直接从缓存拿 A 的早期对象，B 就能完成初始化；然后 A 再拿到 B 的实例继续完成自己的属性注入和初始化，最后把完整的 A 放到单例池。这样 A 和 B 就都创建好了。不过这只对单例 Bean 有效，原型 Bean 每次创建都是新的，缓存里拿不到，所以解决不了循环依赖。
### Spring 中可以出现两个 ID 相同的 bean 吗，如果不行会在什么时候报错
**分析：**
分情况，同一个 spring 配置文件里不能存在 id 相同的 bean，会在解析 xml 文件转换为 BeanDefinition 阶段报错。不同的 spring 配置文件里可以存在 id 相同的两个 bean，默认会把多个 id 相同的 bean 进行覆盖，spring 3.x 版本后使用 @Configuration 进行配置的时候，同一个配置类中使用 @Bean 声明多个相同名字的 bean 默认只会注册第一个，使用 @Autowired  可能会提示找不到未注册的类，使用 @Resource 注解会在 bean 初始化之后依赖注入的时候可能会提示类型不匹配错误
**推荐阅读**
[Spring 中，有两个 id 相同的 bean，会报错吗，如果会报错，在哪个阶段报错](https://zhuanlan.zhihu.com/p/532865580)（文字）
[Spring 中有两个 id 相同的 bean，会报错吗](https://www.bilibili.com/video/BV1WM4y167zi/)（视频）
**回答：**
Spring 里不能有两个 ID 相同的 bean，因为 ID 是容器中标识 bean 的唯一标识，重复的话容器无法区分。这种情况会在容器初始化阶段报错，也就是项目启动的时候。不管是 XML 配置里定义的 bean，还是用注解比如@Component、@Bean 创建的 bean，只要 ID 重复了，启动时容器加载配置文件或者扫描组件的过程中就会检测到，然后抛出异常，导致启动失败。所以开发时必须保证每个 bean 的 ID 是唯一的，避免出现这种重复定义的情况。
### Spring 提供了哪些配置⽅式？ 
**分析：**
将 Spring 配置到应用开发中有以下三种方式：
- 基于 XML 的配置
- 基于注解的配置
- 基于 Java 的配置
具体介绍：
- 基于 xml 配置 
bean 所需的依赖项和服务在 XML 格式的配置⽂件中指定。这些配置⽂件通常包含许多 bean 定义和特 
定于应⽤程序的配置选项。它们通常以 bean 标签开头。例如： 
```XML
<bean id="studentbean" class="org.edureka.firstSpring.StudentBean"> 
    <property name="name" value="Edureka"></property> 
</bean> 
```
- 基于注解配置 
可以通过在相关的类，⽅法或字段声明上使⽤注解，将 bean 配置为组件类本身，⽽不是使⽤ XML 来描述 bean 装配。默认情况下，Spring 容器中未打开注解装配。因此，您需要在使⽤它之前在 Spring 
配置⽂件中启⽤它。例如： 
```XML
<beans> 
    <context:annotation-config/>
    <!-- bean definitions go here -->
</beans> 
```
- 基于 Java API 配置 
Spring 的 Java 配置是通过使⽤ @Bean 和 @Configuration 来实现。 
1. @Bean 注解扮演与 <bean /> 元素相同的⻆⾊。 
2. @Configuration 类允许通过简单地调⽤同⼀个类中的其他 @Bean ⽅法来定义 bean 间依赖关系。 
例如： 
```Java
@Configuration 
public class StudentConfig { 
    @Bean 
    public StudentBean myStudent() { 
        return new StudentBean(); 
    } 
} 
```
**回答：**
Spring 主要有三种配置方式。最早常用的是 XML 配置，就是通过 XML 文件定义 Bean，像设置 id、class 这些属性，还有依赖注入用 property 标签来配置。后来出了注解配置，Spring 2.5 之后支持的，用@Component、@Service 这些注解标记类，再用@ComponentScan 指定扫描路径，依赖注入就用@Autowired，不用写 XML 了，开发起来更方便。
还有 Java 配置，Spring 3.0 引入的，用@Configuration 注解类，里面用@Bean 注解方法来定义 Bean，依赖注入可以直接在方法参数里传需要的 Bean，或者用@Autowired。现在项目里注解和 Java 配置用得比较多，XML 配置相对少了，不过老项目可能还会看到。这三种方式各有场景，实际开发里可能会混合用，但主流是注解和 Java 配置。
### 什么是 Spring 的内部 bean？
**分析：**
只有将 bean 用作另一个 bean 的属性时，才能将 bean 声明为内部 bean。为了定义 bean，Spring 的基于 XML 的配置元数据在 `<property>` 或 `<constructor-arg>` 中提供了 `<bean>` 元素的使用。内部 bean 总是匿名的，它们总是作为原型。
例如，假设我们有一个 Student 类，其中引用了 Person 类。这里我们将只创建一个 Person 类实例并在 Student 中使用它。
Student.java
```Java
public class Student {
    private Person person;
    //Setters and Getters
}
public class Person {
    private String name;
    private String address;
    //Setters and Getters
}
```
bean.xml
```XML
an id=“StudentBean" class="com.edureka.Student">
    <property name="person">
        <!--This is inner bean -->
        <bean class="com.edureka.Person">
            <property name="name" value=“Scott"></property>
            <property name="address" value=“Bangalore"></property>
        </bean>
    </property>
</bean>
```
**回答：**
Spring 的内部 bean 其实就是定义在另一个 bean 里面的 bean，主要用来给外部 bean 做属性注入的。这种 bean 只能被包含它的那个外部 bean 使用，容器里其他 bean 是引用不到的。所以定义内部 bean 的时候不用设置 id 或者 name，因为它不需要被外部查找。
一般在某个 bean 只被另一个特定 bean 依赖，没必要单独定义成顶级 bean 的时候用，这样能让配置更简洁，也避免创建多余的全局 bean。
比如一个 UserService 需要一个 UserDao，而这个 UserDao 只给 UserService 用，那就可以在 UserService 的 bean 配置里定义这个 UserDao 作为内部 bean，不用单独配个顶级的 UserDao bean 了。
简单说，内部 bean 就是满足局部依赖的，减少不必要的全局 bean 定义，让配置更清爽。
## Spring AOP
### 什么是 AOP？
**分析：**
AOP（Aspect-Oriented Programming），即面向切面编程，它与 OOP（ Object-Oriented Programming， 面向对象编程） 相辅相成， 提供了与 OOP 不同的抽象软件结构的视角。
在 OOP 中， 我们以类（class）作为我们的基本单元，而 AOP 中的基本单元是 Aspect（切面）。
具体是含义可以理解为：通过代理的⽅式，在调⽤想要的对象⽅法时候，进⾏拦截处理，执⾏切⼊的逻辑，然后再调⽤真正的⽅法实现。 
例如，你实现了⼀个 A 对象，⾥⾯有 addUser ⽅法，此时你需要记录该⽅法的调⽤次数。 那么你就可以搞个代理对象，这个代理对象也提供了 addUser ⽅法，最终你调⽤的是代理对象的 addUser ，在这个代理对象内部填充记录调⽤次数的逻辑，最终的效果就类似下⾯代码： 
```Java
class A代理 { 
    A a;// 被代理的 A 
    void addUser(User user) { 
        count();// 计数 
        a.addUser(user); 
    } 
}
最终使⽤的是： 
A代理.addUser(user); 
```
这就叫做**⾯向切⾯编程**，当然具体的代理的代码不是像上⾯这样写死的，⽽是动态切⼊。 
实现上代理⼤体上可以分为：**动态代理和静态代理**。 
- 动态代理，即**在运⾏时**将切⾯的逻辑进去，按照上⾯的逻辑就是你实现 A 类，然后定义要代理的切⼊点和切⾯的实现，程序会⾃动在运⾏时⽣成类似上⾯的代理类。 
- 静态代理，**在编译时或者类加载时**进⾏切⾯的织⼊，典型的 AspectJ 就是静态代理。 
Spring AOP 就是基于动态代理的，如果要代理的对象，实现了某个接口，那么 Spring AOP 会使用 **JDK Proxy**，去创建代理对象，而对于没有实现接口的对象，就无法使用 JDK Proxy 去进行代理了，这时候 Spring AOP 会使用 **Cglib** 生成一个被代理对象的子类来作为代理，如下图所示：
当然你也可以使用 **AspectJ** ！Spring AOP 已经集成了 AspectJ ，AspectJ 应该算的上是 Java 生态系统中最完整的 AOP 框架了。
AOP 切面编程涉及到的一些专业术语：
<sheet sheet-id="3hQYZy" token="RCAssZRnlhFO0htbnKSc0igsnss"></sheet>
**回答：**
AOP 就是面向切面编程，主要解决代码复用和业务逻辑与横切关注点分离的问题。横切关注点就是那些很多业务方法都会用到的功能，比如日志记录、事务管理、权限校验这些，传统写法里这些代码会重复出现在各个业务方法里，既冗余又难维护。
AOP 的思路是把这些横切逻辑抽出来，单独写成“切面”，然后在需要的业务方法执行时，通过特定的方式把切面代码“切入”进去，比如在方法执行前、执行后或者抛出异常时执行。这样业务代码里就不用再写这些重复逻辑了，只需要关注核心业务，代码会更干净，以后要改日志或者权限逻辑，直接改切面就行，不用一个个改业务方法。
简单说，AOP 就是把通用功能集中管理，动态植入到业务流程里，减少重复代码，提高代码复用性和可维护性，让业务逻辑更纯粹。
### 谈谈你对 AOP 的理解？
**分析：**
Spring AOP（面向切面编程）是 Spring 框架中的一个重要模块，用于解决系统中的横切关注点（cross-cutting concerns）问题。所谓横切关注点，指的是系统中分散在各个模块中、与主业务逻辑无关的代码，例如日志记录、事务管理、权限控制等。
Spring AOP 采用代理模式实现，它通过在运行期间动态代理目标对象，将横切关注点织入到系统中，从而实现了业务逻辑与横切关注点的分离。Spring AOP 主要由以下几个概念组成：
1. 切面（Aspect）：切面是一个类，它包含了一组横切关注点和相应的逻辑。一个切面通常会跨越多个对象，因此它不仅定义了横切关注点，还定义了横切关注点与业务逻辑的关系。
2. 连接点（Join Point）：连接点是在程序执行期间可以插入切面的点。例如方法调用、异常抛出等。
3. 切入点（Pointcut）：切入点是一组连接点的集合，它定义了在哪些连接点上应用切面。例如所有的方法调用、所有的异常抛出等。
4. 通知（Advice）：通知是切面在特定连接点执行的代码。Spring AOP 提供了五种类型的通知：前置通知（Before）、后置通知（After）、返回通知（After-returning）、异常通知（After-throwing）和环绕通知（Around）。
5. 切面织入（Weaving）：切面织入是将切面应用到目标对象并创建代理对象的过程。
Spring AOP 通过配置文件或注解的方式来定义切面、连接点、切入点和通知等信息，并使用代理模式将切面织入到目标对象中。通过 AOP 技术，可以有效地解耦业务逻辑和横切关注点，提高了系统的可维护性和可扩展性。
**回答：**
我理解 AOP 就是面向切面编程，它是对 OOP 的一种补充。主要用来解决那些在多个业务逻辑里重复出现的代码问题，像日志记录、事务管理、权限校验这些，我们叫它们横切关注点。传统做法是在每个业务方法里都写这些代码，会很冗余还不好维护。
AOP 能把这些重复逻辑抽出来单独写，然后通过切面切入点这些机制，在指定的业务方法执行前后自动插入这些逻辑，这样业务代码里就只需要关注核心功能了。好处是代码复用性提高，维护起来也方便，比如要改日志格式，直接改抽出来的那个切面就行，不用一个个改业务方法。实际开发里常用在记录接口调用日志、控制事务提交回滚、检查用户登录权限这些场景。
### AOP 有哪些实现方式？
**分析：**
AOP 有两种实现方式：静态代理和动态代理。
- **静态代理**：代理类在编译阶段生成，在编译阶段将通知织入 Java 字节码中，也称编译时增强。AspectJ 使用的是静态代理。缺点：代理对象需要与目标对象实现一样的接口，并且实现接口的方法，会有冗余代码。同时，一旦接口增加方法，目标对象与代理对象都要维护。
- **动态代理**：代理类在程序运行时创建，AOP 框架不会去修改字节码，而是在内存中临时生成一个代理对象，在运行期间对业务方法进行增强。动态代理主要有两种实现方式： 
  1. **JDK 动态代理**：JDK 动态代理要求被代理的类必须实现一个接口，它通过反射来接收被代理的类，并使用 InvocationHandler 接口和 Proxy 类实现代理。
  2. **CGLIB 动态代理**：CGLIB 则是一个代码生成的类库，它可以在运行时动态地生成某个类的子类，通过继承的方式实现代理。如果目标类没有实现接口，Spring AOP 会选择使用 CGLIB 来动态代理目标类。
**回答：**
AOP 实现方式主要有静态代理和动态代理。静态代理是编译期织入代码，比如 AspectJ 的静态织入，代理类提前生成，性能好但不够灵活。动态代理是运行时生成代理对象，分 JDK 动态代理和 CGLIB 动态代理。
JDK 动态代理需要目标类实现接口，通过反射生成代理类；CGLIB 不用接口，继承目标类生成子类代理，覆盖方法加逻辑。动态代理更灵活，运行时开销稍大，实际开发中用得多，像 Spring AOP 默认用 JDK 动态代理，没接口时用 CGLIB。
### Spring AOP and AspectJ AOP 有什么区别？
**分析：**
**Spring AOP 属于运行时增强，而 AspectJ 是编译时增强。** Spring AOP 基于代理（Proxying），而 AspectJ 基于字节码操作（Bytecode Manipulation）。
Spring AOP 已经集成了 AspectJ ，AspectJ 应该算的上是 Java 生态系统中最完整的 AOP 框架了。AspectJ 相比于 Spring AOP 功能更加强大，但是 Spring AOP 相对来说更简单，
如果我们的切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择 AspectJ ，它比 Spring AOP 快很多。
<sheet sheet-id="U56Ufq" token="RCAssZRnlhFO0htbnKSc0igsnss"></sheet>
**回答：**
Spring AOP 和 AspectJ AOP 都是实现 AOP 的框架，但它们的底层实现和适用场景不一样。Spring AOP 主要基于动态代理，在运行时通过生成代理对象来织入横切逻辑，它只能对方法级别的连接点进行增强，比如方法执行前后这些场景。而且它是 Spring 框架自带的，配置起来比较简单，一般用@Aspect 注解就能定义切面，适合 Spring 项目里做一些简单的横切处理，比如日志、事务管理这些。
AspectJ 就不一样了，它是静态织入的，在编译期或者类加载期就把横切代码织到目标类里，所以功能更强，能支持的连接点更多，不光是方法，字段访问、构造器调用这些都能切。不过它需要专门的编译器，配置和学习成本也高一些，适合那些需要复杂 AOP 功能的场景。总的来说，Spring AOP 轻量简单，适合 Spring 生态内的常规需求；AspectJ 功能全面但复杂，适合更深入的 AOP 应用。
### AspectJ 定义的通知类型有哪些？
**分析：**
- Before - 这些类型的 Advice 在 joinpoint 方法之前执行，并使用 @Before 注解标记进行配置。
- After Returning - 这些类型的 Advice 在连接点方法正常执行后执行，并使用@AfterReturning 注解标记进行配置。
- After Throwing - 这些类型的 Advice 仅在 joinpoint 方法通过抛出异常退出并使用 @AfterThrowing 注解标记配置时执行。
- After （finally） - 这些类型的 Advice 在连接点方法之后执行，无论方法退出是正常还是异常返回，并使用 @After 注解标记进行配置。
- Around - 这些类型的 Advice 在连接点之前和之后执行，并使用 @Around 注解标记进行配置。
**回答：**
AspectJ 定义的通知类型主要有五种。
- 首先是 Before 通知，就是在目标方法执行之前执行的代码，能在方法运行前做些准备工作。
- 然后是 After 通知，不管目标方法有没有异常，执行完都会触发，一般用来做清理操作。
- AfterReturning 通知是目标方法正常返回结果后才会执行，可以拿到方法的返回值来处理。
- AfterThrowing 通知刚好相反，只有目标方法抛出异常时才会执行，能捕获异常信息。
- 最后是 Around 通知，它能包裹目标方法，控制方法的执行过程，比如可以决定要不要执行目标方法，或者在执行前后添加逻辑，甚至修改返回值，功能比较全面。这五种通知覆盖了方法执行的不同阶段，能满足各种切面需求。
### 动态代理了解吗？
**分析：**
Java 动态代理是 Java 中一种重要的代理模式，它允许在运行时动态地生成代理类和对象，无需编写静态代理类。
在 Java 中，动态代理可以通过 Java 自带的两种方式实现：**基于接口的动态代理（JDK 动态代理）和基于类的动态代理（CGLIB 动态代理）**。
1. **基于接口的动态代理（JDK 动态代理）**
基于接口的动态代理是 Java 官方提供的一种动态代理实现方式。在这种实现方式中，代理类必须实现一个或多个接口，然后在运行时动态创建代理对象。JDK 中提供了一个 Proxy 类和一个 InvocationHandler 接口来实现基于接口的动态代理。
首先，需要定义一个实现 InvocationHandler 接口的代理类，该类实现了代理类的逻辑。这个类中有一个 invoke 方法，这个方法在代理类的方法被调用时被执行。在运行时通过 Proxy 类的静态方法 newProxyInstance 生成代理类对象。这个方法需要三个参数：ClassLoader、代理类需要实现的接口数组和 InvocationHandler 实现类的实例。当通过代理类对象调用方法时，这个方法首先被转发到 InvocationHandler 的 invoke 方法中。在 invoke 方法中，可以根据代理类方法的不同来执行不同的逻辑，包括调用被代理对象的方法和执行其他的逻辑。最终，代理类的方法被执行完毕，返回结果。
1. **基于类的动态代理（CGLIB 动态代理）**
基于类的动态代理是通过字节码生成技术实现的。在这种实现方式中，代理类不需要实现接口，而是通过继承一个已有的类来实现代理功能。在 Java 中，可以通过 CGLIB 库实现基于类的动态代理。
CGLIB（Code Generation Library）是一个高性能的代码生成库，它可以在运行时动态生成字节码来实现类的增强功能。通过 CGLIB 库，可以直接在运行时创建目标对象的子类，从而实现基于类的动态代理。
基于类的动态代理相比于基于接口的动态代理，可以代理那些没有实现任何接口的类，更加灵活。但是它的实现原理比较复杂，需要在运行时动态生成字节码，会带来一定的性能开销。
**回答：**
动态代理就是在程序运行时动态生成代理对象的技术，不用提前手写代理类。主要作用是给目标对象的方法增加额外功能，比如加日志记录、权限检查或者事务控制这些。
常见实现方式有两种，JDK 动态代理和 CGLIB。JDK 动态代理要求目标类必须实现接口，通过 Proxy 类和 InvocationHandler 接口生成代理对象，调用方法时会走 InvocationHandler 的 invoke 方法。
CGLIB 通过继承目标类生成子类作为代理，不需要目标类实现接口，更灵活些。平时用得多的场景像 Spring 的 AOP，比如事务管理，就是用动态代理在目标方法执行前后加事务的开启和提交。这样不用改原来的代码就能增强功能，挺方便的。
### **JDK 动态代理和 CGLIB 动态代理区别是什么？**
**分析：**
JDK 代理和 CGLib 代理都是 Spring 默认支持的代理模式，它们的区别如下：
- **代理对象**：JDK 代理只支持面向接口代理，而 CGLib 代理除了接口外，也可以面向普通的类进行代理。
- **实现原理**：JDK 代理是生成接口的匿名实现类，而 CGLib 则还可以生成目标类的子类。
- **拦截方法**：JDK 代理只支持拦截接口中的公共抽象方法，而 CGLib 支持拦截任何非私有的实例方法
- **内部调用支持**：JDK 代理不支持代理内部调用，而 CGLib 支持，理由同上一点。
在默认情况下，Spring 会优先使用 JDK 代理，不过如目标类没有实现一个公共接口，那就会基于 CGLib 进行代理。此外，还有一种特殊情况，那就是基于 `@Configuration` 的配置类，在 Full 模式下，总是固定使用 CGLib 代理。
**回答：**
JDK 动态代理得让目标类实现接口，生成的代理类是接口的实现类，运行时通过反射调用目标方法。CGLIB 不用接口，它是继承目标类生成子类作为代理，重写父类方法实现代理。所以目标类有接口时常用 JDK，没接口只能用 CGLIB。性能上 JDK8 之后优化了，和 CGLIB 差不多，但 CGLIB 因为继承，目标类里的 final 方法不能被代理，因为子类重写不了 final 方法。另外 JDK 动态代理是 JDK 自带的，CGLIB 需要额外依赖包。
## Spring 注解
### 你用过哪些重要的 Spring 注解？
**分析：**
- @Controller 用于 Spring MVC 项目中的控制器类。
- @Service 用于服务类。
- @RequestMapping 用于在控制器处理程序方法中配置 URI 映射。
- @ResponseBody 用于发送 Object 作为响应，通常用于发送 XML 或 JSON 数据作为响应。
- @PathVariable 用于将动态值从 URI 映射到处理程序方法参数。
- @Autowired 用于在 spring bean 中自动装配依赖项。
- @Qualifier 使用 @Autowired 注解，以避免在存在多个 bean 类型实例时出现混淆。
- @Scope 用于配置 spring bean 的范围。
- @Configuration，@ComponentScan 和 @Bean 用于基于 java 的配置。
- @Aspect，@Before，@After，@Around，@Pointcut 用于切面编程（AOP）。
**回答：**
在实际开发中用过不少 Spring 注解，最常用的是@Component 和它的几个衍生注解，像@Service、@Controller、@Repository，这些主要是把类交给 Spring 容器管理，让 Spring 帮我们创建和管理对象，不用自己手动处理。然后依赖注入常用@Autowired，就是自动注入需要的依赖对象，简化对象创建。
配置方面会用@Configuration 标记配置类，里面用@Bean 定义 Bean 实例。AOP 开发时会用@Aspect 定义切面，再配合@Before、@After 这些注解定义通知方法。
Web 开发里@RestController 用得多，它能直接返回 JSON 数据，还有@RequestMapping 用来映射请求路径，@Value 可以获取配置文件里的属性值。这些注解基本覆盖了日常开发的主要场景，用起来很方便。
### 如何在 Spring 中启动注解装配？
**分析：**
默认情况下，Spring 容器中未打开注解装配。因此，要使用基于注解装配，我们必须通过配置`<context：annotation-config />` 元素在 Spring 配置文件中启用它。
**回答：**
在 Spring 里启动注解装配，主要得开启组件扫描。
可以通过两种方式实现，常用的是 Java 配置类，在配置类上加上@ComponentScan 注解，指定要扫描的包路径，这样 Spring 会自动扫描这个包及其子包下带@Component、@Service、@Controller、@Repository 这些注解的类，把它们注册成 Bean。另一种是 XML 配置，在配置文件里写`<context:component-scan base-package="包路径"/>`。
实际开发中更常用 Java 配置类，然后启动 Spring 容器时，用 AnnotationConfigApplicationContext 加载这个配置类，容器启动时就会执行扫描，完成注解装配。需要注意被扫描的类得加上对应的注解，Spring 才能识别并管理这些 Bean。
### @Configuration 和@Component 有什么区别？
**分析：**
`@Configuration` 是基于 `@Component` 的组合注解，它们都能实现一些类似的功能：
- 将被注解的类声明为 Spring 容器中的 Bean。
- 内部带有 `@Bean` 注解的工厂方法返回的对象，都会被视为 Spring 容器中的 Bean。
它们最主要的区别在于：
- 基于 `@Configuration` 的配置方式被称为 **Full 模式**（Full @Configuration mode）。
- 基于非 `@Configuration` 的配置方式称为 **Lite 模式**（“lite”@Bean mode）。
**回答：**
@Configuration 和@Component 都是 Spring 用来管理 Bean 的注解，但作用场景不一样。
@Component 是通用的组件注解，一般标记业务类、工具类这些普通组件，让 Spring 扫描后把它们注册成 Bean。@Configuration 主要是配置类注解，专门用来声明 Bean 定义，比如类里用@Bean 注解的方法就是定义 Bean 的。
关键区别是@Configuration 类会被 Spring 通过代理处理，所以类里@Bean 方法互相调用时，返回的还是容器里的单例 Bean；而@Component 类里的@Bean 方法没有代理，直接调用可能会创建新实例。
所以通常@Configuration 用在配置类，专门管理 Bean 的定义，@Component 用在普通业务组件，让 Spring 管理它们的生命周期。
### @Component， @Controller， @Repository， @Service 有何区别？
**分析：**
- @Component：这将 java 类标记为 bean。它是任何 Spring 管理组件的通用构造型。spring 的组件扫描机制现在可以将其拾取并将其拉入应用程序环境中。
- @Controller：这将一个类标记为 Spring Web MVC 控制器。标有它的 Bean 会自动导入到 IoC 容器中。
- @Service：此注解是组件注解的特化。它不会对 @Component 注解提供任何其他行为。您可以在服务层类中使用 @Service 而不是 @Component，因为它以更好的方式指定了意图。
- @Repository：这个注解是具有类似用途和功能的 @Component 注解的特化。它为 DAO 提供了额外的好处。它将 DAO 导入 IoC 容器，并使未经检查的异常有资格转换为 Spring DataAccessException。
**回答：**
这些都是 Spring 框架里的组件注解，主要作用是让 Spring 自动识别并管理这些类，也就是把它们变成 Bean。不过它们分工不一样，@Component 是最基础的通用注解，其他三个其实都是它的特殊版本，针对不同的业务层做了专门处理。
@Controller 专门用在控制层，像写接口的时候，处理前端发过来的 HTTP 请求，比如 GET、POST 这些，然后决定返回页面还是数据，一般配合@RequestMapping 这些注解用。
@Service 是业务逻辑层用的，放业务处理的代码，比如数据校验、调用其他服务，或者复杂的计算逻辑，通常会调用 Repository 层的方法。
@Repository 是数据访问层，就是跟数据库打交道的地方，像 MyBatis 的 Mapper 接口或者 JPA 的 Repository 接口，都用这个注解，它还能把数据库相关的异常转换成 Spring 能处理的统一异常。
简单说就是按三层架构分工，Controller 管请求，Service 管逻辑，Repository 管数据，Component 是它们的老大，通用情况用。
### **@Component 和 @Bean 有什么区别？**
**分析：**
它们的作用对象不同：@Component 作用于类，而 @Bean 注解作用于方法。
- @Component 通常是通过类路径扫描来自动侦测和装配对象到 Spring 容器中，比如 @ComponentScan 注解就是定义扫描路径中的类装配到 Spring 的 Bean 容器中；
- @Bean 注解是告诉 Spring 这是某个类的实例，当我需要用它的时把它给我，@Bean 注解比 @Component 注解自定义性更强，很多地方我们只能通过 @Bean 注解来注册 Bean，比如当我们引用第三方库中的类需要装配到 Spring 容器时，则只能通过 @Bean 来实现，比如以下示例，只能通过 @Bean 注解来实现：
```Java
public class WireThirdLibClass {
    @Beanpublic ThirdLibClass getThirdLibClass() {
        return new ThirdLibClass();
    }
}
```
**回答：**
@Component 和@Bean 都是 Spring 里注册 Bean 的方式，但用的地方和场景不一样。@Component 是加在类上的，Spring 会自动扫描这些类，帮你创建对象放到容器里，像我们自己写的 Service、Controller 类，直接在类上标@Component 就行，不用手动配。
@Bean 是加在方法上的，这个方法返回的对象会被 Spring 管理，一般用在第三方库的类或者需要自己写创建逻辑的时候，比如配数据库连接池，你得写个方法 new 出对象，再用@Bean 标这个方法，Spring 就会把返回的对象当 Bean。
另外@Component 是靠扫描自动注册的，@Bean 通常要写在@Configuration 类里，通过配置类的方法来生成 Bean。总的来说，自己的类用@Component 方便，需要手动控制创建过程的用@Bean。
### @Required 注解有什么用？
**分析：**
@Required 应用于 bean 属性 setter 方法。此注解仅指示必须在配置时使用 bean 定义中的显式属性值或使用自动装配填充受影响的 bean 属性。如果尚未填充受影响的 bean 属性，则容器将抛出 BeanInitializationException。
示例：
```Java
public class Employee {
    private String name;
    @Required
    public void setName(String name){
        this.name=name;
    }
    public string getName(){
        return name;
    }
}
```
**回答：**
@Required 注解是 Spring 框架里的一个注解，主要作用是标记 Bean 中的属性，必须在配置的时候被设置好，不管是通过 XML 配置文件，还是注解方式注入依赖，如果这些被标记的属性没有在配置中进行设置， Spring 在初始化 Bean 的时候就会抛出异常，这样就能强制保证这些属性在 Bean 使用之前一定被正确注入，避免后续使用过程中出现空指针异常或者其他因属性未初始化导致的问题，简单说就是确保关键属性不会漏配让依赖注入更可靠
### @Autowired 注解有什么用？
**分析：**
@Autowired 可以更准确地控制应该在何处以及如何进行自动装配。此注解用于在 setter 方法，构造器，具有任意名称或多个参数的属性或方法上自动装配 bean。默认情况下，它是类型驱动的注入。
```Java
public class Employee {
    private String name;
    @Autowired
    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
```
**回答：**
@Autowired 是 Spring 框架里用来自动注入依赖对象的注解。它的主要作用就是让 Spring 帮我们自动创建并注入需要的对象，不用在代码里手动 new，这样能减少类之间的耦合。一般用在类的成员变量、构造方法或者 setter 方法上。默认情况下，它会按照类型去容器里找对应的 bean，如果找到就直接注入。不过要是有多个相同类型的 bean，这时候就需要配合@Qualifier 注解来指定具体要注入的 bean 的名称，不然会报错。用了这个注解，代码会更简洁，不用自己写创建对象的代码，Spring 会自动管理依赖关系，方便后续维护。
### @Qualifier 注解有什么用？
**分析：**
当您创建多个相同类型的 bean 并希望仅使用属性装配其中一个 bean 时，您可以使用@Qualifier 注解和 @Autowired 通过指定应该装配哪个确切的 bean 来消除歧义。
例如，这里我们分别有两个类，Employee 和 EmpAccount。在 EmpAccount 中，使用@Qualifier 指定了必须装配 id 为 emp1 的 bean。
Employee.java
```Java
public class Employee {
    private String name;
    @Autowired
    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
```
EmpAccount.java
```Java
public class EmpAccount {
    @Autowired
    @Qualifier(emp1)
    private Employee emp;
    
    public void showName() {
        System.out.println(“Employee name : ”+emp.getName);
    }
}
```
**回答：**
@Qualifier 是 Spring 里用来解决依赖注入冲突的注解。平时用@Autowired 注入 Bean 时，如果一个接口有多个实现类，或者同一类型定义了多个 Bean 实例，Spring 就不知道该选哪个，这时候就需要@Qualifier 来指定具体用哪个 Bean。具体用法就是在@Autowired 旁边加上@Qualifier，括号里写要注入的 Bean 的名字。比如我定义了两个 UserService 的实现类，Bean 名分别是 userService1 和 userService2，注入的时候加@Qualifier（"userService1"），Spring 就会明确用这个名字对应的 Bean。这样就能避免注入时因为类型相同但实例多个导致的歧义问题，让依赖注入更准确。
### @RequestMapping 注解有什么用？
**分析：**
@RequestMapping 注解用于将特定 HTTP 请求方法映射到将处理相应请求的控制器中的特定类/方法。此注解可应用于两个级别：
- 类级别：映射请求的 URL
- 方法级别：映射 URL 以及 HTTP 请求方法
**回答：**
@RequestMapping 是 Spring MVC 里常用的注解，主要用在控制器的方法上，作用是把 HTTP 请求和这些方法对应起来。简单说就是告诉 Spring，当有某个特定的请求过来时，该调用哪个方法处理。
它最常用的是指定请求的 URL，比如在方法上写@RequestMapping（"/user"），那客户端访问"/user"这个路径时，就会触发这个方法。还能指定请求方式，像 GET 或者 POST，用 method 属性设置，比如@RequestMapping（value="/user"， method=RequestMethod.GET），就表示只处理 GET 请求的"/user"路径。
后来为了更方便，Spring 又出了@GetMapping、@PostMapping 这些注解，其实就是@RequestMapping 的简化版，比如@GetMapping（"/user"）就等于@RequestMapping（value="/user"， method=RequestMethod.GET）。这样用起来更直接，不用每次都写 method 属性。
总的来说，就是通过它把请求路径、请求方式和控制器方法绑定，让 Spring 能自动找到对应的方法处理请求，不用自己写复杂的映射逻辑。
### @RequestMapping 和 @GetMapping 注解的不同之处在哪⾥？ 
**分析：**
- @RequestMapping ：可注解在类和⽅法上； @GetMapping 仅可注册在⽅法上 
- @RequestMapping ：可进⾏GET、POST、PUT、DELETE 等请求⽅法； @GetMapping 是 @RequestMapping 的 GET 请求⽅法的特例，⽬的是为了提⾼清晰度
**回答：**
@RequestMapping 和@GetMapping 都是 Spring 里用来映射请求的注解，不过它们有几个主要区别。
首先，@RequestMapping 能处理多种 HTTP 方法，像 GET、POST、PUT 这些，得通过 method 属性指定具体是哪种，比如要处理 GET 请求就得写 method = RequestMethod.GET。
而@GetMapping 是专门用来处理 GET 请求的，相当于是@RequestMapping（method = RequestMethod.GET）的简化写法，不用再额外写 method 属性了，用起来更方便。
另外，@GetMapping 是 Spring 4.3 版本之后才有的，目的就是让代码更简洁明确，现在开发里处理 GET 请求时基本都优先用它。而@RequestMapping 因为能处理多种方法，所以如果一个接口需要支持多种请求方式，或者想灵活指定不同的 method，就会用它。
总的来说，@GetMapping 是@RequestMappin 针对 GET 请求的快捷版，更专一，@RequestMapping 则更通用，能处理多种请求类型。
### @Controller 注解有什么⽤？
**分析：**
@Controller 注解标记⼀个类为 Spring Web MVC 控制器 Controller。Spring MVC 会将扫描到 该注解的类，然后扫描这个类下⾯带有 @RequestMapping 注解的⽅法，根据注解信息，为这个⽅ 法⽣成⼀个对应的处理器对象。 
**回答：**
@Controller 注解是 Spring MVC 里常用的注解，主要作用是告诉 Spring 这个类是处理用户请求的控制器。当我们在一个类上加上这个注解，Spring 就会把它识别成 MVC 层的控制器组件，自动管理这个类的实例，不用我们手动去创建对象。
控制器类里的方法通常会配合@RequestMapping 注解一起用，用来指定具体处理哪个请求路径和请求方式，比如用户访问某个 URL 或者提交表单时，Spring 就会找到对应的控制器方法来处理。
它的核心功能就是接收前端发过来的请求，调用后面的业务逻辑代码处理数据，处理完之后再把结果返回给前端页面或者客户端，这样就完成了一次请求的处理流程。
简单说，就是负责连接前端请求和后端处理的中间角色，让请求能被正确处理并返回结果。
### @RestController 和 @Controller 有什么区别？ 
**分析：**
@RestController 注解，在 @Controller 基础上，增加了 @ResponseBody 注解，更加适合⽬前前后端分离的架构下，提供 Restful API ，返回例如 JSON 数据格式。当然，返回什么样的数据格式，根据客户端的 ACCEPT 请求头来决定。
**回答：**
@RestController 和@Controller 都是 Spring 里处理请求的注解，但用法不一样。@Controller 主要用来返回视图，就是像 JSP、HTML 这类页面，这时候得配合视图解析器一起用。
要是想用@Controller 返回 JSON 或者字符串这类数据，就得在方法上再加上@ResponseBody 注解才行。而@RestController 是@Controller 和@ResponseBody 的组合注解，用了它之后，方法不用额外加@ResponseBody，直接就能返回数据，像 JSON、XML 或者普通文本都可以，所以更适合开发 RESTful API。
简单说，@Controller 侧重返回页面，@RestController 侧重返回数据，不用多写@ResponseBody 这一步。
### @RequestParam 和 @PathVariable 两个注解的区别？
**分析：**
两个注解都⽤于⽅法参数，获取参数值的⽅式不同， @RequestParam 注解的参数从请求携带的参数中获取，⽽ @PathVariable 注解从请求的 URI 中获取 
**回答：**
@RequestParam 和@PathVariable 都是 Spring 里用来获取请求参数的注解，但用法不一样。@RequestParam 是从请求的参数部分拿值，就是 URL 里问号后面的那些 key=value，比如你访问/user?name=张三，用@RequestParam String name 就能拿到“张三”，参数名不一样的话可以用 value 指定，比如@RequestParam（value="userName"） String name。
@PathVariable 是从 URL 的路径里拿值，比如设计 RESTful 接口时常用的/user/{id}，访问/user/123 的时候，用@PathVariable Long id 就能拿到 123，这个参数是直接嵌在路径里的，不是问号后面的。
用的场景也不同，一般查询列表、带筛选条件的时候用@RequestParam，比如分页的 page、size 参数；而获取单个资源详情，比如根据 ID 查用户，就用@PathVariable 把 ID 从路径里取出来。另外它们默认都是必填的，@RequestParam 可以加 required=false 设为非必填，@PathVariable 要是路径里没这个参数，整个 URL 就不对了，会直接报 404 错误。
### 返回 JSON 格式使⽤什么注解？
**分析：**
可以使⽤ **@ResponseBody** 注解，或者使⽤包含 @ResponseBody 注解的 **@RestController** 注解。 
当然，还是需要配合相应的⽀持 JSON 格式化的 HttpMessageConverter 实现类。例如，Spring  MVC 默认使⽤ MappingJackson2HttpMessageConverter。 
**回答：**
在 Java 开发里，返回 JSON 格式最常用的注解是 Spring 框架里的@ResponseBody。这个注解一般加在控制器的方法上，作用是把方法返回的对象自动转换成 JSON 格式的数据，然后响应给前端。另外还有个更方便的注解@RestController，它其实是@Controller 和@ResponseBody 的组合注解，直接加在控制器类上，这样类里所有的方法就不用单独加@ResponseBody 了，返回的对象也会自动转成 JSON。平时开发中用@RestController 比较多，因为能少写点代码，更简洁。这两个就是最常用的返回 JSON 的注解，基本上覆盖了大部分场景。
## Spring 事务
### Spring 事务实现方式有哪些？
**分析：**
- **编程式事务**：在代码中硬编码（在分布式系统中推荐使用） : 通过 `TransactionTemplate`或者 `TransactionManager` 手动管理事务，事务范围过大会出现事务未提交导致超时，因此事务要比锁的粒度更小。
- **声明式事务**：在 XML 配置文件中配置或者直接基于注解（单体应用或者简单业务系统推荐使用） : 实际是通过 AOP 实现（基于`@Transactional` 的全注解方式使用最多）
**回答：**
Spring 事务主要有两种实现方式。一种是编程式事务，就是手动写代码控制事务，比如用 TransactionTemplate 或者直接调用 PlatformTransactionManager 的方法，手动开启、提交、回滚事务。这种方式好处是灵活，能精确控制事务范围，但代码侵入性高，现在项目里用得比较少。另一种是声明式事务，通过@Transactional 注解来实现，底层是 AOP 机制。用的时候直接在需要事务的方法上加上这个注解，Spring 会自动帮我们管理事务的开启、提交和回滚，不用写额外代码，侵入性低，现在开发里最常用。不过用声明式事务要注意，注解一般加在 public 方法上，非 static 方法，还要根据业务配置传播行为、隔离级别这些参数，但基本使用就是加个注解就行，简单方便。
### 事务的传播级别有哪些？
**分析：**
事务的传播机制定义了在方法被另一个事务方法调用时，这个方法的事务行为应该如何。
Spring 提供了一系列事务传播行为，这些传播行为定义了事务的边界和事务上下文如何在方法调用链中传播。
Spring 事务定义了 7 种传播机制：
- PROPAGATION_REQUIRED：默认的 Spring 事物传播级别，若当前存在事务，则加入该事务，若不存在事务，则新建一个事务。
- PAOPAGATION_REQUIRE_NEW：若当前没有事务，则新建一个事务。若当前存在事务，则新建一个事务，新老事务相互独立。外部事务抛出异常回滚不会影响内部事务的正常提交。
- PROPAGATION_NESTED：如果当前存在事务，则嵌套在当前事务中执行。如果当前没有事务，则新建一个事务，类似于 REQUIRE_NEW。
- PROPAGATION_SUPPORTS：支持当前事务，若当前不存在事务，以非事务的方式执行。
- PROPAGATION_NOT_SUPPORTED：以非事务的方式执行，若当前存在事务，则把当前事务挂起。
- PROPAGATION_MANDATORY：强制事务执行，若当前不存在事务，则抛出异常。
- PROPAGATION_NEVER：以非事务的方式执行，如果当前存在事务，则抛出异常。
这些事务传播机制是使用 ThreadLocal  实现的，所以，如果调用的方法是在新线程中的，事务传播会失效。
Spring 事务传播级别一般不需要定义，默认就是 PROPAGATION_REQUIRED，除非在嵌套事务的情况下需要重点了解。
**回答：**
事务传播级别就是当一个事务方法调用另一个事务方法时，事务怎么传递的规则。最常用的是 REQUIRED，被调用的方法如果当前没有事务就新建一个，如果已经有事务就直接加入进去。然后是 REQUIRES_NEW，不管当前有没有事务，被调用方法都会新建一个自己的事务，原来的事务会先挂起，等它执行完再继续。SUPPORTS 的话，就是有事务就跟着用，没有就不用事务执行。MANDATORY 要求必须在事务里调用，不然会抛异常。NEVER 正好相反，要是在事务里调用就抛异常。还有 NESTED 是嵌套事务，外层事务回滚的话内层也会跟着回滚，但内层自己回滚不会影响外层。这些就是主要的传播级别，日常开发里 REQUIRED 和 REQUIRES_NEW 用得最多。
### Spring 事务中的隔离级别有哪几种？
**分析：**
在 TransactionDefinition 接口中定义了五个表示隔离级别的常量：
- ISOLATION_DEFAULT：使用后端数据库默认的隔离界别，MySQL 默认可重复读，Oracle 默认读已提交。
- ISOLATION_READ_UNCOMMITTED：读未提交，最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读。
- ISOLATION_READ_COMMITTED：读已提交，允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
- ISOLATION_REPEATABLE_READ：可重复读，对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
- ISOLATION_SERIALIZABLE：串行化，最高的隔离级别，完全服从 ACID 的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。
**回答：**
Spring 事务隔离级别主要用来控制多个并发事务之间数据的可见性，有五种。DEFAULT 就是用数据库自己默认的隔离级别，大部分数据库默认是 REPEATABLE_READ。然后是 READ_UNCOMMITTED，这种级别下一个事务能读到另一个没提交的修改，可能出现脏读。READ_COMMITTED 只能读到其他事务已经提交的数据，能避免脏读，但可能有不可重复读的问题，就是同一事务里两次读同一数据结果不一样。REPEATABLE_READ 保证同一事务多次读同一数据结果一致，避免不可重复读，但可能出现幻读，就是查询时多了之前没有的记录。SERIALIZABLE 是最高级别，事务串行执行，能避免所有并发问题，但性能比较低，一般很少用。实际开发里常用的是 READ_COMMITTED 和 REPEATABLE_READ，根据业务需要选。
### 声明式事务实现原理了解吗？
**分析：**
Spring 的声明式事务管理是通过 AOP（面向切面编程）和代理机制实现的。
**第一步，在 Bean 初始化阶段创建代理对象：**
Spring 容器在初始化单例 Bean 的时候，会遍历所有的 BeanPostProcessor 实现类，并执行其 postProcessAfterInitialization 方法。
在执行 postProcessAfterInitialization 方法时会遍历容器中所有的切面，查找与当前 Bean 匹配的切面，这里会获取事务的属性切面，也就是 `@Transactional` 注解及其属性值。
然后根据得到的切面创建一个代理对象，默认使用 JDK 动态代理创建代理，如果目标类是接口，则使用 JDK 动态代理，否则使用 Cglib。
**第二步，在执行目标方法时进行事务增强操作：**
当通过代理对象调用 Bean 方法的时候，会触发对应的 AOP 增强拦截器，声明式事务是一种环绕增强，对应接口为`MethodInterceptor`，事务增强对该接口的实现为`TransactionInterceptor`，类图如下：
事务拦截器`TransactionInterceptor`在`invoke`方法中，通过调用父类`TransactionAspectSupport`的`invokeWithinTransaction`方法进行事务处理，包括开启事务、事务提交、异常回滚等。
**回答：**
声明式事务主要是通过 AOP 来实现的，不用我们手动写事务控制代码，而是通过注解或者 XML 配置来声明事务规则。Spring 会帮我们生成代理对象，在目标方法执行前后进行拦截。执行前先根据配置的传播行为和隔离级别这些参数来开启事务，然后执行目标方法。如果方法正常执行完，就提交事务；要是中间抛了异常，就会回滚事务。代理方式一般是 JDK 动态代理或者 CGLIB 代理，看目标类有没有实现接口。这样我们只需要关注业务逻辑，事务管理交给框架处理，比较方便。
### **Spring 声明式事务无效可能的原因有哪些？**
**分析：**
在开发过程中，可能会遇到使用 @Transactional 进行事务管理时出现失效的情况。这里我们的讨论是基于事务的默认传播行为是 `REQUIRED`。
**常见失效场景：**
- 如果使用 MySQL 且引擎是 MyISAM，则事务会不起作用，原因是 MyISAM 不支持事务，改成 InnoDB 引擎则支持事务。
- 注解 @Trasactional 只能加在 `public` 修饰的方法上事务才起效。如果加在 `protect`、`private` 等非 `public` 修饰的方法上，事务将失效。
- 如果在开启了事务的方法内，使用了 `try-catch` 语句块对异常进行了捕获，而没有将异常抛到外层，事务将不起效。
- 在不同类之间的方法调用中，如果 A 方法开启了事务，B 方法没有开启事务，B 方法调用了 A 方法。
  - 如果 B 方法中发生异常，但不是调用的 A 方法产生的，则异常不会使 A 方法的事务回滚，此时事务无效。
  - 如果 B 方法中发生异常，异常是调用的 A 方法产生的，则 A 方法的事务回滚，此时事务有效。
  - 在 B 方法上加上注解 @Trasactional，这样 A 和 B 方法就在同一个事务里了，不管异常产生在哪里，事务都是有效的。
  - 简单地说，不同类之间方法调用时，异常发生在无事务的方法中，但不是被调用的方法产生的，被调用的方法的事务无效。只有异常发生在开启事务的方法内，事务才有效。
- 在同一个类的方法之间调用中，如果 A 方法调用了 B 方法，不管 A 方法有没有开启事务，由于 Spring 的代理机制 B 方法的事务是无效的
- 如果使用了 Spring + MVC，则 `context:component-scan` 重复扫描问题可能会引起事务失效。
**原因分析**
在应用系统调用声明 @Transactional 的目标方法时，Spring Framework 默认使用 AOP 代理，在代码运行时生成一个代理对象，再由这个代理对象来统一管理。
Spring 事务是使用 AOP 环绕通知和异常通知，就是对方法进行拦截，在方法执行前开启事务，在捕获到异常时进行事务回滚，在方法执行完成后提交事务。
**回答：**
Spring 声明式事务无效可能有这么几个常见原因。首先事务方法不是 public 的，Spring 默认只对 public 方法处理事务，非 public 方法的注解会被忽略。然后如果在同一个类里方法自己调用自己，比如方法 A 调用有@Transactional 的方法 B，这时候因为没经过代理对象，事务注解不会生效，得通过代理调用才行。事务传播行为设错了也会有问题，比如设成 NOT_SUPPORTED 或者 NEVER，这种传播行为不会开启事务。还有如果方法里把异常 try-catch 了却没抛出去，Spring 感知不到异常就不会触发回滚。数据源没配置事务管理器，或者事务管理器没正确关联数据源，事务管理根本起不了作用。另外默认只对运行时异常和 Error 回滚，如果抛的是检查异常，比如 IOException 这种，没设置 rollbackFor 属性的话事务也不会回滚。这些情况都会导致事务注解不生效。
### protected 和 private 加事务会生效吗？
**分析：**
在 Spring 中，只有通过 Spring 容器的 AOP 代理调用的公开方法（public method）上的`@Transactional`注解才会生效。
如果在 protected、private 方法上使用`@Transactional`，这些事务注解将不会生效。如果要用在非 public 方法上，可以开启 AspectJ 代理模式。
> 来自 Spring 官方文档：
> 
> When using proxies, you should apply the @Transactional annotation only to methods with public visibility. If you do annotate protected, private or package-visible methods with the @Transactional annotation, no error is raised, but the annotated method does not exhibit the configured transactional settings. Consider the use of AspectJ (see below) if you need to annotate non-public methods.
**回答：**
protected 和 private 方法加事务一般不生效。因为 Spring 事务主要靠 AOP 代理实现，代理需要能调用到目标方法才能增强事务逻辑。private 方法权限太低，代理类根本访问不到，肯定不会被事务增强。protected 方法虽然权限比 private 高，但实际开发中很少通过代理对象直接调用 protected 方法，大多是类内部用 this 调用，这时候用的是目标对象而不是代理对象，事务自然也不会生效。所以通常只有 public 方法加事务注解才会正常生效，protected 和 private 方法即使加了事务注解，基本也不会起作用。
### 加入事务和嵌套事务有什么区别？
**分析：**
在 Spring 事务管理中，加入事务（Propagation.REQUIRED）和嵌套事务（Propagation.NESTED）是两种不同的事务传播行为。
1. Propagation.REQUIRED：表示如果当前存在事务，则在当前事务中执行；如果当前没有事务，则创建一个新的事务并在其中执行。即，方法被调用时会尝试加入当前的事务，如果不存在事务，则创建一个新的事务。如果外部事务回滚，那么内部事务也会被回滚。
2. Propagation.NESTED：表示如果当前存在事务，则在嵌套事务中执行；如果当前没有事务，则创建一个新的事务并在其中执行。嵌套事务是独立于外部事务的子事务，它具有自己的保存点，并且可以独立于外部事务进行回滚。如果嵌套事务发生异常并回滚，它将会回滚到自己的保存点，而不影响外部事务。
**区别：**
- Propagation.REQUIRED 是默认的传播行为，方法调用将加入当前事务，或者创建一个新事务。
- Propagation.NESTED 是嵌套的传播行为，方法调用将在独立的子事务中执行，具有自己的保存点，可以独立于外部事务进行回滚，而不影响外部事务。
如果你希望内部方法能够独立于外部事务进行回滚，可以选择 Propagation.NESTED，如果你希望内部方法与外部事务一同回滚或提交，可以选择 Propagation.REQUIRED。
**回答：**
加入事务和嵌套事务的核心区别在于事务的范围和独立性。加入事务是指多个操作都在同一个事务里，它们共享同一个事务上下文，要么一起成功提交，要么有一个失败就全回滚。比如转账时，从 A 账户扣钱和给 B 账户加钱，这两个操作就是加入同一个事务，只要有一个没成功，俩操作都得撤销。
嵌套事务是事务里套着事务，外层事务包含内层事务。内层事务可以单独提交，但这提交只是临时的，最后还得看外层事务的结果——如果外层回滚，内层之前提交的也会跟着撤销。而且内层事务失败时，可能只回滚自己，不影响外层事务继续执行。不过实际用的时候嵌套事务场景不多，大部分情况还是用加入事务来保证操作的一致性。
## Spring MVC
### MVC 是什么？MVC 设计模式的好处有哪些
**分析：**
Spring MVC 是一个基于 Java 的实现了 MVC 设计模式的请求驱动类型的轻量级 Web 框架，通过把**模型（model）-视图（view）-控制器（controller）**分离，将 web 层进行职责解耦，把复杂的 web 应用分成逻辑清晰的几部分，简化开发，减少出错，方便组内开发人员之间的配合。
流程步骤：
1. 用户通过 View 页面向服务端提出请求，可以是表单请求、超链接请求、AJAX 请求等；
2. 服务端 Controller 控制器接收到请求后对请求进行解析，找到相应的 Model，对用户请求进行处理 Model 处理；
3. 将处理结果再交给 Controller（控制器其实只是起到了承上启下的作用）；
4. 根据处理结果找到要作为向客户端发回的响应 View 页面，页面经渲染后发送给客户端。
**MVC 设计模式的好处：**
- 分层设计，实现了业务系统各个组件之间的解耦，有利于业务系统的可扩展性，可维护性。
- 有利于系统的并行开发，提升开发效率。
**回答：**
MVC 是一种设计模式，把软件分成模型视图控制器三个部分。模型负责处理数据和业务逻辑，比如数据的存储、验证这些核心功能；视图就是用户看到的界面，负责展示数据；控制器用来接收用户的输入，然后调用模型处理，再把结果给视图显示。
这样分的好处主要是职责清楚，各部分干各的活。比如改界面的时候只动视图部分，不用动模型里的业务逻辑，维护起来方便。而且代码能复用，比如不同的视图可以用同一个模型的数据。多人开发的时候也方便，有人专门写模型处理数据，有人做界面设计，不用挤在一起改同一段代码，效率更高。整体上让代码结构更清晰，后期改东西或者加功能也简单。
### **Spring MVC 常用的注解有哪些？**
**分析：**
- @RequestMapping：用于处理请求 url 映射的注解，可用于类或方法上。用于类上，则表示类中的所有响应请求的方法都是以该地址作为父路径。
- @RequestBody：注解实现接收 http 请求的 json 数据，将 json 转换为 java 对象。
- @ResponseBody：注解实现将 conreoller 方法返回对象转化为 json 对象响应给客户。
- @Controller：控制器的注解，表示是表现层，不能用用别的注解代替
**回答：**
Spring MVC 常用的注解其实不多，主要有这么几个。@Controller 是最基础的，用来标记一个类是控制器，让 Spring 能识别它处理请求。然后是@RequestMapping，这个用在方法上，指定请求的路径和方法类型，比如 GET 或者 POST。@RequestParam 用来获取请求里的参数，像表单提交的参数就靠它拿。@PathVariable 是从 URL 路径里取参数的，比如 RESTful 接口里常见的/{id}这种路径，就用它获取 id 的值。@ResponseBody 很常用，加在方法上，能把返回的对象直接转成 JSON 或者 XML 返回给前端，不用走视图解析器。@RequestBody 正好相反，是接收请求体里的数据，比如前端发 JSON 过来，用它就能直接转成 Java 对象。还有@Autowired，虽然算 Spring Core 的，但 MVC 里控制器注入 Service 层对象经常用它。@Service 标记服务层的类，让 Spring 管理。另外@GetMapping 和@PostMapping 这些，其实是@RequestMapping 的简化版，直接指定 GET 或 POST 请求，写起来更方便。这些就是平时开发里最常用的了，掌握这些基本就能处理大部分场景。
### SpringMVC 有哪些核心组件？
**分析：**
- **DispatcherServlet**：前置控制器，是整个流程控制的**核心**，控制其他组件的执行，进行统一调度，降低组件之间的耦合性，相当于总指挥。
- **Handler**：处理器，完成具体的业务逻辑，相当于 Servlet 或 Action。
- **HandlerMapping**：DispatcherServlet 接收到请求之后，通过 HandlerMapping 将不同的请求映射到不同的 Handler。
- **HandlerInterceptor**：处理器拦截器，是一个接口，如果需要完成一些拦截处理，可以实现该接口。
- **HandlerExecutionChain**：处理器执行链，包括两部分内容：Handler 和 HandlerInterceptor（系统会有一个默认的 HandlerInterceptor，如果需要额外设置拦截，可以添加拦截器）。
- **HandlerAdapter**：处理器适配器，Handler 执行业务方法之前，需要进行一系列的操作，包括表单数据的验证、数据类型的转换、将表单数据封装到 JavaBean 等，这些操作都是由 HandlerApater 来完成，开发者只需将注意力集中业务逻辑的处理上，DispatcherServlet 通过 HandlerAdapter 执行不同的 Handler。
- **ModelAndView**：装载了模型数据和视图信息，作为 Handler 的处理结果，返回给 DispatcherServlet。
- **ViewResolver**：视图解析器，DispatcheServlet 通过它将逻辑视图解析为物理视图，最终将渲染结果响应给客户端。
**回答：**
SpringMVC 的核心组件主要有 DispatcherServlet、HandlerMapping、Controller、ModelAndView、ViewResolver 和 HandlerAdapter。DispatcherServlet 是前端控制器，所有请求都会先经过它，负责接收请求然后分发。HandlerMapping 的作用是根据请求的 URL 找到对应的处理器也就是 Controller。Controller 是处理业务逻辑的地方，处理完会返回 ModelAndView 对象，里面包含要展示的数据和视图名字。ViewResolver 把 ModelAndView 里的视图名解析成具体视图，比如 JSP 或 HTML。HandlerAdapter 是适配器，因为 Controller 实现方式可能不同，它能让 DispatcherServlet 不用管具体类型直接调用 Controller 的方法。这些组件配合起来完成从请求到响应的整个流程。
### SpringMVC 的执行流程了解吗？
**分析：**
SpringMVC 是基于 MVC 设计模式实现的 Web 框架，其工作流程如下：
1. 客户端发送 HTTP 请求至前端控制器 DispatcherServlet。
2. DispatcherServlet 根据请求信息调用 HandlerMapping，解析请求对应的 Handler 即处理器（Controller）。
3. HandlerMapping 根据请求 URL 查找对应的 Controller，同时生成用于执行该请求的 HandlerExecutionChain 对象（包含 Interceptor 链）。
4. DispatcherServlet 调用 HandlerAdapter 执行 Handler。在执行过程中，HandlerAdapter 将把 ModelAndView 对象返回给 DispatcherServlet。
5. Handler 执行完成后，返回一个 ModelAndView 对象给 HandlerAdapter。
6. HandlerAdapter 将 ModelAndView 对象传递给 DispatcherServlet。
7. DispatcherServlet 调用 ViewResolver 解析视图（View）。
8. ViewResolver 解析出 View 对象后，将其返回给 DispatcherServlet。
9. DispatcherServlet 调用 View 对象的 render（）方法进行视图渲染。
10. DispatcherServlet 将渲染后的视图（生成好的 HTML 内容）返回给客户端。
在这个过程中，DispatcherServlet 是整个 SpringMVC 的核心，它负责协调各个组件的工作。HandlerMapping 负责将请求映射到对应的 Controller，而 HandlerAdapter 负责执行 Controller。ViewResolver 则根据逻辑视图名（如 JSP 文件名）解析出 View 对象，最后由 View 渲染出实际的页面内容。通过这种分工协作的方式，SpringMVC 可以实现灵活、高效、可扩展的 Web 应用程序开发。
**回答：**
SpringMVC 的执行流程大概是这样的：用户发请求过来，首先会到 DispatcherServlet，这是核心的前端控制器。然后 DispatcherServlet 会找 HandlerMapping，HandlerMapping 根据请求的路径找到对应的 Controller 里的方法。找到后，DispatcherServlet 再通过 HandlerAdapter 去执行这个方法，处理业务逻辑，处理完会返回一个 ModelAndView 对象。接着 DispatcherServlet 拿着这个 ModelAndView 去找 ViewResolver 视图解析器，解析出具体的视图。最后把 Model 里的数据填充到视图里，生成响应返回给用户。整个过程就是这样，DispatcherServlet 在中间协调各个组件，把请求一步步处理完再返回结果。
### **Spring MVC 拦截器是什么？**
**分析：**
Spring 的处理程序映射机制包括处理程序拦截器，当你希望将特定功能应用于某些请求时，例如，检查用户主题时，这些拦截器非常有用。
拦截器必须实现 org.springframework.web.servlet 包的 HandlerInterceptor。此接口定义了三种方法： 
- preHandle：在执行实际处理程序之前调用。 
- postHandle：在执行完实际程序之后调用。
- afterCompletion：在完成请求后调用。 
**回答：**
Spring MVC 拦截器就是一种能在请求处理过程中进行拦截的组件，主要用来做通用处理，像检查用户登录状态、记录请求日志、监控接口执行时间这些。它会在几个阶段起作用，比如请求到 Controller 之前、Controller 处理完之后，还有视图渲染完成后。我们自己写拦截器的话，只要实现 HandlerInterceptor 接口，重写 preHandle、postHandle、afterCompletion 这几个方法，再配置到 Spring 里就能用了。这样就能统一处理很多重复的逻辑，不用在每个 Controller 里写了，挺方便的。
### **拦截器的使用场景有哪些？**
**分析：**
拦截器的典型使用场景如下：
- 日志记录：可用于记录请求日志，便于信息监控和信息统计；
- 权限检查：可用于用户登录状态的检查；
- 统一安全处理：可用于统一的安全效验或参数的加密 / 解密等。
**回答：**
拦截器一般用在处理请求前后做统一操作的场景。比如登录验证，用户访问需要登录的页面时，拦截器会先检查有没有登录状态，没登录就自动跳转到登录页，避免没权限访问。还有权限检查，不同用户角色能访问的功能不一样，拦截器可以判断当前用户的权限够不够，不够就不让访问。日志记录也常用，每次请求进来，拦截器记录下请求的时间、路径、参数这些信息，后面排查问题时能方便找到记录。请求参数处理也会用到，比如统一处理参数的编码或者格式转换，不用每个接口单独写。响应处理方面，拦截器可以统一包装返回数据的格式，让前后端交互更规范。另外还能做性能监控，记录请求处理的时间，看看哪些接口比较慢，方便优化。
### **Spring MVC** 怎么配置拦截器？
**分析：**
有两种写法。
一种是实现 HandlerInterceptor 接口，另外一种是继承适配器类，接着在接口方法当中，实现处理逻辑，然后在 Spring MVC 的配置文件中配置拦截器即可：
```XML
<!-- 配置Spring MVC的拦截器 -->
<mvc:interceptors>
    <!-- 配置一个拦截器的Bean就可以了 默认是对所有请求都拦截 -->
    <bean id="myInterceptor" class="com.zwp.action.MyHandlerInterceptor"></bean>
    <!-- 只针对部分请求拦截 -->
    <mvc:interceptor>
       <mvc:mapping path="/modelMap.do" />
       <bean class="com.zwp.action.MyHandlerInterceptorAdapter" />
    </mvc:interceptor>
</mvc:interceptors>
```
**回答：**
Spring MVC 配置拦截器主要分两步，先创建拦截器类，再在配置文件里配置。首先创建一个类实现 HandlerInterceptor 接口，然后重写 preHandle、postHandle 和 afterCompletion 这三个方法，根据业务需求在方法里写拦截逻辑，比如登录验证就可以在 preHandle 里判断。接着在 Spring 的配置文件里，用 mvc:interceptors 标签来配置拦截器，在这个标签里通过 bean 标签指定我们刚创建的拦截器类，然后用 mvc:mapping 设置要拦截的请求路径，比如/\*\*表示拦截所有，也可以用 mvc:exclude-mapping 排除不需要拦截的路径，像静态资源或者登录页。这样配置完拦截器就生效了，会按顺序执行拦截逻辑。
### 如何实现过滤器？
**分析：**
过滤器可以使用 Servlet 3.0 提供的 @WebFilter 注解，配置过滤的 URL 规则，然后再实现 Filter 接口，重写接口中的 doFilter 方法，具体实现代码如下：
```Java
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@Component
@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器：执行 init 方法。");
    }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器：开始执行 doFilter 方法。");
        // 请求放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器：结束执行 doFilter 方法。");
    }
    @Override
    public void destroy() {
        System.out.println("过滤器：执行 destroy 方法。");
    }
}
```
其中：
- void init（FilterConfig filterConfig）：容器启动（初始化 Filter）时会被调用，整个程序运行期只会被调用一次。用于实现 Filter 对象的初始化。
- void doFilter（ServletRequest request， ServletResponse response，FilterChain chain）：具体的过滤功能实现代码，通过此方法对请求进行过滤处理，**其中 FilterChain 参数是用来调用下一个过滤器或执行下一个流程**。
- void destroy（）：用于 Filter 销毁前完成相关资源的回收工作。
**回答：**
实现过滤器的话，首先得定义一个过滤器类，实现对应的接口，像 Java 里常用的 Filter 接口就行。然后重写 doFilter 方法，这个方法是核心，里面写具体的过滤逻辑，比如检查请求参数里有没有敏感词，或者验证用户有没有登录权限。处理完之后，必须调用 chain.doFilter 把请求和响应传给下一个过滤器或者目标资源，不然请求就卡在这儿了。接下来要配置过滤器，指定它要拦截哪些 URL，可以在配置文件里配，也能用注解直接标在过滤器类上。要是有多个过滤器，它们会按配置的顺序形成过滤器链，依次执行每个过滤器的逻辑。整个过程就是定义类、写逻辑、传请求、配拦截路径，这样就能实现对请求或响应的过滤处理了。
### 拦截器和过滤器区别是什么？
**分析：**
拦截器和过滤器的区别主要体现在以下 5 点：
1. **出身不同**：过滤器来自于 Servlet，而拦截器来自于 Spring 框架；
2. **触发时机不同**：请求的执行顺序是：请求进入容器 > 进入过滤器 > 进入 Servlet > 进入拦截器 > 执行控制器（Controller），所以过滤器和拦截器的执行时机，是过滤器会先执行，然后才会执行拦截器，最后才会进入真正的要调用的方法；
3. **底层实现不同**：过滤器是基于方法回调实现的，拦截器是基于动态代理（底层是反射）实现的；
4. **支持的项目类型不同**：过滤器是 Servlet 规范中定义的，所以过滤器要依赖 Servlet 容器，它只能用在 Web 项目中；而拦截器是 Spring 中的一个组件，因此拦截器既可以用在 Web 项目中，同时还可以用在 Application 或 Swing 程序中；
5. **使用的场景不同**：因为拦截器更接近业务系统，所以拦截器主要用来实现项目中的业务判断的，比如：登录判断、权限判断、日志记录等业务；而过滤器通常是用来实现通用功能过滤的，比如：敏感词过滤、字符集编码设置、响应数据压缩等功能。
**回答：**
过滤器和拦截器主要有这么几个区别。首先技术层面不一样，过滤器是 Servlet 规范里的，得依赖 Tomcat 这种容器才能跑；拦截器是 Spring 自己的，不挑容器，属于 Spring 框架内部的东西。然后执行时机有先后，过滤器是请求刚进容器，还没到 Servlet 的时候就干活；拦截器得等 Servlet 处理完，到 Controller 之前才执行，而且响应返回的时候还能再处理一下。作用范围也不同，过滤器管的宽，所有请求都能拦，主要搞搞参数处理、编码转换、URL 过滤这些基础活儿；拦截器更贴近业务，像登录验证、权限检查这种，而且只对 Spring 管的请求生效，比如 Controller 里的方法。实现上也有区别，过滤器得实现 Filter 接口，重写 doFilter 方法；拦截器是实现 HandlerInterceptor 接口，有 preHandle、postHandle 这些方法可以用。
### **Spring MVC 异常处理是什么？**
**分析：**
异常处理是 Spring MVC 中处理控制器方法抛出的异常的过程。可以使用@ExceptionHandler 注解、HandlerExceptionResolver 接口或@ControllerAdvice 注解来进行异常处理。
例如，在控制器中使用@ExceptionHandler 注解进行异常处理的示例代码如下：
```Java
@Controller
public class MyController {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        //处理异常并返回错误视图
        return "error";
    }
    @RequestMapping("/hello")
    public String handleRequest() throws Exception {
        throw new Exception("Something went wrong");
    }
}
```
在上面的代码中，@ExceptionHandler 注解处理控制器方法抛出的 Exception 异常，并返回一个名为"error"的错误视图。
**回答：**
Spring MVC 异常处理就是框架提供的处理请求过程中出现的异常的机制。开发时控制器方法执行可能会抛异常，比如参数错误、数据库问题这些，不处理的话用户可能看到一堆错误代码，体验不好。框架有几种常用处理方式，比如用@ExceptionHandler 注解在控制器里定义方法处理特定异常；想全局处理就用@ControllerAdvice 配合@ExceptionHandler，这样所有控制器的异常都能统一管。还能配置 SimpleMappingExceptionResolver 把异常映射到指定视图。这么做不用在每个方法里都写 try-catch，代码更干净，还能统一返回友好的错误页面或者 JSON 数据，用户体验好，开发人员也方便统一管理异常信息。
## **Mybatis**
### **什么是 MyBatis？** 
**分析：**
1. MyBatis 是一个 ORM（对象关系映射）框架，它内部封装了 JDBC，开发时只需要关注 SQL 语句本身，不需要花费精力去处理加载驱动，创建连接，创建 statement 等复杂的过程。开发人员不需要编写原生态 sql，可以严格控制 sql 执行性能，灵活度高。
2. MyBatis 可以使用 xml 或者注解来配置映射原生信息，将 POJO 映射成数据库中的记录，避免了几乎所有的 JDBC 代码和手动设置的参数以及获取结果集。
**回答：**
MyBatis 是一个持久层框架，主要用来简化 Java 项目里的数据库操作。以前用 JDBC 写数据库代码得处理很多重复工作，比如加载驱动、创建连接、手动把结果集转成 Java 对象，很麻烦。MyBatis 把这些底层操作都封装好了，我们不用再写这些重复代码。它最核心的是让 SQL 和 Java 代码分开，SQL 可以写在 XML 文件里，也能用注解直接写在接口方法上，这样改 SQL 的时候不用动 Java 代码，维护起来方便。查询数据时，它能自动把数据库返回的结果集映射成 Java 对象，不用手动一个个取字段值再赋值。还有动态 SQL 功能，比如根据条件拼接 where 子句，不用自己处理字符串拼接，不容易出错。总的来说，它就是帮我们减少数据库操作的底层工作量，让我们更专注写 SQL 和业务逻辑，提高开发效率。
### MyBatis 优缺点是什么？
**分析：**
**Mybaits 的优点：**
- 基于 SQL 语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响， SQL 写在 XML 里，解除 sql 与程序代码的耦合，便于统一管理；提供 XML 标签，支持编写动态 SQL 语句，并可重用。
- 与 JDBC 相比，减少了 50%以上的代码量，消除了 JDBC 大量冗余的代码，不需要手动开关连接；
- 很好的与各种数据库兼容（因为 MyBatis 使用 JDBC 来连接数据库，所以只要 JDBC 支持的 数据库 MyBatis 都支持）。
- 能够与 Spring 很好的集成；
- 提供映射标签，支持对象与数据库的 ORM 字段关系映射；提供对象关系映射标签，支持对象关系组件维护。
**MyBatis 框架的缺点：**
- **SQL 语句依赖**：MyBatis 需要手动编写 SQL 语句，这意味着开发人员需要具备一定的 SQL 知识。此外，如果数据库模式发生变化，需要手动修改 SQL 语句，这可能会导致一些问题。
- **XML 配置文件冗长**：MyBatis 的配置文件通常比较冗长，这可能会导致一些维护问题。此外，如果使用注解配置，代码可能会变得混乱。
- **缺乏自动化创建**：相比于其他 ORM 框架，MyBatis 缺乏自动化。例如，它不支持自动创建表和字段。
**MyBatis 框架适用场合：**
- MyBatis 专注于 SQL 本身，是一个足够灵活的 DAO 层解决方案。
- 对性能的要求很高，或者需求变化较多的项目，如互联网项目，MyBatis 将是不错的选择。
**回答：**
MyBatis 用起来挺实用的，它最大的好处是 SQL 控制特别灵活，写复杂查询的时候能自己直接写 SQL，调优也方便，不像有些全自动框架自动生成的 SQL 不好改。而且它很轻量，学起来简单，和 Spring 集成也容易，动态 SQL 功能也实用，能根据条件拼接 SQL 不用自己处理字符串。不过也有不足，SQL 得自己手写，表多的时候写 SQL 和维护工作量就大了。另外 SQL 和数据库绑定得比较紧，换数据库的话可能要改不少 SQL，移植性一般。还有结果映射得手动配，不像那些全自动映射的框架省事儿，得自己定义表字段和实体类字段的对应关系，稍微麻烦点。
### Mybatis 和 Hibernate 的区别是什么？
**分析：**
**介绍：**
- **Hibernate 框架：Hibernate** 是一个开放源代码的对象关系映射框架，它对 JDBC 进行了非常轻量级的对象封装，建立对象与数据库表的映射。是一个全自动的、完全面向对象的持久层框架。
- **Mybatis 框架：**Mybatis 是一个开源对象关系映射框架，原名：ibatis， 2010 年由谷歌接管以后更名。是一个半自动化的持久层框架。
**区别：**
- **开发方面：**在项目开发过程当中，就速度而言：hibernate 开发中，sql 语句已经被封装，直接可以使用，加快系统开发；Mybatis 属于半自动化，sql 需要手工完成，稍微繁琐；但是，凡事都不是绝对的，如果对于庞大复杂的系统项目来说，复杂语句较多，hibernate 就不是好方案。
- **sql 优化方面：**Hibernate 自动生成 sql，有些语句较为繁琐，会多消耗一些性能；Mybatis 手动编写 sql，可以避免不需要的查询，提高系统性能；
- **对象管理比对：**Hibernate 是完整的对象-关系映射的框架，开发工程中，无需过多关注底层实现，只要去管理对象即可；Mybatis 需要自行管理映射关系；
**适应场景：**
- Hibernate 是标准的 ORM 框架，SQL 编写量较少，但不够灵活，适合于需求相对稳定，中小型的软件项目，比如：办公自动化系统
- MyBatis 是半 ORM 框架，需要编写较多 SQL，但是比较灵活，适合于需求变化频繁，快速迭代的项目，比如：电商网站
**回答：**
MyBatis 和 Hibernate 都是 ORM 框架，不过 MyBatis 是半自动的，Hibernate 是全自动的。MyBatis 需要自己写 SQL 语句，SQL 和 Java 代码分开，用 XML 或注解配置，所以 SQL 控制很灵活，想怎么写就怎么写，调优也方便，懂 SQL 的话上手很快。Hibernate 不用写 SQL，它能自动生成，配置好实体类和数据库表的映射关系就行，用 HQL 或者直接操作对象就能查数据，不过自动生成的 SQL 有时候不够优化，复杂查询不好控制，学起来也麻烦点，要理解 ORM 的概念、缓存这些。一般业务复杂需要 SQL 优化的项目用 MyBatis 多，比如互联网项目；如果项目简单，想快速开发，数据模型稳定，用 Hibernate 合适。
### JDBC 编程有哪些不足之处，MyBatis 是如何解决这些问题的？
**分析：**
- 数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
  - 解决：在 SqlMapConfig.xml 中配置数据链接池，使用连接池管理数据库链接。
- SQL 语句写在代码中造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java 代码。
  - 解决：将 Sql 语句配置在 XXXXmapper.xml 文件中与 java 代码分离。
-  向 sql 语句传参数麻烦，因为 sql 语句的 where 条件不一定，可能多也可能少，占位符需要和参数一一对应。
  - 解决： Mybatis 自动将 java 对象映射至 sql 语句。
-  对结果集解析麻烦，sql 变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成 pojo 对象解析比较方便。
  - 解决：Mybatis 自动将 sql 执行结果映射至 java 对象。
**回答：**
JDBC 编程主要问题就是代码太繁琐，得自己写加载驱动、获取连接这些步骤，用完还得手动关闭，一不小心就连接泄漏。而且 SQL 语句直接写在 Java 代码里，改个 SQL 都得改代码重新编译，维护不方便。查询结果还得手动用 rs.getXXX 取字段值，容易出错。MyBatis 解决这些问题就很到位，它自带连接池管理连接，不用手动开关；SQL 写在 XML 或注解里，和 Java 代码分开，改 SQL 不用动代码；结果集能自动映射到实体类，不用手动取值；参数用#{}处理，防注入还方便，整体用起来简洁多了。
### MyBatis 编程步骤是什么样的？
**分析：**
1、 创建 SqlSessionFactory 
2、通过 SqlSessionFactory 创建 SqlSession 
3、 通过 sqlsession 执行数据库操作 
4、 调用 session.commit（）提交事务 
5、 调用 session.close（）关闭会话
**回答：**
用 MyBatis 编程的话，首先得准备环境，比如导入 MyBatis 的依赖，然后配置核心的 mybatis-config.xml 文件，里面主要配数据库连接信息、事务管理这些基础配置。接着定义实体类，让类的属性和数据库表的字段对应起来。之后写 Mapper 接口，就是定义要操作数据库的方法，比如查数据、新增数据这些具体操作。然后得把接口方法和 SQL 语句对应上，一般是写 Mapper XML 文件，用 select、insert 这些标签，指定接口方法的 id，还有返回类型或者参数类型。配好之后，通过 SqlSessionFactoryBuilder 加载配置文件创建 SqlSessionFactory，再用它获取 SqlSession。拿到 SqlSession 后，就能获取 Mapper 接口的代理对象，调用接口里的方法执行 SQL 了，最后记得把 SqlSession 关掉。整个过程核心就是配置环境、定义接口和 SQL 映射、通过 SqlSession 执行操作，这样就能用 MyBatis 操作数据库了。
### **Xml 映射文件中，除了常见的 select|insert|updae|delete 标签之外，还有哪些标签？**
**分析：**
还有很多其他的标签，`<resultMap>`、`<parameterMap>`、`<sql>`、`<include>`、`<selectKey>`，加上动态 sql 的 9 个标签，`trim|where|set|foreach|if|choose|when|otherwise|bind`等，其中为 sql 片段标签，通过`<include>`标签引入 sql 片段，`<selectKey>`为不支持自增的主键生成策略标签。
**回答：**
除了常用的 select、insert、update、delete 这几个基础操作标签，Xml 映射文件里还有些常用的标签。比如 resultMap 标签，主要用来定义查询结果和实体类的映射关系，像数据库字段名和实体类属性名不一样时，就用它来对应。然后是 sql 标签，能把重复的 SQL 片段抽出来，比如查询时常用的字段列表，写在 sql 标签里，后面用 include 标签引用，减少重复代码。还有 cache 标签，用来配置当前映射文件的缓存，比如开启二级缓存，cache-ref 则是引用其他命名空间的缓存配置。另外 parameterMap 标签以前用来配置参数映射，不过现在大多直接用参数注解或者在 SQL 里用#{}传参，这个用得比较少了。这些标签能让映射文件更灵活，代码更简洁。
### Mybatis 如何防止 SQL 注入？
**分析：**
简单的说就是#{}是经过预编译的，是安全的，**\${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在 SQL 注入。在编写 mybatis 的映射语句时，尽量采用“#{xxx}”**这样的格式。
如果需要实现动态传入表名、列名，还需要做如下修改：添加属性 **statementType="STATEMENT"，同时 sql 里的属性变量取值都改成\${}**是未经过预编译的，仅仅是取变量的值，是非安全的，存在 SQL 注入。在编写 mybatis 的映射语句时，尽量采用“#{xxx}”这样的格式。
**回答：**
Mybatis 防止 SQL 注入主要有两种常用方式。第一种是用#{}占位符，这是最主要的。Mybatis 会把#{}解析成预编译语句里的参数占位符，参数值会被自动处理，比如转义特殊字符，不会直接拼到 SQL 语句里，这样就能避免恶意参数改变 SQL 结构，防止注入。第二种是尽量别用\${}，因为\${}是直接替换变量值，会把参数原样拼到 SQL 里，如果参数里有恶意代码就容易被注入。要是必须用\${}，比如动态表名这种情况，就得自己手动过滤参数，比如检查参数是不是在允许的白名单里，确认没问题再用，这样也能降低注入风险。平时开发里基本用#{}就够了，少用\${}能减少大部分注入问题。
### #{}和\${}的区别？
**分析：**
- `#{}`是占位符，预编译处理；`${}`是拼接符，字符串替换，没有预编译处理。
- Mybatis 在处理`#{}`时，`#{}`传入参数是以字符串传入，会将 SQL 中的`#{}`替换为？号，调用 PreparedStatement 的 set 方法来赋值。
- `#{}` 可以有效的防止 SQL 注入，提高系统安全性；`${}` 不能防止 SQL 注入
- `#{}` 的变量替换是在 DBMS 中；`${}` 的变量替换是在 DBMS 外
**回答：**
#{}和\${}主要区别在处理方式和安全性。#{}是预编译处理，会把传入的参数当成字符串，自动加上单引号，这样能防止 SQL 注入，比如写 where id = #{id}，如果传 1，实际执行就是 where id = '1'。而\${}是直接做字符串替换，把参数原样拼到 SQL 里，比如传 1，就是 where id = 1，这样如果传恶意值就可能有 SQL 注入风险。另外，使用场景不一样，#{}一般用在参数值的地方，比如查询条件里的具体值；\${}适合动态表名、列名这些需要直接拼接的地方，比如 order by \${column}，因为表名列名不能加单引号。所以平时写 SQL 尽量用#{}，只有需要动态拼接表名或列名时才用\${}，而且用\${}要注意防注入。
### 介绍 MyBatis 的一级缓存和二级缓存
**分析：**
- 一级缓存： 基于 PerpetualCache 的 HashMap 本地缓存，其存储作用域为 SqlSession，各个 SqlSession 之间的缓存相互隔离，当 Session flush 或 close 之后，该 SqlSession 中的所有 Cache 就将清空，MyBatis 默认打开一级缓存。
- 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap 存储，不同之处在于其存储作用域为 Mapper（Namespace），可以在多个 SqlSession 之间共享，并且可自定义存储源，如 Ehcache。默认不打开二级缓存，要开启二级缓存，使用二级缓存属性类需要实现 Serializable 序列化接口（可用来保存对象的状态），可在它的映射文件中配置。
当开启二级缓存后，数据的查询执行的流程就是 二级缓存 -> 一级缓存 -> 数据库。
缓存更新机制：当某一个作用域（一级缓存 Session/二级缓存 Mapper）进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。
**回答：**
MyBatis 的一级缓存和二级缓存都是用来减少数据库查询的。一级缓存是 SqlSession 级别的，每个 SqlSession 自己有一份缓存，默认就开启。你用同一个 SqlSession 查同一条数据，第一次会查数据库，结果存到缓存里，第二次查就直接从缓存拿，不用再访问数据库了。不过要是在这个 SqlSession 里执行了增删改操作，或者把 SqlSession 关了，缓存就会清空。
二级缓存是 Mapper 级别的，多个 SqlSession 可以共用同一个 Mapper 的缓存，但得手动开启。先在 MyBatis 配置文件里把 cacheEnabled 设为 true，再在对应的 Mapper.xml 里加个<cache>标签。它的缓存是在 SqlSession 提交或者关闭后才会存进去，这样其他 SqlSession 就能共享了。和一级缓存一样，执行增删改操作的时候，二级缓存也会被清空。
### MyBatis 有哪些设计模式？
**分析：**
- **工厂模式**：工厂模式在 MyBatis 中的典型代表是 SqlSessionFactory。SqlSession 是 MyBatis 中的重要 Java 接口，可以通过该接口来执行 SQL 命令、获取映射器示例和管理事务，而 SqlSessionFactory 正是用来产生 SqlSession 对象的，所以它在 MyBatis 中是比较核心的接口之一。
- **建造者模式**：建造者模式在 MyBatis 中的典型代表是 SqlSessionFactoryBuilder。普通的对象都是通过 new 关键字直接创建的，但是如果创建对象需要的构造参数很多，且不能保证每个参数都是正确的或者不能一次性得到构建所需的所有参数，那么就需要将构建逻辑从对象本身抽离出来，让对象只关注功能，把构建交给构建类，这样可以简化对象的构建，也可以达到分步构建对象的目的，而 SqlSessionFactoryBuilder 的构建过程正是如此。
- **单例模式**：单例模式在 MyBatis 中的典型代表是 ErrorContext。ErrorContext 是线程级别的的单例，每个线程中有一个此对象的单例，用于记录该线程的执行环境的错误信息。
- **适配器模式**：适配器模式在 MyBatis 中的典型代表是 Log。MyBatis 中的日志模块适配了以下多种日志类型：SLF4J、Apache Commons Logging、Log4j 2、Log4j、JDK logging
- **代理模式**：代理模式在 MyBatis 中的典型代表是 MapperProxyFactory。MapperProxyFactory 的 newInstance（） 方法就是生成一个具体的代理来实现某个功能。
- **模板方法模式：**模板方法在 MyBatis 中的典型代表是 BaseExecutor，在 MyBatis 中 BaseExecutor 实现了大部分 SQL 执行的逻辑。
- **装饰器模式**：装饰器模式在 MyBatis 中的典型代表是 Cache。Cache 除了有数据存储和缓存的基本功能外（由 PerpetualCache 永久缓存实现），还有其他附加的 Cache 类，比如先进先出的 FifoCache、最近最少使用的 LruCache、防止多线程并发访问的 SynchronizedCache 等众多附加功能的缓存类。
**推荐阅读：**[MyBatis 使用了哪些设计模式？在源码中是如何体现的？ ](https://www.kancloud.cn/alex_wsc/java_source/1852247)
**回答：**
MyBatis 里常用的设计模式有这么几个。首先是工厂模式，像 SqlSessionFactory，它专门负责创建 SqlSession 对象，我们用的时候直接从工厂获取，不用自己去 new。然后是建造者模式，SqlSessionFactoryBuilder 就是干这个的，它会处理配置信息，帮我们构建出 SqlSessionFactory 实例。还有代理模式，我们写的 Mapper 接口没有实现类，MyBatis 会动态生成代理对象，调用接口方法时实际是代理对象在执行对应的 SQL。模板方法模式也常用，比如 BaseExecutor，它定义了执行 SQL 的基本流程，像查询、更新的步骤，具体实现交给子类比如 SimpleExecutor 去做。另外 SqlSessionFactory 一般是单例的，整个应用里只创建一个实例，这就是单例模式的应用。这些模式让 MyBatis 的结构更清晰，用起来也方便。
### **为什么说 Mybatis 是半自动 ORM 映射工具？它与全自动的区别在哪里？**
**分析：**
谈区别都是对比来看，一般都是对比 hibernate 来说，而且这种问题没有标准答案，讲清楚自己的理解即可
- Hibernate 属于全自动 ORM 映射工具，使用 Hibernate 查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
- 而 Mybatis 在查询关联对象或关联集合对象时，需要手动编写 sql 来完成，所以，称之为半自动 ORM 映射工具。
**回答：**
MyBatis 被称为半自动 ORM 映射工具，主要是因为它需要开发者手动编写 SQL 语句。在使用时，不管是通过 XML 配置还是注解方式，我们都得自己定义具体的 SQL 逻辑，还得手动配置实体类和数据库表字段的对应关系，比如用 ResultMap 指定哪个属性对应哪个列。而全自动 ORM 工具像 Hibernate 就不一样，它不用手动写 SQL，框架会根据实体类和表的映射关系自动生成 SQL 语句，开发者直接调用现成的 API 就能完成增删改查操作。所以两者的核心区别就是 SQL 是否需要开发者手动编写和维护，MyBatis 需要手动处理 SQL，所以是半自动，全自动的 SQL 由框架自动生成。
### Mybatis 都有哪些 Executor 执行器？它们之间的区别是什么？
**分析：**
概念问题，直接参考回答
**回答：**
Mybatis 主要有 SimpleExecutor、ReuseExecutor、BatchExecutor 和 CachingExecutor 这几种执行器。SimpleExecutor 是默认的，每次执行 SQL 都会创建新的 Statement，用完就关闭，适合一般的场景。ReuseExecutor 会重用 Statement，就是相同的 SQL 会复用之前创建的 Statement，不用每次都新建，能减少创建对象的开销，适合需要重复执行相同 SQL 的情况。BatchExecutor 是用来批量执行的，它会把多个更新操作像 insert、update、delete 缓存起来，最后一起提交，这样能减少和数据库的交互次数，提高批量处理的效率。CachingExecutor 是带缓存的，执行查询时会先查缓存，缓存里没有再查数据库，通常会包装其他执行器来处理二级缓存。它们的区别主要在执行方式和适用场景，比如 SimpleExecutor 简单直接，ReuseExecutor 能重用资源，BatchExecutor 适合批量操作，CachingExecutor 则负责处理缓存。
### Mybatis 中如何指定使用哪一种 Executor 执行器？
**分析：**
在 Mybatis 配置文件中，可以指定默认的 ExecutorType 执行器类型，也可以手动给 DefaultSqlSessionFactory 的创建 SqlSession 的方法传递 ExecutorType 类型参数。
配置默认的执行器，SIMPLE 就是普通的执行器，REUSE 执行器会重用预处理语句，BATCH 执行器将重用语句并执行批量更新。
**回答：**
在 Mybatis 里指定用哪种 Executor 执行器，主要通过配置来实现。最常用的是在全局配置文件里设置，比如 mybatis-config.xml 中，在 settings 标签下加一个 defaultExecutorType 属性，值可以设为 SIMPLE、REUSE 或者 BATCH，这样整个项目就会默认用这个类型的执行器。要是想针对某个 SqlSession 单独指定，也可以在获取 SqlSession 的时候设置，比如用 SqlSessionFactory 的 openSession 方法，传 ExecutorType 参数，像 ExecutorType.BATCH，这样这个 SqlSession 就会用对应的执行器。默认情况下 Mybatis 用的是 SIMPLE 执行器，这种方式比较灵活，全局配置和局部指定都能满足不同场景的需求，日常开发里基本就靠这两种配置方式来指定执行器类型。
### MyBatis 工作原理是什么？
**分析：**
1）**读取 MyBatis 配置文件**：mybatis-config.xml 为 MyBatis 的全局配置文件，配置了 MyBatis 的运行环境等信息，例如数据库连接信息。
2）**加载映射文件：**映射文件即 SQL 映射文件，该文件中配置了操作数据库的 SQL 语句，需要在 MyBatis 配置文件 mybatis-config.xml 中加载。mybatis-config.xml 文件可以加载多个映射文件，每个文件对应数据库中的一张表。
3）**构造会话工厂**：通过 MyBatis 的环境等配置信息构建会话工厂 SqlSessionFactory。
4）**创建会话对象**：由会话工厂创建 SqlSession 对象，该对象中包含了执行 SQL 语句的所有方法。
5）**Executor 执行器**：MyBatis 底层定义了一个 Executor 接口来操作数据库，它将根据 SqlSession 传递的参数动态地生成需要执行的 SQL 语句，同时负责查询缓存的维护。
6）**MappedStatement 对象**：在 Executor 接口的执行方法中有一个 MappedStatement 类型的参数，该参数是对映射信息的封装，用于存储要映射的 SQL 语句的 id、参数等信息。
7）**输入参数映射**：输入参数类型可以是 Map、List 等集合类型，也可以是基本数据类型和 POJO 类型。输入参数映射过程类似于 JDBC 对 preparedStatement 对象设置参数的过程。
8）**输出结果映射**：输出结果类型可以是 Map、 List 等集合类型，也可以是基本数据类型和 POJO 类型。输出结果映射过程类似于 JDBC 对结果集的解析过程。
**回答：**
MyBatis 的工作原理其实就是帮我们简化了 JDBC 的操作流程。首先我们要写配置文件，一个是全局的 mybatis-config.xml，里面有数据库连接信息这些；还有 Mapper 映射文件或者用注解，里面写具体的 SQL 语句。然后 MyBatis 会读取这些配置，创建出 SqlSessionFactory，这就像个工厂。接着从这个工厂里获取 SqlSession，这是和数据库交互的会话对象。当我们通过 SqlSession 调用 Mapper 接口的时候，MyBatis 会根据接口找到对应的 SQL，处理传进来的参数，执行 SQL 语句，再把数据库返回的结果集转换成我们需要的 Java 对象，最后把这个对象返回给我们。整个过程中，MyBatis 帮我们做了参数处理、结果映射这些重复性的工作，不用我们自己写 JDBC 那套繁琐的代码了。
### **Mybatis 动态 sql 是做什么的？都有哪些动态 sql？能简述一下动态 sql 的执行原理不？**
**分析：**
- Mybatis 动态 sql 可以让我们在 Xml 映射文件内，以标签的形式编写动态 sql，完成逻辑判断和动态拼接 sql 的功能。
- Mybatis 提供了 9 种动态 sql 标签 `trim|where|set|foreach|if|choose|when|otherwise|bind`。
- 其执行原理为，使用 OGNL 从 sql 参数对象中计算表达式的值，根据表达式的值动态拼接 sql，以此来完成动态 sql 的功能。
**回答：**
MyBatis 动态 SQL 主要是用来根据不同条件动态生成不同的 SQL 语句，解决静态 SQL 写死的问题，比如查询时条件可能有也可能没有，用动态 SQL 就能灵活处理。常用的动态 SQL 标签有 if、choose when otherwise、trim where set、foreach 这些。if 判断条件是否成立，成立就拼接 SQL 片段；choose 类似 switch，按条件选一个 when 执行；trim where set 处理拼接时的多余逗号或 and，比如 where 自动去掉开头的 and；foreach 遍历集合，比如 in 查询拼接参数。执行原理是 MyBatis 解析 Mapper 文件时，把动态标签解析成处理器，运行时根据参数值，处理器判断条件生成 SQL 片段，拼接成完整 SQL 后交给数据库执行。
### Mybatis 能执行一对一、一对多的关联查询吗？
**参考：**
能，但不推荐，大多数情况下都会考虑，在业务逻辑来控制关联关系，而不是使用外键约束的方式
MyBatis 不仅可以执行一对一、一对多的关联查询，还可以执行多对一，多对多的关联查询。
多对一查询，其实就是一对一查询，只需要把 `selectOne()` 修改为 `selectList()` 即可；多对多查询，其实就是一对多查询，只需要把 `selectOne()` 修改为 `selectList()` 即可。
关联对象查询，有两种实现方式，一种是单独发送一个 sql 去查询关联对象，赋给主对象，然后返回主对象。另一种是使用嵌套查询，嵌套查询的含义为使用 join 查询，一部分列是 A 对象的属性值，另外一部分列是关联对象 B 的属性值，好处是只发一个 sql 查询，就可以把主对象和其关联对象查出来。
那么问题来了，join 查询出来 100 条记录，如何确定主对象是 5 个，而不是 100 个？其去重复的原理是 `<resultMap>` 标签内的 `<id>` 子标签，指定了唯一确定一条记录的 id 列，MyBatis 根据 `<id>` 列值来完成 100 条记录的去重复功能， `<id>` 可以有多个，代表了联合主键的语意。
同样主对象的关联对象，也是根据这个原理去重复的，尽管一般情况下，只有主对象会有重复记录，关联对象一般不会重复。
举例：下面 join 查询出来 6 条记录，一、二列是 Teacher 对象列，第三列为 Student 对象列，MyBatis 去重复处理后，结果为 1 个老师 6 个学生，而不是 6 个老师 6 个学生。
<sheet sheet-id="V4u60o" token="RCAssZRnlhFO0htbnKSc0igsnss"></sheet>
**回答：**
MyBatis 能执行一对一和一对多的关联查询。处理一对一关联时，通常在 ResultMap 里用 association 标签，指定 JavaType 为关联对象的类型，比如查询订单时关联对应的用户信息，就把用户的属性配置到 association 里，对应数据库字段和 Java 属性的映射。一对多的话用 collection 标签，指定 ofType 为集合里元素的类型，比如查询用户时关联他的多个订单，就在用户的 ResultMap 里用 collection 配置订单列表，ofType 设为订单类型。这两种情况都可以通过关联查询 SQL 或者嵌套查询实现，主要是通过 ResultMap 里的这两个标签来映射关联关系，配置对应的字段和属性映射就行。
### Mybatis 是否支持延迟加载？原理？
**分析：**
- Mybatis 支持 association 关联对象和 collection 关联集合对象的延迟加载，association 指的就是一对一，collection 指的就是一对多查询。在 Mybatis 配置文件中，可以配置是否启用延迟加载 lazyLoadingEnabled=true|false。
- 它的原理是，使用 CGLIB 创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用 a.getB（）。getName（），拦截器 invoke（）方法发现 a.getB（）是 null 值，那么就会单独发送事先保存好的查询关联 B 对象的 sql，把 B 查询上来，然后调用 a.setB（b），于是 a 的对象 b 属性就有值了，接着完成 a.getB（）。getName（）方法的调用。这就是延迟加载的基本原理。
- 当然了，不光是 Mybatis，几乎所有的包括 Hibernate，支持延迟加载的原理都是一样的。
**回答：**
Mybatis 支持延迟加载，主要用在关联查询场景，像一对一、一对多这种。比如查用户的时候，用户有个订单列表，延迟加载就是先只查用户基本信息，不马上查订单。等代码里第一次用到订单列表的属性时，Mybatis 才会去执行查询订单的 SQL。原理是通过动态代理实现的，查询主对象时返回的是代理对象，这个代理对象里存着关联查询的 SQL 和执行器。当调用关联对象的 getter 方法时，代理对象就会触发执行关联查询，把结果赋值给主对象的关联属性，这样就实现了按需加载，减少不必要的数据库查询。
### 什么是 MyBatis 的接口绑定？有哪些实现方式？
**分析：**
接口绑定，就是在 MyBatis 中任意定义接口，然后把接口里面的方法和 SQL 语句绑定，我们直接调用接口方法就可以，这样比起原来了 SqlSession 提供的方法我们可以有更加灵活的选择和设置。
接口绑定有两种实现方式：
- 通过注解绑定，就是在接口的方法上面加上 @Select、@Update 等注解，里面包含 Sql 语句来绑定；
- 通过 xml 里面写 SQL 来绑定， 在这种情况下，要指定 xml 映射文件里面的 namespace 必须为接口的全路径名。当 Sql 语句比较简单时候，用注解绑定， 当 SQL 语句比较复杂时候，用 xml 绑定，一般用 xml 绑定的比较多。
**回答：**
MyBatis 的接口绑定就是把 Mapper 接口和 SQL 映射关联起来，调用接口方法就能直接执行对应的 SQL，不用自己写接口的实现类。实现方式主要有两种，一种是 XML 文件方式，得把接口和 XML 文件放在同一个包下，文件名保持相同，XML 里的 namespace 设为接口的全类名，SQL 标签的 id 要和接口方法名对应。另一种是注解方式，直接在接口方法上用@Select@Insert 这些注解写 SQL 语句，不用再写 XML 文件。这两种方式都能让 MyBatis 通过接口找到对应的 SQL 去执行，简化开发流程。
### Mybatis 是如何将 sql 执行结果封装为目标对象并返回的？都有哪些映射形式？
**分析：**
- 第一种是使用`<resultMap>`标签，逐一定义列名和对象属性名之间的映射关系。
- 第二种是使用 sql 列的别名功能，将列别名书写为对象属性名，比如`T_NAME AS NAME`，对象属性名一般是 name，小写，但是列名不区分大小写，Mybatis 会忽略列名大小写，智能找到与之对应对象属性名，你甚至可以写成`T_NAME AS NaMe`，Mybatis 一样可以正常工作。
有了列名与属性名的映射关系后，Mybatis 通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。
**回答：**
MyBatis 把 SQL 执行结果封装成目标对象，主要是通过结果集处理器来处理的。它会根据配置的映射规则，把结果集里的列值对应到对象的属性上。映射规则主要看 SQL 查询的列名和目标对象的属性名是否一致，如果一致的话，MyBatis 会自动匹配赋值，这就是自动映射。要是列名和属性名不一样，就得手动配置映射关系，比如在 XML 里用 resultMap 标签，或者用注解@Results、@Result 来指定哪个列对应哪个属性。所以主要的映射形式就是自动映射和手动映射两种，通过这两种方式把结果集的数据封装成需要的对象返回。
### Mybatis 的 Xml 映射文件中，不同的 Xml 映射文件，id 是否可以重复？
**分析：**
不同的 XML 映射文件：
- 如果配置了 namespace，那么 id 可以重复；
- 如果没有配置 namespace，那么 id 不能重复；
原因就是 namespace+id 是作为 Map <String，MapperStatement> 的 key 使用的，如果没有 namespace，就剩下 id，那么，id 重复会导致数据互相覆盖。有了 namespace，自然 id 就可以重复，namespace 不同，namespace+id 自然也就不同。
**回答：**
MyBatis 的 Xml 映射文件里，不同文件的 id 能不能重复，主要看 namespace。因为 MyBatis 识别映射语句是用 namespace 加上 id 作为唯一标识的。要是两个不同的 Xml 文件，它们的 namespace 不一样，就算 id 相同也没问题，不会冲突。但如果 namespace 相同，不管是不是同一个文件，只要 id 重复了，加载的时候就会报错，因为唯一标识重复了。所以不同 Xml 映射文件的 id 可以重复，但得保证它们的 namespace 不同才行。实际开发里，通常每个映射文件对应一个接口，namespace 就是接口的全类名，所以不同接口的映射文件 namespace 不同，id 重复也没关系。
### MyBatis 如何执行批量操作？
**分析：**
MyBatis 批量操作有两种方式：
- 第一种方法：使用 foreach 标签
- 第二种方法：使用 ExecutorType.BATCH
**第一种方法：使用 foreach 标签**
foreach 的主要用在构建 in 条件中，它可以在 SQL 语句中进行迭代一个集合。foreach 标签的属性主要有 item，index，collection，open，separator，close。
- item   表示集合中每一个元素进行迭代时的别名，随便起的变量名；
- index   指定一个名字，用于表示在迭代过程中，每次迭代到的位置，不常用；
- open   表示该语句以什么开始，常用“（”；
- separator 表示在每次进行迭代之间以什么符号作为分隔符，常用“，”；
- close   表示以什么结束，常用“）”。
在使用 foreach 的时候最关键的也是最容易出错的就是 collection 属性，该属性是必须指定的，但是在不同情况下，该属性的值是不一样的，主要有以下 3 种情况：
1. 如果传入的是单参数且参数类型是一个 List 的时候，collection 属性值为 list
2. 如果传入的是单参数且参数类型是一个 array 数组的时候，collection 的属性值为 array
3. 如果传入的参数是多个的时候，我们就需要把它们封装成一个 Map 了，当然单参数也可以封装成 map，实际上如果你在传入参数的时候，在 MyBatis 里面也是会把它封装成一个 Map 的，map 的 key 就是参数名，所以这个时候 collection 属性值就是传入的 List 或 array 对象在自己封装的 map 里面的 key
看看批量保存的两种用法：
```XML
<!-- MySQL下批量保存，可以foreach遍历 mysql支持values(),(),()语法 --> //推荐使用
<insert id="addEmpsBatch">
    INSERT INTO emp(ename,gender,email,did)
    VALUES
    <foreach collection="emps" item="emp" separator=",">
        (#{emp.eName},#{emp.gender},#{emp.email},#{emp.dept.id})
    </foreach>
</insert>
```
**第二种方法：使用 ExecutorType.BATCH**
Mybatis 内置的 ExecutorType 有 3 种，默认为 simple，该模式下它为每个语句的执行创建一个新的预处理语句，单条提交 sql；而 batch 模式重复使用已经预处理的语句，并且批量执行所有更新语句，显然 batch 性能将更优； 但 batch 模式也有自己的问题，比如在 Insert 操作时，在事务没有提交之前，是没有办法获取到自增的 id，在某些情况下不符合业务的需求。
具体用法如下：
```Java
//批量保存方法测试
@Test
public void testBatch() throws IOException{
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    //可以执行批量操作的sqlSession
    SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    //批量保存执行前时间
    long start = System.currentTimeMillis();
    try {
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 5), "b", "1"));
        }
        openSession.commit();
        long end = System.currentTimeMillis();
        //批量保存执行后的时间
        System.out.println("执行时长" + (end - start));
        //批量 预编译sql一次==》设置参数==》10000次==》执行1次   677
        //非批量  （预编译=设置参数=执行 ）==》10000次   1121
    } finally {
        openSession.close();
    }
}
```
mapper 和 mapper.xml 如下
```Java
public interface EmployeeMapper {
    //批量保存员工
    Long addEmp(Employee employee);
}
```
```XML
<mapper namespace="com.jourwon.mapper.EmployeeMapper"
     <!--批量保存员工 -->
    <insert id="addEmp">
        insert into employee(lastName,email,gender)
        values(#{lastName},#{email},#{gender})
    </insert>
</mapper>
```
**回答：**
MyBatis 执行批量操作主要有两种常用方式。一种是在 XML 映射文件里用 foreach 标签拼接 SQL，比如批量插入时，写 insert into 表名（字段 1，字段 2） values，然后用 foreach 遍历要插入的集合，每个元素对应一组 values 里的参数，设置 separator 为逗号，这样就能生成多条 values 的 SQL 语句，一次发送给数据库执行。另一种是用 SqlSession 的批量执行模式，创建 SqlSession 时指定 ExecutorType.BATCH，接着循环调用 mapper 接口的方法，最后调用 commit 提交。这种方式会把多次操作缓存起来，最后一次性提交到数据库，减少和数据库的交互次数，适合数据量大的场景。两种方式都能实现批量操作，具体用哪种看数据量，数据量不大时 foreach 方便，数据量大时 BATCH 模式性能更好。
### 在 mapper 中如何传递多个参数？
**分析：**
有四种方式：
- 方法 1：顺序传参法
- 方法 2：@Param 注解传参法
- 方法 3：Map 传参法
- 方法 4：Java Bean 传参法
方法 1：顺序传参法
```Java
public User selectUser(String name, int deptId);
<select id="selectUser" resultMap="UserResultMap">
    select * from user
    where user_name = #{0} and dept_id = #{1}
</select>
```
- `\#{}`里面的数字代表传入参数的顺序。
- 这种方法不建议使用，sql 层表达不直观，且一旦顺序调整容易出错。
方法 2：@Param 注解传参法
```Java
public User selectUser(@Param("userName") String name, int @Param("deptId") deptId);
<select id="selectUser" resultMap="UserResultMap">
    select * from user
    where user_name = #{userName} and dept_id = #{deptId}
</select>
```
- `\#{}`里面的名称对应的是注解@Param 括号里面修饰的名称。
- 这种方法在参数不多的情况还是比较直观的（推荐使用）。
方法 3：Map 传参法
```Java
public User selectUser(Map<String, Object> params);
<select id="selectUser" parameterType="java.util.Map" resultMap="UserResultMap">
    select * from user
    where user_name = #{userName} and dept_id = #{deptId}
</select>
```
- `\#{}`里面的名称对应的是 Map 里面的 key 名称。
- 这种方法适合传递多个参数，且参数易变能灵活传递的情况。
方法 4：Java Bean 传参法
```Java
public User selectUser(User user);
<select id="selectUser" parameterType="com.jourwon.pojo.User" resultMap="UserResultMap">
    select * from user
    where user_name = #{userName} and dept_id = #{deptId}
</select>
```
- `\#{}`里面的名称对应的是 User 类里面的成员属性。
- 这种方法直观，需要建一个实体类，扩展不容易，需要加属性，但代码可读性强，业务逻辑处理方便，推荐使用。（推荐使用）。
**回答：**
在 mapper 里传递多个参数的话，常用的有几种方式。最直接的是用@Param 注解，就是在方法参数前面加上@Param（"参数名"），这样在 XML 里写 SQL 的时候，直接用#{参数名}就能拿到对应的值，这个方法简单方便，参数少的时候用着很顺手。要是参数比较多，比如四五个以上，一般会封装成一个 POJO 类，把要传的参数都作为类的属性，然后方法里直接传这个 POJO 对象，XML 里用#{属性名}就能取到对应的值，这样代码看起来更清晰，也方便维护。还有一种是用 Map，把参数以键值对的形式放到 Map 里，方法参数传 Map，XML 里通过#{key}获取，不过这种不如 POJO 直观，一般参数少或者临时用的时候可能会用。实际开发里@Param 和 POJO 用得比较多，具体选哪种看参数数量，参数少就用@Param，参数多就用 POJO，这样用起来比较合适。
### MyBatis 是如何进行分页的？
**分析：**
MyBatis 有两种分页模式，一种使用 RowBounds 对象进行分页，它是针对 ResultSet 结果集执行的内存分页，而非物理分页。可以在 sql 内直接书写带有物理分页的参数来完成物理分页功能，另一种是使用分页插件来完成物理分页。
**回答：**
MyBatis 分页主要有两种常用方式。一种是手动写 SQL 分页，就是在 SQL 语句里直接用 limit 关键字，比如 select \* from user limit #{offset}， #{pageSize}，offset 得自己算，就是（页码 -1）乘以每页条数，这样能拿到对应页的数据，但不同数据库分页语法不一样，比如 Oracle 用 rownum，得自己适配，还得手动处理页码计算，比较麻烦。另一种常用的是用分页插件，像 PageHelper，用的时候在查询方法前调用 PageHelper.startPage（pageNum， pageSize），插件会自动拦截 SQL，加上分页条件，还能返回总条数、总页数这些信息，不用自己写 limit，也兼容不同数据库，用起来方便很多。平时开发里用插件比较多，简单省事。
### 分页插件的原理是什么？
**分析：**
分页插件的基本原理是使用 Mybatis 提供的插件接口，实现自定义插件，拦截 Executor 的 query 方法
在执行查询的时候，拦截待执行的 sql，然后重写 sql，根据 dialect 方言，添加对应的物理分页语句和物理分页参数。
举例：`select * from student`，拦截 sql 后重写为：`select t.* from (select * from student) t limit 0, 10`
**回答：**
分页插件主要是为了处理大量数据时，不让页面一次性加载太多内容导致加载慢或卡顿。它的原理就是前后端配合来实现数据分批展示。前端会告诉后端当前要看第几页、每页显示多少条数据，通常传页码和每页条数这两个参数。后端拿到参数后，去数据库查询对应范围的数据，比如用 LIMIT 语句从（页码 -1）\*每页条数的位置开始，取每页条数的数据。同时后端会返回总数据量，前端根据总数据量和每页条数算出总页数，生成页码按钮。用户点击不同页码时，前端传新页码给后端，重新请求数据并更新页面内容。这样每次只加载当前页数据，页面就会比较流畅。
### 简述 Mybatis 的插件运行原理，以及如何编写一个插件？
**分析：**
Mybatis 仅可以编写针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 这 4 种接口的插件。
Mybatis 使用 JDK 的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这 4 种接口对象的方法时，就会进入拦截方法，具体就是 InvocationHandler 的 invoke（）方法，当然，只会拦截那些你指定需要拦截的方法。
实现 Mybatis 的 Interceptor 接口并复写 intercept（）方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。
**回答：**
MyBatis 插件主要靠动态代理和拦截器接口实现功能。它能拦截框架里的 Executor、StatementHandler、ParameterHandler、ResultSetHandler 这四个核心对象，通过代理的方式对这些对象的方法进行增强。编写插件时，首先要实现 Interceptor 接口，然后重写 intercept 方法来写具体的增强逻辑，plugin 方法返回代理对象，setProperties 方法处理配置参数。接着用@Intercepts 和@Signature 注解指定要拦截的对象和方法，比如想拦截 StatementHandler 的 prepare 方法就用这两个注解配置。最后在 MyBatis 的配置文件里注册插件，把插件类的全路径配进去。这样插件就能在目标方法执行的时候起作用了。
## Spring Boot
### **SpringBoot 是什么？有哪些优点？**
**分析：**
Spring Boot 是一个基于 Spring 的全新框架，简化了 Spring 应用的搭建以及开发过程。具体体现在以下几点：
1. **自动配置**：根据应用程序的依赖，自动进行一些常用功能的配置，比如数据库连接、日志、Web 服务器等。。
2. **内嵌服务器**：内嵌了多种常用的 Web 容器，如 Tomcat、Jetty 等，可以直接打包运行，不用额外的部署步骤。
3. **自动化依赖**：提供了一系列的“starter”依赖，简化了依赖管理的工作。
4. **监控与管理**：提供了一些监控和管理功能，比如健康检查、性能指标收集、日志记录等。
Spring Boot 以`约定大于配置`核心思想开展工作，相比 Spring 具有如下优势：
1. Spring Boot 可以快速创建独立的 Spring 应用程序。
2. Spring Boot 内嵌了如 Tomcat，Jetty 和 Undertow 这样的容器，也就是说可以直接跑起来，用不着再做部署工作了。
3. Spring Boot 无需再像 Spring 一样使用一堆繁琐的 xml 文件配置。
4. Spring Boot  可以自动配置（核心）Spring。SpringBoot 将原有的 XML 配置改为 Java 配置，将 bean 注入改为使用注解注入的方式（@Autowire），并将多个 xml、properties 配置浓缩在一个 appliaction.yml 配置文件中。
5. Spring Boot 提供了一些现有的功能，如量度工具，表单数据验证以及一些外部配置这样的一些第三方功能。
6. Spring Boot 可以快速整合常用依赖（开发库，例如 spring-webmvc、jackson-json、validation-api 和 tomcat 等），提供的 POM 可以简化 Maven 的配置。当我们引入核心依赖时，SpringBoot 会自引入其他依赖。
**回答：**
SpringBoot 是 Spring 框架的快速开发工具，主要用来简化 Spring 应用的开发和配置流程。它不用我们手动写大量配置文件，比如以前 Spring 可能要配很多 xml，现在它能根据引入的依赖自动完成大部分配置，开箱就能用。还有起步依赖的设计，把常用的依赖打包成 starter，我们直接引入一个 starter 就能用对应功能，不用自己管理依赖版本，减少版本冲突问题。它自带嵌入式服务器，像 Tomcat，开发完直接打成 jar 包就能运行，不用再部署到外部服务器，支持独立运行。另外它和 Spring 生态集成很顺畅，比如用 Spring Cloud 做微服务，或者 Spring Data 操作数据库，都能直接衔接。还有简化监控，加个 actuator 依赖就能查看应用健康状态、接口信息这些，整体就是让开发更快，配置更少，部署更简单。
### **Spring 与 SpringBoot 的区别？**
**分析：**
SpringBoot=Spring+Boot，没有 SpringBoot 之前，写 Spring 程序，维护 bean，维护配置文件，维护依赖，开发企业应用会极其麻烦。
SpringBoot 就是把 Spring 这些繁琐的东西，通过自动化配置、代码即配置、约定即配置等方式，封装成了一个脚手架，对开发者屏蔽了大量 Spring 的配置细节，并实现依赖的自动装配，让你可以快速开始一个应用，并且很好维护。
**回答：**
Spring 和 SpringBoot 的关系是基础框架和快速开发工具的关系。Spring 是核心框架，提供了 IOC、AOP 这些底层功能，但用的时候配置很麻烦，得手动写 XML 或者 JavaConfig，整合像 MyBatis、Redis 这些框架时，还要自己加依赖、管版本。SpringBoot 就是为了简化这些问题，它基于 Spring，主要做了三件事：自动配置，根据引入的依赖自动配好 Bean，不用手动写配置；起步依赖，把常用依赖打包，比如开发 web 直接引 spring-boot-starter-web，不用自己找一堆依赖和版本；内嵌了 Tomcat、Jetty 这些服务器，项目打包成 jar 就能直接运行，不用部署到外部服务器。简单说，Spring 是底层核心，Boot 是让开发更简单的工具，不用花太多时间在配置上，能更专注业务逻辑。
### Spring、Spring MVC 和 Spring Boot 有什么区别？
**分析：**
- Spring 最重要的特征是依赖注入，核心是 IoC/DI 和 AOP 的内容，使用 IoC/DI 管理依赖可以开发松耦合应用，使用 AOP 可以轻松的去实现面向切面编程。
- Spring MVC 是基于 Spring 框架的一个模块，提供了一种 Model-View-Controller（模型-视图-控制器）的开发模式。
- Spring 和 Spring MVC 的问题在于需要配置大量的参数，SpringBoot 通过一个自动配置和启动的项来解决这个问题。Spring Boot 旨在简化 Spring 应用的配置和部署过程，提供了大量的自动配置选项，以及运行时环境的内嵌 Web 服务器，这样就可以更快速地开发一个 SpringMVC 的 Web 项目。
**回答：**
Spring 是基础框架，主要提供 IOC 和 AOP 这些核心功能，解决企业级开发里的复杂问题，比如对象管理、事务控制这些。Spring MVC 是 Spring 的一部分，专门负责 Web 层开发，处理 HTTP 请求响应，像写控制器接收参数、返回数据给前端都靠它。Spring Boot 是简化 Spring 开发的工具，它把常用配置自动做好了，不用手动写 xml 配置文件，还内置了 Tomcat 这类服务器，直接就能运行项目，开发效率更高。简单说，Spring 是基础，MVC 是 Web 模块，Boot 是让开发更简单的工具。
### 项目怎么用 SpringBoot 的？
**分析：**
1. 用 maven 方式构建 spring boot 项目（jdk maven 配置）
2. 在 pom.xml 中添加 spring-boot-starter-web 依赖
3. 编写启动类，在 main 包中填写业务
4. 编写 web 访问的 controller（路由类）
5. 通过 java -jar xxx 启动程序
**回答：**
我们项目主要用 SpringBoot 做基础框架开发。启动的话就写个启动类，加个@SpringBootApplication 注解，直接 run 就行。依赖管理用 Maven，直接引 SpringBoot 提供的 starter，像 web、data-jpa、security 这些，不用自己配一堆依赖版本，省事。
Web 层就是写 Controller，用@RestController 注解，然后用@GetMapping、@PostMapping 这些定义接口路径和请求方式，参数用@RequestParam 或者@RequestBody 接，返回数据直接 return 对象，SpringBoot 会自动转成 JSON。
数据层我们用的 Spring Data JPA，写个接口继承 JpaRepository，基本的增删改查不用写 SQL，复杂查询就用@Query 注解写 JPQL。事务管理简单，在 Service 方法上加个@Transactional 注解就行。
配置都写在 application.yml 里，像数据库连接、服务端口、日志级别这些，改配置不用动代码。依赖注入就用@Autowired，把 Service 注入到 Controller，Repository 注入到 Service，解耦方便。
异常处理搞了个全局异常类，用@ControllerAdvice 和@ExceptionHandler 注解，统一返回错误信息。登录验证用拦截器，实现 HandlerInterceptor 接口，在 preHandle 里判断 token，注册到 WebMvcConfigurer 里。安全方面用 Spring Security，配个 UserDetailsService 查用户，@PreAuthorize 注解控制接口权限，比如管理员才能调某些接口。
大概就是这些，主要就是用 SpringBoot 简化配置，集成常用组件，开发效率能提不少。
### **运行 Spring Boot 有哪几种方式？**
**分析：**
1. 打包用命令或者放到容器中运行
2. 用 Maven/ Gradle 插件运行
3. 直接执行 main 方法运行
**回答：**
平时开发的时候，最常用的就是直接在 IDE 里运行，像 IntelliJ 或者 Eclipse，找到项目里带 main 方法的启动类，点一下运行按钮就能启动。另外也可以用 Maven 或者 Gradle 的命令，比如 Maven 项目敲 mvn spring-boot:run，Gradle 项目用 gradle bootRun，在命令行里执行也能启动应用。项目打包之后，Spring Boot 默认会打成 JAR 包，这时候直接用 java -jar 命令加上 JAR 包的路径就能运行，这个方式在部署的时候用得比较多，因为 JAR 包包含了所有依赖，部署起来方便。如果有特殊需求，也能配置成 WAR 包，然后放到 Tomcat 这类 Servlet 容器里启动，不过现在 JAR 包的方式更普遍一些。这几种方式覆盖了开发和部署的主要场景，根据实际情况选就行。
### **Spring Boot 需要独立的 WEB 容器运行吗？**
**分析：**
Spring Boot 可以打包成 war 包进行发布，但这有点多此一举，可以不需要做，通过自动化装配，Spring WEB 内置了 Tomcat/ Jetty 等容器可以完成这些工作进行独立部署。
**回答：**
Spring Boot 不需要独立的容器运行。它自己带了嵌入式容器，像 Tomcat、Jetty 这些常用的，默认就集成在项目里了。开发的时候写完代码，打包成 JAR 文件，直接用 java -jar 命令就能跑起来，不用再单独装个 Tomcat 之类的容器去部署。
当然，如果是特殊情况，比如老项目要求用独立容器，也能把项目打成 WAR 包，丢到外部容器里运行，但平时开发基本用不上。主要是 Spring Boot 设计的时候就想简化流程，开箱即用，所以自带容器足够了，不用额外搞独立容器这一套。
### **Spring Boot 有哪些核心注解？**
**分析：**
Spring Boot 的核心注解主要包括以下几个：
1. **@SpringBootApplication**：标识一个类作为 Spring Boot 项目的启动类。实际上，这个注解是@SpringBootConfiguration、@EnableAutoConfiguration 和@ComponentScan 这三个注解的组合。
2. **@SpringBootConfiguration**：这个注解表示该类是一个 Spring Boot 配置类，用于定义 bean。实际上是一个特殊的@Configuration 注解，用于加载 Spring Boot 项目的配置。
3. **@EnableAutoConfiguration**：这个注解用于启用 Spring Boot 的自动化配置功能。通过添加此注解，Spring Boot 可以自动引入相关的配置，减少开发人员的配置成本。
4. **@ComponentScan**：这个注解用于定义 Spring 扫描包的路径，用于发现应用程序中的 bean、组件、配置类和服务等。可以自动扫描并注册包路径下的带有@Component、@Service 等注解的类。
**回答：**
Spring Boot 的核心注解主要是为了简化开发，常用的有这么几个。@SpringBootApplication 是最核心的，用在启动类上，它把@SpringBootConfiguration@EnableAutoConfiguration@ComponentScan 这几个注解合在一起，能自动配置项目和扫描组件。
扩展的，在 Spring boot 项目开发中，经常用的注解还有 @RestController 用在控制器类上，处理 HTTP 请求，返回 JSON 数据，比以前的@Controller 加@ResponseBody 方便。@Service 用在业务逻辑层的类上，@Repository 用在数据访问层，这两个注解能让 Spring 自动管理这些类的实例。@Autowired 用来自动注入依赖，不用手动 new 对象。@Component 是通用的组件注解，那些不好归到@Service 或@Repository 的类就用它。@Configuration 定义配置类，替代以前的 XML 配置文件。@Value 可以读取配置文件里的属性值，比如配置文件里的端口号数据库地址，直接用@Value 注入到变量里。这些注解用起来能少写很多代码，开发效率高不少。
### **Spring Boot 自动装配是什么？**
**分析：**
自动装配可以根据项目中添加的依赖和其他因素，自动创建和配置 Spring 应用所需的 Bean。这意味着开发者不需要编写大量的 XML 配置或 Java 配置类来设置 Spring 容器。
例如，当你添加了 spring-boot-starter-web 依赖时，Spring Boot 会自动配置一个 Servlet 容器（如 Tomcat），并设置相关的 Servlet、Filter 和 Listener。
**回答：**
Spring Boot 自动装配就是它能帮我们自动配置项目里需要的 Bean，不用我们手动写大量配置代码。核心是@EnableAutoConfiguration 这个注解，它会去扫描项目里 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件，里面列了很多自动配置类。这些配置类会根据类路径下有没有对应的依赖（比如引入了 spring-boot-starter-web，就会有 Web 相关的配置类）和配置文件里的属性，通过@Conditional 这类注解判断条件是否满足，满足的话就会把需要的 Bean 注册到 Spring 容器里。比如我们用 Spring Boot 开发 Web 项目，不用手动配 DispatcherServlet，自动装配会根据依赖和默认配置帮我们搞定。如果想改默认配置，直接在 application.properties 里设置对应属性，或者自己定义同名 Bean 就能覆盖默认的，很方便。
### SpringBoot 自动化配置原理是什么？
**分析：**
知道自动化装配流程，知道条件装配，知道 SPI 机制，了解 Spring SPI 和 Java SPI 的区别
先从自动配置首先从注解说起。
@SpringBootApplication 由三个注解组成 @ComponentScan， @EnableAutoConfiguration，@SpringBootConfiguration，其实就是@Configuration 注解
其中 @EnableAutoConfiguration 通过 @Import 注解 将 AutoConfigurationImportSelector.class 这个类引进来，
该类会去加载所有 jar 包的 META-INF 下面的 spring-factories 配置文件，这里其实用到了 spring 里面的 SPI 机制
这个文件是个 key-value 的形式，key 是 EnableAutoConfiguration 的全路径名，value 是各个需要自动配置的类，然后 SpringBoot 默认在这个配置文件中定义了大约 100 多个常用的配置类，然后再根据 Condition 按需加载我们需要的配置类，比如在配置文件中增加了对应的配置，对应的配置类就会生效
**推荐阅读**
[深入剖析 Spring Boot 的 SPI 机制 - 掘金](https://juejin.cn/post/7132742686099898398)
**回答：**
SpringBoot 自动化配置的核心是@EnableAutoConfiguration 注解。它通过 SPI 机制，扫描类路径下 META-INF 目录里的配置文件，这些文件定义了很多自动配置类。每个自动配置类上都有条件注解，比如判断项目里有没有需要的类、有没有对应的配置属性，满足条件后就会把相关的 Bean 注册到 Spring 容器里。这样我们不用手动写配置，SpringBoot 会根据项目依赖和配置自动加载需要的组件，实现自动配置。
### **Springboot 启动流程是什么？（复杂版）**
**分析：**
总的来说，SpringBoot 应用的启动流程就分为 `SpringApplication`的创建，与 `SpringApplication`` ``run` 方法的调用两部分
1. 调用静态 run 方法时，我们首先创建一个 SpringApplication 的对象实例。在创建实例时，进行了一些基本的初始化操作。大体如下
> - 根据 classpath 的类推断 ApplicationContext 类型，设置为 webApplicationType
> - 加载所有的 ApplicationContextInitializer
> - 加载所有的 ApplicationListener
> - 根据入参，设置启动类的类信息 webApplicationType
1. 初始化完成后，执行 run（）方法。先查找并加载所有的 SpringApplicationRunListener，放入到 SpringApplicationRunListeners 这个集合类里面来进行统一管理。然后调用他们的 starting（）来通知所有的 listeners 程序要启动
2. 创建并配置当前应用的 Environment 环境（包括配置 property 和对应的 profile 信息，将其放入 environment 变量），然后通过 SpringApplicationRunListeners 的 environmentPrepared（）来进行通知
3. 根据初始化类时 webApplicationType 信息，创建具体的 ApplicationContext 实例 context
4. 加载所有的 ApplicationContextInitializer，然后遍历调用 initialize（）方法。
5. 将 environment 和 context 进行绑定，然后调用 SpringApplicationRunListeners 的 contextPrepared（）方法
6. 通过自动装配，将获取的所有配置@EnableAutoConfiguration 以及其他形式的 IoC 容器配置加载到已经准备完毕的 ApplicationContext。然后调用 SpringApplicationRunListeners 的 contextLoaded（）方法
7. 调用 SpringApplication 的 refresh（）方法，配置 beanfactory，将所有的标注有@EnableAutoConfiguration 中@Import 注解进行解析处理，将获取的所有 bean 类进行初始化，进行 ioc 容器的最终处理。
8. 调用 SpringApplicationRunListeners 的 started（context）方法；
9. 当前 ApplicationContext 中是否注册有 CommandLineRunner，如果有，则遍历执行它们。
10. 调用 SpringApplicationRunListeners 的 running（context）方法。
以下是⼀个简单的 Spring Boot 启动类的示例代码： 
```Java
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
@SpringBootApplication 
public class DemoApplication { 
    public static void main(String[] args) { 
        SpringApplication.run(DemoApplication.class, args); 
    } 
} 
```
推荐阅读：https://juejin.cn/post/7035910505810100255
**回答：**
Spring Boot 启动首先从 main 方法开始，调用 SpringApplication.run（）。这时候先初始化 SpringApplication 实例，会判断应用类型是普通 Java 还是 Web 应用，同时加载配置的初始化器和监听器。接着执行 run 方法，先启动计时器记录启动时间，然后准备环境，包括加载配置文件、系统变量这些配置信息。之后默认会打印 Banner 图，当然可以关掉。下一步是创建应用上下文，根据应用类型选对应的上下文，比如 Web 应用常用 AnnotationConfigServletWebServerApplicationContext。然后刷新上下文，这是核心步骤，会扫描并加载 Bean，完成 Bean 的初始化，同时启动嵌入式服务器像 Tomcat，这时候会注册 DispatcherServlet 这些 Web 组件。最后执行实现了 CommandLineRunner 或 ApplicationRunner 接口的类，完成启动后的一些初始化操作，整个流程就结束了。
### **Springboot 启动流程是什么？（基础版）**
**分析：**
SpringApplication 这个类主要做了以下四件事情：
1. 推断应用的类型是普通的项目还是 Web 项目
2. 查找并加载所有可用初始化器 ， 设置到 initializers 属性中
3. 找出所有的应用程序监听器，设置到 listeners 属性中
4. 推断并设置 main 方法的定义类，找到运行的主类
SpringBoot 启动大致流程如下 ：
**回答：**
Spring Boot 启动主要从 main 方法开始，调用 SpringApplication.run（）。先初始化 SpringApplication，这时候会判断应用类型是 Web 还是普通环境，确定用哪个应用上下文类。接着准备应用环境，读取配置文件比如 application.properties，还有命令行参数这些配置信息。然后创建应用上下文，根据前面判断的环境类型选对应的上下文，比如 Web 环境常用 AnnotationConfigServletWebServerApplicationContext。之后刷新上下文，这一步会扫描项目里的 Bean 并注册到容器，同时处理@EnableAutoConfiguration 注解，加载 META-INF/spring.factories 里的自动配置类，根据条件注解决定哪些配置生效。最后启动嵌入式服务器，像 Tomcat，这样应用就启动完成可以对外提供服务了。
### Spring Boot 有几种方式读取配置文件？
**分析：**
Spring Boot 中读取配置文件有以下 5 种方法：
- 使用 @Value 读取配置文件。
- 使用 @ConfigurationProperties 读取配置文件。
- 使用 @PropertySource 读取配置文件。
- 使用 Environment 读取配置文件。
- 使用原生方式读取配置文件。
其中最常用的是前 3 种，如果读取某一个配置项可使用 @Value，如果读取一组配置项可使用 @ConfigurationProperties，如果要指定读取某一个具体的配置文件可使用 @PropertySource 来指定。
**回答：**
Spring Boot 读取配置文件主要有这么几种常用方式。最直接的是@Value 注解，在需要的属性上用@Value（"\${配置 key}"）就能获取对应的值，比如配置里有 app.name=demo，就在属性上写@Value（"\${app.name}"）。然后是@ConfigurationProperties，这个适合批量绑定配置，比如配置里有 spring.datasource.url、spring.datasource.username 这些前缀相同的属性，就建个类，加@ConfigurationProperties（prefix="spring.datasource"），类里定义 url、username 等属性，不用每个都写@Value，直接用这个类就能拿到所有相关配置。还有 Environment 接口，注入 Environment 对象后，调用 getProperty（"key"）方法也能获取配置值，比如 env.getProperty（"app.name"）。另外可以用@PropertySource 指定读取自定义配置文件，比如在配置类上用@PropertySource（"classpath:custom.properties"），就能读取非默认的 application.properties 或 application.yml 之外的配置文件。这些方式日常开发用得比较多，根据配置数量和使用场景选就行。
### Spring Boot 配置文件的加载顺序？
**分析：**
1. 先会加载项目中的默认配置文件，通常位于`src/main/resources`目录下的`application.properties`或`application.yml`。
2. 加载**特定命名**的配置文件，如`application-{profile}.properties`或`application-{profile}.yml`。
3. 如果存在`spring.config.location`命令行参数，Spring Boot 会按照指定的路径加载配置文件。
加载过程中，如果同一个属性在多个配置文件中都有定义，那么后加载的配置文件中的值将覆盖先加载的配置文件中的值。
**回答：**
Spring Boot 配置文件加载是有优先级的，从高到低来说，首先是命令行参数，启动时用--开头的参数优先级最高，比如--spring.profiles.active=dev 会覆盖其他配置。然后是 JVM 系统属性，就是用-D 指定的，比如-Dspring.config.location=...这种。接下来是操作系统环境变量，像系统里配置的 SPRING_PROFILES_ACTIVE 这类环境变量。再往下是应用外部的配置文件，先看当前项目目录下的 config 文件夹，然后是当前目录本身，之后是 classpath 里的 config 文件夹，最后是 classpath 根目录。这些位置里，带 profile 的配置文件会覆盖不带 profile 的，比如 application-dev.properties 会覆盖 application.properties 里的相同配置。同一位置下，properties 文件优先级比 yml 高，后加载的配置会覆盖前面的，所以如果有同名配置，后面加载的生效。
### bootstrap.properties 和 application.properties 有何区别 ？
**分析：**
**加载顺序和优先级**：
- `bootstrap.properties`（或`bootstrap.yml`）是 Spring Boot 应用程序启动时最先加载的配置文件。
- `application.properties`（或`application.yml`）是在`bootstrap.properties`之后加载。
**配置范围**：
- `bootstrap.properties`的作用范围是全局的，会被该项目下所有的 Spring 应用程序共享。
- `application.properties`主要关注应用程序本身的配置属性，比如数据库连接、端口设置、日志级别、缓存配置等。
**回答：**
这两个都是 Spring Boot 的配置文件，但加载顺序和主要用途不一样。bootstrap 会在应用启动最开始的时候加载，比 application 早。bootstrap 主要用来加载外部配置，特别是用 Spring Cloud 的时候，比如从外部配置中心拉取配置就得靠它，而且它的配置优先级高，一般不会被其他配置覆盖。application 是应用自己的常规配置，像数据库连接、服务端口这些，加载时间在 bootstrap 之后，里面的配置可能会被后面加载的配置覆盖掉。所以简单说，bootstrap 先加载管外部配置，application 后加载管应用自身配置。
### Spring Boot Actuator 是什么？
**分析：**
**Spring Boot Actuator 是 Spring Boot 提供的一个用于监控和管理应用程序的模块**。包括不限于健康检查、日志监控、指标收集、审计功能、报警功能等。开发人员可以通过这些监控及时发现问题并解决。
默认情况下，Spring Boot Actuator 已经集成在 Spring Boot 中，只需要在项目中添加依赖并配置一些参数即可使用。
**回答：**
Spring Boot Actuator 是 Spring Boot 提供的一个监控和管理应用的工具。它主要帮我们实时了解应用的运行状态，不用自己写监控代码。它提供了很多接口，比如健康检查接口，能直接看应用是不是正常运行；指标接口，可以看内存使用、CPU 占用这些数据；还有环境信息接口，能查配置参数、系统属性这些。
用的时候得先在项目里加个 actuator 依赖，然后在配置文件里设置要暴露哪些接口。默认有些接口是不开放的，比如关闭应用的 shutdown 接口，需要手动开启。通过这些接口，运维人员能随时监控应用状态，出问题时能快速定位，比如内存泄漏或者配置错误，不用自己开发监控功能，挺实用的。
### Spring Boot 项目如何热部署？
**分析：**
热部署意味着能够在不重启应用程序服务器的情况下，重新加载代码的更改，可以大大提高开发效率。
实现 Spring Boot 项目的热部署只需要在 pom.xml 中添加以下依赖：
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```
热部署主要用于开发环境，不建议在生产环境中使用。
**回答：**
其实 Spring Boot 热部署最常用的就是用它自带的 DevTools 工具。首先得在项目里加依赖，Maven 的话就在 pom.xml 里加 spring-boot-devtools 依赖，Gradle 项目就加对应的依赖配置。然后在开发工具比如 IDEA 里做些设置，打开 Settings 找到 Build Execution Deployment 里的 Compiler，勾选 Build project automatically，再按 Shift+Ctrl+Alt+/调出 Registry，把 compiler.automake.allow.when.app.running 这个选项勾上。这样改完代码保存后，项目就会自动重启实现热部署了。DevTools 的原理是用了两个类加载器，基础类比如 Spring 框架的类用 base 加载器加载，我们自己写的应用类用 restart 加载器加载，重启的时候只替换 restart 加载器里的类，所以重启速度比手动停启快很多。这样开发时改完代码不用手动重启项目，直接就能看到效果，挺方便的。
### Spring Boot 中的 starter 是什么 ？
**分析：**
starter 就是一个组件或框架的依赖，通过引入某个组件的 starter，可以简化开发人员的工作量。
以 mybatis-spring 和 mybatis-spring-boot-starter 为例。
**在 Spring 项目中使用 mybatis 大概有以下几个步骤：**
1. 引入 spring、mybatis、jdbc 等相关依赖。
2. 创建 `mybatis-config.xml` 配置文件。
   - 声明数据源 DataSource。
   - 声明 SqlSessionFactoryBean。
   - 声明 MapperScannerConfigurer。
   - 声明等等配置。
3. 编写 `xxxMapper.xml` 及 `xxMapper.java` 文件。
4. 业务编码调用。
**在 SpringBoot 项目中使用 Mybatis 大概有以下几个步骤：**
1. 引入 `mybatis-spring-boot-starter` 依赖。
2. `application.properties` 文件中添加相关配置。
3. 编写 `xxxMapper.xml` 及 `xxMapper.java` 文件。
4. 业务编码调用。
可以明显的感觉到使用 Starter 后，**不用管理相关依赖，不用编写繁琐的配置文件**。这就是 Starter。
**回答：**
Spring Boot 里的 starter 就是用来简化开发的工具，主要解决依赖管理和自动配置的问题。它本质上是一个整合好的依赖包，里面包含了开发某个功能常用的所有依赖，不用我们手动一个个去添加。比如开发 web 应用时，用 spring-boot-starter-web，它就已经整合了 Spring MVC、Tomcat 这些 web 开发需要的依赖，直接引入就能用，避免自己加依赖时出现版本冲突。而且 starter 还会帮我们自动配置相关组件，像 web starter 会自动配置 DispatcherServlet、视图解析器这些，不用我们写额外的配置代码。这样开发的时候，只要引入对应的 starter，就能快速上手开发，省了很多配置和依赖管理的麻烦，提高开发效率。
### 如何自定义一个 starter？
**分析：**
1. 创建一个项目，命名为 demo-spring-boot-starter，引入 SpringBoot 相关依赖
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
1. 编写配置文件
这里定义了属性配置的前缀
```Java
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String name;
    //省略getter、setter
}
```
1. 自动装配
创建自动配置类 HelloPropertiesConfigure
```Java
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloPropertiesConfigure {
}
```
1. 配置自动类
在`/resources/META-INF/spring.factories`文件中添加自动配置类路径
```Java
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  cn.fighter3.demo.starter.configure.HelloPropertiesConfigure
```
1. 测试
至此，随手写的一个自定义 SpringBoot-Starter 就完成了，虽然比较简单，但是完成了主要的自动装配的能力。
- 创建一个工程，引入自定义 starter 依赖
```XML
<dependency>
    <groupId>cn.fighter3</groupId>
    <artifactId>demo-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
- 在配置文件里添加配置
```Java
hello.name=张三
```
- 测试类
```Java
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {
    @Autowired
    HelloProperties helloProperties;
    @Test
    public void hello(){
        System.out.println("你好，"+helloProperties.getName());
    }
}
```
- 运行结果
**回答：**
自定义 starter 其实不难，先建个 Maven 项目，写好基本的坐标信息，比如 groupId、artifactId 这些。然后主要是做自动配置，得写个@Configuration 类，里面定义需要自动装配的 Bean。为了控制 Bean 什么时候生效，得用条件注解，比如@ConditionalOnClass 检查依赖在不在，@ConditionalOnMissingBean 让用户能自己定义 Bean 覆盖默认的。接着要在 META-INF/spring 目录下建个 org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件，把配置类全类名写进去，这样 Spring Boot 启动时才能扫描到。如果需要用户通过配置文件改参数，就写个@ConfigurationProperties 注解的类，绑定配置项。最后用 mvn install 打包，其他项目引这个依赖，就能自动用里面的 Bean 了，不用手动配置。
### Spring Boot 有哪些 starter ？
**分析：**
1. **spring-boot-starter-web**：用于构建 Web 应用程序的 Starter，包括 Spring MVC 和 Tomcat 服务器。
2. **spring-boot-starter-data-jpa**：用于与关系型数据库进行交互的 Starter，用于简化数据库访问层的开发。
3. **spring-boot-starter-data-redis**：用于与 Redis 数据库进行交互的 Starter。
4. **spring-boot-starter-actuator**：用于监控和管理 Spring Boot 应用的 Starter。
5. **mybatis-spring-boot-starter**：用于在 Spring Boot 应用中集成 MyBatis 。
除了上述常用的 Starter，Spring Boot 还提供了许多其他的 Starter。比如数据库连接池、安全、消息队列等。
**回答：**
Spring Boot 的 starter 挺多的，最基础的是 spring-boot-starter，提供自动配置和依赖管理这些核心功能。做 web 开发常用 spring-boot-starter-web，里面集成了 Spring MVC 和 Tomcat，直接就能开发 web 应用。操作数据库的话，用 JPA 就选 spring-boot-starter-data-jpa，用 JDBC 就用 spring-boot-starter-jdbc。安全方面有 spring-boot-starter-security，能处理登录认证这些。测试的时候用 spring-boot-starter-test，集成了 JUnit 这些测试工具。页面渲染常用 spring-boot-starter-thymeleaf。缓存可以用 spring-boot-starter-cache，支持 Redis 这些。消息队列比如对接 RabbitMQ 用 spring-boot-starter-amqp。还有监控应用状态的 spring-boot-starter-actuator，看健康指标这些。这些都是开发里比较常用的 starter。
### Spring Boot 打成的 jar 和普通的 jar 有什么区别 ？
**分析：**
Spring Boot 项目最终打包成的 jar 是可执行 jar ，这种 jar 可以直接通过 `java -jar xxx.jar` 命令来运行，这种 jar 不可以作为普通的 jar 被其他项目依赖，即使依赖了也无法使用其中的类。
Spring Boot 的 jar 无法被其他项目依赖，主要还是他和普通 jar 的结构不同。
普通的 jar 包，解压后直接就是包名，包里就是我们的代码，而 Spring Boot 打包成的可执行 jar 解压后，在 `\BOOT-INF\classes` 目录下才是我们的代码，因此无法被直接引用。如果非要引用，可以在 pom.xml 文件中增加配置，将 Spring Boot 项目打包成两个 jar ，一个可执行，一个可引用。
**回答：**
Spring Boot 打的 jar 和普通 jar 最主要的区别就是能不能直接运行。Spring Boot 的 jar 是可执行的，直接用 java -jar 命令就能跑起来，不用额外配 Tomcat 这些服务器。因为它里面自带了内嵌的 Servlet 容器，像 Tomcat、Jetty 这些，打包的时候就把容器和应用代码、依赖都一起打进 jar 里了。而且它的结构有个 BOOT-INF 目录，里面专门放应用的 class 文件和依赖的 jar 包，普通 jar 一般没这个结构。普通 jar 更多是作为类库给别的项目用的，自己单独跑不起来，得依赖外部环境，比如放到独立的 Tomcat 里部署才能运行。简单说就是 Spring Boot 的 jar 是个完整的可运行应用包，普通 jar 只是代码和类的集合。