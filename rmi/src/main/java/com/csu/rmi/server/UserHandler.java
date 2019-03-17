package com.csu.rmi.server;

import com.csu.rmi.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserHandler extends Remote {

    String getUserName(int id) throws RemoteException;

    int getUserCount() throws RemoteException;

    User getUserByName(String name) throws RemoteException;

}
