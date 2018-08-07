package test.cjh.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestCaseOne extends TestBaseCase {

	@Test
	public void testShowInfo() throws Exception {
		// 请求接口
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test/showInfo").param("src", "test");

		String resultJson = this.getString(requestBuilder);

		System.out.println(resultJson);

		Assert.assertNotEquals("", resultJson);
	}
}
