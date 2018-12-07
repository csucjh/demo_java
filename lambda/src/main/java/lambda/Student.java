package lambda;

import java.util.function.Predicate;

public class Student {

    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

    public Student(long id, String name, int age, int grade, String major, String school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.school = school;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

    /**
     * p元素对应Predicate断言的泛型类型Student
     *
     * @param age
     * @return
     */
    public static Predicate<Student> isAgeGreater(int age) {
        return p -> p.getAge() > age;
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return this.age == student.age && this.grade == student.grade;
    }

    @Override
    public int hashCode() {
        return this.age << 2 & this.grade << 2;
    }
}
