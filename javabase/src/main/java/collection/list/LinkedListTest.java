package collection.list;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    public static void main(String[] args) {
        addAll();
    }

    private static List<String> addElem() {
        List<String> linkedList = new LinkedList<String>();
        linkedList.add("111");
        linkedList.add("222");

        return linkedList;
    }

    private static void addAll() {
//        List<String> list = addElem();
        List<String> list = null;

        List<String> linkedList = new LinkedList<String>();

        linkedList.addAll(list);

        System.out.println(linkedList);
    }
}
