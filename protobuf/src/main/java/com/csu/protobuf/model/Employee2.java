package com.csu.protobuf.model;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ProtobufClass
public class Employee2 implements Serializable {
    @Protobuf(order = 1)
    private int employeeId;

    @Protobuf(order = 2)
    private String employeeName;

    @Protobuf(order = 3)
    private transient String department;

    @Singular
    @Protobuf(order = 4, fieldType = FieldType.STRING)
    private List<String> features;

    @Protobuf(order = 5)
    private Employee2 friend;

//    @Singular
//    @Protobuf(order = 6, fieldType = FieldType.MAP)
//    private Map<String, String> balls;
}
