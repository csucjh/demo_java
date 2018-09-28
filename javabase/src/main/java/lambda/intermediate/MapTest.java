package lambda.intermediate;

import lambda.Base;
import lambda.Student;

/**
 * 于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素
 * <p>
 * 对每个元素按照某种操作进行转换，转换前后Stream中元素的个数不会改变，但元素的类型取决于转换之后的类型。
 * <p>
 * mapToInt，mapToLong和mapToDouble之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗
 */
public class MapTest extends Base {

    public static void main(String[] args) {
        System.out.println("map:");
        students.stream().map(Student::getName).forEach(name -> System.out.println(name));

        System.out.println("------------------------------");

        System.out.println("mapToInt:");
        students.stream().mapToInt(Student::getAge).forEach(age -> System.out.println(age));
    }
}
