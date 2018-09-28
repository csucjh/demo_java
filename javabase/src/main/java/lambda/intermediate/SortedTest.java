package lambda.intermediate;

import lambda.Base;
import lambda.Student;

import java.util.Comparator;

/**
 * 该操作用于对流中元素进行排序，sorted要求待比较的元素必须实现Comparable接口，如果没有实现也不要紧，我们可以将比较器作为参数传递给sorted(Comparator<? super T> comparator)
 */
public class SortedTest extends Base {

    /**
     * 筛选出专业为土木工程的学生，并按年龄从小到大排序，筛选出年龄最小的两个学生
     * <p>
     * Comparator.comparingInt看源码其实就是Integer.compare，
     *
     * @param args
     */
    public static void main(String[] args) {
        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                // 这两句等价，都是年龄升序排序
                .sorted(Comparator.comparingInt(Student::getAge))
//                .sorted((s1, s2) -> s1.getAge() - s2.getAge())
                .limit(2)
                .forEach(student -> System.out.println(student));
    }
}
