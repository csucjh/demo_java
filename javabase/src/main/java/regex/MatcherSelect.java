package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 截取指定值
 */
public class MatcherSelect {

    public static void main(String[] args) {

        String str = "aaaa4585-params={key:val}";

        Pattern pattern = Pattern.compile("params=(.*)");

        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String s = matcher.group(1);
            System.out.println(s);
        }
    }
}
