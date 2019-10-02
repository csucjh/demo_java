package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class Dom4jTest {

    public static void main(String[] args) throws DocumentException {

        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?><root><head><version>1.0.1</version><sign>85049cd7254f582045d21ea9a74e9af2</sign></head><body><name>陈唐关</name><age>2</age><alias2>关心</alias2></body></root>";

        Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML

        System.out.println(doc);
    }
}
