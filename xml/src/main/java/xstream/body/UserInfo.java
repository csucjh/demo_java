package xstream.body;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import xstream.Body;

public class UserInfo implements Body {

    private String name;

    private String age;

    @XStreamAlias("alias2")
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
