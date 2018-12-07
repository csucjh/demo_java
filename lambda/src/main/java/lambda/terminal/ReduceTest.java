package lambda.terminal;

import lambda.Base;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * reduce三个重载
 * 1:有初始值 T reduce(T identity, BinaryOperator<T> accumulator);
 * 初始值identity作为BinaryOperator第一次运算的操作数之一
 * <p>
 * 2:无初始值 Optional<T> reduce(BinaryOperator<T> accumulator);
 * 无初始值，第一次运算的两个操作数是Stream中的头两个
 * <p>
 * 3:三参数 <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
 * 非并行情况下的第三个参数BinaryOperator无效，reduce执行完第二个参数后直接停止运行，根本没有访问到第三个参数去执行
 * 并行(parallel)的影响下，第三个参数才会生效
 * 并行状态下理解为第二个函数根据流内数据的个数分为多线程去处理每个值与首参U的2合计算
 * 首参U分别与流内每个值计算完毕后，由第三个参数对这些值做出整合(该参数要求实现BinaryOperator接口并给出一个单值计算的行为),即，该接口内三个参数为同一类型，并作出操作(T x,T y)->{return ?(T)}
 */
public class ReduceTest extends Base {

    /**
     * 求出年龄之和
     */
    public static void main(String[] args) {

        // 使用IntStream的特有sum方法
        int one = IntStream.of(1, 2, 3).sum();
        System.out.println("one:" + one);

        // reduce 归约操作
        int two = Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b);
        System.out.println("two:" + two);

        // reduce 进一步简化
        int three = Stream.of(1, 2, 3).reduce(4, Integer::sum);
        System.out.println("three:" + three);

        // reduce 采用无初始值的重载版本，需要注意返回Optional
        Optional four = Stream.of(1, 2, 3).reduce(Integer::sum);  // 去掉初始值
        System.out.println("four:" + four.orElse(0));

        // reduce
        Integer five = Stream.of(1, 2, 3)
                .parallel()
                .reduce(10, (a, b) -> a + b, (a, b) -> a * b);
        //实际输出： 1716 = (10+1)*(10+2)*(10+3)
        //去掉parallel()实际输出： 16 = (10+1+2+3)
        System.out.println("five:" + five);
    }
}
