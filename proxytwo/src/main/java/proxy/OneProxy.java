package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OneProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("1111");
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        return "";
    }
}
