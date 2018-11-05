package string;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class ContainTest {

    public static void main(String[] args) {
        List<String> test = new LinkedList<String>();
        test.add("111");
        test.add("222");


        List<String> test2 = new LinkedList<String>();
        test2.add("111");
        test2.add("222");
        test2.add("333");

        System.out.println(test2.containsAll(test));

        String realName = "中文文";
        realName = StringUtils.rightPad(realName.substring(0, 1), realName.length(), "*");
        System.out.println(realName);
    }
}
