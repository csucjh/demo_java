package ex13.startup;

import com.brainysoftware.pyrmont.util.Constant;
import ex13.core.SimpleContextConfig;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;


public final class Bootstrap2 {

    /**
     * http://localhost:8080/myApp/Modern?test=123
     * http://localhost:8080/myApp/Primitive
     */
    public static void main(String[] args) {
        System.setProperty("catalina.base", Constant.CATALINA_BASE);

        Connector connector = new HttpConnector();

        Wrapper wrapper1 = new StandardWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new StandardWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new StandardContext();
        // StandardContext's start method adds a default mapper
        context.setPath("/myApp");
        context.setDocBase("context");

        context.addChild(wrapper1);
        context.addChild(wrapper2);

        LifecycleListener listener = new SimpleContextConfig();
        ((Lifecycle) context).addLifecycleListener(listener);

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        // context.addServletMapping(pattern, name);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");

        Engine engine = new StandardEngine();
        engine.setName("catalina");
        engine.addChild(host);
        engine.setDefaultHost("localhost");

        connector.setContainer(engine);
        try {
            connector.initialize();
            ((Lifecycle) connector).start();
            ((Lifecycle) engine).start();

            // make the application wait until we press a key.
            System.in.read();
            ((Lifecycle) engine).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}