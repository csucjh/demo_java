package zkclient.test;

import org.apache.zookeeper.ZooKeeperMain;

public class CientTest {

    public static void main(String[] args) throws Exception {
        String tempDir = System.getProperty("java.io.tmpdir");
        args = new String[]{"-server","10.2.1.85:2181"};
        ZooKeeperMain.main(args);
    }
}
