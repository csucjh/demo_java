package ex15.startup;

//explain Digester and StandardContext
// use ContextConfig so we don't need to instantiate wrapper

import com.brainysoftware.pyrmont.util.Constant;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.*;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.HostConfig;


public final class Bootstrap {

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

        LifecycleListener listener = new ContextConfig();
        ((Lifecycle) context).addLifecycleListener(listener);

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        // 关注admin.xml部署描述符
        ((Lifecycle) host).addLifecycleListener(new HostConfig());

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        // context.addServletMapping(pattern, name);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");

        Engine engine = new StandardEngine();
        engine.setName("catalina");
        engine.addChild(host);
        engine.setDefaultHost("localhost");

        Service service = new StandardService();
        service.setName("Stand-alone Service");
        service.addConnector(connector);
        //StandardService class's setContainer will call all its connector's setContainer method
        service.setContainer(engine);


        Server server = new StandardServer();
        server.addService(service);


        // Start the new server
        if (server instanceof Lifecycle) {
            try {
                server.initialize();
                ((Lifecycle) server).start();
                server.await();
                // the program waits until the await method returns,
                // i.e. until a shutdown command is received.
            } catch (LifecycleException e) {
                e.printStackTrace(System.out);
            }
        }

        // Shut down the server
        if (server instanceof Lifecycle) {
            try {
                ((Lifecycle) server).stop();
            } catch (LifecycleException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}