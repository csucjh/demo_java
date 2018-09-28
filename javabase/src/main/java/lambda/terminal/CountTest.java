package lambda.terminal;

import lambda.Base;
import lambda.Student;

/**
 * the count of elements in this stream
 * <p>
 * 即返回当前调用count时stream中的元素数量
 */
public class CountTest extends Base {

    public static void main(String[] args) {
        // 这种直接count与list.size()无区别
        long count = students.stream().count();
        System.out.println(count);

        // 这时计算数量才有实用
        count = students.stream().filter(Student.isAgeGreater(22)).count();
        System.out.println(count);
    }
}
