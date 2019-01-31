package test.csu.visitor.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 访问者接收的数据源可能是任意Object
 *
 * @param <R> 处理后的返回值类型
 */
public class LambdaVisitor<R> implements Function<Object, R> {

    private Map<Class<?>, Function<Object, R>> functionMap = new HashMap<>();

    @Override
    public R apply(Object obj) {
        return functionMap.get(obj.getClass()).apply(obj);
    }

    private LambdaVisitor() {
    }

    public static <R> LambdaVisitor<R> instance() {
        return new LambdaVisitor<>();
    }

    public <P> Acceptor<P, R> visitable(Class<P> clazz) {
        return new Acceptor<>(this, clazz);
    }

    public static class Acceptor<P, R> {
        private final LambdaVisitor visitor;
        private final Class<P> clazz;

        public Acceptor(LambdaVisitor<R> visitor, Class<P> clazz) {
            this.visitor = visitor;
            this.clazz = clazz;
        }

        public LambdaVisitor<R> then(Function<P, R> function) {
            visitor.functionMap.put(clazz, function);
            return visitor;
        }

        public LambdaVisitor<R> visitor() {
            return visitor;
        }
    }
}
