package test;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringTest {

    public static void main(String[] args) {

        String string = "123:234|222:245";

        List<Map<String, String>> list = Arrays.asList(string, string).stream()
                .map(x -> StringUtils.split(x, "|"))
                .flatMap(x -> Arrays.stream(x))
                .map(x -> {
                    String[] arr = StringUtils.split(x, ":");
                    Map<String, String> map = new HashMap<>();
                    map.put(arr[0], arr[1]);
                    return map;
                })
                .collect(Collectors.toList());

        System.out.println(list);

        String acc = list.stream().filter(x -> x.containsKey("123")).findFirst().orElse(null).get("123");
        System.out.println(acc);

    }
}
