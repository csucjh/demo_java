package fastjson.parserconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.util.JavaBeanInfo;
import model.ManUser;
import model.WomanUser;

import java.lang.reflect.Type;

/**
 * User类型的反序列化处理
 */
public class UserDeserializer extends JavaBeanDeserializer {


    public UserDeserializer(ParserConfig config, Class<?> clazz) {
        super(config, clazz);
    }

    public UserDeserializer(ParserConfig config, Class<?> clazz, Type type) {
        super(config, clazz, type);
    }

    public UserDeserializer(ParserConfig config, JavaBeanInfo beanInfo) {
        super(config, beanInfo);
    }

    @Override
    public Object createInstance(DefaultJSONParser parser, Type type) {

        // 原始串
        String json = parser.getInput();

        JSONObject jsonObject = JSON.parseObject(json).getJSONObject("user");

        //  根据性别转化为不同子类
        int sex = jsonObject.getIntValue("sex");

        Object object = parser.getContext().object;
        if (1 == sex) {
            type = ManUser.class;
        } else if (0 == sex) {
            type = WomanUser.class;
        }

        return jsonObject.toJavaObject(type);
    }
}