package collection.set;

import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("111");
        set.add("111");
        set.add("222");
        System.out.println(set);
    }
}
