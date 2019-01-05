package model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int age;

    private int sex;

    private String name;

    private List<String> features;

}
