package lambda.intermediate;

import lambda.Base;
import lambda.Student;

public class FilterTest extends Base {

    public static void main(String[] args) {
        students.stream().filter(student -> "华中科技大学".equals(student.getSchool()))
                .forEach(student -> System.out.println(student));


        System.out.println("--------------------------------------");

        students.stream().filter(Student.isAgeGreater(22))
                .forEach(student -> System.out.println(student));
    }
}
