package lambda.terminal;

import lambda.Base;

/**
 * 匹配判定
 */
public class MatchTest extends Base {


    public static void main(String[] args) {
        /**
         * 所有元素都要满足为true
         */
        System.out.println("allMatch:");
        boolean match = students.stream().allMatch(student -> student.getAge() > 19);
        System.out.println(match);

        /**
         * 任意元素满足为true
         */
        System.out.println("------------------------------");
        System.out.println("anyMatch:");
        match = students.stream().anyMatch(student -> student.getAge() > 22);
        System.out.println(match);

        /**
         * 无元素满足为true
         */
        System.out.println("------------------------------");
        System.out.println("noneMatch:");
        match = students.stream().noneMatch(student -> student.getAge() > 25);
        System.out.println(match);
    }
}
