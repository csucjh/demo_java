package lambda.terminal;

import lambda.Base;
import lambda.Student;

import java.util.Optional;

/**
 * findFirst用于返回满足条件的第一个元素
 * <p>
 * findAny不一定返回第一个，而是返回任意一个
 * <p>
 * 实际上对于顺序流式处理而言，findFirst和findAny返回的结果是一样的，至于为什么会这样设计，是因为在下一篇我们介绍的并行流式处理，当我们启用并行流式处理的时候，查找第一个元素往往会有很多限制，如果不是特别需求，在并行流式处理中使用findAny的性能要比findFirst好。
 */
public class FindTest extends Base {

    public static void main(String[] args) {
        System.out.println("findFirst:");
        Optional<Student> first = students.stream().findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        }

        System.out.println("--------------------------------");

        System.out.println("findAny:");
        Student any = students.stream().findAny().orElse(null);
        System.out.println(any);
    }

}
