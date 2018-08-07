package xstream.test;

import xstream.Data;
import xstream.Header;
import xstream.body.UserInfo;
import xstream.util.XmlUtil;

public class Test {
    public static void main(String[] args) {
        Header header = new Header();
        header.setVersion("1.0.1");
        header.setSign("85049cd7254f582045d21ea9a74e9af2");


        UserInfo user = new UserInfo();
        user.setName("陈唐关");
        user.setAge("2");
        user.setAlias("关心");

        Data<UserInfo> data = new Data<UserInfo>();
        data.setHeader(header);
        data.setBody(user);

        String xml = XmlUtil.beanToXml(data);
        System.out.println(xml);


        Data<UserInfo> parseData = XmlUtil.xmlToBean(xml, UserInfo.class);
        System.out.println(parseData);
    }
}
