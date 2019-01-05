package model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
public class WomanUser extends User {

    private List<String> buys;

    @Builder
    public WomanUser(int age, int sex, String name, @Singular List<String> features, @Singular List<String> buys) {
        super(age, sex, name, features);
        this.buys = buys;
    }
}
