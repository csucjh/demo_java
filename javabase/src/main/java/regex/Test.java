package regex;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String reg = "[\\uE863\\u4E00-\\u9FA5a-zA-Z·]{2,85}";
        String text = "木拉提•帕尔克";
        Matcher matcher = Pattern.compile(reg).matcher(text);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        System.out.println(text);
        text = StringUtils.join(list, "·");
        System.out.println(text);
        System.out.println(Pattern.matches(reg, text));

    }
}
