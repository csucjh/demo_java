package ex10.startup;

import com.brainysoftware.pyrmont.util.Constant;
import ex10.core.SimpleContextConfig;
import ex10.core.SimpleWrapper;
import ex10.realm.SimpleRealm;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.deploy.SecurityCollection;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.catalina.loader.WebappLoader;

public final class Bootstrap1 {
    /**
     * http://localhost:8080/Modern?test=123
     * http://localhost:8080/Primitive
     */
    public static void main(String[] args) {
        System.setProperty("catalina.base", Constant.CATALINA_BASE);

        Connector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new StandardContext();
        // StandardContext's start method adds a default mapper
        context.setPath("/myApp");
        context.setDocBase("webapps/context");
        LifecycleListener listener = new SimpleContextConfig();
        ((Lifecycle) context).addLifecycleListener(listener);

        context.addChild(wrapper1);
        context.addChild(wrapper2);
        // for simplicity, we don't add a valve, but you can add
        // valves to context or wrapper just as you did in Chapter 6

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        // context.addServletMapping(pattern, name);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");
        // add ContextConfig. This listener is important because it configures
        // StandardContext (sets configured to true), otherwise StandardContext
        // won't start


        /*  <security-constraint>
                <web-resource-collection>
                    <web-resource-name>JMX Proxy interface</web-resource-name>
                    <url-pattern>/</url-pattern>
                </web-resource-collection>
                <auth-constraint>
                    <role-name>manager-jmx</role-name>
                </auth-constraint>
            </security-constraint>
          */

        /**
         * add constraint
         */
        SecurityConstraint constraint = new SecurityConstraint();
        //web-resource-collection
        SecurityCollection securityCollection = new SecurityCollection();
        securityCollection.addPattern("/");
        securityCollection.addMethod("GET");
        constraint.addCollection(securityCollection);
        // role-name
        constraint.addAuthRole("manager");
        context.addConstraint(constraint);


        /**
         * set login config
         */
        LoginConfig loginConfig = new LoginConfig();
        loginConfig.setRealmName("Simple Realm");
        loginConfig.setAuthMethod("BASIC");
        context.setLoginConfig(loginConfig);

        /**
         * set realm
         */
        Realm realm = new SimpleRealm();
        context.setRealm(realm);

        connector.setContainer(context);

        try {
            connector.initialize();
            ((Lifecycle) connector).start();

            ((Lifecycle) context).start();

            // make the application wait until we press a key.
            System.in.read();
            ((Lifecycle) context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}