    mockito
        能简易和spring结合使用，提供@Mock和@Spy注解
        在spring-boot中也是默认的打桩方式
        
        
    PowerMock
        虽然是基于mocktio扩展的，但只是多了private/final/static method的mock，实际上用处并不是很大
        
        与spring虽然也能整合，但是注解使用麻烦，如下：
            @RunWith(PowerMockRunner.class)
            @PowerMockIgnore({"javax.management.*"})
            @PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
            @PrepareForTest({A.class, B.class})
        
        使用@RunWith(PowerMockRunner.class)注解的类，这种方式是不能重用TestContext的，导致每个测试类，都需要启动spring环境，耗费了大量时间
            PowerMock为每个带有@PrepareForTest的测试类或带有@PrepareForTest的测试方法都会创建一个新的类加载器
            PowerMock代理了大多数类，ClassLoader和标准运行的方式不同，测试类越多重复创建类加载也越多，最后崩溃：jvmcrashed
            参考：
                https://blog.csdn.net/yys79/article/details/80332332
                https://github.com/powermock/powermock/issues/800
        