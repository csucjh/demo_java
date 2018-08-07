package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import model.User;

import java.util.Arrays;

/**
 * ParserConfig.checkAutoType方法会检测序列化的类型信息(@type属性)
 * 反序列化必须开启自动类型支持，不然会抛出异常（autoType is not support）
 */
public class TestTypeInfo {
    public static void main(String[] args) {
        // 自定义类型信息key，默认@type
        JSON.setDefaultTypeKey("@class");

        User user = new User();
        user.setAge(12);
        user.setName("姓名");
        user.setFeatures(Arrays.asList("aaaa", "bbbb", "ccc"));

        String json = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(json);

        // 1、开启全局自动类型支持
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        User user1 = (User) JSON.parse(json);
        System.out.println(user1);


        // 2、序列化是通过Feature指定支持自动类型
        User user2 = (User) JSON.parse(json, Feature.SupportAutoType);
        System.out.println(user2);
    }
}
