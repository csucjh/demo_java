package lambda.intermediate;

import lambda.Base;
import lambda.Student;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 连接两个stream的操作，惰性执行
 * <p>
 * 保持了两个stream中各自元素的顺序
 */
public class ConcatTest extends Base {

    public static void main(String[] args) {
        Stream.concat(students.stream(), students.stream()).forEach(student -> System.out.println(student));

        System.out.println("---------------------------------------------");

        // 第一个排序，第二个不排序，合并后并没有什么区别
        Stream.concat(
                students.stream().sorted(Comparator.comparingInt(Student::getAge)),
                students.stream())
                .forEach(student -> System.out.println(student));

    }
}