package ex06.startup;


import ex06.core.*;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {

    /**
     * http://localhost:8080/Modern?test=123
     * http://localhost:8080/Primitive
     *
     * @param args
     */
    public static void main(String[] args) {
        Connector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);
        context.setName("Context");

        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        LifecycleListener listener = new SimpleContextLifecycleListener();
        ((Lifecycle) context).addLifecycleListener(listener);

        ((Lifecycle) wrapper1).addLifecycleListener(listener);
        ((Lifecycle) wrapper2).addLifecycleListener(listener);

        context.addMapper(mapper);
        Loader loader = new SimpleLoader();
        context.setLoader(loader);
        // context.addServletMapping(pattern, name);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");
        connector.setContainer(context);
        try {
            connector.initialize();
            ((Lifecycle) connector).start();
            ((Lifecycle) context).start();

            // HttpConnector，Tomcat 4里面具体怎么实现的，不知道，在这个应用感觉，好像只监听了一次Http请求，不过，我们不关心细节，只要了解原理就好了
            // 在第三章我们已经了解了HttpConnector原理，以下是逐渐了解各个组件的原理

            // make the application wait until we press a key.
            System.in.read();
            ((Lifecycle) context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}