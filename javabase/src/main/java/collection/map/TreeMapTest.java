package collection.map;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args) {
        test2();
    }


    /**
     * 默认根据key的自然顺序排序，可以传入指定比较器
     * 满足排序二叉树特性，左子树小于当前节点，当前节点小于右子树
     * 插入后会触发红黑树的调整
     */
    private static Map<String, Object> test() {
        Map<String, Object> map = new TreeMap<String, Object>();

        map.put("3", "333");
        map.put("1", "111");
        map.put("2", "222");

        map.get("1");
        map.get("2");
        map.get("3");

        return map;
    }

    /**
     *
     */
    public static void test2() {
        Map<String, Object> map = test();

        map.remove("1");
        return;
    }
}
