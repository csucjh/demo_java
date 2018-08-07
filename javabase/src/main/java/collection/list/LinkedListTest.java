package collection.list;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    public static void main(String[] args) {
        addElem();
    }

    private static void addElem() {
        List<String> linkedList = new LinkedList<String>();
        linkedList.add("111");
        linkedList.add("222");
    }
}
