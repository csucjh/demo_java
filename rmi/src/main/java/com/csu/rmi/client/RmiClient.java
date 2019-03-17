package com.csu.rmi.client;

import com.csu.rmi.server.UserHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {


    public static void main(String[] args) {
        try {
            String rmiName = "rmi://localhost:1022/user";
            UserHandler handler = (UserHandler) Naming.lookup(rmiName);
            int count = handler.getUserCount();
            String name = handler.getUserName(1);
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("user: " + handler.getUserByName("lmy86263"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
