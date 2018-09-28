package lambda.intermediate;

import lambda.Base;

/**
 * skip操作与limit操作相反，如同其字面意思一样，是跳过前n个元素(注意n是个数，不是索引)
 */
public class SkipTest extends Base {

    /**
     * 比如我们希望找出排序在2之后的土木工程专业的学生，那么可以实现为
     * <p>
     * 当n大于元数据最大长度时，返回空
     *
     * @param args
     */
    public static void main(String[] args) {
        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(20)
                .forEach(student -> System.out.println(student));
    }
}
