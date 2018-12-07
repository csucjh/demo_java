package lambda.terminal;

import java.util.stream.Stream;

public class BuilderTest {
    public static void main(String[] args) {
        Stream<Object> steam = Stream.builder().add("one").add("two").build();
        steam.forEach(System.out::println);
    }
}
