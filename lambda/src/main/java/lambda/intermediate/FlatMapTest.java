package lambda.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream可以容纳不同的数据类型，例如
 * Stream<String[]>
 * Stream<Set<String>>
 * Stream<List<String>>
 * Stream<List<Object>>
 * <p>
 * 但是，Stream操作（filter，sum，distinct ...）和collectors不支持它，所以我们需要使用flatMap（）进行以下转换
 * Stream<String[]>		-> flatMap ->	Stream<String>
 * Stream<Set<String>>	-> flatMap ->	Stream<String>
 * Stream<List<String>>	-> flatMap ->	Stream<String>
 * Stream<List<Object>>	-> flatMap ->	Stream<Object>
 * <p>
 * flatMap()如何工作：
 * {{1,2}，{3,4}，{5,6}}  - > flatMap  - > {1,2,3,4,5,6}
 * {'a'，'b'}，{'c'，'d'}，{'e'，'f'}}  - > flatMap  - > {'a'，'b'，'c' D”， 'E'， 'F'}
 * <p>
 * 通俗的讲flatMap()的作用就相当于把原stream中的所有元素都"摊平"之后组成的Stream，转换前后元素的个数和类型都可能会改变。
 */
public class FlatMapTest {

    public static void main(String[] args) {
        System.out.println("flatMap:");
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(i -> System.out.println(i));


        System.out.println("----------------------------------------------");
        System.out.println("flatMapToInt:");
        int[] intArray = {1, 2, 3, 4, 5, 6};
        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);
        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));
        intStream.forEach(x -> System.out.println(x));
    }
}
