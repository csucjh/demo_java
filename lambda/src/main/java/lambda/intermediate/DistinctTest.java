package lambda.intermediate;

import lambda.Base;

/**
 * distinct基于Object.equals(Object)实现(重写equals方法尽量重写hashCode方法，不然HashMap这种数据结构会与预期效果不一致)
 */
public class DistinctTest extends Base {

    public static void main(String[] args) {
        students.stream().distinct()
                .forEach(student -> System.out.println(student));
    }
}
