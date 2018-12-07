package lambda.terminal;

import lambda.Base;
import lambda.Student;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 收集器均实现自接口java.util.stream.Collector
 * 我们也可以实现该接口来定义自己的收集器
 */
public class CollectTest extends Base {

    public static void main(String[] args) {
//        count();
//        sum();
//        average();
//        join();
//        groupingBy();
//        partition();
    }


    /**
     * stream().collect(Collectors.counting())简化为stream().count()
     */
    public static void count() {
        long count1 = students.stream().collect(Collectors.counting());
        System.out.println("count1:" + count1);
        System.out.println("------------------------------");
        long count2 = students.stream().count();
        System.out.println("count2:" + count2);
    }

    /**
     * summingInt 只求总和
     * summarizingInt 一次性得到元素个数、总和、均值、最大值、最小值
     */
    public static void sum() {
        int ageSum = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println("ageSum:" + ageSum);
        System.out.println("------------------------------");

        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("statistics:" + statistics);
    }

    /**
     * average平均值返回的是double
     */
    public static void average() {
        double average = students.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println("average:" + average);
        System.out.println("------------------------------");
    }


    /**
     * join 字符串拼接
     */
    public static void join() {
        String names = students.stream().map(Student::getName).collect(Collectors.joining(", "));
        System.out.println(names);
        System.out.println("------------------------------");
    }


    /**
     * groupingBy分组
     * <p>
     * 在groupingBy的第二个参数不是只能传递groupingBy，还可以传递任意Collector类型
     * 如果我们不添加第二个参数，则编译器会默认帮我们添加一个Collectors.toList()，看方法重载就知道
     */
    public static void groupingBy() {

        /**
         * 一级分组，按学校
         */
        Map<String, List<Student>> groups = students.stream().collect(Collectors.groupingBy(Student::getSchool));
        System.out.println("groups:" + groups);


        /**
         * 二级分组，按学校--专业
         */
        Map<String, Map<String, List<Student>>> groups2 = students.stream()
                .collect(Collectors.groupingBy(Student::getSchool, Collectors.groupingBy(Student::getMajor)));
        System.out.println("groups2:" + groups2);


        /**
         * 在groupingBy的第二个参数不是只能传递groupingBy，还可以传递任意Collector类型，比如我们可以传递一个Collector.counting，用以统计每个组的个数
         * 如果我们不添加第二个参数，则编译器会默认帮我们添加一个Collectors.toList()
         */
        Map<String, Long> groups3 = students.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.counting()));
        System.out.println("groups3:" + groups3);

    }

    /**
     * partition 分组
     * <p>
     * 分区可以看做是分组的一种特殊情况，在分区中key只有两种情况：true或false
     * 目的是将待分区集合按照条件一分为二，java8的流式处理利用Collectors.partitioningBy()方法实现分区，该方法接收一个谓词，
     * <p>
     * 分区相对分组的优势在于，我们可以同时得到两类结果，在一些应用场景下可以一步得到我们需要的所有结果，比如将数组分为奇数和偶数。
     */
    public static void partition() {

        /**
         * 例如我们希望将学生分为武大学生和非武大学生，那么可以实现如下：
         */
        Map<Boolean, List<Student>> partition = students.stream().collect(Collectors.partitioningBy(student -> "武汉大学".equals(student.getSchool())));
        System.out.println(partition);
        System.out.println("------------------------------");
    }

}
