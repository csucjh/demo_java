package collection.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {

//        nullKey();

//        hashConflict();

//        containKeyAndGetKey();

        iterator();
    }

    /**
     * null的key，默认放在index为0的slot
     */
    private static void nullKey() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(null, "就是null");

        String val = map.get(null);
        System.out.println(val);
    }

    /**
     * 手动制造和null键hash冲突，发现后进入的靠近主链作为头结点，指向侧链(拉链)下一个节点(上一个hash冲突的头结点)
     */
    private static void hashConflict() {
        Map<Object, String> map = new HashMap<Object, String>();
        map.put(null, "就是null");

        Object key = new Object() {
            // null的key的hashCode是0，这里模拟哈希冲突
            @Override
            public int hashCode() {
                return 0;
            }
        };
        map.put(key, "就是null-obj");

        // hash冲突时，后进入slot的在靠近主链的一侧
        String val = map.get(key);
        System.out.println(val);

        val = map.get(null);
        System.out.println(val);
    }


    /**
     * 如果要判断Map是否包含某个key，直接get最好，因为containsKey操作其实就是执行了get操作
     * 如果key值在map中不存在操作上等价(都只操作一次map)
     * 如果key值在map存在，则一般会紧接着执行一次get操作获取数据，这种就有了两次get
     */
    private static void containKeyAndGetKey() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(null, "就是null");

        String val = map.get(null);
        System.out.println(val);

        // containsKey内部就会getEntry，实际上执行了一次get操作
        map.containsKey(null);
    }


    /**
     * 当有hash冲突时，迭代会先遍历单链，之后再到下一个hash槽(slot)
     */
    private static void iterator() {
        Map<Object, String> map = new HashMap<Object, String>();
        map.put(null, "就是null");

        Object key = new Object() {
            // null的key的hashCode是0，这里模拟哈希冲突
            @Override
            public int hashCode() {
                return 0;
            }
        };
        map.put(key, "就是null-obj");

        map.put(new Object(), "obejct");

        for (Map.Entry<Object, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

    }
}
