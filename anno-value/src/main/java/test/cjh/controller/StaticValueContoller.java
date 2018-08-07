package test.cjh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 静态变量注解
@RestController
@RequestMapping("/staticvalue")
public class StaticValueContoller {

	private static String jdbcUrl;

	private static String jdbcDriver;

	@RequestMapping("/map")
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("static-jdbc.url", jdbcUrl);
		map.put("static-jdbc.driver", jdbcDriver);

		return map;
	}

	/**
	 * 不能加static,否则不能注解
	 */
	@Value("${jdbc.driver}")
	public void setJdbcDriver(String jdbcDriver) {
		StaticValueContoller.jdbcDriver = jdbcDriver;
	}

	@Value("${jdbc.url}")
	public void setJdbcUrl(String jdbcUrl) {
		StaticValueContoller.jdbcUrl = jdbcUrl;
	}
}
