package jackson;

import model.User;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class TestTypeInfo {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(12);
        user.setName("姓名");
        user.setFeatures(Arrays.asList("aaaa", "bbbb", "ccc"));

        byte[] bytes = JacksonUtil.serialize(user);
        try {
            /**
             * {"@class":"model.User","age":12,"name":"姓名","features":["java.util.Arrays$ArrayList",["aaaa","bbbb","ccc"]]}
             *
             * {"age":12,"name":"姓名","features":["aaaa","bbbb","ccc"]}
             *
             * 对比上面两个json，发现类型信息也作为属性输出到json中
             */
            System.out.println(new String(bytes, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Object obj = JacksonUtil.deserialize(bytes);

        System.out.println(obj);
    }
}
