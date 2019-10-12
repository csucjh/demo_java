package com.csu.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

/**
 * Barrier
 */
public class BarrierTest extends SyncPrimitive {

    int size;
    String name;

    /**
     * Barrier constructor
     *
     * @param address
     * @param root
     * @param size
     */
    BarrierTest(String address, String root, int size) {
        super(address, root);
        this.size = size;

        // My node name
        try {
//            name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());
            name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Join barrier
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */

    boolean enter() throws KeeperException, InterruptedException {
        System.out.println("create node start:" + name);
        name = zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create node end:" + name);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                System.out.println("watcher node children:" + root + " size:" + list.size());

                if (list.size() < size) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * Wait until all reach barrier
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    boolean leave() throws KeeperException, InterruptedException {
        System.out.println("delete node start:" + name);
        zk.delete(name, 0);
        System.out.println("delete node end:" + name);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                System.out.println("watcher node children:" + root + " size:" + list.size());
                if (list.size() > 0) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * java BarrierTest localhost 2
     *
     * @param args
     */
    public static void main(String args[]) {
        BarrierTest b = new BarrierTest(args[0], "/b1", new Integer(args[1]));
        try {
            boolean flag = b.enter();
            System.out.println("Entered barrier: " + args[1]);
            if (!flag) System.out.println("Error when entering the barrier");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Generate random integer
        Random rand = new Random();
        int r = rand.nextInt(100);
        // Loop for rand iterations
        for (int i = 0; i < r; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        try {
            b.leave();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Left barrier");
    }
}
