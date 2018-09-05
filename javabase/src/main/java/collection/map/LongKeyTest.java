package collection.map;

import java.util.HashMap;
import java.util.Map;

public class LongKeyTest {
    public static void main(String[] args) {
        Map<Long, Object> map = new HashMap<>();

        Long key = 100L;
        map.put(key, null);

        Long key2 = 100L;

        System.out.println(map.containsKey(key));
        System.out.println(map.containsKey(key2));
    }
}
