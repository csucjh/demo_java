/**
 *
 */
package velocity.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import test.cjh.file.util.FileUtil;

import javax.servlet.ServletContext;
import java.util.LinkedList;
import java.util.List;

/**
 */

/**
 * @author TQSUMMER
 */
public class ServletContextInitListener implements ApplicationContextAware {


    /**
     * org.apache.catalina.core.ApplicationContextFacade类就是tomcat对ServletContext的实现
     * <p>
     * 可以反编译tomcat的catalina.jar包
     * <p>
     * ServletContext就是我们说的application域
     * <p>
     * 将这个程序的war放到同一个tomcat的webapps目录下，启动tomcat会发现这两个应用的ServletContext是不同的
     *
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebApplicationContext wc = (WebApplicationContext) applicationContext;
        ServletContext sc = (ServletContext) wc.getBean(WebApplicationContext.SERVLET_CONTEXT_BEAN_NAME);
        sc.setAttribute("testParam", "测试application域");
        sc.setAttribute("ServletContext", "ServletContext：" + sc);

//        List<String> stringList = new LinkedList<>();
//        stringList.add("ServletContext:" + sc);
//        FileUtil.writeTxtFile(stringList, "d:/data/logs/temp/info.txt");
    }
}
