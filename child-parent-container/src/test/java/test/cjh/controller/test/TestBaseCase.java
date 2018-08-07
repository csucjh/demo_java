package test.cjh.controller.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//SpringJUnit4ClassRunner必须使用junit4.9以上才有
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/spring-context.xml", "classpath:META-INF/springmvc-servlet.xml" })
public class TestBaseCase {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	public String getString(MockHttpServletRequestBuilder requestBuilder) throws Exception {
		MvcResult result = this.mockMvc.perform(requestBuilder.characterEncoding("utf-8")).andReturn();

		MockHttpServletResponse response = result.getResponse();
		response.setCharacterEncoding("utf-8");

		return response.getContentAsString();
	}
}
