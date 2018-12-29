package functional;

/**
 * @FunctionalInterface 修饰的接口只能有一个接口方法，其他的只能是default方法
 * 如果一个接口有两个或以上接口方法，不能用lambda来去掉方法体执行(无法判断是哪个)
 * 如果一个接口只有一个接口方法，无论接口是否标记@FunctionalInterface注解都不影响使用lambda代替这唯一的一个方法的方法体
 * @FunctionalInterface 作用是保证被修饰的接口满足函数式编程的规范要求，如果不满足编译会报错
 */
public interface Vehicle {

    void driver(String name);

    default void oil(int count) {
        System.out.println("加油" + count + "升");
    }

    default void cleaning(int count) {
        System.out.println("清洗" + count + "次");
    }
}
