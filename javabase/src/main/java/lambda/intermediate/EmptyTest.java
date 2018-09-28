package lambda.intermediate;

import lambda.Base;

import java.util.stream.Stream;

/**
 * Returns an empty sequential
 * <p>
 * 返回一个空的序列
 */
public class EmptyTest extends Base {

    public static void main(String[] args) {

        Stream stream = Stream.empty();
        long count = stream.count();
        System.out.println(count);
    }
}
