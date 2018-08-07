package test.cjh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.cjh.service.TestService;

/*Spring容器扫描所有包，SpringMVC容器扫描controller包
 *发现由于controller包被扫描了两次，所以两个容器中都存在controller包中的bean单例对象(两个容器各有单例)
 *SpringMVC是Spring容器的子容器，子可以引用父的bean，所以TestContoller可以注解TestService
 *如果Spring只扫描service包，会发现TestService类不能注解TestContoller了，所以父是不能引用子的bean的
 */
@RestController
@RequestMapping("/test2")
public class TestContoller2 {

	private @Autowired TestService testService;

	@RequestMapping("/showInfo")
	public Map<String, Object> showInfo() {
		TestContoller2 tc = this.testService.getControllerInstance2();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("TestContoller2:", this.hashCode());
		map.put("TestService--->TestContoller2", tc == null ? "" : tc.hashCode());
		return map;
	}
}