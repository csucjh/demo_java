package lambda.intermediate;

import lambda.Base;

/**
 * limit操作也类似于SQL语句中的LIMIT关键字，不过相对功能较弱，limit返回包含前n个元素的流，当集合大小小于n时，则返回实际长度
 */
public class LimitTest extends Base {

    public static void main(String[] args) {
        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .limit(2) // n为返回的数据最大长度
                .forEach(student -> System.out.println(student));
    }
}
