package collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {

        testLink();
    }

    /**
     * LinkedHashMap内部维护了一个header头结点，继承了HashMap；即是LinkedList与HashMap的融合；
     * LinkedHashMap重写了HashMap的createEntry，每次向Map中添加完节点都会将其添加到LinkedList最后，默认保持插入顺序；
     * LinkedHashMap的Entry继承了HashMap的Entry，增加了addBefore方法，功能与LinkedList的addBefore一致
     */
    private static void testLink() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put(null, "就是null");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        String val = map.get(null);
        System.out.println(val);
    }
}
