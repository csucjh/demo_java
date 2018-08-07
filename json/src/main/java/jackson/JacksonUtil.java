package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

/**
 * DefaultTyping 有四个选项
 * JAVA_LANG_OBJECT:  当对象属性类型为Object时生效
 * OBJECT_AND_NON_CONCRETE:  当对象属性类型为Object或者非具体类型（抽象类和接口）时生效
 * NON_CONCRETE_AND+_ARRAYS:  同上, 另外所有的数组元素的类型都是非具体类型或者对象类型
 * NON_FINAL:  对所有非final类型或者非final类型元素的数组
 * 开启DefaultTyping，并且让所有的非final类型对象持久化时都存储类型信息显然能准确的反序列多态类型的数据。
 */
public class JacksonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        /**
         * JsonTypeInfo.As.PROPERTY  将json的类型信息也作为json属性序列化到json中，便于反序列化
         */
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public static byte[] serialize(Object source) throws SerializationException {

        if (source == null) {
            return new byte[0];
        }

        try {
            return mapper.writeValueAsBytes(source);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
        }
    }

    public static Object deserialize(byte[] source) throws SerializationException {
        return deserialize(source, Object.class);
    }

    public static <T> T deserialize(byte[] source, Class<T> type) throws SerializationException {
        if ((source == null || source.length == 0)) {
            return null;
        }

        try {
            return mapper.readValue(source, type);
        } catch (Exception ex) {
            throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
        }
    }
}
