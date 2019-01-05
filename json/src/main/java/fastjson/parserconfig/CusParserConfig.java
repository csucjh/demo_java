package fastjson.parserconfig;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import model.User;

import java.lang.reflect.Type;

public class CusParserConfig extends ParserConfig {

    /**
     * 将指定的类型映射到自定义的反序列化器
     *
     * @param clazz
     * @param type
     * @return
     */
    @Override
    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        if (type == User.class) {
            return new UserDeserializer(this, clazz, type);
        }

        return super.getDeserializer(clazz, type);
    }
}
