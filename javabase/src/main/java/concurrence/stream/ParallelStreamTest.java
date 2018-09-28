package concurrence.stream;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * https://www.jianshu.com/p/bd825cb89e00
 * <p>
 * <p>
 * 正如我们上面那个列子的情况分析得知,lambda的执行并不是瞬间完成的,所有使用parallel streams的程序都有可能成为阻塞程序的源头,
 * 并且在执行过程中程序中的其他部分将无法访问这些workers,这意味着任何依赖parallel streams的程序在什么别的东西占用着common ForkJoinPool时将会变得不可预知并且暗藏危机.
 * <p>
 * <p>
 * stream or parallelStream？
 * <p>
 * 上面我们也看到了parallelStream所带来的隐患和好处,那么,在从stream和parallelStream方法中进行选择时,我们可以考虑以下几个问题：
 * 1. 是否需要并行？
 * 2. 任务之间是否是独立的？是否会引起任何竞态条件？
 * 3. 结果是否取决于任务的调用顺序？
 * <p>
 * 对于问题1，在回答这个问题之前，你需要弄清楚你要解决的问题是什么，数据量有多大，计算的特点是什么？并不是所有的问题都适合使用并发程序来求解，比如当数据量不大时，顺序执行往往比并行执行更快。毕竟，准备线程池和其它相关资源也是需要时间的。但是，当任务涉及到I/O操作并且任务之间不互相依赖时，那么并行化就是一个不错的选择。通常而言，将这类程序并行化之后，执行速度会提升好几个等级。
 * <p>
 * 对于问题2，如果任务之间是独立的，并且代码中不涉及到对同一个对象的某个状态或者某个变量的更新操作，那么就表明代码是可以被并行化的。
 * <p>
 * 对于问题3，由于在并行环境中任务的执行顺序是不确定的，因此对于依赖于顺序的任务而言，并行化也许不能给出正确的结果。
 */
public class ParallelStreamTest {


    /**
     * 底层是全局的ForkJoinPool
     * 每个子任务无法控制超时时间
     * <p>
     * 尽量少使用ParallelStream
     */
    public static void main(String[] args) {

        List<Callable<String>> tasks = new LinkedList<Callable<String>>() {
            {
                add(() -> {
                    Thread.sleep(5 * 1000);
                    return "test1";
                });
                add(() -> {
                    Thread.sleep(2 * 1000);
                    return "tes2";
                });
            }
        };


        List<String> result = tasks.parallelStream().map(x -> {
            try {
                return "Thread:" + Thread.currentThread().getId() + ":" + x.call();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        System.out.println(result);
    }
}
