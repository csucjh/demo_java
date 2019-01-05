package zkclient.test;

import org.apache.zookeeper.ZooKeeper;

public class Test {

    public static void main(String[] args) throws Exception {
        long sessionId = 0x1674527f3800763L;
        String passwd = null;
        ZooKeeper zkClient = new ZooKeeper("10.6.1.135:2181", 5000, null, sessionId, null);
        zkClient.close();

    }
}
