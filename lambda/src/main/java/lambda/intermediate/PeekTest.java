package lambda.intermediate;

import lambda.Base;

/**
 * forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次 terminal 运算
 * <p>
 * 相反，具有相似功能的 intermediate 操作 peek 可以达到上述目的
 * <p>
 * peek由于是Consumer类型参数，是不能有return的
 */
public class PeekTest extends Base {

    public static void main(String[] args) {
        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .peek(student -> student.setSchool("peek-school"))
                .forEach(student -> System.out.println(student));
    }
}
