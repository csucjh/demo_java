package com.csu.protobuf.serializer;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.csu.protobuf.model.Employee;

import java.io.IOException;

public class ProtobufSerializer {

    public static byte[] serialize(Employee employee) {
        try {
            Codec<Employee> codec = ProtobufProxy.create(Employee.class);
            return codec.encode(employee);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> T deserialize(byte[] employeeArray, Class<T> cls) {
        try {
            Codec<T> codec = ProtobufProxy.create(cls);
            return codec.decode(employeeArray);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {

        Employee employee = Employee.builder()
                .employeeId(1)
                .employeeName("赵新国")
                .department("软件工程师")
                .build();

        // 序列化
        byte[] serialize = serialize(employee);
        System.out.println(serialize);

        // 反序列化
        Employee deserialize = deserialize(serialize, Employee.class);
        System.out.println(deserialize.toString());

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 * 1000 * 1000; i++) {
            deserialize(serialize(Employee.builder()
                    .employeeId(i)
                    .employeeName("赵新国" + i)
                    .department("软件工程师" + i)
                    .build()), Employee.class);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));

    }
}
