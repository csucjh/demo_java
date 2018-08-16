package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import model.User;
import org.springframework.web.servlet.FlashMap;

import java.util.Arrays;
import java.util.Map;

/**
 * ParserConfig.checkAutoType方法会检测序列化的类型信息(@type属性)
 * 反序列化必须开启自动类型支持，不然会抛出异常（autoType is not support）
 */
public class TestTypeInfo {
    // 白名单使用示例
    {
        //开启自动类型支持
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 添加全局白名单
        ParserConfig.getGlobalInstance().addAccept("org.springframework.");
        // 自定义类型信息key，默认@type
        JSON.setDefaultTypeKey("@class");
    }

    public static void main(String[] args) {
//        testUser();
//        testMap();
        testMapNewConfig();
    }

    /**
     * 使用非全局的ParserConfig
     */
    public static void testMapNewConfig() {
        // 使用非全局的ParserConfig
        ParserConfig defaultRedisConfig = new ParserConfig();
        defaultRedisConfig.setAutoTypeSupport(true);
        defaultRedisConfig.addAccept("org.springframework.");
        // 自定义类型信息key，默认@type
        JSON.setDefaultTypeKey("@class");

        Map<String, Object> user = new FlashMap();
        user.put("name", "姓名");
        user.put("age", "12");
        user.put("features", Arrays.asList("aaaa", "bbbb", "ccc"));

        byte[] bytes = JSON.toJSONBytes(user, SerializerFeature.WriteClassName);
        System.out.println(bytes);

        Object obj = JSON.parseObject(new String(bytes, IOUtils.UTF8), Object.class, defaultRedisConfig);
        System.out.println(obj);
    }

    /**
     * 发现autoTypeSupport=false时，先看黑名单，再看白名单，autoTypeSupport=true的时候是先看白名单再看黑名单
     * <p>
     * 因此将不在白名单的类要支持autoType；必须先开启全局自动类型支持，然后添加到白名单
     */
    public static void testMap() {
        // 添加全局白名单
        ParserConfig.getGlobalInstance().addAccept("org.springframework.");
        // 自定义类型信息key，默认@type
        JSON.setDefaultTypeKey("@class");

        Map<String, Object> user = new FlashMap();
        user.put("name", "姓名");
        user.put("age", "12");
        user.put("features", Arrays.asList("aaaa", "bbbb", "ccc"));

        String json = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(json);

        // 1、开启全局自动类型支持
        // 这种方式会先看白名单(Accept)再看黑名单(Deny)；增加到白名单即可
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        FlashMap user1 = (FlashMap) JSON.parse(json);
        System.out.println(user1);


        // 2、序列化是通过Feature指定支持自动类型
        // 这种方式会先看黑名单(Deny)再看白名单(Accept)；因此不开启全局自动类型支持，即使增加了白名单也不能反序列化
        FlashMap user2 = (FlashMap) JSON.parse(json, Feature.SupportAutoType);
        System.out.println(user2);
    }


    /**
     * 发现自定义的User类既不在黑名单也不再白名单，会直接在类路径下查找
     */
    public static void testUser() {
        // 自定义类型信息key，默认@type
        JSON.setDefaultTypeKey("@class");

        User user = new User();
        user.setAge(12);
        user.setName("姓名");
        user.setFeatures(Arrays.asList("aaaa", "bbbb", "ccc"));

        String json = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(json);

        // 1、开启全局自动类型支持
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        User user1 = (User) JSON.parse(json);
//        System.out.println(user1);


        // 2、序列化是通过Feature指定支持自动类型
        User user2 = (User) JSON.parse(json, Feature.SupportAutoType);
        System.out.println(user2);
    }
}
