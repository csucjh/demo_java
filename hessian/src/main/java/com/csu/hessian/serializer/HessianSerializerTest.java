package com.csu.hessian.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.csu.hessian.Employee;
import lombok.Cleanup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializerTest {

    public static byte[] serialize(Employee employee) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            // Hessian的序列化输出
            @Cleanup HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(employee);
            return byteArrayOutputStream.toByteArray();
        } finally {

        }
    }

    /**
     * Hessian实现反序列化
     *
     * @param employeeArray
     * @return
     */
    public static Employee deserialize(byte[] employeeArray) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(employeeArray)) {
            @Cleanup HessianInput hessianInput = new HessianInput(byteArrayInputStream);
            return (Employee) hessianInput.readObject();
        } finally {

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
        Employee deserialize = deserialize(serialize);
        System.out.println(deserialize.toString());


        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 1000; i++) {
            deserialize(serialize(Employee.builder()
                    .employeeId(i)
                    .employeeName("赵新国" + i)
                    .department("软件工程师" + i)
                    .build()));
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }
}
