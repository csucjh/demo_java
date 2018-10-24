package start;

import org.apache.catalina.startup.Bootstrap;

import java.io.File;

public class Main {

    /**
     * 设置默认用户目录
     * 再找不到bootstrap.jar的情况下，以user.dir作为catalina.home和catalina.base目录
     */
    static String USER_DIR = System.getProperty("user.dir") + File.separator + "tomcat8" + File.separator + "show";

    static {
        System.setProperty("user.dir", USER_DIR);
    }

    public static void main(String[] args) {

        Bootstrap.main(args);
    }
}
