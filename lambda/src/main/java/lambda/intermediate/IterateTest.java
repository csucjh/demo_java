package lambda.intermediate;

import lambda.Base;

import java.util.stream.Stream;

/**
 * iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推。
 */
public class IterateTest extends Base {

    /**
     * 生成一个等差数列
     *
     * @param args
     */
    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));
    }
}
