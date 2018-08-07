Spring lets you define multiple contexts in a parent-child hierarchy. 
spring允许你在父子继承关系中定义多个上下文.

The applicationContext.xml defines the beans for the “root webapp context”, i.e. the context associated with the webapp. 
applicationContext.xml文件是为“根webapp应用上下文”定义bean, 也就是说它的上下文是和webapp相关联的.

The spring-servlet.xml (or whatever else you call it) defines the beans for one servlet’s app context. There can be many of these in a webapp, 
spring-servlet.xml文件(或是其他的你习惯的称呼)是为了一个servlet应用上下文定义bean. 在一个webapp中可以有多个此配置文件,

one per Spring servlet (e.g. spring1-servlet.xml for servlet spring1, spring2-servlet.xml for servlet spring2). 
每一个spring的servlelt(例如: 名为spring1的servlet拥有配置文件spring1-servlet.xml, 名为spring2的servlet拥有配置文件spring2-servlet.xml).

Beans in spring-servlet.xml can reference beans in applicationContext.xml, but not vice versa. 
在spring-servlet.xml中定义的bean可以直接引用在applicationContext.xml中定义的bean, 但是反过来不可以.

All Spring MVC controllers must go in the spring-servlet.xml context. 
所有springmvc的Controller必须在spring-servlet.xml对应的上下文中运行.

In most simple cases, the applicationContext.xml context is unnecessary. It is generally used to contain beans that are shared between all servlets 
在大多数简单的情况下, applicationContext.xml对应的上下文并不必须. 它通常用来包含那些bean用来在webapp中所有servlet之间共享.

in a webapp. If you only have one servlet, then there’s not really much point, unless you have a specific use for it. 
如果你只有一个servlet, 那么实际没有什么必要定义applicationContext.xml, 除非你有特别应用.