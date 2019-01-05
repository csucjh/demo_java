package fastjson.parserconfig;

import com.alibaba.fastjson.JSON;
import model.ManUser;
import model.Person;
import model.WomanUser;

public class TestParseConfig {

    public static void main(String[] args) {
        testCusParserConfig();
//        testGlobalParserConfig();
    }

    /**
     * 自定义ParserConfig
     */
    public static void testCusParserConfig() {
        ManUser man = ManUser.builder().age(12).name("小二").sex(1).ball("羽毛球").feature("内向型").build();
        WomanUser woman = WomanUser.builder().age(10).name("小四").sex(0).buy("羽毛球").feature("外向型").build();

        Person original = Person.builder().user(woman).build();
        String json = JSON.toJSONString(original);
        Person person = JSON.parseObject(json, Person.class, new CusParserConfig());
        System.out.println(person.toString());
    }

    /**
     * 全局ParserConfig
     */
    public static void testGlobalParserConfig() {
//        ManUser man = ManUser.builder().age(12).name("小二").sex(1).ball("羽毛球").feature("内向型").build();
//        WomanUser woman = WomanUser.builder().age(10).name("小四").sex(0).buy("羽毛球").feature("外向型").build();
//
//        Person original = Person.builder().user(woman).build();
//        String json = JSON.toJSONString(original);
//
//        ParserConfig.getGlobalInstance().putDeserializer(User.class, new JavaBeanDeserializer() {
//            @Override
//            public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
//                String json = parser.getInput();
//
//                JSONObject jsonObject = JSON.parseObject(json).getJSONObject("user");
//
//                //  根据性别转化为不同子类
//                int sex = jsonObject.getIntValue("sex");
//
//                Object object = parser.getContext().object;
//                if (1 == sex) {
//                    type = ManUser.class;
//                } else if (0 == sex) {
//                    type = WomanUser.class;
//                }
//
//                return jsonObject.toJavaObject(type);
//            }
//        });
//
//        Person person = JSON.parseObject(json, Person.class);
//        System.out.println(person);
    }
}
