调试server端：

    启动类
    org.apache.zookeeper.server.quorum.QuorumPeerMain
    
    命令行参数指定一个配置
    D:\developer\zookeeper-3.4.11\conf\zoo.cfg
    
    
    JVM参数可以配置，参考 http://zookeeper.apache.org/doc/r3.5.5/zookeeperAdmin.html
        # nio的工作者线程数(默认是cpu核数的2倍)
        -Dzookeeper.nio.numWorkerThreads=1



