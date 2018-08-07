package test.cjh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.cjh.controller.TestContoller;
import test.cjh.controller.TestContoller2;

@Service
public class TestService {

	private @Autowired(required = false) TestContoller testContoller;
	
	private @Autowired(required = false) TestContoller2 testContoller2;

	public TestContoller getControllerInstance() {
		return this.testContoller;
	}

	public TestContoller2 getControllerInstance2() {
		return this.testContoller2;
	}
}
