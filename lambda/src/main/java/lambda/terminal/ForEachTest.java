package lambda.terminal;

import lambda.Base;

/**
 * forEach与forEachOrdered主要的区别在并行流的处理上
 * <p>
 * forEach输出的顺序不一定（效率更高）
 * <p>
 * forEachOrdered输出的顺序与元素的顺序严格一致
 * <p>
 * 在并行的程序中，如果对处理之后的数据，没有顺序的要求，使用forEach的效率，肯定是要更好的
 */
public class ForEachTest extends Base {

    public static void main(String[] args) {
        System.out.println("forEach:");
        students.stream().parallel().forEach(System.out::println);

        System.out.println("-------------------------------------");

        System.out.println("forEachOrdered:");
        students.stream().parallel().forEachOrdered(System.out::println);
    }
}
