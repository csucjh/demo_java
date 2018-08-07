package collection.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        addElem();
    }

    private static void addElem() {
        List<String> arrayList = new ArrayList<String>(1);
        arrayList.add("111");
        arrayList.add("222");
    }

}
