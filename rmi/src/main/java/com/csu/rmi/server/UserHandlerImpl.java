package com.csu.rmi.server;

import com.csu.rmi.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {

    protected UserHandlerImpl() throws RemoteException {
    }

    @Override
    public String getUserName(int id) {
        return "csucjh";
    }

    @Override
    public int getUserCount()  {
        return 1;
    }

    @Override
    public User getUserByName(String name) {
        return new User("csucjh", 1);
    }
}
