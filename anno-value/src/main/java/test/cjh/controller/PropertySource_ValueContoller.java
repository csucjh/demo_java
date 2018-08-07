package test.cjh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @PropertySource注解引入配置文件
// 不能使用通配符，多个逗号隔开
@RestController
@RequestMapping("/propertySource")
@PropertySource("classpath:META-INF/app1.properties")
public class PropertySource_ValueContoller {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driver}")
	private String jdbcDriver;

	@RequestMapping("/map")
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("jdbc.url", this.jdbcUrl);
		map.put("jdbc.driver", this.jdbcDriver);
		return map;
	}
}
