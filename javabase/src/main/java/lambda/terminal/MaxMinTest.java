package lambda.terminal;

import lambda.Base;
import lambda.Student;

import java.util.Comparator;

/**
 * 最值处理
 */
public class MaxMinTest extends Base {

    public static void main(String[] args) {

        /**
         * 找出年龄最大的
         */
        System.out.println("max age:");
        Student max = students.stream().max(Comparator.comparingInt(Student::getAge)).orElse(null);
        System.out.println(max);

        System.out.println("--------------------------");

        /**
         * 颠倒比较顺序，max会变成和min效果一样
         */
        System.out.println("max age reverse:");
        Student maxReverse = students.stream().max((s1, s2) -> Integer.compare(s2.getAge(), s1.getAge())).orElse(null);
        System.out.println(maxReverse);

        System.out.println("--------------------------");

        /**
         * 找出年龄最小的
         */
        System.out.println("min age:");
        Student min = students.stream().min(Comparator.comparingInt(Student::getAge)).orElse(null);
        System.out.println(min);

        System.out.println("--------------------------");

        /**
         * 颠倒比较顺序，min会变成和max效果一样
         */
        System.out.println("min age reverse:");
        Student minReverse = students.stream().min((s1, s2) -> Integer.compare(s2.getAge(), s1.getAge())).orElse(null);
        System.out.println(minReverse);
    }
}
