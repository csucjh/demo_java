import proxy.OneProxy;
import target.IPersonInfo;

import java.lang.reflect.Proxy;

public class TestProxy {

    public static void main(String[] args) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        OneProxy one = new OneProxy();
        IPersonInfo oneLevel = (IPersonInfo) Proxy.newProxyInstance(classLoader, new Class[]{IPersonInfo.class}, one);
        Object handler = Proxy.getInvocationHandler(oneLevel);
        oneLevel.getInfo();
        System.out.println(oneLevel);
    }
}
