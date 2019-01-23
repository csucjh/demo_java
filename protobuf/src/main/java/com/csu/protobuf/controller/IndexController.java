package com.csu.protobuf.controller;

import com.csu.protobuf.model.Employee;
import com.csu.protobuf.model.Employee2;
import com.csu.protobuf.serializer.ProtobufSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/getEmployee")
    @ResponseBody
    public Employee2 getEmployee() throws IOException {
        Employee employee = Employee.builder()
                .employeeId(1)
                .employeeName("赵新国")
                .department("软件工程师")
                .ball("one", "羽毛球")
                .feature("腼腆")
                .friend(Employee.builder().employeeName("好朋友").build())
                .build();

        byte[] buf = ProtobufSerializer.serialize(employee);

        Employee2 emp = ProtobufSerializer.deserialize(buf, Employee2.class);

        return emp;
    }
}
