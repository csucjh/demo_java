package test.cjh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 实例变量注解
@RestController
@RequestMapping("/value")
public class ValueContoller {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driver}")
	private String jdbcDriver;
	
	private @Value("${pool.initialSize}") String poolInitialSize;
	
	@RequestMapping("/map")
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("jdbc.url", this.jdbcUrl);
		map.put("jdbc.driver", this.jdbcDriver);
		map.put("pool.initialSize", this.poolInitialSize);
		return map;
	}
}
