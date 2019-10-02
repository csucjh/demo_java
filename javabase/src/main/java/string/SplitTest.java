package string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SplitTest {

    public static void main(String[] args) {
//        String fundoutFailExcludes = "123,234";
//        List<String> list = Arrays.asList(StringUtils.split(fundoutFailExcludes, "\\,"));
//        System.out.println(list);



//        String str = "a,b,c,,";
//        String str = ",,a,b,,c,,";
        String str = ",,,,";
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);
    }
}
