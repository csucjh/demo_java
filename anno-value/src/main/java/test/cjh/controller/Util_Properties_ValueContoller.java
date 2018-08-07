package test.cjh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 使用<util:properties/>标签托管配置项
// <util:properties/>的id作为程序中的取值来源
@RestController
@RequestMapping("/utilvalue")
public class Util_Properties_ValueContoller {

	@Value("#{jdbcSetting['jdbc.url']}")
	private String jdbcUrl;

	@Value("#{jdbcSetting['jdbc.driver']}")
	private String jdbcDriver;

	private @Value("#{jdbcSetting['pool.initialSize']}") String poolInitialSize;

	@RequestMapping("/map")
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("util-jdbc.url", this.jdbcUrl);
		map.put("util-jdbc.driver", this.jdbcDriver);
		map.put("util-pool.initialSize", this.poolInitialSize);

		return map;
	}
}
