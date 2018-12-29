package main;

import com.csu.spring.anno.SpringConfig;
import com.csu.spring.anno.Springs;
import com.csu.spring.anno.User;

public class AnnoWebApplicationContextApp {

    public static void main(String[] args) {

        User user = new User(12, "小强");

        Springs.newInstance().configClass(SpringConfig.class)
                .inject("user", user)
                .create();
    }

}
