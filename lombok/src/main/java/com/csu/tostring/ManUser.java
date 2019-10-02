package com.csu.tostring;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
public class ManUser extends User {

    private List<String> balls;

    @Builder
    public ManUser(int age, int sex, String name, @Singular List<String> features, @Singular
            List<String> balls) {
        super(age, sex, name, features);
        this.balls = balls;
    }
}
