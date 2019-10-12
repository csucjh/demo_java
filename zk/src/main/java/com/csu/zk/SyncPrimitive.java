package com.csu.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class SyncPrimitive implements Watcher {

    static ZooKeeper zk = null;
    static Integer mutex;
    String root;

    SyncPrimitive(String address, String root) {
        mutex = new Integer(-1);
        this.root = root;

        if (zk == null) {
            try {
                System.out.println("Starting ZK:");
                zk = new ZooKeeper(address, 3000000, this);
                System.out.println("Finished starting ZK: " + zk);
            } catch (IOException e) {
                System.out.println(e.toString());
                zk = null;
            }
        }
        //else mutex = new Integer(-1);


        // Create barrier node
        if (zk != null) {
            try {
                Stat s = zk.exists(root, false);
                if (s == null) {
                    zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            } catch (KeeperException e) {
                System.out
                        .println("Keeper exception when instantiating queue: "
                                + e.toString());
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception");
            }
        }

    }

    synchronized public void process(WatchedEvent event) {
        synchronized (mutex) {
            System.out.println("Thread-id:" + Thread.currentThread().getName() + "---Process: " + event.getType());
            mutex.notify();
        }
    }
}
