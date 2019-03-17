package com.csu.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * -verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
 * -Dsun.rmi.dgc.server.gcInterval=20000
 * -Dsun.rmi.dgc.client.gcInterval=20000
 * -XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=15
 */
public class StartServer {

    public static void main(String[] args) {
//        registerV1();
        registerV2();
    }


    /**
     * 不依赖rmiregistry程序
     */
    public static void registerV2() {
        try {
            Registry registry = LocateRegistry.createRegistry(1022);
            UserHandler userHandler = new UserHandlerImpl();
            registry.rebind("user", userHandler);
            System.out.println(" rmi server is ready ...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此种方式依赖本地的注册表程序
     * rmiregistry 1022
     * start rmiregistry 1022
     * 如果是使用maven管理工程，则在target/classes目录中启动该程序
     */
    public static void registerV1() {
        try {
            String rmiName = "rmi://localhost:1022/user";
            UserHandler userHandler = new UserHandlerImpl();
            Naming.rebind(rmiName, userHandler);
            System.out.println(" rmi server is ready ...");
            System.out.println(Naming.list(rmiName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
