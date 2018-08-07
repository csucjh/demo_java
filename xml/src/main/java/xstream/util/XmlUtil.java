package xstream.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import xstream.Body;
import xstream.Data;
import xstream.Header;

import java.io.StringWriter;

public class XmlUtil {

    public static <T extends Body> String beanToXml(Data<T> data) {
        String xml = objectToXml(data);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
        stringBuilder.append(xml);
        return stringBuilder.toString();
    }

    public static <T extends Body> Data xmlToBean(String xml, Class<T> cls) {
        XStream xstream = new XStream();
        // 将具体子类型绑定到基类(这行实现了多态)
        xstream.addDefaultImplementation(cls, Body.class);
        xstream.processAnnotations(new Class[]{Data.class, Header.class, cls});

        return (Data<T>) xstream.fromXML(xml);
    }

    public static String objectToXml(Object object) {
        XStream xstream = new XStream();

        // 自动处理类的注解
        xstream.autodetectAnnotations(true);

        // 去除默认的class属性
        xstream.aliasSystemAttribute(null, "class");

        // 压缩xml，不换行
        StringWriter sw = new StringWriter();
        xstream.marshal(object, new CompactWriter(sw));
        String xml = sw.toString();

        return xml;
    }
}
